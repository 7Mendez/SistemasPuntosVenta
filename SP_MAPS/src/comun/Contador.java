package comun;

public class Contador
{
    public Contador()
    {
    }

    public static boolean getArrancado()
    {
        boolean res = arrancado;
        if(!arrancado)
            arrancado = true;
        return res;
    }



    private static boolean arrancado = false;
    private static int defExamen = 0;

}