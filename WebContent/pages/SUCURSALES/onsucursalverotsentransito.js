$(document).ready(function() {
	
	var moduloOT;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloOT = modulo ? modulo : undefined;
	}});
	
	var exportDocument = function(type){
		var p = $('#resultados').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#resultados').getSortName()[0];
		p.sortOrder = $('#resultados').getSortOrder()[0];
		var f = $('#resultados').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OTTransitoReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OTTransitoReport", 600, 800);
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTEnTransito,
		seccion: 10007000,
		showTableToggleBtn: true,
		tipo: 'OTET',
		multisel:false,
		singleSelect:true, 
		buttons : [
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		],
		dblclickFunction:function(el,idx,comp){      
			parent.location = context + '/index.do?e=' + moduloOT.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + el.id;
		},
	});	
	$('#resultados').loadData([{}]);
});
