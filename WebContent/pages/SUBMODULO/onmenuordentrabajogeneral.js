var initonmenuordentrabajogeneral = function() {
	var isLoad = false;
	$('#onmenuordentrabajogeneral').click(function(){
		if(!isLoad) {
			loadonmenuordentrabajogeneral(ordenTrabajo);		
			isLoad = true;
		}
	});
};

var loadonmenuordentrabajogeneral = function(ordenTrabajo) {
	$('#ordenTrabajo').loadObject(ordenTrabajo);	
};
 