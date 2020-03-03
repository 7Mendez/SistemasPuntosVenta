<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.: Nombre del Sistema :.</title>
<link href="js/hojaestilos.css" rel="stylesheet" type="text/css" />
<link href="imagenes/faviconhor.png" rel="shortcut icon" type="image/png">
</head>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@ page import="org.apache.commons.fileupload.*, 
                 librerias.catalogos.*, 
				 librerias.base.*, 
				 librerias.comun.*, 
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
int cveP = Integer.parseInt(request.getParameter("cve"));
%> 

<h1>Clave: <%=cveP %></h1>

<% Resultados rs = null;
	rs = UtilDB.ejecutaConsulta("Select * from planteles where cve_planteles= "+ cveP  + " Order By nombre");
%>


<script language="JavaScript">


</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<form action="" method="post" name="forma">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF" align="center">
			<tr>
				<td colspan="6" valign="top">
					<p align="center" class="encTablas">Plantel: </p>
					<table width="99%" align="center" class="grid" cellpadding="3"
						cellspacing="0">
					</table>
				<tr>
				  <td class="textoCeldasChicas">&nbsp;</td>
				  <td colspan="2" class="textoCeldasChicas">&nbsp;</td>
				  <td class="textoCeldasChicas">&nbsp;</td>
			    </tr>
			  </td>
			</tr>
		    <%
		    while (rs.next()){
		    %>
		    <tr align="center">
		        <td>
		              <h1 style="color: Blue">Nombre :</h1>
		              <h2><%=rs.getString("nombre")%></h2>
		              <h1 style="color: blue">Nombre Corto :</h1>
		              <h2><%=rs.getString("nombre_corto")%></h2> 
		              <h1 style="color: blue">Direccion :</h1>
		              <h2><%=rs.getString("direccion")%></h2> 
		              <h1 style="color: blue">Latitud :</h1>
		              <h2><%=rs.getString("latitud")%></h2> 
		              <h1 style="color: blue">Longitud :</h1>
		              <h2><%=rs.getString("longitud")%></h2> 
		              <h1 style="color: blue">Nombre de Contacto :</h1>
		              <h2><%=rs.getString("nombre_contacto")%></h2>
		              <h1 style="color: blue">Telefono :</h1>
		              <h2><%=rs.getString("telefono")%></h2>
		              <h1 style="color: blue">Email :</h1>
		              <h2><%=rs.getString("email")%></h2>
		              <h1 style="color: blue">Pagina web :</h1>
		              <h2><%=rs.getString("pagina_web")%></h2>
		        </td> 
		    </tr>
		    <% } %>
  		 </form>
 	</body>
</html>