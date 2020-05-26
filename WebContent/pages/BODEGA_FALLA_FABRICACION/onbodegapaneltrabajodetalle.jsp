<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %> 
	<script type="text/javascript" src="<%= request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onbodegapaneltrabajodetalle.js?<%= version %>"></script> 
</head>
<body>	
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idIndicador" value="<%=request.getParameter("idIndicador")%>"/>
			<div class="container">
			<h1>DETALLE PANEL DE TRABAJO</h1>
			<div class="marco">
				<h1>Detalle del indicador <span id="nombre_indicador"></span></h1>
				<div class="marco_interior_tabla">
					<div id="resultados"></div>
				</div>
			</div>
			</div>
			<table class="table_datos">
				<tr>
					<td align="center"><label style="color: blue;">OTs AUTORIZADAS DE CAMBIO</label> 
					<label style="color: red;">OTs CON TAREA URGENTE</label> 
					<label style="color: gray;">OTs DESACTIVADAS</label>
					<label style="color: brown;">OTs CERRADAS POR CLIENTE</label> 
					<label style="color: darkgreen;">OTs CERRADAS POR USUARIO</label></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>