var idFactura;
$(document).ready(function(){
	idFactura = $('#idFactura').val();
	loadSubmodulosByPage('onpopupfacturadetalle','tabs',true,idFactura,true);
});