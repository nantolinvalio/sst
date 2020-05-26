var oTs = [];
$(document).ready(function(){
	
	$('#buscar').click(function(){
		var inpt = new Array;
		inpt.push($('#idOT'));
		inpt.push($('#numeroGuiaRetiro'));
		inpt.push($('#buscadorManual').find('#codigoBarra'));
		inpt.push($('#numeroGuiaDespachoST'));
		
     
		if (!validEmptyInputs(inpt,'Debe ingresar al menos uno de los parametros de busqueda')) {
			return;       
		}
		if (!$('#buscadorManual').valid()){
			return;
		}
		var filtro = $('#buscadorManual').serializeObject();
		buscar(filtro);
	});
	
	$('#buscadorAutomatico').find('#codigoBarra').keypress(function(e){
		if(e.which == 13) {
			if ($('#buscadorAutomatico').find('#codigoBarra').valid()) {
				var filtro = $('#buscadorAutomatico').serializeObject();
				buscar(filtro);
			}
	    }
	});
	
	var buscar = function (filter) {
		$('#buscadorAutomatico').find('#codigoBarra').val('');
		$('#buscadorAutomatico').find('#codigoBarra').focus();
		SSTFacade.getOTRevisaSalaProveedores(filter,{async:true,callback:function(ot){
			$('#buscadorManual').reset();
			var ordenTrabajo = $('#ordenesTrabajo').getJSONById(ot.id);
			if (ordenTrabajo == null) {
				oTs.push(ot);				
			} else {
				jAlert('La OT ' + ot.id + ' ya existe en esta revisión', 'Información');
			}
			loadAll();
		}});
	};
	
	
	
	$('#ordenesTrabajo').flexigrid({
		height:'auto',
		usepager:false,
		multisel:false,
		singleSelect:true,
		onDragCol:false,
		showToggleBtn:false,
		minheight: 80,
		colModel :[
		   {display:'Eliminar', width:100, align:'center', name_abbr:"eliminar", name:function(o){
			   var btn = $('<input type="button" value="Eliminar">')
			   .attr('onclick','eliminar('+o.id+')');
			   return btn;
		   }},
		   {display:'N° OT',         width:50,  align:'left',  name_abbr:'id',                    name:'id'},
		   {display:'Cod Barras',    width:80,  align:'right', name_abbr:'codigoBarra',           name:'codigoBarra'},
		   {display:'Producto',      width:230, align:'left',  name_abbr:'producto.descripcion',  name:'producto.descripcion'},
		   {display:'Marca',         width:200, align:'left',  name_abbr:'producto.marca.nombre', name:'producto.marca.nombre'},
		   {display:'Clasificación', width:50,  align:'left',  name_abbr:'clasificacion.codigo',  name:'clasificacion.codigo'},
		]
	});
	
	SSTFacade.listParametrosByTipo("CB",{async:false,callback:function(data){
        $("#clasificacion").addItems(data, "codigo", "glosa", true);
        $('#clasificacion option[value="RDEFP"]').remove();
    }});
	
	$('#grabar').click(function(){
		if($('#onsalaproveedoresrevisarot').valid()){
			$.each(oTs,function(index,ordenTrabajo){
				ordenTrabajo.clasificacion={codigo:$('#clasificacion').val()};
				ordenTrabajo.nombreTecnico = $('#nombreTecnico').val();
				ordenTrabajo.observacionRevision =$('#observacion').val();
			});
			SSTFacade.revisarMasivoOnSalaProveedores(oTs,{async:true,callback:function(){
				jAlert('Productos revisado con éxito','aviso',function(){
					location.reload();
					$('#revision').reset();
				});
			}});
		}
	});
	
	
	$('#revision').hide();
	
});

var loadAll = function() {
	$('#ordenesTrabajo').flexAddData({rows:oTs,total:oTs.length});
	if(oTs.length>0){
		$('#revision').show();
	} else {
		$('#revision').hide();
	}
};

var eliminar = function(idOT){
	oTsAux = [];
	$.each(oTs, function(index, oT){
		if(oT.id != idOT){
			oTsAux.push(oT);
		}
	});
	oTs = oTsAux;
	loadAll();
};


