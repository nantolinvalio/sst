package cl.abcdin.sst.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.DespachoDetalleDAO;
import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Clasificacion;
import cl.abcdin.sst.model.DespachoDetalle;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.utils.Constants;

public class MovimientosInternosService {
	private BitacoraDAO bitacoraDAO;
	private UbicacionInternaDAO ubicacionInternaDAO;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private DespachoInternoDAO despachoInternoDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private DespachoDetalleDAO despachoDetalleDAO;
	private UtilService utilService;
	private GestionesDAO gestionesDAO;
	private MobileService mobileService;
	
	private static final Log log = LogFactory.getLog(SucursalService.class);
	
	public BitacoraInterna saveBitacoraInterna(OrdenTrabajo ordenTrabajo, Clasificacion clasificacion) throws Exception {
		try {
			BitacoraInterna bitacoraInterna = new BitacoraInterna();
			bitacoraInterna.setBitacora(bitacoraDAO.getBitacoraUltimaUbicacionOT(ordenTrabajo.getId()));
			bitacoraInterna.setFechaInicio(bitacoraInterna.getBitacora().getFechaEntrada());
			bitacoraInterna.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(Constants.CODIGO_BODEGA_FALLA_FABRICACION));
			bitacoraInterna.setOrdenTrabajo(ordenTrabajo);
			bitacoraInterna.setClasificacion(clasificacion);
			bitacoraInterna.setEstado(new Estado());
			bitacoraInterna.getEstado().setId(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION);
			
			bitacoraInterna.setDespachoInterno(new DespachoInterno());
			bitacoraInterna.setRecepcion(new Recepcion());
			bitacoraInternaDAO.saveBitacoraInterna(bitacoraInterna);
			
			return bitacoraInterna;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public BitacoraInterna cerrarYCrearBitacoraInterna(Long idOT,Integer idEstadoNuevaBitacora, DespachoInterno despachoInterno) throws Exception{
		try {
			bitacoraInternaDAO.cerrarBitacoraInternaByIdOT(idOT);
			BitacoraInterna bitacoraInternaNueva = new BitacoraInterna();
			bitacoraInternaNueva.setEstado(new Estado());
			bitacoraInternaNueva.getEstado().setId(idEstadoNuevaBitacora);
			bitacoraInternaNueva.setBitacora(bitacoraDAO.getUltimaBitacora(idOT));
			bitacoraInternaNueva.setUbicacionInterna(new UbicacionInterna());
			if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_TRASLADO_A_SALA_PROVEEDORES)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.UBICACION_ID_TRASLADO);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_SALA_PROVEEDORES)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.SALA_PROVEEDORES);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_REVISADA_SALA_PROVEEDORES)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.BODEGA_FALLA_FABRICACION);
				OrdenTrabajo oT = ordenTrabajoDAO.getClasificacionOTById(idOT);
				bitacoraInternaNueva.setClasificacion(oT.getClasificacion());
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.BODEGA_FALLA_FABRICACION);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION_A_ESPERA_DE_SER_ESCANEADA)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.BODEGA_FALLA_FABRICACION);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.BODEGA_SERVICIO_TECNICO);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD)){
				bitacoraInternaNueva.getUbicacionInterna().setId(Constants.BODEGA_CONTROL_CALIDAD);
			} else if(bitacoraInternaNueva.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_ALMACENADA)){
				UbicacionInternaCD ubicacionInternaCD = mobileService.getUbicacionInternaByIdOT(idOT);
				bitacoraInternaNueva.getUbicacionInterna().setId(ubicacionInternaCD.getId());
			}
			if(despachoInterno!=null){
				bitacoraInternaNueva.setDespachoInterno(despachoInterno);
			}
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(idOT);
			bitacoraInternaNueva.setOrdenTrabajo(ordenTrabajo);
