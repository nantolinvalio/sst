var initonbodegaeditarcontrolcalidad = function() {
	
};

var loadonbodegaeditarcontrolcalidad = function(ordenTrabajo){
	$('#sinRecepciones').hide();
	$('#controlCalidadForm').hide();
	SSTFacade.listRecepcionesByOT(ordenTrabajo.id,{async:true,callback:function(recepcionesOT){
		if(recepcionesOT.length>0){
			$('#controlCalidadForm').show();
			$('#sinRecepciones').hide();
		}else{
			$('#controlCalidadForm').hide();
			$('#sinRecepciones').show();
		}
	}});
	
	$('#controlCalidadForm').find('#excluir').click(function(){
		SSTFacade.updateExcluirCCalidad({id:ordenTrabajo.id,excluyeCCalidad:true},{async:true,callback:function(){
			jAlert('Orden de trabajo excluída de control de calidad','AVISO',function(){
				location.reload();
			});
		}});
	});
	
	$('#controlCalidadForm').find('#salvar').click(function(){
		var oT = ordenTrabajo;
		
		if($('#pasaCC').is(':checked') || $('#noPasaCC').is(':checked')){
			oT.cCalidadAprueba = ($('#pasaCC').is(':checked')?true:false);
			oT.cCalidadObservacion = $('#observaciones').val();
			SSTFacade.updateOTControlCalidadByFilter(oT,{async:true,callback:function(oT){
				jAlert('El control de calidad se guardo correctamente.','Informacion',function(){
					$('#buscarDocumento').reset();
					location.reload();
				});
			}});
		} else {
			jAlert('Debe seleccionar si el producto pasa o no el control de calidad','Aviso',function(){
				
			});
		}
		
		
		
	});
};