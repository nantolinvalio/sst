var codigoObligatorio;

var initonsucentregacliente = function() {
	$('#onsucentregacliente').click(function(){
		$('#accesoriosOTCliente').recalcLayout();
	});
	
	var moduloFallas;
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getCodigoBarraObligatorio({async:false,callback:function(resp){
		codigoObligatorio = resp;
	}});
	
	$('.fechaHora').datetimepicker();
	
	
	$('#observacionEntrega').maxlength({  
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
	
	$("#accesoriosOTCliente").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		onToggleCol:false,
		title: "Descripción de accesorio recibido",
		colModel : [
		    {display: '¿RECIBIDO?', width:80 , align: 'center', name_abbr:'recibido', name:function(o){
		    	var lblSI = $('<label></label>')
							.attr('for','estadoSi' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('SI');
				var rdSI = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','true');
				var lblNO = $('<label></label>')
							.attr('for','estadoNo' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('NO');
				var rdNO = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','false');
				var cnt = $('<div></div>')
							.css('padding','0px')
							.append(lblSI).append(rdSI).append(lblNO).append(rdNO);
		    	return cnt;
		    }},        
            {display: 'Descripción Accesorio',width:250,align:'left',name:'descripcion'},
            {display: 'Ubicación',width:200,align:'left' ,name:'ubicacion.nombre'},
            {display:'Codigo de Barra', width:200 , align:'center', name_abbr:'codigoBarra', name:function(o){
		    	var input = $('<input type="text">')
		    				.addClass('digits')
		    				.attr('maxlength','10')
		    				.attr('id','codigoBarra' + o.id)
		    				.attr('name','codigoBarra' + o.id)
		    				.attr('onblur','formatcodigo(this)');
		    	
		    	if (codigoObligatorio) {
		    		input.addClass('required');
		    	}
		    	
		    	return input;
		    }}     
		]
	});
	
	$('#clienteAcepta').click(function(){
		
		var valid = false;
		if(validaPestanaCliente()) {
			valid = true;
		}
		if($('#observacionEntrega').val().length == 0 && $('#observacionEntrega').val() == ""){
			$('#observacionEntrega').addClass('error');
			$('#observacionEntrega').attr('title','Información obligatoria');
			valid = false;
		}
		if(valid){
			var rutCliente = $('#rut').val();
			var nombreCompletoCliente = $('#nombreCompleto').val(); 
			var telefonoCliente = $('#telefono').val();
			var observacion = $('#observacionEntrega').val();
			var ot = { 
				id: ordenTrabajo.id,
				banderaOrigenOT: "C3",
				cliente: {
					rut : rutCliente, 
					nombreCompleto : nombreCompletoCliente, 
					telefono : telefonoCliente
				},
				origen : {
					id : ubicacion.id
				}, 
				fechaCierreCliente : $('#fechaEntrega').val(), 
				observacionEntrega : observacion,
				producto: {
					id : ordenTrabajo.producto.id
				}
			};
			var accesorios = $("#accesoriosOTCliente").getJSONFromHTML();
			
			SSTFacade.updateClienteAceptaProducto(accesorios, ot,{async:true,callback:function(msg){
				$('#clienteRechaza,#cambioFalla,#clienteAcepta').attr('disabled',true);
				$('#btnTicketCambio').attr('disabled',false);
				//$.alerts.okButton = '&nbsp;OK&nbsp;';
				//jAlert(msg,'Información',function(){
					//$('#clienteRechaza,#cambioFalla,#clienteAcepta').attr('disabled',true);
					//$('#btnTicketCambio').attr('disabled',false);
				//});
			}});
		}
	});
	
	$('#clienteRechaza').click(function(){
		if(!validaPestanaCliente()) {
			return;
		}
		$.alerts.okButton = '&nbsp;Enviar Tarea a Ejecutiva&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('El cliente al rechazar el producto, provoca el envio de la tarea a la ejecutiva de marca.\n¿Esta seguro de enviar la tarea a la ejecutiva de marca?', 'Confirmación', function(r){
			if (r) {
				var observacion = $('#observacionEntrega').val();
				var ot = { id : ordenTrabajo.id, observacionEntrega : observacion};
				$('#clienteRechaza,#cambioFalla,#clienteAcepta').attr('disabled',true);
				SSTFacade.updateEnviarTareaEjecutiva(ot,{async:true,callback:function(){
					jAlert('La tarea se envio a la ejecutiva.', 'Información', function(){});
				}});
			}
		});
	});
	
	$('#cambioFalla').click(function(){
		SSTFacade.cerrarOTYCrearOTFallaFabricacion(ordenTrabajo,{async:true,callback:function(OT){
			parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+OT.id;
		}});
	});
	
	var validaPestanaCliente = function(){
		var accOT = $("#accesoriosOTCliente").getJSONFromHTML();
		var valid = $('#cliente').valid();
		if (ordenTrabajo.cambioAutorizado){
			$("#accesoriosOTCliente").find('[type=radio]').parent().removeClass('error');
			$.each(accOT, function(idx,accesorio) {
				if (accesorio.recibido == null || accesorio.recibido == 'false') {
					valid = false;
					$("#accesoriosOTCliente").find('[name=estado' + accesorio.id + ']').parent().addClass('error');
					$("#accesoriosOTCliente").find('[name=estado' + accesorio.id + ']').parent().attr('title', 'Se debe recibir el accesorio para realizar la entrega del producto al cliente');
				}
			});
			
			$.each(accOT,function(index,accesorio){
				if($("#accesoriosOTCliente").find('input:[value=' + accesorio.codigoBarra + ']').size() > 1 && accesorio.codigoBarra != "") {
					$("#accesoriosOTCliente").find('input:[value=' + accesorio.codigoBarra + ']').addClass('error');
					$("#accesoriosOTCliente").find('input:[value=' + accesorio.codigoBarra + ']').attr('title', 'Los códigos de barra no pueden estar repetidos');
					valid = false;
				}
			});
			
			SSTFacade.validaCodigosDeBarraAccesorios(accOT,{async:false,callback:function(idAccesorios){
				if (idAccesorios.lenght != 0) {
					$.each(idAccesorios,function(index,idAccesorio){
						$("#accesoriosOTCliente").find('#codigoBarra' + idAccesorio).addClass('error');
						$("#accesoriosOTCliente").find('#codigoBarra' + idAccesorio).attr('title', 'El código de barras ya está en uso');
						valid = false;
					});
				}
			}});
			
		}
		
		return valid;
	};
	
	//--------------------------------------------------------------
	$('#btnTicketCambio').click(function(){
		if(ordenTrabajo.ticketCambio == null) {  //&& validaClienteForm()) {
			SSTFacade.updateOTCATicketCambio(ordenTrabajo,{async:false,callback:function(ot){
				ordenTrabajo = ot;
				//guardarCliente();
				//$('.ticket').show();
				//$('#ticketLabel').text('ticket de cambio');
				//$('#cliente').find('#ticket').text(ordenTrabajo.ticketCambio);
				$('#btnTicketCambio').val('Imprimir ticket de Cambio');
				//$('#clienteAcepta').attr('disabled', false);
				//$('.panelOTCA').find('#salir').show();
				//$('.panelOTCA').find('#volver').hide();
			}});
			
			if(ordenTrabajo.ticketCambio != null){
				var url = "/sst/ViewReportServlet?type=pdf" + 
				"&report=TicketCambioReport" +
				"&idOT=" + ordenTrabajo.id;
				$.openWindowsMenubar(url,"TicketCambioReport", 600, 800);
			}
		} else if (ordenTrabajo.ticketCambio != null) {
			var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=TicketCambioReport" +
			"&idOT=" + ordenTrabajo.id;
			$.openWindowsMenubar(url,"TicketCambioReport", 600, 800);
		}
	});
	//--------------------------------------------------------------
};

