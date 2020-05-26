$(document).ready(function() {
	var menuIndicador, nombreModulo;
	SSTFacade.getModuloInicialByUsuario({async:false, callback:function(modulo) {
		menuIndicador = modulo.codigo;
		nombreModulo = modulo.nombre;
	}});

	var moduloFacturaDetalle;
	SSTFacade.getModuloByNombre('onmenuindicadorfacturadetalle',{async:false, callback:function(modulo) {
		moduloFacturaDetalle = modulo.codigo;
	}});
	
	$('.fecha').datepicker();
	$('select').selectInput();
	

	$('#filtro_buscador').validate();
	$('#fechaInicio').rules('add',{dateRangeMin:'#fechaTermino'});
	$('#fechaTermino').rules('add',{dateRangeMax:'#fechaInicio'});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#facturas').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#facturas').loadData([filter]);
		}
	});
	
	SSTFacade.listEjecutivasMarca({async:true,callback:function(data){
		$("#idEjecutiva").addItems(data, "id", ["nombre", "apellidoPaterno"], true);
	}});
	
	SSTFacade.listTransportista({async:true,callback:function(data){
		$("#idTransportista").addItems(data, "id", "nombreCompleto", true);
	}});
	
	SSTFacade.listTiposOT({async:true,callback:function(data){
		$("#tipoOT").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listProveedor({async:true,callback:function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
	}});
	
	var exportDocument = function(type){
		var p = $('#facturas').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#facturas').getSortName()[0];
		p.sortOrder = $('#facturas').getSortOrder()[0];
		var f = $('#facturas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=FacturacionResumenReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "PDF_OT", 600, 800);
	};

	
	$('#facturas').flexigrid({
		dwrFunction: SSTFacade.listResumenFacturasByOT,
		seccion: 20002000,
		showTableToggleBtn: true,	
		idProperty:'rut',
		tipo: 'resumen',
		singleSelect: true,
		multisel: false,
		dblclickFunction:function(el,idx,comp){
			var tipoOT = ''; facturarTipo = '';  idEjecutiva = 0; fechaInicio = ''; fechaTermino = '';
			parent.location = context +'/index.do?e=' + moduloFacturaDetalle + '&m=' + menuIndicador + '&idIndicador=-1&idRol=6&idFacturar=' + el.rut + '&tipoOT=' + tipoOT + '&idEjecutiva='+0+'&fechaInicio='+fechaInicio+'&fechaTermino='+fechaTermino+'&facturarTipo='+facturarTipo;
		},
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		],
	});
	
});