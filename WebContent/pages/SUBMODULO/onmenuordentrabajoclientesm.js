var initonmenuordentrabajoclientesm = function() { 

};

var loadonmenuordentrabajoclientesm = function(ordenTrabajo) {
	$('#onmenuordentrabajoclientePanel > *').not('#cliente').remove();
	SSTFacade.getClienteByOT(ordenTrabajo.id,{async:true,callback:function(data){
		if(data!=null){
			$('#cliente').loadObject(data);
		} else {
			$('#cliente').hide();
			$('#onmenuordentrabajoclientePanel').append('<h3>La orden de trabajo no presenta Cliente registrado en el sistema.</h3>');
		}
	}});
};
