package sistema;

import comun.*;
import java.util.*;
import java.sql.*;

public class Distribuidores {
	protected int CveMunicipios;
	protected int CveEstados;
	protected int CveDistribuidor;
	protected String Nombre;
	protected String Direccion;
	protected String Latitud;
	protected String Longitud;
	protected String Telefono;
	private boolean _existe;
	
	public Distribuidores()
	{
		clear();
	}
	public Distribuidores(int xCveDistribuidor){
		clear();
		setCveDistribuidor(xCveDistribuidor);
	}
	
	public void clear(){
		_existe = false;
	    CveMunicipios = 0;
		CveEstados = 0;
		Nombre = "";
		Direccion = "";
		Latitud = "";
		Longitud = "";
		Telefono = "";
	}
	
	/*
	 * Get y Set Generados
	 */
	public int getCveMunicipios() {
		return CveMunicipios;
	}
	public void setCveMunicipios(int cveMunicipios) {
		CveMunicipios = cveMunicipios;
	}
	public int getCveEstados() {
		return CveEstados;
	}
	public void setCveEstados(int cveEstados) {
		CveEstados = cveEstados;
	}
	public int getCveDistribuidor() {
		return CveDistribuidor;
	}
	public void setCveDistribuidor(int cveDistribuidor) {
		CveDistribuidor = cveDistribuidor;
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
		String sql = "select * from distribuidores where cve_distribuidor = " + String.valueOf(CveDistribuidor);
		Resultados rs = UtilDB.ejecutaConsulta(sql);
		
		while (rs.next()){
			carga(rs);
		}
		return res;
	}
	
	 public int carga(Resultados rs)
	 {
		 int res = Const.SIN_ERROR;
		 CveDistribuidor= rs.getInt("cve_distribuidor");
		 CveMunicipios = rs.getInt("cve_municipios");
		 CveEstados = rs.getInt("cve_estados");
		 Nombre = rs.getString("nombre");
		 Direccion = rs.getString("direccion");
		 Latitud = rs.getString("latitud");
		 Longitud = rs.getString("longitud");
		 Telefono = rs.getString("telefono");
		 _existe = true;
		 return res;
	 }
	
	
}
