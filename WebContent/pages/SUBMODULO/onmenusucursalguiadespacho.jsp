<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenusucursalguiadespachoPanel">
		<form id="guia" name="guia" class="formTab">
			<h2 style="color:red; font-size:15; margin: 0px" id="guiaAccesorio">Guía de Accesorios</h2>
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%">
						<fieldset class="">
							<label for="id">N° Guía despacho</label>
							<input id="numero" name="numero" type="text" maxlength="10" class="required number">
							
							<label for="fechaEmision">Fecha de Emision</label>
							<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
							
							<label for="transportista.id">Transportista</label>
							<select id="transportista.id" name="transportista.id" class="required"></select>
																
						</fieldset>
					</td>
					<td width="50%">
						<fieldset class="">
							<label for="destino.id">Destino</label>
							<select id="destino.id" name="destino.id" class="required"></select>
							
							<label for="transportista.patente">Patente</label>
							<input id="transportista.patente" name="transportista.patente" type="text" maxlength="8" class="required">
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
		<form id="guiaCliente" name="guiaCliente" class="formTab" >
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%">
						<fieldset class="">
							<label for="id">N° Guía despacho</label>
							<input id="numero" name="numero" type="text" maxlength="10" class="required number">
							
							<label for="fechaEmision">Fecha de Emision</label>
							<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
							
						</fieldset>
					</td>
					<td width="50%">
						<fieldset class="">
							<label for="cliente">Destino</label>
							<label id="cliente" style="width: 59%">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
		<table class="table_plana" width="100%">
   			<tr>
   				<td width="100%">
   					<fieldset class="fieldset_botonera_center">
   						<input type="button" name="grabar" id="grabar" value="Grabar Guía" />
   						<input type="button" name="imprimir" id="imprimir" value="Imprimir Guía" />
   						<input type="button" name="reemitir" id="reemitir" value="Re emitir Guía" />
   						<input type="button" name="confirmar" id="confirmar" value="Confirmar Emisión" />
   					</fieldset>
   				</td>
   			</tr>
		</table>
	</div>
</body>