<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.: Sistemas Politecnicas :.</title>
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
 * <p>Description: Aplicaci�n Web para Nombre del Sistema</p>
 * <p>Copyright: Copyright (c) 20XX</p>
 * <p>Company: Nombre Compa��a </p>
 * @author Nombre del autor
 * @version 1.0
 */
int cve = request.getParameter("cve")!=null?Integer.parseInt(request.getParameter("cve")):0; %>
<h1>Clave: <%=cve %></h1>
<% 
	Resultados rs = null;
	if(cve != 0)
	{
      rs = UtilDB.ejecutaConsulta("Select * from estados where cve_regiones= " + cve + " Order By Nombre"); 	
	}
	else
	{	
      rs = UtilDB.ejecutaConsulta("Select * from estados Order By Nombre"); 	
	}
	
%>



<script language="JavaScript">


</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<form action="" method="post" name="forma">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF" align="center">
			<tr>
				<td colspan="6" valign="top">
					<p align="center" class="encTablas">Estados: </p>
					<table width="99%" align="center" class="grid" cellpadding="3"
						cellspacing="0">
					</table>
				<tr>
				  <td class="textoCeldasChicas">&nbsp;</td>
				  <td colspan="2" class="textoCeldasChicas">&nbsp;</td>
				  <td class="textoCeldasChicas">&nbsp;</td>
			    </tr>
			  </td>
			<%
			while (rs.next()){
			%>
		    <tr align="center">
		        <td>
		            <a href="planteles.jsp?cve=<%=rs.getInt("cve_estados") %>">
		               <h2><%=rs.getString("nombre")%></h2>
		            </a>
		        </td> 
		    </tr>
			<% } %>
		</form>
	</body>
</html>