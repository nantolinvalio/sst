var initonagregarubicinternasucursal = function(){
	
	var moduloCrear;
	SSTFacade.getModuloByNombreUsuario('onsaveubicinternabodega',{async:false, callback:function(modulo) {
		moduloCrear = modulo ? modulo : undefined;
	}});
	
	
	var exportDocument = function(type){
		var p = $('#resultados').getParameters()[0];
		var f = $('#resultados').getFilterColumna()[0];
		p.orderBy = $('#resultados').getSortName()[0];
		p.sortOrder = $('#resultados').getSortOrder()[0];
		var url = "/sst/ViewReportServlet?type=" + type + 
			"&report=UbicacionesInternasCDReport" +
			"&filter=" + JSON.stringify(p) + // + getParametros(form);
			"&filterColumn=" + JSON.stringify(f);
		$.openWindowsMenubar(url, "UbicacionesInternasCDReport", 600, 800);
	};
	
	$('#resultados').flexigrid({
		dwrFunction: SSTFacade.listUbicacionInternaCDByFilter
		,usepager: true
		,showToggleBtn:true
		,multisel:false
		,singleSelect:true
		,onDragCol:false
		,onToggleCol:false
		,saveCheckValues:true
		,title:'Resultados'
		,tipo:'UBIS'
		,seccion:1000090100
		,buttons : [
				    {name:'Nueva Ubicacion', bclass:'btnPlus', onpress:function(){grabarUbicacion();}},
				    {separator: true},
				    {name: 'Exportar PDF', bclass: 'btnPDF', onpress : function(){exportDocument('pdf');} },
			   		{separator: true},
			   		{name: 'Exportar Excel', bclass: 'btnExcel', onpress : function(){exportDocument('xls');}},
			   		{separator: true}
				]
		,overrideModel:[
		    {findName:'sucursales',propiedad:function(o){
		     var sucursales='';
		     $.each(o.sucursales,function(index,sucursal){
		    	if(index==((o.sucursales.length)-1)){
		    		sucursales = sucursales+sucursal.glosa+'.';
		    	} else{
		    		sucursales = sucursales+sucursal.glosa+', ';
		    	}
		     });
		     return sucursales;
		    }},
		    {findName:'familias',propiedad:function(o){
		     var familias='';
		     $.each(o.familias,function(index,familia){
		    	if(index==((o.familias.length)-1)){
		    		familias = familias+$.trim(familia.nombre)+'.';
		    	} else{
		    		familias = familias+$.trim(familia.nombre)+', ';
		    	}
		     });
		     return familias;
		    }},
		    {findName:'lineas',propiedad:function(o){
		     var lineas='';
		     $.each(o.lineas,function(index,linea){
		    	if(index==((o.lineas.length)-1)){
		    		lineas = lineas+$.trim(linea.glosa)+'.';
		    	} else{
		    		lineas = lineas+$.trim(linea.glosa)+', ';
		    	}
		     });
		     return lineas;
		    }},
		    {findName:'vigente',propiedad:function(o){
					
		    	if(o.vigente){
					return 'ACTIVO';
				}
					return 'INACTIVO';
			}},
		],
		dblclickFunction:function(el,idx,comp){
			parent.location = context +'/index.do?e=' + moduloCrear.codigo + '&m=' + moduloInicial.codigo +  '&codigo=' + el.codigo;
		}
	});
	
	SSTFacade.listFamilia({async:true,callback:function(familias){
		$('#idFamilia').addItems(familias,'id','nombre',true);
	}});
	
	SSTFacade.listAllLineas({async:true,callback:function(lineas){
		$('#idLinea').addItems(lineas,'codigo','glosa',true);
	}});
	
	SSTFacade.listSucursales({async:true,callback:function(sucursales){
		$('#idSucursal').addItems(sucursales,'id','nombre',true);
	}});
	
	SSTFacade.listUbicacionInternaCD({async:true,callback:function(ubicacionesInternas){
		$('#nombre').addItems(ubicacionesInternas,'nombre','nombre',true);
	}});
	
	$('#buscar').click(function(){
		var filter = $('#formSucursales').serializeObject();
		$('#resultados').loadData([filter]);
	});
	
	var grabarUbicacion = function(){
		parent.location = context + '/index.do?e='+moduloCrear.codigo+'&m='+moduloInicial.codigo;
	};
};

var imprimirCodigo = function(codigo){
	alert(codigo);
};