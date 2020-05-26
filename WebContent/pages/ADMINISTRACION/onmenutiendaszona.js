$(document).ready(function() {
	var change = false;
	var zona;
		
	$('#idZona,#idUbicacion').selectInput({
		selectStyle:'width:60%'
	});
	
	$('#agregar').click(function(){
		var sel = $("#sucursalesOtrasZona").find(':selected');
		if (sel != null) change = true;
		$("#sucursalesZona").append(sel);
	});
	
	$('#quitar').click(function(){
		var sel = $("#sucursalesZona").find(':selected');
		if (sel != null) change = true;
		$("#sucursalesOtrasZona").append(sel);
	});
	
	$('#grabar').click(function(){
		if (change) {
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro que desea grabar los cambios?', 'Confirmación', function(r){
				if (r) {
					change = false;
					saveSucursales();
					listSucursales();
				}
			});
		} else {
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('No hay modificaciones para grabar','Información');
		}
	});
	
	var exportDocument = function(type){
		var p = $('#tiendas').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#tiendas').getSortName()[0];
		p.sortOrder = $('#tiendas').getSortOrder()[0];
		var f = $('#tiendas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=TiendasZonasReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "PDF_OT", 600, 800);
	};
	
	$('#tiendas').flexigrid({
		height: 300,
		usepager:true,
		multisel:false,
		singleSelect:true,
		dwrFunction:SSTFacade.listSucursalesZonaByFilter,
		seccion: 50001000,
		tipo: 'ubiZona',
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		]
	});
	
	var listSucursales = function(){
		zona = {codigo:$('#idZona').val()};
		$("#sucursalesOtrasZona").empty();
		$("#sucursalesZona").empty();
		if(zona.codigo != '') {
			SSTFacade.listSucursalesNotInZonaByCodigoZona(zona.codigo,function(sucursales){
				$("#sucursalesOtrasZona").addItems(sucursales, "tienda.id", ["tienda.id", "tienda.nombre","zona.nombre"], false, ' : ');
			});
			
			SSTFacade.listSucursalesByCodigoZona(zona.codigo,function(sucursales){
				$("#sucursalesZona").addItems(sucursales, "tienda.id", ["tienda.id", "tienda.nombre", "zona.nombre"], false, ' : ');
			});
		}
	};
	
	var saveSucursales = function(){
		var sucursales = [];
		$("#sucursalesZona").find('option').each(function(){
			sucursales.push({id:$(arguments[1]).val()});
		});
		SSTFacade.saveSucursalesZona(sucursales,zona,{async:false,callback:function(){
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('Sucursales grabadas','Información');
		}});
	};
	
	$('#idZona').change(function(){
		if (change) {
			change = false;
			$.alerts.okButton = '&nbsp;Grabar y continuar&nbsp;';
			$.alerts.cancelButton = '&nbsp;Continuar sin grabar&nbsp;';
			jConfirm('¿Desea grabar los cambios realizados antes de continuar?', 'Confirmación', function(r){
				if (r) {
					saveSucursales();
				}
				listSucursales();
			});
		} else {
			listSucursales();
		}
	});
	
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#tiendas').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#tiendas').loadData([filter]);
		}
	});
	
	SSTFacade.listSucursales(function(sucursales){
		$("#idUbicacion").addItems(sucursales, "id", ["id","nombre"], true);
	});
	
	SSTFacade.listAllZonas({async:true,callback:function(data){
		$("#idZona").addItems(data, "codigo", ["nombre",function(obj){return obj.vigente ? 'ACTIVA  ' : 'INACTIVA';}], true, ' : ');
	}});
	
	$('#tiendas').loadData([{}]);
});