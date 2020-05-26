package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.DespachoDetalleDAO;
import cl.abcdin.sst.dao.DespachoInternoCamionDAO;
import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.dao.EnvioSucursalDAO;
import cl.abcdin.sst.dao.EnvioSucursalDetalleDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.GuiaRemateDAO;
import cl.abcdin.sst.dao.IndicadorDAO;
import cl.abcdin.sst.dao.InventarioDAO;
import cl.abcdin.sst.dao.InventarioProductoDAO;
import cl.abcdin.sst.dao.InventarioUbicacionDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.PeticionDocumentoDAO;
import cl.abcdin.sst.dao.PeticionDocumentoDetalleDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.SelloDAO;
import cl.abcdin.sst.dao.SemaforoDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.TransportistaDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Clasificacion;
import cl.abcdin.sst.model.DespachoDetalle;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.DespachoInternoCamion;
import cl.abcdin.sst.model.EnvioSucursal;
import cl.abcdin.sst.model.EnvioSucursalDetalle;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.Inventario;
import cl.abcdin.sst.model.InventarioProducto;
import cl.abcdin.sst.model.InventarioUbicacion;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.PeticionDocumento;
import cl.abcdin.sst.model.PeticionDocumentoDetalle;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.Sello;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.comparators.OrdenTrabajoByProductoComparator;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.model.filters.FilterDespachoInternoDetalle;
import cl.abcdin.sst.model.filters.FilterEnvioSucursal;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterUbicacionInterna;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.OWConstants;
import cl.abcdin.sst.utils.Utils;


public class BodegaService {
	private static final Log log = LogFactory.getLog(EjecutivaService.class);
	
	private GuiaDAO guiaDAO;
	private SelloDAO selloDAO;
	private UtilDAO utilDAO;
	private DespachoInternoDAO despachoInternoDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private GuiaRemateDAO guiaRemateDAO;
	private EstadoDAO estadoDAO;
	private SemaforoDAO semaforoDAO;
	private UbicacionDAO ubicacionDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private GestionesDAO gestionesDAO;
	private BitacoraDAO bitacoraDAO;
	private DespachoDetalleDAO despachoDetalleDAO;
	private DespachoInternoCamionDAO despachoInternoCamionDAO;
	private AccesorioDAO accesorioDAO;
	private IndicadorDAO indicadorDAO;
	private PeticionDocumentoDAO peticionDocumentoDAO;
	private PeticionDocumentoDetalleDAO peticionDocumentoDetalleDAO;
	private MovimientosInternosService movimientosInternosService;
	private UbicacionInternaDAO ubicacionInternaDAO;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private ProveedorDAO proveedorDAO;
	private InventarioDAO inventarioDAO;
	private TransportistaDAO transportistaDAO;
	private InventarioUbicacionDAO inventarioUbicacionDAO;
	private InventarioProductoDAO inventarioProductoDAO;
	private EnvioSucursalDAO envioSucursalDAO;
	private EnvioSucursalDetalleDAO envioSucursalDetalleDAO;
	private SucursalService sucursalService;
	private OWService oWService;
	private EnvioRecepcionService envioRecepcionService; 
	
