<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenurecibirotproductospPanel">
		<form id="producto" name="producto" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="100%">
						<fieldset>
							<label style="width:220px;" for="datosProdcuto">Producto</label>
							<label style="width:600px;" id="datosProdcuto"><span id="producto.id">&nbsp;</span> <span id="producto.descripcion">&nbsp;</span>&nbsp;,Marca:&nbsp;<span id="producto.marca.nombre">&nbsp;</span></label>
							
							<label id="documento"><span id="tipoDocumento"></span>&nbsp;<span id="idDocumento"></span></label>
						</fieldset>
						<fieldset>
							<label style="width:220px;" for="fechaCambio">FECHA CAMBIO</label>
							<label id="fechaCambio" format="dd/MM/yyyy" style="width:400px;"></label
						</fieldset>
						<fieldset>							
							<label style="width:220px;" for="codigoBarra">Codigo Barras</label>
							<label style="width:400px;" id="codigoBarra">&nbsp;</label>					
							<input type="button" id="imprimirCodigoProducto" name="imprimirCodigoProducto" value="Imprimir Código " style="width: 200px">
						
							<label style="width:220px;" for="numeroSerie">N° Serie</label>
							<label style="width:600px;" id="numeroSerie">&nbsp;</label>
							
							<label style="width:220px;" class="numeroCelular" for="numeroCelular">N° Celular</label>
							<label style="width:600px;" class="numeroCelular" id="numeroCelular">&nbsp;</label>
							
							<label style="width:220px;" for="tipoFalla" class="tipoFallas">Tipos de fallas</label>
							<label style="width:600px;" id="tipoFalla" class="tipoFallas">&nbsp;</label>
								
							<label style="width:220px;" for="descripcionFalla">Descripción de falla</label>
							<label style="width:600px;" id="descripcionFalla">&nbsp;</label>
								
							<label style="width:220px;" for="descripcionEstado">Descripción de estado</label>
							<label style="width:600px;" id="descripcionEstado">&nbsp;</label>
							<label style="width:300px;" for="txtGuia" class="txtGuia">Guía de despacho de Accesorios</label>
							<label style="width:100px;" id="txtGuia" class="txtGuia">&nbsp;</label>
							<input type="button" value="Ver Guia Accesorios" class="txtGuia" id="verGuiaAccesorios">
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
		</form>
	</div><!-- Fin tabProducto -->
	
	<div id="popupGuiaAccesorio" title="Detalle de la Guía de Accesorios" style="padding: 0 !important">
		<div class="container">
			<div class="marco">
				<form id="detalleGuia">
					<table class="table_plana">
						<tr>
							<td width="100%">
								<fieldset>
									<label for="numero" style="width: 50%">Guía de despacho de Accesorios</label>
									<label id="numero" name="numero" style="width: 50%">&nbsp;</label>	
									
									<label for="origenGuia" style="width: 50%">Origen</label>
									<label id="origenGuia" style="width: 50%"><span id="origen.id">&nbsp;</span>  <span id="origen.nombre">&nbsp;</span>  <span id="origen.direccion"></span></label>						
								
									<label for="recepcion" style="width: 50%">Fecha Recepción</label>
									<label id="recepcion" style="width: 50%"><span id="fechaRecepcion" format="dd/MM/yyyy" class="fecha dateDDMMYYYY">&nbsp;</span></label>
									
									<label for="observaciones" style="width: 50%">Observaciones</label>
									<label id="observaciones" style="width: 50%"><span id="observacion">&nbsp;</span></label>
									
									<div id="accesoriosGuia"></div>
								</fieldset>
							</td>
						</tr>
					</table>
				</form>
			</div> <!-- Fin marco -->
		</div><!-- Fin container popup -->
	</div><!-- Fin Popup -->
</body>