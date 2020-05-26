var destino;

var initonbodegaemitirguiadespacho = function() {
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegaemitirguiadespraiz',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$('#guia').validate();
	
	$('[name=destinoGroup]').click(function(){
		$('#destino').find('#destino\\.bodega\\.id').attr('disabled','disabled');
		$('#destino').find('#destino\\.stecnico\\.id').attr('disabled','disabled');
		$('#destino').find('#destino\\.sucursal\\.id').attr('disabled','disabled');
		if (this.id == 'destinoGroup.stecnico') {
			$('#destino').find('#destino\\.stecnico\\.id').removeAttr('disabled');
		} else if (this.id == 'destinoGroup.sucursal') {
			$('#destino').find('#destino\\.sucursal\\.id').removeAttr('disabled');
		} else if (this.id == 'destinoGroup.bodega') {
			$('#destino').find('#destino\\.bodega\\.id').removeAttr('disabled');
		}
	});
	
	$('#grabar').click(function(){
		var guia = {};
		var servicioTecnico = null;
		var destinoGuia = null;
		var destinoSelected = null;
		
		if ($('#guia').valid() && $('#destino').valid()) {
			guia = $('#guia').serializeObject();
			destinoGuia = $('#destino').serializeObject().destino;
			destinoSelected = $("input[name=destinoGroup]:checked").attr('id');    
			
			if (destinoSelected == 'destinoGroup.stecnico') {
				guia.destino = destinoGuia.stecnico;					
			} else if (destinoSelected == 'destinoGroup.sucursal') {
				guia.destino = destinoGuia.sucursal;
			} else if (destinoSelected == 'destinoGroup.bodega') {
				guia.destino = destinoGuia.bodega;
			}
		} else {return;}
		guia.id = idGuia;
		var guiaAux;
		SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
			guiaAux = guia;
		}});
		if(guiaAux.tipoGuia == 'GACC'){
			SSTFacade.emitirGuiaAccesorio(ordenTrabajo.id, guia, {async:true,callback:function(g){
				parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
			}});
		}else{
			SSTFacade.emitirGuia(ordenTrabajo.id, guia, null, {async:true,callback:function(g){
				parent.location = context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
			}});
		}
	});
	
	$('#reemitir').click(function() {
		var guia = {};
		var servicioTecnico = null;
		var destinoSelected = null;
		if ($('#guia').valid()) {
			guia = $('#guia').serializeObject();
			destinoSelected = $("input[name=destinoGroup]:checked").attr('id');
			if (destinoSelected == 'destinoGroup.stecnico') {
				guia.destino = {id: $('#destino\\.stecnico\\.id > option:selected').val()};
			} else if (destinoSelected == 'destinoGroup.sucursal') {
				guia.destino = {id: $('#destino\\.sucursal\\.id > option:selected').val()};
			} else if (destinoSelected == 'destinoGroup.bodega') {
				guia.destino = {id: $('#destino\\.bodega\\.id > option:selected').val()};
			}
		} else {return;}
		guia.id = idGuia;
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('Al re emitir una guía de despacho se desactiva la guia anterior y se crea una nueva guia de despacho para la orden de trabajo. </br> ¿Esta seguro de bloquear esta guía de despacho y crear una nueva?', 'Confirmación', function(r){
			if (r) {
				if(guia.tipoGuia == 'GACC'){
					SSTFacade.reEmitirGuiaAccesorio(ordenTrabajo.id, guia, null, {async:true,callback:function(g){
						parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
					}});
				}else{
					SSTFacade.reEmitirGuia(ordenTrabajo.id, guia, null, {async:true,callback:function(g){
						parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idOT=" + ordenTrabajo.id + "&idGuia=" + g.id;
					}});
				}
			}
		});
	});
	
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
		var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=GuiaDetalleReport" +
			"&idOT=" + ordenTrabajo.id + 
			"&idGuia=" + idGuia;
		$.openWindowsMenubar(url, "GuiaDetalleReport", 600, 800);

		if(ordenTrabajo.procesadaOW){
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
	});
	

	
	SSTFacade.listTransportista({async:false,callback:function(transportistas){
		$('#transportista\\.id').addItems(transportistas,"id","nombreCompleto",true);
	}});

	SSTFacade.listSucursales({async:false, callback:function(data){
		$('#destino\\.sucursal\\.id').addItems(data, "id", ["id","nombre"], true);
	}});
	
	SSTFacade.listBodegasNotMe({async:false,callback:function(bodegas){
		var bodegaAux = [];
		$(bodegas).each(function(id, nombre){
				if ( nombre.id != 10012){
					 bodegaAux.push(nombre);
				}
		});
		$('#destino').find('#destino\\.bodega\\.id').addItems(bodegaAux,"id",["id","nombre"],true," : ");
	}});
	
};

