var initonmenuordentrabajodesactivar = function() {
	$('#btnDesactivarOT').click(function(){
		if($('#desactivarOT').valid()){
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro de desactivar la orden de trabajo?', 'Confirmación', function(r){
				if(r){
					SSTFacade.updateDesactivarOT({id:ordenTrabajo.id,motivoDesactivacion:$('#motivoDesactivacion').val()},{asyn:true,callback:function(data){
						$.alerts.okButton = '&nbsp;Ok&nbsp;';
						jAlert('Se ha desactivado la Orden de Trabajo','Información',function(){
							$('#motivoDesactivacion').attr('readonly','readonly');
							$('#btnDesactivarOT').attr('disabled','disabled');
						});
					}});
				}
			});
		}
	});
	
	$('#motivoDesactivacion').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 127, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(128), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
};

var loadonmenuordentrabajodesactivar = function(ordenTrabajo) {
	if(ordenTrabajo.motivoDesactivacion != null && ordenTrabajo.motivoDesactivacion.length > 0){
		$('#motivoDesactivacion').attr('readonly','readonly');
		$('#btnDesactivarOT').attr('disabled','disabled');
		$('#motivoDesactivacion').val(ordenTrabajo.motivoDesactivacion);
	}
};
