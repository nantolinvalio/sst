<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onejecutivaindicadoresfactura.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container" >
		   			<h1>INDICADORES DE GESTIÓN DE EJECUTIVA DE FACTURACIÓN</h1>
		   			
		   			<!-- FORMULARIO FECHAS -->
		   			<table class="table_datos">
		        		<tr>
		            		<td align="right">Seleccione la fecha de inicio y termino para calcular los indicadores en ese rango de fechas</td>
		        		</tr>
		        		<tr>
		        			<td align="right">
		        				<form action="" name="rangoFechas" id="rangoFechas" >
			        				<input type="text" name="fechaInicio" id="fechaInicio" class="fecha required dateDDMMYYYY">
									<input type="text" name="fechaTermino" id="fechaTermino" class="fecha required dateDDMMYYYY">
									<input type="button" value="Calcular" id="calularIndicadoresFacturas" name="calularIndicadoresFacturas">
								</form>
							</td>
		        	</table>
		        	<!-- FIN FORMULARIO FECHAS -->
		
		        	<div class="marco">
		   				<h1>Facturas</h1>
		   				
		   				<table class="table_plana">
							<tr>
								<td>&nbsp;</td>
								<td>En los ultimos 7 días</td>
								<td>En los ultimos 30 días</td>
								<td>Para selección</td>
							</tr>
							<tr>
								<td align="left"># DE PEDIDOS SC GENERADOS</td>
								<td align="center"><span id="30001000"></span></td>
								<td align="center"><span id="30002000"></span></td>
								<td align="center"><span id="30001010"></span></td>
							</tr>
							<tr>
								<td align="left"># DE FACTURAS EMITIDAS</td>
								<td align="center"><span id="30003000"></span></td>
								<td align="center"><span id="30004000"></span></td>
								<td align="center"><span id="30003010"></span></td>
		            		</tr>
							<tr>
								<td align="left"># DE FACTURAS ACEPTADAS</td>
								<td align="center"><span id="30005000"></span></td>
								<td align="center"><span id="30006000"></span></td>
								<td align="center"><span id="30005010"></span></td>
		            		</tr>
							<tr>
								<td align="left"># DE FACTURAS RECHAZADAS</td>
								<td align="center"><span id="30007000"></span></td>
								<td align="center"><span id="30008000"></span></td>
								<td align="center"><span id="30007010"></span></td>
		            		</tr>
						</table>
		   			</div>
		   			<!-- FIN MARCO -->
			</div>
		</div>
	</div>
</body>
</html>