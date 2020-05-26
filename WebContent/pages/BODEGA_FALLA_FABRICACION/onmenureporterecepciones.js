$(document).ready(function() {
	$('#filtro_buscador').find('select').not('#transportista, #usuarioRecepcion\\.id, #estadoRecepcionGuia\\.id, #estadoActualGuia\\.id, #estadoRecepcionOT\\.id').selectInput();

	$('.fecha').datepicker();
	
	$('#filtro_buscador').validate();
	$('#fechaCreacion').rules('add',{dateRangeMin:'#fechaCreacionFin'});
	$('#fechaRecepcionInicio').rules('add',{dateRangeMin:'#fechaRecepcionFin'});
	
	$('#fechaCreacionFin').rules('add',{dateRangeMax:'#fechaCreacion'});
	$('#fechaRecepcionFin').rules('add',{dateRangeMax:'#fechaRecepcionInicio'});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()){
			var filtro = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filtro]);
		}
	});
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listMarca(function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	});
	
	SSTFacade.listAllZonas({async:true,callback:function(data){
		$("#idZona").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listSucursales(function(data){
		$("#idSucursal").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.listTransportista({async:false,callback:function(transportistas){
		$('#transportista').addItems(transportistas,"id","nombreCompleto",true);
	}});
	
	SSTFacade.listLogisticosRecepcionesCamion({async:false,callback:function(logisticos){
		$('#usuarioRecepcion\\.id').addItems(logisticos,"id","nombreCompleto",true);
	}});
	
	SSTFacade.listEstadoOTFallaFabricacion({async:false,callback:function(estados){
		$('#estadoRecepcionOT\\.id').addItems(estados,"id","glosa",true);
	}});
	
	SSTFacade.listEstadoRecepcionGuia({async:false,callback:function(estados){
		$('#estadoRecepcionGuia\\.id').addItems(estados,"id","glosa",true);
		$('#estadoActualGuia\\.id').addItems(estados,"id","glosa",true);
	}});
	
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
			"&report=RecepcionFallaFabricacionReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "RecepcionFallaFabricacionReport", 600, 800);
	};
	
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTByRecepcionesFilter,
		seccion: 80003000,
		striped: true,
		showTableToggleBtn: true,
		tipo: 'RECOT',
		multisel:false,
		singleSelect:true,
		overrideModel: [{findName:'ordenTrabajo.producto.id',propiedad:function(o){
				return o.ordenTrabajo.producto.id+' '+o.ordenTrabajo.producto.descripcion;
			}},{findName:'recepcionOrdenTrabajo.estado.id',propiedad:function(o){
		    	return o.recepcionOrdenTrabajo.estado.glosa;
			}},{findName:'ordenTrabajo.sucursal.id',propiedad:function(o){
		    	return o.ordenTrabajo.sucursal.glosa;
			}},{findName:'ordenTrabajo.producto.familia.id',propiedad:function(o){
				if (o.ordenTrabajo.producto != null && o.ordenTrabajo.producto.familia != null && o.ordenTrabajo.producto.familia.nombre)
					return o.ordenTrabajo.producto.familia.nombre;
			}},{findName:'recepcionOrdenTrabajo.guia.estado.id',propiedad:function(o){
		    	return o.recepcionOrdenTrabajo.guia.estado.glosa;
			}},{findName:'recepcionCamion.transportista.id',propiedad:function(o){
		    	return o.recepcionCamion.transportista.nombreCompleto;
			}},{findName:'ordenTrabajo.producto.familia.linea.id',propiedad:function(o){
				if (o.ordenTrabajo.producto != null && o.ordenTrabajo.producto.familia != null && o.ordenTrabajo.producto.familia.linea != null)
					return o.ordenTrabajo.producto.familia.linea.glosa;
			}},{findName:'ordenTrabajo.producto.marca.codigo',propiedad:function(o){
				if (o.ordenTrabajo.producto != null && o.ordenTrabajo.producto.marca != null && o.ordenTrabajo.producto.marca.nombre != null)
		        	return o.ordenTrabajo.producto.marca.nombre;
			}},{findName:'recepcionOrdenTrabajo.usuario.id',propiedad:function(o){
		    	return o.recepcionOrdenTrabajo.usuario.nombreCompleto;
			}},{findName:'recepcionOrdenTrabajo.guia.estadoActual.id',propiedad:function(o){
		    	if(o.recepcionOrdenTrabajo.guia.estadoActual != null && o.recepcionOrdenTrabajo.guia.estadoActual.glosa != null)
		    		return o.recepcionOrdenTrabajo.guia.estadoActual.glosa;
			}},{findName:'ordenTrabajo.sucursal.zona.id',propiedad:function(o){
				if (o.ordenTrabajo.sucursal.zona != null)
					return o.ordenTrabajo.sucursal.zona.nombre;
				return '';
			}},
		],
		dblclickFunction:function(el,idx,comp){
//			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + el.id;
		},
		buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
			   		{separator: true}
		   		]
	});
	
	if($('#hiddenRecepcion').val() != "null" && $('#hiddenRecepcion').val() != ""){
		$('#idRecepcion').val($('#hiddenRecepcion').val());
		var filtro = $('#filtro_buscador').serializeObject();
		$('#resultados').loadData([filtro]);
	}
});