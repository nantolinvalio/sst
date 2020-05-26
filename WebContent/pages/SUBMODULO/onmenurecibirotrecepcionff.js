var initonmenurecibirotrecepcionff = function() {
	
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
		if(!validaonmenurecibirotproductoff()) {
			jAlert('Falta información en la pestaña Producto.','Error');
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

	        $.alerts.okButton = '&nbsp;Devolver a Transporte&nbsp;';
	        $.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm("¿Esta seguro que desea devolver el producto a transporte?. La guía se generará una vez terminada la recepción.",'Confirmación', function(r){
				if(r){
					SSTFacade.saveRecepcionOTRechazoGuiaAgrupadas(recepcion,partes, accesorios,{async:false,callback:function(){
				        $.alerts.okButton = '&nbsp;Aceptar&nbsp;';
						jAlert("El producto se devolverá a transporte. Luego de terminar la recepción ingrese al menu Guías Pendientes",'Aviso',function(){
							$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
							var url = "/sst/ViewReportServlet?type=pdf" + 
							"&report=InformeCambioProductoReport" +
							"&idOT=" + ordenTrabajo.id;
							$.openWindowsMenubar(url, "InformeCambioProductoReport", 600, 800);
							reload();
						});
					}});
				}
			});
		}
	});
	
	$('#aceptarConObservaciones').click(function(){
		var valid = true;
		$('#condiciones').addClass('required');

		if(!validaonmenurecibirotproductoff()) {
			jAlert('Falta información en la pestaña Producto.','Error');
			valid = false;
		}
		
		if (!$('#recepcion').valid())
			valid = false;
		if (!$('#recepcionObs').valid())
			valid = false;

		if (valid){
			var accesoriosrecibidos ='';
			var accesorios = $("#accesoriosOT").getJSONFromHTML();
			$.each(accesorios, function(idx,accesorio) {
				if (accesorio.recibido == 'false'){
					accesoriosrecibidos += accesorio.descripcion + ', ';
				}
			});
			var recepcion = { 
				guia: $('#recepcion').serializeObject().guia,
				fechaRecepcion : $('#fechaRecepcion').val(),
				observacion :  $("#condiciones").val()
			};
			
			SSTFacade.getUbicacionOT(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
				recepcion.origen = ubicacionOT;
			}});
			
			recepcion.guia.ordenTrabajo = {
			   id : ordenTrabajo.id,
			   numeroSerie : $("#numeroSerie").val(),
			   clasificacion : {codigo:$('#clasificacion').val()}
		   	};
			
			var partes = $("#partesOT").getJSONFromHTML();
			SSTFacade.saveRecepcionOTGuiaAgrupadas(recepcion,accesoriosrecibidos,false, partes, accesorios,{async:false,callback:function(){
				SSTFacade.saveBitacoraInterna(recepcion.guia.ordenTrabajo,{codigo:$('#clasificacion').val()},{async:false,callback:function(bitacoraInterna){}});
				
				jAlert("Recepción con Observación exitosa","Información",function(){
					var url = "/sst/ViewReportServlet?type=pdf" + 
					"&report=InformeCambioProductoReport" +
					"&idOT=" + ordenTrabajo.id;
					$.openWindowsMenubar(url, "InformeCambioProductoReport", 600, 800);
					reload();
				});
			}});
		}});
	
	$('#aceptarRecepcion').click(function(){
		var valid = true;
		$('#condiciones').removeClass('required');
		$('#condiciones').removeClass('error');
		$('#condiciones').removeAttr('title');
		if(!validaonmenurecibirotproductoff()) {
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert('Falta información en la pestaña Producto.', 'Información');
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
				jAlert('Advertencia, no se han recibido los accesorios: ' + acce + ', por esto no puede aceptar la recepción, puede aceptarla con observaciones o devolver a transporte', 'Información');
			} else {
				var recepcion = { 
					guia: $('#recepcion').serializeObject().guia,
					fechaRecepcion : $('#fechaRecepcion').val(),
					observacion :  $("#condiciones").val()
				};
				
				SSTFacade.getUbicacionOT(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
					recepcion.origen = ubicacionOT;
				}});
				
				recepcion.guia.ordenTrabajo = {
				   id : ordenTrabajo.id,
				   numeroSerie : $("#numeroSerie").val(),
				   clasificacion : {codigo:$('#clasificacion').val()}
			   	};
				
				var partes = $("#partesOT").getJSONFromHTML();
				SSTFacade.saveRecepcionOTGuiaAgrupadas(recepcion, acce, true, partes, accesorios,{async:false,callback:function(){
					SSTFacade.saveBitacoraInterna(recepcion.guia.ordenTrabajo,{codigo:$('#clasificacion').val()},{async:false,callback:function(bitacoraInterna){}});
					$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
					jAlert("Recepcion exitosa","Información",function(){
						$('#rechazarRecepcion, #aceptarConObservaciones, #aceptarRecepcion').attr('disabled',true);
						var url = "/sst/ViewReportServlet?type=pdf" + 
						"&report=InformeCambioProductoReport" +
						"&idOT=" + ordenTrabajo.id;
						$.openWindowsMenubar(url, "InformeCambioProductoReport", 600, 800);
						reload();
					});
				}});
			}
		}
	});
	
	SSTFacade.listParametrosByTipo("CB",{async:true,callback:function(data){
		$("#clasificacion").find('option').remove();
		var tipos = [];
		$.each(data, function(idx,tipo) {
			if (tipo.codigo != 'CP' && tipo.codigo != 'RP'){
				tipos.push(tipo);
			}
		});
		$("#clasificacion").addItems(tipos, "codigo", "glosa", false);
	}});
	
	$('#siguiente').click(function(){
		$('#tabs').tabs('select', $('#tabs').find('#onmenurecibirotrecepcionff').attr('href'));
	});
};

