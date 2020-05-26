<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenucolumnascolas.js?<%= version %>"></script>
</head>
	<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
			<h1 id="firstTitle">Configuración de Columnas</h1>
				<div class="marco">
					<h1 id="title">Columnas de indicadores de ejecutivas de colas</h1>
					<div class="marco_interior">
						<table class="table_plana" width="100%">
							<tr>
								<td style="width: 40%; vertical-align: middle !important;">
									<input style="width:98%" id="10005000" type="button" value="# OTs en sucursal inicio" class="btnIndicador">
									<input style="width:98%" id="10010000" type="button" value="# OTs en sucursal termino" class="btnIndicador">
									<input style="width:98%" id="10011000" type="button" value="Tiempo medio Suc inicio" class="btnIndicador">
									<input style="width:98%" id="10013000" type="button" value="Tiempo medio Suc termino" class="btnIndicador">
								</td>
								<td width="24%">
									<label for="columnasPosibles">COLUMNAS POSIBLES</label>
									<select multiple="multiple" size="15" id="columnasPosibles" name="columnasPosibles" style="width: 100%"></select>
								</td>
								<td width="12%" style="text-align: center; vertical-align: middle !important;">
									<input type="button" value="Agregar ->" id="agregar" name="agregar" style="margin:5px !important; width: 90%;">
									<input type="button" value="<- Quitar" id="quitar" name="quitar" style="margin:5px !important; width: 90%;">
									
								</td>
								<td width="24%">
									<label for="columnasTabla">COLUMNAS EN TABLA</label>
									<select multiple="multiple" size="15" id="columnasTabla" name="columnasTabla" style="width: 100%"></select>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<label>descripción</label>
								</td>
								<td>
									<input type="button" value="&uarr; Subir " style="float: left; margin: 0px ! important;" id="subirOpcion">
									<input type="button" value="&darr; Bajar " style="float: right; margin: 0px ! important;" id="bajarOpcion">
								</td>
								
							</tr>
							<tr>
								<td colspan="4">
									<label id="descripcion"></label>
								</td>
							</tr>
							<tr>
								<td></td>
								<td colspan="3" style="text-align: center;">
									<input type="button" id="grabar" name="grabar" value="Grabar Configuración" style="">
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