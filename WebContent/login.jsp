<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
	<head>
		<%@include file="pages/version.jsp"%>
		<title>ABCDIN :: Sistema de servicio tecnico :: </title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css?<%= version %>" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/jquery-ui-1.8.16.custom.css?<%= version %>" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/jquery.alerts.css?<%= version %>" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui-1.8.20.custom.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.cookie.js"?<%= version %>></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui-timepicker-addon.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.validate.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.alerts.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.maxlength-min.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/date.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/custom.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/SSTFacade.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/utilsLogin.js?<%= version %>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/login.js?<%= version %>"></script>
	</head>
	<body style="width: 100% !important">
		<div class="container" style="width: 30%; margin-top: 200px; background-color: #FFFFFF !important; border-color: #FFFFFF !important">
			<div class="marco" style=" border: 2px solid #BEBB94 !important">
				<h1>Ingreso a la aplicación</h1>
				<div class="marco_interior">
					<table class="table_plana" width="100%">
						<tr>
							<td>
								<form action="" name="login" id="login">
									<fieldset>
										<label for="login">Usuario</label> 
										<input type="text" name="login" id="login" class="required"> 
										<label for="password">Contraseña</label>
										<input type="password" name="password" id="password" class="required">
										<label for="ubicacion.id">Dependencia</label>
										<select name="ubicacion.id" id="ubicacion.id" class="required"></select>
										<label for="autoLogin"></label> 
										<input type="checkbox" id="autoLogin" name="autoLogin" style="width: 20px !important"> No cerrar sesión
									</fieldset>
								</form>
								<fieldset class="fieldset_botonera_right">
									<input type="button" value="Buscar Dependencias" id="buscarDependencias" name="buscarDependencias" style="width: 59%">
									<input type="button" value="Iniciar Sesión" id="iniciarSesion" name="iniciarSesion" style="width: 59%; display: none;">
									<input type="button" value="Cambiar Usuario" id="cambiarUsuario" name="cambiarUsuario" style="width: 59%; display: none; margin-top: 5px">
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>