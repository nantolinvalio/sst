<html>
<head>
	<%@ include file="../library.jsp" %>		
	<script type="text/javascript">
	(function(){
		SSTFacade.logout(function(){
			$.cookie("mod_seleccionado", null);
			var usu_cookie = JSON.parse($.cookie('usu_cookie'));
			if (usu_cookie) {
				usu_cookie.autoLogin = false;
			}
			$.cookie('usu_cookie', JSON.stringify(usu_cookie));
			parent.location = context ;
		});		
	}());
	</script>
</head>
<body>