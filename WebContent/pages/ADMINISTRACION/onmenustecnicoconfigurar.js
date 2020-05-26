$(document).ready(function() {
	var idSTecnico = $('#idSTecnico').val();
	var servicioTecnico;
	var idProducto;
	var producto;
	var tipoGarantia;
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#familia").addItems(data, "id", ["id","nombre"], true);
	});
	
	SSTFacade.listMarca(function(data){
		$("#marca").addItems(data, "codigo", "nombre", true);
	});
	
	SSTFacade.listProveedor(function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#mantenedorProductosSTForm').keypress(function(e){
        if(e.which == 13) {
        	buscar();
        }
	});
	
	var buscar = function() {
		if($('#mantenedorProductosSTForm').valid()) {
			var filter = $('#mantenedorProductosSTForm').serializeObject();
			$('#resultadosAsignar').loadData([filter]);
		}
	};
	
	$('#limpiar').click(function(){
		$('#mantenedorProductosSTForm').reset();
		$('#resultadosAsignar').clean();
	});

	var asignaServicioTecnico = function(){
		$('#mantenedorProductosSTForm').reset();
		$('#resultadosAsignar').clean();
		$('#mantenedorProductosST').dialog('open');
	};

	var asignaProductosServicioTecnico = function(servTecnico){
		$("#detalleProductosLeft, #detalleProductosRight").find('label').remove();
		$('#garantiaMaster, #garantiaProveedor').attr('checked',false);
		var productos = $("#resultadosAsignar").getSelectedCheckFromList();
		if (productos.length == 0) {
			jAlert('Por favor seleccione al menos un producto', 'Aviso');
			return;
		}
		$(productos).each(function(i,producto){
			var lbl=$('<label></label>')
				.css('whidth','100%')
				.css('display','block')
				.append($('<b></b>').text(producto.id));
			if (i % 2 == 0 ) {
				$("#detalleProductosLeft").append(lbl);					
			} else {
				$("#detalleProductosRight").append(lbl);
			}
		 });
		$("#modalAsignacionProductos").dialog('open');
	};
	
	var eliminarAsignacionServicioTecnico = function() {
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro que desea eliminar la asignación?', 'Confirmación', function(r){
			if (r) {
				var ids = $('#resultados').getSelectedCheckFromList();
				SSTFacade.eliminarAsignacionServicioTecnico(ids, {async:true,callback:function(g){
					$('#resultados').flexReload();
				}});
			}
		});
	};
    
	SSTFacade.getSTecnicoById(idSTecnico,{async:false,callback:function(st){
		 servicioTecnico = st;
		 $("#printServicioTecnico").text('Servicio Técnico: ' + st.nombre + ' - Dirección: ' + st.direccion);
	}});

	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listProductosByServicioTecnico,
		dwrFunctionListAllId:SSTFacade.listAllProductosServicioTecnicoCheck,
		tipo: 'CST',
		seccion: 1000010000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		saveCheckValues:true,
		checkAllButton:true,
		overrideModel: [{
							findName:'checkMarca', propiedad:function(o){
								 var check = $('<input type="checkbox">')
						 			.attr('id', 'chk' + o.id)
						 			.attr('name', 'chk' + o.id)
						 			.attr('checked',$('#resultados').isRowChecked(o.id))
					 				.attr('onchange','changeState("resultados", ' + o.id + ',this)');
								 return check;
			    			}
						}],
		buttons : [
            {name: 'Asignar Nuevo(s) Producto(s) al Servicio Técnico', bclass: 'btnPlus', onpress : function(){asignaServicioTecnico();}},
            {separator: true},
            {name: 'Eliminar Asignación', bclass: 'btnRechazar', onpress : function(){eliminarAsignacionServicioTecnico();}},
	   		{separator: true}
		],
	});	
	
	$("#resultados").loadData([{idSTecnico:idSTecnico}]);

	$('#resultadosAsignar').flexigrid({
		dwrFunctionListAllId:SSTFacade.listAllProductosByFilterCheck,
		saveCheckValues:true,
		checkAllButton:true,
		dwrFunction:SSTFacade.listProductosByFilter,
		tipo: 'APST',
		seccion: 1000020000,
		showTableToggleBtn: true,
		multisel:false,
		idProperty: "producto.id",
		singleSelect:true,
		overrideModel: [{
			findName:'checkMarca', propiedad:function(o){
				 var check = $('<input type="checkbox">')
		 			.attr('id', 'chk' + o.id)
		 			.attr('name', 'chk' + o.id)
		 			.attr('checked',$('#resultadosAsignar').isRowChecked(o.id))
	 				.attr('onchange','changeState("resultadosAsignar", ' + o.id + ',this)');
				 return check;
			}
		}],       
		buttons : [
            {name: 'Asignar Productos Seleccionados al Servicio Técnico', bclass: 'btnPlus', onpress : function(){asignaProductosServicioTecnico();}},
            {separator: true}
		],
	});	
	
	$('#mantenedorProductosST').dialog({
		autoOpen: false,
		modal:true,
		width:920,
		buttons:{                         
			Cerrar: function(){
				$('#mantenedorProductosST').dialog('close');
			}
		}
	});
	
	$('#modalAsignacionProductos').dialog({
		autoOpen:false,
		modal:true,
		width:500,
	    buttons:{
	    	Grabar: function(servTecnico, producto, tipoGarantia) {
	    		var garantiaMaster = $('#garantiaMaster').is(':checked');
	    		var garantiaProveedor = $('#garantiaProveedor').is(':checked');
	    		if (!garantiaMaster && !garantiaProveedor) {
	    			jAlert('Favor Seleccione un tipo de Garantía','Aviso');
	    			return;
	    		}
	    		
	    		var productos = $("#resultadosAsignar").getSelectedCheckFromList();
    			SSTFacade.asignarProductosAServicioTecnico(servicioTecnico, productos, garantiaMaster,garantiaProveedor, function(){
    				jAlert('Información Grabada Correctamente','Información');
    				$('#resultados').flexReload();
    				$('#mantenedorProductosSTForm').reset();
    				$('#resultadosAsignar').clean();
    				$('#modalAsignacionProductos').dialog('close');
    				$('#mantenedorProductosST').dialog('close');
    			});
			},	
			Cerrar: function(){
				$('#modalAsignacionProductos').dialog('close');
			}
	   }
	});
	
});










