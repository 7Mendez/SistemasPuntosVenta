<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.:Catalogo Estados:.</title>
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
/*
 * <p>Title: Nombre del Sistema</p>
 * <p>Description: Aplicación Web para Nombre del Sistema</p>
 * <p>Copyright: Copyright (c) 20XX</p>
 * <p>Company: Nombre Compañía </p>
 * @author Henry Abimael Méndez Maldonado
 * @version 1.0
 */
%>
<%
Resultados rs =null;

String linea="";
String evento="";

	String clave =request.getParameter("xClave") !=null ? request.getParameter("xClave") : "0";
	String accion=request.getParameter("xAccion") !=null ? request.getParameter("xAccion"): "listado";
int cveRegiones = request.getParameter("xCveRegiones") !=null ? Integer.parseInt(request.getParameter("xCveRegiones")):0;
int cveEstado = request.getParameter("xCveEstado") !=null ? Integer.parseInt(request.getParameter("xCveEstado")):0;
String nombre=request.getParameter("xNombre")!=null? request.getParameter("xNombre"):"";

if (accion.equals("recargaE") || accion.equals("recargaR"))
	{
		accion="listado";
	}

//Creacion de Un nuevo objeto estados
Estados estados = new Estados();
if (accion.equals("recarga"))
	{
		estados.setCveEstados(Integer.parseInt(clave));
		nombre = estados.getNombre();	
		System.out.println("nombre: "+nombre);
	}

if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
{
	if (Integer.parseInt(clave)==0)
	{
		rs=UtilDB.ejecutaConsulta("select cve_estados from estados order by cve_estados desc limit 1");
		if (rs.recordCount()==0)
			clave="1";
		else
		{
			while(rs.next())
			{
				clave = String.valueOf(rs.getInt("cve_estados")+1);
			}
		}
	}
estados.setCveEstados(Integer.parseInt(clave));
estados.setCveRegiones(cveRegiones);
estados.setNombre(nombre.trim());
estados.graba();

if (accion.equals("grabaNuevo"))
	{
		clave = "0";
		accion = "nuevo";
		nombre = "";
		cveRegiones =0;
	}
if (accion.equals("grabaCierra"))
	{
		accion="listado";
	}

	}
%>

<script language="JavaScript">

	function recargaE(){
		forma.xAccion.value = "recargaE";
		forma.submit();}
	function recargaR(){
		forma.xAccion.value = "recargaR";
		forma.submit();}
	function recarga(clave){
		forma.xClave.value=clave;
		forma.xAccion.value = "recarga";
		forma.submit();}
	function salir(){
		window.location ="index.jsp";}
		/*----*/
	function listar(){
    	forma.xAccion.value = "listado";
    	forma.xClave.value = "0";
    	recarga();
    	forma.submit();
    }
    function nuevo(){
    	forma.xAccion.value = "nuevo";
    	forma.xClave.value = "0";
    	forma.submit();
    }

    function validar(accion)
    {
    	forma.xAccion.value=accion;
    	errores=0;
    	if (forma.xCveRegiones.value == "0") errores++;
    	if (forma.xNombre.value=="") errores++;
    	if (errores>0)
    	{
    		alert("Debe captura todos los Datos...");
    	}
    	else
    	{
    		forma.submit();
    	}
    }
</script>

