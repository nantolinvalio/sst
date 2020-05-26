package cl.abcdin.sst.utils;

public abstract class Constants {
	
	public static final String RUT_ABCDIN = "82982300-4";
	
	public static final String SESSION_USER = "session_user";
	public static final String SESSION_UBICACION = "session_ubicacion";

	public static final String PETICION_DE_FACTURA = "SC";
	public static final String PETICION_DE_NOTA_CREDITO = "DM";
	
	public static final String UBICACION_OFICINA = "OF";
	public static final String UBICACION_SUCURSAL = "SC";
	public static final String UBICACION_SERVICIO_TECNICO = "ST";
	public static final String UBICACION_SERVICIO_TECNICO_AUTORIZADO = "STA";
	public static final String UBICACION_SERVICIO_TECNICO_LOCAL = "STL";
	public static final String UBICACION_BODEGA_REGIONAL = "BR";
	public static final String UBICACION_CENTRO_DISTRIBUCION = "CD";
	public static final String UBICACION_CLIENTE = "CL";
	public static final String UBICACION_TRANSPORTE = "TR";
	public static final String UBICACION_CASA_REMATE = "CR";
	public static final String UBICACION_LIQUIDADORA = "LQ";
	
	public static final String TIPO_OT_GARANTIA_MASTER = "GM";
	public static final String TIPO_OT_GARANTIA_PROVEEDOR = "GP";
	public static final String TIPO_OT_GARANTIA_BIG_TICKET = "BT";
	public static final String TIPO_OT_JEFE_TIENDA = "JT";
	public static final String TIPO_OT_CAMBIO_AUTOMATICO = "CA";
	public static final String TIPO_OT_CAMBIO_MENOR_24_HRS = "24";
	/*
	* Proyecto : 10021 - Mejoras Servicio Tecnico
	* Objetivo : Diferenciar las OT que necesitan certificacion de falla
	* Fecha    : 07/09/2015
	* Autor    : Richard Flores - ScriptIA
	* 
	* INICIO
	*/
	public static final String TIPO_OT_GARANTIA_PROVEEDOR_CF = "GPC";
	/*
	* FIN
	*/

	public static final String PARAMETROS_TIPO_UBICACION = "TIUB";
	public static final String PARAMETROS_TIPO_UBICACION_ = "UBIC";
	public static final String PARAMETROS_TIPO_DOCUMENTO = "TIDO";
	public static final String PARAMETROS_TIPO_CAMBIO_AUTOMATICO = "TICA";
	public static final String PARAMETROS_TIPO_CAMBIO = "CAMB";
	public static final String PARAMETROS_TIPO_SEMAFORO = "SEMA";
	public static final String PARAMETROS_TIPO_OT = "TIOT";
	public static final String PARAMETROS_TIPO_OTOT = "OTOT";
	public static final String PARAMETROS_TIPO_OT_AUTORIZACION_CAMBIO = "BCAE";
//	public static final String PARAMETROS_TIPO_OT_AUTORIZACION_CAMBIO = "CB";
	public static final String PARAMETROS_CODIGO_BARRA_OBLIGATORIO = "COD_OBL";
	
	public static final String INDICADOR_OT_NO_DESPACHO_TIENDA = "OTNDT";
	public static final String INDICADOR_OT_NO_RECIBIDA_BODEGA = "OTNRB";
	public static final String INDICADOR_GUIA_EMITIDA_TRANSPORTISTA = "GETR";
	public static final String INDICADOR_OT_RECEPCION_FALTANTE = "OTRF";
	public static final String INDICADOR_OT_RECEPCION_INCOMPLETA = "OTRI";
	public static final String INDICADOR_OT_FF_NO_REVISION_SP = "OTFFR";
	public static final String INDICADOR_OT_ENVIADA_NO_RECIBIDA_SP = "OTESP";
	public static final String INDICADOR_OT_CLASIFICACION_CP_NO_DESPACHADA_SP = "OTCPD";
	public static final String INDICADOR_OT_CLASIFICACION_RP_NO_DESPACHADA_SP = "OTRPD";
	public static final String INDICADOR_OT_CLASIFICACION_DA_NO_DESPACHADA_SP = "OTDAD";
	public static final String INDICADOR_OT_CLASIFICACION_AV_NO_DESPACHADA_SP = "OTAVD";
	public static final String INDICADOR_OT_ENVIADA_ST_SIN_RECEPCION = "OTEST";
	
	
	public static final Long SEMAFORO_ROJO = 4L;	
	public static final Long SEMAFORO_VERDE_OSCURO = 3L;	
	public static final Long SEMAFORO_VERDE_CLARO = 2L;	
	public static final Long SEMAFORO_AMARILLO = 1L;
	public static final Long SEMAFORO_TODOS_ = 0L;
	
