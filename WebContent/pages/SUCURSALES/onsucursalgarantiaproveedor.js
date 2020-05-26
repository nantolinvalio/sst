$(document).ready(function(){
	var idProducto = $("#idProducto").val();
	var idDocumento = $('#idDocumento').val();
	var tipoDocumento = $('#tipoDocumento').val();
	
	var ot = {};
	ot.idDocumento = idDocumento;
	ot.tipoDocumento=tipoDocumento;

	var moduloSTecnico;
	SSTFacade.getModuloByNombreUsuario('onsucursalserviciostecnicos',{async:false, callback:function(modulo) {
		moduloSTecnico = modulo ? modulo : undefined;
	}});
	
	var moduloHistorial;
	SSTFacade.getModuloByNombreUsuario('onsucursalgarantiahistorial',{async:false, callback:function(modulo) {
		moduloHistorial = modulo ? modulo : undefined;
	}});

	var moduloBuscar;
	SSTFacade.getModuloByNombreUsuario('onsucursalbuscardocumento',{async:false, callback:function(modulo) {
		moduloBuscar = modulo ? modulo : undefined;
	}});

	var moduloFallas;
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;
	}});

	var moduloGMaster;
	SSTFacade.getModuloByNombreUsuario('onsucursalgarantiamaster',{async:false, callback:function(modulo) {
		moduloGMaster = modulo ? modulo : undefined;
	}});
	
	$('#procedimientoPopup').dialog({
		autoOpen: false,
		modal:true,
		width:700,
		buttons:{
			Cerrar: function(){
				$('#procedimientoPopup').dialog('close');
			}
		}
	});
	
	SSTFacade.getDocumentoByIdAndTipo({tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(documento){
		if(documento!=null){
			$('#datosServicio').loadObject(documento);
			SSTFacade.getProductoById(idProducto,{async:true,callback:function(producto){
				if(producto != null){
					$('#producto\\.id').text(producto.id);
					$('#producto\\.descripcion').text(producto.descripcion);
					$('#marca').text(producto.marca.nombre);
					
					$('#visitaN').attr('checked',true);
					ot.producto = producto;
				}
			}});
			ot.fechaEmision = documento.fechaEmision;
		}
	}});
	
	SSTFacade.getCrearOTGP({idProducto:idProducto,tipoOT:'GP'},{idDocumento:idDocumento,tipoDocumento:tipoDocumento,idProducto:idProducto},{tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(crearOTGP){
		ot.fechaVencimiento = crearOTGP.fechaVencimiento;
		if(!crearOTGP.consultarHistorial){
			$('#consultarHistorial').attr('disabled','');
		}
		
		if(!crearOTGP.consultarST){
			$('#consultarST').attr('disabled','');
		}
		if(!crearOTGP.procedimiento) {
			$('#consultarProcedimiento').attr('disabled','');
		} else {
			$('#procedimiento').text(crearOTGP.procedimiento.procedimiento);
		}
		if(crearOTGP.consultarGP){
			$('#gPTituloH2').html('<font color=\"blue\">Garantía de proveedor Activa</font>');
			$('#gPTituloForm').text('Garantía proveedor - Estado Activa');
			$('#consultarGM').attr('disabled','');
			$('#garantiaProveedorDesactivada').css('display','none');
			$('#garantiaProveedor').loadObject(crearOTGP);
		} else {
			$('#gPTituloH2').html('<font color=\"red\">Garantía de proveedor Vencida</font>');
			$('#gPTituloForm').text('Garantía proveedor - Estado Vencida');
			$('#crearOT').attr('disabled',true);
			$('#garantiaProveedor').css('display','none');
			$('#garantiaProveedorDesactivada').loadObject(crearOTGP);
		}
		
		SSTFacade.listSTecnicoYBodegasByFilter({idProducto:idProducto,tipoOT:'GP'},{async:true,callback:function(sTecnicos){
			if(sTecnicos!=null && sTecnicos.length > 0){
				listaDestinos = new Array;
				$.each(sTecnicos,function(){
					var destino = {};
					destino.id=arguments[1].id;
					destino.glosa = (arguments[1].tipo=='ST'?'Servicio Técnico: ': 'Centro de Distribución: ');
					destino.glosa+= arguments[1].glosa+' '+arguments[1].direccion+' '+arguments[1].comuna.glosa;
					listaDestinos.push(destino);
				});
				$('#idDestino').addItems(listaDestinos,'id','glosa',false);
			} else {
				$('#idDestino').append('<option>No hay servicios tecnicos asignados</option>');
			}
		}});
	}});
	
	$('#consultarHistorial').click(function(){
		var params ='idProducto='+idProducto+'&idDocumento='+idDocumento+'&tipoDocumento='+tipoDocumento;
		parent.location= context + "/index.do?e=" + moduloHistorial.codigo+"&m="+moduloInicial.codigo+"&"+params;
	});

	$('#consultarST').click(function(){
		var params ='idProducto='+idProducto+'&idDocumento='+idDocumento+'&tipoDocumento='+tipoDocumento;
		parent.location= context + "/index.do?e=" + moduloSTecnico.codigo+"&m="+moduloInicial.codigo+"&"+params;
	});
	
	$('#consultarProcedimiento').click(function(){
		$('#procedimientoPopup').dialog('open');
	});
	
	$('#volver').click(function(){
		parent.location= context + "/index.do?e=" + moduloBuscar.codigo+"&m="+moduloInicial.codigo;
	});
	
	$('#crearOT').click(function(){
		ot.tipo = {codigo : $('input[name="visita"]:checked').val()};
		if(ot.tipo.codigo == 'GP'){
			ot.idDestino=$('#idDestino').val();
		}		
		SSTFacade.saveOTGP(ot,{async:true,callback:function(oT){
			parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
		}});
	});
	
	$('#visitaS').change(function(){
		$('#idDestino').attr('disabled',true);
	});
	
	$('#visitaN').change(function(){
		$('#idDestino').attr('disabled',false);
	});
	
	$('#consultarGM').click(function(){
		var params ='idProducto='+idProducto+'&idDocumento='+idDocumento+'&tipoDocumento='+tipoDocumento;
		parent.location= context + "/index.do?e=" + moduloGMaster.codigo+"&m="+moduloInicial.codigo+"&"+params;
	});
});