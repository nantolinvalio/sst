var moduloCrear;
$(document).ready(function(){
	$('#resultado').hide();
	
	SSTFacade.getModuloByNombreUsuario('onmenugmastercrear',{async:false, callback:function(modulo) {
		moduloCrear = modulo ? modulo : undefined;
	}});
	
	
	$('#productos').flexigrid({
		height:220,
		userpager:false,
		showToggleBtn:true,
		title:'Productos',
		multisel:false,
		singleSelect:true,
		onDragCol: false,
		colModel : [
		    {display: 'Codigo Corto'    ,width:100 ,align:'right'  ,name:'id'},
		    {display: 'Producto'        ,width:300 ,align:'left'   ,name:'descripcion'},
		    {display: 'Cantidad'        ,width:80  ,align:'right'  ,name:'cantidad'},
		    {display: 'Precio Unitario' ,width:100 ,align:'right'  ,name:'precioVenta'},
		    {display: 'Garantía'        ,width:180 ,align:'center'   ,name:function(o){
		    	var btn = $('<input type="button" value="Consultar Garantía" onclick="javascript: crearOT(' + o.id + ');">');
		    	return btn;
		    }},
		]
	});

	
	$('#buscar').click(function(){
		limpiar();
		if($('#buscarDocumento').valid()){ 
			SSTFacade.getDocumentoByIdAndTipo({tipo:$('#tipoDocumento').val(),id:$('#idDocumento').val()},{async:true,callback:function(documento){
				if(documento!=null){
					SSTFacade.listGuiaDocumentoProductoByDocumento({tipo:$('#tipoDocumento').val(),id:$('#idDocumento').val()},{async:true,callback:function(guias){
						$.each(guias,function(a,b){
							if (b.numero != documento.id )
								documento.numeroGuia = b.numero;
							if (!b.fechaEntrega.equals(documento.fechaEmision))
								documento.fechaEntrega = b.fechaEntrega;
						});
					}});
					$('#resultado').loadObject(documento);
					$('#resultado').show();
					SSTFacade.listProductoByTipoDocumentoAndIdDocumento({tipo:$('#tipoDocumento').val(),id:$('#idDocumento').val()},{async:true,callback:function(productos){
						for(var i=0;i<productos.length; i++){
							if(productos[i].marca == null || productos[i].marca.nombre == null ){
								productos[i].marca = {};	
								productos[i].marca.nombre = 'Sin Marca';
							}
							
							if(productos[i].descripcion == null){
								productos[i].descripcion = 'Sin Descripción';
							}
						}
						$('#productos').flexAddData({rows:productos, total:productos.length});
					}});
				} else {
					jAlert('No se ha encontrado la '+$('#tipoDocumento').val()+' número '+$('#idDocumento').val());
				}
			}});
		}
	});
	
//	
	$('#limpiar').click(function(){
		$('#idDocumento').val('');
		$('#tipoDocumento').val('boleta');
		limpiar();
	});
	
	var limpiar = function(){
		$('#id').text(' ');
		$('#tipo').text(' ');
		$('#sucursal\\.id').text(' ');
		$('#fechaEmision').text(' ');
		$('#telefono').text(' ');
		$('#numeroGuia').text(' ');
		$('#fechaEntrega').text(' ');
		$('#productos').flexAddData({rows:[{}], total:[{}].length});
		$('#resultado').hide();
	};
	
});

var crearOT = function(idPr){
	var params ='idProducto='+idPr+'&idDocumento='+$('#resultado').find('#id').text()+'&tipoDocumento='+$('#resultado').find('#tipo').text();
	parent.location = context +'/index.do?e=' + moduloCrear.codigo + '&m=' + moduloInicial.codigo +'&'+params;
};

