$(document).ready(function(){
//	$('#buscarOT').hide();
	var ubicacion;
	$('#marcoResultado').hide();
	
	$("#buscar").click(function(){
		
		if($("#buscarOT").valid()){
			var filter = $('#buscarOT').serializeObject();
			filter.tipoOT = "GM";
			
			// antes getOtByIdOrNumeroAtencion
			SSTFacade.getOTByFilter(filter,{async:false,callback:function(OT){
					$('#resultado').loadObject(OT);
					$('#marcoResultado').show();
					if($('#numeroCambio').val() == 0){
						$('#numeroCambio').val("");
						$('#textoAutorizacion').hide();
						$('#autorizarCambio').removeAttr('disabled');
						$('#numeroCambio').removeAttr("readOnly");
					}else if(OT.numeroCambio != 0){
						$('#textoAutorizacion').show();
						$('#numeroCambio').attr("readOnly","readOnly");
						$('#autorizarCambio').attr('disabled','disabled');
					}
			}});
		};
	});
	
	$("#autorizarCambio").click(function(){
		var valid = true;
		$('#numeroCambio').removeAttr('required');
		if($('#numeroCambio').valid()){
			if($('#numeroCambio').val() == 0){
				$('#numeroCambio').addClass('error');
				$('#numeroCambio').attr('title', 'Ingrese un numero de autorización distinto de 0');
				valid = false;
			}
			if(valid){
				
				var ot = {
						id: $('#id').text(),
						numeroCambio: $('#numeroCambio').val(),
				};
				SSTFacade.autorizarCambioGarantiaMaster(ot,{async:false,callback:function(){
					jAlert("Se autorizó el cambio correctamente","Información");
					$("#buscarOT").reset();
					$('#marcoResultado').hide();
					$('#resultado').reset();
				}});
			}
		}
	});
});