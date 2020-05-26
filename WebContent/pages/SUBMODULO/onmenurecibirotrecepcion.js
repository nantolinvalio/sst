var initonmenurecibirotrecepcion = function() {
	$('#condiciones').maxlength({  
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
	
	$('#rechazarRecepcion').click(function(){
		$('#condiciones').addClass('required');
		var valid = true;
		if(!validaonmenurecibirotproducto()) {
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert('Falta informacion en la pestaña Producto.','Error');
			valid = false;
		}
		if (!$('#recepcion').valid())
			valid = false;
		if (!$('#recepcionObs').valid())
			valid = false;
		if(valid) {
			
			var recepcion = { 
				guia: $('#recepcion').serializeObject().guia,
				fechaRecepcion : $('#fechaRecepcion').val(),
				observacion :  $("#condiciones").val(),
				ubicacion : ubicacion
			};
		
			SSTFacade.getUbicacionOT(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
				recepcion.origen = ubicacionOT;
			}});
			
			recepcion.guia.ordenTrabajo = {
			   id : ordenTrabajo.id,
			   numeroSerie : $("#numeroSerie").val()
		   	};
			
			var accesorios = $("#accesoriosOT").getJSONFromHTML();
			var partes = $("#partesOT").getJSONFromHTML();
			
			$.alerts.okButton = '&nbsp;Devolver producto al origen&nbsp;';
	        $.alerts.cancelButton = '&nbsp;Enviar a ejecutiva&nbsp;';
			jConfirm( "Al rechazar esta recepción, usted como usuario de bodega puede "+
					"enviar el producto de vuelta a su origen, o enviar la tarea a la ejecutiva de marca",'Confirmación', function(r){
				if(r){
					$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
						SSTFacade.saveRecepcionOTRechazo(recepcion, false, partes, accesorios,{async:true,callback:function(){
							$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
							jAlert("La recepción se rechazo exitosamente, se creó la guía de despacho para enviar de regreso la orden de trabajo",'Aviso',function(){
								location.reload();
								//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
							});
						}});
					} else {
						SSTFacade.saveRecepcionOTRechazo(recepcion, true, partes, accesorios,{async:false,callback:function(){
							$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
							jAlert("La tarea fue enviada a la ejecutiva de marca",'Aviso',function(){
								location.reload();
								//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
							});
						}});
					}
					$('#condiciones').removeClass('required');
				});
		}
	
	});
	
	$('#aceptarConObservaciones').click(function(){
		var valid = true;
		$('#condiciones').addClass('required');
		if(!validaonmenurecibirotproducto()) {
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert('Falta informacion en la pestaña Producto.');
			valid = false;
		}
		
		if (!$('#recepcion').valid())
			valid = false;
		if (!$('#recepcionObs').valid())
			valid = false;

		if (valid){
			var recepcion = { 
					guia: $('#recepcion').serializeObject().guia,
					fechaRecepcion : $('#fechaRecepcion').val(),
					observacion :  $("#condiciones").val()
				};
				
				recepcion.guia.ordenTrabajo = {
				   id : ordenTrabajo.id,
				   numeroSerie : $("#numeroSerie").val()
			   	};
			
			var accesorios = $("#accesoriosOT").getJSONFromHTML();
			var partes = $("#partesOT").getJSONFromHTML();
			$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
			if($('#aceptarRecepcion').val() == 'Aceptar Accesorios'){
				SSTFacade.saveRecepcionGuiaAccesorios(recepcion, false, partes, accesorios,{async:false,callback:function(){
					$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
					jAlert("Recepcion exitosa",'Aviso',function(){
						location.reload();
						//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
					});
				}});
			}else{
				SSTFacade.saveRecepcionOT(recepcion, false, partes, accesorios,{async:false,callback:function(){
					$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
					jAlert("Recepcion exitosa",'Aviso',function(){
						location.reload();
						//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
					});
				}});
			}
		}});
	
	$('#aceptarRecepcion').click(function(){
		var valid = true;
		$('#condiciones').removeClass('required');
		$('#condiciones').removeClass('error');
		$('#condiciones').removeAttr('title');
		if(!validaonmenurecibirotproducto()) {
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert('Falta informacion en la pestaña Producto.','Aviso');
			valid = false;
		}
		if (!$('#recepcion').valid()) {
			valid = false;
		}
		
		if (valid){
			var acce ='';
			var accesorios = $("#accesoriosOT").getJSONFromHTML();
			$.each(accesorios, function(idx,accesorio) {
				if (accesorio.recibido == 'false'){
					acce += accesorio.descripcion + ', ';
				}
			});
			
			if(acce != ''){
				$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
				jAlert('advertencia, no se han recibido los accesorios: ' + acce + ' por esto no puede aceptar la recepcion, puede aceptarla con reparo o rechazarla','Aviso');
			} else {
				var recepcion = { 
					guia: $('#recepcion').serializeObject().guia,
					fechaRecepcion : $('#fechaRecepcion').val(),
					observacion :  $("#condiciones").val()
				};
				
				recepcion.guia.ordenTrabajo = {
				   id : ordenTrabajo.id,
				   numeroSerie : $("#numeroSerie").val()
			   	};
				var partes = $("#partesOT").getJSONFromHTML();
				$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
				if($('#aceptarRecepcion').val() == 'Aceptar Accesorios'){
					SSTFacade.saveRecepcionGuiaAccesorios(recepcion, true, partes, accesorios,{async:false,callback:function(){
						$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
						jAlert("Recepcion exitosa",'Aviso',function(){
							location.reload();
							//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
						});
					}});
				}else{
					SSTFacade.saveRecepcionOT(recepcion, true, partes, accesorios,{async:false,callback:function(){
						$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
						jAlert("Recepcion exitosa",'Aviso',function(){
							location.reload();
							//$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
						});
					}});
				}
			}
		}
	});
};

