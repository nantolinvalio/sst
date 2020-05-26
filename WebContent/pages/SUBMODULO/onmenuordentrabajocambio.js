var initonmenuordentrabajocambio = function() {
	
	comprobar(document.getElementById('sinProductoFisico'));
	desactivaBtnCambioAutorizado();
	
	$('#motivoCambio').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 255, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(256), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
	
	$('#autorizarCambio').click(function(){
		if($('#cambio').valid()){
			var cambio = {
				ot : ordenTrabajo,
				motivoCambio : $('#motivoCambio').val(),
				numeroCambio : 0,
				tipoFacturar:$("#idFacturar option:selected").attr('tipoFacturar')
			};
			cambio.ot.empresaFacturar = {id:$('#idFacturar').val()};
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			if(ordenTrabajo.tipo.glosa == 'GM' && cambio.tipoFacturar == "Proveedor"){
				jAlert('Una OT de garantía Master solo se puede facturar a un transportista o asumir como perdida.');
			} else {
				jConfirm('¿Esta seguro de autorizar el cambio del producto?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.autorizarCambio(cambio,{async:true,callback:function(){
							$.alerts.okButton = '&nbsp;Ok&nbsp;';
							jAlert('Se ha autorizado el cambio del producto','Información',function(){
								$('#autorizarCambio').attr('disabled',true);
							});
						}});	
					}
				});
			}
		}
	});
	
	$('#autorizarCambioBodega').click(function(){
		if($('#cambio').valid()){
			var cambio = {
				ot : ordenTrabajo,
				motivoCambio : $('#motivoCambio').val(),
				numeroCambio : 0,
				tipoFacturar:$("#idFacturar option:selected").attr('tipoFacturar')
			};
			cambio.ot.empresaFacturar = {id:$('#idFacturar').val()};
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			if(ordenTrabajo.tipo.glosa == 'GM' && cambio.tipoFacturar == "Proveedor"){
				jAlert('Una OT de garantía Master solo se puede facturar a un transportista o asumir como perdida.');
			} else {
				jConfirm('¿Esta seguro de autorizar el cambio del producto?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.autorizarCambioBodega(cambio,{async:true,callback:function(){
							$.alerts.okButton = '&nbsp;Ok&nbsp;';
							jAlert('Se ha autorizado el cambio del producto','Información',function(){
								$('#autorizarCambioBodega').attr('disabled',true);
								$('#autorizarCambioProveedor').attr('disabled',true);
								$('#autorizarCambioTransporte').attr('disabled',true);
								$('#sinProductoFisico').attr('disabled',true);
							});
						}});	
					}
				});
			}
		}
	});
	
	$('#autorizarCambioProveedor').click(function(){
		if($('#cambio').valid()){
			var cambio = {
				ot : ordenTrabajo,
				motivoCambio : $('#motivoCambio').val(),
				numeroCambio : 0,
				tipoFacturar:$("#idFacturar option:selected").attr('tipoFacturar')
			};
			cambio.ot.empresaFacturar = {id:$('#idFacturar').val()};
			
			if(document.getElementById('sinProductoFisico').checked){
				cambio.ot.conOsinProductoFisico = "S";
			}else{
				cambio.ot.conOsinProductoFisico = "N";
			}
			
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			if(ordenTrabajo.tipo.glosa == 'GM' && cambio.tipoFacturar == "Proveedor"){
				jAlert('Una OT de garantía Master solo se puede facturar a un transportista o asumir como perdida.');
			} else {
				jConfirm('¿Esta seguro de autorizar el cambio del producto?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.autorizarCambioProveedor(cambio,{async:true,callback:function(){
							$.alerts.okButton = '&nbsp;Ok&nbsp;';
							jAlert('Se ha autorizado el cambio del producto','Información',function(){
								$('#autorizarCambioProveedor').attr('disabled',true);
								$('#autorizarCambioBodega').attr('disabled',true);
								$('#autorizarCambioTransporte').attr('disabled',true);
								$('#sinProductoFisico').attr('disabled',true);
							});
						}});	
					}
				});
			}
		}
	});
	
	$('#autorizarCambioTransporte').click(function(){
		if($('#cambio').valid()){
			var cambio = {
				ot : ordenTrabajo,
				motivoCambio : $('#motivoCambio').val(),
				numeroCambio : 0,
				tipoFacturar:$("#idFacturar option:selected").attr('tipoFacturar')
			};
			cambio.ot.empresaFacturar = {id:$('#idFacturar').val()};

			if(document.getElementById('sinProductoFisico').checked){
				cambio.ot.conOsinProductoFisico = "S";
			}else{
				cambio.ot.conOsinProductoFisico = "N";
			}
			
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			if(ordenTrabajo.tipo.glosa == 'GM' && cambio.tipoFacturar == "Proveedor"){
				jAlert('Una OT de garantía Master solo se puede facturar a un transportista o asumir como perdida.');
			} else {
				jConfirm('¿Esta seguro de autorizar el cambio del producto?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.autorizarCambioTransporte(cambio,{async:true,callback:function(){
							$.alerts.okButton = '&nbsp;Ok&nbsp;';
							jAlert('Se ha autorizado el cambio del producto','Información',function(){
								$('#autorizarCambioTransporte').attr('disabled',true);
								$('#autorizarCambioBodega').attr('disabled',true);
								$('#autorizarCambioProveedor').attr('disabled',true);
								$('#sinProductoFisico').attr('disabled',true);
							});
						}});	
					}
				});
			}
		}
	});
};

