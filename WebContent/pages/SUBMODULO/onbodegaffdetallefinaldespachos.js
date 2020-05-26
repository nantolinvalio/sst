var idEstado;
var initonbodegaffdetallefinaldespachos = function() {
	
	$('#onbodegaffdetallefinaldespachos').click(function(){
		actualizaonbodegaffdetallefinaldespachos();
	});
	
	$('#popupOts').dialog({
		autoOpen: false,
		modal:true,
		width:850,
		position: [300,150]
	});
	
	$('#popupDocumentos').dialog({
		autoOpen: false,
		modal:true,
		width:850,
		position: [300,150]
	});
	
	$('#popupGuias').dialog({
		autoOpen: false,
		modal:true,
		width:1110,
		position: [117,150]
	});
	
	$('#factura, #notaCredito,#bodega,#cerradas,#vigentes,#aptoVenta,#dañado').click(function(){
		idEstado = this.name;
		if(idEstado == 100008000){
			$('#popupDocumentos').dialog('option','title','Peticion de facturas');
			$('#popupDocumentos').dialog('open');
			cargarDocumentos();
		} else if(idEstado == 100008100){
			$('#popupDocumentos').dialog('option','title','Peticion de notas de creditos');
			$('#popupDocumentos').dialog('open');
			cargarDocumentos();
		} else if(idEstado == 100010500){
			//$('#popupGuias').dialog('option','title','guias a la bodega 10000');
			$('#popupGuias').dialog('open');
			cargarGuias();
		} else if(idEstado == 100010600){
			$('#popupOts').dialog('option','title','OTs recibidas en da');
			$('#popupOts').dialog('open');
			cargarOT();
		} else if(idEstado == 100010700){
			$('#popupOts').dialog('option','title','OTs recibidas en av');
			$('#popupOts').dialog('open');
			cargarOT();
		} else if(idEstado == 100008200){
			$('#popupOts').dialog('option','title','OTs cerradas');
			$('#popupOts').dialog('open');
			cargarOT();
		} else {
			$('#popupOts').dialog('option','title','OTs sin acciones');
			$('#popupOts').dialog('open');
			cargarOT();
		}
	});
	
	$('#ots').flexigrid({
		usepager:false,
//		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		    {display: 'N° OT' ,width:80 ,align:'right'  ,name_abbr:'id',name:function(o){
		    	return o.id;
		    }},{display: 'Fecha creación' ,width:100 ,align:'center' , format:'dd/MM/yyyy HH:mm',name_abbr:'fecha',name:function(o){
		    	return o.fechaCreacion.toString('dd/MM/yyyy hh:mm');
		    }},{display: 'Estado' ,width:170 ,align:'left'  ,name_abbr:'estado',name:function(o){
		    	return o.estado.descripcion;
		    }},{display: 'Producto' ,width:200 ,align:'left'  ,name_abbr:'producto',name:function(o){
		    	return o.producto.descripcion;
		    }},{display: 'Marca' ,width:150 ,align:'left'  ,name_abbr:'marca',name:function(o){
		    	return o.producto.marca.nombre;
		    }}
		]
	});
	
	$('#documentos').flexigrid({
		usepager:false,
//		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		     {display: 'N° Documento' ,width:80 ,align:'right'  ,name_abbr:'numero',name:function(o){
		    	return o.numero;
		    }},{display: 'Tipo' ,width:100 ,align:'center' ,name_abbr:'tipo',name:function(o){
		    	return o.tipo;
		    }},{display: 'Facturar' ,width:170 ,align:'left'  ,name_abbr:'facturar',name:function(o){
		    	return o.facturar;
		    }}
		]
	});
	
	$('#guiasPopupFlexi').flexigrid({
		usepager:false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		    {display: 'N° Guía' ,width:70 ,align:'center'  ,name_abbr:'numero',name:function(o){
		    	var text = $('<input type="text">')
		 			.attr('id', 'numero' + o.id)
		 			.attr('name', 'numero' + o.id)
		 			.attr('maxlength','10')
		 			.css('width','70px')
		 			.addClass('required','number');
		    	return text;
		    }},{display: 'Transportista' ,width:260 ,align:'center'  ,name_abbr:'transportista',name:function(o){
		    	var select = $('<select></select>')
		 			.attr('id', 'transportista' + o.id)
		 			.attr('name', 'transportista'+ o.id)
		 			.css('margin','0')
		 			.css('width','250px')
		 			.addClass('required');
		    	return select;
		    }},{display: 'Patente' ,width:70 ,align:'center'  ,name_abbr:'patente',name:function(o){
		    	var textPatente = $('<input type="text">')
		 			.attr('id', 'patente' + o.id)
		 			.attr('name', 'patente' + o.id)
		 			.attr('maxlength','8')
		 			.css('width','60px')
		 			.addClass('required');
		    	return textPatente;
		   }},{display: 'Fecha' ,width:170 ,align:'center'  ,name_abbr:'fecha',name:function(o){
		    	var textFecha = $('<input type="text">')
		 			.attr('id', 'fecha' + o.id)
		 			.attr('name', 'fecha' + o.id) 
		 			.css('margin','0')
		 			.attr('format','dd-MM-yyyy hh:mm')
	 				.addClass('fechaHora dateTimeDDMMYYYYhhmm required');
		    	return textFecha;
		    }},{display: 'Grabar' ,width:90 ,align:'center'  ,name_abbr:'grabar',name:function(o){
			    	var btnGrabar = $('<input type="button">')
			    		.attr('id', 'grabar' + o.id)
			 			.attr('name', 'grabar' + o.id) 
				    	.attr('value','Grabar')
				    	.attr('onClick','grabar('+o.id+')');
				    	if(o.estado.id == 50001000){
				    		btnGrabar.removeAttr('disabled');
				    	}else{
				    		btnGrabar.attr('disabled','disabled');
				    	}
			    	return btnGrabar;
		    }},{display: 'Imprimir' ,width:90 ,align:'center'  ,name_abbr:'imprimir',name:function(o){
		    	var btnImprimir = $('<input type="button">')
		 			.attr('id', 'imprimir' + o.id)
		 			.attr('name', 'imprimir' + o.id) 
			    	.attr('value','Imprimir')
			    	.attr('onclick','imprimir('+o.id+')');
			    	if(o.estado.id != 50001000 && o.estado.id != 50002000){
			    		btnImprimir.removeAttr('disabled');
			    	}else {
			    		btnImprimir.attr('disabled','disabled');
			    	}
		    	return btnImprimir;
		    }},{display: 'Re Emitir' ,width:90 ,align:'center'  ,name_abbr:'reEmitir',name:function(o){
		    	var btnReEmitir = $('<input type="button">')
	 			.attr('id', 'reEmitir' + o.id)
	 			.attr('name', 'reEmitir' + o.id) 
		    	.attr('value','Re Emitir')
		    	.attr('onclick','reEmitirGuia('+o.id+')');
		    	if(o.estado.id != 50001000 && o.estado.id != 50002000){
		    		btnReEmitir.removeAttr('disabled');
		    	}else {
		    		btnReEmitir.attr('disabled','disabled');
		    	}
	    	return btnReEmitir;
	    }},{display: 'Confirmar' ,width:90 ,align:'center'  ,name_abbr:'confirmar',name:function(o){
	    	var btnConfirmar = $('<input type="button">')
 			.attr('id', 'confirmar' + o.id)
 			.attr('name', 'confirmar' + o.id) 
	    	.attr('value','Confirmar')
	    	.attr('onclick','confirmar('+o.id+')');
	    	if(o.estado.id != 50001000 && o.estado.id != 50002000){
	    		btnConfirmar.removeAttr('disabled');
	    	}else {
	    		btnConfirmar.attr('disabled','disabled');
	    	}
	    	return btnConfirmar;
	    }},
		]
	});
};


