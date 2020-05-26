var moduloConfigurar;

$(document).ready(function() {
		
	SSTFacade.getModuloByNombreUsuario('onmenurolesconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listRoles,
		tipo: 'perfil',
		seccion: 50004000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		height: 500,
		overrideModel: [
		   {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
		   {findName:'accion',propiedad:function(o){
            	var btn = $('<input type="button">');
            	if (o.vigente) {
            		btn.attr('value','Bloquear');
            		btn.attr('onclick','changeStatePerfil(' + o.id + ',false)');
            	} else {
            		btn.attr('value','Desbloquear');
            		btn.attr('onclick','changeStatePerfil(' + o.id + ',true)');
            	}
            	return btn;
           }},
		   {findName:'configurar',propiedad:function(o){
            	var btn = $('<input type="button">');
        		btn.attr('value','Configurar');
        		btn.attr('onclick','configureProfile(' + o.id + ')');
            	return btn;
           }}
        ],
		rp: 20,
		dblclickFunction:function(el,idx,comp){
			configureProfile(el.id);
		},
		buttons : [
	   		{name: 'Crear Nuevo Perfil', bclass: 'btnPlus', onpress : function(){
				$('#nombrePerfil').val('');
				$('#perfilModal').dialog('open');
	   		} },
	   		{separator: true}
		],
	});	
	
	
	$('#perfilModal').dialog({
		autoOpen: false,
		modal:true,
		width:700,
		position: [100,100],
		buttons:{
			Cerrar: function(){
				$('#perfilModal').dialog('close');
			}
		}
	});
	
	
	$('#agregarPerfil').click(function(){
		if ($('#perfil').valid()) {
			SSTFacade.saveRol({nombre:$('#nombrePerfil').val()},function(r)
				{$.alerts.okButton = '&nbsp;Ok&nbsp;';
				jAlert('Se ha creado el perfil ' + r.nombre ,'Información',function(){
					$('#resultados').flexReload();
					$('#perfilModal').dialog('close');
				});
			});
		}
	});
	
	$('#resultados').loadData([]);
});


var changeStatePerfil = function(id,state) {
	SSTFacade.updateEstadoRol({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el perfil correctamente','Información',function(){
			$('#resultados').flexReload();
		});
	}});
};

var configureProfile = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idRol=' + id;
};

