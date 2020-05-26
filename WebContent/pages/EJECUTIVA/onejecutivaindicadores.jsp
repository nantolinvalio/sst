<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/EJECUTIVA/onejecutivaindicadores.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Procesos del Sistema</h1>
				<div class="marco">
					<h1>Actualizar vistas materializadas</h1>
					<div class="marco_interior">
						<form action="" name="filtro_buscador_basico" id="filtro_buscador_basico">
							<table class="table_plana" width="100%">
								<tr>
									<td colspan="3">
										La actualización de las vistas materializadas se debe realizar al hacer clic en los botones con el siguiente orden.
									</td>
								</tr>
								<tr>
									<td width="33%">
										<fieldset class="fieldset_botonera_center">
											1° <input type="button" value="Documentos" style="width: 70%" id="documentos">
										</fieldset>
										<fieldset class="fieldset_botonera_center">
											4° <input type="button" value="Marcas" style="width: 70%" id="marcas">
										</fieldset>
										<fieldset class="fieldset_botonera_center">
											7° <input type="button" value="Transportistas" style="width: 70%" id="transportistas">
										</fieldset>
									</td>
									<td width="33%">
										<fieldset class="fieldset_botonera_center">
											2° <input type="button" value="Documentos Productos" style="width: 70%" id="documentosProductos">
										</fieldset>
										<fieldset class="fieldset_botonera_center">
											5° <input type="button" value="Familias" style="width: 70%" id="familias">
										</fieldset>
									</td>
									<td width="33%">
										<fieldset class="fieldset_botonera_center">
											3° <input type="button" value="Productos" style="width: 70%" id="productos">	
										</fieldset>
										<fieldset class="fieldset_botonera_center">
											6° <input type="button" value="Proveedores" style="width: 70%" id="proveedores">
										</fieldset>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="marco">
				<h1>Actualización de Información (Procesos nocturnos)</h1>
					<div id="marco_interior">
						<form id="filtro_buscador_avanzado" name="filtro_buscador_avanzado">
							<table class="table_plana" width="100%">
								<tr>
									<td colspan="3">
										<b>Semaforo de ordenes de trabajo</b> Calcula en que color del semaforo de ordenes de trabajo se encuentran las ordenes de trabajo abiertas y vigentes que sean de garántia Master o proveedor, se recomienda que se ejecute entre las 00:00 y las 08:00 A.M. de cada dia habil.
									</td>
								</tr>
								<tr>
									<td width="33%"></td>
									<td  style="padding-bottom: 10px">
										<fieldset class="fieldset_botonera_center">
											<input type="button" value="Calcular semaforo" style="width: 70%" id="semaforo">
										</fieldset>
									</td>
									<td width="33%"></td>
								</tr>
								<tr>
									<td colspan="3">
										<b>Indicadores de gestion de las ejecutivas de post venta</b> Calcula los indicadores de gestion de las ejecutivas de marca, colas, y facturación, , se recomienda que se ejecute entre las 00:00 y las 08:00 A.M. de cada dia habil.
									</td>
								</tr>
								<tr>
									<td width="33%"></td>
									<td  style="padding-bottom: 10px">
										<fieldset class="fieldset_botonera_center">
											<input type="button" value="Calcular indicadores" style="width: 70%" id="indicadores">	
										</fieldset>
									</td>
									<td width="33%"></td>
								</tr>
									<td colspan="3">
										<b>Guías e información de gestión para la bodega de servicio técnico</b> Calcula los días que las ordenes de trabajo han estado en las diferentes ubicaciones dentro de su ciclo de vida, además de obtener las últimas guías generadas para las diferentes ubicaciones. 
									</td>
								</tr>
								<tr>
									<td width="33%"></td>
									<td  style="padding-bottom: 10px">
										<fieldset class="fieldset_botonera_center">
											<input type="button" value="Ejecutar Proceso Bodega" style="width: 70%" id="procesoBodega">	
										</fieldset>
									</td>
									<td width="33%"></td>
								</tr>
								</tr>
									<td colspan="3">
										<b>Informe de cambios por proveedores</b> Calcula las ordenes de trabajo generadas y las ventas realizadas en los últimos 6 meses para el reporte de proveedores 
									</td>
								</tr>
								<tr>
									<td width="33%"></td>
									<td  style="padding-bottom: 10px">
										<fieldset class="fieldset_botonera_center">
											<input type="button" value="Ejecutar Proceso Proveedores" style="width: 70%" id="procesoProveedores">	
										</fieldset>
									</td>
									<td width="33%"></td>
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