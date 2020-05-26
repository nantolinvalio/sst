<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalterminar.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idOT" value="<%=request.getParameter("idOT")%>"/>
			<div class="container">
				<h1 id="tituloPagina">Crear orden de Trabajo</h1>
				<h2>Terminar orden de trabajo</h2>
				
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   disabled">Documento</td>
							<td class="td_etapa gProveedor  disabled">G. Proveedor</td>
							<td class="td_etapa gMaster     disabled">G. Master</td>
							<td class="td_etapa fallas      disabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    disabled">Revision</td>
							<td class="td_etapa terminar    enabled">Terminar</td>
						</tr>
					</table>
				</div>
				<div id="tabs">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>