var initonmenuordentrabajogestionsm = function() {
	$("#gestionOT").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		title: "Gestiones", 
		onDragCol: false,
		onToggleCol:false,
		colModel : [
            {display: 'Fecha',width:90,align:'left',name:'fecha',format:'dd/MM/yyyy HH:mm'},
            {display: 'Gestion',width:300,align:'left' ,name:'gestion'},
            {display: 'Ejecutiva',width:200,align:'left' ,name:'ejecutiva'},
            {display: 'Archivo',width:220,align:'left' ,name:function(o){
            	if (o.archivo != null) {
            		var a = $('<a></a>')
	        			.attr('href','#')
	        			.attr('onclick','$.openWindowsMenubar("' + context + '/DownloadGestionServlet?id=' + o.id + '",' + '"' + o.archivo + '", 300, 800)')
	        			.text(o.archivo);
            		return a;
            	}
            	return "";
            }},
		]
	});
	
	$('#onmenuordentrabajogestion').click(function(){
		$('#gestionOT').recalcLayout();
		SSTFacade.listGestionesByIdOT(ordenTrabajo.id,function(gestionOT){
			$("#gestionOT").flexAddData({rows:gestionOT,total:gestionOT.length});
		});
	});
	
	SSTFacade.getDate({async:false,callback:function(fechaActual){
		$('#gestiones').find('#fecha').val(fechaActual.toString("dd/MM/yyyy HH:mm"));		
	}});
	
	SSTFacade.listParametrosByTipo('TIAC',{async:true,callback:function(tiposArchivos){
		$('#gestiones').find('#tipoArchivo').addItems(tiposArchivos, "codigo", "glosa", true);
	}});
	

	
	$('#agregarGestion').click(function(){
		if($('#gestiones').valid()){
			if($('#archivo').val().length > 0){
				if($('#tipoArchivo').val().length > 0){
					$('#tipoArchivo').removeClass('error');
					var formData = new FormData($('#gestiones')[0]);
					$.ajax({
						url: context + '/UploadFileServlet',  //server script to process data
						type: 'POST',
						beforeSend: function(){
							var waiting = $('<div class="ui-widget-overlay" id="disabledImageZone"/>');
							waiting.append('<div class="disabledImageZoneImg"/>');
							$('body').append(waiting);        	
						},
						success: function(){
							$('#disabledImageZone').remove();
							jAlert('Gestión Grabada','Información',function(){
								$('#gestiones').reset();
								SSTFacade.getDate({async:false,callback:function(fechaActual){
									$('#gestiones').find('#fecha').val(fechaActual.toString("dd/MM/yyyy HH:mm"));		
								}});
								$('#gestiones').find('#idOT').val(ordenTrabajo.id);
								SSTFacade.listGestionesByIdOT(ordenTrabajo.id,function(gestionOT){
									$("#gestionOT").flexAddData({rows:gestionOT,total:gestionOT.length});
								});
							});
						},
						error: function(a){
							$('#disabledImageZone').remove();
							errorHandler(a.responseText);
						},
						data: formData,
						cache: false,
						contentType: false,
						processData: false
					});
				} else {
					$('#tipoArchivo').addClass('error');
				}
			} else {
				var fecha = $('#gestiones').find('#fecha').val();
				SSTFacade.saveGestionWithFecha(ordenTrabajo.id, (" " +$('#gestion').val()),fecha,{async:true,callback:function(data){
					jAlert('Gestión Grabada','Información',function(){
						$('#gestiones').reset();
						SSTFacade.getDate({async:false,callback:function(fechaActual){
							$('#gestiones').find('#fecha').val(fechaActual.toString("dd/MM/yyyy HH:mm"));		
						}});
						$('#gestiones').find('#idOT').val(ordenTrabajo.id);
						SSTFacade.listGestionesByIdOT(ordenTrabajo.id,function(gestionOT){
							$("#gestionOT").flexAddData({rows:gestionOT,total:gestionOT.length});
						});
					});
				}});
			}
		}
	});
	
	$('#gestiones').find('.fechaHora').datetimepicker();
};

var loadonmenuordentrabajogestionsm = function(ordenTrabajo) {
	$('#gestiones').find('#idOT').val(ordenTrabajo.id);
	SSTFacade.listGestionesByIdOT(ordenTrabajo.id,function(gestionOT){
		$("#gestionOT").flexAddData({rows:gestionOT,total:gestionOT.length});
	});
};