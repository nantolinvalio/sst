$(document).ready(function() {
	
	var idGuia = $("#idGuia").val();
	
	$('#guiaCliente').validate();
	$('#guia').validate();
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegaffemitirguiadespacho',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var moduloAnterior;
	SSTFacade.getModuloByNombreUsuario('onmenuemitirguiaff',{async:false, callback:function(modulo) {
		moduloAnterior = modulo ? modulo : undefined;
	}});
	
	var fechaActual;
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	var minDate = fechaActual.clone().addDays(-1);
	var maxDate = fechaActual.clone().addDays(6);
	
	minDate = removeTimeFromDate(minDate.clone());
	maxDate = removeTimeFromDate(maxDate.clone());
	
	$('.fechaHora').datetimepicker({
		minDate: minDate,
		maxDate: maxDate
	});
	
	$('.fechaHora').rules('add',{dateRangeSingle:[minDate,maxDate,'dd/MM/yyyy HH:mm']});
	
	$('#grabar').click(function(){
		var guia = {};
		if ($('#guia').valid()) {
			guia = $('#guia').serializeObject();
		} else {return;}	
		guia.id = idGuia;
		SSTFacade.emitirGuiaAgrupada(guia, {async:true,callback:function(g){
			parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idGuia=" + g.id;
		}});
	});
	
	$('#reemitir').click(function(){
		var guia = {};
		if ($('#guia').valid()) {
			guia = $('#guia').serializeObject();
		} else {return;}
		guia.id = idGuia;
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para las órdenes de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.reEmitirGuiaAgrupadaFF(guia, {async:true,callback:function(g){
					parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idGuia=" + g.id;
				}});
			}
		});
	});
	
	var reemitir = function(){
		var guia = {};
		if ($('#guia').valid()) {
			guia = $('#guia').serializeObject();
		} else {return;}
		guia.id = idGuia;
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para las órdenes de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.reEmitirGuiaAgrupadaFF(guia, {async:true,callback:function(g){
					parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idGuia=" + g.id;
				}});
			}
		});
	};
	
	$('#confirmar').click(function(){
		
			var guia = {};
			if ($('#guia').valid()) {
				guia = $('#guia').serializeObject();
			} else {return;}
			guia.id = idGuia;
			var guiaAux;
			SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
				guiaAux = guia;
			}});
			$.alerts.okButton = '&nbsp;Continuar&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('Al confirmar la emisión de una guía de despacho no podra realizar nuevas modificaciones sobre la guía</br> ¿Esta seguro de confirmar la emisión?', 'Confirmación', function(r){
				if (r) {
					SSTFacade.confirmarEmisionGuiaAgrupada(guia,{async:true,callback:function(g){
						parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idGuia=" + g.id;
					}});
				}
			});
		
	});
	
	var confirmar = function(){
		var guia = {};
		if ($('#guia').valid()) {
			guia = $('#guia').serializeObject();
		} else {return;}
		guia.id = idGuia;
		var guiaAux;
		SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
			guiaAux = guia;
		}});
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('Al confirmar la emisión de una guía de despacho no podra realizar nuevas modificaciones sobre la guía</br> ¿Esta seguro de confirmar la emisión?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.confirmarEmisionGuiaAgrupada(guia,{async:true,callback:function(g){
					parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idGuia=" + g.id;
				}});
			}
		});
	};
	
	//Inicio Pestaña Guia
	SSTFacade.listTransportista({async:false,callback:function(transportistas){
		$('#transportista\\.id').addItems(transportistas,"id","nombreCompleto",false);
	}});

	
	SSTFacade.getUbicacionTransportista({async:false,callback:function(cd){
		$('#guia').find('#destino\\.id').addItems([cd],"id",["tipo","nombre"],false," : ");
	}});		
	
	
	var exportDocument = function(type){
		var p = $('#ots').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#ots').getSortName()[0];
		p.sortOrder = $('#ots').getSortOrder()[0];
		var f = $('#ots').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoCambioAutomaticoReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "" +
				"CambioAutomaticoReport", 600, 800);
	};
	//Fin Pestaña Guia
	
	$('#ots').flexigrid({
		dwrFunction: SSTFacade.listOTCambioAuomaticoByFilter,
		seccion: 80001000,
		showTableToggleBtn: true,
		tipo: 'OTAC',
		multisel:false,
		singleSelect:true,
		overrideModel: [{findName:'tipoCambio.codigo',propiedad:function(o){
        	return o.tipoCambio.glosa;
    	}},{findName:'sucursal.id',propiedad:function(o){
    		if (o.sucursal == null)
    			return '';
			return o.sucursal.id + ' ' + o.sucursal.glosa;
    	}},{findName:'producto.marca.codigo',propiedad:function(o){
    		if (o.producto != null && o.producto.marca != null && o.producto.marca.nombre != null)
            	return o.producto.marca.nombre;
    	}},{findName:'sucursal.zona.id',propiedad:function(o){
    		if(o.sucursal == null || o.sucursal.zona == null){
    			return '';
    		}else{
    			return o.sucursal.zona.nombre;
    		}
    	}},{findName:'producto.familia.id',propiedad:function(o){
        	return o.producto.familia.nombre;
    	}},{findName:'producto.familia.linea.id',propiedad:function(o){
        	return o.producto.familia.linea.glosa;
    	}},{findName:'producto.id',propiedad:function(o){
        	return o.producto.id + ' ' + o.producto.descripcion;
    	}}],
		buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
	});
	
	var loadGuia = function(){
		SSTFacade.getGuiaById(idGuia,{callback:function(guia){
			$('#grabar').attr('disabled','disabled');
			$('#imprimir').attr('disabled','disabled');
			$('#reemitir').attr('disabled','disabled');
			$('#confirmar').attr('disabled','disabled');
			
			$('#transportista\\.nombre').text(guia.transportista.nombre);
			$('#transportista\\.id').val(guia.transportista.id);
			
			guia.fechaEmision = guia.fechaEmision == null ? fechaActual : guia.fechaEmision;
			if (guia.destino != null && guia.destino.id != null) {
				destino = guia.destino.id;				
			}
			$('#guia').loadObject(guia);
			if (guia.estado.id == 50001000) {
				$('#grabar').removeAttr('disabled');
			} else {
				if (guia.estado.id == 50001500) {
					$('#reemitir').removeAttr('disabled');
					$('#imprimir').removeAttr('disabled');
					$('#confirmar').removeAttr('disabled');
				}
			}
		}});		
	};
	
	loadGuia();
	$("#ots").loadData([{idGuiaAgrupada:idGuia}]);
	
	$('#volver').click(function(){
		parent.location = context + '/index.do?e=' + moduloAnterior.codigo + '&m=' + moduloInicial.codigo;
	});
	
//	$('#imprimir').click(function(){
//		var url = "/sst/ViewReportServlet?type=pdf" + 
//			"&report=GuiaAgrupadaReport" +
//			"&idGuia=" + idGuia;
//		$.openWindowsMenubar(url, "GuiaDetalleReport", 600, 800);
//	});
	
	$('#imprimir').click(function(){
		SSTFacade.validaStockGuiaAgrupada(idGuia,{async:false, callback:function(existe){
			if(existe){
				var url = "/sst/ViewReportServlet?type=pdf" + 
				"&report=GuiaAgrupadaReport" +
				"&idGuia=" + idGuia;
				$.openWindowsMenubar(url, "GuiaDetalleReport", 600, 800);
				$.alerts.okButton = '&nbsp;Confirmar Emisión&nbsp;';
				$.alerts.cancelButton = '&nbsp;Re emitir Guía&nbsp;';
				jConfirm('La guía de despacho está impresa y lista para ser confirmada. ¿Desea confirmar la impresión de la guía de despacho?', 'Confirmación', function(r){
					if (r) {
						confirmar();
					} else {
						reemitir();
					}
				});
			}
		}});
		
	
	});
	
	$("#tabs").tabs();
});


