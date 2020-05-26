var idDespachoInterno;
$(document).ready(function(){
	
	
	$('#informacionDespacho').flexigrid({
		dwrFunction: SSTFacade.listDespachosInternosOnTraladoToSP,
		height:200,
		usepager:true,
		showToggleBtn:false,
		multisel:false,
		singeSelect:true,
		tipo:'DESP',
		seccion:90008000,
		overrideModel: [
		    {findName:'detalle',propiedad:function(o){
		    	var input=$('<input type="button" value="Ver detalle">')
		    	.attr('onclick','verDetalle('+o.id+')');
		    	return input;
		    }}
		]
	});
	
	$('#informacionDespacho').loadData([]);
	
	
	$('#informacionOrdentrabajo').flexigrid({
		height:150,
		usepager:true,
		showToggleBtn:false,
		multisel:false,
		singeSelect:true,
		tipo:'DEDES',
		seccion:90009000,
		dwrFunction: SSTFacade.listOTRecepcionByDespachoInterno,
		overrideModel: [
		    {findName:'imprimirCodigo',propiedad:function(o){
		    	var btn=$('<input type="button" value="Imprimir Código">');
	        	$(btn).attr('onclick','printCodigo("'+o.codigoBarra+'")');
	        	return btn;
		    }},
		    {findName:'marcarRecibido',propiedad:function(o){
		    	var btn = $('<input type="button" value="Marcar Recibido">')
		    	.attr('onclick','marcarRecibido('+o.id+')');
		    	if(o.bitacoraInterna.estado.id==100003000 || o.bitacoraInterna.estado.id==100004000){
		    		$(btn).attr('disabled','');
		    	}
		    	return btn;
		    }},
		]
	});
	
	$('#buscar').click(function(){
		var inpt = new Array;
		inpt.push($('#idOT'));
		inpt.push($('#numeroGuiaRetiro'));
		inpt.push($('#buscadorManual').find('#codigoBarra'));
		inpt.push($('#numeroGuiaDespachoST'));
		     
		if (!validEmptyInputs(inpt,'Debe ingresar al menos uno de los parametros de busqueda')) {
			return;       
		}
		if (!$('#buscadorManual').valid()){
			return;
		}
		var filtro = $('#buscadorManual').serializeObject();
		buscar(filtro);
	});
	
	$('#buscadorAutomatico').find('#codigoBarra').keypress(function(e){
		if(e.which == 13) {
			if ($('#buscadorAutomatico').find('#codigoBarra').valid()) {
				var filtro = $('#buscadorAutomatico').serializeObject();
				buscar(filtro);
			}
	    }
	});
	
	var buscar = function (filter) {
		SSTFacade.marcarOTRecibidaINDespachoInternoByFilter(filter,{async:true,callback:function(ot){
			$('#buscadorManual').reset();
			$('#informacionDespacho').flexReload();
		}});
	};
	
	$('#popup').dialog({
		autoOpen:false,
		modal:true,
		width:900,
		position:[50,100]
	});
	
	$('#popup').bind('dialogclose', function(event){
		$('#informacionOrdentrabajo').clean();
		$('#informacionDespacho').flexReload();
		idDespachoInterno = null;
	});
	
	$('#terminar').click(function(){
		SSTFacade.getCantidadesDespachoByDespachoInterno({id:idDespachoInterno},{asycn:true,callback:function(despachoInterno){
			if(despachoInterno.cantidadOT!=despachoInterno.CantidadOTREcibidas){
				$.alerts.okButton = '&nbsp;Finaliza&nbsp;';
				$.alerts.cancelButton = '&nbsp;Cerrar&nbsp;';
				jConfirm('La recepción que está terminando no está completa, ¿Esta seguro de que desea finalizarla?','Confirmación',function(r){
					if(r){
						SSTFacade.terminarRecepcionDespachoInternoOnSPIncompleta({id:idDespachoInterno},{async:true,callback:function(){
							jAlert('La recepción ha sido marcada como incompleta','Aviso',function(){
								$('#popup').dialog('close');
							});
						}});
					} else {
						$('#popup').dialog('close');
					}
				});
			} else {
				SSTFacade.terminarRecepconDespachoInternoOnSP({id:idDespachoInterno},{async:true,callback:function(){
					jAlert('La recepción ha sido terminada con éxito','Aviso',function(){
						$('#popup').dialog('close');
					});
				}});
			}
		}});
	});
});

var marcarRecibido = function(idOT){
	SSTFacade.marcarOTRecibidaINDespachoInternoByFilter({idOT:idOT},{async:true,callback:function(){
		$('#informacionOrdentrabajo').flexReload();
	}});
};

var verDetalle = function(idDespacho){
	$('#informacionOrdentrabajo').loadData([{id:idDespacho}]);
	idDespachoInterno = idDespacho;
	$('#popup').dialog('open');
};