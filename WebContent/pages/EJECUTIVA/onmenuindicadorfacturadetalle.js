$(document).ready(function() {
		
		
	var menuIndicador, nombreModulo;
	SSTFacade.getModuloInicialByUsuario({async:false, callback:function(modulo) {
		menuIndicador = modulo.codigo;
		nombreModulo = modulo.nombre;
	}});

	var parentIndicadorOrdenTrabajo;
	SSTFacade.getModuloByNombre('onmenuordentrabajo',{async:false, callback:function(modulo) {
		parentIndicadorOrdenTrabajo = modulo.codigo;
	}});	
	
	var parentIndicadorFacturaDetalle;
	SSTFacade.getModuloByNombre('onpopupfacturadetalle',{async:false, callback:function(modulo) {
		parentIndicadorFacturaDetalle = modulo.codigo;
	}});	
	
	var exportDocument = function(type){
		var p = $('#facturas').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#facturas').getSortName()[0];
		p.sortOrder = $('#facturas').getSortOrder()[0];
		var f = $('#facturas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=FacturacionDetalleReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "PDF_OT", 600, 800);
	};
	

	$('#facturas').flexigrid({
		dwrFunction: SSTFacade.listFacturasIndicadoresEjecutivaFacturacion,
		seccion: 30005000,
		showTableToggleBtn: true,		
		multisel:false,
		singleSelect:true,
		tipo: 'factDet',
//		dblclickFunction:function(el,idx,comp){
//			//parent.location = context +'/index.do?e=' + parentIndicadorFacturaDetalle + '&m=' + menuIndicador + '&idOT=' + el.id;
//		},
		buttons : [
		    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
			{separator: true}
   		],
	});	
	
    var idFacturar = $('#idFacturar').isEmpty() ? 0 : $('#idFacturar').val();
//    var tipoOt = $('#tipoOT').isEmpty() ? '' : $('#tipoOT').val();
//    var idEjecutiva = $('#idEjecutiva').isEmpty() ? 0 : $('#idEjecutiva').val();;
    var fechaInicio = $('#fechaInicio').isEmpty() ? '' : $('#fechaInicio').val();
    var fechaTermino = $('#fechaTermino').isEmpty() ? '' : $('#fechaTermino').val();
//    var facturarTipo = $('#facturarTipo').isEmpty() ? '' : $('#facturarTipo').val();
    var idIndicador = $('#idIndicador').isEmpty() ? 0 : $('#idIndicador').val();
//    var fecha = $('#fecha').isEmpty() ? '' : $('#fecha').val();

//    var facturaJson = {'rut' : idFacturar, 'tipoOT' : tipoOt, 'idEjecutiva' : idEjecutiva, 'fechaInicio' : fechaInicio, 'fechaTermino' : fechaTermino, 'tipoFacturado' : facturarTipo, 'idIndicador' : idIndicador, 'fecha' : fecha};
    var facturaJson = {
    	fechaInicio : fechaInicio, 
    	fechaTermino : fechaTermino, 
    	idIndicador : idIndicador,
    	rut:idFacturar
    };
    $('#facturas').loadData([facturaJson]);

	
});
