var initonmenurecibirotproducto = function() {
	$('#onmenurecibirotproducto').click(function(){
		$('#accesoriosOT, #partesOT').recalcLayout();
	});
	
	$('#codigoBarrasAccesorios').keypress(function(e){
        if(e.which == 13) {
        	var codigoBarra = $('#codigoBarrasAccesorios').val();
        	var accesorios = $('#accesoriosOT').getJSONFromHTML();
        	var encontrado = false;
			$.each(accesorios, function(idx,accesorio) {
				$('#codigoBarrasAccesorios').val("");
				if (accesorio.codigoBarra == codigoBarra) {
					$("#accesoriosOT").find('#estadoSi' + accesorio.id).attr('checked',true);
					encontrado = true;
				} 
			});
			if(!encontrado) {
				jAlert("Codigo de barra no corresponde a este producto","Información",function(){
					$('#codigoBarrasAccesorios').focus();
				});
			}
        }
        $('#codigoBarrasAccesorios').focus();
	});
	
	$("#accesoriosOT").flexigrid({
		height:'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		onToggleCol:false,
		title: "Accesorios",
		colModel : [
		    {display: '¿RECIBIDO?', width:100 , align: 'center', name_abbr:'recibido', name:function(o){
		    	var lblSI = $('<label></label>')
							.attr('for','estadoSi' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('SI');
				var rdSI = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','true')
							.attr('id','estadoSi' + o.id)
							.attr('onclick','onCheckAccesorios(true,' + o.id +')');
				var lblNO = $('<label></label>')
							.attr('for','estadoNo' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('NO');
				var rdNO = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','false')
							.attr('checked',true)
							.attr('id','estadoNo' + o.id)
							.attr('onclick','onCheckAccesorios(false,' + o.id +')');
				if(o.codigoBarra==$('#buscadorAutomatico').find('#codigoBarra').val()){
					$(rdNO).removeAttr('checked');
					$(rdSI).attr('checked',true);
				}
				var cnt = $('<div></div>')
							.css('padding','0px')
							.append(lblSI).append(rdSI).append(lblNO).append(rdNO);
				
				
		    	return cnt;
		    }},        
            {display: 'Descripción Accesorio',width:220,align:'left',name:'descripcion',name_abbr:'descripcion'},
            {display: 'Procedencia',width:180,align:'left' ,name:'ubicacion.nombre',name_abbr:'ubicacion.nombre'},
            {display: 'Código de Barras',width:120,align:'right' ,name:'codigoBarra',name_abbr:'codigoBarra'},
            {display: 'Imprimir Código',width:150,align:'center' ,name: function(o){
            	var btn = $('<input type="button">')
            				.attr('value','Imprimir Código')
            				.attr('onclick','printCodigo("' + o.codigoBarra + '")');
            	return btn;
            }}
		]
	});	
	
	$("#partesOT").flexigrid({
		height:'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		onToggleCol:false,
		title: "Estado del Producto y sus partes",
		colModel: [
		    {display: 'Concuerda' ,width:80 ,align:'center', name_abbr:'estado' ,name:function(o){
		    	var lblSI = $('<label></label>')
							.attr('for','estadoSi' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('SI');
				var rdSI = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','B')
							.attr('onclick','onCheckPartes(true,' + o.id +')');
				var lblNO = $('<label></label>')
							.attr('for','estadoNo' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('NO');
				var rdNO = $('<input type="radio">')
							.attr('name','estado' + o.id)
							.attr('value','M')
							.attr('onclick','onCheckPartes(false,' + o.id +')');
				var cnt = $('<div></div>')
							.css('padding','0px')
							.append(lblSI).append(rdSI).append(lblNO).append(rdNO);
				return cnt;
		    }},		 
		    {display: 'Parte del producto' ,width:140 ,align:'left', name_abbr:'glosa'   ,name:'glosa'},
		    {display: 'Observaciones Anteriores' ,width:396 ,align:'left', name_abbr:'observaciones'   ,name:'observaciones'},
		    {display: 'Observación' ,width:200 ,align:'center', name_abbr:'observacion' ,name:function(o){
		    	var tx = $('<input type="text">')
		    			 .css('width','90%')
		    			 .addClass('required')
		    			 .attr('id','txtObs' + o.id)
		    			 .attr('name','txtObs' + o.id)
		    			 .attr('readonly','readonly')
		    			 .attr('maxlength','100');
		    	return tx;
		    }}
	    ]
	});
	
	$('#siguiente').click(function(){
		$('#tabs').tabs('select', $('#tabs').find('#onmenurecibirotrecepcion').attr('href'));
	});
};
var loadAccesoriosAndPartesFromGuiaTipo = function(ordenTrabajo,guia){
	if(guia!=null && guia.origen && guia.origen.tipo == 'SC'){
		$('#numeroSerie').removeAttr('readOnly');
	}
	SSTFacade.listAccesoriosForGuiaTipo(ordenTrabajo,guia,false,{async:false,callback:function(accesoriosOT){
		if (accesoriosOT && accesoriosOT.length != 0) {
			$("#accesoriosOT").parent().parent().show();
			$("#accesoriosOT").flexAddData({rows:accesoriosOT,total:accesoriosOT.length});	
		} else {
			$('#accesoriosOT').clean();
			$("#accesoriosOT").parent().parent().hide();
		}
	}});
	if(guia.tipoGuia == 'GACC'){
		$('#partesOT').clean();
		$("#partesOT").parent().parent().hide();
//		$('#numeroSerie').attr('readonly', true);
		$('#guiaAccesorio').show();
		$('#rechazarRecepcion').hide();
		$('#aceptarConObservaciones').attr('value','Aceptar Accesorios con observación');
		$('#aceptarRecepcion').attr('value','Aceptar Accesorios');
		
	} else {
//		$('#numeroSerie').attr('readonly', false);
		$('#guiaAccesorio').hide();
		$('#rechazarRecepcion').show();
		$('#rechazarRecepcion').attr('value','Rechazar Recepción');
		$('#aceptarConObservaciones').attr('value','Aceptar Recepción con observación');
		$('#aceptarRecepcion').attr('value','Aceptar Recepción');
		
		SSTFacade.listPartesOTByOT(ordenTrabajo.id,{async:false,callback:function(partesOT){
			if (partesOT && partesOT.length != 0) {
				$("#partesOT").parent().parent().show();
				$("#partesOT").flexAddData({rows:partesOT,total:partesOT.length});			
			} else {
				$("#partesOT").parent().parent().hide();
			}
		}});
	}
	$('#codigoBarrasAccesorios').focus();
};

var loadonmenurecibirotproducto = function(ordenTrabajo) {
	SSTFacade.isFamiliaExcluidaNumeroSerieByProducto(ordenTrabajo.producto,{async:false,callback:function(excluida){
		if(excluida == true) {
			$('#numeroSerie, [for="numeroSerie"]').hide();
		} else {
			$('#numeroSerie, [for="numeroSerie"]').show();
		}
	}});
	
	if(ordenTrabajo.numeroCelular == null){
		$('.numeroCelular').hide();
	} else {
		$('.numeroCelular').show();
	}
	
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
	
};

var validaonmenurecibirotproducto = function(){
	var valid = true;
	var accesorios = $("#accesoriosOT").getJSONFromHTML();
	var partes = $("#partesOT").getJSONFromHTML();
	
	$("#partesOT").find('[type=radio]').parent().removeClass('error');
	$("#accesoriosOT").find('[type=radio]').parent().removeClass('error');
	
	$.each(partes, function(idx,parte) {
		if (parte.estado == null) {
			$("#partesOT").find('[name=estado' + parte.id + ']').parent().addClass('error');
			valid = false;
		} else if (parte.estado == 'M') {
			if(!$("#partesOT").find('#txtObs' + parte.id).valid()) {
				valid = false;
			}
		} 	
	});
	$.each(accesorios, function(idx,accesorio) {
		if (accesorio.recibido == null) {
			valid = false;
			$("#accesoriosOT").find('[name=estado' + accesorio.id + ']').parent().addClass('error');
		} 
	});
	
	SSTFacade.isFamiliaExcluidaNumeroSerieByProducto(ordenTrabajo.producto,{async:false,callback:function(excluida){
		if(excluida == false && !$('#numeroSerie').valid()) {
			valid = false;
			if(ordenTrabajo.producto!=null && ordenTrabajo.producto.familia != null && ordenTrabajo.producto.familia.id !=null && (ordenTrabajo.producto.familia.id==259/*CELULARES PREPAGO*/ || ordenTrabajo.producto.familia.id==267 /*CELULARES LIBRES*/ || ordenTrabajo.producto.familia.id==268/*CELULARES A CONTRATO*/ || ordenTrabajo.producto.familia.id==260/*CELULARES PREPAGO SMARTPHONE*/)){
				$('[for="numeroSerie"]').text('IMEI');
			}
		}
	}});
	
	return valid;
};

var onCheckPartes = function(val,id){
	$("#partesOT").find('#txtObs' + id).val('');
	$("#partesOT").find('#txtObs' + id).attr('readonly',val);	
	$("#partesOT").find('#txtObs' + id).removeClass('error');	
	$("#partesOT").find('[name=estado' + id + ']').parent().removeClass('error');	
};

var onCheckAccesorios = function(val,id){
	$("#accesoriosOT").find('[name=estado' + id + ']').parent().removeClass('error');	
};

var imprimirCodigo = function(codigo){
	alert(codigo);
};