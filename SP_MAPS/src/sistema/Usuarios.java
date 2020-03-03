package sistema;
 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import comun.Const;
import comun.Resultados;
import comun.UtilDB;
import comun.Utilerias;


public class Usuarios 
{ 
  protected int CveUsuarios;
  protected String Nombre;
  protected String Paterno;
  protected String Materno;
  protected String Login;
  protected String pass;
  protected int TipoUsuario;
  
  private boolean _existe;
 
  public Usuarios()
  {
    clear();
  }
 
  public Usuarios(int xCveUsuarios)
  {
    clear();
    setCveUsuarios(xCveUsuarios);
  }
  
  
	public void clear()
	{
		_existe = false;
		CveUsuarios = 0;
		Nombre = "";
		Paterno = "";
		Materno = "";
		Login = "";
		pass = "";
		TipoUsuario = 0;
	}
	
	
	

public int getCveUsuarios() {
	return CveUsuarios;
}

public void setCveUsuarios(int cveUsuarios) {
	CveUsuarios = cveUsuarios;
	carga();
}

public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}

public String getPaterno() {
	return Paterno;
}

public void setPaterno(String paterno) {
	Paterno = paterno;
}

public String getMaterno() {
	return Materno;
}

public void setMaterno(String materno) {
	Materno = materno;
}

public String getLogin() {
	return Login;
}

public void setLogin(String login) {
	Login = login;
}

public int getTipoUsuario() {
	return TipoUsuario;
}

public void setTipoUsuario(int tipoUsuario) {
	TipoUsuario = tipoUsuario;
}

public boolean is_existe() {
	return _existe;
}

public void set_existe(boolean _existe) {
	this._existe = _existe;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

public int carga()
{
	int res = Const.SIN_ERROR;
	String sql = "select * from usuarios where cve_usuario = " + String.valueOf(CveUsuarios);
	Resultados rs = UtilDB.ejecutaConsulta(sql);
	
	while (rs.next()){
		carga(rs);
	}
	return res;
}


public int carga(Resultados rs)
 {
	 int res = Const.SIN_ERROR;
	 CveUsuarios= rs.getInt("cve_usuario");
	 Nombre = rs.getString("nombre");
	 Paterno = rs.getString("paterno");
	 Materno = rs.getString("materno");
	 Login = rs.getString("login");
	 pass = rs.getString("contrasena");
	 TipoUsuario = rs.getInt("cve_tipo_usuario");


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
			 sql = "INSERT INTO usuarios (cve_usuario, nombre, paterno, materno, login, contrasena, cve_tipo_usuario) "
			 		+ "VALUES ("
			 		+ String.valueOf(CveUsuarios) + ",  " 
			 		+ Utilerias.CadenaEncomillada(Nombre).trim() + ",  " 
			 		+ Utilerias.CadenaEncomillada(Paterno).trim() + ",  " 
					+ Utilerias.CadenaEncomillada(Materno).trim() + ",  " 
					+ Utilerias.CadenaEncomillada(Login).trim() + ",  " 
					+ Utilerias.CadenaEncomillada(pass).trim() + ",  " 
					+ String.valueOf(TipoUsuario) + " )";
		 } 
		 else{
			 sql = "update usuarios set " 
					+ "nombre="   + Utilerias.CadenaEncomillada(Nombre).trim() +","
			 		+ " paterno=" + Utilerias.CadenaEncomillada(Paterno).trim() +","
			 		+ " materno=" + Utilerias.CadenaEncomillada(Materno).trim() +","
			 		+ " login="   + Utilerias.CadenaEncomillada(Login).trim() +","
			 		+ " contrasena=" + Utilerias.CadenaEncomillada(pass).trim() +","
			 		+ " cve_tipo_usuario=" + String.valueOf(TipoUsuario) 
			 		+ " WHERE  cve_usuario="+ String.valueOf(CveUsuarios);
			 		
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