var loadonmenurecibirotrecepcion = function(ordenTrabajo) {
	var fechaActual;

	$('#recepcion').reset();
	$('#recepcionObs').reset();
	
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',false);
	
	var loadguiarecepcion = function(guia) {
		if (guia.tipoGuia == 'GACC'){
			SSTFacade.getUbicacionOTAccesorio(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
				loadGuia(guia,ubicacionOT);
			}});
		} else {
			SSTFacade.getUbicacionOT(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
				loadGuia(guia,ubicacionOT);
			}});
		}
	};
	
	var loadGuia = function (guia,ubicacionOT){
		var bloquear = false;
		var mostrar = false;
		if (guia.origen != null && guia.origen.tipo != "ST") {
			$('#guia\\.numero').attr('readonly', true);
		} else {
			$('#guia\\.numero').attr('readonly', false);
		}
		
		if (guia.fechaEmision != null) {
			if (guia.fechaEmision > fechaActual) {
				bloquear = true;
				mostrar = true;
			}
		} else {
			SSTFacade.getGuiaByIdOT(ordenTrabajo.id,{async:false,callback:function(guiaOT){
				var guiaOT = guiaOT == null ? {} : guiaOT;
				if(guiaOT.fechaEmision == null) {
					guiaOT.fechaEmision = fechaActual;
				} 
				if (guiaOT.fechaEmision > fechaActual) {
					bloquear = true;
					mostrar = true;
				}
			}});
		}	
		
		var objCarga = {
			guia : guia,
			ubicacionOT : ubicacionOT.tipo == 'ST' ? ubicacionOT.glosa + ': ' + ubicacionOT.nombre : ubicacionOT.glosa + ': ' + ubicacionOT.id + ' ' + ubicacionOT.nombre,
			direccion : ubicacionOT.direccion + ', ' + ubicacionOT.comuna.glosa,
			fechaRecepcion : fechaActual
		};
		
		$('#recepcion').loadObject(objCarga);
		$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',bloquear);
		
		if (mostrar) {
			$('#avisoFecha').show();
		} else {
			$('#avisoFecha').hide();
		}
		loadAccesoriosAndPartesFromGuiaTipo(ordenTrabajo,guia);
	};
	
	SSTFacade.listGuiaRecepcion(ordenTrabajo.id,{async:false,callback:function(guias){
		if(guias.length == 2){
			$.alerts.okButton = '&nbsp;Recibir guía de accesorios&nbsp;';
	        $.alerts.cancelButton = '&nbsp;Recibir guía con el producto&nbsp;';
			jConfirm('La orden de trabajo tiene 2 guías asociadas, una de accesorios y una normal, ¿Cual desea recibir?','Confirmación', function(r){
				if(r){
					for ( var int = 0; int < guias.length; int++) {
						if (guias[int].tipoGuia == 'GACC') {
							loadguiarecepcion(guias[int]);
						}
					}
				} else {
					for ( var int = 0; int < guias.length; int++) {
						if (guias[int].tipoGuia == 'GUNI') {
							loadguiarecepcion(guias[int]);
						}
					}
				}
			});
		} else if(guias.length == 1){
			loadguiarecepcion(guias[0]);
		} else {
			loadguiarecepcion({});
		}
	}});
	
	$('#condiciones').removeClass('required');
	$('#condiciones').removeClass('error');
	$('#condiciones').removeAttr('title');

	$('#recepcion').valid();
	$('#recepcionObs').valid();
};