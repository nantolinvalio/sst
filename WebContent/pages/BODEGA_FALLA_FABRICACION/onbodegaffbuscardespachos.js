$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#filtro_buscador').validate();
	var idDestino;
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegaffdetalledespachoexterno',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var moduloDespacho;
	SSTFacade.getModuloByNombreUsuario('onbodegafftrasladarproductos',{async:false, callback:function(modulo) {
		moduloDespacho = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.listMarca(function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	});
	
	SSTFacade.listProveedorRemateLiquidacion({async:false,callback:function(data){
		$('#tipoUbicacion').addItems(data, "nombre", "glosa", true);
	}});
	
	SSTFacade.listEstadoDespacho({async:false,callback:function(data){
		$('#idEstado').addItems(data, "id", "glosa", true);
	}});
	
	$('#tipoUbicacion').change(function(){
		loadProveedores();
	});
	
	var loadProveedores = function(){
		var tipo = $("#tipoUbicacion").val();
		var proveedores = [];
		$('#idDestino').empty();
		$('#input-no-idDestino').val('');
		if (tipo == "PR") {
			SSTFacade.listProveedor({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		}else if(tipo == "CR"){
			SSTFacade.listUbicacionByTipo('CR',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		} else if(tipo == "LQ"){
			SSTFacade.listUbicacionByTipo('LQ',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		}else if(tipo == "ST"){
			SSTFacade.listDestinosForIdOrigen({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		}else {
			var data = [{id:"",descripcion:"[SELECCIONE TIPO UBICACION]"}];
			$("#idDestino").addItems(data, "id", "descripcion", false);
		}
		idDestino = $('#idDestino').val();
	};

	var proveedores = [{id:"",descripcion:"[SELECCIONE TIPO UBICACION]"}];
	$("#idDestino").addItems(proveedores, "id", "descripcion", false);
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
		loadProveedores();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	var buscar= function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			if (ubicacion.tipo == 'CD') {
				filtro.idSucursal = ubicacion.id;
			}
			$('#resultados').loadData([filtro]);
		}
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listDespachosDesdeFFByFilter,
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
   		var despacho;
   		SSTFacade.getDespachoById(el.id,{async:false,callback:function(desp){
   			despacho = desp;
   		}});
			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo +  '&idDespacho=' + el.id+ '&idDestino=' +despacho.destino.id;
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
			"&report=DespachosDesdeFFReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "DespachosDesdeFFReport", 600, 800);
	};
	
	var filtro = $('#filtro_buscador').serializeObject();
	if (ubicacion.tipo == 'CD') {
		filtro.idSucursal = ubicacion.id;
	}
	$('#resultados').loadData([filtro]);
	
});