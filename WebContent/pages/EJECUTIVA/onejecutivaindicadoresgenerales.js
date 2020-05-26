$(document).ready(function() {
	
	var menuIndicador, nombreModulo;
	SSTFacade.getModuloInicialByUsuario({async:false, callback:function(modulo) {
		menuIndicador = modulo.codigo;
		nombreModulo = modulo.nombre;
	}});

	var detalleIndicador;
	SSTFacade.getModuloByNombre('onejecutivaindicadoresmarca',{async:false, callback:function(modulo) {
		detalleIndicador = modulo;
	}});	

	
	SSTFacade.listIndicadoresEjecutivaMarca({},{async:false,callback:function(indicadores){	
		$.each(indicadores,function(i,indicador){
			var el = $('#' + indicador.id + '');
			var filter = {
				idIndicador:indicador.id
			};
			var filterColumn = {
				idRol:5,
				idSeccion:40001000,
				idIndicador:indicador.id,
				tipo:'OT'
			};
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
	
	SSTFacade.listIndicadoresEjecutivaMarca({idEjecutiva:-1},{async:false,callback:function(indicadores){
		$.each(indicadores,function(i,indicador){
			var el = $('#' + (indicador.id + 10) + '');
			var filter = {
				idIndicador:indicador.id
			};
			var filterColumn = {
				idRol:5,
				idSeccion:40001000,
				idIndicador:indicador.id,
				tipo:'OT'
			};
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
	
	SSTFacade.listEjecutivasMarca({async:true,callback:function(usuarios){			
	    $.each(usuarios, function(i, ejecutiva) {
	    	var trpaso = $('#listIndicadoresEjecutivas').clone();
	    	trpaso.find("#nombreEjecutiva > a")
	    				.text(ejecutiva.nombre + " " + ejecutiva.apellidoPaterno)
	    				.attr('href','#')
	    				.attr('onclick', "parent.location='" +  context + "/index.do?e=" + detalleIndicador.codigo +"&m=" + moduloInicial.codigo  + "&idEjecutiva=" + ejecutiva.id + "'");
    		
	    	SSTFacade.listIndicadoresEjecutivaMarca({idEjecutiva: ejecutiva.id},{async:false,callback:function(indicadores){
	    		$.each(indicadores,function(i,indicador){
	    			var el = $(trpaso).find('#' + (indicador.id + 20) + '');
	    			var filter = {
	    				idIndicador:indicador.id,
	    				idEjecutiva:ejecutiva.id
	    			};
	    			var filterColumn = {
	    				idRol:5,
	    				idSeccion:40001000,
	    				idIndicador:indicador.id,
	    				tipo:'OT'
	    			};
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
	    	trpaso.show();
	    	trpaso.appendTo("#table_indicadores > tbody");
	    });
	}});

	$(".tip img").tooltip({
		track: true,
		delay: 0,
		showURL: false,
		showBody: " - ",
		fade: 250
	});
	
});

var exportDocument = function(type, filterColumn, filter){
	var url = "/sst/ViewReportServlet?type=" + type + 
		"&report=OrdenTrabajoIndicadorEjecutivaReport" +
		"&filter=" + JSON.stringify(filter) + 
		"&filterColumn=" + JSON.stringify(filterColumn);
	$.openWindowsMenubar(url, "OrdenTrabajoIndicadorEjecutivaReport", 600, 800);
};