	public static final Integer FACTURA_ACEPTADA = 40004000;
	public static final Integer FACTURA_RECHAZADA = 40005000;
	public static final Integer FACTURA_DESHECHA = 40000000;
	public static final Integer FACTURA_ASIGNADA = 40003000;
	public static final Integer FACTURA_PROCESO_OW = 40001500;
	public static final Integer FACTURA_GENERADA_ESPERA_EMISION = 40001000;
	public static final Integer FACTURA_LISTA_PARA_EMISION_CON_NUMERO_SC_ASIGNADO = 40002000;
	
	public static final String COLUMNA_ALIGN_CENTER = "center";
	public static final String COLUMNA_ALIGN_LEFT = "left";
	public static final String COLUMNA_ALIGN_RIGHT = "right";
	public static final String FORMATO_FECHA_XPERTS = "dd-MM-yyyy";

	public static final String ENTIDAD_TIPO_PRODUCTO = "P";
	public static final String ENTIDAD_TIPO_FAMILIA = "F";
	public static final String ENTIDAD_TIPO_LINEA = "L";
	public static final String ENTIDAD_TIPO_TIENDA = "T";
	public static final String ENTIDAD_TIPO_ZONA = "Z";

	public static final Integer GUIA_ESTADO_POR_EMITIR = 50001000;
	public static final Integer GUIA_ESTADO_EMITIDA_NO_CONFIRMADA = 50001500;
	public static final Integer GUIA_ESTADO_EMITIDA_NO_RECIBIDA = 50002000;
	public static final Integer GUIA_ESTADO_EMITIDA_RECIBIDA = 50003000;
	public static final Integer GUIA_ESTADO_DESACTIVADA = 50004000;
	public static final Integer GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION = 50005000;
	public static final Integer GUIA_ESTADO_SIN_GUIA = 50006000;
	public static final Integer GUIA_RECIBIDA_COMPLETA = 50007000;
	public static final Integer GUIA_RECIBIDA_FALTANTE = 50008000;
	public static final Integer GUIA_RECIBIDA_INCOMPLETA = 50009000;
	
	public static final String GUIA_TIPO_ENVIAR_A_CLIENTE = "GENV";
	public static final String GUIA_TIPO_RETIRO_EN_CLIENTE = "GRET";
	
	
	public static final Integer DESPACHO_EN_PROCESO = 90001000;
	public static final Integer DESPACHO_PARA_EMISION_DE_GUIA = 90001500;
	public static final Integer DESPACHO_TERMINADO = 90002000;
	public static final Integer ESCANER_EN_PROCESO = 90003000;
	public static final Integer PRODUCTO_NO_ENCONTRADO = 90004000;
	public static final Integer PRODUCTO_ESCANEADO = 90005000;
	public static final Integer ESTADO_DESPACHO_SP_EN_PROCESO = 90006000;
	public static final Integer ESTADO_DESPACHO_SP_EN_TRASLADO = 90006001;
	public static final Integer ESTADO_DESPACHO_SP_TERMINADO = 90006002;
	public static final Integer ESTADO_DESPACHO_SP_INCOMPLETO = 90006003;
	
	
	public static final Integer OT_EN_ST_CON_CONTRATO = 10006000;
	public static final Integer OT_EN_ST_SIN_CONTRATO = 10007000;
	public static final Integer OT_EN_CD_RECEPCION_ACEPTADA= 10024000;
	public static final Integer OT_EN_BR_RECEPCION_ACEPTADA= 10016000;
	public static final Integer OT_EN_CD_RECEPCION_CON_OBSERVACION= 10025000;
	public static final Integer OT_EN_BR_RECEPCION_CON_OBSERVACION= 10017000;
	public static final Integer OT_CERRADA_POR_CLIENTE_EN_SUCURSAL_POR_PRODUCTO_REPARADO = 10014000;
	public static final Integer OT_CERRADA_POR_CLIENTE_POR_PRODUCTO_CAMBIADO = 10015000;
	public static final Integer OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_ACEPTADO = 10022000;
	public static final Integer OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_RECHAZADO = 10023000;
	public static final Integer OT_APTA_PARA_TRASLADO_A_FF = 10301000;
	public static final Integer OT_APTA_PARA_TRASLADO_A_CASA_REMATE = 10303000;  
	public static final Integer OT_APTA_PARA_TRASLADO_A_LIQUIDADORA_SEGUNDA = 10304000;
	public static final Integer OT_APTA_PARA_TRASLADO_A_PROVEEDOR = 10305000;
	public static final Integer OT_APTA_PARA_TRASLADO_A_SERVICIO_TECNICO = 10306000;
	
	public static final Integer OT_FALTANTE_EN_RECEPCION = 10038000;
	public static final Integer OT_YA_RECEPCIONADA_EN_BODEGA = 10039000;
	public static final Long UBICACION_ID_CLIENTE = 10L;
	public static final Long UBICACION_ID_TRANSPORTE = 11L;
	public static final Long UBICACION_NUEVA_DE_LYON_072_PISO_6 = 89772300L;
	public static final Long UBICACION_CENTRO_DISTRIBUCION_LA_VARA = 10490L;
	public static final Long UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF = 10012L;
	public static final Long UBICACION_CENTRO_DISTRIBUCION_LA_VARA_10013 = 10013L;
	public static final Long UBICACION_DIN_GRANDES_TIENDAS = 82982300L;
	
