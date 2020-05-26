$(document).ready(function() {
	
	$('#tabs').tabs();
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
		$('#idProducto').focus();
		$("#descripcion").text(" ");
		$("#marca\\.nombre").text(" ");
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	var buscar = function() {
		if($('#filtro_buscador').valid()) {
			$('#resultados').clean();
			var filtro = $('#filtro_buscador').serializeObject();
			filtro.soloMisDestinos = $('#filtro_buscador').find('#soloMisDestinos').is(':checked');
			var id =$('#idProducto').val();
			SSTFacade.getProductoById(id,{async:true,callback:function(producto){
				if(producto == null){
					jAlert("El producto no existe","Información",function(){
						$("#descripcion").text(" ");
						$("#marca\\.nombre").text(" ");
						$("#procedimiento").text(" ");
					});
				} else {
					$("#descripcion").text(producto.descripcion);
					$("#marca\\.nombre").text(producto.marca.nombre);
					$('#resultados').loadData([filtro]);
					SSTFacade.getProcedimientoByIdProducto(producto.id,{async:false,callback:function(proc){
						if (proc == null) {
							$("#procedimiento").text("Producto sin procedimiento específico");
						} else {
							$("#procedimiento").text(proc.procedimiento);
						}
					}});
				}
			}});
		}
	};
	
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
			"&report=ServiciosTecnicosLocalesReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "ServiciosTecnicosLocalesReport", 600, 800);
	};

	$('#resultados').flexigrid({
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
	
});