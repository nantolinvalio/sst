<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="../library.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/ondetalleotcambioautomatico.js?<%= version %>"></script>	
	</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idOT" name="idOT" value='<%=request.getParameter("idOT")%>'>
			<div class="container">
				<h1>
					Estado de la orden de trabajo N° <span id="idOT"><%=request.getParameter("idOT")%></span>
				</h1>
				<div class="marco">
					<h1>Etapa de la orden de trabajo</h1>
					<table class="table_plana" id="etapasOT">
					</table>
				</div>
				<fieldset id="estadoOT"></fieldset>
				<div class="marco">
					<h1>Bitacora</h1>
					<div class="marco_interior_tabla">
						<div id="bitacora"></div>
					</div>
				</div>
		
				<div id="tabs" >
					<ul></ul>
				</div><!-- Fin tabs -->
				<div align="center">
					<table width="100%" align="center">
						<tr>
							<td width="33%" align="center">
								<input type="button" id="btnReporteCambio" value="Imprimir informe de cambio" style="float:center;"/>
							</td>
							<td width="33%" align="center">
								<input id="btnTicketDeCambio" type="button" name="btnTicketDeCambio" value="Imprimir ticket de cambio"/>
							</td>
						</tr>
					</table>
				</div>
			</div><!-- Fin container -->	
		</div>
	</div>
</body>
</html>