var ordenTrabajo;

$(document).ready(function(){
	$("#tabs, #etapas").hide();
	loadSubmodulosByPage('onsucursalentregacliente','tabs');

	
	$('#buscarDocumento').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	var buscar = function(){
		var inpt = new Array;
		inpt.push($('#idOT'));
		inpt.push($('#numeroFolio'));

		$("#tabs, #etapas").hide();
		$('#tabs').tabs('select', 0);

		if (!validEmptyInputs(inpt,'Debe ingresar al menos uno de los parametros de busqueda')) {
			return;
		}
		
		if (!$('#buscarDocumento').valid()){
			return;
		}
		
		var filtro = $('#buscarDocumento').serializeObject();
		
		SSTFacade.getOTByFilterForEntregaCliente(filtro,{async:true,callback:function(ot){
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
