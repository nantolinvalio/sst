<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ABCDIN :: Sistema de servicio técnico ::</title>
<script type="text/javascript">
	var exit = function() {
		parent.close();
	};
</script>
</head>
	<body>
		<applet code="cl.abcdin.sst.applets.CodigoBarrasApplet" codebase="applets/" archive="CodigoBarrasApplet.jar" width="120" height="100">
			<param name="codigoBarras" value="<%=request.getParameter("codigoBarras")%>">
		</applet>
	</body>
</html>