package comun;

import java.io.PrintStream;
import java.util.Calendar;

public class ErrorSistema
{
    public ErrorSistema()
    {
        numError = 0;
        cadError = "";
        cadSQL = "";
    }

    public ErrorSistema(int num, String cad, String sql)
    {
        numError = num;
        cadError = cad;
        cadSQL = sql;
    }

    public ErrorSistema(int num, String cad)
    {
        numError = num;
        cadError = cad;
        cadSQL = "";
    }

    public String getCadenaError()
    {
        return cadError;
    }

    public String getCadenaSQL()
    {
        return cadSQL;
    }

    public int getNumeroError()
    {
        return numError;
    }

    public void setCadenaError(String cadError)
    {
        this.cadError = cadError;
    }

    public void setCadenaSQL(String cadSQL)
    {
        this.cadSQL = cadSQL;
    }

    public void setNumeroError(int numError)
    {
        this.numError = numError;
    }

    public void out()
    {
        Calendar cal = Calendar.getInstance();
        System.out.println("-----------------------------------------------------------------");
        System.out.println(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(Utilerias.rellenarCeros(String.valueOf(cal.get(5)), 2))))).append("/").append(Utilerias.rellenarCeros(String.valueOf(cal.get(2) + 1), 2)).append("/").append(cal.get(1)).append(" ").append(Utilerias.rellenarCeros(String.valueOf(cal.get(11)), 2)).append(":").append(Utilerias.rellenarCeros(String.valueOf(cal.get(12)), 2)))));
        System.out.println("Numero :".concat(String.valueOf(String.valueOf(String.valueOf(numError)))));
        System.out.println("Cadena :".concat(String.valueOf(String.valueOf(cadError))));
        System.out.println("SQL    :".concat(String.valueOf(String.valueOf(cadSQL))));
    }

    protected int numError;
    protected String cadError;
    protected String cadSQL;
}