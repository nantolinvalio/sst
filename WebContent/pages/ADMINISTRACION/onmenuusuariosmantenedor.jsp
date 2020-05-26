<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuusuariosmantenedor.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>USUARIOS DEL SISTEMA</h1>
				<div class="marco">
					<h1>Buscador de Usuarios</h1>
					<div id="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="49%">
										<fieldset>
											<label for="run" style="width: 30%">Rut Usuario</label>
											<input type="text" id="run" name="run" class="run" style="width: 150px">
										</fieldset>
									</td>
									<td width="49">
										<fieldset>
											<label for="idUbicacion" style="width: 30%">Ubicacion</label>
											<select id="idUbicacion" name="idUbicacion"></select>
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
					<h1>Resultados (Doble click para editar)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				</table>
	 		</div>
 		</div>
 		<div id="ubicacionesModal" title="Ubicaciones">
				<form action="" id="ubicacionesForm" name="ubicacionesForm">
					<div id="ubicaciones"></div>
				</form>
		</div>
	</div>
</body>
</html>