<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/POPUP/onsucursalgarantiahistorial.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
			<h1>Historial del Producto</h1>
				<div class="marco"><br/>
					<form id="documentoYProducto">
						<table class="table_plana" width="100%">
							<tr>
								<td width="100%">
									<fieldset>
										<label id="tipoDocumento" for="idDocumento" style="width:100px;"><%=request.getParameter("tipoDocumento")%></label>
										<label id="idDocumento" style="width:780px;"><%=request.getParameter("idDocumento")%></label>
										
										<label for="producto" style="width:100px;">Producto</label>
										<label id="producto" style="width:780px;"><span id="idProducto"><%=request.getParameter("idProducto")%></span>,&nbsp;<span id="descripcion">&nbsp;</span>,&nbsp;<span id="marca.nombre"></span></label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="marco">
					<div id="historalProducto"></div>
				</div>
				<div style="margin-top:10px"><input type="button" value="Volver" id="volver"/></div>
			</div>
		</div>
	</div>
</body>
</html>