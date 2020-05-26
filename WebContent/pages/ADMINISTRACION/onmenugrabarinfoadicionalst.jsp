<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenugrabarinfoadicionalst.js?<%= version %>"></script>
</head> 
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
    	<input type="hidden" id="idProcedimiento" name="idProcedimiento" value='<%=request.getParameter("idProcedimiento")%>'>
			<div class="container">
			<h1 id="titlePage"></h1>
				<div id="tabs">
					<ul>
						<li id="onmenutipoFallasFamiliaTab"><a href="#onmenuLineaMarca">Linea / Marca</a></li>
						<li id="onmenutipoFallasProductosTab"><a href="#onmenuProducto">Productos</a></li>
					</ul>
					<div id="onmenuLineaMarca">
						<div class="marco_interior">
							<form id="procedimientoLineaMarca" class="formTab" name="procedimientoLineaMarca">
								<input type="hidden" id="id" name="id">
								<table class="table_plana" width="100%">
			   						<tr>
			   							<td width="19%">
			   								<fieldset>
			   									<label for="linea.codigo">Linea</label>
			   									<select name="linea.codigo" id="linea.codigo" class="required"></select>
		   									</fieldset>
			   							</td>
			   							<td width="19%">
			   								<fieldset>
			   									<label for="marca.codigo">Marca</label>
			   									<select name="marca.codigo" id="marca.codigo" class="required"></select>
		   									</fieldset>
			   							</td>
			   						</tr>
								</table>
								<table class="table_plana" width="100%">
			            			<tr>
		   								<td>
		   									<textarea rows="22" style="width: 100%" id="procedimiento" name="procedimiento" class="required"></textarea>	   							
			   							</td>
				            		</tr>		            	
				            		<tr>
				            			<td>
		        	    					<fieldset class="fieldset_botonera_center">
		   										<input type="button" id="grabarLineaMarca" name="grabarLineaMarca" value="Grabar">
		   									</fieldset>	   							
		   								</td>
			            			</tr>
		     					</table>
							</form>
						</div>	
					</div>
				
					<div id="onmenuProducto">
						<form id="procedimientoCodigoDescripcion" name="procedimientoCodigoDescripcion" class="formTab">
						<input type="hidden" id="id" name="id">
							<table class="table_plana" style="width: 100%">
			   					<tr>
				   					<td width="56%">
										<fieldset>
					   						<label for="producto.id">Código Corto</label>
					   						<input type="text" id="producto.id" name="producto.id" class="number required" maxlength="13" >
					   					</fieldset>	   							
				   					</td>
				   					<td width="45%">
										<fieldset>
		   									<input type="button" id="BuscarCodigoDescripcion" name="BuscarCodigoDescripcion" value="Buscar">
			   							</fieldset>	   	
				   					</td>
			   					</tr>
			   					<tr>
				   					<td>
				   						<fieldset>
					   						<label for="marca.nombre" style="width: 20%">Marca:</label>
					   						<label id="marca.nombre">&nbsp;</label>
					   					</fieldset>
				   					</td>
				   					<td colspan="2">
										<fieldset>
					   						<label for="familia.linea.glosa" style="width: 20%">Linea:</label>
					   						<label id="familia.linea.glosa"></label>
					   					</fieldset>
				   					</td>
				   				</tr>
							</table>
							<table class="table_plana" width="100%">
				            	<tr>
			   						<td>
			   							<textarea rows="22" style="width: 100%" id="procedimiento" name="procedimiento" class="required"></textarea>	   							
			   						</td>
				            	</tr>		            	
				            	<tr>
				            		<td>
				            			<fieldset class="fieldset_botonera_center">
			   								<input type="button" id="grabarCodigoDescripcion" name="grabarCodigoDescripcion" value="Grabar">
			   							</fieldset>	   							
			   						</td>
				            	</tr>
			     			</table>
						</form>
					</div>
				</div>
			</div>  
		</div>
	</div>  
</body>
</html>