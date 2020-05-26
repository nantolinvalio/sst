<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursaltareaspendientes.js?<%= version %>"></script>
</head>
<body>	
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idUsuario" name="idUsuario" value='<%= request.getParameter("idUsuario") %>'>
			<div class="container" >
				<form action="" name="indicadores" id="indicadores">
					<h1>TAREAS PENDIENTES</h1>
					<div class="marco">
						<h1>Cantidad de ordenes de trabajo de...</h1>
	   					<table class="table_plana">
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO ABIERTAS DE LA SUCURSAL</td>
	           					<td style="width: 10%" align="right"><span id="40001000"></span></td>
	       					</tr>
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO PENDIENTES POR ACCESORIOS</td>
	           					<td style="width: 10%" align="right"><span id="40002000"></span></td>
	       					</tr>
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO PENDIENTES POR GUIA DE DESPACHO</td>
	           					<td style="width: 10%" align="right"><span id="40003000"></span></td>
	       					</tr>
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO PENDIENTES DE ENTREGA CLIENTE</td>
	           					<td style="width: 10%" align="right"><span id="40005000"></span></td>
	       					</tr>
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO ENVIADAS A SERVICIO TECNICO LOCAL Y SIN CONTRATO INGRESADO</td>
	           					<td style="width: 10%" align="right"><span id="40006000"></span></td>
	       					</tr>
	       					<tr>
	           					<td style="width: 90%"> # DE ORDENES DE TRABAJO CON AUTORIZACION DE CAMBIO</td>
	           					<td style="width: 10%" align="right"><span id="40007000"></span></td>
	       					</tr>
	   					</table>
	   				</div>
			    </form>
			</div>
		</div>
	</div>
</body>
</html>