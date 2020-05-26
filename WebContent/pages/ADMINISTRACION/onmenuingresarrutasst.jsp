<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuingresarrutasst.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<div class="container">
			<h1>Ingresar rutas de servicio técnico</h1>
			<div class="marco">
				<div class="marco_interior">
					<h1>Buscar rutas</h1>
					<form id="buscador">
						<table class="table_plana" width="100%">
							<tr>
								<td width="50%">
									<fieldset>
										<label for="codigo">Código ruta</label>
										<input type="text" id="codigo" name="codigo" maxlength="10">
									
										<label for="vigente">Estado Ruta</label>
										<select id="vigente" name="vigente">
											<option value="">SELECCIONE</option>
											<option value="true">ACTIVO</option>
											<option value="false">INACTIVO</option>
										</select>
									</fieldset>
								</td>
								<td width="50%">
									<fieldset>
										<label for="nombre">Nombre ruta</label>
										<input type="text" id="nombre" name="nombre">
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
			</div>
			<div class="marco">
				<h1>Rutas (doble click para editar una ruta)</h1>
				<div class="marco_interno"><div id="rutas"></div></div>
			</div>
		</div>
	</div>
	
	<div id="mantenedorRutas" title="Ingresar Ruta" style="padding: 0 !important">
		<div class="container">
			<div class="marco">
				<h1>Información ruta</h1>
				<div class="marco_interior">
					<form id="mantenedorRutasForm" name="mantenedorRutasForm">
						<table class="table_plana" width="100%">
							<tr>
								<td colspan="50%">
									<fieldset>
										<input type="hidden" id="id" name="id">
										<label for="nombre">Nombre</label>
										<input type="text" id="nombre" name="nombre" maxlength="64" class="required">
									</fieldset>
								</td>
								<td width="50%">
									<fieldset>
										<label for="codigo">Código</label>
										<input type="text" id="codigo" name="codigo" maxlength="10" class="required">
									</fieldset>
								</td>
							<tr>
							<tr>
								<td>
									<fieldset>
										<label for="vigente">Estado</label>
										<select id="vigente" name="vigente" class="required">
											<option value="">[SELECCIONE]</option>
											<option value="true">ACTIVA</option>
											<option value="false">INACTIVA</option>
										</select>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="marco">
				<h1>Asociar servicios técnicos a la ruta</h1>
				<div class="marco_interior">
					<form id="filtroST" name="filtroST">
						<table class="table_plana" align="center">
							<tr>
								<td width="33%">
									<fieldset>
										<label for="idRegion">Región</label>
										<select id="idRegion" name="idRegion"></select>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset>
										<label for="idComuna">Comuna</label>
										<select id="idComuna" name="idComuna"></select>
									</fieldset>
								</td>
								<td width="33%">
									<fieldset>
										<label for="nombre">Nombre S.T.</label>
										<input type="text" id="nombre" name="nombre">
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<fieldset>
										<input type="button" id="limpiarST" value="Limpiar">
										<input type="button" id="buscarST" value="Buscar servicio técnico">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
					<form id="listasST" name="listasST">
						<table class="table_plana" width="100%">
							<tr>
								<td width="40%">
									<fieldset>
										<label style="width: 100%">Todos los servicios técnicos</label>
										<select id="allSTecnicos" name="allSTecnicos" multiple style="width: 100%" size=20></select>
									</fieldset>
								</td>
								<td width="20%" align="center" style="padding-top:15%">
									<fieldset>
										<input type="button" id="agregar" value="Agregar -->" style="width:140px">
									</fieldset>
									<fieldset>
										<input type="button" id="quitar" value="<--Quitar" style="width:140px">
									</fieldset>
								</td>
								<td width="40%" align="left">
									<fieldset>
										<label style="width: 100%">Servicios técnicos de esta ruta</label>
										<select id="itsSTecnicos" name="itsSTecnicos" multiple style="width: 100%" size=20></select>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>