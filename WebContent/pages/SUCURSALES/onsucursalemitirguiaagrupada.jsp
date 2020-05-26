<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalemitirguiaagrupada.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idGuia" name="idGuia" value='<%=request.getParameter("idGuia")%>'>
			<div class="container">
				<h1>GUÍA PARA FALLA DE FABRICACIÓN</h1>
				<div id="tabs" >
					<ul>
						<li id="onmenusucursalguiadespachoTab" 		><a href="#onmenusucursalguiadespachoPanel">Guía de despacho</a></li>
						<li id="onmenuordentrabajogeneralTab" 		><a href="#onmenuordentrabajogeneralPanel">Ordenes de Trabajo</a></li>
					</ul>
					<div id="onmenusucursalguiadespachoPanel" >
						<form id="guia" name="guia" class="formTab">
							<table class="table_plana" width="100%">
								<tr>
									<td width="50%">
										<fieldset class="">
											<label for="id">N° Guía despacho</label>
											<input id="numero" name="numero" type="text" maxlength="10" class="required number">
											
											<label for="fechaEmision">Fecha de Emision</label>
											<input type="text" name="fechaEmision" id="fechaEmision" format="dd/MM/yyyy HH:mm" class="fechaHora dateTimeDDMMYYYYhhmm required" />
											
											<label for="transportista.id">Transportista</label>
											<select id="transportista.id" name="transportista.id" class="required"></select>
										</fieldset>
									</td>
									<td width="50%">
										<fieldset class="">
											<label for="destino.id">Destino</label>
											<select id="destino.id" name="destino.id" class="required"></select>
											
											<label for="transportista.patente">Patente</label>
											<input id="transportista.patente" name="transportista.patente" type="text" maxlength="8" class="required">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
						<table class="table_plana" width="100%">
				   			<tr>
				   				<td width="100%">
				   					<fieldset class="fieldset_botonera_center">
				   						<input type="button" name="grabar" id="grabar" value="Grabar Guía" />
				   						<input type="button" name="imprimir" id="imprimir" value="Imprimir Guía" />
				   						<input type="button" name="reemitir" id="reemitir" value="Re emitir Guía" />
				   						<input type="button" name="confirmar" id="confirmar" value="Confirmar Emisión" />
				   					</fieldset>
				   				</td>
				   			</tr>
						</table>
					</div>
					<div id="onmenuordentrabajogeneralPanel">
						<form class="formTab">
							<div class="marco_interior_tabla">
								<div id="ots"></div>
							</div>
						</form>
					</div><!-- fin tabOrdenTrabajo -->
				</div><!-- Fin tabs -->
				<div>
					<input type="button" id="volver" name="volver" value="Volver" style="margin-top:10px">
				</div>
			</div>
		</div>
	</div>
</body>
</html>