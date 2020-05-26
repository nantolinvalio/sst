$(document).ready(function(){
	var idProcedimiento = $('#idProcedimiento').val();
	
	SSTFacade.getModuloByNombreUsuario('onmenugrabarinfoadicionalst',{async:false, callback:function(modulo) {
		moduloProcedimiento = modulo ? modulo : undefined;
	}});
	
	$("#tabs").tabs();
	
	SSTFacade.listAllLineas({async:false,callback:function(data){
		$('#linea\\.codigo').addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listMarca({async:false,callback:function(data){
		$('#marca\\.codigo').addItems(data, "codigo", "nombre", true);
	}});
	
	if (idProcedimiento != "null"){
		$('#titlePage').text('EDITAR PROCEDIMIENTO');
		SSTFacade.getProcedimientoById(idProcedimiento, {async:false,callback:function(proc){
			if (proc.producto == null) {
				$('#procedimientoLineaMarca').loadObject(proc);
				$('#onmenutipoFallasProductosTab').hide();
				$('#marca\\.codigo').attr('disabled',true);
				$('#linea\\.codigo').attr('disabled',true);
			} else {
				$("#tabs").tabs('select', 1);	 
				$('#procedimientoCodigoDescripcion').loadObject(proc);
				$('#onmenutipoFallasFamiliaTab').hide();
				$('#producto\\.id').attr('readonly',true);
				$('#BuscarCodigoDescripcion').attr('disabled',true);
			}
		}});
	}
	
	SSTFacade.getUsuarioSession({async:false, callback:function(usuario){
		usuarioActual = usuario.id;
	}});
	
	$('#grabarLineaMarca').click(function(){
		grabarLineaMarca();
	});
	
	var grabarLineaMarca = function(){
		if($('#procedimientoLineaMarca').valid()) {
			var filter = $('#procedimientoLineaMarca').serializeObject();
			SSTFacade.saveProcedimiento(filter,{async:false,callback:function(proc){
				jAlert('Procedimiento Guardado correctamente','Información',function(){
					parent.location = context +'/index.do?e=' + moduloProcedimiento.codigo + '&m=' + moduloInicial.codigo + '&idProcedimiento=' + proc.id;					
				});
			}});
		}
	};
	
	$('#grabarCodigoDescripcion').click(function(){
		grabarCodigoDescripcion();
	});
	
	var grabarCodigoDescripcion = function(){
		if($('#procedimientoCodigoDescripcion').valid()) {
			var procedimiento = $('#procedimientoCodigoDescripcion').serializeObject();
			SSTFacade.saveProcedimiento(procedimiento,{async:false,callback:function(proc){
				jAlert('Procedimiento Guardado correctamente','Información',function(){
					parent.location = context +'/index.do?e=' + moduloProcedimiento.codigo + '&m=' + moduloInicial.codigo + '&idProcedimiento=' + proc.id;
				});
			}});
		}
	};
	
	$('#BuscarCodigoDescripcion').click(function(){
		buscar();
	});
	
	var buscar = function(){
		if($('#producto\\.id').valid()) {
			var id = $('#producto\\.id').val();
			SSTFacade.getProductoById(id,{async:false,callback:function(pr){
				if(pr != null){
					$('#procedimientoCodigoDescripcion').loadObject(pr);
					$('#procedimientoCodigoDescripcion').find('#id').val('');
				} else {
					jAlert('Producto no existe','Información');
					$('#procedimientoCodigoDescripcion').reset();
					$('#marca\\.nombre, #familia\\.linea\\.glosa').text('');
				}
			}});
		}
	};
	
	$('#descripcion').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 2000, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "status", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(2000), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	  });
});