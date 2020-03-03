<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>.: Catalogo Planteles :.</title>
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
	String clave =  request.getParameter("xClave") != null ? request.getParameter("xClave") : "0";
	String accion = request.getParameter("xAccion") != null ? request.getParameter("xAccion"): "listado"; 
	String linea = "";

	int cveEstado = request.getParameter("xCveEstado") != null ? 
					Integer.parseInt(request.getParameter("xCveEstado")):0;
	int cveRegiones = request.getParameter("xCveRegiones") != null ? 
					Integer.parseInt(request.getParameter("xCveRegiones")):0;

	int cveMunicipio=request.getParameter("xCveMunicipio")!=null ? 
					Integer.parseInt(request.getParameter("xCveMunicipio")):0;

	String nombre = request.getParameter("xNombre") != null ? request.getParameter("xNombre") : "";
	String direccion = request.getParameter("xDireccion") != null ? request.getParameter("xDireccion") : "";
	String nombreContacto = request.getParameter("xNombreContacto") != null ? request.getParameter("xNombreContacto") : "";
	String telefono = request.getParameter("xTelefono") != null ? request.getParameter("xTelefono") : "";
	String email = request.getParameter("xEmail") != null ? request.getParameter("xEmail") : "";
	String paginaWeb = request.getParameter("xPaginaWeb") != null ? request.getParameter("xPaginaWeb") : "";
	String nombreCorto = request.getParameter("xNombreCorto") != null ? request.getParameter("xNombreCorto") : "";

	if (accion.equals("recargaE") || accion.equals("recargaR")) 
	{
		accion="listado";	
	}

	Planteles planteles = new Planteles();
	if (accion.equals("recarga")){
		planteles.setCvePlanteles(Integer.parseInt(clave));				
		nombre=planteles.getNombre();
		direccion=planteles.getDireccion();
		nombreContacto=planteles.getNombreContacto();
		telefono=planteles.getTelefono();
		email=planteles.getEmail();
		paginaWeb=planteles.getPaginaWeb();
		nombreCorto=planteles.getNombreCorto();
		cveEstado=planteles.getCveEstados();
		cveMunicipio=planteles.getCveMunicipios();

		System.out.println("nombre: "+nombre);
	}



	
	if (accion.equals("graba") || accion.equals("grabaCierra") || accion.equals("grabaNuevo"))
		{
		if(Integer.parseInt(clave)==0){
			rs=UtilDB.ejecutaConsulta("select cve_planteles from planteles order by cve_planteles desc limit 1");
			if (rs.recordCount()==0) 
				clave="1";
			else
			{
			while (rs.next()){
					clave=String.valueOf(rs.getInt("cve_planteles") + 1);
				}
			}
		}
		planteles.setCvePlanteles(Integer.parseInt(clave));
		planteles.setCveEstados(cveEstado);
		planteles.setCveMunicipios(cveMunicipio);
	
		planteles.setNombre(nombre.trim());
		planteles.setDireccion(direccion.trim());
		planteles.setNombreContacto(nombreContacto.trim());
		planteles.setTelefono(telefono.trim());
		planteles.setEmail(email.trim());
		planteles.setPaginaWeb(paginaWeb.trim());
		planteles.setNombreCorto(nombreCorto.trim());
		planteles.graba();
	
		if (accion.equals("grabaNuevo"))
		{
			clave="0";
			accion="nuevo";
			nombre="";
			direccion="";
			nombreContacto="";
			telefono="";
			email="";
			paginaWeb="";
			nombreCorto="";
		}

		if(accion.equals("grabaCierra")){
			accion="listado";
		}
	}
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

	function recarga(clave){
		forma.xClave.value=clave;
		forma.xAccion.value = "recarga";
		forma.submit();}

		
	function salir() {
		window.location = "index.jsp";}
	function listar()
	{
		forma.xAccion.value = "listado";
		forma.xClave.value ="0";
		forma.submit();
	}
	function nuevo()
	{
		forma.xAccion.value = "validar(nuevo)";
		forma.xClave.value ="0";
		forma.submit();
	}
	function validar(accion)
	{
		forma.xAccion.value= accion;
		errores=0;
		if(forma.xCveMunicipio.value =="0") error++;
		if(forma.xCveEstado.value =="0") error++;

		if (forma.xNombre.value=="") errores++;
		if (forma.xDireccion.value=="") errores++;
		if (forma.xNombreContacto.value=="") errores++;
		if (forma.xTelefono.value=="") errores++;
		if (forma.xEmail.value=="") errores++;
		if (forma.xPaginaWeb.value=="") errores++;
		if (forma.xNombreCorto.value=="") errores++;

		if (errores > 0)
		{
			alert("Debe capturar todos los Datos...")
		}
		else{
			forma.submit();
		}

	}
