package comun;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Vector;
import java.io.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public final class Encriptar
{
    public Encriptar()
    {

    }
	
	public static String encriptarDel(String  cadena)
	{
	    String miOriginal, Encriptada, Temporal;
	
	    miOriginal = "ABCDEFGHIJKLMN-�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890/";
	    Encriptada = "KLO8IGH56fJRX1wDxTUzByF�V7v3MN4�SEWQA-ghklmn0CYpqr92PstuZabcdeijo?";
	    
	    Temporal = "";
	
	    int tamOriginal = miOriginal.length();
	    int tamEncriptada = Encriptada.length();
	    int tamCadena = cadena.length();
	
	    int encontrado = 0;
	    int miMod = 0;
	
		for (int i=0; i<(tamCadena); i++)
	    //r i = 0 To tamCadena - 1
		{
			for (int j=0; j<(tamEncriptada); j++)
			{
	            char cadI = cadena.charAt(i); 
	            char cadO = miOriginal.charAt(j); 
	            if (cadI==cadO) 
	            {
	                if (i % 2 == 0) 
	                {
	                    Temporal = Temporal + Encriptada.charAt(j);
	                }
	                else
	                {
	                    if (j == tamEncriptada - 1) 
	                    {
	                        Temporal = Temporal + Encriptada.charAt(0);
	                    }
	                    else
	                    {
	                        Temporal = Temporal + Encriptada.charAt(j + 1);
	                    }
	                }
	                break;
	            }
			}
		}
	    return Temporal;
	}
	
	
	public static String desEncriptarDel(String  cadena)
	{
	    String miOriginal, Encriptada, Temporal;
	
	    miOriginal = "ABCDEFGHIJKLMN-�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890/";
	    Encriptada = "KLO8IGH56fJRX1wDxTUzByF�V7v3MN4�SEWQA-ghklmn0CYpqr92PstuZabcdeijo?";
	    
	    Temporal = "";
	
	    int tamOriginal = miOriginal.length();
	    int tamEncriptada = Encriptada.length();
	    int tamCadena = cadena.length();
	
	    int encontrado = 0;
	    int miMod = 0;
	
		for (int i=0; i<(tamCadena); i++)
	    //r i = 0 To tamCadena - 1
		{
			for (int j=0; j<(tamEncriptada); j++)
			{
				System.out.println("i=" + i + " j=" + j);
	        //r j = 0 To tamEncriptada - 1
	            char cadI = cadena.charAt(i); 
	            char cadO = miOriginal.charAt(j); 
	            if (cadI==cadO) 
	            {
	                if (i % 2 == 0) 
	                {
	                    Temporal = Temporal + miOriginal.charAt(j);
	                }
	                else
	                {
	                    if (j == tamEncriptada - 1) 
	                    {
	                        Temporal = Temporal + miOriginal.charAt(tamEncriptada - 1);
	                    }
	                    else
	                    {
	                        Temporal = Temporal + miOriginal.charAt(j - 1);
	                    }
	                }
	                break;
	            }
			}
		}
	    return Temporal;
	}	
	
}