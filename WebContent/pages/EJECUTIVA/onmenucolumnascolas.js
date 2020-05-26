$(document).ready(function() {
	var change = false;
	var idRol=3;
	var idIndicador = null;
	var idColumna = null;
	var idSeccion = 40001000;
	
	$('#grabar, select, #quitar, #agregar').attr('disabled',true); // Desabilita los componentes	
		
	$('.btnIndicador').click(function(){
		var id = $('.btnIndicador').attr('id');
		if (change) {
			change = false;
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;No&nbsp;';
			jConfirm('Se han realizado cambios en las columnas. desea grabarlos antes de continuar?', 'Confirmación', function(r){
				if (r) {
					savecolumnas(this);
					clickbutton($('#' + idIndicador)[0]);
				} else {
					clickbutton($('#' + idIndicador)[0]);
				}
			});

		} else {
			clickbutton(this);			
		}
	});
	
	$('#agregar').click(function(){
		var sel = $("#columnasPosibles").find(':selected');
		if (sel != null) change = true;
		$("#columnasTabla").append(sel);
		changeDescripcion();
	});
	
	$('#quitar').click(function(){
		var sel = $("#columnasTabla").find(':selected');
		if (sel != null) change = true;
		$("#columnasPosibles").append(sel);
		changeDescripcion();
	});
	
	
	$('#subirOpcion').click(function(){
		var sel = $("#columnasTabla").find(':selected');
		if (sel != null) change = true;
		$(sel[0]).prev().before(sel);
	});

	
	$('#bajarOpcion').click(function(){
		var sel = $("#columnasTabla").find(':selected');
		if (sel != null) change = true;
		$(sel[sel.length - 1]).next().after(sel);
	});

	
	$('#grabar').click(function(){
		if (change) {
			$.alerts.okButton = '&nbsp;Si&nbsp;';
			$.alerts.cancelButton = '&nbsp;Cancelar&nbsp;';
			jConfirm('¿Esta seguro que desea grabar los cambios?', 'Confirmación', function(r){
				if (r) {
					change = false;
					savecolumnas();
					clickbutton($('#' + idIndicador)[0]);
				}	
			});
		} else {
			$.alerts.okButton = '&nbsp;Ok&nbsp;';
			jAlert('No hay modificaciones para grabar','Información');
		}
	});
		
	var clickbutton = function(el) {
		idIndicador = el.id;
		$("#columnasTabla").empty();
		$("#columnasPosibles").empty();
		$('#grabar, select, #quitar, #agregar').attr('disabled',false);
		$('input[type=button]').attr('disabled',false);
		$('#title').text('Columnas de indicadores de ejecutivas de colas: ' + $(el).val());
		$('#firstTitle').text('Configuración de Columnas para: ' + $(el).val());
		$('#descripcion').find('label').remove();
		$(el).attr('disabled',true);
		var filter = {
			tipo:'OT',
			idRol: idRol,
			idIndicador: el.id
		};
		SSTFacade.listColumnasIndicador(filter,{async:false, callback:function(columnas){
			$("#columnasTabla").addItems(columnas, 'id', 'nombre', false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
		SSTFacade.listColumnasNotInIndicador(filter,{async:false, callback:function(columnas){
			$("#columnasPosibles").addItems(columnas, 'id', 'nombre', false,': ',[{attr:'onclick',valor:'changeDescripcion()'}], 'descripcion');
		}});
	};
	
	var savecolumnas = function(el){ 
		var columna = [];
		$("#columnasTabla").find('option').each(function(){
			columna.push({id:$(arguments[1]).val()});		
		});
		var indicador = {
			id:idIndicador	
		};
		var rol = {
			id:idRol
		};
		var seccion ={
			id:idSeccion
		};
	
		SSTFacade.saveSeccionColumnaIndicador(indicador, rol, seccion, columna, {async:false,callback:function(o){
			jAlert('Columnas agregadas correctamente','Informacion',function(){
				$('#descripcion').find('label').remove();
			});
		}});
	};
	
});

var changeDescripcion = function() {
	$('#descripcion').find('label').remove();
	$("#columnasPosibles, #columnasTabla").find(':selected').each(function(i,el){
		var lbl = $('<label><\label>')
					.css('width','100%')
					.css('display','block')
					.append($('<b></b>').text($(el).text()))
					.append(':: ' +$(el).attr('title'));
		$('#descripcion').append(lbl);
	});
};
