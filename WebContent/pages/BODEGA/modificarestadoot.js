$(document).ready(function(){
	$('.fecha').datepicker();
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTToCambioEstadoByFilter
		,showTableToggleBtn: true
		,seccion:1000900000
		,tipo:'CAES'
		,multisel:false
		,singleSelect:true
		,saveCheckValues: true
//		,dwrFunctionListAllId: SSTFacade.ListAllCheckOTToCambioEstadoByFilter
		,overrideModel:[
		    {findName:'seleccione',propiedad:function(o){
		    	 return $('<input type="checkbox">')
		 			.attr('id', 'chk' + o.id)
		 			.attr('name', 'chk' + o.id);
		    }},
		    {findName:'nuevoEstado',propiedad:function(o){
		    	var sel = $('<select></selec>')
		    		.attr('id','sl'+o.id)
		    		.attr('name','sl'+o.id);
		    	var options = [];
		    	if(o.idEstadoCambio==4){
		    		options.push({value:100010000,text:'Recibido desde S.T.'});
		    		options.push({value:100010100,text:'Recibido desde C.C.'});
		    	}else if(o.idEstadoCambio==5){
		    		options.push({value:100010200,text:'Almacenada'});
		    	} else if(o.idEstadoCambio==2){
		    		if(o.ultimoEstado.id==10023000){
		    			options.push({value:10023000,text:'Rechazado por C.C.'});
		    		} else if(o.ultimoEstado.id==10024000){
		    			options.push({value:10024000,text:'Recibida desde Tienda'});
		    		}
		    	} else if(o.idEstadoCambio==3){
		    		options.push({value:10024000,text:'Recibida desde S.T.'});
		    	}
		    	$(sel).addItems(options,'value','text',true);
		    	return sel;
		    }},
		    {findName:'guardar',propiedad:function(o){
		    	var btn = $('<input type="button">')
		    		.attr('id','btn'+o.id)
		    		.attr('value','Grabar')
		    		.attr('onclick','modificarEstado('+o.id+')');
		    	return btn;
		    }},
		    {findName:'estado.id',propiedad:function(o){
		    	if(o.idEstadoCambio==2){
		    		return 'Enviada a S.T.';
		    	} else if(o.idEstadoCambio==3){
		    		return 'Enviada a Control Calidad';
		    	} else if(o.idEstadoCambio==4){
		    		return 'Almacenada';
		    	} else if(o.idEstadoCambio==5){
		    		return 'Preparada para despacho a sucursal';
		    	}else{
		    		return o.idEstadoCambio;
		    	}
		    	
		    }}
		]
	});
	
	$('#resultados').loadData([{}]);
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()){
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filter]);
		}
	});
});
var modificarEstado = function(idOT){
	var idEstado = $('#sl'+idOT).val();
	if(idEstado!=null){
		SSTFacade.modificarEstadoOT(idOT,idEstado,{asycn:true,callback:function(){
			jAlert('Estado modificado','Aviso',function(){
				$('#resultados').flexReload();
			});
		}});
	}
};