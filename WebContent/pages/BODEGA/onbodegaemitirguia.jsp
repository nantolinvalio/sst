<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onbodegaemitirguia.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Guías de despacho</h1>
				<div class="marco">
					<h1>Buscar ordenes de trabajo</h1>
					<div class="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="32%">
										<fieldset>
											<label for="idSucursal">Sucursal</label>
											<select id="idSucursal" name="idSucursal"></select>
											
											<label for="estadoOT">Estado</label>
											<select id="estadoOT" name="estadoOT"></select>
											
											<label for="idSTecnico">S. Técnico</label>
											<select id="idSTecnico" name="idSTecnico"></select>
										</fieldset>
									</td>
									<td width="35%">
										<fieldset>
											<label for="fechaRecepcionOtInicio" style="width: 180px">Fecha recepción inicio</label>
											<input type="text" id="fechaRecepcionOtInicio" name="fechaRecepcionOtInicio" class="fecha dateDDMMYYYY"/>
											
											<label for="fechaRecepcionOtFin" style="width: 180px">Fecha recepción fin</label>
											<input type="text" id="fechaRecepcionOtFin" name="fechaRecepcionOtFin" class="fecha dateDDMMYYYY"/>
										</fieldset>
										<fieldset style="text-align: center">
											<input type="button" id="limpiar" value="Limpiar">
											<input type="button" id="buscar" value="Buscar">
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="idOT">N° O.T.</label>
											<input type="text" id="idOT" name="idOT" class="number"/>
											
											<label for="idProducto">Código corto</label>
											<input type="text" id="idProducto" name="idProducto" class="number">
											
											<label for="idEtapa">Etapa</label>
											<select id="idEtapa" name="idEtapa">
												<option value="">[Seleccione]</option>
							                    <option value="1" >Recibida de sucursal</option>
							                    <option value="2" >Enviada a S. técnico</option>
							                    <option value="3" >Recibida de S. técnico</option>
							                    <option value="4" >Enviada a sucursal</option>
							                    <option value="5" >Recibida de bodega</option>
							                    <option value="6" >Enviada a bodega</option>
											</select>
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Resultados</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="impresionRapida" title="Imprimir" class="container">
		<div class="marco">
			<h1>Impresión Rápida de Guía</h1>
			<div id="onbodegaemitirguiadespachoPanel">
				<form id="destino" name="destino" class="formTab">
					<table class="table_plana" width="100%">
						<tr>
							<td width="1%">
								<fieldset class="">
									<input type="radio" style="margin: 6px;" name="destinoGroup" id="destinoGroup.stecnico">
									<input type="radio" style="margin: 6px;" name="destinoGroup" id="destinoGroup.sucursal">
									<input type="radio" style="margin: 6px;" name="destinoGroup" id="destinoGroup.bodega">
								</fieldset>
							</td>
							<td width="99%">
								<fieldset class="">
									<label for="destino.stecnico.id" style="width: 25%;">Destino Servicio Técnico</label>
									<select id="destino.stecnico.id" name="destino.stecnico.id" style="width: 74%;" class="required"></select>
									<label for="destino.sucursal.id" style="width: 25%;">Destino Sucursal</label>
									<select id="destino.sucursal.id" name="destino.sucursal.id" style="width: 74%;" class="required"></select>
									<label for="destino.bodega.id" style="width: 25%;">Destino Bodega</label>
									<select id="destino.bodega.id" name="destino.bodega.id"  style="width: 74%;" class="required"></select>
								</fieldset>
							</td>
						</tr>
					</table>
				</form>
				<form id="guia" name="guia" class="formTab">
					<table class="table_plana" width="100%">
						<tr>
							<td width="50%">
								<fieldset class="">
									<label for="id">N° Guía despacho</label>
									<input id="numero" name="numero" type="text" maxlength="10" class="required number">
									
									<label for="transportista.id">Transportista</label>
									<select id="transportista.id" name="transportista.id" class="required"></select>
																		
								</fieldset>
							</td>
							<td width="50%">
								<fieldset class="">
									<label for="fechaEmision">Fecha de Emision</label>
									<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
									
									<label for="transportista.patente">Patente</label>
									<input id="transportista.patente" name="transportista.patente" type="text" maxlength="8" class="required">
																		
								</fieldset>
							</td>
						</tr>
						<tr>
			   				<td colspan="2">
			   					<fieldset class="fieldset_botonera_center">
			   						<input type="button" name="grabar" id="grabar" value="Grabar Guía" />
			   						<input type="button" name="imprimir" id="imprimir" value="Imprimir Guía" />
			   						<input type="button" name="reemitir" id="reemitir" value="Re emitir Guía" />
			   						<input type="button" name="confirmar" id="confirmar" value="Confirmar Emisión" />
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