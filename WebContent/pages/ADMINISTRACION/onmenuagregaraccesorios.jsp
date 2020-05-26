<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuagregaraccesorios.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idTipoFalla" value="<%=request.getParameter("idTipoFalla")%>">
			<div class="container">
				<h1>RELACIONAR TIPO DE FALLAS CON ACCESORIOS</h1>
				<div class="marco">
					<h1>Información del tipo de Falla</h1>
					<div class="marco_interior_tabla">
						<form action="" id="formTipoFalla">
							<table class="table_plana" style="width: 100%">
								<tr>
									<td colspan="2">
										<fieldset>
				   							<label for="descripcion" style="width: 20%">TIPO DE FALLA</label>
				   							<label name="descripcion" id="descripcion"></label>
				   						</fieldset>
									</td>
								</tr>
								<tr>
									<td width="50%">
										<fieldset>
				   							<label for="tipoGlosa" style="width: 40%">APLICABLE A</label>
				   							<label name="tipoGlosa" id="tipoGlosa"></label>
				   						</fieldset>
									</td>
									<td width="50%">
										<fieldset>
				   							<label name="glosa" id="glosa" style="width: 60%"></label>
				   						</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Configuración de Accesorios</h1>
					<div class="marco_interior_tabla">
						<table class="table_plana selectMultiple">
							<tr>
								<td width="43%" style="padding-right: 0px">
									<label for="accesoriosNoEnviar">ACCESORIOS QUE NO SE ENVIAN POR TIPO DE FALLA</label>
									<select multiple="multiple" size="20" id="accesoriosNoEnviar" name="accesoriosNoEnviar"></select>
								</td>
								<td width="14%" class="buttons" style="padding-left: 0px; padding-right: 0px">
									<input type="button" value="Agregar ->" id="agregar" name="agregar">
									<input type="button" value="<- Quitar" id="quitar" name="quitar">
								</td>
								<td width="43%" style="padding-left: 0px">
									<label for="accesoriosEnviar">ACCESORIOS QUE SE ENVIAN POR EL TIPO DE FALLA</label>
									<select multiple="multiple" size="20" id="accesoriosEnviar" name="accesoriosEnviar"></select>
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