$(document).ready(function(){
	SSTFacade.listModulosMenu({async:true,callback:function(modulos){
		var grupo = -1;
		var ul;
		var mod_seleccionado = $.cookie("mod_seleccionado");
		$.each(modulos,function(i,modulo){
			if (modulo.grupo != grupo) {
				grupo = modulo.grupo;
				ul = $('<ul></ul>');
				$('.menu').append(ul);
			}
			var a = $('<a></a>')
					.text(modulo.etiqueta)
					.attr('href','#')
					.attr('onclick',"redirectModulo('/index.do?e="+modulo.codigo+"&m="+moduloInicial.codigo+"','"+modulo.codigo+"')");

			var div = $('<div></div>').append(a);
			var li = $('<li></li>').append(div);
			if (mod_seleccionado && mod_seleccionado == modulo.codigo) {
				div.addClass('selected');
			}
			ul.append(li);
		});
	}});
});

var redirectModulo = function(url,modulo) {
	$.cookie("mod_seleccionado", modulo);
	location = context + url;
};