package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.CambioDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.FamiliaDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.GuiaAgrupadaDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.GuiaRemateDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.RecepcionDAO;
import cl.abcdin.sst.dao.RecepcionMasivaDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Cambio;
import cl.abcdin.sst.model.Clasificacion;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.GuiaAccesorios;
import cl.abcdin.sst.model.GuiaRemateDetalle;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Logistico;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.RecepcionCamion;
import cl.abcdin.sst.model.RecepcionCamionGuia;
import cl.abcdin.sst.model.RecepcionCamionOT;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.DetalleRecepcion;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.model.vo.ProductoReport;
import cl.abcdin.sst.utils.Constants;



public class EnvioRecepcionService {
	
	private RecepcionMasivaDAO recepcionMasivaDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private BitacoraDAO bitacoraDAO;
	private UbicacionDAO ubicacionDAO;
	private GuiaAgrupadaDAO guiaAgrupadaDAO;
	private GuiaDAO guiaDAO;
	private GuiaRemateDAO guiaRemateDAO;
	private AccesorioDAO accesorioDAO;
	private EstadoDAO estadoDAO;
	private RecepcionDAO recepcionDAO;
	private GestionesDAO gestionesDAO;
	private CambioDAO cambioDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private SucursalService sucursalService;
	private OrdenTrabajoService ordenTrabajoService;
	private OWService owService;
	private ProductoDAO productoDAO;
	private FamiliaDAO familiaDAO;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private UtilDAO utilDAO;
	private ParteDAO parteDAO;
	private UbicacionInternaDAO ubicacionInternaDAO;
	private BodegaService bodegaService;
	
