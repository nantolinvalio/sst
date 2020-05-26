<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalbuscardocumento.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Crear orden de Trabajo</h1>
				<h2>Consultar Documento </h2>
				
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   enabled">Documento</td>
							<td class="td_etapa gProveedor  disabled">G. Proveedor</td>
							<td class="td_etapa gMaster     disabled">G. Master</td>
							<td class="td_etapa fallas      disabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    disabled">Revision</td>
							<td class="td_etapa terminar    disabled">Terminar</td>
						</tr>
					</table>
				</div>
				
				<div class="marco">
					<h1>Buscar Documento</h1>
					<form id="buscarDocumento">
						<table class="table_plana" width="100%">
							<tr>
								<td width="40%">
									<fieldset>
										<label for="tipoDocumento">Tipo Documento</label>
										<select id="tipoDocumento" name="tipoDocumento">
											<option value="boleta">Boleta</option>
											<option value="factura">Factura</option>
										</select>
									</fieldset>
								</td>
								<td>
									<fieldset>
										<label for="idDocumento" style="text-align:center">Número</label>
										<input type="text" id="idDocumento" name="idDocumento" class="required number"/>
									</fieldset>
								</td>
								<td width="15%">
									<fieldset>
										<input type="button" id="limpiar" name="limpiar" value="Limpiar" style="float:right;">
									</fieldset>
								</td>
								<td width="15%">
									<fieldset>
										<input type="button" id="buscar" name="buscar" value="buscar">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- FIN FORMULARIO BUSCAR DOCUMENTO -->
				
				<div class="marco">
					<h1>Resultado</h1>
					<form id="resultado" style="display:none">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="id"><span id="tipo">&nbsp;</span></label>
										<label id="id">&nbsp;</label>
										
										<label for="sucursal.id">Sucursal</label>
										<label id="sucursal.id">&nbsp;</label>
									</fieldset>
								</td>
								<td>
									<fieldset>
										<label for="fechaEmision">Fecha de emisón</label>
										<label id="fechaEmision" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="telefono">Telefono</label>
										<label id="telefono">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
						<div class="marco_interior_tabla">
								<div id="productos"></div>
							</div>
					</form>
				</div>
			</div><!-- FIN CONTAINER -->
		</div>
	
		<div id="popupAutorizarCambio" title="Autorizar cambio por Jefe Tienda" style="padding: 0 !important">
			<div class="container">
				<form id="autorizar" autocomplete="off">
					<div class="marco">
					<table class="table_plana">
						<tr>
							<td>
								<fieldset>
									<input type="hidden" id="idProducto"/>
								
									<input type="radio" name="motivo" value="FF" id="fallaFabricacion" class="fallaFabricacion">
									<span id="fallaFabricacionGlosa" class="fallaFabricacion">CAMBIO ESPECIAL POR FALLA DE FABRICACIÓN</span><br/>
									
									<input type="radio" name="motivo" id="precioLimite" value="VA" class="precioLimite">
									<span id="precioLimiteGlosa" class="precioLimite">&nbsp;</span><br/>
									
									<input type="radio" name="motivo" id="fallaReiterada" value="FR" class="fallaReiterada">
									<span id="fallaReiteradaGlosa" class="fallaReiterada">&nbsp;</span><br/><br/>
								</fieldset>
								<fieldset class="numeroSerie" style="display:none !important;">
									<label for="numeroSerie" class="numeroSerie" style="width: 150px; display:none !important;">Numero de Serie</label>
									<input type="text" name="numeroSerie" id="numeroSerie" class="numeroSerie" maxlength="32" style="width: 200px; display:none !important;"/>
								</fieldset>
								<fieldset>	
									<label for="observaciones">Observaciones</label>
									<textarea rows="3" id="observaciones" name="observaciones" style="width: 100%" class="required"></textarea><br/><br/>
									
									<label for="run" style="width: 150px;">Jefe de Tienda</label>
									<input type="text" id="run" name="run" class="required" style="width: 200px;" autocomplete="off">
								</fieldset>
								<fieldset>
									<label for="clave" style="width: 150px;">Clave</label>
									<input type="password" id="clave" name="clave" clasS="required" style="width: 200px" autocomplete="off">
								</fieldset>
								<fieldset style="text-align: center;">
									<input type="button" value="Autorizar" id="autorizarCambio">
								</fieldset>
							</td>
						</tr>
					</table>
					</div><!-- Fin marco_interno -->
				</form>
			</div><!-- Fin container popup -->
		</div><!-- Fin Popup -->
		
		<div id="popupCliente" title="Información Cliente" style="padding: 0 !important">
			<div class="container">
				<form id="cliente" name="cliente" class="formTab">
					<div class="marco">
						<table class="table_plana" width="100%">
							<tr>
								<td width="50%">
									<fieldset>
										<input type="hidden" id="idProducto">
										<input type="hidden" id="banderaOrigen" value="">
										<label for="rut">Rut cliente</label>
										<input id="rut" name="rut" type="text" class="required run" style="width: 30%"/>
										<input type="button" id="buscarCliente" name="buscarCliente" value="Buscar" style="width: 20%"/>
										
										
										<label for="nombreCompleto">Nombre Y Apellido</label>
										<input type="text" id="nombreCompleto" name="nombreCompleto" class="required" maxlength="64"/>
										
										<label for="calle">Dirección</label>
										<input type="text" id="calle" name="calle" class="required" maxlength="64"/>
										
										<label for="numeroCasa">Numero</label>
										<input type="text" id="numeroCasa" name="numeroCasa" class="required" maxlength="16"/>
										
										<label for="comuna.provincia.region.id">Región</label>
										<select id="comuna.provincia.region.id" name="comuna.provincia.region.id" class="required"></select>
										
										<label for="comuna.id">Comuna</label>
										<select id="comuna.id" name="comuna.id" class="required"></select>
										
										<label for="celular">Celular</label>
										<input id="celular" type="text" name="celular" class="celular"/>
									
										<label for="telefono">Teléfono</label>
										<input id="telefono" type="text" name="telefono" class="telefono"/>
									
										<label for="idOT">Número OT</label>
										<label id="idOTCliente">&nbsp;</label>
									</fieldset><br>
									
									<fieldset>
										<input type="button" id="btnTicketCambio" value="Generar ticket de cambio"/>
