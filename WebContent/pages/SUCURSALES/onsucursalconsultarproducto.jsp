<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalconsultarproducto.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>CONSULTAR PRODUCTOS</h1>
				<div class="marco">
					<h1>Buscador por Producto</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana">
				   				<tr>
				   					<td width="33%">
				   						<fieldset>
				   							<label for="idProducto">Código Corto</label>
				   							<input type="text" name="idProducto" id="idProducto" class="required number" maxlength="9">
				   						</fieldset>
				   					</td>
				   					<td  width="33%">
				   						<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
				   						</fieldset>
				   					</td>
				   					<td  width="33%">
				   					</td>
				   				</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Información del producto</h1>
					<div class="marco_interior">
						<form action="" id="formProducto" name="formProducto">
							<table class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset class="twoCol">
											<label for="descripcion" style="width:30%">PRODUCTO</label>
											<label id="descripcion" style="width:69%"></label>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset class="twoCol">
											<label for="marca.nombre" style="width:30%">MARCA</label>
											<label id="marca.nombre" style="width:69%"></label>
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
						<div class="marco_interior_tabla">
							<div id="fallas"></div>
						</div>
					</div>
				</div>
	<!-- 			<div class="marco"> -->
	<!-- 				<h1>Fallas</h1> -->
	<!-- 				<div class="marco_interior_tabla"> -->
	<!-- 					<div id="fallas"></div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
				
				<div style="width: 100%; height: 250px" id="confEspecificas">
					<div class="marco" style="width: 48%; float: left;">
						<h1>Accesorios Requeridos</h1>
						<div class="marco_interior_tabla">
							<div id="accesorios"></div>
						</div>
					</div>
					<div class="marco" style="width: 48%; float: right;">
						<h1>Partes del Producto a Revisar</h1>
						<div class="marco_interior_tabla">
							<div id="partes"></div>
						</div>
					</div>
				</div>
				
	<!-- 			<div class="marco"> -->
	<!-- 				<h1>Accesorios Requeridos</h1> -->
	<!-- 				<div class="marco_interior_tabla"> -->
	<!-- 					<div id="accesorios"></div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 			<div class="marco"> -->
	<!-- 				<h1>Partes del Producto a Revisar</h1> -->
	<!-- 				<div class="marco_interior_tabla"> -->
	<!-- 					<div id="partes"></div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
			</div>
		</div>
	</div>
</body>
</html>