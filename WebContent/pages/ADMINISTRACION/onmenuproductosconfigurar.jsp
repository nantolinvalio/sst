<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuproductosconfigurar.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
		<input type="hidden" id="idProducto" name="idProducto" value='<%=request.getParameter("idProducto")%>'>
		<input type="hidden" id="idSTecnico" name="idSTecnico" value='<%=request.getParameter("idSTecnico")%>'>
<!-- 	    buscador se de servicios tecnicos modal ! -->

			<div class="container">
				<h1>Servicios tecnicos del producto</h1>
				<div class="marco">
					<h1 id="printProducto"></h1>
					<div class="marco_interior_tabla">
					      <div id="resultados"></div>
					</div>
				</div>
			</div>
		</div>

		<div id="mantenedorserviciostecproducto" title="Agregar Servicio tecnico al producto" >
			<div class="marco" >
				<h1>Buscador de Servicios Tecnicos</h1>
				<div class = "marco_interior"> 
					<form action="" name="filtro_buscador" id="filtro_buscador">
						<table class="table_plana" width="100%">
							<tr>
								<td width="49%">
									<fieldset class="fieldset_popup">
										<label for="nombre" style="width: 20%">Nombre</label>
										<input type="text" id="nombre" name="nombre" style="width: 70%">
									</fieldset>
								</td>
								<td width="49%">
									<fieldset class="fieldset_popup">
										<label for="rut" style="width: 20%">Rut</label>
										<input type="text" id="rut" name="rut" class="run" style="width: 50%">
									</fieldset>
								</td>
							</tr>
							<tr>
								<td colspan="2">
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
				<h1>Resultados</h1>
				<div class="marco_interior_tabla">
					<div id="resultadosAsignar"></div>
				</div>
			</div>
		</div>
<!-- 	    buscador se de servicios tecnicos -->	
	<div id="modalAsignacionServiciosTecnicos" title="Servicios Técnicos que se asignaran al producto">
		<div id="detalleServicioTecnico"></div>
			<div class="marco">
				<h1>Servicio Técnico que se asignaran al producto</h1>
				<div class="marco_interior_tabla"> 
					<table class="table_plana" style="width: 100%">
						<tr>
							<td style="width: 50%">
								<fieldset>
									<div id="detalleServicioTecnicoLeft"></div>
								</fieldset>
							</td>
							<td style="width: 50%">
								<fieldset>
									<div id="detalleServicioTecnicoRight"></div>
								</fieldset>
							</td>
						</tr>
					</table>	
				</div>
			</div>
			<div class="marco">
				<h1>¿Que tipo de Garantía Desea Asignar?</h1>
				<div class="marco_interior_tabla"> 
					<table class="table_plana" style="width: 100%">
						<tr>
							<td style="width: 50%">
								<fieldset>
									<input type="checkbox" value="GM" id="garantiaMaster">Garantia Master<br>
								</fieldset>
							</td>
							<td style="width: 50%">
								<fieldset>
									<input type="checkbox" value="GP" id="garantiaProveedor">Garantia Proveedor <br>
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