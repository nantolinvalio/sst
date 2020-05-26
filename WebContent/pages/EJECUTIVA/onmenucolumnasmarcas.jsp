<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenucolumnasmarcas.js?<%= version %>"></script>
</head>
	<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
			<h1 id="firstTitle">Configuración de Columnas</h1>
				<div class="marco">
					<h1 id="title">Columnas de indicadores de ejecutiva de marcas</h1>
					<div class="marco_interior">
						<table class="table_plana" width="100%">
							<tr>
								<td style="width: 40%; vertical-align: middle !important;">
									<input style="width:98%" id="20001000" type="button" value="# OT Abiertas" class="btnIndicador">
									<input style="width:98%" id="20002000" type="button" value="# OT S. Técnico" class="btnIndicador">
									<input style="width:98%" id="20007000" type="button" value="# OT Solucionadas CD" class="btnIndicador">
									<input style="width:98%" id="20009000" type="button" value="# OT Solucionadas Cliente" class="btnIndicador">
									<input style="width:98%" id="20011000" type="button" value="Nivel Servicio OT que pasaron por CD" class="btnIndicador">
									<input style="width:98%" id="20013000" type="button" value="Nivel Servicio OT que NO pasaron por CD" class="btnIndicador">
									<input style="width:98%" id="20015000" type="button" value="Nivel Servicio ácido por CD" class="btnIndicador">
									<input style="width:98%" id="20017000" type="button" value="Tiempo promedio en STL" class="btnIndicador">
									<input style="width:98%" id="20019000" type="button" value="Tiempo promedio OT solucionada" class="btnIndicador">
									<input style="width:98%" id="20021000" type="button" value="Tiempo promedio entrega Cliente" class="btnIndicador">
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