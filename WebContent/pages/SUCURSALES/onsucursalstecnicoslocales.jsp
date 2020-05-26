<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalstecnicoslocales.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>CONSULTA DE SERVICIOS TÉCNICOS LOCALES</h1>
				<div class="marco">
					<h1>Buscador por Producto</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana">
				   				<tr>
				   					<td width="50%">
				   						<fieldset>
				   							<label for="idProducto">Código Corto</label>
				   							<input type="text" name="idProducto" id="idProducto" class="required number" maxlength="9">
				   						</fieldset>
				   					</td>
				   					<td  width="50%" style="text-align: center;">
				   						 MOSTRAR SOLO MIS DESTINOS <input type="checkbox" id="soloMisDestinos" name="soloMisDestinos">
				   					</td>
			   					</tr>
			   					<tr>
				   					<td  colspan="2">
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
					<h1>Información del producto</h1>
					<div class="marco_interior">
						<form action="" name="info_producto" id="info_producto">
							<table class="table_plana">
								<tr>
									<td  width="50%">
				   						<fieldset>
				   							<label for="descripcion" >Producto</label> 
				   							<label id="descripcion" >&nbsp;</label>
				   						</fieldset>
				   					</td>
				   					<td width="30%">
				   						<fieldset>
				   								<label for="marca.nombre" >Marca</label>
				   								<label id="marca.nombre">&nbsp;</label>
				   						</fieldset>
				   					</td>							
								</tr>
							</table>
						</form>
						
					</div>
				</div>
				<div id="tabs">
					<ul>
						<li id="onmenutipoFallasFamiliaTab"><a href="#onmenuserviciostecnicosPanel">Servicios Técnicos</a></li>
						<li id="onmenutipoFallasProductosTab"><a href="#onmenuprocedimientoPanel">Procedimiento</a></li>
					</ul>
					<div id="onmenuserviciostecnicosPanel">
						<div class="marco">
							<div class="marco_interior_tabla">
								<div id="resultados"></div>
							</div>
						</div>
					</div>
					<div id="onmenuprocedimientoPanel">
						<div class="marco">
							<div class="marco_interior_tabla">
								<form action="" name="procedimientoForm" id="procedimientoForm">
									<table class="table_plana">
										<tr>
											<td>
						   						<fieldset>
						   							<label >Procedimiento:</label> 
						   						</fieldset>
						   					</td>
										</tr>
										<tr>
											<td>
						   						<fieldset>
						   							<label id="procedimiento"></label> 
						   						</fieldset>
						   					</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>