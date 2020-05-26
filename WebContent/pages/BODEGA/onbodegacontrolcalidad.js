var ordenTrabajo;
$(document).ready(function(){
	$("#tabs, #macro_etapas").hide();
	loadSubmodulosByPage('onbodegacontrolcalidad','tabs');
	
	$('#buscarDocumento').keypress(function(e){
        if(e.which == 13) {
        	buscar();
        }
	});
	
	
	$('#buscar').click(function(){
    	buscar();
    });
	
	var buscar = function(){
		var otguia =new Array;
		otguia.push($('#idOT'));
		otguia.push($('#numeroSerie'));
		
		$("#tabs, #etapas").hide();
		$('#tabs').tabs('select', 1);
		
		
		if(validEmptyInputs(otguia,'debe ingresar alguno de los parametros de busqueda') && $('#buscarDocumento').valid()){ 
		    var filter = $('#buscarDocumento').serializeObject();
			
		    SSTFacade.getOTControlCalidadByFilter(filter, {async:false,callback:function(ot){
		    	ordenTrabajo = ot;
		    	$("#tabs, #etapas").show();
		    	loadAll(ordenTrabajo);
		    	loadEtapasOT(ordenTrabajo);
			}});
		}
	};

	var loadAll = function(ordenTrabajo) {
		loadAllTabsWithOrdenTrabajo(ordenTrabajo);
	};
	
});
