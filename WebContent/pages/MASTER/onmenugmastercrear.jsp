<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/MASTER/onmenugmastercrear.js?<%= version %>"></script>
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
										
										<label for="garantiaProveedor" style="width:100px;">Garantía Proveedor</label>
										<label id="garantiaProveedor" style="width:340px;">&nbsp;</label>
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
										
										<label for="fechaVencimiento">Fecha de Vencimiento</label>
										<label id="fechaVencimiento" format="dd/MM/yyyy">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin datosServicio -->
				
				<div class="marco">
					<h1>Cliente</h1>
					<form id="cliente">
						<table class="table_plana">
							<tr>
								<td width="60%">
									<fieldset>
										<label for="rut">RUT</label>
										<input id="rut" name="rut" type="text" class="required run" style="width: 30%">
										<input type="button" value="Buscar Cliente" style="width: 25%; float: right;" id="buscarCliente">
										
										<label for="nombreCompleto">Nombre y Apellido</label>
										<input id="nombreCompleto" name="nombreCompleto" type="text" class="required" maxlength="64">
										
										<label for="comuna.provincia.region.id" >REGIÓN</label>
										<select id="comuna.provincia.region.id" name="comuna.provincia.region.id" class="required"></select>
										
										<label for="calle">Dirección</label>
										<input id="calle" name="calle" type="text" class="required" style="width: 40%" maxlength="64">
										
										<label for="numeroCasa" style="width: 5%;  margin-left: 15px">N°</label>
										<input id="numeroCasa" name="numeroCasa" type="text" class="required" maxlength="16" style="width: 10%; float: right; margin-right: 8px">
									</fieldset>
									
								</td>
								<td width="39%">
									<fieldset>
										<label for="telefono">TELEFONO</label>
										<input id="telefono" name="telefono" type="text" maxlength="16">
										
										<label for="celular">CELULAR</label>
										<input id="celular" name="celular" type="text" maxlength="16">
										
										<label for="comuna.id" class="required">COMUNA</label>
										<select id="comuna.id" name="comuna.id" class="required"></select>
										
										<label for="poblacion">POBLACION</label>
										<input id="poblacion" name="poblacion" type="text" maxlength="64">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin datosCliente -->
				
				<div class="marco">
					<h1>Orden de Trabajo</h1>
					<form id="destinoOT">
						<table class="table_plana" width="100%">
							<tr>
								<td width="1%">
									<fieldset class="">
										<input type="radio" style="margin: 6px;" name="destino" id="reparacion" checked="checked" value="reparacion">
										<input type="radio" style="margin: 6px;" name="destino" id="cambio" value="cambio">
									</fieldset>
								</td>
								<td width="99%">
									<fieldset class="">
										<label for="numeroAtencion" style="width: 35%;">Orden de trabajo para reparación</label>
										<label for="numeroAtencion" style="width: 34%;" >N° de atención</label>
										<input id="numeroAtencion" name="numeroAtencion" style="width: 30%;" class="required number" maxlength="12" type="text"/>
										<label for="numeroAutorizacion" style="width: 35%;">Orden de trabajo para cambio </label>
										<label for="numeroAutorizacion" style="width: 34%;">N° de autorización de cambio</label>
										<input id="numeroAutorizacion" name="numeroAutorizacion" style="width: 30%;" class="required number" maxlength="12" type="text" disabled="disabled"/>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
					<form id="destinoReparacion">
						<table class="table_plana" width="100%">
							<tr>
								<td width="100%" colspan="2">
									<fieldset class="">
										<label style="width: 100%">El cliente tiene el producto, seleccione la sucursal que lo recibirá</label>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td width="50%">
									<fieldset class="">
										<label for="region.id">Región</label>
										<select id="region.id" name="region.id" class="required"></select>
										<label for="sucursalRecibe.id">Sucursal</label>
										<select id="sucursalRecibe.id" name="sucursalRecibe.id" class="required"></select>
									</fieldset>
								</td>
								<td width="50%">
									<fieldset class="">
										<label for="comuna.id">Comuna</label>
										<select id="comuna.id" name="comuna.id" class="required"></select>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="2">
									<fieldset class="">
										<label style="width: 100%">Seleccione el servicio técnico al que se enviará el producto</label>
										<input type="checkbox" style="float: left; margin-left: 0; margin-right: 0; width: 3%;" id="centroDistribucionLaVara">
										<label style="width: 96%; float: right;">El servicio tecnico es del Centro de distribución la Vara</label>
										<select id="sTecnico.id" name="sTecnico.id" class="required"></select>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
					<form id="destinoCambio" style="display: none;">
						<table class="table_plana" width="100%">
							<tr>
								<td width="100%" colspan="2">
									<fieldset class="">
										<label style="width: 100%">Seleccione la sucursal donde el cliente retira el producto cambiado</label>
										<label style="width: 20%">Sucursal</label>
										<select style="width: 40%" id="sucursalRetira.id" class="required"></select>
										<label style="width: 100%">Seleccione la ubicacion del producto a recuperar</label>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td width="1%">
									<fieldset class="">
										<input type="radio"style="margin: 6px;" name="origenCambio" id="origenOrigen" value="origenOrigen" checked="checked">
										<input type="radio"style="margin: 6px;" name="origenCambio" id="origenSTecnico" value="origenSTecnico">
										<input type="radio"style="margin: 6px;" name="origenCambio" id="origenDomicilio" value="origenDomicilio">
									</fieldset>
								</td>
								<td width="99%">
									<fieldset class="" >
										<label style="width: 100%">Sucursal donde el cliente retiró producto cambiado</label>
										<label style="width: 20%">Servicio técnico </label>
										<select style="width: 40%" id="stecnicoRetira.id"></select>
										<label style="width: 100%">Producto hay que recuperarlo del domicilio del cliente </label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin datosCliente -->
				
				<!-- div class="marco">
					<h1>Servicios técnicos locales</h1>
					<form id="sTLocales">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="consultarST" style="width:650px;">El cliente puede llevar personalmente el producto a servicio técnico</label>
										<input type="button" id="consultarST" value="Consultar servicio técnico" style="float:right;"/>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div> Fin sTLocales -->
				
				<!--div class="marco">
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
										<input id="visitaS" type="radio" name="visita" value="S">Si
										<input id="visitaN" type="radio" name="visita" value="N">No
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
				</div> garantiaProveedor-->
				
				<form style="margin-top:10px;">
					<table class="table_plana" width="100%">
						<tr>
							<td width="33%">
								<fieldset>
									<input type="button" value="Volver" id="volver">
								</fieldset>
							</td>
							<td width="33%" style="text-align:center;">
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
	</div>
</body>
</html>