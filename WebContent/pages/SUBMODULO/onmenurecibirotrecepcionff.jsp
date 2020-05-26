<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenurecibirotrecepcionffPanel">
		<form id="recepcion" name="recepcion" class="formTab" >
			<table class="table_plana">
	   			<tr>
	   				<td width="50%">
	   					<fieldset>
	   						<input type="hidden" id="guia.id" name="guia.id">
	   						<label for="guia.numero" style="width: 44%">N° de guia de despacho</label>
	   						<input type="text" name="guia.numero" id="guia.numero" style="width: 45%" class="required number" maxlength="16"/>
	   						
	   						<label for="fechaRecepcion" style="width: 44%">Fecha recepción</label>
	   						<input type="text" name="fechaRecepcion" id="fechaRecepcion" class="fechaHora dateTimeDDMMYYYYhhmm required" format="dd/MM/yyyy HH:mm" readOnly/>
	   						
	   						<label style="width: 10px;"></label>
	   					</fieldset>
					</td>
					<td width="50%">		   				
	   					<fieldset>
	   						<label for="ubicacionOT" style="width: 40%">ORIGEN</label>
	   						<label id="ubicacionOT" style="width: 59%">&nbsp;</label>
	   							
	   						<label for="direccion" style="width: 40%">DIRECCIÓN</label>	
	   						<label id="direccion" style="width: 59%">&nbsp;</label>
	   					</fieldset>
	   				</td>
	   			</tr>
   			</table>
		</form>	
		<form id="recepcionObs" name="recepcionObs" class="formTab" >
   			<table class="table_plana" width="100%">
   				<tr>
	   				<td>		   				
	   					<fieldset>
							<label style="width:22%; vertical-align: top;" for="condiciones">CONDICIONES DE RECEPCIÓN</label>
							<textarea rows="2" style="width: 77%"  id="condiciones" name="condiciones"  ></textarea>
	   					</fieldset>	
	   					<fieldset>
	   						<label for="clasificacion" style="width:22%">Clasificación</label>
	   						<select style="width:30%;" id="clasificacion" name="clasificacion"></select>
	   					</fieldset>  
	   					<br> 				
	   					<fieldset class="fieldset_botonera_center">
	   						<label id="avisoFecha" style="color: red; width: 100%">LA FECHA DE ENVÍO ES PORTERIOR A LA FECHA DE RECEPCIÓN</label>
							<input type="button" value="Devolver a transporte" id="rechazarRecepcion" name="rechazarRecepcion">
							<input type="button" value="Aceptar recepcion con observación" id="aceptarConObservaciones" name="aceptarConObservaciones">
							<input type="button" value="Aceptar recepción" id="aceptarRecepcion" name="aceptarRecepcion">
	   					</fieldset>
	   				</td>
	   			</tr>
			</table>
		</form>					
	</div><!-- Fin tabGestiones -->
</body>