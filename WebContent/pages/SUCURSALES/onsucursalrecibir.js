var ordenTrabajo;
$(document).ready(function(){
	$("#tabs, #etapas").hide();
	loadSubmodulosByPage('onsucursalrecibir','tabs');
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#buscarDocumento').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	var buscar = function(){
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
		
		SSTFacade.getOTRecibeSucursal(filtro,{async:true,callback:function(ot){
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
});

