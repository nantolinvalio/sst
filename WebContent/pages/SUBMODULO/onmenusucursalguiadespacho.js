var destino;

var initonmenusucursalguiadespacho = function() {
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onsucursalemitirguiadespacho',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$('#guiaCliente').validate();
	$('#guia').validate();
	
	$('#grabar').click(function(){
		var guia = {};
		if (destino == 10) {
			if ($('#guiaCliente').valid()) {
				guia = $('#guiaCliente').serializeObject();
				guia.destino = {id:destino};
			} else {return;}
		} else {
			if ($('#guia').valid()) {
				guia = $('#guia').serializeObject();
			} else {return;}	
		}
		guia.id = idGuia;
		var guiaAux;
		SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
			guiaAux = guia;
		}});
		if(guiaAux.tipoGuia == 'GACC'){
			SSTFacade.emitirGuiaAccesorio(ordenTrabajo.id, guia, {async:true,callback:function(g){
				parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
			}});
		} else {
			SSTFacade.emitirGuia(ordenTrabajo.id, guia, null, {async:true,callback:function(g){
				parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
			}});
		}
	});
	
	$('#reemitir').click(function(){
		reemitir();
	});
	
	var reemitir = function(){
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
		jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para la orden de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
			if (r) {
				if(guiaAux.tipoGuia == 'GACC'){
					SSTFacade.reEmitirGuiaAccesorio(ordenTrabajo.id, guia,{async:true,callback:function(g){
						parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
					}});
				}else{
					SSTFacade.reEmitirGuia(ordenTrabajo.id, guia, null, {async:true,callback:function(g){
						parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
					}});
				}
			}
		});
	};
	
	$('#confirmar').click(function(){
		confirmar();
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
				SSTFacade.confirmarEmisionGuia(ordenTrabajo.id, guia,{async:true,callback:function(g){
					parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
				}});
			}
		});
	};
	
	$('#imprimir').click(function(){
		if (ordenTrabajo.procesadoOW) {
			SSTFacade.validaStockGuiaUnitaria(idGuia,ordenTrabajo.id,{async:false,callback:function(existe){
				if (existe == true) {
					var url = "/sst/ViewReportServlet?type=pdf" + 
					"&report=GuiaDetalleReport" +
					"&idOT=" + ordenTrabajo.id + 
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
		} else {
			var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=GuiaDetalleReport" +
			"&idOT=" + ordenTrabajo.id + 
			"&idGuia=" + idGuia;
			$.openWindowsMenubar(url, "GuiaDetalleReport", 600, 800);
		}
	});
};

var loadonmenusucursalguiadespacho = function(ordenTrabajo) {
	var fechaActual;
	var bitacora;
	
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	SSTFacade.getBitacoraByIdGuia(idGuia,{async:false,callback:function(b){
		bitacora = b;
	}});
	
	var minDate = (bitacora == null || bitacora.fechaEntrada == null) ? fechaActual : bitacora.fechaEntrada;
	var maxDate = fechaActual.clone().addDays(6);
	
	minDate = removeTimeFromDate(minDate.clone());
	maxDate = removeTimeFromDate(maxDate.clone());
	$('.fechaHora').datetimepicker({
		minDate: minDate,
		maxDate: maxDate
	});
	
	$('.fechaHora').rules('add',{dateRangeSingle:[minDate,maxDate,'dd/MM/yyyy HH:mm']});
	
	SSTFacade.listTransportista({async:false,callback:function(transportistas){
		$('#transportista\\.id').addItems(transportistas,"id","nombreCompleto",true);
	}});
	
	SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
		$('#guia, #guiaCliente, #guiaCliente').hide();
		$('#grabar, #imprimir, #reemitir, #confirmar').attr('disabled',true);
		
		if (guia.tipoGuia == 'GACC') {
			$('#guiaAccesorio').show();
		} else {
			$('#guiaAccesorio').hide();			
		}
		
		if ((ordenTrabajo.tipoCambio != null && (ordenTrabajo.tipoCambio.codigo == 'FR' || ordenTrabajo.tipoCambio.codigo == 'CC')) || guia.tipoGuia == 'GACC' || (ordenTrabajo.cambioAutorizado && ordenTrabajo.tipo.codigo == 'GM')) {
//		if (ordenTrabajo.cambioAutorizado) {
			SSTFacade.getCentroDistribucion({async:false,callback:function(cd){
				$('#guia').find('#destino\\.id').addItems([cd],"id",["tipo","nombre","direccion"],false," : ");
			}});		
		} else {
			SSTFacade.listSTecnicoYBodegasByFilter({idOT:ordenTrabajo.id},{async:false,callback:function(stecnico){
				$('#guia').find('#destino\\.id').addItems(stecnico,"id",["tipo","glosa","direccion"],true," : ");
			}});
		}
		
		guia.fechaEmision = guia.fechaEmision == null ? fechaActual : guia.fechaEmision;
		if (guia.destino != null && guia.destino.id != null) {
			destino = guia.destino.id;				
		}

		//Si es un guia de envio o recepcion carga automaticamente el destino(cliente o sucursal)		
		if (destino == 10) {
			$('#guiaCliente').attr('style','');
			SSTFacade.getClienteByOT(ordenTrabajo.id,{async:false,callback:function(cliente){
				$('#cliente').text(cliente.nombreCompleto);
			}});
			$('#guiaCliente').loadObject(guia);
		} else {
			$('#guia').attr('style','');
			$('#guia').loadObject(guia);
		}
		
		if(guia.procesadoOW){
			$('#grabar,#reemitir,#confirmar').attr('disabled',true);
		} else {
			switch (guia.estado.id) {
				case 50001000:
					$('#grabar').attr('disabled', false);
					if(!ordenTrabajo.procesadaOW){
						$('#confirmar').hide();
					}
					break;
				case 50001500:
					$('#reemitir, #imprimir, #confirmar').attr('disabled', false);
					break;
				case 50002000:
					$('#reemitir,#imprimir').attr('disabled', false);
					$('#confirmar').hide();
					break;
				case 50003000:
					$('#reemitir,#imprimir').attr('disabled', false);
					$('#confirmar').hide();
					break;
				case 50005000:
					$('#imprimir').attr('disabled', false);
					$('#grabar,#reemitir').attr('disabled',true);
					$('#confirmar').hide();
					break;
				default:
					break;
			}
		}
		
		if(guia.tipoGuia==('GENV') || guia.tipoGuia==('GRET')){
			if(destino == 10){
				$('#guiaCliente').loadObject(guia);
			} else {
				SSTFacade.getUbicacionById(destino,{async:false,callback:function(ubicacionDestino){
				$('#destino\\.id')
					.find('option')
					.remove()
					.end()
					.append('<option value="'+destino+'">'+ubicacionDestino.tipo+' : '+ubicacionDestino.nombre+' : '+ubicacionDestino.direccion+'</option>')
					.val(destino)
				;
				}});
			}
		}
		
	}});		
	
};
