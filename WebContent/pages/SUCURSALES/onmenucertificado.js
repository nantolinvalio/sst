$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#certificado').find('select').not('#vigente').selectInput();
	
	
	$('#buscar').attr('disabled','disabled');
	$('#fechas').css('display','none');
	
	$('#certificado').validate();
	$('#fechaCreacion').rules('add',{dateRangeMin:'#fechaCreacionFin'});
	
	
//	var moduloDetalle;
//	SSTFacade.getModuloByNombreUsuario('ondetalleotcambioautomatico',{async:false, callback:function(modulo) {
//		moduloDetalle = modulo ? modulo : undefined;
//	}});
	
	$('#limpiar').click(function(){
		$('#certificado').reset();
		loadProductos();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filtro]);
		}
	});
	
//	var exportDocument = function(type){
//		var p = $('#resultados').getParameters()[0];
//		if (p == undefined) {
//			jAlert(msg.sinParametros,'Información');
//			return;
//		}
//		p.orderBy = $('#resultados').getSortName()[0];
//		p.sortOrder = $('#resultados').getSortOrder()[0];
//		var f = $('#resultados').getFilterColumna()[0];
//		var url = "/sst/ViewReportServlet?type=" + type + 
//			"&report=OrdenTrabajoCambioAutomaticoReport" + 
//			"&filter=" + JSON.stringify(p) +
//			"&filterColumn=" + JSON.stringify(f);
//		$.openWindowsMenubar(url, "OrdenTrabajoCambioAutomaticoReport", 600, 800);
//	};
		
	// carga familia y productos
	$('#idFamilia').change(function(){
		loadProductos();
	});
	
	var loadProductos = function(){
		var idFamilia = $("#idFamilia").val();
		var productos = [];
		$('#idProducto').empty();
		$('#input-no-idProducto').val('');
		if (idFamilia != "") {
			listProductos({idFamilia : idFamilia});
		} else {
			var productos = [{id:"",descripcion:"[SELECCIONE FAMILIA]"}];
			$("#idProducto").addItems(productos, "id", "descripcion", false);
		}
	};
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	var listProductos = function(filter) {
		SSTFacade.listProductosByFilter(filter,function(data){
			$("#idProducto").addItems(data, "id", "descripcion", true);
		});
	};
	var productos = [{id:"",descripcion:"[SELECCIONE FAMILIA]"}];
		$("#idProducto").addItems(productos, "id", "descripcion", false);
		// fin carga familia y productos
	
	SSTFacade.listMarca(function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	});
	
	$('#checkBuscar').click(function(){
		if ($('#checkBuscar').is(':checked')) {
			$('#fechas').css('display','');
			$('#buscar').removeAttr('disabled','disabled');
			$('#guardar').attr('disabled','disabled');
		}
		else{
			$('#fechas').css('display','none');
			$('#buscar').attr('disabled','disabled');
			$('#guardar').removeAttr('disabled','disabled');
		}
	});
	
	$('#resultadoCertificado').flexigrid({
		dwrFunction: SSTFacade.listReglasComercialesHistoricasByFilter,
		seccion: 60002000,
		showTableToggleBtn: true,
		tipo: 'reglaH',
		multisel:false,
		singleSelect:true,
		idProperty: 'idHistorico',
		overrideModel: [{findName:'vigente',propiedad:function(o){return o.vigente ? 'ACTIVA' : 'INACTIVA';}}],
		dblclickFunction:function(el,idx,comp){
			getReglaComercialHistorica(el.idHistorico);
		},
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true},
	   		{name: 'Ingresar Nueva Zona', bclass: 'btnPlus', onpress : function(){saveZona();}},
	   		{separator: true}],
	   		
	   		
	});
	
});
