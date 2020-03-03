<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.: ESTADOS :.</title>
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
 * @author Henry Abimael Méndez Maldonado
 * @version 1.0
 */
%>
<%
Resultados rs = null;

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
	function recargaR() {
		forma.xCveEstado.value = "0"; 
		forma.xAccion.value = "recargaR";
		forma.submit();}
	
	function salir() {
		window.location = "java.jsp";}
	function listar()
	{
		forma.xAccion.value = "listado";
		forma.xClave.value ="0";
		forma.submit();
	}
	function nuevo()
	{
		forma.xAccion.value = "nuevo";
		forma.xClave.value ="0";
		forma.submit();
	}
</script>

<body bgproperties="fixed" topmargin="0" bgproperties="fixed">
	<form action="" method="POST" name="forma">
		<!--
		<input type="hidden" name="xAccion" id="xAccion" value="<%=accion%>">
		<input type="text" name="xClave" id="xClave" value="<%=clave%>">
		-->
		<table border="0" width="100%" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
		
					<table width="98%" cellpadding="0" cellspacing="0" align="center" border="0" >
						
						<tr align="center">
							<td colspan="10" class="encTablas">LISTADO DE ESTADOS</td>
						</tr>
						
						
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
