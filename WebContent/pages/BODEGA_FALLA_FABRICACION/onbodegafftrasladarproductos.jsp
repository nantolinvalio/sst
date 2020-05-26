<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../library.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onbodegafftrasladarproductos.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Traslado de productos con falla de fabricación</h1>
				<div class="marco">
					<h1>Traslado de productos con falla de fabricación</h1>
					<div id="marco_interior">
						<form id="filtro_buscador" name="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset>
											<label for="tipoUbicacion">Tipo ubicacion</label>
											<select id="tipoUbicacion" name="tipoUbicacion"></select>
											
											<label for="idDestino">Destino</label>
											<select id="idDestino" name="idDestino"></select>
											
											<label for="destinoFinal">Destino Proveedor</label>
											<select id="destinoFinal" name="destinoFinal"></select>
											
											<label for="idMarca">Marca </label>
											<select id="idMarca" name="idMarca"></select>
											
											<label for="fechaRecepcionOtInicio" >Fecha corte</label>
											<input type="text" id="fechaRecepcionOtInicio" name="fechaRecepcionOtInicio" class="fecha dateDDMMYYYY"/>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idOT" style="width: 170px">N° OT</label>
											<input type="text" maxlength="8" id="idOT" name="idOT" class="number" style="width: 260px">
											
											<label for="idLinea" style="width: 170px">Linea</label>
											<select id="idLinea" name="idLinea" ></select>
											
											
											<label for="idFamilia" style="width: 170px">Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
											
											<label for="codigoCorto" style="width: 170px">Código corto</label>
											<input type="text" maxlength="25" id="codigoCorto" name="codigoCorto" class="number" style="width: 260px">
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<fieldset style="text-align: center">
											<input type="button" id="limpiar" value="Limpiar">
											<input type="button" id="buscar" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" >
					<h1>Resultados </h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				<div class="marco_interior_tabla">
					<table>
						<tr>
							<td align="left" style="width: 800px">
								<!-- <fieldset>
									<input type="button" id="btnVolver" value="Volver">
								</fieldset> -->
							</td>
						</tr>
					</table>
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>