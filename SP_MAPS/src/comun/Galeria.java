package comun;

import java.util.Calendar;

/**
 * <p>Title        : Persona</p>
 * <p>Description  : Clase para el control de la informaci&oacute;n de una persona</p>
 * <p>Copyright    : Copyright (c) 2004</p>
 * <p>Company      : Universidad Tecnol&oacute;gica de Tabasco</p>
 * @author           Octavio Elias Sanchez Aquino
 * @version          1.0
 */

public class Galeria implements IGrabable
{
    protected int cveEvento;
    protected int consecutivo;
    protected String ruta;
    protected String comentario;
    private boolean _existe;

    /**
     * Constructor por defecto de la clase
     */
    public Galeria()
    {
        limpiar();
    }

    /**
     * M&eacute;todo para limpiar los miembros de la clase
     */
    public void limpiar()
    {
     _existe = false;
    }


    public String getRuta()
    {
        return ruta;
    }

    public int getConsecutivo()
    {
      int sigue=0;
      Resultados rsMaximo=UtilDB.ejecutaConsulta("Select Max(Consecutivo) + 1 maximo " +
          "From Galeria " +
          "Where Cve_Evento=" + cveEvento);
      while (rsMaximo.next())
      {
        sigue=rsMaximo.getInt("maximo");
      }
      if (sigue==0)
      {
        sigue=1;
      }
      return sigue;
    }

    public int getCveFoto()
    {
      int sigue=0;
      Resultados rsMaximo=UtilDB.ejecutaConsulta("Select Max(Cve_Foto) + 1 maximo From Galeria");
      while (rsMaximo.next())
      {
        sigue=rsMaximo.getInt("maximo");
      }
      if (sigue==0)
      {
        sigue=1;
      }
      return sigue;
    }

    public String getComentario(){
      return this.comentario;
    }


    public int getEvento(){
      return cveEvento;
    }

    public void setComentario(String xcomentario){
      this.comentario = xcomentario;
    }

    public ErrorSistema grabar()
    {
        ErrorSistema err = new ErrorSistema();
        err = validar();
        if ( err.getNumeroError() == 0 )
        {
            if ( !_existe )
            {
              String sql = "Insert into Galeria ( Cve_Evento, Ruta, Comentario, Consecutivo, Cve_Foto) " +
                           "Values (" +
                           String.valueOf(cveEvento) + "," +
                           Utilerias.CadenaEncomillada(ruta) + "," +
                           Utilerias.CadenaEncomillada(comentario) + "," +
                           String.valueOf(getConsecutivo()) + "," +
                           String.valueOf(getCveFoto()) + ")";
              err = UtilDB.ejecutaSQL(sql);
            }
            else
            {
              String sql = "Insert into Galeria (Cve_Evento, Ruta, Comentario, Consecutivo, Cve_Foto) " +
                           "Values (" +
                           String.valueOf(cveEvento) + "," +
                           Utilerias.CadenaEncomillada(ruta) + "," +
                           Utilerias.CadenaEncomillada(comentario) + "," +
                           String.valueOf(getConsecutivo()) + "," +
                           String.valueOf(getCveFoto()) + ")";
              err = UtilDB.ejecutaSQL(sql);
            }
        }
        return err;
    }

    /**
     * Valida la informaci&oacute;n contenida en el objeto
     * @return La condici&oacute;n de error
     */
    private ErrorSistema validar()
    {
        ErrorSistema err = new ErrorSistema();
        return err;
    }

    /**
     * Borra al registro de la  de datos,
     * no aplicable para este objeto
     * @return La condici&oacute;n de error
     */
    public ErrorSistema borrar()
    {
        // No se debe de borrar un registro de persona
        ErrorSistema err = new ErrorSistema();
        err.setCadenaError("No se puede borrar la informaci�n de una persona");
        err.setNumeroError(-1);
        return err;
    }

    /**
     * Carga la informaci&oacuten de la  de datos al objeto
     */
    public void cargar()
    {
    }

    /**
     * Carga la informaci&oacute;n de un objeto Resultados al objeto Persona
     * @param res Objeto resultados
     */
    public void cargar(Resultados res)
    {
    }


    /**
     * Indica si la persona existe ya en la  de datos o no
     * @return La condici&oacute;n de existencia
     */
    public boolean existe()
    {
        return _existe;
    }


    public void setRuta(String xRuta)
    {
      ruta = xRuta;
    }
    public void setEvento(int xEvento)
    {
      cveEvento = xEvento;
    }

}