	private static final Log log = LogFactory.getLog(EnvioRecepcionService.class);
	
	
	public List<Recepcion> listRecepcionesMasivas() throws Exception {
		try {
			return recepcionMasivaDAO.listRecepcionesMasivas();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveGuiaRemate(List<OrdenTrabajo> ordenTrabajos, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			Long idDestinoAntesFacturar = null;
			filterOT.setOrdenTrabajos(ordenTrabajos);
			ordenTrabajos = ordenTrabajoDAO.listByFilter(filterOT);
			
			
			for (OrdenTrabajo ordenTrabajo : ordenTrabajos) {
				if (ordenTrabajo.getIdDestinoAntesFacturar() == null || ordenTrabajo.getIdDestinoAntesFacturar() == 0) {
					throw new SSTException("Al menos una ot no tiene elegido donde enviar");
				}
				if (idDestinoAntesFacturar == null) {
					idDestinoAntesFacturar = ordenTrabajo.getIdDestinoAntesFacturar();
				} else if (!idDestinoAntesFacturar.equals(ordenTrabajo.getIdDestinoAntesFacturar())) {
					throw new SSTException("Las ordenes de trabajo seleccionadas no se envian al mismo lugar");
				}
			}
			Ubicacion destino = ubicacionDAO.get(idDestinoAntesFacturar);
			Ubicacion origen = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
			Guia guia = this.saveGuiaRemate(origen, usuario, destino);
			for (OrdenTrabajo ordenTrabajo : ordenTrabajos) {
				GuiaRemateDetalle guiaRemateDetalle = new GuiaRemateDetalle();
				guiaRemateDetalle.setOrdenTrabajo(ordenTrabajo);
				guiaRemateDetalle.setGuia(guia);
				
				guiaRemateDAO.saveDetalle(guiaRemateDetalle);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private Guia saveGuiaRemate(Ubicacion origen, Usuario usuario, Ubicacion destino) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guia.setOrigen(origen);
			guia.setDestino(destino);
			guia.setVigente(true);
			guia.setUsuario(usuario);
			guiaRemateDAO.save(guia);
			return guia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public GuiaPendienteAgrupada getGuiaRemateById(Long id, Ubicacion ubicacion, Usuario usuario) throws Exception {
		try {
			GuiaPendienteAgrupada guia = guiaRemateDAO.getGuiaRemateById(id);
			if(guia.getNumero()==null || guia.getNumero()==0){
				FilterGuia filter = new FilterGuia();
				filter.setIdSucursal(ubicacion.getId());
				filter.setIdUsuario(usuario.getId());
				guia.setNumero(guiaDAO.getSiguienteNumeroGuia(filter));
			}
			return guia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Recepcion saveRecepcionMasiva(RecepcionCamion recepcionCamion, Usuario usuario)throws Exception{
		try {
			recepcionCamion.setLogistico(new Logistico());
			recepcionCamion.setEstado(new Estado());
			recepcionCamion.getEstado().setId(Constants.RECEPCION_MASIVA_EN_CURSO);
			recepcionCamion.getLogistico().setRut(usuario.getRut());
			recepcionCamion.setNombreResponsable(recepcionCamion.getNombreResponsable().toUpperCase());
			RecepcionCamion recepcionCamionValidacion = recepcionMasivaDAO.getRecepcionCamionAbiertaByTransportista(recepcionCamion.getTransportista().getId());
			if (recepcionCamionValidacion != null) {
				throw new SSTException("Existe una recepciín en curso para el transportista seleccionado");
			}
			recepcionMasivaDAO.saveRecepcionMasiva(recepcionCamion);
			return recepcionCamion;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Recepcion getRecepcionCamionAbiertaByTransportista(Long idTransportista) throws Exception{
		try {
			return recepcionMasivaDAO.getRecepcionCamionAbiertaByTransportista(idTransportista);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void recibirOT(FilterOT filterOT, Integer idRecepcionCamion, Ubicacion ubicacion)throws Exception {
		try {
			FilterRecepcionProducto filterRecepcionProducto = new FilterRecepcionProducto();
			OrdenTrabajo ordenTrabajo = ordenTrabajoService.getOTRecibeBodegaFF(filterOT, ubicacion);
			
			filterRecepcionProducto.setIdDestino(ubicacion.getId());
			filterRecepcionProducto.setIdOT(ordenTrabajo.getId());
			Guia guia = getGuiaByFilterGuia(filterRecepcionProducto);
			
			if(guia == null) {
				throw new SSTException("La OT no está asociada a una guía con destino Fallas de Fabricación");
			}
			
			filterRecepcionProducto.setIdGuia(guia.getId());
			filterRecepcionProducto.setIdEstadoRecepcion(Constants.RECEPCION_MASIVA_EN_CURSO);
			filterRecepcionProducto.setIdRecepcionCamion(idRecepcionCamion);
			
			if(!recepcionMasivaDAO.getTotalEstadoRecepcionCamion(filterRecepcionProducto).equals(0)){
				throw new SSTException("La guía ya está asociada a una recepción en curso");
			} else {
				if(recepcionMasivaDAO.existeGuiaEnRecepcion(filterRecepcionProducto).equals(0)){
					RecepcionCamionGuia recepcionCamionGuiaUltima = recepcionMasivaDAO.getUltimaRecepcionGuiaByFilter(filterRecepcionProducto);
					RecepcionCamionGuia recepcionCamionGuia = new RecepcionCamionGuia();
					recepcionCamionGuia.setGuia(guia);
					recepcionCamionGuia.setRecepcionCamion(new RecepcionCamion());
					recepcionCamionGuia.getRecepcionCamion().setId(idRecepcionCamion);
					this.saveRecepcionesCamionGuias(recepcionCamionGuia);
					
					if (recepcionCamionGuiaUltima != null && guia.getEstado().getId().equals(Constants.GUIA_RECIBIDA_FALTANTE)) {
						List<RecepcionCamionOT> ordenesTrabajo = ordenTrabajoDAO.listOTGuiaAgrupadaByIdRecepcionGuia(recepcionCamionGuiaUltima.getId());
						for (RecepcionCamionOT recepcionCamionOT : ordenesTrabajo) {
							recepcionCamionOT.setRecepcionCamionGuia(recepcionCamionGuia);
							if (!recepcionCamionOT.getOrdenTrabajo().getEstado().getId().equals(Constants.OT_FALTANTE_EN_RECEPCION)) {
								recepcionCamionOT.getOrdenTrabajo().getEstado().setId(Constants.OT_YA_RECEPCIONADA_EN_BODEGA);
							}
							recepcionMasivaDAO.saveOTRecepcionMasiva(recepcionCamionOT);
						}
					} else {
						List<RecepcionCamionOT> ordenesTrabajo = ordenTrabajoDAO.listOTGuiaAgrupadaByIdGuia(guia.getId());
						for (RecepcionCamionOT recepcionCamionOT : ordenesTrabajo) {
							recepcionCamionOT.getOrdenTrabajo().setEstado(estadoDAO.getEstadoById(Constants.OT_FALTANTE_EN_RECEPCION));
							recepcionCamionOT.setRecepcionCamionGuia(recepcionCamionGuia);
							recepcionMasivaDAO.saveOTRecepcionMasiva(recepcionCamionOT);
						}
					}
				}
			}
			
			ordenTrabajo.getEstado().setId(Constants.OT_RECIBIDA_POR_REVISAR_EN_FALLA_FABRICACION);
			this.updateEstadoOTRecepcionGuiaMasiva(ordenTrabajo, guia, ordenTrabajo.getEstado());
			ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
			
			guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_RECIBIDA_FALTANTE));
			guiaDAO.updateEstado(guia);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void updateEstadoOTRecepcionGuiaMasiva(OrdenTrabajo ordenTrabajo, RecepcionCamionGuia recepcionCamionGuia, Estado estado) throws Exception {
		try {
			RecepcionCamionOT recepcionCamionOT = new RecepcionCamionOT();
			recepcionCamionOT.setOrdenTrabajo(ordenTrabajo);
			recepcionCamionOT.setRecepcionCamionGuia(recepcionCamionGuia);
			recepcionCamionOT = ordenTrabajoDAO.getOTGuiaAgrupadaByRecepcionCamionOT(recepcionCamionOT);
			recepcionCamionOT.getOrdenTrabajo().setEstado(estado);
			recepcionMasivaDAO.updateOTRecepcionMasiva(recepcionCamionOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void updateEstadoOTRecepcionGuiaMasiva(OrdenTrabajo ordenTrabajo, Guia guia, Estado estado) throws Exception {
		try {
			FilterRecepcionProducto filterRecepcionProducto = new FilterRecepcionProducto();
			filterRecepcionProducto.setIdGuia(guia.getId());
			RecepcionCamionGuia recepcionCamionGuia = recepcionMasivaDAO.getUltimaRecepcionGuiaByFilter(filterRecepcionProducto);
			this.updateEstadoOTRecepcionGuiaMasiva(ordenTrabajo, recepcionCamionGuia, estado);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private Integer saveRecepcionesCamionGuias(RecepcionCamionGuia recepcionCamionGuia) throws Exception {
		try {
			return recepcionMasivaDAO.saveRecepcionesCamionGuias(recepcionCamionGuia);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	private Guia getGuiaByFilterGuia(FilterRecepcionProducto filterRecepcionProducto) throws Exception{
		try {
			Guia guia = null;
			
			filterRecepcionProducto.setIdEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
			filterRecepcionProducto.setIdEstadoGuia(Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA);
			
			guia = recepcionMasivaDAO.getGuiaByFilterGuia(filterRecepcionProducto);
			
			if (guia == null) {
				filterRecepcionProducto.setIdEstadoGuia(Constants.GUIA_RECIBIDA_FALTANTE);
				guia = recepcionMasivaDAO.getGuiaByFilterGuia(filterRecepcionProducto);
			}
			
			return guia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public ListRange listDetalleGuiaRecepcion(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterRecepcionProducto.setOrderBy(gridControl.getOrderBy());
			filterRecepcionProducto.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(recepcionMasivaDAO.listDetalleGuiaRecepcion(filterRecepcionProducto, gridControl));
			listRange.setTotal(recepcionMasivaDAO.getTotalDetalleGuiaRecepcion(filterRecepcionProducto));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Guia getGuiaRecepcionAgrupada(Long idOT, Ubicacion ubicacion) throws Exception {
		try { 
			Guia guia = new Guia();
			guia.setOrdenTrabajo(new OrdenTrabajo());
			guia.getOrdenTrabajo().setId(idOT);
			guia.setDestino(ubicacion);
			Ubicacion origen = ubicacionDAO.getUbicacionOT(idOT);
			guia.setOrigen(origen);
			return guiaAgrupadaDAO.getGuiaRecepcionAgrupada(guia);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	 
	
	public void updateOTTareaUrgenteFF(OrdenTrabajo ot) throws Exception {
		try {
			ot = ordenTrabajoDAO.getOTById(ot.getId());
			ot.setTareaUrgenteFF(true);
			ordenTrabajoDAO.updateOTTareaUrgenteFF(ot);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	
	public void recibirBitacora(Ubicacion origen, Ubicacion ubicacion, Bitacora bitacora, OrdenTrabajo ordenTrabajo, Recepcion recepcion) throws Exception {
		try {
			if (ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)) {
				if (origen.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRODISTRIBUCION_DESPUES_A_CAMION_AL_SERVICIO_TECNICO));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CD_DESPUES_ENTREGA_CAMION_A_SUCURSAL));
					}
					
					if (bitacora.getEstado().getId().equals(Constants.BITACORA_EN_SERVICIO_TECNICO_DEPUES_AL_CD)) {
						Bitacora bitacoraPaso = new Bitacora();
						bitacoraPaso.setRecepcion(recepcion);
						bitacoraPaso.setFechaEntrada(bitacora.getFechaSalida());
						bitacoraPaso.setFechaSalida(bitacora.getFechaSalida());
						bitacoraPaso.setOrdenTrabajo(ordenTrabajo);
						bitacoraPaso.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_A_CD_2));
						bitacoraDAO.save(bitacoraPaso);
					}
					bitacoraDAO.save(bitacoraNueva);
				} else if (origen.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRODISTRIBUCION_SE_DEVUELVE_A_SUCURSAL));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId()));
					}
					bitacoraDAO.save(bitacoraNueva);
				} else if (origen.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRO_DE_DISTRIBUCION));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId()));
					}
					bitacoraDAO.save(bitacoraNueva);
				}
			} else if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)){
				if(origen.getTipo().equals(Constants.UBICACION_SUCURSAL)){
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_CAMION_PARA_TRANSPORTISTA));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FALLAS_DE_FABRICACION));
					}
					bitacoraDAO.save(bitacoraNueva);
				} else if(origen.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && origen.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)){
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_CAMION_PARA_TRANSPORTISTA));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FALLAS_DE_FABRICACION));
					}
					bitacoraDAO.save(bitacoraNueva);
				} else if(origen.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && origen.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)){
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FF_LUEGO_CAMION_PARA_TRANSPORTISTA));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_FALLAS_DE_FABRICACION));
					}
					bitacoraDAO.save(bitacoraNueva);
				}
			} else if (ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
				if (origen.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setFechaSalida(bitacora.getFechaSalida());
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					bitacoraNueva.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId()));
					bitacoraDAO.save(bitacoraNueva);

					Bitacora bitacoraNueva2 = new Bitacora();
					bitacoraNueva2.setRecepcion(recepcion);
					bitacoraNueva2.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva2.setUbicacion(ubicacion);
					bitacoraNueva2.setOrdenTrabajo(ordenTrabajo);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva2.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL));
					} else {
						bitacoraNueva2.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacoraNueva.getEstado().getId()));
					}
					bitacoraDAO.save(bitacoraNueva2);
				} else if (origen.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL) || origen.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					
					if (recepcion.getEstado().getId().equals(Constants.RECEPCION_OT_RECHAZADA)) {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_DEVUELTA_A_CENTRO_DISTRIBUCION));
					} else if (ordenTrabajo.getNumeroContrato() == null) { //Esto es extraño
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD));
					} else {
						bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_TERMINO_DESPUES_ENTREGA_A_CLIENTE));
					}
					bitacoraDAO.save(bitacoraNueva);
				}
			} else if (ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
				if (origen.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					bitacoraNueva.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId()));
					bitacoraDAO.save(bitacoraNueva);
				} else if (origen.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
					bitacoraDAO.updateFechaSalida(bitacora);
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setFechaEntrada(bitacora.getFechaSalida());
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					bitacoraNueva.setEstado(estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId()));
					bitacoraDAO.save(bitacoraNueva);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void recibirBitacoraAccesorio(Ubicacion origen, Ubicacion ubicacion, Bitacora bitacora, OrdenTrabajo ordenTrabajo, Recepcion recepcion)throws Exception {
		if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)) {
			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CD_DESPUES_ENTREGA_CAMION_A_SUCURSAL));
			bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
			bitacoraNueva.setUbicacion(ubicacion);
			bitacoraNueva.setFechaEntrada(recepcion.getFechaRecepcion());
			bitacoraNueva.setRecepcion(recepcion);
			bitacoraDAO.saveBitacoraAccesorios(bitacoraNueva);
		} else if(ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)){
			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_DEVUELTA_A_CENTRO_DISTRIBUCION));
			bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
			bitacoraNueva.setUbicacion(ubicacion);
			bitacoraNueva.setFechaEntrada(recepcion.getFechaRecepcion());
			bitacoraNueva.setRecepcion(recepcion);
			bitacoraDAO.saveBitacoraAccesorios(bitacoraNueva);
		}
	}
	
	public void saveRecepcionOTGuiaAgrupadas(Recepcion recepcion,String accesoriosRecibidos,Boolean aceptarRecepcionSinObservacion, Usuario usuario, Ubicacion ubicacion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		try {
			FilterRecepcionProducto filterRecepcionProducto = new FilterRecepcionProducto();
			Guia guiaRecibida = guiaDAO.getGuiaByGuia(recepcion.getGuia());
			filterRecepcionProducto.setIdGuia(guiaRecibida.getId());
			
			Clasificacion clasificacion = recepcion.getGuia().getOrdenTrabajo().getClasificacion();
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion.getGuia().getOrdenTrabajo().getId());
			ordenTrabajo.setClasificacion(clasificacion);
			Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId());
			bitacora.setFechaSalida(recepcion.getFechaRecepcion());
			
			
			ordenTrabajo.setDescripcionFisica(recepcion.getGuia().getOrdenTrabajo().getDescripcionFisica());
			ordenTrabajo.setNumeroSerie(recepcion.getGuia().getOrdenTrabajo().getNumeroSerie());
			recepcion.setUbicacion(ubicacion);
			recepcion.setOrigen(ubicacionDAO.get(guiaRecibida.getOrigen().getId()));
			recepcion.setUsuario(usuario);
			
			if(recepcionDAO.getExisteRecepcionOT(recepcion) >= 1) {
				throw new SSTException("Ya existe una recepción de OT con el numero de guía - " + recepcion.getGuia().getNumero());
			}
			
			if(aceptarRecepcionSinObservacion){
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA)); 
				if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ACEPTADA_EN_FALLA_FABRICACION));
				}
			} else {
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA_CON_OBSERVACION)); 
				Gestion gestion = new Gestion();
				gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
				gestion.setUsuario(usuario);			
				gestion.setGestion("  Acepta con observación la recepcion de la OT en "+ ubicacion.getId() + " " + ubicacion.getNombre());
				gestionesDAO.saveGestion(gestion);
				
				if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
					if(accesoriosRecibidos != null && !accesoriosRecibidos.equals("")){  
						ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ACEPTADA_CON_OBSERVACION_INCOMPLETA_EN_FALLA_FABRICACION));
					} else if(accesoriosRecibidos != null && accesoriosRecibidos.equals("")){
						ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ACEPTADA_CON_OBSERVACION_COMPLETA_EN_FALLA_FABRICACION));
					} 
				}
			}
			this.updateOTTareaUrgenteFF(ordenTrabajo);
			
			sucursalService.updateParteOTConAnteriorObervacion(partes,usuario,ubicacion);
			this.updateEstadoOTRecepcionGuiaMasiva(ordenTrabajo, guiaRecibida, ordenTrabajo.getEstado());
			ordenTrabajoDAO.updateOTRecepcion(ordenTrabajo);  
			sucursalService.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion, recepcion.getOrigen(), accesorios);
			recepcionDAO.saveRecepcionOT(recepcion);
			this.recibirBitacora(recepcion.getOrigen(), ubicacion, bitacora, ordenTrabajo, recepcion);
			
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Boolean isExcluidoControlCalidadByIdProducto(Integer idProducto) throws Exception{
		try {
			if(productoDAO.isProductoExcuidoByIdProducto(idProducto)>0){
				return true;
			}else if(familiaDAO.isFamiliaExcluidaCCByIdProducto(idProducto)>0){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
//	private void sendToControlCalidad(OrdenTrabajo ordenTrabajo) throws Exception {
//		try {
//			if(!this.isExcluidoControlCalidadByIdProducto(ordenTrabajo.getProducto().getId())){
//				BitacoraInterna bitacoraInterna = new BitacoraInterna();
//				bitacoraInterna.setBitacora(bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId()));
//				bitacoraInterna.setOrdenTrabajo(ordenTrabajo);
//				bitacoraInterna.setEstado(new Estado());
//				bitacoraInterna.getEstado().setId(Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO);
//				bitacoraInterna.setUbicacionInterna(new UbicacionInterna());
//				bitacoraInterna.getUbicacionInterna().setId(Constants.BODEGA_SERVICIO_TECNICO);
//				bitacoraInterna.setClasificacion(new Clasificacion());
//				bitacoraInternaDAO.saveBitacoraInterna(bitacoraInterna);
//			}
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	} 
	
	public void saveRecepcionOTRechazoGuiaAgrupadas(Recepcion recepcion, Ubicacion ubicacion, Usuario usuario, List<Parte> partes, List<Accesorio> accesorios) throws Exception{
		try {
			if(recepcionDAO.getExisteRecepcionOT(recepcion) >= 1) {
				throw new SSTException("Ya se rechazó una recepción de OT con el número de guía "+recepcion.getGuia().getNumero());
			}
			 
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion.getGuia().getOrdenTrabajo().getId());
			Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId());
			bitacora.setFechaSalida(recepcion.getFechaRecepcion());
			Ubicacion origen = ubicacionDAO.get(recepcion.getOrigen().getId());
			ordenTrabajo.setDescripcionFisica(recepcion.getGuia().getOrdenTrabajo().getDescripcionFisica());
			ordenTrabajo.setNumeroSerie(recepcion.getGuia().getOrdenTrabajo().getNumeroSerie());
			sucursalService.updateParteOTConAnteriorObervacion(partes,usuario,ubicacion);
			sucursalService.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion, recepcion.getOrigen(),accesorios);
			
			Gestion gestion = new Gestion();
			gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
			gestion.setUsuario(usuario);			
			gestion.setGestion("  rechaza la recepción de la OT en "+ ubicacion.getId() + " " + ubicacion.getNombre());
			gestionesDAO.saveGestion(gestion);
			
			recepcion.setUsuario(usuario);
			recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_RECHAZADA)); 
			if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_RECHAZADA_EN_FALLA_FABRICACION));
			}
			this.updateOTTareaUrgenteFF(ordenTrabajo);
			ordenTrabajoDAO.updateOTRecepcion(ordenTrabajo); 
			this.updateEstadoOTRecepcionGuiaMasiva(ordenTrabajo, recepcion.getGuia(), ordenTrabajo.getEstado());
			recepcionDAO.saveRecepcionOT(recepcion);
			this.recibirBitacora(origen, ubicacion, bitacora ,ordenTrabajo,recepcion);
			
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Boolean terminarRecepcionMasiva(FilterRecepcionProducto filterRecepcionProducto, Ubicacion ubicacion, Usuario usuario)throws Exception{
		try {
			List<DetalleRecepcion> detallesRecepcion = recepcionMasivaDAO.listDetalleGuiaRecepcion(filterRecepcionProducto);
			RecepcionCamion recepcionCamion = new RecepcionCamion();
			FilterRecepcion filterRecepcion = new FilterRecepcion();
			RecepcionCamionGuia recepcionCamionGuia = new RecepcionCamionGuia();
			
			if(detallesRecepcion.isEmpty()){
				recepcionCamion.setObservacion("Esta recepción no tiene guías asociadas");
			}
			filterRecepcion.setIdRecepcion(filterRecepcionProducto.getIdRecepcionCamion());
			filterRecepcion.setEstadoOT(estadoDAO.getEstadoById(Constants.OT_ACEPTADA_EN_FALLA_FABRICACION));
		
			for(DetalleRecepcion detalleRecepcion: detallesRecepcion){
				Guia guia = guiaAgrupadaDAO.getGuiaAgrupadabyGuia(detalleRecepcion.getGuia());
				if(detalleRecepcion.getCantidadOTRecibidas().equals(detalleRecepcion.getCantidadOTRevisadas())){
					if(guia.getNumeroTO() == null){
						if( !guia.getDestino().getId().equals(Constants.UBICACION_ID_TRANSPORTE)){
							guia.setNumeroTO(owService.receiveTOForUbicacion(guia.getId()));
						} else {
							filterRecepcion.setIdGuia(guia.getId());
							List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTbyFilterRecepcion(filterRecepcion);
							if(ots.size() != 0){
								String clasificacion = ots.get(0).getClasificacion().getCodigo();
								List<OrdenTrabajo> otAgrupadas = bodegaService.groupOtForITAgrupada(ots, ubicacion);
								for(OrdenTrabajo ot : otAgrupadas){
									ot.setNumeroIT(owService.moveITAgrupada(ot, ubicacion.getId(), Constants.UBICACION_RTR_OW, owService.convertUbicaciontoOW(clasificacion)));
									for(OrdenTrabajo otAux : ots){
										if(ot.getProducto().getId().equals(otAux.getProducto().getId())){
											otAux.setNumeroIT(ot.getNumeroIT());
										}
									}
								}
								for (OrdenTrabajo ordenTrabajo : ots) {
									FilterOT filterOT = new FilterOT();
									filterOT.setIdGuiaAgrupada(guia.getId());	
									filterOT.setIdOT(ordenTrabajo.getId());
									Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
									bitacora.setNumeroIT(ordenTrabajo.getNumeroIT());
									bitacoraDAO.updateEstadoBitacora(bitacora);
								}
							} else {
								throw new SSTException("Esta guía no tiene productos asociados en el sistema");
							}
							
						}
						filterRecepcionProducto.setIdGuia(guia.getId());
						recepcionCamionGuia =  recepcionMasivaDAO.getRecepcionGuiaByFilter(filterRecepcionProducto);
						guia.setVigente(true);
						
						if(detalleRecepcion.getCantidadTotalOT() == detalleRecepcion.getCantidadOTRecibidas()) {
							guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_RECIBIDA_COMPLETA));	
							recepcionCamionGuia.setEstadoGuia(guia.getEstado());
						} else {
							guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_RECIBIDA_INCOMPLETA));
							recepcionCamionGuia.setEstadoGuia(guia.getEstado());
						}
						recepcionMasivaDAO.updateCerrarRecepcionMasiva(recepcionCamionGuia);
						guiaDAO.updateEstado(guia);
					}	
				} else {
					throw new SSTException("Aún no se han revisado todas las OT recibidas");
				}
			}
			processGuiasPendientesAgrupadasATransportista(filterRecepcion, ubicacion, usuario);
			
			recepcionCamion.setId(filterRecepcionProducto.getIdRecepcionCamion());
			recepcionCamion.setEstado(new Estado());
			recepcionCamion.getEstado().setId(Constants.RECEPCION_MASIVA_EN_TERMINADA);
			recepcionMasivaDAO.updateTerminoRecepcionMasiva(recepcionCamion);
			
			return true;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	//TODO metodo para recepcionar to normal
