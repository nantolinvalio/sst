$(document).ready(function(){
	
	$('#envios').flexigrid({
		dwrFunction: SSTFacade.listEnviosToSucursal, 
		seccion: 1000800000,
		showTableToggleBtn: true,
		tipo: 'DESU',
		multisel:false,
		singleSelect:true,
		overrideModel: [{findName:'abrirCerrar',propiedad:function(o){
				var btn = $('<input type="button">');
				
				if(((o.fechaInicio!=null && o.fechaTermino!=null) || o.fechaInicio==null) && o.almacenadas>0){
					$(btn).attr('value','Abrir');
		    		$(btn).attr('onclick','abrirCerrarEnvio('+o.sucursal.id+','+true+')');
		    		return btn;
				} else if(o.fechaInicio!=null && o.fechaTermino==null){
					$(btn).attr('value','Cerrar');
	    			$(btn).attr('onclick','abrirCerrarEnvio('+o.sucursal.id+','+false+')');
	    			return btn;
				} else{
					return 'Sin OTs para despacho';
				}
		    }},{findName:'sucursal.glosa',propiedad:function(o){
				if(o.sucursal != null){
					return o.sucursal.id +' '+ o.sucursal.glosa;
				} else {
					return '';
				}
		    }}
		],
		buttons : [
		           	{name: 'Ver OTs listas para despacho', bclass: 'btnLista', onpress : function(){mostrarListaOT();} },
		           	{separator: true},
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true},
			   		],
	});
	
	var exportDocument = function(type){
		var p = $('#envios').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#envios').getSortName()[0];
		p.sortOrder = $('#envios').getSortOrder()[0];
		var f = $('#envios').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=DespachosSucReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "DespachosSucReport", 600, 800);
	};
	
	$('#envios').loadData([{}]);
	
	
	$('#oTsListasParaDespacho').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		Volver: function(){
			$('#crearInventario').dialog('close');
		}
	});
	
	$('#oTsListas').flexigrid({
		dwrFunction: SSTFacade.listOTListasParaDespachoSucursal,
		tipo:'OTPD',
		multisel:false,
		singleSelect:true,
		idProperty: 'id',
		height:280,
		width:750,
		seccion:1000800100,
		buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOTs('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOTs('xls');} },
			   		{separator: true},
			   		],
	});
	
	var mostrarListaOT = function(){
		$('#oTsListas').loadData([{}]);
		$('#oTsListasParaDespacho').dialog('open');
	};
	
	var exportDocumentOTs = function(type){
		var p = $('#oTsListas').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#oTsListas').getSortName()[0];
		p.sortOrder = $('#oTsListas').getSortOrder()[0];
		var f = $('#oTsListas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OTDespachosSucReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OTDespachosSucReport", 600, 800);
	};
});

var abrirCerrarEnvio = function(idSucursal,crear){
	if(crear){
		jConfirm('¿Esta seguro de que desea abrir esta sucursal para despacho?','CONFIRMACIÓN',function(r){
			if(r){
				SSTFacade.abrirCerrarEnvioSucursal(idSucursal,crear,{asycn:true,callback:function(){
					$('#envios').flexReload();
				}});
			}
		});
	} else {
		jConfirm('¿Esta seguro de que desea cerrar esta sucursal para despacho?','CONFIRMACIÓN',function(r){
			if(r){
				SSTFacade.abrirCerrarEnvioSucursal(idSucursal,crear,{asycn:true,callback:function(){
					$('#envios').flexReload();
				}});
			}
		});
	}
	
	
};