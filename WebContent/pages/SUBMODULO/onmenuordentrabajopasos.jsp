<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajopasosPanel">
		<form id="pasosOT" class="formTab">
			<table width="100%" class="table_plana">
				<tr>
					<td width="100%">
						<fieldset class="twoCol">
							<label for="facturarElegido" style="width:55%">Se escogío a quien facturar el producto</label>
							<label id="facturarElegido" style="width:40%">&nbsp;</label>
							
							<label for="productoRetirado" style="width:55%">El cliente retiro el producto cambiado</label>
							<label id="productoRetirado" style="width:40%">&nbsp;</label>
							
							<label for="cargoGenerado" id="cargoGeneradoTitulo" style="width:55%">&nbsp;</label>
							<label id="cargoGenerado" style="width:40%">&nbsp;</label>
							
							<label for="accesoriosRecuperados" style="width:55%">Se recuperaron los accesorios del cliente</label>
							<label id="accesoriosRecuperados" style="width:40%">&nbsp;</label>
							
							<label for="productoRecuperado" style="width:55%">Se recupero el producto dañado</label>
							<label id="productoRecuperado" style="width:40%">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
		<form id="sinPasosOT" class="formTab">
			<h3>La orden de trabajo debe estar autorizada de cambio para mostrar los pasos que se deben dar antes de facturar la orden de trabajo.</h3>
		</form>
	</div>
</body>