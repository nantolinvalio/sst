<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/MASTER/onmenugmasterterminar.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idOT" value="<%=request.getParameter("idOT")%>">
			<div class="container">
				<h1>Crear orden de Trabajo</h1>
				<div class="marco">
					<h1>Orden de Trabajo</h1>
					<form id="ordenTrabajo" name="ordenTrabajo" class="formTab">
						<table class="table_plana" width="100%">
							<tr>
								<td width="50%">
									<fieldset class="twoCol">
										<label style="width: 34%" for="ordenTrabajo">N&deg; Orden de trabajo</label>
										<label style="width: 65%" id="ordenTrabajo"><span id="id">&nbsp;</span></label>
										<label style="width: 34%" for="numeroAtencion">N&deg; de Atención</label>
										<label style="width: 65%" id="numeroAtencion">&nbsp;</label>
										<label style="width: 34%" for="sTecnico">Servicio Técnico</label>
										<label style="width: 65%" id="sTecnico">&nbsp;</label>
										<label style="width: 34%" for="numeroCambio">N&deg; Cambio</label>
										<label style="width: 65%" id="numeroCambio">&nbsp;</label>
										<label style="width: 34%" for="datosProdcuto">Producto</label>
										<label style="width: 65%" id="datosProdcuto"><span id="producto.id">&nbsp;</span> <span id="producto.descripcion">&nbsp;</span></label>
		
										<label style="width: 34%" for="cliente.nombreCompleto">Cliente</label>
										<label style="width: 65%" id="cliente.nombreCompleto">&nbsp;</label>
										<label style="width: 34%" for="cliente.telefono">Teléfono</label>
										<label style="width: 65%" id="cliente.telefono">&nbsp;</label>
									</fieldset>
								</td>
								<td width="50%">
									<fieldset class="twoCol">
										<label style="width: 34%" for="sucursal">Sucursal</label>
										<label style="width: 65%" id="sucursal" ><span id="sucursal.id">&nbsp;</span> <span id="sucursal.glosa">&nbsp;</span></label>
										<label style="width: 34%" for="sucursal.comuna.glosa">Comuna</label>
										<label style="width: 65%" id="sucursal.comuna.glosa">&nbsp;</label>
										<label style="width: 34%" for="sucursal.region.glosa">Región</label>
										<label style="width: 65%" id="sucursal.region.glosa">&nbsp;</label>
										<label style="width: 34%" for="sucursal.telefono">Telefono sucursal</label>
										<label style="width: 65%" id="sucursal.telefono" >&nbsp;</label>
										<label style="width: 34%" for="producto.marca.nombre">Marca</label>
										<label style="width: 65%" id="producto.marca.nombre">&nbsp;</label>
										
										<label style="width: 34%" for="cliente.rut">Rut</label>
										<label style="width: 65%" id="cliente.rut">&nbsp;</label>
										<label style="width: 34%" for="cliente.celular">Celular</label>
										<label style="width: 65%" id="cliente.celular">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
				
				<div class="marco" id="divAccesorios">
					<h1>Accesorios</h1>
					<div class="marco_interior_tabla">
						<div id="accesoriosOT"></div>
					</div>
				</div>
				
				<div class="marco">
					<h1>Observaciones de la orden de trabajo</h1>
					<table class="table_plana" width="100%">
						<tr>
							<td>
								<fieldset class="twoCol">
									<textarea id="observaciones" name="observaciones" rows="3" style="width: 100%"></textarea>	
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
		   						<fieldset class="fieldset_botonera_center">
									<input type="button" name="grabar" id="grabar" value="Grabar">
									<input type="button" name="imprimir" id="imprimir" value="Imprimir Orden de Trabajo">
									<input type="button" name="cancelar" id="cancelar" value="Cancelar">
		   						</fieldset>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>