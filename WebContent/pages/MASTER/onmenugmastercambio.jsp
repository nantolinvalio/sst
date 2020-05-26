<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/MASTER/onmenugmastercambio.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Autorizar cambio de producto</h1>
				<div class="marco">
					<h1>Buscar orden de trabajo</h1>
					<form id="buscarOT">
						<table class="table_plana">
							<tr>
								<td width="40%">
									<fieldset>
										<label for="idOT" width="45%">N° OT</label>
										<input type="text" id="idOT" name="idOT" width="45%" class="number"/>
									</fieldset>
								</td>
								<td>
									<fieldset width="40%">
										<label for="numeroAtencion" width="45%">N° de atención</label>
										<input type="text" id="numeroAtencion" name="numeroAtencion" width="45%" class="number"/>
									</fieldset>
								</td>
								<td width="20%">
									<fieldset class="fieldset_botonera_center">
										<input type="button" name="buscar" id="buscar" value="Buscar">
			   						</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- FIN FORMULARIO BUSCAR OT -->
				
				<div class="marco" id="marcoResultado">
					<h1>Datos de la orden de trabajo</h1>
					<form id="resultado">
						<table class="table_plana">
							<tr>
								<td width="49%">
									<fieldset>
										<label for="id">N° orden de trabajo</label>
										<label id="id" name="id" style="width: 50%">&nbsp;</label>
										
										<label for="numeroAtencion" >N° de atención</label>
										<label id="numeroAtencion" name="numeroAtencion" style="width: 50%">&nbsp;</label>
									
										<label for="fechaCreacion" >Fecha de creación</label>
										<label id="fechaCreacion" name="fechaCreacion" format="dd/MM/yyyy" style="width: 50%">&nbsp;</label>
										
										<label for="cliente.nombre" >Cliente</label>
										<label id="cliente.nombre" name="cliente.nombre" style="width: 50%">&nbsp;</label>
										
										<label for="cliente.telefono" >Telefono</label>
										<label id="cliente.telefono" name="cliente.telefono" style="width: 50%">&nbsp;</label>
										
										<label id="tipoDocumento" name="tipoDocumento" >&nbsp;</label>
										<label id="idDocumento" name="idDocumento" style="width: 50%">&nbsp;</label>
										
										<label for="producto">Producto</label>
										<label id="producto" style="width: 60%"><span id="producto.id" name="producto.id">&nbsp;</span> <span id="producto.descripcion">&nbsp;</span></label>
										
										<label for="numeroCambio">N° de autorización</label>
										<input type="text" id="numeroCambio" name="numeroCambio" class="number required" />
									</fieldset>
								</td>
								<td width="49%">
									<fieldset>
										<label for="sucursal.region.glosa" style="width: 30%">Región</label>
										<label id="sucursal.region.glosa" name="sucursal.region.glosa" style="width: 70%">&nbsp;</label>
										
										<label for="sucursal.comuna.glosa" style="width: 30%">Comuna</label>
										<label id="sucursal.comuna.glosa" name="sucursal.comuna.glosa" style="width: 70%">&nbsp;</label>
									
										<label for="sucursal" style="width: 30%">Sucursal</label>
										<label id="sucursal" style="width: 70%"><span id="sucursal.id">&nbsp;</span> <span id="sucursal.glosa">&nbsp;</span></label>
										
										<label for="cliente.rut" style="width: 30%">Rut</label>
										<label id="cliente.rut" name="cliente.rut" style="width: 70%">&nbsp;</label>
										
										<label for="cliente.celular" style="width: 30%">Celular</label>
										<label id="cliente.celular" name="cliente.celular" style="width: 70%">&nbsp;</label>
										
										<label for="logistico.nombreCompleto"style="width: 30%" >Logístico</label>
										<label id="logistico.nombreCompleto" style="width: 70%">&nbsp;</label>
										
										<label for="producto.marca.nombre" style="width: 30%">Marca</label>
										<label id="producto.marca.nombre" style="width: 70%">&nbsp;</label>
										
									</fieldset>
								</td>
							</tr>
							<tr>
								<fieldset>
									<td>
										<label id="textoAutorizacion" name="textoAutorizacion" style="color: red">La orden de trabajo ya fue autorizada de cambio</label> 
									</td>
								</fieldset>
							</tr>
							<tr>
								<td width="50%"></td>
								<td width="50%">
									<fieldset>
										<input type="button" id="autorizarCambio" value="Autorizar cambio" style="float:right; margin-right:20px;"/>
									</fieldset>
								</td>
							</tr>	
						</table>
					</form>
				</div>
			</div><!-- FIN CONTAINER -->
		</div>
	</div>
</body>
</html>