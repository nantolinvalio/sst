$(document).ready(function() {
	var indicadores=[];
	var idIndicador=[];
	var valText;
	var valores = $('#guardaIndicadoresForm').find('input');
	
	$(valores).each(function(index, valor){	
		SSTFacade.getIndicadorById(valor.id,{async:true,callback:function(rango){
			indicadores.push(rango);
			 $('#guardaIndicadoresForm').find('#'+valor.id ).val(rango.rangoMaximo);		 
		}});
	});   
	
	$('#grabar').click(function(){
		$('#guardaIndicadoresForm').validate();
		
		$('#10001000').rules('add',{ max:parseInt($('#10002000').val())-1});
		$('#10002000').rules('add',{ max:parseInt($('#10003000').val())-1, min:parseInt($('#10001000').val())+1 });
		$('#10003000').rules('add',{ min:parseInt($('#10002000').val())+1});
			
		$('#10006000').rules('add',{ max:parseInt($('#10007000').val())-1});
		$('#10007000').rules('add',{ max:parseInt($('#10008000').val())-1, min:parseInt($('#10006000').val())+1 });
		$('#10008000').rules('add',{ min:parseInt($('#10006000').val())+1});
		
		$('#20003000').rules('add',{ max:parseInt($('#20004000').val())-1});
		$('#20004000').rules('add',{ max:parseInt($('#20005000').val())-1, min:parseInt($('#20003000').val())+1 });
		$('#20005000').rules('add',{ min:parseInt($('#20004000').val())+1});
		
		$('#100017000').rules('add',{ max:parseInt($('#100018000').val())-1});
		$('#100018000').rules('add',{ max:parseInt($('#100019000').val())-1, min:parseInt($('#100017000').val())+1 });
		$('#100019000').rules('add',{ min:parseInt($('#100018000').val())+1});
		
		$('#50002000').rules('add',{ max:parseInt($('#50003000').val())-1});
		$('#50003000').rules('add',{ max:parseInt($('#50004000').val())-1, min:parseInt($('#50002000').val())+1 });
		$('#50004000').rules('add',{ min:parseInt($('#50003000').val())+1});
		
		$('#50007000').rules('add',{ max:parseInt($('#50008000').val())-1});
		$('#50008000').rules('add',{ max:parseInt($('#50009000').val())-1, min:parseInt($('#50007000').val())+1 });
		$('#50009000').rules('add',{ min:parseInt($('#50008000').val())+1});
		
		$('#60008000').rules('add',{ max:parseInt($('#60009000').val())-1});
		$('#60009000').rules('add',{ max:parseInt($('#70001000').val())-1, min:parseInt($('#60008000').val())+1 });
		$('#70001000').rules('add',{ min:parseInt($('#60009000').val())+1});
		
		$('#70004000').rules('add',{ max:parseInt($('#70005000').val())-1});
		$('#70005000').rules('add',{ max:parseInt($('#70006000').val())-1, min:parseInt($('#70004000').val())+1 });
		$('#70006000').rules('add',{ min:parseInt($('#70005000').val())+1});
		
		$('#70009000').rules('add',{ max:parseInt($('#80001000').val())-1});
		$('#80001000').rules('add',{ max:parseInt($('#80002000').val())-1, min:parseInt($('#70009000').val())+1 });
		$('#80002000').rules('add',{ min:parseInt($('#80001000').val())+1});
		
		$('#80005000').rules('add',{ max:parseInt($('#80006000').val())-1});
		$('#80006000').rules('add',{ max:parseInt($('#80007000').val())-1, min:parseInt($('#80005000').val())+1 });
		$('#80007000').rules('add',{ min:parseInt($('#80006000').val())+1});
		
		$('#90001000').rules('add',{ max:parseInt($('#90002000').val())-1});
		$('#90002000').rules('add',{ max:parseInt($('#90003000').val())-1, min:parseInt($('#90001000').val())+1 });
		$('#90003000').rules('add',{ min:parseInt($('#90002000').val())+1});
		
		$('#90006000').rules('add',{ max:parseInt($('#90007000').val())-1});
		$('#90007000').rules('add',{ max:parseInt($('#90008000').val())-1, min:parseInt($('#90006000').val())+1 });
		$('#90008000').rules('add',{ min:parseInt($('#90007000').val())+1});
		
		$('#100002000').rules('add',{ max:parseInt($('#100003000').val())-1});
		$('#100003000').rules('add',{ max:parseInt($('#100004000').val())-1, min:parseInt($('#100002000').val())+1 });
		$('#100004000').rules('add',{ min:parseInt($('#100003000').val())+1});
		
		$('#100007000').rules('add',{ max:parseInt($('#100008000').val())-1});
		$('#100008000').rules('add',{ max:parseInt($('#100009000').val())-1, min:parseInt($('#100007000').val())+1 });
		$('#100009000').rules('add',{ min:parseInt($('#100008000').val())+1});
		
		$('#100012000').rules('add',{ max:parseInt($('#100013000').val())-1});
		$('#100013000').rules('add',{ max:parseInt($('#100014000').val())-1, min:parseInt($('#100012000').val())+1 });
		$('#100014000').rules('add',{ min:parseInt($('#100013000').val())+1});
		

		
		if($('#guardaIndicadoresForm').valid()) {
			var indicadores = [];
			var parametros = [];
			var valorAnterior;
			
			var rangoMinimo, rangoMaximo;	
			$(valores).each(function(index, valor){
				if($(valor).hasClass('rojo')){
					indicadores.push({id: valor.id, rangoMinimo:valorAnterior});
					valorAnterior = null;
				}else{	
					indicadores.push({id: valor.id, rangoMaximo:valor.value, rangoMinimo:valorAnterior });
					valorAnterior = valor.value;
				}
				
			});
			
			var trs = $('tr');
			
			$(trs).each(function(index, tr){
				parametro = {};
				if ($(tr).attr('parametro')){
					parametro.integer1 = $(tr).find('input.verde_claro').val();
					parametro.integer2 = $(tr).find('input.verde_oscuro').val();
					parametro.integer3 = $(tr).find('input.amarillo').val();
					parametro.integer4 = 100;
					parametro.id = $(tr).attr('parametro');
					parametros.push(parametro);
					
				}
			});
			
			SSTFacade.updateIndicadores(indicadores,{async:false,callback:function(){
				jAlert("Indicadores actualizados correctamente", "informacion");
			}});
			
			SSTFacade.updateParametros(parametros,{async:false,callback:function(){}});
		   };
	});
	

	
});