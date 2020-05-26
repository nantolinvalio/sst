<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuproductosmantenedor.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
			<h1>Administrar Productos</h1>
				<div class="marco">
					<h1>Buscar Productos</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana" style="padding-bottom: 0px">
				   				<tr>
				   					<td width="33%">
				   						<fieldset>
				   							<label for="idFamilia">Familia</label>
				   							<select name="idFamilia" id="idFamilia"></select>
				   							<label for="marca">Marca</label>
				   							<select name="marca" id="marca"></select>
			   							</fieldset>
				   					</td>
				   					<td  width="33%">
				   						<fieldset>
				   							<label for="descripcion">Descripción</label>
				   							<input type="text" maxlength="30" name="descripcion" id="descripcion">
				   							<label for="idProveedor">Proveedor</label>
				   							<select name="idProveedor" id="idProveedor"></select>
			   							</fieldset>
				   					</td>
				   					<td  width="33%">
				   						<fieldset>
				   							<label for="idProducto">Código Corto</label>
				   							<input type="text" maxlength="9" name="idProducto" id="idProducto" class="number">
				   							<label for="conSTecnico">Productos</label>
				   							<select name="conServicioTecnico" id="conServicioTecnico">
					   							<option value="">Ambas opciones</option>
												<option value="true">Con S. técnico asignado</option>
												<option value="false">Sin S. técnico asignado</option>
				   							</select>
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