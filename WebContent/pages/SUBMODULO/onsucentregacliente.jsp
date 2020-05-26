<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onsucentregaclientePanel">
		<form id="cliente" name="cliente" class="formTab">
			<h2 style="color:red; font-size:15; margin: 0px" id="productoCambioAutorizadoFF">PRODUCTO AUTORIZADO DE CAMBIO</h2>
			<table class="table_plana" style="width: 100%">
				<tr>
					<td width="50%">
						<fieldset>
							<label for="nombreCompleto">cliente</label>
							<input id="nombreCompleto" class="required" type="text" name="nombreCompleto" maxlength="64">
							
	   						<label for="fechaEntrega" >Fecha Entrega</label>
							<input type="text" name="fechaEntrega" id="fechaEntrega" class="fechaHora dateTimeDDMMYYYYhhmm required" format="dd/MM/yyyy HH:mm" />
						</fieldset>
					</td>
					<td width="50%">
						<fieldset>
							<label for="rut" >rut</label>
	   						<input name="rut" id="rut" type="text" class="required run">
	   						<label for="telefono" float: left;">teléfono o celular</label>
	   						<input name="telefono" id="telefono" type="text" class="telefonoCelular required">
						</fieldset>
					</td>
				</tr>
				<tr>
	   				<td colspan="2">
		   				<fieldset>
	   						<label for="observacionEntrega" style="width: 90%">Observaciones de la entrega o del producto entregado</label>
	   						<textarea name="observacionEntrega" id="observacionEntrega" rows="3" style="width: 99%"></textarea>			   						
	   					</fieldset>
   					</td>				
				</tr>
				<tr id="panelAccesoriosCliente">
   					<td colspan="2">
						<div id=accesoriosOTCliente></div>
   					</td>
   				</tr>
	   			<tr>			   				
	   				<td colspan="2">
		   				<fieldset>
		   					<div align="center">
		   						<input type="button" name="clienteRechaza" id="clienteRechaza" value="Cliente rechaza producto" class="panelSinAutorizacionCambio"/>
		   						<input type="button" name="cambioFalla" id="cambioFalla" value="Autorizar cambio por falla" class="panelSinAutorizacionCambio"/>
		   						<input type="button" name="clienteAcepta" id="clienteAcepta" value="Cliente acepta producto" />
		   						<input type="button" id="btnTicketCambio" value="Generar ticket de cambio"/>
	   						</div>
	   					</fieldset>
   					</td>
	   			</tr>		   				
			</table>
		</form>					
	</div>
</body>