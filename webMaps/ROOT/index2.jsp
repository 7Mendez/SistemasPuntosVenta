<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.: Nombre del Sistema :.</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="imagenes/faviconhor.png" rel="shortcut icon" type="image/png">
<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow ">
	  <div class="container">
	    <a  class="navbar-brand" href="index2.jsp">Sistema Comercio</a>
	    <a  class="navbar-brand" href="index1.jsp">Empleado</a>
	  </div>
	</nav>
</head>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@ page import="org.apache.commons.fileupload.*, 
                 sistema.*, 
				 comun.*, 
				 java.sql.*, java.util.*, java.io.File"%>
<%
/**
 * <p>Title: Nombre del Sistema</p>
 * <p>Description: Aplicación Web para Nombre del Sistema</p>
 * <p>Copyright: Copyright (c) 20XX</p>
 * <p>Company: Nombre Compañía </p>
 * @author Nombre del autor
 * @version 1.0
 */

 //Codigo Java
%>

	<script language="JavaScript">
	</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<!-- Vendedores  -->
	<header class="masthead"> 
	  <div class="container h-100">
	    <div class="row align-items-center">
	      <div class="col-12 text-center">
	      	<p>&nbsp;</p>
	      	<p>&nbsp;</p>
	      	<h1 class="font-weight-light">Selecciona Una Opcion</h1>
	      	<div class="row">
		      	<!-- Cuadro 1  -->
			    <div class="col-sm-4 ">
			    	<p>&nbsp;</p>
			      <div class="card h-30">
			        <div class="card-body">
			          <h5 class="card-title"><a href="distribuidor.jsp">Distribuidores</a> </h5>
			        </div>
			      </div>
			    </div>

			    <!-- Cuadro 2 -->
			    <div class="col-sm-4 ">
			    	<p>&nbsp;</p>
			      <div class="card h-30">
			        <div class="card-body">
			          <h5 class="card-title"><a href="puntosVentas1.jsp">Puntos De Venta</a> </h5>
			        </div>
			      </div>
			    </div>

			    <!-- Cuadro 4 -->
			    <div class="col-sm-4 ">
			    	<p>&nbsp;</p>
			      <div class="card h-30">
			        <div class="card-body">
			          <h5 class="card-title"><a href="productos.jsp">Productos</a> </h5>
			        </div>
			      </div>
			    </div>
			</div>
	      </div>
	    </div>
	  </div>
	</header>

</body>
</html>