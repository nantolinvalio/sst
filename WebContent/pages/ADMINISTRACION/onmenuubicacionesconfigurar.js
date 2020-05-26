var moduloConfigurar;

$(document).ready(function() {
	var idSTecnico  = $('#idSTecnico').val();
	var idUbicacion = $('#idUbicacion').val();
	var idComuna;
	var tipo;
	
	SSTFacade.getModuloByNombreUsuario('onmenustecnicoconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
    SSTFacade.getUbicacionById(idUbicacion,{async:false,callback:function(ubicacion){
        idComuna = ubicacion.comuna.id;
        tipo = ubicacion.tipo;
        switch (ubicacion.tipo) {
        case ("SC"):
        	$("#printUbicacion").text('Ubicacion: ' + ubicacion.nombre + ' - Tipo: ' + "SUCURSAL" + '- Direccion: ' + ubicacion.direccion);
            break;
        case ("OF"):
        	$("#printUbicacion").text('Ubicacion: ' + ubicacion.nombre + ' - Tipo: ' + "OFICINA" + '- Direccion: ' + ubicacion.direccion);
            break;
        case ("ST"):
        	$("#printUbicacion").text('Ubicacion: ' + ubicacion.nombre + ' - Tipo: ' + "OFICINA" + '- Direccion: ' + ubicacion.direccion);
            break;
        case ("CD"):
        	$("#printUbicacion").text('Ubicacion: ' + ubicacion.nombre + ' - Tipo: ' + "CENTRO DE DISTRIBUCION" + '- Direccion: ' + ubicacion.direccion);
            break;
        case ("BR"):
        	$("#printUbicacion").text('Ubicacion: ' + ubicacion.nombre + ' - Tipo: ' + "BODEGA" + '- Direccion: ' + ubicacion.direccion);
            break;
        default:
        	$("#printUbicacion").text("Error al recuperar informacion" );
    }
        
    }});
    
    SSTFacade.listBodegas({async:false,callback:function(bodega){	
    	$.each(bodega,function(i,bd){
			var chk = $('<input type="checkbox">')
						.attr('id', bd.id)
						.attr('name', bd.id)
						.css('width','10%');
			
			SSTFacade.isDestinoDeUbicacion({id:idUbicacion},{id:bd.id},{async:false,callback:function(isDestino){
				chk.attr('checked',isDestino);
			}});
			var dv = $('<div></div>')
			            .css('whidth','1000px')
						.append(chk)
						.append(bd.id)
						.append(" ")
						.append(bd.nombre);	
			if (i%2 == 0) {
				$('#rolLeft').append(dv);
			} else {
				$('#rolRight').append(dv);
			}
		});
    	
    	
    }});
    
	$('#buscar').click(function(){
		if($('#mantenedorStecnicoForm').valid()) {
			var filter = $('#mantenedorStecnicoForm').serializeObject();
			$('#resultadosAsignar').loadData([filter]);
		}
	});
	
	$('#mantenedorStecnicoForm').keypress(function(e){
		if(e.which == 13) {
			if($('#mantenedorStecnicoForm').valid()) {
				var filter = $('#mantenedorStecnicoForm').serializeObject();
				$('#resultadosAsignar').loadData([filter]);
			}
		}
	});
	
	$('#limpiar').click(function(){  
		$('#mantenedorStecnicoForm').reset();
		$('#resultadosAsignar').clean();
	});
	
	$('#mantenedorProveedorForm').keypress(function(e){
		if(e.which == 13) {
			if($('#mantenedorProveedorForm').valid()) {
				var filter = $('#mantenedorProveedorForm').serializeObject();
				$('#resultadosAsignarProveedor').loadData([filter]);
			}
		}
	});
	
	$('#buscarProveedor').click(function(){
		if($('#mantenedorProveedorForm').valid()) {
			var filter = $('#mantenedorProveedorForm').serializeObject();
			$('#resultadosAsignarProveedor').loadData([filter]);
		}
	});
	
	$('#limpiarProveedor').click(function(){  
		$('#mantenedorProveedorForm').reset();
		$('#resultadosAsignarProveedor').clean();
	});
	
	var loadComunas = function(idRegion) {
		$('#idComuna').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#idComuna').addItems(comunas,"id","glosa",true);
		}});
	};
	
	$('#grabarBodegas').click(function(){
		jConfirm('¿Esta seguro que desea grabar las bodegas seleccionadas como destinos?', 'Confirmación', function(r){
			if (r) {
				var bodegas = $('#bodegasForm').find('input[type=checkbox]:checked'); 
				var bodegasJson = [];
				$.each(bodegas,function(i,bodega){
					bodegasJson.push({id:$(bodega).attr('id')});
				});
				SSTFacade.asignarBodegasAUbicacion({id:idUbicacion}, bodegasJson, function(){
			            		
						jAlert('Información Grabada Correctamente','Información');
						$('#resultados').flexReload();
				});
			}
		});
	});
	
	var eliminarAsignacionUbicacion = function() {
		$.alerts.okButton = '&nbsp;Continuar&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro que desea eliminar la asignación?', 'Confirmación', function(r){
			if (r) {
				var ids = $('#resultados').getSelectedCheckFromList();
				SSTFacade.eliminarAsignacionUbicacion(ids, {async:true,callback:function(g){
					$('#resultados').flexReload();
				}});
			}
		});
	};
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#idRegion').addItems(data, "id", "glosa", true);
		loadComunas($('#idComuna').val());
	}});
	
	$('#idRegion').change(function(){
		loadComunas($(this).val());
	});
	
	var asignaServicioTecnicoaUbicacion = function(servTecnico){
		var serviciosTecnicos = $("#resultadosAsignar").getSelectedCheckFromList();
		if (serviciosTecnicos.length == 0) {
			jAlert('Por favor seleccione al menos un elemento', 'Aviso');
			return;
		}
		
		jConfirm('¿Esta seguro que desea grabar el Servicio tecnico?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.getUbicacionById(idUbicacion,{async:false,callback:function(ubicacion){
					SSTFacade.asignarDestinoAUbicacion(ubicacion, serviciosTecnicos, function(){
						jAlert('Información Grabada Correctamente','Información');
						$('#resultados').flexReload();
						$('#resultadosAsignar').clean();
						$('#mantenedorStecnico').dialog('close');
					});
				 }});
			}
		});
	};
	
	var asignaProveedorUbicacion = function(proveedor){
		var proveedores = $("#resultadosAsignarProveedor").getSelectedCheckFromList();
		if (proveedores.length == 0) {
			jAlert('Por favor seleccione al menos un elemento', 'Aviso');
			return;
		}
		
		jConfirm('¿Esta seguro que desea grabar el proveedor?', 'Confirmación', function(r){
			if (r) {
				SSTFacade.getUbicacionById(idUbicacion,{async:false,callback:function(ubicacion){
					SSTFacade.asignarProveedoresAUbicacion(ubicacion, proveedores, function(){
						jAlert('Información Grabada Correctamente','Información');
						$('#resultados').flexReload();
						$('#resultadosAsignarProveedor').clean();
						$('#mantenedorProovedor').dialog('close');
					});
				 }});
			}
		});
	};
	
	var buscadorAsignacion = function(){
		$('#mantenedorStecnicoForm').reset();
		$('#resultadosAsignar').clean();
		$('#mantenedorStecnico').dialog('open');
	 };
		
	var buscadorAsignacionProveedor = function(){
		$('#mantenedorProveedorForm').reset();
		$('#resultadosAsignarProveedor').clean();
		$('#mantenedorProovedor').dialog('open');
	 };
	 
	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listDestinosUbicacion, 
		dwrFunctionListAllId:SSTFacade.listAllDestinosUbicacionCheck,
		saveCheckValues:true,
		checkAllButton:true,
		tipo: 'AUB',
		seccion: 90007000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		overrideModel: [
          {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
          {findName:'checkMarca', propiedad:function(o){
					 var check = $('<input type="checkbox">')
			 			.attr('id', 'chk' + o.id)
			 			.attr('name', 'chk' + o.id)
			 			.attr('checked',$('#resultados').isRowChecked(o.id))
		 				.attr('onchange','changeState("resultados", ' + o.id + ',this)');
					 return check;
 			}
          }
          
        ],
		buttons : [
            {name: 'Buscar Servicio Tecnico', bclass: 'btnPlus', onpress : function(){buscadorAsignacion();}},
	   		{separator: true},
            {name: 'Buscar Proveedor', bclass: 'btnPlus', onpress : function(){buscadorAsignacionProveedor();}},
	   		{separator: true},
            {name: 'Eliminar Destino', bclass: 'btnRechazar', onpress : function(){eliminarAsignacionUbicacion();}},
	   		{separator: true}
		],
	});	
	
	$("#resultados").loadData([{idUbicacion:idUbicacion}]);
	
	$('#resultadosAsignar').flexigrid({
		dwrFunction:SSTFacade.listServicioTecnico,
		dwrFunctionListAllId:SSTFacade.listAllServiciosTecnicosCheck,
		saveCheckValues:true,
		checkAllButton:true,
		tipo: 'ASUB',
		seccion: 1000020000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		overrideModel: [
			{findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
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
            {name: 'Asignar selección a la ubicación', bclass: 'btnPlus', onpress : function(){asignaServicioTecnicoaUbicacion();}},
            {separator: true}
		],
	});	
	
	$('#resultadosAsignarProveedor').flexigrid({
		dwrFunction:SSTFacade.listProveedoresByFilter,
		dwrFunctionListAllId:SSTFacade.listAllProveedoresCheck,
		saveCheckValues:true,
		checkAllButton:true,
		tipo: 'ASP', // nuevas columnas para proveedor
		seccion: 1000020000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		overrideModel: [
			{findName:'checkMarca', propiedad:function(o){
				 var check = $('<input type="checkbox">')
		 			.attr('id', 'chk' + o.id)
		 			.attr('name', 'chk' + o.id)
		 			.attr('checked',$('#resultadosAsignarProveedor').isRowChecked(o.id))
	 				.attr('onchange','changeState("resultadosAsignarProveedor", ' + o.id + ',this)'); 
				 return check; 
			}
		}],  
		buttons : [
            {name: 'Asignar selección a la ubicación', bclass: 'btnPlus', onpress : function(){asignaProveedorUbicacion();}},
            {separator: true}
		],
	});	

	$('#mantenedorStecnico').dialog({
		autoOpen: false,
		modal:true,
		width:920,
		buttons:{
			Cerrar: function(){
				$('#mantenedorStecnico').dialog('close');
			}
		}
	});
	
	$('#mantenedorProovedor').dialog({
		autoOpen: false,
		modal:true,
		width:920,
		buttons:{
			Cerrar: function(){
				$('#mantenedorProovedor').dialog('close');
			}
		}
	});
	
});


var changeStateServiciotecnico = function(id,state) {
	SSTFacade.updateServicioTecByubicacion({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el perfil correctamente','Información',function(){
			$('#resultados').flexReload();
		});
	}});
};


