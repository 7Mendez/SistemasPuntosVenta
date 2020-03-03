package sistema;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import comun.Const;
import comun.Resultados;
import comun.UtilDB;
import comun.Utilerias;

public class Stock {

	protected int CveStock;
	protected int CveProducto;
	protected int Cantidad;
	
	private boolean _existe;
	
	public Stock()
	{
		clear();
	}
	public Stock (int xCveStock){
		clear();
		setCveStock(xCveStock);
	}
	public void clear()
	{
		_existe = false;
		CveStock = 0;
		CveProducto = 0;
		Cantidad = 0;
	}

	public int getCveStock() {
		return CveStock;
	}
	public void setCveStock(int cveStock) {
		CveStock = cveStock;
		carga();
	}
	public int getCveProducto() {
		return CveProducto;
	}
	public void setCveProducto(int cveProducto) {
		CveProducto = cveProducto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public boolean is_existe() {
		return _existe;
	}
	public void set_existe(boolean _existe) {
		this._existe = _existe;
	}
	
	// INSERT INTO `sp`.`stock` (`cve_stock`, `cve_producto`, `cve_pv`, `cantidad`) VALUES ('1', '1', '1', '55');
	
	public int carga()
	{
		int res = Const.SIN_ERROR;
		String sql = "select * from producto as p, stock as s where p.cve_p=s.cve_producto and cve_stock = " + String.valueOf(CveStock);
		Resultados rs = UtilDB.ejecutaConsulta(sql);
		
		while (rs.next()){
			carga(rs);
		}
		return res;
	}
	public int carga(Resultados rs)
	 {
		 int res = Const.SIN_ERROR;
		 CveStock = rs.getInt("cve_stock");
		 CveProducto = rs.getInt("cve_producto");
		 Cantidad = rs.getInt("cantidad");

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
				 sql = "insert into stock ( cve_stock, cve_producto, cantidad ) values ( " +
			                                 String.valueOf(CveStock) + ",  " 
			                                 + String.valueOf(CveProducto) + ",  " 
	                                		 + String.valueOf(Cantidad) + "  )";
			 } 
			 else{
				 sql = "update stock set " +
			           " cve_stock=" + String.valueOf(CveStock) + ",  " +
			           " cve_producto=" + String.valueOf(CveProducto) + ",  " +
			           " cantidad=" + String.valueOf(Cantidad) +
			           "  where cve_stock=" + String.valueOf(CveStock);
				
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
