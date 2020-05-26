var initonmenuordentrabajocliente = function() {
	var loadComunasCliente = function(idRegion) {
		$('#comunaCliente').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#comunaCliente').addItems(comunas,"id","glosa",true);
		}});
	};
	//getUbicacionSession
	$("#regionCliente").change(function(){
		loadComunasCliente($(this).val());
	});
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#regionCliente').addItems(data, "id", "glosa", true);
	}});

	$('#comunaCliente').addItems([],"id","glosa",true);

	$('#guardarCliente').click(function(){
		var cliente = $("#cliente").serializeObject();
		cliente.comuna = {id:$('#comunaCliente').val()}; 
		cliente.rut = $('#cliente #rut').text();
		cliente.nombreCompleto = $('#cliente #nombreCompleto').text(); 
		
		var contactoTel = new Array;
		contactoTel.push($('#cliente').find('#telefono'));
		contactoTel.push($('#cliente').find('#celular'));
		
		if($('#cliente').valid() & validEmptyInputs(contactoTel,'debe ingresar al menos un contacto telefónico')){
			SSTFacade.updateClienteByOT(ordenTrabajo.id,cliente,{async:true,callback:function(){
				jAlert('Se ha actualizado la información del cliente','Información');
			}});
		}
	});
};

var loadonmenuordentrabajocliente = function(ordenTrabajo) {
	SSTFacade.getClienteByOT(ordenTrabajo.id,{async:true,callback:function(data){
		$('#guardarCliente').attr('disabled',false);
		if (data) {
			$('#cliente').loadObject(data);
			if (data.comuna && data.comuna.provincia && data.comuna.provincia.region) {
				$('#regionCliente').val(data.comuna.provincia.region.id);				
				$('#comunaCliente').empty();
				SSTFacade.listComunasByRegion(data.comuna.provincia.region.id,{async:false,callback:function(comunas){
					$('#comunaCliente').addItems(comunas,"id","glosa",true);
				}});
				$('#comunaCliente').val(data.comuna.id);	
			}
		} else {
			$('#guardarCliente').attr('disabled',true);
		}
	}});
};
