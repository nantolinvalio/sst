<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onbodegacontrolcalidad.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head>
<body> 
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>CONTROL DE CALIDAD</h1>			
				<div class="marco" id="etapas" style="display: none;">
					<h1>Etapa de la orden de trabajo</h1>
					<table class="table_plana" id="etapasOT">
					</table>
				</div>
				<div class="marco">
					<h1>Buscar orden de trabajo</h1>
					<form id="buscarDocumento">
						<table class="table_plana" width="100%">
							<tr>
								<td style="width: 40%">
									<fieldset>
										<label for="idOT" style="text-align:left; width: 43%">N° ORDEN DE TRABAJO</label>&nbsp;
										<input type="text" id="idOT" name="idOT" style="width: 45%" maxlength="9" class="number"/>
									</fieldset>
								</td>
								<td style="width: 40%">
									<fieldset>								
										<label for="numeroSerie" style="text-align:center">N° DE SERIE </label>
										<input type="text" id="numeroSerie" name="numeroSerie"  style="width: 35%" maxlength="32"/>								
									</fieldset>
								</td>
								<td style="width: 20%">
									<fieldset>								
										<input type="button" id="buscar" name="buscar" value="Buscar">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- FIN FORMULARIO BUSCAR DOCUMENTO -->
				<div id="tabs" name="tabs">
					<ul></ul>
				</div>
			</div><!-- FIN CONTAINER -->
		</div>
	</div>
</body>
</html>