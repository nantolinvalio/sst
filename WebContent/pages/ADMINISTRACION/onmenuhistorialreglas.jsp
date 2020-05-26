<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuhistorialreglas.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>HISTORIAL DE REGLAS COMERCIALES</h1>
				
				<div class="marco">
					<h1>Buscador Historial Reglas Comerciales</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador" id="filtro_buscador">
							<table class="table_plana" style="padding-bottom: 0px">
				   				<tr>
				   					<td width="35%">
				   						<fieldset>
				   							<label for="idRegla">Regla</label>
				   							<select name="idRegla" id="idRegla"></select>
				   							<label for="idUsuario">Usuario</label>
				   							<select name="idUsuario" id="idUsuario"></select>
				   							<label for="idEstado">Estado</label>
				   							<select name="idEstado" id="idEstado">
					   							<option value="">[SELECCIONE]</option>
				   								<option value="true">ACTIVA</option>
				   								<option value="false">INACTIVA</option>
				   							</select>
				   							<label for="vigente">Vigencia</label>
				   							<select name="vigente" id="vigente">
					   							<option value="">[SELECCIONE]</option>
				   								<option value="true">VIGENTE</option>
				   								<option value="false">NO VIGENTE</option>
				   							</select>
				   						</fieldset>
				   					</td>
				   					<td  width="35%">
				   						<fieldset>
					   						<label for="idTipoAutorizacion">Tipo Regla</label>
				   							<select name="idTipoAutorizacion" id="idTipoAutorizacion">
					   							<option value="">[SELECCIONE]</option>
				   								<option value="VA">VALOR</option>
				   								<option value="FR">FALLAS REITERADAS</option>
				   								<option value="FF">FALLAS DE FABRICACION</option>
				   								<option value="CP">AUTORIZADAS PROVEEDOR</option>
				   							</select>
				   							<label for="idZona">Zona</label>
				   							<select name="idZona" id="idZona"></select>
				   							<label for="idTienda">Tienda</label>
				   							<select name="idTienda" id="idTienda"></select>
			   							</fieldset>
				   					</td>
				   					<td  width="30%">
				   						<fieldset>
				   							<label for="idLinea">Linea</label>
				   							<select name="idLinea" id="idLinea"></select>
				   							<label for="idFamilia">Familia</label>
				   							<select name="idFamilia" id="idFamilia"></select>
				   							<label for="idProducto">Producto</label>
				   							<select name="idProducto" id="idProducto"></select>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
							<table class="table_plana" style="padding-top: 0px">
								<tr>
									<td width="40%">
										<fieldset>
				   							<label for="fechaInicioDesde"  style="width: 200px !important">Fecha inicio desde</label>
				   							<input type="text" name="fechaInicioDesde" id="fechaInicioDesde" class="fecha dateDDMMYYYY">
				   							<label for="fechaTerminoDesde"  style="width: 200px !important">Fecha término desde</label>
				   							<input type="text" name="fechaTerminoDesde" id="fechaTerminoDesde" class="fecha dateDDMMYYYY">
				   							<label for="fechaCreacionDesde"  style="width: 200px !important">Fecha modificación desde</label>
				   							<input type="text" name="fechaCreacionDesde" id="fechaCreacionDesde" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="40%">
										<fieldset>
				   							<label for="fechaInicioHasta" style="width: 200px !important">Fecha inicio hasta</label>
				   							<input type="text" name="fechaInicioHasta" id="fechaInicioHasta" class="fecha dateDDMMYYYY">
				   							<label for="fechaTerminoHasta"  style="width: 200px !important">Fecha término hasta</label>
				   							<input type="text" name="fechaTerminoHasta" id="fechaTerminoHasta" class="fecha dateDDMMYYYY">
				   							<label for="fechaCreacionHasta"  style="width: 200px !important">Fecha modificación hasta</label>
				   							<input type="text" name="fechaCreacionHasta" id="fechaCreacionHasta" class="fecha dateDDMMYYYY">
										</fieldset>
									</td>
									<td width="20%"></td>
								</tr>
								<tr>
									<td colspan="3">
				   						<fieldset class="fieldset_botonera_center">
											<input type="button" name="limpiar" id="limpiar" value="Limpiar">
											<input type="button" name="buscar" id="buscar" value="Buscar">
				   						</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				
				<div class="marco">
					<h1>Reglas Comerciales (Doble click para ver detalle)</h1>
					<div class="marco_interior_tabla">
						<div id="reglasComerciales"></div>
					</div>
				</div>
				
				<div id="detalleRegla" title="Regla Comercial" style="padding: 0 !important">
					<div class="container">
						<form action="" name="configuracion_general" id="configuracion_general">
							<div class="marco">
								<h1>Configuración general de regla comercial</h1>
								<div class="marco_interior">
									<table class="table_plana">
						   				<tr>
						   					<td colspan="3">
						   						<fieldset>
						   							<input type="hidden" id="id" name="id">
						   							<label for="nombre" style="width: 15% !important">NOMBRE</label>
						   							<input type="text" name="nombre" id="nombre" maxlength="128" style="width: 35% !important" disabled="disabled">
						   						</fieldset>
						   						<fieldset>
						   							<label for="descripcion" style="width: 15% !important">DESCRIPCIÓN</label>
						   							<textarea rows="1" cols="110" id="descripcion" name="descripcion" style="width: 84%" disabled="disabled"></textarea>
						   						</fieldset>
						   					</td>
						   				</tr>
						   				<tr>
						   					<td width="33%">
						   						<fieldset>
						   							<label for="fechaInicio" style="width: 46% !important">INICIO</label>
						   							<input type="text" name="fechaInicio" id="fechaInicio" format="dd/MM/yyyy" class="fecha dateDDMMYYYY" disabled="disabled">
						   						</fieldset>
						   					</td>
						   					<td width="28%">
						   						<fieldset>
						   							<label for="fechaTermino" >EXPIRACIÓN</label>
						   							<input type="text" name="fechaTermino" id="fechaTermino" format="dd/MM/yyyy" class="fecha dateDDMMYYYY" disabled="disabled">
						   						</fieldset>
						   					</td>
						   					<td width="39%">
						   						<fieldset>
						   							<label for="vigente" style="width: 20% !important" >ESTADO</label>
						   							<select name="vigente" id="vigente" class="required" disabled="disabled">
						   								<option value="true">ACTIVO</option>
						   								<option value="false">INACTIVO</option>
						   							</select>
						   						</fieldset>
						   					</td>
						   				</tr>
									</table>
								</div>
							</div>
							<div style="width: 100%; height: 100px">
								<div class="marco" style="width: 48%; float: left;">
									<h1>Configuraciones cambio automatico</h1>
									<div class="marco_interior">
										<table class="table_plana">
							   				<tr>
							   					<td>
							   						<fieldset>
							   							<label for="precioLimite">Precio límite</label>
							   							<input type="text" name="cambioAutomatico.precioLimite" id="cambioAutomatico.precioLimite" maxlength="8" class="number" disabled="disabled">
							   							<label for="numeroFallas">Número de fallas</label>
							   							<input type="text" name="cambioAutomatico.numeroFallas" id="cambioAutomatico.numeroFallas" maxlength="3" class="number" style="width: 10%; margin-right: 20px" disabled="disabled">
							   							<label for="diasTope" style="width: 20%; ">Periodo</label>
							   							<input type="text" name="cambioAutomatico.diasTope" id="cambioAutomatico.diasTope" maxlength="3" class="number" style="width: 10%" disabled="disabled">
							   						</fieldset>
							   					</td>
							   				</tr>
										</table>
									</div>
								</div>
								<div class="marco" style="width: 48%; float: right;">
									<h1>Configuraciones cambio por falla de fabricación</h1>
									<div class="marco_interior">
										<table class="table_plana">
							   				<tr>
							   					<td>
							   						<fieldset>
							   							<label for="precioLimite">Precio límite</label>
							   							<input type="text" name="fallaFabricacion.precioLimite" id="fallaFabricacion.precioLimite" maxlength="8" class="number" disabled="disabled">
							   							<label for="diasTope">Número de dias</label>
							   							<input type="text" name="fallaFabricacion.diasTope" id="fallaFabricacion.diasTope" maxlength="3" class="number" style="width: 10%;" disabled="disabled">
							   						</fieldset>
							   					</td>
							   				</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="marco">
						<h1>Configuración Certificación de Falla</h1>
						<div class="marco_interior">
							<table class="table_plana">
				   				<tr>
				   					<td>
				   						<fieldset>
					   						<form action="" id="cambioPorCertificacionFalla" name="cambioPorCertificacionFalla">
												<label for="certificacionFalla.inicio" style="width: 19% !important">PERIODO INICIO (DÍAS)</label>
					   							<input type="text" name="certificacionFalla.inicio" id="certificacionFalla.inicio" class="number" maxlength="3" style="width: 10% !important; margin-right: 210px" disabled="disabled">
					   							<label for="certificacionFalla.termino" style="width: 19% !important">PERIODO TERMINO (DÍAS)</label>
					   							<input type="text" name="certificacionFalla.termino" id="certificacionFalla.termino" class="number" maxlength="3"style="width: 10% !important" disabled="disabled">
					   						</form>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
						</div>
					</div>
							<div class="marco" >
								<h1>Configuraciones por autorización del prooveedor</h1>
								<div class="marco_interior">
									<table class="table_plana">
						   				<tr>
						   					<td>
						   						<fieldset>
						   							<label for="reglaCambioProoveedor.autorizadoProveedor" style="width: 54%">CONFIGURAR CAMBIOS DE PRODUCTOS AUTORIZADOS POR EL PROOVEDOR</label>
						   							<input type="checkbox" style="width: 5%" id="reglaCambioProoveedor.autorizadoProveedor" name="reglaCambioProoveedor.autorizadoProveedor" value="true">
						   							<label for="reglaCambioProoveedor.notaProoveedor" style="width: 100% !important">INGRESE EL MOTIVO POR EL CUAL EL PROVEEDOR AUTORIZA EL CAMBIO</label>
						   							<textarea rows="1" cols="110" id="reglaCambioProoveedor.notaProoveedor" name="reglaCambioProoveedor.notaProoveedor" style="width: 100%" readonly="readonly"></textarea>
						   						</fieldset>
						   					</td>
						   				</tr>
									</table>
								</div>
							</div>
						</form>
						<div style="width: 100%; height: 200px" id="confEspecificas">
							<div class="marco" style="width: 48%; float: left;">
								<h1>Zona y tienda donde se aplica la regla</h1>
								<div class="marco_interior_tabla">
									<div id="zonaTienda" style=" overflow-x: none; overflow-y: scroll; height: 150px; background-color: #F0F0F0 !important"></div>
								</div>
							</div>
							<div class="marco" style="width: 48%; float: right;">
								<h1>Línea, familia y producto donde se aplica la regla</h1>
								<div class="marco_interior_tabla">
									<div id="productoFamiliaLinea" style=" overflow-x: none; overflow-y: scroll; height: 150px; background-color: #F0F0F0 !important"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>