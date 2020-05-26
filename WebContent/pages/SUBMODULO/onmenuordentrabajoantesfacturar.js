var initonmenuordentrabajoantesfacturar = function() {
	
	$('#btnGuardarAntesEnviar').click(function(){
		if ($('#antesEnviar').valid()) {
			var accesorios = $("#accesoriosAntesEnviar").getJSONFromHTML();
			ordenTrabajo.numeroCargo = $('#numeroCargo').val();
			ordenTrabajo.idDestinoAntesFacturar = $('#destinoProducto').val();
			
			SSTFacade.antesEnviarOT(ordenTrabajo,accesorios,{async:false,callback:function(){
				jAlert("Los datos se guardaron correctamente");
			}});			
		}
	});
	
	$('#antesEnviar').find('#checkCargoGenerado').change(function(){
		if($('#antesEnviar').find('#checkCargoGenerado').is(':checked')){
			$('#numeroCargo').removeAttr("disabled");
		} else {
			$('#numeroCargo').attr("disabled","disabled");
		}
	});
	
	$('#onmenuordentrabajoantesfacturar').click(function(){
		loadonmenuordentrabajoantesfacturar(ordenTrabajo);
		$('#accesoriosAntesEnviar').recalcLayout();
	});
	
	$("#accesoriosAntesEnviar").flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		title: "Accesorios",
		onDragCol: false,
		onToggleCol:false,
		colModel : [
            {display: 'Descripción Accesorio',width:330,align:'left',name:'descripcion',name_abbr:'descripcion'},
            {display: 'Ubicación',width:250,align:'left' ,name:'ubicacion.nombre',name_abbr:'ubicacion.nombre'},
            {name:'ubicacion.id',name_abbr:'ubicacion.id', hide:true},
            {display: '¿Se recibio en C.D.?',width:245,align:'left' , name_abbr:'recibidoCd', name:function(o){
            	var lblSI = $('<label></label>')
							.attr('for','recibidoCDSi' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('SI');
				var rdSI = $('<input type="radio">')
							.attr('name','recibidoCD' + o.id)
							.attr('value','true')
							.attr('checked',(o.recibidoCd != null && o.recibidoCd));
				var lblNO = $('<label></label>')
							.attr('for','recibidoCDNo' + o.id)
							.css('margin','0px 5px 0px 5px')
							.text('NO');
				var rdNO = $('<input type="radio">')
							.attr('name','recibidoCD' + o.id)
							.attr('value','false')
							.attr('checked',(o.recibidoCd == null || !o.recibidoCd));
				var cnt = $('<div></div>')
							.css('padding','0px')
							.append(lblSI).append(rdSI).append(lblNO).append(rdNO);
				return cnt;
            }},
		],
	});
};

var loadonmenuordentrabajoantesfacturar = function(ordenTrabajo) {
	$('#destinoProducto').empty();
	if(ordenTrabajo.numeroCargo != null && ordenTrabajo.numeroCargo > 0){
		$('#antesEnviar').find('#checkCargoGenerado').attr("checked",true);
		$('#numeroCargo').val(ordenTrabajo.numeroCargo);
	} else {
		$('#numeroCargo').attr("disabled",true);
	}

	if(ordenTrabajo.empresaFaturar != null && ordenTrabajo.empresaFacturar.id == 89772300){
		$('#facturarId').text('Se emitió una factura en la sucursal por el cambio');
		$('#numeroCargoId').text('N° de factura');
	} else {
		$('#facturarId').text('Se generó una guía de despacho de cargo');
		$('#numeroCargoId').text('N° del cargo');
	}
	
	SSTFacade.getCambioByOT(ordenTrabajo.id,{async:false,callback:function(cambio){
		
		if(cambio != null && cambio.ot != null && cambio.ot.empresaFacturar != null && cambio.ot.empresaFacturar.nombre != null){
			if(cambio.ot.empresaFacturar.nombre == 'Proveedor'){
				var prooveedor = {
					id:cambio.ot.empresaFacturar.id,
					nombre: 'Proveedor ' + cambio.proveedor.nombre + ', ' + cambio.proveedor.direccion + ', '+ cambio.proveedor.comuna
				};
				$('#destinoProducto').addItems([prooveedor], "id", "nombre", true);
				$('#destinoProducto').val(prooveedor.id);
			} else if(cambio.ot.empresaFacturar.nombre == 'Transportista'){
				var transportista = {
					id:cambio.ot.empresaFacturar.id,
					nombre: 'Transportista ' + cambio.transportista.nombre + ', ' + cambio.transportista.direccion + ', ' + ((cambio.transportista != null && cambio.transportista.comuna != null && cambio.transportista.comuna != null && cambio.transportista.comuna.glosa != null) ? cambio.transportista.comuna.glosa : '')
				};
				$('#destinoProducto').addItems([transportista], "id", "nombre", true);
				$('#destinoProducto').val(transportista.id);
			} else if (cambio.ot.empresaFacturar.id == 82982300 || cambio.ot.empresaFacturar.id == 89772300) {
				SSTFacade.listUbicacionesByIdAndTipo("CR",13000,{async:false,callback:function(ubicaciones){
					$.each(ubicaciones,function(i,ubicacion) {
						ubicacion.nombre = 'Casa de remate ' + ubicacion.nombre + ', ' + ubicacion.direccion; 
					});
					$('#destinoProducto').addItems(ubicaciones, "id", "nombre", true);
				}});
				$('#destinoProducto').val(ordenTrabajo.idDestinoAntesFacturar);
			}
		}
	}});
	
	SSTFacade.listAccesoriosByOT(ordenTrabajo.id,{async:false,callback:function(accesoriosOT){
		if (accesoriosOT && accesoriosOT.length != 0) {
			$("#accesoriosAntesEnviar").parent().parent().show();
			$("#accesoriosAntesEnviar").flexAddData({rows:accesoriosOT,total:accesoriosOT.length});				
		} else {
			$("#accesoriosAntesEnviar").parent().parent().hide();
		}
	}});
};
