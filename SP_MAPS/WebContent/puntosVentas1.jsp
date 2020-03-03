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
	    <a  class="navbar-brand" href="puntosVentas1.jsp">Sistema Comercio</a>
	    <a  class="navbar-brand" href="index1.jsp">*</a>
	  </div>
	</nav>
    <style>
      #map {
        width: 100%;
        height: 400px;
        background-color: grey;
      }
    </style>
    </style>
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
int cvePDV = request.getParameter("xcvePuntosVenta") !=null ? Integer.parseInt(request.getParameter("xcvePuntosVenta")):0;

String nombre = request.getParameter("xNombre") != null ? request.getParameter("xNombre") : "";
String direccion = request.getParameter("xDireccion") != null ? request.getParameter("xDireccion") : "";
String telefono = request.getParameter("xTelefono") != null ? request.getParameter("xTelefono") : "";
String latitud = request.getParameter("xLatitud") != null ? request.getParameter("xLatitud") : "";
String longitud = request.getParameter("xLongitud") != null ? request.getParameter("xLongitud") : "";

/*

	Variables en Archivo .Java
	------------------------------
	protected int cvePuntosVenta;

	protected String Nombre;
	protected String Direccion;
	protected String Latitud;
	protected String Longitud;
	protected String Telefono;

	private boolean _existe;

*/

//Creacion de Un nuevo objeto estados
puntosVenta pdv = new puntosVenta();
if (accion.equals("recarga"))
{
	
	// a Buscar
	pdv.setCvePuntosVenta(Integer.parseInt(clave));
	// a Mostrar
	nombre    = pdv.getNombre();	
	direccion = pdv.getDireccion();
	telefono  = pdv.getTelefono();
	latitud   = pdv.getLatitud();
	longitud  = pdv.getLongitud();

	System.out.println("clave >>>: "+ clave);
	System.out.println("nombre: "+ nombre);
	System.out.println("direccion: "+ direccion);
	System.out.println("telefono: "+ telefono);
	System.out.println("latitud: "+ latitud);
	System.out.println("longitud: "+ longitud);

}

if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
		{
		if (Integer.parseInt(clave)==0)  
		{
			rs= UtilDB.ejecutaConsulta(" select * from puntoventa order by cve_pdv desc limit 1 ");
			if (rs.recordCount()==0) {
				clave = "1";
			}
			else 
			{
				while (rs.next()) 
				{
					clave = String.valueOf(rs.getInt("cve_pdv") + 1);
					System.out.println("clave: >> "+ clave + "<< ");
				}
			}
		}
		

		pdv.setCvePuntosVenta(Integer.parseInt(clave));
		pdv.setNombre(nombre.trim());
		pdv.setDireccion(direccion.trim());
		pdv.setTelefono(telefono.trim());
		pdv.setLatitud(latitud.trim());
		pdv.setLongitud(longitud.trim());
		pdv.graba();

		if (accion.equals("grabaNuevo") )
		{
		    clave="0";
			accion="nuevo";

			nombre     ="";
		    direccion  ="";
			telefono   ="";
			latitud    ="";
			longitud   ="";

		}

		if(accion.equals("grabaCierra"))
		{
			accion="listado";
		}

		if(accion.equals("mapa"))
		{
			accion="mapa";
		}
	}


%>

