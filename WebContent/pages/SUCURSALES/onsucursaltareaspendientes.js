$(document).ready(function() {
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuindicadordetallesuc',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listIndicadorSucursal(function(indicadores){
		
		$.each(indicadores, function(idx,indicador) {
			var el = $('#' + indicador.id);
			if (indicador.cantidad != 0 && moduloDetalle != null) {
				var a = $('<a></a>')
					.attr('href','#')
					.text(indicador.cantidad)
					.attr('onclick', "parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idIndicador=" + indicador.id + "&idSeccion=40001000'");				
				el.append(a);		
			} else {
				el.text(indicador.cantidad);
			}
	    });	
	});
});