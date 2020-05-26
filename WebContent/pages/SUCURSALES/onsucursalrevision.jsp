 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalrevision.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1 id="tituloPagina">Crear orden de Trabajo</h1>
				<h2>Revisar Producto</h2>
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   disabled">Documento</td>
							<td class="td_etapa gProveedor  disabled">G. Proveedor</td>
							<td class="td_etapa gMaster     disabled">G. Master</td>
							<td class="td_etapa fallas      disabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    enabled">Revision</td>
							<td class="td_etapa terminar    disabled">Terminar</td>
						</tr>
					</table>
				</div>
				
				<div class="marco">
					<h1>Datos de la orden de trabajo</h1>
					<form id="datos">
						<table class="table_plana" width="100%">
							<tr>
								<td width="55%">
									<fieldset>
										<label id="tipoDocumento" for="idDocumento" style="width:170px;">&nbsp;</label>
										<label id="idDocumento" style="width:315px;">&nbsp;</label>
										
										<label for="idOT" style="width:170px;">N° OT</label>
										<label id="idOT" style="width:315px;"><%=request.getParameter("idOT")%></label>
										
										<label for="sucursal" style="width:170px;">Sucursal</label>
										<label id="sucursal" style="width:315px;""><span id="sucursal.id">&nbsp;</span>&nbsp;<span id="sucursal.glosa">&nbsp;</span></label>
										
										<label for="producto" style="width:170px;">Producto</label>
										<label id="producto" style="width:315px;"><span id="producto.id"></span>&nbsp;<span id="producto.descripcion"></span></label>
									</fieldset>
								</td>
								<td>
									<fieldset>
										<label for="fechaEmision" style="width:145px;">Fecha emisión</label>
										<label id="fechaEmision" style="width:205px;" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="tipo.glosa" style="width:145px;" class="tipoOT">Tipo OT</label>
										<label id="tipo.glosa" style="width:205px;" class="tipoOT">&nbsp;</label>
										
										<label for="sucursal.telefono" style="width:145px;">Teléfono sucursal</label>
										<label id="sucursal.telefono" style="width:205px;">&nbsp;</label>
										
										<label for="producto.marca.nombre" style="width:145px;" class="marca">Marca</label>
										<label id="producto.marca.nombre" style="width:205px;" class="marca">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<fieldset>
										<label for="tipoFallas" class="tipoFallas" style="width:170px;">Tipos de falla</label>
										<label id="tipoFallas" class="tipoFallas" style="width:700px;">&nbsp;</label>
										
										<label for="descripcionFalla" class="descripcionFalla" style="width:170px;">Descripción falla</label>
										<label id="descripcionFalla" class="descripcionFalla" style="width:700px;">&nbsp;</label>
										
										<label for="accesorios" class="accesoriosOT" style="width:170px;">Accesorios</label>
										<label id="accesorios" class="accesoriosOT" style="width:315px;">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- fin datosOT -->
				
				<div class="marco">
					<h1 id="txtPartes" name="txtPartes"></h1>
					<form action="">
						<div id="partes"></div>
					</form>
					<form id="detallePartes">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="numeroSerie" style="width:250px" class="numeroSerie">Número de serie</label>
										<input type="text" id="numeroSerie" style="width:250px" maxlength="32" class="numeroSerie"><br class="numeroSerie"/><br class="numeroSerie"/>
										
										<label for="codigoBarra" style="width: 250px;">Codigo de Barras</label>
										<input type="text" id="codigoBarra" name="codigoBarra" class="digits" style="width:250px" maxlength="10"/><br/><br/>
										
										<label for="numeroAtencion" class="numeroAtencion" style="width: 250px;">N° atención servicio técnico</label>
										<input type="text" id="numeroAtencion" name="numeroAtencion" class="numeroAtencion number" style="width:250px" maxlength="13"><br class="numeroAtencion"/><br class="numeroAtencion"/>
										
										<label for="numeroIncidente" class="numeroIncidente" style="width: 250px;">N° Incidente Marca</label>
										<input type="text" id="numeroIncidente" name="numeroIncidente" class="numeroIncidente" style="width:250px"/><br class="numeroIncidente"/><br class="numeroIncidente"/>
										
										<label for="numeroCelular" class="numeroCelular" style="width:250px">Número de celular</label>
										<input type="text" id="numeroCelular" class="numeroCelular celular" style="width:250px"><br class="numeroCelular"/><br class="numeroCelular"/>
										
										<label for="imei" style="width: 250px;">IMEI</label>
										<input type="text" id="imei" name="imei" class="imei" style="width:250px">
									</fieldset>
									<fieldset >
										<label for="descripcionEstado" style="width:250px">Descripcion del estado</label>
										<textarea rows="2" style="width: 99%" id="descripcionEstado"></textarea><br/><br/>
									</fieldset>
									<br>
									<fieldset>
										<input type="button" value="Volver" id="volver">
										<input type="button" value="Siguiente" id="siguiente" style="float:right; margin-right:10px;">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>