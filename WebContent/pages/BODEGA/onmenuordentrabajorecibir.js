var ordenTrabajo;

$(document).ready(function(){
	$("#tabs, #etapas").hide();
	loadSubmodulosByPage('onmenuordentrabajorecibir','tabs');
	
	$('#buscadorAutomatico').find('#codigoBarra').keypress(function(e){
		if(e.which == 13) {
			if ($('#buscadorAutomatico').find('#codigoBarra').valid()) {
				
				$("#tabs, #etapas").hide();
				$('#tabs').tabs('select', 0);
				
				var filtro = $('#buscadorAutomatico').serializeObject();
				buscar(filtro);
			}
	    }
	});
	
	$('#buscarDocumento').keypress(function(e){ 
		if(e.which == 13) {
			prebuscar();
		}	
	});
	
	$('#buscar').click(function(){
		prebuscar();
	});
	
	
	var prebuscar = function(){
		var inpt = new Array;
		inpt.push($('#idOT'));
		inpt.push($('#numeroGuia'));
		inpt.push($('#numeroContrato'));
		
		$("#tabs, #etapas").hide();
		$('#tabs').tabs('select', 0);
     
		if (!validEmptyInputs(inpt,'Debe ingresar al menos uno de los parametros de busqueda')) {
			return;       
		}
		
		if (!$('#buscarDocumento').valid()){
			return;
		}
		
		var filtro = $('#buscarDocumento').serializeObject();
		
		buscar(filtro);
	};
	
	var buscar = function(filtro){
		SSTFacade.getOTRecibeBodega(filtro,{async:true,callback:function(ot){
			$("#tabs, #etapas").show();
			$('#buscarDocumento').reset();
			ordenTrabajo = ot;
			loadAll(ordenTrabajo);
		}});
	};
	
	
	var loadAll = function(ordenTrabajo) {
		loadEtapasOT(ordenTrabajo);
		loadAllTabsWithOrdenTrabajo(ordenTrabajo);
	};
	
	$('#buscadorAutomatico').find('#codigoBarra').focus();
//	$('#codigoBarrasAccesorios').focus();
});
