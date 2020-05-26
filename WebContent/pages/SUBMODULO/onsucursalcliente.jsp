<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onsucursalclientePanel">
		<form id="cliente" name="cliente" class="formTab">
			<table class="table_plana">
				<tr>
					<td width="50%">
						<fieldset>
							<label for="rut">Rut cliente</label>
							<input id="rut" name="rut" type="text"  style="width:130px" class="required run"/>
							<input type="button" value="Buscar" name="rut" id="buscarCliente" />
						
							<label for="nombreCompleto">Nombre Y Apellido</label>
							<input type="text" id="nombreCompleto" name="nombreCompleto" class="required" maxlength="64"/>
						
							<label for="celular">Celular</label>
							<input id="celular" type="text" name="celular" class="celular"/>
						
							<label for="telefono">Teléfono</label>
							<input id="telefono" type="text" name="telefono" class="telefono"/>
							
							<label for="numeroFolio">N° Folio</label>
							<input id=numeroFolio type="text" name="numeroFolio" class="number"/>
							
							<label for="ticket" class="ticket" id="ticketLabel">Ticket de cambio o Nota de crédito</label>
							<label id="ticket" class="ticket">&nbsp;</label>
						</fieldset>
					</td>
					<td>
						<fieldset>
							<label for="comuna.provincia.region.id">Región</label>
							<select id="comuna.provincia.region.id" name="comuna.provincia.region.id" class="required"></select>
							
							<label for="comuna.id">Comuna</label>
							<select id="comuna.id" name="comuna.id" class="required"></select>
						
							<label for="email">E-Mail</label>
							<input id="email" type="text" name="email" class="email" maxlength="64"/>
							
							<label for="calle">Calle/Dirección</label>
							<input id="calle" type="text" name="calle" class="required" maxlength="64"/>
						
							<label for="numeroCasa">N°</label>
							<input id="numeroCasa" type="text" name="numeroCasa" class="required" maxlength="16"/>
						
							<label for="poblacion">Población</label>
							<input id="poblacion" type="text" name="poblacion" maxlength="64" />
						</fieldset>
					</td>
				</tr>
			</table>
			
			<table id="panelOTGeneral" class="table_plana" width="100%">
				<tr>
					<td>
						<fieldset>
							<input type="button" value="Volver" id="volver"><br><br>
							<input type="button" value="Salir" id="salir" disabled>
						</fieldset>
					</td>
					<td>
						<fieldset style="text-align:right;">
							<input type="button" value="Guardar Orden de Trabajo" id="guardarCliente"><br><br>
							<input type="button" value="Imprimir" id="imprimir" name="imprimir">
						</fieldset>
					</td>
				</tr>
			</table>
			<table class="table_plana panelOTCA">	
				<tr>
					<td style="width: 500px;"></td>
					<td>
						<fieldset>
							<input type="button" id="btnTicketCambio" value="Generar ticket de cambio" style="float:left;"/>
						</fieldset>
					</td>
					<td style="width: 500px;"></td>
					<td>
						<fieldset>
							<input type="button" id="btnReporteCambio" value="Imprimir reporte de cambio" style="float:center;"/>								
						</fieldset>
					</td> 
					<td style="width: 500px;"></td>
				</tr>
				<tr>
					<td colspan="5">
						<fieldset>
							<input type="button" value="Volver" id="volver" style="float: left;">
							<input type="button" value="Salir" id="salir" style="float: left;">
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div><!-- fin cliente -->
</body>