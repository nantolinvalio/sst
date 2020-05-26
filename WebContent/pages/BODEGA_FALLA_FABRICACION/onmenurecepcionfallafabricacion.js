$(document).ready(function() {
	
	SSTFacade.listTransportista({async:true ,callback:function(transportistas){
		$('#idTransportista').addItems(transportistas,'id','nombreCompleto',true);
	}});
	
		
	$("#recepciones").flexigrid({
		height:'auto',
		usepager:false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		showToggleBtn:false,
//		title: "Detalle de recepciones de los transportistas",
		colModel : [
		    {display: 'Nombre transportista', width:250, align: 'left',name_abbr:'transportista.nombre',name: function(o){     
		    	if(o.transportista.nombre == null){
		    		return "sin nombre";
		    	} else{
		    		return o.transportista.nombre;
		    	}
		    }},{display: 'Rut',    width:70, align:'right',name:'transportista.rut',name_abbr:'transportista.rut'},
            {display: 'Responsable',          width:270, align:'left' ,name_abbr:'nombreResponsable',name: function(o){
            	if(o.estado != null && o.estado.id == 80001000){
            		return o.nombreResponsable;
            	} else {
            		var text = $('<input type="text">')
            		.attr('id','txtResponsable' + o.transportista.id)
    				.attr('class','required')
					.attr('maxlength','64')
        			.css('width','100%');
        		return text;
            	}
            }},
            {display: 'Estado',width:120, align:'left' ,name_abbr:'estado.id',name: function(o){
            	if(o.estado != null && o.estado.id == 80001000){
            		return o.estado.nombre;
            	} 	else {
            		return "Sin recepción";
            	}
            }},
            {display: 'Abrir/crear recepcion',width:120,align:'center' ,name: function(o){
            	if(o.estado != null && o.estado.id == 80001000){
            		var btn = $('<input type="button">')
            		.attr('id','abrir')
    				.attr('value','Abrir');
    				if(o.transportista.nombre != null){
    					btn.attr('onclick','abrirRecepcion(' + o.transportista.id + ',"' + o.transportista.nombre.replace(/\"/g,'<br>').replace(/\'/g,'<br>') + '")');
    				}
            		return btn;
            	} else {
            		var btn = $('<input type="button">')
            		.attr('id','crear')
    				.attr('value','Crear');
    				if(o.transportista.nombre != null){
    					btn.attr('onclick','crearRecepcion(' + o.transportista.id + ',"' + o.transportista.nombre.replace(/\"/g,'<br>').replace(/\'/g,'<br>') + '")');    	
    				}
        		return btn;
            	}
            }}
		]
	});	
	
	SSTFacade.listRecepcionesMasivas({async:false,callback:function(recepciones){
		$("#recepciones").flexAddData({rows:recepciones,total:recepciones.length});			
	}});
});

var moduloRecibir;
SSTFacade.getModuloByNombreUsuario('onbodegaffrecibirproductofdp',{async:false, callback:function(modulo) {
	moduloRecibir = modulo ? modulo : undefined;
}});

var abrirRecepcion = function(idTransportista, nombre){
	$.alerts.okButton = '&nbsp;Abrir&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('¿Esta seguro que desea abrir la recepción del transportista ' + nombre + '?', 'Confirmación',function(r){
		if (r) {
			var recepcion = {
				transportista:{id:idTransportista},
//					nombreResponsable : $("#recepciones").find('[name_abbr=nombreResponsable' + idTransportista + ']').val()
			};
			SSTFacade.getRecepcionCamionAbiertaByTransportista(recepcion.transportista.id,{asunc:false,callback:function(recepcionCamion){
				parent.location = context +'/index.do?e=' + moduloRecibir.codigo + '&m=' + moduloInicial.codigo+'&idRecepcion='+recepcionCamion.id;
			}});
		}
	});
};

var crearRecepcion = function(idTransportista, nombre){
	if($('#txtResponsable' + idTransportista).valid()){
		$.alerts.okButton = '&nbsp;Crear&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Esta seguro que desea crear una recepción para el transportista ' + nombre + '?', 'Confirmación',function(r){
			if (r) {
				var recepcion = {
					transportista:{id:idTransportista},
					nombreResponsable : $('#txtResponsable' + idTransportista).val()	
				};
				SSTFacade.saveRecepcionMasiva(recepcion,{async:true,callback:function(recepcion){
					parent.location = context +'/index.do?e=' + moduloRecibir.codigo + '&m=' + moduloInicial.codigo+'&idRecepcion='+recepcion.id;
				}});
			}
		});
	}
};

