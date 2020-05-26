var moduloReporteRecepcion;
var nmroGuia;



$(document).ready(function() {
	var idRecepcionCamion = $('#idRecepcion').val();
	
	SSTFacade.getModuloByNombreUsuario('onmenureporterecepciones',{async:false, callback:function(modulo) {
		moduloReporteRecepcion = modulo ? modulo : undefined;
	}});
	
	$('#detalleRecepcion').flexigrid({
		dwrFunction: SSTFacade.listDetalleGuiaRecepcion,
		seccion:90001000,
		height:'auto',
		minheight: 150, 
		usepager:true,
		showToggleBtn:true,
		tipo: 'DERE',
		title:'Detalle de la Recepción',
		multisel:false,
		singleSelect:true,
		idProperty: 'idGuia',
		overrideModel: [
            {findName:'detalle' ,propiedad:function(o){
            	var btn = $('<input type="button" value="Ver Detalle">');
            	$(btn).attr('onclick','verDetalle('+o.guia.id+',' + o.guia.numero +',' + idRecepcionCamion + ')');
            	return btn;
            }},
            {findName:'estado.id',propiedad:function(o){
            	if(o.cantidadTotalOT == o.cantidadOTRecibidas && o.cantidadTotalOT == o.cantidadOTRevisadas && o.guia.numeroTO == null){
            		return 'Completa';
            	} else if(o.cantidadTotalOT == o.cantidadOTRecibidas && o.cantidadTotalOT == o.cantidadOTRevisadas && o.guia.numeroTO != null){
            		return 'Completa / TO';
            	} else {
            		return 'En Proceso';
            	}
            }},
            {findName:'guia.origen.id', propiedad:function(o){
            	return o.guia.origen.id + ' ' + o.guia.origen.nombre;
            }}
        ]
	});
	
	$('#detallesGuia').flexigrid({
		height:'auto',
		userpager:false,
		showToggleBtn:true,
		multisel:false,
		singleSelect: true,
		dwrFunction : SSTFacade.listOTRecepcionBodegaByFilter,
		seccion: 90002000,
		tipo:'DEGU',
		overrideModel: [
	        {findName:'producto' ,propiedad:function(o){
	        	var msg = o.producto.id+' '+o.producto.descripcion;
	        	return msg;
	        }},
	        {findName:'estado',propiedad:function(o){
	        	var msg='';
	        	if(o.estado.id == 10031000){
	        		msg='Recibido';
	        	}else if(o.estado.id == 10032000 || o.estado.id == 10033000 || o.estado.id == 10034000 || o.estado.id == 10039000){
	        		msg='Revisado';
	        	}else if(o.estado.id == 10035000){
	        		msg='Rechazado';
	        	}else{
	        		msg='Faltante';
	        	}
	        	return msg;
	        }},
	        {findName:'marcarRecibido',propiedad:function(o){
	        	if(o.estado.id!=10031000 && o.estado.id!=10032000 && o.estado.id!=10033000 && o.estado.id!=10034000 && o.estado.id!=10035000 && o.estado.id!=10039000){
	        		var btn=$('<input type="button" value="Marcar Recibido">');
	        		o.codigoBarra = o.codigoBarra == null ? '' : o.codigoBarra;
	        		$(btn).attr('onclick','marcarRecibido("'+ o.codigoBarra + '",' + o.id + ')');
	        		return btn;
	        	}
	        }},
	        {findName:'imprimirCodigo',propiedad:function(o){
	        	var btn=$('<input type="button" value="Imprimir Código">');
	        	$(btn).attr('onclick','printCodigo("'+o.codigoBarra+'")');
	        	return btn;
	        }}
		 ]
	});
	
	
	$('#detalleRecepcion').loadData([{idRecepcionCamion:idRecepcionCamion}]);
	
	$('#buscadorAutomatico').find('#codigoBarra').keypress(function(e){
		if(e.which == 13) {
			if ($('#buscadorAutomatico').find('#codigoBarra').valid()) {
				var filter = {
					codigoBarra:$('#buscadorAutomatico').find('#codigoBarra').val()
				};
				$('#buscadorAutomatico').find('#codigoBarra').val('');
				$('#buscadorAutomatico').find('#codigoBarra').focus();
		        SSTFacade.recibirOT(filter,idRecepcionCamion,{async:true,callback:function(data){
		        	$('#detalleRecepcion').loadData([{idRecepcionCamion:idRecepcionCamion}]);
		        }});				
			}
	    }
	});
	 
	var validaBuscadorManual = function(){
		return $('#buscadorManual').valid() & validEmptyInputs($('#buscadorManual input[type=text]'),'debe ingresar al menos un criterio para la busqueda');
	};
	
	$('#buscar').click(function(){
		if(validaBuscadorManual()){
			var filter = $('#buscadorManual').serializeObject();
			SSTFacade.recibirOT(filter,idRecepcionCamion,{async:true,callback:function(data){
				$('#detalleRecepcion').loadData([{idRecepcionCamion:idRecepcionCamion}]);
			}});
		}
	});
	
	$('#popupDetalleGuia').dialog({
		autoOpen: false,
		modal:true,
		width:940
	});
	
	$('#terminarRecepcion').click(function(){
		$('#detalleRecepcion').loadData([{idRecepcionCamion:idRecepcionCamion}]);
		SSTFacade.terminarRecepcionMasiva({idRecepcionCamion: idRecepcionCamion},{async:true,callback:function(data){
			if(data){
				jAlert('Recepción terminada','Aviso',function(){
					parent.location = context + "/index.do?e=" + moduloReporteRecepcion.codigo + "&m=" + moduloInicial.codigo + "&idRecepcion=" + idRecepcionCamion;
				});
			} 
		}});
	});
	$('#buscadorAutomatico').find('#codigoBarra').focus();
	
});

function verDetalle(idGuia, numeroGuia, idRecepcionCamion){
//	nmroGuia = numeroGuia;
	$('#popupDetalleGuia').dialog('option', 'title', 'Detalle guía '+ numeroGuia);
	$('#detallesGuia').loadData([{idGuia:idGuia,idRecepcionCamion:idRecepcionCamion}]);
	$('#popupDetalleGuia').dialog('open');
	$('#detalleRecepcion').recalcLayout();
};

var marcarRecibido = function (codigoBarra, idOT){
	var idRecepcionCamion = $('#idRecepcion').val();
	var filter = {};
	filter.idRecepcionCamion=idRecepcionCamion;
	filter.codigoBarra=codigoBarra;
	filter.idOT=idOT;
	SSTFacade.recibirOT(filter,idRecepcionCamion,{async:true,callback:function(data){
		$('#detalleRecepcion').flexReload();
		$('#detallesGuia').flexReload();
	}});
};

var callbackAfterError = function(){
	$('#buscadorAutomatico').find('#codigoBarra').focus();
};