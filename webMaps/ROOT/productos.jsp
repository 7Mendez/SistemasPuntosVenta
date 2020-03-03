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
	    <a  class="navbar-brand" href="productos.jsp">Sistema Comercio</a>

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
int cvePRODUCTOS = request.getParameter("xcveProductos") !=null ? Integer.parseInt(request.getParameter("xcveProductos")):0;
int precio = request.getParameter("xPrecio") !=null ? Integer.parseInt(request.getParameter("xPrecio")):0;

String nombre = request.getParameter("xNombre") != null ? request.getParameter("xNombre") : "";
String imagen = request.getParameter("xImagen") != null ? request.getParameter("xImagen") : "";

/*

	Variables en Archivo .Java
	------------------------------
	protected int cveProductos;

	protected String Nombre;
	protected String Imagen;
	protected int Precio;

	private boolean _existe;

*/

//Creacion de Un nuevo objeto estados
Productos products = new Productos();
if (accion.equals("recarga"))
{
	// a Buscar
	products.setCveProductos(Integer.parseInt(clave));
	// a Mostrar
	nombre  = products.getNombre();	
	imagen  = products.getImagen();
	precio  = products.getPrecio();


	System.out.println("clave >>>: "+ clave);
	System.out.println("nombre: "+ nombre);
	System.out.println("precio: "+ precio);
	System.out.println("imagen: "+ imagen);
}

if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
		{
		if (Integer.parseInt(clave)==0)  
		{
			rs= UtilDB.ejecutaConsulta(" select * from producto order by cve_p desc limit 1 ");
			if (rs.recordCount()==0) {
				clave = "1";
			}
			else 
			{
				while (rs.next()) 
				{
					clave = String.valueOf(rs.getInt("cve_p") + 1);
					System.out.println("clave: >> "+ clave + "<< ");
				}
			}
		}
		

		products.setCveProductos(Integer.parseInt(clave));
		products.setNombre(nombre.trim());
		products.setImagen(imagen.trim());
		products.setPrecio(precio);
		products.graba();

		if (accion.equals("grabaNuevo") )
		{
		    clave="0";
			accion="nuevo";

			nombre     ="";
		    imagen     ="";
			precio     = 0;

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
		//if (forma.xImagen.value=="") errores++;
		if (forma.xPrecio.value==0) errores++;

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
					<h3 align="center">Listado de Productos</h3>
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
						     <!-- <th scope="col">Imagen</th> -->
							  <th scope="col">Precio</th>
							  <th scope="col" style="width: 5%" >Editar</th>
				   			</tr>

						</thead>

						<tbody>
					  	<%
							linea="select * from producto ";

							System.out.println("Consulta CVE >>"+ clave);

							if (cvePRODUCTOS > 0 ) 
							{
								linea+= "  where cve_p =" + clave;  
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
					      <!-- <td><%=rs.getString("imagen")%></td> -->
					      <td>$<%=rs.getInt("precio")%></td>

					      <th> <a href="#" onclick="recarga('<%=rs.getInt("cve_p")%>');"> <img src="imagenes/edit.png" border="0"/></a>  </th>
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
							        <th scope="col"><a href="productos.jsp" >				   <img border="0" src="imagenes/botones/cerrar.png"></a></th>
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
								   	<th >Precio</th>
								    <th><input type="text" name="xPrecio" id="xPrecio" maxlength="11" size="80"  value="<%= precio %>"  ></th>
							  	</tr>

							<!--
							  	<tr>
								   	<th >Imagen</th>
								    <th><input type="text" name="xImagen" id="xImagen" maxlength="150" size="80" value="<%= imagen %>" ></th>
							  	</tr>
							-->
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