$(document).ready(function() {
	var moduloVolver;
	SSTFacade.getModuloByNombreUsuario('onmenuingresarubicbodega',{async:false, callback:function(modulo) {
		moduloVolver = modulo ? modulo : undefined;
	}});
	
	var existe=false;
	var modifica=false;
	var codigo = $('#codigo').val();
	SSTFacade.listParametrosByTipo('UBIS',{async:true,callback:function(tipos){
		$('#tipo\\.codigo').addItems(tipos,'codigo','glosa');
	}});
	if(codigo!=null){
		SSTFacade.getUbicacionInternaCDByCodigo($('#codigo').val(),{async:false,callback:function(ubicacionInterna){
			if(ubicacionInterna!=null && ubicacionInterna.id!=null){
				existe=true;
				modifica=true;
				$('#datosUbicacion').loadObject(ubicacionInterna);
			};
		}});
	}
	
	$('#familias').flexigrid({
		dwrFunction: SSTFacade.listFamiliasByIdUbicacionInterna
		,width:400
		,height:100
		,usepager: false
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'Familias'
		,tipo:'FAUI'
		,seccion:1000090200
		,buttons : [
				    {name:'Agregar', bclass:'btnPlus', onpress:function(){agregarFamilias();}},
				    {separator: true},
				    {name: 'Eliminar', bclass: 'btnMinus', onpress : function(){quitarFamilias();} },
			   		{separator: true},
				]
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
		    	.attr('id', 'chk' + o.id)
		    	.attr('name', 'chk' + o.id)
		    	.attr('checked', $('#familias').isRowChecked(o.id))
		    	.attr('onchange', 'changeState("familias", ' + o.id + ', this)');
			return check;
		}}
		]
	});
	
	$('#lineas').flexigrid({
		dwrFunction: SSTFacade.listLineasByCodigoUbicacionInterna
		,width:400
		,height:100
		,usepager:false
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,title:'Lineas'
		,tipo:'LIUI'
		,seccion:1000090200
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
			    .attr('id', 'chk' + o.id)
			    .attr('name', 'chk' + o.id)
			    .attr('checked', $('#lineas').isRowChecked(o.id))
			    .attr('onchange', 'changeState("lineas", ' + o.id + ', this)');
			return check;
		}}
		]
		,buttons : [
					    {name:'Agregar', bclass:'btnPlus', onpress:function(){agregarLineas();}},
					    {separator: true},
					    {name: 'Eliminar', bclass: 'btnMinus', onpress : function(){quitarLineas();} },
				   		{separator: true},
					]
		
	});
	
	$('#sucursales').flexigrid({
		dwrFunction: SSTFacade.listSucursalesByIdUbicacionInterna
		,width:400
		,height:100
		,usepager: false
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'SUCURSALES'
		,tipo:'SUUI'
		,seccion:1000090200
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
		    	.attr('id', 'chk' + o.id)
		    	.attr('name', 'chk' + o.id)
		    	.attr('checked', $('#sucursales').isRowChecked(o.codigo))
		    	.attr('onchange', 'changeState("sucursales", ' + o.id + ', this)');
			return check;
		}}
		]
		,buttons : [
				    {name:'Agregar', bclass:'btnPlus', onpress:function(){agregarSucursales();}},
				    {separator: true},
				    {name: 'Eliminar', bclass: 'btnMinus', onpress : function(){quitarSucursales();} },
			   		{separator: true},
				]
	});
	
	$('#productos').flexigrid({
		dwrFunction: SSTFacade.listProductosByIdUbicacionInterna
		,width:400
		,height:100
		,usepager: false
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'Productos'
		,tipo:'PRUI'
		,seccion:1000090200
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
		    	.attr('id', 'chk' + o.id)
		    	.attr('name', 'chk' + o.id)
		    	.attr('checked', $('#productos').isRowChecked(o.codigo))
		    	.attr('onchange', 'changeState("productos", ' + o.id + ', this)');
			return check;
		}}
		]
		,buttons : [
				    {name:'Agregar', bclass:'btnPlus', onpress:function(){agregarProductos();}},
				    {separator: true},
				    {name: 'Eliminar', bclass: 'btnMinus', onpress : function(){quitarProductos();} },
			   		{separator: true},
				]
	});
	
	if(existe){
		//Cargar las listas
		$('#crearUbicacion').hide();
		$('#codigo').attr('readonly','');
		$('#lineas').loadData([codigo]);
		$('#familias').loadData([codigo]);
		$('#sucursales').loadData([codigo]);
		$('#productos').loadData([codigo]);
	} else {
		$('#agregarDetalles').hide();
		$('#codigo').val('');
		$('#codigo').removeAttr('readonly');
	}
	
	$('#crearUbicacion').click(function(){
		if($('#datosUbicacion').valid()){
			SSTFacade.saveUbicacionInternaCD($('#datosUbicacion').serializeObject(),{async:true,callback:function(){
				existe=true;
				codigo = $('#codigo').val();
				$('#agregarDetalles').show();
				$('#crearUbicacion').hide();
				$('#lineas').loadData([$('#codigo').val()]);
				$('#codigo').attr('readonly','');
			}});
		}
	});
	
	
	//ADMINISTRAR LINEAS
	var agregarLineas = function(){
		$('#lineasToAdd').loadData([codigo]);
		$('#agregarLineas').dialog('open');
	};
	
	var quitarLineas = function(){
		var lineas=[];
		$.each($('#lineas').getJSONCheckboxSelected(),function(index,value){
			lineas.push(value.codigo);
		});
		SSTFacade.eliminarLineasFromUbicacionInternaDetalle(lineas,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
			$('#lineas').flexReload();
		}});
	};
	
	$('#lineasToAdd').flexigrid({
		dwrFunction: SSTFacade.listLineasLessCodigoUbicacion
		,width:330
		,height:200
		,usepager: true
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'AGREGAR LINEAS'
		,tipo:'LIUI'
		,seccion:1000090200
		,idPropertyForCheck:'codigo'
		,dwrFunctionListAllId:SSTFacade.listAllIdLineaLessCodigoUbicacion
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
			    .attr('id', 'chk' + o.codigo)
			    .attr('name', 'chk' + o.codigo)
			    .attr('checked', $('#lineasToAdd').isRowChecked(o.codigo))
			    .attr('onchange', 'changeState("lineasToAdd", ' + o.codigo + ', this)');
			return check;
		}}
		]
	});
	
	$('#agregarLineas').dialog({
		autoOpen: false,
		modal:true,
		width:360,
		buttons:{
			Grabar: function() {
				var lineas = [];
				$.each($('#lineasToAdd').getSelectedCheckFromList(),function(index,value){
					lineas.push(value.codigo);
				});
				SSTFacade.saveLineasForUbicacionInterna(lineas,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
					if(modifica){
						$('#lineas').flexReload();
						var ubi=$('#datosUbicacion').serializeObject();
						ubi.codigo=codigo;
						SSTFacade.updateModificaUbicacionInternaCD(ubi,{async:true,callback:function(){
							
						}});
					} else {
						$('#lineas').loadData([codigo]);
					}
				}});
				$('#agregarLineas').dialog('close');
			},
			Cerrar: function(){
				$('#agregarLineas').dialog('close');
			}
		}
	});
	
	
	//ADMINISTRAR FAMILIAS
	var agregarFamilias = function(){
		$('#familiasToAdd').loadData([codigo]);
		$('#agregarFamilias').dialog('open');
	};
	
	var quitarFamilias = function(){
		var familias=[];
		$.each($('#familias').getJSONCheckboxSelected(),function(index,value){
			familias.push(value.id);
		});
		SSTFacade.eliminarFamiliasFromUbicacionInternaDetalle(familias,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
			$('#familias').flexReload();
		}});
	};
	
	$('#familiasToAdd').flexigrid({
		dwrFunction: SSTFacade.listFamiliaLessCodigoUbicacion
		,width:330
		,height:200
		,usepager: true
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'AGREGAR FAMILIAS'
		,tipo:'FAUI'
		,seccion:1000090200
		,idPropertyForCheck:'codigo'
		,dwrFunctionListAllId:SSTFacade.listAllFamiliaLessCodigoUbicacion
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
			    .attr('id', 'chk' + o.codigo)
			    .attr('name', 'chk' + o.codigo)
			    .attr('checked', $('#familiasToAdd').isRowChecked(o.codigo))
			    .attr('onchange', 'changeState("familiasToAdd", ' + o.codigo + ', this)');
			return check;
		}}
		]
	});
	
	$('#agregarFamilias').dialog({
		autoOpen: false,
		modal:true,
		width:360,
		buttons:{
			Grabar: function() {
				var familias = [];
				$.each($('#familiasToAdd').getSelectedCheckFromList(),function(index,value){
					familias.push(value.codigo);
				});
				SSTFacade.saveFamiliasForUbicacionInterna(familias,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
					
					if(modifica){
						$('#familias').flexReload();
						var ubi=$('#datosUbicacion').serializeObject();
						ubi.codigo=codigo;
						SSTFacade.updateModificaUbicacionInternaCD(ubi,{async:true,callback:function(){
							
						}});
					} else {
						$('#familias').loadData([codigo]);
					}
				}});
				$('#agregarFamilias').dialog('close');
			},
			Cerrar: function(){
				$('#agregarFamilias').dialog('close');
			}
		}
	});
	
	
	
	
	//ADMINISTRAR SUCURSALES
	var agregarSucursales = function(){
		$('#sucursalesToAdd').loadData([codigo]);
		$('#agregarSucursales').dialog('open');
	};
	
	var quitarSucursales = function(){
		var sucursales=[];
		$.each($('#sucursales').getJSONCheckboxSelected(),function(index,value){
			sucursales.push(value.id);
		});
		var todas = [];
		todas = $('#sucursales').getJSON();
		if(todas.length==sucursales.length){
			jAlert('Debe dejar al menos una Sucursal configurada','Aviso',function(){});
		} else {
			SSTFacade.eliminarSucursalesFromUbicacionInternaDetalle(sucursales,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
				$('#sucursales').flexReload();
			}});
		}
	};
	
	$('#sucursalesToAdd').flexigrid({
		dwrFunction: SSTFacade.listSucursalLessCodigoUbicacion
		,width:330
		,height:200
		,usepager: true
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'AGREGAR SUCURSALES'
		,tipo:'SUUI'
		,seccion:1000090200
		,idPropertyForCheck:'id'
		,dwrFunctionListAllId:SSTFacade.listAllSucursalLessCodigoUbicacion
		,overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
			    .attr('id', 'chk' + o.id)
			    .attr('name', 'chk' + o.id)
			    .attr('checked', $('#sucursalesToAdd').isRowChecked(o.id))
			    .attr('onchange', 'changeState("sucursalesToAdd", ' + o.id + ', this)');
			return check;
		}}
		]
	});
	
	$('#agregarSucursales').dialog({
		autoOpen: false,
		modal:true,
		width:360,
		buttons:{
			Grabar: function() {
				var sucursales = [];
				var familias = [];
				var lineas  = [];
				var productos = [];
				$.each($('#sucursalesToAdd').getSelectedCheckFromList(),function(index,value){
					sucursales.push(value.id);
				});
				$.each($('#familias').getJSON(),function(index,value){
					familias.push(value.id);
				});
				$.each($('#lineas').getJSON(),function(index,value){
					lineas.push(value.codigo);
				});
				$.each($('#productos').getJSON(),function(index,value){
					productos.push(value.id);
				});
				
				SSTFacade.sucursalesAreAvailable(familias,lineas,productos,sucursales,{ubicacionInterna:{codigo:codigo}},{asycn:true,callback:function(){
					SSTFacade.saveSucursalesForUbicacionInterna(sucursales,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
						if(modifica){
							$('#sucursales').flexReload();
							var ubi=$('#datosUbicacion').serializeObject();
							ubi.codigo=codigo;
							SSTFacade.updateModificaUbicacionInternaCD(ubi,{async:true,callback:function(){
								
							}});
						}else{
							$('#sucursales').loadData([codigo]);
						}
					}});
				}});
				
				$('#agregarSucursales').dialog('close');
			},
			Cerrar: function(){
				$('#agregarSucursales').dialog('close');
			}
		}
	});
	
	
	//ADMINISTRAR PRODUCTOS
	var producto = {};
	var agregarProductos = function(){
		$('div[aria-labelledby="ui-dialog-title-agregarProductos"]').find('input[value="Grabar"]').attr('disabled','');
		$('#agregarProductos').dialog('open');
		$('#agregarProductosForm').reset();
		limpiarAgregarProductos();
	};
	
	var quitarProductos = function(){
		var productos=[];
		$.each($('#productos').getJSONCheckboxSelected(),function(index,value){
			productos.push(value.id);
		});
		SSTFacade.eliminarProductosFromUbicacionInternaDetalle(productos,{ubicacionInterna:{codigo:codigo}},{async:true,callback:function(){
			$('#productos').flexReload();
		}});
	};
	
	$('#agregarProductos').dialog({
		autoOpen: false,
		modal:true,
		width:500,
		title:'Agregar Producto',
		buttons:{
			Grabar: function() {
				if(producto!=null){
					SSTFacade.saveProductoForUbicacionInterna({ubicacionInterna:{codigo:codigo},producto:{id:$('#idProducto').val()}},{async:true,callback:function(){
						if(modifica){
							$('#productos').flexReload();
							var ubi=$('#datosUbicacion').serializeObject();
							ubi.codigo=codigo;
							SSTFacade.updateModificaUbicacionInternaCD(ubi,{async:true,callback:function(){
								
							}});
						} else {
							$('#productos').loadData([codigo]);
						}
					}});
//					$('#productos').flexReload();
					$('#agregarProductos').dialog('close');
				}
			},
			Cerrar: function(){
				$('#agregarProductos').dialog('close');
			}
		}
	});
	
	$('#buscarProducto').click(function(){
		if($('#agregarProductosForm').valid()){
			SSTFacade.isProductoOnUbicacionInternaCD($('#idProducto').val(),codigo,{async:true,callback:function(existe){
				if(!existe){
					SSTFacade.getProductoById($('#idProducto').val(),{async:true,callback:function(data){
						producto=data;
						if(producto.id!=null){
							$('#lineaProducto').text(producto.familia.linea.glosa);
							$('#familiaProducto').text(producto.familia.nombre);
							$('#descripcionProducto').text(producto.descripcion);
							$('div[aria-labelledby="ui-dialog-title-agregarProductos"]').find('input[value="Grabar"]').removeAttr('disabled');
							
						} else {
							jAlert('No existe el producto','Aviso',function(){
								limpiarAgregarProductos();
							});
						}
						
					}});
				} else {
					jAlert('El producto ya pertenece a esta ubicación','Aviso',function(){
						limpiarAgregarProductos();
					});
				}
			}});
		}
	});
	
	$('#grabar').click(function(){
		if($('#datosUbicacion').valid()){
			var ubi=$('#datosUbicacion').serializeObject();
			ubi.codigo=codigo;
			$.alerts.okButton = '&nbsp;Continuar&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro de grabar la ubicación interna?','AVISO',function(r){
				if(r){
					SSTFacade.updateModificaUbicacionInternaCD(ubi,{async:true,callback:function(){
						jAlert('Se ha grabado la ubicación interna','AVISO');
					}});
				}
			});
		};
	});
	
	$('#volver').click(function(){
		parent.location = context +'/index.do?e=' + moduloVolver.codigo + '&m=' + moduloInicial.codigo;
	});
	
	var limpiarAgregarProductos = function(){
		$('#idProducto').val('');
		$('#descripcionProducto').text('');
		$('#familiaProducto').text('');
		$('#lineaProducto').text('');
	};
});