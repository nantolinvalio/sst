<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/POPUP/onbodegaordentrabajohistorial.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Historial del Producto</h1>
				<div class="marco">
					<form id="productoForm">
						<table class="table_plana" width="100%">
							<tr>
								<td>
									<fieldset>
										<input type="hidden" id="idOT" value="<%=request.getParameter("idOT")%>">
										<label id="producto" style="width: 100%">Producto <span id="idProducto"><%=request.getParameter("idProducto")%></span></label>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<div id="grillaProducto"></div>
									<input type="button" value="Volver" id="volver">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>