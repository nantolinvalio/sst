$(document).ready(function() {
	var ind = [];
	var moduloDetalle;
	var moduloDetalleGuia;
	var span = $('#indicador').find('span');
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegapaneltrabajodetalle',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : {};
	}});
	
	SSTFacade.getModuloByNombreUsuario('onbodegaffdetalleguia',{async:false, callback:function(modulo) {
		moduloDetalleGuia = modulo ? modulo : {};
	}});
	
	$.each(span, function() {
		ind.push(indicador={id:this.id});
	});
	
	SSTFacade.getIndicadoresEjecutivaFF(ind,{async:false,callback:function(indicadores){
			$.each(indicadores, function(i, indicador) {
				var el = $('#' + indicador.id + '');
				if (indicador.valor != 0) {
					if(indicador.grupo == 'GETR'){
						var a = $('<a></a>')
						.attr('href','#')
						.text(indicador.valor)
						.attr('onclick', "parent.location='" +  context + "/index.do?e=" + moduloDetalleGuia.codigo+"&m="+moduloInicial.codigo+"&idIndicador=" + indicador.id+"'");				
						el.append(a);
					}else{
						var a = $('<a></a>')
						.attr('href','#')
						.text(indicador.valor)
						.attr('onclick', "parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo+"&m="+moduloInicial.codigo+"&idIndicador=" + indicador.id+"'");				
						el.append(a);
					}
				} else {
					el.text(indicador.valor);
				}
			}); 
	}});
	
//	$(".tip img").tooltip({
//		track: true,
//		delay: 0,
//		showURL: false,
//		showBody: " - ",
//		fade: 250
//		}); 
});

