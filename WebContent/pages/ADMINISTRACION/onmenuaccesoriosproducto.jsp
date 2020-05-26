<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuaccesoriosproducto.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>ADMINISTRAR ACCESORIOS DE LOS PRODUCTOS</h1>
				 <div id="tabs">
					<ul>
						<li id="onmenutipoFallasFamiliaTab"><a href="#onmenutipoFallasFamiliaPanel">Familia</a></li>
						<li id="onmenutipoFallasProductosTab"><a href="#onmenutipoFallasProductosPanel">Productos</a></li>
					</ul> 
					<div id="onmenutipoFallasFamiliaPanel">
						<div class="marco_interior">
							<form id="filtro_familia" class="formTab">
								<table class="table_plana" style="width: 100%">
					   				<tr>
					   					<td width="40%">
					   						<fieldset>
						   						<label for="idFamilia">Familia</label>
						   						<select id="idFamilia" name="idFamilia" class="required"></select>
						   					</fieldset>
					   					</td>
					   					<td width="15%">
											<fieldset>
						   						<input type="button" id="buscarTipoFamilia" name="buscarTipoFamilia" value="Buscar">
						   					</fieldset>
					   					</td>
					   					<td width="45%">
											<fieldset>
												<label for="cantidad" style="width: 70%">Cantidad de Productos</label>
						   						<label id="cantidad" name="cantidad" style="width: 10%"></label>
						   					</fieldset>
					   					</td>
					   				</tr>
								</table>
							</form>
							<div class="marco" id="DivResultado">
								<h1>Resultados (Doble Click para editar)</h1>
								<div class="marco_interior">
									<div id="resultadosFamilias"></div>
								</div>
							</div>
						</div>
					</div>
					<div id="onmenutipoFallasProductosPanel">
						<form id="filtro_producto" name="filtro_buscador" class="formTab">
							<table class="table_plana" style="width: 100%">
				   				<tr>
				   					<td width="40">
										<fieldset>
					   						<label for="idProducto">Codigo Corto</label>
					   						<input type="text" id="idProducto" name="idProducto" class="number required" maxlength="9">
					   					</fieldset>
				   					</td>
				   					<td width="15%">
										<fieldset>
					   						<input type="button" id="buscarTipoProducto" name="buscarTipoProducto" value="Buscar">
					   					</fieldset>
				   					</td>
				   					<td width="45%">
										<fieldset>
				   							<label id="descripcion" name="descripcion" style="width: 100%"></label>
			   							</fieldset>
				   					</td>
				   				</tr>
				   				<tr>
				   					<td>
				   						<fieldset>
					   						<label for="marca.nombre" style="width: 15%">Marca</label>
					   						<label id="marca.nombre">&nbsp;</label>
					   					</fieldset>
				   					</td>
				   					<td colspan="2">
										<fieldset>
					   						<label for="familia.nombre" style="width: 15%">Familia</label>
					   						<label id="familia.nombre" name="familia.nombre">&nbsp;</label>
					   					</fieldset>
				   					</td>
				   				</tr>
							</table>
						</form>
						<div class="marco" id="DivResultado">
							<h1>Resultados (Doble Click para editar)</h1>
							<div class="marco_interior">
								<div id="resultadosProductos"></div>
							</div>
						</div>
					</div>
				</div>
			</div> 
		</div>
		
		<div id="mantenedorAccesorio" title="Accesorio">
			<form action="" id="accesorioForm" name="accesorioForm">
				<fieldset class="fieldset_popup">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="familia.id" name="familia.id">
					<input type="hidden" id="producto.id" name="producto.id">
					<input type="hidden" id="tipo" name="tipo">
					<input type="hidden" id="vigente" name="vigente">
					<label for="descripcion">Descripcion</label>
					<input type="text" id="descripcion" name="descripcion" maxlength="128" class="required">
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>