//			bitacoraInternaNueva.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			bitacoraInternaNueva.setClasificacion(bitacoraInternaNueva.getOrdenTrabajo().getClasificacion());
			
			bitacoraInternaDAO.saveBitacoraInterna(bitacoraInternaNueva);
			
			return bitacoraInternaNueva;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	} 
	
	public DespachoDetalle saveDespachoDetalle(DespachoInterno despachoInterno, Usuario usuario, Long idOT)throws Exception {
		try {
			DespachoDetalle despachoDetalle = new DespachoDetalle();
			despachoDetalle.setDespachoInterno(despachoInterno);
			despachoDetalle.setEstado(new Estado());
			despachoDetalle.getEstado().setId(Constants.ESCANER_EN_PROCESO);
			despachoDetalle.setUsuario(usuario);
			
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(idOT);
			despachoDetalle.setOrdenTrabajo(ordenTrabajo);
//			despachoDetalle.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			despachoDetalle.setFechaEstado(utilService.getDate());
			
			despachoDetalleDAO.saveDespachoDetalle(despachoDetalle);
			this.cerrarYCrearBitacoraInterna(idOT, Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION_A_ESPERA_DE_SER_ESCANEADA, despachoInterno);
			return despachoDetalle;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public DespachoInterno saveDespachoInternoASalaProveedores(DespachoInterno despachoInterno, List<CheckForFlexigrid> checksOTs, Usuario usuario) throws Exception{
		try {
			despachoInterno.setUsuarioCreacion(usuario);
			despachoInterno.setEstado(new Estado());
			despachoInterno.getEstado().setId(Constants.ESTADO_DESPACHO_SP_EN_PROCESO);
			despachoInterno.setOrigen(new Ubicacion());
			despachoInterno.getOrigen().setId(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
			despachoInterno.setDestino(despachoInterno.getOrigen());
			despachoInterno.setDestinoInterno(new UbicacionInterna());
			despachoInterno.getDestinoInterno().setId(Constants.SALA_PROVEEDORES);
			despachoInternoDAO.save(despachoInterno);
			
			Gestion gestion = new Gestion();
			gestion.setGestion("Crea lista despacho a Sala Proveedores");
			gestion.setUsuario(usuario);
			for(CheckForFlexigrid checkOT : checksOTs){
				this.saveDespachoDetalle(despachoInterno, usuario, checkOT.getId());
				OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
				ordenTrabajo.setId(checkOT.getId());
				gestion.setOt(ordenTrabajo);
//				gestion.setOt(ordenTrabajoDAO.getOTById(checkOT.getId())); 
				gestionesDAO.saveGestion(gestion);
			}
			
			return despachoInterno;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Boolean terminarEscaneoEnFallaFabricacion(DespachoInterno despachoInterno)throws Exception{
		try {
			List<DespachoDetalle> despachoDetalles = despachoDetalleDAO.listDespachoDetalleByIdDespacho(despachoInterno.getId());
			
			for(DespachoDetalle despachoDetalle : despachoDetalles){
				if(despachoDetalle.getEstado().getId().equals(Constants.ESCANER_EN_PROCESO)){
					return false;
				}
			}
			despachoInterno.setEstado(new Estado());
			despachoInterno.getEstado().setId(Constants.ESTADO_DESPACHO_SP_EN_TRASLADO);
			despachoInternoDAO.updateEstadoByDespachoInterno(despachoInterno);
			
			return true;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listDespachosInternosOnTraladoToSP(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			List<DespachoInterno> despachosInternos = despachoInternoDAO.listDespachosInternosOnTraladoToSP();
			for(DespachoInterno despachoInterno : despachosInternos){
				if(despachoInterno.getCantidadOT().equals(despachoInterno.getCantidadOTRecibidas())){
					this.terminarRecepconDespachoInternoOnSP(despachoInterno);
				}
			}
			listRange.setRows(despachoInternoDAO.listDespachosInternosOnTraladoToSP(gridControl));
			listRange.setTotal(despachoInternoDAO.getTotalDespachosInternosOnTraladoToSP());
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void terminarRecepconDespachoInternoOnSP(DespachoInterno despachoInterno) throws Exception {
		try {
			despachoInterno = despachoInternoDAO.getCantidadesDespachoByDespachoInterno(despachoInterno);
			if(despachoInterno.getCantidadOT().equals(despachoInterno.getCantidadOTRecibidas())){
				despachoInterno.setEstado(new Estado());
				despachoInterno.getEstado().setId(Constants.ESTADO_DESPACHO_SP_TERMINADO);
				despachoInternoDAO.updateEstadoByDespachoInterno(despachoInterno);
			} else {
				throw new SSTException("faltan "+(despachoInterno.getCantidadOT()-despachoInterno.getCantidadOTRecibidas())+"OTs que recibir");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public ListRange listOTRecepcionByDespachoInterno(DespachoInterno despacho, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(ordenTrabajoDAO.listOTRecepcionByDespachoInterno(despacho, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotaltOTRecepcionByDespachoInterno(despacho));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void marcarOTRecibidaINDespachoInternoByFilter(FilterOT filterOT) throws Exception {
		try {
			OrdenTrabajo oT = ordenTrabajoDAO.getOTRecebirDespachoSPByFilter(filterOT);
			if(oT != null){
				this.cerrarYCrearBitacoraInterna(oT.getId(), Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_SALA_PROVEEDORES, despachoInternoDAO.getDespachoById(oT.getDespachoDetalle().getDespachoInterno().getId()));
				if(oT.getDespachoDetalle().getDespachoInterno().getEstado().getId().equals(Constants.ESTADO_DESPACHO_SP_INCOMPLETO)){
					DespachoInterno despachoInterno = oT.getDespachoDetalle().getDespachoInterno();
					despachoInterno.getEstado().setId(Constants.ESTADO_DESPACHO_SP_EN_TRASLADO);
					despachoInternoDAO.updateEstadoByDespachoInterno(despachoInterno);
				}
			} else {
				throw new SSTException("No se ha encontrado la ot ingresada");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw(e);
		}
	}
	
	public DespachoInterno getCantidadesDespachoByDespachoInterno(DespachoInterno despachoInterno) throws Exception {
		try {
			return despachoInternoDAO.getCantidadesDespachoByDespachoInterno(despachoInterno);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO){
		this.bitacoraDAO = bitacoraDAO;
	}
	
	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO){
		this.ubicacionInternaDAO = ubicacionInternaDAO;
	}
	
	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO){
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}
	
	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO){
		this.despachoInternoDAO = despachoInternoDAO;
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO){
		this.ordenTrabajoDAO=ordenTrabajoDAO;
	}
	
	public void setDespachoDetalleDAO(DespachoDetalleDAO despachoDetalleDAO){
		this.despachoDetalleDAO = despachoDetalleDAO;
	}
	
	public void setUtilService(UtilService utilService){
		this.utilService = utilService;
	}
	
	public void setGestionesDAO(GestionesDAO gestionesDAO){
		this.gestionesDAO = gestionesDAO;
	}
	
	public void setMobileService(MobileService mobileService) throws Exception {
		this.mobileService = mobileService;
	}
}
