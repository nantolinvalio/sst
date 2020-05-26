var moduloProveedor;
var moduloTerminar;
var moduloAccesorios;
var moduloFallas;
var tipoCambioAutomatico;
var producto;
var oTGeneral = {};
var btnOrigen = "";
var cheBoxSinProdFisico = "";

function asignaOrigen(origen){
	btnOrigen = origen;
	//alert("-----> El valor de origen asignado va: "+btnOrigen);
}

function asignaChecked(){ 
   	//alert("---> El checkBox viene chequeado?: "+document.registrar.sinProductoFisico.checked);
   	if(document.registrar.sinProductoFisico.checked){
   		cheBoxSinProdFisico = "SIN";
   	}else{
   		cheBoxSinProdFisico = "CON";
   	}
}

$(document).ready(function(){
	SSTFacade.getModuloByNombreUsuario('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getModuloByNombreUsuario('onsucursalaccesorios',{async:false, callback:function(modulo) {
		moduloAccesorios = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getModuloByNombreUsuario('onsucursalgarantiaproveedor',{async:false, callback:function(modulo) {
		moduloProveedor = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getModuloByNombreUsuario('onsucursalterminar',{async:false, callback:function(modulo) {
		moduloTerminar = modulo ? modulo : undefined;
	}});
	
	
	$('#productos').flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		    {display: 'C. Costo'    ,width:40 ,align:'right'  ,name:'id'},
		    {display: 'Producto'        ,width:170 ,align:'left'   ,name_abbr:'descripcion',name:function(o){
		    	return o.descripcion == null ? 'Sin Descripción' : o.descripcion;
		    }},
		    {display: '#'        ,width:15  ,align:'right'  ,name:'cantidad'},
		    {display: '$ Unitario' ,width:40 ,align:'right'  ,name:'precioVenta'},
		    {display: 'Garantía'        ,width:170 ,align:'center'   ,name:function(o){
		    	if (o.descripcion != null) {
		    		var btn = $('<input type="button">');	
			    		btn.attr('value', 'Consultar Garantía');
			    		btn.attr('onclick', 'javascript: crearOT(' + o.id + ');');
			    	return btn;
		    	} else {
		    		return '';
		    	}
		    }},
		    {display: 'Cambio'        ,width:80 ,align:'center'   ,name:function(o){
		    	if (o.descripcion != null) {
		    		var btn = $('<input type="button">');	
		    		//alert("1. "+o.enGarantiaProveedor);
		    		if (o.enGarantiaProveedor == true) {
		    			//alert("2. "+o.cambioPorValor);
		    			if (o.cambioPorValor == true) {
				    		btn.attr('value', 'Cambiar');
				    		btn.attr('onclick', 'javascript: cambioPorValor(' + o.id + '); asignaOrigen("C6");');
			    		}else if (o.cambioPor24h == true) {
				    		btn.attr('value', 'Cambiar');
				    		btn.attr('onclick', 'javascript: cambioMenor24Hrs(' + o.id + '); asignaOrigen("C7");');
			    		} else if(o.cambioAutorizadoProveedor == true) {
				    		btn.attr('value', 'Cambiar');
				    		btn.attr('onclick', 'javascript: cambioPorProveedor(' + o.id + '); asignaOrigen("C8");');
			    		} else if (o.cambioPorFallaFabricacion == true) {
			    			if(o.cambioMeson){
			    				btn.attr('value', 'En mesón');
			    			} else {
			    				btn.attr('value', 'Cambiar');
			    			}
				    		btn.attr('onclick', 'javascript: fallaFabricacion(' + o.id + ');');
				    		if(o.productoParaEvaluacion == true){
				    			btn.attr('value', 'Para Evaluación');
					    		btn.attr('onclick', 'javascript: evaluacionProducto(' + o.id + ');');
				    		}
			    		} else {
				    		return '';
			    		}
			    	} else {
			    		return '';
			    	}
			    	return btn;
		    	} else {
		    		return '';
		    	}
		    }},
		    {display: ''        ,width:20 ,align:'center'   ,name:function(o){
		    	var dv = $('<div>').addClass('tip');
		    	var img = $('<img>');
		    	var str = '';
		    	img.attr('src',context + '/icons/warning.png');
		    	if (o.descripcion != null) {
		    		
			    	if (o.enGarantiaProveedor == true) {
			    		if (o.cambioPor24h == true) {
			    			
			    			str += 'Producto con autorización de cambio menor 24 horas <br> ';
			    		}
			    		if (o.cambioAutorizadoProveedor == true) {
			    			str += 'Producto con autorización de cambio del proveedor <br> ';
			    			str += o.motivoCambioAutorizadoProveedor == null ? '' : o.motivoCambioAutorizadoProveedor + ' - ';
			    		}else if (o.cambioPorValor == true) {
			    			str += 'Producto con autorizacion de cambio por valor <br> ';
			    			str += o.motivoCambioPorValor == null ? '' : o.motivoCambioPorValor + ' - ';
					    	str += o.motivoCambioPorFallaFabricacion == null ? '' : o.motivoCambioPorFallaFabricacion;
			    		}  else if (o.cambioPorFallaFabricacion == true) {
			    			
			    			if(o.cambioMeson){
			    				str += 'Producto con autorización de cambio en mesón <br> ';
			    			} else {
				    			str += 'Producto con autorización por Falla de Fabricación <br> ';
			    			}
					    	if(o.productoParaEvaluacion){
					    		str = 'Producto para evaluación';
					    	}
			    		}  else {
			    			str += 'Producto en Garantía - ';
					    	str += o.motivoCambioPorFallaFabricacion == null ? '' : o.motivoCambioPorFallaFabricacion + '<br>';
			    			str += o.motivoCambioPorValor == null ? '' : o.motivoCambioPorValor;
			    		}
			    	} else {
			    		str += 'Producto fuera de Garantía - ';
			    	}
		    	} else {
		    		str += 'Producto sin descripción - ';
		    	}
		    	img.attr('title',str);
		    	dv.append(img);
		    	return dv;
		    }},
		    {display: 'Cert. Falla', width:80, align:'center', name:function(o){
	    		var btn = $('<input type="button" value="Evaluar" onclick="javascript: evaluacionPorCliente('+o.id+');">');
	    		if (o.cambioPorValor == true){
	    			btn.attr('disabled','disabled');
	    		} else {
	    			if(o.cambioCertificacionFalla != null && o.cambioCertificacionFalla){
	    				btn.removeAttr('disabled');
	    			} else {
	    				btn.attr('disabled','disabled');
	    			}
	    		}
		    	return btn;
	    }}, 
		    {display: 'Autorización de cambio'        ,width:120 ,align:'center'   ,name:function(o){
		    	if (o.descripcion != null) {
		    		var btn = $('<input type="button" value="Ingresar" onclick="javascript: registrarCambioAutomatico('+o.id+');">');
			    	return btn;
		    	}
		    	return '';
		    }},
		    {display: 'Cambio por Jefe Tienda', width:120, align:'center', name:function(o){
		    	if (o.descripcion != null) {
		    		var btn = $('<input type="button" value="Cambiar" onclick="javascript: autorizarCambioJT('+o.id+'); asignaOrigen('+"'C9'"+');">');
			    	return btn;
		    	}
		    	return '';
		    }}, 
		]
	});
	
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#buscarDocumento').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	var buscar = function(){
		if($('#buscarDocumento').valid()){ 
			var doc = {
				tipo:$('#tipoDocumento').val(),
				id:$('#idDocumento').val()
			};
			
			SSTFacade.getDocumentoCompletoByIdAndTipo(doc,{async:true,callback:function(documento){
				$('#resultado').loadObject(documento);
				$('#resultado').show();
				$('#productos').flexAddData({rows:documento.productos, total:documento.productos.length});
				$(".tip img").tooltip({
					track: true,
					delay: 0,
					showURL: false,
					showBody: " - ",
					fade: 250
				});
			}});
		}
	};
	
	
	$('#limpiar').click(function(){
		$('#idDocumento').val('');
		$('#tipoDocumento').val('boleta');
		$('#id').text(' ');
		$('#tipo').text(' ');
		$('#sucursal\\.id').text(' ');
		$('#fechaEmision').text(' ');
		$('#telefono').text(' ');
		$('#productos').flexAddData({rows:[{}], total:[{}].length});
		$('#resultado').hide();
	});
	
	$('#popupAutorizarCambio').dialog({
		autoOpen: false,
		modal:true,
		width:650,
		position: [300,150]
	});
	
	 $('#popupAutorizarCambio').bind('dialogclose', function(event) {
	     $('#popupAutorizarCambio').find('#run').val('');
	     $('#popupAutorizarCambio').find('#clave').val('');
	 });
	
	$('#popupRegistrarCambio').dialog({
		autoOpen: false,
		modal:true,
		width:650,
		position: [300,150]
	});
	
	$('#popupCliente').dialog({
		autoOpen: false,
		modal:true,
		width:600,
		position: [300,150]
	});
	
	$('input[name=motivo]').change(function(){
		if($('#fallaReiterada').is(':checked')){
			$('.numeroSerie').css('display','');
			$('#numeroSerie').addClass('required');
		}
		else{
			$('.numeroSerie').css('display','none');
			$('#numeroSerie').removeClass('required');
		}
	});
	
	$('#autorizarCambio').click(function(){
		if($('#autorizar').valid()){
			filterTipoCambio = {}; 
			filterTipoCambio.usuario={password:$('#clave').val(),login:$('#run').val()};
			filterTipoCambio.producto={id:$('#autorizar').find('#idProducto').val()};
			filterTipoCambio.documento={id:$('#resultado').find('#id').text(),tipo:$('#resultado').find('#tipo').text()};
			filterTipoCambio.motivo=$('input[name=motivo]:checked').val();
			filterTipoCambio.numeroSerie=$('#numeroSerie').val();
			
			SSTFacade.autorizacionTipo(filterTipoCambio,{async:true,callback:function(usuario){
				if(usuario != null){
					var oT = {};
					oT.tipo={codigo:'CA'};
					oT.tipoCambio = {codigo:'JT'};
					oT.tipoDocumento=$('#resultado').find('#tipo').text();
					oT.idDocumento=$('#resultado').find('#id').text();
					oT.producto ={id:$('#autorizar').find('#idProducto').val()};
					oT.observacion=$('#autorizar').find('#observaciones').val();
					oT.tipoCambioJT={codigo:$('input[name=motivo]:checked').val()};
					oT.usuarioCambio = usuario;
					
					if(oT.tipoCambioJT.codigo == 'FR'){
						oT.numeroSerie=$('#numeroSerie').val();
					}
					
					//if(oT.tipoCambioJT.codigo == 'VA'){
						$('#popupAutorizarCambio').dialog('close');
						$('#popupCliente').dialog('open');
						$('#cliente').reset();
						$('#idOTCliente').text(' ');
						$('#cliente').find('#comuna\\.id').empty();
						$('#btnReporteCambio').attr('disabled',true);
						$('#btnImprimirTicketCambio').attr('disabled',true);
						$('#btnTicketCambio').attr('disabled',false);  
						$('#buscarCliente').attr('disabled',false);
						loadComunas();
						oTGeneral = oT;
					//} else {
						/*SSTFacade.saveOTJT(oT,{async:true,callback:function(oT){
							if(oT.tipoCambioJT.codigo == 'FF'){
								parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
							} else if(oT.tipoCambioJT.codigo == 'FR'){
								parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
							}
						}});*/
					//}
				}
			}});
		}
	});
	
	$('#btnValidar').click(function(){
		if($('#registrar').valid()){ 
			var cambioAutomaticoProveedorCarta = {};
			var oT = {};
			cambioAutomaticoProveedorCarta = $('#registrar').serializeObject();
//			oT = $('#buscarDocumento').serializeObject();
			oT.idDocumento = $('#resultado').find('#id').text();
			oT.tipoDocumento = $('#resultado').find('#tipo').text();
			oT.producto = producto;
			oT.conOsinProductoFisico = cheBoxSinProdFisico;
			//alert("El valor de oT.conOsinProductoFisico: "+oT.conOsinProductoFisico);
			//alert("El valor de oT.id: "+oT.id);
			if(oT.conOsinProductoFisico == "CON"){
				SSTFacade.saveOTCambioPorProveedorCertificado(oT,cambioAutomaticoProveedorCarta,{async:true,callback:function(oT,cambioAutomaticoProveedorCarta){			
					parent.location= context + "/index.do?e=" + moduloAccesorios.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
				}});
			}
			if(oT.conOsinProductoFisico == "SIN"){
				SSTFacade.saveOTCambioPorProveedorCertificado(oT,cambioAutomaticoProveedorCarta,{async:true,callback:function(oT,cambioAutomaticoProveedorCarta){			
					parent.location= context + "/index.do?e=" + moduloTerminar.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
				}});
			}
			
		}	
	});
	
	/*LISTAR REGIONES Y COMUNAS*/
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#comuna\\.provincia\\.region\\.id').addItems(data, "id", "glosa", true);
		loadComunas($('#comuna\\.provincia\\.region\\.id').val());
	}});
	
	$('#comuna\\.provincia\\.region\\.id').change(function(){
		loadComunas($(this).val());
	});
	/*FIN LISTAR REGIONES Y COMUNAS*/
	
	$('#buscarCliente').click(function(){
		//alert("0.- document.cliente.banderaOrigen.value: "+document.cliente.banderaOrigen.value);
		/*if(document.cliente.banderaOrigen.value != ""){
			btnOrigen = document.cliente.banderaOrigen.value;
		}*/
			
		if($('#rut').valid()){
			var clientePaso = {rut:$('#rut').val()};
			SSTFacade.getClienteByRut(clientePaso,{async:true,callback:function(cliente){
				$('#cliente').reset();
				loadComunas(null);
				if (cliente == null) {
					cliente = clientePaso;
					jAlert('No existe un cliente con este rut en el sistema.','Información');
				} else {
					if (cliente.comuna != null && cliente.comuna.provincia != null && cliente.comuna.provincia.region != null) {
						loadComunas(cliente.comuna.provincia.region.id);
					}
				}
				$('#cliente').loadObject(cliente);
				validaClienteForm();
			}});
		}
	});
	
	$('#btnImprimirTicketCambio').click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=TicketCambioReport" +
		"&idOT=" + oTGeneral.id;
		$.openWindowsMenubar(url, "TicketCambioReport", 600, 800);	
	});
	
	$('#btnReporteCambio').click(function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=InformeCambioProductoReport" +
		"&idOT=" + oTGeneral.id;
		$.openWindowsMenubar(url, "InformeCambioProductoReport", 600, 800);
	});
	
	$('#btnTicketCambio').click(function(){
		if(validaClienteForm()){
			oTGeneral.tipoDocumento=$('#resultado').find('#tipo').text();
			oTGeneral.idDocumento=$('#resultado').find('#id').text();
			if(oTGeneral.producto == null){
				oTGeneral.producto = producto;
			}
			oTGeneral.cliente = $('#cliente').serializeObject();
			
			//alert("3.- var buscarCliente: "+buscarCliente);
			if(btnOrigen != ""){
				//alert("4.- var buscarCliente: "+buscarCliente);
				oTGeneral.banderaOrigenOT = btnOrigen;
				//alert("oTGeneral.banderaOrigen: "+oTGeneral.banderaOrigen);
			}
						
			/*alert("1 ---> " + oTGeneral.tipoCambio.codigo);
			alert("2 ---> " + tipoCambioAutomatico);*/
			
			/*if(oTGeneral.tipoCambio != null && oTGeneral.tipoCambio.codigo=='JT'){
				SSTFacade.saveOTJT(oTGeneral,"TC",function(OT){
					oTGeneral = OT;
					$('#btnTicketCambio').attr('disabled',true);
					$('#btnReporteCambio').attr('disabled',false);
					$('#btnImprimirTicketCambio').attr('disabled',false);
					$('#buscarCliente').attr('disabled',true);
				});
			} else if (tipoCambioAutomatico == "VA") {*/
				SSTFacade.saveOTGenerico(oTGeneral,"TC",function(OT){
					oTGeneral = OT;
					$('#idOTCliente').text(OT.id);
					$('#btnTicketCambio').attr('disabled',true);
					$('#btnReporteCambio').attr('disabled',false);
					$('#btnImprimirTicketCambio').attr('disabled',false);
					$('#buscarCliente').attr('disabled',true);
				});
			//} 
			/*else if (tipoCambioAutomatico == "FF" || tipoCambioAutomatico == "FR"){
				alert(tipoCambioAutomatico);
				SSTFacade.saveOTJT(oT,{async:true,callback:function(oT){
					if(oT.tipoCambioJT.codigo == 'FF'){
						parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
					} else if(oT.tipoCambioJT.codigo == 'FR'){
						parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
					}
				}});
			}*/
		}
	});
});


