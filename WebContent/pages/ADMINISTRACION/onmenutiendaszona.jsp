<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenutiendaszona.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>TIENDAS POR ZONA</h1>
				<div class="marco">
					<h1>Asociar tiendas a Zonas</h1>
					<table class="table_plana">
						<tr>
							<td style="padding-left:20px;">
								<fieldset style="width: 50%">
									<label for="idZona" style="width: 15% !important">Zona</label>
									<select id="idZona" name="idZona" style="width: 85% !important"></select>
								</fieldset>
							</td>
						</tr>
					</table>
					<table class="table_plana selectMultiple">
						<tr>
							<td width="40%">
								<label for="sucursalesOtrasZona">TIENDAS ASOCIADAS A OTRAS ZONAS</label>
								<select multiple="multiple" size="10" id="sucursalesOtrasZona" name="sucursalesOtrasZona"></select>
							</td>
							<td width="20%" class="buttons">
								<input type="button" value="Agregar ->" id="agregar" name="agregar">
								<input type="button" value="<- Quitar" id="quitar" name="quitar">
							</td>
							<td width="40%">
								<label for="sucursalesZona">TIENDAS ASOCIADAS A ZONA</label>
								<select multiple="multiple" size="10" id="sucursalesZona" name="sucursalesZona"></select>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: right;">
								<input type="button" id="grabar" name="grabar" value="Grabar" style="width: 100px">
							</td>
						</tr>
					</table>
				</div>
				<div class="marco">
					<h1>Buscar Tiendas por Zona</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana">
								<tr>
									<td style="padding-left:20px;">
										<fieldset style="width: 50%">
											<label for="idUbicacion" style="width: 15% !important">Tienda</label>
											<select id="idUbicacion" name="idUbicacion" style="width: 85% !important"></select>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td style="padding-left:20px;">
										<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
				   						</fieldset>
									</td>
								</tr>
							</table>
						</form>
						<div id="tiendas"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>