<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenufamiliasexcluidas.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>FAMILIAS DE PRODUCTOS EXCLUIDAS DE FALLAS DE FABRICACION</h1>
				<div class="marco">
					<h1>Excluir familias de productos</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana">
				   				<tr>
				   					<td width="50%">
				   						<fieldset>
				   							<label for="idLinea">Linea de Producto</label>
				   							<select name="idLinea" id="idLinea"></select>
				   						</fieldset>
				   					</td>
				   					<td  width="50%">
				   					</td>
				   				</tr>
							</table>
						</form>
						<div class="marco_interior_tabla">
							<div id="familias"></div>
						</div>
						<table class="table_plana">
			   				<tr>
			   					<td>
			   						<fieldset class="fieldset_botonera_center">
										<input type="button" name="grabar" id="grabar" value="Grabar">
			   						</fieldset>
			   					</td>
			   				</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>