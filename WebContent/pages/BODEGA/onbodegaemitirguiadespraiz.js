var ordenTrabajo;
var idGuia;

$(document).ready(function() {
	$('#tabs').hide();
	idGuia = $("#idGuia").val();
	
	var moduloAnterior;
	SSTFacade.getModuloByNombreUsuario('onbodegaemitirguia',{async:false, callback:function(modulo) {
		moduloAnterior = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getOTById($("#idOT").val(),{async:false,callback:function(ot){
		ordenTrabajo=ot;
	}});

	$('#volver').click(function(){
		parent.location = context + '/index.do?e=' + moduloAnterior.codigo + '&m=' + moduloInicial.codigo;
	});

	loadEtapasOT(ordenTrabajo);
	
	loadSubmodulosByPage('onbodegaemitirguiadespraiz','tabs', false, ordenTrabajo, true, true);
	
});