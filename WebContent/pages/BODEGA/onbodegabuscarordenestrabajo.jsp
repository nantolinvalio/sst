<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/onbodegabuscarordenestrabajo.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Buscar Orden de Trabajo</h1>
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
											<label for="idEtapa" >Etapa</label>
											<select id="idEtapa" name="idEtapa" >
												<option value="">[Seleccione]</option>
												<option value="1">Recibida de sucursal</option>
												<option value="2">Enviada a S. técnico</option>
												<option value="3">Recibida de S. técnico</option>
												<option value="4">Enviada a sucursal</option>
												<option value="5">Recibida de bodega</option>
												<option value="6">Enviada a bodega</option>
											</select>
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
				<div class="marco" id="buscador_avanzado" style="display: none;">
				<h1>Buscador Avanzado de Ordenes de Trabajo</h1>
					<div id="marco_interior">
						<form id="filtro_buscador_avanzado" name="filtro_buscador_avanzado">
							<table class="table_plana" width="100%">
								<tr>
									<td width="33%">
										<fieldset>
				   							<label for="tipoOT">TIPO OT</label>
				   							<select name="tipoOT" id="tipoOT"></select>
											<label for="estadoOT" >Estado O.T.</label>
											<select id="estadoOT" name="estadoOT"></select>
											<label for="idSTecnico" >S. Técnico</label>
											<select id="idSTecnico" name="idSTecnico"></select>
											<label for="sucursal" >Sucursal</label>
											<select id="sucursal" name="sucursal"></select>
											<label for="idFamilia" >Familia</label>
											<select id="idFamilia" name="idFamilia"></select>
											<label for="idMarca" >Marca</label>
											<select id="idMarca" name="idMarca"></select>
											<label for="numeroGuiaDespacho" >N° Guia</label>
											<input type="text" id="numeroGuiaDespacho" name="numeroGuiaDespacho" class="number" maxlength="9">
				   							<label for="idEjecutiva">EJ. MARCA</label>
				   							<select name="idEjecutiva" id="idEjecutiva"></select>
				   							<label for="vigente">Vigente</label>
				   							<select id="vigente" name="vigente">
												<option value="">[Seleccione]</option>
												<option value="true">Si</option>
												<option value="false">No</option>
											</select>
											<label for="almacenada">Almacenamiento</label>
											<select id="almacenada" name="almacenada">
												<option value="">[Seleccione]</option>
												<option value="true">almacenadas</option>
												<option value="false">No almacenadas</option>
											</select>
										</fieldset>
									</td>
									<td width="29%">
										<fieldset>
											<label for="idOT" >N° O.T.</label>
											<input type="text" maxlength="9" id="idOT" name="idOT" class="number">
											<label for="idProducto" >C. CORTO</label>
											<input type="text" maxlength="25" id="idProducto" name="idProducto" class="number" >
											<label for="numeroAtencion" >Número GM</label>
											<input type="text"  maxlength="13" id="numeroAtencion" name="numeroAtencion" class="number" >
											<label for="numeroContrato" > N° contrato</label>
											<input type="text"  maxlength="13" id="numeroContrato" name="numeroContrato" class="number" >
											<label for="numeroSerie" >N° Serie</label>
											<input type="text" maxlength="32" id="numeroSerie" name="numeroSerie" >
											<label for="rutCliente" >Rut Cliente</label>
											<input type="text" id="rutCliente" name="rutCliente" class="run" >
				   							<label for="cambioAutorizado">CAMBIO</label>
				   							<select name="cambioAutorizado" id="cambioAutorizado">
												<option value="">[Seleccione]</option>
												<option value="true">Autorizado</option>
												<option value="false">No Autorizado</option></select>
				   							<label for="semaforo">SEMAFORO</label>
				   							<select name="semaforo" id="semaforo"></select>
											<label for="tareaUrgente">T. Urgente</label>
											<select id="tareaUrgente" name="tareaUrgente">
												<option value="">[Seleccione]</option>
												<option value="true">Si</option>
												<option value="false">No</option>
											</select>
											<label for="idUbicacionInterna">Ubicación Interna</label>
											<select id="idUbicacionInterna" name="idUbicacionInterna"></select>
										</fieldset>
									</td>
									<td width="39%">
										<fieldset>
											<label for="fechaRecepcionOtInicio">RECEPCION DESDE</label>
											<input type="text" id="fechaRecepcionOtInicio" name="fechaRecepcionOtInicio" class="fecha dateDDMMYYYY"/>
											<label for="fechaRecepcionOtFin">RECEPCION HASTA</label>
											<input type="text" id="fechaRecepcionOtFin" name="fechaRecepcionOtFin" class="fecha dateDDMMYYYY"/>
				   							<label for="fechaInicio">CREACION DESDE</label>
				   							<input type="text" name="fechaInicio" id="fechaInicio" class="fecha dateDDMMYYYY">
				   							<label for="fechaTermino">CREACION HASTA</label>
				   							<input type="text" name="fechaTermino" id="fechaTermino" class="fecha dateDDMMYYYY">
											<label for="productoRemate"> para remate</label>
											<select id="productoRemate" name="productoRemate">
												<option value="">[Seleccione]</option>
												<option value="true">Si</option>
												<option value="false">No</option>
											</select>
											<label for="ccalidad">C. Calidad</label>
											<select id="ccalidad" name="ccalidad" ">
												<option value="">[Seleccione]</option>
												<option value="1">Aprobado</option>
												<option value="2">Rechazado</option>
												<option value="3">Excluido</option>
											</select>
											<label for="idEtapa">Etapa</label>
											<select id="idEtapa" name="idEtapa">
												<option value="">[Seleccione]</option>
												<option value="1">Recibida de sucursal</option>
												<option value="2">Enviada a S. técnico</option>
												<option value="3">Recibida de S. técnico</option>
												<option value="4">Enviada a sucursal</option>
												<option value="5">Recibida de bodega</option>
												<option value="6">Enviada a bodega</option>
											</select>
								            <label for="tipoUbicacion">Tipo ubicación</label>
											<select id="tipoUbicacion" name="tipoUbicacion" "></select>
											<select id="tipoDocumento" name="tipoDocumento" style="width: 37%">
												<option value="">Tipo Documento</option>
												<option value="boleta">Boleta</option>
												<option value="factura">Factura</option>
											</select>
											<input type="text" id="idDocumento" name="idDocumento" class="number" style="width:122px; margin-left:5px" placeholder="N° DOCUMENTO">
											<label for="numeroFolio" >N° Folio</label>
											<input type="text" id="numeroFolio" name="numeroFolio" maxlength="16">
											<!-- NUEVO CAMPO DE LPN -->
											<label for="codigoBarra">COD. BARRA/LPN</label>
											<input type="text" id="codigoBarra" name="codigoBarra" />
										</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<fieldset style="text-align: center">
											<input type="button" id="limpiar_avanzado" value="Limpiar">
											<input type="button" name="change_buscador_basico" id="change_buscador_basico" value="Buscador Básico">
											<input type="button" id="buscar_avanzado" value="Buscar">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco" id="contendorGrilla">
					<h1>Resultados (Doble click para ver el detalle de la OT)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
					<table class="table_datos">
			  			<tr>
			      			<td align="center">
								<label style="color:blue;">OTs AUTORIZADAS DE CAMBIO</label>
								<label style="color:red;">OTs CON TAREA URGENTE</label>
								<label style="color:gray;">OTs DESACTIVADAS</label>
								<label style="color:brown;">OTs CERRADAS POR CLIENTE</label>
								<label style="color:darkgreen;">OTs CERRADAS POR USUARIO</label>
			      			</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>