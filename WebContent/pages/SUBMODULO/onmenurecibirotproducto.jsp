<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenurecibirotproductoPanel">
		<form id="producto" name="producto" class="formTab">
			<h2 style="color:red; font-size:15; margin: 0px" id="guiaAccesorio">Guía de Accesorios</h2>
			<table class="table_plana" width="100%">
				<tr>
					<td width="100%" colspan="2">
						<label style="width:69%;" for="producto.id">CAPTURE EL CÓDIGO DE BARRA DE LOS ACCESORIOS</label>
						<input type="text" id="codigoBarrasAccesorios" name="codigoBarrasAccesorios" style="width:140px;" class="number" maxlength="10">
					</td>
				</tr>
				<tr>
					<td width="60%">
						<fieldset>
							<label style="width:37%;" for="producto.id">PRODUCTO</label>
							<label id="producto.id" style="width:13%;">&nbsp;</label>
							<label id="producto.descripcion" style="width:49%;">&nbsp;</label>
						
							<label style="width:37%;" for="numeroSerie">N° SERIE</label>
							<input type="text" style="width:50%;" id="numeroSerie" name="numeroSerie" maxlength="32" readonly="readonly">
						</fieldset>
					</td>
					<td width="40%">
						<fieldset>
							<label style="width:35%;" for="producto.marca.nombre">MARCA</label>
							<label id="producto.marca.nombre" style="width: 64%">&nbsp;</label>
						
							<label style="width:35%;" class="numeroCelular" for="numeroTelefono">N° CELULAR</label>
							<label class="numeroCelular" id="numeroCelular" style="width: 64%">&nbsp;</label>
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
							<label style="width:22%;" for="descripcionEstado">Descripción de estado</label>
							<label style="width:77%;" id="descripcionEstado">&nbsp;</label>
							<label style="width:22%;" for="tipoFalla" class="tipoFallas">Tipos de fallas</label>
							<label style="width:77%;" id="tipoFalla" class="tipoFallas">&nbsp;</label>
							<label style="width:22%;" for="descripcionFalla">Descripción de falla</label>
							<label style="width:77%;" id="descripcionFalla">&nbsp;</label>
						</fieldset>
						 	
					</td>
				</tr>
			</table>
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%" align="left">
						<fieldset class="fieldset_botonera" style="text-align: right">
<!-- 							<label id="tranportista" style="color:red">tranportista</label><br> -->
						</fieldset>
					</td>
					<td width="50%" align="right">
						<fieldset class="fieldset_botonera" style="text-align: right">
   							<input type="button" id="siguiente" name="siguiente" value="Siguiente">
						</fieldset>	
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="2" align="left"> -->
<!-- 						<input type="button" id="terminarRecepcion" name="terminarRecepcion" value="Terminar Recepción"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
			</table>
		</form>
	</div><!-- Fin tabProducto -->
</body>