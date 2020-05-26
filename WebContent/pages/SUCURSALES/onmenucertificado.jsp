<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onmenucertificado.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>BUSCAR E INSERTAR CERTIFICADOS DE ORDENES DE TRABAJO CON AUTORIZACION DE CAMBIO</h1>
				<div class="marco">
					<h1>BUSQUEDA E INSERCION DE CERTIFICADOS OT CON AUTORIZACION DE CAMBIO</h1>
					<div class="marco_interior">
						<form id="certificado" name="certificado" action="">			   					
							<table class="table_plana" style="padding-top: 0px">
								<tr>
									<td width="40%">
										<fieldset>
				   						    <label for="idMarca">Marca</label>
				   							<select name="idMarca" id="idMarca"></select>
				   							<label for="idFamilia">Familia</label>
				   							<select name="idFamilia" id="idFamilia"></select>
				   							<label for="idProducto">Producto</label>
				   							<select name="idProducto" id="idProducto"></select>
				   							<label for="numeroCertificado">Nº de certificado</label>
				   							<input type="text" id="numeroCertificado" name="numeroCertificado">
				   						</fieldset>
									</td>
									<td width="40%">
										<fieldset>
											<label for="idModelo">Modelo</label>
				   							<select name="idModelo" id="idModelo"></select>
				   							<label for="numeroSerie">Nº de serie</label>
				   							<input type="text" name="numeroSerie" id="numeroSerie"></select>
				   							<label for="vigente">Estado</label>
				   							<select id="vigente" name="vigente">
				   								<option value="">[SELECCIONE]</option>
				   								<option value="true">VIGENTE</option>
				   								<option value="false">NO VIGENTE</option>
				   							</select>
				   						</fieldset>
									</td>
									<td width="20%"></td>
								</tr>
								<tr id="fechas">
									<td width="40%">
										<fieldset>
											<label for="fechaCreacion" style="width: 227px !important">Fecha modificacion inicio</label>
				   							<input type="text" name="fechaCreacion" id="fechaCreacion" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="40%">
										<fieldset>
				   							<label for="fechaCreacionFin" style="width: 227px !important">Fecha modificacion fin</label>
				   							<input type="text" name="fechaCreacionFin" id="fechaCreacionFin" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="20%"></td>
								</tr>
								<tr>
									<td colspan="3">
				   						<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="guardar" id="guardar" value="Guardar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
											<input type="checkbox" id="checkBuscar" name="checkBuscar">
				   						</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<!-- <h1>Resultados(Doble click para ver resultado de la OT)</h1> -->
					<div class="marco_interior_tabla">
						<div id="resultadoCertificado"></div>
					</div>
				</div>
	 		</div>
 		</div>
	</div>
</body>
</html>