//	public Boolean terminarRecepcionMasiva(FilterRecepcionProducto filterRecepcionProducto, Ubicacion ubicacion, Usuario usuario)throws Exception{
//		try {
//			FilterOT filterOT = new FilterOT();
//			List<DetalleRecepcion> detallesRecepcion = recepcionMasivaDAO.listDetalleGuiaRecepcion(filterRecepcionProducto);
//			RecepcionCamion recepcionCamion = new RecepcionCamion();
//			FilterRecepcion filterRecepcion = new FilterRecepcion();
//			RecepcionCamionGuia recepcionCamionGuia = new RecepcionCamionGuia();
//			
//			if(detallesRecepcion.isEmpty()){
//				recepcionCamion.setObservacion("Esta recepción no tiene guías asociadas");
//			}
//			filterRecepcion.setIdRecepcion(filterRecepcionProducto.getIdRecepcionCamion());
//			filterRecepcion.setEstadoOT(estadoDAO.getEstadoById(Constants.OT_ACEPTADA_EN_FALLA_FABRICACION));
//			List<OrdenTrabajo> ots=ordenTrabajoDAO.listOTbyFilterRecepcion(filterRecepcion);
//		
//			for(DetalleRecepcion detalleRecepcion: detallesRecepcion){
//				Guia guia = guiaAgrupadaDAO.getGuiaAgrupadabyGuia(detalleRecepcion.getGuia());
//				if(detalleRecepcion.getCantidadOTRecibidas().equals(detalleRecepcion.getCantidadOTRevisadas())){
//						if( !guia.getDestino().getId().equals(Constants.UBICACION_TRANSPORTE)){
//							guia.setNumeroTO(owService.receiveTO(guia.getId()));						
////							guia.setNumeroTO(owService.receiveTOForUbicacion(guia.getId()));
//						} 
//						else{
//							for(OrdenTrabajo ot : ots){
//								ot = ordenTrabajoDAO.getOTById(ot.getId());
//								if(ot.getEstado().getId().equals(Constants.OT_RECHAZADA_EN_FALLA_FABRICACION)){
//									filterOT.setIdGuiaAgrupada(guia.getId());	
//									filterOT.setIdOT(ot.getId());
//									Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
//									bitacora.setNumeroIT(owService.moveIT(ot, ubicacion.getId(), Constants.UBICACION_RTR_OW, OWConstants.UBICACION_FDP));
//									bitacoraDAO.updateEstadoBitacora(bitacora);
//								}
//							}
//						}
//						filterRecepcionProducto.setIdGuia(guia.getId());
//						recepcionCamionGuia =  recepcionMasivaDAO.getRecepcionGuiaByFilter(filterRecepcionProducto);
//						guia.setVigente(true);
//						
//						if(detalleRecepcion.getCantidadTotalOT() == detalleRecepcion.getCantidadOTRecibidas()) {
//						guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_RECIBIDA_COMPLETA));	
//						recepcionCamionGuia.setEstadoGuia(guia.getEstado());
//						} else {
//							guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_RECIBIDA_FALTANTE));
//							recepcionCamionGuia.setEstadoGuia(guia.getEstado());
//						}
//						recepcionMasivaDAO.updateCerrarRecepcionMasiva(recepcionCamionGuia);
//						guiaDAO.updateEstado(guia);
//				} else {
//					throw new SSTException("Aún no se han revisado todas las OT recibidas");
//				}
//			}
//			
//			
//			for(OrdenTrabajo ot : ots){
//				ot = ordenTrabajoDAO.getOTById(ot.getId());
//				if(!ot.getClasificacion().getCodigo().equals("RDEFP")){
//					BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getUltimaBitacoraInternaByIdOT(ot.getId());
//					bitacoraInterna.setNumeroIT(owService.moveIT(ot, ubicacion.getId(),OWConstants.UBICACION_FDP,owService.convertUbicaciontoOW(ot.getClasificacion().getCodigo())));
//					bitacoraInternaDAO.updateNumeroItByIdBitacoraInterna(bitacoraInterna);
//				}
//			}
//			
//			processGuiasPendientesAgrupadasATransportista(filterRecepcion, ubicacion, usuario);
//			
//			recepcionCamion.setId(filterRecepcionProducto.getIdRecepcionCamion());
//			recepcionCamion.setEstado(new Estado());
//			recepcionCamion.getEstado().setId(Constants.RECEPCION_MASIVA_EN_TERMINADA);
//			recepcionMasivaDAO.updateTerminoRecepcionMasiva(recepcionCamion);
//			
//			return true;
//		} catch (SSTException e) {
//			throw e;
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	
	
	public Guia processGuiasPendientesAgrupadasATransportista(FilterRecepcion filterRecepcion, Ubicacion ubicacion, Usuario usuario) throws Exception {
		try {
			List<OrdenTrabajo> ordenesRecepcion = new ArrayList<OrdenTrabajo>();
			//OrdenTrabajo otRecepcionAux  = new OrdenTrabajo();
			RecepcionCamion recepcionCamion= recepcionMasivaDAO.getRecepcionCamionById(filterRecepcion.getIdRecepcion());
			Transportista transportista = recepcionCamion.getTransportista();
			Guia guia = null;
			filterRecepcion.setEstadoOT(new Estado());
			
			filterRecepcion.getEstadoOT().setId(Constants.OT_RECHAZADA_EN_FALLA_FABRICACION);
			ordenesRecepcion.addAll(ordenTrabajoDAO.listOTbyFilterRecepcion(filterRecepcion));
			filterRecepcion.getEstadoOT().setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);
			ordenesRecepcion.addAll(ordenTrabajoDAO.listOTbyFilterRecepcion(filterRecepcion));
			
			if (!ordenesRecepcion.isEmpty()) {
				guia = this.saveGuiaParaAgrupacion(ubicacion, usuario, ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),transportista);
				
				Bitacora bitacora = new Bitacora();
				Integer productos = 0;
				Producto productoAnterior = new Producto();
				productoAnterior.setId(0);
				
				for (OrdenTrabajo ordenTrabajo : ordenesRecepcion) {
					ordenTrabajo = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
					if (!productoAnterior.getId().equals(ordenTrabajo.getProducto().getId())) {
						productos ++;
						productoAnterior = ordenTrabajo.getProducto();
						if (productos.equals(11)){
							productos = 0;
							guia = this.saveGuiaParaAgrupacion(ubicacion, usuario,ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),transportista);
						}				
					}
					if (ordenTrabajo.getEstado().getId().equals(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR)) {
						Recepcion recepcion = new Recepcion();
						recepcion.setGuia(getGuiaRecepcionAgrupada(ordenTrabajo.getId(), ubicacion));
//						recepcion.setGuia(guiaDAO.getGuiaByIdOT(ordenTrabajo.getId()));
						recepcion.setFechaRecepcion(utilDAO.getDate());
						recepcion.setObservacion("OT no recibida en destino, se devuelve a transporte");
						recepcion.setUbicacion(ubicacion);
						recepcion.setOrigen(ubicacionDAO.getUbicacionOT(ordenTrabajo.getId()));
						recepcion.getGuia().setOrdenTrabajo(ordenTrabajo);
						
						List<Accesorio> accesorios = accesorioDAO.listAccesoriosByOT(ordenTrabajo.getId());
						List<Parte> partes = parteDAO.listPartesOTByOT(ordenTrabajo.getId());
						
						this.saveRecepcionOTRechazoGuiaAgrupadas(recepcion, ubicacion, usuario, partes, accesorios);
					}
					
					bitacora.setGuia(guia);
					bitacora.setOrdenTrabajo(ordenTrabajo);
					bitacora.setUbicacion(ubicacion);
					bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				}	
			}
			
			return guia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private Guia saveGuiaParaAgrupacion(Ubicacion origen, Usuario usuario, Ubicacion destino,Transportista transportista) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guia.setOrigen(origen);
			guia.setDestino(destino);
			guia.setEntregaCliente(false);
			guia.setVigente(true);
			guia.setUsuario(usuario);
			guia.setTransportista(transportista);
			guia.setTipoGuia(Constants.GUIA_TIPO_AGRUPADA);
			guiaDAO.save(guia);
			return guia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveRecepcionOT(Recepcion recepcion,Boolean aceptarRecepcion, Usuario usuario, Ubicacion ubicacion, List<Parte> partes, List<Accesorio> accesorios)throws Exception {
		try {
			SSTException exceptionGM = null;
			Bitacora bitacora = null;
			if(recepcionDAO.getExisteRecepcionOT(recepcion) >= 1) {
				throw new SSTException("Ya existe una recepción de OT con el número de guía - " + recepcion.getGuia().getNumero());
			}
			Guia guiaRecibida = guiaDAO.get(recepcion.getGuia().getId());
			Ubicacion ubicacionOT = ubicacionDAO.getUbicacionOT(recepcion.getGuia().getOrdenTrabajo().getId());
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion.getGuia().getOrdenTrabajo().getId());
			bitacora = bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId());
			
			bitacora.setFechaSalida(recepcion.getFechaRecepcion());
			
			if (guiaRecibida == null && ubicacionOT.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)){
				guiaRecibida = new Guia();
				guiaRecibida.setOrigen(ubicacionOT);
				guiaRecibida.setDestino(ubicacion);
			} else {
				if (guiaRecibida.getProcesadoOW()) {
					guiaRecibida.setNumeroTO(owService.receiveTO(guiaRecibida.getId()));					
				}
				guiaRecibida.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
				guiaDAO.updateEstado(guiaRecibida);
			}
			
			ordenTrabajo.setDescripcionFisica(recepcion.getGuia().getOrdenTrabajo().getDescripcionFisica());
			ordenTrabajo.setNumeroSerie(recepcion.getGuia().getOrdenTrabajo().getNumeroSerie());
			recepcion.setUbicacion(ubicacion);
			recepcion.setOrigen(ubicacionDAO.get(guiaRecibida.getOrigen().getId()));
			recepcion.setUsuario(usuario);
			
			if(aceptarRecepcion){
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA)); 
				if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)) {
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_EN_CD_RECEPCION_ACEPTADA));
				} else if(ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_EN_BR_RECEPCION_ACEPTADA));
				} else if (ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_EN_SUCURSAL));
				} 
			} else {
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA_CON_OBSERVACION)); 
				Gestion gestion = new Gestion();
				gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
				gestion.setUsuario(usuario);			
				gestion.setGestion("  Acepta con observación la recepcion de la OT en "+ ubicacion.getId() + " " + ubicacion.getNombre());
				gestionesDAO.saveGestion(gestion);
				try {
					sucursalService.updateOTTareaUrgente(ordenTrabajo);
				} catch (SSTException e) {
					exceptionGM = e;
				}
				if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_EN_CD_RECEPCION_CON_OBSERVACION));
				} else if(ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_EN_BR_RECEPCION_CON_OBSERVACION));
				} else if (ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
					ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_CON_OBSERVACION_EN_SUCURSAL));
				} 
			} 
			sucursalService.updateParteOTConAnteriorObervacion(partes,usuario,ubicacion);
			ordenTrabajoDAO.updateOTRecepcion(ordenTrabajo);  
			sucursalService.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion, recepcion.getOrigen(),accesorios);
			recepcionDAO.saveRecepcionOT(recepcion);
			this.recibirBitacora(recepcion.getOrigen(), ubicacion, bitacora, ordenTrabajo, recepcion);
			
			if (ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL) && ordenTrabajo.getCambioAutorizado()) {
				Cambio cambio = cambioDAO.getCambioByOT(ordenTrabajo.getId());
				if (!(ordenTrabajo.getCambioAutorizado() && (ordenTrabajo.getTipo().getCodigo().equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR) || ordenTrabajo.getTipo().getCodigo().equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR_CF)) || (ordenTrabajo.getTipo().getCodigo().equals(Constants.TIPO_OT_GARANTIA_MASTER) && cambio.getOt().getEmpresaFacturar().getNombre().equals(Constants.FACTURAR_TIPO_TRANSPORTISTA)))) {
					Guia guia = new Guia();
					guia.setOrdenTrabajo(ordenTrabajo); 
					guia.setOrigen(ubicacion);
					guia.setDestino(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA));
					guia.setEntregaCliente(false);
					guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
					guia.setVigente(true);
					guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
					guiaDAO.save(guia);
					
					for(Accesorio accesorio : accesorios){
						if(accesorio.getRecibido() == true){
							accesorio.setGuia(guia);
							accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
						}
					}
					
					Bitacora bitacoraParaAsignar = new Bitacora();
					bitacoraParaAsignar.setGuia(guia);
					bitacoraParaAsignar.setOrdenTrabajo(ordenTrabajo);
					bitacoraParaAsignar.setUbicacion(ubicacion);				
					bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				}
			} 
			
			if (ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) || ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
				if (!(ordenTrabajo.getCambioAutorizado())) {  
						Guia guia = new Guia();
						guia.setOrdenTrabajo(ordenTrabajo);
						guia.setEntregaCliente(false);
						guia.setEstado(new Estado());
						guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
						guia.setVigente(true);
						guia.setOrigen(ubicacion);
						guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
						
						if (recepcion.getOrigen().getTipo().equals(Constants.UBICACION_SUCURSAL)) {
							ServicioTecnico servicioTecnico = servicioTecnicoDAO.getServicioTecnicoByOT(ordenTrabajo.getId());
							if(servicioTecnico == null) {
								FilterServicioTecnico filter = new FilterServicioTecnico();
								filter.setIdOrigen(ubicacion.getId());
								filter.setIdOT(ordenTrabajo.getId());
								filter.setIdProducto(ordenTrabajo.getProducto().getId());
								filter.setTipoOT(ordenTrabajo.getTipo().getCodigo());
								
								List<ServicioTecnico> servicioTecnicos = servicioTecnicoDAO.listSTecnicoByFilter(filter);
								if (servicioTecnicos != null && servicioTecnicos.size() > 0)
									servicioTecnico = servicioTecnicos.get(0);
								guia.setDestino(new Ubicacion(servicioTecnico));
								guia.getOrdenTrabajo().setsTecnico(servicioTecnico);
								ordenTrabajoDAO.updateUbicacionSTecnico(guia.getOrdenTrabajo());
							} else {
								guia.setDestino(new Ubicacion(servicioTecnico));
							}
						} else if (recepcion.getOrigen().getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
							guia.setDestino(ubicacionDAO.get(ordenTrabajo.getSucursal().getId().longValue()));
						}
						
						guiaDAO.save(guia);
						
						for(Accesorio accesorio : accesorios){
							if(accesorio.getRecibido() == true){
								accesorio.setGuia(guia);
								accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
							}
						}
						
						Bitacora bitacoraNueva = new Bitacora();
						bitacoraNueva.setGuia(guia);
						bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
						bitacoraNueva.setUbicacion(ubicacion);
						bitacoraDAO.updateAsignaBitacoraAGuia(bitacoraNueva);
						
						//SI NO ES UN PRODUCTO ECLUIDO DE CC CREAR LA BITACORA INTERNA Y ENVIAR A RDEST
//						if(!this.isExcluidoControlCalidadByIdProducto(ordenTrabajo.getProducto().getId())){
//							this.sendToControlCalidad(ordenTrabajo);
//						}
						
						BitacoraInterna bitacoraInterna = new BitacoraInterna();
						bitacoraInterna.setBitacora(bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId()));
						bitacoraInterna.setOrdenTrabajo(ordenTrabajo);
						bitacoraInterna.setEstado(new Estado());
						bitacoraInterna.getEstado().setId(Constants.BITACORA_INTERNA_OT_UBICACION_INTERNA_SERVICIO_TECNICO);
						bitacoraInterna.setUbicacionInterna(new UbicacionInterna());
						bitacoraInterna.getUbicacionInterna().setId(Constants.BODEGA_SERVICIO_TECNICO);
						bitacoraInterna.setClasificacion(new Clasificacion());
						bitacoraInternaDAO.saveBitacoraInterna(bitacoraInterna);
						
					} else {
						if (ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
							Guia guia = new Guia();
							guia.setOrdenTrabajo(ordenTrabajo);
							guia.setEntregaCliente(false);
							guia.setEstado(new Estado());
							guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
							guia.setVigente(true);
							guia.setOrigen(ubicacion);
							guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
							guia.setDestino(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA));
							
							guiaDAO.save(guia);
							
							for(Accesorio accesorio : accesorios){
								if(accesorio.getRecibido() == true){
									accesorio.setGuia(guia);
									accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
								}
							}					
						}
					}
				} else if(ordenTrabajo.getTipo().getCodigo().equals(Constants.TIPO_OT_GARANTIA_MASTER)){
					 throw new SSTException("La recepción se guardo con exito, debe guardar el número de factura del producto cambiado antes de enviar a remate");
				}	
			
			if(exceptionGM != null){
				throw new SSTException("La orden de trabajo está recepcionada, pero no tiene una ejecutiva de marca asignada, contactese con la ejecutiva supervisora");
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) { 
			log.error(e, e);
			throw e;
		}
	}
	
	public void saveRecepcionGuiaAccesorios(Recepcion recepcion,Boolean aceptarRecepcion, Usuario usuario, Ubicacion ubicacion, List<Parte> partes, List<Accesorio> accesorios)throws Exception {
		try {
			SSTException exceptionGM = null;
			if(recepcionDAO.getExisteRecepcionOT(recepcion) >= 1) {
				throw new SSTException("Ya existe una recepción de OT con el número de guía - " + recepcion.getGuia().getNumero());
			}
			Guia guiaRecibida = guiaDAO.get(recepcion.getGuia().getId());
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion.getGuia().getOrdenTrabajo().getId());
			Bitacora bitacora = bitacoraDAO.getUltimaBitacoraAccesorio(ordenTrabajo.getId());
			bitacora.setFechaSalida(recepcion.getFechaRecepcion());
			bitacoraDAO.updateFechaSalidaBitacoraAccesorio(bitacora);
			guiaRecibida.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
		
			recepcion.setUbicacion(ubicacion);
			recepcion.setOrigen(ubicacionDAO.get(guiaRecibida.getOrigen().getId()));
			recepcion.setUsuario(usuario);
			
			if (guiaRecibida.getProcesadoOW()) {
				guiaRecibida.setNumeroTO(owService.receiveTO(guiaRecibida.getId()));					
			}
			guiaDAO.updateEstado(guiaRecibida);
			
			
			if(aceptarRecepcion){
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA)); 
			} else {
				recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_ACEPTADA_CON_OBSERVACION)); 
				Gestion gestion = new Gestion();
				gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
				gestion.setUsuario(usuario);			
				gestion.setGestion("  Acepta con observación la recepcion de la Guía de Accesorios en "+ ubicacion.getId() + " " + ubicacion.getNombre());
				gestionesDAO.saveGestion(gestion);
				try {
					sucursalService.updateOTTareaUrgente(ordenTrabajo);
				} catch (SSTException e) {
					exceptionGM = e;
				}
			} 
			
			recepcionDAO.saveRecepcionOT(recepcion);
			
			if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)){
				Bitacora bitacoraNueva = new Bitacora();
				bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRO_DE_DISTRIBUCION));
				bitacoraNueva.setRecepcion(recepcion);
				bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
				bitacoraNueva.setUbicacion(ubicacion);
				bitacoraNueva.setFechaEntrada(recepcion.getFechaRecepcion());
				bitacoraNueva.setFechaSalida(recepcion.getFechaRecepcion());
				bitacoraDAO.saveBitacoraAccesorios(bitacoraNueva);
				for(Accesorio accesorio: accesorios){
					accesorio.setRecibidoCd(accesorio.getRecibido());
				}
			} else if(ubicacion.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
				Guia guiaAux = new Guia();
				GuiaAccesorios guiaAccesorios = new GuiaAccesorios();

				guiaAux.setOrdenTrabajo(ordenTrabajo);
				guiaAux.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
				guiaAux.setOrigen(ubicacion);
				guiaAux.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
				guiaAux = guiaDAO.getGuiaByGuia(guiaAux);
				
				if (guiaAux == null) {
					guiaAux = saveGuiaAccesorio(ordenTrabajo);
					
					Bitacora bitacoraNueva = new Bitacora();
					bitacoraNueva.setGuia(guiaAux);
					bitacoraNueva.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_BODEGA_REGIONAL_DESPUES_ENTREGA_AL_CAMION_AL_CD));
					bitacoraNueva.setRecepcion(recepcion);
					bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
					bitacoraNueva.setUbicacion(ubicacion);
					bitacoraNueva.setFechaEntrada(recepcion.getFechaRecepcion());
					bitacoraNueva.setFechaSalida(recepcion.getFechaRecepcion());
					bitacoraDAO.saveBitacoraAccesorios(bitacoraNueva);
				}
				
				guiaAccesorios.setGuia(guiaAux);
				sucursalService.asignarGuiaToAccesorios(guiaAux, accesorios);
				
			}
			
			sucursalService.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion, recepcion.getOrigen(),accesorios);
			
			if(exceptionGM != null){
				throw new SSTException("La orden de trabajo está recepcionada, pero no tiene una ejecutiva de marca asignada, contactese con la ejecutiva supervisora");
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) { 
			log.error(e, e);
			throw e;
		}
	}
	
	public Guia saveGuiaAccesorio(OrdenTrabajo ot) throws Exception{
		try {
			Guia guiaAccesorios = new Guia();
			guiaAccesorios.setOrdenTrabajo(ot);
			guiaAccesorios.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
			guiaAccesorios.setDestino(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA));
			guiaAccesorios.setOrigen(ot.getUbicacion());
			guiaAccesorios.setVigente(true);
			guiaAccesorios.setEntregaCliente(false);
			guiaAccesorios.setTipoGuia(Constants.GUIA_TIPO_ACCESORIO);
			guiaDAO.save(guiaAccesorios);
			return guiaAccesorios;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void saveRecepcionOTRechazo(Recepcion recepcion, Boolean tareaEjecutiva, Ubicacion ubicacion, Usuario usuario, List<Parte> partes, List<Accesorio> accesorios)throws Exception {
		try {
			if(recepcionDAO.getExisteRecepcionOT(recepcion) >= 1) {
				throw new SSTException("Ya se rechazo una recepción de OT con el numero de guía "+recepcion.getGuia().getNumero());
			}
			Ubicacion ubicacionOT = ubicacionDAO.getUbicacionOT(recepcion.getGuia().getOrdenTrabajo().getId());
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion.getGuia().getOrdenTrabajo().getId());
			Guia guiaRecibida =  guiaDAO.get(recepcion.getGuia().getId());
			Bitacora bitacora = bitacoraDAO.getUltimaBitacora(ordenTrabajo.getId());
			bitacora.setFechaSalida(recepcion.getFechaRecepcion());
			Ubicacion origen = ubicacionDAO.get(recepcion.getOrigen().getId());
			sucursalService.updateParteOTConAnteriorObervacion(partes,usuario,ubicacion);
			sucursalService.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion, recepcion.getOrigen(),accesorios);
			
			if (guiaRecibida == null && ubicacionOT.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
				guiaRecibida = new Guia();
				guiaRecibida.setOrigen(ubicacionOT);
				guiaRecibida.setDestino(ubicacion);
				guiaRecibida.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
			} else {
//				if (guiaRecibida.getProcesadoOW()) {
//					guiaRecibida.setNumeroTO(owService.receiveTO(guiaRecibida.getId()));					
//				}
				guiaRecibida.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
				guiaDAO.updateEstado(guiaRecibida);		 
			}
			
			Gestion gestion = new Gestion();
			gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
			gestion.setUsuario(usuario);			
			gestion.setGestion("  rechaza la recepción de la OT en "+ ubicacion.getId() + " " + ubicacion.getNombre());
			gestionesDAO.saveGestion(gestion);
			
			if(ubicacion.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)){
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_CENTRO_DE_DISTRIBUCION));
			} else if(ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)){						
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_SUCURSAL));
			} else {
				ordenTrabajo.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_BODEGA));
			}
			
			recepcion.setUsuario(usuario);
			recepcion.setEstado(estadoDAO.getEstadoById(Constants.RECEPCION_OT_RECHAZADA));
			recepcionDAO.saveRecepcionOT(recepcion);
			
			recibirBitacora(origen, ubicacion, bitacora ,ordenTrabajo,recepcion);
			ordenTrabajoDAO.updateOTRecepcion(ordenTrabajo);
			
			Guia guiaNueva = new Guia();
			guiaNueva.setOrdenTrabajo(ordenTrabajo);
			if(tareaEjecutiva == true){
				guiaNueva.setDestino(null);
			} else { 
				guiaNueva.setDestino(recepcion.getOrigen());
			}
			guiaNueva.setOrigen(ubicacion); 
			guiaNueva.setEntregaCliente(false);
			guiaNueva.setVigente(true);				
			guiaNueva.setEstado(new Estado());
			guiaNueva.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guiaNueva.setTipoGuia(guiaRecibida.getTipoGuia());
			guiaDAO.save(guiaNueva);
			
			for(Accesorio accesorio : accesorios){
				if(accesorio.getRecibido() == true){
					accesorio.setGuia(guiaNueva);
					accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
				}
			}
		
			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setGuia(guiaNueva);
			bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
			bitacoraNueva.setUbicacion(ubicacion);
			bitacoraDAO.updateAsignaBitacoraAGuia(bitacoraNueva);
			
			try {
				sucursalService.updateOTTareaUrgente(ordenTrabajo);
			} catch (Exception e) {
				if(tareaEjecutiva == true){
					throw new SSTException("La recepción se rechazo exitosamente, pero la orden de trabajo no tiene una ejecutiva de marca asignada, contactese con la ejecutiva supervisora");
				}
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	
	
	public OrdenTrabajo getOTRecibeSucursal(FilterOT filter, Ubicacion ubicacion) throws Exception {
		try {
			filter.setIdOrigen(ubicacion.getId());
			filter.setIdDestino(ubicacion.getId());
			filter.setVigente(true);
			OrdenTrabajo orden = sucursalService.getOTByFilter(filter);
			
			if (ubicacionEsperaGuiaProducto(orden, ubicacion)) {
				if (orden.getEstado().getId().equals(Constants.OT_EN_ST_CON_CONTRATO)) {
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_EN_ST_SIN_CONTRATO)) {
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR)) {
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_CERRADA_POR_CLIENTE_POR_PRODUCTO_CAMBIADO)) {
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_EN_CD_RECEPCION_ACEPTADA)) {
					return orden;
				}
			}
			if (ubicacionEsperaGuiaAccesorios(orden, ubicacion)) {
				if(sucursalService.getGuiaRecepcion(orden.getId(), Constants.GUIA_TIPO_ACCESORIO, ubicacion) != null){
					return ordenTrabajoDAO.getOTById(orden.getId());
				}
			}
			throw new SSTException("La orden de trabajo tiene el estado : " + orden.getEstado().getDescripcion());
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Boolean ubicacionEsperaGuiaProducto(OrdenTrabajo ordenTrabajo, Ubicacion ubicacion) throws Exception {
		try {
			Guia guia = guiaDAO.getUltimaGuiaByOT(ordenTrabajo.getId());
			if (guia != null) {
				if (guia.getDestino().getId().equals(ubicacion.getId())) {
					return true;
				} else if (guia.getOrigen().getId().equals(ubicacion.getId()) && guia.getDestino().getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					return true;
				}
			}
			return false;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Boolean ubicacionEsperaGuiaAccesorios(OrdenTrabajo ordenTrabajo, Ubicacion ubicacion) throws Exception {
		try {
			Guia guia = guiaDAO.getUltimaGuiaAccesoriosByOT(ordenTrabajo.getId());
			if (guia != null) {
				if (guia.getDestino().getId().equals(ubicacion.getId())) {
					return true;
				}
			}
			return false;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	
	public OrdenTrabajo getOTRecepcionBodegaByFilter(FilterOT filter)throws Exception {
		try {
			List<OrdenTrabajo> ordenes = ordenTrabajoDAO.listByFilter(filter);
			if (ordenes == null || ordenes.size() == 0){
				OrdenTrabajo ot = ordenTrabajoDAO.getOTbyCodigoAccesorio(filter.getCodigoBarra());
				if (ot != null) {
					ordenes.add(ordenTrabajoDAO.getOTbyCodigoAccesorio(filter.getCodigoBarra()));					
				} else {
					throw new SSTException("No hay ordenes con los parametros ingresados");					
				}
			} 

			if (ordenes.size() > 1){
				throw new SSTException("Existe mas de una ot que cumple las condiciones");
			}
			return ordenTrabajoDAO.getOTById(ordenes.get(0).getId());

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public OrdenTrabajo getOTRecibeBodega(FilterOT filter, Ubicacion ubicacion) throws Exception {
		try {
			filter.setIdDestino(ubicacion.getId());
			filter.setIdOrigen(ubicacion.getId());
			filter.setVigente(true);
			OrdenTrabajo orden = this.getOTRecepcionBodegaByFilter(filter);
			Guia guia = guiaDAO.getUltimaGuiaByOT(orden.getId());
			
			Boolean ubicacionEsperaGuiaProducto   = this.ubicacionEsperaGuiaProducto(orden, ubicacion);
			Boolean ubicacionEsperaGuiaAccesorios = this.ubicacionEsperaGuiaAccesorios(orden, ubicacion);
			
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaById(Constants.BODEGA_SERVICIO_TECNICO);
			if(ubicacionInterna.getInventario() && ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)){
				throw new SSTException("La orden de trabajo no se puede recepcionar debido a que la ubicación interna Bodega servicio técnico está en inventario");
			}
			
			if (!ubicacionEsperaGuiaProducto && !ubicacionEsperaGuiaAccesorios) {
				throw new SSTException("La orden de trabajo debe ser recepcionada por otra ubicación");
			}
			
			if(guia != null && guia.getEstado() != null && guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA)) {
				throw new SSTException("La guía de despacho tiene el estado : " + guia.getEstado().getDescripcion());
			}
			
			if (ubicacionEsperaGuiaProducto) {
				if (orden.getEstado().getId().equals(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR)){
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_EN_ST_CON_CONTRATO)){
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_EN_ST_SIN_CONTRATO)){
					return orden;
				} else if (orden.getEstado().getId().equals(Constants.OT_CERRADA_POR_CLIENTE_POR_PRODUCTO_CAMBIADO)){
					return orden;
				}
//				} else if (orden.getEstado().getId().equals(Constants.OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_ACEPTADO)){
//					return orden;
//				} else if (orden.getEstado().getId().equals(Constants.OT_EN_BODEGA_CON_CONTROL_DE_CALIDAD_RECHAZADO)){
//					return orden;
//				}
			}
			if (ubicacionEsperaGuiaAccesorios) {
				if(sucursalService.getGuiaRecepcion(orden.getId(), Constants.GUIA_TIPO_ACCESORIO, ubicacion) != null){
					return orden;
				}
			}
			throw new SSTException("La orden de trabajo tiene el estado : " + orden.getEstado().getDescripcion());
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listGuiasPendientesAgrupadasFF(Ubicacion ubicacion, GridControl gridControl)throws Exception {
		try {
			ListRange listRange = new ListRange();
			FilterGuiasPendientes filter = new FilterGuiasPendientes();
			filter.setIdUbicacion(ubicacion.getId());
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(guiaAgrupadaDAO.listGuiasAgrupadasPendientesFF(filter,gridControl));
			listRange.setTotal(guiaAgrupadaDAO.getTotalGuiasAgrupadasPendientesFF(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void emitirGuiaAgrupadaDestinoTransportista(Guia guia, Ubicacion ubicacion)throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
			
			FilterAccesorio filterAccesorio = new FilterAccesorio();
			filterAccesorio.setIdUbicacion(Constants.UBICACION_ID_TRANSPORTE);
			
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			guia.getEstado().setId(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA); 
			
			this.validaStockAgrupado(guia.getOrigen(), Constants.UBICACION_RTR_OW, guia);
			
			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");
			
			for (OrdenTrabajo ordenTrabajo : ots) {
				filterAccesorio.setIdOT(ordenTrabajo.getId());
				sucursalService.updateUbicacionAccesorios(guia,ordenTrabajo, ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE), guia.getOrigen());
				ordenTrabajo.setEstado(new Estado());
				ordenTrabajo.getEstado().setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);
				filterOT.setIdOT(ordenTrabajo.getId());
				Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
				sucursalService.saveBitacoraByGuiaAndOT(guia,ordenTrabajo, bitacora);
			}
			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Guia reEmitirGuiaAgrupadaFF(Guia guia, Usuario usuario, Ubicacion origen) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT);
			
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			
			Guia guiaNueva = saveGuiaParaAgrupacion(origen, usuario,ubicacionDAO.get(guia.getDestino().getId()),guia.getTransportista());
			
			for (OrdenTrabajo ordenTrabajo : ots) {
				filterOT.setIdOT(ordenTrabajo.getId());
				ordenTrabajo.setEstado(new Estado());
				ordenTrabajo.getEstado().setId(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_CENTRO_DE_DISTRIBUCION);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);

				Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(null);
				bitacora.setGuia(guiaNueva);
				bitacora.setUbicacion(origen);
				
				bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalida(bitacora);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
				
				if (guia.getDestino().getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					sucursalService.updateUbicacionAccesorios(guia,ordenTrabajo, guia.getOrigen(), guia.getDestino());
				} else {
					sucursalService.updateUbicacionAccesorios(guia,ordenTrabajo, guia.getOrigen(), ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));
				}
				
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
	
	public Guia getGuiaVigenteByIdOT(Long idOT) throws Exception {
		try {
			return guiaDAO.getGuiaVigenteByIdOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Recepcion getRecepcionByGuia(Guia guia)throws Exception{
		try {
			return recepcionDAO.getRecepcionByGuia(guia);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer getTotalGuiasPendientesBySucursalSinEmitir(FilterGuiasPendientes filterGuiasPendientes, Ubicacion ubicacion) throws Exception {
		try {
			filterGuiasPendientes.setIdUbicacion(ubicacion.getId());
			filterGuiasPendientes.setIdEstadoGuia(Constants.GUIA_ESTADO_POR_EMITIR);
			return guiaDAO.getTotalGuiasPendientesBySucursal(filterGuiasPendientes);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer getTotalGuiasAgrupadasPendientesSinEmitir(Ubicacion ubicacion, FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		try {
			filterGuiasPendientes.setIdUbicacion(ubicacion.getId());
			return guiaAgrupadaDAO.getTotalGuiasAgrupadasPendientesSinEmitir(filterGuiasPendientes);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Guia confirmarEmisionGuia(Long idOT, Guia guia, Usuario usuario) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(idOT);
			guia = guiaDAO.get(guia.getId());
			Ubicacion destino = ubicacionDAO.get(guia.getDestino().getId());
			
			if (!guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA))
				throw new SSTException("La guía no se encuentra en estado. POR CONFIRMAR RECEPCIÓN");
			
			
			
			if (destino.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)) {
				guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA));
			} else if (destino.getTipo().equals(Constants.UBICACION_CLIENTE)) {
				guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
			} else {
				guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA));
			}
			
			guia.setVigente(true);
			
			if (ordenTrabajo.getProcesadoOW()) {
				//TODO DESCOMENTAR HOY!!!!
				guia.setProcesadoOW(true);
				guia.setNumeroTSTO(owService.createTSTO(guia.getId()));
				if(!guia.getDestino().getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)){ 
					ordenTrabajo.setNumeroCargo(guia.getNumero());
					ordenTrabajoDAO.updateNumeroCargo(ordenTrabajo);
				}
			}
			guiaDAO.updateEstado(guia);
			
			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public Guia confirmarEmisionGuiaAgrupada(Guia guia, Usuario usuario,Ubicacion ubicacion) throws Exception {
		try {
			guia = guiaDAO.get(guia.getId());
//			FilterOT filterOT = new FilterOT();
			if (!guia.getEstado().getId().equals(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA))
				throw new SSTException("La guía no se encuentra en estado. POR CONFIRMAR RECEPCIÓN");
			
			guia.setEstado(estadoDAO.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA));
			guia.setVigente(true);
			
			Boolean procesadas = true;
			List<OrdenTrabajo> ots = ordenTrabajoDAO.listOTByIdGuiaAgrupada(guia.getId());
			for(OrdenTrabajo ot:ots){
				ot = ordenTrabajoDAO.getOTById(ot.getId());
				if( ot.getProcesadoOW() == null || ot.getProcesadoOW() == false){
					procesadas= false;
				}
			}
			
			if(procesadas && !guia.getDestino().getId().equals(Constants.UBICACION_ID_TRANSPORTE) ){
				//TODO DESCOMENTAR HOY!!!!
				guia.setProcesadoOW(true);
				if(guia.getDestino().getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)){
					guia.setNumeroTSTO(owService.createTSTO(guia.getId()));
				}else{
					guia.setNumeroTSTO(owService.createTSTO(guia.getId(),Constants.UBICACION_STA_OW," "));
				}
			}else if(procesadas && guia.getDestino().getId().equals(Constants.UBICACION_ID_TRANSPORTE) ){
//				for(OrdenTrabajo ot:ots){
//					filterOT.setIdGuiaAgrupada(guia.getId());	
//					filterOT.setIdOT(ot.getId());
//					Bitacora bitacora = bitacoraDAO.getByIdGuiaAgrupadaAndOT(filterOT);
//					bitacora.setNumeroIT(owService.moveIT(ot, ubicacion.getId(), OWConstants.UBICACION_FDP, Constants.UBICACION_RTR_OW));
//					bitacoraDAO.updateEstadoBitacora(bitacora);
//				}
			}
			guiaDAO.updateEstado(guia);
			return guia;
		} catch (SSTException e) {
 			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void emitirEnvioSinGuia(List<OrdenTrabajo> oTs, Ubicacion destino, Usuario usuario, Ubicacion ubicacion, Date fechaEmision) throws Exception {
		try {	
			for(OrdenTrabajo oT : oTs){
				Guia guia=guiaDAO.getGuiaSinEmitirByIdOT(oT.getId());
				oT = ordenTrabajoDAO.getOTById(oT.getId());
				ServicioTecnico servicioTecnico = (oT.getsTecnico()!=null?oT.getsTecnico():null);
				guia.setNumero(oT.getId());
				guia.setDestino(destino);
				guia.setObservacion(Constants.GUIA_ENVIADA_SIN_EMISION_GUIA);
				guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
				guia.setFechaEmision(fechaEmision);
				sucursalService.emitirGuia(oT.getId(), guia, servicioTecnico, usuario, ubicacion);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<OrdenTrabajo> listOTByGuiaRemate(Long idGuiaRemate) throws Exception {
		try {
			return ordenTrabajoDAO.listOTByGuiaRemate(idGuiaRemate);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Accesorio> listAccesoriosOTbyFilter(FilterAccesorio filterAccesorio, Ubicacion ubicacion) throws Exception{
		try {
			filterAccesorio.setIdUbicacion(ubicacion.getId());
			return accesorioDAO.listAccesoriosOTbyFilter(filterAccesorio);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public GuiaPendienteAgrupada emitirGuiaRemate(GuiaPendienteAgrupada guia, Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			if (guiaRemateDAO.getGuiaRemateById(guia.getId()) == null)
				throw new SSTException("No existe la guía de despacho en el sistema");
			if (!guiaRemateDAO.getGuiaRemateByNumero(guia.getNumero()).isEmpty())
				throw new SSTException("Ya existe una guía con el mismo número");
			
			guia.setDestino(ubicacionDAO.get((guiaRemateDAO.getGuiaRemateById(guia.getId())).getDestino().getId()));
			guia.setUsuario(usuario);
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA);
			guia.setOrigen(ubicacion);
			
			List<OrdenTrabajo> oTs = ordenTrabajoDAO.listOTByGuiaRemate(guia.getId());
			
			guiaRemateDAO.emitirGuiaRemate(guia);
			
			for(OrdenTrabajo oT : oTs){
				oT.setEstado(new Estado());
				oT.getEstado().setId(Constants.OT_ESTADO_EN_CASA_DE_REMATE);
				ordenTrabajoDAO.updateEstadoOT(oT);
			} 
			
			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Boolean validaStock(Ubicacion origen, String ubicacion, Producto producto, Integer cantidad) throws Exception {
		Boolean stock = owService.validaStock(origen, ubicacion, producto, cantidad);
		if (stock) {
			return true;
		} 
		throw new SSTException("No hay stock disponible para el producto " + producto.getId() + " " + producto.getDescripcion() + " en el centro de costo " + origen.getId() + " " + origen.getNombre() + " ubicación " + ubicacion);
	}
	
	public Boolean validaStockAgrupado(Ubicacion origen, String ubicacion, Guia guia) throws Exception {
		List<ProductoReport> productoReports = guiaAgrupadaDAO.listDetalleGuiaAgrupadaByGuia(guia);
		return this.validaStockAgrupado(origen, ubicacion, productoReports);
	}
	
	public Boolean validaStockAgrupado(Ubicacion origen, String ubicacion, List<ProductoReport> productoReports) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		Boolean error = false;
		for (ProductoReport productoReport : productoReports) {
			try {
				if(!owService.validaStock(origen, ubicacion, productoReport.getProducto(), productoReport.getCantidadOT())) {
					error = true;
					stringBuilder.append("No hay stock disponible para el producto " + productoReport.getProducto().getId() + " " + productoReport.getProducto().getDescripcion() + " en el centro de costo " + origen.getId() + " " + origen.getNombre() + " ubicación " + ubicacion + ".\n" );
				}
			} catch (SSTException e) {
				error = true;
				stringBuilder.append("No hay stock disponible para el producto " + productoReport.getProducto().getId() + " " + productoReport.getProducto().getDescripcion() + " en el centro de costo " + origen.getId() + " " + origen.getNombre() + " ubicación " + ubicacion + ".\n" );
			}
		}
		if (!error) {
			return true;
		} 
		throw new SSTException(stringBuilder.toString());
	}
	
	public void setRecepcionMasivaDAO(RecepcionMasivaDAO recepcionMasivaDAO) {
		this.recepcionMasivaDAO=recepcionMasivaDAO;
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}
	
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) {
		this.bitacoraDAO = bitacoraDAO;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setGuiaAgrupadaDAO(GuiaAgrupadaDAO guiaAgrupadaDAO) {
		this.guiaAgrupadaDAO = guiaAgrupadaDAO;
	}
	
	
	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public void setRecepcionDAO(RecepcionDAO recepcionDAO) {
		this.recepcionDAO = recepcionDAO;
	}

	public void setGestionesDAO(GestionesDAO gestionesDAO) {
		this.gestionesDAO = gestionesDAO;
	}
	
	public void setCambioDAO(CambioDAO cambioDAO) {
		this.cambioDAO = cambioDAO;
	}
	
	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}
	
	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	public void setOrdenTrabajoService(OrdenTrabajoService ordenTrabajoService) {
		this.ordenTrabajoService = ordenTrabajoService;
	}
	
	public void setAccesorioDAO(AccesorioDAO accesorioDAO){
		this.accesorioDAO = accesorioDAO;
	}

	public void setGuiaRemateDAO(GuiaRemateDAO guiaRemateDAO) {
		this.guiaRemateDAO = guiaRemateDAO;
	}

	public void setOWService(OWService owService) {
		this.owService = owService;
	}
	
	public void setProductoDAO(ProductoDAO productoDAO){
		this.productoDAO=productoDAO;
	}
	
	public void setFamiliaDAO(FamiliaDAO familiaDAO){
		this.familiaDAO=familiaDAO;
	}

	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO) {
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}
	
	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO){
		this.ubicacionInternaDAO = ubicacionInternaDAO; 
	}
	
	public void setBodegaService(BodegaService bodegaService){
		this.bodegaService = bodegaService; 
	}
}
