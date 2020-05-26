<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalemitirguia.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>GUIAS PENDIENTES DE DESPACHO O RECEPCION EN DESTINO</h1>
				<div id="tabs">
					<ul>
						<li id="onguiasSTReparacionTab"><a href="#onguiasSTReparacionPanel">S. Técnico - Reparación</a></li>
						<li id="onguiasSTCambioTab"><a href="#onguiasSTCambioPanel">S. Técnico - Cambios</a></li>
						<!-- CU-11 SST DESHABILITAR  TAB-->
						<!--  <li id="onguiasFFTab"><a href="#onguiasFFPanel">Fallas de Fabricación</a></li> -->
						<li id="onguiasClienteTab"><a href="#onguiasClientePanel">Cliente</a></li>
					</ul>
					<div id="onguiasSTReparacionPanel">
						<form class="formTab">
							<div class="marco_interior_tabla">
								<div id="guiasSTReparacion"></div>
							</div>
						</form>
					</div>
					<div id="onguiasSTCambioPanel">
						<form class="formTab">
							<div class="marco_interior_tabla">
								<div id="guiasSTCambio"></div>
							</div>
						</form>
					</div>
					<!-- div id="onguiasFFPanel">
						<form class="formTab">
							<div class="marco_interior_tabla">
								<div id="guiasFF"></div>
							</div>
						</form>
					</div -->
					<div id="onguiasClientePanel">
						<form class="formTab">
							<div class="marco_interior_tabla">
								<div id="guiasCliente"></div>
							</div>
						</form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>