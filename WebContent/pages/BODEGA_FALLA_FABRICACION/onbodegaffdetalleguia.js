$(document).ready(function() {

	var idIndicador = $('#idIndicador').val();
	
	var ots = [];


	$('#nombre_indicador').val($('#idIndicador').val());
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listGuiaIndicadorEjecutivaFF,
		seccion: 1000040000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'DGIA',
		overrideModel: [
	        {findName:'facturar',propiedad:function(o){
	        	var idGuia = o.id;
	        	var btn = $('<input type="button">');
				btn.attr('value', 'Facturar');
				btn.attr('onclick','facturarAtransportista(' + idGuia + ')');
	        	return btn;
	    	}},            
        ],
    	buttons : [
			{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			{separator: true}
		]
	});	
	
	$('#directoaFacturar').flexigrid({ // GRILLA QUE MUESTRA LAS OT A FACTURAR
		dwrFunction: SSTFacade.listOtByIdGuia,
		seccion: 1000040000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'OTAF',
		overrideModel: [{findName:'id',propiedad:function(o){
			ots.push(o);
			return o.id;
		}},
		],
    	buttons : [
			{name: 'FACTURAR SC', bclass: 'btnFactura', onpress : function(){generarSCTransportista(ots);} },
			{separator: true},
		]
	});
	
	var generarSCTransportista = function(ots){
		if(ots.length == 0){
			jAlert('No existen ot para generar factura','AVISO');
		}else{
			var guia = {id : $('#idGuia').val()}; 
			 SSTFacade.generarSC(ots,null, guia,{async:false,callback:function(){
				 jAlert('Se genero correctamente la SC','AVISO');
				 $('#directoaFacturar').flexReload();
			 }});
		}
	};
	
	$('#resultados').loadData([{indicador:{id:idIndicador}}]);
	
	$('#directoaFacturar').loadData([{idGuia:$('#idGuia').val()}]);
	
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
				"&report=OrdenTrabajoIndicadorFFGuiaReport" +
				"&filter=" + JSON.stringify(p) +
				"&filterColumn=" + JSON.stringify(f);
			$.openWindowsMenubar(url, "OrdenTrabajoIndicadorFFGuiaReport", 600, 800);
		};
	});

	var moduloRecibir;
	SSTFacade.getModuloByNombreUsuario('onbodegadetalleguiaafacturar',{async:false, callback:function(modulo) {
		moduloRecibir = modulo ? modulo : undefined;
	}});

	var facturarAtransportista = function(idGuia) {
			parent.location = context +'/index.do?e=' + moduloRecibir.codigo + '&m=' + moduloInicial.codigo+'&idGuia='+idGuia;
		};
		
	
	

	