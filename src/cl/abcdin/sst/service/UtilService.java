package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.ColumnaDAO;
import cl.abcdin.sst.dao.ComunaDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.LogisticoDAO;
import cl.abcdin.sst.dao.ParametroDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.RegionDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.dao.UsuarioDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Logistico;
import cl.abcdin.sst.model.Macro;
import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.Region;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.Seccion;
import cl.abcdin.sst.model.SeccionColumna;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterProveedor;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.Modulo;
import cl.abcdin.sst.utils.Constants;

public class UtilService {
	private static final Log log = LogFactory.getLog(UtilService.class);
	
	private UtilDAO utilDAO;
	private EstadoDAO estadoDAO;
	private UbicacionDAO ubicacionDAO;
	private UbicacionInternaDAO  ubicacionInternaDAO;
	private UsuarioDAO usuarioDAO;
	private ParametroDAO parametroDAO;
	private ColumnaDAO columnaDAO;
	private RegionDAO regionDAO;
	private ComunaDAO comunaDAO;
	private LogisticoDAO logisticoDAO;
	private ProveedorDAO proveedorDAO;
	
	public List<Modulo> listModulosMenu(Usuario usuario) throws Exception {
		try {
			return utilDAO.listModulosMenu(usuario);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Date getDate() throws Exception{
		try {
			return utilDAO.getDate();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Date getDateTrunc() throws Exception{
		try {
			return utilDAO.getDateTrunc();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}	
	
	public List<Estado> listEstadosOT() throws Exception{
		try {
			return estadoDAO.listEstadosOT();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<Estado> listEstadoOTFallaFabricacion() throws Exception{
		try {
			return estadoDAO.listEstadoOTFallaFabricacion();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<Estado> listEstadoRecepcionGuia() throws Exception{
		try {
			return estadoDAO.listEstadoRecepcionGuia();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<Estado> listEstadoDespacho() throws Exception {
		try {
			return estadoDAO.listEstadoDespacho();
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public List<Ubicacion> listUbicacionesByIdUsuario(Usuario usuario) throws Exception {
		try {
			return ubicacionDAO.listByIdUsuario(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public List<Ubicacion> listDependenciasByFilter(FilterUbicacion filterUbicacion) throws Exception {
		try {
			return ubicacionDAO.listDependenciasByFilter(filterUbicacion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Ubicacion> listAllByIdUsuario(Usuario usuario) throws Exception {
		try {
			return ubicacionDAO.listAllByIdUsuario(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Ubicacion> listUbicacionesByIdAndTipo(String tipo, Long idRegion) throws Exception{
		try {
			Map tipoAndId = new HashMap();
			tipoAndId.put("tipo", tipo);
			tipoAndId.put("idRegion", idRegion);
			return ubicacionDAO.listUbicacionesByIdAndTipo(tipoAndId);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Ubicacion> listBodegas() throws Exception {
		try {
			return ubicacionDAO.listBodegas();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Ubicacion> listBodegasNotMe(Ubicacion ubicacion) throws Exception {
		try {
			return ubicacionDAO.listBodegasNotMe(ubicacion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Ubicacion> listSucursales() throws Exception {
		try {
			return ubicacionDAO.listByTipo(Constants.UBICACION_SUCURSAL);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<UbicacionInterna> getUbicacionInternaByTipo(String tipo) throws Exception{
		try {
				return ubicacionInternaDAO.getUbicacionInternaByTipo(tipo);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<Usuario> listEjecutivasMarca() throws Exception {
		try {
			return usuarioDAO.listEjecutivasMarca();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Usuario getUsuarioById(Long id) throws Exception {
		try {
			return usuarioDAO.get(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parametro> listTiposUbicacion() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_UBICACION);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public List<Parametro> listTiposDocumento() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_DOCUMENTO);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parametro> listParametrosByTipo(String tipo) throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(tipo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parametro> listTiposCambio() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_CAMBIO);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public List<Parametro> listEstadosAutorizacionCambio() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_OT_AUTORIZACION_CAMBIO);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listProveedoresByFilter(FilterProveedor filterProveedor, GridControl gridControl) throws Exception {
		try {
			filterProveedor.setSortOrder(gridControl.getSortOrder());
			filterProveedor.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(proveedorDAO.listProveedoresByFilter(filterProveedor, gridControl));
			listRange.setTotal(proveedorDAO.getTotalProveedoresByFilter(filterProveedor));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Boolean getCodigoBarraObligatorio() throws Exception {
		try {
			Parametro parametro = parametroDAO.listParametrosByTipo(Constants.PARAMETROS_CODIGO_BARRA_OBLIGATORIO).get(0);
			return parametro.getCodigo().equals("S");
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parametro> listTiposCambioAutomatico() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_CAMBIO_AUTOMATICO);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public List<Parametro> listTiposSemaforo() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_SEMAFORO);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public List<Parametro> listUbicacionesShowByTipo() throws Exception {
		try {
			return parametroDAO.listParametrosByShowTipo(Constants.PARAMETROS_TIPO_UBICACION_);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	

	public List<Parametro> listTiposOT() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_OT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parametro> listProveedorRemateLiquidacion()throws Exception{
		try {
			return parametroDAO.listProveedorRemateLiquidacion();
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public List<Parametro> listTiposOTOT() throws Exception {
		try {
			return parametroDAO.listParametrosByTipo(Constants.PARAMETROS_TIPO_OTOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Columna> listColumnasByFilter(FilterColumna filter, Usuario usuario) throws Exception {
		try {
			List<Columna> columnas;
			filter.setUsuario(usuario);
			if (columnaDAO.getCantidadColumnasByFilter(filter).equals(0)) {
				columnas = columnaDAO.listColumnasDefaultByFilter(filter);
				saveColumnas(columnas, filter, usuario);
			}
			return columnaDAO.listColumnasByFilter(filter);			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Columna> listColumnasIndicadorByFilter(FilterColumna filter) throws Exception {
		try {
			if (columnaDAO.getCantidadColumnasByFilter(filter).equals(0)) {
				return columnaDAO.listColumnasDefaultByFilter(filter);
			}
			return columnaDAO.listColumnasByFilter(filter);			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Columna> listColumnasIndicador(FilterColumna filter) throws Exception {
		try {
			return columnaDAO.listColumnasIndicador(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<Columna> listColumnasNotInIndicador(FilterColumna filter) throws Exception {
		try {
			return columnaDAO.listColumnasNotInIndicador(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Columna> getidColumnasById(Integer id)
			throws Exception {
		try {
			FilterColumna columna = new FilterColumna();
			columna.setIdIndicador(id);
			return columnaDAO.getidColumnasById(columna);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	
	public void saveSeccionColumnaIndicador(Indicador indicador, Rol rol, Seccion seccion, List<Columna> columnas) throws Exception {
		try {
			SeccionColumna seccionColumna = new SeccionColumna();
			seccionColumna.setIndicador(indicador);
			seccionColumna.setRol(rol);
			if(seccionColumna.getRol().getId().equals(Constants.ROL_EJECUTIVA_MARCA.longValue())){
				if(indicador.getId().equals(Constants.OT_ASIGNADAS_EJECUTIVA_MARCA_INDICADOR)){ //20001000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.OT_SERVICIO_TECNICO_INDICADOR)){ //20002000
					indicador.setId(Constants.OT_SERVICIO_TECNICO_INDICADOR);
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_SERVICIO_TECNICO_VERDE_CLARO); //20003000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_SERVICIO_TECNICO_VERDE_OSCURO); //20004000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
						
					indicador.setId(Constants.OT_SERVICIO_TECNICO_AMARILLO); //20005000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
						
					indicador.setId(Constants.OT_SERVICIO_TECNICO_ROJO); //20006000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				
				} else if(indicador.getId().equals(Constants.OT_ASIGNADAS_SOLUCIONADAS_CD_INDICADOR)){ // 20007000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.OT_SOLUCIONADAS_CD_30); // 20008000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
	             } else if(indicador.getId().equals(Constants.OT_SOLUCIONADAS_CLIENTE_INDICADOR)){ //20009000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.OT_SOLUCIONADAS_CLIENTE_30); //2001000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
				 } else if(indicador.getId().equals(Constants.NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR)){ //20011000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.NIVEL_SERVICIO_CD_30); //20012000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
				 } else if(indicador.getId().equals(Constants.NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR)){ // 20013000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas); 
					indicador.setId(Constants.NIVEL_SERVICIO_NO_CD_30); //20014000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				
				 } else if(indicador.getId().equals(Constants.NIVEL_SERVICIO_ACIDO_BY_CD_INDICADOR)){ // 20015000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.NIVEL_SERVICIO_ACIDO_30); // 20016000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				
				 } else if(indicador.getId().equals(Constants.TIEMPO_PROMEDIO_STL_INDICADOR)){ //20017000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas); 
					indicador.setId(Constants.TIEMPO_SERVICIO_TECNICO_LOCAL_30); //20018000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				
				 } else if(indicador.getId().equals(Constants.TIEMPO_PROMEDIO_OT_SOLUCIONADAS_INDICADOR)){ //20019000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas); 
					indicador.setId(Constants.TIEMPO_SOLUCIONADA_30); //20020000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				
				 } else if(indicador.getId().equals(Constants.TIEMPO_PROMEDIO_ENTREGA_CLIENTE_INDICADOR)){ // 20021000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas); 
					indicador.setId(Constants.TIEMPO_CERRADA_CLIENTE_30); //20022000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				 }
			} else if(seccionColumna.getRol().getId().equals(Constants.ROL_EJECUTIVA_FACTURACION.longValue())){ 
				
				if(indicador.getId().equals(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_7_DIAS)){ //30001000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_30_DIAS); //30002000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.FACTURAS_EMITIDAS_ULTIMOS_7_DIAS)){ //30003000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.FACTURAS_EMITIDAS_ULTIMOS_30_DIAS); //30004000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_7_DIAS)){ //30005000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_30_DIAS); //30006000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_7_DIAS)){ //30007000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					indicador.setId(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_30_DIAS); //30008000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				}
			} else if(seccionColumna.getRol().getId().equals(Constants.ROL_LOGISTICO_SUCURSAL.longValue())){
				if(indicador.getId().equals(Constants.INDICADOR_OT_ABIERTAS_SUCURSAL)){ //40001000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.INDICADOR_OT_PENDIENTES_ACCESORIOS)){ //40002000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.INDICADOR_OT_PENDIENTES_GUIAS)){ //40003000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.INDICADOR_OT_PENDIENTES_ENTREGA_CLIENTE)){ // 40005000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.INDICADOR_OT_STL_SIN_CONTRATO)){ // 40006000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.INDICADOR_OT_AUTORIZACION_CAMBIO)){ // 40007000
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} 
			} else if(seccionColumna.getRol().getId().equals(Constants.ROL_EJECUTIVA_GESTION.longValue())){
				if(indicador.getId().equals(Constants.OT_ESTADO_REPARACION_ESPERA_CONTRATO)){ //10005000
					indicador.setId(Constants. OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE); // 10001000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE); //10002000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA); //10003000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
						
					indicador.setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR); //10004000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
						
					indicador.setId(Constants.OT_ESTADO_REPARACION_ESPERA_CONTRATO); //10005000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.OT_ESTADO_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO)){ //10010000
					indicador.setId(Constants.OT_EN_ST_CON_CONTRATO); //10006000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_EN_ST_SIN_CONTRATO); //10007000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_BIG_TICKET_A_LA_ESPERA_DE_ELEGIR_SERVICIO_TECNICO); //10008000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_EN_CLIENTE_ESPERA_ENTREGA_EN_SUCURSAL); //10009000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO); //10010000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_EN_SUCURSAL)){ //10011000 X
					indicador.setId(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_EN_SUCURSAL); //10011000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_SUCURSAL); //10012000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				} else if(indicador.getId().equals(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_CON_OBSERVACION_EN_SUCURSAL)){ //10013000
					indicador.setId(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_CON_OBSERVACION_EN_SUCURSAL); //10013000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
					
					indicador.setId(Constants.OT_CERRADA_POR_CLIENTE_EN_SUCURSAL_POR_PRODUCTO_REPARADO); //10014000
					seccionColumna.setIndicador(indicador);
					limpiaAgregaColumnasByIndicador(seccion, seccionColumna, columnas);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	private void limpiaAgregaColumnasByIndicador (Seccion seccion,SeccionColumna seccionColumna, List<Columna> columnas) throws Exception{
		try {
			Integer orden = 0;
			columnaDAO.deleteSeccionColumnaByIndicadorRol(seccionColumna);
			seccionColumna.setSeccion(seccion);
			for(Columna columna : columnas){
				seccionColumna.setColumna(columna);
				columna.setOrden(orden++);
				columnaDAO.saveSeccionColumnaIndicador(seccionColumna);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
					
	}
		
	public void deleteSeccionColumna(SeccionColumna seccionColumna, Usuario usuario) throws Exception {
		try {
			seccionColumna.setUsuario(usuario);
			SeccionColumna seccionColumnaOld = columnaDAO.getSeccionColumnaBySeccionColumna(seccionColumna);
			columnaDAO.deleteSeccionColumna(seccionColumna);
			columnaDAO.desplazaIzquierdaOrdenSeccionColumna(seccionColumnaOld);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public void updateOrdenColumnasSeccionColumna(SeccionColumna seccionColumna, Usuario usuario) throws Exception {
		try {
			seccionColumna.setUsuario(usuario);
			SeccionColumna seccionColumnaOld = columnaDAO.getSeccionColumnaBySeccionColumna(seccionColumna);
			if (seccionColumna.getColumna().getOrden() > seccionColumnaOld.getColumna().getOrden())
				seccionColumna.getColumna().setOrden(seccionColumna.getColumna().getOrden() + 1);
			seccionColumnaOld.getColumna().setOrden(seccionColumnaOld.getColumna().getOrden() + 1);
			columnaDAO.desplazaDerechaOrdenSeccionColumna(seccionColumna);
			columnaDAO.updateOrdenSeccionColumna(seccionColumna);
			columnaDAO.desplazaIzquierdaOrdenSeccionColumna(seccionColumnaOld);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveColumnas(List<Columna> columnas, FilterColumna filter, Usuario usuario) throws Exception {
		try {
			Seccion seccion = new Seccion();
			seccion.setId(filter.getIdSeccion());
			this.saveColumnas(columnas, seccion, usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	

	
	public void saveSeccionColumna(SeccionColumna seccionColumna, Usuario usuario) throws Exception {
		try {
			seccionColumna.setUsuario(usuario);
			if (columnaDAO.listSeccionColumnaBySeccionColumna(seccionColumna).size() != 0){
				return;
			}
			columnaDAO.desplazaDerechaOrdenSeccionColumna(seccionColumna);
			columnaDAO.deleteSeccionColumna(seccionColumna);
			columnaDAO.saveSeccionColumna(seccionColumna);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveColumnas(List<Columna> columnas, Seccion seccion, Usuario usuario) throws Exception {
		try {
			Integer orden = 1;
			for (Columna columna : columnas) {
				SeccionColumna seccionColumna = new SeccionColumna();
				seccionColumna.setColumna(columna);
				seccionColumna.setSeccion(seccion);
				seccionColumna.setUsuario(usuario);
				columna.setOrden(orden++);
				columnaDAO.saveSeccionColumna(seccionColumna);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	
	public Modulo getModuloInicialByUsuario(Usuario usuario) throws Exception {
		try {			
			return utilDAO.getModuloInicialByUsuario(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Modulo> listModulosNotInRolByMacro(Long idRol, String macro) throws Exception {
		try {			
			return utilDAO.listModulosNotInRolByMacro(idRol, macro);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Modulo> listModulosInRolByMacro(Long idRol, String macro) throws Exception {
		try {			
			return utilDAO.listModulosInRolByMacro(idRol, macro);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Modulo getModuloByNombre(String modulo) throws Exception {
		try {			
			return utilDAO.getModuloByNombre(modulo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Modulo> listSubModuloByFilter(String nombreModulo, Usuario usuario) throws Exception {
		try {
			Modulo modulo = utilDAO.getModuloByNombre(nombreModulo);
			if(modulo!=null && modulo.getId()!=null){
				return utilDAO.listSubModuloByFilter(modulo.getId(), usuario);
			} else {
				return new ArrayList<Modulo>();
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Modulo> listSubModulosInModuloByRol(Long idModulo, Long idRol) throws Exception {
		try { 
			return utilDAO.listSubModulosInModuloByRol(idModulo,idRol);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Modulo> listSubModulosNotInModuloByRol(Long idModulo, Long idRol) throws Exception {
		try {
			return utilDAO.listSubModulosNotInModuloByRol(idModulo,idRol);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Modulo getModuloByNombreUsuario(String modulo, Usuario usuario) throws Exception {
		try {			
			return utilDAO.getModuloByNombreUsuario(modulo, usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Modulo getModuloByNombreUsuarioPermisos(String modulo, Usuario usuario) throws Exception {
		try {			
			return utilDAO.getModuloByNombreUsuarioPermisos(modulo, usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Macro> listMacros() throws Exception {
		try {			
			return utilDAO.listMacros();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Region> listRegiones() throws Exception {
		try {
			return regionDAO.listRegiones();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunas() throws Exception {
		try {
			return comunaDAO.listComunas();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunasByRegion(Long idRegion) throws Exception {
		try {
			return comunaDAO.listComunasByRegion(idRegion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunasConSTByRegion(Long idRegion) throws Exception {
		try {
			return comunaDAO.listComunasConSTByRegion(idRegion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunasConSucursalesByRegion(Long idRegion) throws Exception {
		try {
			return comunaDAO.listComunasConSucursalesByRegion(idRegion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunasST() throws Exception {
		try {
			return comunaDAO.listComunasST();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Modulo getModuloByCodigo(String codigo) throws Exception {
		try {
			return utilDAO.getModuloByCodigo(codigo);
		}
		catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Modulo getModuloById(Long id) throws Exception {
		try {
			return utilDAO.getModuloById(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Ubicacion getUbicacionById(Long id)throws Exception{
		try {
			return ubicacionDAO.get(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Ubicacion getCentroDistribucion() throws Exception {
		try {
			return ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Ubicacion getCentroDistribucionFF() throws Exception {
		try {
			return ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Ubicacion getUbicacionTransportista() throws Exception {
		try {
			return ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Logistico> listLogisticosRecepcionesCamion() throws Exception {
		try {
			return logisticoDAO.listLogisticosRecepcionesCamion();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void logClientException(Map<?, ?> e, String message) throws Exception {
//		Para logear los mensajes de la presentacion descomentar este codigo
//		StringBuilder st = new StringBuilder();
//		st.append("Message     => " + message + "\n");
//		st.append("Line Number => " + e.get("lineNumber") + "\n");
//		st.append("File Name   => " + e.get("fileName") + "\n");
//		st.append("Stack       => \n" + e.get("stack") + "\n");
//		
//		Exception ex = new Exception(st.toString());
//		log.error(ex);
	}
	
	public List<Ubicacion> listUbicacionByTipo(String tipo) throws Exception {
		try {
			return ubicacionDAO.listUbicacionByTipo(tipo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Ubicacion> listDestinosForIdOrigen(Ubicacion ubicacion)throws Exception{
		try {
			return ubicacionDAO.listDestinosForIdOrigen(ubicacion);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Ubicacion getProveedorById(Long id)throws Exception{
		try {
			return proveedorDAO.getProveedorById(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	public void setColumnaDAO(ColumnaDAO columnaDAO) {
		this.columnaDAO = columnaDAO;
	}
	
	public void setRegionDAO(RegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	
	public void setComunaDAO(ComunaDAO comunaDAO) {
		this.comunaDAO = comunaDAO;
	}

	public void setLogisticoDAO(LogisticoDAO logisticoDAO) {
		this.logisticoDAO = logisticoDAO;
	}

	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO) {
		this.ubicacionInternaDAO = ubicacionInternaDAO;
	}
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}
}
