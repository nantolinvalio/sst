<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenustecnicoconfigurar.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idSTecnico" name="idSTecnico" value='<%=request.getParameter("idSTecnico")%>'>
			<div class="container">
			<h1>Productos del servicio tecnico</h1>
			<div class="marco">
				<h1 id="printServicioTecnico"></h1>
				<div class="marco_interior_tabla">
				      <div id="resultados"></div>
				</div>
			</div>
		</div>
		</div>
       
		<div id="mantenedorProductosST" title="Agregar productos al Servicio tecnico" >
			<div class="marco" >
				<h1>Buscador de Productos</h1>
				<div class="marco_interior_tabla"> 
					<form action="" id="mantenedorProductosSTForm" name="mantenedorProductosSTForm">
						<table class="table_plana" width="100%">
							<tr>
								<td width="50%">
									<fieldset class="fieldset_popup">
										<label for="familia">FAMILIA</label>
										<select id="familia" name="familia" ></select>
										<label for="idProducto">COD CORTO</label>
										<input type="text" name="idProducto" id="idProducto" maxlength="20" class="number">
										<label for="conServicioTecnico">PRODUCTOS</label>
										<select id="conServicioTecnico" name="conServicioTecnico" >
											<option value="">AMBAS OPCIONES</option>
											<option value="true">Con servicio técnico asignado</option>
											<option value="false">Sin servicio técnico asignado</option>
										</select>
			   						</fieldset>
								</td>
								<td width="50%">
									<fieldset class="fieldset_popup">
			   							<label for="marca">MARCA</label>
			   							<select id="marca" name="marca" ></select>
			   							<label for="idProveedor">PROVEEDOR</label>
			   							<select id="idProveedor" name="idProveedor" ></select>
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
				<h1>Resultados del servicio tecnico</h1>
				<div class="marco_interior_tabla">
					<div id="resultadosAsignar"></div>
				</div>
			</div>
		</div>
     
		<div id="modalAsignacionProductos" title="Productos que asignará al Servicio Técnico">
			<div id="detalleProductos"></div>
			
			 <div class="marco">
				<h1>Productos a los que se asignará el Servicio Técnico</h1>
				<div class="marco_interior_tabla"> 
					<table class="table_plana" style="width: 100%">
						<tr>
							<td style="width: 50%">
								<fieldset>
									<div id="detalleProductosLeft"></div>
								</fieldset>
							</td>
							<td style="width: 50%">
								<fieldset>
									<div id="detalleProductosRight"></div>
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