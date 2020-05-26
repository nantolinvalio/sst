$(document).ready(function(){
	$('#usuario_header').text(usuario.nombreCompleto.toUpperCase());
	$('#ubicacion_header').text((ubicacion.glosa + ' - ' + ubicacion.nombre + ', ' + ubicacion.direccion).toUpperCase());
	date_time('hora_header');
	$('#errno_app').dialog({
		autoOpen: false,
		modal:true,
		width:550,
		buttons:{
			Cerrar: function(){
				$('#errno_app').dialog('close');
			}
		}
	});
	
	$('#showExcepcion').click(function(){
		if ($('#exception').is(':visible')) {
			$('#exception').hide();
		} else {
			$('#exception').show();
		}
	});
	
	$('#showStackTrace').click(function(){
		if ($('#stackTrace').is(':visible')) {
			$('#stackTrace').hide();
		} else {
			$('#stackTrace').show();
		}
	});
});

function date_time(id) {
	var date = new Date().toString('dddd dd');
	date = date + ' de ' + new Date().toString('MMMM');
	date = date + ' de ' + new Date().toString('yyyy hh:mm:ss');
	$('#' + id).text(date);
	setTimeout('date_time("'+id+'");','1000');
}