var loadonmenurecibirotrecepcionff = function(ordenTrabajo) {
	var fechaActual;
	$('#recepcion').reset();
	$('#recepcionObs').reset();
	
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	$('.fechaHora').not('#fechaRecepcion').datetimepicker({
		minDate: removeTimeFromDate(fechaActual.clone().addDays(-1)),
		maxDate: removeTimeFromDate(fechaActual.clone())
	});
	
	SSTFacade.getGuiaRecepcionAgrupada(ordenTrabajo.id,{async:false,callback:function(guia){
		guia = guia == null ? {} : guia;
		SSTFacade.getUbicacionOT(ordenTrabajo.id,{async:false,callback:function(ubicacionOT){
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
				guia.fechaEmision = fechaActual;
			}	
			
			var objCarga = {
				guia : guia,
				ubicacionOT : ubicacionOT.tipo == 'ST' ? ubicacionOT.glosa + ': ' + ubicacionOT.nombre : ubicacionOT.glosa + ': ' + ubicacionOT.id + ' ' + ubicacionOT.nombre,
				direccion : ubicacionOT.direccion + ', ' + ubicacionOT.comuna.glosa,
				fechaRecepcion : fechaActual
			};
			
			
			$('#recepcionObs').find('#condiciones').text('');
			$('#recepcion').loadObject(objCarga);
			$('#rechazarRecepcion').attr('disabled',bloquear);
			$('#aceptarConObservaciones').attr('disabled',bloquear);
			$('#aceptarRecepcion').attr('disabled',bloquear);
			if (mostrar) {
				$('#avisoFecha').show();
			} else {
				$('#avisoFecha').hide();
			}
			loadAccesoriosGuiaAgrupada(guia); 
		}});
	}});

	$('#condiciones').removeClass('required');
	$('#condiciones').removeClass('error');
	$('#condiciones').removeAttr('title');

	$('#recepcion').valid();
	$('#recepcionObs').valid();
};
