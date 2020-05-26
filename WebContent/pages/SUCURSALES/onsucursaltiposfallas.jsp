<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursaltiposfallas.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head>
<body> 
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1 id="tituloPagina">Crear orden de Trabajo</h1>
				<h2>Registrar Fallas</h2>
				
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   disabled">Documento</td>
							<td class="td_etapa gProveedor  disabled">G. Proveedor</td>
							<td class="td_etapa gMaster     disabled">G. Master</td>
							<td class="td_etapa fallas      enabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    disabled">Revision</td>
							<td class="td_etapa terminar    disabled">Terminar</td>
						</tr>
					</table>
				</div>
				
				<div class="marco">
					<h1>Datos de la orden de trabajo</h1>
					<form id="datosOT">
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
					<h1>Fallas del producto</h1>
					<form id="fallasProducto">
						<table class="table_plana" width="100%">
							<tr>
								<td width="43%">
									<fieldset>
										Tipos de Fallas posibles
										<select multiple="multiple" id="tiposFallas" style="width:100%; height:140px"></select>
									</fieldset>
								</td>
								<td width="13%" align="center" valign="middle" style="vertical-align:middle;">
									<fieldset>
										<input type="button" value="Agregar ->" id="agregar" style="width: 90%"/><br/><br/>
										<input type="button" value="<- Quitar" id="quitar" style="width: 90%"/>
									</fieldset>
								</td>
								<td width="43%">
									<fieldset>
										Fallas del producto cliente
										<select multiple="multiple" id="fallasAsignadas" style="width:100%; height:140px"></select>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<fieldset>
										<label>Descripción de la falla</label>
										<textarea rows="4"  style="width:100%" id="descripcionFalla" maxlength="255"></textarea>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="1">
									<fieldset>
										<input type="button" value="Cancelar" id="cancelar"/>
									</fieldset>
								</td>
								<td></td>
								<td colspan="1">
									<fieldset>
										<input type="button" value="Siguiente" id="siguiente" style="float:right;"/>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
					
					<form id="condiciones">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="condicionesEnvioRecepcion" style="color:red;width: 100%;">Condiciones de recepción y envío de productos  a fallas de fabricación</label><br/>
										<label id="condicionesEnvioRecepcion" style="width: 100%; text-align: justify;"></label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin fallasProoducto -->
			</div>
		</div>
	</div>
</body>
</html>