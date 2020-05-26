<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalbuscarordentrabajo.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>BUSCAR ORDEN DE TRABAJO</h1>
				<div class="marco" id="buscador_basico">
					<h1>Buscador Básico de Ordenes de Trabajo</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador_basico" id="filtro_buscador_basico">
							<table class="table_plana" width="100%">
								<tr>
									<td width="33%">
										<fieldset>
											<label for="idOT">N° OT</label> 
											<input type="text" name="idOT" id="idOT" class="number" maxlength="9">
										</fieldset> 
									</td>
									<td width="33%">
										<fieldset>
											<label for="numeroSerie">N° SERIE</label> 
											<input type="text" name="numeroSerie" id="numeroSerie" maxlength="32">
										</fieldset>
									</td>
									<td width="33%">
									 	<fieldset>
											<label for="rutCliente">RUN CLIENTE</label> 
											<input type="text" name="rutCliente" id="rutCliente" class="run">
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar_basico" id="limpiar_basico" value="Limpiar">
											<input type="button" name="change_buscador_avanzado" id="change_buscador_avanzado" value="Buscador Avanzado"> 
											<input type="button" name="buscar_basico" id="buscar_basico" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" id="buscador_avanzado" style="display: none">
					<h1>Buscador Avanzado de Ordenes de Trabajo</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana" width="100%">
								<tr>
									<td width="33%">
										<fieldset>
											<label for="idOT">N° OT</label> 
											<input type="text" name="idOT" id="idOT" class="number" maxlength="9"> 
											<label for="rutCliente">RUN CLIENTE</label> 
											<input type="text" name="rutCliente" id="rutCliente" class="run"> 
											<label for="idProducto">CÓDIGO CORTO</label> 
											<input type="text" name="idProducto" id="idProducto" class="number" maxlength="9">
											<label for="numeroFolio">NUMERO FOLIO</label> 
											<input type="text" name="numeroFolio" id="numeroFolio" class="number" maxlength="9"> 
											<label for="numeroGuiaDespacho">N°GUIA</label> 
											<input type="text" name="numeroGuiaDespacho" id="numeroGuiaDespacho" class="number" maxlength="9">
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="idSTecnico">S. TÉCNICO</label> 
											<select name="idSTecnico" id="idSTecnico"></select> 
											<label for="nombreCliente">CLIENTE</label> 
											<input type="text" name="nombreCliente" id="nombreCliente" maxlength="64">
											<label for="sucursal">SUCURSAL</label> 
											<select name="sucursal" id="sucursal"></select> 
											<label for="numeroSerie">N° SERIE</label> 
											<input type="text" name="numeroSerie" id="numeroSerie" maxlength="32">
											<label for="numeroFolio">N° FOLIO</label> 
											<input type="text" name="numeroFolio" id="numeroFolio" maxlength="16">
											<!-- NUEVO CAMPO DE LPN -->
											<label for="codigoBarra">C&Oacute;D. BARRA/LPN</label> 
											<input type="text" id="codigoBarra" name="codigoBarra" maxlength="18" class="alfanumerico" />
										</fieldset>
									</td>
									
									<td width="33%">
										<fieldset>
											<label for="fechaInicio">FECHA INICIO</label> 
											<input type="text" name="fechaInicio" id="fechaInicio" class="fecha dateDDMMYYYY"> 
											<label for="fechaTermino">FECHA TERMINO</label> 
											<input type="text" name="fechaTermino" id="fechaTermino" class="fecha dateDDMMYYYY"> 
		<!-- 									<label for="idDocumento">N° DOC</label>  -->
		<!-- 									<input type="text" name="idDocumento" id="idDocumento" class="number" maxlength="9">  -->
											<label for="numeroAtencion">N° ATENCIÓN GM</label> 
											<input type="text" name="numeroAtencion" id="numeroAtencion" class="number" maxlength="9"> 
												
											<label for="estadoOT">ESTADO OT</label> 
											<select id="estadoOT" name="estadoOT">
											<option value="">[SELECCIONE]</option>
												<option undefined="undefined" value="10001000">OT en preparación</option>
												<option undefined="undefined" value="10002000">OT pendiente por accesorios</option>
												<option undefined="undefined" value="10003000">OT en Suc. inicio</option>
												<option undefined="undefined" value="10004000">OT en transporte</option>
												<option undefined="undefined" value="10005000">OT en S.T.</option>
												<option undefined="undefined" value="10006000">OT en S.T. con contrato</option>
												<option undefined="undefined" value="10007000">OT en S.T. sin contrato</option>
												<option undefined="undefined" value="10008000">OT BT sin S.T.</option>
												<option undefined="undefined" value="10009000">OT en cliente</option>
												<option undefined="undefined" value="10010000">OT esperando en S.T.</option>
												<option undefined="undefined" value="10011000">OT en SUC recepcion aceptada</option>
												<option undefined="undefined" value="10012000">OT en SUC recepcion rechazada</option>
												<option undefined="undefined" value="10013000">OT en SUC recepcion con observacion</option>
												<option undefined="undefined" value="10014000">OT cerrada por cliente</option>
												<option undefined="undefined" value="10015000">OT cerrada por cliente (cambio)</option>
												<option undefined="undefined" value="10016000">OT en BR recepcion aceptada</option>
												<option undefined="undefined" value="10017000">OT en BR recepcion con observacion</option>
												<option undefined="undefined" value="10018000">OT en BR recepcion rechazada</option>
												<option undefined="undefined" value="10019000">OT BT con S.T.</option>
												<option undefined="undefined" value="10020000">OT BT visitada a domicilio</option>
												<option undefined="undefined" value="10022000">OT en CD con C.C. Aceptado</option>
												<option undefined="undefined" value="10023000">OT en CD C.C. Rechazado</option>
												<option undefined="undefined" value="10024000">OT en CD recepcion aceptada</option>
												<option undefined="undefined" value="10025000">OT en CD recepcion con observacion</option>
												<option undefined="undefined" value="10026000">OT en CD recepcion rechazada</option>
												<option undefined="undefined" value="10027000">OT en casa remate</option>
												<option undefined="undefined" value="10028000">OT en cliente con cambio</option>
												<option undefined="undefined" value="10029000">OT rechazada por cliente</option>
												<option undefined="undefined" value="10030000">OT en Sucursal</option>
												<option undefined="undefined" value="10031000">OT recibida por revisar FF</option>
												<option undefined="undefined" value="10032000">OT aceptada en FF</option>
												<option undefined="undefined" value="10033000">OT aceptada con observacion completa en FF</option>
												<option undefined="undefined" value="10034000">OT aceptada con observacion incompleta en FF</option>
												<option undefined="undefined" value="10035000">OT rechazada en FF</option>
												<option undefined="undefined" value="10036000">OT en cliente</option>
												<option undefined="undefined" value="10037000">OT en cliente</option>
												<option undefined="undefined" value="10040000">OT en cliente</option>
												<option undefined="undefined" value="10101000">OT en camión</option>
												<option undefined="undefined" value="10201000">OT en S. técnico</option>
												<option undefined="undefined" value="10301000">OT apta traslado FF</option>
											</select>
											<select id="tipoDocumento" name="tipoDocumento" style="width: 110px">
												<option value="">Tipo Documento</option>
												<option value="boleta">Boleta</option>
												<option value="factura">Factura</option>
											</select>
											<input type="text" id="idDocumento" name="idDocumento" class="number" style="width:122px; margin-left:5px" placeholder="N° DOCUMENTO">
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar_avanzado" id="limpiar_avanzado" value="Limpiar">
											<input type="button" name="change_buscador_basico" id="change_buscador_basico" value="Buscador Básico"> 
											<input type="button" name="buscar_avanzado" id="buscar_avanzado" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
					<h1>Resultados (Doble click para ver el detalle de la OT)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				<table class="table_datos">
					<tr>
						<td align="center"><label style="color: blue;">OTs
								AUTORIZADAS DE CAMBIO</label> <label style="color: red;">OTs CON
								TAREA URGENTE</label> <label style="color: gray;">OTs DESACTIVADAS</label>
							<label style="color: brown;">OTs CERRADAS POR CLIENTE</label> <label
							style="color: darkgreen;">OTs CERRADAS POR USUARIO</label></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>