$(document).ready(function() {
	$('.fecha').datepicker();
	

	$('#configuracion_general').validate();
	$('#fechaInicio').rules('add',{dateRangeMin:'#fechaTermino'});
	$('#fechaTermino').rules('add',{dateRangeMax:'#fechaInicio'});
	
	$('#limpiar').click(function(){
		limpiar();
	});
	
	var limpiar = function() {
		$('form').reset();
		$('#chkLinea').attr('checked',false);
		$('#chkZona').attr('checked',false);
		$("#productoFamiliaLinea, #zonaTienda").jstree('uncheck_all');
		$('#zonaTienda').show();
		$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas,#fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #cambioAutomatico\\.precioLimite' ).attr('readonly', false);
		$('#reglaCambioProoveedor\\.notaProoveedor').attr('readonly', true);
	};
	
	$('#reglaCambioProoveedor\\.autorizadoProveedor').click(function(){
		$('#chkZona').attr('checked',false);
		$('#chkZona').attr('disabled',$(this).is(':checked'));
		$("#zonaTienda").jstree('uncheck_all');
		$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas, #fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #cambioAutomatico\\.precioLimite, #reglaCambioProoveedor\\.notaProoveedor' ).val('');
		$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas, #fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #cambioAutomatico\\.precioLimite, #reglaCambioProoveedor\\.notaProoveedor' ).removeClass('error');
		
		$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas, #fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #cambioAutomatico\\.precioLimite' ).attr('readonly', $(this).is(':checked'));
		$('#reglaCambioProoveedor\\.notaProoveedor').attr('readonly', !$(this).is(':checked'));
		if ($(this).is(':checked')) {
			$('#zonaTienda').hide();
		} else {
			$('#zonaTienda').show();
		}
	});
	
	$('#grabar').click(function(){
		var isCambioPorValor = false;
		var isCambioPorFallasReiteradas = false;
		var isCambioPorFallasFabricacion = false;
		var isCambioPorProoveedor = false;
		var isCambioCertificacionFalla = false;
		
		var isValidCambioPorValor = false;
		var isValidCambioPorFallasReiteradas = false;
		var isValidCambioPorFallasFabricacion = false;
		var isValidCambioPorProoveedor = false;
		var isValidCambioCertificacionFalla = false;
		
		var isValidRegla = true;
		$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas, #fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #notaProoveedor,#certificacionFalla\\.inicio, #certificacionFalla\\.termino').removeClass('error');
		
		var cambioPorValor = $('#cambioPorValor').serializeObject();
		var cambioPorFallasReiteradas = $('#cambioPorFallasReiteradas').serializeObject();
		var cambioPorFallasFabricacion = $('#cambioPorFallasFabricacion').serializeObject();
		var cambioPorAutorizacionDeProoveedor = $('#cambioPorAutorizacionDeProoveedor').serializeObject();
		var cambioPorCertificacionFalla = $('#cambioPorCertificacionFalla').serializeObject();
		
		if (cambioPorValor.cambioAutomatico.precioLimite != "") {
			isValidCambioPorValor = $('#cambioPorValor').valid();
			isCambioPorValor = true;
		}
		
		if (cambioPorFallasReiteradas.cambioAutomatico.numeroFallas != "" || cambioPorFallasReiteradas.cambioAutomatico.diasTope != "") {
			isValidCambioPorFallasReiteradas = $('#cambioPorFallasReiteradas').valid();
			if (cambioPorFallasReiteradas.cambioAutomatico.numeroFallas == "") {
				isValidCambioPorFallasReiteradas = false;
				$('#cambioPorFallasReiteradas').find('#cambioAutomatico\\.numeroFallas').addClass('error');
				jAlert('Para configurar una falla reiterada, ingrese los valores Número de Fallas y Periodo');
			}
			if (cambioPorFallasReiteradas.cambioAutomatico.diasTope == "") {
				isValidCambioPorFallasReiteradas = false;
				$('#cambioPorFallasReiteradas').find('#cambioAutomatico\\.diasTope').addClass('error');
				jAlert('Para configurar una falla reiterada, ingrese los valores Número de Fallas y Periodo');
			} 
			isCambioPorFallasReiteradas = true;
		}
		
		if (cambioPorFallasFabricacion.fallaFabricacion.precioLimite != "" || cambioPorFallasFabricacion.fallaFabricacion.diasTope != "") {
			isValidCambioPorFallasFabricacion = $('#cambioPorFallasFabricacion').valid();
			if (cambioPorFallasFabricacion.fallaFabricacion.precioLimite == "") {
				isValidCambioPorFallasFabricacion = false;
				$('#cambioPorFallasFabricacion').find('#fallaFabricacion\\.precioLimite').addClass('error');
				jAlert('Para configurar una falla de fabricación, ingrese los valores Precio Límite y Número de días');
			}
			if (cambioPorFallasFabricacion.fallaFabricacion.diasTope == "") {
				isValidCambioPorFallasFabricacion = false;
				$('#cambioPorFallasFabricacion').find('#fallaFabricacion\\.diasTope').addClass('error');
				jAlert('Para configurar una falla de fabricación, ingrese los valores Precio Límite y Número de días');
			} 
			isCambioPorFallasFabricacion = true;
		}
		
		if (cambioPorAutorizacionDeProoveedor.reglaCambioProoveedor.autorizadoProveedor == "true") {
			isValidCambioPorProoveedor = $('#cambioPorAutorizacionDeProoveedor').valid();
			if (cambioPorAutorizacionDeProoveedor.reglaCambioProoveedor.notaProoveedor == "") {
				isValidCambioPorProoveedor = false;
				$('#cambioPorAutorizacionDeProoveedor').find('#reglaCambioProoveedor\\.notaProoveedor').addClass('error');
				jAlert('Para configurar una autorización por prooveedor, ingrese un motivo de cambio');
			}
			if (isValidCambioPorProoveedor) {
				var lineas = $('#productoFamiliaLinea').jstree('get_json');
				if(lineas.length == 0) {
					isValidCambioPorProoveedor = false;
					jAlert('Para configurar una autorización por prooveedor, seleccione al menos un producto');
				}
			}
			isCambioPorProoveedor = true;
		} else {
			cambioPorAutorizacionDeProoveedor.reglaCambioProoveedor.autorizadoProveedor = "false";
		}
		
		if(cambioPorCertificacionFalla.certificacionFalla.inicio != "" && cambioPorCertificacionFalla.certificacionFalla.termino != ""){
			isValidCambioCertificacionFalla = $('#cambioPorCertificacionFalla').valid();
			if(cambioPorCertificacionFalla.certificacionFalla.inicio == ""){
				isValidCambioCertificacionFalla = false;
				$('#cambioPorCertificacionFalla').find('#certificacionFalla\\.inicio').addClass('error');
				jAlert('Para configurar un cambio de certifición de falla, ingrese dias de inicio');
			}
			if(cambioPorCertificacionFalla.certificacionFalla.termino == ""){
				isValidCambioCertificacionFalla = false;
				$('#cambioPorCertificacionFalla').find('#certificacionFalla\\.termino').addClass('error');
				jAlert('Para configurar un cambio de certifición de falla, ingrese dias de termino');
			}
			isCambioCertificacionFalla = true;
		}
		if (!(isCambioPorValor || isCambioPorFallasReiteradas || isCambioPorValor || isCambioPorProoveedor || isCambioPorFallasFabricacion || isCambioCertificacionFalla)) {
			jAlert('Configure por lo menos una regla de cambio automatico');
			return;
		} else {
			if (isCambioPorValor && !isValidCambioPorValor) {
				isValidRegla = false;
			}
			if (isCambioPorFallasFabricacion && !isValidCambioPorFallasFabricacion) {
				isValidRegla = false;
			}
			if (isCambioPorFallasReiteradas && !isValidCambioPorFallasReiteradas) {
				isValidRegla = false;
			}
			if (isCambioPorFallasReiteradas && !isValidCambioPorFallasReiteradas) {
				isValidRegla = false;
			}
			if (isCambioPorProoveedor && !isValidCambioPorProoveedor) {
				isValidRegla = false;
			}
			if (isCambioCertificacionFalla && !isValidCambioCertificacionFalla) {
				isValidRegla = false;
			}
		}
		
		if($('#configuracion_general').valid() && isValidRegla) {
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro que desea grabar los cambios?', 'Confirmación', function(r){
				if (r) {
					var regla = $('#configuracion_general').serializeObject();
					regla.zonas = $('#zonaTienda').jstree('get_json');
					regla.lineas = $('#productoFamiliaLinea').jstree('get_json');
					
					regla.cambioAutomatico = cambioPorFallasReiteradas.cambioAutomatico;
					regla.cambioAutomatico.precioLimite = cambioPorValor.cambioAutomatico.precioLimite;
					
					regla.fallaFabricacion = cambioPorFallasFabricacion.fallaFabricacion;
					regla.reglaCambioProoveedor = cambioPorAutorizacionDeProoveedor.reglaCambioProoveedor;
					
					regla.certificacionFalla = {
						inicio	: cambioPorCertificacionFalla.certificacionFalla.inicio,
						termino : cambioPorCertificacionFalla.certificacionFalla.termino
					};
					SSTFacade.saveReglaComercial(regla,{async:false,callback:function(id){
						$('#reglasComerciales').flexReload();
						$('#configuracion_general').find('#id').val(id);
						jAlert('Regla Grabada','Información');
					}});
				}
			});
		}
	});
	
	$('#descripcion, #notaProoveedor').maxlength({  
	    events: [], // Array of events to be triggered 
	    maxCharacters: 1024, // Characters limit  
	    status: true, // True to show status indicator below the element
	    statusClass: "hideLabelStatus", // The class on the status div
	    statusText: "caracteres restantes", // The status text 
	    notificationClass: "error",  // Will be added when maxlength is reached
	    showAlert: false, // True to show a regular alert message
	    alertText: "La cantidad máxima de caracteres(2000), ha sido sobrepasada.", // Text in alert message
	    slider: false // True Use counter slider   
	  });
	
	$('#chkZona').change(function(){
		if (!$(this).is(':checked')) {
			$("#zonaTienda").jstree('uncheck_all');
		} else {
			$("#zonaTienda").jstree('check_all');
		}
	});
	
	$('#chkLinea').change(function(){
		if (!$(this).is(':checked')) {
			$("#productoFamiliaLinea").jstree('uncheck_all');
		} else {
			$("#productoFamiliaLinea").jstree('check_all');
		} 	
	});
	
	var exportDocument = function(type){
		var p = $('#reglasComerciales').getParameters()[0];
		var f = $('#reglasComerciales').getFilterColumna()[0];
		p.orderBy = $('#reglasComerciales').getSortName()[0];
		p.sortOrder = $('#reglasComerciales').getSortOrder()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ReglasComercialesReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ReglasComercialesReport", 600, 800);
	};
	
	var getReglaComercial = function (id) {
		limpiar();
		SSTFacade.getReglaComercialById(id,function(regla){
			$('#configuracion_general').loadObject(regla);
			$('#cambioPorValor').loadObject(regla);
			$('#cambioPorFallasFabricacion').loadObject(regla);
			$('#cambioPorFallasReiteradas').loadObject(regla);
			$('#cambioPorAutorizacionDeProoveedor').loadObject(regla);
			$('#cambioPorCertificacionFalla').loadObject(regla);
			createJSTreeZonas(regla.zonas);
			createJSTreeLineas(regla.lineas);
			if(regla.reglaCambioProoveedor != null && regla.reglaCambioProoveedor.autorizadoProveedor != null && regla.reglaCambioProoveedor.autorizadoProveedor) {
				$('#zonaTienda').hide();
				$('#cambioAutomatico\\.diasTope, #cambioAutomatico\\.numeroFallas,#fallaFabricacion\\.diasTope, #fallaFabricacion\\.precioLimite, #cambioAutomatico\\.precioLimite' ).attr('readonly', true);
				$('#reglaCambioProoveedor\\.notaProoveedor').attr('readonly', false);
			}
		});
	};
	
	$('#reglasComerciales').flexigrid({
		dwrFunction: SSTFacade.listReglasComercialesByFilter,
		seccion: 60001000,
		showTableToggleBtn: true,
		tipo: 'regla',
		multisel:false,
		singleSelect:true,
		height:'auto',
		overrideModel: [{findName:'vigente',propiedad:function(o){return o.vigente ? 'ACTIVA' : 'INACTIVA';}}],
		dblclickFunction:function(el,idx,comp){
			getReglaComercial(el.id);
		},
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true}
   		],
	});
	
	$('#reglasComerciales').loadData([{}]);
	
	var loadTreeViewLinea = function(lin){
		if(lin.is('.jstree-leaf')) {
			if (lin.data("tipo") === 'LINEA'){
				SSTFacade.listFamiliaByFilterAsTreeView({idLinea:lin.data("id")},function(sucursales){
					$.each(sucursales,function(){
						$('#productoFamiliaLinea').jstree("create_node", lin, 'inside', arguments[1], false, false);
					});
				});
			}
			if (lin.data("tipo") === 'FAMILIA') {
				$.alerts.okButton = '&nbsp;Continuar&nbsp;';
				$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
				jConfirm('Listar los productos puede tardar varios minutos. ¿Desea Continuar?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.listProductoByFilterAsTreeView({idFamilia:lin.data("id")},function(sucursales){
							$.each(sucursales,function(){
								$('#productoFamiliaLinea').jstree("create_node", lin, 'inside', arguments[1], false, false);
							});
						});
					}
				});
			}
		}
	};
	
	var loadTreeViewZona = function(zona){
		if(zona.is('.jstree-leaf')) {
			if (zona.data("tipo") === 'ZONA'){
				SSTFacade.listSucursalesByZonaAsTreeView(zona.data("id"),function(sucursales){
					$.each(sucursales,function(){
						$('#zonaTienda').jstree("create_node", zona, 'inside', arguments[1], false, false);
					});
				});
			}
		}
	};
	
	var createJSTreeLineas = function(lineas) {
		if ($("#productoFamiliaLinea").is('.jstree')) {
			$("#productoFamiliaLinea").jstree('destroy');
		}
		$("#productoFamiliaLinea").jstree({
			"json_data" : {"data" :lineas},
	        "themes" : {
	            "theme" : "default",
	            "dots" : true,
	            "icons" : false
	        },
	        "ui" : {
				"select_limit" : 1
			},
	        "plugins" : [ "themes", "json_data", "checkbox", "ui"]
		}).bind("select_node.jstree", function (e, data) { 
			$("#productoFamiliaLinea").jstree('check_node',data.rslt.obj);
			loadTreeViewLinea(data.rslt.obj);
	    }).bind("check_node.jstree", function (e, data) {
	    	loadTreeViewLinea(data.rslt.obj);
	    }).bind("uncheck_node.jstree", function (e, data) {
	    	loadTreeViewLinea(data.rslt.obj);
	    });
	};
	
	var createJSTreeZonas = function(zonas) {
		if ($("#zonaTienda").is('.jstree')) {
			$("#zonaTienda").jstree('destroy');
		}
		$("#zonaTienda").jstree({
			"json_data" : {"data" : zonas},
	        "themes" : {
	            "theme" : "default",
	            "dots" : true,
	            "icons" : false
	        },
	        "ui" : {
				"select_limit" : 1
			},
	        "plugins" : [ "themes", "json_data", "checkbox", "ui"]
		}).bind("select_node.jstree", function (e, data) { 
			$("#zonaTienda").jstree('check_node',data.rslt.obj);
			loadTreeViewZona(data.rslt.obj);
	    }).bind("check_node.jstree", function (e, data) {
	    	loadTreeViewZona(data.rslt.obj);
	    }).bind("uncheck_node.jstree", function (e, data) {
	    	loadTreeViewZona(data.rslt.obj);
	    });
	};
	
	SSTFacade.listLineaAsTreeView({async:true,callback:function(lineas){
		createJSTreeLineas(lineas);
	}});
	
	SSTFacade.listZonasAsTreeView({async:true,callback:function(zonas){
		createJSTreeZonas(zonas);
	}});
	
});