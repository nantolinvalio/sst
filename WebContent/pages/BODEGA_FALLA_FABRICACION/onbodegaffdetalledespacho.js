var despacho;
$(document).ready(function() {
//	$('#tabs').hide();
	
	SSTFacade.getDespachoById($("#idDespacho").val(),{async:false,callback:function(despa){
		despacho = despa;
		if(despacho.estado.id==90006000){
			$('#etapasDespacho .preparacion').removeClass('disabled');
			$('#etapasDespacho .traslado2').addClass('disabled');
			$('#etapasDespacho .terminar').addClass('disabled');
		}else if(despacho.estado.id==90006001){
			$('#etapasDespacho .preparacion').addClass('disabled');
			$('#etapasDespacho .traslado2').removeClass('disabled');
			$('#etapasDespacho .terminar').addClass('disabled');
		} else if(despacho.estado.id==90006002){
			$('#etapasDespacho .preparacion').addClass('disabled');
			$('#etapasDespacho .traslado2').addClass('disabled');
			$('#etapasDespacho .terminar').removeClass('disabled');
		}
	}});
	
	

	loadSubmodulosByPage('onbodegaffdetalledespacho','tabs',true,despacho,true);
});

