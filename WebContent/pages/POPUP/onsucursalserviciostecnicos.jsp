<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/POPUP/onsucursalserviciostecnicos.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Consultar Servicios Técnicos</h1>
				<div class="marco">
					<h1>Información del Producto</h1>
					<form id="documentoProducto">
						<table class="table_plana" width="100%" style="text-transform: uppercase;">
							<tr>
								<td width="55%">
									<fieldset>
										<label id="tipoDocumento" for="idDocumento" style="width:170px;"><%=request.getParameter("tipoDocumento")%></label>
										<label id="idDocumento" style="width:315px;"><%=request.getParameter("idDocumento")%></label>
										
										<label for="estadoGP" style="width:170px;">Garantía Proveedor</label>
										<label id="estadoGP" style="width:315px;">&nbsp;</label>
										
										<label for="producto" style="width:170px;">Producto</label>
										<label id="producto" style="width:315px;"><span id="idProducto"><%=request.getParameter("idProducto")%>,</span><span id="producto.descripcion">&nbsp;</span></label>
									</fieldset>
								</td>
								<td width="45%">
									<fieldset>
										<label for="fechaEmision" style="width:145px;">Fecha Emisión</label>
										<label id="fechaEmision" style="width:205px;" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="fechaVencimiento" style="width:145px;">Fecha Vencimiento</label>
										<label id="fechaVencimiento" style="width:205px;" format="dd/MM/yyyy">&nbsp;</label>
										
										<label for="marca" style="width:145px;">Marca</label>
										<label id="marca" style="width:205px;">&nbsp;</label>
									</fieldset>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="tabs">
					<ul>
						<li id="onmenutipoFallasFamiliaTab"><a href="#onmenuserviciostecnicosPanel">Servicios Técnicos</a></li>
						<li id="onmenutipoFallasProductosTab"><a href="#onmenuprocedimientoPanel">Procedimiento</a></li>
					</ul>
					<div id="onmenuserviciostecnicosPanel">
						<div class="marco">
							<div class="marco_interior_tabla">
								<div id="sTecnico"></div>
							</div>
						</div>
					</div>
					<div id="onmenuprocedimientoPanel">
						<div class="marco">
							<div class="marco_interior_tabla">
								<form action="" name="procedimientoForm" id="procedimientoForm">
									<table class="table_plana">
										<tr>
											<td>
						   						<fieldset>
						   							<label >Procedimiento:</label> 
						   						</fieldset>
						   					</td>
										</tr>
										<tr>
											<td>
						   						<fieldset>
						   							<label id="procedimiento"></label> 
						   						</fieldset>
						   					</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			  	<table width="70%" >
			  		<tr>
			  			<td width="65%">
			  				<fieldset>
			  					<input type="button" value="Volver" id="volver">
			  				</fieldset>
			  			</td>
			  		</tr>
			  	</table>
			</div>
		</div>
	</div>
</body>
</html>