var loadComunas = function(idRegion) {
	$('#comuna\\.id').empty();
	SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas) {
		$('#comuna\\.id').addItems(comunas,"id","glosa",true);
	}});
};

var validaClienteForm = function(){
	var valid;
	var contactoTel=new Array;
	contactoTel.push($('#cliente').find('#telefono'));
	contactoTel.push($('#cliente').find('#celular'));
	valid = $('#cliente').valid() && validEmptyInputs(contactoTel,'debe ingresar al menos un contacto telefonico');
	return valid;
};

var crearOT = function(idPr){
	var filterTipoCambio = {};
	filterTipoCambio.producto={id:idPr};
	filterTipoCambio.documento={id:$('#resultado').find('#id').text(), tipo:$('#resultado').find('#tipo').text()};
	var params ='idProducto='+idPr+'&idDocumento=' + $('#resultado').find('#id').text() +'&tipoDocumento=' + $('#resultado').find('#tipo').text();
	parent.location = context +'/index.do?e='+moduloProveedor.codigo+'&m='+moduloInicial.codigo + '&' + params;	
};


var cambioPorProveedor = function(idProducto){
	var oT={};
	oT.tipoDocumento=$('#resultado').find('#tipo').text();
	oT.idDocumento=$('#resultado').find('#id').text();
	oT.producto ={id:idProducto};
	//alert("-----> CU-08, btnOrigen: "+btnOrigen);
	//oT.banderaOrigenOT = btnOrigen;
	SSTFacade.saveOTCambioPorProveedor(oT,{async:true,callback:function(oT){
		parent.location= context + "/index.do?e=" + moduloAccesorios.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
	}});
};

