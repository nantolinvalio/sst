<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuzona.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>ZONAS</h1>
					<div class="marco">
					<h1>Zonas Existentes (Doble click para editar una zona)</h1>
					<div class="marco_interior_tabla">
						<div id="zonas"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="mantenedorZona" title="Zona">
			<form action="" id="mantenedorZonaForm" name="mantenedorZonaForm">
				<fieldset class="fieldset_popup">
					<input type="hidden" id="id" name="id">
					<label for="codigo">Código</label>
					<input type="text" id="codigo" name="codigo" maxlength="10" class="required">
					<label for="nombre">Nombre</label>
					<input type="text" id="nombre" name="nombre" maxlength="64" class="required">
					<label for="vigente">Estado</label>
					<select id="vigente" name="vigente" class="required">
						<option value="">[SELECCIONE]</option>
						<option value="true">ACTIVA</option>
						<option value="false">INACTIVA</option>
					</select>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>