<script language="JavaScript">

	function recarga(clave){
		forma.xClave.value= clave;
		forma.xAccion.value = "recarga";
		forma.submit();}
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
		if (forma.xDireccion.value=="") errores++;
		if (forma.xTelefono.value=="") errores++;
		if (forma.xLatitud.value=="") errores++;
		if (forma.xLongitud.value=="") errores++;
		if (errores > 0)
		{
			alert("Debe capturar todos los Datos...");
		}
		else{
			forma.submit();
		}

	}
	function mapa(clave){
		forma.xClave.value= clave;
		forma.xAccion.value = "mapa";
		forma.submit();
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
					<h3 align="center">Listado de Puntos de Venta</h3>
	    			<%
					if (accion.equals("listado"))
					{
					%>

			    	<table class="table table-striped">
				    	<thead align="center">

							<!-- Cinta de Opciones-->
						    <tr>
						    <td>&nbsp;</td>
						     	<td><a href="#"><img src="imagenes/botones/disabled/nuevo.png"></a></td>
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
						      <th scope="col">Direccion</th>
							  <th scope="col">Telefono</th>
						      <th scope="col">Latitud</th>
						      <th scope="col">Longitud</th> 
						      <th scope="col" style="width: 6%">Mapa</th>
				   			</tr>

						</thead>

						<tbody>
					  	<%
							linea="select * from puntoventa "  ;

							System.out.println("Consulta CVE >>"+ clave);

							if (cvePDV > 0 ) 
							{
								linea+= "  where cve_pdv =" + clave;  
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
					      <td><%=rs.getString("direccion")%></td>
					      <td><%=rs.getString("telefono")%></td>
					      <td><%=rs.getString("latitud")%></td>
					      <td><%=rs.getString("longitud")%></td>
					      <th> <a href="#" onclick="mapa('<%=rs.getInt("cve_pdv")%>');"><img src="imagenes/pincho.png" style="width: 65% " border="0" /></a>  </th> 
					    </tr>
					<% 	 }
					}
					%>
				     	</tbody>
		            </table>
					<% 
						} 
						else if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo") ||  accion.equals("nuevo") || accion.equals("recarga") ) 
						{
					%>   

				    	<table class="table table-striped">
					    	<thead align="center">

								<!-- Cinta de Opciones-->
							    <tr align="center">
							        <th scope="col"><a href="#">								   <img border="0" src="imagenes/botones/disabled/nuevo.png"></a></th>
							        <th scope="col"><a href="#" onclick="validar('grabaCierra')"> <img border="0" src="imagenes/botones/guardarycerrar.png"></a></th>
							        <th scope="col"><a href="#" onclick="validar('grabaNuevo')">  <img border="0" src="imagenes/botones/guardarynuevo.png"></th>
							        <th scope="col"><a href="puntosVentas1.jsp" >				   <img border="0" src="imagenes/botones/cerrar.png"></a></th>
							    </tr>

							</thead>
						</table>

						<table class="table table-striped">


							  	<tbody>

								<tr>
								   	<th >Nombre</th>
								    <th><input type="text" name="xNombre" id="xNombre" maxlength="100" size="80" value="<%=nombre%>" ></th>
							  	</tr>

							  	<tr>
								   	<th >Direccion</th>
								    <th><input type="text" name="xDireccion" id="xDireccion" maxlength="150" size="80" value="<%=direccion%>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Telefono</th>
								    <th><input type="text" name="xTelefono" id="xTelefono" maxlength="10" size="80" value="<%=telefono%>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Latitud</th>
								    <th><input type="text" name="xLatitud" id="xLatitud" maxlength="30" size="80" value="<%=latitud%>" ></th>
							  	</tr>
							  	<tr>
								   	<th >Longitud</th>
								    <th><input type="text" name="xLongitud" id="xLongitud" maxlength="30" size="80" value="<%=longitud%>" ></th>
							  	</tr>

							</tbody>
						</table>
					<%
					}
					if (accion.equals("mapa")){
					%>
					 	<table class="table table-striped">
					    	<thead align="center">

							<!-- Campos de la Tabla-->
						    <tr style="text-align: center;">
						      <th scope="col">Nombre</th>
						      <th scope="col">Direccion</th>
							  <th scope="col">Telefono</th>
  							  <th scope="col"><a href="puntosVentas.jsp" > <img border="0" src="imagenes/botones/cerrar.png"></a></th>
							  <th scope="col">&nbsp;</th>


				   			</tr>

							</thead>
							<tbody align="center">
					  		<%
								linea="select * from puntoventa where cve_pdv =" + clave;  
								
								rs = UtilDB.ejecutaConsulta(linea);
		
							    while (rs.next())
								{
								%>
								<!--- Listado -->	
							    <tr style="text-align: center;">
							      <td><%=rs.getString("nombre")%></td>
							      <td><%=rs.getString("direccion")%></td>
							      <td><%=rs.getString("telefono")%></td>
							  	  <th scope="col">&nbsp;</th>
							      <th scope="col">&nbsp;</th>
							    </tr>
							<% 	} %>
				     		</tbody>
						</table>

						

						<div class="container">
						    <!--The div element for the map -->
						    <div id="map"></div>
						    <script>
							// Initialize and add the map
							function initMap() {
							  // The location of Uluru
							  var uluru = {lat:<%=rs.getString("latitud")%>, lng:<%=rs.getString("longitud")%>};
							  // The map, centered at Uluru
							  var map = new google.maps.Map(
							      document.getElementById('map'), {zoom: 15, center: uluru});
							  // The marker, positioned at Uluru
							  var marker = new google.maps.Marker({position: uluru, map: map});
							}
						    </script>

						    <!--Load the API from the specified URL
						    * The async attribute allows the browser to render the page while the API loads
						    * The key parameter will contain your own API key (which is not needed for this tutorial)
						    * The callback parameter executes the initMap() function
						    -->
						    <script async defer
						   		 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTSZ_uBrcEkXIGqNCoTwqTDctmjvqAKMs&callback=initMap">
						    </script>
						</div>
						<h3>&nbsp;</h3>
					<%
						}
					%>

					</div>
				</section>
		</form>
	</div>
</body>	
</html>