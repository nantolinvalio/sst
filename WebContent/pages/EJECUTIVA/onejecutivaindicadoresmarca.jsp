<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/pages/EJECUTIVA/onejecutivaindicadoresmarca.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idEjecutiva" name="idEjecutiva" value='<%= request.getParameter("idEjecutiva") %>'>
			<div class="container" >
				<h1>INDICADORES DE GESTIÓN DE EJECUTIVA DE MARCA</h1>
				<div class="marco">
   					<h1>Cantidad de ordenes de trabajo de...</h1>
   					<table class="table_plana">
       					<tr>
       						<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# de ordenes de trabajo abiertas asignadas - Cantidad de ordenes de trabajo que estan asignadas a la ejecutiva de marca y que no han sido cerradas por el cliente. se recuerda que la ejecutiva es asignada a la orden de trabajo cuando se elige el servicio técnico al que se envia."/> # de ordenes de trabajo abiertas asignadas</td>
   	        				<td align="center"><span id="20001000"></span></td>
       	    				<td align="center">Verde claro</td>
           					<td align="center">Verde oscuro</td>
           					<td align="center">Amarillo</td>
           					<td align="center">Rojo</td>
       					</tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# ordenes de trabajo en servicio técnico - Cantidad de órdenes de trabajo, que están actualmente en servicio técnico y que están asignadas a la ejecutiva de marca, en primera columna, se muestra la cantidad total, en las siguientes esta cantidad se separar según el color del semáforo."> # ordenes de trabajo en servicio técnico</td>
           					<td align="center"><span id="20002000"></span></td>
           					<td align="center"><span id="20003000"></span></td>
           					<td align="center"><span id="20004000"></span></td>
           					<td align="center"><span id="20005000"></span></td>
           					<td align="center"><span id="20006000"></span></td>
       					</tr>
   					</table>
   				</div>
	   				
   				<table class="table_datos">
       				<tr>
           				<td align="right">Seleccione la fecha de inicio y termino para calcular los indicadores en ese rango de fechas</td>
       				</tr>
       				<tr>
           				<td align="right">
           					<form action="" name="rangoFechas" id="rangoFechas">
		        				<input type="text" name="fechaInicio" id="fechaInicio" class="fecha required dateDDMMYYYY">
								<input type="text" name="fechaTermino" id="fechaTermino" class="fecha required dateDDMMYYYY">
								<input type="button" value="Calcular" id="calularIndicadoresMarca" name="calularIndicadoresMarca">
							</form>
           				</td>
   					</tr>
   				</table>
	   				
				<div class="marco">
    				<h1>Nivel de servicio</h1>
    				<table class="table_plana">
       					<tr>
           					<td width="45%">&nbsp;</td>
           					<td colspan="2" align="center">Para los ultimos 7 días</td>
           					<td colspan="2" align="center">Para los ultimos 30 días</td>
           					<td colspan="2" align="center">Para selección</td>
       					</tr>
       					<tr>
           					<td>&nbsp;</td>
           					<td align="center">Ejecutiva</td>
           					<td align="center">Equipo</td>
           					<td align="center">Ejecutiva</td>
           					<td align="center">Equipo</td>
           					<td align="center">Ejecutiva</td>
           					<td align="center">Equipo</td>
       					</tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "# ordenes de trabajo solucionadas en C.D. - Una orden de trabajo solucionada en centro de distribución, se define como, aquella que es recibida en el centro de distribución reparada, o con autorización de cambio desde un servicio técnico del centro de distribución, esta cantidad se muestra para los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior, tanto para las ordenes de trabajo de la ejecutiva y del equipo."> # ordenes de trabajo solucionadas en C.D.</td>
           					<td align="center"><span id="20007000"></span></td>
           					<td align="center"><span id="20007010"></span></td>
           					<td align="center"><span id="20008000"></span></td>
           					<td align="center"><span id="20008010"></span></td>
           					<td align="center"><span id="20007020"></span></td>
           					<td align="center"><span id="20007030"></span></td>
   					    </tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "# ordenes de trabajo cerradas por cliente - Se establece que una orden de trabajo esta solucionada al cliente, cuando al cliente se le entrega el producto reparado o cambiado (cuando el cliente cierra la O.T.), en este indicador se toman en cuenta las ordenes de trabajo que han pasado por un servicio tecnico local."> # ordenes de trabajo cerradas por cliente</td>
           					<td align="center"><span id="20009000"></span></td>
           					<td align="center"><span id="20009010"></span></td>
           					<td align="center"><span id="20010000"></span></td>
           					<td align="center"><span id="20010010"></span></td>
           					<td align="center"><span id="20009020"></span></td>
           					<td align="center"><span id="20009030"></span></td>
       					</tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Nivel de servicio de O.T. que pasaron por C.D. - El nivel de servicio es igual a la cantidad de órdenes de trabajo solucionadas en un rango igual o menor a 14 días, dividido por la cantidad total de órdenes de trabajo solucionadas, esto para las ordenes de trabajo solucionadas en los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior, tanto para las ordenes de trabajo de la ejecutiva y del equipo. Una orden de trabajo solucionada en centro de distribución, se define como, aquella que es recibida en el centro de distribución reparada, o con autorización de cambio desde un servicio técnico del centro de distribución."> Nivel de servicio de O.T. que pasaron por C.D.</td>
           					<td align="center"><span id="20011000"></span></td>
           					<td align="center"><span id="20011010"></span></td>
           					<td align="center"><span id="20012000"></span></td>
           					<td align="center"><span id="20012010"></span></td>
           					<td align="center"><span id="20011020"></span></td>
           					<td align="center"><span id="20011030"></span></td>
       					</tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Nivel de servicio de O.T. que no pasaron por C.D. - El nivel de servicio es igual a la cantidad de órdenes de trabajo solucionadas en un rango igual o menor a 21 días, dividido por la cantidad total de órdenes de trabajo solucionadas, esto para las ordenes de trabajo solucionadas en los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior, tanto para las ordenes de trabajo de la ejecutiva y del equipo. Una orden de trabajo solucionada que no paso por el centro de distribución, se define como, aquella que es recibida en la sucursal reparada, o con autorización de cambio desde un servicio técnico local."> Nivel de servicio de O.T. que no pasaron por C.D.</td>
           					<td align="center"><span id="20013000"></span></td>
           					<td align="center"><span id="20013010"></span></td>
           					<td align="center"><span id="20014000"></span></td>
           					<td align="center"><span id="20014010"></span></td>
           					<td align="center"><span id="20013020"></span></td>
           					<td align="center"><span id="20013030"></span></td>
   					    </tr>
       					<tr>
           					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Nivel de servicio acido del C.D. - El nivel de servicio acido, es igual a la cantidad de órdenes de trabajo, que están listas para cierre en un rango igual o menor a 28 días, dividido por la cantidad de órdenes de trabajo, listas para cierre. Se entiende por orden de trabajo lista para cierre,  aquella que sale desde el centro de distribución o bodega regional en dirección a la tienda, el tiempo comienza a contarse desde que se crea la orden de trabajo en la tienda. Esto para las órdenes de trabajo que están listas para cierre en los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior, tanto para las ordenes de trabajo de la ejecutiva y del equipo."> Nivel de servicio acido del C.D.</td>
           					<td align="center"><span id="20015000"></span></td>
           					<td align="center"><span id="20015010"></span></td>
           					<td align="center"><span id="20016000"></span></td>
           					<td align="center"><span id="20016010"></span></td>
           					<td align="center"><span id="20015020"></span></td>
           					<td align="center"><span id="20015030"></span></td>
				        </tr>
   					</table>
    			</div>
		    			
    			<div class="marco">
	    			<h1>Nivel de servicio</h1>
	    			<table class="table_plana">
        				<tr>
            				<td width="45%">&nbsp;</td>
           					<td colspan="2" align="center">Para los ultimos 7 días</td>
           					<td colspan="2" align="center">Para los ultimos 30 días</td>
           					<td colspan="2" align="center">Para selección</td>
        				</tr>
        				<tr>
            				<td>&nbsp;</td>
            				<td align="center">Ejecutiva</td>
            				<td align="center">Equipo</td>
            				<td align="center">Ejecutiva</td>
            				<td align="center">Equipo</td>
            				<td align="center">Ejecutiva</td>
            				<td align="center">Equipo</td>
        				</tr>
        				<tr>
            				<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Tiempo promedio en servicio técnico local - Tiempo promedio en días de estadía de la orden de trabajo en servicio técnico local, para las ordenes de trabajo que han sido recibidas en sucursal en los últimos 7 días y 30 días, para las ordenes de trabajo asignadas a la ejecutiva y el equipo."> Tiempo promedio en servicio técnico local</td>
            				<td align="center"><span id="20017000"></span></td>
            				<td align="center"><span id="20017010"></span></td>
            				<td align="center"><span id="20018000"></span></td>
            				<td align="center"><span id="20018010"></span></td>
            				<td align="center"><span id="20017020"></span></td>
            				<td align="center"><span id="20017030"></span></td>
        				</tr>
        				<tr>
            				<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Tiempo promedio de cierre de orden de trabajo de C.D. - Tiempo promedio en días, para el cierre de la orden de trabajo del centro de distribución, el tiempo se contabiliza desde que se crea la orden de trabajo, hasta que se envía a la sucursal, esto para las ordenes de trabajo que se han enviado a sucursal en los últimos 7 días, 30 días y un rango de fechas que se selecciona en la parte superior, tanto para las ordenes de trabajo de la ejecutiva y del equipo."> Tiempo promedio de cierre de O.T. de C.D.</td>
            				<td align="center"><span id="20019000"></span></td>
            				<td align="center"><span id="20019010"></span></td>
            				<td align="center"><span id="20020000"></span></td>
            				<td align="center"><span id="20020010"></span></td>
            				<td align="center"><span id="20019020"></span></td>
            				<td align="center"><span id="20019030"></span></td>
				        </tr>
        				<tr>
            				<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title= "Tiempo promedio de orden de trabajo cerrada por cliente - Tiempo promedio en dias de la orden de trabajo desde que se crea hasta que se cierra por el cliente, cuando se le entrega el producto reparado o cambiado, esto para las ordenes de trabajo cerradas en los ultimos 7 y 30 dias, para las ordenes de trabajo asignadas a la ejecutiva y equipo."> Tiempo promedio de O.T. cerrada por cliente</td>
            				<td align="center"><span id="20021000"></span></td>
            				<td align="center"><span id="20021010"></span></td>
            				<td align="center"><span id="20022000"></span></td>
            				<td align="center"><span id="20022010"></span></td>
            				<td align="center"><span id="20021020"></span></td>
            				<td align="center"><span id="20021030"></span></td>
        				</tr>
    				</table>
				</div> 
			</div>
		</div>
	</div>
</body>
</html>