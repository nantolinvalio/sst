<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuingresarubicbodega.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Ingresar Ubicaciones de Bodega</h1>
				<div class="marco">
					<h1>Buscar ubicaciones internas</h1>
					<div class="marco_interior">
						<form id="formSucursales" class="formTab">
							<table class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="nombre">Nombre</label>
											<select id="nombre" name="nombre"></select>
										</fieldset>
										<fieldset>
											<label for="idLinea">Linea</label>
											<select id="idLinea" name="idLinea"></select>
										</fieldset>
										<fieldset>
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>									
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idSucursal">Sucursal</label>
											<select id="idSucursal" name="idSucursal"></select>
										</fieldset>
										<fieldset>
											<label for="codigo">Código</label>
											<input type="text" id="codigo" name="codigo" class="">
										</fieldset>
										<fieldset>
											<label for="vigente">Estado</label>
											<select id="vigente" name="vigente">
												<option value="">[SELECCIONE]</option>
												<option value="true">ACTIVO</option>
												<option value="false">INACTIVO</option>
											</select>
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
						<h1>Resultados (doble click para editar un ubicación)</h1>
					<div class="marco_interior">
						<div id="resultados"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>