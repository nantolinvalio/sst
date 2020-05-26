var idGuia;
var terminado;
var initonbodegaffdetalledesp = function() {

	$('#onbodegaffdetalledesp').click(function(){
//		$('#guias').recalcLayout();
		otsEscaneadas();
		SSTFacade.getDespachoById(despacho.id,{async:false,callback:function(despachoAux){
			despacho = despachoAux;
		}});
		loadGuia();
	});
	
	
	$('#guias').flexigrid({
		usepager:false,
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
		    }},{display: 'Transportista' ,width:260 ,align:'center'  ,name_abbr:'transportista',name:function(o){
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
		 			.css('width','60px')
		 			.addClass('required');
		    	return textPatente;
		   }},{display: 'Fecha' ,width:180 ,align:'center'  ,name_abbr:'fecha', format:'dd/MM/yyyy HH:mm',name:function(o){
		    	var textFecha = $('<input type="text">')
		 			.attr('id', 'fecha' + o.id)
		 			.attr('name', 'fecha' + o.id) 
		 			.css('margin','0')
//		 			.attr('format','dd-MM-yyyy hh:mm')
	 				.addClass('fechaHora dateTimeDDMMYYYYhhmm required');
		    	return textFecha;
		    }},{display: 'Sellos' ,width:100 ,align:'center'  ,name_abbr:'sellos',name:function(o){
		    	var sellos = $('<select></select>')
		 			.attr('id', 'sellos' + o.id)
		 			.attr('name', 'sellos'+ o.id)
		 			.css('margin','0');
		    	return sellos;
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
			    	if(o.estado.id != 50001000 && o.estado.id != 50005000){
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
		    	.attr('onclick','reemitirDetalleGuia('+o.id+')');
		    	if(o.estado.id != 50001000 && o.estado.id != 50005000){
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
				if(o.estado.id != 50001000 && o.estado.id != 50005000){
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
			SSTFacade.emitirGuiaDespachoAgrupada(despacho,guia,{async:false,callback:function(){
				loadGuia(); 
			}});
		}
};

//var reEmitir = function(id){
//	reemitirDetalleGuia(id);
//};

var reemitirDetalleGuia = function(id){
	
	SSTFacade.getGuiaById(id,{async:false,callback:function(g){
		guia = g;
	}});
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para las órdenes de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
		if (r) {
			SSTFacade.reEmitirGuiaDespachoAgrupadaHaciaDestino(despacho,guia, {async:true,callback:function(g){
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
					reemitirDetalleGuia(id);
				}
			});
		}
	}});
};


var confirmarGuia = function(id){
		SSTFacade.getGuiaById(id,{async:false,callback:function(g){
			guia = g;
		}});
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('Al confirmar la emisión de una guía de despacho no podra realizar nuevas modificaciones sobre la guía</br> ¿Esta seguro de confirmar la emisión?', 'Confirmación', function(r){
		if (r) {
			SSTFacade.confirmarGuiaDespachoAgrupada(guia,{async:true,callback:function(g){
				loadGuia();
				mostrarPestanas();
			}});
		}
	});
};

var validaGuia = function(guia){
	var valid = true;
	var existe;
	SSTFacade.getExisteNumero(guia.numero,{async:false,callback:function(data){
		existe = data;
	}});
	if(existe > 0){
		$('#guias').find('#numero'+guia.id).addClass('error');
		$('#guias').find('#numero'+guia.id).attr('title','Ingrese otro número de guía');
		valid = false;
	} else{ 
		$('#guias').find('#numero'+guia.id).removeClass('error');
		$('#guias').find('#numero'+guia.id).removeAttr('title');
	}
	if(guia.transportista.id == ""){
		$('#guias').find('#transportista'+guia.id).addClass('error');
		$('#guias').find('#transportista'+guia.id).attr('title','Seleccione un Transportista');
		valid = false;
	} else{
		$('#guias').find('#transportista'+guia.id).removeClass('error');
		$('#guias').find('#transportista'+guia.id).removeAttr('title');
	}
	if(guia.transportista.patente == ""){
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



var loadonbodegaffdetalledesp = function(despacho) {
	SSTFacade.getUbicacionById(despacho.destino.id,{async:false,callback:function(data){
		if(data == null){
			SSTFacade.getProveedorById(despacho.destino.id,{async:false,callback:function(proveedor){
				data = proveedor;
			}});
		}
		$('#destino').addItems([data], "id", "nombre", false);
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
				jAlert("No se han encontrado guías para el despacho número : " + despacho.id);
				$('#tabs').tabs('select', $('#tabs').find('#onbodegaffexternodetalleproducto').attr('href'));
				terminado = false;
			} 
			$('#guias').flexAddData({rows:guias,total:guias.length});
			$('.fechaHora').datetimepicker();
			$.each(guias, function(idx,guia) {
				if(guia.estado.id == 50001000){
					SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
						SSTFacade.listTransportista({async:false,callback:function(transportistas){
							$('#guias').find('#transportista'+guia.id).addItems(transportistas,"id","nombreCompleto",true);
							$('#guias').find('#transportista'+guia.id).val(guia.transportista.id);
						}});
						$('#guias').find('#patente'+guia.id).val(guia.transportista.patente);
						$('#guias').find('#numero'+guia.id).attr('value',(guia.numero+idx));
						SSTFacade.getSellosByIdGuia(guia.id,{async:false,callback:function(data){
							$('#guias').find('#sellos'+guia.id).addItems(data,"id","numero",false);
						}});
						terminado= false;
					}});
				} else {
					SSTFacade.getGuiaById(guia.id,{async:false,callback:function(guia){
						$('#guias').find('#numero'+guia.id).val(guia.numero);
						SSTFacade.listTransportista({async:false,callback:function(transportistas){
							$('#guias').find('#transportista'+guia.id).addItems(transportistas,"id","nombreCompleto",true);
							$('#guias').find('#transportista'+guia.id).val(guia.transportista.id);
						}});
						$('#guias').find('#patente'+guia.id).val(guia.transportista.patente);
						$('#guias').find('#fecha'+guia.id).val(guia.fechaEmision.toString('dd/MM/yyyy hh:mm'));//.toString('dd/MM/yyyy hh:mm')
						SSTFacade.getSellosByIdGuia(guia.id,{async:false,callback:function(data){
							$('#guias').find('#sellos'+guia.id).addItems(data,"id","numero",false);
						}});
					}});
				}
			});
		}});
		if(terminado){
			if(despacho.estado.id == 90001500)
			SSTFacade.updateEstadoFromDespachoToNextEstado(despacho,{async:false,callback:function(){}});
		}
	}
};