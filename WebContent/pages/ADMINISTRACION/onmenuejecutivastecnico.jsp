<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuejecutivastecnico.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Administrar ejecutivas de marcas</h1>
				<div class="marco">
					<h1>Buscar servicios técnicos - marcas</h1>
					<div id="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="33%">
										<fieldset>
											<label for="nombreSTecnico">S.Tecnico</label>
											<input type="text" id="nombreSTecnico" name="nombreSTecnico">
		
											<label for="idEjecutiva">Ejecutiva</label>
											<select id="idEjecutiva" name="idEjecutiva"></select>
											
											<label for="conEjecutiva">S.Tecnico</label>
											<select id="conEjecutiva" name="conEjecutiva">
												<option value="" id="">[SELECCIONE]</option>
												<option id="true" value="true">Con ejecutiva</option>
												<option id="false" value="false">Sin ejecutiva</option>
											</select>
											
											<label for="idMarca">Marca</label>
											<select id="idMarca" name="idMarca"></select>
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="idRegion">Región</label>
											<select id="idRegion" name="idRegion"></select>
											
											<label for="idComuna">Comuna</label>
											<select id="idComuna" name="idComuna"></select>
											
											<label for="idFamilia">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
											
											<fieldset class="fieldset_botonera_center">
												<input type="button" id="limpiar" value="Limpiar">
												<input type="button" id="buscar" value="Buscar">
											</fieldset>
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="idSucursal">S.T. de Sucursal</label>
											<select id="idSucursal" name="idSucursal"></select>
											
											<label for="idBodega">S.T. de Bodega</label>
											<select id="idBodega" name="idBodega"></select>
											
											<label for="idProveedor">S.T. Proveedor</label>
											<select id="idProveedor" name="idProveedor"></select>
											
											<label for="idProducto" >C. Corto</label>
											<input type="text" id="idProducto" name="idProducto" class="number">
											
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" id="divResultado">
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="mantenedorEjecutiva" title="Ejecutiva">
			<form action="" id="ejecutivaForm" name="ejecutivaForm">
					<fieldset class="fieldset_popup">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="marca.codigo" name="marca.codigo">
					<input type="hidden" id="ubicacion.id" name="ubicacion.id">
					<label for="usuario.id">Ejecutiva de Marca</label>
					<select id="usuario.id" name="usuario.id"></select>
				</fieldset>				
			</form>
		</div>
	</div>
</body>
</html>

