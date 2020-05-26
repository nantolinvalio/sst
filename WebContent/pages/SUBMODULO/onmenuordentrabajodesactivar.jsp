<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajodesactivarPanel">
		<form id="desactivarOT" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="100%">
						<fieldset>
							<label for="motivoDesactivacion" style="width:15%">Motivo de la desactivación</label>
							<textarea id="motivoDesactivacion"rows="10" style="width:84%" class="required"></textarea>
						</fieldset>
						<fieldset>
							<input type="button" value="Desactivar Orden de Trabajo" id="btnDesactivarOT" name="btnDesactivarOT" style="float:right; margin-right:10px; margin-top:10px;"/>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>