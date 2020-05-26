<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<div id="onbodegaffdetallefinaldespachosPanel">
			<form id="detalleForm" name="filtro_buscador" class="formTab">
			  <table class="table_plana" width="100%">
					<tr>
						<td width="100%"> 
							<fieldset>
								<label for="factura" style="width: 50%">PETICION DE FACTURA</label>
								<a href="#popupDocumentos" id="factura" name="100008000">&nbsp;</a><span value="100008000">
							</fieldset>
							<fieldset>	
								<label for="notaCredito" style="width: 50%">PETICION DE NOTA DE CREDITO</label>
								<a href="#popupDocumentos" id="notaCredito" name="100008100">&nbsp;</a><input type="hidden" id="100008100">
							</fieldset>
							<fieldset>
								<label for="bodega" style="width: 50%">GUIAS A LA BODEGA 10000</label>
								<a href="#popupGuias" id="bodega" name="100010500">&nbsp;</a><input type="hidden" id="100010500">
							</fieldset>
								<fieldset>
								<label for="aptoVenta" style="width: 50%">OT'S RECEPCIONADAS EN AV </label>
								<a href="#popupOts" id="aptoVenta"name="100010700">&nbsp;</a><input type="hidden" id="100010700">
							</fieldset>
								<fieldset>
								<label for="dañado" style="width: 50%">OT'S RECEPCIONADAS EN DA</label>
								<a href="#popupOts" id="dañado"name="100010600">&nbsp;</a><input type="hidden" id="100010600">
							</fieldset>
							<fieldset>
								<label for="cerradas" style="width: 50%">OT'S CERRADAS </label>
								<a href="#popupOts" id="cerradas"name="100008200">&nbsp;</a><input type="hidden" id="100008200">
							</fieldset>
							<fieldset>	
								<label for="vigentes" style="width: 50%">OT'S SIN ACCIONES </label>
								<a href="#popupOts" id="vigentes" name="0">&nbsp;</a>
							</fieldset>
						</td>
					</tr>
				</table>  
			</form>
		</div>
		<div id="popupOts">
				<form id="formOts">
					<fieldset>
						<div id="ots"></div>					
					</fieldset>
				</form>
		</div><!-- Fin Popup -->
		<div id="popupDocumentos">
				<form id="formDocumentos">
					<fieldset>
						<div id="documentos"></div>				
					</fieldset>
				</form>
		</div><!-- Fin Popup -->
		<div id="popupGuias" title="Guias a la bodega 10000">
				<form id="formGuias">
					<fieldset>
						<div id="guiasPopupFlexi"></div>
					</fieldset>
				</form>
		</div><!-- Fin Popup -->
	</body>
</html>