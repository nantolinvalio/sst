<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onbuscarotautorizacioncambio.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>BUSCAR ORDEN DE TRABAJO CON AUTORIZACION DE CAMBIO</h1>
				<div class="marco">
					<h1>BUSCAR ORDEN DE TRABAJO CON AUTORIZACION DE CAMBIO</h1>
					<div class="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" style="padding-bottom: 0px">
				   				<tr>
				   					<td width="35%">
				   						<fieldset>
				   							<label for="idOT">N OT</label>
				   							<input type="text" name="idOT" id="idOT" class="number" maxlength="9">
				   							<label for="tipoCambio">Tipo cambio</label>
				   							<select name="tipoCambio" id="tipoCambio"></select>
				   							<label for="tipoDocumento">Tipo documento</label>
				   							<select name="tipoDocumento" id="tipoDocumento">
					   							<option value="">[SELECCIONE]</option>
				   								<option value="boleta">BOLETA</option>
				   								<option value="factura">FACTURA</option>
				   							</select>
				   							<label for="numeroDocumento">Nº documento</label>
				   							<input type="text" name="numeroDocumento" id="numeroDocumento" class="number" maxlength="9"">
											<label for="numeroSerie">N serie</label>
				   							<input type="text" name="numeroSerie" id="numeroSerie">
				   							<label for="numeroFolio">N Folio</label>
				   							<input type="text" name="numeroFolio" id="numeroFolio" maxlength="16">
					   					</fieldset>
				   					</td>
				   					<td  width="35%">
				   						<fieldset>
					   						<label for="rutCliente">Run cliente</label>
				   							<input type="text" name="rutCliente" id="rutCliente" class="run" />
					   						<label for="idLinea">Linea</label>
				   							<select name="idLinea" id="idLinea"></select>
				   							<label for="idFamilia">Familia</label>
				   							<select name="idFamilia" id="idFamilia"></select>
				   							<label for="idProducto">Producto</label>
				   							<input type="text" name="idProducto" id="idProducto" class="number">
				   							<!-- <select name="idProducto" id="idProducto"></select> -->
				   							<label for="codigoBarra">Cód. Barra / LPN</label>
				   							<input type="text" id="codigoBarra" name ="codigoBarra" maxlength="18" class="alfanumerico">
			   							</fieldset>
				   					</td>
				   					<td  width="30%">
				   						<fieldset>
				   							<label for="idEstado">Estado</label>
				   							<select name="idEstado" id="idEstado"></select>
				   							<label for="clasificacion">Clasificación</label>
											<select id="clasificacion" name="clasificacion"></select>
				   							<label for="idZona">Zona</label>
				   							<select name="idZona" id="idZona"></select>
				   							<label for="idSucursal">Sucursal</label>
				   							<select name="idSucursal" id="idSucursal"></select>
				   							<label for="idMarca">Marca</label>
				   							<select name="idMarca" id="idMarca"></select>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
							<table class="table_plana" style="padding-top: 0px">
								<tr>
									<td width="40%">
										<fieldset>
				   							<label for="fechaCreacion"  style="width: 200px !important">Fecha creacion inicio</label>
				   							<input type="text" name="fechaCreacion" id="fechaCreacion" class="fecha dateDDMMYYYY">
				   							<label for=""  style="width: 200px !important">Fecha autorizacion inicio</label>
				   							<input type="text" name="fechaAutorizacionOtInicio" id="fechaAutorizacionOtInicio" class="fecha dateDDMMYYYY">
				   							<label for="fechaRecepcionOtInicio"  style="width: 200px !important">Fecha recepcion inicio</label>
				   							<input type="text" name="fechaRecepcionOtInicio" id="fechaRecepcionOtInicio" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="40%">
										<fieldset>
				   							<label for="fechaCreacionFin" style="width: 200px !important">Fecha creacion fin</label>
				   							<input type="text" name="fechaCreacionFin" id="fechaCreacionFin" class="fecha dateDDMMYYYY">
				   							<label for="fechaAutorizacionOtFin"  style="width: 200px !important">Fecha autorizacion fin</label>
				   			   				<input type="text" name="fechaAutorizacionOtFin" id="fechaAutorizacionOtFin" class="fecha dateDDMMYYYY">
				   							<label for="fechaRecepcionOtFin"  style="width: 200px !important">Fecha recepcion fin</label>
				   							<input type="text" name="fechaRecepcionOtFin" id="fechaRecepcionOtFin" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="20%"></td>
								</tr>
								<tr>
									<td colspan="3">
				   						<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
				   						</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Resultados(Doble click para ver resultado de la OT)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				</table>
				<table class="table_datos">
					<tr>
						<td align="center"><label style="color: blue;">OTs
								AUTORIZADAS DE CAMBIO</label> <label style="color: red;">OTs CON
								TAREA URGENTE</label> <label style="color: gray;">OTs DESACTIVADAS</label>
							<label style="color: brown;">OTs CERRADAS POR CLIENTE</label> <label
							style="color: darkgreen;">OTs CERRADAS POR USUARIO</label></td>
					</tr>
				</table>
	 		</div>
 		</div>
	</body>
</html>