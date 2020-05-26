<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenudescripcionfallas.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>DESCRIPCION DE FALLAS</h1>
				<div class="marco">
					<h1> Consultar descripciones de fallas </h1>
					<form action="" name="descripcionFallasForm" id="descripcionFallasForm">
						<div class="marco_interior_tabla">
							<table class="table_plana" width="100%">
								<tr>
									<td width="49%">
										<fieldset>
											<label for="idFamilia" >Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
										</fieldset>
									</td>
									
									<td width="49%">
										<fieldset>
											<label for="idProducto">Codigo Corto</label>
											<input type="text" id="idProducto" name="idProducto" class="number" maxlength="13" >
							        	</fieldset>
									</td>
								</tr>
								
								<tr>
									<td width="51%">
										<fieldset>
											<label for="fechaInicio">Fecha Inicio</label>
											<input type="text" id="fechaInicio" name="fechaInicio" class="fecha dateDDMMYYYY"/>
							        	</fieldset>
									</td>
									
									<td width="49%">
										<fieldset>
											<label for="fechaTermino">Fecha Termino</label>
											<input type="text" id="fechaTermino" name="fechaTermino" class="fecha dateDDMMYYYY"/>
							        	</fieldset>
									</td>	
								</tr>
								
								<tr>
									<td colspan="2">
			   							<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
			   							</fieldset>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
				
				<div class="marco" id="contendorGrillaFallas">
					<h1>Resultados</h1>
					<div class="marco_interior_tabla">
						<div id="resultadosDescripcionFallas"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>