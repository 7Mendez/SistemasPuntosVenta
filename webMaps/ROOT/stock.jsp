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
	    <a  class="navbar-brand" href="index.jsp">Sistema Comercio</a>

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
int cveStock = request.getParameter("xCveStock") !=null ? Integer.parseInt(request.getParameter("xCveStock")):0;
int cveProducto = request.getParameter("xCveProducto") !=null ? Integer.parseInt(request.getParameter("xCveProducto")):0;
int cantidad = request.getParameter("xCantidad") !=null ? Integer.parseInt(request.getParameter("xCantidad")):0;

/*

	Variables en Archivo .Java
	------------------------------
	protected int CveStock;
	protected int CveProducto;
	protected int Cantidad;

	private boolean _existe;

*/

//Creacion de Un nuevo objeto estados
Stock stocker = new Stock();
if (accion.equals("recarga"))
{
	// a Buscar
	stocker.setCveStock(Integer.parseInt(clave));
	// a Mostrar
	cveProducto  = stocker.getCveProducto();
	cantidad  = stocker.getCantidad();


	System.out.println("clave >>>: "+ clave);
	System.out.println(" cveProducto :"+ cveProducto);
	System.out.println(" cantidad : "  + cantidad);

}

if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
		{
		if (Integer.parseInt(clave)==0)  
		{
			rs= UtilDB.ejecutaConsulta(" select * from stock order by cve_stock desc limit 1 ");
			if (rs.recordCount()==0) {
				clave = "1";
			}
			else 
			{
				while (rs.next()) 
				{
					clave = String.valueOf(rs.getInt("cve_stock") + 1);
					System.out.println("clave: >> "+ clave + "<< ");
				}
			}
		}
		

		stocker.setCveStock(Integer.parseInt(clave));
		stocker.setCveProducto(cveProducto);
		stocker.setCantidad(cantidad);
		stocker.graba();

		if (accion.equals("grabaNuevo") )
		{
		    clave="0";
			accion="nuevo";

			cveProducto  = 0;
			cantidad  = 0;

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
		if (forma.xCveProducto.value==0) errores++;
		if (forma.xCantidad.value==0) errores++;

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
					<h3 align="center">Listado de Stock's</h3>
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
						      	<td ><a href="index.jsp"><img src="imagenes/botones/cerrar.png"></td>
						      	<td>&nbsp;</td>
							</tr>

						</thead>
					</table>

					<table class="table table-striped">
				    	<thead>
							<!-- Campos de la Tabla-->
						    <tr style="text-align: center;">
						      <th scope="col">&nbsp;</th>
						      <th scope="col">Producto</th>
						      <th scope="col">Stock</th>
							  <th scope="col" style="width: 5%" >Editar</th>
				   			</tr>

						</thead>

						<tbody>
					  	<%
							linea="select * from  stock ";

							System.out.println("Consulta CVE >>"+ clave);

							if (cveStock > 0 ) 
							{
								linea+= " where  cve_stock =" + clave;  
							}
							else 
							{
								linea+= "  order by cve_stock"; 
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
					      <td><%=rs.getInt("cve_producto")%></td>
					      <td><%=rs.getInt("cantidad")%></td>

					      <th> <a href="#" onclick="recarga('<%=rs.getInt("cve_stock")%>');"> <img src="imagenes/edit.png" border="0"/></a>  </th>
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