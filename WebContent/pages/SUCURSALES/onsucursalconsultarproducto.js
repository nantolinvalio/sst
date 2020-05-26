$(document).ready(function() {
	
	var filtro;
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		filtro = null;
		limpiar();
	});
	
	var limpiar = function() {
		$('#descripcion').text('');
		$('#marca\\.nombre').text('');
		$('#fallas').clean();
		$('#accesorios').clean();
		$('#partes').clean();
	};
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			filtro = $('#filtro_buscador').serializeObject();
			loadProducto(filtro);
		}
	});

	$('#fallas').flexigrid({
		height: 150,
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		title: 'Fallas',
		afterAddData: function() {
			$('#fallas').find('tbody tr > td > div > input[type=checkbox]').click(function(){
				$('#fallas').find('tbody tr > td > div > input[type=checkbox]').not('#' + this.id).attr('checked',false);
				$('#accesorios').clean();
				if (this.checked) {
					filtro.idTipoFalla = this.id;
					SSTFacade.listAccesoriosByFilter(filtro,{async:true,callback:function(accesorios) {
						if (accesorios == null || accesorios.length == 0) 
							accesorios = [{descripcion:'Sin accesorios requeridos.'}];
						$('#accesorios').flexAddData({rows:accesorios,total:accesorios.length});
					}});
				}
 			});
		},
		colModel : [
            {display: 'SELECCIONAR'         ,width:100 ,align:'center',name:function(o){
            	return $('<input type="checkbox">')
            				.attr('id',o.id)
            				.attr('name',o.id)
				 			.attr('checked',false);
            }},
            {display: 'TIPOS DE FALLA'      ,width:700 ,align:'left'  ,name:'descripcion'}
		]
	});

	$('#accesorios').flexigrid({
		height: 150,
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
            {display: 'NOMBRE'      ,width:400 ,align:'left'  ,name:'descripcion'}
		]
	});

	$('#partes').flexigrid({
		height: 150,
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
            {display: 'TIPOS DE FALLA'      ,width:400 ,align:'left'  ,name:'glosa'}
		]
	});
	
	var loadProducto = function() {
		limpiar();
		SSTFacade.getProductoById(filtro.idProducto,{async:true,callback:function(producto) {
			if (!producto) {
				errorHandler('No existe un producto en el sistema con este codigo corto.', 'Información');				
			}
			else {
				$('#formProducto').loadObject(producto);				
			}
		}});

		SSTFacade.listTipoFallasByFilter(filtro,{async:true,callback:function(fallas) {
			$('#fallas').flexAddData({rows:fallas,total:fallas.length});
		}});

		SSTFacade.listPartesByFilter(filtro,{async:true,callback:function(partes) {
			$('#partes').flexAddData({rows:partes,total:partes.length});
		}});
	};
	
});