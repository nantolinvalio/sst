<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<form id="onsalaproveedoresrevisarotPanel" class="formTab">
			<div class="marco_interior_tabla">
				<table class="table_plana" width="100%">
					<tr>
						<td width="50%">
							<fieldset>
								<label for="clasificacion">Clasificación</label>
								<select id="clasificacion" name="clasificacion" class="required">
								</select>
							</fieldset>
						</td>
						<td width="50%">
							<fieldset>
								<label for="nombreTecnico">Nombre del técnico</label>
								<input type="text" id="nombreTecnico" name="nombreTecnico" class="required">
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<fieldset>
								<label for="observacion">Observación</label>
								<textarea rows="5" cols="117" id="observacion" name="observacion" class="required"></textarea>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td>
							
						</td>
						<td>
							<fieldset>
								<input type="button" id="grabar" value="Grabar" style="float:right">
							</fieldset>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>