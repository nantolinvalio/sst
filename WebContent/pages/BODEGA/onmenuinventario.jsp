<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onmenuinventario.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Inventario</h1>
				<div class="marco">
					<h1>Buscar inventario</h1>
					<div class="marco_interior">
						<form id="filtro_buscador">
							<table class="table_plana">
								<tr>
									<td>
										<fieldset>
											<label for="idUbicacionInterna">Ubicación interna</label>
											<select id="idUbicacionInterna" name="idUbicacionInterna"></select>
										</fieldset>
										<fieldset>
											<label for="fechaCreacionDesde">Fecha inicio desde</label>
				   							<input type="text" name="fechaCreacionDesde" id="fechaCreacionDesde" class="fecha dateDDMMYYYY">
				   							<label for="fechaCierreDesde">Fecha término desde</label>
				   							<input type="text" name="fechaCierreDesde" id="fechaCierreDesde" class="fecha dateDDMMYYYY">
										</fieldset>									
									</td>
									<td>
										<fieldset>
											<label for="vigente">Estado</label>
											<select id="vigente" name="vigente">
<!-- 												<option value="">[SELECCIONE]</option> -->
												<option value="true">ABIERTO</option>
												<option value="false">CERRADO</option>
											</select>
										</fieldset>
										<fieldset>
											<label for="fechaCreacionHasta">Fecha inicio hasta</label>
				   							<input type="text" name="fechaCreacionHasta" id="fechaCreacionHasta" class="fecha dateDDMMYYYY">
				   							<label for="fechaCierreHasta">Fecha término hasta</label>
				   							<input type="text" name="fechaCierreHasta" id="fechaCierreHasta" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
								</tr>
								<tr>
									<td align="right">
										<fieldset>
											<input type="button" id="limpiar" value="Limpiar">
										</fieldset>
									</td>
									<td>
										<fieldset>
											<input type="button" id="buscar" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Inventarios (doble click para ver el detalle del inventario)</h1>
					<div class="marco_interior">
						<div id="inventarios"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="crearInventario" title="Nuevo Inventario">
			<form id="crearInventarioForm">
				<fieldset>
					<div id="ubicacionesToInventario"></div>
				</fieldset>
			</form>
		</div>
		
		<div id="cerrarInventario" title="Revisar Inventario">
			<form id="cerrarInventarioForm">
				<fieldset>
					<div id="ubicacionesFromInventario"></div>
				</fieldset>
			</form>
		</div>
		
		
	</div>
</body>
</html>