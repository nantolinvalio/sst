<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalgarantiaproveedor.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id='idProducto' value='<%=request.getParameter("idProducto")%>'/>
			<input type="hidden" id='idDocumento' value='<%=request.getParameter("idDocumento")%>'/>
			<input type="hidden" id='tipoDocumento' value='<%=request.getParameter("tipoDocumento")%>'/>
			<div class="container">
				<h1>Crear orden de Trabajo</h1>
				
				<h2><span id="gPTituloH2"></span></h2>
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   disabled">Documento</td>
							<td class="td_etapa gProveedor  enabled">G. Proveedor</td>
							<td class="td_etapa gMaster     disabled">G. Master</td>
							<td class="td_etapa fallas      disabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    disabled">Revision</td>
							<td class="td_etapa terminar    disabled">Terminar</td>
						</tr>
					</table>
				</div>
				
				<div class="marco">
					<h1>Datos del servicio</h1>
					<form id="datosServicio">
						<table class="table_plana">
							<tr>
								<td width="50%">
									<fieldset>
										<label for="id" style="width:100px;"><span id="tipo">&nbsp;</span></label>
										<label id="id" style="width:340px;">&nbsp;</label>
										
										<label for="sucursal" style="width:100px;">Sucursal</label>
										<label id="sucursal" style="width:340px;"><span id="sucursal.id" ></span>&nbsp;<span id="sucursal.glosa"></span></label>
										
										<label for="producto" style="width:100px;">Producto</label>
										<label id="produto" style="width:340px;"><span id="producto.id"></span>&nbsp;<span id="producto.descripcion"></span></label>
									</fieldset>
									
								</td>
								<td width="50%">
									<fieldset>
										<label for="fechaEmision">Fecha de emisón</label>
										<label id="fechaEmision" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="telefono">Telefono</label>
										<label id="telefono">&nbsp;</label>
										
										<label for="marca">Marca</label>
										<label id="marca">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="2" width="100%">
									<fieldset>
										<input type="button" id="consultarHistorial" value="Consultar Historial" style="float:right;"/>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin datosServicio -->
				
				<div class="marco">
					<h1>Servicios técnicos locales</h1>
					<form id="sTLocales">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="consultarST" style="width:650px;">El cliente puede llevar personalmente el producto a servicio técnico</label>
										<input type="button" id="consultarST" value="Consultar servicio técnico" style="float:right; width: 230px"/>
									</fieldset>
									<fieldset>
										<input type="button" id="consultarProcedimiento" value="Consultar Procedimiento" style="float:right; width: 230px"/>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin sTLocales -->
				
				<div class="marco">
					<h1><span id="gPTituloForm">asd</span></h1>
					<form id="garantiaProveedor">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="fechaVencimiento">Fecha de Vencimiento</label>
										<label id="fechaVencimiento" format="dd/MM/yyyy">&nbsp;</label>
									</fieldset>
									<fieldset>
										<label for="visita">¿Requiere visita a domicilio del cliente? </label>
										<input id="visitaS" type="radio" name="visita" value="BT">Si
										<input id="visitaN" type="radio" name="visita" value="GP">No
									</fieldset>
									<fieldset>
										<label for="visita">Usted como tienda puede enviar el producto</label>
										<select id="idDestino"></select>
									</fieldset>
									<fieldset>
										<label for="diasReparacion">Cantidad de dias estimada para la reparacion:</label>
										<label id="diasReparacion">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
					<form id="garantiaProveedorDesactivada">
						<table class="table_plana" width="100%">
							<tr>
								<td width="100%">
									<fieldset>
										<label for="fechaVencimiento">Fecha de Vencimiento</label>
										<label id="fechaVencimiento" format="dd/MM/yyyy">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- garantiaProveedor-->
				
				<form style="margin-top:10px;">
					<table class="table_plana" width="100%">
						<tr>
							<td width="33%">
								<fieldset>
									<input type="button" value="Volver" id="volver">
								</fieldset>
							</td>
							<td width="33%" style="text-align:center;">
								<fieldset style="text-align:center;">
									<input type="button" value="Consultar garantía Master" id="consultarGM">
								</fieldset>
							</td>
							<td width="33%">
								<fieldset >
									<input type="button" value="Crear orden de trabajo" id="crearOT" style="float:right;"/>
								</fieldset>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	
		<div id="procedimientoPopup" title="Procedimiento">
			<form action="" id="mantenedorZonaForm" name="mantenedorZonaForm">
				<fieldset class="fieldset_popup">
					<label id="procedimiento"></label>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>