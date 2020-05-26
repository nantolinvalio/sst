var initonbodegaffdetalleproveedor = function() {
	
	$('#onbodegaffdetalleproveedor').click(function(){
		listarGuia(despacho,idGuia);
	});
	
	$('#guia\\.id').change(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = {
					idGuia : $('#guia\\.id').val(),
			};
			$('#proveedor').loadData([filtro]);
		}
	});
	
	$('#proveedor').flexigrid({
		dwrFunction: SSTFacade.listOtByIdGuiaAndClasificacionCpOrRp,
		seccion: 1000060000,
		tipo: 'OTSC',
			usepager:true,
			showToggleBtn: false,
			multisel:false,
			singleSelect:true,
			onDragCol: false,
			onToggleCol:false,
			saveCheckValues:true,
			dwrFunctionListAllId:SSTFacade.listAllOtByIdGuiaAndClasificacionCpOrRp,
			beforeChangePage : function(){
					$.alerts.okButton = '&nbsp;Grabar y continuar&nbsp;';
					$.alerts.cancelButton = '&nbsp;Continuar sin grabar&nbsp;';
					var sel = $("#proveedor").getJSONCheckboxSelected();
					var nosel = $("#proveedor").getJSONCheckboxUnselected();
//					jConfirm('¿Desea grabar los cambios realizados antes de continuar?', 'Confirmación', function(r){
//						if (r) {
					if(sel.length > 0){
						$.each(sel,function(index,ot){
							ot.recuperacion = $('#recuperacion' + ot.id).val();
						});
						SSTFacade.updateRecuperacionForOT(sel,{async:false,callback:function(){}});
					}
//						}
//					});
			},
			overrideModel: [{findName:'seleccionado',propiedad:function(o){
				var check = $('<input type="checkbox">')
					.attr('id', 'chk' + o.id)
					.attr('name', 'chk' + o.id)
					.attr('checked',$('#proveedor').isRowChecked(o.id))
					.attr('onchange','changeState("proveedor", ' + o.id + ',this)');
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
				   		{name: 'DM', bclass: 'btnNotaCredito', onpress : function(){generarDM();} },
				   		{separator: true},
				   		{name: 'Cerrar OT', bclass: 'btnRechazar', onpress : function(){cerrarOT();} },
				   		{separator: true},
				   		{name: 'Recibir', bclass: 'btnRecibir', onpress : function(){recibirOT();} },
				   		{separator: true}],
				   		
		});
	
	var generarSC = function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		var sel = $("#proveedor").getJSONCheckboxSelected();
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
				SSTFacade.getGuiaById(parseInt($('#guia\\.id').val()),{async:false,callback:function(guia){
					SSTFacade.generarSC(ots,despacho,guia,{async:false,callback:function(){
						 jAlert('Se genero correctamente la SC','AVISO');
						 $('#proveedor').flexReload();
					 }});
				}});
			}
		}});
	};
	
	var generarDM = function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para generar nota de credito','AVISO');
			}else{
//				$.each(ots,function(index,ot){
//					 ot.recuperacion = $('#recuperacion' + ot.id).val();
//				   });
				 
				 SSTFacade.generarDM(ots,despacho,$('#guia\\.id').text(),"STA",{async:false,callback:function(){
					 jAlert('Se genero correctamente la DM','AVISO');
					 $('#proveedor').flexReload();
				 }});
			}
		}});
	};
	
	var cerrarOT = function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para su cierre','AVISO');
			}else{
				 SSTFacade.cerrarOTByUsuario(ots,{async:false,callback:function(){
					 jAlert('Se han cerrado correctamente las OT','AVISO');
					 $('#proveedor').flexReload();
				 }});
			}
		}});
	};

	var recibirOT = function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			if(ots.length == 0){
				jAlert('No se ha seleccionado ninguna ot para recepción','AVISO');
			}else{
				$('#popupRecibir').dialog('open');
			}
		}});
	};

	$('#popupRecibir').dialog({
		autoOpen: false,
		modal:true,
		width:650,
		position: [300,150]
	});
	
	$('#DA').click(function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			var guia= {
					id:parseInt($('#guia\\.id').val()),
			};
			var clasificacion = {
					codigo: $('#DA').attr('id'),
			};
			SSTFacade.saveBitacoraHaciaDestino(ots,guia,clasificacion,{async:false,callback:function(){
				jAlert('La recepción se ha realizado con éxito','AVISO');
				$('#popupRecibir').dialog('close');
				$('#tabs').tabs('select', $('#tabs').find('#onbodegaffdetallefinaldespachos').attr('href'));
			}});
		}});
	});
	
	$('#10000').click(function(){
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro de enviar los productos seleccionados a la bodega 10000?', 'Confirmación', function(r){
			if (r) {
				var otsAux = $('#proveedor').getSelectedCheckFromList();
				SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
					var ordenes = "";
					$.each(ots,function(index,ot){
						if(index > 0){
							ordenes += ",";
						}
						ordenes += ot.id;
					});
					SSTFacade.saveGuiaHaciaBodegaDiezMil(ots,despacho,{async:true,callback:function(g){
							jAlert("se ha generado una nueva guia con las siguientes ots : "+ordenes+"."+"\n\n Confirme su emision en la pestaña detalle OT");
							$('#popupRecibir').dialog('close');
							listarGuia(despacho);
					}});
				}});
			}
		});
	});
	
	$('#AV').click(function(){
		var otsAux = $('#proveedor').getSelectedCheckFromList();
		SSTFacade.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(otsAux,{async:false,callback:function(ots){
			var guia= {
					id:parseInt($('#guia\\.id').val()),
			};
			var clasificacion = {
					codigo: $('#AV').attr('id'),
			};
			SSTFacade.saveBitacoraHaciaDestino(ots,guia,clasificacion,{async:false,callback:function(){
				jAlert('La recepción se ha realizado con éxito','AVISO');
				$('#popupRecibir').dialog('close');
				$('#tabs').tabs('select', $('#tabs').find('#onbodegaffdetallefinaldespachos').attr('href'));
			}});
		}});
	});
};

var listarGuia = function(despacho){
	SSTFacade.listGuiasByIdDespacho(despacho.id,{async:false,callback:function(guias){
		var emitidas = [];
			 $.each(guias,function(index,guia){
				  if(guia.estado.id == 50005000){
					  emitidas.push(guia);
				  }
			   });
			$("#guia\\.id").find('option').remove(); 
			$("#guia\\.id").addItems(emitidas, "id", "numero", false);
			var filtro = {
					idGuia : $('#guia\\.id').val(),
			};
			 $('#proveedor').loadData([filtro]);
	}});
};

var loadonbodegaffdetalleproveedor = function(despacho) {
	listarGuia(despacho);
};