	public static final Integer OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE= 10002000;
	public static final Integer OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA = 10003000;
	public static final Integer OT_ESTADO_BIG_TICKET_A_LA_ESPERA_DE_ELEGIR_SERVICIO_TECNICO = 10008000;
	public static final Integer OT_ESTADO_BIG_TICKET_CON_S_TECNICO_A_LA_ESPERA_DE_VISITA_A_DOMICILIO = 10019000;
	public static final Integer OT_ESTADO_CON_RECEPCION_ACEPTADA_EN_SUCURSAL = 10011000;
	public static final Integer OT_ESTADO_CON_RECEPCION_ACEPTADA_CON_OBSERVACION_EN_SUCURSAL = 10013000;
	public static final Integer OT_ESTADO_RECHAZADA_POR_CLIENTE = 10029000;
	public static final Integer OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE = 10001000;
	public static final Integer OT_ESTADO_TRANSPORTE_POR_RECIBIR = 10004000;
	public static final Integer OT_ESTADO_REPARACION_ESPERA_CONTRATO = 10005000;
	public static final Integer OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_CENTRO_DE_DISTRIBUCION = 10026000;
	public static final Integer OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_BODEGA = 10018000;
	public static final Integer OT_ESTADO_EN_SUCURSAL_SIN_ENVIO = 10030000;
	public static final Integer OT_ESTADO_EN_CLIENTE_ESPERA_ENTREGA_EN_SUCURSAL = 10009000;
	public static final Integer OT_ESTADO_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO = 10010000;
	public static final Integer OT_ESTADO_EN_CASA_DE_REMATE = 10027000;
	public static final Integer OT_ESTADO_EN_CLIENTE_A_LA_ESPERA_DE_RECUPERACION = 10028000;
	public static final Integer OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_SUCURSAL = 10012000;
	public static final Integer OT_RECIBIDA_POR_REVISAR_EN_FALLA_FABRICACION = 10031000;
	public static final Integer OT_ACEPTADA_EN_FALLA_FABRICACION = 10032000;
	public static final Integer OT_ACEPTADA_CON_OBSERVACION_COMPLETA_EN_FALLA_FABRICACION = 10033000;
	public static final Integer OT_ACEPTADA_CON_OBSERVACION_INCOMPLETA_EN_FALLA_FABRICACION = 10034000;
	public static final Integer OT_RECHAZADA_EN_FALLA_FABRICACION = 10035000;
	public static final Integer OT_CERRADA_POR_PETICION_FACTURA = 100008000;
	public static final Integer OT_CERRADA_POR_PETICION_NOTA_CREDITO = 100008100;
	public static final Integer OT_CERRADA_POR_USUARIO = 100008200;
	public static final Integer OT_EN_CONTROL_CALIDAD = 10026050;
	public static final Integer OT_APTA_TRASLADO_SC_O_BR = 10026100;
	public static final Integer OT_CERRADA_POR_ENVIO_10000 = 100010500;
	public static final Integer OT_ENVIADA_A_AV = 100010700;
	public static final Integer OT_ENVIADA_A_DA = 100010600;
	
	public static final Integer BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL_20001000 = 20001000;
	public static final Integer BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL = 20002000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SERVICIO_TECNICO_LOCAL = 20003000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LOCAL_DESPUES_ENTREGA_A_CAMION_HACIA_SUCURSAL = 20004000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SUCURSAL = 20005000;
	public static final Integer BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_A_LA_BODEGA_REGIONAL = 20008000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_ENTREGA_A_BODEGA_REGIONAL = 20009000;
	public static final Integer BITACORA_EN_CAMION_ENTREGA_A_SERVICIO_TECNICO = 20011000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_ENTREGA_A_CD_2 = 20021000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_DESPUES_ENTREGA_A_CAMION_A_BODEGA_REGIONAL = 20012000;
	public static final Integer BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD = 20016000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_ENTREGA_A_CD = 20017000;
	public static final Integer BITACORA_EN_CD_DESPUES_ENTREGA_CAMION_A_SUCURSAL = 20022000;
	public static final Integer BITACORA_EN_CAMION_ENTREGA_A_SERVICIO_TECNICO_2 = 20019000;
	public static final Integer BITACORA_EN_CD_CONTROL_CALIDAD = 20021050;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_DEPUES_ENTREGA_A_CAMION_AL_CD = 20020000;
	public static final Integer BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL = 20023000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO = 20024000;
	public static final Integer BITACORA_EN_BODEGA_REGIONAL_DESPUES_ENTREGA_AL_CAMION_AL_CD = 20031000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_SE_ENTREGA_EN_CENTRO_DE_DISTRIBUCION = 20032000;
	public static final Integer BITACORA_EN_CAMION_A_BODEGA_REGIONAL = 20038000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_DEPUES_AL_CD = 20039000;
	public static final Integer BITACORA_OT_EN_SUCURSAL_LUEGO_EJECUTIVA_ASIGNA_SERVICIO_TECNICO = 20040000;
	public static final Integer BITACORA_EN_CAMION_DESPUES_DEVUELVE_A_SUCURSAL = 20044000;
	public static final Integer BITACORA_EN_CLIENTE_A_LA_ESPERA_DE_RECUPERARLO_UNA_SUCURSAL = 70027000;
	public static final Integer BITACORA_EN_SUCURSAL_DEVUELTA_A_CENTRO_DISTRIBUCION = 20045000;
	public static final Integer BITACORA_EN_SUCURSAL_TERMINO_DESPUES_ENTREGA_A_CLIENTE = 20006000;
	public static final Integer BITACORA_EN_CENTRODISTRIBUCION_SE_DEVUELVE_A_SUCURSAL = 20043000;
	public static final Integer BITACORA_EN_CENTRODISTRIBUCION_DESPUES_A_CAMION_AL_SERVICIO_TECNICO = 20018000;