var loadonbodegaemitirguiadespacho = function(ordenTrabajo) {
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
	
	SSTFacade.listSTecnicoByFilter({idOT:ordenTrabajo.id},{async:false,callback:function(stecnico){
		$('#destino').find('#destino\\.stecnico\\.id').addItems(stecnico,"id",["tipoGlosa","glosa","direccion"],true," : ");
	}});
	
	SSTFacade.getGuiaById(idGuia,{async:false,callback:function(guia){
		$('#grabar').attr('disabled','disabled');
		$('#imprimir').attr('disabled','disabled');
		$('#reemitir').attr('disabled','disabled');
		$('#confirmar').attr('disabled','disabled');
		$('#destino').find('#destino\\.bodega\\.id').attr('disabled','disabled');
		$('#destino').find('#destino\\.stecnico\\.id').attr('disabled','disabled');
		$('#destino').find('#destino\\.sucursal\\.id').attr('disabled','disabled');
		$('[name=destinoGroup]').attr('disabled','disabled');
		$('[name=destinoGroup]').attr('checked',false);
		
		guia.fechaEmision = guia.fechaEmision == null ? fechaActual : guia.fechaEmision;
		
		if (guia.destino != null && guia.destino.id != null) {
			SSTFacade.getUbicacionById(guia.destino.id, {async:false,callback:function(d){
				destino = d;	
			}});
		}
		
		if (destino == null) {
			$('#destinoGroup\\.stecnico').attr('checked',true);
		} else if (destino.tipo == 'ST') {
			$('#destinoGroup\\.stecnico').attr('checked',true);
			$('#destino').find('#destino\\.stecnico\\.id').val(destino.id);
		} else if (destino.tipo == 'SC') {
			$('#destinoGroup\\.sucursal').attr('checked',true);
			$('#destino').find('#destino\\.sucursal\\.id').val(destino.id);
		} else if (destino.tipo == 'CD' || destino.tipo == 'BR') {
			$('#destinoGroup\\.bodega').attr('checked',true);
			$('#destino').find('#destino\\.bodega\\.id').val(destino.tipo.id);
		}
		
		if(guia.procesadaOW){
			$('#grabar,#reemitir,#confirmar').attr('disabled',true);
		} else {
			switch (guia.estado.id) {
				case 50001000:
					$('[name=destinoGroup]').removeAttr('disabled');
					if (destino == null) {
						$('#destino').find('#destino\\.stecnico\\.id').removeAttr('disabled');
					} else if (destino.tipo == 'ST') {
						$('#destino').find('#destino\\.stecnico\\.id').removeAttr('disabled');
					} else if (destino.tipo == 'SC') {
						$('#destino').find('#destino\\.sucursal\\.id').removeAttr('disabled');
					} else if (destino.tipo == 'CD' || destino.tipo == 'BR') {
						$('#destino').find('#destino\\.bodega\\.id').removeAttr('disabled');
					}
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
		
		$('#guia').loadObject(guia);
	}});
	
};