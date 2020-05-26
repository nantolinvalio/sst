$(document).ready(function(){
	
	
	dwr.engine.setPreHook(function() {});

	dwr.engine.setPostHook(function() {});
	
	$('#documentos').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#marcas').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#transportistas').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#documentosProductos').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#familias').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#productos').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#proveedores').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.actualizarVista(btnId, {async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#semaforo').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.calcularSemaforo({async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#indicadores').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.calcularIndicadores({async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#procesoBodega').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.procesoBodega({async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	$('#procesoProveedores').click(function(){
		var btnId = this.id;
		var btnValue = this.value;
		preHook(this.id);
		SSTFacade.procesoProveedores({async:true,callback:function(resp){
			postHook(btnId, btnValue, resp);
		}});
	});
	
	var preHook = function(btnName) {
		$('#' + btnName).val('Procesando...');
		$('#' + btnName).attr('disabled',true);
	};
	
	var postHook = function(btnName, btnVal, resp) {
		if (resp.salida) {
			jAlert(resp.salida);				
		} else {
			jAlert('Proceso Finalizado');
		}
		$('#' + btnName).val(btnVal);
		$('#' + btnName).attr('disabled',false);
	};
});