var grabar = function(id){
	var guia = {
			id: id,
			numero : $('#guiasPopupFlexi').find('#numero'+id).val(),
			fechaEmision : $('#guiasPopupFlexi').find('#fecha'+id).val(),
			};
			guia.transportista={
			id : $('#guiasPopupFlexi').find('#transportista'+id).val(),
			patente : $('#guiasPopupFlexi').find('#patente'+id).val()
			};
			guia.destino={
			id : 10000
			};
		var valid = validaFlexGuia(guia); 
		if(valid){
			SSTFacade.emitirGuiaAgrupada(guia, {async:false,callback:function(g){
				loadGuiasFlexi();
			}});
		}
};

var reEmitirGuia = function(id){
	reemitir(id);
};

var reemitir = function(id){
	var guia = {
			id: id,
			numero : $('#guiasPopupFlexi').find('#numero'+id).val(),
			fechaEmision : $('#guiasPopupFlexi').find('#fecha'+id).val(),
			};
			guia.transportista={
			id : $('#guiasPopupFlexi').find('#transportista'+id).val(),
			patente : $('#guiasPopupFlexi').find('#patente'+id).val()
			};
			guia.destino={
			id : 10000
			};
	var valid = validaFlexGuia(guia); 
	if(valid){
		SSTFacade.getGuiaById(id,{async:false,callback:function(g){
			guia = g;
		}});
	} else {return;}
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para las órdenes de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
		if (r) {
			SSTFacade.reEmitirGuiaAgrupada(guia, {async:true,callback:function(g){
				loadGuiasFlexi();
			}});
		}
	});
};