var loadonmenuordentrabajocambio = function(ordenTrabajo) {
	SSTFacade.listProveedores({async:false,callback:function(proveedores){
		var listProv = [];
		$.each(proveedores,function(i,proveedor){
			proveedor.glosa = "Facturar al proveedor "+arguments[1].nombre + ", y enviar el producto";
		});
		$('#idFacturar').addItems(proveedores,"id", "glosa", true," ",[{attr:"tipoFacturar",valor:"Proveedor"}]);
	}});
	
	SSTFacade.listTransportista({async:false,callback:function(transportistas){
		$.each(transportistas,function(i,transportista ){
			transportista.nombreCompleto = "Facturar al transportista " + arguments[1].nombreCompleto + ",y entregar el producto";
		});
		$('#idFacturar').addItems(transportistas,"id","nombreCompleto",false," ",[{attr:"tipoFacturar",valor:"Transportista"}]);
	}});
	
	var tiendas = [];
	if(ordenTrabajo.tipo.codigo != null && ordenTrabajo.tipo.codigo == "GM"){
		var estado = {
			id:	89772300,
			nombre: 'Facturar a Servicios Estado S.A y enviar el producto a remate'
		};
		tiendas.push(estado);
	}
	
	var abcdin = {
		id:	82982300,
		nombre: 'Asumir perdida y enviar a remate'
	};
	
	tiendas.push(abcdin);
	
	$('#idFacturar').addItems(tiendas,"id","nombre",false," ",[{attr:"tipoFacturar",valor:"Tienda"}]);

	if (ordenTrabajo.empresaFacturar != null) {
		$('#idFacturar').val(ordenTrabajo.empresaFacturar.id);
	}
	if (ordenTrabajo.cambio != null) {
		$('#motivoCambio').val(ordenTrabajo.cambio.motivoCambio);
	}
	
	if(ordenTrabajo.cambioAutorizado){
		$('#autorizarCambio').attr('disabled',true);
	} else {
		$('#autorizarCambio').attr('disabled',false);
	}
};

/**
 * Funcion que habilita o deshabilita los botones de acuerdo al flujo desde
 * donde se accede a la OT
 * 
 * @param obj
 */
function comprobar(obj){

		// Cuando se entra por el flujo de Certificacion de falla
	if (obj.checked && ordenTrabajo.tipo.codigo == 'GPC') {
		$("#autorizarCambioBodega").attr('disabled', true);
		$("#autorizarCambioProveedor").attr('disabled', false);
		$("#autorizarCambioTransporte").attr('disabled', false);
	} else if (!obj.checked && ordenTrabajo.tipo.codigo == 'GPC') {
		$("#autorizarCambioBodega").attr('disabled', false);
		$("#autorizarCambioProveedor").attr('disabled', true);
		$("#autorizarCambioTransporte").attr('disabled', false);

	} else {

		if (obj.checked) {
			document.getElementById('autorizarCambioTransporte').disabled = false;
			document.getElementById('autorizarCambioBodega').disabled = true;
		} else {
			document.getElementById('autorizarCambioTransporte').disabled = true;
			document.getElementById('autorizarCambioBodega').disabled = false;
		}
	}
}

function desactivaBtnCambioAutorizado(){
	//jAlert(ordenTrabajo.cambioAutorizado);
	if(ordenTrabajo.cambioAutorizado == true){
		$('#sinProductoFisico').attr('disabled',true);
		$("#autorizarCambioBodega").attr('disabled', true);
		$("#autorizarCambioProveedor").attr('disabled', true);
		$("#autorizarCambioTransporte").attr('disabled', true);
	}
}
