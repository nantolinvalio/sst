var moduloConfigurar;

$(document).ready(function() {
	var idSTecnico = $('#idSTecnico').val();
	SSTFacade.getModuloByNombreUsuario('onmenustecnicoconfigurar',{async:false, callback:function(modulo) {
		moduloConfigurar = modulo ? modulo : undefined;
	}});
	
	$('#buscar').click(function(){
		if($('#filtro_buscador').valid()) {
			var filter = $('#filtro_buscador').serializeObject();
			$('#resultados').loadData([filter]);
		}
	});
	
	$('#limpiar').click(function(){
		$('#filtro_buscador').reset();
		$('#resultados').clean();
	});
	
	var loadComunas = function(idRegion) {
		$('#comuna\\.id').empty();
		SSTFacade.listComunasByRegion(idRegion,{async:false,callback:function(comunas){
			$('#comuna\\.id').addItems(comunas,"id","glosa",true);
			//loadDependencias();
		}});
	};
	
	var loadDependencias = function() {
		var filter = {
			idRegion:$('#region.id').val(),
			idComuna:$('#comuna.id').val()
		};
		$('#idDependencia').empty();
		SSTFacade.listDependenciasByFilter(filter,{async:false,callback:function(comunas){
			$('#idDependencia').addItems(comunas,"id",function(o) {
				return o.glosa + ' ' + o.id + ', ' + o.nombre + ', ' + o.direccion;
			},true);
		}});
	};
	
	SSTFacade.listRegiones({async:false,callback:function(data){
		$('#region\\.id').addItems(data, "id", "glosa", true);
		loadComunas($('#comuna\\.id').val());
	}});
	
	$('#region\\.id').change(function(){
		loadComunas($(this).val());
	});
	
	$('#id').change(function(){
		loadDependencias();
	});

	var saveServicioTecnico = function(servTecnico){
		$('#mantenedorStecnicoForm').reset();
		if (servTecnico) {
			$('#mantenedorStecnicoForm').loadObject(servTecnico);
			if (servTecnico.comuna && servTecnico.comuna && servTecnico.region) {
				$('#region\\.id').val(servTecnico.region.id);				
				$('#comuna\\.id').empty();
				SSTFacade.listComunasByRegion(servTecnico.region.id,{async:false,callback:function(comunas){
					$('#comuna\\.id').addItems(comunas,"id","glosa",true);
				}});
				$('#comuna\\.id').val(servTecnico.comuna.id);	
			}
			$('#mantenedorStecnico').dialog('option', 'title', 'Modificar Servicio Técnico');
		} else {
			$('#mantenedorStecnico').dialog('option', 'title', 'Agregar Nuevo Servicio Técnico');
		}
		$('#mantenedorStecnico').dialog('open');
	};
	
	
	
	$('#resultados').flexigrid({
		dwrFunction:SSTFacade.listServicioTecnico,
		tipo: 'AST',
		seccion: 90007000,
		showTableToggleBtn: true,
		multisel:false,
		singleSelect:true,
		overrideModel: [
          {findName:'vigente',propiedad:function(o){return o.vigente ? '<font color="blue">Activo</font>' : '<font color="red">Bloqueado</font>';} },
          {findName:'glosa',propiedad:function(o){return o.vigente ? o.glosa : '<font color="red">' + o.glosa + '</font>';}},
          {findName:'accion',propiedad:function(o){
	         var btn = $('<input type="button">');
	         if (o.vigente) {
		      	 btn.attr('value','Bloquear');
			     btn.attr('onclick','changeStateServiciotecnico(' + o.id + ',false)');
	         } else {
				btn.attr('value','Desbloquear');
				btn.attr('onclick','changeStateServiciotecnico(' + o.id + ',true)');
	         }
	         return btn;
          }},
          {findName:'configuracion',propiedad:function(o){
          	var btn = $('<input type="button">');
      		btn.attr('value','Configurar');
      		btn.attr('onclick','configureProfile(' + o.id + ')');
          	return btn;
         }}  
        ],
		dblclickFunction:function(el,idx,comp){
			saveServicioTecnico(el);
		},
		buttons : [
            {name: 'Ingresar Nuevo Servicio Tecnico ', bclass: 'btnPlus', onpress : function(){saveServicioTecnico();}},
	   		{separator: true}
		],
	});	
	
	$("#resultados").loadData([{}]);
	
	$('#mantenedorStecnico').dialog({
		autoOpen: false,
		modal:true,
		width:920,
		buttons:{
			Grabar: function() {
				if($('#mantenedorStecnicoForm').valid()) {
					var servTecnico = $('#mantenedorStecnicoForm').serializeObject();
					jConfirm('¿Esta seguro que desea grabar el Servicio tecnico?', 'Confirmación', function(r){
						if (r) {
							SSTFacade.saveServicioTecnico(servTecnico,function(){
								$("#resultados").flexReload(servTecnico);
								$('#mantenedorStecnico').dialog('close');
								jAlert('Servicio técnico grabado','Información');																
							});
						}
					});
				}
			},
			Cerrar: function(){
				$('#mantenedorStecnico').dialog('close');
			}
		}
	});
	
});

var changeStateServiciotecnico = function(id,state) {
	SSTFacade.updateServicioTecByubicacion({id:id,vigente:state},{asyn:false,callback:function(){
		$.alerts.okButton = '&nbsp;Ok&nbsp;';
		jAlert('Se ha actualizado el servicio técnico correctamente','Información',function(){
			$('#resultados').flexReload();
		});
	}});
};

var configureProfile = function(id) {
	parent.location = context +'/index.do?e=' + moduloConfigurar.codigo + '&m=' + moduloInicial.codigo + '&idSTecnico=' + id;  
};












