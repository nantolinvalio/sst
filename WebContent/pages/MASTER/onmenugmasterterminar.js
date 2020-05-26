$(document).ready(function(){
	var idOT = $('#idOT').val();
	var accesoriosAux;
	$('#imprimir').attr('disabled',true);
	
	SSTFacade.getOTById(idOT,{async:false,callback:function(ordenTrabajo){
		SSTFacade.getClienteByOT(idOT,{async:false,callback:function(cliente){
			ordenTrabajo.cliente = cliente;
		}});
		$('#ordenTrabajo').loadObject(ordenTrabajo);	
		SSTFacade.getServicioTecnicoByOT(idOT,{async:false,callback:function(sTecnico){
			if (sTecnico != null) {
				$('#sTecnico').text(sTecnico.glosa + ", " + sTecnico.direccion);				
			}
		}});
	}});
	
//	SSTFacade.getClienteByOT(idOT,{async:false,callback:function(cliente){
//		$('#ordenTrabajo').loadObject(cliente);
//	}});
	
	$("#accesoriosOT").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onToggleCol:false,
		onDragCol: false,
		title: "Accesorios",
		colModel : [
            {display: 'Descripción Accesorio',width:500,align:'left',name:'descripcion'},
            {display: 'Ubicación',width:200,align:'left' ,name:'ubicacion.nombre'},
            {display: 'Código de Barras',width:120,align:'right' ,name:'codigoBarra',name_abbr:'codigoBarra'},
		]
	});
	
	$('#observaciones').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 128, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(128), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
	
	SSTFacade.listAccesoriosByOT(idOT,{async:false,callback:function(accesorios){
		accesoriosAux = accesorios;
		if (accesorios && accesorios.length != 0) {
			$("#accesoriosOT").parent().parent().show();
			$("#accesoriosOT").flexAddData({rows:accesorios,total:accesorios.length});				
		} else {
			$("#divAccesorios").hide();
		}
	}});

	$('#cancelar').click(function(){
		SSTFacade.updateDesactivarOT({id:idOT,motivoDesactivacion:$('#observaciones').val()},{asyn:true,callback:function(data){
			jAlert('Se cancela creacion de OT de garantía Master"');
		}});
	});
	
	$('#imprimir').click(function(){
		$('#imprimir').click( function(){
			var url = "/sst/ViewReportServlet?type=pdf" + 
			"&report=OrdenTrabajoDetalleReport" +
			"&idOT=" + idOT;
			$.openWindowsMenubar(url, "OrdenTrabajoDetalle", 600, 800);
		});
	});
	
	$('#grabar').click(function(){
		var ubicacion={
				id:$('#sucursal\\.id').text(),
		};
		SSTFacade.saveOtTerminarGarantiaMaster(idOT,accesoriosAux,ubicacion,{async:false,callback:function(){
			jAlert('Ahora puede imprimir la orden de trabajo');
			$('#grabar').attr('disabled',true); 
			$('#cancelar').attr('disabled',true); 
			$('#imprimir').removeAttr('disabled');
		}});
	});
	
	
});

