$(document).ready(function() {

	var ots = [];

	$('#directoaFacturar').flexigrid({ // GRILLA QUE MUESTRA LAS OT A FACTURAR
		dwrFunction: SSTFacade.listOtByIdGuia,
		seccion: 1000040000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'OTAF',
		overrideModel: [{findName:'id',propiedad:function(o){
			ots.push(o);
			return o.id;
		}},
		],
    	buttons : [
			{name: 'FACTURAR SC', bclass: 'btnFactura', onpress : function(){generarSCTransportista(ots);} },
			{separator: true},
		]
	});
	
	var generarSCTransportista = function(ots){
		if(ots.length == 0){
			jAlert('No existen ot para generar factura','AVISO');
		}else{
			var guia = {id : $('#idGuia').val()}; 
			 SSTFacade.generarSC(ots,null, guia,{async:false,callback:function(){
				 jAlert('Se genero correctamente la SC','AVISO');
			 }});
		}
		parent.location = context +'/index.do?e=' + moduloRecibir.codigo + '&m=' + moduloInicial.codigo;
	};
	
	$('#directoaFacturar').loadData([{idGuia:$('#idGuia').val()}]);
	});

	var moduloRecibir;
	SSTFacade.getModuloByNombreUsuario('onbodegapaneltrabajo',{async:false, callback:function(modulo) {
		moduloRecibir = modulo ? modulo : undefined;
	}});
		
	