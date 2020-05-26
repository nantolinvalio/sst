$(document).ready(function(){
	var idPr = $("#idProducto").val();
	var idDo = $('#idDocumento').val();
	var tiDo = $('#tipoDocumento').val();
	
	var ot = {};
	ot.idDocumento = idDo;
	ot.tipoDocumento = tiDo;
	
	var cd={};
	SSTFacade.getCentroDistribucion({async:false,callback:function(CD){
		$('#isCentroDistribucion').val(CD.id);
		cd=CD;
	}});
	
	var moduloGProveedor;
	SSTFacade.getModuloByNombreUsuario('onsucursalgarantiaproveedor',{async:false, callback:function(modulo) {
		moduloGProveedor = modulo ? modulo : undefined;
	}});
	
	var moduloTipoFalla;
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloTipoFalla = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getDocumentoByIdAndTipo({tipo:tiDo,id:idDo},{async:true,callback:function(documento){
		if(documento!=null){
			$('#datosServicio').loadObject(documento);
			SSTFacade.getProductoById(idPr,{async:true,callback:function(producto){
				if(producto !=null){
					$('#producto\\.id').text(producto.id);
					$('#producto\\.descripcion').text(producto.descripcion);
					$('#marca').text(producto.marca.nombre);
					ot.producto = producto;
				}
			}});
			ot.fechaEmision=documento.fechaEmision;
		} else {
			jAlert('Nose ha encontrado la '+$('#tipoDocumento').val()+' número '+$('#idDocumento').val());
		}
	}});

	var serviciosTecnicosCD=[];
	var serviciosTecnicosSC=[];
	
	var listServiciossTecnicos = function(){
		$('#idDestino').find('option').remove().end();
		if($('#isCentroDistribucion').is(':checked')){
			$('#idDestino').addItems(serviciosTecnicosCD,'id',['tipoGlosa','glosa','direccion'],true,' : ');
		} else {
			$('#idDestino').addItems(serviciosTecnicosSC,'id',['tipoGlosa','glosa','direccion'],true,' : ');
		}
	};
	
	SSTFacade.listSTecnicoByFilter({idProducto:idPr,tipoOT:'GM',idOrigen:cd.id},{async:false,callback:function(sTecnicos){
		serviciosTecnicosCD = sTecnicos;
	}});
	
	SSTFacade.listSTecnicoYBodegasByFilter({idProducto:idPr,tipoOT:'GM'},{async:false,callback:function(sTecnicos){
		serviciosTecnicosSC = sTecnicos;
	}});
	
	listServiciossTecnicos();
	
	$('#isCentroDistribucion').change(function(){
		listServiciossTecnicos();
	});
	
	$('#crearOT').click(function(){
		if ($('#serviciosTecnicosLocales').valid()) {
			ot.numeroAtencion=$('#numeroAtencion').val();
			ot.idDestino=$('#idDestino').val();
			ot.tipo = {codigo:'GM'};
			
			SSTFacade.saveOTGM(ot,{async:true,callback:function(ordenTrabajo){
				parent.location = context +'/index.do?e=' + moduloTipoFalla.codigo + '&m=' + moduloInicial.codigo + '&idOT='+ordenTrabajo.id;
			}});
		}
	});
	
	$('#volver').click(function(){
		var params ='idProducto='+idPr+'&idDocumento='+idDo+'&tipoDocumento='+tiDo;
		parent.location = context +'/index.do?e='+moduloGProveedor.codigo+'&m='+moduloInicial.codigo + '&' + params;
	});
});