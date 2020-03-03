package sistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import comun.Const;
import comun.Resultados;
import comun.UtilDB;
import comun.Utilerias;

public class puntosVenta {
	protected int cvePuntosVenta;
	protected String Nombre;
	protected String Direccion;
	protected String Latitud;
	protected String Longitud;
	protected String Telefono;
	private boolean _existe;
	
	public puntosVenta()
	{
		clear();
	}
	public puntosVenta(int xcvePuntosVenta){
		clear();
		setCvePuntosVenta(xcvePuntosVenta);
	}
	
	public void clear(){
		_existe = false;
		Nombre = "";
		Direccion = "";
		Latitud = "";
		Longitud = "";
		Telefono = "";
	}
	
	/*
	 * Get y Set Generados
	 */
	
	
	public int getCvePuntosVenta() {
		return cvePuntosVenta;
	}
	public void setCvePuntosVenta(int cvePuntosVenta) {
		cvePuntosVenta = cvePuntosVenta;
		carga();
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getLatitud() {
		return Latitud;
	}
	public void setLatitud(String latitud) {
		Latitud = latitud;
	}
	public String getLongitud() {
		return Longitud;
	}
	public void setLongitud(String longitud) {
		Longitud = longitud;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
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
		String sql = "select * from puntoventa where cve_pdv = " + String.valueOf(cvePuntosVenta);
		Resultados rs = UtilDB.ejecutaConsulta(sql);
		
		while (rs.next()){
			carga(rs);
		}
		return res;
	}
	
	 public int carga(Resultados rs)
	 {
		 int res = Const.SIN_ERROR;
		 cvePuntosVenta= rs.getInt("cve_pdv");
		 Nombre = rs.getString("nombre");
		 Direccion = rs.getString("direccion");
		 Latitud = rs.getString("latitud");
		 Longitud = rs.getString("longitud");
		 Telefono = rs.getString("telefono");
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
				 sql = "insert into puntoventa (" +
			           "cve_pdv, " + 
			           "nombre, " +
					   "direccion, " +
			           "latitud, " +
					   "longitud, " +
					   "telefono ) values ( " +
			                                 String.valueOf(cvePuntosVenta) + ", " + 
			                                 Utilerias.CadenaEncomillada(Nombre).trim() + ", " +
					                         Utilerias.CadenaEncomillada(Direccion).trim() + ", " +
			                                 Utilerias.CadenaEncomillada(Latitud).trim() + ", " +
					                         Utilerias.CadenaEncomillada(Longitud).trim() + ", " +
					                         Utilerias.CadenaEncomillada(Telefono).trim() + " )";
			 }
			 else{
				 sql = "update puntoventa set " +
			           "cve_pdv=" + String.valueOf(cvePuntosVenta) + ", " +
			           "nombre=" + Utilerias.CadenaEncomillada(Nombre).trim() + ", " +
			           "direccion=" + Utilerias.CadenaEncomillada(Direccion).trim() + ", " +
			           "latitud=" + Utilerias.CadenaEncomillada(Latitud).trim() + ", " +
			           "longitud=" + Utilerias.CadenaEncomillada(Longitud).trim() + ", " +
			           "telefono=" + Utilerias.CadenaEncomillada(Telefono).trim()  +
			           " where cve_pdv=" + String.valueOf(cvePuntosVenta);
				
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
