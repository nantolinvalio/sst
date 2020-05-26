var moduloConfigurar;
$(document).ready(function() {
	$("#tabs").tabs();
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.getModuloByNombreUsuario('onmenuagregartiposfalla',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
	$('#resultadosFamilias').flexigrid({
		dwrFunction: SSTFacade.listAccesoriosByFilterForAdministracion,
		seccion: 1000100000,
		tipo: 'ACCE',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'configurar',propiedad:function(o){
		    	var btn = $('<input type="button">')
        			.attr('onclick','setupAccesorio(' + o.id + ',false)')
					.attr('value','Asignar Fallas');
		    	return btn;
		    }},
		    {findName:'tipo',propiedad:function(o){return 'FAMILIA';}},
		    {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
		    {findName:'bloquear',propiedad:function(o){
		    	var btn = $('<input type="button">');
		    	if (o.vigente) {
	        		btn.attr('value','Bloquear');
	        		btn.attr('onclick','changeStateAccesorio(' + o.id + ',false, "resultadosFamilias")');
	        	} else {
	        		btn.attr('value','Desbloquear');
	        		btn.attr('onclick','changeStateAccesorio(' + o.id + ',true, "resultadosFamilias")');
	        	}
	        	return btn;
		    }},
	   	],
		dblclickFunction:function(el,idx,comp){
			nuevoAccesorioFamilia(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Accesorio', bclass: 'btnPlus', onpress : function(){nuevoAccesorioFamilia();} },
			{separator: true}
   		],
	});
	
	$('#resultadosProductos').flexigrid({
		dwrFunction: SSTFacade.listAccesoriosByFilterForAdministracion,
		seccion: 1000200000,
		tipo: 'ACCE',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'configurar',propiedad:function(o){
		    	var btn = $('<input type="button">')
        			.attr('onclick','setupAccesorio(' + o.id + ',false)')
					.attr('value','Asignar Fallas');
		    	return btn;
		    }},
		    {findName:'tipo',propiedad:function(o){return 'PRODUCTO';}},
		    {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
		    {findName:'bloquear',propiedad:function(o){
		    	var btn = $('<input type="button">');
		    	if (o.vigente) {
	        		btn.attr('value','Bloquear');
	        		btn.attr('onclick','changeStateAccesorio(' + o.id + ',false, "resultadosProductos")');
	        	} else {
	        		btn.attr('value','Desbloquear');
	        		btn.attr('onclick','changeStateAccesorio(' + o.id + ',true, "resultadosProductos")');
	        	}
	        	return btn;
		    }},
	   	],
		dblclickFunction:function(el,idx,comp){
			nuevoAccesorioProducto(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Accesorios', bclass: 'btnPlus', onpress : function(){nuevoAccesorioProducto();} },
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
	
	var nuevoAccesorioFamilia = function(id){
		var accesorio = {};
		var title;
		if (id) {
			SSTFacade.getAccesorioById(id,{async:false,callback:function(tf){
				accesorio = tf;
			}});
			title = 'MODIFICAR ACCESORIO ' + accesorio.descripcion;
		} else {
			var p = $('#resultadosFamilias').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione una familia primero','Información');
				return;
			}
			accesorio = {
				familia:{id:p.idFamilia},
				tipo:'F',
				vigente:true
			};
			SSTFacade.getFamiliaByFilter(p,{async:false,callback:function(familia){
				title = 'NUEVO ACCESORIO PARA LA FAMILIA: ' + familia.nombre;
			}});
		}
		$('#accesorioForm').reset();
		$('#accesorioForm').loadObject(accesorio);
		$('#mantenedorAccesorio').dialog('option','title',title);
		$('#mantenedorAccesorio').dialog('open');			
	};
	
	var nuevoAccesorioProducto = function(id){
		var accesorio = {};
		var title;
		if (id) {
			SSTFacade.getAccesorioById(id,{async:false,callback:function(tf){
				accesorio = tf;
			}});
			title = 'MODIFICAR ACCESORIO ' + accesorio.descripcion;
		} else {
			var p = $('#resultadosProductos').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione un producto primero','Información');
				return;
			}
			accesorio = {
				producto:{id:p.idProducto},
				tipo:'P',
				vigente:true
			};
			SSTFacade.getProductoById(p.idProducto,{async:false,callback:function(producto){
				title = 'NUEVO ACCESORIO PARA EL PRODUCTO: ' + producto.descripcion;
			}});
		}
		$('#accesorioForm').reset();
		$('#accesorioForm').loadObject(accesorio);
		$('#mantenedorAccesorio').dialog('option','title',title);
		$('#mantenedorAccesorio').dialog('open');		
	};
	
	$('#mantenedorAccesorio').dialog({
		autoOpen:false,
		modal:true,
		width:800,
	    buttons:{
	    	Grabar: function() {
	    		if($('#accesorioForm').valid()) {
	    			var accesorio = $('#accesorioForm').serializeObject();
	    			jConfirm('¿Esta seguro que desea grabar el nuevo accesorio?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveAccesorio(accesorio, function(){
								if (accesorio.tipo == 'P') {
									$("#resultadosProductos").flexReload();									
								} else {
									$("#resultadosFamilias").flexReload();									
								}
								$('#mantenedorAccesorio').dialog('close');
								jAlert('Nuevo Accesorio Grabado','Información');																
							});
						}
					});
	    		}
			},	
			Cerrar: function(){
				$('#mantenedorAccesorio').dialog('close');
			}
	   }
	});
});

var changeStateAccesorio = function(id,state, grid){
	SSTFacade.updateAccesoriosByEstado({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el accesorio correctamente','Información',function(){
			$('#' + grid).flexReload();
		});
	}});
};

var setupAccesorio = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idAccesorio=' + id ;  
};
