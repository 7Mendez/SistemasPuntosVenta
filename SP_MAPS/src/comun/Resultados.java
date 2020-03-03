package comun;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class Resultados
{

    public Resultados()
    {
        close();
    }

    public void close()
    {
        if(columnas != null)
        {
            columnas.clear();
            for(int i = 0; i < filas.size(); i++)
                ((Vector)filas.get(i)).clear();

            filas.clear();
        }
        columnas = null;
        filas = null;
        numRec = -1;
    }

    public void setDatos(ResultSet rs)
    {
        ErrorSistema err = new ErrorSistema();
        filas = new Vector();
        try
        {
            columnas = new Vector();
            int num = rs.getMetaData().getColumnCount();
            for(int i = 1; i < num + 1; i++)
                columnas.add(rs.getMetaData().getColumnName(i).toLowerCase());

            Vector v;
            for(; rs.next(); filas.add(v))
            {
                v = new Vector();
                for(int i = 1; i < num + 1; i++)
                {
                    Object obj = rs.getObject(i);
                    if(rs.wasNull())
                    {
                        String paso = "***NULL***";
                        obj = paso;
                    }
                    v.add(obj);
                }

            }

        }
        catch(SQLException e)
        {
            err.setNumeroError(e.getErrorCode());
            err.setCadenaError(e.toString());
            err.setCadenaSQL("");
            err.out();
        }
    }

    public int recordCount()
    {
        if(filas != null)
            return filas.size();
        else
            return 0;
    }

    public boolean next()
    {
        if(numRec < filas.size() - 1)
        {
            numRec++;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean previous()
    {
        if(numRec > 0)
        {
            numRec--;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean first()
    {
        if(filas.size() > 0)
        {
            numRec = 0;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean beforeFirst()
    {
        if(filas.size() > 0)
        {
            numRec = -1;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean last()
    {
        if(filas.size() > 0)
        {
            numRec = filas.size() - 1;
            return true;
        } else
        {
            return false;
        }
    }

    public int getInt(int col, int defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        Object i = v.get(col);
        String tipo = i.getClass().getName();
        if(noEsNulo(i))
        {
            if(tipo.equals("java.lang.Integer"))
                return ((Integer)i).intValue();
            else
                return ((BigDecimal)i).intValue();
        } else
        {
            return defVal;
        }
    }

    public int getInt(int col)
    {
        return getInt(col, 0);
    }

    public int getInt(String strCol, int defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getInt(nCol, defVal);
    }

    public int getInt(String strCol)
    {
        return getInt(strCol, 0);
    }

    public String getString(int col, String defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        String cad = (String)v.get(col);
        if(cad.equals("***NULL***"))
            return defVal;
        else
            return cad;
    }

    public String getString(int col)
    {
        return getString(col, "");
    }

    public String getString(String strCol, String defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getString(nCol, defVal);
    }

    public String getString(String strCol)
    {
        return getString(strCol, "");
    }

    public float getFloat(int col, float defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        Object i = v.get(col);
        String tipo = i.getClass().getName();
        if(noEsNulo(i))
        {
            if(tipo.equals("java.lang.Float"))
                return ((Float)i).floatValue();
            else
                return ((BigDecimal)i).floatValue();
        } else
        {
            return defVal;
        }
    }

    public float getFloat(int col)
    {
        return getFloat(col, 0.0F);
    }

    public float getFloat(String strCol, float defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getFloat(nCol, defVal);
    }

    public float getFloat(String strCol)
    {
        return getFloat(strCol, 0.0F);
    }

    public double getDouble(int col, double defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        Object i = v.get(col);
        String tipo = i.getClass().getName();
        if(noEsNulo(i))
        {
            if(tipo.equals("java.lang.Double"))
                return ((Double)i).doubleValue();
            else
                return (double)((BigDecimal)i).intValue();
        } else
        {
            return defVal;
        }
    }

    public double getDouble(int col)
    {
        return getDouble(col, 0.0D);
    }

    public double getDouble(String strCol, double defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getDouble(nCol, defVal);
    }

    public double getDouble(String strCol)
    {
        return getDouble(strCol, 0.0D);
    }

    public Calendar getCalendar(int col, Calendar defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        if(noEsNulo(v.get(col)))
        {
            Timestamp d = (Timestamp)v.get(col);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c;
        } else
        {
            return defVal;
        }
    }
    

    public Calendar getCalendar(int col)
    {
        return getCalendar(col, Calendar.getInstance());
    }

    public Calendar getCalendar(String strCol, Calendar defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getCalendar(nCol, defVal);
    }

    public Calendar getCalendar(String strCol)
    {
        return getCalendar(strCol, Calendar.getInstance());
    }
    
    
    

    public boolean getBoolean(int col, boolean defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        if(noEsNulo(v.get(col)))
        {
            Boolean i = (Boolean)v.get(col);
            return i.booleanValue();
        } else
        {
            return defVal;
        }
    }

    public boolean getBoolean(int col)
    {
        return getBoolean(col, false);
    }

    public boolean getBoolean(String strCol, boolean defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getBoolean(nCol, defVal);
    }

    public boolean getBoolean(String strCol)
    {
        return getBoolean(strCol, false);
    }

    public long getLong(int col, long defVal)
    {
        Vector v = (Vector)filas.get(numRec);
        if(!noEsNulo(v.get(col)))
        {
            return defVal;
        } else
        {
            Long i = (Long)v.get(col);
            return i.longValue();
        }
    }

    public long getLong(int col)
    {
        return getLong(col, 0L);
    }

    public long getLong(String strCol, long defVal)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getLong(nCol, defVal);
    }

    public long getLong(String strCol)
    {
        return getLong(strCol, 0L);
    }

    public boolean isNull(int col)
    {
        Vector v = (Vector)filas.get(numRec);
        return !noEsNulo(v.get(col));
    }

    public boolean isNull(String strCol)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return isNull(nCol);
    }

    public Object getObject(int col)
    {
        Vector v = (Vector)filas.get(numRec);
        return v.get(col);
    }

    public Object getObject(String strCol)
    {
        int nCol = columnas.indexOf(strCol.toLowerCase());
        return getObject(nCol);
    }

    private boolean noEsNulo(Object obj)
    {
        String tipo = obj.getClass().getName();
        if(!tipo.equals("java.lang.String"))
            return true;
        String cad = (String)obj;
        return !cad.equals("***NULL***");
    }

    Vector columnas;
    Vector filas;
    int numRec;
}