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
	    <a  class="navbar-brand" href="index.jsp"> .:: Acceso para Usuarios ::. </a>

	  </div>
	</nav>
</head>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@ page import="org.apache.commons.fileupload.*, 
                 sistema.*, 
				 comun.*, 
				 java.sql.*, java.util.*, java.io.File"%>

<%


  String mensaje = "";
  
  Resultados rs=null;
  
  String login=request.getParameter("xLogin")!=null?request.getParameter("xLogin"):"";
  String contrasena=request.getParameter("xContrasena")!=null?request.getParameter("xContrasena"):"";
  int typeUsr = request.getParameter("xTipoUsuario") !=null ? Integer.parseInt(request.getParameter("xTipoUsuario")):0;

  if (login.length()>0)
  {
	  rs=UtilDB.ejecutaConsulta("select * from usuarios where " + 
	                            "login='" + login + "' and " + 
								"contrasena='" + contrasena + "'");
	  if (rs.recordCount()==1)
	  {
		  
		  while (rs.next()) 
		  {
		  typeUsr = rs.getInt("cve_tipo_usuario");
		  System.out.println(typeUsr);
		  response.sendRedirect("index"+typeUsr+".jsp"); //Enviar a página 
		  }
	  }
	  else
	  {
		  mensaje="Nombre usuario o contraseña incorrectos...";
	  }
  }

%>

<script language="JavaScript">
	function highlightButton(s)
	{
	if ("INPUT"==event.srcElement.tagName)
	event.srcElement.className=s;
	}


	function validar()
	{
			if (forma.xLogin.value == "") 
			{
			alert("Debe escribir el nombre del usuario");
			}
			else
			{
			if (forma.xContrasena.value == "") 
			{
				alert("Debe escribir el password del usuario");
			}		
			else
			{
				forma.submit();
			}
		}
	}
document.onmousedown=click;
</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<!-- Vendedores  -->
		<header class="masthead"> 
		<div class="container h-100">
		<div class="row align-items-center">
		<div class="col-12 text-center">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<h1 class="font-weight-light">Iniciar Sesion</h1>
			
		<div class="container">
		    <div class="row">
		      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
		        <div class="card card-signin my-5">
		          <div class="card-body">
		            <form class="form-signin">
		              <div class="form-label-group">
		              	<a href=""></a>
		              	<h6 class="card-title text-center"><%= mensaje %></h6>
		                <input type="text" name="xLogin" class="form-control" placeholder="Usuario" required autofocus>
		                <label for="inputUser"></label>
		              </div>

		              <div class="form-label-group">
		                <input type="password" id="inputPassword"  class="form-control" placeholder="Contraseña" required name="xContrasena" maxlength="20">
		                <label for="inputPassword"></label>
		              </div>

	              		<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  onclick="validar();"
			       class="boton"
			       onmouseover="highlightButton('selBoton')"
			       onmouseout="highlightButton('boton')"
			       onfocus="highlightButton('selBoton')"
			       onblur="highlightButton('boton')"
			       name="Aceptar"
			       value="Aceptar" /> Aceptar</button>
			           		</form>
	          			</div>
	          			<a href="puntosVentas1.jsp">
	          				<button class="btn btn-lg btn-google btn-block text-uppercase" type="submit"><i class="fab fa-google mr-2"></i> Entrar sin Registro </button>
	          			</a>
	        		</div>
	     	 	</div>
	   		</div>
	  </div>
</body>
</html>