var callbackAfterError;

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
    	if(this.name.indexOf('input-no-') != -1) {
    		return;
    	} else if(this.name.indexOf('.') < 0) {
    		o[this.name] = this.value;
    	} else {
    		var attributes = this.name.split('.');
            var actual = o;
            for (var i = 0; i < attributes.length - 1; i++) {
                  var att = attributes[i];
                  if (actual[att] == undefined)
                       actual[att] = new Object();
                  actual = actual[att];
            }
            actual[attributes[attributes.length - 1]] = this.value;
    	}
    });
    return o;
};

var removeTimeFromDate = function(d) {
	var curr_day = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();
	return new Date(curr_year, curr_month, curr_day);
};

$.fn.loadObject = function(o) {
	o = $.loadObject(o, this);
};

$.fn.isEmpty = function() {
	var rtn = true;
	$.each(this,function(){
		var v = $(arguments[1]).val();		
		if (rtn) rtn = (v === null || v === 'null' || v === undefined || v === 'undefined' || v === ''); 
	});
	return rtn;
};

$.loadObject = function(o, frm, rn) {
	$.each(o,function(){
		if (arguments[1] instanceof Object && !(arguments[1] instanceof Date))
			$.loadObject(arguments[1], frm, (rn === undefined ? "" : rn) + arguments[0] + '\\.');
		else {
			var id = (rn === undefined ? "" : rn) + arguments[0];
			var el = $(frm).find('name="' + id + '"');
			el = (el === undefined || el.length === 0) ? $(frm).find('#' + id + '') : el;
			var strArgument = arguments[1] + " ";
			if (el !== undefined && el.length !== 0 && !((arguments[1]==null) || ((strArgument.replace(/\s/gi, '')) == ''))){
				
				if (el[0].tagName == 'SELECT') {
					$(el[0]).find('option[value="' + arguments[1] + '"]').attr('selected','selected');					
				} else if (el[0].tagName == 'INPUT') {
					if($('#'+el[0].id).attr('format') != null){
						el.val(arguments[1].toString($('#'+el[0].id).attr('format')));
					} else if ($(el).is(':checkbox')){
						el.attr('checked',arguments[1]);
					} else {
						el.val(arguments[1]);
					}			
				} else {
					var newid = el[0].id.replace(".","\\.");
					if($('#'+newid).attr('format')!=null ){
						el.text(arguments[1].toString($('#'+newid).attr('format')));
					} else{
						el.text(arguments[1]);	
					}
									
				}
			}	
		}
	});
};

$.fn.removeItems = function(values){
	$.each(values,function(index,value){
		if(!(value==''||value===undefined)){
			this.find('option[value=]').remove();
		}
	});
};

$.fn.getSelectedItems = function(){
	var values = [];
	this.find('option:selected').each(function(){
		values.push(this.val());
	});
	return values;
};

$.fn.getAllItems = function(){
	var values = [];
	this.find('option').each(function(){ 
		values.push(this.value);
	});
	return values;
};

$.fn.addItems = function(data, id, text, initial, separator, atributos, title, initialText) {
	separator = separator === undefined ? ' ' : separator;
	initial = initial === undefined ? false : initial;
	atributos = atributos === undefined ? [""]:atributos;
    return this.each(function() {
        var list = this;
        if (initial) {
        	if(initialText === undefined){
        		list.add(new Option('[SELECCIONE]', ''));
        	}else{
        		list.add(new Option(initialText, ''));
        	}
        	
        }
        $.each(data, function(index, itemData) {
        	var newText = null;
        	if (text.constructor == Array) {
        		$.each(text,function(idx,name){
        			var txt = $.getJSONRealValue(itemData,name);
        			newText = newText === null ? txt + '' : newText + separator + (txt === undefined ? '' : txt);
        		});
        	} else {
        		var txt = $.getJSONRealValue(itemData,text);
        		newText = txt === undefined ? '' : txt;
        	}
        	var opt = $('<option></option>');
        	opt.val($.getJSONRealValue(itemData,id));
        	opt.text(newText);
        	
        	for(var i =0 ; i < atributos.length ; i++){
        		opt.attr(atributos[i].attr,atributos[i].valor);
        	}        	
        	
        	if (title) {
        		opt.attr('title',$.getJSONRealValue(itemData,title));
        	}
        	$(list).append(opt);
        });
    });
};

