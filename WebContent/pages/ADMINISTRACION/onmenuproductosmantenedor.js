$(document).ready(function() {
	var idProducto = $('#idProducto').val();
	

	SSTFacade.getModuloByNombreUsuario('onmenuproductosconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
 
	
	SSTFacade.listMarca({async:true,callback:function(marcas){
		$('#marca').addItems(marcas,"codigo","nombre",true);
	}});
	
	SSTFacade.listProveedores({async:true,callback:function(marcas){
		$('#idProveedor').addItems(marcas,"id","nombre",true);
	}});

	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#filtro_buscador').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	var buscar = function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filter]);
		}
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listProductosByFilter,
		seccion: 1000020000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true, 
		tipo: 'PROD',
		overrideModel: [
		                {findName:'configurar',propiedad:function(o){
		                  	var btn = $('<input type="button">');
		              		btn.attr('value','Configurar');
		              		btn.attr('onclick','configureProfile(' + o.id + ')');
		                  	return btn;
		                 }}  
		]
	});	
	
});

var configureProfile = function(id) {
   parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idProducto=' + id;  
};

