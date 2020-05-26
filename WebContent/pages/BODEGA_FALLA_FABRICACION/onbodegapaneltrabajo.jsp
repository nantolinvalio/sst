<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %> 
	<script type="text/javascript" src="<%= request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onbodegapaneltrabajo.js?<%= version %>"></script> 
</head>
<body>	
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idUsuario" name="idUsuario" value='<%= request.getParameter("idUsuario") %>'>
			<div class="container" >
				<h1>PANEL DE TRABAJO</h1>
				<div class="marco">
   					<h1>Cantidad de ordenes de trabajo</h1>
   					<form id="indicador" action="">
   						<table class="table_plana">
       					<tr>
       						<td></td>
       						<td align="center" >Total</td>
       	    				<td align="center" style="width: 110px;" class="indicador verde_claro">Verde claro</td>
           					<td align="center" style="width: 110px;" class="indicador verde_oscuro">Verde oscuro</td>
           					<td align="center" style="width: 90px;" class="indicador amarillo">Amarillo</td>
           					<td align="center" style="width: 60px;" class="indicador rojo">Rojo</td>
       					</tr>
       					<tr>
           					<td>N° de ordenes no despachados desde tienda</td>
							<td align="center"><span id="50001000"></span></td>
           					<td align="center"><span id="50002000" ></span></td>
           					<td align="center"><span id="50003000" ></span></td>
           					<td align="center"><span id="50004000" ></span></td>
           					<td align="center"><span id="50005000" ></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes no recibidas en bodega</td>
           					<td align="center"><span id="50006000"></span></td>
           					<td align="center"><span id="50007000"></span></td>
           					<td align="center"><span id="50008000"></span></td>
           					<td align="center"><span id="50009000"></span></td>
        					<td align="center"><span id="60001000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de guias emitidas a transportistas</td>
           					<td align="center"><span id="60002000"></span></td>
           					<td align="center"><span id="60003000"></span></td>
           					<td align="center"><span id="60004000"></span></td>
           					<td align="center"><span id="60005000"></span></td>
        					<td align="center"><span id="60006000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes con recepcion faltante</td>
           					<td align="center"><span id="60007000"></span></td>
           					<td align="center"><span id="60008000"></span></td>
           					<td align="center"><span id="60009000"></span></td>
           					<td align="center"><span id="70001000"></span></td>
        					<td align="center"><span id="70002000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes con recepcion incompleta</td>
           					<td align="center"><span id="70003000"></span></td>
           					<td align="center"><span id="70004000"></span></td>
           					<td align="center"><span id="70005000"></span></td>
           					<td align="center"><span id="70006000"></span></td>
        					<td align="center"><span id="70007000"></span></td>
       					</tr>
       					<tr>
           					<td> N° de ordenes no enviada a revisión en SP</td>
           					<td align="center"><span id="70008000"></span></td>
           					<td align="center"><span id="70009000"></span></td>
           					<td align="center"><span id="80001000"></span></td>
           					<td align="center"><span id="80002000"></span></td>
        					<td align="center"><span id="80003000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes enviada y no recibidas en SP</td>
           					<td align="center"><span id="80004000"></span></td>
           					<td align="center"><span id="80005000"></span></td>
           					<td align="center"><span id="80006000"></span></td>
           					<td align="center"><span id="80007000"></span></td>
        					<td align="center"><span id="80008000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes clasificación cp no despachadas sp</td>
           					<td align="center"><span id="80009000"></span></td>
           					<td align="center"><span id="90001000"></span></td>
           					<td align="center"><span id="90002000"></span></td>
           					<td align="center"><span id="90003000"></span></td>
        					<td align="center"><span id="90004000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes clasificación rp no despachadas sp</td>
           					<td align="center"><span id="90005000"></span></td>
           					<td align="center"><span id="90006000"></span></td>
           					<td align="center"><span id="90007000"></span></td>
           					<td align="center"><span id="90008000"></span></td>
        					<td align="center"><span id="90009000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes clasificación da no despachadas sp</td>
           					<td align="center"><span id="100001000"></span></td>
           					<td align="center"><span id="100002000"></span></td>
           					<td align="center"><span id="100003000"></span></td>
           					<td align="center"><span id="100004000"></span></td>
        					<td align="center"><span id="100005000"></span></td>
       					</tr>
       					<tr>
           					<td>N° de ordenes clasificación av no despachadas sp</td>
           					<td align="center"><span id="100006000"></span></td>
           					<td align="center"><span id="100007000"></span></td>
           					<td align="center"><span id="100008000"></span></td>
           					<td align="center"><span id="100009000"></span></td>
        					<td align="center"><span id="100010000"></span></td>
       					</tr>
   					</table>
   					</form>
   				</div>
			</div>
		</div>
	</div>
</body>
</html>