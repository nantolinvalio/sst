$(document).ready(function() {
	var idRol = $('#idRol').val();
	var idModulo = $('#idModulo').val();
//	
	SSTFacade.getRolById(idRol, {async:false,callback:function(rol){
		$('#rol').text(rol.nombre);
	}});
	
	SSTFacade.getModuloById(idModulo, {async:false,callback:function(modulo){
		$('#pagina').text(modulo.etiqueta);
		$('#descripcionModulo').html(modulo.descripcion);
	}});
	
	$('#agregar').click(function(){
		var sel = $("#pestanasSistema").find(':selected');
		if (sel != null) change = true;
		$("#pestanasPagina").append(sel);
		changeDescripcion();
	});
	
	$('#quitar').click(function(){
		var sel = $("#pestanasPagina").find(':selected');
		if (sel != null) change = true;
		$("#pestanasSistema").append(sel);
		changeDescripcion();
	});

	
	$('#subirOpcion').click(function(){
		var sel = $("#pestanasPagina").find(':selected');
		$(sel[0]).prev().before(sel);
	});

	
	$('#bajarOpcion').click(function(){
		var sel = $("#pestanasPagina").find(':selected');
		$(sel[sel.length - 1]).next().after(sel);
	});
	
	var loadPestanasInModulo = function(){
		$("#pestanasPagina").empty();
		SSTFacade.listSubModulosInModuloByRol(idModulo,idRol,{async:false, callback:function(modulos){
			$("#pestanasPagina").addItems(modulos, 'id', function(o){
				var txt = o.macro;
				txt = txt + ': ' + (o.tipo == 'M' ? 'MENÚ' : 'PÁGINA');
				txt = txt + ': ' + o.etiqueta;
				return txt;
			}, false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
	};	
	
	var grabar = function(){
		var modulos = [];
		$("#pestanasPagina").find('option').each(function(){
			modulos.push({id:$(arguments[1]).val()});
		});
		var rol = {
			id:idRol	
		};
		var modulo = {
			id:idModulo
		};
		
		SSTFacade.saveConfiguracionPestana(rol,modulo,modulos,{async:false,callback:function(o){
			jAlert('Modulo configurado correctamente','Informacion',function(){
			});
		}});
	};
	
	$('#grabar').click(function(){
		grabar();
	});
	
	var loadPestanasNotInModulo = function(){
		$("#pestanasSistema").empty();
		SSTFacade.listSubModulosNotInModuloByRol(idModulo,idRol,{async:false, callback:function(modulos){
			$("#pestanasSistema").addItems(modulos, 'id', function(o){
				var txt = o.macro;
				txt = txt + ': ' + (o.tipo == 'M' ? 'MENÚ' : 'PÁGINA');
				txt = txt + ': ' + o.etiqueta;
				return txt;
			}, false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
	};
	
	loadPestanasNotInModulo();
	loadPestanasInModulo();
});

var changeDescripcion = function() {
	$('#descripcion').find('label').remove();
	$("#pestanasPagina, #pestanasSistema").find(':selected').each(function(i,el){
		var lbl = $('<label><\label>')
					.css('width','100%')
					.css('display','block')
					.append($('<b></b>').text($(el).text()))
					.append(':: ' +$(el).attr('title'));
		$('#descripcion').append(lbl);
	});
};