<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../library.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/modificarestadoot.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Modificar estado</h1>
				<div class="marco">
					<h1>Buscar orden de trabajo para modificar estado</h1>
					<div class="marco_interior">
						<form id="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="idOT">Nº O.T.</label>
											<input type="text" id="idOT" name="idOT" class="number">
											
											<label for="fechaCambioEstadoInicio">Fecha cambio inicio</label>
											<input type="text" name="fechaCambioEstadoInicio" id="fechaCambioEstadoInicio" format="dd/MM/yyyy" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idEstado">Estado O.T.</label>
											<select id="idEstado" name="idEstado">
												<option value="">[SELECCIONE]</option>
												<option value="2">Enviada a S. Técnico</option>
												<option value="3">Enviada a Control calidad</option>
												<option value="4">Alamacenada</option>
												<option value="5">Preparada para despacho a sucursal</option>
											</select>
										</fieldset>
										<fieldset>
											<label for="fechaCambioEstadoFin">Fecha cambio termino</label>
											<input type="text" name="fechaCambioEstadoFin" id="fechaCambioEstadoFin" format="dd/MM/yyyy" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
								</tr>
								<tr>
									<td align="right">
										<fieldset>
											<input type="button" id="limpiar" value="Limpiar">
										</fieldset>
									</td>
									<td align="left">
										<fieldset>
											<input type="button" id="buscar" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="marco_interior">
						<div id="resultados"></div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>