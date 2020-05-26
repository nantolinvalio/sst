<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onmenuresumenfacturacion.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container" >
				<h1 >Resumen de facturas</h1>
		   		<div class="marco">
		   			<h1 >Buscar facturas</h1>
		   				<div class="marco_interior">
		   				<form action="" name="filtro_buscador" id="filtro_buscador">
		   				<table class="table_plana">
			   				<tr>
			   					<td width="50%">
			   						<fieldset>
										<label for="idProveedor">FACTURADO A PROVEEDOR</label>
			   							<select name="idProveedor" id="idProveedor"></select>
			   						</fieldset>
			   						<fieldset>
			   							<label for="idTransportista">FACTURADO A TRANSP.</label>
			   							<select name="idTransportista" id="idTransportista"></select>
			   							<label for="idFacturar">FACTURADO A</label>
			   							<select name="idFacturar" id="idFacturar">
			   								<option value="">[SELECCIONE]</option>
		                   					<option value="89772300">Servicio estado</option>
			   							</select>
			   							<label for="tipoOT">TIPO O.T.</label>
			   							<select name="tipoOT" id="tipoOT"></select>	   							
			   						</fieldset>
			   					</td>
			   					<td  width="50%">	   					
			   						<fieldset>
			   							<label for="idEjecutiva">EJ. MARCA</label>
			   							<select name="idEjecutiva" id="idEjecutiva"></select>
			   							<label for="tipoFacturado">TIPO DE FACTURADO</label>
			   							<select name="tipoFacturado" id="tipoFacturado">
				   							<option value="">[SELECCIONE]</option>
						                    <option value="Proveedor">Proveedor</option>
						                    <option value="Transportista">Transportista</option>
			   							</select>
			   							<label style="width:39%;" for="fechaInicio">FECHA INICIO EMISIÓN</label>
			   							<input type="text" name="fechaInicio" id="fechaInicio" class="fecha dateDDMMYYYY">
			   							<label style="width:39%;" for="fechaTermino">FECHA TÉRMINO EMISIÓN</label>
			   							<input type="text" name="fechaTermino" id="fechaTermino" class="fecha dateDDMMYYYY">	   									   						
			   						</fieldset>	   						
			   					</td>	   					
		         			</tr>
		         			<tr>
		         				<td width="30%" colspan="2">
			   						<fieldset style="text-align: center;">
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
			        	<h1>Facturas</h1>
						<div class="marco_interior_tabla">
							<div id="facturas"></div>
						</div>
					</div> 
			</div>
		</div>
	</div>
</body>
</html>