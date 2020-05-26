<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/MASTER/onmenugmasterbuscar.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head>
<body> 
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Crear orden de Trabajo</h1>
				<div class="marco">
					<h1>Buscar Documento</h1>
					<form id="buscarDocumento">
						<table class="table_plana" width="100%">
							<tr>
								<td width="40%">
									<fieldset>
										<label for="tipoDocumento">Tipo Documento</label>
										<select id="tipoDocumento" name="tipoDocumento">
											<option value="boleta">Boleta</option>
											<option value="factura">Factura</option>
										</select>
									</fieldset>
								</td>
								<td>
									<fieldset>
										<label for="idDocumento" style="text-align:center">Número</label>
										<input type="text" id="idDocumento" class="required number"/>
									</fieldset>
								</td>
								<td width="15%">
									<fieldset>
										<input type="button" id="limpiar" name="limpiar" value="Limpiar" style="float:right;">
									</fieldset>
								</td>
								<td width="15%">
									<fieldset>
										<input type="button" id="buscar" name="buscar" value="buscar">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- FIN FORMULARIO BUSCAR DOCUMENTO -->
				
				<div class="marco">
					<h1>Resultado</h1>
					<form id="resultado">
						<table class="table_plana">
							<tr>
								<td style="width: 33%">
									<fieldset>
										<label for="id" style="width: 50%"><span id="tipo">&nbsp;</span></label>
										<label id="id" style="width: 50%">&nbsp;</label>
										
										<label for="sucursal.id" style="width: 50%">Sucursal</label>
										<label id="sucursal.id" style="width: 50%">&nbsp;</label>
									</fieldset>
								</td>
								<td style="width: 33%">
									<fieldset>
										<label for="fechaEmision" style="width: 50%">Fecha de emisón</label>
										<label id="fechaEmision" format="dd/MM/yyyy" style="width: 50%">&nbsp;</label>
										
										<label for="telefono" style="width: 50%">Telefono</label>
										<label id="telefono" style="width: 50%">&nbsp;</label>
									</fieldset>
								</td>
								<td style="width: 33%">
									<fieldset>
										<label for="numeroGuia" style="width: 50%">N° Guía Despacho</label>
										<label id="numeroGuia" format="dd/MM/yyyy" style="width: 50%">&nbsp;</label>
										
										<label for="fechaEntrega" style="width: 50%">Fecha Entrega</label>
										<label id="fechaEntrega" format="dd/MM/yyyy" style="width: 50%">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
						<div class="marco_interior_tabla">
							<div id="productos"></div>
						</div>
					</form>
				</div>
			</div><!-- FIN CONTAINER -->
		</div>
	</div>
</body>
</html>