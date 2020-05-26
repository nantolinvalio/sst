<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="version.jsp"%>
<%@page language="java" %>
<%

cl.abcdin.sst.service.UtilService utilService = (cl.abcdin.sst.service.UtilService)cl.abcdin.sst.service.SpringApplicationContext.getBean("utilService");
cl.abcdin.sst.model.Usuario       usuario_pas = (cl.abcdin.sst.model.Usuario)request.getSession(false).getAttribute(cl.abcdin.sst.utils.Constants.SESSION_USER);
String[]                          path_name   = request.getRequestURI().toString().split("/");
String                            page_name   = path_name[path_name.length - 1].substring(0,path_name[path_name.length - 1].indexOf('.'));

java.util.List<cl.abcdin.sst.model.vo.Modulo> sub_modulos = utilService.listSubModuloByFilter(page_name, usuario_pas);

for(cl.abcdin.sst.model.vo.Modulo modulo : sub_modulos) {
	out.print("<script type='text/javascript' src='" + request.getContextPath() + "/pages/" + modulo.getMacro() + "/" + modulo.getNombre() + ".js?" + version + "'></script>");
}

%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css?<%= version %>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/jquery-ui-1.8.16.custom.css?<%= version %>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/flexigrid.css?<%= version %>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/jquery.alerts.css?<%= version %>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/tabs.css?<%= version %>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/plugin/jstree.css?<%= version %>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.cookie.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui-1.8.20.custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui-timepicker-addon.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.validate.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.alerts.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.tooltip.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.maxlength-min.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.jstree.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/date.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/flexigrid.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/custom.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/SSTFacade.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils.js?<%= version %>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/commons.js?<%= version %>"></script>

<!-- NO AGREGAR MAS SUBMODULOS A ESTA PAGINA, LAS PESTAÑAS SE AGREGAN AUTOMATICAMENTE -->
<!-- TODO: MODIFICAR EL USO DE LA SESION CUANDO ACABE LA MIGRACION -->