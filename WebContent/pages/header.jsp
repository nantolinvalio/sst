<title>ABCDIN :: Sistema Servicio Tecnico</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/header.js?<%= version %>"></script>
<div class="header">
	<table style="width: 100%">
		<tr>
			<td class="header_left">
				<div style="text-align: center; width: 175px !important">
					<img alt="" src="<%=request.getContextPath()%>/images/logo_nuevo.png">
<!-- 					Para cambiar la version del sistema, modificar el archivo version.jsp -->
					<label><%= version %></label>
				</div>
			</td>
			<td class="header_center">
				<label>SISTEMA DE SERVICIO TECNICO</label>
			</td>
			<td class="header_right">
				<label id="usuario_header"></label>
				<label id="ubicacion_header"></label>
				<label id="hora_header"></label>
			</td>
		</tr>
	</table>
</div>
<div id="errno_app">
	<form action="">
		<fieldset class="fieldset_popup">
			<b><span id="errno_m"></span></b>
			<br>
			<br>
			<div class="marco">
				<h1 style="cursor: pointer;" id="showExcepcion">Exception</h1>
				<div class="marco_interior">
					<table class="table_plana" style="width: 100%;overflow: hidden;" id="exception">
						<tr>
							<td>
								<fieldset>
									<b><label for="errno_cause"> - CAUSE:</label></b>
									<label id="errno_cause"></label>
									<b><label for="errno_javaClassName"> - JAVACLASSNAME:</label></b>
									<label id="errno_javaClassName"></label>
									<b><label for="errno_message"> - MESSAGE:</label></b>
									<label id="errno_message"></label>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="marco">
				<h1 style="cursor: pointer;" id="showStackTrace">StackTrace</h1>
				<div class="marco_interior">
					<table class="table_plana" id="stackTrace" style="width: 100%; overflow: hidden;">
						<tr id="stackTrace_step" style="display: none;">
							<td>
								<fieldset>
									<b><label for="errno_className"> * CLASSNAME:</label></b>
									<label id="errno_className"></label>
									<b><label for="errno_fileName"> - FILENAME:</label></b>
									<label id="errno_fileName"></label>
									<b><label for="errno_lineNumber"> - LINENUMBER:</label></b>
									<label id="errno_lineNumber"></label>
									<b><label for="errno_methodName"> - METHODNAME:</label></b>
									<label id="errno_methodName"></label>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</fieldset>	
	</form>
</div>
