var idGuia;
var terminado;
var moduloDetalle;
var initonbodegadetalledesp = function() {
	
	SSTFacade.getModuloByNombreUsuario('onbodegadetalledespacho',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var moduloInicial;
	SSTFacade.getModuloByNombreUsuario('onmenuinicio',{async:false, callback:function(modulo) {
		moduloInicial = modulo ? modulo : undefined;
	}});
	
	$('#onbodegadetalledesp').click(function(){
//		$('#guias').recalcLayout();
		otParaEliminar();
		loadGuia();
	});
	
	
	$('#guias').flexigrid({
		usepager:false,
//		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		    {display: 'N° Guia' ,width:80 ,align:'center'  ,name_abbr:'numero',name:function(o){
		    	idGuia = o.id;
		    	var text = $('<input type="text">')
		 			.attr('id', 'numero' + o.id)
		 			.attr('name', 'numero' + o.id)
		 			.attr('maxlength','10')
		 			.css('width','70px')
		 			.addClass('required','number');
		    	return text;
		    }},{display: 'Transportista' ,width:270 ,align:'center'  ,name_abbr:'transportista',name:function(o){
		    	var select = $('<select></select>')
		 			.attr('id', 'transportista' + o.id)
		 			.attr('name', 'transportista'+ o.id)
		 			.css('margin','0')
		 			.addClass('required');
		    	return select;
		    }},{display: 'Patente' ,width:80 ,align:'center'  ,name_abbr:'patente',name:function(o){
		    	var textPatente = $('<input type="text">')
		 			.attr('id', 'patente' + o.id)
		 			.attr('name', 'patente' + o.id)
		 			.attr('maxlength','8')
		 			.css('width','70px')
		 			.addClass('required');
		    	return textPatente;
		   }},{display: 'Fecha' ,width:180 ,align:'center'  ,name_abbr:'fecha',name:function(o){
		    	var textFecha = $('<input type="text">')
		 			.attr('id', 'fecha' + o.id)
		 			.attr('name', 'fecha' + o.id) 
		 			.css('margin','0')
		 			.attr('format','dd-MM-yyyy hh:mm')
	 				.addClass('fechaHora dateTimeDDMMYYYYhhmm required');
		    	return textFecha;
		    }},{display: 'Grabar' ,width:100 ,align:'center'  ,name_abbr:'grabarGuia',name:function(o){
		    	var btnGrabar = $('<input type="button">')
	    		.attr('id', 'grabarGuia' + o.id)
	 			.attr('name', 'grabarGuia' + o.id) 
		    	.attr('value','Grabar')
		    	.attr('onClick','grabarGuia('+o.id+')');
		    	if(o.estado.id == 50001000){
		    		btnGrabar.removeAttr('disabled');
		    	}else{
		    		btnGrabar.attr('disabled','disabled');
		    	}
	    	return btnGrabar;
		    }},{display: 'Imprimir' ,width:100 ,align:'center'  ,name_abbr:'imprimirGuia',name:function(o){
		    	var btnImprimir = $('<input type="button">')
		 			.attr('id', 'imprimirGuia' + o.id)
		 			.attr('name', 'imprimirGuia' + o.id) 
			    	.attr('value','Imprimir')
			    	.attr('onclick','imprimirGuia('+o.id+')');
			    	if(o.estado.id != 50001000 && o.estado.id != 50002000){
			    		btnImprimir.removeAttr('disabled');
			    	}else {
			    		btnImprimir.attr('disabled','disabled');
			    	}
		    	return btnImprimir;
		    }},{display: 'Re Emitir' ,width:100 ,align:'center'  ,name_abbr:'reEmitirGuia',name:function(o){
		    	var btnReEmitir = $('<input type="button">')
					.attr('id', 'reEmitirGuia' + o.id)
					.attr('name', 'reEmitirGuia' + o.id) 
		    	.attr('value','Re Emitir')
		    	.attr('onclick','reEmitirGuia('+o.id+')');
		    	if(o.estado.id != 50001000 && o.estado.id != 50002000){
		    		btnReEmitir.removeAttr('disabled');
		    	}else {
		    		btnReEmitir.attr('disabled','disabled');
		    	}
		    	return btnReEmitir;
			}},{display: 'Confirmar' ,width:100 ,align:'center'  ,name_abbr:'confirmarGuia',name:function(o){
				var btnConfirmar = $('<input type="button">')
					.attr('id', 'confirmarGuia' + o.id)
					.attr('name', 'confirmarGuia' + o.id) 
				.attr('value','Confirmar')
				.attr('onclick','confirmarGuia('+o.id+')');
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
var grabarGuia = function(id){
	var guia = {
			id: id,
			numero : $('#guias').find('#numero'+id).val(),
			fechaEmision : $('#guias').find('#fecha'+id).val(),
	};
	
	guia.transportista={	
			id : $('#guias').find('#transportista'+id).val(),
			patente : $('#guias').find('#patente'+id).val()
	};
	
	guia.destino={
			id : $('#guiaDespacho').find('#destino').val()
	};
	var valid = validaGuia(guia);
	
	if(valid){
		SSTFacade.emitirGuiaAgrupada(guia,{async:false,callback:function(){
			loadGuia();
		}});
	}
};

var reEmitirGuia = function(id){
reemitir(id);
};

var reemitir = function(id){
var guia ={};
if ($('#guiaDespacho').valid()) {
	SSTFacade.getGuiaById(id,{async:false,callback:function(g){
		guia = g;
	}});
} else {return;}
$.alerts.okButton = '&nbsp;Continuar&nbsp;';
$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para las órdenes de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
	if (r) {
		SSTFacade.reEmitirGuiaAgrupada(guia, {async:true,callback:function(g){
			loadGuia();
		}});
	}
});
};
var imprimirGuia = function(id){
	SSTFacade.validaStockGuiaAgrupada(idGuia,{async:false, callback:function(existe){
		if(existe){
			var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=GuiaAgrupadaGIMReport" +
			"&idGuia=" + id;
			$.openWindowsMenubar(url, "GuiaAgrupadaGIMReport", 600, 800);
			$.alerts.okButton = '&nbsp;Confirmar Emisión&nbsp;';
			$.alerts.cancelButton = '&nbsp;Re emitir Guía&nbsp;';
			jConfirm('La guía de despacho está impresa y lista para ser confirmada. ¿Desea confirmar la impresión de la guía de despacho?', 'Confirmación', function(r){
				if (r) {
					confirmarGuia(id);
				} else {
					reemitir(id);
				}
				});
		}
	}});
};


var confirmarGuia = function(id){
var guia ={};
if ($('#guiaDespacho').valid()) {
	SSTFacade.getGuiaById(id,{async:false,callback:function(g){
		guia = g;
	}});
} else {return;}
$.alerts.okButton = '&nbsp;Continuar&nbsp;';
$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
jConfirm('Al confirmar la emisión de una guía de despacho no podra realizar nuevas modificaciones sobre la guía</br> ¿Esta seguro de confirmar la emisión?', 'Confirmación', function(r){
	if (r) {
		SSTFacade.confirmarEmisionGuiaAgrupada(guia,{async:true,callback:function(g){
			if(terminado){
				if(despacho.estado.id == 90001500)
				SSTFacade.updateEstadoFromDespachoToNextEstado(despacho,{async:false,callback:function(){}});
			}
			loadGuia();
		}});
	}
});
};

var validaGuia = function(guia){
	var valid = true;
	var numeroGuiaSiguiente;
	SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
		numeroGuiaSiguiente = guia.numero;
	}});
	if(guia.numero == '' || guia.numero < numeroGuiaSiguiente){
		$('#guias').find('#numero'+guia.id).addClass('error');
		$('#guias').find('#numero'+guia.id).attr('title','Ingrese otro número de guía');
		valid = false;
	} else{ 
		$('#guias').find('#numero'+guia.id).removeClass('error');
		$('#guias').find('#numero'+guia.id).removeAttr('title');
	}
	if(guia.transportista != null && guia.transportista.id == ""){
		$('#guias').find('#transportista'+guia.id).addClass('error');
		$('#guias').find('#transportista'+guia.id).attr('title','Seleccione un Transportista');
		valid = false;
	} else{
		$('#guias').find('#transportista'+guia.id).removeClass('error');
		$('#guias').find('#transportista'+guia.id).removeAttr('title');
	}
	if(guia.transportista != null && guia.transportista.patente == ""){
		$('#guias').find('#patente'+guia.id).addClass('error');
		$('#guias').find('#patente'+guia.id).attr('title','Ingrese un número de patente');
		valid = false;
	} else{
		$('#guias').find('#patente'+guia.id).removeClass('error');
		$('#guias').find('#patente'+guia.id).removeAttr('title');
	}
	if(guia.fechaEmision == ""){
		$('#guias').find('#fecha'+guia.id).addClass('error');
		$('#guias').find('#fecha'+guia.id).attr('title','Ingrese la fecha de emisión');
		valid = false;
	} else{
		$('#guias').find('#fecha'+guia.id).removeClass('error');
		$('#guias').find('#fecha'+guia.id).removeAttr('title');
	}
	return valid;
};

