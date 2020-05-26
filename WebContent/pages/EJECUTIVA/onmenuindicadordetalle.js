$(document).ready(function() {
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var filter = {
		idEjecutiva:$("#idUsuario").val() == "null" ? null : $("#idUsuario").val(),
		fechaInicio:$("#fechaInicio").val() == "null" ? null : $("#fechaInicio").val(),
		fechaTermino:$("#fechaTermino").val() == "null" ? null : $("#fechaTermino").val(),
		idIndicador:$("#idIndicador").val() == "null" ? "null" : $("#idIndicador").val()
	};
	
	SSTFacade.listOTAsignadaEjecutivaMarcaIndicador(filter,{async:false,callback:function(ordenesTrabajo){
		jAlert(dwr.util.toDescriptiveString(ordenesTrabajo,2));
	}});

//	SSTFacade.getIndicadorById($("#idIndicador").val(),function(indicador){
//		if (indicador) {
//			$("#h1Indicador").text(indicador.descripcion);			
//		} else {
//			$("#h1Indicador").text('No se obtuvieron los parametros correspondientes');
//		}
//	});
//	
//	var functionColModel = function(flex) {
//		SSTFacade.listColumnasIndicadorByFilter({idRol:5, idIndicador:20008000, tipo:this.tipo},{async:false,callback:function(data){
//			flex.colModel = [];
//			$.each(data, function(){
//				var el = arguments[1];
//				flex.colModel.push({
//					sortable : (el.columna != null && el.columna != "") , 
//					column: el.columna, 
//					id: el.id, 
//					order: el.orden, 
//					display: el.nombre, 
//					name : el.propiedad, 
//					align: el.alinear, 
//					width: el.ancho,
//					hide: el.orden == null,
//					format: el.formato});
//			});
//		}});
//	};
//
//	var exportToExcelResultados = function(){
//	};
//
//	var exportToPDFResultados = function(){
//	};
//	
//	$('#resultados').flexigrid({
//		dwrFunction: SSTFacade.listOTByFilter,
//		tipo: 'OT',
//		functionColModel: functionColModel,
//		dblclickFunction:function(el,idx,comp){
//			parent.location = context +'/index.do?e=' + moduloDetalle.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + el.id;
//		},
//		buttons : [
//	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : exportToPDFResultados },
//	   		{separator: true},
//	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : exportToExcelResultados},
//	   		{separator: true}
//   		],
//	}).attr('width','95%');
	
});