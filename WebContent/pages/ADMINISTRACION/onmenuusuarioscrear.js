$(document).ready(function() {
	
	var moduloDetalle;
	SSTFacade.getModuloByNombreUsuario('onmenuusuarioscrear',{async:false, callback:function(modulo) {
		moduloDetalle = modulo ? modulo : undefined;
	}});
	
	var idUsuario = parseInt($('#idUsuario').val());
		
	var loadComunas = function(idRegion) {
		$('#idComuna').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#idComuna').addItems(comunas,"id","glosa",true);
			loadDependencias();
		}});
	};
	
	var loadDependencias = function() {
		var filter = {
			idRegion:$('#idRegion').val(),
			idComuna:$('#idComuna').val(),
			vigente:true
		};
		$('#idDependencia').empty();
		SSTFacade.listDependenciasByFilter(filter,{async:false,callback:function(comunas){
			$('#idDependencia').addItems(comunas,"id",function(o) {
				return o.glosa + ' ' + o.id + ', ' + o.nombre + ', ' + o.direccion;
			},true);
		}});
	};
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#idRegion').addItems(data, "id", "glosa", true);
		loadComunas($('#idRegion').val());
	}});
	
	$('#idRegion').change(function(){
		loadComunas($(this).val());
	});
	
	$('#idComuna').change(function(){
		loadDependencias();
	});
	
	function getSelectedRoles() {
		var r = $('input[type=checkbox][id^=rol]:checked');
		var roles = [];
		$.each(r,function(i,rol){
			roles.push({id: parseInt(rol.id.replace('rol','')) });
		});
		return roles;
	}
	
	$('#grabar').click(function(){
		var ubicaciones = $('#dependencias').getJSON();
		var roles = getSelectedRoles();
		
		
		if ($('#usuarioForm').valid()) {
			if (ubicaciones.length == 0){
				jAlert('Debe ingresar al menos una dependencia','Error');
				return;
			}			
			if (roles.length == 0){
				jAlert('Debe ingresar al menos un rol','Error');
				return;
			}			
			
			var usuario = $('#usuarioForm').serializeObject();
			usuario.roles = roles;
			usuario.ubicaciones = ubicaciones;
			
			var nuevo = !$.isNumeric(idUsuario);
			

			SSTFacade.saveUsuario(usuario,nuevo,{async:false,callback:function(user){
				jAlert('Usuario grabado correctamente','Informacion',function(){
					parent.location = context + "/index.do?e=" + moduloDetalle.codigo + "&m=" + moduloInicial.codigo + "&idUsuario=" + user.id;					
				});
			}});
		}
	});
	
	SSTFacade.listRolesActivos({async:false,callback:function(roles){
		$.each(roles,function(i,rol){
			var chk = $('<input type="checkbox">')
						.attr('id','rol' + rol.id)
						.attr('name','rol' + rol.id)
						.css('width','10%');
			
			var dv = $('<div></div>')
						.append(chk)
						.append(rol.nombre);	
			
			if (i%2 == 0) {
				$('#rolLeft').append(dv);
			} else {
				$('#rolRight').append(dv);
			}
		});
	}});
	
	$('#agregarDependencia').click(function(){
		if ($('#ubicacionForm').valid()) {
			SSTFacade.getUbicacionById($('#idDependencia').val(),{async:false,callback:function(ubicacion){
				var ubicaciones = $('#dependencias').getJSON();
				var allowAdd = true;
				$.each(ubicaciones,function(i,el){
					if (el.id == ubicacion.id) {
						allowAdd = false;
					}
				});
				if (allowAdd) {
					ubicaciones.push(ubicacion);					
				}
				$('#dependencias').flexAddData({rows:ubicaciones, total:ubicaciones.length});
				$('#dependencias').recalcLayout();
			}});			
		}
	});
	
	$('#dependenciaModal').dialog({
		autoOpen: false,
		modal:true,
		width:850,
		buttons:{
			Cerrar: function(){
				$('#dependenciaModal').dialog('close');
			}
		}
	});
	
	$('#dependencias').flexigrid({
		height: 'auto',
		usepager:false,
		showToggleBtn: false,
		multisel:false,
		singleSelect:true,
		onToggleCol:false,
		onDragCol: false,
		colModel : [
            {display: 'Tipo',width:170,align:'left',name:'glosa'},
            {display: 'Dependencia',width:220,align:'left' ,name:'nombre'},
            {display: 'Dirección',width:300,align:'left' ,name:'direccion'},
            {display: 'Quitar',width:100,align:'center' ,name: function(o){
            	var btn = $('<input type="button">')
					.attr('value','Quitar')
            		.attr('onclick','deleteUbicacion(' + o.id + ')');
            	return btn;
            }}
		],
		buttons:[
         	{name: 'Agregar Dependencia', bclass: 'btnPlus', onpress : function(){
         		$('#dependenciaModal').dialog('open');
         	} },
        ]
	});
	
	if ($.isNumeric(idUsuario)) {
		$('#titlePage').text('EDITAR USUARIO');
		$('#rut').attr('readonly',true);
		SSTFacade.getUsuarioById(idUsuario,{async:false,callback:function(user){
			$('#usuarioForm').loadObject(user);
			SSTFacade.listRolesByUsuario(user,{async:false,callback:function(roles){
				$.each(roles,function(i,el){
					$('input[name=rol' + el.id + ']').attr('checked',true);
				});
			}});
			SSTFacade.listUbicacionesByUsuario(user,{async:false,callback:function(ubicaciones){
				$('#dependencias').flexAddData({rows:ubicaciones, total:ubicaciones.length});
				$('#dependencias').recalcLayout();
			}});
		}});
	} else {
		$('#titlePage').text('NUEVO USUARIO');
	}
});

var deleteUbicacion = function(id) {
	var ubicaciones = $('#dependencias').getJSON();
	ubicaciones = $.grep(ubicaciones, function(value) {
		return value.id != id;
	});
	$('#dependencias').flexAddData({rows:ubicaciones, total:ubicaciones.length});
};