var cambioMenor24Hrs = function(idProducto){
	var oT={};
	oT.tipoDocumento=$('#resultado').find('#tipo').text();
	oT.idDocumento=$('#resultado').find('#id').text();
	oT.producto ={id:idProducto};
	SSTFacade.saveOTCambioPorVentaMenor24Hr(oT,{async:true,callback:function(oT){
		parent.location= context + "/index.do?e=" + moduloAccesorios.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
	}});
};

var cambioPorValor = function(idProducto){
	$('#popupCliente').dialog('open');
	$('#cliente').reset();
	$('#idOTCliente').text(' ');
	//('#banderaOrigen').val('C6');
	$('#btnTicketCambio').attr('disabled',false);
	$('#btnReporteCambio').attr('disabled',true);
	$('#btnImprimirTicketCambio').attr('disabled',true);
	$('#buscarCliente').attr('disabled',false);

	tipoCambioAutomatico = "VA";
	producto = {id:idProducto};
};

var registrarCambioAutomatico = function(idPr){
	producto = {id:idPr};
	$('#registrar').reset();
	SSTFacade.isFamiliaExcluidaNumeroSerieByProducto(producto,function(resp){
		$('#registrar').find('#numeroSerie').attr('disabled',resp);				
		$('#registrar').find('.numeroSerie').show();
		$('#popupRegistrarCambio').dialog('open');
	});
};

