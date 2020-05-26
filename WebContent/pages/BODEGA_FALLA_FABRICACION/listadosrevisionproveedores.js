$(document).ready(function(){
	
	$('.fechaHora').datetimepicker();
	$('.paraRevision').hide();
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onbodegaffdetalledespacho',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listProveedor({async:true,callback:function(proveedores){
		$('#filtroRevisiones #idProveedor, #filtroParaRevision #idProveedor').addItems(proveedores,'id','nombre',true);
	}});
	
	SSTFacade.listFamilia({sync:true,callback:function(familias){
		$('#filtroRevisiones #idFamilia, #filtroParaRevision #idFamilia').addItems(familias,"id","nombre",true);
	}});
	
	$('#filtroRevisiones #limpiar').click(function(){
		$('#filtroRevisiones').reset();
//		$('#resultadosRevisiones').clean();
	});
	
	$('#filtroParaRevision #limpiar').click(function(){
		$('#filtroParaRevision').reset();
		$('#resultadosParaRevision').clean();
	});
	
	SSTFacade.getDate({async:true,callback:function(date){
		$('#filtroParaRevision').loadObject({fechaCreacionCorte:date});
	}});
	
	$('#filtroRevisiones #buscar').click(function(){
		buscar();
	});
	
	$('#filtroRevisiones').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	$('#filtroParaRevision #buscar').click(function(){
		if($('#filtroParaRevision').valid()){
			var filter = $('#filtroParaRevision').serializeObject();
			
			$('#resultadosParaRevision').loadData([filter]);
		}
	});
	
	
	var buscar = function(){
		if($('#filtroRevisiones').valid()){
			var filter = $('#filtroRevisiones').serializeObject();
			
			$('#resultadosRevisiones').loadData([filter]);
		}
	};
	var exportDocumentDespachos = function(type){
		var p = $('#resultadosRevisiones').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#resultadosRevisiones').getSortName()[0];
		p.sortOrder = $('#resultadosRevisiones').getSortOrder()[0];
		var f = $('#resultadosRevisiones').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=DespachosRevisionReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "DespachosRevisionReport", 600, 800); 
	};
	
	$('#resultadosRevisiones').flexigrid({
		dwrFunction: SSTFacade.ListRevisionesByFilter,
		showTableToggleBtn: true,
		showToggleBtn: true,
		multisel:false,
		singleSelect:true,
		seccion:90004000,
		tipo:'REVS',
		buttons : [
		    {name:'Nuevo listado para revisión', bclass: 'btnPlus', onpress : function(){nuevaBusqueda();}},
		    {separator: true},
		    {name:'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentDespachos('pdf');}},
		    {separator: true},
		    {name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentDespachos('xls');}},
		    {separator: true}
		],
		dblclickFunction: function(el,idx,comp){
			parent.location = context+'/index.do?e='+moduloDetalle.codigo+'&m='+moduloInicial.codigo+'&idDespacho='+el.id;
		}
	});
	
	var exportDocumentOts = function(type){
		var p = $('#resultadosParaRevision').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#resultadosParaRevision').getSortName()[0];
		p.sortOrder = $('#resultadosParaRevision').getSortOrder()[0];
		var f = $('#resultadosParaRevision').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=OrdenTrabajoParaDespachoReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoParaDespachoReport", 600, 800); 
	};
	
	$('#resultadosParaRevision').flexigrid({
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		dwrFunction: SSTFacade.listOtParaDespachoByFilter,
		tipo:'LPRE',
		seccion: 90005000,
		saveCheckValues:true,
		dwrFunctionListAllId: SSTFacade.listAllOtParaDespachoCheck,
		overrideModel: [{findName:'checkMarca',propiedad:function(o){
			var check = $('<input type="checkbox">')
			    .attr('id', 'chk' + o.id)
			    .attr('name', 'chk' + o.id)
			    .attr('checked', $('#resultadosParaRevision').isRowChecked(o.id))
			    .attr('onchange', 'changeState("resultadosParaRevision", ' + o.id + ', this)');
			return check;
		}}
		],
		buttons : [
			{name:'Enviar a sala de proveedores', bclass: 'btnMove', onpress : function(){enviarListado();}},
			{separator: true},
		    {name:'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocumentOts('pdf');}},
		    {separator: true},
		    {name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocumentOts('xls');}},
		    {separator: true}
		]
	});
	
	var nuevaBusqueda = function(){
		$('.paraRevision').show();
		$('.revisiones').hide();
	};
	
	var enviarListado = function(){
		var checkOTs = $('#resultadosParaRevision').getSelectedCheckFromList();
		if(checkOTs.length > 0){
			SSTFacade.saveDespachoInternoASalaProveedores({proveedor:{id:$('#filtroParaRevision').find('#idProveedor').val()},familia:{id:$('#filtroParaRevision').find('#idFamilia').val()}},checkOTs,{async:true,callback:function(despachoInterno){
				jAlert('Lista de despacho creada con éxito, el número es : '+despachoInterno.id,'Aviso',function(){
					location.reload();
				});
			}});
		}else{
			jAlert("Debe seleccionar al menos una OT para enviar a sala de proveedores","Aviso");
		}
	}; 
	
//	$('#enviarListado').click(function(){
//		var checkOTs = $('#resultadosParaRevision').getSelectedCheckFromList();
//		SSTFacade.saveDespachoInternoASalaProveedores({proveedor:{id:$('#filtroParaRevision').find('#idProveedor').val()},familia:{id:$('#filtroParaRevision').find('#idFamilia').val()}},checkOTs,{async:true,callback:function(despachoInterno){
//			jAlert('Lista de despacho creada con éxito','Aviso',function(){
//				location.reload();
//			});
//		}});
//		
//	});
	
	
	$('#resultadosRevisiones').loadData([{}]);
});