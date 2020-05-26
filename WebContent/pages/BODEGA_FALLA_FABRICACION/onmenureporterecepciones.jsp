<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onmenureporterecepciones.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="hiddenRecepcion" name="hiddenRecepcion" value='<%=request.getParameter("idRecepcion")%>'>
			<div class="container">
				<h1>REPORTE DE RECEPCIONES</h1>
				<div class="marco">
					<h1>REPORTE DE RECEPCIONES</h1>
					<div id="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador" class="formTab">
							<table class="table_plana" style="padding-bottom: 0px">
								<tr>
									<td width="35%">
										<fieldset>
											<label for="transportista">Transportista</label>
											<select id="transportista" name="transportista"></select>
											
											<label for="idRecepcion">ID recepción</label>
											<input type="text" id="idRecepcion" name="idRecepcion" class="number">
											
											<label for="usuarioRecepcion.id">Usu. Recepción</label>
											<select id="usuarioRecepcion.id" name="usuarioRecepcion.id"></select>
										
											<label for="responsable">Responsable</label>
											<input type="text" id="responsable" name="responsable">
											
											<label for="numeroGuia">N° de guía</label>
											<input type="text" id="numeroGuia" name="numeroGuia" class="number">
										</fieldset>
									</td>
									<td width="35%">
										<fieldset>
											<fieldset>
											<label for="estadoRecepcionGuia.id" >Estado guía</label>
											<select id="estadoRecepcionGuia.id" name="estadoRecepcionGuia.id" ></select>
											</fieldset>
											<label for="estadoActualGuia.id">Es. actual guía</label>
											<select id="estadoActualGuia.id" name="estadoActualGuia.id"></select>
		
											<label for="estadoRecepcionOT.id">Estado rec. OT </label>
											<select id="estadoRecepcionOT.id" name="estadoRecepcionOT.id"></select>
		
											<label for="idZona">Zona</label>
											<select id="idZona" name="idZona" class="select" ></select>
											
											<label for="idSucursal">Sucursal</label>
											<select id="idSucursal" name="idSucursal" class="select"></select>
										</fieldset>
									</td>
									<td width="30%">
										<fieldset>
											<label for="idOT">N° OT</label>
											<input type="text" id="idOT" name="idOT" class="number">
											
											<label for="codigoBarras">Cód. barra</label>
											<input type="text" id="codigoBarras" name="codigoBarras" class="number">
											
											<label for="idLinea">Linea</label>
											<select id="idLinea" name="idLinea" ></select>
											
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia" ></select>
											
											<label for="idProducto">Producto</label>
											<input type="text" id="idProducto" name="idProducto" maxlength="9" class="number">
		<!-- 									<select id="idProducto" name="idProducto" ></select> -->
										</fieldset>
									</td>
								</tr>
							</table>
							<table class="table_plana" style="padding-top: 0px">
								<tr>
									<td width="35%">
										<fieldset>
											<label for="fechaRecepcionInicio" style="width: 170px !important">fecha Recepción inicio</label>
											<input type="text" id="fechaRecepcionInicio" name="fechaRecepcionInicio" class="fecha dateDDMMYYYY" >
											<label for="fechaCreacion" style="width: 170px !important">fecha creación inicio</label>
											<input type="text" id="fechaCreacion" name="fechaCreacion" class="fecha dateDDMMYYYY" >
										</fieldset>
									</td>
									<td width="35%">
										<fieldset>
											<label for="fechaRecepcionFin" style="width: 170px !important">Fecha recepción fin</label>
											<input type="text" id="fechaRecepcionFin" name="fechaRecepcionFin" class="fecha dateDDMMYYYY" >
											<label for="fechaCreacionFin" style="width: 170px !important">Fecha creación fin</label>
											<input type="text" id="fechaCreacionFin" name="fechaCreacionFin" class="fecha dateDDMMYYYY" >
										</fieldset>
									</td>
									<td width="30%">
										<fieldset>
											<label for="idMarca">Marca</label>
											<select id="idMarca" name="idMarca"></select>
																			
											<label for="numeroSerie">N° de serie</label>
											<input type="text" id="numeroSerie" name="numeroSerie">
										</fieldset>
									</td>
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
						<h1>Resultados</h1>
						<div class="marco_interior_tabla">
							<div id="resultados"></div>
						</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>