	public static final Integer BITACORA_EN_SUCURSAL_LUEGO_A_CAMION_A_CD_PARA_REMATE =70003000;
	public static final Integer BITACORA_EN_BODEGA_REGIONAL_LUEGO_A_CAMION_A_CD = 70019000;
	public static final Integer BITACORA_EN_CENTRO_DE_DISTRIBUCION_LUEGO_SE_ENVIA_A_REMATE = 70005000; 
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SUCURSAL =70001000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_BR = 70017000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_CD = 70020000;
	public static final Integer BITACORA_EN_CLIENTE_LUEGO_ENTREGA_PRODUCTO_A_SC = 70022000;
	public static final Integer BITACORA_EN_SUCURSAL_LUEGO_A_CAMION_A_CD_PARA_PROVEEDOR = 70010000;
	public static final Integer BITACORA_EN_BODEGA_REGIONALLUEGO_A_CAMION_A_CD_PARA_PROVEEDOR = 70023000;
	public static final Integer BITACORA_EN_CENTRO_DE_DISTRIBUCION_LUEGO_SE_ENVIA_A_PROVEEDOR = 70012000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SC_PARA_PROVEEDOR = 70008000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_BR_PARA_PROVEEDOR = 70024000;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_CD_PARA_PROVEEDOR = 70026000;
	
	public static final Integer BITACORA_EN_CAMION_A_CASA_REMATE = 100006000;
//	public static final Integer BITACORA_EN_CASA_REMATE_LUEGO_A_CAMION_FF = 100006100;
//	public static final Integer BITACORA_EN_LIQUIDADORA_LUEGO_A_CAMION_A_FF = 100006300;
	public static final Integer BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_FF = 100006400;
	public static final Integer BITACORA_EN_PROVEEDOR_LUEGO_A_CAMION_A_FF = 100006500;
	public static final Integer BITACORA_EN_CAMION_LUEGO_A_FF = 100006200;
	public static final Integer BITACORA_EN_CAMION_A_PROVEEDOR = 100007100;
	public static final Integer BITACORA_EN_CAMION_A_SERVICIO_TECNICO = 100007200;
	public static final Integer BITACORA_EN_CAMION_A_LIQUIDADORA = 100007200;
	
	
	public static final Integer BITACORA_EN_SUCURSAL_ALMACENADA_EN_SUCURSAL = 70028000;
	public static final Integer BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF = 70029000;
	public static final Integer BITACORA_EN_CAMION_A_FF = 70030000;
	public static final Integer BITACORA_EN_FALLAS_DE_FABRICACION = 70031000;
	public static final Integer BITACORA_EN_FF_LUEGO_A_CASA_REMATE = 70031100;
	public static final Integer BITACORA_EN_FF_LUEGO_A_SERVICIO_TECNICO = 70031200;
	public static final Integer BITACORA_EN_FF_LUEGO_A_LIQUIDADORA = 70031300;
	public static final Integer BITACORA_EN_FF_LUEGO_A_PROVEEDOR = 70031400;
	public static final Integer BITACORA_EN_FF_LUEGO_A_CAMION_PARA_BODEGA_10000 = 100010400;
	
	public static final Integer BITACORA_EN_SUCURSAL_LUEGO_A_CD= 70032000;
	public static final Integer BITACORA_EN_CAMION_A_CD = 70033000;
	public static final Integer BITACORA_EN_CD = 70034000;
	public static final Integer BITACORA_EN_FF_LUEGO_CAMION_PARA_TRANSPORTISTA = 70035000;
	public static final Integer BITACORA_EN_CAMION_PARA_TRANSPORTISTA = 70036000;
	
	public static final Integer BITACORA_EN_CD_LUEGO_A_CAMION_A_FF = 70037000;
	
	public static final Integer BITACORA_EN_CENTRO_DE_DISTRIBUCION = 20037000;
	
