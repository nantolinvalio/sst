$(document).ready(function() {
	var moduloEmitirGuia;
	
	SSTFacade.getModuloByNombreUsuario('onbodegaffemitirguiadespacho',{async:false, callback:function(modulo) {
		moduloEmitirGuiaAgrupada = modulo ? modulo : undefined;
	}});
	
	
	var exportDocumentAgrupado = function(type){
		var p = {};
		p.orderBy = $('#guiasPendientes').getSortName()[0];
		p.sortOrder = $('#guiasPendientes').getSortOrder()[0];
		var f = $('#guiasPendientes').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=GuiaPendienteAgrupadaReportFF" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&filter=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "GuiaPendienteAgrupadaReportFF", 600, 800);
	};
	
	$("#guiasPendientes").flexigrid({
		height: 300,
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listGuiasPendientesAgrupadasFF,
		tipo: 'guiaA',
		seccion: 80001000,
		overrideModel: [{findName:'destino.id',propiedad:function(o){
            if(o.destino != null && o.destino.nombre != null){
            	return o.destino.nombre;
            } else {
            	return '';
            }
		}},{findName:'tipo.glosa',propiedad:function(o){
            		return 'Recepción rechazada en Falla Fabricación';
		}},{findName:'imprimir',propiedad:function(o){
			if (moduloEmitirGuiaAgrupada) {
				return $('<input type="button" value="Imprimir Guía" onclick="emitirGuiaAgrupada(' + o.id + ')">');
			}
            return '';
		}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentAgrupado('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentAgrupado('xls');}},
	   		{separator: true}
   		]
	});
	
	$("#guiasPendientes").loadData([]);
});

var emitirGuiaAgrupada = function(idGuia) {
	parent.location = context + '/index.do?e=' + moduloEmitirGuiaAgrupada.codigo + '&m=' + moduloInicial.codigo + '&idGuia=' + idGuia;
};