<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA_FALLA_FABRICACION/onsalaproveedoresrecibir.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Recibir Orden de Trabajo</h1>
				<div class="marco" id="etapas" style="display: none;">
					<h1>Etapa de la orden de trabajo</h1>
					<table class="table_plana" id="etapasOT">
					</table>
				</div>
				<div class="marco">
					<h1>Buscador Automático</h1>
					<div id="marco_interior">
						<form id="buscadorAutomatico">
							<table class="table_plana">
								<tr>
									<td>
										<fieldset>
											<label for="codigoBarra">Codigo de barras (Búsqueda con lector)</label>
											<input type="text" id="codigoBarra" name="codigoBarra" style="width: 140px;" class="number" maxlength="10">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div><!-- fin buscadorAutomatico -->
				<div class="marco">
					<h1>Buscador manual</h1>
					<div class="marco_interior">
						<form id="buscadorManual">
							<table class="table_plana" width="100%">
								<tr>
									<td width="35%">
										<fieldset>
											<label for="codigoBarra" style="width: 150px;">Código de barras</label>
											<input type="text" id="codigoBarra" name="codigoBarra" style="width: 140px;" class="number" maxlength="10">
										</fieldset>
										<fieldset>	
											<label for="numeroSerie" style="width: 150px;">N° Serie</label>
											<input type="text" id="numeroSerie" name="numeroSerie" style="width: 140px;" maxlength="32">
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="idOT" style="width: 315px;">N° orden de trabajo</label>
											<input type="text" id="idOT" name="idOT" style="width: 140px;" class="number" maxlength="9">
										</fieldset>
										<fieldset>
											<input type="button" value="Buscar" id="buscar" style="margin-left: 315px; width: 140px">
										</fieldset>
									</td>
							</table>
						</form>
					</div>
				</div><!-- fin buscadorManual -->
				<div class="marco">
					<h1>Información del despacho</h1>
					<div class="marco_interior">
						<div id="informacionDespacho"></div>
					</div>
				</div>
			</div>
			<div id="popup" title="Detalle Despacho" style="padding:0 !important">
				<div class="container">
					<div class="marco">
						<div id="informacionOrdentrabajo"></div>
						<input type="button" id="terminar" value="Terminar" style="margin-left: 90%;">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>