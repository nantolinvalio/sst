<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/BODEGA/revisarinventario.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<input type="hidden" id="idInventario" name="idInventario" value='<%=request.getParameter("idInventario")%>'>
			<div class="container">
				<h1>Resultado inventario</h1>
				<div class="marco">
					<h1>Resumen por ubicación</h1>
					<div class="marco_interior">
						<div id="ubicaciones"></div>
						<form id="resumenInventarioForm">
							<table class="table_plana">
								<tr>
									<td colspan='3'>
										<fieldset>
											<h1>Resumen Total</h1>
											<input id="idInventario" type="hidden"/>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td width="33%">
										<fieldset>
											<label for="productosPreparados" style="width: 50%">Preparados</label>
											<label id="productosPreparados"></label>
											
											<label for="productosContados" style="width: 50%">Contados</label>
											<label id="productosContados"></label>
											
											<label for="sinDiferencia" style="width: 50%">Sin diferencias</label>
											<label id="sinDiferencia"></label>
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="productosFaltantes" style="width: 50%">Faltantes</label>
											<label id="productosFaltantes"></label>
											
											<label for="productosSobrantes" style="width: 50%">Sobrantes</label>
											<label id="productosSobrantes" style="width: 50%"></label>
											
											<label for="brecha" style="width: 50%">Brecha</label>
											<label id="brecha"></label>
										</fieldset>
									</td>
									<td width="33%">
										<fieldset>
											<label for="fechaCreacion" style="width: 50%">Fecha inicio</label>
											<label id="fechaCreacion" format="dd/MM/yyyy hh:mm"></label>
											
											<label for="fechaCierre" style="width: 50%">Fecha Termino</label>
											<label id="fechaCierre" format="dd/MM/yyyy hh:mm"></label>
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div><!-- Fin container -->
		</div><!-- Fin wrapper -->
		
		<div id="resumenInventarioUbicacion" title="Resultado Inventario"><!-- POPUP -->
			<div id="productosInventariados"></div>
			<form id="resumenInventarioUbicacionForm">
				<h1>Resumen inventario <span id="ubicacionInterna.nombre"></span></h1>
				<table class="table_plana" width="100%">
					<tr>
						<td width="33%">
							<fieldset>
								<label for="productosPreparados" style="width: 40%; float:left;">Preparados:</label>
								<label id="productosPreparados" name="productosPreparados" ># preparados</label>
							</fieldset>
							<fieldset>
								<label for="productosContados" style="width: 40%; float:left;">Contados:</label>
								<label id="productosContados" name="productosContados" ># contados</label>
							</fieldset>
							<fieldset>
								<label for="sinDiferencia" style="width: 40%; float:left;">Sin diferencia:</label>
								<label id="sinDiferencia" name="sinDiferencia" ># sin diferencia</label>
							</fieldset>
						</td>
						<td width="33%">
							<fieldset>
								<label for="productosFaltantes" style="width: 40%; float:left;">Faltantes:</label>
								<label id="productosFaltantes" name="productosFaltantes" ># faltantes</label>
							</fieldset>
							<fieldset>
								<label for="productosSobrantes" style="width: 40%; float:left;">Sobrantes:</label>
								<label id="productosSobrantes" name="productosSobrantes" ># sobrantes</label>
							</fieldset>
							<fieldset>
								<label for="brecha" style="width: 40%; float:left;">Brecha:</label>
								<label id="brecha" name="brecha" ># brecha</label>
							</fieldset>
						</td>
						<td width="33%">
							<fieldset>
								<label for="fechaInicio" style="width: 40%; float:left;">Fecha inicio:</label>
								<label id="fechaInicio" name="fechaInicio" format="dd/MM/yyyy hh:mm" ># fecha inicio</label>
							</fieldset>
							<fieldset>
								<label for="fechaTermino" style="width: 40%; float:left;">Fecha termino:</label>
								<label id="fechaTermino" name="fechaTermino" format="dd/MM/yyyy hh:mm" >fecha termino</label>
							</fieldset>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div><!-- Fin main_body -->
</body>