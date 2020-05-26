<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenustecnicomantenedor.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Administrar Servicios Tecnicos</h1>
				<div class="marco">
				<h1> Buscar servicios tecnicos</h1>
				    <form action="" name="filtro_buscador" id="filtro_buscador">
				   <div class = "marco_interior_tabla"> 
					<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="49%">
										<fieldset>
								            <label for="nombre" style="width: 20%">Nombre</label>
									        <input type="text" id="nombre" name="nombre" style="width: 70%">
										</fieldset>
									</td>
									<td width="49%">
										<fieldset>
											<label for="rut" style="width: 20%">Rut</label>
											<input type="text" id="rut" name="rut" class="run" style="width: 150px">
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
					</form>
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
 		<div id="mantenedorStecnico" title="Agregar S.tecnico" >
			<form action="" id="mantenedorStecnicoForm" name="mantenedorStecnicoForm">
				<fieldset class="fieldset_popup">
					<div class="marco" >
						<h1>Información del Servicio Técnico</h1>
						<div class="marco_interior" >
							<table class="table_plana">
								<tr>
									<td style="width: 49%">
										<fieldset>
				   							<label for="nombre">NOMBRE</label>
				   							<input type="text" name="nombre" id="nombre" maxlength="50" class="required" >
				   							<label for="direccion">DIRECCION</label>
				   							<input type="text" name="direccion" id="direccion" maxlength="50" class="required">
				   							<label for="giro">GIRO</label>
				   							<input type="text" name="giro" id="giro" maxlength="32" class="required">
				   							<label for="region.id">REGION</label>
				   						    <select id="region.id" name="region.id" class="required" ></select>
				   						
				   						</fieldset>
									</td>
									<td style="width: 49%">
										<fieldset>
				   							<input type="hidden" name="id" id="id">
				   							<label for="rut">RUT</label>
				   							<input type="text" name="rut" id="rut" class="run required" >
				   							<label for="telefono">TELEFONO</label>
				   							<input type="text" name="telefono" id="telefono" maxlength="20">
				   							<label for="comuna.id">COMUNA</label>
				   							<select id="comuna.id" name="comuna.id" class="required"></select>
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