$.fn.reset = function () {
	$(this).find('input[type=hidden]').val('');
	$(this).find('select :selected').removeAttr('selected');
	$(this).find('textarea').text('');
	$(this).find('select').find('option[value=]').attr('selected','selected');	
	$(this).find('input[type=checkbox]').attr('checked',false);
	$(this).find('input, select, textarea').removeAttr('title').removeClass('error');
	$(this).each(function() { 
		this.reset(); 
	});
	$(this).valid();
};

$.getJSONRealValue = function(o, name, model) {
	if (name == null) return "";
	if (o == null) return "";
	if (typeof(name)==='function') {
		return name(o);
	}
   	if (name.indexOf('.') < 0) {
   		if (o[name] instanceof Date && model.format != undefined && model.format != null && model.format != '') {
   			return o[name].toString(model.format);   			
   		}
   		return o[name] == null ? '' : o[name].toString().replace(/\n/g,'<br>');
	} else {
		var attributes = name.split('.');
        for (var i = 0; i < attributes.length; i++) 
        	o = o == null ? null : o[attributes[i]];
        if (o instanceof Date && model.format != undefined && model.format != null && model.format != '') {
        	return o.toString(model.format);        	
        }
        return o == null ? '' : o.toString().replace(/\n/g,'<br>');
	}
};

$.openWindowsMenubar = function(path, nombre) {
      var height = 600;
      var width = 800;
	  var top = (screen.height - height) / 2;
	  var left = (screen.width - width) / 2;
	  var wind = window.open(path, nombre, 'height=' + height + ',width=' + width + ',resizable=yes,scrollbars=yes,menubar=yes,top=' + top + ',left=' + left);
};

var errorHandler = function(message,title,callback,ex) {
	$.alerts.okButton = '&nbsp;Ok&nbsp;';
	if (callback == null && callbackAfterError != null) {
		callback = callbackAfterError;
	}
	
	jAlert(message, title, callback);
};

var exceptionHandler = function(message,title,callback,ex) {
	$.alerts.okButton = '&nbsp;Ok&nbsp;';
	if (callback == null && callbackAfterError != null) {
		callback = callbackAfterError;
	}
	
	$('#errno_app').dialog('option','title',title);
	$('#errno_app').find('#errno_m').text(message);
	$('#errno_app').find('#errno_cause').text(ex.cause);
	$('#errno_app').find('#errno_javaClassName').text(ex.javaClassName);
	$('#errno_app').find('#errno_message').text(ex.message);

	$('#exception').hide();
	$('#stackTrace').hide();

	$('#stackTrace > tBody > *').not('#stackTrace_step').remove();
	if (ex.stackTrace) {
		$.each(ex.stackTrace,function(i,stack){
			var stack_step = $('#stackTrace_step').clone()
							.attr('id','stackTrace_step' + i)
							.show()
							.appendTo('#stackTrace');
			stack_step.find('#errno_className').text(stack.className);
			stack_step.find('#errno_fileName').text(stack.fileName);
			stack_step.find('#errno_lineNumber').text(stack.lineNumber);
			stack_step.find('#errno_methodName').text(stack.methodName);
		});
	}
	
	$('#errno_app').dialog('open');
};


dwr.engine.setErrorHandler(function (message, ex){  
	if(ex){  
		if (ex.javaClassName === 'cl.abcdin.sst.exceptions.SSTException') {
			errorHandler(message, 'Información',null,ex);
		} else if (ex.javaClassName === 'cl.abcdin.sst.exceptions.AccessException') {
			parent.location = context;
		} else if (ex.javaClassName == null){
			SSTFacade.logClientException(ex,message,{async:true,callback:function(){
				exceptionHandler('Lo sentimos, ha ocurrido un error en el sistema', 'ERROR',null,ex);				
			}});
		} else {
			exceptionHandler('Lo sentimos, ha ocurrido un error en el sistema', 'ERROR',null,ex);
		}
	}  
});

