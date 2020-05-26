<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenurolesconfigurar.js?<%= version %>"></script>
</head>
	<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idRol" value="<%=request.getParameter("idRol")%>">
			<div class="container">
				<h1>CONFIGURACION DEL PERFIL</h1>
				<div class="marco">
					<h1>Información del perfil</h1>
					<div class="marco_interior_tabla">
						<table class="table_plana selectMultiple">
							<tr>
								<td style="width: 40%">
									<fieldset>
										<form action="" id="rol" name="rol">
				   							<label for="nombre" style="width: 20% !important">PERFIL</label>
				   							<input type="text" name="nombre" id="nombre" maxlength="64" class="required" style="width: 78% !important">
			   							</form>
			   						</fieldset>
								</td>
								<td style="width: 20%"></td>
								<td style="width: 40%">
									<fieldset>
			   							<label for="idMacro" style="width: 20% !important">MACRO</label>
			   							<select name="idMacro" id="idMacro" style="width: 79% !important"></select>
			   						</fieldset>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="marco">
					<h1>Configuración de Páginas</h1>
					<div class="marco_interior_tabla">
						<table class="table_plana selectMultiple">
							<tr>
								<td width="43%" style="padding-right: 0px">
									<label for="paginasSistema">PÁGINAS DEL SISTEMA</label>
									<select multiple="multiple" size="20" id="paginasSistema" name="paginasSistema"></select>
								</td>
								<td width="14%" class="buttons" style="padding-left: 0px; padding-right: 0px">
									<input type="button" value="Agregar ->" id="agregar" name="agregar">
									<input type="button" value="<- Quitar" id="quitar" name="quitar">
								</td>
								<td width="43%" style="padding-left: 0px">
									<label for="paginasPerfil">PÁGINAS DEL PERFIL</label>
									<select multiple="multiple" size="20" id="paginasPerfil" name="paginasPerfil"></select>
									<input type="button" value="Configurar Pestañas" style="float: right" id="configurarPestanas">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<label style="width: 20%"><b>DESCRIPCIÓN</b></label>
								</td>
							</tr>
							<tr>
								<td colspan="3" id="descripcion">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<table class="table_plana" style="width: 100%">
	   				<tr>
	   					<td>
	   						<fieldset class="fieldset_botonera_center">
								<input type="button" name="grabar" id="grabar" value="Grabar Configuración">
	   						</fieldset>
	   					</td>
	   				</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>