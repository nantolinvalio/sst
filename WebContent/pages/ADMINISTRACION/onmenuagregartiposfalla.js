$(document).ready(function(){
	var filter = {};
	
	SSTFacade.getAccesorioById($('#idAccesorio').val(),{async:false,callback:function(accesorio){
		filter.idAccesorio = accesorio.id;
		if (accesorio.tipo == 'P') {
			accesorio.tipoGlosa = 'PRODUCTO';
			SSTFacade.getProductoById(accesorio.producto.id,{async:false,callback:function(producto){
				accesorio.glosa = producto.id + ' ' + producto.descripcion;
				filter.idProducto = producto.id;
			}});
		} else {
			accesorio.tipoGlosa = 'FAMILIA';
			SSTFacade.getFamiliaByFilter({idFamilia:accesorio.producto.familia.id},{async:false,callback:function(familia){
				accesorio.glosa = familia.nombre;
				filter.idFamilia = familia.id; 
			}});
		}
		$('#formAccesorio').loadObject(accesorio);
		
	}});
	
	SSTFacade.listTipoFallasByFilterFallas(filter,{async:false,callback:function(accesorios){
		$('#tipoFallaEnviar').addItems(accesorios,'id','descripcion');
	}});
	
	SSTFacade.listTipoFallasNotExistsByFilterFallas(filter,{async:false,callback:function(accesorios){
		$('#tipoFallaNoEnviar').addItems(accesorios,'id','descripcion');
	}});
	
	$('#agregar').click(function(){
		var sel = $("#tipoFallaNoEnviar").find(':selected');
		if (sel != null) change = true;
		$("#tipoFallaEnviar").append(sel);
	});
	
	$('#quitar').click(function(){
		var sel = $("#tipoFallaEnviar").find(':selected');
		if (sel != null) change = true;
		$("#tipoFallaNoEnviar").append(sel);
	});
	

	var grabar = function(){
		jConfirm('¿Esta seguro que desea grabar esta configuración?', 'Confirmación', function(r){
			var tipoFallas = [];
			$("#tipoFallaEnviar").find('option').each(function(){
				tipoFallas.push({id:$(arguments[1]).val()});
			});
			var accesorio = { id:filter.idAccesorio};
			
			SSTFacade.saveTipoFallaForAccesorios(accesorio, tipoFallas,{async:false,callback:function(rol){
				jAlert('Accesorios grabados correctamente','Informacion',function(){});
			}});
		});
	};
	
	$('#grabar').click(function(){
		grabar();
	});
});