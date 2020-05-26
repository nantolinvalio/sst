var initonmenuordentrabajostecnico = function(){
	
	$('#enviadoST').find('#ot\\.diagnostico').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 254, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "notification",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(256), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	});
	
	$('#sTecnico').find('#guardarSTecnico').click(function(){
		if($('#sTecnico').valid()){
			var ot = {
				contratoEmitido : !$('#sTecnico').find('#ot\\.contratoEmitido').is(':checked'),
				numeroContrato : $('#sTecnico').find('#ot\\.numeroContrato').val(),
				diagnostico : $('#sTecnico').find('#ot\\.diagnostico').val(),
				id : ordenTrabajo.id		
			};
			SSTFacade.saveSTecnicoOT(ot,{async:true,callback:function(){
				$.alerts.okButton = '&nbsp;Ok&nbsp;';
				jAlert('Se ha guardado el contrato correctamente','Información');
			}});
		}
	});
	
	$('#noEnviadoST').find('#asignarSTecnico').click(function(){
		if ($('#asignarSTecnicoForm').valid()) {
			var sTecnico = {
				ot: ordenTrabajo,
				id: $('#asignarSTecnicoForm').find('#serviciosTecnico').val()
			};
			SSTFacade.asignarServicioTecnico(sTecnico,{async:true,callback:function(){
				jAlert('Servicio Técnico Asignado','Aviso');
				SSTFacade.getServicioTecnicoByOT(ordenTrabajo.id,{async:false,callback:function(stecnico){
					if(stecnico != null){
						$('#noEnviadoST').hide();
						$('#enviadoST').show();
						stecnico.ot.contratoEmitido = !stecnico.ot.contratoEmitido;
						$('#sTecnico').loadObject(stecnico);
						$('#sTecnico').find('#ot\\.numeroContrato').attr('readonly', !ordenTrabajo.contratoEmitido);
					}
				}});
			}});
		}
	});
	
	var loadComunasST = function(idRegion) {
		$('#noEnviadoST').find('#comunaSTecnico').empty();
		$('#noEnviadoST').find('#serviciosTecnico').empty();
		$('#noEnviadoST').find('#serviciosTecnico').addItems([],"id","glosa",true);
		SSTFacade.listComunasConSTByRegion(idRegion,{async:false,callback:function(comunas){
			$('#noEnviadoST').find('#comunaSTecnico').addItems(comunas,"id","glosa",true);
		}});
	};
	
	var loadSTByComuna = function(idRegion) {
		$('#noEnviadoST').find('#serviciosTecnico').empty();
		SSTFacade.listServicioTecnicoByComuna({idComuna:idRegion},{async:false,callback:function(stecnicos){
			$('#noEnviadoST').find('#serviciosTecnico').addItems(stecnicos,"id","nombre",true);
		}});
	};

	$('#noEnviadoST').find("#regionSTecnico").change(function(){
		loadComunasST($(this).val());
	});
	
	$('#noEnviadoST').find("#comunaSTecnico").change(function(idComuna){
		loadSTByComuna($(this).val());
	});
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#noEnviadoST').find('#regionSTecnico').addItems(data, "id", "glosa", true);
	}});

	$('#noEnviadoST').find('#comunaSTecnico').addItems([],"id","glosa",true);
	$('#noEnviadoST').find('#serviciosTecnico').addItems([],"id","glosa",true);
	
	$('#enviadoST').find('#ot\\.contratoEmitido').click(function(){
		$('#enviadoST').find('#ot\\.numeroContrato').val(this.checked ? $('#enviadoST').find('#sTecnico').find('#guia\\.numero').text() : '' );
		$('#enviadoST').find('#ot\\.numeroContrato').attr('readOnly',this.checked);
	});
};

var loadonmenuordentrabajostecnico = function(ordenTrabajo) {
	SSTFacade.getServicioTecnicoByOT(ordenTrabajo.id,{async:false,callback:function(stecnico){
		if(stecnico != null){
			$('#noEnviadoST').hide();
			$('#enviadoST').show();
			stecnico.ot.contratoEmitido = !stecnico.ot.contratoEmitido;
			$('#sTecnico').loadObject(stecnico);
			$('#sTecnico').find('#ot\\.numeroContrato').attr('readonly', !ordenTrabajo.contratoEmitido);
		} else {
			$('#noEnviadoST').show();
			$('#enviadoST').hide();
		}
	}});
};