var despacho;
$(document).ready(function() {
//	$('#tabs').hide();
	
	SSTFacade.getDespachoById($("#idDespacho").val(),{async:false,callback:function(despa){
		despacho = despa;
	}});
	
	if(despacho.estado.id == 90001000){
		$('#etapas').find('#preparacion').removeClass('disabled');
	} else if(despacho.estado.id == 90001500){
		$('#etapas').find('#busqueda').removeClass('disabled');
	}else{
		$('#etapas').find('#terminar').removeClass('disabled');
	}
	
	
	loadSubmodulosByPage('onbodegaffdetalledespachoexterno','tabs',true,despacho,true);
	mostrarPestanas();
});

var mostrarPestanas = function(){
	SSTFacade.listGuiasByIdDespacho(despacho.id,{async:false,callback:function(guias){
		if(guias.length == 0){
			$("#onbodegaffdetalleremate" ).hide();
			$("#onbodegaffdetalleproveedor" ).hide();
		} else {
			var emitida = false;
			var tipoDestino = {};
			$.each(guias,function(index,guia){
				SSTFacade.getUbicacionById(guia.destino.id,{async:false,callback:function(ubicacion){
					if(guia.estado.id == 50005000){
						emitida = true;
						if(ubicacion != null){
							tipoDestino = ubicacion.tipo;
						}
					}
				}});
		   });
			if(emitida){
				if(tipoDestino != null && tipoDestino == 'LQ' || tipoDestino == 'CR'){
					$("#onbodegaffdetalleremate" ).show();
					$("#onbodegaffdetalleproveedor" ).hide();
				} else {
					$("#onbodegaffdetalleproveedor" ).show();
					$("#onbodegaffdetalleremate" ).hide();
				}
			}else{
				$("#onbodegaffdetalleremate" ).hide();
				$("#onbodegaffdetalleproveedor" ).hide();
			}
		}
	}});
};