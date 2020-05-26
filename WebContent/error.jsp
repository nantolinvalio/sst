<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="pages/library.jsp" %>
</head>
<body>
	<div class="main_body">
		<%@ include file="pages/header.jsp" %>
		<%@ include file="pages/menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>${error}</h1>
				<div class="marco">
					<h1>${pagina}</h1>
					<form action="" name="" id="">
						<div class="marco_interior_tabla">
							<table class="table_plana" width="100%">
								<tr>
									<td>
										<fieldset>
											<label>MACRO</label>
											<label>${macro}</label>
											<label>NOMBRE</label>
											<label>${nombre}</label>
											<label>CODIGO</label>
											<label>${codigo}</label>
											<label>PAGINA</label>
											<label>${pagina}</label>
										</fieldset>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>