<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/pages/EJECUTIVA/onejecutivanivelservicio.js?<%= version %>"></script>
</head>
<body>	
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Nivel de Servicio</h1>
				<div class="marco" >
					<h1>Filtro para Nivel de Servicio</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="49%">
										<fieldset>
											<label for="idProveedor">Proveedor</label>
											<select id="idProveedor" name="idProveedor"></select>
											<label for="fechaInicio">Fecha de Inicio</label>
											<input type="text" id="fechaInicio" name="fechaInicio" class="fecha dateDDMMYYYY required"/> 
										</fieldset> 
									</td>
									<td width="49%">
										<fieldset>
											<label for="idMarca">Marca</label>
											<select id="idMarca" name="idMarca"></select> 
											<label for="fechaTermino">Fecha de Termino</label>
											<input type="text" id="fechaTermino" name="fechaTermino" class="fecha dateDDMMYYYY required"/> 
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
						</form>
					</div>
				</div>
				<div class="marco" id="buscador_basico">
					<h1>Resultados para Nivel de Servicio</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana">
								<tr>
									<td>&nbsp;</td>
									<td>Para los ultimos 7 días</td>
									<td>Para los ultimos 30 días</td>
									<td>Para selección</td>
								</tr>
								<tr>
									<td class="tip">Nivel de servicio de O.T. que paso por C.D.</td>
									<td align="center"><span id="20011000"></span></td>
									<td align="center"><span id="20012000"></span></td>
									<td align="center"><span id="20012010"></span></td>
								</tr>
								<tr>
									<td class="tip">Nivel de servicio de O.T. que no paso por C.D.</td>
			            			<td align="center"><span id="20013000"></span></td>
									<td align="center"><span id="20014000"></span></td>
									<td align="center"><span id="20014010"></span></td>
			            		</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>