//onmenuinventario

$(document).ready(function() {
	var moduloRevisar;
	SSTFacade.getModuloByNombreUsuario('revisarinventario',{async:false, callback:function(modulo) {
		moduloRevisar = modulo ? modulo : undefined;
	}});
	
	var idInventario;
	
	$('.fecha').datepicker();
	
	$('#filtro_buscador').validate();
	$('#fechaCreacionDesde').rules('add',{dateRangeMin:'#fechaCreacionHasta'});
	$('#fechaCierreDesde').rules('add',{dateRangeMin:'#fechaCierreHasta'});
	
	$('#fechaCreacionHasta').rules('add',{dateRangeMax:'#fechaCreacionDesde'});
	$('#fechaCierreHasta').rules('add',{dateRangeMax:'#fechaCierreDesde'});
	
	SSTFacade.listTodasUbicacionesInternasCD({async:true,callback:function(ubicacionesInternas){
		$('#idUbicacionInterna').addItems(ubicacionesInternas,'id','nombre',true);
	}});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#inventarios').clean();
	});
	
	
	$('#buscar').click(function(){
		var creacion = [];
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#inventarios').loadData([filtro]);
		}
	});
	
	var exportDocumentInventario = function(type){
		var p = $('#inventarios').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#inventarios').getSortName()[0];
		p.sortOrder = $('#inventarios').getSortOrder()[0];
		var f = $('#inventarios').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=InventarioReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "InventarioReport", 600, 800);
	};
		
	$('#inventarios').flexigrid({
		dwrFunction: SSTFacade.listInventariosByFilter,
		seccion: 1000100000,
		showTableToggleBtn: true,
		tipo: 'INVE',
		multisel:false,
		singleSelect:true,
//		idProperty: 'id',
		height:280,
		overrideModel: [
		    {findName:'estado',propiedad:function(o){return o.estado ? 'APROBADO' : 'RECHAZADO';}},
		    {findName:'vigente',propiedad:function(o){return o.vigente? 'ABIERTO':'CERRADO';}}
		],
		dblclickFunction:function(el,idx,comp){
			if(el.vigente){
				cerrarInventario(el.id);
			} else{
				parent.location = context +'/index.do?e=' + moduloRevisar.codigo + '&m=' + moduloInicial.codigo +'&idInventario='+el.id;
			}
		},
		buttons : [
		    {name: 'Nuevo inventario', bclass: 'btnPlus', onpress : function(){nuevoInventario();} },
		    {separator: true},
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentInventario('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentInventario('xls');} },
	   		{separator: true}],
	});
	
	$('#productosInventariados').flexigrid({
		dwrFunction: SSTFacade.selectInventarioResumen,
		seccion: 1000100000,
		showTableToggleBtn: true,
		tipo: 'RESUINV',
		multisel:false,
		singleSelect:true,
		height:280,
		overrideMode: [{findName:'inventariado',propiedad:function(o){
			if (o.inventariado == false)
        	return 'No inventariado';
			else
			return 'Inventariado';
    	}},             
        ],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true}],
	});
	
	var cerrarInventario = function(id){
		idInventario = id;
		$('#ubicacionesFromInventario').loadData([idInventario]);
		$('#cerrarInventario').dialog('open');
	};
	var filtro = $('#filtro_buscador').serializeObject();
	$('#inventarios').loadData([filtro]);
	
	
	
	var nuevoInventario = function(){
		$('#ubicacionesToInventario').loadData([]);
		$('#crearInventario').dialog('open');
	};
		
	$('#crearInventario').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		buttons:{
			Crear: function() {
				if($('#crearInventarioForm').valid()) {
					var ruta = $('#crearInventarioForm').serializeObject();
					jConfirm('¿Esta seguro que desea crear el inventario?', 'Confirmación', function(r){
						if (r) {
							var ubicaciones = $("#ubicacionesToInventario").getJSONCheckboxSelected();
							if(ubicaciones.length>0){
								SSTFacade.saveInventario(ubicaciones,{async:true,callback:function(){
									jAlert('Inventario creado','AVISO',function(){
										$('#inventarios').flexReload();
										$('#crearInventario').dialog('close');
									});
									
								}});
							} else {
								jAlert('No ha seleccionado ninguna ubicación','AVISO');
							}
							
						}
					});
				}
			},
			Volver: function(){
				$('#crearInventario').dialog('close');
			}
		}
	});
	
	$('#ubicacionesToInventario').flexigrid({
		dwrFunction: SSTFacade.listUbicacionesInternasToInventario,
		seccion: 1000100000,
//		showTableToggleBtn: true,
		tipo: 'UBIN',
		multisel:false,
		singleSelect:true,
		idProperty: 'id',
		height:280,
		width:750,
		saveCheckValues:true,
		dwrFunctionListAllId: SSTFacade.listAllUbicacionesInternasToInventario,
		overrideModel: [
			{findName:'seleccionado',propiedad:function(o){
				var check = $('<input type="checkbox">')
				.attr('id', 'chk' + o.id)
				.attr('name', 'chk' + o.id)
				.attr('checked',$('#productos').isRowChecked(o.id))
				.attr('onchange','changeState("productos", ' + o.id + ',this)');
				return check;
			}},
		],
//		dblclickFunction:function(el,idx,comp){
//			getReglaComercialHistorica(el.idHistorico);
//		},
//		buttons : []
	});
	
	
	$('#cerrarInventario').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		buttons:{
			Terminar: function() {
				if($('#cerrarInventarioForm').valid()) {
					var ruta = $('#cerrarInventarioForm').serializeObject();
					jConfirm('¿Esta seguro que desea cerrar el inventario?', 'Confirmación', function(r){
						if (r) {
							if(idInventario!=null){
								SSTFacade.abrirCerrarInventario({id:idInventario,vigente:false},{async:true,callback:function(){
									jAlert('Inventario cerrado','AVISO',function(){
										$('#inventarios').flexReload();
										$('#cerrarInventario').dialog('close');
									});
									
								}});
							} else {
								jAlert('No ha seleccionado ningun Inventario','AVISO');
							}
							
						}
					});
				}
			},
			Volver: function(){
				$('#cerrarInventario').dialog('close');
			}
		}
	});
	
	$('#ubicacionesFromInventario').flexigrid({
		dwrFunction: SSTFacade.listInventarioUbicacionesByIdInventario,
		seccion: 1000100000,
		showTableToggleBtn: true,
		tipo: 'UBII',
		multisel:false,
		singleSelect:true,
		height:280,
		width:750,
		overrideModel: [
			{findName:'terminada',propiedad:function(o){
				if(o.terminada){
					var lbl = $('<label></label>')
						.text('Terminada');
					return lbl;
				} else {
					var btn = $('<input type="button">')
						.attr('value','Terminar')
						.attr('onclick','terminarUbicacion('+o.id+',"'+o.ubicacionInterna.nombre+'",'+o.inventario.id+')');
					return btn;
				}
			}},
		],
	});
	
	$('#resultadoInventario').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		buttons:{
			Volver: function(){
				$('#resultadoInventario').dialog('close');
			}
		}
	});
});

var terminarUbicacion = function(idInventarioUbicacion,nombreUbicacion,idInventario){
	$.alerts.okButton = '&nbsp;Sí&nbsp;';
	$.alerts.cancelButton = '&nbsp;No&nbsp;';
	jConfirm('¿Esta seguro que quiere terminar el inventario de la ubicación interna '+nombreUbicacion+'?','Confirmación',function(r){
		if(r){
			SSTFacade.terminarInventarioUbicacion(idInventarioUbicacion,{async:true,callback:function(){
				SSTFacade.contarInventarioUbicacionOpenByIdInventario(idInventario,{async:true,callback:function(total){
					if(total>0){
						jAlert('Inventario de la ubicación interna '+nombreUbicacion+' terminado','Aviso',function(){
							$('#ubicacionesFromInventario').flexReload();
						});
					} else {
						jAlert('Inventario de la ubicación interna '+nombreUbicacion+' terminado','Aviso',function(){
							jAlert('Todas las ubicaciones están terminadas, Inventario finalizado','Aviso',function(){
								var inventario = {id:idInventario,vigente:false};
								SSTFacade.abrirCerrarInventario(inventario,{asyn:true,callback:function(){
									location.reload();
								}});
							});
						});
						
					}
				}});
			}});
		}
	});
};