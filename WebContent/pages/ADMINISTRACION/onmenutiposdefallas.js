var moduloConfigurar;
$(document).ready(function() {
	$("#tabs").tabs();
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.getModuloByNombreUsuario('onmenuagregaraccesorios',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
	$('#resultadosFamilias').flexigrid({
		dwrFunction: SSTFacade.listTipoFallasByFilterFallas,
		seccion: 1000100000,
		tipo: 'TIFA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'configurar',propiedad:function(o){
		    	var btn = $('<input type="button">')
        			.attr('onclick','setupTipoFalla(' + o.id + ',false)')
					.attr('value','Asignar Accesorio');
		    	return btn;
		    }},
		    {findName:'tipo',propiedad:function(o){return o.tipo =='P' ? 'PRODUCTO' : 'FAMILIA';}},
		    {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
		    {findName:'bloquear',propiedad:function(o){
		    	var btn = $('<input type="button">');
		    	if (o.vigente) {
	        		btn.attr('value','Bloquear');
	        		btn.attr('onclick','changeStateTipoFalla(' + o.id + ',false, "resultadosFamilias")');
	        	} else {
	        		btn.attr('value','Desbloquear');
	        		btn.attr('onclick','changeStateTipoFalla(' + o.id + ',true, "resultadosFamilias")');
	        	}
	        	return btn;
		    }},
	   	],
		dblclickFunction:function(el,idx,comp){
			nuevoTipoFallaFamilia(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Tipo de Falla', bclass: 'btnPlus', onpress : function(){nuevoTipoFallaFamilia();} },
			{separator: true}
   		],
	});
	
	$('#resultadosProductos').flexigrid({
		dwrFunction: SSTFacade.listTipoFallasByFilterFallas,
		seccion: 1000200000,
		tipo: 'TIFA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'configurar',propiedad:function(o){
		    	var btn = $('<input type="button">')
        			.attr('onclick','setupTipoFalla(' + o.id + ',false)')
					.attr('value','Asignar Accesorio');
		    	return btn;
		    }},
		    {findName:'tipo',propiedad:function(o){return o.tipo =='P' ? 'PRODUCTO' : 'FAMILIA';}},
		    {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
		    {findName:'bloquear',propiedad:function(o){
		    	var btn = $('<input type="button">');
		    	if (o.vigente) {
	        		btn.attr('value','Bloquear');
	        		btn.attr('onclick','changeStateTipoFalla(' + o.id + ',false, "resultadosProductos")');
	        	} else {
	        		btn.attr('value','Desbloquear');
	        		btn.attr('onclick','changeStateTipoFalla(' + o.id + ',true, "resultadosProductos")');
	        	}
	        	return btn;
		    }},
	   	],
		dblclickFunction:function(el,idx,comp){
			nuevoTipoFallaProducto(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Tipo de Falla', bclass: 'btnPlus', onpress : function(){nuevoTipoFallaProducto();} },
			{separator: true}
   		],
	});
	
	$('#filtro_familia').keypress(function(e){
		if(e.which == 13) {
			buscarFamilia();
		}
	});
	
	$('#buscarTipoFamilia').click(function(){
		buscarFamilia();
	});
	
	var buscarFamilia = function(){
		if($('#filtro_familia').valid()){
			var filtro = $('#filtro_familia').serializeObject(); 
			$('#resultadosFamilias').loadData([filtro]);
			SSTFacade.getFamiliaByFilter(filtro,{async:false,callback:function(familia){
				$('#filtro_familia').find('#cantidad').text(familia.cantidad);
			}});
		}
	};
	
	$('#filtro_producto').keypress(function(e){
		if(e.which == 13) {
			buscarProducto();
		}
	});
	
	$('#buscarTipoProducto').click(function(){
		buscarProducto();
	});
	
	var buscarProducto = function(){
		if($('#filtro_producto').valid()){
			var filtro = $('#filtro_producto').serializeObject(); 
			SSTFacade.getProductoById(filtro.idProducto,{async:false,callback:function(producto){
				if (producto) {
					$('#filtro_producto').loadObject(producto);
					$('#resultadosProductos').loadData([filtro]);
				} else {
					jAlert('El producto no existe','Información');
					$('#filtro_producto').find('#descripcion, #marca\\.nombre, #familia\\.nombre' ).text('');
					$("#resultadosProductos").clean();
					
				}
			}});
		}
	};
	
	var nuevoTipoFallaFamilia = function(id){
		var tipoFalla = {};
		var title;
		if (id) {
			SSTFacade.getTipoFallas(id,{async:false,callback:function(tf){
				tipoFalla = tf;
			}});
			title = 'MODIFICAR TIPO DE FALLA ' + tipoFalla.descripcion;
		} else {
			var p = $('#resultadosFamilias').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione una familia primero','Información');
				return;
			}
			tipoFalla = {
				familia:{id:p.idFamilia},
				tipo:'F',
				vigente:true
			};
			SSTFacade.getFamiliaByFilter(p,{async:false,callback:function(familia){
				title = 'NUEVO TIPO DE FALLA PARA LA FAMILIA: ' + familia.nombre;
			}});
		}
		$('#tipoFallaForm').reset();
		$('#tipoFallaForm').loadObject(tipoFalla);
		$('#mantenedorTipoFalla').dialog('option','title',title);
		$('#mantenedorTipoFalla').dialog('open');			
	};
	
	var nuevoTipoFallaProducto = function(id){
		var tipoFalla = {};
		var title;
		if (id) {
			SSTFacade.getTipoFallas(id,{async:false,callback:function(tf){
				tipoFalla = tf;
			}});
			title = 'MODIFICAR TIPO DE FALLA ' + tipoFalla.descripcion;
		} else {
			var p = $('#resultadosProductos').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione un producto primero','Información');
				return;
			}
			tipoFalla = {
				producto:{id:p.idProducto},
				tipo:'P',
				vigente:true
			};
			SSTFacade.getProductoById(p.idProducto,{async:false,callback:function(producto){
				title = 'NUEVO TIPO DE FALLA PARA EL PRODUCTO: ' + producto.descripcion;
			}});
		}
		$('#tipoFallaForm').reset();
		$('#tipoFallaForm').loadObject(tipoFalla);
		$('#mantenedorTipoFalla').dialog('option','title',title);
		$('#mantenedorTipoFalla').dialog('open');			
	};
	
	$('#mantenedorTipoFalla').dialog({
		autoOpen:false,
		modal:true,
		width:800,
	    buttons:{
	    	Grabar: function() {
	    		if($('#tipoFallaForm').valid()) {
	    			var tipoFalla = $('#tipoFallaForm').serializeObject();
	    			jConfirm('¿Esta seguro que desea grabar el nuevo tipo de Falla?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveTipoFalla(tipoFalla, function(){
								if (tipoFalla.tipo == 'P') {
									$("#resultadosProductos").flexReload();									
								} else {
									$("#resultadosFamilias").flexReload();									
								}
								$('#mantenedorTipoFalla').dialog('close');
								jAlert('Nuevo tipo de Falla Grabado','Información');																
							});
						}
					});
	    		}
			},	
			Cerrar: function(){
				$('#mantenedorTipoFalla').dialog('close');
			}
	   }
	});
});

var changeStateTipoFalla = function(id,state, grid){
	SSTFacade.updateTipoFallaByEstado({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el tipo de fallas correctamente','Información',function(){
			$('#' + grid).flexReload();
		});
	}});
};

var setupTipoFalla = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idTipoFalla=' + id ;  
};