var imprimir = function(id){
	SSTFacade.validaStockGuiaAgrupada(id,{async:false, callback:function(existe){
		if(existe){
			var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=GuiaAgrupadaGIMReport" +
			"&idGuia=" + id;
			$.openWindowsMenubar(url, "GuiaAgrupadaGIMReport", 600, 800);
			$.alerts.okButton = '&nbsp;Confirmar Emisión&nbsp;';
			$.alerts.cancelButton = '&nbsp;Re emitir Guía&nbsp;';
			jConfirm('La guía de despacho está impresa y lista para ser confirmada. ¿Desea confirmar la impresión de la guía de despacho?', 'Confirmación', function(r){
				if (r) {
					confirmar(id);
				} else {
					reemitir(id);
				}
			});
		}
	}});
};

var confirmar = function(id){
	var guia = {
			id: id,
			numero : $('#guiasPopupFlexi').find('#numero'+id).val(),
			fechaEmision : $('#guiasPopupFlexi').find('#fecha'+id).val(),
			};
			guia.transportista={
			id : $('#guiasPopupFlexi').find('#transportista'+id).val(),
			patente : $('#guiasPopupFlexi').find('#patente'+id).val()
			};
			guia.destino={
			id : 10000
			};
	var valid = validaFlexGuia(guia); 
	if(valid){
		SSTFacade.getGuiaById(id,{async:false,callback:function(g){
			guia = g;
		}});
	} else {return;}
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('Al confirmar la emisión de una guía de despacho no podra realizar nuevas modificaciones sobre la guía</br> ¿Esta seguro de confirmar la emisión?', 'Confirmación', function(r){
		if (r) {
			SSTFacade.confirmarEmisionGuiaAgrupada(guia,{async:true,callback:function(g){
				loadGuiasFlexi();	
			}});
		}
	});
};

var cargarOT= function(){
	var filter = {
			idEstado : parseInt(idEstado),
			idDespacho : despacho.id
	};
	
	SSTFacade.listOtByDespachoAndEstado(filter,{async:false,callback:function(data){
		$('#ots').flexAddData({rows:data,total:data.length});
	}});
};

