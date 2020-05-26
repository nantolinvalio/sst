var fechaActual;
$(document).ready(function(){
	var moduloActual;
	SSTFacade.getModuloByNombreUsuario('onbodegaemitirguiaremate',{async:false, callback:function(modulo) {
		moduloActual = modulo ? modulo : undefined;
	}});
	
	var exportDocumentOTSinGuia = function(type){
		var p = {};
		p.orderBy = $('#ordenesAutorizadasSinGuia').getSortName()[0];
		p.sortOrder = $('#ordenesAutorizadasSinGuia').getSortOrder()[0];
		var f = $('#ordenesAutorizadasSinGuia').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenesAutorizadasSinGuiaReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&gridControl=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "OrdenesAutorizadasSinGuiaReport", 600, 800);
	};
	
	var exportDocumentGuiaRemate = function(type){
		var p = {};
		p.orderBy = $('#guiasDespacho').getSortName()[0];
		p.sortOrder = $('#guiasDespacho').getSortOrder()[0];
		var f = $('#guiasDespacho').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=GuiaRemateReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&gridControl=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "GuiaRemateReport", 600, 800);
	};
	
	var generarGuiaDespacho = function(){
		var ordenesTrabajo = $('#ordenesAutorizadasSinGuia').getJSONCheckboxSelected();
		if(ordenesTrabajo.length < 1){
			jAlert("No se ha seleccionado ninguna Orden de Trabajo",'Información');
			return;
		}
		
		SSTFacade.saveGuiaRemate(ordenesTrabajo,{async:true,callback:function(){
			jAlert('La Guía se ha generado Correctamente', 'Información',function(){
				parent.location = context +'/index.do?e=' + moduloActual.codigo + '&m=' + moduloInicial.codigo;
			});
		}});
	};
	
	$("#ordenesAutorizadasSinGuia").flexigrid({
		height:100,
		showTableToggleBtn: true,
		multisel:false,
		tipo: 'OTREM',
		dwrFunction: SSTFacade.listOTRemate,
		seccion: 10010000,
		singleSelect:true,
		overrideModel: [{
			findName:'checkMarca',
				propiedad:function(o){
					 return $('<input type="checkbox">')
			 			.attr('id', 'chk' + o.id)
			 			.attr('name', 'chk' + o.id)
			 			.attr('checked',o.checkMarca);
    			}
            },{
			findName:'tipo.codigo',
				propiedad:function(o){
					 return o.tipo.glosa;
    			}
            },{
			findName:'producto.id',
				propiedad:function(o){
					if (o.producto != null) {
						return o.producto.id + ' ' + o.producto.descripcion + ' ' + (o.producto.marca != null ? o.producto.marca.nombre : '');						
					}
    			}
            },,{
			findName:'destino.glosa',
				propiedad:function(o){
					if (o.destino)
						return o.destino.glosa + ' ' + o.destino.nombre + ', ' + o.destino.direccion;
    			}
            }
            
        ],
		buttons : [
		    {name: 'Generar Guía Despacho', bclass: 'btnFactura', onpress : function(){generarGuiaDespacho();}},
	   		{separator: true},
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOTSinGuia('pdf');}},
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOTSinGuia('xls');}},
	   		{separator: true}
	   	]	
	});	
	
	var eliminarGuia = function(){
		var guias = $("#guiasDespacho").getJSONCheckboxSelected();
		if(guias.length == 0){
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert("No se ha seleccionado ninguna Guía",'Información');
			return;
		}
		$.alerts.okButton = '&nbsp;Si&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro que desea Eliminar las Guías Seleccionadas?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.eliminarGuiaRemate(guias, {async:false,callback:function(p){
					parent.location = context +'/index.do?e=' + moduloActual.codigo + '&m=' + moduloInicial.codigo;
				}});
			}
		});
	};
	
	var marcarRecibidaGuiaRemate = function(){
		var guias = $("#guiasDespacho").getJSONCheckboxSelected();
		if(guias.length == 0){
			$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
			jAlert("No se ha seleccionado ninguna Guía",'Información');
			return;
		}
		$.alerts.okButton = '&nbsp;Si&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro que desea marcar como recibidas las Guías Seleccionadas?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.marcarRecibidaGuiaRemate(guias, {async:false,callback:function(p){
					parent.location = context +'/index.do?e=' + moduloActual.codigo + '&m=' + moduloInicial.codigo;
				}});
			}
		});
	};
	
	$("#guiasDespacho").flexigrid({
		height:100,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		tipo: 'GREM',
		dwrFunction: SSTFacade.listGuiasRemate,
		seccion: 10010000,
		overrideModel: [{
			findName:'checkMarca',
				propiedad:function(o) {
					 return $('<input type="checkbox">')
			 			.attr('id', 'chk' + o.id)
			 			.attr('name', 'chk' + o.id)
			 			.attr('checked',o.checkMarca);
    			}
            },{
			findName:'destino.tipo',
				propiedad:function(o){
					return o.destino.tipo + ' ' + o.destino.nombre + ' ' + o.destino.direccion;
				}
            },{
			findName:'imprimir',
				propiedad:function(o){
					return $('<input type="button" value="Imprimir Guía" onclick="emitirGuiaRemate(' + o.id + ')">');
				}
            }
        ],
		buttons : [
		    {name: 'Marcar Recibida', bclass: 'btnAceptar', onpress : function(){marcarRecibidaGuiaRemate();}},
	   		{separator: true},
		    {name: 'Eliminar Guía', bclass: 'btnRechazar', onpress : function(){eliminarGuia();}},
	   		{separator: true},
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentGuiaRemate('pdf');}},
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentGuiaRemate('xls');}},
	   		{separator: true}
	   	]	
	});
	

	$("#ordenesAutorizadasSinGuia").loadData([]);

	$("#guiasDespacho").loadData([]);
	
	
	/*----------------------------------POPUP-------------------------------------*/
	
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	var minDate = fechaActual.clone().addDays(-2);
	var maxDate = fechaActual.clone().addDays(5);
	
	minDate = removeTimeFromDate(minDate.clone());
	maxDate = removeTimeFromDate(maxDate.clone());
	
	$('.fechaHora').datetimepicker({
		minDate: minDate,
		maxDate: maxDate
	});
	
	SSTFacade.listTransportista({async:true,callback:function(transportistas){
		$('#transportista\\.id').addItems(transportistas,"id","nombreCompleto",true);
	}});
	
	$('#oTsGuiaDespacho').flexigrid({
		height: 'auto',
		usepager: false, 
		showToggleBtn:false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		colModel: [
		   {display: 'N° OT',      width:40, align:'right', name_abbr:'id',          name:'id'},
		   {display: 'Producto',   width:280, align:'left' , name_abbr:'producto',    name:function(o){
			   return o.producto.descripcion+', '+o.producto.marca.nombre;
		   }},
		   {display: 'N° Serie',   width:60, align:'right', name_abbr:'numeroSerie', name:'numeroSerie'},
		   {display: 'Accesorios', width:400, align:'left',  name_abbr:'accesorios',  name:function(o){
			   var str = '';
			   SSTFacade.listAccesoriosOTbyFilter({idOT:o.id},{async:false,callback:function(accesorios){
				   $.each(accesorios,function(index,accesorio){
					   str+=accesorio.descripcion;
					   if(index<accesorios.length-1){
						   str+=', ';
					   }
				   });
			   }});
			   return str;
		   }}
	    ]
	});
	
	
	$('#popup').dialog({
		autoOpen:false,
		modal:true,
		width:900
	});
	
	$('#popup').find('#cerrar').click(function(){
		$('#popup').dialog('close');
	});
	
	$('#popup').bind('dialogclose', function(event) {
	     $('#imprimirGuiaDespachoRemate').reset();
	 });
	
	$('#popup').find('#grabar').click(function(){
		var guia = {};
		if($('#imprimirGuiaDespachoRemate').valid()){
			guia = $('#imprimirGuiaDespachoRemate').serializeObject();
			SSTFacade.emitirGuiaRemate(guia,{async:true,callback:function(guia){
				jAlert('La guia de despacho se guardo correctamente.','Aviso',function(){
					$('#popup').dialog('close');
					$("#guiasDespacho").flexReload();
					emitirGuiaRemate(guia.id);
				});
			}});
		}
	});
	
	$('#popup').find('#imprimir').click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=GuiaAgrupadaReport" +
		"&idGuia=" + $('#popup').find('#id').val();
		$.openWindowsMenubar(url, "GuiaDetalleReport", 600, 800);
	});
});

var emitirGuiaRemate = function(idGuia){
	$('#popup').find('#id').val(idGuia);
	$('#grabar, #imprimir').attr('disabled',true);
//	alert(idGuia);
	var guia = {};
	SSTFacade.getGuiaRemateById(idGuia,{async:false,callback:function(g){
		guia=g;
	}});
	$('#popup').find('#destino').text(guia.destino.tipo+' '+guia.destino.nombre+', '+guia.destino.direccion);
	if(guia.estado.id == 50001000) {
		$('#grabar').attr('disabled', false);
	} else {
		$('#imprimir').attr('disabled', false);
	}
	
	guia.fechaEmision = guia.fechaEmision == null ? fechaActual : guia.fechaEmision;
	
	$('#imprimirGuiaDespachoRemate').loadObject(guia);
	SSTFacade.listOTByGuiaRemate(idGuia,{async:true,callback:function(oTs){
		$('#oTsGuiaDespacho').flexAddData({rows:oTs,total:oTs.length});
	}});
	$('#popup').dialog('open');
};