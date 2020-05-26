<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<div id="onbodegaffdetalleproveedorPanel">
			<form id="filtro_buscador" name="filtro_buscador" class="formTab">
			  <table class="table_plana" width="100%">
					<tr>
						<td width="100%"> 
							<fieldset>
								<label for="guia.id" style="width:100px;">N° Guía</label>
								<select id="guia.id" name="guia.id" style="width:250px;"></select>
							</fieldset>
						</td>
					</tr>
				</table>  
				<div class="marco_interior_tabla">
					<div id="proveedor"></div>
				</div> 
			</form>
		</div>
		<div id="popupRecibir" title="Recepcion de productos" style="padding: 0 !important">
			<div class="container">
				<form id="autorizar">
					<div class="marco">
						<table class="table_plana" style="width: 100%">
							<tr>
								<td colspan="2">
									<label>las ots seleccionadas seran almacenadas en </label><br><br>
								</td>
							</tr>
							<tr>
								<td style="width: 50%; text-align:center">
									<input type="button" id="AV" name="AV" value="Apto para la venta(AV)">
								</td>
								<td style="width: 50%; text-align:center">
									<input type="button" id="DA" name="DA" value="Producto Dañado(DA)">
								</td>
							</tr>
							<tr>
								<td colspan="2">
										<br><label>las ots seleccionadas seran cambiadas por productos nuevos </label><br><br>
								</td>
							</tr>
							<tr>
								<td style="width: 50%; text-align:center">
									<input type="button" id="10000" name="10000" value="Enviar a bodega 10.000">
								</td>
							</tr>
						</table>
					</div><!-- Fin marco_interno -->
				</form>
			</div><!-- Fin container popup -->
		</div><!-- Fin Popup -->
		<!-- <div id="popupGuia" title="Emisión de la Guía" style="padding: 0 !important">
			<div class="container">
				<form id="guiaForm">
					<div class="marco">
						<table class="table_plana" style="width: 100%">
							<tr>
								<td >
									<input type="hidden" id="id" name="id">
									<label>N° de guía</label>
									<input type="text" name="numero" id="numero" class="number required">
								</td>
							</tr>
							<tr>
				 				<td width="100%">
				 					<fieldset class="fieldset_botonera_center">
				 						<input type="button" name="grabar" id="grabar" value="Grabar Guía" />
				 						<input type="button" name="imprimir" id="imprimir" value="Imprimir Guía" />
				 						<input type="button" name="reemitir" id="reemitir" value="Re emitir Guía" />
				 						<input type="button" name="confirmar" id="confirmar" value="Confirmar Emisión" />
				 					</fieldset>
				 				</td>
				 			</tr>
						</table>
					</div>Fin marco_interno
				</form>
			</div>Fin container popup
		</div>Fin Popup -->
	</body>
</html>