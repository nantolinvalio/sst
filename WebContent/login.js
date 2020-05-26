$(document).ready(function() {
	var usu_cookie = JSON.parse($.cookie('usu_cookie'));
	
	SSTFacade.logout({async:false,callback:function(){}});
	$.cookie("mod_seleccionado", null);
	
	$('#ubicacion\\.id').attr('disabled',true);
	
	$('#login').keypress(function(e){
		if(e.which == 13) {
			buscarDependencias();
		}
	});
	
	$("#buscarDependencias").click(function(){
		buscarDependencias();
	});
	
	var buscarDependencias = function(){
		if($("#login").valid()) {
		    var usuario = $("#login").serializeObject();
		    SSTFacade.login(usuario,{async:false,callback:function(user){
		    	SSTFacade.listUbicacionesByUsuario(user, {async:false,callback:function(ubicaciones){
	    			if (!ubicaciones || ubicaciones.length == 0) {
	    				jAlert('El usuario no tiene ubicaciones configuradas en el sistema', 'Información');
	    				return;
	    			} else if (ubicaciones.length == 1) {
	    				$("#ubicacion\\.id").addItems(ubicaciones, "id", function(o){
	    					return o.tipo == 'OF' ? o.nombre : o.id + ' ' + o.nombre;
	    				}, false);	    				
	    			} else {
	    				$("#ubicacion\\.id").addItems(ubicaciones, "id", function(o){
	    					return o.tipo == 'OF' ? o.nombre : o.id + ' ' + o.nombre;
	    				}, true);	    				
	    			}
	    			$('#login').keypress(function(e){
	    				if(e.which == 13) {
	    					iniciarSesion();
	    				}
	    			});
	    			$('#ubicacion\\.id').attr('disabled',false);
	    			$("#buscarDependencias").hide();
	    			$("#iniciarSesion").show();
	    			$("#cambiarUsuario").show();
	    			$("#login, #password").attr('readonly', true);
		    	}});
		    }});
		}
	};
	
	$('#cambiarUsuario').click(function(){
		location.reload();
	});
	
	$('#iniciarSesion').click(function(){
		iniciarSesion();
	});
	
	var iniciarSesion = function() {
		if($("#login").valid()) {
			var usuario = $("#login").serializeObject();
			usuario.autoLogin = $("#autoLogin").is(':checked');		
			fullLogin(usuario);
		}
	};
	
	var fullLogin = function(usuario) {
	    SSTFacade.fullLogin(usuario,{async:false,callback:function(resp){
	    	if (resp) {
    			$.cookie('usu_cookie', JSON.stringify(usuario), {expires:30});
	    		window.location.href=context+'/index.do';
	    	} else {
	    		jAlert('No puede ingresar al sistema','Información');
	    	}
	    }});	
	};
	
	if (usu_cookie) {
		if (usu_cookie.autoLogin) {
			fullLogin(usu_cookie);
		} else {
			$('#login').find('#login').val(usu_cookie.login);
		}
	}
	
});