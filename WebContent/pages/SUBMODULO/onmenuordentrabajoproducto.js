var initonmenuordentrabajoproducto = function() {
	var isLoad = false;
	$('#onmenuordentrabajoproducto').click(function(){
		if(!isLoad) {
			loadonmenuordentrabajoproducto(ordenTrabajo);
			isLoad = true;
		}
		$('#accesoriosOT').recalcLayout();
		$('#partesOT').recalcLayout();
	});
	
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
            {display: 'Descripción Accesorio',width:330,align:'left',name:'descripcion'},
            {display: 'Ubicación',width:200,align:'left' ,name:'ubicacion.nombre'},
            {display: 'Código de Barras',width:120,align:'right' ,name:'codigoBarra',name_abbr:'codigoBarra'},
            {display: 'Imprimir Código',width:150,align:'center' ,name: function(o){
            	var btn = $('<input type="button">')
					.attr('value','Imprimir Código')
					.attr('onclick','printCodigo("' + o.codigoBarra + '")')
            		.attr('disabled',(o.codigoBarra == null || o.codigoBarra == ''));
            	return btn;
            }}
		]
	});
	
	$("#partesOT").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		onToggleCol:false,
		title: "Partes",
		colModel : [
            {display: 'Estado',width:70,align:'left',name:function(o) {
            	return o.estado == 'B' ? 'Bueno' : 'Malo';
            } },
            {display: 'Parte revisada',width:250,align:'left' ,name:'glosa'},
            {display: 'Observaciones',width:490,align:'left' ,name:'observaciones'},
		]
	});
	
	$("#accesoriosGuia").flexigrid({
		height: 'auto',
		width:550,
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onToggleCol:false,
		onDragCol: false,
		title: "ACCESORIOS",
		colModel : [
            {display: '¿RECIBIDO C.D.?',width:80,align:'left',name:function(o) {
            	return o.recibidoCd == true ? 'SI' : 'NO';
            } },  
            {display: 'DESCRIPCIÓN ACCESORIO',width:250,align:'left',name:'descripcion'},
            {display: 'UBICACIÓN',width:150,align:'left' ,name:'ubicacion.nombre'},
		]
	});
	
	$('#verGuiaAccesorios').click(function(){
		$('#popupGuiaAccesorio').dialog('open');
	});
	
	$('#popupGuiaAccesorio').dialog({
		autoOpen: false,
		modal:true,
		width:600,
		position: [100,100]
	});
	
	$('#idGuiaAccesorio').click(function(){
		$('#popupGuiaAccesorio').dialog('open');
	});
	
};

var loadonmenuordentrabajoproducto = function(ordenTrabajo) {
	
	if (ordenTrabajo.codigoBarra != null && ordenTrabajo.codigoBarra != '') {
		$('#imprimirCodigoProducto').attr('onclick','printCodigo("' + ordenTrabajo.codigoBarra + '")');
		$('#imprimirCodigoProducto').attr('disabled', false);
	} else {
		$('#imprimirCodigoProducto').attr('disabled', true);
	} 
	
	SSTFacade.isFamiliaExcluidaNumeroSerieByProducto(ordenTrabajo.producto,{async:false,callback:function(excluida){
		if(excluida == true) {
			$('#numeroSerie, [for="numeroSerie"]').hide();
		} else {
			$('#numeroSerie, [for="numeroSerie"]').show();
			if(ordenTrabajo.producto!=null && ordenTrabajo.producto.familia != null && ordenTrabajo.producto.familia.id !=null && (ordenTrabajo.producto.familia.id==259/*CELULARES PREPAGO*/ || ordenTrabajo.producto.familia.id==267 /*CELULARES LIBRES*/ || ordenTrabajo.producto.familia.id==268/*CELULARES A CONTRATO*/ || ordenTrabajo.producto.familia.id==260/*CELULARES PREPAGO SMARTPHONE*/)){
				$('[for="numeroSerie"]').text('IMEI');
			}
		}
	}});
	
	if(ordenTrabajo.numeroCelular == null){
		$('.numeroCelular').hide();
	} else {
		$('.numeroCelular').show();
	}
	
	SSTFacade.listAccesoriosByOT(ordenTrabajo.id,{async:false,callback:function(accesorios){
		if (accesorios && accesorios.length != 0) {
			$("#accesoriosOT").parent().parent().show();
			$("#accesoriosOT").flexAddData({rows:accesorios,total:accesorios.length});				
		} else {
			$("#accesoriosOT").parent().parent().hide();
		}
	}});
	
	SSTFacade.listPartesOTByOT(ordenTrabajo.id,function(partes){
		if (partes && partes.length != 0) {
			$("#partesOT").parent().parent().show();
			$("#partesOT").flexAddData({rows:partes,total:partes.length});				
		} else {
			$("#partesOT").parent().parent().hide();
		}
	});
	
	SSTFacade.listTipoFallasByOT(ordenTrabajo.id,{async:false,callback:function(tipoFallas){
		var text = '';
		if(tipoFallas!=null && tipoFallas.length >0){
			$('.tipoFallas').show();
			$.each(tipoFallas,function(index,value){
				text = text + value.descripcion + '<br>';
			});
		} else {
			$('.tipoFallas').hide();
		}
		$('#tipoFalla').html(text);
	}});
	$('#producto').loadObject(ordenTrabajo);

	var recepcion;
	SSTFacade.getGuiaAccesorioForIdOT(ordenTrabajo.id,{async:false,callback:function(guiaAcc){
		if(guiaAcc != null){
			$('#txtGuia').text(guiaAcc.numero);
			$('.txtGuia').show();
			$('#detalleGuia').loadObject(guiaAcc);
			SSTFacade.getRecepcionByGuia(guiaAcc,{async:false,callback:function(recepcion){
				recepcion = recepcion == null ? {} : recepcion ;
				$('#detalleGuia').loadObject(recepcion);
			}});
			SSTFacade.listAllAccesoriosFromTipoGuia(guiaAcc,{async:false,callback:function(accesoriosOT){
				if (accesoriosOT && accesoriosOT.length != 0) {
					$("#accesoriosGuia").parent().parent().show();
					$("#accesoriosGuia").flexAddData({rows:accesoriosOT,total:accesoriosOT.length});
				} else {
					$("#accesoriosGuia").parent().parent().hide();
					$("#accesoriosGuia").clean();
				}
			}});
		} else {
			$('#detalleGuia').reset();
			$('#txtGuia').text('');
			$('.txtGuia').hide();
			$("#accesoriosGuia").parent().parent().hide();
			$("#accesoriosGuia").clean();
		}
	}});
	
};
