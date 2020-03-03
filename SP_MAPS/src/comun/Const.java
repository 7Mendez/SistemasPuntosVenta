package comun;

public class Const
{

    public Const()
    {
    }

    public static final String getMes(int m)
    {
        if(m >= 1 && m <= 12)
            return mesC[m - 1];
        else
            return "";
    }

    public static final String getMes(int m, int tipo)
    {
        if(m >= 1 && m <= 12)
        {
            if(tipo == 0)
                return mesC[m - 1].toUpperCase();
            else
                return mesC[m - 1].toLowerCase();
        } else
        {
            return "";
        }
    }

    public static final String getMesAbreviado(int m)
    {
        if(m >= 1 && m <= 12)
            return mesA[m - 1];
        else
            return "";
    }

    public static final String getMesAbreviado(int m, int tipo)
    {
        if(m >= 1 && m <= 12)
        {
            if(tipo == 0)
                return mesA[m - 1].toUpperCase();
            else
                return mesA[m - 1].toLowerCase();
        } else
        {
            return "";
        }
    }

    public static final String getDia(int m)
    {
        if(m >= 1 && m <= 7)
            return diaC[m - 1];
        else
            return "";
    }

    public static final String getDia(int m, int tipo)
    {
        if(m >= 1 && m <= 7)
        {
            if(tipo == 0)
                return diaC[m - 1].toUpperCase();
            else
                return diaC[m - 1].toLowerCase();
        } else
        {
            return "";
        }
    }

    public static final String getDiaAbreviado(int m)
    {
        if(m >= 1 && m <= 7)
            return diaA[m - 1];
        else
            return "";
    }

    public static final String getDiaAbreviado(int m, int tipo)
    {
        if(m >= 1 && m <= 7)
        {
            if(tipo == 0)
                return diaA[m - 1].toUpperCase();
            else
                return diaA[m - 1].toLowerCase();
        } else
        {
            return "";
        }
    }

    private static final String mesC[] = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
        "Noviembre", "Diciembre"
    };
    private static final String mesA[] = {
        "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct",
        "nov", "dic"
    };
    private static final String diaC[] = {
        "Lunes", "Martes", "Mi\351rcoles", "Jueves", "Viernes", "S\341bado", "Domingo"
    };
    private static final String diaA[] = {
        "lun", "mar", "mie", "jue", "vie", "sab", "dom"
    };
    
    public static final int ENERO = 1;
    public static final int FEBRERO = 2;
    public static final int MARZO = 3;
    public static final int ABRIL = 4;
    public static final int MAYO = 5;
    public static final int JUNIO = 6;
    public static final int JULIO = 7;
    public static final int AGOSTO = 8;
    public static final int SEPTIEMBRE = 9;
    public static final int OCTUBRE = 10;
    public static final int NOVIEMBRE = 11;
    public static final int DICIEMBRE = 12;
    public static final int LUNES = 1;
    public static final int MARTES = 2;
    public static final int MIERCOLES = 3;
    public static final int JUEVES = 4;
    public static final int VIERNES = 5;
    public static final int SABADO = 6;
    public static final int DOMINGO = 7;
    public static final int MAYUSCULAS = 0;
    public static final int MINUSCULAS = 1;
    
    public static final int SIN_ERROR=0;
    public static final int ERROR_SQL_BORRA=1;
    public static final int ERROR_SQL_CARGA=2;
    public static final int ERROR_SQL_GRABA=3;
    

}