package base;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public final class Funciones
{
    public Funciones()
    {

    }
	
    public static String numerosALetras(int valor)
    {
        int digito = 0;
        int primerDigito = 0;
        int segundoDigito = 0;
        int tercerDigito = 0;
        int numeroBloques = 0;
        int bloqueCero = 0;
        String bloque = "";
        String cantidadEnLetra = valor != 0 ? "" : "CERO";
        String Unidades[] = {
            "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ",
            "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE",
            "VEINTIUNO", "VEINTIDOS", "VEINTITRES", "VEINTICUATRO", "VEINTICINCO", "VEINTISEIS", "VEINTISIETE", "VEINTIOCHO", "VEINTINUEVE"
        };
        String Decenas[] = {
            "DIEZ", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA"
        };
        String Centenas[] = {
            "CIENTO", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS"
        };
        numeroBloques = 1;
        if(valor != 0)
            do
            {
                primerDigito = 0;
                segundoDigito = 0;
                tercerDigito = 0;
                bloque = "";
                bloqueCero = 0;
                int i = 1;
                do
                {
                    if(i > 3)
                        break;
                    digito = valor % 10;
                    if(digito != 0)
                        switch(i)
                        {
                        case 1: // '\001'
                            bloque = " ".concat(String.valueOf(String.valueOf(Unidades[digito - 1])));
                            primerDigito = digito;
                            break;

                        case 2: // '\002'
                            if(digito <= 2)
                                bloque = " ".concat(String.valueOf(String.valueOf(Unidades[(digito * 10 + primerDigito) - 1])));
                            else
                                bloque = String.valueOf(String.valueOf((new StringBuffer(" ")).append(Decenas[digito - 1]).append(primerDigito == 0 ? " " : " Y").append(bloque)));
                            segundoDigito = digito;
                            break;

                        case 3: // '\003'
                            bloque = String.valueOf(String.valueOf((new StringBuffer(" ")).append(digito != 1 || primerDigito != 0 || segundoDigito != 0 ? Centenas[digito - 1] : "CIEN ").append(bloque)));
                            tercerDigito = digito;
                            break;
                        }
                    else
                        bloqueCero++;
                    valor = (int)Math.floor(valor / 10);
                    if(valor == 0)
                        break;
                    i++;
                } while(true);
                switch(numeroBloques)
                {
                case 1: // '\001'
                    cantidadEnLetra = bloque;
                    break;

                case 2: // '\002'
                    cantidadEnLetra = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(bloque)))).append(bloqueCero != 3 ? " MIL" : " ").append(cantidadEnLetra)));
                    break;

                case 3: // '\003'
                    cantidadEnLetra = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(bloque)))).append(primerDigito != 1 || segundoDigito != 0 || tercerDigito != 0 ? " MILLONES" : " MILLON").append(cantidadEnLetra)));
                    break;
                }
                numeroBloques++;
            } while(valor != 0);
        return cantidadEnLetra;
    }	
	
    public static String rellenarCeros(String cad, int lng)
    {
        String pattern = "000000000000000000000000000000000";
        if(cad.equals(""))
            return cad;
        else
            return String.valueOf(String.valueOf(String.valueOf(pattern.substring(0, lng - cad.length())))) + String.valueOf(String.valueOf(String.valueOf(cad)));
    }
    
	
	public static String reemplazar(String cadena, String busqueda, String reemplazo) 
	{  
		return cadena.replaceAll(busqueda, reemplazo);
	}

    public static String getCadenaFechaJS(Calendar fecha)
    {
        String res = "";
        res = rellenarCeros(String.valueOf(fecha.get(2) + 1), 2) + "/" +
		      rellenarCeros(String.valueOf(fecha.get(5)), 2) + "/"  +
			  String.valueOf(fecha.get(1));
        return res;
    }
	
    public static String formatoFechaMDY_YMD(String fecha)
    {
      String fechaDB=fecha.substring(6,10) + '-' + fecha.substring(0,2) + "-" + fecha.substring(3,5);
       return fechaDB;
    }

    public static String formatoFechaMDY_DMY(String fecha)
    {
      String fechaDB=fecha.substring(3,5) + '-' + fecha.substring(0,2) + "-" + fecha.substring(6,10);
       return fechaDB;
    }
	
    public static String letraCapital(String cadena) {
		String temporal="";
		String actual="";
		String anterior="";
		for (int x=0; x<cadena.length(); x++)
		{
			actual=String.valueOf(cadena.charAt(x));
			if (x==0)
			{
				temporal+= actual.toUpperCase();
			}
			else
			{
				anterior = String.valueOf(cadena.charAt(x-1));
				if (anterior.equals(" "))
				{
					temporal+= actual.toUpperCase();
				}
				else
				{
					temporal+= actual.toLowerCase();
				}
			}
		}
		
		temporal = temporal.replace(" De ", " de ");
		temporal = temporal.replace(" La ", " la ");
		temporal = temporal.replace(" Los ", " los ");
		temporal = temporal.replace(" El ", " el ");
		temporal = temporal.replace(" Las ", " las ");
		temporal = temporal.replace(" Y ", " y ");
		temporal = temporal.replace("S.a.", "S.A.");
		temporal = temporal.replace("C.v.", "C.V.");
		temporal = temporal.replace(" Sa ", "SA");
		temporal = temporal.replace(" Cv", " CV");

		return temporal;
	}	

}

