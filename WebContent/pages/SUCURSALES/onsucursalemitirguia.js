var moduloEmitirGuia;
var moduloEmitirGuiaAgrupada;
$(document).ready(function() {
	
	SSTFacade.getModuloByNombreUsuario('onsucursalemitirguiadespacho',{async:false, callback:function(modulo) {
		moduloEmitirGuia = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getModuloByNombreUsuario('onsucursalemitirguiaagrupada',{async:false, callback:function(modulo) {
		moduloEmitirGuiaAgrupada = modulo ? modulo : undefined;
	}});

	var exportDocument = function(type,nombreGrilla){
		var p = $('#' + nombreGrilla).getParameters()[0];
		p.orderBy = $('#' + nombreGrilla).getSortName()[0];
		p.sortOrder = $('#' + nombreGrilla).getSortOrder()[0];
		var f = $('#' + nombreGrilla).getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=GuiaPendienteReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&filter=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "GuiaPendienteReport", 600, 800);
	};
	
	var exportDocumentAgrupado = function(type){
		var p = {};
		p.orderBy = $('#guiasFF').getSortName()[0];
		p.sortOrder = $('#guiasFF').getSortOrder()[0];
		var f = $('#guiasFF').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=GuiaPendienteAgrupadaReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&filter=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "GuiaPendienteAgrupadaReport", 600, 800);
	};
	
	$("#guiasSTReparacion").flexigrid({
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listGuiasPendientesSucursal,
		tipo: 'guiaP',
		seccion: 80001000,
		overrideModel: [{findName:'destino.id',propiedad:function(o){
			if (o.destino == null)
				return "";
            if (o.destino.tipo == 'BR' || o.destino.tipo == 'CD')
            	return o.destino.id + ' ' + o.destino.nombre;
            if (o.destino.tipo == 'ST')
            	return o.destino.id + ' ' + o.destino.nombre + ', ' + o.destino.direccion;
            if (o.destino.tipo == 'UA')
            	return o.destino.nombre;
		}},{findName:'imprimir',propiedad:function(o){
			if (moduloEmitirGuia) {
				return $('<input type="button" value="Imprimir Guía" onclick="emitirGuia(' + o.idOT + ',' + o.id + ')">');
			}
            return '';
		}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf','guiasSTReparacion');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls','guiasSTReparacion');}},
	   		{separator: true}
   		]
	});
	
	$("#guiasSTCambio").flexigrid({
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listGuiasPendientesSucursal,
		tipo: 'guiaP',
		seccion: 80004000,
		overrideModel: [{findName:'destino.id',propiedad:function(o){
			if (o.destino == null)
				return "";
            if (o.destino.tipo == 'BR' || o.destino.tipo == 'CD')
            	return o.destino.id + ' ' + o.destino.nombre;
            if (o.destino.tipo == 'ST')
            	return o.destino.id + ' ' + o.destino.nombre + ', ' + o.destino.direccion;
            if (o.destino.tipo == 'UA')
            	return o.destino.nombre;
		}},{findName:'imprimir',propiedad:function(o){
			if (moduloEmitirGuia) {
				return $('<input type="button" value="Imprimir Guía" onclick="emitirGuia(' + o.idOT + ',' + o.id + ')">');
			}
            return '';
		}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf','guiasSTCambio');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls','guiasSTCambio');}},
	   		{separator: true}
   		]
	});
	
	$("#guiasFF").flexigrid({
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listGuiasPendientesAgrupadasSucursal,
		tipo: 'guiaA',
		seccion: 80001000,
		overrideModel: [{findName:'destino.id',propiedad:function(o){
			if (o.destino == null)
				return "";
            if (o.destino.tipo == 'BR' || o.destino.tipo == 'CD')
            	return o.destino.id + ' ' + o.destino.nombre;
            if (o.destino.tipo == 'ST')
            	return o.destino.id + ' ' + o.destino.nombre + ', ' + o.destino.direccion;
            if (o.destino.tipo == 'UA')
            	return o.destino.nombre;
		}},{findName:'imprimir',propiedad:function(o){
			if (moduloEmitirGuia) {
				return $('<input type="button" value="Imprimir Guía" onclick="emitirGuiaAgrupada(' + o.id + ')">');
			}
            return '';
		}}],
		buttons : [
		    {name: 'Procesar OT No Enviadas', bclass: 'btnProcesar', onpress: function(){
		    		SSTFacade.processGuiasPendientesAgrupadas({async:true,callback:function(){
		    			$("#guiasFF").loadData([]);
		    			SSTFacade.getTotalGuiasAgrupadasPendientesSinEmitir({},{async:true,callback:function(pendientes){
		    				$('#onguiasFFTab a').text('('+pendientes +') '+'Fallas de Fabricación');
		    			}});
		    		}});
		    	}
		    },
	   		{separator: true},
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentAgrupado('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentAgrupado('xls');}},
	   		{separator: true}
   		]
	});
	
	$('#guiasCliente').flexigrid({
		showToggleBtn: true,
		multisel:false,
		singleSelect: true,
		dwrFunction: SSTFacade.listGuiasPendientesSucursal,
		tipo: 'guiaP',
		seccion: 80005000,
		overrideModel: [{findName:'destino.id',propiedad:function(o){
			if (o.destino == null)
				return "";
            if (o.destino.tipo == 'BR' || o.destino.tipo == 'CD')
            	return o.destino.id + ' ' + o.destino.nombre;
            if (o.destino.tipo == 'ST')
            	return o.destino.id + ' ' + o.destino.nombre + ', ' + o.destino.direccion;
            if (o.destino.tipo == 'UA')
            	return o.destino.nombre;
		}},{findName:'imprimir',propiedad:function(o){
			if (moduloEmitirGuia) {
				return $('<input type="button" value="Imprimir Guía" onclick="emitirGuia(' + o.idOT + ',' + o.id + ')">');
			}
            return '';
		}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf','guiasCliente');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls','guiasCliente');}},
	   		{separator: true}
   		]
	});

	$("#guiasSTReparacion").loadData([{tipoOT:'GP'}]);
	SSTFacade.getTotalGuiasPendientesBySucursalSinEmitir({tipoOT:'GP'},{async:true,callback:function(pendientes){
		$('#onguiasSTReparacionTab a').text('('+pendientes +') ' + $('#onguiasSTReparacionTab a').text());
	}});
	
	$("#guiasSTCambio").loadData([{tipoOT:'CA'}]);
	SSTFacade.getTotalGuiasPendientesBySucursalSinEmitir({tipoOT:'CA'},{async:true,callback:function(pendientes){
		$('#onguiasSTCambioTab a').text('('+pendientes +') ' + $('#onguiasSTCambioTab a').text());
	}});
	
	$("#guiasCliente").loadData([{tipoOT:'CL'}]);
	SSTFacade.getTotalGuiasPendientesBySucursalSinEmitir({tipoOT:'CL'},{async:true,callback:function(pendientes){
		$('#onguiasClienteTab a').text('('+pendientes +') ' + $('#onguiasClienteTab a').text());
	}});
	
	$("#guiasFF").loadData([]);
	SSTFacade.getTotalGuiasAgrupadasPendientesSinEmitir({},{async:true,callback:function(pendientes){
		$('#onguiasFFTab a').text('('+pendientes +') ' + $('#onguiasFFTab a').text());
	}});
	
	$("#tabs").tabs();
	
	$('#onguiasFFTab > a').click(function(){
		$('#guiasFF').recalcLayout();
	});
	$('#onguiasSTCambioTab > a').click(function(){
		$('#guiasSTCambio').recalcLayout();
	});
	$('#onguiasSTReparacionTab > a').click(function(){
		$('#guiasSTReparacion').recalcLayout();
	});
	$('#onguiasEnvioRetiroTab > a').click(function(){
		$('#guiasEnvioRetiro').recalcLayout();
	});
	
	$('#onguiasEnvioRetiroTab a').text('cliente');
});

var emitirGuiaAgrupada = function(idGuia) {
	parent.location = context + '/index.do?e=' + moduloEmitirGuiaAgrupada.codigo + '&m=' + moduloInicial.codigo + '&idGuia=' + idGuia;
};

var emitirGuia = function(idOt,idGuia) {
	parent.location = context + '/index.do?e=' + moduloEmitirGuia.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + idOt + '&idGuia=' + idGuia;
};
