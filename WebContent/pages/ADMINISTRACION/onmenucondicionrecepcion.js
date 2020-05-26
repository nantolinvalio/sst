$(document).ready(function() {
	
	SSTFacade.getCondicionRecepcion({async:true,callback:function(data){
		if (data != null){
			$('#descripcion').text(data.descripcion);
		}		
	}});
	
	$("#guardar").click(function(){
		if ($('#descripcion').valid()) {
			SSTFacade.saveCondicionRecepcion($('#descripcion').val(),{async:false,callback:function(){	
				jAlert('Condición Grabada', 'Informacion');
			}});	
		}	
	});
	
	$("#imprimir").click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=CondicionRecepcionReport";
		$.openWindowsMenubar(url, "CondicionRecepcion", 600, 800);
	});
	

	
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