var cargarGuias = function(){
		loadGuiasFlexi();
};

var cargarDocumentos = function(){
	var filter = {
			idEstado : parseInt(idEstado),
			idDespacho : despacho.id
	};
	
	SSTFacade.listPeticionDocumentoByDespachoIterno(filter,{async:false,callback:function(data){
		$('#documentos').flexAddData({rows:data,total:data.length});
//		$('#documentos').recalcLayout();
		
	}});
};


var validaFlexGuia = function(guia){
	var valid = true;
	if(guia.transportista.id == ""){
		$('#guiasPopupFlexi').find('#transportista'+guia.id).addClass('error');
		$('#guiasPopupFlexi').find('#transportista'+guia.id).attr('title','Seleccione un Transportista');
		valid = false;
	} else{
		$('#guiasPopupFlexi').find('#transportista'+guia.id).removeClass('error');
		$('#guiasPopupFlexi').find('#transportista'+guia.id).removeAttr('title');
	}
	if(guia.transportista.patente == ""){
		$('#guiasPopupFlexi').find('#patente'+guia.id).addClass('error');
		$('#guiasPopupFlexi').find('#patente'+guia.id).attr('title','Ingrese un número de patente');
		valid = false;
	} else{
		$('#guiasPopupFlexi').find('#patente'+guia.id).removeClass('error');
		$('#guiasPopupFlexi').find('#patente'+guia.id).removeAttr('title');
	}
		if(guia.fechaEmision == ""){
		$('#guiasPopupFlexi').find('#fecha'+guia.id).addClass('error');
		$('#guiasPopupFlexi').find('#fecha'+guia.id).attr('title','Ingrese la fecha de emisión');
		valid = false;
	} else{
		$('#guiasPopupFlexi').find('#fecha'+guia.id).removeClass('error');
		$('#guiasPopupFlexi').find('#fecha'+guia.id).removeAttr('title');
	}
	return valid;
}; 

var loadGuiasFlexi = function(){
	SSTFacade.listGuiasToUbicacionDiezMilByidDespacho(despacho.id,{async:false,callback:function(guias){
		$('#guiasPopupFlexi').flexAddData({rows:guias,total:guias.length});
		$('.fechaHora').datetimepicker();
		$.each(guias, function(idx,guia) {
			if(guia.estado.id == 50001000){
				SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
					SSTFacade.listTransportista({async:false,callback:function(transportistas){
						$('#guiasPopupFlexi').find('#transportista'+guia.id).addItems(transportistas,"id","nombreCompleto",true);
					}});
					$('#guiasPopupFlexi').find('#numero'+guia.id).attr('value',(guia.numero+idx));
				}});
			} else {
				SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
					$('#guiasPopupFlexi').find('#numero'+guia.id).val(guia.numero);
					SSTFacade.listTransportista({async:false,callback:function(transportistas){
						$('#guiasPopupFlexi').find('#transportista'+guia.id).addItems(transportistas,"id","nombreCompleto",true);
						$('#guiasPopupFlexi').find('#transportista'+guia.id).val(guia.transportista.id);
					}});
					$('#guiasPopupFlexi').find('#patente'+guia.id).val(guia.transportista.patente);
					$('#guiasPopupFlexi').find('#fecha'+guia.id).val(guia.fechaEmision.toString('dd/MM/yyyy hh:mm'));
				}});
			}
		});
	}});
};

var loadonbodegaffdetallefinaldespachos = function() {
	SSTFacade.getIndicadoresbyidDespacho(despacho,{async:false,callback:function(detalle){
		if(detalle != null){
			$('#detalleForm').loadObject(detalle);
		}
	}});
};

var actualizaonbodegaffdetallefinaldespachos = function(){
	SSTFacade.getIndicadoresbyidDespacho(despacho,{async:false,callback:function(detalle){
		if(detalle != null){
			$('#detalleForm').loadObject(detalle);
		}
	}});
};
