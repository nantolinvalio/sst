$(document).ready(function(){
	
	var tipoDocumento = $('#tipoDocumento').text();
	var idDocumento = parseInt($('#idDocumento').text());
	var idProducto = parseInt($('#idProducto').text());

	$('#tabs').tabs();
	
	var moduloAnterior;
	SSTFacade.getModuloByNombreUsuario('onsucursalgarantiaproveedor',{async:false, callback:function(modulo) {
		moduloAnterior = modulo ? modulo : undefined;
	}});

	SSTFacade.getDocumentoByIdAndTipo({tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(documento){
		if(documento!=null){
			$('#documentoProducto').loadObject(documento);
		}
	}});
	
	SSTFacade.getCrearOTGP({idProducto:idProducto,tipoOT:'GP'},{idDocumento:idDocumento,tipoDocumento:tipoDocumento,idProducto:idProducto},{tipo:tipoDocumento,id:idDocumento},{async:true,callback:function(crearOTGP){
		$('#documentoProducto').loadObject(crearOTGP);
		
		if(crearOTGP.consultarGP){
			$('#estadoGP').text('Activa');
		}
		else{
			$('#estadoGP').text('Desactivada');
		}
	}});
	
	SSTFacade.getProductoById(idProducto,{async:true,callback:function(producto){
		if(producto !=null){
			$('#producto\\.descripcion').text(' '+producto.descripcion);
			$('#marca').text(producto.marca.nombre);
		}
		else{
			$('#producto\\.id').text('0');
		}
	}});
	
	SSTFacade.getProcedimientoByIdProducto(idProducto,{async:false,callback:function(proc){
		if (proc == null) {
			$("#procedimiento").text("Producto sin procedimiento específico");
		} else {
			$("#procedimiento").text(proc.procedimiento);
		}
	}});
	
	var exportDocument = function(type){
		var p = $('#sTecnico').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		p.orderBy = $('#sTecnico').getSortName()[0];
		p.sortOrder = $('#sTecnico').getSortOrder()[0];
		var f = $('#sTecnico').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=ServiciosTecnicosLocalesReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ServiciosTecnicosLocalesReport", 600, 800);
	};
	
	$('#sTecnico').flexigrid({
		dwrFunction: SSTFacade.listSTecnicoFromProductoByUbicacion,
		seccion: 80002000,
		showTableToggleBtn: true,
		tipo: 'STL',
		multisel:false,
		singleSelect:true,
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
	   		{separator: true}]
	});
	
	$('#sTecnico').loadData([{idProducto:idProducto,tipoOT:'GP',soloMisDestinos:true}]);
	
	$('#volver').click(function(){
		var params ='idProducto='+idProducto+'&idDocumento='+idDocumento+'&tipoDocumento='+tipoDocumento;
		parent.location = context + "/index.do?e=" + moduloAnterior.codigo+"&m="+moduloInicial.codigo+"&" + params; 
	});
});