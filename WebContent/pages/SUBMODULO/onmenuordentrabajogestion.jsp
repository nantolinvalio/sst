<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="onmenuordentrabajogestionPanel" >
		<form id="gestiones" name="gestiones" class="formTab" enctype="multipart/form-data">
			<table class="table_plana">
	   			<tr>
	   				<td width="100%">
	   					<fieldset>
	   						<label for="gestion">Gestión</label>
	   						<input type="text" name="gestion" id="gestion" class="required" maxlength="255" />
	   					</fieldset>
	   					<fieldset>
	   						<label for="fecha">Fecha</label>
	   						<input type="text" name="fecha" id="fecha" class="fechaHora dateTimeDDMMYYYYhhmm required" />
	   					</fieldset>
	   					<fieldset>
	   						<label for="archivo">Archivo (Opcional)</label>
	   						<input name="archivo" id="archivo" type="file" class="extensionGestion"> (pdf, doc, docx, xls, xlsx o jpg)
	   						<label for="tipoArchivos">Tipo archivo</label>
	   						<select name="tipoArchivo" id="tipoArchivo"></select>
	   					</fieldset>
	   					<fieldset>
	   						<input type="button" name="agregarGestion" id="agregarGestion" value="Agregar" style="float:right; margin-right:10px;"/>
	   						<input type="hidden" id="idOT" name="idOT"/>
	   					</fieldset>
	   				</td>
	   			</tr>
			</table>
			<div id="gestionOT"></div>
		</form>
	</div><!-- Fin tabGestiones -->
</body>