var formatcodigo = function(el){
	if ($(el).val() != "" && $(el).valid()) {
		$(el).val($.leftPad($(el).val(),10,'0'));
	}
};


var loadonsucentregacliente = function(ordenTrabajo) {
	$('#cliente').reset();
	
	SSTFacade.getDate({async:false, callback:function(fechaEntrega) {
		$('#cliente').loadObject({fechaEntrega:fechaEntrega});
	}});
	
	$('#clienteRechaza,#cambioFalla,#clienteAcepta, #btnTicketCambio').attr('disabled',false);
	
	if(ordenTrabajo.cambioAutorizado == true){
		if(ordenTrabajo.tipo.codigo == 'GM'){
			 $('#btnTicketCambio').hide();
		} else {
			$('#btnTicketCambio').show();
		}
		$('#panelAccesoriosCliente, #productoCambioAutorizadoFF').show();
		$('.panelSinAutorizacionCambio').hide();
		var guia = {};
		$('#btnTicketCambio').attr('disabled',true);
		SSTFacade.listAccesoriosForGuiaTipo(ordenTrabajo,guia, true, {async:false,callback:function(accesoriosOT){
			if (accesoriosOT && accesoriosOT.length != 0) {
				$("#accesoriosOTCliente").parent().parent().show();
				$("#accesoriosOTCliente").flexAddData({rows:accesoriosOT,total:accesoriosOT.length});				
			} else {
				$("#accesoriosOTCliente").clean();
				$("#accesoriosOTCliente").parent().parent().hide();
			}
		}});
	} else {
		$("#accesoriosOTCliente").clean();
		$('#panelAccesoriosCliente, #productoCambioAutorizadoFF, #btnTicketCambio').hide();
		$('.panelSinAutorizacionCambio').show();
	}
	
	SSTFacade.getClienteByOT(ordenTrabajo.id,{async:true,callback:function(cl){
		if(cl == null || cl.telefono == null){
			$('#telefono').attr('id','celular');
		}
		$('#cliente').loadObject(cl);
		$('#cliente').valid();
	}});	
	
	$('#accesoriosOTCliente').recalcLayout();

};
