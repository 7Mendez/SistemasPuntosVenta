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
int cve = request.getParameter("cve")!=null?Integer.parseInt(request.getParameter("cve")):0; 
%>
<h1>Clave: <%=cve %></h1>

<% Resultados rs = null;
    rs = UtilDB.ejecutaConsulta("Select * from planteles where cve_estados= "+ cve + "  Order By nombre" );	
%>


<script language="JavaScript">


</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<form action="" method="post" name="forma">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF" align="center">
			<tr>
				<td colspan="6" valign="top">
					<p align="center" class="encTablas">Planteles: </p>
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
			if(rs.recordCount() == 0) 
			{
			%>
			<tr>
				<td>
					<h1> Universidades Politecnicas No Disponibles</h1>	
				</td>
			</tr>
			<%}
			else {
			while (rs.next()){
			%>
			<tr align="center">
			    <td>
			    	<a href="plantel.jsp?cve=<%=rs.getInt("cve_planteles") %>">
			    		  <h1><%=rs.getString("nombre")%> </h1>
			    	</a>
			    </td> 
			</tr>
			<% }
			} %>
		</form>
 	</body>
</html>