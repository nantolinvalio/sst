$(document).ready(function() {
	$('.fecha').datepicker();
	
	$('#filtro_buscador').validate();
	$('#fechaInicio').rules('add',{dateRangeMin:'#fechaTermino'});
	$('#fechaTermino').rules('add',{dateRangeMax:'#fechaInicio'});
	
	SSTFacade.listMarca({async:true,callback:function(marcas){
		$('#idMarca').addItems(marcas,"codigo","nombre",true);
	}});
	
	SSTFacade.listProveedores({async:true,callback:function(marcas){
		$('#idProveedor').addItems(marcas,"id","nombre",true);
	}});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
	});
	
	var buscar = function() {
		var validar = [];
		validar.push($('#idMarca'));
		validar.push($('#idProveedor'));
		if($('#filtro_buscador').valid() && validEmptyInputs(validar,'debe ingresar alguno de los parametros de busqueda')) {
			var filter = $('#filtro_buscador').serializeObject();
			filter.fechaInicio = null;
			filter.fechaTermino = null;
			var filterSeleccion = $('#filtro_buscador').serializeObject();
			SSTFacade.listIndicadoresNivelServicio(filter,{async:false,callback:function(indicadores){
				$.each(indicadores,function(i, indicador){
					var el = $('#' + indicador.id + '');
					var filter = {
						idIndicador:indicador.id
					};
					var filterColumn = {
						idRol:8,
						idSeccion:40001000,
						idIndicador:indicador.id,
						tipo:'OT'
					};
					el.text('');
					el.children().remove();
					if (indicador.valor <= 0) {
						el.text(0);
					} else {
						var a = $('<a></a>')
						.attr('href','#')
						.text(Math.round(indicador.valor * 100) / 100)
						.attr('onclick','exportDocument("xls",' + JSON.stringify(filterColumn) + ',' + JSON.stringify(filter) + ')');
						el.append(a);
					}
				});
			}});
			SSTFacade.listIndicadoresNivelServicio(filterSeleccion,{async:false,callback:function(indicadores){
				$.each(indicadores,function(i, indicador){
					var el = $('#' + (indicador.id + 10) + '');
					var filter = {
						idIndicador:indicador.id,
						fechaInicio:filterSeleccion.fechaInicio,
						fechaTermino:filterSeleccion.fechaTermino
					};
					var filterColumn = {
						idRol:8,
						idSeccion:40001000,
						idIndicador:indicador.id,
						tipo:'OT'
					};
					el.text('');
					el.children().remove();
					if (indicador.valor <= 0) {
						el.text(0);
					} else {
						var a = $('<a></a>')
						.attr('href','#')
						.text(Math.round(indicador.valor * 100) / 100)
						.attr('onclick','exportDocument("xls",' + JSON.stringify(filterColumn) + ',' + JSON.stringify(filter) + ')');
						el.append(a);
					}
				});
			}});
		}
	};
});

var exportDocument = function(type, filterColumn, filter){
	var url = "/sst/ViewReportServlet?type=" + type + 
		"&report=OrdenTrabajoIndicadorEjecutivaReport" +
		"&filter=" + JSON.stringify(filter) + 
		"&filterColumn=" + JSON.stringify(filterColumn);
	$.openWindowsMenubar(url, "OrdenTrabajoIndicadorEjecutivaReport", 600, 800);
};

