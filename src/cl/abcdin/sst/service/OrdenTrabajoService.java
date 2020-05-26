package cl.abcdin.sst.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.utils.Constants;

public class OrdenTrabajoService {

	private OrdenTrabajoDAO ordenTrabajoDAO;
	private EstadoDAO estadoDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private SucursalService sucursalService;
	private EjecutivaService ejecutivaService;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private EnvioRecepcionService envioRecepcionService;
	private GuiaDAO guiaDAO;
	private BitacoraDAO bitacoraDAO;
	private MovimientosInternosService movimientosInternosService;
	
	private static final Log log = LogFactory.getLog(OrdenTrabajoService.class);
	
	public OrdenTrabajo getOTCreaEnvioRecepcion(FilterOT filterOT) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = sucursalService.getOTByFilter(filterOT);
			
			//En caso de tener condiciones colocar aca
			
			return ordenTrabajo;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void asignarServicioTecnico(ServicioTecnico servicioTecnico, Usuario usuario) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(servicioTecnico.getOt().getId());
			ejecutivaService.saveGestion(ordenTrabajo.getId(), usuario, " asigna el servicio técnico que se encargará de la reparación");
			
			if (ordenTrabajo.getTipo().getCodigo().equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET) && ordenTrabajo.getEstado().getId().equals(Constants.OT_ESTADO_BIG_TICKET_A_LA_ESPERA_DE_ELEGIR_SERVICIO_TECNICO)) {
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_BIG_TICKET_CON_S_TECNICO_A_LA_ESPERA_DE_VISITA_A_DOMICILIO));
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);				
			}
			
			servicioTecnicoDAO.asignarSTecnicoOT(servicioTecnico);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		//Caso BT
		//actualizarBitacora??
	}
	
	public OrdenTrabajo getOTControlCalidadByFilter(FilterOT filterOT) throws Exception {
		try {
			OrdenTrabajo orden = sucursalService.getOTByFilter(filterOT);
			if(envioRecepcionService.isExcluidoControlCalidadByIdProducto(orden.getProducto().getId())){
				throw new SSTException("Producto excluído de control de calidad");
			} else if(orden.getExcluyeCCalidad()){
				throw new SSTException("Orden de trabajo excluída de control de calidad");
			}
			
			BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getBitacoraInternaById(orden.getId());
			if(bitacoraInterna!=null && bitacoraInterna.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD)){
				orden.setEstado(new Estado());
				orden.getEstado().setId(Constants.OT_EN_CONTROL_CALIDAD);
				ordenTrabajoDAO.updateEstadoOT(orden);
				return orden;
			} else {
				throw new SSTException("El producto debe ser movido a la ubicación control calidad primero");
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public OrdenTrabajo updateOTControlCalidadByOT(OrdenTrabajo oT, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			OrdenTrabajo oTaux = ordenTrabajoDAO.getOTById(oT.getId());
			if(oTaux==null)
				throw new SSTException("la orden de trabajo no existe no existe");
			oT.setUsuarioCCalidad(usuario);
			
			oT.setEstado(new Estado());
			String gestion;
			if(oT.getcCalidadAprueba()){
				oT.getEstado().setId(Constants.OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_ACEPTADO);
				oT.setTareaUrgente(false);
				gestion = " aprueba el control de calidad con la observación: "+oT.getcCalidadObservacion();
			} else {
				oT.getEstado().setId(Constants.OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_RECHAZADO);
				oT.setTareaUrgente(true);
				gestion = " rechaza el control de calidad con la observación: "+oT.getcCalidadObservacion();
				
//				crear guia a servicio técnico
				Guia guiaVieja = guiaDAO.getGuiaByIdOT(oT.getId());
				if(guiaVieja!=null){
					guiaVieja.setEstado(new Estado());
					guiaVieja.setVigente(false);
					guiaVieja.getEstado().setId(Constants.GUIA_ESTADO_DESACTIVADA);
					guiaDAO.updateEstado(guiaVieja);
				}
				
				
				Guia guia = new Guia();
				guia.setOrdenTrabajo(oT);
				guia.setEntregaCliente(false);
				guia.setEstado(new Estado());
				guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
				guia.setVigente(true);
				guia.setOrigen(ubicacion);
				guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
				ServicioTecnico servicioTecnico = servicioTecnicoDAO.getServicioTecnicoByOT(oT.getId());
				if(servicioTecnico == null) {
					FilterServicioTecnico filter = new FilterServicioTecnico();
					filter.setIdOrigen(ubicacion.getId());
					filter.setIdOT(oT.getId());
					filter.setIdProducto(oTaux.getProducto().getId());
					filter.setTipoOT(oTaux.getTipo().getCodigo());
					
					List<ServicioTecnico> servicioTecnicos = servicioTecnicoDAO.listSTecnicoByFilter(filter);
					if (servicioTecnicos != null && servicioTecnicos.size() > 0)
						servicioTecnico = servicioTecnicos.get(0);
					guia.setDestino(new Ubicacion(servicioTecnico));
				} else {
					guia.setDestino(new Ubicacion(servicioTecnico));
				}
				guiaDAO.save(guia);
				
				Bitacora bitacoraNueva = new Bitacora();
				bitacoraNueva.setGuia(guia);
				bitacoraNueva.setOrdenTrabajo(oT);
				bitacoraNueva.setUbicacion(ubicacion);
				bitacoraNueva.setEstado(new Estado());
				bitacoraNueva.getEstado().setId(Constants.BITACORA_EN_CENTRODISTRIBUCION_DESPUES_A_CAMION_AL_SERVICIO_TECNICO);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacoraNueva);
				bitacoraDAO.updateEstadoUltimaBitacora(bitacoraNueva);
			}
			movimientosInternosService.cerrarYCrearBitacoraInterna(oT.getId(), Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO, null);
			ordenTrabajoDAO.updateOTControlCalidadByOT(oT);
			ejecutivaService.saveGestion(oT.getId(), usuario, gestion);
			
			return oT;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public OrdenTrabajo getOTRecibeBodegaFF(FilterOT filter, Ubicacion ubicacion) throws Exception {
		try {
			filter.setVigente(true);
			OrdenTrabajo orden = null;
			try {
				orden = sucursalService.getOTByFilter(filter);
			} catch (SSTException e) {
				filter.setCodigoBarraAccesorio(filter.getCodigoBarra());
				filter.setCodigoBarra(null);
				orden = sucursalService.getOTByFilter(filter);
			}
			if (orden.getEstado().getId().equals(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR))
				return orden;
			else
				throw new SSTException("La orden de trabajo tiene el estado : " + orden.getEstado().getGlosa());
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public OrdenTrabajo saveOTFallaReiterada(OrdenTrabajo oT,Usuario usuario,Ubicacion ubicacion) throws Exception{
		try {
			oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
			oT.setTipo(new TipoOT());
			if(oT.getSucursal()!=null){
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CD);
			}
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA);
			
			return oT = sucursalService.saveOT(oT, usuario, ubicacion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveOtTerminarGarantiaMaster(Long idOT,List<Accesorio> accesorios,Ubicacion ubicacion)throws Exception{
		try {
			OrdenTrabajo ot = ordenTrabajoDAO.getOTById(idOT);
			ordenTrabajoDAO.updateObservacionOT(ot);
//			if(ot.getEstado().getId().equals(Constants.OT_ESTADO_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO) || ot.getEstado().getId().equals(Constants.OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA)){
//				if(accesorios != null && accesorios.size()>0){
//					for(Accesorio accesorio:accesorios){
//						accesorio.setUbicacion(ubicacion);
//						accesorioDAO.updateUbicacionAccesorio(accesorio);
//					}
//				}
//			}
		} catch (Exception e) {
			log.error(e , e);
			throw e; 
		}
	}
	
	public void updateExcluirCCalidad(OrdenTrabajo ordenTrabajo) throws Exception {
		try {
			ordenTrabajoDAO.updateExcluirCCalidad(ordenTrabajo);
			movimientosInternosService.cerrarYCrearBitacoraInterna(ordenTrabajo.getId(), Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO, null);
		} catch (Exception e) {
			log.error(e , e);
			throw e; 
		}
	}
	
	public void setEjecutivaService(EjecutivaService ejecutivaService) {
		this.ejecutivaService = ejecutivaService;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}
	
	public void setSucursalService(SucursalService sucursalService){
		this.sucursalService = sucursalService;
	}
	
	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO) {
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}

	public void setEnvioRecepcionService(EnvioRecepcionService envioRecepcionService) {
		this.envioRecepcionService = envioRecepcionService;
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}
	
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO){
		this.bitacoraDAO = bitacoraDAO;
	}

	public void setMovimientosInternosService(MovimientosInternosService movimientosInternosService) {
		this.movimientosInternosService = movimientosInternosService;
	}
}
