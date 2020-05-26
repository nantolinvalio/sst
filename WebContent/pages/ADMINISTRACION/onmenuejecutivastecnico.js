$(document).ready(function() {
	
	SSTFacade.listMarca({async:false,callback:function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	}});
	
	SSTFacade.listSucursales({async:false,callback:function(data){
		$("#idSucursal").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listProveedor({async:false,callback:function(data){
		$("#idProveedor").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listBodegas({async:false,callback:function(data){
		$("#idBodega").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listFamiliasByFilter({async:false,callback:function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	}});
	
	SSTFacade.listEjecutivasMarca({async:false,callback:function(data){
		$("#idEjecutiva").addItems(data, "id", "nombreCompleto", true);
		$("#usuario\\.id").addItems(data, "id", "nombreCompleto", true);
	}});
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$("#idRegion").addItems(data, "id", "glosa", true);
	}});
	
	var comuna = [{id:"",descripcion:"[SELECCIONE REGIÓN]"}];
	$("#idComuna").addItems(comuna, "id", "descripcion", false);
	
	$('#idRegion').change(function(){
		loadComuna();
	});
	
	var loadComuna = function(){
		var idRegion = $("#idRegion").val();
		var comuna = [];
		$('#idComuna').empty();
		$('#input-no-idComuna').val('');
		if (idRegion != "") {
			SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(data){
				$("#idComuna").addItems(data, "id", "glosa", true);
			}});
		} else {
			var productos = [{id:"",descripcion:"[SELECCIONE FAMILIA]"}];
			$("#idProducto").addItems(productos, "id", "descripcion", false);
		}
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listServicioTecnicoForEjecutiva,
		seccion: 1000400000,
		tipo: 'STEJ',
		multisel:false,
		singleSelect:true,
		showTableToggleBtn: true,
		overrideModel: [
		    {findName:'accion',propiedad:function(o){
		    	var btn = $('<input type="button">');
		    	if(o.usuario.id == null ){
					btn.attr('value','Asignar Ejecutiva');
		    	} else {
					btn.attr('value','modificar Ejecutiva');
		    	}
		    	btn.attr('onclick','nuevaEjecutiva("'+o.id+'")');
		    	return btn;
		    }},{findName:'usuario.nombreCompleto',propiedad:function(o){
		    	if(o.usuario == null){
		    		return '';
		    	} else {
		    		return o.usuario.nombreCompleto;
		    	}
		    }},
	   	],
	   	buttons : [
//			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
//			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
	});

	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscarSTecnico();
		}
	});
	
	 $('#buscar').click(function(){
		 buscarSTecnico();
	 });
	 
	 var exportDocument = function(type){
			var p = $('#resultados').getParameters()[0];
			if (p == undefined) {
				jAlert(msg.sinParametros,'Información');
				return;
			}
			p.orderBy = $('#resultados').getSortName()[0];
			p.sortOrder = $('#resultados').getSortOrder()[0];
			var f = $('#resultados').getFilterColumna()[0];
			var url = "/sst/ViewReportServlet?type=" + type + 
				"&report=EjecutivasServicioTecnicoReport" +
				"&filter=" + JSON.stringify(p) +
				"&filterColumn=" + JSON.stringify(f);
			$.openWindowsMenubar(url, "EjecutivasServicioTecnicoReport", 600, 800);
		};
	 
	 $('#limpiar').click(function(){
		 $('#filtro_buscador').reset();
		 $('#resultados').clean();
	 });
	
	var buscarSTecnico = function(){
		 if($('#filtro_buscador').valid()) {
			 var filtro = $('#filtro_buscador').serializeObject(); 
			 $('#resultados').loadData([filtro]);
		 } 
	};
	
	$('#mantenedorEjecutiva').dialog({
		autoOpen:false,
		modal:true,
		width:800,
	    buttons:{
	    	Grabar: function() {
	    		if($('#ejecutivaForm').valid()) {
	    			var sTecnicoEjecutiva = $('#ejecutivaForm').serializeObject(); 
	    			jConfirm('¿Esta seguro que desea grabar la ejecutiva?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.updateSTecnicoEjecutiva(sTecnicoEjecutiva, function(){
									$("#resultados").flexReload();									
								$('#mantenedorEjecutiva').dialog('close');
//								jAlert('Nuevo tipo de Falla Grabado','Información');																
							});
						}
					});
	    		}
			},	
			Cerrar: function(){
				$('#mantenedorEjecutiva').dialog('close');
			}
	   }
	});
});

var nuevaEjecutiva = function(id){
	var title;
	var stEjecutiva;
	SSTFacade.getExisteEjecutivaSTecnico(id,{async:false,callback:function(ejecutiva){
		stEjecutiva = ejecutiva;
		if(ejecutiva.usuario != null){
			title = 'MODIFICAR EJECUTIVA PARA SERVICIO TECNICO';
		} else{
			title = 'ASIGNAR EJECUTIVA PARA SERVICIO TECNICO';
		}
	}});
	$('#ejecutivaForm').reset();
	$('#ejecutivaForm').loadObject(stEjecutiva);
	$('#mantenedorEjecutiva').dialog('option','title',title);
	$('#mantenedorEjecutiva').dialog('open');			
};

var validaForm = function(){
	 var valid = false;
	 forms = $('#filtro_buscador').find('select , input[type=text]');
	 $.each(forms,function(index,form){
		   if(form.value != ""){
			   valid =true;
		   }
	 });
	 return valid;
};