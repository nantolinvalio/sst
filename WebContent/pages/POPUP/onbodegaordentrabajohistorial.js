$(document).ready(function(){
	var idOT = $('#idOT').val();
	var idProducto = $('#idProducto').text();
	
	var moduloBuscador; 
	SSTFacade.getModuloByNombreUsuario('onbodegabuscarordenestrabajo',{async:false, callback:function(modulo) {
		moduloBuscador = modulo ? modulo : undefined;
	}});
	
	
	$('#grillaProducto').flexigrid({
		height:'auto',
		usepager:false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		colModel:[
		    {display: 'N° OT',              width:100, align:'right', name_abbr:'id',                name:'id'},
		    {display: 'Fecha Creación',     width:200, align:'left',  name_abbr:'fechaCreacion',     name:'fechaCreacion', format:'dd/MM/yyyy'},
		    {display: 'N° de serie',        width:200, align:'left',  name_abbr:'numeroSerie',       name:'numeroSerie'},
		    {display: 'Descripcion Estado', width:300, align:'left',  name_abbr:'descripcionEstado', name:'descripcionEstado'}
		]
	});
	
	SSTFacade.listHistorialOT({idOT:idOT},{async:true, callback:function(historial){ // grilla popup
		$('#grillaProducto').flexAddData({rows:historial,total:historial.length});
	}});
	
	SSTFacade.getProductoById(idProducto,{async:true, callback:function(producto){
		$('#producto').text($('#producto').text()+', '+producto.descripcion+', '+producto.marca.nombre);
	}});
	
	$('#volver').click(function(){
		parent.location = context +'/index.do?e=' + moduloBuscador.codigo + '&m=' + moduloInicial.codigo;
	});
});