function largestZindex(element) {
	var allObjects = $(element);
	var allObjectsArray = $.makeArray(allObjects);
	var zIndexArray = [0];
	var largestZindex = 0;
	for (var i = 0; i < allObjectsArray.length; i++) {
		if($.isNumeric($(allObjectsArray[i]).css('z-index'))) {
			zIndexArray.push($(allObjectsArray[i]).css('z-index'));			
		}
	}
	var largestZindex = Math.max.apply(Math, zIndexArray);
	return $.isNumeric(largestZindex) ? largestZindex : 9999;
};

dwr.engine.setPreHook(function() {
	var zIndex = largestZindex('body > *');
	var waiting = $('<div class="ui-widget-overlay" id="disabledImageZone"/>');
	waiting.append('<div class="disabledImageZoneImg"/>');
	waiting.css('width', $( document ).width());
	waiting.css('height', $( document ).height());
	waiting.css('z-index',zIndex + 1);
	$('body').append(waiting);
});

dwr.engine.setPostHook(function() {
	$('#disabledImageZone').remove();
});

$(document).ready(function(){
	var msg = $('<div id="serverException"/>');
	msg.append('<div id="serverExceptionMessage"/>');
	$('body').append(msg);
	$('#serverException').dialog({
		autoOpen: false,
		modal:true,
		position:[100,100],
		buttons:{
			Cerrar: function() {
				$( this ).dialog( "close" );
			} 
		}
	});
	
	$('form').submit(function(e){
	    e.preventDefault();
	});
});

/**Validar varios inputs juntos*/
var validEmptyInputs = function(inputs,msg,padre){
	valido = false;
//	TODO revisar validacion en ordentrabajo
	if(padre!=null && padre!=''){
		$.each(inputs,function(index, input){
			if($('#'+padre).find(input.attr('id')).val()!=''){
				valido=true;
			};
			$('#'+padre).find(input.attr('id')).change(function(){
				validEmptyInputs(inputs,msg);
			});
		});
		
		if(valido){
			$.each(inputs,function(index, input){
				$('#'+padre).find(input.attr('id')).removeClass('error2');
				$('#'+padre).find(input.attr('id')).removeAttr('title',msg);
			});
			return true;
		}
		else{
			$.each(inputs,function(index, input){
				$('#'+padre).find(input.attr('id')).addClass('error2');
				$('#'+padre).find(input.attr('id')).attr('title',msg);
			});
			return false;
		}
	}
	else{
		$.each(inputs,function(index, input){
			if($(input).val()!=''){
				valido=true;
			};
			$(input).change(function(){
				validEmptyInputs(inputs,msg);
			});
		});
		
		if(valido){
			$.each(inputs,function(index, input){
				$(input).removeClass('error2');
				$(input).removeAttr('title',msg);
			});
			return true;
		}
		else{
			$.each(inputs,function(index, input){
				$(input).addClass('error2');
				$(input).attr('title',msg);
			});
			return false;
		}
	}
};

$.leftPad = function(i,l,s) {
	var o = i.toString();
	if (!s) { s = '0'; }
	while (o.length < l) {
		o = s + o;
	}
	return o;
};

var msg = {
	sinParametros : 'No hay parametros para generar el archivo'
};

var context = location.pathname.substring(0, location.pathname.indexOf('/',2));
var usuario = {};
SSTFacade.getUsuarioSession({async:false, callback:function(usr) {usuario = usr;}});

var moduloInicial = {};
SSTFacade.getModuloInicialByUsuario({async:false, callback:function(modulo) {moduloInicial = modulo;}});

var ubicacion = {};
SSTFacade.getUbicacionSession({async:false,callback:function(u){ubicacion = u;}});

var printCodigo = function(barcode) {
	var moduloCodigoBarras;
	SSTFacade.getModuloByNombreUsuario('onimprimircodigobarras',{async:false, callback:function(modulo) {
		moduloCodigoBarras = modulo ? modulo : undefined;
	}});
	if (moduloCodigoBarras == undefined) {
		jAlert('No tiene permisos para imprimir códigos de Barra','Información');
	} else {
		var url = context + "/index.do?e=" + moduloCodigoBarras.codigo+"&m="+moduloInicial.codigo+"&codigoBarras=" + barcode + "&justpopup=true";
		$.openWindowsMenubar(url, "ImprimirCodigoBarras", 200, 200);	
	}
}; 