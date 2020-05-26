<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalemitirguiadespacho.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idOT" name="idOT" value='<%=request.getParameter("idOT")%>'>
			<input type="hidden" id="idGuia" name="idGuia" value='<%=request.getParameter("idGuia")%>'>
			<div class="container">
				<div class="marco">
					<h1>Etapa de la orden de trabajo</h1>
					<table class="table_plana" id="etapasOT">
					</table>
				</div>
				
				<div id="tabs" >
					<ul></ul>
				</div>
				<div>
					<input type="button" id="volver" name="volver" value="Volver" style="margin-top:10px">
				</div>
			</div>
		</div>
	</div>
</body>
</html>