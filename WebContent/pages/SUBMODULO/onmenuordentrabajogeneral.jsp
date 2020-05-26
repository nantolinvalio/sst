<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajogeneralPanel">
		<form id="ordenTrabajo" name="ordenTrabajo" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="45%">
						<fieldset class="twoCol">
							<label for="id">N&deg; Orden de trabajo</label>
							<label id="id">&nbsp;</label>
							
							<label for="fechaCreacion">Fecha de creación</label>
							<label id="fechaCreacion" format="dd/MM/yyyy hh:mm:ss">&nbsp;</label>
							
							<label for="idDocumento" id="tipoDocumento">&nbsp;</label>
							<label id="idDocumento">&nbsp;</label>
						
							<label for="logistico.nombreCompleto" >Logístico responsable</label>
							<label id="logistico.nombreCompleto">&nbsp;</label>
							
							<label for="ejecutiva.nombreCompleto">Ejecutiva marca</label>
							<label id="ejecutiva.nombreCompleto">&nbsp;</label>
							
							<label for="sucursal">Sucursal</label>
							<label id="sucursal" ><span id="sucursal.id">&nbsp;</span> <span id="sucursal.glosa">&nbsp;</span></label>
							
							<label for="sucursal.region.glosa">Región</label>
							<label id="sucursal.region.glosa">&nbsp;</label>
						</fieldset>
					</td>
					<td width="50%">
						<fieldset class="twoCol">
							<label for="tipo.glosa">Tipo de orden de trabajo</label>
							<label id="tipo.glosa">&nbsp;</label>
							
							<label for="fechaVencimiento">Fecha vencimiento garantía</label>
							<label id="fechaVencimiento" format="dd/MM/yyyy">&nbsp;</label>
							
							<label for="fechaEmision">Fecha emisión</label>
							<label id="fechaEmision" format="dd/MM/yyyy">&nbsp;</label>
																
							<label for="logistico.anexo">Telefono logístico</label>
							<label id="logistico.anexo" >&nbsp;</label>
							
							<label for="ejecutiva.anexo">Telefono ejecutiva</label>
							<label id="ejecutiva.anexo" >&nbsp;</label>
							
							<label for="sucursal.telefono">Telefono sucursal</label>
							<label id="sucursal.telefono" >&nbsp;</label>
							
							<label for="sucursal.comuna.glosa">Comuna</label>
							<label id="sucursal.comuna.glosa">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div><!-- fin tabOrdenTrabajo -->
</body>