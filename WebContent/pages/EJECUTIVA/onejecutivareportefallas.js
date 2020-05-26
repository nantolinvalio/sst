$(document).ready(function() {
	
	if($('#producto').val() != 'null'){
		$('#idProducto').val($('#producto').val());
	}
	
	$('.fecha').datepicker();
	$('#filtro_buscador').validate();
	$('#fechaCreacionFin').rules('add',{dateRangeMax:'#fechaCreacion'});
	$('#fechaCreacion').rules('add',{dateRangeMin:'#fechaCreacionFin'});
	$('#fechaCreacion').rules('add',{rangoMeses6:'#fechaCreacionFin'});
	
	if($('#fechaInicio').val() != null && $('#fechaFin').val() != null){
		$('#fechaCreacionFin').val($('#fechaFin').val().toString("dd/MM/yyyy"));	
		$('#fechaCreacion').val($('#fechaInicio').val().toString("dd/MM/yyyy"));	
	} else {
		SSTFacade.getDate({async:false,callback:function(fechaActual){
			$('#fechaCreacionFin').val(fechaActual.toString("dd/MM/yyyy"));	
			$('#fechaCreacion').val(fechaActual.addMonths(-3).toString("dd/MM/yyyy"));	
		}});
	}
	
	SSTFacade.listMarca({async:false,callback:function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
		if($('#marca').val() != null){
			$("#idMarca").val($('#marca').val());
		}
	}});
	
	SSTFacade.listProveedor({async:false,callback:function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
		if($('#proveedor').val() != null){
			$("#idProveedor").val($('#proveedor').val());
		}
	}});
	
	
	
//	SSTFacade.listParametrosByTipo('TICA',{async:false,callback:function(data){
//		var tipos =[];
//		$.each(data, function(index, tipo){
//			if(tipo.codigo != 'VA' && tipo.codigo != 'JT'){
//				tipos.push(tipo);
//			}
//		});
//		$("#tipoCambio").addItems(tipos, "id", "glosa", true);
//	}});
	
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

	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listReporteFallaByBilter,
		seccion: 1000600000,
		tipo: 'REFA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'producto.id',propiedad:function(o){
		    	return o.producto.id +' '+o.producto.descripcion;
		    }}, {findName:'ubicacion.nombre',propiedad:function(o){
		    	return o.ubicacion.nombre +' '+o.ubicacion.direccion;
		    }},{findName:'fallas',propiedad:function(o){
		    	return o.fallas;
		    }},
	   	],
	   	buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
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
			"&report=ProveedorReportFallas" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ProveedorReportFallas", 600, 800);
	};
	
	buscaProveedorReporte();
});