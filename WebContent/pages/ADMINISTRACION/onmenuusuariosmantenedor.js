$(document).ready(function() {
	var moduloCrear;
	SSTFacade.getModuloByNombreUsuario('onmenuusuarioscrear',{async:false, callback:function(modulo) {
		moduloCrear = modulo ? modulo : undefined;
	}});
	
	SSTFacade.listDependenciasByFilter({},function(data){
		$("#idUbicacion").addItems(data, "id", ["id", "nombre"], true);
	});

	$('#idUbicacion').selectInput();

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
	
	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listUsuarios,
		tipo: 'usuario',
		seccion: 50003000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		overrideModel: [
            {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';}},
            {findName:'nombreCompleto',propiedad:function(o){return o.vigente ? o.nombreCompleto : '<font color="red">' + o.nombreCompleto + '</font>';}},
            {findName:'cantidadUbicaciones',propiedad:function(o){
            	var glosa = o.cantidadUbicaciones == 1 ? 'Ubicación' : 'Ubicaciones';
            	var btn = $('<input type="button">')
            				.attr('value', o.cantidadUbicaciones + ' ' + glosa)
            				.attr('onclick','showUbicacionesUsuario(' + o.id + ')');
            	return btn;
        	}},
            {findName:'accion',propiedad:function(o){
            	var btn = $('<input type="button">');
            	if (o.vigente) {
            		btn.attr('value','Bloquear');
            		btn.attr('onclick','changeStateUsuario(' + o.id + ',false)');
            	} else {
            		btn.attr('value','Desbloquear');
            		btn.attr('onclick','changeStateUsuario(' + o.id + ',true)');
            	}
            	return btn;
           }}
	    ],
		dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + moduloCrear.codigo + '&m=' + moduloInicial.codigo +'&idUsuario=' + el.id;
		},
		buttons : [
	   		{name: 'Crear Nuevo Usuario', bclass: 'btnPlus', onpress : function(){
	   			parent.location = context +'/index.do?e=' + moduloCrear.codigo + '&m=' + moduloInicial.codigo;
	   		} },
	   		{separator: true}
		],
	});	
	
	$('#ubicaciones').flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onToggleCol:false,
		onDragCol: false,
		colModel : [
		            {display: 'Dependencia',width:190,align:'left', name_abbr: 'id', name:function(o){
		            	return o.id + ' ' + o.nombre;
		            }},
		            {display: 'Tipo',width:140,align:'left',name:'glosa', name_abbr: 'glosa'},
		            {display: 'Comuna',width:140,align:'left',name:'comuna.glosa', name_abbr: 'comuna.glosa'},
		            {display: 'Región',width:250,align:'left',name:'region.glosa', name_abbr: 'region.glosa'}
		]
	});
	
	$('#ubicacionesModal').dialog({
		autoOpen: false,
		modal:true,
		width:850,
		buttons:{
			Cerrar: function(){
				$('#ubicacionesModal').dialog('close');
			}
		}
	});
	
	$('#resultados').loadData([{}]);
	
});

var showUbicacionesUsuario = function(id) {
	$('#ubicacionesModal').dialog('open');
	SSTFacade.listAllByIdUsuario({id:id},{async:false,callback:function(ubicaciones){
		$("#ubicaciones").flexAddData({rows:ubicaciones,total:ubicaciones.length});
	}});
};

var changeStateUsuario = function(id,state) {
	SSTFacade.updateEstadoUsuario({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado al usuario correctamente','Información',function(){
			$('#resultados').flexReload();
		});
	}});
};
