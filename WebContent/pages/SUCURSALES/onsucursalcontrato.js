$(document).ready(function(){

	var moduloContrato;
	SSTFacade.getModuloByNombre('onsucursalcontrato',{async:false, callback:function(modulo) {
		moduloContrato = modulo;
	}});
	
	$('#ot\\.contratoEmitido').click(function(){
		$('#ot\\.numeroContrato').val(this.checked ? $('#guia\\.numero').text() : '' );
		$('#ot\\.numeroContrato').attr('readOnly',this.checked);
	});
    
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#buscarDocumento').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	var buscar = function() {
		var otguia =new Array;
		otguia.push($('#idOT'));
		otguia.push($('#numeroGuia'));

		$('#formulario').hide();
		$("#etapas").hide();
		
		if(validEmptyInputs(otguia,'debe ingresar alguno de los parametros de busqueda') && $('#buscarDocumento').valid()){ 
		    var filter = $('#buscarDocumento').serializeObject();
			
		    SSTFacade.getOTContratoByGuia(filter, {async:false,callback:function(st){
		    	$('#formulario').show();
				$('#etapas').show();
		    	$('#resultado').loadObject(st);
		    	$('#ot\\.contratoEmitido').attr('checked', (st.contratoEmitido == true));
		    	$('#ot\\.numeroContrato').attr('readOnly', (st.contratoEmitido == true));
		    	 
		    	loadEtapasOT(st.ot);

		    	$('#ot\\.contratoEmitido').attr('disabled', st.ot.estado.id != 10005000);
	    		$('#ot\\.diagnostico').attr('readonly', st.ot.estado.id != 10005000);
	    		$('#ot\\.numeroContrato').attr('readonly', st.ot.estado.id != 10005000);
	    		$('#grabar').attr('disabled', st.ot.estado.id != 10005000);
			}});
		}
	};
	
	
	$('#grabar').click(function(){
		if($('#resultado').valid()) {
			var ot = {
				contratoEmitido : !$('#ot\\.contratoEmitido').is(':checked'),
				numeroContrato : $('#ot\\.numeroContrato').val(),
				diagnostico : $('#ot\\.diagnostico').val(),
				id : $('#ot\\.id').text()		
			};
			SSTFacade.saveSTecnicoOT(ot, {async:false,callback:function(){
				$.alerts.okButton = '&nbsp;Ok&nbsp;';
				jAlert('Se ha guardado el contrato correctamente','Información',function(){
		    		$('#ot\\.contratoEmitido').attr('disabled', true);
		    		$('#ot\\.diagnostico').attr('readonly', true);
		    		$('#ot\\.numeroContrato').attr('readonly', true);
		    		$('#grabar').attr('disabled', true);
				});
			}});
		}
	});
	
	$('#ot\\.diagnostico').maxlength({  
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
	
	$('#cancelar').click(function(){	
		parent.location = context + '/index.do?e=' + moduloContrato.codigo + '&m=' + moduloInicial.codigo;
	});
	
});
