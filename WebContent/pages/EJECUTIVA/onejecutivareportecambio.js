var moduloDetalle;
$(document).ready(function() {
	SSTFacade.getModuloByNombreUsuario('onejecutivareportefallas',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$('.fecha').datepicker();
	$('#filtro_buscador').validate();
	$('#fechaCreacionFin').rules('add',{dateRangeMax:'#fechaCreacion'});
	$('#fechaCreacion').rules('add',{dateRangeMin:'#fechaCreacionFin'});
	$('#fechaCreacion').rules('add',{rangoMeses6:'#fechaCreacionFin'});
	
	SSTFacade.getDate({async:false,callback:function(fechaActual){
		$('#fechaCreacionFin').val(fechaActual.toString("dd/MM/yyyy"));	
		$('#fechaCreacion').val(fechaActual.addMonths(-3).toString("dd/MM/yyyy"));	
	}});
	
	SSTFacade.listMarca({async:false,callback:function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	}});
	
	
	SSTFacade.listProveedor({async:false,callback:function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
	}});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listProveedorReportByfilter,
		seccion: 1000500000,
		tipo: 'RECA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'producto.id',propiedad:function(o){
		    	return o.producto.id +' '+o.producto.descripcion;
		    }},{findName:'cantidadFF',propiedad:function(o){
		    	if(o.cantidadFF == 0 ){
		    		return 0;
		    	}
		    	else {
		    		var a = $('<a></a>')
					.attr('href','#')
					.text(o.cantidadFF)
					.attr('onclick',"parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idProveedor=" + o.proveedor.id + "&idMarca=" + o.marca.codigo+"&idProducto="+ o.producto.id+"&tipoCambio="+"FF"+"&fechaInicio="+$('#fechaCreacion').val()+"&fechaFin="+$('#fechaCreacionFin').val()+"'");
		    		return a;
		    	}
		    }},{findName:'cantidadST',propiedad:function(o){
		    	if(o.cantidadST == 0 ){
		    		return 0;
		    	}
		    		else {
		    		var a = $('<a></a>')
					.attr('href','#')
					.text(o.cantidadST)
					.attr('onclick',"parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idProveedor=" + o.proveedor.id + "&idMarca=" + o.marca.codigo+"&idProducto="+ o.producto.id+"&tipoCambio="+"ST"+"&fechaInicio="+$('#fechaCreacion').val()+"&fechaFin="+$('#fechaCreacionFin').val()+"'");
		    		return a;
		    	}
		    }},{findName:'cantidadOT',propiedad:function(o){
		    	if(o.cantidadOT == 0 ){
		    		return 0;
		    	} 
		    		else {
		    		var a = $('<a></a>')
		    		.attr('href','#')
					.text(o.cantidadOT)
					.attr('onclick',"parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idProveedor=" + o.proveedor.id + "&idMarca=" + o.marca.codigo+"&idProducto="+ o.producto.id+"&fechaInicio="+$('#fechaCreacion').val()+"&fechaFin="+$('#fechaCreacionFin').val()+"'");
		    		return a;
		    	}
		    }},
	   	],	
	   	buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#fechaCreacion').removeClass('error');
		$('#fechaCreacionFin').removeClass('error');
		$('#fechaCreacion').removeAttr('title');
		$('#fechaCreacionFin').removeAttr('title');
		SSTFacade.getDate({async:false,callback:function(fechaActual){
			$('#fechaCreacionFin').val(fechaActual.toString("dd/MM/yyyy"));	
			$('#fechaCreacion').val(fechaActual.addMonths(-3).toString("dd/MM/yyyy"));	
		}});
		$('#resultados').clean();
	});
	
	var buscaProveedorReporte = function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filtro]);
		}
	};
	
	$('#buscar').click(function(){
		buscaProveedorReporte();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscaProveedorReporte();
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
			"&report=ProveedorReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ProveedorReport", 600, 800);
	};
	
});

