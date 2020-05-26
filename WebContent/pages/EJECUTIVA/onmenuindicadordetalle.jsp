<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenuindicadordetalle.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idRol"        name="idRol"         value='<%= request.getParameter("idRol") %>'>
			<input type="hidden" id="idIndicador"  name="idIndicador"   value='<%= request.getParameter("idIndicador") %>'>
			<input type="hidden" id="idUsuario"    name="idUsuario"     value='<%= request.getParameter("idUsuario") %>'>
			<input type="hidden" id="fecha"        name="fecha"         value='<%= request.getParameter("fecha") %>'>
			<input type="hidden" id="fechaInicio"  name="fechaInicio"   value='<%= request.getParameter("fechaInicio") %>'>
			<input type="hidden" id="fechaTermino" name="fechaTermino"  value='<%= request.getParameter("fechaTermino") %>'>
			<div class="container">
				<h1>DETALLE DE ORDENES DE TRABAJO DEL INDICADOR</h1>
				<div class="marco">
					<h1 id="h1Indicador"></h1>
					<div id="resultados"></div>
				</div>
				<table class="table_datos">
		  			<tr>
		      			<td align="center">
							<label style="color:blue;">OTs AUTORIZADAS DE CAMBIO</label>
							<label style="color:red;">OTs CON TAREA URGENTE</label>
							<label style="color:gray;">OTs DESACTIVADAS</label>
							<label style="color:brown;">OTs CERRADAS POR CLIENTE</label>
							<label style="color:darkgreen;">OTs CERRADAS POR USUARIO</label>
		      			</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</boSdy>
</html>