	public static final Integer RECEPCION_OT_RECHAZADA = 60002000;
	public static final Integer RECEPCION_OT_ACEPTADA = 60001000;
	public static final Integer RECEPCION_OT_ACEPTADA_CON_OBSERVACION = 60003000;
	
	public static final Integer RECEPCION_MASIVA_EN_CURSO = 80001000;
	public static final Integer RECEPCION_MASIVA_EN_TERMINADA = 80002000;
		
	public static final String PARTE_BUENO = "B";
	public static final String PARTE_MALO = "M";

	public static final Integer INDICADOR_OT_ABIERTAS_SUCURSAL = 40001000;
	public static final Integer INDICADOR_OT_PENDIENTES_ACCESORIOS = 40002000;
	public static final Integer INDICADOR_OT_PENDIENTES_GUIAS = 40003000;
	public static final Integer INDICADOR_OT_EN_TRANSITO = 40004000;
	public static final Integer INDICADOR_OT_PENDIENTES_ENTREGA_CLIENTE = 40005000;
	public static final Integer INDICADOR_OT_STL_SIN_CONTRATO = 40006000;
	public static final Integer INDICADOR_OT_AUTORIZACION_CAMBIO = 40007000;
	public static final Integer INDICADOR_OT_NO_DESPACHADO_TIENDA = 50001000;
	
	public static final String CAMBIO_AUTOMATICO_VALOR = "VA";
	public static final String CAMBIO_AUTOMATICO_FALLA_REITERADA = "FR";
	public static final String CAMBIO_AUTOMATICO_FALLA_FABRICACION = "FF";
	public static final String CAMBIO_CERTIFICACION_FALLA = "CF";
	public static final String CAMBIO_JEFE_TIENDA = "JT";
	public static final String CAMBIO_EJECUTIVA_MARCA = "EM";
	
	public static final String CAMBIO_AUTOMATICO_PROVEEDOR_CARTA = "CC";
	public static final String CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO = "CP";
	public static final String CAMBIO_AUTOMATICO_VENTA_MENOR_24_HORAS = "24";
	
	
	public static final String TIPO_DOCUMENTO_NOTA_CREDITO = "NC";
	public static final String TIPO_DOCUMENTO_TICKET_CAMBIO = "TC";
	
	public static final Integer ROL_JEFE_DE_TIENDA = 180;
	public static final Integer ROL_EJECUTIVA_MARCA = 5;
	public static final Integer ROL_EJECUTIVA_FACTURACION = 6;
	public static final Integer ROL_LOGISTICO_SUCURSAL = 2;
	public static final Integer ROL_EJECUTIVA_GESTION = 3;
	public static final Integer ROL_EJECUTIVA_SUPERVISORA = 8;

	public static final String FACTURAR_TIPO_TRANSPORTISTA = "Transportista";
	public static final String FACTURAR_TIPO_PROVEEDOR = "Proveedor";
	public static final String FACTURAR_TIPO_TIENDA = "Tienda";
	public static final String FACTURAR_TIPO_EMPRESA = "Empresa";
	
	public static final String GUIA_TIPO_ACCESORIO = "GACC";
	public static final String GUIA_TIPO_UNITARIA = "GUNI";
	public static final String GUIA_TIPO_AGRUPADA = "GAGR";
	
	public static final String GUIA_ENVIADA_SIN_EMISION_GUIA = "Enviada sin emision de guia";
	
	public static final Integer LARGO_CODIGO_BARRA = 10;
	public static final Character LEFT_PAD_CODIGO_BARRA = '0';
	
	public static final Integer LARGO_FACTURAR_OW = 12;
	public static final Character LEFT_PAD_FACTURAR_OW = ' ';
	
	public static final Integer SALA_PROVEEDORES = 1001200001;
	public static final Integer BODEGA_FALLA_FABRICACION = 1001200002;
	public static final Integer BODEGA_SENSIBLES= 1001200003;
	public static final Integer BODEGA_SERVICIO_TECNICO= 1001500001;
	public static final Integer BODEGA_CONTROL_CALIDAD= 1001500002;
	public static final Integer BODEGA_MESON_ELABORACION= 1001500003;
	public static final Integer UBICACION_ID_TRASLADO = 12;
	
	public static final String CODIGO_SALA_PROVEEDORES = "RDESP";
	public static final String CODIGO_BODEGA_FALLA_FABRICACION = "RDEFF";
	public static final String CODIGO_BODEGA_SENSIBLES = "RDESS";
	public static final String CODIGO_TRASLADO = "RDETR";
	
	public static final Integer BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION = 100001000;
	public static final Integer BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION_A_ESPERA_DE_SER_ESCANEADA = 100001001;
	public static final Integer BITACORA_INTERNA_ESTADO_OT_TRASLADO_A_SALA_PROVEEDORES = 100002000;
	public static final Integer BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_SALA_PROVEEDORES = 100003000;
	public static final Integer BITACORA_INTERNA_OT_REVISADA_SALA_PROVEEDORES = 100004000;
	public static final Integer BITACORA_INTERNA_OT_EN_UBICACION_INTERNA_SENSIBLES = 100005000;
	public static final Integer BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO = 100010000;
	public static final Integer BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD = 100010100;
	public static final Integer BITACORA_INTERNA_OT_ALMACENADA = 100010200;
	public static final Integer BITACORA_INTERNA_OT_EN_MESON_ELABORACION = 100010300;
	
