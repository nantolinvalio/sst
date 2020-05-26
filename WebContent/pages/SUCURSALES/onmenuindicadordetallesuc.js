$(document).ready(function() {
	var idIndicador = $('#idIndicador').val();

	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getIndicadorById(idIndicador,function(indicador){
		$("#h1Indicador").text(indicador.descripcion);	
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
			"&report=OrdenTrabajoIndicadorReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoIndicadorReport", 600, 800);
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTIndicadorSucursal,
		seccion: 40001000,
		showTableToggleBtn: true,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		tipo: 'OT',
		overrideModel: [{
				findName:'semaforoServicioTecnico',propiedad:function(o){
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
		    },{
		    	findName:'semaforoSucursalInicio',propiedad:function(o){
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
		    },{
		    	findName:'semaforoSucursalfin',propiedad:function(o){
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
	    }],
		buttons : [
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		],
		dblclickFunction:function(el,idx,comp){      
			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + el.id;
		},
	});	
	$('#resultados').loadData([{idIndicador:idIndicador}]);
});
