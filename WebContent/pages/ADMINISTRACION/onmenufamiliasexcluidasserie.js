$(document).ready(function() {
	var change = false;
	
	$('select').selectInput();
	
	$('#idLinea').change(function(){
		preSaveFamilias();
	});
	
	var preSaveFamilias = function(){
		if (change) {
			change = false;
			$.alerts.okButton = '&nbsp;Grabar y continuar&nbsp;';
			$.alerts.cancelButton = '&nbsp;Continuar sin grabar&nbsp;';
			jConfirm('¿Desea grabar los cambios realizados antes de continuar?', 'Confirmación', function(r){
				if (r) {
					saveFamilias();
				}
				listFamilias();
			});
		} else {
			listFamilias();
		}		
	};
	
	$('#grabar').click(function(){
		if (change) {
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro que desea grabar los cambios?', 'Confirmación', function(r){
				if (r) {
					change = false;
					saveFamilias();
					listFamilias();
				}
			});
		} else {
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('No hay modificaciones para grabar','Información');
		}
	});

	var saveFamilias = function(sel, nosel){
		if (sel == undefined)
			sel = $("#familias").getJSONCheckboxSelected();
		if (nosel == undefined)
			nosel = $("#familias").getJSONCheckboxUnselected();
		SSTFacade.saveFamiliasExcluidasSerie(sel,nosel,{async:false,callback:function(){
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('Familias grabadas','Información');
		}});
	};
	
	var listFamilias = function(){
		$("#familias").loadData([{idLinea:$('#idLinea').val()}]);
	};
	
	
	SSTFacade.listAllLineas(function(data){
		$("#idLinea").addItems(data, "codigo", "glosa", true);
	});
	
	var exportDocument = function(type){
		var p = $('#familias').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#familias').getSortName()[0];
		p.sortOrder = $('#familias').getSortOrder()[0];
		var f = $('#familias').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=FamiliaExcluidaSerieReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ZonaReport", 600, 800);
	};
	
	$("#familias").flexigrid({
//		height: 300,
		dwrFunction:SSTFacade.listFamiliasExcluidasSerieByFilter,
		seccion: 70002000,
		showTableToggleBtn: true,
		tipo: 'famExc',
		multisel:false,
		singleSelect:true,
		showToggleBtn: true,
		afterAddData: function() {
			$('#familias').find('tbody tr > td > div > input[type=checkbox]').click(function(){
 				change = true;
 			}).change(function(){
 				change = true;
 			});
		},
		beforeChangePage : function(){
			if (change) {
				change = false;
				$.alerts.okButton = '&nbsp;Grabar y continuar&nbsp;';
				$.alerts.cancelButton = '&nbsp;Continuar sin grabar&nbsp;';
				var sel = $("#familias").getJSONCheckboxSelected();
				var nosel = $("#familias").getJSONCheckboxUnselected();
				jConfirm('¿Desea grabar los cambios realizados antes de continuar?', 'Confirmación', function(r){
					if (r) {
						saveFamilias(sel,nosel);
					}
				});
			}
		},
		overrideModel: [{findName:'excluida',
						 propiedad:function(o){
							 return $('<input type="checkbox">')
							 			.attr('id', 'chk' + o.id)
							 			.attr('name', 'chk' + o.id)
							 			.attr('checked',o.excluida);
						 }}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		]
	});
	
	listFamilias();
	
});