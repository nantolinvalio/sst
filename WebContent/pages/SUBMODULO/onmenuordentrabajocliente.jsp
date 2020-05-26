<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajoclientePanel"  >
		<form id="cliente" name="cliente" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%">
						<fieldset >
							<label for="rut">Rut</label>
							<label id="rut">&nbsp;</label>
							
							<label for="nombreCompleto">Nombre</label>
							<label id="nombreCompleto">&nbsp;</label>
							
							<label for="regionCliente">Region</label>
							<select id="regionCliente" name="regionCliente" class="required"></select>
						</fieldset>
						<fieldset>
							<label for="comunaCliente">Comuna</label>
							<select id="comunaCliente" name="comunaCliente" class="required"></select>
						</fieldset>
						<fieldset>
							<label for="poblacion">Población</label>
							<input type="text" id="poblacion" name="poblacion" maxlength="64"></input>
						</fieldset>
					</td>
					<td width="50%">
						<fieldset >
							<label for="celular">Celular</label>
							<input type="text" id="celular" name="celular"  class="celular"/>
						</fieldset>
						<fieldset>
							<label for="telefono">Teléfono</label>
							<input type="text" id="telefono" name="telefono" class="telefono"/>
						</fieldset>
						<fieldset>
							<label for="email">Correo Electrónico</label>
							<input type="text" id="email" name="email" class="email" maxlength="64"/>
						</fieldset>
						<fieldset>
							<label for="calle">Calle</label> 
							<input type="text" id="calle" name="calle" class="required" maxlength="64" />
						</fieldset>
						<fieldset>
							<label for="numeroCasa">N° Casa/Dpto</label>
							<input type="text" id="numeroCasa" name="numeroCasa" class="required" maxlength="16" />
						</fieldset>
						<fieldset>
							<input type="button" value="Guardar" id="guardarCliente" name="guardarCliente" style="float:right; margin-right:10px;">
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	
		
	
		
		
	</div><!-- Fin tabCliente -->
</body>