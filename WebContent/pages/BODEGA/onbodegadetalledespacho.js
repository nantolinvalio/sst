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
	
	
	loadSubmodulosByPage('onbodegadetalledespacho','tabs',true,despacho,true);
});