var autorizarCambioJT = function(idPr){
	$('#autorizar').find('#idProducto').val(idPr);
	SSTFacade.getProductoById(idPr,{async:true,callback:function(producto){
		SSTFacade.getReglaComercialCompleta(producto,{async:true,callback:function(reglas){
			if(reglas!=null){
				var motivo;
				//alert("1 reglas.fallaFabricacion: "+reglas.fallaFabricacion); //object object
				if(reglas.fallaFabricacion == null){
					SSTFacade.isFamiliaExcluidaFallaFabricacionByProducto(producto,{async:false,callback:function(excluida){
						//alert("2 excluida: "+excluida); //no entro
						if (excluida == false) {
							$('.fallaFabricacion').css('display','none');							
						}
					}});
				}
				
				//alert("3 reglas.cambioAutomatico: "+reglas.cambioAutomatico); //null
				//alert("4 reglas.cambioAutomatico.precioLimite: "+reglas.cambioAutomatico.precioLimite);//se cayo no se puede leer properties precioLimite
				if(reglas.cambioAutomatico!=null && reglas.cambioAutomatico.precioLimite!=null){
					motivo = 'CAMBIO ESPECIAL POR VALOR MENOR A $'+reglas.cambioAutomatico.precioLimite;
					$('#precioLimiteGlosa').text(motivo);
				} else {
					$('.precioLimite').css('display','none');
				}
				
				//alert("5 reglas.cambioAutomatico: "+reglas.cambioAutomatico);
				//alert("6 reglas.cambioAutomatico.diasTope: "+reglas.cambioAutomatico.diasTope);
				if(reglas.cambioAutomatico!=null && reglas.cambioAutomatico.diasTope!=null){
					motivo = 'Falla reiterada después de '+ reglas.cambioAutomatico.diasTope +' días';
					$('#fallaReiteradaGlosa').text(motivo);
				} else {
					$('.fallaReiterada').css('display','none');
				}
			}
			$('#fallaFabricacion').attr('checked','');
			$('.numeroSerie').css('display','none');
			$('.numeroSerie').val('');
			$('#observaciones').val('');
			//$('#banderaOrigen').val('C9');
			$('#popupAutorizarCambio').dialog('open');
		}});
	}});
};

