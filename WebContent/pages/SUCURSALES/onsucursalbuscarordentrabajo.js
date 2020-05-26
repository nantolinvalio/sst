$(document).ready(function() {
	$('.fecha').datepicker();
	
	$('#limpiar_avanzado').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#limpiar_basico').click(function(){
		$('#filtro_buscador_basico').reset();
		$('#resultados').clean();
	});
	
	$('#buscar_avanzado').click(function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filter]);
		}
	});
	
	$('#buscar_basico').click(function(){
		var validar = [];
		validar.push($('#filtro_buscador_basico').find('#idOT'));
		validar.push($('#filtro_buscador_basico').find('#numeroSerie'));
		validar.push($('#filtro_buscador_basico').find('#rutCliente'));
		if($('#filtro_buscador_basico').valid() && validEmptyInputs(validar,'debe ingresar alguno de los parametros de busqueda')) {
			var filter = $('#filtro_buscador_basico').serializeObject();
			$('#resultados').loadData([filter]);
		}
	});
	
	$('#change_buscador_avanzado').click(function(){
		$('#buscador_basico').hide();
		$('#buscador_avanzado').show();
	});
	
	$('#change_buscador_basico').click(function(){
		$('#buscador_basico').show();
		$('#buscador_avanzado').hide();
	});
	
	$('#buscador_basico').keypress(function(e){
        if(e.which == 13) {
    		var validar = [];
    		validar.push($('#filtro_buscador_basico').find('#idOT'));
    		validar.push($('#filtro_buscador_basico').find('#numeroSerie'));
    		validar.push($('#filtro_buscador_basico').find('#rutCliente'));
    		if($('#filtro_buscador_basico').valid() && validEmptyInputs(validar,'debe ingresar alguno de los parametros de busqueda')) {
    			var filter = $('#filtro_buscador_basico').serializeObject();
    			$('#resultados').loadData([filter]);
    		}
    	}
	});
	
	$('#buscador_avanzado').keypress(function(e){
        if(e.which == 13) {
    		if($('#filtro_buscador').valid()) {
    			var filter = $('#filtro_buscador').serializeObject();
    			$('#resultados').loadData([filter]);
    		}
        }
	});
	
	$('#filtro_buscador').validate();
	$('#fechaInicio').rules('add',{dateRangeMin:'#fechaTermino'});
	$('#fechaTermino').rules('add',{dateRangeMax:'#fechaInicio'});
	
	var exportDocument = function(type){
		var p = $('#resultados').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#resultados').getSortName()[0];
		p.sortOrder = $('#resultados').getSortOrder()[0];
		var f = $('#resultados').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoReport", 600, 800);
	};
	
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});	

	SSTFacade.listSucursales({async:true,callback:function(data){
		$("#sucursal").addItems(data, "id", ["id", "nombre"], true);
		$("#sucursal > option[value=" + ubicacion.id + "]").attr('selected',true);
	}});
	
	SSTFacade.listSTecnicoByFilter({idOrigen:ubicacion.id},{async:true,callback:function(data){
		$("#idSTecnico").addItems(data, "id", ["glosa","direccion"], true,', ');
	}});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTByFilter,
		seccion: 30001000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'OT',
		dblclickFunction:function(el,idx,comp){      
			redirectOtDetalle(el.id);
		},
		overrideModel: [
			{findName:'semaforoServicioTecnico',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoServicioTecnico) + '"></div>');}},
			{findName:'semaforoSucursalInicio',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalInicio) + '"></div>');}},
			{findName:'semaforoSucursalfin',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalfin) + '"></div>');}},
			{findName:'sucursal.glosa',propiedad:function(o){return o.sucursal.id + ' ' + o.sucursal.glosa;}},							    
		],
		buttons : [
			{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			{separator: true}
		]
	});	
	
});
