var pages = [];

var loadSubmodulosByPage = function(pageName,idDiv, loadOnStart, object, displayAtTheEnd, loadOnDemand) {
	if (pageName == null) {
		return;
	}

	idDiv = idDiv == null ? 'tabs' : idDiv;
	loadOnStart = loadOnStart == null ? false : loadOnStart;
	loadOnDemand = loadOnDemand == null ? false : loadOnDemand;
	displayAtTheEnd = displayAtTheEnd == null ? false : displayAtTheEnd;
	
	SSTFacade.listSubModuloByFilter(pageName, {async:false,callback:function(submodulos){
		$.each(submodulos, function(i,submodulo){
			var href = $('<a></a>')
				.text(submodulo.etiqueta)
				.attr('id',submodulo.nombre)
				.attr('href', context + '/pages/' + submodulo.macro + '/' + submodulo.nombre + '.jsp' );
			var tab = $('<li></li>')
				.append(href);
			$('#' + idDiv +' > ul').append(tab);
			pages.push(submodulo.nombre);
		});
	}});
	
	$('#' + idDiv).tabs({
	    cache: true,
	    load:function(ev,ui){
			eval('init' + ui.tab.id)();
			if (loadOnStart || (loadOnDemand && ui.index == 0)) {
				eval('load' + ui.tab.id)(object);
			}
			if ($(this).tabs('length') - 1 == ui.index) {
				$(this).tabs('select', 0);
				if (displayAtTheEnd) {
					$(this).show();
				} 
			} else {
				$(this).tabs('select', ui.index + 1);
			}
	    }
	});
};

var loadEtapasOT = function(ordenTrabajo, idDiv){
	idDiv = idDiv == null ? 'etapasOT' : idDiv;
	$('#' + idDiv + ' > *').remove();
	
	SSTFacade.listResumenBitacorasByIdOT(ordenTrabajo.id,{async:false,callback:function(bitacoras){
		var tr = $('<tr></tr>');
		var insertlasttd = true;
		$('#' + idDiv).append(tr);
		$.each(bitacoras,function(i,bitacora){
			var td = $('<td>' + bitacora.estado.glosa + '</td>')
				.addClass('td_etapa')
				.addClass(bitacora.vigente == true ? 'enabled' : 'disabled');
			tr.append(td);
			if (tr.parent().parent().parent().width() < tr.width()) {
				td.remove();
				tr = $('<tr></tr>');
				$('#' + idDiv).append(tr);
				tr.append(td);
				insertlasttd = false;
			}
			if (bitacora.bitacorasInternas != null && bitacora.bitacorasInternas.length > 0) {
				createInternalEtapasOT(bitacora, td);
				td.addClass(bitacora.estado.icono + '_bi');
			} else {
				td.addClass(bitacora.estado.icono);
			}
		});
		if (insertlasttd) {
			tr.append('<td width="100%"></td>');			
		}
	}});
};

var createInternalEtapasOT = function(bitacora, td) {
	var mac = $('<div></div>').addClass('marco').css('padding','0 !important');
	var table = $('<table></table>').addClass('table_plana');
	var trI = $('<tr></tr>');
	$('body').append(mac);
	mac.append(table);
	table.append(trI);
	
	$.each(bitacora.bitacorasInternas,function(i,bitacoraInterna){
		var tdI = $('<td>' + bitacoraInterna.estado.glosa + '</td>')
			.addClass('td_etapa')
			.addClass(bitacoraInterna.estado.icono)
			.addClass(bitacoraInterna.vigente == true ? 'enabled' : 'disabled');	
		trI.append(tdI);
		if (trI.parent().parent().parent().width() < trI.width()) {
			tdI.remove();
			tr = $('<tr></tr>');
			table.append(tr);
			tr.append(tdI);
		}
	});
	
	mac.dialog({
		autoOpen: false,
		modal:true,
		width:table.width() + 40,
		resizable: false,
		title:'Detalle de la Bitacora',
		buttons:{
		}
	});
	
	td.click(function(){
		mac.dialog('open');
	});
	
};

var loadAllTabsWithOrdenTrabajo = function(ordenTrabajo) {
	$.each(pages, function(i,page){
		eval('load' + page)(ordenTrabajo);						
	});
};

/**
 * Función de redireccionamiento
 * 
 * 
 */

function redirectOtDetalle(id){
	var moduloCA;
	var moduloSM;
	var moduloOT;
	var url = context +'/index.do?';

	SSTFacade.getModuloByNombreUsuario('onmenuordentrabajo',{async:false, callback:function(moduloDetalleOT) {
		moduloOT = moduloDetalleOT;
	}});		
	SSTFacade.getModuloByNombreUsuario('ondetalleotcambioautomatico',{async:false, callback:function(moduloDetalleCA) {
		moduloCA = moduloDetalleCA;
	}});		
	SSTFacade.getModuloByNombreUsuario('onmenuordendetrabajosinmodificar',{async:false, callback:function(moduloDetalleSM) { 
		moduloSM = moduloDetalleSM;
	}});
	
	var ordenTrabajo;
	SSTFacade.getOTById(id,{async:false,callback:function(ot){
		ordenTrabajo = ot;
	}});

	if(ubicacion.tipo == 'SC'){
		if (ubicacion.id == ordenTrabajo.sucursal.id) {
			if (ordenTrabajo.tipo.codigo == 'CA') {
				parent.location = url + 'e=' + moduloCA.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + ordenTrabajo.id;		
			} else {
				parent.location = url + 'e=' + moduloOT.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + ordenTrabajo.id;
			} 
		} else {
			parent.location = url + 'e=' + moduloSM.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + ordenTrabajo.id;
		}
	} else {
			if (ordenTrabajo.tipo.codigo == 'CA') {
				parent.location = url + 'e=' + moduloCA.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + ordenTrabajo.id;		
			} else {
				parent.location = url + 'e=' + moduloOT.codigo + '&m=' + moduloInicial.codigo +  '&idOT=' + ordenTrabajo.id;
			} 
	}
	
}

function colorSemaforo(i) {
		switch (i){
		case 1:
			return "#69D700";
			break;
		case 2:
			return "#007500";
			break;	
		case 3:
			return "#FDFD00";
			break;
		case 4:
			return "#FF0000";
			break;
		default: 
			return "";
		break;
	}
}
