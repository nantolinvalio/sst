var initonbodegafamiliacc = function(){
	var exportDocumentFamilia = function(type){
		var p = $('#familias').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#familias').getSortName()[0];
		p.sortOrder = $('#familias').getSortOrder()[0];
		var f = $('#familias').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=FamiliaExcluidaCCReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ZonaReport", 600, 800);
	};
	
	$('#familias').flexigrid({
		dwrFunction: SSTFacade.listFamiliasParaExcluirCC,
		seccion:1000070000,
		usepager:true,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
//		onDragCol:false,
//		onToggleCol:false,
		saveCheckValues:true,
		checkAllButton:true,
		idPropertyForCheck:'codigo',
		tipo:'FAEX',
		dwrFunctionListAllId:SSTFacade.listAllFamiliaCheck,
		overrideModel: [{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
	 			.attr('id', 'chk' + o.id)
	 			.attr('name', 'chk' + o.id)
	 			.attr('checked',$('#familias').isRowChecked(o.id))
 				.attr('onchange','changeState("familias", ' + o.id + ',this)');
			 return check;
		}}],
		buttons : [
		    {name:'grabar', bclass:'btnGrabar', onpress:function(){grabarFamilias();}},
		    {separator: true},
		    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentFamilia('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentFamilia('xls');}},
	   		{separator: true}
		]
	});
	
	$('#familias').loadData([{}]);
	
//	$('#grabar').click(function(){
//		codigos = $('#familias').getNotSelectedCheckFromList();
//		seleccionadas=[];
//		$.each($('#familias').getSelectedCheckFromList(),function(index,chk){
//			seleccionadas.push(chk.codigo);
//		});
//		
//		SSTFacade.updateFamiliasExcluidas(seleccionadas,{async:false,callback:function(){
//			jAlert('Se han actualizado las familias excluidas de Control de Calidad correctamente','AVISO',function(){
//				$('#familias').flexReload();
//			});
//		}});
//		
//	});
};

var grabarFamilias = function(){
	$.alerts.okButton = '&nbsp;Continuar&nbsp;';
	$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
	jConfirm('¿Esta seguro de que desea actualizar las familias excluídas de control de calidad?','CONFIRMACIÓN',function(r){
		if(r){
			all = $('#familias').getAllCheckFromList();
			familias = [];
			$.each(all,function(index,value){
				familias.push({familia:{id:value.codigo},excluida:value.check});
			});
			
			SSTFacade.updateFamiliasExcluidas(familias,{async:false,callback:function(){
				jAlert('Se han actualizado las familias excluidas de Control de Calidad correctamente','AVISO',function(){
					$('#familias').flexReload();
				});
			}});
		}
	});
};

var loadonbodegafamiliacc = function(){
//	$('#familias').loadData([{}]);
};