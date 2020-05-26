var estado;
var initonbodegaffdetalleproducto = function() {
	
	$('#productos').flexigrid({
		dwrFunction: SSTFacade.listOtByDespachoToSP,
		seccion: 90006000,
		tipo: 'DDFF',
		multisel:false,
		singleSelect:true,
		saveCheckValues:true,
		dwrFunctionListAllId:SSTFacade.listOtByDespachoCheck,
		overrideModel: [{findName:'seleccionado',propiedad:function(o){
			if(o.despachoDetalle.estado.id == 90005000){
				return ' ';
			} else {
				var check = $('<input type="checkbox">')
				.attr('id', 'chk' + o.id)
				.attr('name', 'chk' + o.id)
				.attr('checked',$('#productos').isRowChecked(o.id))
				.attr('onchange','changeState("productos", ' + o.id + ',this)');
			return check;
			}
	   	}},{findName:'estado.id',propiedad:function(o){
			return o.despachoDetalle.estado.descripcion;
	   	}},{findName:'producto.id',propiedad:function(o){
			return o.producto.id +' '+ o.producto.descripcion;
	   	}},{findName:'producto.familia.id',propiedad:function(o){
			return o.producto.familia.nombre;
	   	}},{findName:'producto.marca.codigo',propiedad:function(o){
			return o.producto.marca.nombre;
	   	}},{findName:'producto.familia.linea.id',propiedad:function(o){
			return o.producto.familia.linea.glosa;
	   	}},{findName:'tipoCambio',propiedad:function(o){
			if(tipoCambio == null){
				return ' ';
			}else{
				return o.tipoCambio.id;
			}
	   	}},{findName:'ubicacion.id',propiedad:function(o){
			return o.ubicacion.direccion;
	   	}},
	   	],
	   	buttons : [
	   				{name:'Eliminar OT del despacho', bclass: 'btnMinus', onpress : function(){desvincular();}},
	   				{separator: true},
	   			]
	});

	var desvincular = function(){
		var ots = $('#productos').getSelectedCheckFromList();
		if(ots.length > 0){
			SSTFacade.verificarEstadoParaEliminarOtsFromDespacho(ots,despacho,{async:false,callback:function(paraeliminar){
				if(paraeliminar == true){
					SSTFacade.desvincularOTDespacho(despacho,ots,{async:false,callback:function(){
						$('#productos').flexReload();  
						jAlert('Se han eliminado correctamente las ots del despacho','Aviso'); 
					}});
				}
			}});
		} else {
			jAlert('No hay ots seleccionadas para eliminar','Aviso'); 
		}
	};
	
	$('#despachar').click(function(){
		SSTFacade.terminarEscaneoEnFallaFabricacion(despacho,{async:true,callback:function(termina){
			if(!termina){
				jAlert('No es posible continuar con el despacho, elimine los productos no encontrados,o escanee los productos faltantes','Aviso',function(){
					
				});
			} else {
				jAlert('Se ha realizado el despacho','Aviso',function(){
					location.reload();
				});
			}
		}});
	});
};


var loadonbodegaffdetalleproducto = function(despacho) {
	$('#productos').loadData([despacho]);
};


