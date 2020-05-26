<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenurolesmantenedor.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>PERFILES DE USUARIO</h1>
				<div class="marco">
					<h1>Resultados (Doble click para editar)</h1>
					<div class="marco_interior_tabla">
						<div id="resultados"></div>
					</div>
				</div>
				<div id="perfilModal" title="Nuevo Perfil">
					<form action="" id="perfil" name="perfil">
						<fieldset class="fieldset_popup">
							<div class="marco">
								<h1>Perfil</h1>
								<div class="marco_interior">
									<table class="table_plana" style="width: 100%">
										<tr>
											<td>
												<fieldset>
													<label for="nombrePerfil">Nombre del Perfil</label>
													<input id="nombrePerfil" name="nombrePerfil" type="text" maxlength="64" class="required" >
						   						</fieldset>
											</td>
										</tr>
										<tr>
											<td>
												<fieldset>
													<input type="button" value="Agregar Nuevo Perfil" style="float: right;" id="agregarPerfil">
						   						</fieldset>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>