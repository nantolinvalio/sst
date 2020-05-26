$(document).ready(function() {
	var idProducto = $('#idProducto').val();
	var producto;
	var tipoGarantia;

	SSTFacade.getProductoById(idProducto,{async:false,callback:function(pr){
		producto = pr;
		$("#printProducto").text('Producto: ' + pr.descripcion);		
	}});

	$('#buscar').click(function(){
		buscar();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	var buscar = function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultadosAsignar').loadData([filter]);
		}
	};
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultadosAsignar').clean();
	});

	var asignaServicioTecnico = function(){
		$('#resultadosAsignar').clean();
		$('#filtro_buscador').reset();
		$('#mantenedorserviciostecproducto').dialog('open');
	};

	var asignaProductosServicioTecnico = function(){
		$("#detalleServicioTecnicoLeft, #detalleServicioTecnicoRight").find('label').remove();
		$('#garantiaMaster, #garantiaProveedor').attr('checked',false);
		var servicios = $("#resultadosAsignar").getSelectedCheckFromList();
		if (servicios.length == 0) {
			jAlert('Por favor seleccione al menos un servicio técnico', 'Aviso');
			return;
		}
		$(servicios).each(function(i,servicio){
			var lbl=$('<label></label>')
				.css('whidth','100%')
				.css('display','block')
				.append($('<b></b>').text(servicio.id));
			if (i % 2 == 0 ) {
				$("#detalleServicioTecnicoLeft").append(lbl);					
			} else {
				$("#detalleServicioTecnicoRight").append(lbl);
			}
		 });
		$("#modalAsignacionServiciosTecnicos").dialog('open');
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

	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listServicioTecnicoProducto, // lista servicios tecnicos del producto
		dwrFunctionListAllId:SSTFacade.listAllServiciosTecnicosProductoCheck, // lista los check 
		tipo: 'ASTP',
		seccion: 90007000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		saveCheckValues:true,
		checkAllButton:true,
		overrideModel: [
          {findName:'servicioTecnico.vigente',propiedad:function(o){return o.servicioTecnico.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';} },
      	  {findName:'checkMarca', propiedad:function(o){
			 var check = $('<input type="checkbox">')
	 			.attr('id', 'chk' + o.id)
	 			.attr('name', 'chk' + o.id)
	 			.attr('checked',$('#resultados').isRowChecked(o.id))
				.attr('onchange','changeState("resultados", ' + o.id + ',this)');
			 return check;
		}}
        ],
    	rp: 10,
		buttons : [
               {name: 'Asignar Nuevo(s) Servicios Técnicos al Producto', bclass: 'btnPlus', onpress : function(){asignaServicioTecnico();}},
               {separator: true},
               {name: 'Eliminar Asignación', bclass: 'btnRechazar', onpress : function(){eliminarAsignacionServicioTecnico();}},
               {separator: true}
		],
	});	
	
	$("#resultados").loadData([{idProducto:idProducto}]);

	$('#resultadosAsignar').flexigrid({
		dwrFunction:SSTFacade.listServicioTecnico, 
		dwrFunctionListAllId:SSTFacade.listAllProductosServicioTCheck,
		tipo: 'ASTP',
		dwrFunctionListAllId:SSTFacade.listAllServiciosTecnicosCheck,
		tipo: 'AST2',
		seccion: 1000010000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		saveCheckValues:true,
		checkAllButton:true,
		overrideModel: [{findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';} },
		                {findName:'checkMarca', propiedad:function(o){
								 var check = $('<input type="checkbox">')
						 			.attr('id', 'chk' + o.id)
						 			.attr('name', 'chk' + o.id)
						 			.attr('checked',$('#resultadosAsignar').isRowChecked(o.id))
					 				.attr('onchange','changeState("resultadosAsignar", ' + o.id + ',this)');
								 return check;
			    			}
						}],
		buttons : [
		    {name: 'Asignar nuevo(s) Servicio(s) Tecnico(s) al Producto ', bclass: 'btnPlus', onpress : function(){asignaProductosServicioTecnico();}},       
            {separator: true},
           
		],
	});	
	
	$("#resultadosAsignar").loadData([{idProducto:idProducto}]);
	
	$('#mantenedorserviciostecproducto').dialog({
		autoOpen: false,
		modal:true,
		width:920,
		buttons:{                         
			Cerrar: function(){
				$('#mantenedorserviciostecproducto').dialog('close');
			}
		}
	});
	
	$('#modalAsignacionServiciosTecnicos').dialog({
		autoOpen:false,
		modal:true,
		width:500,
	    buttons:{
	    	Grabar: function() {
	    		
	    		var garantiaMaster = $('#garantiaMaster').is(':checked');
	    		var garantiaProveedor = $('#garantiaProveedor').is(':checked');
	    		
	    		if (!garantiaMaster && !garantiaProveedor) {
	    			jAlert('Favor Seleccione un tipo de Garantía','Aviso');
	    			return;
	    		}
	    		
	    		var servicios = $("#resultadosAsignar").getSelectedCheckFromList();
    			SSTFacade.asignarServicioTecnicoAProductos(producto, servicios, garantiaMaster,garantiaProveedor, function(){
    				jAlert('Información Grabada Correctamente','Información');
    				$('#resultados').flexReload();
    				$('#filtro_buscador').reset();
    				$('#resultadosAsignar').clean();
    				$('#mantenedorserviciostecproducto').dialog('close');
    				$('#modalAsignacionServiciosTecnicos').dialog('close');
    			});
			},	
			Cerrar: function(){
				$('#modalAsignacionServiciosTecnicos').dialog('close');
			}
	   }
	});
	
});
