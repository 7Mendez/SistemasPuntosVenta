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
				 base.*, 
				 comun.*,
				 sistema.*, 
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
	Resultados rs = null;
	String evento = "";
	String clave =  request.getParameter("xClave") != null ? request.getParameter("xClave") : "0";
	String accion = request.getParameter("xAccion") != null ? request.getParameter("xAccion"): "listado";
	
%>

<script language="JavaScript">
	function recargaCombo() {
		forma.xAccion.value = "recarga";
		forma.submit();}
	function recargaC() {
		forma.xAccion.value = "recargaC";
		forma.submit();}
	function recargaE() {
		forma.xAccion.value = "recargaE";
		forma.submit();}
	function salir() {
		window.location = "contenido.jsp"

</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
	<form action="" method="post" name="forma">
		<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"></table>
			<tr>
				<td>
				<% 
				if (accion.equals("listado"))
				{
				%>
						<table width="90%" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td colspan="8" >LISTADO DE PLANTELES</td>
						</tr>
						<tr>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
							<td> &nbsp; </td>
						</tr>
					</table>
				<%
				}
					else
				{
				%>
					<table width="90%" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td> </td>
						</tr>
					</table>
				<%
					}
				%>
				</td>
			</tr>
		
	</form>
</body>
</html>
