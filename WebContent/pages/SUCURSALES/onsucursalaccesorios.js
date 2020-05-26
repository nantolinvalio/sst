$(document).ready(function(){
	var idOT = parseInt($('#idOT').text());
	var oT;
	var codigoObligatorio;
	
	SSTFacade.getCodigoBarraObligatorio({async:false,callback:function(resp){
		codigoObligatorio = resp;
	}});
	
	var moduloFallas;
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;
	}});
	
	var moduloRevision;
	SSTFacade.getModuloByNombreUsuario('onsucursalrevision',{async:false, callback:function(modulo) {
		moduloRevision = modulo ? modulo : undefined;
	}});
	
	var moduloBuscar;
	SSTFacade.getModuloByNombreUsuario('onsucursalbuscardocumento',{async:false, callback:function(modulo) {
		moduloBuscar = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getOTById(idOT,{async:false,callback:function(OT){
		$('#datos').loadObject(OT);
		if(OT.descripcionFalla==null){
			$('.descripcionFalla').css('display','none');	
		}
		oT=OT;
	}});
	 
	var validarAccesorios = function(){
		var valid = true;
		var accOT = $("#accesoriosGrid").getJSONFromHTML();
		valid = $('#acceroriosForm').valid();
		if (valid) {
			$.each(accOT,function(index,accesorio){
				if($("#accesoriosGrid").find('input:[value=' + accesorio.codigoBarra + ']').size() > 1 && accesorio.codigoBarra != "") {
					$("#accesoriosGrid").find('input:[value=' + accesorio.codigoBarra + ']').addClass('error');
					$("#accesoriosGrid").find('input:[value=' + accesorio.codigoBarra + ']').attr('title', 'Los códigos de barra no pueden estar repetidos');
					valid = false;
				}
			});
		}
		
		if (valid) {
			SSTFacade.validaCodigosDeBarraAccesorios(accOT,{async:false,callback:function(idAccesorios){
				if (idAccesorios.lenght != 0) {
					$.each(idAccesorios,function(index,idAccesorio){
						$("#accesoriosGrid").find('#codigoBarra' + idAccesorio).addClass('error');
						$("#accesoriosGrid").find('#codigoBarra' + idAccesorio).attr('title', 'El código de barras ya está en uso');
						valid = false;
					});
				}
			}});			
		}
		
		return valid;
	};
	
	
	//TODO REVISAR LA CREACION DE OT EN GM, VALIDAR QUE LOS ACCESORIOS QUEDEN EN 10 
	if (oT.cambioAutorizado) {
//		if(oT.tipo != null && oT.tipo.codigo != null && (oT.tipo.codigo == 'CA' || (oT.tipo.codigo == 'GP' && oT.tipoCambio != null))){
		$('#tituloPagina').text('Recepción de producto con autorización de cambio' + (oT.tipo.codigo == 'GM' ? '. Garantía Master' : ''));
		$('.gProveedor, .gMaster').css('display','none');
		
		$('#accesoriosGrid').flexigrid({
			height:'auto',
			usepager:false,
			showToggleBtn:true,
			title:'Accesorios',
			multisel:false,
			singleSelect:true,
			onDragCol:false,
			colModel: [
			    {display: 'Descripción Accesorio' ,width:450 ,align:'left' ,  name_abbr:'descripcion',name:'descripcion'},
			    {display: 'Requerido'             ,width:100 ,align:'center', name_abbr:'requerido',name:function(o){
			    	return 'SI';
			    }},
			    {display: 'Seleccionar'           ,width:100 ,align:'center',  name_abbr:'seleccionado', name:function(o){
			    	var chk =$('<input type="checkbox">')
			    			.attr('checked',o.ubicacion.id!=null && o.ubicacion.id!=10)
			    			.attr('id','checkSeleccionado' + o.id)
							.attr('onclick','onCheckSeleccionar(' + o.id + ')');
			    	return chk;
			    }},
			    {display:'Código de Barras', width:200 , align:'center', name_abbr:'codigoBarra', name:function(o){
			    	var input = $('<input type="text">')
			    				.addClass('digits')
			    				.attr('maxlength','10')
			    				.attr('id','codigoBarra' + o.id)
			    				.attr('name','codigoBarra' + o.id)
			    				.attr('disabled',o.ubicacion.id == null || o.ubicacion.id == 10)
			    				.attr('onblur','formatcodigo(this)')
			    				.attr('value', o.codigoBarra);
			    	
			    	if (codigoObligatorio) {
			    		input.addClass('required');
			    	}
			    	
			    	return input;
			    }}
			]
		});
	} else{
		$('#accesoriosGrid').flexigrid({
			height: 'auto',
			usepager:false,
			showToggleBtn:false,
			title:'Accesorios',
			multisel:false,
			singleSelect:true,
			onDragCol:false,
			colModel: [
			    {display: 'Descripción Accesorio' ,width:450 ,align:'left'   ,name_abbr:'descripcion',name:'descripcion'},
			    {display: 'Requerido'             ,width:100 ,align:'center' , name_abbr:'requerido', name:function(o){
			    	if(o.requerido){
			    		return 'SI';
			    	} else {
			    		return 'NO';
			    	}
			    }},
			    {display: 'Seleccionar'           ,width:100 ,align:'center',  name_abbr:'seleccionado', name:function(o){
			    	var chk =$('<input type="checkbox">')
			    			.attr('checked',o.ubicacion.id!=null && o.ubicacion.id!=10)
			    			.attr('id','checkSeleccionado' + o.id)
							.attr('onclick','onCheckSeleccionar(' + o.id + ')');
			    	return chk;
			    }},
			    {display:'Codigo de Barra', width:200 , align:'center', name_abbr:'codigoBarra', name:function(o){
			    	var input = $('<input type="text">')
			    				.addClass('digits')
			    				.attr('maxlength','10')
			    				.attr('id','codigoBarra' + o.id)
			    				.attr('name','codigoBarra' + o.id)
			    				.attr('disabled',o.ubicacion.id == null || o.ubicacion.id == 10)
			    				.attr('onblur','formatcodigo(this)')
			    				.attr('value', o.codigoBarra);
			    	
			    	if (codigoObligatorio) {
			    		input.addClass('required');
			    	}
			    	
			    	return input;
			    }}
			]
		});
	}
	
	
	
	SSTFacade.listAccesoriosByOT(idOT,{async:true,callback:function(accesoriosOT){
		$('#accesoriosGrid').flexAddData({rows:accesoriosOT,total:accesoriosOT.length});
		if(accesoriosOT.length>0){
			var text='';
			$.each(accesoriosOT,function(index,value){
				if (value.ubicacion.id == ubicacion.id)
					text+=value.descripcion + '; ';				
			});
			$('#accesorios').text(text);
			$('#noAccesorios').hide();
		} else {
			$('.accesoriosOT').css('display','none');
			$('#acceroriosForm').hide();
		}
	}});
	
	SSTFacade.listTipoFallasByOT(idOT,{async:true,callback:function(tipoFallas){
		var text ="";
		if(tipoFallas!=null && tipoFallas.length >0){
			$.each(tipoFallas,function(index,value){
				text =text+value.descripcion;
				text=text+(tipoFallas.length>(index+1)? ', ':'');
			});
		} else {
			$('.tipoFallas').css("display","none");
		}
		$('#tipoFallas').text(text);
	}});
	
	
	$('#siguiente').click(function(){
		var correcto = true;
		var accOT = $('#accesoriosGrid').getJSONFromHTML();
		
		if(!validarAccesorios()){
			return false;
		}
		
		$.each(accOT,function(index,accesorio){
			if (accesorio.requerido == 'SI' && !accesorio.seleccionado) {
				correcto = false;
			} 
			if (accesorio.seleccionado == true) {
				accesorio.ubicacion = ubicacion;
			} else {
				accesorio.ubicacion = {id:10};
			}
			accesorio.requerido = null;
			SSTFacade.updateUbicacionAccesorio(accesorio,{async:false,callback:function(data){}});
		});
		
		if(!correcto){
			$.alerts.okButton = '&nbsp;Dejar pendiente por accesorios&nbsp;';
			$.alerts.cancelButton = '&nbsp;Desactivar orden de trabajo&nbsp;';
			var msg = 'Estimado usuario, no se han marcado todos los accesorios que son requeridos para enviar a servicio técnico,';
			msg+='el cliente puede dejar el producto en la sucursal y traer los accesorios requeridos faltantes mas tarde, con esto la orden de trabajo queda pendiente por accesorios, o el cliente se lleva el producto y vuelve con todos los accesorios requeridos, con esto la orden de trabajo se desactiva y se crea otra cuando cuando vuelva.';
			jConfirm(msg,'Confirmacion',function(r){
				if(r){
					SSTFacade.updateEstadoOT({id:idOT,estado:{id:10002000}},{async:true,callback:function(data){
						$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
						jAlert('La orden de trabajo quedo pendiente por accesorios, puede seguir llenando la información de la orden de trabajo','Aviso',function(r){
							if(r){
								parent.location = context + '/index.do?e='+moduloRevision.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
							}
						});
					}});
				} else {
					$.alerts.okButton = '&nbsp;Aceptar&nbsp;';
					SSTFacade.updateDesactivarOT({id:idOT,motivoDesactivacion:'El cliente no trajo los accesorios requeridos a la sucursal'},{async:false,callback:function(){
						jAlert('El cliente no trajo los accesorios requeridos a la sucursal','Aviso',function(r){
							if(r){
								parent.location = context + '/index.do?e='+moduloBuscar.codigo+'&m='+moduloInicial.codigo;
							}
						});
					}});
				}
			});
		} else {
			//alert("estoy en el lugar correcto. "+idOT);
			SSTFacade.updateEstadoOT({id:idOT,estado:{id:10001000}},{async:true,callback:function(data){
				parent.location = context + '/index.do?e='+moduloRevision.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
			}});
		}
	});
	
	
	$('#volver').click(function(){
		parent.location = context + '/index.do?e='+moduloFallas.codigo+'&m='+moduloInicial.codigo+'&idOT='+idOT;
	});
});

var formatcodigo = function(el){
	if ($(el).val() != "" && $(el).valid()) {
		$(el).val($.leftPad($(el).val(),10,'0'));
	}
};

var onCheckSeleccionar = function(id) {
	var val = !$("#accesoriosGrid").find('#checkSeleccionado' + id).is(':checked');
	$("#accesoriosGrid").find('#codigoBarra' + id).val('');
	$("#accesoriosGrid").find('#codigoBarra' + id).attr('disabled',val);
	$("#accesoriosGrid").find('#codigoBarra' + id).removeClass('error');
};