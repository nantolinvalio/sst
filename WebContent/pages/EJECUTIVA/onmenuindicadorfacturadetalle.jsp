<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenuindicadorfacturadetalle.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idFacturar" name="idFacturar" value='<%= request.getParameter("idFacturar") %>'>
			<input type="hidden" id="tipoOT" name="tipoOT" value='<%= request.getParameter("tipoOT") %>'>
			<input type="hidden" id="idEjecutiva" name="idEjecutiva" value='<%= request.getParameter("idEjecutiva") %>'>
			<input type="hidden" id="fechaInicio" name="fechaInicio" value='<%= request.getParameter("fechaInicio") %>'>
			<input type="hidden" id="fechaTermino" name="fechaTermino" value='<%= request.getParameter("fechaTermino") %>'>
			<input type="hidden" id="facturarTipo" name="facturarTipo" value='<%= request.getParameter("facturarTipo") %>'>
			<input type="hidden" id="idIndicador" name="idIndicador" value='<%= request.getParameter("idIndicador") %>'>
			<input type="hidden" id="fecha" name="fecha" value='<%= request.getParameter("fecha") %>'>
			<div class="container" >
		   			<div class="marco">
		   				<h1 >Detalle de Facturas del Indicador</h1>
		   				<div class="marco_interior">   				
							<div class="marco_interior_tabla">
								<div id="facturas"></div>
							</div>					
		   				</div>   		
					</div> 
			</div>
		</div>
	</div>
</body>
</html>