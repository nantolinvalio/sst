<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalgarantiamaster.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id='idProducto' value='<%=request.getParameter("idProducto")%>'/>
			<input type="hidden" id='idDocumento' value='<%=request.getParameter("idDocumento")%>'/>
			<input type="hidden" id='tipoDocumento' value='<%=request.getParameter("tipoDocumento")%>'/>
			<div class="container">
				<h1>Crear orden de Trabajo</h1>
				
				<h2>Garantía Master</h2>
				<div class="marco">
					<h1>Etapas de creación de orden de trabajo</h1>
					<table class="table_plana">
						<tr id="etapasCreacionOT">
							<td class="td_etapa documento   disabled">Documento</td>
							<td class="td_etapa gProveedor  disabled">G. Proveedor</td>
							<td class="td_etapa gMaster     enabled">G. Master</td>
							<td class="td_etapa fallas      disabled">Fallas</td>
							<td class="td_etapa accesorios  disabled">Accesorios</td>
							<td class="td_etapa revision    disabled">Revision</td>
							<td class="td_etapa terminar    disabled">Terminar</td>
						</tr>
					</table>
				</div>
				
				<div class="marco">
					<h1>Datos del servicio</h1>
					<form id="datosServicio">
						<table class="table_plana">
							<tr>
								<td width="50%">
									<fieldset>
										<label for="id" style="width:100px;"><span id="tipo">&nbsp;</span></label>
										<label id="id" style="width:340px;">&nbsp;</label>
										
										<label for="sucursal" style="width:100px;">Sucursal</label>
										<label id="sucursal" style="width:340px;"><span id="sucursal.id" ></span>&nbsp;<span id="sucursal.glosa"></span></label>
										
										<label for="producto" style="width:100px;">Producto</label>
										<label id="produto" style="width:340px;"><span id="producto.id"></span>&nbsp;<span id="producto.descripcion"></span></label>
									</fieldset>
									
								</td>
								<td width="50%">
									<fieldset>
										<label for="fechaEmision">Fecha de emisón</label>
										<label id="fechaEmision" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="telefono">Telefono</label>
										<label id="telefono">&nbsp;</label>
										
										<label for="marca">Marca</label>
										<label id="marca">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- Fin datosServicio -->
				
				<div class="marco">
					<h1>Servicios técnicos locales</h1>
					<form id="serviciosTecnicosLocales">
						<table class="table_plana" width="100%">
							<tr>
								<td width="100%">
									<fieldset>
										<label for="numeroAtencion" style="width:170px;">N° de atención</label>
										<input type="text" id="numeroAtencion" name="numeroAtencion" class="required number" maxlength="13" style="width:150px;"/>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<fieldset>
										<input id="isCentroDistribucion" type="checkbox" name="isCentroDistribucion" style="width: 10px;">&nbsp;El servicio técnico es del centro de distribución
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
									<fieldset>
										<label for="idDestino" style="width:170px;">Enviar producto a</label>
										<select id="idDestino" name="idDestino" class="required"></select>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- fin serviciosTecnicosLocales -->
				<form style="margin-top:10px;">
					<table class="table_plana" width="100%">
						<tr>
							<td width="50%">
								<fieldset>
									<input type="button" value="Volver" id="volver">
								</fieldset>
							</td>
							<td width="50%">
								<fieldset >
									<input type="button" value="Crear orden de trabajo" id="crearOT" style="float:right;"/>
								</fieldset>
							</td>
						</tr>
					</table>
				</form>
				
			</div><!-- Fin container -->
		</div>
	</div>
</body>
</html>