	public static final Long CODIGO_ERROR_MOBILE = 1L;
	public static final Long CODIGO_OK_MOBILE = 0L;
	
	public static final String GIRO_GRANDES_TIENDAS = "GRANDES TIENDAS";
	
	public static final String UBICACION_DA_OW = "RDEDA";
	public static final String UBICACION_AV_OW = "RDEAV";
	public static final String UBICACION_CP_OW = "RDECP";
	public static final String UBICACION_RP_OW = "RDERP";
	public static final String UBICACION_RTR_OW = "RTR";
	public static final String UBICACION_STA_OW = "STA";
	public static final String UBICACION_FF_OW = "FDP";
	public static final String UBICACION_RDEFP = "RDEFP";
	
	
	public static final String TIPO_UBICACION_CASA_REMATE = "CR";
	public static final String TIPO_UBICACION_LIQUIDADORA = "LQ";
	public static final String TIPO_UBICACION_PROVEEDOR = "PR";
	public static final String TIPO_UBICACION_SERVICIO_TECNICO = "ST";
	
	
	public static final Integer OT_ASIGNADAS_EJECUTIVA_MARCA_INDICADOR = 20001000;
	public static final Integer OT_SERVICIO_TECNICO_INDICADOR = 20002000;
	
	public static final Integer OT_SERVICIO_TECNICO_VERDE_CLARO = 20003000;
	public static final Integer OT_SERVICIO_TECNICO_VERDE_OSCURO = 20004000;
	public static final Integer OT_SERVICIO_TECNICO_AMARILLO = 20005000;
	public static final Integer OT_SERVICIO_TECNICO_ROJO = 20006000;
	
	public static final Integer OT_ASIGNADAS_SOLUCIONADAS_CD_INDICADOR = 20007000;
	public static final Integer OT_SOLUCIONADAS_CD_30 = 20008000;
	public static final Integer OT_SOLUCIONADAS_CLIENTE_INDICADOR = 20009000;
	public static final Integer OT_SOLUCIONADAS_CLIENTE_30 = 20010000;
	public static final Integer NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR = 20011000;
	public static final Integer NIVEL_SERVICIO_CD_30 = 20012000;
	public static final Integer NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR = 20013000;
	public static final Integer NIVEL_SERVICIO_NO_CD_30 = 20014000;
	public static final Integer NIVEL_SERVICIO_ACIDO_BY_CD_INDICADOR = 20015000;
	public static final Integer NIVEL_SERVICIO_ACIDO_30 = 20016000;
	public static final Integer TIEMPO_PROMEDIO_STL_INDICADOR = 20017000;
	public static final Integer TIEMPO_SERVICIO_TECNICO_LOCAL_30 = 20018000;
	public static final Integer TIEMPO_PROMEDIO_OT_SOLUCIONADAS_INDICADOR = 20019000;
	public static final Integer TIEMPO_SOLUCIONADA_30 = 20020000;
	public static final Integer TIEMPO_PROMEDIO_ENTREGA_CLIENTE_INDICADOR = 20021000;
	public static final Integer TIEMPO_CERRADA_CLIENTE_30 = 20022000;

	public static final Integer OT_SUCURSAL_INICIO = 10005000;
	public static final Integer OT_SUCURSAL_INICIO_VERDE_CLARO = 10001000;
	public static final Integer OT_SUCURSAL_INICIO_VERDE_OSCURO = 10002000;
	public static final Integer OT_SUCURSAL_INICIO_AMARILLO = 10003000;
	public static final Integer OT_SUCURSAL_INICIO_ROJO = 10004000;
	public static final Integer OT_SUCURSAL_TERMINO = 10010000;
	public static final Integer OT_SUCURSAL_TERMINO_VERDE_CLARO = 10006000;
	public static final Integer OT_SUCURSAL_TERMINO_VERDE_OSCURO = 10007000;
	public static final Integer OT_SUCURSAL_TERMINO_AMARILLO = 10008000;
	public static final Integer OT_SUCURSAL_TERMINO_ROJO = 10009000;
	
	public static final Integer TIEMPO_PROMEDIO_SUCURSAL_INICIO_7 = 10011000;
	public static final Integer TIEMPO_PROMEDIO_SUCURSAL_INICIO_30 = 10012000;
	public static final Integer TIEMPO_PROMEDIO_SUCURSAL_TERMINO_7 = 10013000;
	public static final Integer TIEMPO_PROMEDIO_SUCURSAL_TERMINO_30 = 10014000;
	
