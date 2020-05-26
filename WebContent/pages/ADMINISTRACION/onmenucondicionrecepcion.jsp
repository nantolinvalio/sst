<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenucondicionrecepcion.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container" >
			<h1>Condiciones de recepción y envío de productos</h1>
				<div class="marco">
		  			<h1>Condición</h1>
		  			<form action="" name="condicion_text" id="condicion_text">
			   		<table class="table_plana">
						<tr>
			   				<td >
			   					<textarea rows="22" style="width: 100%" id="descripcion" name="descripcion" class="required"></textarea>	   							
			   				</td>
						</tr>		            	
						<tr>
				            <td>
				            	<fieldset class="fieldset_botonera_center">
			   						<input type="button" id="guardar" name="guardar" value="Grabar">
			   						<input type="button" id="imprimir" name="imprimir" value="Imprimir">
			   					</fieldset>	   							
			   				</td>
						</tr>
			     	</table>
		     	</form>
		  		</div>
			</div>
		</div>
	</div>
</body>
</html>