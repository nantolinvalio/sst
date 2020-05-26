$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#filtro_buscador').validate();
	var tipoInicio ;
	
	$("#destinoFinal").attr('disabled','disabled');
	
	
	var moduloBuscarDespacho;
	SSTFacade.getModuloByNombreUsuario('onbodegabuscardespachos',{async:false, callback:function(modulo) {
		moduloBuscarDespacho = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listParametrosByTipo('TICA',{async:false,callback:function(data){
		$("#tipoCambio").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	SSTFacade.listMarca(function(data){
		$("#idMarca").addItems(data, "codigo", "nombre", true);
	});
	
	var loadDestinoInicial = function(tipoInicio){
		if (tipoInicio == "PR") {
			SSTFacade.listProveedor({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		}else if(tipoInicio == "CR"){
			SSTFacade.listUbicacionByTipo('CR',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
			var data = [{id:"",nombre:"SELECCIONE TIPO PROVEEDOR]"}];
			$("#destinoFinal").addItems(data, "id", "nombre", false); 
		} else if(tipoInicio == "LQ"){
			SSTFacade.listUbicacionByTipo('LQ',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		} else if(tipoInicio == "ST"){
			SSTFacade.listDestinosForIdOrigen({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
		} else {
			var destinoFinal = [{id:"",descripcion:"SELECCIONE DESTINO]"}];
			$("#destinoFinal").addItems(data, "id", "descripcion", false);
		}
	};
	
	SSTFacade.listProveedorRemateLiquidacion({async:false,callback:function(data){
		$('#tipoUbicacion').addItems(data, "nombre", "glosa", false);
		tipoInicio = $("#tipoUbicacion").val();
		loadDestinoInicial(tipoInicio);
	}});
	
	$('#tipoUbicacion').change(function(){
		loadDestino();
	});
	
	$("#idDestino").change(function(){
		var tipo = $('#tipoUbicacion').val();
		 if(tipo == 'PR'){
		 	$("#destinoFinal").removeAttr('disabled');
			loadDestinoFinal(); 
		 }
	});
	
	var loadDestinoFinal = function(){
		$("#destinoFinal").empty();
		$("#destinoFinal").removeAttr('disabled','disabled');
		var destinos=[];
		SSTFacade.getProveedorById($("#idDestino").val(),{async:false,callback:function(ubicacion){
			SSTFacade.listDestinosForIdOrigen({async:false,callback:function(data){
				data.push(ubicacion);
				$("#destinoFinal").addItems(data, "id", "nombre", false);
				$("#destinoFinal").val($("#idDestino").val());
			}});
		}});
	};
	
	var loadDestino = function(){
		var tipo = $("#tipoUbicacion").val();
		var proveedores = [];
		$('#idDestino').empty();
		$('#input-no-idDestino').val('');
		if (tipo == "PR") {
			SSTFacade.listProveedor({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
			loadDestinoFinal();
		}else if(tipo == "CR"){
			SSTFacade.listUbicacionByTipo('CR',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
				cleanDestinoFinal();
			}});
		} else if(tipo == "LQ"){
			SSTFacade.listUbicacionByTipo('LQ',{async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
				cleanDestinoFinal();
			}});
		} else if(tipo == "ST"){
			SSTFacade.listDestinosForIdOrigen({async:false,callback:function(data){
				$("#idDestino").addItems(data, "id", "nombre", false);
			}});
			cleanDestinoFinal();
		}
	};

	var cleanDestinoFinal = function(){
		$("#destinoFinal").empty();
		var data = [{id:"",nombre:"SELECCIONE TIPO PROVEEDOR]"}];
		$("#destinoFinal").addItems(data, "id", "nombre", false); 
		$("#destinoFinal").attr('disabled','disabled');
	};
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
		loadDestino();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			if (ubicacion.tipo == 'CD') {
				filtro.idUbicacion = ubicacion.id;
			}
			$('#resultados').loadData([filtro]);
		}
	});
	
	$('#btnVolver').click(function(){
		parent.location = context +'/index.do?e=' + moduloBuscarDespacho.codigo + '&m=' + moduloInicial.codigo;
	});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOtInFFTrasladoByFilter,
		seccion: 1000050000,
		//height:'auto',
		showTableToggleBtn: true,
		tipo: 'OTDF',
		multisel:false,
		singleSelect:true,
		//userpager:true,
		saveCheckValues:true,
		dwrFunctionListAllId:SSTFacade.listALLOtInFFTrasladoCheck,
		overrideModel: [{findName:'seleccionado',propiedad:function(o){
			 var check = $('<input type="checkbox">')
	 			.attr('id', 'chk' + o.id)
	 			.attr('name', 'chk' + o.id)
	 			.attr('checked',$('#resultados').isRowChecked(o.id))
 				.attr('onchange','changeState("resultados", ' + o.id + ',this)');
			 return check;
    	}},{findName:'producto.familia.linea.id',propiedad:function(o){
			 return o.producto.familia.linea.glosa;
    	}},
		
		],
		dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + el.id;
		},
		buttons : [
		           	{name: 'Trasladar', bclass: 'btnTrasladar', onpress : function(){trasladar($('#resultados').getSelectedCheckFromList());} },
					{separator: true},
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true}],
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
			"&report=OrdenTrabajoTrasladoDesdeFFReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoTrasladoDesdeFFReport", 600, 800);
	};
	
	var trasladar = function(ots){
			var destino;
			if(ots.length == 0){
				jAlert('No hay productos seleccionados para poder trasladar','Aviso');
			}else{
				if($('#tipoUbicacion').val() != 'PR'){
					destino = {
							id : $("#idDestino").val(),
					};
				} else {
					destino = {
							id : $("#destinoFinal").val(),
					};
				}
				var tipoUbicacion = $("#tipoUbicacion").val();
				SSTFacade.generarTrasladoDesdeFF(ots,destino,tipoUbicacion,{async:false,callback:function(despacho){	
					jAlert('Traslado generado con exito, el numero es: '+despacho.id,'Aviso');
					$('#resultados').clean();
				}});
			}
	};
	
	$('#btnTrasladar').click(function(){
		var destino;
		var ots = $('#resultados').getSelectedCheckFromList();
		if(ots.length == 0){
			jAlert('No hay productos seleccionados para poder trasladar','Aviso');
		}else{
			if($('#tipoUbicacion').val() != 'PR'){
				destino = {
						id : $("#idDestino").val(),
				};
			} else {
				destino = {
						id : $("#destinoFinal").val(),
				};
			}
			var tipoUbicacion = $("#tipoUbicacion").val();
			SSTFacade.generarTrasladoDesdeFF(ots,destino,tipoUbicacion,{async:false,callback:function(){	
				jAlert('Traslado generado con exito','Aviso');
				$('#resultados').clean();
			}});
		}
	});
});
