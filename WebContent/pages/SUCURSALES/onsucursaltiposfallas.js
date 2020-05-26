$(document).ready(function(){
	var idOT = parseInt($('#idOT').text());
	var oT = {};
	
	var moduloBuscador;
	SSTFacade.getModuloByNombreUsuario('onsucursalbuscardocumento',{async:false, callback:function(modulo) {
		moduloBuscador = modulo ? modulo : undefined;
	}});
	
	var moduloAccesorios;
	SSTFacade.getModuloByNombreUsuario('onsucursalaccesorios',{async:false, callback:function(modulo) {
		moduloAccesorios = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getOTById(idOT,{async:false,callback:function(oTGeneral){
		oT=oTGeneral;
		$('#datosOT').loadObject(oTGeneral);
		$('#fallasProducto').loadObject(oTGeneral);
		if(oTGeneral.descripcionFalla==null){
			$('.descripcionFalla').css('display','none');	
		}
		
		SSTFacade.listTipoFallasCrearOT(oTGeneral,{async:true,callback:function(fallas){
			$('#tiposFallas').addItems(fallas[0],'id','descripcion');
			$('#fallasAsignadas').addItems(fallas[1],'id','descripcion');
		}});
		
	}});
	
	
	$('[for=condicionesEnvioRecepcion]').hide();
	if (oT.cambioAutorizado) {
//	if(oT.tipo != null && oT.tipo.codigo != null && (oT.tipo.codigo == 'CA' || (oT.tipo.codigo == 'GP' && oT.tipoCambio != null))){
		$('#tituloPagina').text('Recepción de producto con autorización de cambio' + (oT.tipo.codigo == 'GM' ? '. Garantía Master' : ''));
		$('.gProveedor, .gMaster').css('display','none');
		
		if (oT.tipoCambio == 'FF' || (oT.tipoCambioJT != null && oT.tipoCambioJT.codigo == 'FF')) {
			SSTFacade.getCondicionRecepcion({async:true,callback:function(condicion){
				if (condicion != null)
					$('#condicionesEnvioRecepcion').text(condicion.descripcion);
			}});
			$('[for=condicionesEnvioRecepcion]').show();
		}
		
	} 
	
	
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
	
	$('#agregar').click(function(){
		$('#fallasAsignadas').removeClass('error');
		$('#fallasAsignadas').attr('title','');
		$('#fallasProducto').find('#descripcionFalla').removeClass('error');
		$('#fallasProducto').find('#descripcionFalla').attr('title','');
		var sel = $("#tiposFallas").find(':selected');
		if (sel != null) change = true;
		$("#fallasAsignadas").append(sel);
	});
	
	$('#quitar').click(function(){
		var sel = $("#fallasAsignadas").find(':selected');
		if (sel != null) change = true;
		$("#tiposFallas").append(sel);
	});
	
	$('#quitar option').dblclick(function(el){
		alert(this);
		alert(el);
	});
	
	$('#siguiente').click(function(){
		var idTipoFallas = new Array;
		if ($('#fallasAsignadas option').length == 0 && $('#fallasProducto').find('#descripcionFalla').val() == "") {
			$('#fallasAsignadas').addClass('error');
			$('#fallasAsignadas').attr('title','Debe seleccionar al menos un tipo de falla o ingresar la descripción de la falla del producto');
			$('#fallasProducto').find('#descripcionFalla').addClass('error');
			$('#fallasProducto').find('#descripcionFalla').attr('title','Debe seleccionar al menos un tipo de falla o ingresar la descripción de la falla del producto');
		} else {
			$('#fallasAsignadas option').each(function(){
				idTipoFallas.push($(this).val());
			});
			SSTFacade.saveTipoFallas(idOT,idTipoFallas,{idOT:idOT,descripcionFalla:$('#fallasProducto').find('#descripcionFalla').val()},{async:true,callback:function(agrego){
				parent.location = context +'/index.do?e=' + moduloAccesorios.codigo + '&m=' + moduloInicial.codigo + '&idOT='+idOT;
			}});
		}
	});
	
	$('#cancelar').click(function(){
		jConfirm('Este boton, desactiva la orden de trabajo, ¿Esta seguro de desactivar la orden de trabajo?','Confirmacion',function(r){
			if(r){
				SSTFacade.updateDesactivarOTByFilter({idOT:idOT,motivoDesactivacion:'El logistico de sucursal, cancela la orden de trabajo en su estado de preparación'},{async:true,callback:function(desactivo){
					if(desactivo>0){
						SSTFacade.saveGestion(idOT, " desactiva la orden de trabajo",{async:false,callback:function(){
							parent.location = context +'/index.do?e=' + moduloBuscador.codigo + '&m=' + moduloInicial.codigo;
						}});
					} else {
						jAlert('Error al actualizar el registro','Error');
					}
				}});
			}
		});
	});
});