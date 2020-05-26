var initonmenuordentrabajopasos = function() {
	
};

var loadonmenuordentrabajopasos = function(ordenTrabajo) {
	if(ordenTrabajo.cambioAutorizado == true){
		$('#pasosOT').show();
		$('#sinPasosOT').hide();
		SSTFacade.getPasosOT(ordenTrabajo.id,{async:true,callback:function(pasos){
			$('#facturarElegido').append(pasos.facturarElegido != null && pasos.facturarElegido == 'Listo'? "<font color=\"blue\">Listo</font>": "<font color=\"red\">Pendiente</font>");
			$('#productoRetirado').append(pasos.productoRetirado != null && pasos.productoRetirado == 'Listo'? "<font color=\"blue\">Listo</font>": "<font color=\"red\">Pendiente</font>");
			
			$('#cargoGeneradoTitulo').text(ordenTrabajo.empresaFacturar != null && ordenTrabajo.empresaFacturar.id != null && ordenTrabajo.empresaFacturar.rut == 89772300 ? "Se genero una factura en sucursal" : "Se genero una guia de despacho de cargo por el producto");
			$('#cargoGenerado').append(pasos.cargoGenerado != null && pasos.cargoGenerado == 'Listo'? "<font color=\"blue\">Listo</font>": "<font color=\"red\">Pendiente</font>");
			
			$('#accesoriosRecuperados').append(pasos.accesoriosRecuperados != null && pasos.accesoriosRecuperados == 'Listo'? "<font color=\"blue\">Listo</font>": "<font color=\"red\">Pendiente</font>");
			$('#productoRecuperado').append(pasos.productoRecuperado != null && pasos.productoRecuperado == 'Listo'? "<font color=\"blue\">Listo</font>": "<font color=\"red\">Pendiente</font>");
		}});
	} else {
		$('#pasosOT').hide();
		$('#sinPasosOT').show();
	}
};
