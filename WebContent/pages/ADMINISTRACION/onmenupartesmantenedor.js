var moduloConfigurar;
$(document).ready(function() {
	$("#tabs").tabs();
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	$('#resultadosFamilias').flexigrid({
		dwrFunction: SSTFacade.listPartesByFilterPartes,
		seccion: 1000300000,
		tipo: 'TIPA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'seleccione',propiedad:function(o){
		    	var check = $('<input type="checkbox">')
				.attr('id', 'chk' + o.id)
				.attr('name', 'chk' + o.id)
				.attr('checked',$('#productos').isRowChecked(o.id))
				.attr('onchange','changeState("productos", ' + o.id + ',this)');
		    	return check;
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
			nuevaParteFamilia(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Parte', bclass: 'btnPlus', onpress : function(){nuevaParteFamilia();} },
			{separator: true}
   		],
	});
	
	$('#resultadosProductos').flexigrid({
		dwrFunction: SSTFacade.listPartesByFilterPartes,
		seccion: 1000300000,
		tipo: 'TIPA',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'seleccione',propiedad:function(o){
		    	var check = $('<input type="checkbox">')
				.attr('id', 'chk' + o.id)
				.attr('name', 'chk' + o.id)
				.attr('checked',$('#productos').isRowChecked(o.id))
				.attr('onchange','changeState("productos", ' + o.id + ',this)');
		    	return check;
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
			nuevaParteProducto(el.id);
		},
	   	buttons : [
           	{name: 'Ingresar Parte', bclass: 'btnPlus', onpress : function(){nuevaParteProducto();} },
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
	
	var nuevaParteFamilia = function(id){
		var parte = {};
		var title;
		if (id) {
			SSTFacade.getParteById(id,{async:false,callback:function(p){
				parte = p;
			}});
			title = 'MODIFICAR TIPO DE FALLA ' + parte.descripcion;
		} else {
			var p = $('#resultadosFamilias').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione una familia primero','Información');
				return;
			}
			parte = {
				familia:{id:p.idFamilia},
				tipo:'F',
				vigente:true
			};
			SSTFacade.getFamiliaByFilter(p,{async:false,callback:function(familia){
				title = 'NUEVA PARTE PARA LA FAMILIA: ' + familia.nombre;
			}});
		}
		$('#partesForm').reset();
		$('#partesForm').loadObject(parte);
		$('#mantenedorpartes').dialog('option','title',title);
		$('#mantenedorpartes').dialog('open');			
	};
	
	var nuevaParteProducto = function(id){
		var parte = {};
		var title;
		if (id) {
			SSTFacade.getParteById(id,{async:false,callback:function(p){ 
				parte = p;
			}});
			title = 'MODIFICAR TIPO DE FALLA ' + parte.descripcion;
		} else {
			var p = $('#resultadosProductos').getParameters()[0];
			if (p == undefined) {
				jAlert('Seleccione un producto primero','Información');
				return;
			}
			parte = {
				producto:{id:p.idProducto},
				tipo:'P',
				vigente:true
			};
			SSTFacade.getProductoById(p.idProducto,{async:false,callback:function(producto){
				title = 'NUEVA PARTE PARA EL PRODUCTO: ' + producto.descripcion;
			}});
		}
		$('#partesForm').reset();
		$('#partesForm').loadObject(parte);
		$('#mantenedorpartes').dialog('option','title',title);
		$('#mantenedorpartes').dialog('open');			
	};
	
	$('#mantenedorpartes').dialog({
		autoOpen:false,
		modal:true,
		width:800,
	    buttons:{
	    	Grabar: function() {
	    		if($('#partesForm').valid()) {
	    			var parte = $('#partesForm').serializeObject();
	    			jConfirm('¿Esta seguro que desea grabar la nueva parte?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.savePartes(parte, function(){
								if (parte.tipo == 'P') {
									$("#resultadosProductos").flexReload();									
								} else {
									$("#resultadosFamilias").flexReload();									
								}
								$('#mantenedorpartes').dialog('close');
								jAlert('Nueva Parte Grabada','Información');																
							});
						}
					});
	    		}
			},	
			Cerrar: function(){
				$('#mantenedorpartes').dialog('close');
			}
	   }
	});
});

var changeStateTipoFalla = function(id,state, grid){
	SSTFacade.updateParteByEstado({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado la Parte correctamente','Información',function(){
			$('#' + grid).flexReload();
		});
	}});
};

var setupTipoFalla = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idTipoFalla=' + id ;  
};