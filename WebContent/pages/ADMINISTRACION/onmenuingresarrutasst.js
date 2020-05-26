$(document).ready(function(){
	
	var saveRuta = function(ruta){
		$('#mantenedorRutasForm').reset();
		$('#filtroST').reset();
		$('#listasST').reset();
		$('#allSTecnicos,#itsSTecnicos')
			.find('option')
	    	.remove()
	    	.end();
		
		if (ruta) {
			$('#mantenedorRutasForm').loadObject(ruta);
			$('#mantenedorRutas').find('#codigo').attr('readonly','readonly');
			SSTFacade.listServiciosTecnicosFromRutasSTByRuta(ruta,{asycn:true,callback:function(st){
				$('#mantenedorRutas').find('#itsSTecnicos').addItems(st,"id","nombre",false,", ");
			}});
		} else {
			$('#mantenedorRutas').find('#codigo').removeAttr('readonly');
		}
		SSTFacade.listRegiones({asycn:true,callback:function(regiones){
			$('#filtroST').find('#idRegion').addItems(regiones,"id","glosa",true);
		}});
		
		
		$('#mantenedorRutas').dialog('open');
	};
	
	
	$('#mantenedorRutas').dialog({
		autoOpen: false,
		modal:true,
		width:800,
		buttons:{
			Grabar: function() {
				if($('#mantenedorRutasForm').valid()) {
					var ruta = $('#mantenedorRutasForm').serializeObject();
					jConfirm('¿Esta seguro que desea grabar la ruta?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveRutaServicioTecnico(ruta,function(){
								rutaDetalle={};
								rutaDetalle.rutaServicioTecnico = ruta;
								SSTFacade.saveRutaServicioTecnicoDetalle(rutaDetalle, $('#itsSTecnicos').getAllItems(),{async:true,callback:function(){
									$("#rutas").flexReload();
									$('#mantenedorRutas').dialog('close');
									jAlert('Ruta grabada','Información');	
								}});
							});
						}
					});
				}
			},
			Cerrar: function(){
				$('#mantenedorRutas').dialog('close');
			}
		}
	});
	
	var exportDocument = function(type){
		var p = $('#rutas').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#rutas').getSortName()[0];
		p.sortOrder = $('#rutas').getSortOrder()[0];
		var f = $('#rutas').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=RutaServicioTecnicoReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "RutaServicioTecnicoReport", 600, 800);
	};
	
	$('#rutas').flexigrid({
		dwrFunction:SSTFacade.listRutasServicioTecnico,
		seccion:1000080000,
		userpager:true,
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		onToggleCol:false,
		tipo:'RUST',
		overrideModel: [
						{findName:'vigente',propiedad:function(o){
							
							if(o.vigente){
								return 'ACTIVO';
							}
								return 'INACTIVO';
						}},
		],
		buttons : [
		    {name:'Agregar Ruta', bclass:'btnPlus', onpress:function(){saveRuta();}},
		    {separator: true},
		    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true}
		    ],
		dblclickFunction:function(el,idx,comp){
			saveRuta(el);
		}
	});
	
	$('#rutas').loadData([{}]);
	
	$('#buscar').click(function(){
		$('#rutas').loadData([$('#buscador').serializeObject()]);
	});
	
	$('#limpiar').click(function(){
		$('#rutas').clean();
		$('#buscador').reset();
	});
	
	//Filtro ST
	$('#idRegion').change(function(){
		$('#idComuna')
	    	.find('option')
	    	.remove();
		
		$('#idComuna')
    		.find('option')
    		.remove();
		
		SSTFacade.listComunasConSucursalesByRegion($('#filtroST').find('#idRegion').val(),{asycn:true,callback:function(comunas){
			$('#filtroST').find('#idComuna').addItems(comunas,"id","glosa",true,undefined,undefined,undefined,"[SELECCIONE COMUNA");
		}});
	});
	
	$('#limpiarST').click(function(){
		$('#filtroST').reset();
		$('#filtroST').find('#idComuna').remove('option');
	});
	
	$('#buscarST').click(function(){
		if($('#filtroST').valid()){
			$('#allSTecnicos')
    			.find('option')
    			.remove();
			var idStecnicos=[];
			$.each($('#itsSTecnicos').find('option'),function(index,el){
				idStecnicos.push($(el).val());
			});
		
			var filtroST = $('#filtroST').serializeObject();
			filtroST.idStecnicos=idStecnicos;
			SSTFacade.listServiciosTecnicosForRutasSTByFilter(filtroST,{async:true,callback:function(st){
				$('#mantenedorRutas').find('#allSTecnicos').addItems(st,"id","nombre",false,", ");
			}});
			$('#mantenedorRutas').find('#allSTecnicos').removeItems($('#itsSTecnicos').getSelectedItems());
		}
	});
	
	
	$('#agregar').click(function(){
		$('#itsSTecnicos').removeClass('error');
		$('#itsSTecnicos').attr('title','');
//		$('#fallasProducto').find('#descripcionFalla').removeClass('error');
//		$('#fallasProducto').find('#descripcionFalla').attr('title','');
		var sel = $("#allSTecnicos").find(':selected');
		if (sel != null) change = true;
		$("#itsSTecnicos").append(sel);
	});
	
	$('#quitar').click(function(){
		var sel = $("#itsSTecnicos").find(':selected');
		if (sel != null) change = true;
		$("#allSTecnicos").append(sel);
	});
});

//var cambiarEstadoRuta = function(estado,id){
//	SSTFacade.updateEstadoRutaServicioTecnico({id:id,vigente:estado},{async:true,callback:function(){
//		jAlert('Se a actualizado la ruta','AVISO',function(){
//			$('#rutas').flexReload();
//		});
//	}});
//};