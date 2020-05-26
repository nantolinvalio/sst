var initonmenuordentrabajocerrar = function() {
	$('#btnCerrarOT').click(function(){
		if($('#cerrarOT').valid()){
			ordenTrabajo.motivoCierre = $('#motivoCierre').val();
			SSTFacade.updateCerrarOT(ordenTrabajo,{aync:true,callback:function(){
				jAlert('OT Cerrada','Información',function(){
					$('#btnCerrarOT').attr("disabled","disabled");
					$('#motivoCierre').attr("readOnly","readOnly");
				});	
			}});
		}
	});
	
	$('#motivoCierre').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 120, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(120), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
};

var loadonmenuordentrabajocerrar = function(ordenTrabajo) {
	if(ordenTrabajo.cerrada == true) {
		$('#btnCerrarOT').attr("disabled","disabled");
		$('#motivoCierre').val(ordenTrabajo.motivoCierre);
		$('#motivoCierre').attr("readOnly","readOnly");
	}
};
