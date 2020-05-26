<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onbodegaenviarsinguia.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Enviar a servicio técnico sin guia de despacho</h1>
				<div class="marco">
					<h1>Buscar ordenes de trabajo</h1>
					<div class="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="32%">
										<fieldset>
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
											
											<label for="idMarca">Marca</label>
											<select id="idMarca" name="idMarca"></select>
											
											<label for="idSucursal">Sucursal</label>
											<select id="idSucursal" name="idSucursal"></select>
											
										</fieldset>
									</td>
									<td width="31%">
										<fieldset>
											<label for="tipoOT">Tipo OT</label>
											<select id="tipoOT" name="tipoOT">
												<option value="">[Seleccione]</option>
												<option value="GP">Garantía proveedor</option>
												<option value="GM">Garantía Master</option>
											</select>
											
											<label for="idOT">N° OT</label>
											<input type="text" id="idOT" name="idOT" maxlength="16">
										</fieldset>
										<fieldset style="text-align: center">
											<input type="button" id="limpiar" value="Limpiar">
											<input type="button" id="buscar" value="Buscar">
										</fieldset>
									</td>
									<td width="34%">
										<fieldset>
											<label for="fechaRecepcionOtInicio" style="width: 180px">Fecha recepción inicio</label>
											<input type="text" id="fechaRecepcionOtInicio" name="fechaRecepcionOtInicio" class="fecha dateDDMMYYYY"/>
											
											<label for="fechaRecepcionOtFin" style="width: 180px">Fecha recepción fin</label>
											<input type="text" id="fechaRecepcionOtFin" name="fechaRecepcionOtFin" class="fecha dateDDMMYYYY"/>
											
											<label for="idProducto">Código corto</label>
											<input type="text" id="idProducto" name="idProducto" class="number" style="width:101px; margin-left:19%" maxlength="16">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" id="contendorGrilla">
					<h1>Resultados</h1>
					<div class="marco_interior_tabla">
						<form id="formResultados"><div id="resultados"></div></form>
					</div>
				</div>
				<div class="marco" id="contendorEnvio">
					<h1>Enviar ordenes de trabajo</h1>
					<div class="marco_interior">
						<form id="envioOT" name="envioOT" class="formTab">
							<table class="table_plana" width="100%">
								<tr>
									<td>
										<fieldset>
											<label for="idSTecnico" style="width: 20%">S. Técnico</label>
											<select id="idSTecnico" name="idSTecnico" style="width: 79%" class="required"></select>
											
											<label for="fechaEmision" style="width: 20%">Fecha de envío</label>
											<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
											<input type="button" id="enviar" value="Enviar sin guía de despacho" style="float: right;">
										</fieldset>
									</td>
								</tr>
							</table>
							<table id="otroST" name="otroST" class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="nombre">Nombre S. Técnico</label>
											<input type="text" id="nombre" name="nombre">
											
											<label for="direccion">Dirección</label>
											<input type="text" id="direccion" name="direccion">
											
											<label for="region.id">Región</label>
											<select id="region.id" name="region.id"></select>
											
											<label for="giro">Giro</label>
											<input type="text" id="giro" name="giro">
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="rut">RUT</label>
											<input type="text" id="rut" name="rut" class="run">
											
											<label for="telefono">Teléfono</label>
											<input type="text" id="telefono" name="telefono" class="telefono">
											
											<label for="comuna.id">Comuna</label>
											<select id="comuna.id" name="comuna.id"></select>
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>