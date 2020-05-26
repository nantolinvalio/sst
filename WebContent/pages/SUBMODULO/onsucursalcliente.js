var initonsucursalcliente = function() {
	var moduloTerminar;
	SSTFacade.getModuloByNombreUsuario('onsucursalterminar',{async:false, callback:function(modulo) {
		moduloTerminar = modulo ? modulo : undefined;
	}});
	
	var moduloFallas;
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;
	}});
	
	var moduloRevision;
	SSTFacade.getModuloByNombreUsuario('onsucursalrevision',{async:false, callback:function(modulo) {
		moduloRevision = modulo ? modulo : undefined;
	}});
	
	var moduloCrearOT;
	SSTFacade.getModuloByNombreUsuario('onsucursalbuscardocumento',{async:false, callback:function(modulo) {
		moduloCrearOT = modulo ? modulo : undefined;
	}});
	
	$('#btnReporteCambio').attr('disabled',true);
	
	var loadComunas = function(idRegion) {
		$('#comuna\\.id').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#comuna\\.id').addItems(comunas,"id","glosa",true);
		}});
	};
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#comuna\\.provincia\\.region\\.id').addItems(data, "id", "glosa", true);
		loadComunas($('#comuna\\.provincia\\.region\\.id').val());
	}});
	
	$('#comuna\\.provincia\\.region\\.id').change(function(){
		loadComunas($(this).val());
	});
	
	var validaClienteForm = function(){
		var contactoTel=new Array;
		contactoTel.push($('#cliente').find('#telefono'));
		contactoTel.push($('#cliente').find('#celular'));
		return $('#cliente').valid() & validEmptyInputs(contactoTel,'Debe ingresar al menos un contacto telefónico');
	};
	
	$('#btnReporteCambio').click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=InformeCambioProductoReport" +
		"&idOT=" + ordenTrabajo.id;
		$.openWindowsMenubar(url, "InformeCambioProductoReport", 600, 800);
	});
	
	$('#buscarCliente').click(function(){
		if($('#rut').valid()){
			var clientePaso = {rut:$('#rut').val()};
			SSTFacade.getClienteByRut(clientePaso,{async:true,callback:function(cliente){
				$('#cliente').reset();
				loadComunas(null);
				if (cliente == null) {
					cliente = clientePaso;
					jAlert('No existe un cliente con este rut en el sistema.','Información');
				} else {
					if (cliente.comuna != null && cliente.comuna.provincia != null && cliente.comuna.provincia.region != null) {
						loadComunas(cliente.comuna.provincia.region.id);
					}
				}
				$('#cliente').loadObject(cliente);
				validaClienteForm();
			}});
		}
	});
	
	$('#cliente').find('#guardarCliente').click(function(){
		if(validaClienteForm()){
			var cliente = {};
			cliente = $('#cliente').serializeObject();
			ordenTrabajo.numeroFolio = $('#cliente').find('#numeroFolio').val();
			ordenTrabajo.cliente = cliente;
			SSTFacade.terminarOrdenTrabajo(cliente,ordenTrabajo,{async:true,callback:function(oTResultado){
				ordenTrabajo = oTResultado;
				if( ordenTrabajo.estado.id == 10003000 || ordenTrabajo.estado.id == 10008000){
					if(ordenTrabajo.tipo.codigo == 'CA'){
						jAlert('Cliente guardado','Aviso');
					} else {
						jAlert('La orden de trabajo esta lista para ser enviada, imprima la orden de trabajo y entregele una copia al cliente.','Aviso',function(){});
					}
					$('#cliente').find('#imprimir').attr('disabled',false);
					$('#cliente').find('#volver').attr('disabled',true);
				}
				if(ordenTrabajo.estado.id == 10002000){
					jAlert('La orden de trabajo quedo pendiente por accesorios, se terminara cuando regrese el cliente con los accesorios requeridos.','Aviso',function(){
						parent.location = context+'/index.do?e=' + moduloCrearOT.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + ordenTrabajo.id;
					});
				}
				$('#cliente').find('#guardarCliente').attr('disabled',true);
				$('#btnTicketCambio').attr('disabled', false);
			}});
		}
	});
	
	$('#cliente').find('#volver').click(function(){
		parent.location = context+'/index.do?e='+(ordenTrabajo.tipo == 'BT' ? moduloFallas.codigo : moduloRevision.codigo) + '&m=' + moduloInicial.codigo + '&idOT=' + ordenTrabajo.id;
	});
	
	$("#imprimir").click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=OrdenTrabajoDetalleReport" +
		"&idOT=" + ordenTrabajo.id;
		$.openWindowsMenubar(url, "OrdenTrabajoDetalle", 600, 800);
		$('#cliente').find('#salir').attr('disabled', false);
	});

	$('#panelOTGeneral').find('#salir').click(function(){
		parent.location = context+'/index.do?e=' + moduloCrearOT.codigo + '&m=' + moduloInicial.codigo;
	});
	
	$('.panelOTCA').find('#salir').click(function(){
		parent.location = context+'/index.do?e=' + moduloCrearOT.codigo + '&m=' + moduloInicial.codigo;
	});
	
	$('#cliente').find('#btnTicketCambio').click(function(){
		if(ordenTrabajo.ticketCambio == null && validaClienteForm()) {
			SSTFacade.updateOTCATicketCambio(ordenTrabajo,{async:false,callback:function(ot){
				ordenTrabajo = ot;
				guardarCliente();
				$('.ticket').show();
				$('#ticketLabel').text('ticket de cambio');
				$('#cliente').find('#ticket').text(ordenTrabajo.ticketCambio);
				$('#btnTicketCambio').val('Imprimir ticket de Cambio');
				$('#btnReporteCambio').attr('disabled', false);
				$('.panelOTCA').find('#salir').show();
				$('.panelOTCA').find('#volver').hide();
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
	
	var guardarCliente = function(){
		if(validaClienteForm()){
			var cliente = {};
			cliente = $('#cliente').serializeObject();
			ordenTrabajo.numeroFolio = $('#cliente').find('#numeroFolio').val();
			ordenTrabajo.cliente = cliente;
			SSTFacade.terminarOrdenTrabajo(cliente,ordenTrabajo,{async:false,callback:function(oTResultado){
				ordenTrabajo = oTResultado;
				if( ordenTrabajo.estado.id == 10003000 || ordenTrabajo.estado.id == 10008000){
					if(ordenTrabajo.tipo.codigo == 'CA'){
						jAlert('Orden de Trabajo finalizada','Aviso');
					} else {
						jAlert('La orden de trabajo esta lista para ser enviada, imprima la orden de trabajo y entregele una copia al cliente.','Aviso',function(){});
					}
					
					$('#cliente').find('#imprimir').attr('disabled',false);
					$('#cliente').find('#volver').attr('disabled',false);
				}
				if(ordenTrabajo.estado.id == 1000200){
					jAlert('La orden de trabajo quedo pendiente por accesorios, se terminara cuando regrese el cliente con los accesorios requeridos.','Aviso',function(){
						parent.location = context+'/index.do?e=' + moduloFallas.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + ordenTrabajo.id;
					});
				}				
			}});
		}
	};
};

var loadonsucursalcliente = function(ordenTrabajo) {
	$('#cliente').find('#numeroFolio').val(ordenTrabajo.numeroFolio);
	
	SSTFacade.getClienteByOT(ordenTrabajo.id,{async:false,callback:function(cliente){
		if(cliente!=null && cliente.rut!=null){
			if (cliente.comuna != null && cliente.comuna.provincia != null && cliente.comuna.provincia.region != null) {
				SSTFacade.listComunasByRegion(cliente.comuna.provincia.region.id,{async:false,callback:function(comunas){
					$('#comuna\\.id').addItems(comunas,"id","glosa",true);
				}});
			}
			$('#cliente').loadObject(cliente);
		} else {
			SSTFacade.getClienteByFilter({tipo:ordenTrabajo.tipoDocumento,id:ordenTrabajo.idDocumento},{async:false,callback:function(cliente){
				if(cliente != null && cliente.rut != null && cliente.rut != ''){
					if (cliente.comuna != null && cliente.comuna.provincia != null && cliente.comuna.provincia.region != null) {
						SSTFacade.listComunasByRegion(cliente.comuna.provincia.region.id,{async:false,callback:function(comunas){
							$('#comuna\\.id').addItems(comunas,"id","glosa",true);
						}});
					}
					$('#cliente').loadObject(cliente);
				}
			}});
		}
	}});
	
	if(ordenTrabajo.estado != null && ordenTrabajo.estado.id != null) {
		
		if (ordenTrabajo.estado.id == 10002000) {
			$('#cliente').find('#guardarCliente').val('Guardar OT pendiente');
			$('#cliente').find('#imprimir').attr('disabled',true);
			$('.panelOTCA').hide();
		} else {
			$('#cliente').find('#guardarCliente').val('Guardar Orden de Trabajo');
			
			if( ordenTrabajo.estado.id != 10003000 && ordenTrabajo.estado.id != 10008000){
				$('#cliente').find('#imprimir').attr('disabled',false);
			}
			if(ordenTrabajo.estado.id == 10003000 || ordenTrabajo.estado.id == 10008000){
				$('#cliente').find('#volver').attr('disabled',false);
				$('#cliente').find('#guardarCliente').attr('disabled',false);
			};	
			
			if(ordenTrabajo.tipo != null && ordenTrabajo.tipo.codigo != null && (ordenTrabajo.tipo.codigo == 'CA')){
				$('#panelOTGeneral').hide();
				$('#cliente').find('#ticket').text(ordenTrabajo.ticketCambio);
			} else {
				$('.panelOTCA').hide();
			}			
			
		}
		
	}
	
	if(ordenTrabajo.ticketCambio != null) {
		$('#ticketLabel').text('ticket de cambio');
		$('#btnTicketCambio').val('Imprimir ticket de Cambio');
		$('#btnReporteCambio').attr('disabled', false);
		$('.panelOTCA').find('#salir').show();
		$('.panelOTCA').find('#volver').hide();
	} else{
		$('.ticket').hide();
		$('.panelOTCA').find('#salir').hide();
		$('.panelOTCA').find('#volver').show();
	}
	
	if(ordenTrabajo.tipo != null && ordenTrabajo.tipo.codigo != null && (ordenTrabajo.tipo.codigo == 'CA')){
		$('#tituloPagina').text('Recepción de producto con autorización de cambio');
		$('.gProveedor, .gMaster').hide();
	}
	
};
