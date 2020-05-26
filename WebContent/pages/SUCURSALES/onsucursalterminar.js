var ordenTrabajo;

$(document).ready(function(){
	$('#tabs').hide();
	var idOT = parseInt($('#idOT').val());
	
	SSTFacade.getOTById(idOT,{async:false,callback:function(ot){
		ordenTrabajo = ot;
	}});
	
	if (ordenTrabajo.cambioAutorizado) {
		$('#tituloPagina').text('Recepción de producto con autorización de cambio' + (ordenTrabajo.tipo.codigo == 'GM' ? '. Garantía Master' : ''));
		$('.gProveedor, .gMaster').css('display','none');
	}
	
	loadSubmodulosByPage('onsucursalterminar','tabs', true, ordenTrabajo, true);
});