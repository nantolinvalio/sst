$(document).ready(function(){
	
	var moduloAccesorios;
	SSTFacade.getModuloByNombreUsuario('onsucursalaccesorios',{async:false, callback:function(modulo) {
		moduloAccesorios = modulo ? modulo : undefined;
	}});
	
	var moduloBuscar;
	SSTFacade.getModuloByNombreUsuario('onsucursalbuscardocumento',{async:false, callback:function(modulo) {
		moduloBuscar = modulo ? modulo : undefined;
	}});	
	
	var moduloTerminar;
	SSTFacade.getModuloByNombreUsuario('onsucursalterminar',{async:false, callback:function(modulo) {
		moduloTerminar = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getCodigoBarraObligatorio({async:false,callback:function(resp){
		if (resp) {
			$('#codigoBarra').addClass('required');			
		}
	}});
	
	$('#codigoBarra').blur(function(){
		if ($(this).val() != "" && $(this).valid()) {
			$(this).val($.leftPad($(this).val(),10,'0'));
		}
	});
	
	var idOT = parseInt($('#idOT').text());
	
	var oT;
	SSTFacade.getOTById(idOT,{async:false,callback:function(OT){
		$('#datos').loadObject(OT);
		if(OT.descripcionFalla==null){
			$('.descripcionFalla').css('display','none');	
		}
		oT=OT;
		
		$('#datos').loadObject(OT);
		$('#detallePartes').loadObject(OT);
		
		if(OT.descripcionFalla==null){
			$('.descripcionFalla').css('display','none');	
		}
//		
		if(OT.producto!=null && OT.producto.familia != null & OT.producto.familia.id !=null && (OT.producto.familia.id==259/*CELULARES PREPAGO*/ || OT.producto.familia.id==267 /*CELULARES LIBRES*/ || OT.producto.familia.id==268/*CELULARES A CONTRATO*/|| OT.producto.familia.id==260/*CELULARES PREPAGO SMARTPHONE*/)){
			$('#numeroCelular, #imei').addClass('required');
			$('.numeroIncidente').hide();
			$('.numeroSerie').hide();
		} else {
			$('.numeroCelular, .imei, [for="imei"]').hide();
			if(OT.producto.familia.id==461){
				$('#numeroIncidente').addClass('required');
			}
		}
		
		if(OT.tipo.codigo == 'BT'){
			$('#numeroAtencion').addClass('required');
		} else {
			$('.numeroAtencion').hide();
		}
		
		SSTFacade.isFamiliaExcluidaNumeroSerieByProducto(OT.producto,{async:false,callback:function(excluida){
			if(excluida == true) {
				$('#numeroSerie, [for="numeroSerie"]').hide();
				$('#descripcionEstado').addClass('required');
			} else {
				$('#numeroSerie').addClass('required');
			}
		}});
	}});
	
//	var tipoCambio='';
	if (oT.cambioAutorizado) {
//	if(oT.tipo!=null && oT.tipo.codigo!=null && (oT.tipo.codigo == 'CA' || (oT.tipo.codigo == 'GP' && oT.tipoCambio != null))){
		$('#tituloPagina').text('Recepción de producto con autorización de cambio' + (oT.tipo.codigo == 'GM' ? '. Garantía Master' : ''));
		$('.gProveedor, .gMaster').css('display','none');
//		if(oT.tipoCambio.codigo=="JT"){
//			tipoCambio=oT.tipoCambioJT.codigo;
//		}
//		else{
//			tipoCambio=oT.tipoCambio.codigo;
//		}
	}
	
	$('#partes').flexigrid({
		height:'auto',
		usepager:false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		showToggleBtn:false,
		colModel: [
		    {display: 'Buen Estado' ,width:70 ,align:'center' ,name_abbr:'malEstado', name:function(o){
				var rd = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','B')
							.attr('onclick','onCheckPartes(true,' + o.id +')')
							.attr('checked',o.estado!=null || o.estado=='B');
		    	return rd;
		    }},
		    {display: 'Mal Estado' , width:70 ,align:'center', name_abbr:'estado' ,name:function(o){
				var rd = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','M')
							.attr('onclick','onCheckPartes(false,' + o.id +')')
							.attr('checked',o.estado!=null && o.estado=='M');
		    	return rd;
		    }},
		    {display: 'Parte del producto' ,width:300 ,align:'left', name_abbr:'glosa' ,name:'glosa'},
		    {display: 'Observación'        ,width:405 ,align:'center', name_abbr:'observacion' ,name:function(o){
		    	if (o.observaciones != null) {
		    		o.observaciones = o.observaciones.split("::");
		    		if (o.observaciones.length > 0) {
		    			o.observaciones = o.observaciones[o.observaciones.length - 1];	    			
		    		}
		    	}
		    	var tx = $('<input type="text">')
			   			 .css('width','90%')
			   			 .addClass('required')
			   			 .attr('id','observacion' + o.id)
			   			 .attr('name','observacion' + o.id)
			   			 .attr('readonly',o.estado!=null && o.estado=='B')
			   			 .attr('maxlength','100')
			   			 .attr('value', o.observaciones);
		    	return tx;
		    }}
	    ]
	});
	
	SSTFacade.listPartesOTByOT(idOT,function(partes){
		if (partes && partes.length != 0) {
			$("#txtPartes").text('Revisar partes del producto');
			$("#partes").parent().parent().show();
			$("#partes").flexAddData({rows:partes,total:partes.length});				
		} else {
			$("#txtPartes").text('Producto sin partes, continue');
			$("#partes").parent().parent().hide();
		}
	});
	
	SSTFacade.listAccesoriosByOT(idOT,{async:true,callback:function(accesoriosOT){
		if(accesoriosOT.length>0){
			var text='';
			$.each(accesoriosOT,function(index,value){
				if (value.ubicacion.id == ubicacion.id)
					text+=value.descripcion + '; ';					
			});
			$('#accesorios').text(text);
		} else {
			$('.accesoriosOT').css('display','none');
		}
	}});
	
	SSTFacade.listTipoFallasByOT(idOT,{async:true,callback:function(tipoFallas){
		var text ="";
		if(tipoFallas!=null && tipoFallas.length >0){
			$.each(tipoFallas,function(index,value){
				text =text+value.descripcion;
				text=text+(tipoFallas.length>(index+1)? ', ':'');
			});
		} else {
			$('.tipoFallas').css("display","none");
		}
		$('#tipoFallas').text(text);
	}});	
	
	var validarPartes = function(){
		var valid = true;
		var partes = $("#partes").getJSONFromHTML();
		
		$.each(partes, function(idx,parte) {
			if (parte.estado == "M" && !$("#partes").find('#observacion' + parte.id).valid()) {
				valid = false;
			}	
		});
		return valid;
	};
	
	$('#siguiente').click(function(){
		if($('#numeroSerie').val() == ''){
			$('#numeroSerie').val($('#imei').val());
		}
		
		if($('#detallePartes').valid() & validarPartes()){
			oT.numeroSerie = $('#numeroSerie').val();
			oT.codigoBarra = $('#codigoBarra').val();
			oT.imei=$('#imei').val();
			var rtn = false;
			SSTFacade.validaCodigosDeBarraOT(oT,{async:false,callback:function(resp){
				if (!resp) {
					$('#codigoBarra').addClass('error');
					$('#codigoBarra').attr('title', 'El código de barras ya está en uso');
					rtn = true;
				}
			}});
			
			if (rtn) return;
			
			var parts = $("#partes").getJSONFromHTML();
			
			SSTFacade.validarFallaReiterada(oT,{async:true,callback:function(fallaReiterada){
				if(fallaReiterada){
					jAlert('No se puede continuar con la orden de trabajo por que el producto presenta una falla reiterada. Debe autorizar el cambio del producto','Confirmación',function(r){
						if(r){
							oT.banderaOrigenOT = "FR";
							//alert(oT.banderaOrigenOT);
							SSTFacade.updateOTFallaReiterada(oT,{async:false,callback:function(ordenTrabajo){
								oT=ordenTrabajo;
							}});
							$.each(parts,function(index, parte){
								parte.idOT=idOT;
								parte.estado=$('input[name="estado'+parte.id+'"]:checked').val();
								parte.observaciones=$('#observacion'+parte.id).val();
							});
							SSTFacade.saveParteOT(parts,{async:false, callback:function(guardo){}});
							
							//aqui guardaremos la bandera que nos dice que es falla reiterada
							//alert("-----> Antes del llamar SSTFacade.updateOTRevision: "+oT.banderaOrigenOT);
							SSTFacade.updateOTRevision({banderaOrigenOT:oT.banderaOrigenOT, id:idOT,descripcionEstado:$('#descripcionEstado').val(), numeroSerie:$('#numeroSerie').val(), numeroTelefono:$('#numeroCelular').val(),codigoBarra:$('#codigoBarra').val(),numeroAtencion:$('#numeroAtencion').val(),imei:$('#imei').val(),numeroIncidenteMarca:$('#numeroIncidente').val()},{async:false,callback:function(actualizo){}});
							
							var moduloTerminar;
							SSTFacade.getModuloByNombreUsuario('onsucursalterminar',{async:false, callback:function(modulo) {
								moduloTerminar = modulo ? modulo : undefined;
							}});
							
							SSTFacade.listAccesoriosByOT(idOT,{async:false,callback:function(accesoriosOT){
								var todosLosAccesorios = true;
								$.each(accesoriosOT, function(index,accesorio){
									if(accesorio.ubicacion.tipo=='CL'){
										todosLosAccesorios = false;
										return false;
									}
								});
								
								if(todosLosAccesorios){
									parent.location = context+'/index.do?e='+moduloTerminar.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
								} else {
									jConfirm('Para realizar un cambio automático se requieren todo los accesorios\n\nSi desea ingresar los accesorios ahora presione "OK" o "Cancel" para desactivar la orden de trabajo y que el cliente vuelva más tarde con el producto y los accesorios','Aviso',function(r){
										if(r){
											parent.location = context+'/index.do?e='+moduloAccesorios.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
										}else{
											jAlert('El cliente no trajo los accesorios requeridos a la sucursal','Aviso',function(r){
												if(r){
													parent.location = context + '/index.do?e='+moduloBuscar.codigo+'&m='+moduloInicial.codigo;
												}
											});
										}
									});
								}
								
							}});
						}
					});
				} else {
					$.each(parts,function(index, parte){
						parte.idOT=idOT;
						parte.estado=$('input[name="estado'+parte.id+'"]:checked').val();
						parte.observacion=$('#observacion'+parte.id).val();
					});
					SSTFacade.saveParteOT(parts,{async:false, callback:function(guardo){}});
					//alert("banderaOrigen: "+oT.banderaOrigenOT);
					SSTFacade.updateOTRevision({banderaOrigenOT:oT.banderaOrigenOT, id:idOT, descripcionEstado:$('#descripcionEstado').val(), numeroSerie:$('#numeroSerie').val(), numeroTelefono:$('#numeroCelular').val(),codigoBarra:$('#codigoBarra').val(),numeroAtencion:$('#numeroAtencion').val(),imei:$('#imei').val(),numeroIncidenteMarca:$('#numeroIncidente').val()},{async:false,callback:function(actualizo){
						parent.location = context+'/index.do?e='+moduloTerminar.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;						
					}});
				}
				
			}});
		}
	});
	
	$('#volver').click(function(){
		parent.location = context+'/index.do?e='+moduloAccesorios.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
	});
	
	$('#descripcionEstado').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 254, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(256), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
	
});

var onCheckPartes = function(val,id){
	$("#partes").find('#observacion' + id).val('');
	$("#partes").find('#observacion' + id).attr('readonly',val);
	$("#partes").find('#observacion' + id).removeClass('error');
};


