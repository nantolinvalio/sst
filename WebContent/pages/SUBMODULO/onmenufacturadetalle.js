var initonmenufacturadetalle = function() {
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listarDetalleFactura,
		seccion: 2000100000,
		height: 'auto',
		showTableToggleBtn: true,
		tipo: 'detFac',
		multisel:false,
		singleSelect:true,
		usepager: false,
		overrideModel: [{findName:'producto.id',propiedad:function(o){
        	if(o.producto == null && o.producto.id == null){
        		return '';
        	} else {
        		return o.producto.id;
        	}
    	}},{findName:'producto.descripcion',propiedad:function(o){
        	if(o.producto == null && o.producto.descripcion == null){
        		return '';
        	} else {
        		return o.producto.descripcion;
        	}
    	}}],
	});

};

var loadonmenufacturadetalle = function() {
	SSTFacade.getFacturaById(idFactura,{async:false,callback:function(factura) {
		$('#factura').loadObject(factura);
	}});
	
	var filtro = {
			idFacturar : idFactura
		};
	$('#resultados').loadData([filtro]);
};