</script>

<body bgproperties="fixed" topmargin="0" bgproperties="fixed">
	<form action="" method="POST" name="forma">
		<input type="hidden" name="xAccion" id="xAccion" value="<%=accion%>">
		<input type="text" name="xClave" id="xClave" value="<%=clave%>">
		<table border="0" width="100%" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
					<%
					if(accion.equals("listado"))
						{
					%>
					<table width="98%" cellpadding="0" cellspacing="0" align="center" border="0" >
						
						<tr align="center">
							<td colspan="10" class="encTablas">LISTADO DE PLANTELES</td>
						</tr>
						
						<tr>
							<td>Regiones:</td>
							<td> &nbsp; </td>
							<td>
								<select name="xCveRegiones" class="captura" onchange="recargaR();">
									<option value="0">Elegir una de las opciones</option>
									
								</select>
							</td>
						</tr>
						<tr>
							<td>Estados:</td>
							<td> &nbsp; </td>
							<td>
								<select name="xCveEstado" class="captura" onchange="recargaE();">
									<option value="0">Elegir una de las opciones</option>
									
									
								</select>
							</td>
						</tr>
						
					</table>
					<br>   
						<table width="99%" cellpadding="0" cellspacing="0" align="center" border="0">
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

					
				<% 
				if (accion.equals("listado"))
				{
				%>
					
					<table width="90%" cellpadding="0" cellspacing="0" align="center" border="0">
						<tr class="textoCeldasPie" align="center" >
							<td> &nbsp; </td>
							<td style="width: 5%"> &nbsp; </td>
							<td>Nombre Plantel</td>
							<td>Direccion</td>
							<td>Municipio</td>
							<td>Estado</td>
							<td>Telefono</td>
							<td>Correo</td>
							<td>Pagina</td>
							<td>Contacto</td>
						</tr>

				<% 
				linea = "Select p.*, e.nombre as estado, m.nombre as municipio " +
						"From Planteles as p, estados as e, municipios as m " + 
						"where p.cve_estados=e.cve_estados and p.cve_municipios = m.cve_municipios " +
						"and e.cve_estados=m.cve_estados ";
				if ( cveEstado > 0){
					linea+= " and e.cve_estados= " + cveEstado;
					linea+= " order by p.nombre";
				}
				rs = UtilDB.ejecutaConsulta(linea);
				if ( rs.recordCount() == 0 ) {
				%>
					<tr>
						<td colspan="9" align="center" >No existen registros...</td>
					</tr>
				<%
				} else { 
					int x = 0; 
					String clase= "";
					while ( rs.next() )
					{
						x++;
						clase="textoCeldasChicas";
				        if ( x % 2==0)
				        clase="textoCeldasChicas2";	
				%>
						
						<td>  &nbsp; </td>
						<tr class="<%=clase%>" align="center">
							<td> <%=x%>.- </td>
							<td>
								<a href="#" onclick="recarga('<%=rs.getInt("cve_planteles")%>')">
									<img src="imagenes/edit.png" border="0">
								</a>
							</td>
							<td> <%=rs.getString("nombre")%> </td>
							<td> <%=rs.getString("direccion")%> </td>
							<td> <%=rs.getString("municipio")%> </td>
							<td> <%=rs.getString("estado")%> </td>
							<td> <%=rs.getString("telefono")%> </td>
							<td> <%=rs.getString("email")%> </td>
							<td> <%=rs.getString("pagina_web")%> </td>
							<td> <%=rs.getString("nombre_contacto")%> </td>
						</tr>
				<% 		} //cerramos  while
					} //cerramos el Else
					}
			 %> 

				</table>

					<%
					}
					else
					{
					%>
					<br>
						<table width="99%" cellpadding="0" cellspacing="0" align="center" border="0">
							<tr>
								<td width="20%" align="center"><a href="#" onclick="nuevo();"><img border="0" src="imagenes/botones/disabled/nuevo.png"></a></td>
								<td><a href="#" onclick="validar('grabaCierra');"><img border="0" src="imagenes/botones/guardarycerrar.png"></a></td>
								<td><a href="#" onclick="validar('grabaNuevo');"><img border="0" src="imagenes/botones/guardarynuevo.png"></a></td>
								<td><a href="#" onclick="listar();"><img border="0" src="imagenes/botones/cerrar.png"></a></td>
							</tr>
						</table>
					<br>

					<table width="98%" cellpadding="0" cellspacing="0" align="center">
							<tr>
							  <td width="30%">&nbsp;</td>
							  <td width="70%">&nbsp;</td>
						  </tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>&nbsp;</td>
						  </tr>
							
						<tr>
							<td>Estado:</td>
							 <td>
                              <select name="xCveEstado" class="captura" onchange="recargaC()">
                              	<option value="0">Elegir una de las Opciones</option>
                              	<%
                              		linea="select * from estados order by nombre";
                              		rs = UtilDB.ejecutaConsulta(linea);
                              		while( rs.next() )
                              		{
                              			if( cveEstado==0) cveEstado=rs.getInt("cve_estados");
                              	%>
                              	<option value="<%=rs.getInt("cve_estados")%>"
                              		<%=rs.getInt("cve_estados")==cveEstado?"selected":""%>><%=rs.getString("nombre")%></option>
                              		<%
                              		}
                              		rs.close();
                              		rs = null;
                              		%>
                              	</option>
                              </select>
                             </td>
						  </tr>

							<tr>
							  <td>Municipio:</td>
							  <td>
							  	<select name="xCveMunicipio" class="captura">
                              	<option value="0">Elegir una de las Opciones</option>
                              	<%
                              		linea="select * from municipios where cve_estados=" + cveEstado + " order by nombre";
                              		rs = UtilDB.ejecutaConsulta(linea);
                              		while( rs.next() )
                              		{
                              	%>
                              	<option value="<%=rs.getInt("cve_municipios")%>"
                              				   <%=rs.getInt("cve_municipios")==cveMunicipio?"selected":""%>><%=rs.getString("nombre")%></option>
                              		<%
                              		}
                              		rs.close();
                              		rs = null;
                              		%>
                              	</option>
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
							  <td>Direcci&oacute;n:</td>
							  <td><input type="text" class="captura" name="xDireccion" id="xDireccion" maxlength="200" size="40" value="<%=direccion%>"/></td>
						  </tr>
							<tr>
							  <td>Contacto:</td>
							  <td><input type="text" class="captura" name="xNombreContacto" id="xNombreContacto" maxlength="90" size="40" value="<%=nombreContacto%>"/></td>
						  </tr>
							<tr>
							  <td>Tel&eacute;fono:</td>
							  <td><input type="text" class="captura" name="xTelefono" id="xTelefono" maxlength="120" size="40" value="<%=telefono%>"/></td>
						  </tr>
							<tr>
							  <td>e-mail:</td>
							  <td><input type="text" class="captura" name="xEmail" id="xEmail" maxlength="120" size="40" value="<%=email%>"/></td>
						  </tr>
							<tr>
							  <td>P&aacute;gina Web:</td>
							  <td><input type="text" class="captura" name="xPaginaWeb" id="xPaginaWeb" maxlength="120" size="40" value="<%=paginaWeb%>"/></td>
						  </tr>
							<tr>
							  <td>Nombre Corto:</td>
							  <td><input type="text" class="captura" name="xNombreCorto" id="xNombreCorto" maxlength="200" size="40" value="<%=nombreCorto%>"/></td>
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
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							
						</table>
                     <%}%>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
