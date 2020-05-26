<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onejecutivareportecambio.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>CAMBIOS Y REPARACIONES DE PRODUCTOS</h1>
				<div class="marco">
					<h1>BUSCAR ORDEN DE TRABAJO CON CAMBIOS Y REPARACIONES DE PRODUCTOS</h1>
					<div class="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador" action="">
							<table class="table_plana" style="padding-bottom: 0px" width="100%">
				   				<tr>
				   					<td width="45%">
				   						<fieldset>
				   							<label for="fechaCreacion" style="width: 173px !important">Fecha creacion</label>
				   							<input type="text" name="fechaCreacion" id="fechaCreacion" class="fecha dateDDMMYYYY required">
				   							<label for="idProducto">Producto</label>
				   							<input type="text" name="idProducto" id="idProducto" class="number">
				   							<label for="idMarca">Marca</label>
				   							<select name="idMarca" id="idMarca"></select>
					   					</fieldset>
				   					</td>
				   					<td  width="45%">
				   						<fieldset>
				   							<label for="fechaCreacionFin"  style="width: 173px !important">fecha creacion</label>
				   							<input type="text" name="fechaCreacionFin" id="fechaCreacionFin" class="fecha dateDDMMYYYY required">
				   							<label for="idProveedor">Proveedor</label>
				   							<select id="idProveedor" name="idProveedor"></select>
			   							</fieldset>
				   					</td>
				   				</tr>
				   				<tr>
									<td colspan="2">
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