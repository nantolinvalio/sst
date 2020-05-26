<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
</head> 
<body>
	<div id="onmenufacturadetalle">
		<form id='factura' name="factura" class="formTab">
			<table class="table_plana">
				<tr>
					<td width="40%">
						<fieldset >
							<label for="id">N° Factura</label>
							<label id="id">&nbsp;</label>
							
							<label for="fechaEmision">Fecha emision</label>
							<label id="fechaEmision" format="dd/MM/yyyy">&nbsp;</label>
							
							<label for="comuna">Comuna</label>
							<label id="comuna">&nbsp;</label>
							
							<label for="giro">Giro</label>
							<label id="giro">&nbsp;</label>
						</fieldset>
					</td>
					<td width="50%">
						<fieldset >
							<label for="nombre"  style="width: 150px ">Facturado a</label>
							<label id="nombre" >&nbsp;</label>
							
							<label for="rutFacturar" style="width: 150px ">Rut</label>
							<label id="rutFacturar">&nbsp;</label>
							
							<label for="direccion" style="width: 150px ">Direccion</label>
							<label id="direccion" style="width: 250px ">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
			<div class="marco_interior_tabla">
					<div id="resultados"></div>
			</div>
			<table class="table_plana">
				<tr>
					<td width="20%">
						<fieldset></fieldset>
					</td>
					<td width="30%">
						<fieldset >
							<label for="montoNeto">Neto</label>
							<label id="montoNeto">&nbsp;</label>
							
							<label for="iva">I.V.A</label>
							<label id="iva">&nbsp;</label>
							
							<label for="montoTotal">Total</label>
							<label id="montoTotal">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>	
			<div>
		</form>
	</div>
</body>
</html>