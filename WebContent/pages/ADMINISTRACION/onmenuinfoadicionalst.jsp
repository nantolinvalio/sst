<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuinfoadicionalst.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
			<h1>Procedimientos Garantías</h1>
				<div class="marco">
					<h1>Buscar Productos</h1>
					<div class="marco_interior">
						<form action="" name="buscadorProcedimientos" id="buscadorProcedimientos">
							<table class="table_plana" style="padding-bottom: 0px">
				   				<tr>
				   					<td width="33%">
				   						<fieldset>
				   							<label for="linea">Linea</label>
				   							<select name="linea" id="linea"></select>
				   							<label for="vigente">Estado</label>
				   							<select name="vigente" id="vigente">
				   								<option value="">[Seleccione]</option>
												<option value="true">Vigente</option>
												<option value="false">No Vigente</option>
				   							</select>
			   							</fieldset>
				   					</td>
				   					<td  width="33%">
				   						<fieldset>
				   							<label for="marca">Marca</label>
				   							<select name="marca" id="marca"></select>
			   							</fieldset>
				   					</td>
				   					<td  width="33%">
				   						<fieldset>
				   							<label for="producto">Código Corto</label>
				   							<input type="text" id="producto" name="producto" class="number" maxlength="13" >			   						
			   							</fieldset>
				   					</td>
				   				</tr>
				   				<tr>
				   					<td colspan="3">
				   						<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
										</fieldset>
				   					</td>
				   				</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Resultados (Doble Cick para editar)</h1>
					<div class="marco_interior_tabla">
						<div id="grillainformacionadicional"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>