var loadonbodegadetalledesp = function(despacho) {
	SSTFacade.listBodegasNotMe({async:false,callback:function(data){
		data.reverse();	
		$('#destino').addItems(data, "id", ["id","nombre"], true);
		$('#destino').val(10012);
		$('#destino').attr('disabled','disabled');
	}});
};

var loadGuia = function(){
	SSTFacade.getDespachoById(despacho.id,{async:false,callback:function(despachoAux){
		despacho = despachoAux;
	}});
	if(despacho.estado.id != 90001000){
		terminado = true;
		SSTFacade.listGuiasByIdDespacho(despacho.id,{async:false,callback:function(guias){
			if(guias.length == 0){
				SSTFacade.processGuiasGim(despacho,{async:false,callback:function(guiasCreadas){
					guias = guiasCreadas;
				}});
			} 
			$('#guias').flexAddData({rows:guias,total:guias.length});
			$('.fechaHora').datetimepicker();
			$.each(guias, function(idx,guia) {
				if(guia.estado.id == 50001000){
					SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
						SSTFacade.listTransportista({async:false,callback:function(transportistas){
							$('#guias').find('select').addItems(transportistas,"id","nombreCompleto",true);
						}});
						$('#guias').find('#numero'+guia.id).attr('value',(guia.numero+idx));
						terminado= false;
				}});
				} else {
					SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
						$('#guias').find('#numero'+guia.id).val(guia.numero);
						SSTFacade.listTransportista({async:false,callback:function(transportistas){
							$('#guias').find('select').addItems(transportistas,"id","nombreCompleto",true);
							$('#guias').find('#transportista'+guia.id).val(guia.transportista.id);
						}});
						$('#guias').find('#patente'+guia.id).val(guia.transportista.patente);
						$('#guias').find('#fecha'+guia.id).val(guia.fechaEmision.toString('dd/MM/yyyy hh:mm'));
				}});
				}
			});

		}});
	}
};