	public static final Integer FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_7_DIAS  = 30001000;
	public static final Integer FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_30_DIAS = 30002000;
	public static final Integer FACTURAS_EMITIDAS_ULTIMOS_7_DIAS = 30003000;
	public static final Integer FACTURAS_EMITIDAS_ULTIMOS_30_DIAS = 30004000;
	public static final Integer FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_7_DIAS = 30005000;
	public static final Integer FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_30_DIAS = 30006000;
	public static final Integer FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_7_DIAS = 30007000;
	public static final Integer FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_30_DIAS = 30008000;
	public static final Integer RESUMEN_FACTURAS = -1;
	
	public static final Long BODEGA_10000 = 10000L;
	public static final String APTO_VENTA = "AV";
	public static final String DAÑADO = "DA";
	
	public static final Integer SECCION_DETALLE_INDICADOR = 40001000;
	public static final String DEVOLUCION = "CP";
	public static final String SERVICIO_TECNICO = "RP";
	
	public static final String SIN_PRODUCTO_FISICO = "SIN";
	public static final String CON_PRODUCTO_FISICO = "CON";
	public static final String CAMBIO_CARTA_PRODUCTO_FISICO = "Cambio carta con producto fisico";
	public static final String CAMBIO_CARTA_SIN_PRODUCTO_FISICO = "Cambio carta sin producto fisico";
	
	public static final Integer OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE_CA= 100010800;
	public static final Integer OT_ESTADO_PENDIENTE_POR_TICKET_DE_CAMBIO_QUE_TIENE_EL_CLIENTE_CA= 100010900;

	public static final long ID_OT_INTERFAZ_XN = 1;
	public static final long ID_OT_INTERFAZ_PMM = 2;
	public static final long ID_OT_INTERFAZ_ORR = 3;
	public static final long ID_OT_INTERFAZ_DES_CARGA = 4;
	public static final long ID_OT_INTERFAZ_VER_ASN_ORR = 5;
	public static final long ID_OT_INTERFAZ_ASN = 6;
	public static final long ID_OT_INTERFAZ_VER_ASN = 7;
	
	public static final String PATH_ADJ = "PATH_ADJ";
	public static final String STARWITH_ADJ = "STARWITH_ADJ";
	public static final String ENDSWITH_ADJ = "ENDSWITH_ADJ";
	public static final String SYSTEM_ORIGEN_ADJ = "SYSTEM_ORIGEN_ADJ";
	public static final String SYSTEM_DESTINO_ADJ = "SYSTEM_DESTINO_ADJ";
	
	public static final String PATH_ORDEN = "PATH_ORDEN";
	public static final String STARWITH_ORDEN = "STARWITH_ORDEN";
	public static final String SYSTEM_ORIGEN_ORR = "SYSTEM_ORIGEN_ORR";
	public static final String SYSTEM_DESTINO_ORR = "SYSTEM_DESTINO_ORR";
	public static final String ENDSWITH_ORDEN = "ENDSWITH_ORDEN";

	public static final String DC_QUANTITY_POSITIVO = "1";

	public static final String DC_QUANTITY_NEGATIVO = "-1";

	public static final String PIPE = "|";
	
	public static final String FORMATO_FECHA_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String FORMATO_FECHA_YYYYMMDD = "yyyyMMdd";

	public static final String FROM_INV_TYPE = "04";

	//---------------------------------------------------------------------
	public static final String INV_MRPT_DRPT_CODE_CU_01 = "INV_MRPT_DRPT_CODE_CU_01";
	public static final String INV_MRPT_DRPT_CODE_CU_02 = "INV_MRPT_DRPT_CODE_CU_02"; 
	public static final String INV_MRPT_DRPT_CODE_CU_04_1 = "INV_MRPT_DRPT_CODE_CU_04_1";
	public static final String INV_MRPT_DRPT_CODE_CU_04_2 = "INV_MRPT_DRPT_CODE_CU_04_2";
	public static final String INV_MRPT_DRPT_CODE_CU_04_3 = "INV_MRPT_DRPT_CODE_CU_04_3";
	public static final String INV_MRPT_DRPT_CODE_CU_05 = "INV_MRPT_DRPT_CODE_CU_05";
	public static final String INV_MRPT_DRPT_CODE_CU_06 = "INV_MRPT_DRPT_CODE_CU_06";
	public static final String INV_MRPT_DRPT_CODE_CU_07 = "INV_MRPT_DRPT_CODE_CU_07";
	public static final String INV_MRPT_DRPT_CODE_CU_08 = "INV_MRPT_DRPT_CODE_CU_08";
	public static final String INV_MRPT_DRPT_CODE_CU_09 = "INV_MRPT_DRPT_CODE_CU_09";
	//---------------------------------------------------------------------
	public static final String ACTION_FLAG_ADJ = "A";

	public static final String PRD_SLL_UOM = ""; //"Unidad Medida Pendiente"; 

	public static final String VACIO = "";

