$(document).ready(function() {
	$('.fecha').datepicker();
	$('#filtro_buscador').find('select').not('#idEstado,#idTipoAutorizacion,#vigente').selectInput();

	$('#filtro_buscador').validate();
	$('#fechaInicioDesde').rules('add',{dateRangeMin:'#fechaInicioHasta'});
	$('#fechaTerminoDesde').rules('add',{dateRangeMin:'#fechaTerminoHasta'});
	$('#fechaCreacionDesde').rules('add',{dateRangeMin:'#fechaCreacionHasta'});
	
	$('#fechaInicioHasta').rules('add',{dateRangeMax:'#fechaInicioDesde'});
	$('#fechaTerminoHasta').rules('add',{dateRangeMax:'#fechaTerminoDesde'});
	$('#fechaCreacionHasta').rules('add',{dateRangeMax:'#fechaCreacionDesde'});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#reglasComerciales').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#reglasComerciales').loadData([filtro]);
		}
	});
	
	$('#idFamilia').change(function(){
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
	});
	
	var exportDocument = function(type){
		var p = $('#reglasComerciales').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#reglasComerciales').getSortName()[0];
		p.sortOrder = $('#reglasComerciales').getSortOrder()[0];
		var f = $('#reglasComerciales').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ReglasComercialesHistoricasReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ReglasComercialesHistoricasReport", 600, 800);
	};

	var getReglaComercialHistorica = function (id) {
		$('#detalleRegla').dialog('open');
		$('#configuracion_general').reset();
		SSTFacade.getReglaComercialHisoricaById(id,function(regla){
			$('#configuracion_general').loadObject(regla);
			createJSTreeZonas(regla.zonas);
			createJSTreeLineas(regla.lineas);
		});
	};

	$('#reglasComerciales').flexigrid({
		dwrFunction: SSTFacade.listReglasComercialesHistoricasByFilter,
		seccion: 60002000,
		showTableToggleBtn: true,
		tipo: 'reglaH',
		multisel:false,
		singleSelect:true,
		idProperty: 'idHistorico',
		height:280,
		overrideModel: [{findName:'vigente',propiedad:function(o){return o.vigente ? 'ACTIVA' : 'INACTIVA';}}],
		dblclickFunction:function(el,idx,comp){
			getReglaComercialHistorica(el.idHistorico);
		},
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true}],
	});
	
	var createJSTreeLineas = function(lineas) {
		if ($("#productoFamiliaLinea").is('.jstree')) {
			$("#productoFamiliaLinea").jstree('destroy');
		}
		$("#productoFamiliaLinea").jstree({
			"json_data" : {"data" :lineas},
	        "themes" : {
	            "theme" : "default",
	            "dots" : true,
	            "icons" : false
	        },
	        "ui" : {
				"select_limit" : 1
			},
			"checkbox" : {
				  "check_node" : function () {
				     if(node_should_be_disabled) return false;
				     return true;
				  }
			},
	        "plugins" : [ "themes", "json_data", "checkbox", "ui"]
		});
	};
	
	var createJSTreeZonas = function(zonas) {
		if ($("#zonaTienda").is('.jstree')) {
			$("#zonaTienda").jstree('destroy');
		}
		$("#zonaTienda").jstree({
			"json_data" : {"data" : zonas},
	        "themes" : {
	            "theme" : "default",
	            "dots" : true,
	            "icons" : false
	        },
	        "ui" : {
				"select_limit" : 1
			},
	        "plugins" : [ "themes", "json_data", "checkbox", "ui"]
		});
	};
	
	SSTFacade.listReglasComercialByFilter({},function(data){
		$("#idRegla").addItems(data, "id", "nombre", true);
	});

	SSTFacade.listAllZonas(function(data){
		$("#idZona").addItems(data, "id", "nombre", true);
	});

	SSTFacade.listSucursales(function(data){
		$("#idTienda").addItems(data, "id", "nombre", true);
	});

	SSTFacade.listAllLineas(function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	});

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
	
	SSTFacade.listUsuariosReglasComerciales(function(data){
		$("#idUsuario").addItems(data, "id", "nombreCompleto", true);
	});
	
	$('#detalleRegla').dialog({
		autoOpen: false,
		modal:true,
		width:940,
		buttons:{
			Cerrar: function(){
				$('#detalleRegla').dialog('close');
			}
		}
	});
	
});