$(document).ready(function() {
	$('.fecha').datepicker();
//	$('#sucursal').selectInput();
	
	var buscadorCargado= false;
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	$('#popup').dialog({
		autoOpen: false,
		modal:true,
		width:500,
		buttons:{
			Grabar: function() {
				var sel = $('#resultados').getJSONCheckboxSelected();
				var ejecutiva = $('#idEjecutivaTraspasar').val();
				$.alerts.okButton = '&nbsp;Si&nbsp;';
				$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
				jConfirm('¿Esta seguro que desea asignar a esta ejecutiva la(s) orden(es) de trabajo?', 'Confirmación', function(r){
					if (r) {
						SSTFacade.moverOrdenSupervisora(sel, ejecutiva, {async:false,callback:function(p){
							$('#popup').dialog('close');
							$.alerts.okButton = '&nbsp;Ok&nbsp;';
							$('#resultados').flexReload();
							jAlert('Orden(es) de trabajo asignada(s) a Hoja de Trabajo de ' + p.nombreCompleto.toUpperCase(),'Información');
						}});;
					}
				});
			},
			Cerrar: function(){
				$('#popup').dialog('close');
			}
		}	
	});
	
	var cargaBuscador = function() {
		if (!buscadorCargado) {

			SSTFacade.listEstadosOT({async:true, callback:function(estadosOT){
				$('#estadoOT').addItems(estadosOT,'id','glosa',true);
			}});
			
			SSTFacade.listTiposUbicacion({async:true, callback:function(ubicacionesOT){
				$('#tipoUbicacion').addItems(ubicacionesOT,'codigo','glosa',true);
			}});
				
			SSTFacade.getModuloByNombreUsuario('onbodegaordentrabajohistorial',{async:false, callback:function(modulo) {
				moduloHistorial = modulo ? modulo : undefined;
			}});
			
			SSTFacade.ListServiciosTecnicosBuscadorOT({idOrigen:10015},{async:true,callback:function(sTecnicos){
				$("#idSTecnico").addItems(sTecnicos, "id", ["glosa","direccion"], true);
			}});
			
			SSTFacade.listSucursales({async:true,callback:function(sucursales){
				$('#sucursal').addItems(sucursales,"id","nombre",true);
			}});
			
			SSTFacade.listFamilia({sync:true,callback:function(familias){
				$('#idFamilia').addItems(familias,"id","nombre",true);
			}});
			
			SSTFacade.listMarca({async:true,callback:function(marcas){
				$('#idMarca').addItems(marcas,"codigo","nombre",true);
			}});
			
			SSTFacade.listTiposOT({async:true,callback:function(data){
				$("#tipoOT").addItems(data, "codigo", "glosa", true);
			}});
			
			SSTFacade.listTiposOTOT({async:true,callback:function(data){
				$("#ot").addItems(data, "codigo", "glosa", true);
			}});
			
			SSTFacade.listTiposSemaforo({async:true,callback:function(data){
				$("#semaforo").addItems(data, "codigo", "glosa", true);
			}});

			SSTFacade.listEjecutivasMarca({async:true,callback:function(data){
				$("#idEjecutivaTraspasar").addItems(data, "id",["nombre", "apellidoPaterno"],true);
				data.push({id:-1,nombre:'SIN EJECUTIVA',apellidoPaterno:""});
				data.reverse();
				$("#idEjecutiva").addItems(data, "id", ["nombre", "apellidoPaterno"], true);
			}});
			buscadorCargado = true;
		}
	};
	
	$('#limpiar_avanzado').click(function(){
		$('#filtro_buscador_avanzado').reset();
		$('#resultados').clean();
	});
	
	$('#limpiar_basico').click(function(){
		$('#filtro_buscador_basico').reset();
		$('#resultados').clean();
	});
	
	$('#buscar_avanzado').click(function(){
		buscarAvanzado();
	});
	
	$('#buscar_basico').click(function(){
		buscarBasico();
	});
	
	$('#change_buscador_avanzado').click(function(){
		$('#buscador_basico').hide();
		$('#buscador_avanzado').show();
		cargaBuscador();
	});
	
	$('#change_buscador_basico').click(function(){
		$('#buscador_basico').show();
		$('#buscador_avanzado').hide();
	});
	
	$('#buscador_basico').keypress(function(e){
		if(e.which == 13) {
			buscarBasico();
		}
	});
	
	$('#buscador_avanzado').keypress(function(e){
		if(e.which == 13) {
			buscarAvanzado();
		}
	});
	
	var buscarBasico = function() {
		var validar = [];
		validar.push($('#filtro_buscador_basico').find('#idOT'));
		validar.push($('#filtro_buscador_basico').find('#numeroSerie'));
		validar.push($('#filtro_buscador_basico').find('#idEtapa'));
		if($('#filtro_buscador_basico').valid() && validEmptyInputs(validar,'debe ingresar alguno de los parametros de busqueda')) {
			var filter = $('#filtro_buscador_basico').serializeObject();
			$('#resultados').showBody();
			$('#resultados').loadData([filter]);
		}
	};
	
	var buscarAvanzado = function() {
		if($('#filtro_buscador_avanzado').valid()){
			$('#resultados').showBody();
			var filter = $('#filtro_buscador_avanzado').serializeObject();
			$('#resultados').loadData([filter]);
		}
	};

	var moverHojaTrabajoEjecutiva = function(){
		var sel = $('#resultados').getJSONCheckboxSelected();
		if(sel.length < 1){
			jAlert("No se ha seleccionado ninguna Orden de Trabajo",'Información');
			return;
		} else {
			$('#popupForm').reset();
			$('#popup').dialog('open');			
		}		
	};
	
	var moverMyHojaTrabajo = function(){
		var sel = $('#resultados').getJSONCheckboxSelected();
		if(sel.length < 1){			
			jAlert("No se ha seleccionado ninguna Orden de Trabajo",'Información');
			return;
		} else {
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Le seran asignadas las orden(es) de trabajo, desea continuar?', 'Confirmación', function(r){
				if (r) {
					SSTFacade.moverOrden(sel, {async:false,callback:function(){
						$.alerts.okButton = '&nbsp;Ok&nbsp;';
						jAlert('Orden(es) de trabajo asignada(s) a mi Hoja de Trabajo ','Información');
					}});
					$('#resultados').flexReload();		
					$('#hojaTrabajoEjecutivaMarca').flexReload();	
				}
			});
		}
	};
	
	var exportDocumentOTEjecutiva = function(type, id){
		var p = $("#"+id).getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		
		p.orderBy = $("#"+id).getSortName()[0];
		p.sortOrder = $("#"+id).getSortOrder()[0];
		var f = $("#"+id).getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, id.toUpperCase() + "_OT", 600, 800);
	};
	
	var exportDocumentOT = function(type, id){
		var p = $("#"+id).getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		
		p.orderBy = $("#"+id).getSortName()[0];
		p.sortOrder = $("#"+id).getSortOrder()[0];
		var f = $("#"+id).getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, id.toUpperCase() + "_OT", 600, 800);
	};
	
	var cambioPrioridad = function(type, id){
		var p = $("#"+id).getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}		
		var sel = $("#"+id).getJSONCheckboxSelected();
		if (sel.length == 0) {
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert("No se ha seleccionado ninguna Orden de Trabajo",'Información');
			return;
		}
		$.alerts.okButton = '&nbsp;Si&nbsp;';
		$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
		jConfirm('¿Desea ' + type + ' la prioridad de los registros seleccionados?', 'Confirmación', function(r){
			if (r) {
				savePrioridad(sel, id, type);
				$("#"+id).flexReload();
			}
		});
	};
	
	var savePrioridad = function(sel, id, type){
		if (sel == undefined)
			sel = $("#"+id).getJSONCheckboxSelected();
		SSTFacade.saveCambioPrioridadSemaforo(sel, type,{async:false,callback:function(){
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('Se han cambiado las prioridades de los semaforos','Información');
		}});
	};

	
	var crearGrilla = function(id,titulo,rol){
		$("#hojasTrabajo").append("<div class=marco_interior_tabla><div id="+id+"></div></div>");
			$("#"+id).flexigrid({
				dwrFunction: SSTFacade.listOTByFilter,
				seccion: 10005000, 
				tipo: 'OT2',
				title: titulo,
				showTableToggleBtn: true,
				multisel:false,
				singleSelect:true,
				dblclickFunction:function(el,idx,comp){
					redirectOtDetalle(el.id);
				},
				overrideModel: [
	    			{findName:'semaforoServicioTecnico',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoServicioTecnico) + '"></div>');}},
	    			{findName:'semaforoSucursalInicio',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalInicio) + '"></div>');}},
	    			{findName:'semaforoSucursalfin',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalfin) + '"></div>');}},
	    			{findName:'sucursal.glosa',propiedad:function(o){if (o.sucursal){ return o.sucursal.id + ' ' + o.sucursal.glosa;}}},
	    			{findName:'checkMarca',propiedad:function(o){return $('<input type="checkbox">').attr('id', 'chk' + o.id).attr('name', 'chk' + o.id).attr('checked',o.checkMarca);}}
	    		],
				buttons : [
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOTEjecutiva('pdf', id); }},
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOTEjecutiva('xls', id);}},
			   		{separator: true},
			   		{name: 'Bajar Prioridad', bclass: 'btnDownPrioridad', onpress : function(){cambioPrioridad('bajar', id);}},
			   		{separator: true},
			   		{name: 'Subir Prioridad', bclass: 'btnUpPrioridad', onpress : function(){cambioPrioridad('subir', id);}},
			   		{separator: true}
			   	]	
			});
			var filtro = {
				vigente:true,
				cerrada:false,
				idRol:rol
			};
			switch (filtro.idRol) {
			case 5:
				filtro.idEjecutiva = usuario.id;
				break;
			case 3:
				filtro.tipoOT = 'GP';
				filtro.cerradaCliente = false;
				break;
			case 70:
				filtro.tipoOT = 'GM';
				filtro.cambioAutorizado = true;
				filtro.productoRemate = false;
				break;
			case 7:
				filtro.tipoOT = 'GM';
				filtro.cambioAutorizado = false;
				break;
			default:
				break;
			}
			$('#'+id).loadData([filtro]);			
	};
	
	
	SSTFacade.listMyRoles({async:true,callback:function(roles){
		var isEjecutivaSupervisora = false;
		var isEjecutivaMarca = false;
		
		var btns = [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOT('pdf', 'resultados'); }},
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOT('xls', 'resultados');}},
	   		{separator: true}
        ];

		for(var i=0;i<roles.length;i++ ){
			var rol = roles[i].id;
			switch(rol){
				case 3: 
					crearGrilla("hojaTrabajoEjecutivaGestion","Hoja de trabajo: Ejecutiva de Gestión",rol);
					break;
				case 5: 
					crearGrilla("hojaTrabajoEjecutivaMarca","Hoja de trabajo: Ejecutiva de Marca",rol);
					isEjecutivaMarca = true;
					break;
				case 70: 
					crearGrilla("hojaTrabajoBodegaGarantiaMaster","Hoja de trabajo: Bodega de Garantía Master",rol);
					break;
				case 6: 
//					crearGrilla("hojaTrabajoEjecutivaFacturacion","Hoja de trabajo: Ejecutiva Facturacion",rol);
					break;	
				case 7: 
					crearGrilla("hojaTrabajoEjecutivaGarantiaMaster","Hoja de trabajo: Ejecutiva Garantía Master",rol);
					break;				
				case 8: 
					isEjecutivaSupervisora = true;
					break;
			}
		}
		
		if (isEjecutivaSupervisora) {
			btns.push({name: 'Asignar a Ejecutiva de Marca', bclass: 'btnMove', onpress : moverHojaTrabajoEjecutiva});
			btns.push({separator: true});
		}
		if (isEjecutivaMarca) {
			btns.push({name: 'Mover a mi Hoja de Trabajo', bclass: 'btnMove', onpress : moverMyHojaTrabajo});
			btns.push({separator: true});
		}
		
		$('#resultados').flexigrid({
			dwrFunction: SSTFacade.listOTByFilter,
			seccion: 10006000,
			showTableToggleBtn: true,
			title: 'Resultados',
			tipo: 'OT2',
			multisel:false,
			singleSelect:true,
			startMinimized:true,
			dblclickFunction:function(el,idx,comp){
				redirectOtDetalle(el.id);
			},
			overrideModel: [
    			{findName:'semaforoServicioTecnico',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoServicioTecnico) + '"></div>');}},
    			{findName:'semaforoSucursalInicio',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalInicio) + '"></div>');}},
    			{findName:'semaforoSucursalfin',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalfin) + '"></div>');}},
    			{findName:'sucursal.glosa',propiedad:function(o){if (o.sucursal){ return o.sucursal.id + ' ' + o.sucursal.glosa;}}},
    			{findName:'checkMarca',propiedad:function(o){return $('<input type="checkbox">').attr('id', 'chk' + o.id).attr('name', 'chk' + o.id).attr('checked',o.checkMarca);}}
    		],
			buttons : btns
		});
	}});
});
