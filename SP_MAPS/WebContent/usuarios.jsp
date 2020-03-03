<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

	<title>.: Sistema Comercio :.</title>

	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="imagenes/faviconhor.png" rel="shortcut icon" type="image/png">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow ">
	  <div class="container">
	    <a  class="navbar-brand" href="usuarios.jsp">Sistema Comercio</a>

	  </div>
	</nav>
</head>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@ page import="org.apache.commons.fileupload.*, 
                 sistema.*, 
				 comun.*,
				 base.*, 
				 java.sql.*, java.util.*, java.io.File"%>
<%

 //Variables Auxiliares
Resultados rs = null;

String linea="";
String clave =request.getParameter("xClave") !=null ? request.getParameter("xClave") : "0";
String accion=request.getParameter("xAccion") !=null ? request.getParameter("xAccion"): "listado";

 //Variables Genrales
int cveUsuarios = request.getParameter("xCveUsuarios") !=null ? Integer.parseInt(request.getParameter("xCveUsuarios")):0;
String nombre = request.getParameter("xNombre") != null ? request.getParameter("xNombre") : "";
String paterno = request.getParameter("xPaterno") != null ? request.getParameter("xPaterno") : "";
String materno = request.getParameter("xMaterno") != null ? request.getParameter("xMaterno") : "";
String login = request.getParameter("xLogin") != null ? request.getParameter("xLogin") : "";
String pass = request.getParameter("xpass") != null ? request.getParameter("xpass") : "";
int tipoUsuario = request.getParameter("xTipoUsuario") !=null ? Integer.parseInt(request.getParameter("xTipoUsuario")):0;


/*

	Variables en Archivo .Java
	------------------------------
  protected int CveUsuarios;
  protected String Nombre;
  protected String Paterno;
  protected String Materno;
  protected String Login;
  protected String Contraseña;
  protected int TipoUsuario;
  
  private boolean _existe;

*/

//Creacion de Un nuevo objeto estados
Usuarios user = new Usuarios();
if (accion.equals("recarga"))
{
	// a Buscar
	user.setCveUsuarios(Integer.parseInt(clave));
	// a Mostrar
	nombre  = user.getNombre();	
	paterno  = user.getPaterno();
	materno  = user.getMaterno();
	login  = user.getLogin();
	pass  = user.getPass();
	tipoUsuario  = user.getTipoUsuario();


	System.out.println("clave >>>: "+ clave);
	System.out.println("paterno: "+ paterno);
	System.out.println("materno: "+ materno);
	System.out.println("login: "+ login);
	System.out.println("pass: "+ pass);
	System.out.println("tipoUsuario: "+ tipoUsuario);


}

if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
		{
		if (Integer.parseInt(clave)==0)  
		{
			rs= UtilDB.ejecutaConsulta(" select * from usuarios order by cve_usuario desc limit 1 ");
			if (rs.recordCount()==0) {
				clave = "1";
			}
			else 
			{
				while (rs.next()) 
				{
					clave = String.valueOf(rs.getInt("cve_usuario") + 1);
					System.out.println("clave: >> "+ clave + "<< ");
				}
			}
		}
		

		user.setCveUsuarios(Integer.parseInt(clave));
		user.setNombre(nombre.trim());
		user.setPaterno(paterno.trim());
		user.setMaterno(materno.trim());
		user.setLogin(login.trim());
		user.setPass(paterno.trim());
		user.setTipoUsuario(tipoUsuario);
		user.graba();

		if (accion.equals("grabaNuevo") )
		{
		    clave="0";
			accion="nuevo";

			nombre     ="";
		    paterno    ="";
		    materno    ="";
		    login    ="";
		    pass    ="";
			tipoUsuario     = 0;

		}

		if(accion.equals("grabaCierra"))
		{
			accion="listado";
		}
	}

%>

<script language="JavaScript">

	function recarga(clave){
		forma.xClave.value= clave;
		forma.xAccion.value = "recarga";
		forma.submit();
	}
		
	function listar(){
    	forma.xAccion.value = "listado";
    	forma.xClave.value = "0";
    	forma.submit();
    }
    function nuevo(){
    	forma.xAccion.value = "nuevo";
    	forma.xClave.value = "0";
    	forma.submit();
    }

    function validar(accion)
	{
		forma.xAccion.value= accion;
		errores=0;
		if (forma.xNombre.value=="") errores++;
		if (forma.xPaterno.value=="") errores++;
		if (forma.xMaterno.value=="") errores++;
		if (forma.xLogin.value=="") errores++;
		if (forma.xpass.value=="") errores++;
		if (forma.xTipoUsuario.value==0) errores++;

		if (errores > 0)
		{
			alert("Debe capturar todos los Datos...");
		}
		else{
			forma.submit();
		}

	}
   
</script>

