$(document).ready(function(){
	var idProducto = $("#idProducto").val();
	var idDocumento = $('#idDocumento').val();
	var tipoDocumento = $('#tipoDocumento').val();

	var ot = {};
	ot.idDocumento = idDocumento;
	ot.tipoDocumento = tipoDocumento;

	var moduloAnterior;
	SSTFacade.getModuloByNombreUsuario('onmenugmasterbuscar',{async:false, callback:function(modulo) {
		moduloAnterior = modulo ? modulo : undefined;
	}});
	
	var moduloMasterTerminar;
	SSTFacade.getModuloByNombreUsuario('onmenugmasterterminar',{async:false, callback:function(modulo) {
		moduloMasterTerminar = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getCentroDistribucion({async:false,callback:function(CD){
		$('#centroDistribucionLaVara').val(CD.id);
	}});
	
	$('[name=destino]').click(function(){
		$('#numeroAtencion, #numeroAutorizacion').removeClass('error');
		if(this.id == 'reparacion') {
			$('#numeroAtencion').attr('disabled',false);
			$('#numeroAutorizacion').attr('disabled',true);
			$('#destinoReparacion').show();
			$('#destinoCambio').hide();
		} else {
			$('#numeroAtencion').attr('disabled',true);
			$('#numeroAutorizacion').attr('disabled',false);
			$('#destinoReparacion').hide();
			$('#destinoCambio').show();
		}
	});
	
	SSTFacade.getDocumentoByIdAndTipo({tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(documento){
		if(documento!=null){
			$('#datosServicio').loadObject(documento);
			
			SSTFacade.getProductoById(idProducto,{async:true,callback:function(producto){
				if(producto !=null){
					$('#producto\\.id').text(producto.id);
					$('#producto\\.descripcion').text(producto.descripcion);
					$('#marca').text(producto.marca.nombre);
					ot.producto = producto;
				}
				else{
					$('#producto\\.id').text('0');
				}
			}});
			ot.fechaEmision=documento.fechaEmision;
		} else {
			jAlert('No se ha encontrado la '+$('#tipoDocumento').val()+' número '+$('#idDocumento').val());
		}
	}});
	
	
	SSTFacade.getCrearOTGP({idProducto:idProducto,tipoOT:'GP'},{idDocumento:idDocumento,tipoDocumento:tipoDocumento,idProducto:idProducto},{tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(crearOTGP){
		ot.fechaVencimiento = crearOTGP.fechaVencimiento;
		if(crearOTGP.consultarGP){
			$('#garantiaProveedor').html('<font color=\"blue\">ACTIVA</font>');
			$('#consultarGM').attr('disabled','');
			$('#crearOT').attr('disabled',true);
			$('#destinoOT, #destinoReparacion, #destinoCambio').hide();
		}
		else{
			$('#garantiaProveedor').html('<font color=\"red\">VENCIDA</font>');
			$('#fechaVencimiento').text(crearOTGP.fechaVencimiento.toString('dd/MM/yyyy'));
		}
	}});
	
	var buscarCliente = function(rut){
		var clientePaso = {rut:rut};
		SSTFacade.getClienteByRut(clientePaso,{async:true,callback:function(cliente){
			$('#cliente').reset();
			loadComunasCliente(null);
			if (cliente == null) {
				cliente = clientePaso;
				jAlert('No existe un cliente con este rut en el sistema.','Información');
			} else {
				if (cliente.comuna != null && cliente.comuna.provincia != null && cliente.comuna.provincia.region != null) {
					loadComunasCliente(cliente.comuna.provincia.region.id);
				}
			}
			$('#cliente').loadObject(cliente);
//			loadDatorRepararacion();
			validaCliente();
		}});
	
	};
//	var loadDatorRepararacion = function(){
//		$('#destinoReparacion').find('#region\\.id').val($('#cliente').find('#comuna\\.provincia\\.region\\.id').val());
//		$('#destinoReparacion').find('#comuna\\.id').val(2201);
//		//$('#destinoReparacion').find('#sucursalRecibe\\.id')
//	}; 
	
	$('#buscarCliente').click(function(){
		if($('#rut').valid()){
			buscarCliente($('#rut').val());
		}
	});
	
	var loadComunasConSucursales = function(idRegion) {
		$('#destinoReparacion').find('#comuna\\.id').empty();
		SSTFacade.listComunasConSucursalesByRegion(idRegion,{async:false,callback:function(comunas){
			$('#destinoReparacion').find('#comuna\\.id').addItems(comunas,"id","glosa",true);
			loadSucursales($('#destinoReparacion').find('#comuna\\.id').val());
		}});
	};
	
	var loadComunasCliente = function(idRegion) {
		$('#cliente').find('#comuna\\.id').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#cliente').find('#comuna\\.id').addItems(comunas,"id","glosa",true);
		}});
	};
	
	var loadSucursales = function(idComuna) {
		$('#destinoReparacion').find('#sucursalRecibe\\.id').empty();
		SSTFacade.listSucursalesByComuna(idComuna,{async:false,callback:function(sucursales){
			$('#destinoReparacion').find('#sucursalRecibe\\.id').addItems(sucursales,"id",["id","glosa"],true);
			loadSTecnicos();
		}});
	};
	
	var loadSTecnicos = function() {
		var filter = {
			tipoOT:'GM',
			idProducto:$('#producto\\.id').text(),
		};
		
		if($('#centroDistribucionLaVara').is(':checked')){
			filter.idOrigen = 10015;
		}
		else {
			filter.idOrigen = $('#sucursalRecibe\\.id').val();
		}
		
		$('#sTecnico\\.id').empty();
		SSTFacade.listSTecnicoByFilter(filter,{async:false,callback:function(servicios){
			if (servicios == null || servicios.length == 0)
				$('#sTecnico\\.id').addItems([{id:"",glosa:"La sucursal no tiene S. Técnicos Asignados"}],"id","glosa",false);
			else {
				$('#sTecnico\\.id').addItems(servicios,"id","glosa",true);
			}
		}});
	};
	
	$('#destinoReparacion').find('#region\\.id').change(function(){
		loadComunasConSucursales($(this).val());
	});
	
	$('#cliente').find('#comuna\\.provincia\\.region\\.id').change(function(){
		loadComunasCliente($(this).val());
	});
	
	$('#destinoReparacion').find('#comuna\\.id').change(function(){
		loadSucursales($(this).val());
	});
	
	$('#destinoReparacion').find('#sucursalRecibe\\.id').change(function(){
		loadSTecnicos();
	});
	
	$('#destinoReparacion').find('#centroDistribucionLaVara').change(function(){
		loadSTecnicos();
	});
	
		SSTFacade.listRegiones({async:false,callback:function(regiones){
			$('#destinoReparacion').find('#region\\.id').addItems(regiones,"id","glosa",true);
			$('#destinoReparacion').find('#region\\.id').val($('#cliente').find('#comuna\\.provincia\\.region\\.id').text());
			$('#cliente').find('#comuna\\.provincia\\.region\\.id').addItems(regiones,"id","glosa",true);
			loadComunasConSucursales($('#destinoReparacion').find('#region\\.id').val());
			loadComunasCliente($('#cliente').find('#comuna\\.provincia\\.region\\.id').val());
		}});

	SSTFacade.listSucursales({async:false,callback:function(sucursales){
		$('#destinoCambio').find('#sucursalRetira\\.id').addItems(sucursales,"id",["id","nombre"],true);
	}});
	
	SSTFacade.listServicioTecnicoGM({async:false,callback:function(stecnicos){
		$('#destinoCambio').find('#stecnicoRetira\\.id').addItems(stecnicos,"id",["nombre","direccion","comuna.glosa"],true,", ");
	}});
	 
		
	SSTFacade.getClienteByFilter({tipo:tipoDocumento,id:idDocumento},function(cliente){
		if (cliente && cliente.rut) {
			buscarCliente(cliente.rut);
		}
	});
	
	$('#volver').click(function(){
		parent.location = context +'/index.do?e=' + moduloAnterior.codigo + '&m=' + moduloInicial.codigo;
	});
	
	var validaCliente = function(){
		var contactoTel=new Array;
		contactoTel.push($('#cliente').find('#telefono'));
		contactoTel.push($('#cliente').find('#celular'));
		validEmptyInputs(contactoTel,'Debe ingresar al menos un contacto telefonico');
		return $('#cliente').valid() & validEmptyInputs(contactoTel,'Debe ingresar al menos un contacto telefonico');
	};
		
	$('#crearOT').click(function(){
		if($('#destinoOT').find('[name=destino]:checked').val() == 'reparacion') {
			if($('#numeroAtencion').valid() & validaCliente() & $('#destinoReparacion').valid()){
				var ot = {};
				ot.servicioTecnico = {id:$('#sTecnico\\.id').val()};
				ot.sucursal = {id:$('#sucursalRecibe\\.id').val()};
				ot.numeroAtencion = $('#numeroAtencion').val();
				ot.cliente = $('#cliente').serializeObject();
				ot.producto = {id:$('#producto\\.id').text()};
				ot.numeroCambio = 0;
				ot.idDocumento = $('#id').text();
				ot.tipoDocumento = $('#tipo').text();
				if($('#centroDistribucionLaVara').is(':checked')) {
					ot.idDestino = $('#centroDistribucionLaVara').val();
				} else {
					ot.idDestino = ot.servicioTecnico.id;
				}
				SSTFacade.saveOTGMFromCD(ot, ot.sucursal,function(OT){
					jAlert('La orden de trabajo se creo correctamente','Aviso',function(){
						parent.location = context +'/index.do?e=' + moduloMasterTerminar.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + OT.id;						
					});
				});	
			} else {
				return;
			}
		}

		if($('#destinoOT').find('[name=destino]:checked').val() == 'cambio') {
			var valid = false;
			if ($('#destinoCambio').find('[name=origenCambio]:checked').val() == 'origenSTecnico' && $('#destinoCambio').find('#stecnicoRetira\\.id').val() == '') {
				$('#destinoCambio').find('#stecnicoRetira\\.id').addClass('error');
			} else {
				$('#destinoCambio').find('#stecnicoRetira\\.id').removeClass('error');
				valid = true;
			}
			
			if($('#numeroAutorizacion').valid() & validaCliente() & $('#destinoCambio').valid() & valid){
				var ot = {};
				ot.numeroCambio = $('#numeroAutorizacion').val();
				ot.cliente = $('#cliente').serializeObject();
				ot.producto = {id:$('#producto\\.id').text()};
				ot.numeroAtencion = 0;
				ot.idDocumento = $('#id').text();
				ot.tipoDocumento = $('#tipo').text();
				ot.servicioTecnico = {id:$('#stecnicoRetira\\.id').val()};
				ot.sucursal = {id:$('#sucursalRetira\\.id').val()};
				
				var origen = $('#destinoCambio').find('[name=origenCambio]:checked').val();
				
				SSTFacade.saveOTGMFromCDForCambio(ot, ot.sucursal, origen, function(OT){
					jAlert('La orden de trabajo se creo correctamente','Aviso',function(){
						parent.location = context +'/index.do?e=' + moduloMasterTerminar.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + OT.id;						
					});
				});
			} else {
				return;
			}
		}
	});
});