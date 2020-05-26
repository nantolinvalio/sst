var initonmenuotstecnicocompleto = function() {
	
	var loadComunasSTCompleto = function(idRegion) {
		$('#noEnviadoSTCompleto').find('#comunaSTecnico').empty();
		$('#noEnviadoSTCompleto').find('#serviciosTecnico').empty();
		$('#noEnviadoSTCompleto').find('#serviciosTecnico').addItems([],"id","glosa",true);
		SSTFacade.listComunasConSTByRegion(idRegion,{async:false,callback:function(comunas){
			$('#noEnviadoSTCompleto').find('#comunaSTecnico').addItems(comunas,"id","glosa",true);
		}});
	};
	var loadSTByComunaCompleto = function(idRegion) {
		$('#noEnviadoSTCompleto').find('#serviciosTecnico').empty();
		SSTFacade.listServicioTecnicoByComuna({idComuna:idRegion},{async:false,callback:function(stecnicos){
			$('#noEnviadoSTCompleto').find('#serviciosTecnico').addItems(stecnicos,"id","nombre",true);
		}});
	};
	$('#noEnviadoSTCompleto').find("#regionSTecnico").change(function(){
		loadComunasSTCompleto($(this).val());
	});
	
	$('#noEnviadoSTCompleto').find("#comunaSTecnico").change(function(idComuna){
		loadSTByComunaCompleto($(this).val());
	});
	
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#noEnviadoSTCompleto').find('#regionSTecnico').addItems(data, "id", "glosa", true);
	}});

	$('#noEnviadoSTCompleto').find('#comunaSTecnico').addItems([],"id","glosa",true);
	$('#noEnviadoSTCompleto').find('#serviciosTecnico').addItems([],"id","glosa",true);
	
	$('#noEnviadoSTCompleto').hide();
	$('#enviadoSTCompleto').hide();
	
	$('#enviadoSTCompleto').find('#ot\\.contratoEmitido').click(function(){
		$('#enviadoSTCompleto').find('#ot\\.numeroContrato').val(this.checked ? $('#enviadoSTCompleto').find('#sTecnicoCompleto').find('#guia\\.numero').text() : '' );
		$('#enviadoSTCompleto').find('#ot\\.numeroContrato').attr('readOnly',this.checked);
	});
	
	$('#enviadoSTCompleto').find('#ot\\.diagnostico').maxlength({  
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
		
	$('#sTecnicoCompleto').find('#guardarSTecnico').click(function(){
		if($('#sTecnicoCompleto').valid()){
			var ot = {
				contratoEmitido : !$('#sTecnicoCompleto').find('#ot\\.contratoEmitido').is(':checked'),
				numeroContrato : $('#sTecnicoCompleto').find('#ot\\.numeroContrato').val(),
				diagnostico : $('#sTecnicoCompleto').find('#ot\\.diagnostico').val(),
				id : ordenTrabajo.id,
				calificaFR: !$('#sTecnicoCompleto').find('#ot\\.calificaFR').is(':checked'),		
				excluyeCCalidad : $('#sTecnicoCompleto').find('#ot\\.excluyeCCalidad').is(':checked')
			};
			SSTFacade.saveSTecnicoOT(ot,{async:true,callback:function(){
				$.alerts.okButton = '&nbsp;Ok&nbsp;';
				jAlert('Se ha guardado el contrato correctamente','Información');
			}});
		}
	});
	
	$('#noEnviadoSTCompleto').find('#asignarSTecnico').click(function(){
		if ($('#asignarSTecnicoFormCompleto').valid()) {
			var sTecnico = {
				ot:ordenTrabajo,
				id: $('#asignarSTecnicoFormCompleto').find('#serviciosTecnico').val()
			};
			SSTFacade.asignarServicioTecnico(sTecnico,{async:true,callback:function(){
				jAlert('Servicio Técnico Asignado','Aviso');
				SSTFacade.getServicioTecnicoByOT(ordenTrabajo.id,{async:false,callback:function(stecnico){
					if(stecnico != null){
						$('#noEnviadoSTCompleto').hide();
						$('#enviadoSTCompleto').show();
						stecnico.ot.contratoEmitido = !stecnico.ot.contratoEmitido;
						$('#sTecnicoCompleto').loadObject(stecnico);
						$('#sTecnicoCompleto').find('#ot\\.numeroContrato').attr('readonly', !ordenTrabajo.contratoEmitido);
					}
				}});
			}});
		}
	});
};

var loadonmenuotstecnicocompleto = function(ordenTrabajo) {
	
	SSTFacade.getServicioTecnicoByOT(ordenTrabajo.id,{async:false,callback:function(stecnico){
		if(stecnico != null){
			$('#enviadoSTCompleto').show();
			stecnico.ot.contratoEmitido = !stecnico.ot.contratoEmitido;
			stecnico.ot.calificaFR = !stecnico.ot.calificaFR;
			$('#sTecnicoCompleto').loadObject(stecnico);
			$('#sTecnicoCompleto').find('#ot\\.numeroContrato').attr('readonly', !ordenTrabajo.contratoEmitido);
		} else {
			$('#noEnviadoSTCompleto').show();
		}
	}});
};
