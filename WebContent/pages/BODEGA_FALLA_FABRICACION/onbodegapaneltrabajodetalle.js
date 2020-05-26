$(document).ready(function() {

	var idIndicador = $('#idIndicador').val();
	$('#nombre_indicador').val($('#idIndicador').val());
	
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTIndicadorEjecutivaFF,
		seccion: 10021000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'OT',
		dblclickFunction:function(el,idx,comp){      
			redirectOtDetalle(el.id);
		},
		overrideModel: [
					    {findName:'semaforoServicioTecnico',propiedad:function(o){
							var background; 
							switch (o.semaforoServicioTecnico){
								case 1:
									background = $('<div style="background-color: lime"></div>');
									break;
								case 2:
									background = $('<div style="background-color: green"></div>');
									break;	
								case 3:
									background = $('<div style="background-color: yellow"></div>');
									break;
								case 4:
									background = $('<div style="background-color: red"></div>');
									break;
								default: background = "";
								break;
							}
							return background;
							}
					    },
					    {findName:'semaforoSucursalInicio',propiedad:function(o){
							var background; 
							switch (o.semaforoSucursalInicio){
								case 1:
									background = $('<div style="background-color: lime"></div>');
									break;
								case 2:
									background = $('<div style="background-color: green"></div>');
									break;	
								case 3:
									background = $('<div style="background-color: yellow"></div>');
									break;
								case 4:
									background = $('<div style="background-color: red"></div>');
									break;
								default: background = "";
								break;
							}
							return background;
							}
					    },
					    {findName:'semaforoSucursalfin',propiedad:function(o){
							var background; 
							switch (o.semaforoSucursalfin){
								case 1:
									background = $('<div style="background-color: lime"></div>');
									break;
								case 2:
									background = $('<div style="background-color: green"></div>');
									break;	
								case 3:
									background = $('<div style="background-color: yellow"></div>');
									break;
								case 4:
									background = $('<div style="background-color: red"></div>');
									break;
								default: background = "";
								break;
							}
							return background;
							}
					    },
					    {findName:'sucursal.glosa',propiedad:function(o){return o.sucursal.id + ' ' + o.sucursal.glosa;}},							    
					    ],
    	buttons : [
	            {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
				{separator: true},
				{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
				{separator: true}],
	});	
	
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
			"&report=OrdenTrabajoIndicadorFFReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoIndicadorFFReport", 600, 800);
	};
	
	$('#resultados').loadData([{indicador:{id:idIndicador}}]);
	
//	var exportToExcelResultados = function(){
//		var p = $('#resultados').getParameters()[0];
//		if (p == undefined) {
//			jAlert(msg.sinParametros,'Información');
//			return;
//		}
//		var f = $('#resultados').getFilterColumna()[0];
//		var url = "/sst/ViewReportServlet?type=xls&report=OrdenTrabajoReport&" +
//			"filter=" + JSON.stringify(p) + 
//			"&filterColumn=" + JSON.stringify(f);
//		$.openWindowsMenubar(url, "EXCEL_OT", 600, 800);
//	};
//
//	var exportToPDFResultados = function(){
//		var p = $('#resultados').getParameters()[0];
//		if (p == undefined) {
//			jAlert(msg.sinParametros,'Información');
//			return;
//		}
//		var f = $('#resultados').getFilterColumna()[0];
//		var url = "/sst/ViewReportServlet?type=pdf&report=OrdenTrabajoReport&" +
//			"filter=" + JSON.stringify(p) + // + getParametros(form);
//			"&filterColumn=" + JSON.stringify(f);
//		$.openWindowsMenubar(url, "PDF_OT", 600, 800);
//	};
});

