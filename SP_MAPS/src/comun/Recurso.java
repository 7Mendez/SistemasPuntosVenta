package comun;

import java.util.*;

public class Recurso
{

    private Recurso(String packageName, Locale loc)
    {
        String bundleName = String.valueOf(String.valueOf(packageName)).concat(".properties");
        if(loc != null)
            bundle = ResourceBundle.getBundle(bundleName, loc);
        else
            bundle = ResourceBundle.getBundle(bundleName);
    }

    private Recurso(String packageName, String fileName, Locale loc)
    {
        String bundleName = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append(".").append(fileName)));
        if(loc != null)
            bundle = ResourceBundle.getBundle(bundleName, loc);
        else
            bundle = ResourceBundle.getBundle(bundleName);
    }

    public String getString(String key)
    {
        if(key == null)
        {
            String msg = "La llave es nula";
            throw new NullPointerException(msg);
        }
        String str = null;
        try
        {
            str = bundle.getString(key);
        }
        catch(MissingResourceException missingresourceexception) { }
        return str;
    }

    public String getString(String key, Object args[])
    {
        String iString = null;
        String value = getString(key);
        try
        {
            Object nonNullArgs[] = args;
            for(int i = 0; i < args.length; i++)
            {
                if(args[i] != null)
                    continue;
                if(nonNullArgs == args)
                    nonNullArgs = (Object[])args.clone();
                nonNullArgs[i] = "null";
            }

            iString = MessageFormat.format(value, nonNullArgs);
        }
        catch(IllegalArgumentException iae)
        {
            StringBuffer buf = new StringBuffer();
            buf.append(value);
            for(int i = 0; i < args.length; i++)
                buf.append(String.valueOf(String.valueOf((new StringBuffer(" arg[")).append(i).append("]=").append(args[i]))));

            iString = buf.toString();
        }
        return iString;
    }

    public String getString(String key, Object arg)
    {
        Object args[] = {
            arg
        };
        return getString(key, args);
    }

    public String getString(String key, Object arg1, Object arg2)
    {
        Object args[] = {
            arg1, arg2
        };
        return getString(key, args);
    }

    public String getString(String key, Object arg1, Object arg2, Object arg3)
    {
        Object args[] = {
            arg1, arg2, arg3
        };
        return getString(key, args);
    }

    public String getString(String key, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        Object args[] = {
            arg1, arg2, arg3, arg4
        };
        return getString(key, args);
    }

    public static synchronized Recurso getManager(String packageName)
    {
        Recurso mgr = (Recurso)managers.get(packageName);
        if(mgr == null)
        {
            mgr = new Recurso(packageName, null);
            managers.put(packageName, mgr);
        }
        return mgr;
    }

    public static synchronized Recurso getManager(String packageName, Locale loc)
    {
        Recurso mgr = null;
        if(loc != null)
            mgr = (Recurso)managers.get(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append("_").append(loc.toString()))));
        else
            mgr = (Recurso)managers.get(packageName);
        if(mgr == null)
        {
            mgr = new Recurso(packageName, loc);
            if(loc != null)
                managers.put(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append("_").append(loc.toString()))), mgr);
            else
                managers.put(packageName, mgr);
        }
        return mgr;
    }

    public static synchronized Recurso getManager(String packageName, String fileName, Locale loc)
    {
        Recurso mgr = null;
        if(loc != null)
            mgr = (Recurso)managers.get(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append(".").append(fileName).append("_").append(loc.toString()))));
        else
            mgr = (Recurso)managers.get(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append(".").append(fileName))));
        if(mgr == null)
        {
            mgr = new Recurso(packageName, fileName, loc);
            if(loc != null)
                managers.put(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append(".").append(fileName).append("_").append(loc.toString()))), mgr);
            else
                managers.put(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(packageName)))).append(".").append(fileName))), mgr);
        }
        return mgr;
    }

    private ResourceBundle bundle;
    private static Hashtable managers = new Hashtable();

}