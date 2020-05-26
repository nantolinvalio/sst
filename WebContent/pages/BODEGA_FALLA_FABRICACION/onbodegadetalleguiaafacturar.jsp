<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onbodegadetalleguiaafacturar.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idGuia" value="<%=request.getParameter("idGuia")%>"/>
			<div class="container">
			<h1>DETALLE ORDENES DE TRABAJO A FACTURAR</h1>
				<div class="marco">
					<h1>Detalle de las OT de la guia <span id="nombre_indicador"></span></h1>
					<div class="marco_interior_tabla">
						<div id="directoaFacturar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>

