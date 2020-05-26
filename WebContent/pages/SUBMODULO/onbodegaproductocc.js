var initonbodegaproductocc = function(){
	$('#productos').flexigrid({
		dwrFunction:SSTFacade.listProductosParaExcluirCCByFilter,
		seccion:1000080000,
		usepager:true,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
//		onDragCol:false,
//		onToggleCol:false,
		saveCheckValues:true,
		checkAllButton:true,
		idPropertyForCheck:'id',
		tipo:'PREX',
		dwrFunctionListAllId:SSTFacade.listAllProductosCCCheck,
		overrideModel: [{findName:'seleccionado',propiedad:function(o){
							var check = $('<input type="checkbox">')
								.attr('id', 'chk' + o.id)
								.attr('name', 'chk' + o.id)
								.attr('checked',$('#productos').isRowChecked(o.id))
								.attr('onchange','changeState("productos", ' + o.id + ',this)');
							return check;
						}}
						],
		buttons : [
		    {name:'Grabar', bclass:'btnGrabar', onpress:function(){grabarProductos();}},
		    {separator: true},
		    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentProducto('pdf');} },
		    {separator: true},
		    {name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentProducto('xls');}},
		    {separator: true}
		]
	});
	
	var exportDocumentProducto = function(type){
		var p = $('#productos').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#productos').getSortName()[0];
		p.sortOrder = $('#productos').getSortOrder()[0];
		var f = $('#productos').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ProductoExcluidoCCReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ZonaReport", 600, 800);
	};
	
	SSTFacade.listFamilia({async:true,callback:function(familias){
		$('#idFamilia').addItems(familias,'id','nombre',true);
	}});
	
	SSTFacade.listMarca({async:true,callback:function(marcas){
		$('#marca').addItems(marcas,'codigo','nombre',true);
	}});
	
	$('#buscar').click(function(){
		var filtros = new Array;
		filtros.push($('#buscador').find('#id'));
		filtros.push($('#buscador').find('#marca'));
		filtros.push($('#buscador').find('#descripcion'));
		filtros.push($('#buscador').find('#idFamilia'));
		if($('#buscador').valid() && validEmptyInputs(filtros,'debe ingresar al menos un campo en el buscador')){
			var filtro = $('#buscador').serializeObject(); 
			$('#productos').loadData([filtro]);
		}
	});
	
	$('#limpiar').click(function(){
		$('#buscador').reset();
		$('#productos').clean();
	});
	
};
var grabarProductos = function(){
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('¿Esta seguro de que desea actualizar los productos excluídos de control de calidad?','CONFIRMACIÓN',function(r){
		if(r){
			
			all = $('#productos').getAllCheckFromList();
			productos=[];
			$.each(all,function(index,value){
				productos.push({id:value.id,excluido:value.check});
			});
			
			if(productos.length>0){
				SSTFacade.updateProductosExcluidosCC(productos,{async:false,callback:function(){
					jAlert('Se han actualizado los productos excluídos de Control de Calidad correctamente', 'AVISO', function(){
						$('#productos').flexReload();
					});
				}});
			} else {
				jAlert('No se hay productos que grabar','AVISO');
			}
		}
	});
};

var loadonbodegaproductocc = function(){
	
};