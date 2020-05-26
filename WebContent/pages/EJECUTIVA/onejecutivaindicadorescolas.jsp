<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onejecutivaindicadorescolas.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container" >
		   			<h1>INDICADORES DE GESTIÓN DE EJECUTIVA DE GESTIÓN</h1>
		   			
		   			<div class="marco">
		   				<h1 >Cantidad de ordenes de trabajo</h1>
		   				<table class="table_plana">
		       				<tr>
		           				<td colspan="2">&nbsp;</td>
		           				<td align="center">Verde claro</td>
		           				<td align="center">Verde oscuro</td>
		           				<td align="center">Amarillo</td>
		           				<td align="center">Rojo</td>
		       				</tr>
		       				<tr>
			           			<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# de ordenes de trabajo en sucursal inicio - Cantidad de órdenes de trabajo que están en el estado \'OT en sucursal, a la espera de ser enviada\', es decir, OT que son recibidas en tienda a la espera de ser enviada a servicio técnico local o centro de distribución. En la primera columna se muestra el total de dicha cantidad, después se separa según el color del semáforo." /># de ordenes de trabajo en sucursal inicio</td>
			           			<td align="center"><span id="10005000"></span></td>
			           			<td align="center"><span id="10001000"></span></td>
			           			<td align="center"><span id="10002000"></span></td>
			           			<td align="center"><span id="10003000"></span></td>
			           			<td align="center"><span id="10004000"></span></td>
		
			           		</tr>
			           		<tr>
			           			<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# de ordenes de trabajo en sucursal termino - Cantidad de órdenes de trabajo que han sido recibidas en la sucursal (aceptadas o con observaciones), sin autorización de cambio, y que no han sido entregadas al cliente. En la primera columna se muestra el total de dicha cantidad, después se separa según el color del semáforo." /># de ordenes de trabajo en sucursal termino</td>
			           			<td align="center"><span id="10010000"></span></td>
			           			<td align="center"><span id="10006000"></span></td>
			           			<td align="center"><span id="10007000"></span></td>
			           			<td align="center"><span id="10008000"></span></td>
			           			<td align="center"><span id="10009000"></span></td>
			           		</tr>
		   				</table>
		   			</div>
		   			<!-- FIN MARCO -->	
		   			
		   			<!-- FORMULARIO FECHAS -->
		   			<table class="table_datos">
		        		<tr>
		            		<td align="right">Seleccione la fecha de inicio y termino para calcular los indicadores en ese rango de fechas</td>
		        		</tr>
		        		<tr>
		        			<td align="right" id="calcular">
		        				<form action="" name="rangoFechas" id="rangoFechas">
			        				<input type="text" name="fechaInicio" id="fechaInicio" class="fecha required dateDDMMYYYY">
									<input type="text" name="fechaTermino" id="fechaTermino" class="fecha required dateDDMMYYYY">
									<input type="button" value="Calcular" id="calularIndicadoresColas" name="calularIndicadoresColas">
								</form>
							</td>
		        	</table>
		        	<!-- FIN FORMULARIO FECHAS -->
		
		        	<div class="marco">
		   				<h1>Tiempo promedio de estadia de las ordenes de trabajo</h1>
		   				
		   				<table class="table_plana">
							<tr>
								<td>&nbsp;</td>
								<td>Para los ultimos 7 días</td>
								<td>Para los ultimos 30 días</td>
								<td>Para selección</td>
							</tr>
							<tr>
								<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="Tiempo promedio en sucursal inicio - Tiempo promedio en días, de estancia de las órdenes de trabajo, que han salido de las sucursales en su etapa inicial, en la primera columna se calcula el promedio de las órdenes de trabajo que han salido en los últimos 7 días, los últimos 30 días y un rango de fechas que se selecciona en la parte superior. El tiempo estancia corresponde al tiempo desde que se crea la orden de trabajo hasta que se emite la guía de despacho." />Tiempo promedio en sucursal inicio</td>
								<td align="center"><span id="10011000"></span></td>
								<td align="center"><span id="10012000"></span></td>
								<td align="center"><span id="10012010"></span></td>
							</tr>
							<tr>
								<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="Tiempo promedio en sucursal termino - Tiempo promedio en días, de las órdenes de trabajo, que han sido entregadas al cliente, ya sea por cambio autorizado o producto reparado, el tiempo se cuenta desde que es recibida en las sucursales o la fecha en que se autoriza el cambio. En la primera columna, se calcula el promedio de las órdenes de trabajo que han sido cerradas en los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior." />Tiempo promedio en sucursal termino</td>
		            			<td align="center"><span id="10013000"></span></td>
								<td align="center"><span id="10014000"></span></td>
								<td align="center"><span id="10014010"></span></td>
		            		</tr>
						</table>
		   			</div>
		   			<!-- FIN MARCO -->
			</div>
		</div>
	</div>
</body>
</html>