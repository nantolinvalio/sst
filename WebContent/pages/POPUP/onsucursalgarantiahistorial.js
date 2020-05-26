$(document).ready(function(){
	var idProducto = $("#idProducto").text();
	var idDocumento = $('#idDocumento').text();
	var tipoDocumento = $('#tipoDocumento').text();
	
	
	$('#historalProducto').flexigrid({
		height:150,
		usepager:false,
		showToggleBtn:true,
		title:'Historial',
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		onDragCol: false,
		colModel : [
		    {display: 'N° OT'               ,width:70  ,align:'right'  ,name:'id'},
		    {display: 'Fecha creacion'      ,width:100 ,align:'right'  ,name:'fechaCreacion' , format:'dd-MM-yyyy'},
		    {display: 'Ver estado'          ,width:130 ,align:'center' ,name:function(o){
		    	var btn = $('<input type="button" value="Ver estado" onclick="javascript: verEstado(' + o.id + ');">');
		    	return btn;
		    }},
		    {display: 'N° de serie'         ,width:265 ,align:'left'   ,name:'numeroSerie'},
		    {display: 'Descripción Estado'  ,width:265 ,align:'left'   ,name:'descripcionEstado'},
		]
	});
	SSTFacade.listHistorialOT({idDocumento:idDocumento,tipoDocumento:tipoDocumento,idProducto:idProducto},{async:true,callback:function(historialOT){
		$('#historalProducto').flexAddData({rows:historialOT, total:historialOT.length});
	}});
	
	SSTFacade.getProductoById(idProducto,{async:true,callback:function(producto){
		if(producto !=null){
			$('#documentoYProducto').loadObject(producto);
		}
	}});
	
	$('#volver').click(function(){
		var params ='idProducto='+idProducto+'&idDocumento='+idDocumento+'&tipoDocumento='+tipoDocumento;
		parent.location = context +'/index.do?e=4DF4C011F3C441F30DB186A7E4F5202F&m=1ABCC2BFC718FE450DFCBD39F067CEF5&'+params;
	});
	
});

var verEstado = function(idOT){
	window.open(context +'/index.do?e=96738B6D7C6F00AEB99C0AFB2D76E457&m=1ABCC2BFC718FE450DFCBD39F067CEF5&idOT='+idOT);
};