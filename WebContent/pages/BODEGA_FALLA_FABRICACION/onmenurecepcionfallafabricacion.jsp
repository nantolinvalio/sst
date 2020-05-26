<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onmenurecepcionfallafabricacion.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Recepción falla de fabricación</h1>
				<div class="marco">
					<h1>Seleccione un transportista y al responsable para iniciar la recepción</h1>
					<div class="marco_interior_tabla">
						<form action="">
							<div id="recepciones"></div>				
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>