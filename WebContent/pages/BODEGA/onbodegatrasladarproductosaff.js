$(document).ready(function() {
		
	$('.fecha').datepicker();
	$('#filtro_buscador').find('select').not('#tipoDocumento').selectInput();
	$('#filtro_buscador').validate();
	
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
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			if (ubicacion.tipo == 'CD') {
				filtro.idSucursal = ubicacion.id;
			}
			$('#resultados').loadData([filtro]);
		}
	});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOtTrasladoToFFByFilter,
		seccion: 10020000,
		//height:'auto',
		showTableToggleBtn: true,
		tipo: 'OTFF',
		multisel:false,
		singleSelect:true,
		//userpager:true,
		saveCheckValues:true,
		dwrFunctionListAllId:SSTFacade.listAllCheck,
		overrideModel: [{findName:'seleccionado',propiedad:function(o){
			 var check = $('<input type="checkbox">')
	 			.attr('id', 'chk' + o.id)
	 			.attr('name', 'chk' + o.id)
	 			.attr('checked',$('#resultados').isRowChecked(o.id))
 				.attr('onchange','changeState("resultados", ' + o.id + ',this)');
			 return check;
    	}},{findName:'tipoCambio.codigo',propiedad:function(o){
			if(o.tipoCambio == null && o.tipoCambio.codigo == null){
				return '';
			} else {
				if(o.tipoCambio == null && o.tipoCambio.descripcion == null){
					return o.tipoCambio.descripcion;
				} else { 
					return '';
				} 
			}
		}},
		],
		dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + el.id;
		},
		buttons : [
		           	{name: 'Generar Traslado', bclass: 'btnTrasladar', onpress : function(){trasladar();} },
		           	{separator: true},
			   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			   		{separator: true},],
			   		
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
			"&report=OrdenTrabajoTrasladoFFReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoTrasladoFFReport", 600, 800);
	};
	
	
	
	var trasladar = function(){
		var ots = $('#resultados').getSelectedCheckFromList();
		if(ots.length == 0){
			jAlert('No hay productos seleccionados para poder trasladar a Fallas de Fabricación','Aviso');
		}else{
			SSTFacade.generarTrasladoFF(ots,{async:false,callback:function(){	
				jAlert('Traslado generado con exito','Aviso');
				$('#resultados').clean();
			}});
		}
	};
});