var fallaFabricacion = function(idProducto){ 
	oT={producto:{id:idProducto},idDocumento:$('#resultado').find('#id').text(),tipoDocumento:$('#resultado').find('#tipo').text()};
	
	SSTFacade.validarFallaFabricacion(oT,{async:true,callback:function(data){
		if(data == true){
			oT={};
			oT.tipoDocumento=$('#resultado').find('#tipo').text();
			oT.idDocumento=$('#resultado').find('#id').text();
			oT.producto ={id:idProducto};
			SSTFacade.saveOTFallaFabricacion(oT,{async:true,callback:function(oT){
				parent.location= context + "/index.do?e=" + moduloAccesorios.codigo+"&m="+moduloInicial.codigo+"&idOT="+oT.id;
			}});
		}
	}});
};

var evaluacionProducto = function(idProducto){	

	oT={producto:{id:idProducto},idDocumento:$('#resultado').find('#id').text(),tipoDocumento:$('#resultado').find('#tipo').text()};
	
	SSTFacade.saveOTParaEvaluacion(oT,{async:true, callback:function(ordenTrabajo){
		parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+ordenTrabajo.id;
	}});
};

var evaluacionPorCliente = function(idProducto){
	oT={producto:{id:idProducto},idDocumento:$('#resultado').find('#id').text(),tipoDocumento:$('#resultado').find('#tipo').text()};
	SSTFacade.saveOTParaEvaluacion(oT,{async:true, callback:function(ordenTrabajo){
		parent.location= context + "/index.do?e=" + moduloFallas.codigo+"&m="+moduloInicial.codigo+"&idOT="+ordenTrabajo.id;
	}});
};
