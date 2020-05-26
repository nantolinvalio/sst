$(document).ready(function() {

	var saveZona = function(zona){
		$('#mantenedorZonaForm').reset();
		if (zona) {
			$('#mantenedorZonaForm').loadObject(zona);
			$('#codigo').attr('readonly','readonly');
		} else {
			$('#codigo').removeAttr('readonly');
		}
		$('#mantenedorZona').dialog('open');
	};
	
	
	var exportDocument = function(type){
		var p = {};
		p.orderBy = $('#zonas').getSortName()[0];
		p.sortOrder = $('#zonas').getSortOrder()[0];
		var f = $('#zonas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ZonaReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&gridControl=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "ZonaReport", 600, 800);
	};
	
	$("#zonas").flexigrid({
		height: 300,
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listZonas,
		tipo: 'zona',
		seccion: 50002000,
		overrideModel: [{findName:'vigente',propiedad:function(o){return o.vigente ? 'ACTIVA' : 'INACTIVA';}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true},
	   		{name: 'Ingresar Nueva Zona', bclass: 'btnPlus', onpress : function(){saveZona();}},
	   		{separator: true}
   		],
   		dblclickFunction:function(el,idx,comp){
   			saveZona(el);
		}
	});
	
	$('#mantenedorZona').dialog({
		autoOpen: false,
		modal:true,
		width:500,
		buttons:{
			Grabar: function() {
				if($('#mantenedorZonaForm').valid()) {
					var zona = $('#mantenedorZonaForm').serializeObject();
					jConfirm('¿Esta seguro que desea grabar la zona?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveZona(zona,function(){
								$("#zonas").flexReload();
								$('#mantenedorZona').dialog('close');
								jAlert('Zona grabada','Información');																
							});
						}
					});
				}
			},
			Cerrar: function(){
				$('#mantenedorZona').dialog('close');
			}
		}
	});
	
	$("#zonas").loadData([]);
});