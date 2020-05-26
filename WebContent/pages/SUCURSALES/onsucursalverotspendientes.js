var moduloAccesorios = {};
var moduloFallas = {};

$(document).ready(function() {
	
	SSTFacade.getModuloByNombre('onsucursalaccesorios',{async:false, callback:function(modulo) {
		moduloAccesorios = modulo ? modulo : undefined;;
	}});
	
	SSTFacade.getModuloByNombre('onsucursaltiposfallas',{async:false, callback:function(modulo) {
		moduloFallas = modulo ? modulo : undefined;;
	}});
	
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
			"&report=OTPendienteReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OTPendienteReport", 600, 800);
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTPendientesAccesorios,
		seccion: 10008000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		tipo: 'OTPE',
		height:360, 
		overrideModel: [
		    {findName:'estado.id',
				 propiedad:function(o){
					 var btn = $('<input type="button">').css('width','110px');
					 switch (o.estado.id) {
					 case 10009000:
						 $(btn).attr('value','Tipos de fallas')
						 .attr('onclick','recibirOTSucursal(' + o.id + ')');
						 break;
					 case 10001000:
						 $(btn).attr('value','Tipos de fallas')
						 .attr('onclick','irFallas(' + o.id + ')');
						 break;
					 case 10003000:
						 $(btn).attr('value','Tipos de fallas')
						 .attr('onclick','irFallas(' + o.id + ')');
						 break;
					 default:
						 $(btn).attr('value','Accesorios')
						 .attr('onclick','irAccesorios(' + o.id + ')');
						 break;
					 }
					 return btn;
				 }}
		    ],
		buttons : [
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
   		],
	});	
	$('#resultados').loadData([{}]);
});

var recibirOTSucursal = function(idOt) {
	SSTFacade.recibirOTSucursal({id:idOt},{async:true,callback:function(){
		irFallas(idOt);
	}});
};

var irAccesorios = function(idOt) {
	parent.location = context + '/index.do?e=' + moduloAccesorios.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + idOt;
};

var irFallas = function(idOt) {
	parent.location = context + '/index.do?e=' + moduloFallas.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + idOt;
};
