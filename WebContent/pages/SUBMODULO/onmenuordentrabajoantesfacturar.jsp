<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajoantesfacturarPanel" >
			<form id="antesEnviar" class="formTab">
				<table width="100%" class="table_plana">
					<tr>
						<td width="49%">
							<fieldset class="twoCol">
								<input type="checkbox" id="checkCargoGenerado">
								<span id="facturarId">&nbsp;</span>
							</fieldset>
						</td>
						<td width="49%">
							<fieldset class="twoCol">
								<label for="numeroCargo" id="numeroCargoId" style="width:30%;">N°</label>
								<input id="numeroCargo" type="text" style="width:69%;" class="number" maxlength="10"/>
							</fieldset>
						</td>
					</tr>
					<tr >
						<td width="49%">
							<fieldset>
								<label for="destinoProducto" style="width:99%;">Destino del producto cambiado</label>
							</fieldset>
						</td>
						<td width="49%">
							<fieldset class="twoCol">
								<select id="destinoProducto" style="width:99%;"></select>
							</fieldset>
						</td>
					</tr>
				</table>
				<div class="marco_interior_tabla">
					<div id=accesoriosAntesEnviar></div>
				</div>
				<table width="100%" class="table_plana">
					<tr>
						<td width="40%">
							<fieldset>
								<input type="button" value="Guardar" id="btnGuardarAntesEnviar" name="btnGuardarAntesEnviar" style="float:right; margin-right:10px; margin-top:10px;"/>
							</fieldset>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div><!-- Fin tabs -->
</body>