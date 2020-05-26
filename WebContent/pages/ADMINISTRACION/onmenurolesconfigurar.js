$(document).ready(function() {
	var moduloConfigurar;
	var moduloPestana;
	var change;
	var idRol = $('#idRol').val();
	
	SSTFacade.getModuloByNombreUsuario('onmenurolesconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getModuloByNombreUsuario('onmenurolesconfigurarpestana',{async:false, callback:function(modulo) {
		moduloPestana = modulo ? modulo : undefined;
	}});
	
	SSTFacade.getRolById(idRol, {async:false,callback:function(rol){
		$('#nombre').val(rol.nombre);
	}});
	
	$('#agregar').click(function(){
		var sel = $("#paginasSistema").find(':selected');
		if (sel != null) change = true;
		$("#paginasPerfil").append(sel);
		changeDescripcion();
	});
	
	$('#quitar').click(function(){
		var sel = $("#paginasPerfil").find(':selected');
		if (sel != null) change = true;
		$("#paginasSistema").append(sel);
		changeDescripcion();
	});
	
	var loadPaginasInRol = function(){
		$("#paginasPerfil").empty();
		SSTFacade.listModulosInRolByMacro(idRol,$('#idMacro').val(),{async:false, callback:function(modulos){
			$("#paginasPerfil").addItems(modulos, 'id', function(o){
				var txt = o.macro;
				txt = txt + ': ' + (o.tipo == 'M' ? 'MENÚ' : 'PÁGINA');
				txt = txt + ': ' + o.etiqueta;
				return txt;
			}, false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
	};	
	
	$('#configurarPestanas').click(function(){
		var modulos = [];
		$("#paginasPerfil").find(':selected').each(function(){
			modulos.push({id:$(arguments[1]).val()});
		});
		
		if (change) {
			$.alerts.okButton = '&nbsp;Grabar&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('Debe grabar los cambios para configurar las pestañas. ¿Desea grabar los cambios?', 'Confirmación', function(r){
				if (r) {grabar();}
			});
			return;
		}
		
		if (modulos.length == 0) {
			jAlert('Favor Seleccione una página del perfil primero','Información');
			return;
		}
		if (modulos.length > 1) {
			jAlert('Favor Seleccione SOLO una página del perfil','Información');
			return;
		}
		
		parent.location = context +'/index.do?e=' + moduloPestana.codigo + '&m=' + moduloInicial.codigo + '&idRol=' + idRol + '&idModulo=' + modulos[0].id;
	});
	
	var grabar = function(){
		if ($('#rol').valid()) {
			var modulos = [];
			$("#paginasPerfil").find('option').each(function(){
				modulos.push({id:$(arguments[1]).val()});
			});
			var rol_aux = {
				id: idRol,
				nombre:$('#rol').find('#nombre').val()
			};
			SSTFacade.updateRol(rol_aux, modulos,{async:false,callback:function(rol){
				jAlert('Perfil configurado correctamente','Informacion',function(){
					parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idRol=' + rol.id;					
				});
			}});
		}
	};
	
	$('#grabar').click(function(){
		grabar();
	});
	
	var loadPaginasNotInRol = function(){
		$("#paginasSistema").empty();
		SSTFacade.listModulosNotInRolByMacro(idRol,$('#idMacro').val(),{async:false, callback:function(modulos){
			$("#paginasSistema").addItems(modulos, 'id', function(o){
				var txt = o.macro;
				txt = txt + ': ' + (o.tipo == 'M' ? 'MENÚ' : 'PÁGINA');
				txt = txt + ': ' + o.etiqueta;
				return txt;
			}, false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
	};
	
	SSTFacade.listMacros({async:false,callback:function(macros){
		$('#idMacro').addItems(macros,"nombre","nombre",true);
	}});
	
	$('#idMacro').change(function(){
		loadPaginasNotInRol();	
	});

	loadPaginasInRol();
	loadPaginasNotInRol();

	
});



var changeDescripcion = function() {
	$('#descripcion').find('label').remove();
	$("#paginasPerfil, #paginasSistema").find(':selected').each(function(i,el){
		var lbl = $('<label><\label>')
					.css('width','100%')
					.css('display','block')
					.append($('<b></b>').text($(el).text()))
					.append(':: ' +$(el).attr('title'));
		$('#descripcion').append(lbl);
	});
};




