$(document).ready(function(){
	var filter = {};
	
	SSTFacade.getTipoFallas($('#idTipoFalla').val(),{async:false,callback:function(tipoFalla){
		filter.idTipoFalla = tipoFalla.id;
		if (tipoFalla.tipo == 'P') {
			tipoFalla.tipoGlosa = 'PRODUCTO';
			SSTFacade.getProductoById(tipoFalla.producto.id,{async:false,callback:function(producto){
				tipoFalla.glosa = producto.id + ' ' + producto.descripcion;
				filter.idProducto = producto.id;
			}});
		} else {
			tipoFalla.tipoGlosa = 'FAMILIA';
			SSTFacade.getFamiliaByFilter({idFamilia:tipoFalla.familia.id},{async:false,callback:function(familia){
				tipoFalla.glosa = familia.nombre;
				filter.idFamilia = familia.id; 
			}});
		}
		$('#formTipoFalla').loadObject(tipoFalla);
		
	}});
	
	SSTFacade.listAccesoriosByFilter(filter,{async:false,callback:function(accesorios){
		$('#accesoriosEnviar').addItems(accesorios,'id','descripcion');
	}});
	
	SSTFacade.listAccesoriosNotExistsByFilter(filter,{async:false,callback:function(accesorios){
		$('#accesoriosNoEnviar').addItems(accesorios,'id','descripcion');
	}});
	
	$('#agregar').click(function(){
		var sel = $("#accesoriosNoEnviar").find(':selected');
		if (sel != null) change = true;
		$("#accesoriosEnviar").append(sel);
	});
	
	$('#quitar').click(function(){
		var sel = $("#accesoriosEnviar").find(':selected');
		if (sel != null) change = true;
		$("#accesoriosNoEnviar").append(sel);
	});
	

	var grabar = function(){
		jConfirm('¿Esta seguro que desea grabar esta configuración?', 'Confirmación', function(r){
			var accesorios = [];
			$("#accesoriosEnviar").find('option').each(function(){
				accesorios.push({id:$(arguments[1]).val()});
			});
			var tipoFalla = { id:filter.idTipoFalla};
			
			SSTFacade.saveAccesoriosForTipoFalla(tipoFalla, accesorios,{async:false,callback:function(rol){
				jAlert('Accesorios grabados correctamente','Informacion',function(){});
			}});
		});
	};
	
	$('#grabar').click(function(){
		grabar();
	});
});
	
