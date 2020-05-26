<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuusuarioscrear.js?<%= version %>"></script>
</head>
<body>
	<input type="hidden" id="idUsuario" value="<%=request.getParameter("idUsuario")%>">
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1 id="titlePage"></h1>
				<form action="" name="usuarioForm" id="usuarioForm">
					<div class="marco">
						<h1>Información del Usuario</h1>
						<div class="marco_interior">
							<table class="table_plana">
								<tr>
									<td style="width: 49%">
										<fieldset>
				   							<label for="nombre">NOMBRE</label>
				   							<input type="text" name="nombre" id="nombre" maxlength="32" class="required">
				   							<label for="apellidoPaterno">APELLIDO PATERNO</label>
				   							<input type="text" name="apellidoPaterno" id="apellidoPaterno" maxlength="32" class="required">
				   							<label for="apellidoMaterno">APELLIDO MATERNO</label>
				   							<input type="text" name="apellidoMaterno" id="apellidoMaterno" maxlength="32" class="required">
				   						</fieldset>
									</td>
									<td style="width: 49%">
										<fieldset>
				   							<label for="rut">RUT</label>
				   							<input type="text" name="rut" id="rut" class="required run">
				   							<label for="email">CORREO ELÉCTRONICO</label>
				   							<input type="text" name="email" id="email" maxlength="64" class="email required">
				   							<label for="anexo">ANEXO</label>
				   							<input type="text" name="anexo" id="anexo" maxlength="16" class="number required">
				   						</fieldset>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</form>
				<div class="marco">
					<h1>Dependencias</h1>
					<div class="marco_interior">
						<div id="dependencias"></div>
					</div>
				</div>
				<div class="marco">
					<h1>Perfiles</h1>
					<div class="marco_interior">
						<table class="table_plana">
							<tr>
								<td style="width: 49%">
									<fieldset  id="rolLeft">
									</fieldset>
								</td>
								<td style="width: 49%">
									<fieldset id="rolRight">
									</fieldset>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<table class="table_plana" style="width: 100%">
	   				<tr>
	   					<td>
	   						<fieldset class="fieldset_botonera_center">
								<input type="button" name="grabar" id="grabar" value="Grabar Usuario">
	   						</fieldset>
	   					</td>
	   				</tr>
				</table>
	 		</div>
 		</div>
 		<div id="dependenciaModal" title="Dependencias">
			<div class="container">
				<div class="marco">
					<h1>Seleccione depencia para agregar</h1>
					<div class="marco_interior">
						<table class="table_plana">
							<tr>
								<td style="width: 49%">
									<fieldset>
										<label for="idRegion">Región</label>
										<select id="idRegion" name="idRegion"></select>
			   						</fieldset>
								</td>
								<td style="width: 49%">
									<fieldset>
										<label for="idComuna">Comuna</label>
										<select id="idComuna" name="idComuna"></select>
			   						</fieldset>
								</td>
							</tr>
						</table>
						<table class="table_plana">
							<tr>
								<td style="width: 65%">
									<fieldset>
										<form action="" name="ubicacionForm" id="ubicacionForm">
											<label for="idDependencia" style="width: 30% !important">Dependencia</label>
											<select id="idDependencia" name="idDependencia" class="required" style="width: 65% !important"></select>
										</form>
									</fieldset>
								</td>
								<td>
									<input type="button" value="Agregar Nueva Dependencia" style="float: right;" id="agregarDependencia">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>