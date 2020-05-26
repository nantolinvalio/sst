<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onagregarubicinternasucursalPanel">
		
		<form id="formSucursales" class="formTab">
			<h1>Buscar ubicaciones internas</h1>
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%">
						<fieldset>
							<label for="nombre">Nombre</label>
							<select id="nombre" name="nombre"></select>
								
							<label for="idLinea">Linea</label>
							<select id="idLinea" name="idLinea"></select>
							
							<label for="idFamilia">Familia</label>
							<select id="idFamilia" name="idFamilia"></select>									
						</fieldset>
					</td>
					<td width="50%">
						<fieldset>
							<label for="idSucursal">Sucursal</label>
							<select id="idSucursal" name="idSucursal"></select>
							
							<label for="codigo">Código</label>
							<input type="text" id="codigo" name="codigo" class="">
						</fieldset>
					</td>
				</tr>
				<tr>
					<td align="right">
						<fieldset>
							<input type="button" id="limpiar" value="Limpiar">
						</fieldset>
					</td>
					<td align="left">
						<fieldset>
							<input type="button" id="buscar" value="Buscar">
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
		<form id="formResultados" class="formTab">
			<div id="resultados"></div>
		</form>
	</div>
</body>
</html>