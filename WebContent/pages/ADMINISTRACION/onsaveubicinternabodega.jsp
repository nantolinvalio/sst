<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onsaveubicinternabodega.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Agregar ubicaciones bodega</h1>
				<div class="marco">
					<div class="marco_interior">
						<h1>Crear una nueva ubicacion</h1>
						<form id="datosUbicacion" name="datosUbicacion">
							<table width="100%" class="table_plana">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="codigo">Código</label>
											<input type="text" id="codigo" name="codigo" value="<%=request.getParameter("codigo")%>" class="required" maxlength="6">
											
											<label for="vigente">Estado</label>
											<select id="vigente" name="vigente">
												<option value="true">ACTIVADO</option>
												<option value="false">DESACTIVADO</option>
											</select>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="nombre">Nombre</label>
											<input type="text" id="nombre" name="nombre" class="required">
											
											<label for="tipo.codigo">Tipo</label>
											<select id="tipo.codigo" name="tipo.codigo"></select>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<fieldset>
											<label for="descripcion">Descripción</label>
											<textarea style="width: 100%;"></textarea>
										</fieldset>
										<fieldset>
											<input type="button" id="crearUbicacion" value="Crear Ubicación" style="float:right;">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" id="agregarDetalles">
					<div class="marco_interior">
						<h1>Lineas, Familias o Productos almacenados en esta ubicación (Primero debe agregar una sucursal a la ubicación)</h1>
						<form id="elementosAlmacenados" name="elementosAlmacenados" style="background-color:#E8E3B7; padding: 5px; text-transform: uppercase;">
							<div class="by2" style="width:100%; overflow: hidden;">
								<div style="width: 100%">
									<div style="float:left; width: 50%">
										<fieldset>
											<div id="sucursales"></div>
										</fieldset>
									</div>
									<div style="float:right;width: 50%">
										<fieldset>
											<div id="familias"></div>
										</fieldset>
									</div>
								</div>
							</div>
							<div class="by2" style="width:100%; overflow: hidden;">
								<div style="width: 100%">
									<div style="float:left; width: 50%">
										<fieldset>
											<div id="lineas"></div>
										</fieldset>
									</div>
									<div style="float:right; width: 50%">
										<fieldset>
											<div id="productos"></div>
										</fieldset>
									</div>
								</div>
								<table width="100%">
									<tr>
										<td width="50%">
											<fieldset>
												<input type="button" id="volver" value="Volver">
											</fieldset>
										</td>
										<td width="50%" align="right">
											<fieldset>
												<input type="button" id="grabar" value="Grabar">
											</fieldset>
										</td>
									</tr>
								</table>
							</div><!-- Fin by2 -->
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="agregarLineas">
			<form id="agregarLineasForm" name="agregarLineasForm">
				<div id="lineasToAdd">
					
				</div>
			</form>
		</div>
		
		<div id="agregarFamilias">
			<form id="agregarFamiliasForm" name="agregarFamiliasForm">
				<div id="familiasToAdd">
					
				</div>
			</form>
		</div>
		
		<div id="agregarSucursales">
			<form id="agregarSucursalesForm" name="agregarSucursalesForm">
				<div id="sucursalesToAdd">
					
				</div>
			</form>
		</div>
		
		<div id="agregarProductos">
			<form id="agregarProductosForm" name="agregarProductosForm" class="marco">
				<table class="table_plana" width="100%">
					<tr>
						<td width="50%">
							<fieldset>
								<label for="idProducto" style="width: 100%">Código corto</label>
								<input type="text" id="idProducto" name="idProducto" class="required number">
							</fieldset>
						</td>
						<td width="50%">
							<fieldset>
								<input type="button" id="buscarProducto" value="buscar">
							</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<fieldset>
								<label for="descripcionProducto">Descripción: <span id="descripcionProducto">-</span></label>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td>
							<fieldset>
								<label for="familiaProducto">Familia: <span id="familiaProducto"></span></label>
	
							</fieldset>
						</td>
						<td>
							<fieldset>
								<label for="lineaProducto">Linea: <span id="lineaProducto">-</span></label>
							</fieldset>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>