<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
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
</body>