<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
</head>
<body>
	<div id="onmenuordentrabajocambioPanel" >
		<form id="cambio" name="cambio" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td>
						<fieldset>
							<label style="width:20%;" for="idFacturar">Próxima Acción</label>
							<select style="width:79%;" id="idFacturar" name="idFacturar" class="required"></select>
							
							<label style="width:20%;" for="motiviCambio">Motivo del Cambio</label>
							<textarea id="motivoCambio" name="motivoCambio" rows="5" class="required" style="width: 79%"></textarea>
						</fieldset>
					</td>
				</tr>
				
				<tr>
					<td>
						<fieldset>
							<!-- input type="checkbox" id="sinProductoFisico" name="sinProductoFisico" onChange="comprobar(this);" style="float: left; margin: 5px;width: 3%">
					        <label for="sinProductoFisico" style="text-align:left; float: right; width: 95%">Sin Producto Fisico</label -->
					        <input type="checkbox" id="sinProductoFisico" name="sinProductoFisico"  onChange="comprobar(this);" > Sin Producto Fisico<BR>
					                
							<input type="button" value="Autorizar Cambio a Bodega" id="autorizarCambioBodega" name="autorizarCambioBodega" style="float:right; margin-right:10px;">
							<input type="button" value="Autorizar Cambio a Proveedor" id="autorizarCambioProveedor" name="autorizarCambioProveedor" style="float:right; margin-right:10px;">
							<input type="button" value="Autorizar Cambio a Transporte" id="autorizarCambioTransporte" name="autorizarCambioTransporte" style="float:right; margin-right:10px;" disabled>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div><!-- Fin tabCambio -->
</body>