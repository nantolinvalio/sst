var initonmenurecibirothistorial = function() {
	$('#onmenurecibirothistorial').click(function(){
		loadonmenurecibirothistorial(ordenTrabajo);
	});
};

var loadonmenurecibirothistorial = function(ordenTrabajo) {
	$('#onmenurecibirothistorialPanel > *').not('#historial').remove();
	SSTFacade.listRecepcionesByOT(ordenTrabajo.id,{async:true,callback:function(recepciones){
		if(recepciones.length > 0){
			$.each(recepciones,function(i,recepcion){
				var hst = $('#historial').clone();
				$('#historial').clone().attr('id', 'historial' + i).show().appendTo('#onmenurecibirothistorialPanel');
				$('#historial' + i).loadObject(recepcion);
			});
		} else {
			$('#onmenurecibirothistorialPanel').append('<h3>La orden de trabajo no presenta recepciones registradas en el sistema.</h3>');
		}
	}});
};