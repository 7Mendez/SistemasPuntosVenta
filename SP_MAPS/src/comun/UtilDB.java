package comun;

import com.codestudio.util.JDBCPool;
import com.codestudio.util.SQLManager;
import java.sql.*;

public class UtilDB {
	public UtilDB() {

	}

	public static Connection leerConexion(String driver, String base, String usuario, String pass, String tabla) {
		Connection con = null;
		
		String manejador="SQL_SERVER";
		String sURL="jdbc:sqlserver://localhost:1433";
		//String sURL="jdbc:sqlserver://localhost:1433;DataBase=baseDeDatos";
		//String sURL="jdbc:mysql://localhost/pensabe";
		String password="adminsa";

		
		String sDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String sDriver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(sDriver).newInstance();
			con = DriverManager.getConnection(sURL, usuario, password);

		} catch (SQLException sqle) {
			System.out.println("SQLState: " + sqle.getSQLState());
			System.out.println("SQLErrorCode: " + sqle.getErrorCode());
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return con;
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.codestudio.sql.PoolMan").newInstance();
			if (!Contador.getArrancado()) {
				JDBCPool jpool = (JDBCPool) SQLManager.getInstance().getPool(
						"control");
				System.out
						.println("Poolman inicializado utilizando base de datos en "
								.concat(String.valueOf(String.valueOf(jpool
										.getURL()))));
			}
		} catch (Exception ex) {
			System.out
					.println("No se ha hallado el PoolMan. Checar si esta instalado.");
			Connection connection = null;
			return connection;
		}
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:poolman//control");
		} catch (SQLException e) {
			System.out.println("No se ha establecido la conexion:"
					.concat(String.valueOf(String.valueOf(e.toString()))));
			Connection connection1 = null;
			return connection1;
		}
		return con;
	}

	public static ErrorSistema insertaDatos(String tabla, String campos[],
			String valores[]) {
		ErrorSistema err = new ErrorSistema();
		if (campos.length == valores.length) {
			String cadSQL = String.valueOf(String.valueOf((new StringBuffer(
					"insert into ")).append(tabla).append(" (")));
			String cadSQL2 = " values (";
			for (int i = 0; i < campos.length; i++) {
				cadSQL = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cadSQL)))).append(campos[i])
						.append(",")));
				cadSQL2 = String.valueOf(String.valueOf((new StringBuffer(
						String.valueOf(String.valueOf(cadSQL2)))).append(
						valores[i]).append(",")));
			}

			cadSQL = String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(cadSQL.substring(0,
							cadSQL.length() - 1))))).append(")")
					.append(cadSQL2.substring(0, cadSQL2.length() - 1))
					.append(")")));
			Connection con = null;
			Statement s = null;
			try {
				System.out.println(cadSQL);
				con = getConnection();
				s = con.createStatement();
				s.execute(cadSQL);
				s.close();
			} catch (SQLException e) {
				err.setNumeroError(e.getErrorCode());
				err.setCadenaError(e.toString());
				err.setCadenaSQL(cadSQL);
				err.out();
			} finally {
				try {
					con.close();
				} catch (Exception exception1) {
				}
			}
		} else {
			err.numError = -2;
			err.setCadenaError("Numero de campos y valores no coincidente");
			err.out();
		}
		return err;
	}

	public static ErrorSistema borraDatos(String tabla, String campos[],
			String valores[]) {
		ErrorSistema err = new ErrorSistema();
		if (campos.length == valores.length) {
			String cadSQL = String.valueOf(String.valueOf((new StringBuffer(
					"delete from ")).append(tabla).append(" where ")));
			for (int i = 0; i < campos.length; i++)
				cadSQL = String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(cadSQL)))).append(campos[i])
						.append("=").append(valores[i]).append(" and ")));

			cadSQL = cadSQL.substring(0, cadSQL.length() - 5);
			System.out.println("Borrando...".concat(String.valueOf(String
					.valueOf(cadSQL))));
			Connection con = null;
			Statement s = null;
			try {
				con = getConnection();
				s = con.createStatement();
				s.execute(cadSQL);
				s.close();
			} catch (SQLException e) {
				err.setNumeroError(e.getErrorCode());
				err.setCadenaError(e.toString());
				err.setCadenaSQL(cadSQL);
				err.out();
			} finally {
				try {
					con.close();
				} catch (Exception exception1) {
				}
			}
		} else {
			err.numError = -2;
			err.setCadenaError("Numero de campos y valores no coincidente");
		}
		return err;
	}

	public static ErrorSistema ejecutaSQL(String sql) {
		ErrorSistema err = new ErrorSistema();
		Connection con = null;
		Statement s = null;
		int num = 0;
		try {
			con = getConnection();
			s = con.createStatement(1003, 1007);
			num = s.executeUpdate(sql);
		} catch (SQLException e) {
			num = -1;
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				con.close();
			} catch (Exception exception1) {
			}
		}
		if (num == 0) {
			err.setNumeroError(100);
			err.setCadenaError("No se afecto ningun registro");
		}
		return err;
	}

	public static Resultados ejecutaConsulta(String sql) {
		ErrorSistema err = new ErrorSistema();
		Resultados res = new Resultados();
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement(1004, 1007);
			rs = s.executeQuery(sql);
			res.setDatos(rs);
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int getSiguienteNumero(String tabla, String campo) {
		ErrorSistema err = new ErrorSistema();
		int res = 0;
		String sql = String.valueOf(String.valueOf((new StringBuffer(
				"select max(")).append(campo).append(") as num from ")
				.append(tabla)));
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next())
				res = rs.getInt("num") + 1;
			else
				res = 1;
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int getSiguienteNumero(String tabla, String campo,
			String filtro) {
		ErrorSistema err = new ErrorSistema();
		int res = 0;
		String sql = String.valueOf(String.valueOf((new StringBuffer(
				"select max(")).append(campo).append(") as num from ")
				.append(tabla).append(" ").append(filtro)));
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next())
				res = rs.getInt("num") + 1;
			else
				res = 1;
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int getSiguienteNumero(String tabla, String campo,
			int universidad) {
		ErrorSistema err = new ErrorSistema();
		int res = 0;
		String sql = String.valueOf(String.valueOf((new StringBuffer(
				"select max(")).append(campo).append(") as num from ")
				.append(tabla).append(" where cve_universidad = ")
				.append(universidad)));
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next())
				res = rs.getInt("num") + 1;
			else
				res = 1;
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int getSiguienteNumero(String tabla, String campo,
			int universidad, int periodo) {
		ErrorSistema err = new ErrorSistema();
		int res = 0;
		String sql = String.valueOf(String.valueOf((new StringBuffer(
				"select max(")).append(campo).append(") as num from ")
				.append(tabla).append(" where cve_universidad = ")
				.append(universidad).append("and cve_periodo = ")
				.append(periodo)));
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next())
				res = rs.getInt("num") + 1;
			else
				res = 1;
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int getSiguienteNumero(String tabla, String campo,
			int universidad, int unidadAcademica, int division, int carrera) {
		ErrorSistema err = new ErrorSistema();
		int res = 0;
		String sql = String.valueOf(String
				.valueOf((new StringBuffer("select max(")).append(campo)
						.append(") as num from ").append(tabla)
						.append(" where cve_universidad = ")
						.append(universidad)
						.append(" and cve_unidad_academica = ")
						.append(unidadAcademica).append(" and cve_division = ")
						.append(division).append(" and cve_carrera = ")
						.append(carrera)));
		Connection con = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next())
				res = rs.getInt("num") + 1;
			else
				res = 1;
			rs.close();
		} catch (SQLException e) {
			err.setNumeroError(e.getErrorCode());
			err.setCadenaError(e.toString());
			err.setCadenaSQL(sql);
			err.out();
		} finally {
			try {
				s.close();
				con.close();
			} catch (Exception exception1) {
			}
		}
		return res;
	}

	public static int numRegistros(String sql) {
		int total = 0;
		Resultados rs = null;
		try {
			for (rs = ejecutaConsulta(sql); rs.next();)
				total++;

		} catch (Exception exception) {
		}
		return total;
	}
}