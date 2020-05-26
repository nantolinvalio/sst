<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="onmenuotstecnicocompletoPanel">
		<div id="enviadoSTCompleto">
			<form id="sTecnicoCompleto" name="sTecnicoCompleto" class="formTab">
				<table class="table_plana" width="100%">
					<tr>
						<td width="50%">
							<fieldset>
								<label for="glosa" style="width:40%">S. Técnico</label>
								<label id="glosa" style="width:50%">&nbsp;</label>
								
								<label for="direccion" style="width:40%">Dirección</label>
								<label id="direccion" style="width:50%">&nbsp;</label>
								
								<label for="region.glosa" style="width:40%">Región</label>
								<label id="region.glosa" style="width:50%">&nbsp;</label>
									
								<label for="comuna.glosa" style="width:40%">Comuna</label>
								<label id="comuna.glosa" style="width:50%">&nbsp;</label>
								
								<label for="telefono" style="width:40%">Teléfono</label>
								<label id="telefono" style="width:50%">&nbsp;</label>
							</fieldset>
						</td>
						<td width="50%">
							<fieldset>
								<input type="checkbox" id="ot.calificaFR"/>Producto no califica para Falla de Reiterada
								<br>
								<input type="checkbox" id="ot.excluyeCCalidad" />Producto excluido de Control de calidad
								<br>
								<input type="checkbox" id="ot.contratoEmitido"/>Servicio técnico no emitío contrato 
								<fieldset>
									<label for="ot.numeroContrato">N° de Contrato</label>
									<input type="text" id="ot.numeroContrato" name="ot.numeroContrato" style="width: 250px" maxlength="25" class="required" />
								</fieldset>
								<fieldset>
									<label for="ot.diagnostico">Diagnóstico</label>
									<textarea cols="64" id="ot.diagnostico" name="ot.diagnostico"></textarea>
								</fieldset>	
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<fieldset>
								<label for="guia.numero">N° de guía de despacho a servicio técnico</label>
								<label id="guia.numero">&nbsp;</label>
								<input type="button" value="Guardar" id="guardarSTecnico" name="guardarSTecnico" style="float:right; margin-right:10px;">
							</fieldset>
							
						</td>
					</tr>
				</table>
			</form>
		  </div><!-- Fin enviado -->
		  <div id="noEnviadoSTCompleto" >
	  	<form id="asignarSTecnicoFormCompleto" name="asignarSTecnicoFormCompleto" class="formTab">
	  		<table class="table_plana" width="100%">
	  			<tr>
	  				<td width="100%">
	  					<span>Seleccione el servicio técnico que asignara a la orden de trabajo</span>
	  					<fieldset>
							<label for="regionSTecnico">Región</label>
							<select id="regionSTecnico" name="regionSTecnico" class="required"></select>
						</fieldset>
						<fieldset>
							<label for="comunaSTecnico">Comuna</label> 
							<select id="comunaSTecnico" name="comunaSTecnico" class="required"></select>
						</fieldset>
						<fieldset>
							<label for="serviciosTecnico">Servicio Técnico</label>
							<select id="serviciosTecnico" name="serviciosTecnico" class="required"></select>
						</fieldset>
						<fieldset>
							<input type="button" value="Asignar S. Técnico" id="asignarSTecnico" name="asignarSTecnico" style="float:right; margin-right:10px;">
						</fieldset>
					</td>
				</tr>
			</table>
	  	</form>
	  </div><!--Fin noEnviado -->
	</div>
</body>