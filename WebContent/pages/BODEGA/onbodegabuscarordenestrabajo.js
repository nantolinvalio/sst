var moduloHistorial; 
$(document).ready(function(){
	$('.fecha').datepicker();
	$('#sucursal').selectInput();
	var buscadorCargado = false;
	
	var cargaBuscador = function() {
		if (!buscadorCargado) {

			SSTFacade.listEstadosOT({async:true, callback:function(estadosOT){
				$('#estadoOT').addItems(estadosOT,'id','glosa',true);
			}});
			
			SSTFacade.listTiposUbicacion({async:true, callback:function(ubicacionesOT){
				$('#tipoUbicacion').addItems(ubicacionesOT,'codigo','glosa',true);
			}});
				
			SSTFacade.getModuloByNombreUsuario('onbodegaordentrabajohistorial',{async:false, callback:function(modulo) {
				moduloHistorial = modulo ? modulo : undefined;
			}});
			
			SSTFacade.ListServiciosTecnicosBuscadorOT({idOrigen:10015},{async:true,callback:function(sTecnicos){
				$("#idSTecnico").addItems(sTecnicos, "id", ["glosa","direccion"], true);
			}});
			
			SSTFacade.listSucursales({async:true,callback:function(sucursales){
				$('#sucursal').addItems(sucursales,"id","nombre",true);
			}});
			
			SSTFacade.listFamilia({sync:true,callback:function(familias){
				$('#idFamilia').addItems(familias,"id","nombre",true);
			}});
			
			SSTFacade.listMarca({async:true,callback:function(marcas){
				$('#idMarca').addItems(marcas,"codigo","nombre",true);
			}});
			
			SSTFacade.listTiposOT({async:true,callback:function(data){
				$("#tipoOT").addItems(data, "codigo", "glosa", true);
			}});
			
			SSTFacade.listTiposOTOT({async:true,callback:function(data){
				$("#ot").addItems(data, "codigo", "glosa", true);
			}});
			
			SSTFacade.listTiposSemaforo({async:true,callback:function(data){
				$("#semaforo").addItems(data, "codigo", "glosa", true);
			}});

			SSTFacade.listEjecutivasMarca({async:true,callback:function(data){
				$("#idEjecutivaTraspasar").addItems(data, "id",["nombre", "apellidoPaterno"],true);
				data.push({id:-1,nombre:'SIN EJECUTIVA',apellidoPaterno:""});
				data.reverse();
				$("#idEjecutiva").addItems(data, "id", ["nombre", "apellidoPaterno"], true);
			}});
			
			SSTFacade.listTodasUbicacionesInternasCD({async:true,callback:function(ubicacionesInternas){
				$('#idUbicacionInterna').addItems(ubicacionesInternas,"id","nombre",true);
			}});
			buscadorCargado = true;
		}
	};
	
	$('#limpiar_avanzado').click(function(){
		$('#filtro_buscador_avanzado').reset();
		$('#resultados').clean();
	});
	
	$('#limpiar_basico').click(function(){
		$('#filtro_buscador_basico').reset();
		$('#resultados').clean();
	});
	
	$('#buscar_avanzado').click(function(){
		buscarAvanzado();
	});
	
	$('#buscar_basico').click(function(){
		buscarBasico();
	});
	
	$('#change_buscador_avanzado').click(function(){
		$('#buscador_basico').hide();
		$('#buscador_avanzado').show();
		cargaBuscador();
	});
	
	$('#change_buscador_basico').click(function(){
		$('#buscador_basico').show();
		$('#buscador_avanzado').hide();
	});
	
	$('#buscador_basico').keypress(function(e){
		if(e.which == 13) {
			buscarBasico();
		}
	});
	
	$('#buscador_avanzado').keypress(function(e){
		if(e.which == 13) {
			buscarAvanzado();
		}
	});
	
	var buscarBasico = function() {
		var validar = [];
		validar.push($('#filtro_buscador_basico').find('#idOT'));
		validar.push($('#filtro_buscador_basico').find('#numeroSerie'));
		validar.push($('#filtro_buscador_basico').find('#idEtapa'));
		if($('#filtro_buscador_basico').valid() && validEmptyInputs(validar,'debe ingresar alguno de los parametros de busqueda')) {
			var filter = $('#filtro_buscador_basico').serializeObject();
			$('#resultados').loadData([filter]);
		}
	};
	
	var buscarAvanzado = function() {
		if($('#filtro_buscador_avanzado').valid()){
			var filter = $('#filtro_buscador_avanzado').serializeObject();
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
			"&report=OrdenTrabajoReport" +
			"&filter=" + JSON.stringify(p) + 
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "OrdenTrabajoReport", 600, 800);
	};
	
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listOTByFilter,
		seccion: 10004000,
		multisel:false,
		singleSelect:true, 
		tipo: 'OT',
		dblclickFunction:function(el,idx,comp){      
			redirectOtDetalle(el.id);
		},
		overrideModel: [
			{findName:'semaforoServicioTecnico',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoServicioTecnico) + '"></div>');}},
			{findName:'semaforoSucursalInicio',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalInicio) + '"></div>');}},
			{findName:'semaforoSucursalfin',propiedad:function(o){return $('<div style="background-color: ' + colorSemaforo(o.semaforoSucursalfin) + '"></div>');}},
			{findName:'sucursal.glosa',propiedad:function(o){if (o.sucursal){ return o.sucursal.id + ' ' + o.sucursal.glosa;}}},							    
		],
		buttons : [
			{name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			{separator: true},
			{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');} },
			{separator: true}],
	});	
	
});
var verHistorial = function(idOT,idProducto){
	parent.location = context +'/index.do?e=' + moduloHistorial.codigo + '&m=' + moduloInicial.codigo + '&idOT=' + idOT+'&idProducto='+idProducto;
};