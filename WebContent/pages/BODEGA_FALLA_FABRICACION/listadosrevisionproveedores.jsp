<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="../library.jsp" %>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/listadosrevisionproveedores.js?<%= version %>"></script>
	</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
			<div class="wrapper">
				<div class="container">
				<h1>Listados de Revisión de Proveedores</h1>
				<div class="marco revisiones">
					<h1>Listado de revisión de proveedores</h1>
					<div class="marco_interior">
						<form id="filtroRevisiones" name="filtroRevisiones" class="formTab">
							<table class="table_plana">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="idDespacho">N° Despacho</label>
											<input type="text" id="idDespacho" name="idDespacho" class="number">
											
											<label for="idProveedor">Proveedor</label>
											<select id="idProveedor" name="idProveedor"></select>
											
											<label for="fechaCreacionDesde">Fecha creación desde</label>
											<input type="text" id="fechaCreacionDesde" name="fechaCreacionDesde" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm"/>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
											
											<label for="fechaCreacionHasta">Fecha creación hasta</label>
											<input type="text" id="fechaCreacionHasta" name="fechaCreacionHasta" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm"/>
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
				<div class="marco revisiones">
					<h1>Resultados(Hace doble click para ver el detalle)</h1>
					<div class="marcoInterior"><div id="resultadosRevisiones"></div></div>
				</div>
				<div class="marco paraRevision">
					<h1>Listado para revisión en sala de proveedores</h1>
					<div class="marco_interior">
						<form id="filtroParaRevision" name="filtroParaRevision" class="formTab">
							<table class="table_plana">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="idProveedor">Proveedor</label>
											<select id="idProveedor" name="idProveedor" class="required"></select>
											
											<label for="fechaCreacionCorte">Fecha creación corte(Recepción)</label>
											<input type="text" id="fechaCreacionCorte" name="fechaCreacionCorte" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required"/>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
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
				<div class="marco paraRevision" >
					<h1>Resultados</h1>
					<div class="marco_interior">
						<div id="resultadosParaRevision"></div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>