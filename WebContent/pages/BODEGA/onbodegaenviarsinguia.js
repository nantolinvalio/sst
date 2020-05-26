$(document).ready(function(){
	$('select').not('#region\\.id, #idSTecnico, #comuna\\.id').selectInput();
	$('.fecha').datepicker();
	$('#contendorEnvio').hide();
	$('#otroST').hide();
	
	var fechaActual;
	SSTFacade.getDate({async:false, callback:function(fecha) {
		fechaActual = fecha;
	}});
	
	var minDate = fechaActual.clone().addDays(-1);
	var maxDate = fechaActual.clone().addDays(1);
	
	minDate = removeTimeFromDate(minDate.clone());
	maxDate = removeTimeFromDate(maxDate.clone());
	
	$('.fechaHora').datetimepicker({
		minDate: minDate,
		maxDate: maxDate
	});
	
	
	SSTFacade.listFamilia({sync:true,callback:function(familias){
		$('#idFamilia').addItems(familias,"id","nombre",true);
	}});
	
	SSTFacade.listMarca({async:true,callback:function(marcas){
		$('#idMarca').addItems(marcas,"codigo","nombre",true);
	}});
	
	SSTFacade.listSucursales({async:true,callback:function(sucursales){
		$('#idSucursal').addItems(sucursales,"id","nombre",true);
	}});
	
	SSTFacade.listSTecnicoByFilter({},{async:true, callback:function(sTecnicos){
		$('#idSTecnico').addItems(sTecnicos,"id",["glosa","direccion"],true);
		$('#idSTecnico').addItems([{id:-2,glosa:"Otro servicio técnico"}],"id","glosa",false);
	}});
	
	SSTFacade.getUbicacionSession({async:false,callback:function(ubicacion){
		$('#otroST').find('#region\\.id').addItems([ubicacion.region],"id","glosa",false);
		$('#otroST').find('#region\\.id').attr('disabled','');
	}});
	
	SSTFacade.listComunasConSTByRegion($('#otroST').find('#region\\.id').val(),{async:true,callback:function(comunasRegion){
		$('#otroST').find('#comuna\\.id').addItems(comunasRegion,"id","glosa",true);
	}});;
	
	$('#idSTecnico').change(function(){
		if($('#idSTecnico').val()==-2){
			$('#otroST').show();
			$('#otroST').find('input, select').addClass('required');
		} else {
			$('#otroST').hide();
			$('#otroST').find('input, select').removeClass('required');
			limpiarTableInput($('#otroST'));
		}
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
		$('#contendorEnvio').hide();
	});
	
	$('#filtro_buscador').keypress(function(e){
        if(e.which == 13) {
        	buscar();
        }
	});
	
	$('#buscar').click(function(){
		buscar();
	});
	
	var buscar = function(){
		if($('#filtro_buscador').valid()){
			var filter = $('#filtro_buscador').serializeObject();
			filter.paraEnvioSinGuia = true;
			$('#contendorGrilla').show();
			$('#contendorEnvio').show();
			$('#resultados').loadData([filter]);
		}
	};
	
	var exportDocument = function(type){
		var p = $('#resultados').getParameters()[0];
		if (p == undefined) {
			jAlert(msg.sinParametros,'Información');
			return;
		}
		
		p.orderBy = $('#resultados').getSortName()[0];
		p.sortOrder = $('#resultados').getSortOrder()[0];
		var f = $('#resultados').getFilterColumna()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=GuiaPendienteBodegaReport" +
			"&filterColumn=" + JSON.stringify(f) + 
			"&filter=" + JSON.stringify(p);
		$.openWindowsMenubar(url, "GuiaPendienteBodegaReport", 600, 800);
	};
	
	
	$('#resultados').flexigrid({
		showToggleBtn: true,
		dwrFunction: SSTFacade.listGuiasBodegaByFilter,
		multisel:false,
		singleSelect:true,
		tipo: 'GESG',
		seccion:80005000,
		overrideModel: [{findName:'origen.nombre',propiedad:function(o){
			if (o.origen) {
				if (o.origen.tipo == 'ST') {
					return o.origen.glosa + ' ' + o.origen.nombre;
				} else {
					return o.origen.glosa + ' ' + o.origen.id + ' ' + o.origen.nombre;
				}
			}
		}},{findName:'seleccionado',propiedad:function(o){
			var check = $('<input type="checkbox">')
            .attr('id', 'chk' + o.id)
            .attr('name', 'chk' + o.id)
            .attr('checked',false);
			return check;
		}}],
		buttons : [
	   		{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
	   		{separator: true},
	   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
	   		{separator: true}
		]
	});
	
	$('#enviar').click(function(){ //TODO: validar la seleccion de OT(s) en la grilla
		var oTs = [];
		var filas =[];
		filas = $('#resultados').getJSONFromHTML();
		$.each(filas,function(index,fila){
			if(fila.seleccionado){
				oTs.push(fila.ordenTrabajo);
			}
		});
		if(oTs.length>0){
			var idSTecnico =$('#idSTecnico').val();
			if(idSTecnico==-2){
				if($('#envioOT').valid()){
					var ubicacion = $('#envioOT').serializeObject();
					SSTFacade.saveServicioTecnico(ubicacion,{async:false,callback:function(sTecnico){
						idSTecnico = sTecnico.id;
					}});
					SSTFacade.emitirEnvioSinGuia(oTs,{id:idSTecnico},$('#fechaEmision').val(),{async:true,callback:function(){
						jAlert('La(s) ordene(s) de trabajo se enviaron correctamente.','Aviso',function(){
							location.reload();
						});
					}});
				}
			} else if ($('#envioOT').valid()){
				SSTFacade.emitirEnvioSinGuia(oTs,{id:idSTecnico},$('#fechaEmision').val(),{async:true,callback:function(){
					jAlert('La(s) ordene(s) de trabajo se enviaron correctamente.','Aviso',function(){
						location.reload();
					});
				}});
			}
		} else {
			jAlert('No a seleccionado ninguna orden de trabajo','Aviso');
		}
	});
	
	var limpiarTableInput = function(el){
		$(el).find('input').val("");
	};
});