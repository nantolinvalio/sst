var moduloConfigurar;
$(document).ready(function() {
    var idComuna;
	var nueva;
	
	SSTFacade.getModuloByNombreUsuario('onmenuubicacionesconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
		
	SSTFacade.listUbicacionesShowByTipo({async:false,callback:function(ubicaciones){
		$('select[name=tipo]').addItems(ubicaciones,"codigo","glosa",true);
	}});
		
	$('#mantenedorUbicacionForm').find('select[name=tipo]').change(function(){
		changeCentroCostoRut($(this).val());
	});
	
	var openUbicacion = function(idUbicacion){
		$('#mantenedorUbicacionForm').reset();
		$('#mantenedorUbicacionForm').find('select[name=tipo]').attr('disabled',false);
		if (idUbicacion) {
			SSTFacade.getUbicacionById(idUbicacion, {async:false,callback:function(ubicacionChange){
				$('#mantenedorUbicacion').loadObject(ubicacionChange);
				if (ubicacionChange.comuna && ubicacionChange.comuna && ubicacionChange.region) {
					$('#region\\.id').val(ubicacionChange.region.id);				
					$('#comuna\\.id').empty();
					SSTFacade.listComunasByRegion(ubicacionChange.region.id,{async:false,callback:function(comunas){
						$('#comuna\\.id').addItems(comunas,"id","glosa",true);
					}});
					$('#comuna\\.id').val(ubicacionChange.comuna.id);	
				}
				changeCentroCostoRutLoad(ubicacionChange.tipo);
			}});
			nueva = false;
			$('#mantenedorUbicacion').dialog('option', 'title', 'Modificar Servicio Técnico');
			$('#mantenedorUbicacionForm').find('select[name=tipo]').attr('disabled',true);
		} else {
			$('#mantenedorUbicacion').dialog('option', 'title', 'Agregar Nuevo Servicio Técnico');
			nueva = true;
		}
		$('#mantenedorUbicacion').dialog('open');
	};
	
	var changeCentroCostoRutLoad = function(tipo) {
		$('#mantenedorUbicacionForm').find('#id').attr('readonly',true);
		$('#mantenedorUbicacionForm').find('#rut').attr('disabled',false);
		switch (tipo) {
		case 'SC':
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			break;
		case 'BR':
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			break;
		case 'OF':
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			break;
		case 'CR':
			break;
		case 'CD':
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			break;
		case 'LQ':
			break;
		default:
			$('#mantenedorUbicacionForm').find('#rut').attr('readonly',true);
			break;
		}
		$('#mantenedorUbicacionForm').find('#id, #rut').removeClass('error');
	};
	
	
	var changeCentroCostoRut = function(tipo) {
		switch (tipo) {
		case 'SC':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',false);
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#rut').val('');
			break;
		case 'BR':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',false);
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#rut').val('');
			break;
		case 'OF':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#id').val('');
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#rut').val('');
			break;
		case 'CR':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#id').val('');
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',false);
			break;
		case 'CD':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',false);
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#rut').val('');
			break;
		case 'LQ':
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#id').val('');
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',false);
			break;
		default:
			$('#mantenedorUbicacionForm').find('#id').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#id').val('');
			$('#mantenedorUbicacionForm').find('#rut').attr('disabled',true);
			$('#mantenedorUbicacionForm').find('#rut').val('');
			break;
		}
		$('#mantenedorUbicacionForm').find('#id, #rut').removeClass('error');
	};
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#region\\.id').addItems(data, "id", "glosa", true);
	}});
	
	$('#comuna\\.id').addItems([],"id","glosa",true);
	
	$("#region\\.id").change(function(){
		$('#comuna\\.id').empty();
		SSTFacade.listComunasByRegion($(this).val(),{async:false,callback:function(comunas){
			$('#comuna\\.id').addItems(comunas,"id","glosa",true);
		}});
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filtro = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filtro]);
		}
	});

	$('#mantenedorUbicacion').dialog({
		autoOpen:false,
		modal:true,
		width:800,
	    buttons:{
	    	Grabar: function() {
	    		if($('#mantenedorUbicacionForm').valid()) {
	    			var ubicacion = $('#mantenedorUbicacionForm').serializeObject();
	    			jConfirm('¿Esta seguro que desea grabar la nueva ubicacion?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveUbicacion(ubicacion, nueva, function(){
								$("#resultados").flexReload(ubicacion);
								$('#mantenedorUbicacion').dialog('close');
								jAlert('Nueva ubicacion grabada','Información');																
							});
						}
					});
	    		}
			},	
			Cerrar: function(){
				$('#comuna\\.id').empty();
				$('#comuna\\.id').addItems([],"id","glosa",true);
				$('#mantenedorUbicacion').dialog('close');
			}
	   }
	});
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listUbicacionByFilter,
		seccion: 1000030000,
		showTableToggleBtn: true,
		tipo: 'UBIC',
		multisel:false,
		singleSelect:true,
		dblclickFunction:function(el,idx,comp){
			openUbicacion(el.id);
		},
		overrideModel: [
            {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
            {findName:'bloquear',propiedad:function(o){	
            	var btn = $('<input type="button">');
            	if (o.vigente) {
            		btn.attr('value','Bloquear');
            		btn.attr('onclick','changeStateUbicacion(' + o.id + ',false)');
            	} else {
            		btn.attr('value','Desbloquear');
            		btn.attr('onclick','changeStateUbicacion(' + o.id + ',true)');
            	}
            	return btn;
           }},
           {findName:'configurar',propiedad:function(o){	
           		if (o.tipo == 'LQ' || o.tipo == 'CR' || o.tipo == 'OF') {
        			return '';
        		}
	           	var btn = $('<input type="button">');
	       		btn.attr('value','Configurar');
	       		btn.attr('onclick','setupUbicacion(' + o.id + ')');
	           	return btn;
           }},
           {findName:'id',propiedad:function(o){	
          		if (o.tipo == 'LQ' || o.tipo == 'CR' || o.tipo == 'OF') {
          			return '';
          		}
          		return o.id;
           }},
           {findName:'cantidadDestinos',propiedad:function(o){	
         		if (o.tipo == 'LQ' || o.tipo == 'CR' || o.tipo == 'OF') {
         			return '';
         		}
         		return o.cantidadDestinos;
           }}
	    ],
		buttons : [
			{name: 'Ingresar Nueva Ubicación', bclass: 'btnPlus', onpress : function(){openUbicacion();}},
			{separator: true}
		],
	});
	
	$('#resultados').loadData([{}]);
	
});

var changeStateUbicacion = function(id,state){
	SSTFacade.updateUbicacionByEstado({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado la ubicacion correctamente','Información',function(){
			$('#resultados').flexReload();
		});
	}});
};


var setupUbicacion = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idUbicacion=' + id ;  
};
