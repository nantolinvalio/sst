$(document).ready(function() {
	var moduloProcedimiento;
	var idProcedimiento = $('#idProcedimiento').val();
	SSTFacade.getModuloByNombreUsuario('onmenugrabarinfoadicionalst',{async:false, callback:function(modulo) {
		moduloProcedimiento = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listAllLineas({async:true,callback:function(data){
		$("#linea").addItems(data, "codigo", "glosa", true);
	}});
	
	SSTFacade.listMarca({async:true,callback:function(data){
		$("#marca").addItems(data, "codigo", "nombre", true);
	}});
	
	var buscarProcedimientos = function(){
		parent.location = context +'/index.do?e=' + moduloProcedimiento.codigo + '&m=' + moduloInicial.codigo;
	};
	
	var exportDocument = function(type){
		var p = $('#grillainformacionadicional').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#grillainformacionadicional').getSortName()[0];
		p.sortOrder = $('#grillainformacionadicional').getSortOrder()[0];
		var f = $('#grillainformacionadicional').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ProcedimientoProveedorReport" +
			"&filter=" + JSON.stringify(p) +
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ProcedimientoProveedorReport", 600, 800);
	};
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#limpiar').click(function(){
		$('#buscadorProcedimientos').reset();
		$('#grillainformacionadicional').clean();
	});
	
	var buscar = function(){
		if($('#buscadorProcedimientos').valid()) {
			var filter = $('#buscadorProcedimientos').serializeObject();
			$('#grillainformacionadicional').loadData([filter]);
		}
	};
	
	var redireccionaProcedimiento = function (id){
		parent.location = context +'/index.do?e=' + moduloProcedimiento.codigo + '&m=' + moduloInicial.codigo + '&idProcedimiento=' + id;
	};
	
	$('#grillainformacionadicional').flexigrid({
		height: 300,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
 		dwrFunction:SSTFacade.listProcedimientosProductosByFilter,
		tipo: 'GIA',
		seccion: 50002000,
		overrideModel: [
            {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
            {findName:'bloquear',propiedad:function(o){	
            	var btn = $('<input type="button">');
            	if (o.vigente) {
        			btn.attr('value','Bloquear');
        			btn.attr('onclick','changeStateProcedimiento(' + o.id + ',false)');
        		} else {
            		btn.attr('value','Desbloquear');
            		btn.attr('onclick','changeStateProcedimiento(' + o.id + ',true)');
            	}
    		 	return btn;
        	}}],
   		buttons : [
	        {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
			{separator: true},
			{name: 'Ingresar Nuevo Procedimiento', bclass: 'btnPlus', onpress : function(){buscarProcedimientos();}},
			{separator: true}
   		],
   		dblclickFunction: function(el,idx,comp){
			redireccionaProcedimiento(el.id);
 		},
	  });
	

	});

var changeStateProcedimiento = function(id,state){
	SSTFacade.updateEstadoProcedimiento({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el estado del procedimiento','Información',function(){
			$('#grillainformacionadicional').flexReload();
		});
	}});
};