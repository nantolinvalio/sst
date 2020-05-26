<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajorevisionPanel">
		<form id="revision" name="revision" class="formTab">
			<table class="table_plana" width="100%">
				<tr>
					<td width="50%">
						<fieldset>
							<label for="tecnico">NOMBRE TÉCNICO</label>	
							<label id="nombreTecnico" name="nombreTecnico">&nbsp;</label>
							
							<label for="clasificacion">CLASIFICACIÓN</label>	
							<label id="clasificacion.glosa" name="clasificacion.glosa">&nbsp;</label>
						</fieldset>
					</td>
					<td width="50%">
						<fieldset>
							<label for="fechaEvaluacion">FECHA EVALUACIÓN</label>	
							<label id="fechaRevision" format="dd/MM/yyyy">&nbsp;</label>
							
							<label for="observacion">OBSERVACIÓN</label>	
							<label id="observacionRevision" name="observacionRevision">&nbsp;</label>
						</fieldset>
					</td>
				</tr>
			</table>
		</form>
	</div><!-- Fin tabProducto -->
</body>