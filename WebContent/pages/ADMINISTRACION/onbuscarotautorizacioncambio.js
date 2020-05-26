$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#filtro_buscador').find('select').not('#idEstado,#idSucursal,#idZona,#clasificacion').selectInput();
	
	$('#filtro_buscador').validate();
	$('#fechaCreacion').rules('add',{dateRangeMin:'#fechaCreacionFin'});
	$('#fechaAutorizacionOtInicio').rules('add',{dateRangeMin:'#fechaAutorizacionOtFin'});
	$('#fechaRecepcionOtInicio').rules('add',{dateRangeMin:'#fechaRecepcionOtFin'});
	
	$('#fechaCreacionFin').rules('add',{dateRangeMax:'#fechaCreacion'});
	$('#fechaAutorizacionOtFin').rules('add',{dateRangeMax:'#fechaAutorizacionOtInicio'});
	$('#fechaRecepcionOtFin').rules('add',{dateRangeMax:'#fechaRecepcionOtInicio'});
	
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('ondetalleotcambioautomatico',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		loadProductos();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filtro]);
		}
	});
	
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
			"&report=OrdenTrabajoCambioAutomaticoReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoCambioAutomaticoReport", 600, 800);
	};
	
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
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.listEstadosAutorizacionCambio(function(data){
		$("#idEstado").addItems(data, "codigo", "glosa", true);
	});
	
	SSTFacade.listParametrosByTipo("CB",{async:false,callback:function(data){
		$("#clasificacion").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listAllZonas({async:false,callback:function(data){
		$("#idZona").addItems(data, "id", "nombre", true);
		if (ubicacion.tipo == 'SC') 
			$("#idZona").attr("disabled",false);
	}});
		
	SSTFacade.listSucursales(function(data){
		$("#idSucursal").addItems(data, "id", ["id", "nombre"], true);
		if (ubicacion.tipo == 'SC' || ubicacion.tipo == 'CD') {
			$("#idSucursal").attr("disabled",false);
			$("#idSucursal > option[value=" + ubicacion.id + "]").attr('selected',true);
		}else {
			$("#idSucursal").attr("disabled",true);
			$("#idSucursal > option[value=" + ubicacion.id + "]").attr('selected',true);
		}
	});
	
	SSTFacade.listMarca(function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	});
	
	SSTFacade.listTiposCambioAutomatico({async:true,callback:function(listTiposCambioAutomatico){
		$("#tipoCambio").addItems(listTiposCambioAutomatico, "codigo", "glosa", true);
	}});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTCambioAuomaticoByFilter,
		seccion: 10009000,
		showTableToggleBtn: true,
		tipo: 'OTAC',
		multisel:false,
		singleSelect:true,
		overrideModel: [{findName:'tipoCambio.codigo',propiedad:function(o){
        	if(o.tipoCambio == null){
        		return '';
        	}else{
        		return o.tipoCambio.glosa;
        	}
    	}},{findName:'sucursal.id',propiedad:function(o){
    		if (o.sucursal == null)
    			return '';
			return o.sucursal.id;
    	}},{findName:'sucursal.glosa',propiedad:function(o){
    		if (o.sucursal == null)
    			return '';
			return o.sucursal.glosa;
    	}},{findName:'producto.marca.codigo',propiedad:function(o){
    		if (o.producto != null && o.producto.marca != null && o.producto.marca.nombre != null)
            	return o.producto.marca.nombre;
    	}},{findName:'sucursal.zona.id',propiedad:function(o){
    		if(o.sucursal == null || o.sucursal.zona == null)
    			return '';	
        	return o.sucursal.zona.nombre;
    	}},{findName:'producto.familia.id',propiedad:function(o){
    		if (o.producto != null && o.producto.familia != null && o.producto.familia.nombre != null)
    			return o.producto.familia.nombre;
    	}},{findName:'clasificacion.codigo',propiedad:function(o){
        	if(o.clasificacion == null){
        		return '';
        	}else{
        		return o.clasificacion.glosa;
        	}
    	}},{findName:'producto.familia.linea.id',propiedad:function(o){
    		if (o.producto != null && o.producto.familia != null && o.producto.familia.linea != null && o.producto.familia.linea.glosa != null)
    			return o.producto.familia.linea.glosa;
    	}},{findName:'producto.id',propiedad:function(o){
    		if (o.producto != null)
    			return o.producto.id;
    	}},{findName:'producto.descripcion',propiedad:function(o){
    		if (o.producto != null && o.producto.descripcion != null)
    			return o.producto.descripcion;
    	}}],
		dblclickFunction:function(el,idx,comp){
			redirectOtDetalle(el.id);
		},
		buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
	});
	
});
