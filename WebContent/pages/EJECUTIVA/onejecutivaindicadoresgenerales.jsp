<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onejecutivaindicadoresgenerales.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container" >
				<form action="" name="indicadoresGenerales" id="indicadoresGenerales" >
		   			<h1>INDICADORES DE GESTIÓN DE LAS EJECUTIVAS</h1>
		   			<div class="marco">
		   				<h1 >Cantidad de ordenes de trabajo</h1>
		   				<table class="table_plana" id="table_indicadores">
		       				<tr>
								<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# de ordenes de trabajo abiertas asignadas - Cantidad de ordenes de trabajo que estan asignadas a la ejecutiva de marca y que no han sido cerradas por el cliente. se recuerda que la ejecutiva es asignada a la orden de trabajo cuando se elige el servicio técnico al que se envia."/> # ORDENES DE TRABAJO TOTAL ABIERTAS</td>
		           				<td align="center"><span id="20001000"></span></td>
		           				<td align="center">Verde claro</td>
		           				<td align="center">Verde oscuro</td>
		           				<td align="center">Amarillo</td>
		           				<td align="center">Rojo</td>
		       				</tr>
		       				<tr>
		       					<td class="tip"><img src="<%=request.getContextPath()%>/icons/warning.png" title="# ordenes de trabajo en servicio técnico - Cantidad de órdenes de trabajo, que están actualmente en servicio técnico y que están asignadas a la ejecutiva de marca, en primera columna, se muestra la cantidad total, en las siguientes esta cantidad se separar según el color del semáforo."> # ORDENES DE TRABAJO EN SERVICIO TÉCNICO</td>	           			
			           			<td align="center"><span id="20002000"></span></td>
			           			<td align="center"><span id="20003000"></span></td>
			           			<td align="center"><span id="20004000"></span></td>
			           			<td align="center"><span id="20005000"></span></td>
			           			<td align="center"><span id="20006000"></span></td>
			           		</tr>	
			           		<tr>
			           			<td colspan="6" height="10px"></td>
			           		</tr>           		
			           		<tr>
					            <td>EJECUTIVA DE MARCA</td>
					            <td align="center"># TOTAL DE O.T.<br>EN S. TÉCNICO</td>
					            <td align="center"></td>
					            <td align="center"></td>
					            <td align="center"></td>
					            <td align="center"></td>
		        			</tr>
		        			<tr>
					            <td>SIN EJECUTIVA</td>
					            <td align="center"><span id="20002010"></span></td>
					            <td align="center"><span id="20003010"></span></td>
					            <td align="center"><span id="20004010"></span></td>
					            <td align="center"><span id="20005010"></span></td>
					            <td align="center"><span id="20006010"></span></td>
					        </tr>
					        <tr id="listIndicadoresEjecutivas" style="display: none">
					            <td id="nombreEjecutiva"><a></a></td>
					            <td align="center"><span id="20002020"></span></td>
					            <td align="center"><span id="20003020"></span></td>
					            <td align="center"><span id="20004020"></span></td>
					            <td align="center"><span id="20005020"></span></td>
					            <td align="center"><span id="20006020"></span></td>
					        </tr>	
		   				</table>
		   			</div>   		
				</form>
			</div>
		</div>
	</div>
</body>
</html>