	public ListRange listGuiasBodegaByFilter(FilterGuia filter,  GridControl gridControl, Ubicacion ubicacion)throws Exception{
		try {
			filter.setoTCerrada(false);
			filter.setIdOrigen(ubicacion.getId());
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			
			ListRange listRange = new ListRange();
			listRange.setRows(guiaDAO.listGuiasBodegaByFilter(filter, gridControl));
			listRange.setTotal(guiaDAO.getTotalGuiasBodegaByFilter(filter));
			
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public OrdenTrabajo getOTRevisaBodegaFF(FilterOT filter, Ubicacion ubicacion) throws Exception {
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
			if (orden.getEstado().getId().equals(Constants.OT_RECIBIDA_POR_REVISAR_EN_FALLA_FABRICACION))
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
	
	public OrdenTrabajo getOTRevisaSalaProveedores(FilterOT filter) throws Exception {
		try {
			filter.setVigente(true);
			OrdenTrabajo orden = null;
			orden = sucursalService.getOTByFilter(filter);
			if(orden!=null && orden.getBitacoraInterna()!= null && orden.getBitacoraInterna().getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_SALA_PROVEEDORES)){
				return orden;
			}else if(orden!=null && orden.getBitacoraInterna()!= null && orden.getBitacoraInterna().getEstado().getId().equals(Constants.BITACORA_INTERNA_ESTADO_OT_TRASLADO_A_SALA_PROVEEDORES)){
				movimientosInternosService.marcarOTRecibidaINDespachoInternoByFilter(filter);
				return orden;
			} else {
				throw new SSTException("El producto no requiere revisión");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public ListRange listOTByFilter(FilterOT filterOT, List<Rol> roles, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			filterOT.setRoles(roles);				
			listRange.setRows(ordenTrabajoDAO.listByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listGuiasRemate(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(guiaRemateDAO.listGuiasRemate(gridControl));
			listRange.setTotal(guiaRemateDAO.getTotalGuiasRemate());
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void eliminarGuiaRemate(List<GuiaPendienteAgrupada> guiaPendienteAgrupadas, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			for (GuiaPendienteAgrupada guia : guiaPendienteAgrupadas) {
				guia = guiaRemateDAO.getGuiaRemateById(guia.getId());
				if (guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION)) {
					throw new SSTException("Hay Seleccionadas guias recepcionadas. No se puede eliminar una guía que ha sido recibida.");
				}
			}
			
			for (GuiaPendienteAgrupada guia : guiaPendienteAgrupadas) {
				guiaRemateDAO.deleteDetalleGuiaRemate(guia.getId());
				guiaRemateDAO.deleteGuiaRemate(guia.getId());
			}		
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ServicioTecnico saveServicioTecnico(Ubicacion ubicacion) throws Exception {
		try {
			if(ubicacion.getId()== null || ubicacion.getId() == 0 ){
				ubicacion.setTipo(Constants.UBICACION_SERVICIO_TECNICO);
				ubicacion.setVigente(true);
				ubicacionDAO.save(ubicacion);
		    } else {
		    	ubicacionDAO.updateUbicacion(ubicacion);
			}
			ServicioTecnico servicioTecnico = servicioTecnicoDAO.getSTecnicoById(ubicacion.getId());
			return servicioTecnico;
		
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	

	
	
	
	public void marcarRecibidaGuiaRemate(List<GuiaPendienteAgrupada> guiaPendienteAgrupadas, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			Estado estado = estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION);
			
			for (GuiaPendienteAgrupada guia : guiaPendienteAgrupadas) {
				guia = guiaRemateDAO.getGuiaRemateById(guia.getId());
				if (guia.getEstado().getId().equals(Constants.GUIA_ESTADO_POR_EMITIR)) {
					throw new SSTException("Hay Seleccionadas guías que no han sido emitidas. No se puede marcar como recibida una guia que no ha sido emitida.");
				} else if (guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION)) {
					throw new SSTException("Hay Seleccionadas guías que están marcadas como Recibidas.");
				} else if (!guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA)) {
					throw new SSTException("Hay Seleccionadas guías en estado " + guia.getEstado().getNombre());
				}
				
			}
			
			for (GuiaPendienteAgrupada guia : guiaPendienteAgrupadas) {
				guia.setEstado(estado);
				guiaRemateDAO.updateEstado(guia);
				guiaRemateDAO.cerrarOTFromGuiaRemate(guia);
			}		
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	 public DespachoInterno getDespachoById(Long idDespacho) throws Exception{
		 try {
			 return despachoInternoDAO.getDespachoById(idDespacho);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	
	 
	 public ListRange listOtByDespacho(DespachoInterno despacho,GridControl gridControl) throws Exception{
		 try {
			 	FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
			 	filterDespacho.setIdDespacho(despacho.getId());
			 
			 	ListRange listRange = new ListRange();
			 	filterDespacho.setOrderBy(gridControl.getOrderBy());
			 	filterDespacho.setSortOrder(gridControl.getSortOrder());
			 	
				listRange.setRows(ordenTrabajoDAO.listOtByDespacho(filterDespacho, gridControl));
				listRange.setTotal(ordenTrabajoDAO.getOtByDespacho(filterDespacho));
				return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	 
	 public List<OrdenTrabajo> listOtEscaneadasByDespacho(DespachoInterno despacho) throws Exception{
		 try {
			 return ordenTrabajoDAO.listOtEscaneadasByDespacho(despacho);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	 
	 public ListRange listOtByDespachoToSP(DespachoInterno despacho,GridControl gridControl) throws Exception{
		 try {
			 	FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
			 	filterDespacho.setIdDespacho(despacho.getId());
			 
			 	ListRange listRange = new ListRange();
			 	filterDespacho.setOrderBy(gridControl.getOrderBy());
			 	filterDespacho.setSortOrder(gridControl.getSortOrder());
			 	
				listRange.setRows(ordenTrabajoDAO.listOtByDespachoToSP(filterDespacho, gridControl));
				listRange.setTotal(ordenTrabajoDAO.getTotalOtByDespachoToSP(filterDespacho));
				return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	
	 public List<Guia> processGuiasGim(DespachoInterno despachoInterno, Usuario usuario,Ubicacion ubicacion)throws Exception{
		 try {
			 FilterDespachoInterno filterDespachoInterno = new FilterDespachoInterno();
			 filterDespachoInterno.setIdDespacho(despachoInterno.getId());
			 List<OrdenTrabajo> ots = ordenTrabajoDAO.listOtByDespacho(filterDespachoInterno);
			 List<Guia> guiasCreadas = new ArrayList<Guia>();
			 
			Bitacora bitacora = new Bitacora();
			Integer productos = 0;
			Producto productoAnterior = new Producto();
			productoAnterior.setId(0);
			
			
			if (!ots.isEmpty()) {
				Guia guia = this.saveGuiaDespachoParaAgrupacion(despachoInterno,ubicacion, usuario,ubicacionDAO.get(despachoInterno.getDestino().getId()),false);
				
				guiasCreadas.add(guia);
				for (OrdenTrabajo ordenTrabajo : ots) {
					if (!productoAnterior.getId().equals(ordenTrabajo.getProducto().getId())) {
						productos ++;
						productoAnterior = ordenTrabajo.getProducto();
						if (productos.equals(40)){
							productos = 0;
							guia = this.saveGuiaDespachoParaAgrupacion(despachoInterno,ubicacion, usuario, ubicacionDAO.get(despachoInterno.getDestino().getId()),false);
							guiasCreadas.add(guia);
						}	
						//asignar accesorios que esten en mi ubicacion a la guia
						asignarGuiaDespachoToAccesorios(guia, accesorioDAO.listAccesoriosByOT(ordenTrabajo.getId()),ubicacion);
					}
					bitacora.setGuia(guia);
					bitacora.setOrdenTrabajo(ordenTrabajo);
					bitacora.setUbicacion(ubicacion);
					bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				}
			}
			return guiasCreadas;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	 
	 public List<Guia> processGuiasGimMobile(DespachoInterno despachoInterno, Usuario usuario,Ubicacion ubicacion,DespachoInternoCamion despachoInternocamion)throws Exception{
		 try {
		 	List<Guia> guiasCreadas = new ArrayList<Guia>();
			Bitacora bitacora = new Bitacora();
			Integer productos = 0;
			Producto productoAnterior = new Producto();
			productoAnterior.setId(0);
			
			List<OrdenTrabajo> ots =ordenTrabajoDAO.listOtEscaneadasByDespacho(despachoInterno);// solo ots escaneadas
			
			
			if (!ots.isEmpty()) {
				Ubicacion destino;
				destino = ubicacionDAO.get(despachoInterno.getDestino().getId());
				if(destino == null){
					destino = proveedorDAO.getProveedorById(despachoInterno.getDestino().getId());
				}
				Guia guia = this.saveGuiaDespachoParaAgrupacionMobile(despachoInterno,ubicacion, usuario,destino,false,despachoInternocamion);
				
				guiasCreadas.add(guia);
				for (OrdenTrabajo ordenTrabajo : ots) {
					if (!productoAnterior.getId().equals(ordenTrabajo.getProducto().getId())) {
						productos ++;
						productoAnterior = ordenTrabajo.getProducto();
						if (productos.equals(40)){
							productos = 0;
							guia = this.saveGuiaDespachoParaAgrupacionMobile(despachoInterno,ubicacion, usuario, ubicacionDAO.get(despachoInterno.getDestino().getId()),false,despachoInternocamion);
							guiasCreadas.add(guia);
						}	
						//asignar accesorios que esten en mi ubicacion a la guia
						asignarGuiaDespachoToAccesorios(guia, accesorioDAO.listAccesoriosByOT(ordenTrabajo.getId()),ubicacion);
					}
					bitacora.setGuia(guia);
					bitacora.setOrdenTrabajo(ordenTrabajo);
					bitacora.setUbicacion(ubicacion);
					bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				}
			}else{
				throw new SSTException("No existen ots escaneadas para trasladar");
			}
			return guiasCreadas;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	 }
	 
	 
	 private void asignarGuiaDespachoToAccesorios(Guia guia,List<Accesorio> accesorios,Ubicacion ubicacion)throws Exception{
			try {
				for(Accesorio accesorio : accesorios){
					if (accesorio.getUbicacion().getId().equals(ubicacion.getId())) {
						accesorio.setGuia(guia);
						accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
					}
				}
			} catch (Exception e) {
				log.error(e , e);
				throw e;
			}
		}
	 
	 public Guia saveGuiaDespachoParaAgrupacion(DespachoInterno despachoInterno,Ubicacion origen, Usuario usuario, Ubicacion destino, Boolean reemitir) throws Exception {
			try {
				Guia guiaDespachoInterno = new Guia();
				if(!reemitir){
					DespachoInternoCamion despachoInternoCamion = new DespachoInternoCamion();
					despachoInternoCamion.setDespachoInterno(despachoInterno);
					despachoInternoCamionDAO.save(despachoInternoCamion);
					guiaDespachoInterno.setDespachoInternoCamion(despachoInternoCamion);
				}
				
				guiaDespachoInterno.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
				guiaDespachoInterno.setOrigen(origen);
				guiaDespachoInterno.setDestino(destino);
				guiaDespachoInterno.setEntregaCliente(false);
				guiaDespachoInterno.setVigente(true);
				guiaDespachoInterno.setUsuario(usuario);
				guiaDespachoInterno.setTipoGuia(Constants.GUIA_TIPO_AGRUPADA);
				guiaDAO.save(guiaDespachoInterno);
				return guiaDespachoInterno;
			} catch (Exception e) {
				log.error(e,e);
				throw e;
			}
		}
	 
	 private Guia saveGuiaDespachoParaAgrupacionMobile(DespachoInterno despachoInterno,Ubicacion origen, Usuario usuario, Ubicacion destino, Boolean reemitir,DespachoInternoCamion despachoInternoCamion) throws Exception {
			try {
				Guia guiaDespachoInterno = new Guia();
					if(despachoInternoCamion == null){
						DespachoInternoCamion despachoInternoCamionAux = new DespachoInternoCamion();
						despachoInternoCamionAux.setDespachoInterno(despachoInterno);
						despachoInternoCamion = despachoInternoCamionAux;
								
					}
					despachoInternoCamionDAO.save(despachoInternoCamion);
					guiaDespachoInterno.setDespachoInternoCamion(despachoInternoCamion);
				
				guiaDespachoInterno.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
				guiaDespachoInterno.setOrigen(origen);
				guiaDespachoInterno.setDestino(destino);
				guiaDespachoInterno.setEntregaCliente(false);
				guiaDespachoInterno.setVigente(true);
				guiaDespachoInterno.setUsuario(usuario);
				guiaDespachoInterno.setTipoGuia(Constants.GUIA_TIPO_AGRUPADA);
				guiaDAO.save(guiaDespachoInterno);
				return guiaDespachoInterno;
			} catch (Exception e) {
				log.error(e , e);
				throw e;
			}
		} 
	 
	public void generarTrasladoFF(List<OrdenTrabajo> ots,Ubicacion ubicacion,Usuario usuario) throws Exception{
		try {
			DespachoInterno despacho = new DespachoInterno();
			despacho.setEstado(estadoDAO.getEstadoById(Constants.DESPACHO_EN_PROCESO));
			despacho.setUsuarioCreacion(usuario);
			despacho.setOrigen(ubicacion);
			despacho.setDestino(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
			
			
			despachoInternoDAO.save(despacho);
			
			for(OrdenTrabajo ot : ots){
				asignarOTTraslado(ot,despacho,usuario);
				Bitacora bitacora = bitacoraDAO.getUltimaBitacoraAbierta(ot.getId());
				bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CD_LUEGO_A_CAMION_A_FF));
				bitacoraDAO.updateEstadoBitacora(bitacora);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public DespachoInterno generarTrasladoDesdeFF(List<OrdenTrabajo> ots,Ubicacion destino,String tipoUbicacion,Ubicacion ubicacion,Usuario usuario) throws Exception{
		try {
			DespachoInterno despacho = new DespachoInterno();
			despacho.setEstado(estadoDAO.getEstadoById(Constants.DESPACHO_EN_PROCESO));
			despacho.setUsuarioCreacion(usuario);
			despacho.setOrigen(ubicacion);
			if(tipoUbicacion.equals("PR")){
				destino = proveedorDAO.getProveedorById(destino.getId());
				despacho.setDestino(destino);
			} else {
				destino = ubicacionDAO.get(destino.getId());
				despacho.setDestino(destino);
			}
			despacho.setTipoUbicacion(tipoUbicacion);
			despachoInternoDAO.save(despacho);
			
			for(OrdenTrabajo ot : ots){
				asignarOTTrasladoDesdeFF(ot,destino,tipoUbicacion,despacho,usuario);
				Bitacora bitacora = bitacoraDAO.getUltimaBitacoraAbierta(ot.getId());
				if(tipoUbicacion.equals(Constants.TIPO_UBICACION_CASA_REMATE)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_A_CASA_REMATE));
				} else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_LIQUIDADORA)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_A_LIQUIDADORA));
				} else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_PROVEEDOR)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_A_PROVEEDOR));
				}else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_SERVICIO_TECNICO)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_A_SERVICIO_TECNICO));
				}
				bitacoraDAO.updateEstadoBitacora(bitacora);
			}
			return despacho;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private void asignarOTTrasladoDesdeFF(OrdenTrabajo ot,Ubicacion destino,String tipoUbicacion,DespachoInterno despacho,Usuario usuario) throws Exception{
		try {
			
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());

			if(tipoUbicacion.equals(Constants.TIPO_UBICACION_PROVEEDOR)){
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_APTA_PARA_TRASLADO_A_PROVEEDOR));
			} else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_CASA_REMATE)){
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_APTA_PARA_TRASLADO_A_CASA_REMATE));
			} else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_LIQUIDADORA)){
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_APTA_PARA_TRASLADO_A_LIQUIDADORA_SEGUNDA));
			} else if(tipoUbicacion.equals(Constants.TIPO_UBICACION_SERVICIO_TECNICO)){
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_APTA_PARA_TRASLADO_A_SERVICIO_TECNICO));
			}
			
			ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
			
			DespachoDetalle detalleDespacho = new DespachoDetalle();
			detalleDespacho.setDespachoInterno(despachoInternoDAO.getDespachoById(despacho.getId()));
			detalleDespacho.setEstado(estadoDAO.getEstadoById(Constants.ESCANER_EN_PROCESO));
			detalleDespacho.setOrdenTrabajo(ordenTrabajo);
			detalleDespacho.setFechaEstado(utilDAO.getDate());
			detalleDespacho.setUsuario(usuario);
			despachoDetalleDAO.saveDespachoDetalle(detalleDespacho);
			
			Gestion gestion = new Gestion();
			gestion.setOt(ot);
			gestion.setUsuario(usuario);
			gestion.setGestion(" Asigna la OT nó " + ot.getId() + " para traslado hacia "+destino.getNombre());
			gestionesDAO.saveGestion(gestion);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private void asignarOTTraslado(OrdenTrabajo ot,DespachoInterno despacho,Usuario usuario) throws Exception{
		try {
			
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());
			ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_APTA_PARA_TRASLADO_A_FF));
			ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
			
			DespachoDetalle detalleDespacho = new DespachoDetalle();
			detalleDespacho.setDespachoInterno(despachoInternoDAO.getDespachoById(despacho.getId()));
			detalleDespacho.setEstado(estadoDAO.getEstadoById(Constants.ESCANER_EN_PROCESO));
			detalleDespacho.setOrdenTrabajo(ordenTrabajo);
			detalleDespacho.setFechaEstado(utilDAO.getDate());
			detalleDespacho.setUsuario(usuario);
			despachoDetalleDAO.saveDespachoDetalle(detalleDespacho);
			
			Gestion gestion = new Gestion();
			gestion.setOt(ot);
			gestion.setUsuario(usuario);
			gestion.setGestion(" Asigna la OT nó " + ot.getId() + " para traslado a falla de fabriciación ");
			gestionesDAO.saveGestion(gestion);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listOtByDespachoCheck(DespachoInterno despacho)throws Exception{
		try {
			 List<CheckForFlexigrid> lst = new ArrayList<CheckForFlexigrid>();
			lst = ordenTrabajoDAO.listOtByDespachoCheck(despacho);
			return lst;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void desvincularOTDespacho(DespachoInterno despacho, List<OrdenTrabajo> ots,Ubicacion ubicacion)throws Exception{
		try {
			if(ots.size()==0){
				throw new SSTException("No hay Ordenes de Trabajo que eliminar");
			}
			DespachoDetalle despachoDetalle = new DespachoDetalle();
			despachoDetalle.setDespachoInterno(despacho);
			despachoDetalle.setEstado(new Estado());
			despachoDetalle.getEstado().setId(Constants.PRODUCTO_NO_ENCONTRADO);
			for(OrdenTrabajo ot : ots){
				despachoDetalle.setOrdenTrabajo(ot);
				despachoDetalleDAO.updateEstadoDespachoDetalleByIdAndOT(despachoDetalle);
				movimientosInternosService.cerrarYCrearBitacoraInterna(ot.getId(), Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION, null);
			}
//			this.terminarRecepcionDespachoInternoOnSP(despacho,ubicacion,false);
			this.terminarEscaneoToSPOnFallaFabricacion(despacho);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private Boolean terminarEscaneoToSPOnFallaFabricacion(DespachoInterno despacho) throws Exception {
		try {
			FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
		 	filterDespacho.setIdDespacho(despacho.getId());
		 	
		 	List<DespachoDetalle> despachoDetalles = despachoDetalleDAO.listDespachoDetalleByIdDespacho(despacho.getId());
			if(despachoDetalles.size() == 0){
				despacho.setEstado(estadoDAO.getEstadoById(Constants.ESTADO_DESPACHO_SP_TERMINADO));
			}else{
				for(DespachoDetalle despachoDetalle : despachoDetalles){
					if(despachoDetalle.getEstado().getId().equals(Constants.ESCANER_EN_PROCESO))
						return false;
				}
				despacho.setEstado(estadoDAO.getEstadoById(Constants.ESTADO_DESPACHO_SP_EN_TRASLADO));
			}
			despachoInternoDAO.updateEstadoByDespachoInterno(despacho);
			return true;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public void desvincularOTDespachoExterno(DespachoInterno despacho, List<OrdenTrabajo> ots)throws Exception{
		try {
			DespachoDetalle despachoDetalle = new DespachoDetalle();
			despachoDetalle.setDespachoInterno(despacho);
			despachoDetalle.setEstado(estadoDAO.getEstadoById(Constants.PRODUCTO_NO_ENCONTRADO));
			for(OrdenTrabajo ot : ots){
				Bitacora bitacora = bitacoraDAO.getUltimaBitacoraAbierta(ot.getId());
				bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FALLAS_DE_FABRICACION));
				bitacoraDAO.updateEstadoBitacora(bitacora);
				despachoDetalle.setOrdenTrabajo(ot);
				despachoDetalleDAO.updateEstadoDespachoDetalleByIdAndOT(despachoDetalle);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void updateEstadoFromDespachoToNextEstado(DespachoInterno despachoInterno) throws Exception{
		try {
			despachoInternoDAO.updateEstadoFromDespachoToNextEstado(despachoInterno);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void updateEstadoFromDespachoToBackEstado(DespachoInterno despachoInterno) throws Exception{
		try {
				if(despachoInterno.getEstado().getId().equals(Constants.DESPACHO_TERMINADO)){
					despachoInterno.setEstado(estadoDAO.getEstadoById(Constants.DESPACHO_PARA_EMISION_DE_GUIA));
				}
			despachoInternoDAO.updateEstadoByDespachoInterno(despachoInterno);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<Guia> listGuiasByIdDespacho(Long id) throws Exception{
		try {
				return guiaDAO.listGuiasByIdDespacho(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<Guia> listGuiasToUbicacionDiezMilByidDespacho(Long id) throws Exception{
		try {
				return guiaDAO.listGuiasToUbicacionDiezMilByidDespacho(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void emitirGuiaDespachoAgrupada(DespachoInterno despachoInterno,Guia guia, Usuario usuario, Ubicacion ubicacion) throws Exception{
		try {
			//filtrar los accesorios por la guia y la ot para cambiar su ubicacion
			Ubicacion destino ;
//			String clasificacionOT = "";
			destino = ubicacionDAO.get(guia.getDestino().getId());
			if(destino == null){
				destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
			}
			guia.setDestino(destino);
			guia.setUsuario(usuario);
			guia.setEstado(new Estado());
			guia.setOrigen(ubicacion);
			guia.setEntregaCliente(false);
			guia.getEstado().setId(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);
			
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
			
			envioRecepcionService.validaStockAgrupado(guia.getOrigen(), oWService.convertUbicaciontoOW(ots.get(0).getClasificacion().getCodigo()), guia);
			
			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");
			
			
			for (OrdenTrabajo ordenTrabajo : ots) {
//				clasificacionOT = ordenTrabajo.getClasificacion().getCodigo();
				updateUbicacionAccesoriosGuiaDespacho(guia,ordenTrabajo, ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE), guia.getOrigen());
				ordenTrabajo.setEstado(new Estado());
				ordenTrabajo.getEstado().setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);
				filterOT.setIdOT(ordenTrabajo.getId());
				Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
				sucursalService.saveBitacoraByGuiaAndOT(guia,ordenTrabajo, bitacora);
			}
//			if((destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE)) || (destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
//				guia.setProcesadoOW(true);
//				guia.setNumeroTSTO(oWService.createTSTO(guia.getId(),clasificacionOT,destino.getCodigoOW()));
//				
//				if (guiaDAO.update(guia).equals(0))
//					throw new SSTException("Error al actualizar el registro");
//			} else {
//				for (OrdenTrabajo ordenTrabajo : ots) {
//					filterOT.setIdGuiaAgrupada(guia.getId());	
//					filterOT.setIdOT(ordenTrabajo.getId());
//					Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
//					bitacora.setNumeroIT(oWService.moveIT(ordenTrabajo, ubicacion.getId(), oWService.convertUbicaciontoOW(clasificacionOT), Constants.UBICACION_STA_OW));
//					bitacoraDAO.updateEstadoBitacora(bitacora);
//				}
//			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Guia confirmarGuiaDespachoAgrupada (Guia guia,Usuario usuario,Ubicacion ubicacion)throws Exception{
		try {
			guia = guiaDAO.get(guia.getId());

			if (!guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA)){
				throw new SSTException("La guía no se encuentra en estado. POR CONFIRMAR RECEPCIÓN");
			}
			guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));  
			guia.setVigente(true);
			
			Ubicacion destino;
			destino = ubicacionDAO.get(guia.getDestino().getId());
			if(destino == null){
				destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
			}
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
			
			String clasificacionOT = ots.get(0).getClasificacion().getCodigo();
			
			if((destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE)) || (destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
				guia.setProcesadoOW(true);
				guia.setNumeroTSTO(oWService.createTSTO(guia.getId(),oWService.convertUbicaciontoOW(clasificacionOT),destino.getCodigoOW()));
				
				if (guiaDAO.updateEstado(guia).equals(0))
					throw new SSTException("Error al actualizar el registro");
				
				guia.setNumeroTO(oWService.receiveTO(guia.getId()));
				
				if (guiaDAO.updateEstado(guia).equals(0))
					throw new SSTException("Error al actualizar el registro");
			} else {
				List<OrdenTrabajo> otAgrupadas = groupOtForITAgrupada(ots, ubicacion);
				for(OrdenTrabajo ot : otAgrupadas){
					ot.setNumeroIT(oWService.moveITAgrupada(ot, ubicacion.getId(), oWService.convertUbicaciontoOW(clasificacionOT), Constants.UBICACION_STA_OW));
					for(OrdenTrabajo otAux : ots){
						if(ot.getProducto().getId().equals(otAux.getProducto().getId())){
							otAux.setNumeroIT(ot.getNumeroIT());
						}
					}
				}
				for (OrdenTrabajo ordenTrabajo : ots) {
					filterOT.setIdGuiaAgrupada(guia.getId());	
					filterOT.setIdOT(ordenTrabajo.getId());
					Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
					bitacora.setNumeroIT(ordenTrabajo.getNumeroIT());
					bitacoraDAO.updateEstadoBitacora(bitacora);
				}
			}
			if (guiaDAO.update(guia).equals(0)){
				throw new SSTException("Error al actualizar el registro de Guia");
			}
			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}	
	
//	public Guia confirmarGuiaDespachoAgrupada (Guia guia,Usuario usuario,Ubicacion ubicacion)throws Exception{
//		try {
//			guia = guiaDAO.get(guia.getId());
//			Ubicacion destino;
//			if (!guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA))
//				throw new SSTException("La guía no se encuentra en estado. POR CONFIRMAR RECEPCIÓN");
//			
//			guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));  
//			guia.setVigente(true);
//			if (guiaDAO.update(guia).equals(0))
//				throw new SSTException("Error al actualizar el registro");
//			
//			String clasificacionOT= "";
//			destino = ubicacionDAO.get(guia.getDestino().getId());
//			if(destino == null){
//				destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
//			}
//			FilterOT filterOT = new FilterOT();
//			filterOT.setIdGuiaAgrupada(guia.getId());
//			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
//			
//			for (OrdenTrabajo ordenTrabajo : ots) {
//				clasificacionOT = ordenTrabajo.getClasificacion().getCodigo();
//			}
//			
//			if((destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE)) || (destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
//				guia.setProcesadoOW(true);
//				guia.setNumeroTSTO(oWService.createTSTO(guia.getId(),oWService.convertUbicaciontoOW(clasificacionOT),destino.getCodigoOW()));
//				
//				if (guiaDAO.updateEstado(guia).equals(0))
//					throw new SSTException("Error al actualizar el registro");
//				
//				guia.setNumeroTO(oWService.receiveTO(guia.getId()));
//				
//				if (guiaDAO.updateEstado(guia).equals(0))
//					throw new SSTException("Error al actualizar el registro");
//			} else {
//				for (OrdenTrabajo ordenTrabajo : ots) {
//					filterOT.setIdGuiaAgrupada(guia.getId());	
//					filterOT.setIdOT(ordenTrabajo.getId());
//					Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
//					bitacora.setNumeroIT(oWService.moveIT(ordenTrabajo, ubicacion.getId(), oWService.convertUbicaciontoOW(clasificacionOT), Constants.UBICACION_STA_OW));
//					bitacoraDAO.updateEstadoBitacora(bitacora);
//				}
//			}
//			return guia;
//		} catch (SSTException e) {
//			throw e;
//		} catch (Exception e) {
//			log.error(e , e);
//			throw e;
//		}
//	}
	
	
	private void updateUbicacionAccesoriosGuiaDespacho(Guia guia,OrdenTrabajo ordenTrabajo, Ubicacion destino, Ubicacion origen)throws Exception {
		try {
			FilterAccesorio filterAccesorio = new FilterAccesorio();
			filterAccesorio.setIdOT(ordenTrabajo.getId());
			filterAccesorio.setIdUbicacion(origen.getId());
			List<Accesorio> accesorios = accesorioDAO.listAccesoriosByOT(ordenTrabajo.getId());
			for (Accesorio accesorio : accesorios) {
				if (accesorio.getUbicacion().getId().equals(origen.getId())) {
					accesorio.setUbicacion(destino);
					accesorioDAO.updateUbicacionAccesorio(accesorio);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Guia reEmitirGuiaDespachoAgrupada(DespachoInterno despachoInterno, Guia guia, Usuario usuario, Ubicacion origen) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
			
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			
			Guia guiaNueva = saveGuiaDespachoParaAgrupacion(despachoInterno,origen, usuario,despachoInterno.getDestino(),true);
			
			for (OrdenTrabajo ordenTrabajo : ots) {
				filterOT.setIdOT(ordenTrabajo.getId());
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);

				Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(null);
				bitacora.setGuia(guiaNueva);
				bitacora.setUbicacion(origen);
				
				bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalida(bitacora);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);
				
				updateUbicacionAccesoriosGuiaDespacho(guia,ordenTrabajo, guia.getOrigen(), ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));
				updateEstadoFromDespachoToBackEstado(despachoInterno);
				
			}
			
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_DESACTIVADA);
			guia.setVigente(false);
			
			guiaDAO.updateEstado(guia);

			return guiaNueva;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Guia reEmitirGuiaDespachoAgrupadaHaciaDestino(DespachoInterno despachoInterno, Guia guia, Usuario usuario, Ubicacion origen) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			
			FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
			filterDespacho.setIdDespacho(despachoInterno.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTByIdGuiaAgrupada(guia.getId());
			
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			
			Guia guiaNueva = saveGuiaDespachoParaAgrupacion(despachoInterno,origen, usuario,despachoInterno.getDestino(),true);
			guiaNueva.setDespachoInternoCamion(despachoInternoCamionDAO.get(despachoInterno));
			guiaNueva.setTransportista(guia.getTransportista());
//			guiaNueva.setNumero(guia.getNumero());
			guiaDAO.update(guiaNueva);
			
			for (OrdenTrabajo ordenTrabajo : ots) {
				filterOT.setIdOT(ordenTrabajo.getId());
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);

				Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(null);
				bitacora.setGuia(guiaNueva);
				bitacora.setUbicacion(origen);
				
				bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalida(bitacora);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);
				
				updateUbicacionAccesoriosGuiaDespacho(guia,ordenTrabajo, guia.getOrigen(), ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));
				updateEstadoFromDespachoToBackEstado(despachoInterno);
				
			}
			
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_DESACTIVADA);
			guia.setVigente(false);
			
			guiaDAO.updateEstado(guia);

			return guiaNueva;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void revisarOnSalaProveedores(OrdenTrabajo oT,Ubicacion ubicacion,Boolean masivo) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdOT(oT.getId());
			DespachoInterno despachoInterno = despachoInternoDAO.getDespachoByIdOT(oT.getId());
			ordenTrabajoDAO.updateRevisionSP(oT);
			movimientosInternosService.cerrarYCrearBitacoraInterna(oT.getId(), Constants.BITACORA_INTERNA_OT_REVISADA_SALA_PROVEEDORES, null);
			BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getBitacoraInternaById(oT.getId());
			bitacoraInterna.setNumeroIT(oWService.moveIT(oT, ubicacion.getId(), OWConstants.UBICACION_FDP,oWService.convertUbicaciontoOW(oT.getClasificacion().getCodigo())));
			bitacoraInternaDAO.updateNumeroItByIdBitacoraInterna(bitacoraInterna);
			this.terminarRecepcionDespachoInternoOnSP(despachoInterno,ubicacion,masivo);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Boolean terminarRecepcionDespachoInternoOnSP(DespachoInterno despachoInterno,Ubicacion ubicacion,Boolean masivo) throws Exception{
		try {
			FilterDespachoInterno filterDespachoInterno = new FilterDespachoInterno();
			filterDespachoInterno.setIdDespacho(despachoInterno.getId());
			
			List<OrdenTrabajo> oTs = ordenTrabajoDAO.listOtByDespacho(filterDespachoInterno);
			for(OrdenTrabajo oT:oTs){
				if( despachoInterno.getEstado().getId().equals(Constants.ESTADO_DESPACHO_SP_TERMINADO) || oT.getClasificacion().getCodigo().equals(Constants.UBICACION_RDEFP)){
					return false;
				}
			}
			
			despachoInterno.setEstado(new Estado());
			despachoInterno.getEstado().setId(Constants.ESTADO_DESPACHO_SP_TERMINADO);
			despachoInterno.setFechaCierre(utilDAO.getDate());
			despachoInternoDAO.updateEstadoByDespachoInterno(despachoInterno);
			
			return true;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void revisarMasivoOnSalaProveedores(List<OrdenTrabajo> oTs,Ubicacion ubicacion) throws Exception {
		try {
			List<OrdenTrabajo> otAgrupadas = groupOtForITAgrupada(oTs, ubicacion);
			String clasificacion = oTs.get(0).getClasificacion().getCodigo();
			
			if(validaStockAgrupadoInSalaProveedores(ubicacion,OWConstants.UBICACION_FDP,otAgrupadas)){
				log.info("inicio Validacion");
				for(OrdenTrabajo oT : oTs){
					FilterOT filterOT = new FilterOT();
					filterOT.setIdOT(oT.getId());
					DespachoInterno despachoInterno = despachoInternoDAO.getDespachoByIdOT(oT.getId());
					if (despachoInterno == null){
						throw new SSTException("La OT " + oT.getId() + " no tiene despachos abiertos. No se han generado movimientos en el sistema.");
					}
				}
				for(OrdenTrabajo ot : otAgrupadas){
					ot.setNumeroIT(oWService.moveITAgrupada(ot, ubicacion.getId(), OWConstants.UBICACION_FDP, oWService.convertUbicaciontoOW(clasificacion)));
					for(OrdenTrabajo otAux : oTs){
						if(ot.getProducto().getId().equals(otAux.getProducto().getId())){
							otAux.setNumeroIT(ot.getNumeroIT());
						}
					}
				}
				for(OrdenTrabajo oT : oTs){
					FilterOT filterOT = new FilterOT();
					filterOT.setIdOT(oT.getId());
					DespachoInterno despachoInterno = despachoInternoDAO.getDespachoByIdOT(oT.getId());
					ordenTrabajoDAO.updateRevisionSP(oT);
					movimientosInternosService.cerrarYCrearBitacoraInterna(oT.getId(), Constants.BITACORA_INTERNA_OT_REVISADA_SALA_PROVEEDORES, null);
					BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getBitacoraInternaById(oT.getId());
					bitacoraInterna.setNumeroIT(oT.getNumeroIT());
					bitacoraInternaDAO.updateNumeroItByIdBitacoraInterna(bitacoraInterna);
					this.terminarRecepcionDespachoInternoOnSP(despachoInterno,ubicacion,true);
				}
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Boolean validaStockAgrupadoInSalaProveedores(Ubicacion origen, String ubicacion, List<OrdenTrabajo> ots) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		Boolean error = false;
		for (OrdenTrabajo ot : ots) {
			try {
				if(!oWService.validaStock(origen, ubicacion, ot.getProducto(), ot.getProducto().getCantidad().intValue())) {
					error = true;
					stringBuilder.append("No hay stock disponible para el producto " + ot.getProducto().getId() + " " + ot.getProducto().getDescripcion() + " en el centro de costo " + origen.getId() + " " + origen.getNombre() + " ubicación " + ubicacion + ".\n" );
				}
			} catch (SSTException e) {
				error = true;
				stringBuilder.append("No hay stock disponible para el producto " + ot.getProducto().getId() + " " + ot.getProducto().getDescripcion() + " en el centro de costo " + origen.getId() + " " + origen.getNombre() + " ubicación " + ubicacion + ".\n" );
			}
		}
		if (!error) {
			return true;
		} 
		throw new SSTException(stringBuilder.toString());
	}
	
	public List<OrdenTrabajo> groupOtForITAgrupada(List<OrdenTrabajo> oTs,Ubicacion ubicacion) throws Exception {
		try {
			Producto producto = new Producto();
			producto.setId(0);
			List<OrdenTrabajo> otAgrupadas = new ArrayList<OrdenTrabajo>();
			OrdenTrabajo ordenTrabajo = null;

			Collections.sort(oTs, new OrdenTrabajoByProductoComparator());
			
			for(OrdenTrabajo ot : oTs){
				if(!producto.getId().equals(ot.getProducto().getId())){
					ordenTrabajo = new OrdenTrabajo();
					ordenTrabajo.setProducto(ot.getProducto());
					ordenTrabajo.getProducto().setCantidad(1L);
					otAgrupadas.add(ordenTrabajo);
					producto.setId(ot.getProducto().getId());
				} else {
					ordenTrabajo.getProducto().setCantidad(ordenTrabajo.getProducto().getCantidad() + 1);
				}
			}
			return otAgrupadas;

		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<Indicador> getIndicadoresEjecutivaFF(List<Indicador> indicadores) throws Exception{
		try {
			List<Indicador> indicadoresAux = new ArrayList<Indicador>();
			for(Indicador indicador : indicadores){ 
				indicador = indicadorDAO.getIndicadorById(indicador.getId());
				getIndicadoresForParametro(indicador);
				indicadoresAux.add(indicador);
			}
			return indicadoresAux;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listOTIndicadorEjecutivaFF(FilterIndicador filter, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			
			filter.setIndicador(indicadorDAO.getIndicadorById(filter.getIndicador().getId()));
			
			filter.setIndicador(getIndicadoresForParametro(filter.getIndicador()));
			listRange.setTotal(filter.getIndicador().getValor().intValue());
			
			listRange.setRows(this.listOTIndicadorForParametro(filter, gridControl));
			
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<OrdenTrabajo> listOTIndicadorForParametro(FilterIndicador filter, GridControl gridControl) throws Exception{
		try {
			List<OrdenTrabajo> ordenesTrabajo = new ArrayList<OrdenTrabajo>();
			if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_NO_DESPACHO_TIENDA)){
				ordenesTrabajo = semaforoDAO.listOtOtNotDespachadasFromSucursal(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_NO_RECIBIDA_BODEGA)){
				ordenesTrabajo = semaforoDAO.listOtNotRecibidasInBodega(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_FALTANTE)){
				ordenesTrabajo = semaforoDAO.listOtPendientesWithRecepcionFaltante(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_INCOMPLETA)){
				ordenesTrabajo = semaforoDAO.listOtPendientesWithRecepcionIncompleta(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_FF_NO_REVISION_SP)){
				ordenesTrabajo = semaforoDAO.ListOtRecibidasFFAndNotRevisadasInSP(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_ENVIADA_NO_RECIBIDA_SP)){
				ordenesTrabajo = semaforoDAO.listOtNotRecibidasInSP(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_CP_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionCPAndNotDesapachaFromSP(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_RP_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionRPAndNotDesapachaFromSP(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_DA_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionDAAndNotDesapachaFromSP(filter, gridControl);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_AV_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionAVAndNotDesapachaFromSP(filter, gridControl);
			}
			return ordenesTrabajo;
		}	
		catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
		
	public List<OrdenTrabajo> listOTIndicadorForParametro(FilterIndicador filter) throws Exception{
		try {
			List<OrdenTrabajo> ordenesTrabajo = new ArrayList<OrdenTrabajo>();
			if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_NO_DESPACHO_TIENDA)){
				ordenesTrabajo = semaforoDAO.listOtOtNotDespachadasFromSucursal(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_NO_RECIBIDA_BODEGA)){
				ordenesTrabajo = semaforoDAO.listOtNotRecibidasInBodega(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_FALTANTE)){
				ordenesTrabajo = semaforoDAO.listOtPendientesWithRecepcionFaltante(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_INCOMPLETA)){
				ordenesTrabajo = semaforoDAO.listOtPendientesWithRecepcionIncompleta(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_FF_NO_REVISION_SP)){
				ordenesTrabajo = semaforoDAO.ListOtRecibidasFFAndNotRevisadasInSP(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_ENVIADA_NO_RECIBIDA_SP)){
				ordenesTrabajo = semaforoDAO.listOtNotRecibidasInSP(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_CP_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionCPAndNotDesapachaFromSP(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_RP_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionRPAndNotDesapachaFromSP(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_DA_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionDAAndNotDesapachaFromSP(filter);
			}else if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_AV_NO_DESPACHADA_SP)){
				ordenesTrabajo = semaforoDAO.listOtWithClasificasionAVAndNotDesapachaFromSP(filter);
			}
			return ordenesTrabajo;
		}	
		catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private Indicador getIndicadoresForParametro(Indicador indicador) throws Exception{
		try {
			if(indicador.getGrupo().equals(Constants.INDICADOR_OT_NO_DESPACHO_TIENDA)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtNotDespachadasFromSucursal(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_NO_RECIBIDA_BODEGA)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtNotRecibidasInBodega(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_GUIA_EMITIDA_TRANSPORTISTA)){
				Long valorAux = null;
				valorAux = semaforoDAO.getGuiasEmitidasToTransportistas(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_FALTANTE)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtPendientesWithRecepcionFaltante(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_RECEPCION_INCOMPLETA)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtPendientesWithRecepcionIncompleta(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_FF_NO_REVISION_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtRecibidasFFAndNotRevisadasInSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_ENVIADA_NO_RECIBIDA_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtNotRecibidasInSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_CP_NO_DESPACHADA_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtWithClasificasionCPAndNotDesapachaFromSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_RP_NO_DESPACHADA_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtWithClasificasionRPAndNotDesapachaFromSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_DA_NO_DESPACHADA_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtWithClasificasionDAAndNotDesapachaFromSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				indicador = validaSemaforo(indicador);
			}else if(indicador.getGrupo().equals(Constants.INDICADOR_OT_CLASIFICACION_AV_NO_DESPACHADA_SP)){
				Long valorAux = null;
				valorAux = semaforoDAO.getOtWithClasificasionAVAndNotDesapachaFromSP(indicador);
				if(valorAux == null){
					indicador.setValor(0D);
				}else{
					indicador.setValor(valorAux.doubleValue());
				}
				
				indicador = validaSemaforo(indicador);
			}
			return indicador;
		}	
		catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	
	public ListRange listGuiaIndicadorEjecutivaFF(FilterIndicador filter, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			
			
			filter.setIndicador(indicadorDAO.getIndicadorById(filter.getIndicador().getId()));			
			filter.setIndicador(getIndicadoresForParametro(filter.getIndicador()));
			
			listRange.setTotal(filter.getIndicador().getValor().intValue());
			
			listRange.setRows(this.listGuiaIndicadorForParametro(filter, gridControl));
			
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private List<Guia> listGuiaIndicadorForParametro(FilterIndicador filter, GridControl gridControl) throws Exception{
		try {
			List<Guia> guias = new ArrayList<Guia>();
			if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_GUIA_EMITIDA_TRANSPORTISTA)){
				guias = semaforoDAO.listGuiasEmitidasToTransportistas(filter, gridControl);
			}
			return guias;
		}	
		catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<Guia> listGuiaIndicadorForParametro(FilterIndicador filter) throws Exception{
		try {
			List<Guia> guias = new ArrayList<Guia>();
			if(filter.getIndicador().getGrupo().equals(Constants.INDICADOR_GUIA_EMITIDA_TRANSPORTISTA)){
				guias = semaforoDAO.listGuiasEmitidasToTransportistas(filter);
				}
			return guias;
		}	
		catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	private Indicador validaSemaforo(Indicador indicador)throws Exception{
		if(indicador.getValor() == null){
			indicador.setValor(0d);
		}
		return indicador;
	}
	
	public List<Sello> getSellosByIdGuia(Long id)throws Exception{
		try {
			return selloDAO.getSellosByIdGuia(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	

	public ListRange listOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT,GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
//			filterOT.setRoles(roles);				
			listRange.setRows(ordenTrabajoDAO.listOtByIdGuiaAndClasificacionAvOrDa(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getOtByIdGuiaAndClasificacionAvOrDa(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT,GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
//			filterOT.setRoles(roles);				
			listRange.setRows(ordenTrabajoDAO.listOtByIdGuiaAndClasificacionCpOrRp(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getOtByIdGuiaAndClasificacionCpOrRp(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
		
	public ListRange listOtByIdGuia(FilterOT filterOT,GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
//			filterOT.setRoles(roles);				
			listRange.setRows(ordenTrabajoDAO.listOtByIdGuia(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getOtByIdGuia(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT)throws Exception{
		try {
			 List<CheckForFlexigrid> lst = new ArrayList<CheckForFlexigrid>();
			lst = ordenTrabajoDAO.listAllOtByIdGuiaAndClasificacionAvOrDa(filterOT);
			return lst;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT)throws Exception{
		try {
			 List<CheckForFlexigrid> lst = new ArrayList<CheckForFlexigrid>();
			lst = ordenTrabajoDAO.listAllOtByIdGuiaAndClasificacionCpOrRp(filterOT);
			return lst;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void generarSC(List<OrdenTrabajo> ots,DespachoInterno despacho,Guia guia, Usuario usuario,Ubicacion ubicacion) throws Exception{
		if(despacho != null){
			generarSCNormal(ots, despacho, guia, usuario, ubicacion);
		} else {
			generarSCTransportista(ots, guia, usuario, ubicacion);
		}
	}
	
//	private void generarSCNormal(List<OrdenTrabajo> ots,DespachoInterno despacho,Guia guia, Usuario usuario,Ubicacion ubicacion) throws Exception{
//		try {
//			Ubicacion destinoCC = new Ubicacion();
//			String origenUbicacion = "";
//			guia = guiaDAO.get(guia.getId());
//			guia.setTransportista(transportistaDAO.getTransportistaById(guia.getTransportista().getId()));
//			
//			PeticionDocumento peticionDocumento = new PeticionDocumento();
//			peticionDocumento.setTipo(Constants.PETICION_DE_FACTURA);
//			peticionDocumento.setFacturar(despacho.getDestino().getId());
//			
//			destinoCC = ubicacionDAO.get(peticionDocumento.getFacturar());
//			if(destinoCC == null){
//				destinoCC = proveedorDAO.getProveedorById(peticionDocumento.getFacturar());
//				destinoCC.setRut(destinoCC.getId().toString());
//			}
//			peticionDocumento.setVigente(true);
//			peticionDocumentoDAO.save(peticionDocumento);
//			
//			for(OrdenTrabajo ot : ots){
//				OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());
//				ot.setProducto(ordenTrabajo.getProducto());
//				PeticionDocumentoDetalle peticionDetalle = new PeticionDocumentoDetalle();
//				peticionDetalle.setPeticionDocumento(peticionDocumento);
//				peticionDetalle.setOrdenTrabajo(ot);
//				peticionDocumentoDetalleDAO.save(peticionDetalle);
//			}
//			
//			try {
//				Ubicacion origenCC = new Ubicacion();
//				
//				if(destinoCC.getTipo() != null && (destinoCC.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE) || destinoCC.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
//					origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_10013);
//					origenUbicacion = destinoCC.getCodigoOW();
//					peticionDocumento.setNumero(oWService.moveSC(peticionDocumento,origenCC.getId(),origenUbicacion,Utils.getIdFromRun(destinoCC.getRut()),guia.getNumero(),ubicacion));
//				} else {
//					origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
//					origenUbicacion = Constants.UBICACION_SERVICIO_TECNICO_AUTORIZADO;
//					
//					if(Utils.getIdFromRun(destinoCC.getRut()) == null){
//						peticionDocumento.setNumero(oWService.moveSC(peticionDocumento,origenCC.getId(),origenUbicacion,Long.parseLong(destinoCC.getRut()),guia.getNumero().longValue(),ubicacion));
//					} else {
//						peticionDocumento.setNumero(oWService.moveSC(peticionDocumento,origenCC.getId(),origenUbicacion,Utils.getIdFromRun(destinoCC.getRut()),guia.getNumero(),ubicacion));
//					}
//				} 
//				
//				peticionDocumentoDAO.update(peticionDocumento);
//				for(OrdenTrabajo ot : ots){
//					Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
//					bitacora.setFechaSalida(utilDAO.getDate());
//					bitacoraDAO.updateFechaSalida(bitacora);
//					
//					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_PETICION_FACTURA));
//					ordenTrabajoDAO.updateCerrarOT(ot); 
//					
//					Gestion gestion = new Gestion();
//					gestion.setUsuario(usuario);
//					gestion.setOt(ot);
//					gestion.setGestion("cierra OT N°"+ ot.getId()+" por petición de factura");
//					gestionesDAO.saveGestion(gestion);
//				}
//			} catch (Exception e) {
//				peticionDocumento.setVigente(false);
//				peticionDocumentoDAO.update(peticionDocumento);
//				throw e;
//			}
//			
//		} catch (SSTException e) {
//			throw e;
//		} catch (Exception e) {
//			log.error(e , e);
//			throw e;
//		}
//	}

	private void generarSCNormal(List<OrdenTrabajo> ots,DespachoInterno despacho,Guia guia, Usuario usuario,Ubicacion ubicacion) throws Exception{
		try {
			PeticionDocumento peticionDocumento = new PeticionDocumento();
			List<PeticionDocumento> peticionesDocumentos = new ArrayList<PeticionDocumento>();
			Ubicacion destinoCC = new Ubicacion();
			String origenUbicacion = "";
			guia = guiaDAO.get(guia.getId());
			guia.setTransportista(transportistaDAO.getTransportistaById(guia.getTransportista().getId()));
			
			peticionDocumento = this.savePeticionDocumento(despacho);
			peticionesDocumentos.add(peticionDocumento);
			destinoCC = ubicacionDAO.get(peticionDocumento.getFacturar());
			if(destinoCC == null){
				destinoCC = proveedorDAO.getProveedorById(peticionDocumento.getFacturar());
				destinoCC.setRut(destinoCC.getId().toString());
			}
			
			for(OrdenTrabajo ordenTrabajo : ots){
//				Integer recuperacion = ordenTrabajo.getRecuperacion();
				OrdenTrabajo ot = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
				ordenTrabajo.setRecuperacion(ot.getRecuperacion());
//				ordenTrabajo.setRecuperacion(recuperacion);
			}
			Collections.sort(ots, new OrdenTrabajoByProductoComparator());
			
			Integer productos = 0;
			Producto productoAnterior = new Producto();
			productoAnterior.setId(0);
			
			for (OrdenTrabajo ot : ots) {
				if (!productoAnterior.getId().equals(ot.getProducto().getId())) {
					productos ++;
					productoAnterior = ot.getProducto();
					if (productos.equals(11)){
						productos = 0;
						peticionDocumento = this.savePeticionDocumento(despacho);
						peticionesDocumentos.add(peticionDocumento);
					}
				}
					PeticionDocumentoDetalle peticionDetalle = new PeticionDocumentoDetalle();
					peticionDetalle.setPeticionDocumento(peticionDocumento);
					peticionDetalle.setOrdenTrabajo(ot);
					peticionDocumentoDetalleDAO.save(peticionDetalle);
			}
			
			try {
				for(PeticionDocumento peticion : peticionesDocumentos){
					Ubicacion origenCC = new Ubicacion();
					
					if(destinoCC.getTipo() != null && (destinoCC.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE) || destinoCC.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
						origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_10013);
						origenUbicacion = destinoCC.getCodigoOW();
						peticionDocumento.setNumero(oWService.moveSC(peticion,origenCC.getId(),origenUbicacion,Utils.getIdFromRun(destinoCC.getRut()),guia.getNumero(),ubicacion));
					} else {
						origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
						origenUbicacion = Constants.UBICACION_SERVICIO_TECNICO_AUTORIZADO;
						
						if(Utils.getIdFromRun(destinoCC.getRut()) == null){
							peticion.setNumero(oWService.moveSC(peticion,origenCC.getId(),origenUbicacion,Long.parseLong(destinoCC.getRut()),guia.getNumero().longValue(),ubicacion));
						} else {
							peticion.setNumero(oWService.moveSC(peticion,origenCC.getId(),origenUbicacion,Utils.getIdFromRun(destinoCC.getRut()),guia.getNumero(),ubicacion));
						}
					} 
					peticionDocumentoDAO.update(peticion);
				}
				for(OrdenTrabajo ot : ots){
					Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
					bitacora.setFechaSalida(utilDAO.getDate());
					bitacoraDAO.updateFechaSalida(bitacora);
					
					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_PETICION_FACTURA));
					ordenTrabajoDAO.updateCerrarOT(ot); 
					
					Gestion gestion = new Gestion();
					gestion.setUsuario(usuario);
					gestion.setOt(ot);
					gestion.setGestion("cierra OT N°"+ ot.getId()+" por petición de factura");
					gestionesDAO.saveGestion(gestion);
				}
			} catch (Exception e) {
				peticionDocumento.setVigente(false);
				peticionDocumentoDAO.update(peticionDocumento);
				throw e;
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private PeticionDocumento savePeticionDocumento(DespachoInterno despacho) throws Exception{
		try {
			PeticionDocumento peticionDocumento = new PeticionDocumento();
			peticionDocumento.setTipo(Constants.PETICION_DE_FACTURA);
			peticionDocumento.setFacturar(despacho.getDestino().getId());
			peticionDocumento.setVigente(true);
			peticionDocumentoDAO.save(peticionDocumento);
			return peticionDocumento;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private void generarSCTransportista(List<OrdenTrabajo> ots, Guia guia, Usuario usuario, Ubicacion ubicacion) throws Exception{
		try {
			Transportista destinoCC = new Transportista();
			String origenUbicacion = "";
			guia = guiaDAO.get(guia.getId());
			
			if(guia.getVigente().equals(false)){
				throw new SSTException("Las ordenes de trabajo de esta guia ya fueron facturadas");
			}
			
			guia.setTransportista(transportistaDAO.getTransportistaById(guia.getTransportista().getId()));
			
			PeticionDocumento peticionDocumento = new PeticionDocumento(); 
			peticionDocumento.setTipo(Constants.PETICION_DE_FACTURA);
			peticionDocumento.setFacturar(guia.getTransportista().getId());
			peticionDocumento.setVigente(true);
			peticionDocumentoDAO.save(peticionDocumento);
			
			destinoCC = guia.getTransportista(); 
			
			for(OrdenTrabajo ot : ots){
				OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());
				ot.setProducto(ordenTrabajo.getProducto());
				PeticionDocumentoDetalle peticionDetalle = new PeticionDocumentoDetalle();
				peticionDetalle.setPeticionDocumento(peticionDocumento);
				peticionDetalle.setOrdenTrabajo(ot);
				peticionDetalle.getOrdenTrabajo().setRecuperacion(100);
				peticionDocumentoDetalleDAO.save(peticionDetalle);
			}
			
			try {
				Ubicacion origenCC = new Ubicacion();
				origenUbicacion = Constants.UBICACION_RTR_OW;
				origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
				peticionDocumento.setNumero(oWService.moveSC(peticionDocumento,origenCC.getId(),origenUbicacion,Utils.getIdFromRun(destinoCC.getRut()),guia.getNumero(),ubicacion));
				peticionDocumentoDAO.update(peticionDocumento);
				guia.setVigente(false);
				guiaDAO.update(guia);
				for(OrdenTrabajo ot : ots){
					Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
					bitacora.setFechaSalida(utilDAO.getDate());
					bitacoraDAO.updateFechaSalida(bitacora);
					
					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_PETICION_FACTURA));
					ordenTrabajoDAO.updateCerrarOT(ot); 
					
					Gestion gestion = new Gestion();
					gestion.setUsuario(usuario);
					gestion.setOt(ot);
					gestion.setGestion("cierra OT N°"+ ot.getId()+" por petición de factura");
					gestionesDAO.saveGestion(gestion);
				}
			} catch (Exception e) {
				peticionDocumento.setVigente(false);
				peticionDocumentoDAO.update(peticionDocumento);
				throw e;
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}

	}
	
	public void generarDM(List<OrdenTrabajo> ots,DespachoInterno despacho,Integer numeroGuia, String origen, Usuario usuario,Ubicacion ubicacion) throws Exception{
		try {
			PeticionDocumento peticionDocumento = new PeticionDocumento(); 
			peticionDocumento.setTipo(Constants.PETICION_DE_NOTA_CREDITO);
			peticionDocumento.setFacturar(despacho.getDestino().getId());
			peticionDocumento.setVigente(true);
			peticionDocumentoDAO.save(peticionDocumento);
			
			for(OrdenTrabajo ot : ots){
				OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());
				ot.setProducto(ordenTrabajo.getProducto());
				
				PeticionDocumentoDetalle peticionDetalle = new PeticionDocumentoDetalle();
				peticionDetalle.setPeticionDocumento(peticionDocumento);
				peticionDetalle.setOrdenTrabajo(ot);
				peticionDocumentoDetalleDAO.save(peticionDetalle);
			}
			
			try {
				Ubicacion origenCC = new Ubicacion();
				String origenUbicacion;
				Ubicacion destinoCC = ubicacionDAO.get(despacho.getDestino().getId());
				if(destinoCC == null){
					destinoCC = proveedorDAO.getProveedorById(despacho.getDestino().getId());
				}
				
				if(destinoCC.getTipo() != null && (destinoCC.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE) || destinoCC.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA))){
					origenCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_10013);
					origenUbicacion = destinoCC.getCodigoOW();
				} else {
					origenCC = ubicacionDAO.get(despacho.getOrigen().getId());
					origenUbicacion= Constants.UBICACION_SERVICIO_TECNICO_AUTORIZADO;
				} 
				peticionDocumento.setNumero(oWService.moveDM(peticionDocumento,numeroGuia,origenCC,origenUbicacion,destinoCC));
				peticionDocumentoDAO.update(peticionDocumento);
				
				for(OrdenTrabajo ot : ots){
					Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
					bitacora.setFechaSalida(utilDAO.getDate());
					bitacoraDAO.updateFechaSalida(bitacora);
					
					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_PETICION_NOTA_CREDITO));
					ordenTrabajoDAO.updateCerrarOT(ot);
					
					Gestion gestion = new Gestion();
					gestion.setUsuario(usuario);
					gestion.setOt(ot);
					gestion.setGestion("cierra OT N°"+ ot.getId()+" por petición de nota de credito");
					gestionesDAO.saveGestion(gestion);
				}
			} catch (Exception e) {
				peticionDocumento.setVigente(false);
				peticionDocumentoDAO.update(peticionDocumento);
				throw e;
			} 
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void cerrarOTByUsuario(List<OrdenTrabajo> ots,Usuario usuario) throws Exception{
		try {
			for(OrdenTrabajo ot : ots){
				Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
				bitacora.setFechaSalida(utilDAO.getDate());
				bitacoraDAO.updateFechaSalida(bitacora);
				
				ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_USUARIO));
				ordenTrabajoDAO.updateCerrarOT(ot);
				
				Gestion gestion = new Gestion();
				gestion.setUsuario(usuario);
				gestion.setOt(ot);
				gestion.setGestion("cierra la OT N°"+ ot.getId()+"el usuario"+ usuario.getNombreCompleto());
				gestionesDAO.saveGestion(gestion);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void saveBitacoraHaciaDestino(List<OrdenTrabajo> ots,Guia guia,Clasificacion clasificacion,Ubicacion ubicacion)throws Exception{
		try {
			List<OrdenTrabajo> otAgrupadas = groupOtForITAgrupada(ots, ubicacion);
			
			for(OrdenTrabajo ot : otAgrupadas){
				ot.setNumeroIT(oWService.moveITAgrupada(ot, ubicacion.getId(),Constants.UBICACION_STA_OW,oWService.convertUbicaciontoOW(clasificacion.getCodigo())));
				for(OrdenTrabajo otAux : ots){
					if(ot.getProducto().getId().equals(otAux.getProducto().getId())){
						otAux.setNumeroIT(ot.getNumeroIT());
					}
				}
			}
			
			for(OrdenTrabajo ot : ots){
				ot = ordenTrabajoDAO.getOTById(ot.getId());
				ot.setClasificacion(clasificacion);
				ordenTrabajoDAO.updateOTRecepcion(ot);// revisar numeroserie, estado y la clasificacion a actualizar
				
				Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
				bitacora.setFechaSalida(utilDAO.getDate());
				bitacoraDAO.updateFechaSalida(bitacora);
				
				saveBitacoraHaciaDestino(ot,guiaDAO.get(guia.getId()),clasificacion);
				
				BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getUltimaBitacoraInternaByIdOT(ot.getId());
				bitacoraInterna.setNumeroIT(ot.getNumeroIT());
				bitacoraInternaDAO.updateNumeroItByIdBitacoraInterna(bitacoraInterna);
				
				if(clasificacion.getCodigo().equals(Constants.APTO_VENTA)){
					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_ENVIADA_A_AV));
				}else {
					ot.setEstado(estadoDAO.getEstadoById(Constants.OT_ENVIADA_A_DA));
				}
				ordenTrabajoDAO.updateEstadoOT(ot);
			} 
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void saveBitacoraHaciaDestino(OrdenTrabajo ot,Guia guia,Clasificacion clasificacion)throws Exception{
		try {
			Ubicacion destino = ubicacionDAO.get(guia.getDestino().getId()); 
			if(destino == null){
				destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
			}
			
			Bitacora b1 = new Bitacora();
			b1.setOrdenTrabajo(ot);
			b1.setFechaEntrada(utilDAO.getDate());
			b1.setFechaSalida(utilDAO.getDate());
			if(destino.getTipo() != null && !destino.getTipo().equals("PROV")){
				b1.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_FF));
			}else {
				b1.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_PROVEEDOR_LUEGO_A_CAMION_A_FF));
			}
			
			bitacoraDAO.save(b1);
			
			Bitacora b2 = new Bitacora();
			b2.setEstado(new Estado());
			b2.setOrdenTrabajo(ot);
			b2.setFechaEntrada(utilDAO.getDate());
			b2.setFechaSalida(utilDAO.getDate());
			b2.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CAMION_LUEGO_A_FF));
			bitacoraDAO.save(b2);
			
			Bitacora b3 = new Bitacora();
			b3.setEstado(new Estado());
			b3.setOrdenTrabajo(ot);
			b3.setFechaEntrada(utilDAO.getDate());
			b3.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FALLAS_DE_FABRICACION));
			b3.setUbicacion(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
			bitacoraDAO.save(b3);
			
			BitacoraInterna bitacoraInterna = new BitacoraInterna();
			bitacoraInterna.setBitacora(bitacoraDAO.getBitacoraUltimaUbicacionOT(ot.getId()));
			bitacoraInterna.setFechaInicio(utilDAO.getDate());
			bitacoraInterna.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(Constants.CODIGO_BODEGA_FALLA_FABRICACION));
			bitacoraInterna.setOrdenTrabajo(ot);
			bitacoraInterna.setClasificacion(clasificacion);
			bitacoraInterna.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_FALLA_FABRICACION));
			bitacoraInterna.setDespachoInterno(new DespachoInterno());
			bitacoraInterna.setRecepcion(new Recepcion());
			bitacoraInternaDAO.saveBitacoraInterna(bitacoraInterna);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Integer getExisteNumero(Long numero)throws Exception{
		try {
			return guiaDAO.getExisteNumero(numero); 
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listInventariosByFilter(FilterInventario filterInventario, GridControl gridControl) throws Exception{
		try {
			filterInventario.setOrderBy(gridControl.getOrderBy());
			filterInventario.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(inventarioDAO.list(filterInventario,gridControl));
			listRange.setTotal(inventarioDAO.getTotalInventarios(filterInventario));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange selectInventarioResumen(Integer idInventarioUbicacion, GridControl gridControl) throws Exception{
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(inventarioDAO.selectInventarioResumen(idInventarioUbicacion,gridControl));
			listRange.setTotal(inventarioDAO.getTotalInventarioResumen(idInventarioUbicacion));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer getTotalInventarioResumen(Integer idInventarioUbicacion) throws Exception {
		try {
			return inventarioDAO.getTotalInventarioResumen(idInventarioUbicacion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listUbicacionesInternasToInventario(GridControl gridControl) throws Exception{
		try {
			FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
			filterUbicacionInterna.setOrderBy(gridControl.getOrderBy());
			filterUbicacionInterna.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(ubicacionInternaDAO.listToInventario(filterUbicacionInterna,gridControl));
			listRange.setTotal(ubicacionInternaDAO.getTotalToInventario());
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllUbicacionesInternasToInventario() throws Exception {
		try {
			return ubicacionInternaDAO.listAllToInventario();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveInventario(List<UbicacionInterna> ubicaciones, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			Inventario inventario = new Inventario();
			inventario.setUsuarioCreacion(usuario);
			inventario.setVigente(true);
			inventario.setUbicacion(ubicacion);
			inventarioDAO.save(inventario);
			
			InventarioUbicacion inventarioUbicacion = new InventarioUbicacion();
			inventarioUbicacion.setInventario(inventario);
			for(UbicacionInterna ubicacionInterna: ubicaciones){
				ubicacionInterna.setInventario(true);
				ubicacionInternaDAO.upateInventario(ubicacionInterna);
				inventarioUbicacion.setUbicacionInterna(ubicacionInterna);
				inventarioUbicacionDAO.save(inventarioUbicacion);
				
				InventarioProducto inventarioProducto = new InventarioProducto();
				inventarioProducto.setInventarioUbicacion(inventarioUbicacion);
				inventarioProducto.setPreparado(true);
				inventarioProducto.setInventariado(false);
				List<OrdenTrabajo> oTs = ordenTrabajoDAO.listOTByUbicacionInterna(ubicacionInterna.getId());
				for(OrdenTrabajo oT:oTs){
					inventarioProducto.setOrdenTrabajo(oT);
					inventarioProductoDAO.save(inventarioProducto);
				}
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void terminarInventarioUbicacion(Integer idInventarioUbicacion) throws Exception{
		try {
			InventarioUbicacion inventarioUbicacion = inventarioUbicacionDAO.getEstadisticasByIdInventarioUbicacion(idInventarioUbicacion);
			Double brecha = (double) (inventarioUbicacion.getProductosFaltantes()+inventarioUbicacion.getProductosSobrantes());
			if(brecha>0D && inventarioUbicacion.getProductosPreparados()>0){
				brecha= (double) (brecha/inventarioUbicacion.getProductosPreparados());
				brecha = (double) (brecha*100);
			}
			inventarioUbicacion.setBrecha(brecha);
			inventarioUbicacionDAO.cerrarInventarioUbicacion(inventarioUbicacion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void abrirCerrarInventario(Inventario inventario,Usuario usuario) throws Exception {
		try {
			if(inventario.getVigente()){
				if(inventarioUbicacionDAO.haveRedundantUbicacionesByIdInventario(inventario.getId())>0){
					throw new SSTException("No se puede abrir el inventario, hay ubicaciones que está siendo inventariadas en este momento");
				}
				inventarioDAO.abrirCerrarInventario(inventario);
				ubicacionInternaDAO.updateInventarioByInventario(inventario);
			} else {
				
				inventario.setUsuarioCierre(usuario);
				List<InventarioUbicacion> estadisticas = inventarioUbicacionDAO.listEstadisticas(inventario.getId());
				for(InventarioUbicacion inventarioUbicacion:estadisticas){
					if(inventarioUbicacion.getFechaTermino()==null){
						this.terminarInventarioUbicacion(inventarioUbicacion.getId());
					}
					UbicacionInterna ubicacionInterna = new UbicacionInterna();
					ubicacionInterna.setInventario(false);
					ubicacionInterna.setId(inventarioUbicacion.getUbicacionInterna().getId());
					ubicacionInternaDAO.upateInventario(ubicacionInterna);
				}
				inventarioDAO.abrirCerrarInventario(inventario);
				this.calcularEstadisitcasInventario(inventario);
				ubicacionInternaDAO.updateInventarioByInventario(inventario);
			}
		} catch(SSTException e){
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void calcularEstadisitcasInventario(Inventario inventario) throws Exception {
		try {
			FilterInventario filterInventario = new FilterInventario();
			filterInventario.setIdInventario(inventario.getId());
			List<InventarioUbicacion> inventarioUbicaciones = inventarioUbicacionDAO.listUbicacionesInternasByFilter(filterInventario);
			inventario.setProductosContados(0);
			inventario.setProductosFaltantes(0);
			inventario.setProductosPreparados(0);
			inventario.setProductosSobrantes(0);
			inventario.setSinDiferencia(0);
			for(InventarioUbicacion inventarioUbicacion:inventarioUbicaciones){
				inventario.setProductosContados(inventario.getProductosContados()+inventarioUbicacion.getProductosContados());
				inventario.setProductosFaltantes(inventario.getProductosFaltantes()+inventarioUbicacion.getProductosFaltantes());
				inventario.setProductosPreparados(inventario.getProductosPreparados()+inventarioUbicacion.getProductosPreparados());
				inventario.setProductosSobrantes(inventario.getProductosSobrantes()+inventarioUbicacion.getProductosSobrantes());
				inventario.setSinDiferencia(inventario.getSinDiferencia()+inventarioUbicacion.getSinDiferencia());
			}
			Double brecha = (double) (inventario.getProductosFaltantes()+inventario.getProductosSobrantes());
			if(brecha>0D){
				brecha= (double) (brecha/inventario.getProductosPreparados());
				brecha = (double) (brecha*100);
			}
			inventario.setBrecha(brecha);
			inventarioDAO.updateEstadisticas(inventario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer contarInventarioUbicacionOpenByIdInventario(Integer idInventario) throws Exception{
		try {
			return inventarioUbicacionDAO.contarInventarioUbicacionOpenByIdInventario(idInventario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
//	public ListRange listUbicacionesInternasFromInventario(Integer idInventario, GridControl gridControl) throws Exception{
//		try {
//			ListRange listRange = new ListRange();
//			listRange.setRows(ubicacionInternaDAO.listFromInventario(idInventario,gridControl));
//			listRange.setTotal(ubicacionInternaDAO.getTotalFromInventario(idInventario));
//			return listRange;
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	public ListRange listEnviosToSucursal(FilterEnvioSucursal filterEnvioSucursal, GridControl gridControl) throws Exception {
		try {
			filterEnvioSucursal.setOrderBy(gridControl.getOrderBy());
			filterEnvioSucursal.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(envioSucursalDAO.listEnviosToSucursal(filterEnvioSucursal,gridControl));
			listRange.setTotal(envioSucursalDAO.getTotalEnviosToSucursal());
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listInventarioUbicacionesByIdInventario(Integer idInventario,GridControl gridControl) throws Exception{
		try {
			FilterInventario filterInventario = new FilterInventario();
			filterInventario.setIdInventario(idInventario);
			filterInventario.setOrderBy(gridControl.getOrderBy());
			filterInventario.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(inventarioUbicacionDAO.listInventarioUbicacionesByFilterInventario(filterInventario, gridControl));
			listRange.setTotal(inventarioUbicacionDAO.getTotalInventarioUbicacionesByFilterInventario(filterInventario));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void abrirCerrarEnvioSucursal(Integer idSucursal, Boolean crear, Usuario usuario) throws Exception{
		try {
			if(crear){
				this.crearEnvioSucursal(idSucursal, usuario);
			} else {
				this.cerrarEnvioSucursal(idSucursal, usuario);
			}
		}catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void crearEnvioSucursal(Integer idSucursal, Usuario usuario) throws Exception{
		try {
			EnvioSucursal envioSucursalValidacion = envioSucursalDAO.getEnvioToSucursalByIdSucursal(idSucursal);
			if (envioSucursalValidacion != null) {
				Ubicacion ubicacion = ubicacionDAO.get(idSucursal.longValue());
				throw new SSTException("Ya existe un envio abierto para la ubicación " + ubicacion.getId() + " : " + ubicacion.getNombre());
			}
			
			EnvioSucursal envioSucursal = new EnvioSucursal();
			envioSucursal.setUsuarioInicio(usuario);
			envioSucursal.setSucursal(new Sucursal());
			envioSucursal.getSucursal().setId(idSucursal);
			List<OrdenTrabajo> oTs = ordenTrabajoDAO.listOTToEnvioSucursal(idSucursal);
			envioSucursal.setTotalOT(oTs.size());
			envioSucursalDAO.save(envioSucursal);
			
			EnvioSucursalDetalle envioSucursalDetalle = new EnvioSucursalDetalle();
			envioSucursalDetalle.setEnvioSucursal(envioSucursal);
			envioSucursalDetalle.setVigente(true);
			for(OrdenTrabajo oT:oTs){
				envioSucursalDetalle.setOrdenTrabajo(oT);
				envioSucursalDetalleDAO.save(envioSucursalDetalle);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void cerrarEnvioSucursal(Integer idSucursal, Usuario usuario) throws Exception {
		try {
			EnvioSucursal envioSucursal = envioSucursalDAO.getEnvioToSucursalByIdSucursal(idSucursal);
			if(envioSucursal != null){
				envioSucursalDetalleDAO.cerrarEnvioSucursalDetalleByIdEnvio(envioSucursal.getId());
				envioSucursal.setUsuarioTermino(usuario);
				envioSucursalDAO.cerrarEnvioSucursalByEnvioSucursa(envioSucursal);
			} else {
				throw new SSTException("Al parecer este envío ya ha sido cerrado, por favor refresque la pagina para verificar la información");
			}
		}catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOTToCambioEstadoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOTToCambioEstadoByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTToCambioEstadoByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> ListAllCheckOTToCambioEstadoByFilter(FilterOT filterOT) throws Exception {
		try {
			return ordenTrabajoDAO.ListAllCheckOTToCambioEstadoByFilter(filterOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Guia saveGuiaHaciaBodegaDiezMil(List<OrdenTrabajo> ots ,DespachoInterno despacho,Ubicacion ubicacion,Usuario usuario)throws Exception{
		try {
			Guia guia = new Guia();
			guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
			guia.setDestino(new Ubicacion());
			guia.getDestino().setId(Constants.BODEGA_10000);
			guia.setOrigen(ubicacion);
			guia.setEntregaCliente(false);
			guia.setVigente(true);
			guia.setUsuario(usuario);
			guia.setTipoGuia(Constants.GUIA_TIPO_AGRUPADA);
			guiaDAO.save(guia);
			
			for(OrdenTrabajo ot : ots){
				saveBitacoraHaciaBodegaDiezMil(ot,despacho);
				
				Bitacora bitacora = new Bitacora();
				bitacora.setGuia(guia);
				bitacora.setOrdenTrabajo(ot);
				bitacora.setUbicacion(ubicacion);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
			
				Gestion gestion = new Gestion();
				gestion.setFecha(utilDAO.getDate());
				gestion.setGestion("OT cerrada en falla de fabricacion por envio a la bodega 10000");
				gestion.setOt(ot);
				gestion.setUsuario(usuario);
				
				ot.setEstado(estadoDAO.getEstadoById(Constants.OT_CERRADA_POR_ENVIO_10000));
				ordenTrabajoDAO.updateEstadoOT(ot);
			}
			return guia;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	

	public void saveBitacoraHaciaBodegaDiezMil(OrdenTrabajo ot,DespachoInterno despacho) throws Exception{
		
		Ubicacion destino = ubicacionDAO.get(despacho.getDestino().getId());
		if(destino == null){
			destino = proveedorDAO.getProveedorById(despacho.getDestino().getId());
		}
		Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ot.getId());
		bitacora.setFechaSalida(utilDAO.getDate());
		bitacoraDAO.updateFechaSalida(bitacora);
		
		
		Bitacora bitacora1 = new Bitacora();
		bitacora1.setOrdenTrabajo(ot);
		bitacora1.setFechaEntrada(utilDAO.getDate());
		bitacora1.setFechaSalida(utilDAO.getDate());
		bitacora1.setUbicacion(destino);
		if(destino.getTipo() != null && destino.getTipo().equals(Constants.TIPO_UBICACION_SERVICIO_TECNICO)){
			bitacora1.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_FF));
		} else {
			bitacora1.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_PROVEEDOR_LUEGO_A_CAMION_A_FF));
		}
		bitacoraDAO.save(bitacora1);
		
		Bitacora bitacora2 = new Bitacora();
		bitacora2.setOrdenTrabajo(ot);
		bitacora2.setFechaEntrada(utilDAO.getDate());
		bitacora2.setFechaSalida(utilDAO.getDate());
		bitacora2.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CAMION_LUEGO_A_FF));
		bitacoraDAO.save(bitacora2);
		
		Bitacora bitacora3 = new Bitacora();
		bitacora3.setOrdenTrabajo(ot);
		bitacora3.setFechaEntrada(utilDAO.getDate());
		//bitacora3.setFechaSalida(utilDAO.getDate());
		bitacora3.setUbicacion(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
		bitacora3.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_A_CAMION_PARA_BODEGA_10000));
		bitacoraDAO.save(bitacora3);
	}
	
	public OrdenTrabajo getEstadosOT(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.getEstadosOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void modificarEstadoOT(Long idOT,Integer idEstado,Usuario usuario)throws Exception{
		try {
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setUsuarioModificacionEstado(usuario);
			ordenTrabajo.setId(idOT);
			ordenTrabajo.setEstado(new Estado());
			if(idEstado.equals(Constants.OT_EN_CD_RECEPCION_ACEPTADA) || idEstado.equals(Constants.OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_RECHAZADO)){
				ordenTrabajo.getEstado().setId(idEstado);
				//cambiarEstadoOT;
				ordenTrabajoDAO.modificarEstadoOT(ordenTrabajo);
			} else if(idEstado.equals(Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO)){
				movimientosInternosService.cerrarYCrearBitacoraInterna(idOT, idEstado, null);
				ordenTrabajo.getEstado().setId(Constants.OT_EN_CD_RECEPCION_ACEPTADA);
				ordenTrabajoDAO.modificarEstadoOT(ordenTrabajo);
				
			} else if(idEstado.equals(Constants.BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD)){
				movimientosInternosService.cerrarYCrearBitacoraInterna(idOT, idEstado, null);
				ordenTrabajo.getEstado().setId(Constants.OT_EN_CD_RECEPCION_ACEPTADA);
				ordenTrabajoDAO.modificarEstadoOT(ordenTrabajo);
				
			}else if(idEstado.equals(Constants.BITACORA_INTERNA_OT_ALMACENADA)){
				movimientosInternosService.cerrarYCrearBitacoraInterna(idOT, idEstado, null);
				ordenTrabajo.getEstado().setId(Constants.OT_APTA_TRASLADO_SC_O_BR);
				//cambiar estado ot;
				ordenTrabajoDAO.modificarEstadoOT(ordenTrabajo);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public InventarioUbicacion getEstadisitcasByIdInventarioUbicacion(Long id) throws Exception{
		try {
			return inventarioUbicacionDAO.getEstadisitcasByIdInventarioUbicacion(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listUbicacionesInternasByFilter(FilterInventario filterInventario,GridControl gridControl) throws Exception {
		try {
			filterInventario.setOrderBy(gridControl.getOrderBy());
			filterInventario.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(inventarioUbicacionDAO.listUbicacionesInternasByFilter(filterInventario, gridControl));
			listRange.setTotal(inventarioUbicacionDAO.getTotalUbicacionesInternasByFilter(filterInventario));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Inventario getEstadisticasInventarioByIdInventario(Integer idInventario) throws Exception {
		try {
			return inventarioDAO.getEstadisticasInventarioByIdInventario(idInventario); 
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listInventarioProductoByFilter(FilterInventario filterInventario,GridControl gridControl) throws Exception{
		try {
			filterInventario.setOrderBy(gridControl.getOrderBy());
			filterInventario.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(inventarioProductoDAO.listInventarioProductoByFilter(filterInventario, gridControl));
			listRange.setTotal(inventarioProductoDAO.getTotalInventarioProductoByFilter(filterInventario));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public ListRange listOTListasParaDespachoSucursal(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOTListasParaDespachoSucursal(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTListasParaDespachoSucursal());
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
//	public Boolean verificarEstadoParaEliminarOtsFromDespacho(List<OrdenTrabajo> ots,DespachoInterno despacho) throws Exception{
//		try {
//			String otsEscaneadas = "";
//			FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
//		 	filterDespacho.setIdDespacho(despacho.getId());
//		 	List<OrdenTrabajo> otAux = new ArrayList<OrdenTrabajo>();
//		 	for(OrdenTrabajo ot : ots){
//		 		otAux.add(ot);
//		 	}
//		 	filterDespacho.setIdOts(otAux);
//		 	
//		 	List<DespachoDetalle> despachoDetalles = despachoDetalleDAO.listDespachoDetalleByIdDespachoInterno(filterDespacho);
//		 	for(DespachoDetalle detalle : despachoDetalles){
//				if(detalle.getEstado().getId().equals(Constants.PRODUCTO_ESCANEADO)){
//					otsEscaneadas = otsEscaneadas+detalle.getOrdenTrabajo().getId().toString()+',';
//				}
//			}
//		 	if(!otsEscaneadas.equals("")){
//		 		throw new SSTException("No se pueden eliminar las Ots "+otsEscaneadas+" ya que fueron escaneadas");
//		 	} else { 
//		 		return true;
//		 	}
//		} catch (SSTException e) {
//			throw e;
//		}catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	public Boolean verificarEstadoParaEliminarOtsFromDespacho(List<OrdenTrabajo> ots,DespachoInterno despacho) throws Exception{
		try {
			String otsEscaneadas = "";
			FilterDespachoInterno filterDespacho = new FilterDespachoInterno();
			filterDespacho.setIdDespacho(despacho.getId());
		 	for(OrdenTrabajo ot : ots){
		 		filterDespacho.setIdOT(ot.getId());
		 		DespachoDetalle despachoDetalle = despachoDetalleDAO.listDespachoDetalleByIdOT(filterDespacho);
					if(despachoDetalle.getEstado().getId().equals(Constants.PRODUCTO_ESCANEADO)){
						otsEscaneadas = otsEscaneadas+despachoDetalle.getOrdenTrabajo().getId().toString()+',';
					}
		 	}
		 	if(!otsEscaneadas.equals("")){
		 		throw new SSTException("No se pueden eliminar las Ots "+otsEscaneadas+" ya que fueron escaneadas");
		 	} else { 
		 		return true;
		 	}
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	
	public List<OrdenTrabajo> verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(List<OrdenTrabajo> ots) throws Exception{
		try {
			List<OrdenTrabajo> otAux = new ArrayList<OrdenTrabajo>();
		 	for(OrdenTrabajo ot : ots){
		 		
		 		ot = ordenTrabajoDAO.getOTById(ot.getId());
				if(!ot.getEstado().getId().equals(Constants.OT_CERRADA_POR_PETICION_FACTURA)
					&& !ot.getEstado().getId().equals(Constants.OT_CERRADA_POR_PETICION_NOTA_CREDITO)
					&& !ot.getEstado().getId().equals(Constants.OT_CERRADA_POR_USUARIO)
					&& !ot.getEstado().getId().equals(Constants.OT_CERRADA_POR_ENVIO_10000)
					&& !ot.getEstado().getId().equals(Constants.OT_ENVIADA_A_DA)
					&& !ot.getEstado().getId().equals(Constants.OT_ENVIADA_A_AV)
					&& !ot.getEstado().getId().equals(Constants.OT_ACEPTADA_EN_FALLA_FABRICACION)){

					otAux.add(ot);
				
				}
			}
		 	return otAux;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateRecuperacionForOT(List<OrdenTrabajo> ots) throws Exception{
		try {
		 	for(OrdenTrabajo ot : ots){
				ordenTrabajoDAO.updateRecuperacionForOT(ot);
				}
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public DespachoDetalle getIndicadoresbyidDespacho(DespachoInterno despacho)throws Exception{
		 DespachoDetalle despachoDetalle = new DespachoDetalle();
		 despachoDetalle = despachoDetalleDAO.getIndicadoresbyidDespacho(despacho.getId());
		 despachoDetalle.setBodega(guiaDAO.getGuiasToUbicacionDiezMilByidDespacho(despacho.getId()));
		 FilterOT filter = new FilterOT();
		 filter.setIdDespacho(despacho.getId());
		 filter.setIdEstado(Constants.OT_CERRADA_POR_PETICION_FACTURA.toString());
		 despachoDetalle.setFactura(peticionDocumentoDAO.getPeticionDocumentoByDespachoIterno(filter));
		 
		 filter.setIdEstado(Constants.OT_CERRADA_POR_PETICION_NOTA_CREDITO.toString());
		 despachoDetalle.setNotaCredito(peticionDocumentoDAO.getPeticionDocumentoByDespachoIterno(filter));
		return despachoDetalle;
		
	}
	
	public List<OrdenTrabajo> listOtByDespachoAndEstado(FilterDespachoInternoDetalle filterDespacho)throws Exception{
		return ordenTrabajoDAO.listOtByDespachoAndEstado(filterDespacho);
	}
	
	public List<PeticionDocumento> listPeticionDocumentoByDespachoIterno(FilterOT filter) throws Exception{
		return peticionDocumentoDAO.listPeticionDocumentoByDespachoIterno(filter);
	}
	
	public void setGuiaDAO(GuiaDAO guiaDAO){
		this.guiaDAO = guiaDAO;
	}

	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setGuiaRemateDAO(GuiaRemateDAO guiaRemateDAO) {
		this.guiaRemateDAO = guiaRemateDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}
	
	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO) {
		this.despachoInternoDAO = despachoInternoDAO;
	}
	
	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}
	
	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}
	
	public void setGestionesDAO(GestionesDAO gestionesDAO) {
		this.gestionesDAO= gestionesDAO;
	}
	
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO){
		this.bitacoraDAO = bitacoraDAO;
	}
	
	public void setDespachoDetalleDAO(DespachoDetalleDAO despachoDetalleDAO){
		this.despachoDetalleDAO = despachoDetalleDAO;
	}
	
	public void setDespachoInternoCamionDAO (DespachoInternoCamionDAO despachoInternoCamionDAO){
		this.despachoInternoCamionDAO = despachoInternoCamionDAO;
	}
	
	public void setAccesorioDAO (AccesorioDAO accesorioDAO){
		this.accesorioDAO = accesorioDAO;
	}
	
	public void setMovimientosInternosService(MovimientosInternosService movimientosInternosService){
		this.movimientosInternosService = movimientosInternosService;
	}
	
	public void setSemaforoDAO (SemaforoDAO semaforoDAO){
		this.semaforoDAO = semaforoDAO;
	}
	
	public void setIndicadorDAO(IndicadorDAO indicadorDAO){
		this.indicadorDAO = indicadorDAO;
	}
	
	public void setSelloDAO(SelloDAO selloDAO){
		this.selloDAO = selloDAO;
	}
	
	public void setPeticionDocumentoDAO(PeticionDocumentoDAO peticionDocumentoDAO){
		this.peticionDocumentoDAO = peticionDocumentoDAO;
	}
	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO){
		this.ubicacionInternaDAO = ubicacionInternaDAO;
	}
	
	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO){
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO){
		this.proveedorDAO=proveedorDAO;
	}
	
	public void setOWService(OWService oWService) {
		this.oWService = oWService;
	}
	
	public void setPeticionDocumentoDetalleDAO(PeticionDocumentoDetalleDAO peticionDocumentoDetalleDAO){
		this.peticionDocumentoDetalleDAO = peticionDocumentoDetalleDAO;
	}

	public void setInventarioDAO(InventarioDAO inventarioDAO) {
		this.inventarioDAO = inventarioDAO;
	}

	public void setInventarioUbicacionDAO(InventarioUbicacionDAO inventarioUbicacionDAO) {
		this.inventarioUbicacionDAO = inventarioUbicacionDAO;
	}

	public void setInventarioProductoDAO(InventarioProductoDAO inventarioProductoDAO) {
		this.inventarioProductoDAO = inventarioProductoDAO;
	}
	
	public void setEnvioSucursalDAO(EnvioSucursalDAO envioSucursalDAO) {
		this.envioSucursalDAO = envioSucursalDAO;
	}

	public void setEnvioSucursalDetalleDAO(EnvioSucursalDetalleDAO envioSucursalDetalleDAO) {
		this.envioSucursalDetalleDAO = envioSucursalDetalleDAO;
	}
	
	public void setTransportistaDAO(TransportistaDAO transportistaDAO) {
		this.transportistaDAO = transportistaDAO;
	}
	
	public void setEnvioRecepcionService(EnvioRecepcionService envioRecepcionService) {
		this.envioRecepcionService = envioRecepcionService;
	}
}
