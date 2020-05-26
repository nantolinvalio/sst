var ordenTrabajo;
$(document).ready(function() {
	$('#tabs').show();

	SSTFacade.getOTById($("#idOT").val(),{async:false,callback:function(ot){
		ordenTrabajo = ot;
	}});
	
	$('#estadoOT').append('<p>' + ordenTrabajo.estado.descripcion + '</p>');
	if (ordenTrabajo.vigente == false) {
		$('#estadoOT').append('<p>Orden de trabajo desactivada</p>');		
	}
	if (ordenTrabajo.cambioAutorizado == true) {
		$('#estadoOT').append('<p>Autorizada de cambio</p>');
	}
	if (ordenTrabajo.cerradaCliente == true) {
		$('#estadoOT').append('<p>Cerrada por el cliente</p>');		
	}
	if (ordenTrabajo.cerrada == true) {
		$('#estadoOT').append('<p>Cerrada por usuario</p>');		
	}
	
	$("#bitacora").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
            {display: 'Ubicación'         ,width:380 ,align:'left'   ,name:'estado.descripcion'},
            {display: 'Fecha de entrada'  ,width:100 ,align:'center' ,name:'fechaEntrada', format:'dd/MM/yyyy HH:mm'},
            {display: 'Fecha de salida'   ,width:100 ,align:'center' ,name:'fechaSalida', format:'dd/MM/yyyy HH:mm'},
            {display: 'Duración (Días)'   ,width:70  ,align:'right'  ,name:'duracion'},
            {display: 'Ubicación'         ,width:350 ,align:'left'   ,name:function(obj){
            	if (obj.ubicacion) {
            		if (obj.ubicacion.tipo == 'ST'){
            			return obj.ubicacion.glosa + ': ' + obj.ubicacion.nombre;
            		} else if(obj.ubicacion.tipo == 'CL'){
            			return obj.ubicacion.nombre;
            		} else {
            			return obj.ubicacion.glosa + ': ' + obj.ubicacion.id + ' ' + obj.ubicacion.nombre;
            		}
            	}
            }},
            {display: 'Recepción'         ,width:200 ,align:'left'   ,name:'guia.recepcion.estado.glosa'},
            {display: 'Usuario recepción' ,width:150 ,align:'left'   ,name:'guia.recepcion.usuario.nombreCompleto'},
            {display: 'Observación'       ,width:200 ,align:'left'   ,name:'guia.recepcion.observacion'},
            {display: 'N° guía'           ,width:50  ,align:'right'  ,name:'guia.numero'},
            {display: 'Estado guía'       ,width:120 ,align:'left'   ,name:'guia.estado.glosa'},
            {display: 'Usuario emisor'    ,width:120 ,align:'left'   ,name:'guia.usuario.nombreCompleto'},
            {display: 'Transportista'     ,width:200 ,align:'left'   ,name:'guia.transportista.nombreCompleto'},
            {display: 'Patente'           ,width:70  ,align:'center' ,name:'guia.transportista.patente'}
		]
	});
	
	SSTFacade.listAllBitacorasByIdOT(ordenTrabajo.id,function(bitacoras){
		$("#bitacora").flexAddData({rows:bitacoras,total:bitacoras.length});
	});

	loadEtapasOT(ordenTrabajo);
	
	loadSubmodulosByPage('onmenuordentrabajo','tabs', true, ordenTrabajo, true);
	
	$('#imprimir').click( function(){
		var url = "/sst/ViewReportServlet?type=pdf" + 
		"&report=OrdenTrabajoDetalleReport" +
		"&idOT=" + ordenTrabajo.id;
		$.openWindowsMenubar(url, "OrdenTrabajoDetalle", 600, 800);
	});
});