<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenurecibirothistorialPanel"  name="tabHistorial">
		<form id="historial" name="historial" class="formTab" style="display: none">
			<table class="table_plana" width="100%">
				<tr>
					<td width="30%"	>
						<fieldset>
							<label for="fechaRecepcion" class="tituloCampo" style="width: 41% !important" >Fecha Recepcion</label>
							<label id="fechaRecepcion" class="glosaCampo" format="hh:mm dd/MM/yyyy">&nbsp;</label>
						</fieldset>
					</td>
					<td width="35%"	>
						<fieldset>
							<label for="guia.numero" class="tituloCampo" style="width: 25% !important">N° Guía</label>
							<label id="guia.numero" class="glosaCampo" style="width:70% !important">&nbsp;</label>
						</fieldset>
					</td>
					<td width="35%">									
						<fieldset>
							<label for="guia.origen.id" class="tituloCampo">Origen</label>
							<label id="guia.origen.id" style="width:50px">&nbsp;</label>
							<label id="guia.origen.nombre">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td >
						<fieldset>
							<label for="usuario.nombreCompleto" class="tituloCampo" style="width: 41% !important">Usurario Receptor</label>
							<label id="usuario.nombreCompleto" class="glosaCampo">&nbsp;</label>
						</fieldset>
					</td>
					<td >
						<fieldset>	
							<label for="usuario.email" class="tituloCampo" style="width: 25% !important">email</label>
							<label id="usuario.email" style="width:50%" class="glosaCampo" style="width:70% !important">&nbsp;</label>
						</fieldset>
					</td>
					<td >
						<fieldset>	
							<label for="usuario.anexo" class="tituloCampo">Anexo</label>
							<label id="usuario.anexo"class="glosaCampo">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="3">									
						<fieldset>
							<label id="ubicacion.tipo" for="ubicacion.nombre" class="tituloCampo" style="width: 30% !important">&nbsp;</label>
							<label id="ubicacion.nombre" class="glosaCampo" style="width: 69% !important">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<fieldset>
							<label for="observacion" class="tituloCampo" style="width: 19% !important">Observación</label>
							<label id="observacion" style="width:79%">&nbsp;</label>
						</fieldset>
					</td>
					<td >
						<fieldset>	
							<label id="estado.descripcion" style="width:100%; font-weight: bold;">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div><!-- Fin tabHistorial -->
</body>