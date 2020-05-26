$(document).ready(function() {
	$('.fecha').datepicker();
	
	$('#rangoFechas').validate();
	$('#fechaInicio').rules('add',{dateRangeMin:'#fechaTermino'});
	$('#fechaTermino').rules('add',{dateRangeMax:'#fechaInicio'});
	$('#fechaInicio').rules('add',{rangoMeses:'#fechaTermino'});
	
	var fechaActual;
	SSTFacade.getDateTrunc({async:false, callback:function(fechaTrunc) {
		fechaActual = fechaTrunc.toString("dd/MM/yyyy");
	}});	

	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuindicadorfacturadetalle',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$("#calularIndicadoresFacturas").click(function() {
		if($('#rangoFechas').valid()){
			var filter = {
				fechaInicio:$("#fechaInicio").val(), 
				fechaTermino:$("#fechaTermino").val()
			};
			SSTFacade.listIndicadoresEjecutivaFacturacion(filter,{async:true,callback:function(indicadores){

				$.each(indicadores, function(i,indicador) {
					 var el = $('#' + (indicador.id + 10) + '');
					 if (indicador.valor <= 0) {
						 el.text(0);
					 } else {
						var a = $('<a></a>')
						.attr('href','#')
						.attr('onclick', "parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo +"&m=" + moduloInicial.codigo + "&idIndicador=" + indicador.id +"&idRol=6&fechaInicio=" + filter.fechaInicio + "&fechaTermino=" + filter.fechaTermino + "'")
						.text(Math.round(indicador.valor * 100) / 100);
						el.append(a);
					}
			    });
			}});
		}
	});

	SSTFacade.listIndicadoresEjecutivaFacturacion({},{async:true,callback:function(indicadores){
		 $.each(indicadores, function(i,indicador) {
			 var el = $('#' + indicador.id + '');
			 if (indicador.valor <= 0) {
				 el.text(0);
			 } else {
				var a = $('<a></a>')
				.attr('href','#')
				.attr('onclick', "parent.location='" +  context + "/index.do?e=" + moduloDetalle.codigo  +"&m=" + moduloInicial.codigo + "&idIndicador=" + indicador.id +"&idRol=6'")
				.text(Math.round(indicador.valor * 100) / 100);
				el.append(a);
			}
	    });		
	}});
	
});