<!-- 										<label id="idOT">Número OT</label> -->
									</fieldset>
									
									<fieldset>
										<input type="button" id="btnImprimirTicketCambio" value="Imprimir ticket de cambio"/>
										<input type="button" id="btnReporteCambio" value="Imprimir reporte de cambio" style="float:center;"/>
									</fieldset>
								</td>
								
									
							</tr>
						</table>
					</div><!-- Fin Marco -->
				</form>
			</div>
		</div><!-- popupCliente -->
		
		<div id="popupRegistrarCambio" title="Autorización de cambio" style="padding: 0 !important">
			<div class="container">
				<form id="registrar" name="registrar" style="padding-bottom: 0px">
					<div class="marco">
						<table class="table_plana">
							<tr>
								<td>
									<fieldset>
										<label for="numeroSerie" class="numeroSerie">Numero de Serie</label>
										<input type="text" name="numeroSerie" id="numeroSerie" class="numeroSerie required"  maxlength="32" style="width: 200px; "/>
										
										<label for="numeroCertificado">Nº de certificado</label>
										<input type="text" id="numeroCertificado" name="numeroCertificado"  class="required"  maxlength="64" style="width: 200px"/>		
										
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<!-- fieldset>	
									    <input type="checkbox" id="sinProductoFisico" name="sinProductoFisico" style="float: left; margin: 5px;width: 3%">
					            		<label for="sinProductoFisico" style="text-align:left; float: right; width: 95%">Sin Producto Fisico</label>
					                </fieldset -->
					                <input type="checkbox" id="sinProductoFisico" name="sinProductoFisico"> Sin Producto Fisico<BR>
								</td>
							</tr>   
							<tr>
								<td>		
									<fieldset style="text-align: center;">
										<input type="button" id="btnValidar" name="btnValidar" value="Validar" onclick="javascript: asignaChecked();">
									</fieldset>								
								</td>
							</tr>
						</table>
					</div><!-- Fin Marco -->
				</form>
			</div><!-- Fin Marco -->
		</div><!-- popupRegistrarCambio -->
	</div>
</body>
</html>