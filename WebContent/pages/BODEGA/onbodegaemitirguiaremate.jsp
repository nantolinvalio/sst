<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onbodegaemitirguiaremate.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>GUÍAS DE DESPACHO</h1>
				<div class="marco">
					<h1>Ordenes de trabajo autorizadas de cambio, listas para enviar a remate</h1>
					<div class="marco_interior_tabla">
						<div id="ordenesAutorizadasSinGuia"></div>
					</div>
				</div>
				<div class="marco">
					<h1>Guias de despacho</h1>
					<div class="marco_interior_tabla">
						<div id="guiasDespacho"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="popup" title="Guia de despacho a remate" style="padding: 0 !important">
			<div class="container">
<!-- 				<h1>Guia de despacho a remate</h1> -->
				
				<div class="marco">
					<h1>Ordenes de trabajo de la guia de despacho</h1>
					<div class="marco_interior">
						<div id="oTsGuiaDespacho"></div>
					</div>
				</div>
				<div class="marco">
					<h1>Guía de despacho</h1>
					<div class="marco_interior">
						<form id="imprimirGuiaDespachoRemate" name="imprimirGuiaDespachoRemate">
							<table class="table_plana" width="100%">
								<tr>
									<td colspan="2" width="100%">
										<fieldset>
											<input type="hidden" id="id" name="id">
											<label for="destino" style="width:22%">Destino</label>
											<label id="destino" style="width: 77%"></label>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td width="50%">
										<fieldset>	
											<label for="transportista.id">Transportista</label>
											<select id="transportista.id" name="transportista.id" class="required"></select>
											
											<label>N° guía despacho</label>
											<input type="text" id="numero" name="numero" class="required number" maxlength="16">
										</fieldset>
									</td>
									<td width="50%">
										<fieldset>
											<label for="transportista.patente">Patente</label>
											<input type="text" id="transportista.patente" name="transportista.patente" maxlength="8" class="required">
										</fieldset>
										<fieldset>
											<label for="fechaEmision">Fecha de envío</label>
											<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
										</fieldset>
									</td>
								</tr>
							</table>
							<table width="100%" class="table_plana">
								<tr>
									<td align="left" width="33%">
										<input type="button" id="cerrar" value="Cerrar">
									</td>
									<td align="center" width="33%">
										<input type="button" id="grabar" value="Grabar guía">
									</td>
									<td align="right" width="33%">
										<input type="button" id="imprimir" value="Imprimir">
									</td>
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