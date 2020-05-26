<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onbodegaproductoccPanel" class="marco_interior_tabla">
		<form id="buscador" class="formTab">
			<h1>Excepciones por productos</h1>
		
			<label>Debe seleccionar las excepciones y presionar el botón " Grabar"</label>
				<table class="table_plana" width="100%">
					<tr>
						<td width="50%">
							<fieldset>
								<label for="id">Código Corto</label>
								<input type="text" id="id" name="id" class="number">
								
								<label for="marca">Marca</label>
								<select id="marca" name="marca"></select>
								
							</fieldset>
						</td>
						<td width="50%">
							<fieldset>
								<label for="descripcion">Descripción</label>
								<input type="text" id="descripcion" name="descripcion">
								
								<label for="idFamilia">Familia</label>
								<select id="idFamilia" name="idFamilia"></select>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td align="right" width="50%">
							<input type="button" id="limpiar" value="Limpiar">
						</td>
						<td align="left" width="50%">
							<input type="button" id="buscar" value="buscar">
						</td>
					</tr>
				</table>
		</form>
		<div id="productos"></div>
	</div>
</body>
</html>