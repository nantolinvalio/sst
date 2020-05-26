$(document).ready(function() {
	$('.fecha').datepicker();
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultados').showBody();
			$('#resultados').loadData([filter]);
		}
	});
	
	var menuIndicador, nombreModulo;
	SSTFacade.getModuloInicialByUsuario({async:false, callback:function(modulo) {
		menuIndicador = modulo.codigo;
		nombreModulo = modulo.nombre;
	}});

	var parentIndicadorOrdenTrabajo;
	SSTFacade.getModuloByNombre('onmenuordentrabajo',{async:false, callback:function(modulo) {
		parentIndicadorOrdenTrabajo = modulo.codigo;
	}});	
	
	var parentIndicadorFacturaDetalle;
	SSTFacade.getModuloByNombre('onpopupfacturadetalle',{async:false, callback:function(modulo) {
		parentIndicadorFacturaDetalle = modulo.codigo;
	}});	
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	

	
	SSTFacade.listEjecutivasMarca({async:true,callback:function(data){
		$("#idEjecutiva").addItems(data, "id", ["nombre", "apellidoPaterno"], true);
	}});
	
	SSTFacade.listTransportista({async:true,callback:function(data){
		$("#idTransportista").addItems(data, "id", "nombreCompleto", true);
	}});
	
	SSTFacade.listTiposOT({async:true,callback:function(data){
		$("#tipoOT").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listMarca({async:true,callback:function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	}});
	
	SSTFacade.listProveedor({async:true,callback:function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listFamilia({async:true,callback:function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	}});
	
	var exportDocument = function(type){
		var f = $('#facturas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=FacturacionReport" +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "PDF_OT", 600, 800);
	};
	
	var exportDocumentOT = function(type){
		var p = $('#resultados').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#resultados').getSortName()[0];
		p.sortOrder = $('#resultados').getSortOrder()[0];
		var f = $('#resultados').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoFacturacionReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, type.toUpperCase() + "_OT", 600, 800);
	};
	
	var aceptarFactura = function(){
		var sel = $('#facturas').getJSONCheckboxSelected();
		if(sel.length<1){
			jAlert("No se ha seleccionado ninguna Factura",'Información');
			return;
		} else {
			SSTFacade.AceptarFactura(sel,{async:false, callback:function() {
				jAlert("Factura(s) aceptada(s)",'Información');
				$('#facturas').flexReload();
				return;
			}});
		}
	};

	var rechazarFactura = function(){
		var sel = $('#facturas').getJSONCheckboxSelected();
		if(sel.length<1){
			jAlert("No se ha seleccionado ninguna Factura",'Información');
			return;
		} else {
			SSTFacade.RechazarFactura(sel, {async:false, callback:function() {
				jAlert("Factura(s) rechazada(s)",'Información');
				$('#facturas').flexReload();
				return;
			}});
		}		
	};
	
	var deshacerFactura = function(){		
		var sel = $('#facturas').getJSONCheckboxSelected();
		if(sel.length<1){
			jAlert("No se ha seleccionado ninguna Factura",'Información');
			return;
		} else if (sel.length==1){
			SSTFacade.deshacerFactura(sel, {async:false, callback:function() {
				jAlert("Factura desechada",'Información');
				$('#facturas').flexReload();
				return;
			}});
		} else{			
			jAlert("Solo se puede seleccionar una factura para deshacer",'Información');
			return;
		}
	};
	
    var procesarOW = function(){
    	var sel = $('#facturas').getJSONCheckboxSelected();
		if(sel.length<1){
			jAlert("No se ha seleccionado ninguna Factura",'Información');
			return;
		} else {
			SSTFacade.procesarOW(sel,{async:false, callback:function() {
				jAlert("Factura(s) procesada(s) en OW correctamente",'Información');
				$('#facturas').flexReload();
				return;
			}});			
		}	
    };
	
    var generarFactura = function(){
    	var sel = $('#resultados').getSelectedCheckFromList();
		if(sel.length<1){
			jAlert("No se ha seleccionado ninguna orden de Trabajo",'Información');
			return;
		} else {
			SSTFacade.GenerarFactura(sel,{async:false, callback:function() {
				jAlert("Generación de pedido de SC creado",'Información');
				$('#resultados').flexReload();
				$('#facturas').flexReload();
				return;
			}});			
		}	
    };
    
	$('#asignar').click(function(){
		var sel = $('#facturas').getJSONCheckboxSelected();
		SSTFacade.asignarFactura(sel, $("#numeroFactura").val(), $("#fechaEmision").val(), {async:false, callback:function() {
			jAlert("Factura asignada",'Información');
			$('#facturas').flexReload();
			return;
		}});
		//refrescar la grilla
	});

	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOtsFactura,
		seccion: 10005000,
		showTableToggleBtn: true,
		title: 'Resultados',
		tipo: 'OT2',
		startMinimized:true,
		multisel:false,
		singleSelect:true,
		saveCheckValues:true,
		dwrFunctionListAllId:SSTFacade.listAllOtsFacturaCheck,
		dblclickFunction:function(el,idx,comp){
			redirectOtDetalle(el.id);
		},
		overrideModel: [
			{findName:'semaforoServicioTecnico',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoServicioTecnico) + '"></div>');}},
			{findName:'semaforoSucursalInicio',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalInicio) + '"></div>');}},
			{findName:'semaforoSucursalfin',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalfin) + '"></div>');}},
			{findName:'sucursal.glosa',propiedad:function(o){if (o.sucursal){ return o.sucursal.id + ' ' + o.sucursal.glosa;}}},
			{findName:'checkMarca',
				 propiedad:function(o){
					 return $('<input type="checkbox">')
					 			.attr('id', 'chk' + o.id)
					 			.attr('name', 'chk' + o.id)
					 			.attr('disabled', (o.numeroCargo == 0 || (o.empresaFacturar.rut != null && o.empresaFacturar.rut == "82982300-4")))
					 			.attr('checked',$('#resultados').isRowChecked(o.id))
								.attr('onchange','changeState("resultados", ' + o.id + ',this)');
				 }}
		],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOT('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOT('xls');}},
			{separator: true},
			{name: 'Generar pedido de SC', bclass: 'btnFactura', onpress : function(){generarFactura();}},
			{separator: true}			
   		]
	});

	$('#facturas').flexigrid({
		dwrFunction: SSTFacade.listFacturas,
		seccion: 20003000,
		title: 'Resultados',
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,		
		tipo: 'fact2',
		dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + parentIndicadorFacturaDetalle + '&m=' + menuIndicador + '&idFactura=' + el.id;
		},
		overrideModel: [{findName:'checkMarca',
			 propiedad:function(o){
				 return $('<input type="checkbox">')
				 			.attr('id', 'chk' + o.id)
				 			.attr('name', 'chk' + o.id)
				 			.attr('checked',$('#resultados').isRowChecked(o.id))
							.attr('onchange','changeState("facturas", ' + o.id + ',this)');
			 }},{findName:'estado.id',propiedad:function(o){
					 return o.estado.glosa;
			 }},{findName:'idFacturar',propiedad:function(o){
				 return o.nombre;
			 }}
		 ],
		buttons : [
		    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
			{separator: true},
	   		{name: 'Marcar Aceptado', bclass: 'btnAceptar', onpress : aceptarFactura},
	   		{separator: true},
	   		{name: 'Marcar Rechazado', bclass: 'btnRechazar', onpress : rechazarFactura},
	   		{separator: true},
	   		{name: 'Deshacer Factura', bclass: 'btnDeshacer', onpress : deshacerFactura},
	   		{separator: true},
	   		{name: 'Procesar en OW', bclass: 'btnProcesar', onpress : procesarOW},
	   		{separator: true}
   		],
	});	
	
	$('#facturas').loadData([]);
	
});
