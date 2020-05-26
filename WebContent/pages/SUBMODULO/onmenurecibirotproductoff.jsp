<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenurecibirotproductoffPanel">
		<form id="producto" name="producto" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="100%" colspan="2">
						<label style="width:69%;" for="producto.id">CAPTURE EL CÓDIGO DE BARRA DE LOS ACCESORIOS</label>
						<input type="text" id="codigoBarrasAccesorios" name="codigoBarrasAccesorios" style="width:140px;" class="number" maxlength="10">
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%">
						<label style="width:30%;" for="producto.id">PRODUCTO</label>
						<label id="producto.id" style="width:13%;">&nbsp;</label>
						<label id="producto.descripcion" style="width:57%;">&nbsp;</label>
						<label style="width:30%;" for="producto.marca.nombre"> - MARCA</label> 
						<label id="producto.marca.nombre">&nbsp;</label>
						<input type="button" id="imprimirCodigoProducto" name="imprimirCodigoProducto" value="Imprimir Código de Barras" style="float: right; margin-right: 50px; margin-bottom: 0px;">
					</td>
				</tr>
				<tr>
					<td width="50%">
						<fieldset>
							<label style="width:40%;" for="numeroSerie">N° SERIE</label>
							<input type="text" style="width:50%;" id="numeroSerie" name="numeroSerie" maxlength="32">
						</fieldset>
					</td>
					<td width="50%">
						<fieldset>
							<label style="width:30%;" class="numeroCelular" for="numeroTelefono">N° CELULAR</label>
							<label class="numeroCelular" id="numeroTelefono"></label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="100%">
						<fieldset>
							<label style="width:30%; vertical-align: top;" for="descripcionEstado">DESCRIPCIÓN ESTADO</label>
							<label style="width:69%;" id="descripcionEstado">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
			
			<div class="marco_interior_tabla">
				<div id="accesoriosOT"></div>
			</div>
			
			<div class="marco_interior_tabla">
				<div id="partesOT"></div>
			</div>
			
			<table class="table_plana" width="100%">
				<tr>
					<td>
						<fieldset>
							<label style="width:20%;" for="descripcionEstado">Descripción de estado</label>
							<label style="width:79%;" id="descripcionEstado">&nbsp;</label>
							<label style="width:20%;" for="tipoFalla" class="tipoFallas">Tipos de fallas</label>
							<label style="width:79%;" id="tipoFalla" class="tipoFallas">&nbsp;</label>
							<label style="width:20%;" for="descripcionFalla">Descripción de falla</label>
							<label style="width:79%;" id="descripcionFalla">&nbsp;</label>
							<label style="width:20%; color: red;" for="transportista">Transportista</label>
							<label style="width:79%; color: red;" id="transportista">&nbsp;</label>
						</fieldset>
						<fieldset class="fieldset_botonera" style="text-align: right; margin-right: 50px;">
   							<input type="button" id="siguiente" name="siguiente" value="Siguiente" >
						</fieldset>	 
					</td>
				</tr>
			</table>
		</form>
	</div><!-- Fin tabProducto -->
</body>