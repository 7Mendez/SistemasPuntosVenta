package comun;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Locale;


public class MessageFormat extends Format
{

    private MessageFormat(String pattern, Locale loc)
    {
        locale = Locale.getDefault();
        this.pattern = "";
        formats = new Format[100];
        offsets = new int[100];
        argumentNumbers = new int[100];
        maxOffset = -1;
        locale = (Locale)loc.clone();
        applyPattern(pattern);
    }

    public MessageFormat(String pattern)
    {
        locale = Locale.getDefault();
        this.pattern = "";
        formats = new Format[100];
        offsets = new int[100];
        argumentNumbers = new int[100];
        maxOffset = -1;
        applyPattern(pattern);
    }

    public void setLocale(Locale theLocale)
    {
        locale = theLocale;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void applyPattern(String newPattern)
    {
        StringBuffer segments[] = new StringBuffer[4];
        for(int i = 0; i < segments.length; i++)
            segments[i] = new StringBuffer();

        int part = 0;
        int formatNumber = 0;
        boolean inQuote = false;
        int braceStack = 0;
        maxOffset = -1;
        for(int i = 0; i < newPattern.length(); i++)
        {
            char ch = newPattern.charAt(i);
            if(part == 0)
            {
                if(ch == '\'')
                {
                    if(i + 1 < newPattern.length() && newPattern.charAt(i + 1) == '\'')
                    {
                        segments[part].append(ch);
                        i++;
                    } else
                    {
                        inQuote = !inQuote;
                    }
                    continue;
                }
                if(ch == '{' && !inQuote)
                    part = 1;
                else
                    segments[part].append(ch);
                continue;
            }
            if(inQuote)
            {
                segments[part].append(ch);
                if(ch == '\'')
                    inQuote = false;
                continue;
            }
            switch(ch)
            {
            case 44: // ','
                if(part < 3)
                    part++;
                else
                    segments[part].append(ch);
                break;

            case 123: // '{'
                braceStack++;
                segments[part].append(ch);
                break;

            case 125: // '}'
                if(braceStack == 0)
                {
                    part = 0;
                    makeFormat(i, formatNumber, segments);
                    formatNumber++;
                } else
                {
                    braceStack--;
                    segments[part].append(ch);
                }
                break;

            case 39: // '\''
                inQuote = true;
                // fall through

            default:
                segments[part].append(ch);
                break;
            }
        }

        if(braceStack == 0 && part != 0)
        {
            maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        } else
        {
            pattern = segments[0].toString();
            return;
        }
    }

    public String toPattern()
    {
        int lastOffset = 0;
        StringBuffer result = new StringBuffer();
        for(int i = 0; i <= maxOffset; i++)
        {
            copyAndFixQuotes(pattern, lastOffset, offsets[i], result);
            lastOffset = offsets[i];
            result.append('{');
            result.append(argumentNumbers[i]);
            if(formats[i] != null)
                if(formats[i] instanceof DecimalFormat)
                {
                    if(formats[i].equals(NumberFormat.getInstance(locale)))
                        result.append(",number");
                    else
                    if(formats[i].equals(NumberFormat.getCurrencyInstance(locale)))
                        result.append(",number,currency");
                    else
                    if(formats[i].equals(NumberFormat.getPercentInstance(locale)))
                        result.append(",number,percent");
                    else
                    if(formats[i].equals(getIntegerFormat(locale)))
                        result.append(",number,integer");
                    else
                        result.append(",number,".concat(String.valueOf(String.valueOf(((DecimalFormat)formats[i]).toPattern()))));
                } else
                if(formats[i] instanceof SimpleDateFormat)
                {
                    if(formats[i].equals(DateFormat.getDateInstance(2, locale)))
                        result.append(",date");
                    else
                    if(formats[i].equals(DateFormat.getDateInstance(3, locale)))
                        result.append(",date,short");
                    else
                    if(formats[i].equals(DateFormat.getDateInstance(2, locale)))
                        result.append(",date,medium");
                    else
                    if(formats[i].equals(DateFormat.getDateInstance(1, locale)))
                        result.append(",date,long");
                    else
                    if(formats[i].equals(DateFormat.getDateInstance(0, locale)))
                        result.append(",date,full");
                    else
                    if(formats[i].equals(DateFormat.getTimeInstance(2, locale)))
                        result.append(",time");
                    else
                    if(formats[i].equals(DateFormat.getTimeInstance(3, locale)))
                        result.append(",time,short");
                    else
                    if(formats[i].equals(DateFormat.getTimeInstance(2, locale)))
                        result.append(",time,medium");
                    else
                    if(formats[i].equals(DateFormat.getTimeInstance(1, locale)))
                        result.append(",time,long");
                    else
                    if(formats[i].equals(DateFormat.getTimeInstance(0, locale)))
                        result.append(",time,full");
                    else
                        result.append(",date,".concat(String.valueOf(String.valueOf(((SimpleDateFormat)formats[i]).toPattern()))));
                } else
                if(formats[i] instanceof ChoiceFormat)
                    result.append(",choice,".concat(String.valueOf(String.valueOf(((ChoiceFormat)formats[i]).toPattern()))));
            result.append('}');
        }

        copyAndFixQuotes(pattern, lastOffset, pattern.length(), result);
        return result.toString();
    }

    public void setFormats(Format newFormats[])
    {
        try
        {
            formats = (Format[])newFormats.clone();
        }
        catch(Exception e)
        {
            return;
        }
    }

    public void setFormat(int variable, Format newFormat)
    {
        formats[variable] = newFormat;
    }

    public Format[] getFormats()
    {
        try
        {
            Format aformat[] = (Format[])formats.clone();
            return aformat;
        }
        catch(Exception e)
        {
            Format aformat1[] = formats;
            return aformat1;
        }
    }

    public final StringBuffer format(Object source[], StringBuffer result, FieldPosition ignore)
    {
        return format(source, result, ignore, 0);
    }

    public static String format(String pattern, Object arguments[])
    {
        MessageFormat temp = new MessageFormat(pattern);
        return temp.format(((Object) (arguments)));
    }

    public final StringBuffer format(Object source, StringBuffer result, FieldPosition ignore)
    {
        return format((Object[])source, result, ignore, 0);
    }

    public Object[] parse(String source, ParsePosition status)
    {
        Object empty[] = new Object[0];
        if(source == null)
            return empty;
        Object resultArray[] = new Object[10];
        int patternOffset = 0;
        int sourceOffset = status.getIndex();
        ParsePosition tempStatus = new ParsePosition(0);
        for(int i = 0; i <= maxOffset; i++)
        {
            int len = offsets[i] - patternOffset;
            if(len == 0 || pattern.regionMatches(patternOffset, source, sourceOffset, len))
            {
                sourceOffset += len;
                patternOffset += len;
            } else
            {
                status.setErrorIndex(sourceOffset);
                return null;
            }
            if(formats[i] == null)
            {
                int tempLength = i == maxOffset ? pattern.length() : offsets[i + 1];
                int next;
                if(patternOffset >= tempLength)
                    next = source.length();
                else
                    next = source.indexOf(pattern.substring(patternOffset, tempLength), sourceOffset);
                if(next < 0)
                {
                    status.setErrorIndex(sourceOffset);
                    return null;
                }
                String strValue = source.substring(sourceOffset, next);
                if(!strValue.equals(String.valueOf(String.valueOf((new StringBuffer("{")).append(argumentNumbers[i]).append("}")))))
                    resultArray[argumentNumbers[i]] = source.substring(sourceOffset, next);
                sourceOffset = next;
                continue;
            }
            tempStatus.setIndex(sourceOffset);
            resultArray[argumentNumbers[i]] = formats[i].parseObject(source, tempStatus);
            if(tempStatus.getIndex() == sourceOffset)
            {
                status.setErrorIndex(sourceOffset);
                return null;
            }
            sourceOffset = tempStatus.getIndex();
        }

        int len = pattern.length() - patternOffset;
        if(len == 0 || pattern.regionMatches(patternOffset, source, sourceOffset, len))
        {
            status.setIndex(sourceOffset + len);
        } else
        {
            status.setErrorIndex(sourceOffset);
            return null;
        }
        return resultArray;
    }

    public Object[] parse(String source)
        throws ParseException
    {
        ParsePosition status = new ParsePosition(0);
        Object result[] = parse(source, status);
        if(status.getIndex() == 0)
            throw new ParseException("MessageFormat parse error!", status.getErrorIndex());
        else
            return result;
    }

    public Object parseObject(String text, ParsePosition status)
    {
        return ((Object) (parse(text, status)));
    }

    public Object clone()
    {
        MessageFormat other = (MessageFormat)super.clone();
        other.formats = (Format[])formats.clone();
        for(int i = 0; i < formats.length; i++)
            if(formats[i] != null)
                other.formats[i] = (Format)formats[i].clone();

        other.offsets = (int[])offsets.clone();
        other.argumentNumbers = (int[])argumentNumbers.clone();
        return other;
    }

 

    public int hashCode()
    {
        return pattern.hashCode();
    }

    private StringBuffer format(Object arguments[], StringBuffer result, FieldPosition status, int recursionProtection)
    {
        int lastOffset = 0;
        for(int i = 0; i <= maxOffset; i++)
        {
            result.append(pattern.substring(lastOffset, offsets[i]));
            lastOffset = offsets[i];
            int argumentNumber = argumentNumbers[i];
            if(arguments == null || argumentNumber >= arguments.length)
            {
                result.append(String.valueOf(String.valueOf((new StringBuffer("{")).append(argumentNumber).append("}"))));
                continue;
            }
            Object obj = arguments[argumentNumber];
            boolean tryRecursion = false;
            String arg;
            if(obj == null)
                arg = "null";
            else
            if(formats[i] != null)
            {
                arg = formats[i].format(obj);
                tryRecursion = formats[i] instanceof ChoiceFormat;
            } else
            if(obj instanceof Number)
                arg = NumberFormat.getInstance(locale).format(obj);
            else
            if(obj instanceof Date)
                arg = DateFormat.getDateTimeInstance(3, 3, locale).format(obj);
            else
            if(obj instanceof String)
            {
                arg = (String)obj;
            } else
            {
                arg = obj.toString();
                if(arg == null)
                    arg = "null";
            }
            if(tryRecursion && arg.indexOf('{') >= 0)
            {
                MessageFormat temp = new MessageFormat(arg, locale);
                temp.format(arguments, result, status, recursionProtection);
            } else
            {
                result.append(arg);
            }
        }

        result.append(pattern.substring(lastOffset, pattern.length()));
        return result;
    }

    private void makeFormat(int position, int offsetNumber, StringBuffer segments[])
    {
        int oldMaxOffset = maxOffset;
        try
        {
            int argumentNumber = Integer.parseInt(segments[1].toString());
            if(argumentNumber < 0 || argumentNumber > 99)
                throw new NumberFormatException();
            maxOffset = offsetNumber;
            offsets[offsetNumber] = segments[0].length();
            argumentNumbers[offsetNumber] = argumentNumber;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("argument number too large at ");
        }
        Format newFormat = null;
label0:
        switch(findKeyword(segments[2].toString(), typeList))
        {
        case 0: // '\0'
            break;

        case 1: // '\001'
        case 2: // '\002'
            switch(findKeyword(segments[3].toString(), modifierList))
            {
            case 0: // '\0'
                newFormat = NumberFormat.getInstance(locale);
                break label0;

            case 1: // '\001'
            case 2: // '\002'
                newFormat = NumberFormat.getCurrencyInstance(locale);
                break label0;

            case 3: // '\003'
            case 4: // '\004'
                newFormat = NumberFormat.getPercentInstance(locale);
                break label0;

            case 5: // '\005'
            case 6: // '\006'
                newFormat = getIntegerFormat(locale);
                break label0;

            default:
                newFormat = NumberFormat.getInstance(locale);
                break;
            }
            try
            {
                ((DecimalFormat)newFormat).applyPattern(segments[3].toString());
                break;
            }
            catch(Exception e)
            {
                maxOffset = oldMaxOffset;
            }
            throw new IllegalArgumentException("Pattern incorrect or locale does not support formats, error at ");

        case 3: // '\003'
        case 4: // '\004'
            switch(findKeyword(segments[3].toString(), dateModifierList))
            {
            case 0: // '\0'
                newFormat = DateFormat.getDateInstance(2, locale);
                break label0;

            case 1: // '\001'
            case 2: // '\002'
                newFormat = DateFormat.getDateInstance(3, locale);
                break label0;

            case 3: // '\003'
            case 4: // '\004'
                newFormat = DateFormat.getDateInstance(2, locale);
                break label0;

            case 5: // '\005'
            case 6: // '\006'
                newFormat = DateFormat.getDateInstance(1, locale);
                break label0;

            case 7: // '\007'
            case 8: // '\b'
                newFormat = DateFormat.getDateInstance(0, locale);
                break label0;

            default:
                newFormat = DateFormat.getDateInstance(2, locale);
                break;
            }
            try
            {
                ((SimpleDateFormat)newFormat).applyPattern(segments[3].toString());
                break;
            }
            catch(Exception e)
            {
                maxOffset = oldMaxOffset;
            }
            throw new IllegalArgumentException("Pattern incorrect or locale does not support formats, error at ");

        case 5: // '\005'
        case 6: // '\006'
            switch(findKeyword(segments[3].toString(), dateModifierList))
            {
            case 0: // '\0'
                newFormat = DateFormat.getTimeInstance(2, locale);
                break label0;

            case 1: // '\001'
            case 2: // '\002'
                newFormat = DateFormat.getTimeInstance(3, locale);
                break label0;

            case 3: // '\003'
            case 4: // '\004'
                newFormat = DateFormat.getTimeInstance(2, locale);
                break label0;

            case 5: // '\005'
            case 6: // '\006'
                newFormat = DateFormat.getTimeInstance(1, locale);
                break label0;

            case 7: // '\007'
            case 8: // '\b'
                newFormat = DateFormat.getTimeInstance(0, locale);
                break label0;

            default:
                newFormat = DateFormat.getTimeInstance(2, locale);
                break;
            }
            try
            {
                ((SimpleDateFormat)newFormat).applyPattern(segments[3].toString());
                break;
            }
            catch(Exception e)
            {
                maxOffset = oldMaxOffset;
            }
            throw new IllegalArgumentException("Pattern incorrect or locale does not support formats, error at ");

        case 7: // '\007'
        case 8: // '\b'
            try
            {
                newFormat = new ChoiceFormat(segments[3].toString());
                break;
            }
            catch(Exception e)
            {
                maxOffset = oldMaxOffset;
            }
            throw new IllegalArgumentException("Choice Pattern incorrect, error at ");

        default:
            maxOffset = oldMaxOffset;
            throw new IllegalArgumentException("unknown format type at ");
        }
        formats[offsetNumber] = newFormat;
        segments[1].setLength(0);
        segments[2].setLength(0);
        segments[3].setLength(0);
    }

    private static final int findKeyword(String s, String list[])
    {
        s = s.trim().toLowerCase();
        for(int i = 0; i < list.length; i++)
            if(s.equals(list[i]))
                return i;

        return -1;
    }

    NumberFormat getIntegerFormat(Locale locale)
    {
        NumberFormat temp = NumberFormat.getInstance(locale);
        if(temp instanceof DecimalFormat)
        {
            DecimalFormat temp2 = (DecimalFormat)temp;
            temp2.setMaximumFractionDigits(0);
            temp2.setDecimalSeparatorAlwaysShown(false);
            temp2.setParseIntegerOnly(true);
        }
        return temp;
    }

    private static final void copyAndFixQuotes(String source, int start, int end, StringBuffer target)
    {
        for(int i = start; i < end; i++)
        {
            char ch = source.charAt(i);
            if(ch == '{')
            {
                target.append("'{'");
                continue;
            }
            if(ch == '}')
            {
                target.append("'}'");
                continue;
            }
            if(ch == '\'')
                target.append("''");
            else
                target.append(ch);
        }

    }

    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException
    {
        in.defaultReadObject();
        boolean isValid = maxOffset >= -1 && maxOffset < 100 && formats.length == 100 && offsets.length == 100 && argumentNumbers.length == 100;
        if(isValid)
        {
            int lastOffset = pattern.length() + 1;
            int i = maxOffset;
            do
            {
                if(i < 0)
                    break;
                if(offsets[i] < 0 || offsets[i] > lastOffset)
                {
                    isValid = false;
                    break;
                }
                lastOffset = offsets[i];
                i--;
            } while(true);
        }
        if(!isValid)
            throw new InvalidObjectException("Could not reconstruct MessageFormat from corrupt stream.");
        else
            return;
    }

    private Locale locale;
    private String pattern;
    private static final int MAX_ARGUMENTS = 100;
    private Format formats[];
    private int offsets[];
    private int argumentNumbers[];
    private int maxOffset;
    private static final String typeList[] = {
        "", "", "number", "", "date", "", "time", "", "choice"
    };
    private static final String modifierList[] = {
        "", "", "currency", "", "percent", "", "integer"
    };
    private static final String dateModifierList[] = {
        "", "", "short", "", "medium", "", "long", "", "full"
    };

}