	public static final String HDR_GROUP_NBR_CAB_ORR = "[H1]";
	public static final String FACILITY_CODE_CAB_ORR = "A";
	public static final String COMPANY_CODE_CAB_ORR = "ADR";
	public static final String ORDER_NBR_CAB_ORR = "OT";
	public static final String ORDER_TYPE_CAB_ORR = "FALLA";
	public static final String ACTION_CODE_C = "CREATE";
	public static final String CUST_SHORT_TEXT_10_CAB_ORR = "SC1300015";
	public static final String DEST_FACILITY_CODE_HDR_ORR = "DEST_FACILITY_CODE_HDR_ORR";


	public static final String HDR_GROUP_NBR_DET_ORR = "[H2]";

	public static final String ITEM_PART_A = "A";
 
	// Constantes usadas en Cabecera ASN
	public static final String HDR_GROUP_NBR_CAB_ASN = "[H1]";
	public static final String SHIPMENT_NBR = "OT";
	public static final String FACILITY_CODE = "FACILITY_CODE_ASN";
	public static final String COMPANY_CODE_ASN = "ADR";
	public static final String ACTION_CODE_ASN = "CREATE";
	public static final String SHIPMENT_TYPE = "SST";
	public static final String ORIG_SHIPPED_UNITS = "0";
	public static final String ORIG_SHIPPED_LPNS = "0";
	public static final String CUST_FIELD1 = "ABCDin";
	
	// Constantes usadas en Detalle ASN
	public static final String HDR_GROUP_NBR_DET_ASN = "[H2]";
	public static final String LPN_WEGHT = "0";
	public static final String LPN_VOLUME = "0";
	public static final String ItemPartA = "A";
	public static final String PRE_PACK_RATIO = "0";
	public static final String PRE_PACK_TOTAL_UNITS = "0";
	public static final String SHIPPED_QTY = "1";
	public static final String PUT_AWAY_TYPE = "PUT_AWAY_TYPE";
	public static final String PO_SEQ_NBR = "0";
	public static final String PRE_PACK_RATIO_SEQ = "0";
	
	// Constantes usadas en carga de archivos ASN
	public static final String PATH_ASN = "PATH_ASN";
	public static final String STARWITH_ASN = "STARWITH_ASN";
	public static final String SYSTEM_ORIGEN_ASN = "SYSTEM_ORIGEN_ASN";
	public static final String SYSTEM_DESTINO_ASN = "SYSTEM_DESTINO_ASN";
	public static final String ENDSWITH_ASN = "ENDSWITH_ASN";
	
	// Constantes usadas para identificar el origen de cada flujo de OT
	public static final String CASO_USO_6 = "C6";
	public static final String CASO_USO_9 = "C9";
	public static final String CASO_USO_4 = "C4";
	public static final String CASO_USO_3 = "C3";
	public static final String CASO_USO_2 = "C2";
	public static final String CASO_USO_1 = "C1";
	public static final String CASO_USO_5 = "FR";
	public static final String CASO_USO_7 = "C7";
	public static final String CASO_USO_8 = "C8";
	public static final String CASO_USO_10 = "C10";
	public static final String CASO_USO_GENERICO_1 = "G1";
	
	// Constantes usadas para identificar tipo de cambio durante creacion de XN
	public static final String TIPO_XN_TRANSPORTE_CON = "TC";
	public static final String TIPO_XN_TRANSPORTE_SIN = "TS";
	public static final String TIPO_XN_PROVEEDOR_CON = "PC";
	public static final String TIPO_XN_PROVEEDOR_SIN = "PS";
	public static final String TIPO_XN_BODEGA_CON = "BC";
	public static final String TIPO_XN_BODEGA_SIN = "BS";
	
	// Constantes usadas para identificar tipos de sucursales asignadas durante creacion de XN
	
	public static final String ID_SUCURSAL_10490 = "       10490";
	public static final String ID_SUCURSAL_10488 = "       10488";
	public static final String ID_SUCURSAL_10487 = "       10487";
	
	
	// Constantes para el aumento del NO DISPONIBLE en sucursal FRANCHISEDE en PMM.
	public static final String SUCURSAL_FRANCHISE_TRANSPORTE = "SUCURSAL_FRANCHISE_TRANSPORTE";
	public static final String SUCURSAL_FRANCHISE_PROVEEDOR = "SUCURSAL_FRANCHISE_PROVEEDOR";
	public static final String LOGISTICA_INVERSA = "LOGISTICA_INVERSA";
	public static final String CAMBIO_PRODUCTO_FALLA_REITERADA = "Cambio de Producto por falla reiterada";
	public static final String CAMBIO_PRODUCTO_FALLA_VENTA_MENOR_24_HORAS = "Producto con autorización de cambio menor 24 horas";
	public static final String CAMBIO_PRODUCTO_PROVEEDOR = "Cambio de Producto por Proveedor";
	public static final String CAMBIO_CON_PRODUCTO_FISICO = "Cambio con producto fisico";
	public static final String CAMBIO_SIN_PRODUCTO_FISICO = "Cambio sin producto fisico";
}
