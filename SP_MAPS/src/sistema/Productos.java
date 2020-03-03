package sistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import comun.Const;
import comun.Resultados;
import comun.UtilDB;
import comun.Utilerias;

public class Productos {
	protected int cveProductos;
	protected String Nombre;
	protected String Imagen;
	protected int Precio;
	
	private boolean _existe;
	
	public Productos()
	{
		clear();
	}
	public Productos (int xcveProductos){
		clear();
		setCveProductos(xcveProductos);
	}
	
	public void clear()
	{
		_existe = false;
		cveProductos = 0;
		Nombre = "";
		Imagen = "";
		Precio = 0;
	}
	
	/*
	 * Get y Set Generados
	 */
	
	
	 public int getCveProductos() {
		return cveProductos;
	}

	public void setCveProductos(int cveProductos) {
		this.cveProductos = cveProductos;
		carga();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public int getPrecio() {
		return Precio;
	}

	public void setPrecio(int precio) {
		Precio = precio;
	}

	public boolean is_existe() {
		return _existe;
	}

	public void set_existe(boolean _existe) {
		this._existe = _existe;
	}
	
	public int carga()
	{
		int res = Const.SIN_ERROR;
		String sql = "select * from producto where cve_p = " + String.valueOf(cveProductos);
		Resultados rs = UtilDB.ejecutaConsulta(sql);
		
		while (rs.next()){
			carga(rs);
		}
		return res;
	}
	
	
	public int carga(Resultados rs)
	 {
		 int res = Const.SIN_ERROR;
		 cveProductos= rs.getInt("cve_p");
		 Nombre = rs.getString("nombre");
		 Precio = rs.getInt("precio");
		 Imagen = rs.getString("imagen");

		 _existe = true;
		 return res;
	 }
	 
	 public int graba()
	 {
		 int res = Const.SIN_ERROR;
		 Connection con = null;
		 Statement s = null;
		 try
		 {
			 con = UtilDB.getConnection();
			 String sql;
			 if (!_existe)
			 {
				 sql = "insert into producto (cve_p,nombre, precio,imagen ) values ( " +
			                                 String.valueOf(cveProductos) + ",  " + 
			                                 Utilerias.CadenaEncomillada(Nombre).trim() + ",  " +
			                                 String.valueOf(Precio) + ",  "   + 
					                         Utilerias.CadenaEncomillada(Imagen).trim() + "  )";
			 } 
			 else{
				 sql = "update producto set " +
			           "cve_p=" + String.valueOf(cveProductos) + ",  " +
			           "nombre=" + Utilerias.CadenaEncomillada(Nombre).trim() + ",  " +
			           "precio=" + String.valueOf(Precio) + ", " +
			           "imagen=" + Utilerias.CadenaEncomillada(Imagen).trim() + ",  " +
			           "  where cve_p=" + String.valueOf(cveProductos);
				
			 }
			 s = con.createStatement();
			 s.executeUpdate(sql);
		 }
		 catch (SQLException sqe){
			 System.out.println (sqe.getMessage());
			 res = Const.ERROR_SQL_BORRA;
		 }
		 finally
		 {
		   try { s.close(); con.close();}
		   catch (SQLException ex){}
		 }
		return res;
	 }
	  public int borra() { return Const.ERROR_SQL_BORRA;}
	
	
}
