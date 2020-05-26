<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenurolesconfigurarpestana.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idRol" value="<%=request.getParameter("idRol")%>">
		<input type="hidden" id="idModulo" value="<%=request.getParameter("idModulo")%>">
			<div class="container">
				<h1>CONFIGURACION DE PESTAÑAS DE LA PAGINA</h1>
				<div class="marco">
					<h1 id="">Información de la página</h1>
					<div class="marco_interior_tabla">
						<table class="table_plana selectMultiple">
							<tr>
								<td colspan="3">
									<fieldset>
										<label for="pagina" style="width: 20%;">PAGINA</label>
			   							<label id="pagina" style="width: 79%;">&nbsp;</label>
			   							<label for="descripcionModulo"  style="width: 20%;">DESCRIPCIÓN</label>
			   							<label id="descripcionModulo" style="width: 79%;">&nbsp;</label>
			   							<label for="rol"  style="width: 20%;">PERFIL</label>
			   							<label id="rol" style="width: 79%;">&nbsp;</label>
		   							</fieldset>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="marco">
					<h1>Configuracón de Pestañas</h1>
					<div class="marco_interior_tabla">
						<table class="table_plana selectMultiple">
							<tr>
								<td width="43%" style="padding-right: 0px">
									<label for="pestanasSistema">PESTAÑAS POSIBLES</label>
									<select multiple="multiple" size="20" id="pestanasSistema" name="pestanasSistema"></select>
								</td>
								<td width="14%" class="buttons" style="padding-left: 0px; padding-right: 0px">
									<input type="button" value="Agregar ->" id="agregar" name="agregar">
									<input type="button" value="<- Quitar" id="quitar" name="quitar">
								</td>
								<td width="43%" style="padding-left: 0px">
									<label for="pestanasPagina">PESTAÑAS DE LA PAGINA</label>
									<select multiple="multiple" size="20" id="pestanasPagina" name="pestanasPagina"></select>
									<input type="button" value="&uarr; Subir Pestaña" style="float: left" id="subirOpcion">
									<input type="button" value="&darr; Bajar Pestaña" style="float: right" id="bajarOpcion">
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