<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuubicacionesmantenedor.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>UBICACIONES</h1>
				<div class="marco">
				  <h1>Buscar Ubicaciones</h1>
					<div class="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador" action="">
							<table class="table_plana" style="padding-bottom: 0px">
				   				<tr>
				   					<td width="49%">
				   						<fieldset>
				   							<label for="idUbicacion">Centro de Costo</label>
				   							<input type="text" name="idUbicacion" id="idUbicacion" class="number" maxlength="9">
					   					</fieldset>
				   					</td>
				   					<td  width="49%">
				   						<fieldset>
					   						<label for="tipo">Tipo Ubicación</label>
				   							<select name="tipo" id="tipo"></select>
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
					<h1>Resultados Ubicaciones (Doble Click para editar)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				</table>
	 		</div>
 		</div>
		<div id="mantenedorUbicacion" title="Ubicación">
			<form action="" id="mantenedorUbicacionForm" name="mantenedorUbicacionForm">
				<fieldset class="fieldset_popup">
					<div class="marco">
						<h1>Información de la Ubicación</h1>
						<div class="marco_interior_tabla"> 
							<table class="table_plana" style="width: 100%">
								<tr>
									<td style="width: 50%">
										<fieldset>
											<label for="tipo">Tipo</label>
											<select name="tipo" id="tipo" class="required" ></select>
											<label for="id">Centro Costo</label>
											<input type="text" name="id" id="id" class="number required" maxlength="9" >
											<label for="telefono">Teléfono</label>
											<input type="text" name="telefono" id="telefono" class="telefono required" >
											<label for="region.id">Región</label>
											<select name="region.id" id="region.id" class="required" ></select>
											<label for="codigoOW">Ubicacion OW</label>
											<input type="text" name="codigoOW" id="codigoOW" maxlength="6" >
										</fieldset>
									</td>
									<td style="width: 50%">
										<fieldset>
											<label for="nombre">Nombre</label>
											<input type="text" name="nombre" id="nombre" class="required" maxlength="64" >
											<label for="rut">Rut</label>
											<input type="text" name="rut" id="rut" class="run required" >
											<label for="direccion">Direccion</label>
											<input type="text" name="direccion" id="direccion" class="required" maxlength="64" >
											<label for="comuna.id">Comuna</label>
											<select type="text" name="comuna.id" id="comuna.id" class="required" ></select>
										</fieldset>
									</td>
								</tr>
						</table>	
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>