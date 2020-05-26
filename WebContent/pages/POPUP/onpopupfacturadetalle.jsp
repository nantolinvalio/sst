<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/POPUP/onpopupfacturadetalle.js?<%= version %>"></script>	
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idFactura" name="idFactura" value='<%=request.getParameter("idFactura")%>'>
			<div class="container">
				<h1>
					Detalle de Factura
				</h1>
				<div id="tabs" >
					<ul></ul>
				</div><!-- Fin tabs -->
			</div><!-- Fin container -->	
		</div>
	</div>
</body>
</html>