var initonmenuordentrabajoservtec = function() {

};
	
var loadonmenuordentrabajoservtec = function(ordenTrabajo) {
	$('#onmenuordentrabajoservtecPanel > *').not('#servicioTecnico').remove();
	SSTFacade.getServicioTecnicoByOT(ordenTrabajo.id,{async:true,callback:function(data){
		if(data!=null){
			$('#servicioTecnico').loadObject(data);
		} else {
			$('#servicioTecnico').hide();
			$('#onmenuordentrabajoservtecPanel').append('<h3>La orden de trabajo no presenta servicio tecnico.</h3>');
		}
	}});

};
	

 