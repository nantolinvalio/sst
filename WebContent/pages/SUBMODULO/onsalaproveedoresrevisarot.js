var initonsalaproveedoresrevisarot = function(){
	SSTFacade.listParametrosByTipo("CB",{async:false,callback:function(data){
        $("#clasificacion").addItems(data, "codigo", "glosa", true);
        $('#clasificacion option[value="RDEFP"]').remove();
    }});
	
	$('#grabar').click(function(){
		if($('#onsalaproveedoresrevisarotPanel').valid()){
			ordenTrabajo.clasificacion={codigo:$('#clasificacion').val()};
			ordenTrabajo.nombreTecnico = $('#nombreTecnico').val();
			ordenTrabajo.observacionRevision =$('#observacion').val();
			SSTFacade.revisarOnSalaProveedores(ordenTrabajo,{async:true,callback:function(){
				jAlert('Producto revisado con éxito','aviso',function(){
					location.reload();
				});
			}});
		}
	});
};



var loadonsalaproveedoresrevisarot = function(){
	$('#clasificacion').val('');
	$('#observacion').val('');
};