<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/SUCURSALES/onsucursalcontrato.js?<%= version %>"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/pasosCrearOT.css?<%= version %>" rel="stylesheet"/>
</head>
<body> 
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>CONTRATO DE SERVICIO TÉCNICO</h1>		
				
				<div class="marco" id="etapas" style="display: none;">
					<h1>Etapa de la orden de trabajo</h1>
					<table class="table_plana" id="etapasOT">
					</table>
				</div>
				<div class="marco">
					<h1>Buscar orden de trabajo</h1>
					<form id="buscarDocumento">
						<table class="table_plana" width="100%">
							<tr>
								<td style="width: 40%">
									<fieldset>
										<label for="idOT" style="text-align:left; width: 43%">N° ORDEN DE TRABAJO</label>&nbsp;
										<input type="text" id="idOT" name="idOT" style="width: 45%" maxlength="9" class="number"/>
									</fieldset>
								</td>
								<td style="width: 40%">
									<fieldset>								
										<label for="numeroGuia" style="text-align:center">N° GUÍA </label>
										<input type="text" id="numeroGuia" name="numeroGuia"  style="width: 35%" maxlength="9" class="number"/>								
									</fieldset>
								</td>
								<td style="width: 20%">
									<fieldset>								
										<input type="button" id="buscar" name="buscar" value="Buscar">
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div><!-- FIN FORMULARIO BUSCAR DOCUMENTO -->
				
				<div class="marco" id="formulario" style="display: none;">
					<h1>Tienda - servicio técnico local</h1>
					<form id="resultado">
						<table class="table_plana">
							<tr>	
					            <td class="labels" style="width: 50%">
					            	<fieldset>			            		
					            			<label for="ot.id" style="text-align:left; width: 40%">N° orden de trabajo</label>
					            			<label id="ot.id" style="text-align:left; width: 59%"></label>			            		
					            			<label for="glosa" style="text-align:left; width: 40%">Servicio técnico</label>
					            			<label id="glosa" style="text-align:left; width: 59%"></label>
					            			<label for="rut" style="text-align:left; width: 40%">Rut servicio técnico</label>
					            			<label id="rut" style="text-align:left; width: 59%"></label>
					            			<label for="direccion" style="text-align:left; width: 40%">Dirección</label>
					            			<label id="direccion" style="text-align:left; width: 59%"></label>
					            			<label for="ot.producto.descripcion" style="text-align:left; width: 40%">Producto</label>
					            			<label id="ot.producto.descripcion" style="text-align:left; width: 59%"></label>	            				            					            	
					            	</fieldset>			            	
					            </td>
					            <td class="labels" style="width: 50%">
					            	<fieldset>			            		
					            			<label for="ot.tipo.glosa" style="text-align:left; width: 50%">Tipo orden de trabajo</label>
					            			<label id="ot.tipo.glosa" style="text-align:left; width: 49%"></label>			            	
					            			<label for="guia.numero" style="text-align:left; width: 50%">N° de guia de despacho</label>
					            			<label id="guia.numero" style="text-align:left; width: 49%"></label>			            	
					            			<label for="guia.fechaEmision" style="text-align:left; width: 50%">Fecha guia despacho</label>
					            			<label id="guia.fechaEmision" format="dd/MM/yyyy hh:mm" style="text-align:left; width: 49%"></label>			            		
					            			<label for="telefono" style="text-align:left; width: 50%">Teléfono servicio técnico</label>
					            			<label id="telefono" style="text-align:left; width: 49%"></label>			            		
					            			<label for="ot.producto.marca.nombre" style="text-align:left; width: 50%">Marca</label>
					            			<label id="ot.producto.marca.nombre" style="text-align:left; width: 49%"></label>			            			            	
					            	</fieldset>		            	
					            </td>
					         </tr>
					         <tr>
					         	<td colspan="2">
					            	<fieldset>			            		
					            		<label for="cliente.nombreCompleto" style="text-align:left; width: 20%">Cliente</label>
					            		<label id="cliente.nombreCompleto" style="text-align:left; width: 79%"></label>			            					            		
					            		<input type="checkbox" id="ot.contratoEmitido" name="ot.contratoEmitido" style="float: left; margin: 5px;width: 3%">
					            		<label for="ot.contratoEmitido" style="text-align:left; float: right; width: 95%">Servicio técnico no emitio contrato</label>
					            		<label for="ot.numeroContrato" style="text-align:left; width: 290px">N° de contrato de servicio técnico</label>
					            		<input type="text" id="ot.numeroContrato" name="ot.numeroContrato" style="width: 250px" maxlength="25" class="required"/>
				            		</fieldset>
				            		<fieldset>
					            		<label for="ot.diagnostico" style="text-align:left; width: 290px">diagnostico</label>	
					            		<textarea id="ot.diagnostico" name="ot.diagnostico" rows="3" style="width: 100%"></textarea>			            	
					            	</fieldset>			            		
					            </td>
					         </tr>			    
					         <tr>
					            <td class="fieldset_botonera_center">
					                <input type="button" id="cancelar" name="cancelar" value="Cancelar" style="float:left; margin-right: 10px">				                
					            </td>
					            <td>
					            	<input type="button" id="grabar" name="grabar" value="Grabar" style="float:right; margin-left: 10px">
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