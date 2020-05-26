<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenuubicacionesconfigurar.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Configurar Destinos</h1>
				<input type="hidden" id="idUbicacion" name="idUbicacion" value='<%=request.getParameter("idUbicacion")%>'>
				<div class="marco">
					<h1 id="printUbicacion"></h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
		 		
		 		<div id="mantenedorStecnico" title="Agregar S.tecnico" >
					<form action="" id="mantenedorStecnicoForm" name="mantenedorStecnicoForm">
						<div class="marco" >
							<h1>Información a asignar</h1>
							<div class="marco_interior" >
								<table class="table_plana">
			                       <tr>
										<td width="50%">
											<fieldset class="fieldset_popup">
										    	<label for="nombre">NOMBRE</label>
										    	<input type="text" name="nombre" id="nombre" maxlength="50" class="text" >
										    	<label for="direccion">RUT</label>
										    	<input type="text" name="rut" id="rut" maxlength="50" class="run">
					 						</fieldset>
										</td>
										<td width="50%">
											<fieldset class="fieldset_popup">
					 							<label for="idRegion">REGION</label>
					 							<select id="idRegion" name="idRegion"></select>
					 							<label for="idComuna">COMUNA</label>
					 							<select id="idComuna" name="idComuna" ></select>
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
							</div>
						  </div>
						<div class="marco">
							<h1>Resultados a asignar a ubicacion</h1>
							<div class="marco_interior_tabla">
								<div id="resultadosAsignar"></div>
							</div>
						</div>
					</fieldset>
				</form>
				</div>
				
				<div id="mantenedorProovedor" title="Agregar Proveedor" >
					<form action="" id="mantenedorProveedorForm" name="mantenedorProveedorForm">
						<div class="marco" >
							<h1>Información a asignar</h1>
							<div class="marco_interior" >
								<table class="table_plana">
			                       <tr>
										<td width="6%">
											<fieldset class="fieldset_popup">
										    	<label for="nombre">NOMBRE</label>
										    	<input type="text" name="nombre" id="nombre" maxlength="50">
										
					 						</fieldset>
										</td>
										<td width="6%">
											<fieldset class="fieldset_popup">
					 							<label for="id">RUT</label>
					 							<input type="text" name="rut" id="rut" class="run" >
				 							</fieldset>
										</td>
									</tr>
							   	 	<tr>
								      <td colspan="2">
				 				         <fieldset class="fieldset_botonera_center">
					        		          <input type="button" name="limpiarProveedor" id="limpiarProveedor" value="Limpiar">
										      <input type="button" name="buscarProveedor" id="buscarProveedor" value="Buscar">
				 					     </fieldset>
								      </td>
						        	</tr>
								</table>
							</div>
						  </div>
						  </form>
						<div class="marco">
							<h1>Resultados a asignar a ubicacion</h1>
							<div class="marco_interior_tabla">
								<div id="resultadosAsignarProveedor"></div>
							</div>
						</div>
					</fieldset>
				</div>
				
				<div id="bodegas" title="Bodegas" >
					<form action="" id="bodegasForm" name="bodegasForm">
			         	<div class="marco">
							<h1>Bodegas</h1>
							<div class="marco_interior">
								<table class="table_plana">
									<tr>
										<td style="width: 13%">
											<fieldset  id="rolLeft">
											</fieldset>
										</td>
										<td style="width: 23%">
											<fieldset id="rolRight">
											</fieldset>
										</td>
									</tr>
									    <tr>
									      <td colspan="2">
					   				         <fieldset class="fieldset_botonera_center">
											      <input type="button"  id="grabarBodegas" value="Grabar Bodegas">
					   					     </fieldset>
									      </td>
								        </tr>
								</table>
							</div>
						</div>
					</form>
			  </div>	
		</div>
	</div>
</body>
</html>