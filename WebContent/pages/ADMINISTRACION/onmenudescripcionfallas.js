$(document).ready(function(){
	$('.fecha').datepicker();
	
	SSTFacade.listFamiliasByFilter({},function(data){
		$("#idFamilia").addItems(data, "id", "nombre", true);
	});
			
	$('#buscar').click(function(){
		buscar();
	});
	
	$('#descripcionFallasForm').keypress(function(e){
		if(e.which == 13) {
			buscar();
		}
	});
		
	$('#limpiar').click(function(){
		$('#descripcionFallasForm').reset();
		$('#resultadosDescripcionFallas').clean();
	});
	
	var buscar = function(){
		if($('#descripcionFallasForm').valid()) {
			var filter = $('#descripcionFallasForm').serializeObject();
			$('#resultadosDescripcionFallas').loadData([filter]);
		}
	};
	
	
	$('#resultadosDescripcionFallas').flexigrid({
		dwrFunction: SSTFacade.listOTDescripcionFallas,
		seccion: 10004000,
		multisel:false,
		singleSelect:true, 
		tipo: 'DF', // columnas
		overrideModel: [
            {findName:'sucursal.id', propiedad:function(o){return o.sucursal.id + ' ' + o.sucursal.glosa;}},
            {findName:'producto.id', propiedad:function(o){
            	if(o.producto.descripcion == null){
            		return 'producto sin descripcion';	
            		} else {
            				return o.producto.id + ' ' + o.producto.descripcion + ',Marca ' + o.producto.marca.nombre ;
            		}
            }},               
		],

	});	

});