<body bgproperties="fixed" topmargin="0" leftmargin="0" >
		<form action="" method="POST" name="forma">
			<input type="hidden" name="xAccion" id="xAccion" value="<%=accion%>"/>
			<input type="text" name="xClave" id="xClave" value="<%=clave%>"/>
			<table width="100%" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" border="0">
				<tr>
					<td height="163">
						<%
						if(accion.equals("listado"))
						{
						%>
						<table width="100%" cellpadding="0" cellspacing="0" align="center" border="0" class="tablaBotones">
							<tr>
								<td>Regiones</td>
								<td>
									<select name="xCveRegiones" class="captura" onchange="recargaR()">
										<option value="0">Elegir una de las Opciones</option>
										<%
										linea="select * from regiones order by nombre";
										rs = UtilDB.ejecutaConsulta(linea);
										while(rs.next())
										{
										
										%>
										<option value="<%=rs.getInt("cve_regiones")%>"<%=rs.getInt("cve_regiones")==cveRegiones?"selected":""%>><%=rs.getString("nombre")%></option>
										<%
										}
										rs.close();
										rs = null;
										%>
									</select>
								</td>
							</tr>

							

							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<br>   
						<table width="100%" cellpadding="0" cellspacing="0" align="center" border="0">
							<tr>
								<td><a href="#" onclick="nuevo();"><img border="0" src="imagenes/botones/nuevo.png"></a></td>
								<td><img border="0" src="imagenes/botones/disabled/guardarycerrar.png"></td>
								<td><img border="0" src="imagenes/botones/disabled/guardarynuevo.png">
								</td>
								<td><img border="0" src="imagenes/botones/disabled/eliminar.png"></td>
								<td><a href="#" onclick="salir();"><img border="0" src="imagenes/botones/cerrar.png"></a></td>
							</tr>
						</table>
						<br>
						<br>
						</table>
						<table width="100%" cellpadding="0" cellspacing="0" align="center" border="0">
							<tr>
								<td colspan="10" align="center"><h1 class="encTablas">LISTADO DE ESTADOS</h1></td>
							</tr>
							<tr class="textoCeldasPie">
							  <td >.</td>
							  <td width="5%">&nbsp;</td>
							  <td >Nombre del Estado</td>
							  <td >Regiones</td>
							</tr>
                            <%
                            linea = "select cve_estados, e.nombre as estado, r.nombre as regiones from estados as e, regiones as r  where e.cve_regiones=r.cve_regiones and r.cve_regiones= "+ cveRegiones + "  order by e.nombre" ;
							//linea="select * from estados where cve_regiones= "+cveRegiones+"  Order by nombre";
								 //if (cveEstado>0)
								System.out.println(cveRegiones);
							rs = UtilDB.ejecutaConsulta(linea);
							if (rs.recordCount()==0)
							{
							%>
                            <tr>
                            <td height="32" colspan="10" align="center">No existe registros...</td>
                            </tr>
                            <%
							}
							else
							{
								int x=0;
								String clase="";
								while (rs.next()){
									x++;
									clase="textoCeldasChicas";
									clase="textoCeldasChicas22";
									if(x % 2==0) clase="textoCeldasChicas";
									if(x % 2!=0) clase="textoCeldasChicas22";
									%>
                             <tr class="<%=clase%>">
                               <td align="center"><%=x%>.-</td>
                               <td align="center">
                               	<a href="#" onclick="recarga('<%=rs.getInt("cve_estados")%>')">
                               		<img src="imagenes/edit.png" border="0"/>
                               	</a>
                               </td>
                                <td align="center"><%=rs.getString("estado")%></td>
								<td align="center"><%=rs.getString("regiones")%></td>
							</tr>
                            <%
								}
							}
						%>
                        </table>
                        <%
						}
						else
						{
						%>
						<br>
						<br>
						<table width="99%" cellpadding="0" cellspacing="0" align="center" border="0">
							<tr>
								<td width="20%" align="center"><a href="#" onclick="nuevo();"><img border="0" src="imagenes/botones/disabled/nuevo.png"></a></td>
								<td><a href="#" onclick="validar('grabaCierra');"><img border="0" src="imagenes/botones/guardarycerrar.png"></a></td>
								<td><a href="#" onclick="validar('grabaNuevo');"><img border="0" src="imagenes/botones/guardarynuevo.png"></a></td>
								<td><a href="#" onclick="eliminar('<%=clave%>');"><img border="0" src="imagenes/botones/eliminar.png"></a></td>
								<td><a href="#" onclick="listar();"><img border="0" src="imagenes/botones/cerrar.png"></a></td>
							</tr>
						</table>
						<br>
						<table width="90%" cellpadding="0" cellspacing="0" align="center" border="0">
							<tr>
							  <td width="30%">&nbsp;</td>
							  <td width="70%">&nbsp;</td>
						  </tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
						  </tr>
							<tr>
                              <td>Regiones</td>
								<td>
									<select name="xCveRegiones" class="captura">
										<option value="0">Elegir una de las Opciones</option>
										<%
										linea="select * from regiones order by nombre";
										rs = UtilDB.ejecutaConsulta(linea);
										while(rs.next())
										{
										%>
										<option value="<%=rs.getInt("cve_regiones")%>" 
													   <%=rs.getInt("cve_regiones")==cveRegiones?"selected":""%>>
													   <%=rs.getString("nombre")%></option>
										<%
										}
										rs.close();
										rs = null;
										%>
									</select>
                              </td>
						  </tr>
						  <tr>
							  <td>Nombre:</td>
							  <td>
							  	<input type="text" class="captura" name="xNombre" id="xNombre" maxlength="120" size="40" value="<%=nombre%>"/>
							  </td>
						  </tr>
							<tr>
							  <td>&nbsp;</td>
							  <td><input type="text" name="xCveRegiones" id="xCveRegiones" value="<%=cveRegiones%>"/></td>
						  </tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
						  </tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
						  </tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							
						</table>
						<%	
						}
						%>
					<!--<tr>
					  <td>                                           
					  </td>
					<tr>--
				  <td>-->               
				  </td>                  
				  </tr>
			</table>
		</form>
</body>
</html>