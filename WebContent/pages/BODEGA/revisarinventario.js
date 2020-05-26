$(document).ready(function() {
	var idInventario=$('#idInventario').val();
	
	$('#ubicaciones').flexigrid({
		dwrFunction: SSTFacade.listUbicacionesInternasByFilter,
		seccion: 1000700100,
		showTableToggleBtn: true,
		tipo: 'UIBI',
		multisel:false,
		singleSelect:true,
		height:280,
		dblclickFunction:function(el,idx,comp){
			verResumenInventarioUbicacion(el.id);
		},
	});
	
	var exportDocument = function(type){
		var p = $('#productosInventariados').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#productosInventariados').getSortName()[0];
		p.sortOrder = $('#productosInventariados').getSortOrder()[0];
		var f = $('#productosInventariados').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ProductosInventarioReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ProductosInventarioReport", 600, 800);
	};
	
	$('#productosInventariados').flexigrid({
		dwrFunction: SSTFacade.listInventarioProductoByFilter,
		seccion: 1000700200,
		showTableToggleBtn: true,
		tipo: 'PIUB',
		multisel:false,
		singleSelect:true,
		height:280,
		overrideModel: [
		    			{findName:'inventariado',propiedad:function(o){
		    				if(o.inventariado){
		    					return 'Inventariado';
		    				} else{
		    					return 'Sin inventariar';
		    				}
		    			}},
		    			{findName:'preparado',propiedad:function(o){
		    				if(o.inventariado){
		    					return 'Preparado';
		    				} else{
		    					return 'Sin preparar';
		    				}
		    			}}
		    		],
		buttons : [
		           {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
		           {separator: true},
		           {name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
		           {separator: true}],
	});
	
	$('#ubicaciones').loadData([{idInventario:idInventario}]);
	
	SSTFacade.getEstadisticasInventarioByIdInventario(idInventario,{async:true,callback:function(estadisticas){
		$('#resumenInventarioForm').loadObject(estadisticas);
	}});
	
	
	$('#resumenInventarioUbicacion').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		buttons:{
			Volver: function(){
				$('#resumenInventarioUbicacion').dialog('close');
			}
		}
	});
	
	var verResumenInventarioUbicacion = function(idInventarioUbicacion){
		
		SSTFacade.getEstadisitcasByIdInventarioUbicacion(idInventarioUbicacion,{async:true,callback:function(estadisticas){
			$('#resumenInventarioUbicacionForm').loadObject(estadisticas);
		}});
		$('#productosInventariados').clean();
		$('#productosInventariados').loadData([{idInventarioUbicacion:idInventarioUbicacion}]);
		
		$('#resumenInventarioUbicacion').dialog('open');
	};
});