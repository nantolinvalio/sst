$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#filtro_buscador').find('select').not('#tipoDocumento,#idEstados').selectInput();
	$('#filtro_buscador').validate();
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegadetalledespacho',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var moduloDespacho;
	SSTFacade.getModuloByNombreUsuario('onbodegatrasladarproductosaff',{async:false, callback:function(modulo) {
		moduloDespacho = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listEstadoDespacho({async:true,callback:function(data){
		$('#idEstado').addItems(data, "id", "glosa", true);
	}});
	
	SSTFacade.listParametrosByTipo('TICA',{async:false,callback:function(data){
		$("#tipoCambio").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			if (ubicacion.tipo == 'CD') {
				filtro.idSucursal = ubicacion.id;
			}
			$('#resultados').loadData([filtro]);
		}
	});
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listDespachosToFallaFabricacionByFilter, 
		seccion: 90003000,
		showTableToggleBtn: true,
		tipo: 'DEFF',
		multisel:false,
		singleSelect:true,
		overrideModel: [{findName:'estado.glosa',propiedad:function(o){
			return o.estado.glosa;
   	}},
   	],
   	dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo +  '&idDespacho=' + el.id;
		},
		buttons : [
		           	{name: 'Ingresar Nuevo Despacho', bclass: 'btnPlus', onpress : function(){nuevoTraslado();} },
					{separator: true},
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true},
			   		],
	});
	
	var filtro = $('#filtro_buscador').serializeObject();
	if (ubicacion.tipo == 'CD') {
		filtro.idSucursal = ubicacion.id;
	}
	$('#resultados').loadData([filtro]);
	
	var nuevoTraslado = function(){
		parent.location = context +'/index.do?e=' + moduloDespacho.codigo + '&m=' + moduloInicial.codigo;
	};
	
	
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
			"&report=DespachosFFReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "DespachosFFReport", 600, 800);
	};
});