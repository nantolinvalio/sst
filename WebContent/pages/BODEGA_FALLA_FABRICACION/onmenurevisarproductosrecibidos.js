var ordenTrabajo;

$(document).ready(function(){
	$("#tabs, #etapas").hide();
	loadSubmodulosByPage('onmenurevisarproductosrecibidos','tabs');
	
	$('#buscar').click(function(){
		var inpt = new Array;
		inpt.push($('#idOT'));
		inpt.push($('#numeroGuiaRetiro'));
		inpt.push($('#buscadorManual').find('#codigoBarra'));
		inpt.push($('#numeroGuiaDespachoST'));
		
		$("#tabs, #etapas").hide();
		$('#tabs').tabs('select', 0);
     
		if (!validEmptyInputs(inpt,'Debe ingresar al menos uno de los parametros de busqueda')) {
			return;       
		}
		if (!$('#buscadorManual').valid()){
			return;
		}
		var filtro = $('#buscadorManual').serializeObject();
		buscar(filtro);
	});
	
	$('#buscadorAutomatico').find('#codigoBarra').keypress(function(e){
		if(e.which == 13) {
			if ($('#buscadorAutomatico').find('#codigoBarra').valid()) {
				var filtro = $('#buscadorAutomatico').serializeObject();
				buscar(filtro);
			}
	    }
	});
	
	var buscar = function (filter) {
		$("#tabs, #etapas").hide();
		$('#tabs').tabs('select', 0);
		$('#buscadorAutomatico').find('#codigoBarra').val('');
		$('#buscadorAutomatico').find('#codigoBarra').focus();
		SSTFacade.getOTRevisaBodegaFF(filter,{async:true,callback:function(ot){
			$("#tabs, #etapas").show();
			$('#buscadorManual').reset();
			ordenTrabajo = ot;
			loadAll(ordenTrabajo);
			$('#codigoBarrasAccesorios').focus();
		}});
	};
	
	var loadAll = function(ordenTrabajo) {
		loadEtapasOT(ordenTrabajo);
		loadAllTabsWithOrdenTrabajo(ordenTrabajo);
	};
	
	$('#codigoBarrasAccesorios').focus();
});

var reload = function() {
	$("#tabs, #etapas").hide();
	$('#codigoBarrasAccesorios').focus();
	
};

