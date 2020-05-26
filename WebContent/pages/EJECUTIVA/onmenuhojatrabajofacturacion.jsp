<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenuhojatrabajofacturacion.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idsSeleccion" name="idsSeleccion">
			<input type="hidden" id="idsSeleccionFactura" name="idsSeleccionFactura">
			<div class="container" >
				<h1>HOJA DE TRABAJO DE FACTURACIÓN</h1>
				<form action="" name="filtro_buscador" id="filtro_buscador" > 
		   			<div class="marco" id="filtro_buscador">
		   				<h1>Buscar ordenes de trabajo</h1>
		   				<table class="table_plana">
			   				<tr>
			   					<td width="50%">
			   						<fieldset>
			   							<label for="idOT">N° OT</label>
			   							<input type="text" name="idOT" id="idOT" class="number">
			   							<label for="idEjecutiva">EJ. MARCA</label>
			   							<select name="idEjecutiva" id="idEjecutiva"></select>
			   							<label for="idTransportista">Facturado a transp.</label>
			   							<select name="idTransportista" id="idTransportista"></select>
			   							<label for="tipoOT">Tipo O.T.</label>
			   							<select name="tipoOT" id="tipoOT"></select>
			   							<label for="idMarca">Marca</label>
			   							<select name="idMarca" id="idMarca"></select>	   							
			   						</fieldset>
			   					</td>
			   					<td  width="50%">	   					
			   						<fieldset>
			   							<label for="idProveedor">Facturado a proveedor</label>
			   							<select name="idProveedor" id="idProveedor"></select>
			   							<label for="idFamilia">Familia</label>
			   							<select name="idFamilia" id="idFamilia"></select>
			   							<label for="idProducto">Código Corto</label>
			   							<input type="text" name="idProducto" id="idProducto" class="number" maxlength="9">
			   							<label style="width:55%;" for="fechaInicio">Fecha Inicio Autorización</label>
			   							<input type="text" name="fechaInicio" id="fechaInicio" class="fecha dateDDMMYYYY">
			   							<label style="width:55%;" for="fechaTermino">Fecha Termino Autorización</label>
			   							<input type="text" name="fechaTermino" id="fechaTermino" class="fecha dateDDMMYYYY">			   						
			   						</fieldset>
		<!-- 	   						<fieldset class="fieldset_botonera_center"> -->
		<!-- 								<input type="button" name="limpiar" id="limpiar" value="Limpiar"> -->
		<!-- 								<input type="button" name="buscar" id="buscar" value="Buscar"> -->
		<!-- 	   						</fieldset> -->
			   					</td>
		         			</tr>
		         			<tr>
		       					<td width="50%" colspan="2">
		         					<fieldset class="fieldset_botonera_center">
										<input type="button" name="limpiar" id="limpiar" value="Limpiar">
										<input type="button" name="buscar" id="buscar" value="Buscar">
			   						</fieldset>
		   						</td>
		         			
		         			</tr>
		           		</table>
		           	</div>
	          	</form>	
	            <div class="marco">
					<h1>Resultados</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>       
				<div class="marco">
					<h1>Facturas</h1>
					<div class="marco_interior_tabla">
						<div id="facturas"></div>
					</div>
					<table width="100%">
						<tr>
							<td>
								<label for="numeroFactura">N° Factura</label>
								<input type="text" name="numeroFactura" id="numeroFactura" class="number">
								<label for="fechaEmision">Fecha Emisión</label>
								<input type="text" name="fechaEmision" id="fechaEmision" class="fecha dateDDMMYYYY">
								<input type="button" name="asignar" id="asignar" value="Asignar">
							</td>													
						</tr>
						<tr>
						</tr>
					</table>			
				</div>       			
   			</div>
		</div>
	</div>
</body>
</html>