   <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenureglascomerciales.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>REGLAS COMERCIALES</h1>
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
				   							<input type="text" name="nombre" id="nombre" maxlength="128" class="required" style="width: 35% !important">
				   						</fieldset>
				   						<fieldset>
				   							<label for="descripcion" style="width: 15% !important">DESCRIPCIÓN</label>
				   							<textarea rows="1" cols="110" id="descripcion" name="descripcion" class="required" style="width: 84%"></textarea>
				   						</fieldset>
				   					</td>
				   				</tr>
				   				<tr>
				   					<td width="33%">
				   						<fieldset>
				   							<label for="fechaInicio" style="width: 46% !important">INICIO</label>
				   							<input type="text" name="fechaInicio" id="fechaInicio" format="dd/MM/yyyy" class="fecha required dateDDMMYYYY">
				   						</fieldset>
				   					</td>
				   					<td width="28%">
				   						<fieldset>
				   							<label for="fechaTermino" >EXPIRACIÓN</label>
				   							<input type="text" name="fechaTermino" id="fechaTermino" format="dd/MM/yyyy" class="fecha dateDDMMYYYY">
				   						</fieldset>
				   					</td>
				   					<td width="39%">
				   						<fieldset>
				   							<label for="vigente" style="width: 20% !important">ESTADO</label>
				   							<select name="vigente" id="vigente" class="required">
				   								<option value="true">ACTIVO</option>
				   								<option value="false">INACTIVO</option>
				   							</select>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
						</div>
					</div>
				</form>
				<div style="width: 100%; height: 112px">
					<div class="marco" style="width: 48%; float: left; height: 100px;">
						<h1>Configuraciones cambio automatico</h1>
						<div class="marco_interior">
							<table class="table_plana">
				   				<tr>
				   					<td>
				   						<fieldset>
				   							<form action="" name="cambioPorValor" id="cambioPorValor">
					   							<label for="precioLimite">PRECIO LÍMITE</label>
												<input type="text" name="cambioAutomatico.precioLimite" id="cambioAutomatico.precioLimite" maxlength="8" class="number">
				   							</form>
				   							<form action="" name="cambioPorFallasReiteradas" id="cambioPorFallasReiteradas">
					   							<label for="numeroFallas">NÚMERO DE FALLAS</label>
					   							<input type="text" name="cambioAutomatico.numeroFallas" id="cambioAutomatico.numeroFallas" maxlength="3" class="number" style="width: 10%; margin-right: 20px">
					   							<label for="diasTope" style="width: 20%; ">PERIODO</label>
					   							<input type="text" name="cambioAutomatico.diasTope" id="cambioAutomatico.diasTope" maxlength="3" class="number" style="width: 10%">
				   							</form>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
						</div>
					</div>
					<div class="marco" style="width: 48%; float: right; height: 100px;">
						<h1>Configuraciones por fallas de fabricación</h1>
						<div class="marco_interior">
							<table class="table_plana">
				   				<tr>
				   					<td>
				   						<fieldset>
					   						<form action="" name="cambioPorFallasFabricacion" id="cambioPorFallasFabricacion">
					   							<label for="precioLimite">Precio límite</label>
					   							<input type="text" name="fallaFabricacion.precioLimite" id="fallaFabricacion.precioLimite" maxlength="8" class="number">
					   							<label for="diasTope">Número de dias</label>
					   							<input type="text" name="fallaFabricacion.diasTope" id="fallaFabricacion.diasTope" maxlength="3" class="number" style="width: 10%;">
				   							</form>
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
					   							<input type="text" name="certificacionFalla.inicio" id="certificacionFalla.inicio" class="required number" maxlength="3" style="width: 10% !important; margin-right: 210px">
					   							<label for="certificacionFalla.termino" style="width: 19% !important">PERIODO TERMINO (DÍAS)</label>
					   							<input type="text" name="certificacionFalla.termino" id="certificacionFalla.termino" class="required number" maxlength="3"style="width: 10% !important">
					   						</form>
				   						</fieldset>
				   					</td>
				   				</tr>
							</table>
						</div>
					</div>
				<div class="marco" >
					<h1>Configuraciones por autorización del proveedor</h1>
					<div class="marco_interior">
						<table class="table_plana">
			   				<tr>
			   					<td>
			   						<fieldset>
				   						<form action="" name="cambioPorAutorizacionDeProoveedor" id="cambioPorAutorizacionDeProoveedor">
				   							<label for="reglaCambioProoveedor.autorizadoProveedor" style="width: 54%">CONFIGURAR CAMBIOS DE PRODUCTOS AUTORIZADOS POR EL PROOVEDOR</label>
				   							<input type="checkbox" style="width: 5%" id="reglaCambioProoveedor.autorizadoProveedor" name="reglaCambioProoveedor.autorizadoProveedor" value="true">
				   							<label for="reglaCambioProoveedor.notaProoveedor" style="width: 100% !important">INGRESE EL MOTIVO POR EL CUAL EL PROVEEDOR AUTORIZA EL CAMBIO</label>
				   							<textarea rows="1" cols="110" id="reglaCambioProoveedor.notaProoveedor" name="reglaCambioProoveedor.notaProoveedor" style="width: 100%" readonly="readonly"></textarea>
			   							</form>
			   						</fieldset>
			   					</td>
			   				</tr>
						</table>
					</div>
				</div>
				<div style="width: 100%; height: 200px" id="confEspecificas">
					<div class="marco" style="width: 48%; float: left;">
						<h1><input type="checkbox" id="chkZona" name="chkZona"> Zona y tienda donde se aplica la regla <br> (En caso de no seleccionar ningún nodo la regla se será aplicada a todas las tiendas)</h1>
						<div class="marco_interior_tabla">
							<div id="zonaTienda" style=" overflow-x: none; overflow-y: scroll; height: 150px"></div>
						</div>
					</div>
					<div class="marco" style="width: 48%; float: right;">
						<h1><input type="checkbox" id="chkLinea" name="chkLinea"> Línea, familia y producto donde se aplica la regla <br> (En caso de no seleccionar ningún nodo la regla será aplicada a todos los productos)</h1>
						<div class="marco_interior_tabla">
							<div id="productoFamiliaLinea" style=" overflow-x: none; overflow-y: scroll; height: 150px"></div>
						</div>
					</div>
				</div>
				<table class="table_plana" style="width: 100%">
	   				<tr>
	   					<td>
	   						<fieldset class="fieldset_botonera_center">
								<input type="button" name="limpiar" id="limpiar" value="Limpiar">
								<input type="button" name="grabar" id="grabar" value="Grabar">
	   						</fieldset>
	   					</td>
	   				</tr>
				</table>
				<div class="marco">
					<h1>Reglas Comerciales (Doble click para editar)</h1>
					<div class="marco_interior_tabla">
						<div id="reglasComerciales"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>