<body>
	<div class="container-fluid">
		<form action="" method="POST" name="forma">
			<input type="hidden" name="xAccion" id="xAccion" value="<%=accion%>"/>
			<input type="hidden" name="xClave" id="xClave" value="<%=clave%>"/>

			<!-- Tabla -->
			<section class="py-2">
		    	<div class="container-fluid">	    		
					<a>&nbsp;</a>
					<h3 align="center">Listado de Usuarios</h3>
	    			<%
					if (accion.equals("listado"))
					{
					%>

			    	<table class="table table-striped">
				    	<thead align="center">

							<!-- Cinta de Opciones-->
						    <tr>
						    <td>&nbsp;</td>
						     	<td><a href="#" onclick="nuevo(); "><img src="imagenes/botones/nuevo.png"></a></td>
								<td>&nbsp;</td>	     
						      	<td><a href="#"><img  src="imagenes/botones/disabled/guardarycerrar.png"></a></td>
						      	<td>&nbsp;</td>	     
						     	<td ><a href="#"><img src="imagenes/botones/disabled/guardarynuevo.png"></td>
						     	<td>&nbsp;</td>	
						      	<td ><a href="login.jsp"><img src="imagenes/botones/cerrar.png"></td>
						      	<td>&nbsp;</td>
							</tr>

						</thead>
					</table>

					<table class="table table-striped">
				    	<thead>
							<!-- Campos de la Tabla-->
						    <tr style="text-align: center;">
						      <th scope="col">&nbsp;</th>
						      <th scope="col">Nombre</th>
							  <th scope="col">Apellido Paterno</th>
							  <th scope="col">Apellido Materno</th>
							  <th scope="col">Usuario</th>
							  <th scope="col" style="width: 5%" >Editar</th>
				   			</tr>

						</thead>

						<tbody>
					  	<%
							linea="select * from usuarios ";

							System.out.println("Consulta CVE >>"+ clave);

							if (cveUsuarios > 0 ) 
							{
								linea+= "  where cve_usuario =" + clave;  
							}
							else 
							{
								linea+= "  order by nombre"; 
							}

							rs = UtilDB.ejecutaConsulta(linea);
							if (rs.recordCount()==0)
							{
							//Termina Codigo en Java
						%>
							<tr align="center">
								<th colspan="10" align="center"><h3> No existen registros...</h3></th>
				    		</tr>
			    		<%
							}
							else
							{
								int x=0;
								while (rs.next())
								{
									x++;

									

							//Termina Codigo en Java
						%>

						<!--- Listado -->	
					    <tr style="text-align: center;">
					      <th scope="row"><%=x%>.</th>
					      <td><%=rs.getString("nombre")%></td>
					      <td><%=rs.getString("paterno")%></td>
					      <td><%=rs.getString("materno")%></td>
					      <td><%=rs.getString("login")%></td>

					      <th> <a href="#" onclick="recarga('<%=rs.getInt("cve_usuario")%>');"> <img src="imagenes/edit.png" border="0"/></a>  </th>
					    </tr>
					<% 	 }
					}
					%>
				     	</tbody>
		            </table>
					<% 
						} 
						else 
						{
					%>   

				    	<table class="table table-striped">
					    	<thead align="center">

								<!-- Cinta de Opciones-->
							    <tr align="center">
							        <th scope="col"><a href="#">								   <img border="0" src="imagenes/botones/disabled/nuevo.png"></a></th>
							        <th scope="col"><a href="#" onclick="validar('grabaCierra')"> <img border="0" src="imagenes/botones/guardarycerrar.png"></a></th>
							        <th scope="col"><a href="#" onclick="validar('grabaNuevo')">  <img border="0" src="imagenes/botones/guardarynuevo.png"></th>
							        <th scope="col"><a href="usuarios.jsp" >				   <img border="0" src="imagenes/botones/cerrar.png"></a></th>
							    </tr>

							</thead>
						</table>

						<table class="table table-striped">


							  	<tbody>

								<tr>
								   	<th >Nombre</th>
								    <th><input type="text" name="xNombre" id="xNombre" maxlength="100" size="80" value="<%= nombre %>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Paterno</th>
								    <th><input type="text" name="xPaterno" id="xPaterno" maxlength="100" size="80" value="<%= paterno %>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Materno</th>
								    <th><input type="text" name="xMaterno" id="xMaterno" maxlength="100" size="80" value="<%= materno %>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Usuario</th>
								    <th><input type="text" name="xLogin" id="xLogin" maxlength="100" size="80" value="<%= login %>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Password</th>
								    <th><input type="text" name="xpass" id="xpass" maxlength="100" size="80" value="<%= pass %>" ></th>
							  	</tr>


								<tr>
									<th >Tipo de Usuarios</th>
									<td>
		                              <select name="xTipoUsuario" class="captura" onchange="recargaC()">
		                              	<option value="0">Elegir una de las Opciones</option>
		                              	<%
		                              		linea="select * from tipousuario order by nombre";
		                              		rs = UtilDB.ejecutaConsulta(linea);
		                              		while( rs.next() )
		                              		{
		                              			if( tipoUsuario==0) tipoUsuario=rs.getInt("cve_tipo_usuario");
		                              	%>
		                              	<option value="<%=rs.getInt("cve_tipo_usuario")%>"
		                              		<%=rs.getInt("cve_tipo_usuario")==tipoUsuario?"selected":""%>><%=rs.getString("nombre")%></option>
		                              		<%
		                              		}
		                              		rs.close();
		                              		rs = null;
		                              		%>
		                              	</option>
		                              </select>
		                            </td>
								  </tr>

							</tbody>
						</table>
					<%
					}
					%>
					</div>
				</section>
		</form>
	</div>
</body>	
</html>