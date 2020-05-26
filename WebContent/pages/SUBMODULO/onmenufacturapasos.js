var initonmenufacturapasos = function() {
};

var loadonmenufacturapasos = function() {
	SSTFacade.getPasosFacturaById(idFactura,{async:false,callback:function(factura) {
		if(factura != null){
			$('#pasos').loadObject(factura);
			factura.generada == 'Listo' ? $('#pasos').find('#generada').css('color','blue') : $('#pasos').find('#generada').css('color','red');
			factura.emitir == 'Listo' ? $('#pasos').find('#emitir').css('color','blue') : $('#pasos').find('#emitir').css('color','red');
			factura.emitida == 'Listo' ? $('#pasos').find('#emitida').css('color','blue') : $('#pasos').find('#emitida').css('color','red');
			factura.aceptada == 'Listo' ? $('#pasos').find('#aceptada').css('color','blue') : $('#pasos').find('#aceptada').css('color','red');
		}
	}});
};