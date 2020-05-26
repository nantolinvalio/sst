var initonbodegaffdetalleremate = function() {
	
	$('#onbodegaffdetalleremate').click(function(){
		listarGuiaRemate(despacho);
	});
	
	$('#idGuia').change(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#remate').loadData([filtro]);
		}
	});
	
	$('#remate').flexigrid({
		dwrFunction: SSTFacade.listOtByIdGuiaAndClasificacionAvOrDa,
		seccion: 1000060000,
		tipo: 'OTSC',
			usepager:true,
			showToggleBtn: false,
			multisel:false,
			singleSelect:true,
			onDragCol: false,
			onToggleCol:false,
			saveCheckValues:true,
			dwrFunctionListAllId:SSTFacade.listAllOtByIdGuiaAndClasificacionAvOrDa,
			beforeChangePage : function(){
				$.alerts.okButton = '&nbsp;Grabar y continuar&nbsp;';
				$.alerts.cancelButton = '&nbsp;Continuar sin grabar&nbsp;';
				var sel = $("#remate").getJSONCheckboxSelected();
				var nosel = $("#remate").getJSONCheckboxUnselected();
				jConfirm('¿Desea grabar los cambios de porcentaje recuperación antes de continuar?', 'Confirmación', function(r){
					if (r) {
						if(sel.length > 0){
							$.each(sel,function(index,ot){
								ot.recuperacion = $('#recuperacion' + ot.id).val();
							});
							SSTFacade.updateRecuperacionForOT(sel,{async:false,callback:function(){}});
						}
					}
				});
		},
			overrideModel: [{findName:'seleccionado',propiedad:function(o){
				var check = $('<input type="checkbox">')
					.attr('id', 'chk' + o.id)
					.attr('name', 'chk' + o.id)
					.attr('checked',$('#remate').isRowChecked(o.id))
					.attr('onchange','changeState("remate", ' + o.id + ',this)');
				return check;
		   	}},{findName:'recuperacion',propiedad:function(o){
		   		var recuperacion;
				SSTFacade.getOTById(o.id,{async:false,callback:function(ot){
					var porcentaje;
					if(ot.recuperacion == null){
						porcentaje = 100;
					} else {
						porcentaje = ot.recuperacion;
					}
					recuperacion = $('<input type="text">')
					.attr('id', 'recuperacion' + o.id)
					.attr('name', 'recuperacion' + o.id)
					.attr('maxlenght','3')
					.css('width','70px')
					.addClass('number')
					.attr('value',porcentaje);
				}});
				return recuperacion;
		   	}},
		   	],
			buttons : [
				   		{name: 'SC', bclass: 'btnFactura', onpress : function(){generarSC();} },
				   		{separator: true},
//				   		{name: 'DM', bclass: 'btnNotaCredito', onpress : function(){generarDM();} },
//				   		{separator: true},
				   		{name: 'Cerrar OT', bclass: 'btnRechazar', onpress : function(){cerrarOT();} },
				   		{separator: true}],
				   		
		});
	
	var generarSC = function(){
		var otsAux = $('#remate').getSelectedCheckFromList();
		var sel = $("#remate").getJSONCheckboxSelected();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para generar factura','AVISO');
			}else{
				if(sel.length > 0){
					$.each(sel,function(index,ot){
						 ot.recuperacion = $('#recuperacion' + ot.id).val();
					   });
					SSTFacade.updateRecuperacionForOT(sel,{async:false,callback:function(){}});
				}
				SSTFacade.getGuiaById($('#idGuia').val(),{async:false,callback:function(guia){
					SSTFacade.generarSC(ots,despacho,guia,{async:false,callback:function(){
						 jAlert('Se genero correctamente la SC','AVISO');
						 $('#remate').flexReload();
					 }});
				}});
			}
		}});
	};
	
	var generarDM = function(){
		var otsAux = $('#remate').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para generar nota de credito','AVISO');
			}else{
				$.each(ots,function(index,ot){
					 ot.recuperacion = $('#recuperacion' + ot.id).val();
				   });
				 
				 SSTFacade.generarDM(ots,despacho,$('idGuia').text(),"STA",{async:false,callback:function(){
					 jAlert('Se genero correctamente la DM','AVISO');
					 $('#remate').flexReload();
				 }});
			}
		}});
	};
	
	var cerrarOT = function(){
		var otsAux = $('#remate').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para su cierre','AVISO');
			}else{
				 SSTFacade.cerrarOTByUsuario(ots,{async:false,callback:function(){
					 jAlert('Se han cerrado correctamente las OT','AVISO');
					 $('#remate').flexReload();
				 }});
			}
		}});
	};
};

var listarGuiaRemate = function(despacho){
	SSTFacade.listGuiasByIdDespacho(despacho.id,{async:false,callback:function(guias){
		var emitidas = [];
		 $.each(guias,function(index,guia){
			  if(guia.estado.id == 50005000){
				  emitidas.push(guia);
			  }
		   });
		$("#idGuia").find('option').remove(); 
		$("#idGuia").addItems(emitidas, "id", "numero", false);
		var filtro = $('#filtro_buscador').serializeObject();
		  $('#remate').loadData([filtro]);
	}});
};

var loadonbodegaffdetalleremate = function(despacho) {
	listarGuiaRemate(despacho);
};
