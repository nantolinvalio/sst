<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ include file="../library.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/ADMINISTRACION/onmenusemaforo.js?<%= version %>"></script>
</head>
<body>
	<div class="main_body">
		<%@ include file="../header.jsp" %>
		<%@ include file="../menu.jsp" %>
		<div class="wrapper">
			<div class="container">
				<h1>Semáforo de ordenes de trabajo</h1>
				<div class="marco">
					<h1>Semáforos de ordenes de trabajo</h1>
					<div class="marco_interior">
					 <form  id="guardaIndicadoresForm" name="guardaIndicadoresForm">
						<table class="table_plana">
							<tr parametro="10001000">
								<td style="width: 30%">OTs en sucursal inicio </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="text" maxlength="3" id="10001000" name="10001000" class="number required verde_claro"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="10002000" name="10002000" class="number required verde_oscuro" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="10003000" name="10003000" class="number required amarillo" size="2" ></input></td></td>
								<td>&le; <span class="indicador rojo">Rojo</span><input type="hidden" id="10004000" name="10004000" value="" class="rojo parametro"/></td>
							</tr>
							
							<tr parametro="10002000">
								<td style="width: 30%">OTs en sucursal termino </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="10006000" name="10006000" class="number required verde_claro"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="10007000" name="10007000" class="number required verde_oscuro" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="10008000" name="10008000" class="number required amarillo" size="2" ></input></td></td>
								<td>&le; <span class="indicador rojo">Rojo</span><input type="hidden" id="10009000" name="10009000" value="" class="rojo parametro"/></td>
								
							</tr>	
							
							<tr parametro="10003000">
								<td style="width: 30%">OTs asignadas a ej. marca en ST </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="20003000" name="20003000" class="number required verde_claro"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="20004000" name="20004000" class="number required verde_oscuro" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="20005000" name="20005000" class="number required amarillo" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span><input type="hidden" id="20006000" name="20006000" value="" class="rojo parametro"/></td>
								
							</tr>	
	
	 						<tr parametro="10004000"> 
	 							<td style="width: 30%">OTs en S. técnico local </td> 
	 							<td>0 &le;</td> 
	 							<td style="text-align: center" class="indicador verde_claro">Verde Claro</td> 
	 							<td>&lt; <input type="number" maxlength="3" id="100017000" name="100017000" class="number required verde_claro"  size="2"  ></input></td> 
								
	 							<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							 
	 							<td>&lt; <input type="number" maxlength="3" id="100018000" name="100018000" class="number required verde_oscuro" size="2" ></input></td></td> 
								
	 							<td>&le; <span class="indicador amarillo">Amarillo</span></td>							 
	 							<td>&lt; <input type="number" maxlength="3" id="100019000" name="100019000" class="number required amarillo" size="2" ></input></td></td> 
								
	 							<td>&le; <span class="indicador rojo">Rojo</span></td> 
	                                <input type="hidden" id="100020000" name="100020000" value="" class="rojo parametro"/>
	 						</tr> 
	
	                        <!-- Indicadores nuevos -->	
			   			</table>
			   			
			  <div class="marco_interior">
			   	  <table class="table_plana">
			   		<h1>Semáforo de ordenes falla de fabricacion </h1>
			   		
			   		<tr>
								<td style="width: 30%">OTs no despachadas desde tienda </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="50002000" name="50002000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="50003000" name="50003000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="50004000" name="50004000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="50005000" name="50005000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs no recibidas en bodega </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="50007000" name="50007000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="50008000" name="50008000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="50009000" name="50009000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="60001000" name="60001000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs con recepcion faltante </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="60008000" name="60008000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="60009000" name="60009000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="70001000" name="70001000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="70002000" name="70002000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs con recepcion incompleta </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="70004000" name="70004000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="70005000" name="70005000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="70006000" name="70006000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="70007000" name="70007000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">N° OTs en FF sin revision en SP </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="70009000" name="70009000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="80001000" name="80001000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="80002000" name="80002000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="80003000" name="80003000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs enviada y no recibida en SP </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="80005000" name="80005000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="80006000" name="80006000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="80007000" name="80007000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="80008000" name="80008000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs CP no despachadas desde SP </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="90001000" name="90001000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="90002000" name="90002000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="90003000" name="90003000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="90004000" name="90004000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs RP no despachadas desde SP </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="90006000" name="90006000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="90007000" name="90007000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="90008000" name="90008000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="90009000" name="90009000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs DA no despachadas desde SP </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="100002000" name="100002000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100003000" name="100003000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100004000" name="100004000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="100005000" name="100005000" value="" class="rojo"/>
							</tr>
							
							<tr>
								<td style="width: 30%">OTs AV no despachadas desde SP  </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="100007000" name="100007000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100008000" name="100008000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100009000" name="100009000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="100010000" name="100010000" value="" class="rojo"/>
							</tr>
							
							
							<tr>
								<td style="width: 30%">OTs enviadas a ST sin recepcion  </td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="100012000" name="100012000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100013000" name="100013000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="100014000" name="100014000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="100015000" name="100015000" value="" class="rojo"/>
							</tr>
			   		    </table>
			   		    </div>
			   
			   <div class="marco_interior">
			   	  <table class="table_plana">
			   		<h1>Semáforo de guias</h1>
			   			<tr>
								<td style="width: 30%">Guias emitidas a transportistas</td>
								<td>0 &le;</td>
								<td style="text-align: center" class="indicador verde_claro">Verde Claro</td>
								<td>&lt; <input type="number" maxlength="3" id="60003000" name="60003000" class="number required"  size="2"  ></input></td>
								
								<td>&le; <span class="indicador verde_oscuro">Verde Oscuro</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="60004000" name="60004000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador amarillo">Amarillo</span></td>							
								<td>&lt; <input type="number" maxlength="3" id="60005000" name="60005000" class="number required" size="2" ></input></td></td>
								
								<td>&le; <span class="indicador rojo">Rojo</span></td>
								<input type="hidden" id="60006000" name="60006000" value="" class="rojo"/>
						 </tr>
			   	   </table>
			   </div>	  
		 </form>
			   		        <tr>
			   					<td colspan="8">
			   						<fieldset class="fieldset_botonera_center">
										<input type="button" name="grabar" id="grabar" value="Actualizar indicadores">
			   						</fieldset>
			   					</td>
			   				</tr>
				   </div>
			  </div>
		  </div>
	  </div>
  </div>S
</body>
</html>