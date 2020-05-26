<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onbodegaeditarcontrolcalidadPanel">
		<form id="controlCalidadForm" name="controlCalidadForm" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td colspan="2">
						<fieldset>
							<input type="radio" name="controlCalidad" value="S" id="pasaCC"/> Producto pasa control de calidad
							<input type="radio" name="controlCalidad" value="N" id="noPasaCC" style="margin-left:10%"/> Producto <strong>NO</strong> pasa control de calidad
						</fieldset>
						<fieldset>
							<label for="observaciones" style="width: 15%">Observaciones</label>
							<textarea id="observaciones" name="observaciones" style="width: 62%" rows="5" maxlength="256"></textarea>
							
							<br><label id="usuario">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<input type="button" id="excluir" name="excluir" value="Excluir de control de calidad">
						</fieldset>
					</td>
					<td>
						<fieldset>
							<input type="button" id="salvar" name="salvar" value="Grabar" style="float:right">
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
		<form id="sinRecepciones" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="100%">
						<fieldset>
							<label>La orden de trabajo no presenta recepciones registradas en el sistema.</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>