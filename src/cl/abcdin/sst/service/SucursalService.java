package cl.abcdin.sst.service;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.CambioAutomaticoProveedorCartaDAO;
import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.DestinoDAO;
import cl.abcdin.sst.dao.DocumentoDAO;
import cl.abcdin.sst.dao.EjecutivaDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.FamiliaDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.GuiaAgrupadaDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.LineaDAO;
import cl.abcdin.sst.dao.LogisticoDAO;
import cl.abcdin.sst.dao.NotaCreditoDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.RecepcionDAO;
import cl.abcdin.sst.dao.ReglaComercialDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.SucursalDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.dao.ZonaDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.login.service.ILoginService;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.CambioAutomaticoProveedorCarta;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Destino;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Empresa;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.GuiaAccesorios;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Logistico;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Persona;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.ReglaCambioAutomatico;
import cl.abcdin.sst.model.ReglaCambioProoveedor;
import cl.abcdin.sst.model.ReglaCertificacionFalla;
import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.ReglaFallaFabricacion;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.Zona;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterDestino;
import cl.abcdin.sst.model.filters.FilterEjecutiva;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.FilterHisotrial;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterParte;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.FilterTipoCambio;
import cl.abcdin.sst.model.filters.FilterTipoFallas;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CrearOTGP;
import cl.abcdin.sst.model.vo.FamiliaExcluida;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.OWConstants;

public class SucursalService {

	private DocumentoDAO documentoDAO;
	private ProductoDAO productoDAO;
	private GuiaDAO guiaDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private UbicacionDAO ubicacionDAO;
	private TipoFallasDAO tipoFallasDAO;
	private BitacoraDAO bitacoraDAO;
	private AccesorioDAO accesorioDAO;
	private GestionesDAO gestionesDAO;
	private EstadoDAO estadoDAO;
	private EjecutivaDAO ejecutivaDAO;
	private ClienteDAO clienteDAO;
	private UtilDAO utilDAO;
	private ParteDAO parteDAO;
	private SucursalDAO sucursalDAO;
	private LogisticoDAO logisticoDAO;
	private RecepcionDAO recepcionDAO;
	private DestinoDAO destinoDAO;
	private ZonaDAO zonaDAO;
	private FamiliaDAO familiaDAO;
	private LineaDAO lineaDAO;
	private ReglaComercialDAO reglaComercialDAO;
	private NotaCreditoDAO notaCreidtoDAO;
	private GuiaAgrupadaDAO guiaAgrupadaDAO;
	private CambioAutomaticoProveedorCartaDAO cambioAutomaticoProveedorCartaDAO;

	private AdministracionService administracionService;
	private ILoginService loginService;
	private ProveedorService proveedorService;
	private EjecutivaService ejecutivaService;
	private EnvioRecepcionService envioRecepcionService;
	private OrdenTrabajoService ordenTrabajoService;
	private OWService oWService;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private ProveedorDAO proveedorDAO;
	private InterfazService interfazService;
	private static final Log log = LogFactory.getLog(SucursalService.class);

	public Documento getDocumentoByIdAndTipo(Documento documento)
			throws Exception {
		try {
			return documentoDAO.getDocumentoByIdAndTipo(documento);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Documento getDocumentoCompletoByIdAndTipo(Documento doc,
			Ubicacion ubicacion) throws Exception {
		
		Date fechaEmison = null;
		try {
			Documento documento = documentoDAO.getDocumentoByIdAndTipo(doc);
			
			if (documento == null){
				throw new SSTException("No se ha encontrado la " + doc.getTipo() + " numero " + doc.getId());
			}else{
				documento.setProductos(productoDAO.listProductoByTipoDocumentoAndIdDocumento(documento));
				fechaEmison = documento.getFechaEmision();	
			}
				
			if (documento.getProductos() == null || documento.getProductos().size() == 0)
				throw new SSTException("La " + doc.getTipo() + " número " + doc.getId() + " no tiene productos asociados");

			for (Producto producto : documento.getProductos()) {
				if (productoDAO.getProductoById(producto.getId()) != null) {
					producto.setEnGarantiaProveedor(isGarantiaProveedor(producto, documento));
					producto.setFechaEntrega(fechaEmison);
					if (isCambioPor24h(producto, ubicacion)) {
						producto.setMotivoCambioMenor24h("Producto con autorización de cambio menor 24 horas");
						producto.setCambioPor24h(true);

					}
					if (isCambioPorAutorizacionProveedor(producto, ubicacion)) {
						producto.setCambioAutorizadoProveedor(true);
						producto.setMotivoCambioAutorizadoProveedor(this.getReglaComercialVigentePorAutorizacionProveedor(producto, ubicacion).getReglaCambioProoveedor().getNotaProoveedor());
					}
					
					try {
						producto.setCambioPorValor(isCambioPorValor(producto, ubicacion));
						if (producto.getCambioPorValor()) {
							producto.setMotivoCambioPorValor("Producto con valor menor a $"+ this.getReglaComercialVigentePorValor(producto, ubicacion).getCambioAutomatico().getPrecioLimite());
						}
						
					} catch (SSTException e) {
						producto.setCambioPorValor(false);
						producto.setMotivoCambioPorValor(e.getMessage());
					}
					
					try {
						producto.setCambioPorFallaFabricacion(isCambioPorFallaFabricacion(producto, documento, ubicacion));
						if (producto.getCambioPorFallaFabricacion()) {
							if (validarCambioEnMeson(documento, producto, ubicacion)) {
								producto.setMotivoCambioPorFallaFabricacion("Producto califica como cambio En meson");
								producto.setCambioMeson(true);
								
							} else {
								producto.setMotivoCambioPorFallaFabricacion("Producto califica como cambio por falla de fabricacion");
								producto.setCambioMeson(false);
								Date fecha = new Date();
								SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyyy");
								String fechaSistema = formateador.format(fecha);
								String fechaEmision = formateador.format(documento.getFechaEmision());

								if (fechaSistema.equals(fechaEmision)) {
									producto.setProductoParaEvaluacion(false);
									
								} else {
									producto.setProductoParaEvaluacion(!isCambioAutomaticoPorFallaFabricacion(producto, ubicacion));
									if (producto.getProductoParaEvaluacion()) {
										producto.setMotivoProductoParaEvaluacion("Producto para evaluación");
									}
								}
							}
						}
					} catch (SSTException e) {
						producto.setCambioPorFallaFabricacion(false);
						producto.setMotivoCambioPorFallaFabricacion(e
								.getMessage());
					}
					producto.setCambioCertificacionFalla(isCambioPorCertificacionFalla(
							producto, documento, ubicacion));
				}
			}

			return documento;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean validarCambioEnMeson(Documento documento, Producto producto,
			Ubicacion ubicacion) throws Exception {
		try {
			// ReglaComercial reglaComercial =
			// getReglaComercialCompleta(producto, ubicacion);
			Date fechaActual = new Date();
			// SimpleDateFormat formateador = new
			// SimpleDateFormat("dd/MM/yyyy");
			// Date fechaEmision = formateador.parse(fecha);

			long MILISEGUNDOS_EN_UN_DIA = 1000 * 60 * 60 * 24;
			long dias = (fechaActual.getTime() - documento.getFechaEmision()
					.getTime()) / MILISEGUNDOS_EN_UN_DIA;
			if (dias == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Producto> listProductoByTipoDocumentoAndIdDocumento(
			Documento documento) throws Exception {
		try {
			return productoDAO
					.listProductoByTipoDocumentoAndIdDocumento(documento);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Producto getProductoById(Integer id) throws Exception {
		try {
			return productoDAO.getProductoById(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<ServicioTecnico> listSTecnicoYBodegasByFilter(
			FilterServicioTecnico filter, Ubicacion ubicacion) throws Exception {
		try {
			filter.setIdOrigen(ubicacion.getId());
			List<ServicioTecnico> sTecnicos = this.listSTecnicoByFilter(filter,
					ubicacion);
			sTecnicos.addAll(servicioTecnicoDAO.listBodegasByFilter(filter));
			for (int i = 0; i < sTecnicos.size(); i++) {
				if (sTecnicos
						.get(i)
						.getId()
						.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF
								.intValue())) {
					sTecnicos.remove(i);
					break;
				}
			}
			return sTecnicos;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<ServicioTecnico> listSTecnicoByFilter(
			FilterServicioTecnico filter, Ubicacion ubicacion) throws Exception {
		try {
			if (filter.getIdOT() != null) {
				OrdenTrabajo ot = ordenTrabajoDAO.getOTById(filter.getIdOT());
				filter.setIdProducto(ot.getProducto().getId());
				filter.setTipoOT(ot.getTipo().getCodigo());
			}
			if (filter.getIdOrigen() == null) {
				filter.setIdOrigen(ubicacion.getId());
			}

			return servicioTecnicoDAO.listSTecnicoByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<OrdenTrabajo> listHistorialOT(FilterHisotrial filter)
			throws Exception {
		try {
			return ordenTrabajoDAO.listHistorialOT(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listGuiasPendientesSucursal(
			FilterGuiasPendientes filterGuiasPendientes, Ubicacion ubicacion,
			GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterGuiasPendientes.setIdUbicacion(ubicacion.getId());
			filterGuiasPendientes.setOrderBy(gridControl.getOrderBy());
			filterGuiasPendientes.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(guiaDAO.listGuiasPendientesBySucursal(
					filterGuiasPendientes, gridControl));
			listRange.setTotal(guiaDAO
					.getTotalGuiasPendientesBySucursal(filterGuiasPendientes));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Bitacora getBitacoraByIdGuia(Long idGuia) throws Exception {
		try {
			return bitacoraDAO.getByIdGuia(idGuia);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia getGuiaById(Long id, Ubicacion ubicacion, Usuario usuario)
			throws Exception {
		try {
			Guia guia = guiaDAO.get(id);
			if (guia.getNumero() == null || guia.getNumero() == 0) {
				FilterGuia filter = new FilterGuia();
				filter.setIdSucursal(ubicacion.getId());
				filter.setIdUsuario(usuario.getId());
				guia.setNumero(guiaDAO.getSiguienteNumeroGuia(filter));
			}
			return guia;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public CrearOTGP getCrearOTGP(FilterServicioTecnico filterST,
			FilterHisotrial filterH, Documento documento, Ubicacion ubicacion,
			Date dateActual) throws Exception {
		try {
			CrearOTGP crearOTGP = new CrearOTGP();

			Producto producto = getProductoById(filterST.getIdProducto());
			if (producto != null) {
				crearOTGP.setBigTicket(producto.getBigTicket());
			}
			crearOTGP
					.setConsultarHistorial(!listHistorialOT(filterH).isEmpty());
			crearOTGP.setConsultarST(!listSTecnicoByFilter(filterST, ubicacion)
					.isEmpty());

			crearOTGP.setConsultarGP(isGarantiaProveedor(producto, documento));
			crearOTGP.setFechaVencimiento(getFechaVencimientoGarantiaProducto(
					producto, documento));
			crearOTGP.setProcedimiento(administracionService
					.getProcedimientoByIdProducto(producto.getId()));

			return crearOTGP;

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Date getFechaVencimientoGarantiaProducto(Integer idProducto,
			Integer idDocumento, String tipoDocumento) throws Exception {
		try {
			Documento documento = new Documento();
			Producto producto = new Producto();

			documento.setId(idDocumento);
			documento.setTipo(tipoDocumento);
			producto.setId(idProducto);

			return getFechaVencimientoGarantiaProducto(producto, documento);
		} catch (Exception e) {
			throw e;
		}
	}

	private Date getFechaVencimientoGarantiaProducto(Producto producto,
			Documento documento) throws Exception {
		try {
			producto = getProductoById(producto.getId());
			documento = getDocumentoByIdAndTipo(documento);

			Calendar fechaVencimiento = Calendar.getInstance();
			fechaVencimiento.setTime(documento.getFechaEmision());
			if (producto.getDuracionGarantia() != null) {
				fechaVencimiento.add(Calendar.MONTH,
						producto.getDuracionGarantia());
			}
			return fechaVencimiento.getTime();
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean isGarantiaProveedor(Producto producto, Documento documento)
			throws Exception {
		try {
			producto = getProductoById(producto.getId());
			documento = getDocumentoByIdAndTipo(documento);

			Date fechaActual = utilDAO.getDateTrunc();
			Calendar fechaVencimiento = Calendar.getInstance();
			fechaVencimiento.setTime(documento.getFechaEmision());

			if (producto.getDuracionGarantia() == null) {
				return false;
			}
			fechaVencimiento
					.add(Calendar.MONTH, producto.getDuracionGarantia());

			return fechaActual.before(fechaVencimiento.getTime())
					|| fechaActual.equals(fechaVencimiento.getTime());
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TipoFallas> listTipoFallasByFilter(FilterTipoFallas filter)
			throws Exception {
		try {
			return tipoFallasDAO.listTipoFallasByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TipoFallas> listTipoFallasByIdProducto(Integer idProducto)
			throws Exception {
		try {
			return tipoFallasDAO.listTipoFallasByIdProducto(idProducto);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<List<TipoFallas>> listTipoFallasCrearOT(OrdenTrabajo oT)
			throws Exception {
		try {
			List<TipoFallas> fallasProducto = tipoFallasDAO
					.listTipoFallasByIdProducto(oT.getProducto().getId());

			if (fallasProducto.size() == 0) {
				Producto producto = productoDAO.getProductoById(oT
						.getProducto().getId());
				fallasProducto = tipoFallasDAO
						.listTipoFallasByIdFamilia(producto.getFamilia()
								.getId());
			}

			// Separa las fallas asiganadas a la OT y las que no estÃ¡n
			// asiganadas
			List<TipoFallas> fallasOT = tipoFallasDAO.listFallasOTById(oT
					.getId());

			for (TipoFallas fallaOT : fallasOT) {
				for (TipoFallas fallaProducto : fallasProducto) {
					if (fallaOT.getId().equals(fallaProducto.getId())) {
						fallasOT.set(fallasOT.indexOf(fallaOT), fallaProducto);
						fallasProducto.remove(fallaProducto);
						break;
					}
				}
			}

			List<List<TipoFallas>> fallas = new ArrayList<List<TipoFallas>>();
			fallas.add(fallasProducto);
			fallas.add(fallasOT);

			return fallas;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Accesorio> listAccesoriosByFilter(FilterAccesorio filter)
			throws Exception {
		try {
			if (filter.getIdTipoFalla() != null) {
				return accesorioDAO.listAccesoriosByFilter(filter);
			}
			if (filter.getIdFamilia() != null) {
				return accesorioDAO.listAccesoriosByFilter(filter);
			}
			if (filter.getIdProducto() != null) {
				List<Accesorio> accesorios = accesorioDAO
						.listAccesoriosByFilter(filter);
				if (accesorios.size() != 0) {
					return accesorios;
				}
				Producto producto = productoDAO.getProductoById(filter
						.getIdProducto());
				filter.setIdFamilia(producto.getFamilia().getId());
				filter.setIdProducto(null);
				return accesorioDAO.listAccesoriosByFilter(filter);
			}
			throw new SSTException("Ingrese al menos un filtro");
		} catch (SSTException e) {
			log.error(e, e);
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Accesorio> listAccesoriosNotExistsByFilter(
			FilterAccesorio filter) throws Exception {
		try {
			if (filter.getIdTipoFalla() != null) {
				return accesorioDAO.listAccesoriosNotExistsByFilter(filter);
			}
			if (filter.getIdFamilia() != null) {
				return accesorioDAO.listAccesoriosNotExistsByFilter(filter);
			}
			if (filter.getIdProducto() != null) {
				List<Accesorio> accesorios = accesorioDAO
						.listAccesoriosNotExistsByFilter(filter);
				if (accesorios.size() != 0) {
					return accesorios;
				}
				Producto producto = productoDAO.getProductoById(filter
						.getIdProducto());
				filter.setIdFamilia(producto.getFamilia().getId());
				filter.setIdProducto(null);
				return accesorioDAO.listAccesoriosNotExistsByFilter(filter);
			}
			throw new SSTException("Ingrese al menos un filtro");
		} catch (SSTException e) {
			log.error(e, e);
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Parte> listPartesByFilter(FilterParte filter) throws Exception {
		try {
			return parteDAO.listPartesByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean saveTipoFallas(Long idOT, List<Long> idTipoFallas,
			FilterOT filterOT) throws Exception {
		try {
			Boolean rt = true;
			Integer existe = tipoFallasDAO.listTotalFallasByOT(idOT);
			if (existe > 0) {
				tipoFallasDAO.deleteFallasByOT(idOT);
			}

			List<Accesorio> accesorios = accesorioDAO.listAccesoriosByOT(idOT);
			FilterTipoFallas filterFallas = new FilterTipoFallas();
			filterFallas.setIdOT(idOT);
			for (Accesorio acc : accesorios) {
				filterFallas.setIdOT(idOT);
				filterFallas.setIdAccesorio(acc.getId());
				Integer fallasAc = tipoFallasDAO
						.listTotalFallasAccesorioByFilter(filterFallas);
				if (fallasAc == 0) {
					FilterAccesorio filterAcc = new FilterAccesorio();
					filterAcc.setIdAccesorio(acc.getId());
					filterAcc.setIdOT(idOT);
					accesorioDAO.updateAccesoriosOTRequridoByFilter(filterAcc);
				}
			}

			for (Long idTF : idTipoFallas) {
				List<TipoFallas> fallasAcc = tipoFallasDAO
						.listAccesoriosByidTipoFalla(idTF);
				filterFallas.setIdTipoFalla(idTF);

				if (tipoFallasDAO.listTotalFallasOTByFilter(filterFallas)
						.equals(0)) {
					if (tipoFallasDAO.saveTipoFallasOT(filterFallas).equals(0)) {
						rt = false;
					} else {
						for (TipoFallas idFA : fallasAcc) {
							filterFallas.setIdAccesorio(idFA.getAccesorio()
									.getId());
							tipoFallasDAO
									.updateAccesoriosOTByFilter(filterFallas);
						}
					}
				} else {
					rt = false;
				}
			}

			if (ordenTrabajoDAO.listTotalOTbyidOT(idOT) > 0) {
				ordenTrabajoDAO.updateDescripcionFallaByFilter(filterOT);
			}

			return rt;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer listTotalFallasOTByFilter(FilterTipoFallas filter)
			throws Exception {
		try {
			return tipoFallasDAO.listTotalFallasOTByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer updateDesactivarOTByFilter(FilterOT filter) throws Exception {
		try {
			return ordenTrabajoDAO.updateDesactivarOTByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TipoFallas> listTipoFallasByOT(Long idOT) throws Exception {
		try {
			return tipoFallasDAO.listTipoFallasByOT(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private void emitirGuiaDestinoCliente(Guia guia) throws Exception {
		try {
			guia.setTransportista(new Transportista());
			updateUbicacionAccesorios(guia, guia.getOrdenTrabajo(),
					guia.getDestino(), guia.getOrigen());
			guia.getEstado().setId(
					Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION);
			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");
			saveBitacoraByGuiaAndOTEntregaCliente(guia, guia.getOrdenTrabajo());
		} catch (Exception e) {
			throw e;
		}
	}

	private void emitirGuiaDestinoServicioTecnico(Guia guia) throws Exception {
		try {
			guia.getOrdenTrabajo().setEjecutiva(
					ejecutivaDAO.getEjecutivaByOT(guia.getOrdenTrabajo()
							.getId()));
			FilterOT filter = new FilterOT();
			filter.setIdOT(guia.getOrdenTrabajo().getId());
			OrdenTrabajo oT = this.getOTByFilter(filter);
			if (oT.getProcesadoOW()) {
				envioRecepcionService.validaStock(guia.getOrigen(),
						OWConstants.UBICACION_FDP, oT.getProducto(), 1);
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);
			} else {
				guia.getEstado().setId(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA);
			}
			guia.getOrdenTrabajo().getEstado()
					.setId(Constants.OT_ESTADO_REPARACION_ESPERA_CONTRATO);
			guia.getOrdenTrabajo().setsTecnico(new ServicioTecnico());
			guia.getOrdenTrabajo().getsTecnico()
					.setId(guia.getDestino().getId().intValue());

			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");

			ordenTrabajoDAO.updateEjecutiva(guia.getOrdenTrabajo());

			ordenTrabajoDAO.updateUbicacionSTecnico(guia.getOrdenTrabajo());
			ordenTrabajoDAO.updateEstadoOT(guia.getOrdenTrabajo());
			updateUbicacionAccesorios(guia, guia.getOrdenTrabajo(),
					guia.getDestino(), guia.getOrigen());
			saveBitacoraByGuiaAndOT(guia, guia.getOrdenTrabajo());

		} catch (Exception e) {
			throw e;
		}
	}

	private void emitirGuiaDestinoSucursal(Guia guia) throws Exception {
		try {

			Gestion gestion = new Gestion();
			gestion.setOt(guia.getOrdenTrabajo());
			gestion.setUsuario(guia.getUsuario());
			gestion.setGestion(" Cambia la sucursal de destino de la ot, de "
					+ guia.getOrdenTrabajo().getSucursal().getId() + " a la "
					+ guia.getDestino().getId());
			FilterOT filter = new FilterOT();
			filter.setIdOT(guia.getOrdenTrabajo().getId());
			OrdenTrabajo oT = this.getOTByFilter(filter);
			if (oT.getProcesadoOW()) {
				envioRecepcionService.validaStock(guia.getOrigen(),
						OWConstants.UBICACION_FDP, oT.getProducto(), 1);
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);
			} else {
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA);
			}
			guia.getOrdenTrabajo().getEstado()
					.setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);
			guia.getOrdenTrabajo().getSucursal()
					.setId(guia.getDestino().getId().intValue());

			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");

			ordenTrabajoDAO.updateUbicacionSucursal(guia.getOrdenTrabajo());
			ordenTrabajoDAO.updateEstadoOT(guia.getOrdenTrabajo());
			updateUbicacionAccesorios(guia, guia.getOrdenTrabajo(),
					ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),
					guia.getOrigen());
			gestionesDAO.saveGestion(gestion);
			saveBitacoraByGuiaAndOT(guia, guia.getOrdenTrabajo());
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean validaStockGuiaUnitaria(Long idGuia, Long idOT)
			throws Exception {
		Guia guia = guiaDAO.get(idGuia);
		guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
		OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(idOT);
		return envioRecepcionService.validaStock(guia.getOrigen(),
				OWConstants.UBICACION_FDP, ordenTrabajo.getProducto(), 1);
	}

	public Boolean validaStockGuiaAgrupada(Long idGuia) throws Exception {
		Guia guia = guiaDAO.get(idGuia);
		guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
		Ubicacion destino = ubicacionDAO.get(guia.getDestino().getId());
		if (destino == null) {
			destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
		}
		guia.setDestino(destino);

		if (guia.getDestino().getTipo() != null
				&& guia.getDestino()
						.getId()
						.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
			return envioRecepcionService.validaStockAgrupado(guia.getOrigen(),
					OWConstants.UBICACION_FDP, guia);
		} else if (guia.getDestino().getTipo() != null
				&& guia.getDestino().getTipo()
						.equals(Constants.UBICACION_TRANSPORTE)) {
			return envioRecepcionService.validaStockAgrupado(guia.getOrigen(),
					Constants.UBICACION_RTR_OW, guia);
		} else if (guia.getDestino().getTipo() != null
				&& guia.getDestino().getId().equals(Constants.BODEGA_10000)) {
			return envioRecepcionService.validaStockAgrupado(guia.getOrigen(),
					Constants.UBICACION_SERVICIO_TECNICO_AUTORIZADO, guia);
		} else {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO
					.listOTCambioAuomaticoByFilter(filterOT);
			return envioRecepcionService.validaStockAgrupado(
					guia.getOrigen(),
					oWService.convertUbicaciontoOW(ots.get(0)
							.getClasificacion().getCodigo()), guia);
		}
		// ver caso emision transporte, emision a proveedor
	}

	private void emitirGuiaDestinoBRoCD(Guia guia) throws Exception {
		try {
			FilterAccesorio filterAccesorio = new FilterAccesorio();
			filterAccesorio.setIdOT(guia.getOrdenTrabajo().getId());
			filterAccesorio.setIdUbicacion(Constants.UBICACION_ID_TRANSPORTE);

			FilterOT filter = new FilterOT();
			filter.setIdOT(guia.getOrdenTrabajo().getId());
			OrdenTrabajo oT = this.getOTByFilter(filter);
			if (oT.getProcesadoOW()) {
				envioRecepcionService.validaStock(guia.getOrigen(),
						OWConstants.UBICACION_FDP, oT.getProducto(), 1);
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);
			} else {
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA);
			}
			guia.getOrdenTrabajo().getEstado()
					.setId(Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);

			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");

			ordenTrabajoDAO.updateEstadoOT(guia.getOrdenTrabajo());
			updateUbicacionAccesorios(guia, guia.getOrdenTrabajo(),
					ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),
					guia.getOrigen());
			saveBitacoraByGuiaAndOT(guia, guia.getOrdenTrabajo());

		} catch (Exception e) {
			throw e;
		}
	}

	private void emitirGuiaAccesorioDestinoBRoCD(Guia guia) throws Exception {
		try {
			if (guia.getOrdenTrabajo().getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_MASTER)) {
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_RECIBIDA);
			} else {
				envioRecepcionService.validaStock(guia.getOrigen(),
						OWConstants.UBICACION_FDP, guia.getOrdenTrabajo()
								.getProducto(), 1);
				guia.getEstado().setId(
						Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);
			}

			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");

			updateUbicacionAccesorios(guia, guia.getOrdenTrabajo(),
					ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),
					guia.getOrigen());
			saveBitacoraByGuiaAccesorioAndOT(guia, guia.getOrdenTrabajo());

		} catch (Exception e) {
			throw e;
		}
	}

	public void updateUbicacionAccesorios(Guia guia, OrdenTrabajo ordenTrabajo,
			Ubicacion destino, Ubicacion origen) throws Exception {
		try {
			Guia guiaAux = guiaDAO.get(guia.getId());
			FilterGuia filterGuia = new FilterGuia();
			filterGuia.setIdGuia(guiaAux.getId());
			List<Accesorio> accesorios = accesorioDAO
					.listAccesoriosFromIdGuia(filterGuia);
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

	public Boolean validaCodigosDeBarraOT(OrdenTrabajo ot) throws Exception {
		try {
			FilterOT filter = new FilterOT();
			filter.setIdOT(ot.getId());
			filter.setCodigoBarra(ot.getCodigoBarra());
			return (utilDAO.getCantidadCodBarras(filter).equals(0));
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Integer> validaCodigosDeBarraAccesorios(
			List<Accesorio> accesorios) throws Exception {
		try {
			FilterOT filter = new FilterOT();
			List<Integer> idAccesorios = new ArrayList<Integer>();
			for (Accesorio accesorio : accesorios) {
				filter.setIdAccesorio(accesorio.getId());
				filter.setCodigoBarra(accesorio.getCodigoBarra());
				if (utilDAO.getCantidadCodBarras(filter) != 0) {
					idAccesorios.add(accesorio.getId());
				}
			}
			return idAccesorios;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateUbicacionAccesoriosRecibidos(OrdenTrabajo ordenTrabajo,
			Ubicacion destino, Ubicacion origen, List<Accesorio> accesorios)
			throws Exception {
		try {
			if (accesorios != null) {
				for (Accesorio accesorio : accesorios) {
					if (accesorio.getRecibido() != null
							&& accesorio.getRecibido()) {
						accesorio.setUbicacion(destino);
						accesorioDAO.updateUbicacionAccesorio(accesorio);
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia emitirGuiaAgrupada(Guia guia, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setUsuario(usuario);
			guia.setEstado(new Estado());
			guia.setOrigen(ubicacion);
			guia.setEntregaCliente(false);

			if (guiaDAO.get(guia.getId()) == null)
				throw new SSTException(
						"No existe la guía de despacho en el sistema");
			if (!guiaDAO.getByNumero(guia.getNumero()).isEmpty())
				throw new SSTException("Ya existe una guía con el mismo número");

			if (guia.getDestino().getTipo()
					.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
				// emitirGuiaDestinoServicioTecnico(guia);
			} else if (guia.getDestino().getTipo()
					.equals(Constants.UBICACION_SUCURSAL)) {
				// emitirGuiaDestinoSucursal(guia);
			} else if (guia.getDestino().getTipo()
					.equals(Constants.UBICACION_TRANSPORTE)) {
				envioRecepcionService.emitirGuiaAgrupadaDestinoTransportista(
						guia, ubicacion);
			} else {
				emitirGuiaAgrupadaDestinoBRoCD(guia);
			}

			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private void emitirGuiaAgrupadaDestinoBRoCD(Guia guia) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO
					.listOTCambioAuomaticoByFilter(filterOT);

			FilterAccesorio filterAccesorio = new FilterAccesorio();
			filterAccesorio.setIdUbicacion(Constants.UBICACION_ID_TRANSPORTE);

			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));

			if (guia.getDestino().getId().equals(Constants.BODEGA_10000)) {
				envioRecepcionService.validaStockAgrupado(guia.getOrigen(),
						Constants.UBICACION_SERVICIO_TECNICO_AUTORIZADO, guia);
			} else {
				envioRecepcionService.validaStockAgrupado(guia.getOrigen(),
						OWConstants.UBICACION_FDP, guia);
			}

			guia.getEstado().setId(Constants.GUIA_ESTADO_EMITIDA_NO_CONFIRMADA);

			if (guiaDAO.update(guia).equals(0))
				throw new SSTException("Error al actualizar el registro");

			for (OrdenTrabajo ordenTrabajo : ots) {
				filterAccesorio.setIdOT(ordenTrabajo.getId());
				updateUbicacionAccesorios(guia, ordenTrabajo,
						ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE),
						guia.getOrigen());
				if (!guia.getDestino().getId().equals(Constants.BODEGA_10000)) {
					ordenTrabajo.setEstado(new Estado());
					ordenTrabajo.getEstado().setId(
							Constants.OT_ESTADO_TRANSPORTE_POR_RECIBIR);
				}
				filterOT.setIdOT(ordenTrabajo.getId());
				Bitacora bitacora = bitacoraDAO
						.getByIdGuiaAgrupadaAndOT(filterOT);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
				saveBitacoraByGuiaAndOT(guia, ordenTrabajo, bitacora);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public Guia emitirGuia(Long idOT, Guia guia,
			ServicioTecnico servicioTecnico, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			guia.setUsuario(usuario);
			guia.setEstado(new Estado());
			guia.setOrigen(ubicacion);

			if (guiaDAO.get(guia.getId()) == null)
				throw new SSTException(
						"No existe la guía de despacho en el sistema");
			if (!(guia.getObservacion() != null && guia.getObservacion()
					.equals(Constants.GUIA_ENVIADA_SIN_EMISION_GUIA))) {
				if (!guiaDAO.getByNumero(guia.getNumero()).isEmpty()) {
					throw new SSTException(
							"Ya existe una guía con el mismo número");
				}
			}

			guia.setEntregaCliente(guia.getDestino().getId()
					.equals(Constants.UBICACION_ID_CLIENTE));

			if (guia.getEntregaCliente()) {
				emitirGuiaDestinoCliente(guia);
			} else {
				if (guia.getDestino().getTipo()
						.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					emitirGuiaDestinoServicioTecnico(guia);
				} else if (guia.getDestino().getTipo()
						.equals(Constants.UBICACION_SUCURSAL)) {
					emitirGuiaDestinoSucursal(guia);
				} else {
					emitirGuiaDestinoBRoCD(guia);
				}
			}

			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia emitirGuiaAccesorio(Long idOT, Guia guia, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			guia.setUsuario(usuario);
			guia.setEstado(new Estado());
			guia.setOrigen(ubicacion);
			guia.setEntregaCliente(false);
			Guia guiaAuxiliar = guiaDAO.get(guia.getId());
			if (guiaAuxiliar == null) {
				throw new SSTException(
						"No existe la guía de despacho en el sistema");
			}
			if (!guiaDAO.getByNumero(guia.getNumero()).isEmpty()) {
				throw new SSTException("Ya existe una guía con el mismo número");
			}

			emitirGuiaAccesorioDestinoBRoCD(guia);

			return guia;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia reEmitirGuiaAgrupada(Guia guia, Usuario usuario,
			Ubicacion origen) throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setIdGuiaAgrupada(guia.getId());
			List<OrdenTrabajo> ots = ordenTrabajoDAO
					.listOTCambioAuomaticoByFilter(filterOT);

			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			Guia guiaNueva = new Guia();

			if (guia.getDestino().getId()
					.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
				guiaNueva = saveGuiaParaAgrupacion(
						origen,
						usuario,
						ubicacionDAO
								.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
			} else if (guia.getDestino().getId().equals(Constants.BODEGA_10000)) {
				guiaNueva = saveGuiaParaAgrupacion(origen, usuario,
						ubicacionDAO.get(Constants.BODEGA_10000));
			}

			for (OrdenTrabajo ordenTrabajo : ots) {
				filterOT.setIdOT(ordenTrabajo.getId());
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);
				ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);

				Bitacora bitacora = bitacoraDAO
						.getByIdGuiaAgrupadaAndOT(filterOT);
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(null);
				bitacora.setGuia(guiaNueva);
				bitacora.setUbicacion(origen);

				bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalida(bitacora);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				ordenTrabajoDAO.updateRollbackEstadoGuia(ordenTrabajo);

				if (guia.getDestino().getTipo()
						.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
					updateUbicacionAccesorios(guia, ordenTrabajo,
							guia.getOrigen(), guia.getDestino());
				} else {
					updateUbicacionAccesorios(guia, ordenTrabajo,
							guia.getOrigen(),
							ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));
				}

				List<Accesorio> accesoriosOT = accesorioDAO
						.listAccesoriosByOT(ordenTrabajo.getId());
				for (Accesorio accesorio : accesoriosOT) {
					if (accesorio.getUbicacion().getTipo()
							.equals(origen.getTipo())) {
						accesorio.setGuia(guiaNueva);
						accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
					}
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

	public Guia reEmitirGuia(Long idOT, Guia guia,
			ServicioTecnico servicioTecnico, Usuario usuario, Ubicacion origen)
			throws Exception {
		try {
			Bitacora bitacora = bitacoraDAO.getByIdGuia(guia.getId());
			bitacora.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			bitacora.setFechaSalida(null);
			Guia guiaForAccesorios = guiaDAO.get(guia.getId());
			guiaForAccesorios.setDestino(ubicacionDAO.get(guiaForAccesorios
					.getDestino().getId()));
			guiaForAccesorios.setOrigen(ubicacionDAO.get(guiaForAccesorios
					.getOrigen().getId()));
			guia.setOrdenTrabajo(bitacora.getOrdenTrabajo());
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_DESACTIVADA);
			guia.setVigente(false);
			guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
			guiaDAO.updateEstado(guia);
			ordenTrabajoDAO
					.updateRollbackEstadoGuia(bitacora.getOrdenTrabajo());
			Guia guiaNueva = new Guia();
			guiaNueva.setEstado(new Estado());
			guiaNueva.setOrdenTrabajo(guia.getOrdenTrabajo());
			guiaNueva.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guiaNueva.setOrigen(origen);
			guiaNueva.setDestino(guia.getDestino());
			guiaNueva.setEntregaCliente(false);
			guiaNueva.setVigente(true);
			guiaNueva.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
			guiaDAO.save(guiaNueva);

			asignarGuiaToAccesorios(guiaNueva,
					accesorioDAO.listAllAccesoriosFromTipoGuia(guia));

			if (guiaForAccesorios.getDestino().getTipo()
					.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
				updateUbicacionAccesorios(guiaNueva, guia.getOrdenTrabajo(),
						guiaForAccesorios.getOrigen(),
						guiaForAccesorios.getDestino());
			} else {
				updateUbicacionAccesorios(guiaNueva, guia.getOrdenTrabajo(),
						guiaForAccesorios.getOrigen(),
						ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));
			}

			bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
			bitacoraDAO.updateFechaSalida(bitacora);

			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setGuia(guiaNueva);
			bitacoraNueva.setOrdenTrabajo(guiaNueva.getOrdenTrabajo());
			bitacoraNueva.setUbicacion(origen);

			bitacoraDAO.updateAsignaBitacoraAGuia(bitacoraNueva);
			bitacoraInternaDAO.abrirBitacoraInternaByIdOT(idOT);
			return guiaNueva;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void asignarGuiaToAccesorios(Guia guia, List<Accesorio> accesorios)
			throws Exception {
		try {
			for (Accesorio accesorio : accesorios) {
				accesorio.setGuia(guia);
				accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia reEmitirGuiaAccesorio(Long idOT, Guia guia, Usuario usuario,
			Ubicacion origen) throws Exception {
		try {
			Bitacora bitacora = bitacoraDAO.getBitacoraAccesorioByIdGuia(guia
					.getId());
			bitacora.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			bitacora.setFechaSalida(null);
			guia.setOrdenTrabajo(bitacora.getOrdenTrabajo());
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_DESACTIVADA);
			guia.setVigente(false);
			guia.setTipoGuia(Constants.GUIA_TIPO_ACCESORIO);
			guiaDAO.updateEstado(guia);
			ordenTrabajoDAO
					.updateRollbackEstadoGuia(bitacora.getOrdenTrabajo());

			Guia guiaNueva = new Guia();
			guiaNueva.setEstado(new Estado());
			guiaNueva.setOrdenTrabajo(guia.getOrdenTrabajo());
			guiaNueva.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guiaNueva.setOrigen(origen);
			guiaNueva.setDestino(guia.getDestino());
			guiaNueva.setEntregaCliente(false);
			guiaNueva.setVigente(true);
			guiaNueva.setTipoGuia(Constants.GUIA_TIPO_ACCESORIO);
			guiaDAO.save(guiaNueva);

			asignarGuiaToAccesorios(guiaNueva,
					accesorioDAO.listAllAccesoriosFromTipoGuia(guia));
			updateUbicacionAccesorios(guiaNueva, guia.getOrdenTrabajo(),
					guiaNueva.getOrigen(),
					ubicacionDAO.get(Constants.UBICACION_ID_TRANSPORTE));

			bitacoraDAO.deleteBitacoraAccesoriosMayoresByOT(bitacora);
			bitacoraDAO.updateFechaSalidaBitacoraAccesorio(bitacora);

			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setGuia(guiaNueva);
			bitacoraNueva.setOrdenTrabajo(guiaNueva.getOrdenTrabajo());
			bitacoraNueva.setUbicacion(origen);

			bitacoraDAO.updateAsignaBitacoraAGuiaAccesorio(bitacoraNueva);

			return guiaNueva;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveBitacoraByGuiaAndOT(Guia guia, OrdenTrabajo ordenTrabajo,
			Bitacora bitacora) throws Exception {
		try {
			if (bitacora != null) {
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(guia.getFechaEmision());
				bitacoraDAO.deleteBitacoraMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalida(bitacora);
				// cerrar la bitacora interna en caso de que exista (corregido
				// en las pruebas integrales 01/08/1013, en el flujo recibir OT
				// en 10015 y luego enviar a ST)
				BitacoraInterna bitacoraInterna = bitacoraInternaDAO
						.getBitacoraInternaById(ordenTrabajo.getId());
				if (bitacoraInterna != null) {
					bitacoraInternaDAO.cerrarBitacoraInternaByIdOT(ordenTrabajo
							.getId());
				}

				if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_SUCURSAL)) {
					if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.setFechaSalida(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SERVICIO_TECNICO_LOCAL);
						bitacoraDAO.save(b1);

						Bitacora b2 = new Bitacora();
						b2.setEstado(new Estado());
						b2.setOrdenTrabajo(ordenTrabajo);
						b2.setFechaEntrada(guia.getFechaEmision());
						b2.getEstado()
								.setId(Constants.BITACORA_EN_SERVICIO_TECNICO_LOCAL_DESPUES_ENTREGA_A_CAMION_HACIA_SUCURSAL);
						b2.setUbicacion(guia.getDestino());
						bitacoraDAO.save(b2);
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_BODEGA_REGIONAL)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_A_BODEGA_REGIONAL);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
						if (bitacora.getEstado().getId() > 70000000) {
							Bitacora b1 = new Bitacora();
							b1.setEstado(new Estado());
							b1.setOrdenTrabajo(ordenTrabajo);
							b1.setFechaEntrada(guia.getFechaEmision());
							b1.setEstado(estadoDAO
									.getNextEstadoByIdEstado(bitacora
											.getEstado().getId()));
							bitacoraDAO.save(b1);
						} else {
							Bitacora b1 = new Bitacora();
							b1.setEstado(new Estado());
							b1.setOrdenTrabajo(ordenTrabajo);
							b1.setFechaEntrada(guia.getFechaEmision());
							b1.getEstado()
									.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_A_CD);
							bitacoraDAO.save(b1);
						}
					}
				} else if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)
						&& guia.getOrigen()
								.getId()
								.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA)) {
					if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
						if (guia.getEstado().getId() > 50006000) {
							Bitacora b1 = new Bitacora();
							b1.setEstado(new Estado());
							b1.setOrdenTrabajo(ordenTrabajo);
							b1.setFechaEntrada(guia.getFechaEmision());
							b1.getEstado()
									.setId(Constants.BITACORA_EN_SERVICIO_TECNICO_DEPUES_AL_CD);
							b1.setUbicacion(guia.getDestino());

							bitacoraDAO.save(b1);
						} else {
							Bitacora b1 = new Bitacora();
							b1.setEstado(new Estado());
							b1.setOrdenTrabajo(ordenTrabajo);
							b1.setFechaEntrada(guia.getFechaEmision());
							b1.setFechaSalida(guia.getFechaEmision());
							b1.getEstado()
									.setId(Constants.BITACORA_EN_CAMION_ENTREGA_A_SERVICIO_TECNICO_2);
							bitacoraDAO.save(b1);

							Bitacora b2 = new Bitacora();
							b2.setEstado(new Estado());
							b2.setOrdenTrabajo(ordenTrabajo);
							b2.setFechaEntrada(guia.getFechaEmision());
							b2.getEstado()
									.setId(Constants.BITACORA_EN_SERVICIO_TECNICO_DEPUES_ENTREGA_A_CAMION_AL_CD);
							b2.setUbicacion(guia.getDestino());
							bitacoraDAO.save(b2);
						}
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SUCURSAL)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						if (ordenTrabajo
								.getEstado()
								.equals(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_CENTRO_DE_DISTRIBUCION)) {
							b1.getEstado()
									.setId(Constants.BITACORA_EN_CAMION_DESPUES_DEVUELVE_A_SUCURSAL);
						} else {
							b1.getEstado()
									.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SUCURSAL);
						}
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_BODEGA_REGIONAL)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(
								Constants.BITACORA_EN_CAMION_A_BODEGA_REGIONAL);
						bitacoraDAO.save(b1);
					} else if (guia
							.getDestino()
							.getId()
							.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(Constants.BITACORA_EN_CAMION_A_FF);
						bitacoraDAO.save(b1);
					}
				} else if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)
						&& guia.getOrigen()
								.getId()
								.equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
					if (guia.getDestino().getTipo() != null
							&& guia.getDestino().getTipo()
									.equals(Constants.UBICACION_TRANSPORTE)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(Constants.BITACORA_EN_CAMION_A_FF);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo() != null
							&& guia.getDestino().getTipo()
									.equals(Constants.UBICACION_CASA_REMATE)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(
								Constants.BITACORA_EN_CAMION_A_CASA_REMATE);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo() != null
							&& guia.getDestino().getTipo()
									.equals(Constants.UBICACION_LIQUIDADORA)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(
								Constants.BITACORA_EN_CAMION_A_LIQUIDADORA);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo() != null
							&& guia.getDestino()
									.getTipo()
									.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_A_SERVICIO_TECNICO);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo() != null
							&& guia.getDestino()
									.getTipo()
									.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)
							&& guia.getDestino().getId()
									.equals(Constants.BODEGA_10000)) {

					} else {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado().setId(
								Constants.BITACORA_EN_CAMION_A_PROVEEDOR);
						bitacoraDAO.save(b1);
					}
				} else if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_BODEGA_REGIONAL)) {
					if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_SE_ENTREGA_EN_CENTRO_DE_DISTRIBUCION);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SUCURSAL)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SUCURSAL);
						bitacoraDAO.save(b1);
					} else if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SERVICIO_TECNICO)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.setFechaSalida(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_ENTREGA_A_SERVICIO_TECNICO);
						bitacoraDAO.save(b1);

						Bitacora b2 = new Bitacora();
						b1.setEstado(new Estado());
						b2.setOrdenTrabajo(ordenTrabajo);
						b2.setFechaEntrada(guia.getFechaEmision());
						b2.getEstado()
								.setId(Constants.BITACORA_EN_SERVICIO_TECNICO_DESPUES_ENTREGA_A_CAMION_A_BODEGA_REGIONAL);
						b2.setUbicacion(guia.getDestino());
						bitacoraDAO.save(b2);
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveBitacoraByGuiaAccesorioAndOT(Guia guia,
			OrdenTrabajo ordenTrabajo, Bitacora bitacora) throws Exception {
		try {
			if (bitacora != null) {
				bitacora.setOrdenTrabajo(ordenTrabajo);
				bitacora.setFechaSalida(guia.getFechaEmision());
				bitacoraDAO.deleteBitacoraAccesoriosMayoresByOT(bitacora);
				bitacoraDAO.updateFechaSalidaBitacoraAccesorio(bitacora);

				if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_SUCURSAL)) {
					if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_A_CD);
						bitacoraDAO.saveBitacoraAccesorios(b1);
					}
				} else if (guia.getOrigen().getTipo()
						.equals(Constants.UBICACION_CENTRO_DISTRIBUCION)) {
					if (guia.getDestino().getTipo()
							.equals(Constants.UBICACION_SUCURSAL)) {
						Bitacora b1 = new Bitacora();
						b1.setEstado(new Estado());
						b1.setOrdenTrabajo(ordenTrabajo);
						b1.setFechaEntrada(guia.getFechaEmision());
						b1.getEstado()
								.setId(Constants.BITACORA_EN_CAMION_DESPUES_ENTREGA_EN_SUCURSAL);
						bitacoraDAO.saveBitacoraAccesorios(b1);
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveBitacoraByGuiaAndOT(Guia guia, OrdenTrabajo ordenTrabajo)
			throws Exception {
		try {
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			ordenTrabajo = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
			Bitacora bitacora = bitacoraDAO.getByIdGuia(guia.getId());
			saveBitacoraByGuiaAndOT(guia, ordenTrabajo, bitacora);
		} catch (Exception e) {
			throw e;
		}
	}

	public void saveBitacoraByGuiaAndOTEntregaCliente(Guia guia,
			OrdenTrabajo ordenTrabajo) throws Exception {
		try {
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			ordenTrabajo = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
			Bitacora bitacora = bitacoraDAO
					.getUltimaBitacoraAbierta(ordenTrabajo.getId());
			saveBitacoraByGuiaAndOT(guia, ordenTrabajo, bitacora);
		} catch (Exception e) {
			throw e;
		}
	}

	public void saveBitacoraByGuiaAccesorioAndOT(Guia guia,
			OrdenTrabajo ordenTrabajo) throws Exception {
		try {
			guia = guiaDAO.get(guia.getId());
			guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
			guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
			ordenTrabajo = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
			Bitacora bitacora = bitacoraDAO.getBitacoraAccesorioByIdGuia(guia
					.getId());
			saveBitacoraByGuiaAccesorioAndOT(guia, ordenTrabajo, bitacora);
		} catch (Exception e) {
			throw e;
		}
	}

	public Integer updateUbicacionAccesorio(Accesorio accesorio)
			throws Exception {
		try {
			return accesorioDAO.updateUbicacionAccesorio(accesorio);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer updateUbicacionByEstado(Ubicacion ubicacion)
			throws Exception {
		try {
			return ubicacionDAO.updateVigenciaUbicacion(ubicacion);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer listTotalOTbyidOT(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.listTotalOTbyidOT(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/* Region Contrato */
	public ServicioTecnico getOTContratoByGuia(FilterOT filterOT,
			Ubicacion ubicacion) throws Exception {
		try {
			filterOT.setIdOrigen(ubicacion.getId());

			if (filterOT.getNumeroGuia() != null) {
				List<Guia> guias = guiaDAO
						.getByNumero(filterOT.getNumeroGuia());
				if (guias == null || guias.size() == 0) {
					throw new SSTException(
							"No hay guías con los parametros ingresados");
				}
				if (guias.size() > 1) {
					throw new SSTException(
							"Existe mas de una guía que cumple las condiciones");
				}

				filterOT.setIdDestino(guias.get(0).getDestino().getId());
			}

			OrdenTrabajo orden = this.getOTByFilter(filterOT);

			if (!orden.getEstado().getId()
					.equals(Constants.OT_ESTADO_REPARACION_ESPERA_CONTRATO)) {
				throw new SSTException("La orden de trabajo tiene el estado : "
						+ orden.getEstado().getDescripcion());
			}

			Guia guia = guiaDAO.getGuiaByIdOT(orden.getId());
			guia = guiaDAO.get(guia.getId());

			if (!guia.getOrigen().getId().equals(ubicacion.getId())) {
				throw new SSTException("Esta guía pertenece a otra ubicación");
			}

			return ejecutivaService.getServicioTecnicoByOT(orden.getId());
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo getProductoOTById(Long idOT) throws Exception {
		try {
			return productoDAO.getProductoOTById(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/* End Contrato */
	public Cliente saveCliente(Cliente cliente) throws Exception {
		try {
			Cliente cl = clienteDAO.getClienteByRutFromSST(cliente);

			if (cl == null) {
				clienteDAO.saveCliente(cliente);
			} else {
				clienteDAO.updateCliente(cliente);
				// cliente.setId(cl.getId());
			}

			return clienteDAO.getClienteByRutFromSST(cliente);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo terminarOrdenTrabajo(Cliente cliente, OrdenTrabajo oT,
			Ubicacion origen) throws Exception {
		try {
			this.saveCliente(cliente);

			if (oT.getEstado()
					.getId()
					.equals(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE)
					&& oT.getTipo().getCodigo()
							.equals(Constants.TIPO_OT_CAMBIO_AUTOMATICO)) {
				oT.setCerradaCliente(true);
			}

			if (!oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET)
					&& oT.getEstado()
							.getId()
							.equals(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE)) {
				Guia guia = new Guia();
				if (((oT.getTipo().getCodigo()
						.equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR) || oT
						.getTipo().getCodigo()
						.equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR_CF))
						&& oT.getTipoCambio() != null && oT.getTipoCambio()
						.getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION))
						|| (!(oT.getTipoCambio() != null && (oT
								.getTipoCambio()
								.getCodigo()
								.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)
								|| (oT.getTipoCambio().getCodigo()
										.equals(Constants.CAMBIO_JEFE_TIENDA) && oT
										.getTipoCambioJT()
										.getCodigo()
										.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION))
								|| oT.getTipoCambio()
										.getCodigo()
										.equals(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO) || oT
								.getTipoCambio()
								.getCodigo()
								.equals(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_CARTA))))) {

					OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(oT
							.getId());
					guia.setOrdenTrabajo(oT);
					guia.setOrigen(origen);
					// guia.setDestino(ubicacionDAO.get(ordenTrabajo.getIdDestino()));

					if (oT.getsTecnico() != null
							&& oT.getsTecnico().getId() != null
							&& oT.getsTecnico().getId() > 0) {
						guia.setDestino(ubicacionDAO.get(oT.getsTecnico()
								.getId().longValue()));
					} else {
						if (ordenTrabajo.getIdDestino() == null) {
							ordenTrabajo
									.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
						}
						guia.setDestino(ubicacionDAO.get(ordenTrabajo
								.getIdDestino()));
					}
					guia.setEntregaCliente(false);
					guia.setEstado(new Estado());
					guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
					guia.setVigente(true);
					guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
					guiaDAO.save(guia);

					List<Accesorio> accesoriosOT = accesorioDAO
							.listAccesoriosByOT(oT.getId());
					for (Accesorio accesorio : accesoriosOT) {
						if (accesorio.getUbicacion().getTipo()
								.equals(origen.getTipo())) {
							accesorio.setGuia(guia);
							accesorioDAO.updateIdGuiaFromAccesorio(accesorio);
						}
					}
				}
				/**/
				Bitacora bitacora = new Bitacora();
				bitacora.setGuia(guia);
				bitacora.setOrdenTrabajo(oT);
				bitacora.setUbicacion(origen);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA);
			}

			if (oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET)) {
				oT.getEstado()
						.setId(Constants.OT_ESTADO_BIG_TICKET_A_LA_ESPERA_DE_ELEGIR_SERVICIO_TECNICO);
			}

			ordenTrabajoDAO.updateOTbyOT(oT);
			return ordenTrabajoDAO.getOTById(oT.getId());

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo updateOTFallaReiterada(OrdenTrabajo oT)
			throws Exception {
		try {
			oT.setCambioAutorizado(true);
			oT.setTipo(new TipoOT());
			oT.setTipoCambio(new TipoCambio());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA);
			oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
			Bitacora bitacora = new Bitacora();
			bitacora.setIdOT(oT.getId());
			bitacora.setEstado(new Estado());
			bitacora.getEstado().setId(
					Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CD);
			bitacoraDAO.updateBitacoraByOT(bitacora);
			ordenTrabajoDAO.updateOTbyOT(oT);
			return oT;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo updateOTCATicketCambio(OrdenTrabajo oT,
			Ubicacion ubicacion) throws Exception {
	
		try { //TODO
			// ---------------------------------------------------------------------------
			String flujoOrigen = "";
			if (ordenTrabajoDAO.getEsFallaReiterada(oT.getId()) != null) {
				flujoOrigen = ordenTrabajoDAO.getEsFallaReiterada(oT.getId());
			}
				// plarrain CU-05
			if (flujoOrigen.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
				//System.out.println("-----> Es falla reiterada: " + flujoOrigen);

				oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_FALLA_REITERADA);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				// -----> 3° Aumento del NO disponible en PMM
				ordenTrabajoDAO.updateTicketCambio(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_5);
				interfazService.createInterfazPMM(ubicacion, oT, 
						Constants.DC_QUANTITY_POSITIVO);		
				// -----> 4° Tracking
				interfazService
						.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
				// -----> 5° Generar interfaz de Orden
				interfazService.createInterfazOrden(ubicacion, oT);
				// -----> 6° Tracking
				interfazService
						.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_ORR);
				// -----> 7° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 8° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);

				// ---------------------------------------------------------------------------
				// plarrain CU-01
			} else if (flujoOrigen.equals(Constants.CASO_USO_1)) {
				oT.setMotivoCambio(Constants.CAMBIO_CARTA_PRODUCTO_FISICO);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_1);
				// -----> 3° Aumento del NO disponible en PMM
				interfazService.createInterfazPMM(ubicacion, oT,
						Constants.DC_QUANTITY_POSITIVO);
				// -----> 4° Tracking
				interfazService
						.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
				// -----> 5° Generar interfaz de Orden
				interfazService.createInterfazOrden(ubicacion, oT);
				// -----> 6° Tracking
				interfazService
						.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_ORR);
				// -----> 7° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 8° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
				// ---------------------------------------------------------------------------
				// plarrain CU-02
			} else if (flujoOrigen.equals(Constants.CASO_USO_2)) {
				oT.setMotivoCambio(Constants.CAMBIO_CARTA_SIN_PRODUCTO_FISICO);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_2);
				// -----> 3° Aumento del NO disponible en PMM
				interfazService.createInterfazPMM(ubicacion, oT,
						Constants.DC_QUANTITY_POSITIVO);
				// -----> 4° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
				// -----> 5° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 6° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
				// ---------------------------------------------------------------------------
				// plarrain CU-03
			} else if (flujoOrigen.equals(Constants.CASO_USO_3)) {
				oT.setMotivoCambio(Constants.CAMBIO_CON_PRODUCTO_FISICO);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				// -----> 3° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 4° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
				// ---------------------------------------------------------------------------
				// plarrain CU-04
			} else if (flujoOrigen.equals(Constants.CASO_USO_4)) {				
				oT.setMotivoCambio(Constants.CAMBIO_SIN_PRODUCTO_FISICO);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT,
				Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				// -----> 3° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 4° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
				 // ---------------------------------------------------------------------------
				 // plarrain CU-08
			}  else if (flujoOrigen.equals(Constants.CASO_USO_8)) {
				oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_PROVEEDOR);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_8);
				// -----> 3° Aumento del NO disponible en PMM
				interfazService.createInterfazPMM(ubicacion, oT, Constants.DC_QUANTITY_POSITIVO);
				// -----> 4° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
				// -----> 5° Generar interfaz de Orden
				interfazService.createInterfazOrden(ubicacion, oT);
				// -----> 6° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_ORR);
				// -----> 7° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 8° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
			}   else if (flujoOrigen.equals(Constants.CASO_USO_7)) {
				oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_FALLA_VENTA_MENOR_24_HORAS);
				// -----> 1° Generar xn
				oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
				// -----> 2° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
				oT.setProcesadoOW(true);
				oT.setTicketCambio(getNumeroTicketCambio());
				ordenTrabajoDAO.updateTicketCambio(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_8);
				// -----> 3° Aumento del NO disponible en PMM
				interfazService.createInterfazPMM(ubicacion, oT, Constants.DC_QUANTITY_POSITIVO);
				// -----> 4° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
				// -----> 5° Generar interfaz de Orden
				interfazService.createInterfazOrden(ubicacion, oT);
				// -----> 6° Tracking
				interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_ORR);
				// -----> 7° Ot cerrada por cliente
				oT.setCerradaCliente(true);
				oT.setCerrada(false);
				// -----> 8° Actualiza estado de la Ot
				Estado e = new Estado();
				e.setId(Constants.OT_CERRADA_POR_USUARIO);
				oT.setEstado(e);
				ordenTrabajoDAO.updateCerrarOT(oT);
			} else {

				if (Constants.TIPO_OT_GARANTIA_MASTER.equals(oT.getTipo().getCodigo())) {
					return oT;
				}

				if(Constants.TIPO_OT_CAMBIO_AUTOMATICO.equals(oT.getTipo().getCodigo())){
					oT.setBanderaOrigenOT(Constants.CASO_USO_6);
				}
				
				/*if(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO.equals(oT.getTipoCambio().getCodigo())){
					oT.setBanderaOrigenOT(Constants.CASO_USO_8);
				}
				
				if(Constants.TIPO_OT_CAMBIO_MENOR_24_HRS.equals(oT.getTipoCambio().getCodigo())){
					oT.setBanderaOrigenOT(Constants.CASO_USO_7);
				}*/
				
				if (Constants.TIPO_OT_CAMBIO_AUTOMATICO.equals(oT.getTipo().getCodigo()) || Constants.TIPO_OT_GARANTIA_PROVEEDOR.equals(oT.getTipo().getCodigo())) {
					if (oT.getTipoCambio() != null) {
						if (Constants.TIPO_OT_CAMBIO_MENOR_24_HRS.equals(oT.getTipoCambio().getCodigo())) {
							oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_FALLA_VENTA_MENOR_24_HORAS);
						} else {
							oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_PROVEEDOR);
						}
					} else {
						oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_PROVEEDOR);
					}

					oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
					interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
					oT.setProcesadoOW(true);
					oT.setTicketCambio(getNumeroTicketCambio());
					ordenTrabajoDAO.updateTicketCambio(oT);
					interfazService.createInterfazPMM(ubicacion, oT, Constants.DC_QUANTITY_POSITIVO);
					interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);

					// if
					// (oT.getEstado().getId().equals(Constants.OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE_CA))
					// {
					
					/*if(Constants.CASO_USO_7.equalsIgnoreCase(oT.getBanderaOrigenOT()) || Constants.CASO_USO_8.equalsIgnoreCase(oT.getBanderaOrigenOT())){
						interfazService.createInterfazOrden(ubicacion, oT);
						interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_ORR);
					}*/
					
					// }
					oT.setCerradaCliente(true);
					oT.setCerrada(false);
					Estado e = new Estado();
					e.setId(10003000); // OT en sucursal, a la espera de ser
										// enviada
					oT.setEstado(e);
					ordenTrabajoDAO.updateCerrarOT(oT);
					if (oT.getEstado().getId().equals(Constants.OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE_CA) || oT.getEstado().getId().equals(Constants.OT_ESTADO_PENDIENTE_POR_TICKET_DE_CAMBIO_QUE_TIENE_EL_CLIENTE_CA)) {
						oT.getEstado().setId(Constants.OT_CERRADA_POR_USUARIO);
						ordenTrabajoDAO.updateEstadoOT(oT);
					}
				}// fin de condicion if

				if (Constants.TIPO_OT_GARANTIA_PROVEEDOR_CF.equals(oT.getTipo()
						.getCodigo())) {
					oT.setMotivoCambio(Constants.CAMBIO_PRODUCTO_PROVEEDOR);
					oT.setBanderaOrigenOT(Constants.CASO_USO_10);
					ordenTrabajoDAO.updateOTBTNOrigen(oT);
					// 1° Generar interfaz XN
					oT.setNumeroXN(oWService.createXN(oT.getId(), ubicacion));
					// 2° Insertara Tracking
					interfazService.saveOtTracking(oT,
							Constants.ID_OT_INTERFAZ_XN);
					oT.setProcesadoOW(true);
					oT.setTicketCambio(getNumeroTicketCambio());
					ordenTrabajoDAO.updateTicketCambio(oT);

					oT.setCerradaCliente(true);
					oT.setCerrada(false);

					Estado e = new Estado();
					e.setId(Constants.OT_CERRADA_POR_USUARIO);
					oT.setEstado(e);
					ordenTrabajoDAO.updateCerrarOT(oT);
				}
			}

			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo updateOTFRNotaCredito(OrdenTrabajo oT) throws Exception {
		try {
			ordenTrabajoDAO.updateNotaCredito(oT);
			return oT;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo updateNotaCredito(OrdenTrabajo oT) throws Exception {
		try {
			ordenTrabajoDAO.updateNotaCredito(oT);
			return oT;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listSTecnicoFromProductoByUbicacion(
			FilterServicioTecnico filterServicioTecnico,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			filterServicioTecnico.setIdOrigen(ubicacion.getId());
			filterServicioTecnico.setOrderBy(gridControl.getOrderBy());
			filterServicioTecnico.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(servicioTecnicoDAO
					.listSTecnicoFromProductoByUbicacion(filterServicioTecnico,
							gridControl));
			listRange
					.setTotal(servicioTecnicoDAO
							.getTotalSTecnicoFromProductoByUbicacion(filterServicioTecnico));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Ubicacion getUbicacionById(Long id) throws Exception {
		try {
			return ubicacionDAO.get(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Sucursal getSucursalById(Long id) throws Exception {
		try {
			return sucursalDAO.getSucursalById(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Sucursal> listSucursalesByComuna(Long idComuna)
			throws Exception {
		try {
			return sucursalDAO.listSucursalesByComuna(idComuna);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Logistico getLogisticoById(Long id) throws Exception {
		try {
			return logisticoDAO.getLogisticoById(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveSTecnicoOT(OrdenTrabajo ot, Usuario usuario)
			throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(ot.getId());
			servicioTecnicoDAO.updateSTecnicoOT(ot);
			ordenTrabajoDAO.updateExcluirCCalidad(ot);

			Gestion gestion = new Gestion();
			gestion.setOt(ot);
			gestion.setUsuario(usuario);
			gestion.setGestion(" ingresa el contrato y/o diagnostico del servicio tecnico");
			gestionesDAO.saveGestion(gestion);

			if (ot.getCalificaFR() != null
					&& !ot.getCalificaFR().equals(ordenTrabajo.getCalificaFR())) {
				Gestion gestionCalificaFR = new Gestion();
				gestionCalificaFR.setOt(ot);
				gestionCalificaFR.setUsuario(usuario);
				if (ot.getCalificaFR()) {
					gestionCalificaFR
							.setGestion(" producto califica para fallas reiteradas");
				} else {
					gestionCalificaFR
							.setGestion(" producto NO califica para fallas reiteradas");
				}
				gestionesDAO.saveGestion(gestion);
			}

			if (ordenTrabajo.getEstado().getId()
					.equals(Constants.OT_ESTADO_REPARACION_ESPERA_CONTRATO)) {
				Guia guia = guiaDAO.getGuiaByIdOT(ot.getId());
				guia.setEstado(estadoDAO
						.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
				guia.setVigente(true);
				guiaDAO.updateEstado(guia);

				if (ot.getContratoEmitido()) {
					ot.setEstado(estadoDAO
							.getEstadoById(Constants.OT_EN_ST_CON_CONTRATO));
				} else {
					ot.setEstado(estadoDAO
							.getEstadoById(Constants.OT_EN_ST_SIN_CONTRATO));
				}
				ordenTrabajoDAO.updateEstadoOT(ot);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia getGuiaByIdOT(Long idOT) throws Exception {
		try {
			return guiaDAO.getGuiaByIdOT(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTGM(OrdenTrabajo oT, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			oT.setSucursal(getSucursalById(ubicacion.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			Ubicacion destino = getUbicacionById(oT.getIdDestino());

			if (oT.getEstadoBitacora() == null) {
				if (destino.getTipo().equals(
						Constants.UBICACION_SERVICIO_TECNICO)) {
					oT.setServicioTecnico(new ServicioTecnico(destino));
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL);
				} else if (destino.getTipo().equals(
						Constants.UBICACION_BODEGA_REGIONAL)) {
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_A_LA_BODEGA_REGIONAL);
				} else if (destino.getTipo().equals(
						Constants.UBICACION_CENTRO_DISTRIBUCION)) {
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD);
				}
			}

			oT.setEstado(estadoDAO
					.getEstadoById(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE));
			oT = saveOT(oT, usuario, ubicacion);
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTGMFromCDForCambio(OrdenTrabajo ot,
			Ubicacion sucursal, Date fechaActual, String origenGlosa,
			Usuario usuario) throws Exception {
		try {
			ot.setCliente(this.saveCliente(ot.getCliente()));
			ot.setSucursal(sucursalDAO.getSucursalById(ot.getSucursal().getId()
					.longValue()));
			if (origenGlosa.equals("origenOrigen")) {
				ot.setServicioTecnico(new ServicioTecnico());
				ot.getServicioTecnico().setId(0);
				ot.setEstado(estadoDAO
						.getEstadoById(Constants.OT_ESTADO_EN_CLIENTE_ESPERA_ENTREGA_EN_SUCURSAL));
				// ot.setEstado(estadoDAO.getEstadoById(Constants.OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA));
				ot.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CAMION_A_CD_PARA_REMATE);
			} else if (origenGlosa.equals("origenDomicilio")) {
				ot.setServicioTecnico(new ServicioTecnico());
				ot.getServicioTecnico().setId(0);
				ot.setEstado(estadoDAO
						.getEstadoById(Constants.OT_ESTADO_EN_CLIENTE_A_LA_ESPERA_DE_RECUPERACION));
				ot.setEstadoBitacora(Constants.BITACORA_EN_CLIENTE_A_LA_ESPERA_DE_RECUPERARLO_UNA_SUCURSAL);
			} else if (origenGlosa.equals("origenSTecnico")) {
				ot.setServicioTecnico(servicioTecnicoDAO.getSTecnicoById(ot
						.getServicioTecnico().getId().longValue()));
				ot.setEstado(estadoDAO
						.getEstadoById(Constants.OT_ESTADO_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO));
				ot.setEstadoBitacora(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SUCURSAL);
			}
			ot.setTipo(new TipoOT());
			ot.getTipo().setCodigo(Constants.TIPO_OT_GARANTIA_MASTER);
			ot.setIdDestino(0L);
			ot = saveOT(ot, usuario, sucursal);
			return ot;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTGMFromCD(OrdenTrabajo ot, Ubicacion sucursal,
			Date fechaActual, Usuario usuario) throws Exception {
		try {
			ot.setCliente(this.saveCliente(ot.getCliente()));
			ot.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			if (ot.getServicioTecnico() != null) {
				ot.setServicioTecnico(servicioTecnicoDAO.getSTecnicoById(ot
						.getServicioTecnico().getId().longValue()));
			} else {
				ot.setServicioTecnico(new ServicioTecnico());
				ot.getServicioTecnico().setId(0);
			}
			ot.setTipo(new TipoOT());
			ot.getTipo().setCodigo(Constants.TIPO_OT_GARANTIA_MASTER);
			Ubicacion ubicacionDestino = ubicacionDAO.get(ot.getIdDestino());
			if (ubicacionDestino.getTipo().equals(
					Constants.UBICACION_SERVICIO_TECNICO)) {
				ot.setServicioTecnico(servicioTecnicoDAO.getSTecnicoById(ot
						.getIdDestino()));
				ot.setEstadoBitacora(Constants.BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL_20001000);
			} else if (ubicacionDestino.getTipo().equals(
					Constants.UBICACION_CENTRO_DISTRIBUCION)) {
				ot.setEstadoBitacora(Constants.BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL);
			}
			ot.setEstado(estadoDAO
					.getEstadoById(Constants.OT_ESTADO_EN_CLIENTE_ESPERA_ENTREGA_EN_SUCURSAL));
			ot = saveOT(ot, usuario, sucursal);
			return ot;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTGP(OrdenTrabajo oT, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			oT.setSucursal(getSucursalById(ubicacion.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			Ubicacion destino = getUbicacionById(oT.getIdDestino());

			if (!oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET)) {
				if (destino.getTipo().equals(
						Constants.UBICACION_SERVICIO_TECNICO)) {
					oT.setServicioTecnico(new ServicioTecnico(destino));
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL);
				} else if (destino.getTipo().equals(
						Constants.UBICACION_BODEGA_REGIONAL)) {
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_A_LA_BODEGA_REGIONAL);
				} else if (destino.getTipo().equals(
						Constants.UBICACION_CENTRO_DISTRIBUCION)) {
					oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD);
				}
			} else {
				oT.setEstadoBitacora(Constants.BITACORA_OT_EN_SUCURSAL_LUEGO_EJECUTIVA_ASIGNA_SERVICIO_TECNICO);
			}
			oT.setEstado(estadoDAO
					.getEstadoById(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE));
			oT = saveOT(oT, usuario, ubicacion);
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTParaEvaluacion(OrdenTrabajo oT, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			Sucursal sucursal = getSucursalById(ubicacion.getId());
			oT.setSucursal(sucursal);
			Logistico logisitco = getLogisticoById(usuario.getId());
			oT.setLogistico(logisitco);
			oT.setTipo(new TipoOT());
			/*
			 * Proyecto : 10021 - Mejoras Servicio Tecnico Objetivo :
			 * Diferenciar las OT que necesitan certificacion de falla Fecha :
			 * 07/09/2015 Autor : Richard Flores - ScriptIA
			 * 
			 * INICIO******************* Original **********************
			 * oT.getTipo().setCodigo(Constants.TIPO_OT_GARANTIA_PROVEEDOR);
			 * **************************************************
			 */
			oT.getTipo().setCodigo(Constants.TIPO_OT_GARANTIA_PROVEEDOR_CF);
			/*
			 * FIN
			 */
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION);
			oT.setCambioAutorizado(false);
			oT.setEstado(new Estado());
			oT.getEstado()
					.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			Ubicacion destino = getUbicacionById(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);

			oT.setIdDestino(destino.getId());

			oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD);

			// if (destino != null && destino.getId() != null && destino.getId()
			// != 0) {
			// if
			// (destino.getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO))
			// {
			// oT.setServicioTecnico(new ServicioTecnico(destino));
			// oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL);
			// } else if
			// (destino.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)) {
			// oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_A_LA_BODEGA_REGIONAL);
			// } else if
			// (destino.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION))
			// {
			// oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD);
			// }
			// }

			oT.setEstadoBitacora(oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET) ? Constants.BITACORA_OT_EN_SUCURSAL_LUEGO_EJECUTIVA_ASIGNA_SERVICIO_TECNICO
					: oT.getEstadoBitacora());
			oT = saveOT(oT, usuario, ubicacion);

			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTGenerico(OrdenTrabajo oT, String tipoDocumento,
			Ubicacion sucursal, Usuario usuario) throws Exception {
		try {

			// --------------------------------------------------------------------------------------------------------------
			/*
			 * if(oT.getNotaCredito()!=null){ updateNotaCredito(oT); }
			 * ReglaComercial reglaComercial =
			 * getReglaComercialVigentePorValor(oT.getProducto(), sucursal);
			 * oT.setReglaComercial
			 * (reglaComercialDAO.getLastReglaComercialHistoricaByIdRegla
			 * (reglaComercial.getId()));
			 */
			// --------------------------------------------------------------------------------------------------------------

			oT.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			oT.setIdDestino(0L);
			oT.setEstado(new Estado());

			if (oT.getBanderaOrigenOT().equals(Constants.CASO_USO_6)
					|| oT.getBanderaOrigenOT().equals(Constants.CASO_USO_9)) {
				System.out.println("oT.getBanderaOrigen().toString(): "
						+ oT.getBanderaOrigenOT().toString());
				TipoCambio tipoCambio = new TipoCambio();
				tipoCambio.setCodigo(oT.getBanderaOrigenOT());
				oT.setTipoCambioJT(tipoCambio);
			}

			if (oT.getTipoCambioJT().getCodigo()
					.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			} else if (oT.getTipoCambioJT().getCodigo()
					.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CD);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			} else {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_ALMACENADA_EN_SUCURSAL);
				oT.getEstado().setId(Constants.OT_ESTADO_EN_SUCURSAL_SIN_ENVIO);
			}
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(Constants.CAMBIO_JEFE_TIENDA);
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			if (tipoDocumento != null
					&& tipoDocumento
							.equals(Constants.TIPO_DOCUMENTO_TICKET_CAMBIO)) {
				oT.setTicketCambio(getNumeroTicketCambio());
			}

			Cliente cliente = oT.getCliente();
			oT = saveOT(oT, usuario, sucursal);
			oT.setCliente(this.saveCliente(cliente));
			ordenTrabajoDAO.updateClienteByOT(oT);
			// --------------------------------------------------------------------------------------------------------------
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTJT(OrdenTrabajo oT, String tipoDocumento,
			Ubicacion sucursal, Usuario usuario) throws Exception {
		try {
			oT.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			oT.setIdDestino(0L);
			oT.setEstado(new Estado());

			if (oT.getTipoCambioJT().getCodigo()
					.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			} else if (oT.getTipoCambioJT().getCodigo()
					.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CD);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			} else {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_ALMACENADA_EN_SUCURSAL);
				oT.getEstado().setId(Constants.OT_ESTADO_EN_SUCURSAL_SIN_ENVIO);
			}

			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(Constants.CAMBIO_JEFE_TIENDA);
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			if (tipoDocumento != null
					&& tipoDocumento
							.equals(Constants.TIPO_DOCUMENTO_TICKET_CAMBIO)) {
				oT.setTicketCambio(getNumeroTicketCambio());
			}

			if (oT.getTipoCambioJT().getCodigo()
					.equals(Constants.CAMBIO_AUTOMATICO_VALOR)) {
				Cliente cliente = oT.getCliente();
				oT = saveOT(oT, usuario, sucursal);
				oT.setCliente(this.saveCliente(cliente));
				ordenTrabajoDAO.updateClienteByOT(oT);
			} else {
				oT = saveOT(oT, usuario, sucursal);
			}

			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Long getNumeroTicketCambio() throws Exception {
		try {
			return utilDAO.getNumeroTicketCambio();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}

	}

	public OrdenTrabajo saveOTCambioPorValor(OrdenTrabajo oT,
			String tipoDocumento, Ubicacion sucursal, Usuario usuario)
			throws Exception {
		try {
			oT.setCliente(this.saveCliente(oT.getCliente()));
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			oT.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_ALMACENADA_EN_SUCURSAL);
			oT.setEstado(new Estado());
			oT.getEstado().setId(Constants.OT_ESTADO_EN_SUCURSAL_SIN_ENVIO);
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(Constants.CAMBIO_AUTOMATICO_VALOR);
			if (tipoDocumento.equals(Constants.TIPO_DOCUMENTO_TICKET_CAMBIO)) {
				oT.setTicketCambio(getNumeroTicketCambio());
			}
			oT = saveOT(oT, usuario, sucursal);
			if (oT.getNotaCredito() != null) {
				updateNotaCredito(oT);
			}
			ReglaComercial reglaComercial = getReglaComercialVigentePorValor(
					oT.getProducto(), sucursal);
			oT.setReglaComercial(reglaComercialDAO
					.getLastReglaComercialHistoricaByIdRegla(reglaComercial
							.getId()));
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTCambioPorVentaMenor24Hr(OrdenTrabajo oT,
			Ubicacion sucursal, Usuario usuario) throws Exception {
		try {
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);

			oT.setSucursal(sucursal != null ? sucursalDAO
					.getSucursalById(sucursal.getId()) : new Sucursal());
			oT.setLogistico(usuario != null ? getLogisticoById(usuario.getId())
					: new Logistico());
			if (oT.getEstadoBitacora() == null) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
			}
			oT.setEstado(new Estado());
			oT.getEstado()
					.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_VENTA_MENOR_24_HORAS);
			/*Elimina validacion de reglas comerciales para flujod e 24 hrs
			 * ReglaComercial reglaComercial = getReglaComercialVigentePorAutorizacionProveedor(
					oT.getProducto(), sucursal);
			if (reglaComercial != null) {
				oT.setReglaComercial(reglaComercialDAO
						.getLastReglaComercialHistoricaByIdRegla(reglaComercial
								.getId()));*/
				oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
				oT = saveOT(oT, usuario, sucursal);
				
				String origen = Constants.CASO_USO_7;
				oT.setBanderaOrigenOT(origen);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
				return oT;
			/*} else {
				throw new SSTException(
						"Este producto no tiene regla comercial vigente por autorizacion proveedor");
			}*/

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTCambioPorProveedor(OrdenTrabajo oT,
			Ubicacion sucursal, Usuario usuario) throws Exception {
		try {
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);

			oT.setSucursal(sucursal != null ? sucursalDAO
					.getSucursalById(sucursal.getId()) : new Sucursal());
			oT.setLogistico(usuario != null ? getLogisticoById(usuario.getId())
					: new Logistico());
			if (oT.getEstadoBitacora() == null) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
			}
			oT.setEstado(new Estado());
			oT.getEstado()
					.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO);
			ReglaComercial reglaComercial = getReglaComercialVigentePorAutorizacionProveedor(
					oT.getProducto(), sucursal);
			if (reglaComercial != null) {
				oT.setReglaComercial(reglaComercialDAO
						.getLastReglaComercialHistoricaByIdRegla(reglaComercial
								.getId()));
				oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
				oT = saveOT(oT, usuario, sucursal);
				
				String origen = Constants.CASO_USO_8;
				oT.setBanderaOrigenOT(origen);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
				return oT;
			} else {
				throw new SSTException(
						"Este producto no tiene regla comercial vigente por autorizacion proveedor");
			}

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public CambioAutomaticoProveedorCarta getNumeroCertificadoByIdProducto(
			Producto producto) throws Exception {
		try {
			return cambioAutomaticoProveedorCartaDAO
					.getNumeroCertificadoByIdProducto(producto);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTCambioPorProveedorCertificado(OrdenTrabajo oT,
			CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta,
			String productoFisico, Ubicacion sucursal, Usuario usuario)
			throws Exception {
		
		String origen = "";
		try {
			CambioAutomaticoProveedorCarta cartaCambio = new CambioAutomaticoProveedorCarta();
			cambioAutomaticoProveedorCarta.setProducto(productoDAO
					.getProductoById(oT.getProducto().getId()));

			// TODO ELIMINAR CUANDO LO INGRESE EL PROVEEDOR
			proveedorService
					.saveCambioAutProveedorCarta(cambioAutomaticoProveedorCarta);
			// END TODO ELIMINAR CUANDO LO INGRESE EL PROVEEDOR

			cartaCambio = proveedorService
					.getVigenteByNumeroSerieAndProductoAndCertificado(cambioAutomaticoProveedorCarta);

			if (cartaCambio == null) {
				throw new SSTException(
						"No existe un certificado para el producto "
								+ cambioAutomaticoProveedorCarta.getProducto()
										.getId()
								+ " - "
								+ cambioAutomaticoProveedorCarta.getProducto()
										.getDescripcion());
			}

			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			oT.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			// oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CD);

			oT.setEstado(new Estado());
			// oT.getEstado().setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			// if
			// (productoFisico.equalsIgnoreCase(Constants.CON_PRODUCTO_FISICO))
			// {

			// System.out.println("-----> oT.getId()): "+oT.getId());
			// System.out.println("-----> oT.getConOsinProductoFisico(): "+oT.getConOsinProductoFisico());

			if (oT.getConOsinProductoFisico().equals(
					Constants.CON_PRODUCTO_FISICO)) {
				oT.setBanderaOrigenOT(Constants.CASO_USO_1);
				origen = Constants.CASO_USO_1;
				// ordenTrabajoDAO.updateOTBTNOrigen(oT);
				// oT.getEstado().setId(Constants.OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE_CA);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
			} else if (oT.getConOsinProductoFisico().equals(
					Constants.SIN_PRODUCTO_FISICO)) {
				oT.setBanderaOrigenOT(Constants.CASO_USO_2);
				origen = Constants.CASO_USO_2;
				// ordenTrabajoDAO.updateOTBTNOrigen(oT);
				oT.getEstado()
						.setId(Constants.OT_ESTADO_PENDIENTE_POR_TICKET_DE_CAMBIO_QUE_TIENE_EL_CLIENTE_CA);
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_ALMACENADA_EN_SUCURSAL);
			}
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_CARTA);
			oT.setNumeroSerie(cambioAutomaticoProveedorCarta.getNumeroSerie());
			// oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
			oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);

			//System.out.println("-----> banderaOrigen: "+oT.getBanderaOrigenOT()+", id:"+oT.getId());
			oT = saveOT(oT, usuario, sucursal);
			oT.setBanderaOrigenOT(origen);
			ordenTrabajoDAO.updateOTBTNOrigen(oT);
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo saveOTFallaFabricacion(OrdenTrabajo oT,
			Ubicacion sucursal, Usuario usuario) throws Exception {
		try {
			oT.setTipo(new TipoOT());
			oT.getTipo().setCodigo(Constants.TIPO_OT_CAMBIO_AUTOMATICO);
			oT.setSucursal(sucursalDAO.getSucursalById(sucursal.getId()));
			oT.setLogistico(getLogisticoById(usuario.getId()));
			if (oT.getEstadoBitacora() == (null)) {
				oT.setEstadoBitacora(Constants.BITACORA_EN_SUCURSAL_LUEGO_CAMION_PARA_FF);
			}
			oT.setEstado(new Estado());
			oT.getEstado()
					.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
			oT.setServicioTecnico(new ServicioTecnico());
			oT.getServicioTecnico().setId(0);
			oT.setTipoCambio(new TipoCambio());
			oT.getTipoCambio().setCodigo(
					Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION);
			ReglaComercial reglaComercial = getReglaComercialVigentePoFallaFabricacion(
					oT.getProducto(), sucursal);
			if (reglaComercial != null) {
				oT.setReglaComercial(reglaComercialDAO
						.getLastReglaComercialHistoricaByIdRegla(reglaComercial
								.getId()));
			}
			oT.setIdDestino(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF);
			oT = saveOT(oT, usuario, sucursal);
			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/**
	 * @param oT
	 * @param usuario
	 * @param sucursal
	 * @return
	 * @throws Exception
	 */
	public OrdenTrabajo saveOT(OrdenTrabajo oT, Usuario usuario,
			Ubicacion sucursal) throws Exception {
		try {
			oT.setFechaVencimiento(getFechaVencimientoGarantiaProducto(oT
					.getProducto().getId(), oT.getIdDocumento(), oT
					.getTipoDocumento()));
			oT.setTareaUrgente(false);
			oT.setTareaUrgenteFF(false);
			oT.setCalificaFR(true);
			oT.setNuevaOt(true);
			FilterOT filter = new FilterOT();
			Date fechaActual = utilDAO.getDate();
			filter.setTipoOT(oT.getTipo().getCodigo());
			filter.setTipoDocumento(oT.getTipoDocumento());
			filter.setIdDocumento(oT.getIdDocumento().longValue());
			filter.setIdProducto((oT.getProducto().getId()).longValue());
			filter.setCerradaCliente(false);
			filter.setVigente(true);
			Integer existeOT = ordenTrabajoDAO.exsiteAntesCrear(filter);
			FilterProducto filterProducto = new FilterProducto();
			filterProducto.setTipoDocumento(oT.getTipoDocumento());
			filterProducto.setIdDocumento(oT.getIdDocumento().longValue());
			filterProducto
					.setIdProducto((oT.getProducto().getId()).longValue());
			Integer productoEnDocumento = productoDAO
					.getTotalSumEnDocumentoByFilter(filterProducto);
			if (productoEnDocumento == 0) {
				throw new SSTException(
						"La cantidad de productos en el documento es "
								+ productoEnDocumento);
			}
			if (existeOT >= productoEnDocumento) {
				throw new SSTException(
						"No se puede crear la orden de trabajo, ya existen "
								+ existeOT + " en el sistema.");
			}
			if (oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_MASTER)) {
				if (oT.getNumeroAtencion() != null
						&& oT.getNumeroAtencion() > 0
						&& ordenTrabajoDAO.existeNumeroAtencion(oT
								.getNumeroAtencion()) > 0) {
					throw new SSTException(
							"Ya existe una orden de trabajo con el numero de atencion "
									+ oT.getNumeroAtencion() + ".");
				}
				if (oT.getNumeroCambio() != null
						&& oT.getNumeroCambio() > 0
						&& ordenTrabajoDAO.existeNumeroCambio(oT
								.getNumeroCambio()) > 0) {
					throw new SSTException(
							"Ya existe una orden de trabajo con el numero de autorizacion de cambio "
									+ oT.getNumeroCambio() + ".");
				}
				if ((oT.getNumeroCambio() == null || oT.getNumeroCambio()
						.equals(0))
						&& (oT.getNumeroAtencion() == null || oT
								.getNumeroAtencion().equals(0))) {
					throw new SSTException(
							"Para crear una OT de garantia Master el numero de atencion o de autorizacion de cambio deben ser mayor que cero.");
				}
			}
			oT.setCambioAutorizado(false);
			oT.setCerradaCliente(false);
			oT.setVigente(true);
			oT.setCerrada(false);
			oT.setProcesadoOW(false);
			if (oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_CAMBIO_AUTOMATICO)) {
				Ubicacion ubicacion = ubicacionDAO.get(oT.getSucursal().getId()
						.longValue());
				if (oT.getTipoCambio().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_VALOR)) {
					oT.setNumeroXN(oWService.createXN(oT, ubicacion));
					oT.setProcesadoOW(true);
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(true);
					oT.setCerradaCliente(true);
					ordenTrabajoDAO.saveOT(oT);

				} else if (Constants.CAMBIO_AUTOMATICO_VENTA_MENOR_24_HORAS.equals(oT.getTipoCambio().getCodigo())) { //TODO CU-07
					//System.out.println("generar venta menor a 24 horas");
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(true);
					ordenTrabajoDAO.saveOT(oT);
					
				} else if (oT.getTipoCambio().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)) {
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(false);
					ordenTrabajoDAO.saveOT(oT);
					// CU- 05 FALLA REITERADA
				} else if (oT.getTipoCambio().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
					// oT.setNumeroXN(oWService.createXN(oT,ubicacion));
					// interfazService.createInterfazPMM(ubicacion,oT,Constants.DC_QUANTITY_POSITIVO);
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(false);
					ordenTrabajoDAO.saveOT(oT);
				} else if (oT.getTipoCambio().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_CARTA)) {
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(false);
					ordenTrabajoDAO.saveOT(oT);
				} else if (oT.getTipoCambio().getCodigo().equals(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO)) { //TODO CU-08
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(true);
					ordenTrabajoDAO.saveOT(oT);
				} else if (oT.getTipoCambio().getCodigo().equals(Constants.TIPO_OT_JEFE_TIENDA)) {

					if (oT.getTipoCambioJT().getCodigo().equals(Constants.CAMBIO_AUTOMATICO_VALOR)) {
						oT.setNumeroXN(oWService.createXN(oT, ubicacion));
						oT.setProcesadoOW(true);
						oT.setCerrada(false);
						oT.setCerradaCliente(false);
					}

					/* Cambio en jefe de tienda por menor valor $15000 */
					if (oT.getBanderaOrigenOT().equals(Constants.CASO_USO_6) || oT.getBanderaOrigenOT().equals(Constants.CASO_USO_9)) {
						// -----> 1° Generar xn
						oT.setNumeroXN(oWService.createXN(oT, ubicacion));
						oT.setProcesadoOW(true);
						oT.setTicketCambio(getNumeroTicketCambio());
						ordenTrabajoDAO.updateTicketCambio(oT);
						// -----> 2° Aumento del NO disponible en PMM
						interfazService.createInterfazPMM(ubicacion, oT,
								Constants.DC_QUANTITY_POSITIVO);
						oT.setCerradaCliente(true);
						oT.setCerrada(false);
					}

					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					ordenTrabajoDAO.saveOT(oT);
					// -----> 3° Tracking
					interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_XN);
					// -----> 4° Tracking
					interfazService.saveOtTracking(oT, Constants.ID_OT_INTERFAZ_PMM);
					
				} else if (oT.getTipoCambio().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
					oT.setFechaCambio(fechaActual);
					oT.setCambioAutorizado(true);
					oT.setCerrada(false);
					oT.setCerradaCliente(false);
					ordenTrabajoDAO.saveOT(oT);
				}
			} else {
				ordenTrabajoDAO.saveOT(oT);
			}

			/*
			 * Proyecto : 10021 - Mejoras Servicio Tecnico Objetivo : Asignar la
			 * ejecutiva al momento de crear la OT Fecha : 07/09/2015 Autor :
			 * Richard Flores - ScriptIA
			 * 
			 * INICIO*************** Original ********************* if
			 * (!(oT.getTipo
			 * ().getCodigo().equals(Constants.TIPO_OT_GARANTIA_MASTER)) &&
			 * (oT.getServicioTecnico()!=null && oT.getServicioTecnico().getId()
			 * != null && oT.getServicioTecnico().getId() > 0)) {
			 * FilterEjecutiva filterEjecutiva = new FilterEjecutiva();
			 * filterEjecutiva
			 * .setIdSTecnico(oT.getServicioTecnico().getId().longValue());
			 * filterEjecutiva
			 * .setIdProducto(oT.getProducto().getId().longValue()); if
			 * (ejecutivaDAO.existeEjecutivaByFilter(filterEjecutiva) == 1) {
			 * Persona ejecutiva =
			 * ejecutivaDAO.getEjecutivaByFilter(filterEjecutiva); if (ejecutiva
			 * != null && ejecutiva.getId() != null && ejecutiva.getId() > 0) {
			 * ordenTrabajoDAO.updateEjecutiva(oT); } } }
			 * *********************************************
			 */
			if (!(oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_MASTER))) {
				if ((oT.getServicioTecnico() != null
						&& oT.getServicioTecnico().getId() != null && oT
						.getServicioTecnico().getId() > 0)) {
					FilterEjecutiva filterEjecutiva = new FilterEjecutiva();
					filterEjecutiva.setIdSTecnico(oT.getServicioTecnico()
							.getId().longValue());
					filterEjecutiva.setIdProducto(oT.getProducto().getId()
							.longValue());
					if (ejecutivaDAO.existeEjecutivaByFilter(filterEjecutiva) == 1) {
						Persona ejecutiva = ejecutivaDAO
								.getEjecutivaByFilter(filterEjecutiva);
						if (ejecutiva != null && ejecutiva.getId() != null
								&& ejecutiva.getId() > 0) {
							oT.setEjecutiva(ejecutiva);
							ordenTrabajoDAO.updateEjecutiva(oT);
						}
					}
				} else if (oT.getTipo().getCodigo()
						.equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR)
						|| oT.getTipo()
								.getCodigo()
								.equals(Constants.TIPO_OT_GARANTIA_PROVEEDOR_CF)) {
					FilterEjecutiva filterEjecutiva = new FilterEjecutiva();
					filterEjecutiva.setIdSTecnico(0L);
					filterEjecutiva.setIdProducto(oT.getProducto().getId()
							.longValue());
					if (ejecutivaDAO.existeEjecutivaByFilter(filterEjecutiva) >= 1) {
						Persona ejecutiva = ejecutivaDAO
								.getEjecutivaByFilter(filterEjecutiva);
						if (ejecutiva != null && ejecutiva.getId() != null
								&& ejecutiva.getId() > 0) {
							oT.setEjecutiva(ejecutiva);
							ordenTrabajoDAO.updateEjecutiva(oT);
						}
					}
				}
			}
			/*
			 * FIN
			 */

			if (oT.getIdDestino() != null && oT.getIdDestino() > 0) {
				ordenTrabajoDAO.updateDestinoOT(oT);
			}
			if (oT.getServicioTecnico() != null
					&& oT.getServicioTecnico().getId() > 0) {
				ordenTrabajoDAO.updateIdSTecnico(oT);
			}
			if (oT.getSucursal() != null && oT.getSucursal().getId() != null
					&& oT.getSucursal().getId() > 0) {
				ordenTrabajoDAO.updateIdSucursal(oT);
			}

			if (oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_MASTER)
					&& oT.getNumeroCambio() != null && oT.getNumeroCambio() > 0) {
				Empresa empresaFacturar = new Empresa();
				oT.setEmpresaFacturar(empresaFacturar);
				oT.getEmpresaFacturar().setId(
						Constants.UBICACION_NUEVA_DE_LYON_072_PISO_6
								.longValue());
				oT.setMotivoCambio("Creacion de OT de cambio");
				oT.setTipoFacturar("Tienda");
				oT.setCambioAutorizado(true);
				ordenTrabajoDAO.updateOTCambio(oT);
			}

			if (!ordenTrabajoDAO.existeBitacora(
					oT.getEstadoBitacora().longValue()).equals(0)) {
				Long idUbicacion = oT.getSucursal().getId().longValue();

				if (oT.getEstadoBitacora()
						.equals(Constants.BITACORA_EN_SERVICIO_TECNICO_A_LA_ESPERA_DE_SER_RECOGIDO)
						|| oT.getEstadoBitacora()
								.equals(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SUCURSAL)) {
					idUbicacion = oT.getServicioTecnico().getId().longValue();
				} else if (oT
						.getEstadoBitacora()
						.equals(Constants.BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL_20001000)
						|| oT.getEstadoBitacora()
								.equals(Constants.BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL)
						|| oT.getEstadoBitacora()
								.equals(Constants.BITACORA_EN_CLIENTE_A_LA_ESPERA_DE_RECUPERARLO_UNA_SUCURSAL)) {
					idUbicacion = Constants.UBICACION_ID_CLIENTE;
				}
				Bitacora bitacora = new Bitacora();
				bitacora.setEstado(new Estado());
				bitacora.getEstado().setId(oT.getEstadoBitacora());
				bitacora.setIdOT(oT.getId());
				bitacora.setUbicacion(new Ubicacion());
				bitacora.getUbicacion().setId(idUbicacion);
				iniciarBitacora(bitacora);
			}

			FilterAccesorio filterAccesorio = new FilterAccesorio();
			filterAccesorio.setIdProducto(oT.getProducto().getId().longValue());
			filterAccesorio.setVigente(true);
			List<Accesorio> accesorios = this
					.listAccesoriosByFilter(filterAccesorio);
			for (Accesorio acc : accesorios) {
				if (acc.getVigente()) {
					acc.setUbicacion(new Ubicacion());
					acc.getUbicacion().setId(Constants.UBICACION_ID_CLIENTE);
					acc.setIdOT(oT.getId());
					acc.setRequerido(false);
					acc.setRecibidoCCalidad(false);
					acc.setRecibidoCd(false);
					acc.setRecibidoDestino(false);
					accesorioDAO.saveAcceoriosOT(acc);
				}
			}

			if (!oT.getTipo().getCodigo()
					.equals(Constants.TIPO_OT_GARANTIA_BIG_TICKET)) {
				FilterParte filterParte = new FilterParte();
				filterParte.setIdProducto(oT.getProducto().getId().longValue());
				filterParte.setVigente(true);
				List<Parte> partes = parteDAO.listPartesByFilter(filterParte);
				if (partes.size() == 0) {
					Producto producto = productoDAO.getProductoById(filter
							.getIdProducto());
					filterParte.setIdFamilia(producto.getFamilia().getId());
					filterParte.setIdProducto(null);
					partes = parteDAO.listPartesByFilter(filterParte);
				}

				for (Parte parte : partes) {
					parte.setIdOT(oT.getId());
					parte.setObservaciones(null);
					parte.setcCalidad(null);
					parte.setEstado(Constants.PARTE_BUENO);
					parteDAO.saveParteOT(parte);
				}
			}

			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			log.error("acción realizada por usuario : " + usuario.getRut()
					+ ", ubicación : " + sucursal.getId() + " "
					+ sucursal.getNombre() + ", Tipo documento : "
					+ oT.getTipoDocumento() + ", N° documento: "
					+ oT.getIdDocumento());
			throw e;
		}
	}

	private void iniciarBitacora(Bitacora bitacora) throws Exception {
		try {
			List<Bitacora> bitacoras = bitacoraDAO.listBitacorasByIdOT(bitacora
					.getIdOT());
			if (bitacoras.size() > 0) {
				bitacoraDAO.deleteBitacoraByOT(bitacora.getIdOT());
			}
			bitacoraDAO.saveBitacoraEnCrearOT(bitacora);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTEnTransito(FilterOT filter, GridControl gridControl,
			Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTEnTransito(filter,
					gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTEnTransito(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTPendientesAccesorios(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTPendientesEnSucursal(
					filter, gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTPendientesEnSucursal(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public boolean saveParteOT(List<Parte> partes, Usuario usuario,
			Ubicacion ubicacion) throws Exception {
		try {
			Date fecha = utilDAO.getDateTrunc();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			String fechaActual = simpleDateFormat.format(fecha);
			for (Parte parte : partes) {
				if (!parte.getObservacion().equals("")) {
					String obs = ubicacion.getId() + "::" + ubicacion.getTipo()
							+ "::" + fechaActual + "::"
							+ usuario.getNombreCompleto() + ":: "
							+ parte.getObservacion();
					parte.setObservacion(obs);
				}
				parteDAO.updateParteOT(parte);
			}
			return true;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateParteOTConAnteriorObervacion(List<Parte> partes,
			Usuario usuario, Ubicacion ubicacion) throws Exception {
		try {
			Date fecha = utilDAO.getDateTrunc();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			String fechaActual = simpleDateFormat.format(fecha);
			if (partes != null) {
				for (Parte parte : partes) {
					Parte parteActual = parteDAO.getParteOTbyId(parte.getId());
					String obs = "";
					if (parte.getObservacion() != null
							&& !parte.getObservacion().equals("")) {
						if (!parte.getObservacion().equals("")) {
							obs = ubicacion.getId() + "::"
									+ ubicacion.getTipo() + "::" + fechaActual
									+ "::" + usuario.getNombreCompleto()
									+ ":: " + parte.getObservacion();
							parte.setObservacion(obs);
						}
					}
					if (parteActual.getObservaciones() != null
							&& parteActual.getObservaciones().length() > 0) {
						if (!obs.equals("")) {
							parte.setObservacion(obs + '\n'
									+ parteActual.getObservaciones());
						} else {
							parte.setObservacion(parteActual.getObservaciones());
						}
					}
					parteDAO.updateParteOT(parte);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer updateOTRevision(OrdenTrabajo oT) throws Exception {
		try {
			return ordenTrabajoDAO.updateOTRevision(oT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer updateOTBTNOrigen(OrdenTrabajo oT) throws Exception {
		try {
			return ordenTrabajoDAO.updateOTBTNOrigen(oT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void recibirOTSucursal(OrdenTrabajo ot, Ubicacion ubicacion)
			throws Exception {
		try {
			List<Bitacora> bitacoras = bitacoraDAO.listBitacorasByIdOT(ot
					.getId());
			Date fecha = utilDAO.getDate();

			if (bitacoras == null || bitacoras.size() == 0)
				throw new SSTException("La orden de trabajo no tiene bitacora");

			if (bitacoras.size() > 1) {
				Bitacora bitacora = new Bitacora();
				bitacora.setOrdenTrabajo(ot);
				bitacora.setEstado(new Estado());
				bitacora.getEstado()
						.setId(Constants.BITACORA_EN_CLIENTE_DESPUES_ENTREGA_A_SUCURSAL_20001000);
				bitacoraDAO.deleteBitacoraByOTNoEstado(bitacora);
			}

			bitacoras = bitacoraDAO.listBitacorasByIdOT(ot.getId());

			if (bitacoras == null || bitacoras.size() == 0)
				throw new SSTException("La orden de trabajo no tiene bitacora");

			if (bitacoras.size() == 1) {
				Bitacora bitacora = bitacoras.get(0);
				bitacora.setFechaSalida(fecha);
				bitacoraDAO.updateFechaSalida(bitacora);
				Bitacora bitacoraSiguiente = new Bitacora();
				bitacoraSiguiente.setOrdenTrabajo(ot);
				bitacoraSiguiente.setFechaEntrada(fecha);
				bitacoraSiguiente.setEstado(new Estado());
				bitacoraSiguiente
						.getEstado()
						.setId(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_SERVICIO_TECNICO_LOCAL);
				bitacoraSiguiente.setUbicacion(ubicacion);
				bitacoraDAO.save(bitacoraSiguiente);
				ot.setEstado(new Estado());
				ot.getEstado()
						.setId(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE);
				ordenTrabajoDAO.updateEstadoOT(ot);
			} else {
				throw new SSTException(
						"Error con la bitacora, no tiene arreglo");
			}

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo getOTByFilterForEntregaCliente(FilterOT filter)
			throws Exception {
		try {
			filter.setCerradaCliente(false);
			filter.setVigente(true);
			filter.setCerrada(false);
			OrdenTrabajo orden = this.getOTByFilter(filter);
			if (orden
					.getEstado()
					.getId()
					.equals(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_EN_SUCURSAL))
				return ordenTrabajoDAO.getOTById(orden.getId());
			if (orden
					.getEstado()
					.getId()
					.equals(Constants.OT_ESTADO_CON_RECEPCION_ACEPTADA_CON_OBSERVACION_EN_SUCURSAL))
				return ordenTrabajoDAO.getOTById(orden.getId());
			if (orden.getEstado().getId()
					.equals(Constants.OT_ESTADO_RECHAZADA_POR_CLIENTE))
				return ordenTrabajoDAO.getOTById(orden.getId());
			if (orden.getCambioAutorizado()
					&& !orden
							.getEstado()
							.getId()
							.equals(Constants.OT_ESTADO_EN_SUCURSAL_A_LA_ESPERA_DE_SER_ENVIADA)
					&& !orden
							.getEstado()
							.getId()
							.equals(Constants.OT_ESTADO_PENDIENTE_POR_ACCESORIOS_QUE_TIENE_EL_CLIENTE)
					&& !orden
							.getEstado()
							.getId()
							.equals(Constants.OT_ESTADO_EN_SUCURSAL_EN_PREPARACION_CUANDO_SE_ASIGNAN_LOS_TIPOS_DE_FALLAS_ACCESORIOS_REVISION_CLIENTE))
				return ordenTrabajoDAO.getOTById(orden.getId());
			else
				throw new SSTException("La orden de trabajo tiene el estado : "
						+ orden.getEstado().getDescripcion());
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo getOTByFilter(FilterOT filter) throws Exception {
		try {
			List<OrdenTrabajo> ordenes = ordenTrabajoDAO.listByFilter(filter);
			if (ordenes == null || ordenes.size() == 0) {
				filter.setCodigoBarraAccesorio(filter.getCodigoBarra());
				filter.setCodigoBarra(null);
				ordenes = ordenTrabajoDAO.listByFilter(filter);
				if (ordenes == null || ordenes.size() == 0)
					throw new SSTException(
							"No hay ordenes con los parametros ingresados");
			}

			if (ordenes.size() > 1)
				throw new SSTException(
						"Existe mas de una ot que cumple las condiciones");

			return ordenTrabajoDAO.getOTById(ordenes.get(0).getId());

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Ubicacion getUbicacionOT(Long idOT) throws Exception {
		try {
			return ubicacionDAO.getUbicacionOT(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Ubicacion getUbicacionOTAccesorio(Long idOT) throws Exception {
		try {
			Ubicacion ubicacionOTAccesorio = ubicacionDAO
					.getUbicacionOTAccesorios(idOT);
			return ubicacionOTAccesorio;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia getGuiaRecepcion(Long idOT, String guiaAccesorio,
			Ubicacion ubicacion) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setOrdenTrabajo(new OrdenTrabajo());
			guia.getOrdenTrabajo().setId(idOT);
			guia.setDestino(ubicacion);
			if (guiaAccesorio != null
					&& guiaAccesorio.equals(Constants.GUIA_TIPO_ACCESORIO)) {
				guia.setTipoGuia(Constants.GUIA_TIPO_ACCESORIO);
				Ubicacion origen = ubicacionDAO.getUbicacionOTAccesorios(idOT);
				guia.setOrigen(origen);
			} else if (guiaAccesorio != null
					&& guiaAccesorio.equals(Constants.GUIA_TIPO_UNITARIA)) {
				guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
				Ubicacion origen = ubicacionDAO.getUbicacionOT(idOT);
				guia.setOrigen(origen);
			}
			return guiaDAO.getGuiaRecepcion(guia);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia getGuiaAccesorioForIdOT(Long idOT) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
			guia.setTipoGuia(Constants.GUIA_TIPO_ACCESORIO);
			return guiaDAO.getGuiaAccesorioForIdOT(guia);

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Guia> listGuiaRecepcion(Long idOT, Ubicacion ubicacion)
			throws Exception {
		try {
			Guia guia = new Guia();
			guia.setOrdenTrabajo(new OrdenTrabajo());
			guia.getOrdenTrabajo().setId(idOT);
			guia.setDestino(ubicacion);
			return guiaDAO.listGuiaRecepcion(guia);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer getCantidadOtsEjecutiva(Usuario usuario, Long idOT)
			throws Exception {
		try {
			FilterOT filter = new FilterOT();
			filter.setIdOT(idOT);
			filter.setUsuario(usuario);
			filter.setTipoUbicacion(Constants.UBICACION_SERVICIO_TECNICO_LOCAL);
			return ordenTrabajoDAO.getTotalByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer getCantidadOTByFilter(FilterOT filter) throws Exception {
		try {
			return ordenTrabajoDAO.getTotalByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Accesorio> listAccesoriosForGuiaTipo(OrdenTrabajo ordenTrabajo,
			Guia guia, Boolean cliente, Ubicacion ubicacion) throws Exception {
		try {
			// en caso de FF revisar Guia y OT
			if (ubicacion.getId().equals(
					Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
				FilterGuia filterGuia = new FilterGuia();
				filterGuia.setIdOT(ordenTrabajo.getId());
				filterGuia.setIdGuia(guia.getId());
				return accesorioDAO.listAccesoriosFromIdGuia(filterGuia);
			}
			// REVISAR EMISION y RE EMISION DE FF
			if (cliente) {
				return accesorioDAO
						.ListAccesoriosFromClienteByIdOT(ordenTrabajo.getId());
			} else {
				if (guia.getId() != null) {
					FilterGuia filterGuia = new FilterGuia();
					filterGuia.setIdGuia(guia.getId());
					return accesorioDAO.listAccesoriosFromIdGuia(filterGuia);
				} else {
					return accesorioDAO
							.ListAccesoriosFromGuiaAnterior(ordenTrabajo
									.getId());
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Accesorio> listAllAccesoriosFromTipoGuia(Guia guia)
			throws Exception {
		try {
			List<Accesorio> accesorios = accesorioDAO
					.listAllAccesoriosFromTipoGuia(guia);
			return accesorios;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/* End Recibir OT en Sucursal */

	public List<Indicador> listIndicadorSucursal(Ubicacion ubicacion)
			throws Exception {
		try {
			FilterOT filter = new FilterOT();
			filter.setSucursal(ubicacion.getId());
			List<Indicador> indicadores = new ArrayList<Indicador>();
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_ABIERTAS_SUCURSAL, ordenTrabajoDAO
							.getTotalOTAbiertasSucursal(filter)));
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_AUTORIZACION_CAMBIO, ordenTrabajoDAO
							.getTotalOTAutorizadaCambio(filter)));
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_STL_SIN_CONTRATO, ordenTrabajoDAO
							.getTotalOTEnviadasSTLSinContrato(filter)));
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_PENDIENTES_ACCESORIOS,
					ordenTrabajoDAO.getTotalOTPendienteAccesorios(filter)));
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_PENDIENTES_ENTREGA_CLIENTE,
					ordenTrabajoDAO.getTotalOTPendienteEntregaCliente(filter)));
			indicadores.add(new Indicador(
					Constants.INDICADOR_OT_PENDIENTES_GUIAS, ordenTrabajoDAO
							.getTotalOTPendienteGuia(filter)));
			return indicadores;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTIndicadorSucursal(FilterIndicador filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_ABIERTAS_SUCURSAL.longValue())) {
				listRange = this.listOTAbiertasSucursal(new FilterOT(),
						gridControl, ubicacion);
			} else if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_PENDIENTES_ACCESORIOS.longValue())) {
				listRange = this.listOTPendienteAccesorios(new FilterOT(),
						gridControl, ubicacion);
			} else if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_PENDIENTES_GUIAS.longValue())) {
				listRange = this.listOTPendienteGuia(new FilterOT(),
						gridControl, ubicacion);
			} else if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_PENDIENTES_ENTREGA_CLIENTE
							.longValue())) {
				listRange = this.listOTPendienteEntregaCliente(new FilterOT(),
						gridControl, ubicacion);
			} else if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_STL_SIN_CONTRATO.longValue())) {
				listRange = this.listOTEnviadasSTLSinContrato(new FilterOT(),
						gridControl, ubicacion);
			} else if (filter.getIdIndicador().equals(
					Constants.INDICADOR_OT_AUTORIZACION_CAMBIO.longValue())) {
				listRange = this.listOTAutorizadaCambio(new FilterOT(),
						gridControl, ubicacion);
			} else {
				throw new SSTException(
						"Indicador inexistente para las sucursales");
			}

			return listRange;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Bitacora getUltimaBitacora(Long idOT) throws Exception {
		try {
			return bitacoraDAO.getUltimaBitacora(idOT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTAbiertasSucursal(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTAbiertasSucursal(filter,
					gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTAbiertasSucursal(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTPendienteAccesorios(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTPendienteAccesorios(filter,
					gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTPendienteAccesorios(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTPendienteGuia(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTPendienteGuia(filter,
					gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTPendienteGuia(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTPendienteEntregaCliente(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTPendienteEntregaCliente(
					filter, gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTPendienteEntregaCliente(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTEnviadasSTLSinContrato(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTEnviadasSTLSinContrato(
					filter, gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTEnviadasSTLSinContrato(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listOTAutorizadaCambio(FilterOT filter,
			GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setSucursal(ubicacion.getId());
			listRange.setRows(ordenTrabajoDAO.listOTAutorizadaCambio(filter,
					gridControl));
			listRange.setTotal(ordenTrabajoDAO
					.getTotalOTAutorizadaCambio(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public String updateClienteAceptaProducto(List<Accesorio> accesorios,
			OrdenTrabajo ot, Usuario usuario, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterOT filterAux = new FilterOT();
			filterAux.setIdOT(ot.getId());

			ot.setUbicacion(ubicacion);
			OrdenTrabajo ordenAux = getOTByFilterForEntregaCliente(filterAux);
			ot.setTipo(ordenAux.getTipo());
			ot.setEstado(ordenAux.getEstado());
			ot.setCambioAutorizado(ordenAux.getCambioAutorizado());
			for (Accesorio accesorio2 : accesorios) {
				accesorio2.setUbicacion(ubicacion);
				accesorioDAO.updateUbicacionAccesorio(accesorio2);
			}

			// System.out.println("-----> El valor de la bandera viene: "+ot.getBanderaOrigenOT());
			ordenTrabajoDAO.updateOTBTNOrigen(ot);

			if (ot.getCambioAutorizado()) {
				// TODO Se comento la linea siguiente para separar esta llamada (plarrain)
				// a un segundo boton (generar ticket de cambio).
				// this.updateOTCATicketCambio(ot, ubicacion);

				ot.setEstado(estadoDAO
						.getEstadoById(Constants.OT_CERRADA_POR_CLIENTE_POR_PRODUCTO_CAMBIADO));

				Guia guia = new Guia();
				GuiaAccesorios guiaAccesorios = new GuiaAccesorios();

				guia.setOrdenTrabajo(ot);
				guia.setEstado(estadoDAO
						.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
				guia.setOrigen(ubicacion);
				guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
				guia = guiaDAO.getGuiaByGuia(guia);

				if (guia == null) {
					guia = envioRecepcionService.saveGuiaAccesorio(ot);

					Bitacora bitacora = new Bitacora();
					bitacora.setOrdenTrabajo(ot);
					bitacora.setGuia(guia);
					bitacora.setFechaEntrada(utilDAO.getDate());
					bitacora.setUbicacion(ubicacion);
					bitacora.setEstado(estadoDAO
							.getEstadoById(Constants.BITACORA_EN_SUCURSAL_INICIO_DESPUES_ENTREGA_A_CAMION_AL_CD));
					bitacoraDAO.saveBitacoraAccesorios(bitacora);
					bitacoraDAO.updateAsignaBitacoraAGuiaAccesorio(bitacora);
				}

				guiaAccesorios.setGuia(guia);
				asignarGuiaToAccesorios(guia, accesorios);

				ordenTrabajoDAO.updateCambioAutorizado(ot);
				Gestion gestion = new Gestion();
				gestion.setOt(ot);
				gestion.setUsuario(usuario);
				gestion.setFecha(utilDAO.getDate());
				gestion.setGestion("  cierra la orden de trabajo por cliente en la sucursal ");
				gestionesDAO.saveGestion(gestion);
				return "La orden de trabajo se cerró correctamente, debe imprimir la guía de despacho de accesorios (cargo), en 'OTs pendientes por guía";
			} else {
				ot.setEstado(estadoDAO
						.getEstadoById(Constants.OT_CERRADA_POR_CLIENTE_EN_SUCURSAL_POR_PRODUCTO_REPARADO));

				Guia guia = new Guia();
				guia.setOrdenTrabajo(ot);
				Recepcion recepcion = new Recepcion();
				recepcion.setGuia(guia);
				recepcion.setFechaRecepcion(ot.getFechaCierreCliente());
				bitacoraDAO.updateBitacoraFechaEstado(recepcion);
				Bitacora bitacora = new Bitacora();
				bitacora.setOrdenTrabajo(ot);
				bitacora.setFechaEntrada(ot.getFechaCierreCliente());
				bitacoraDAO.saveBitacoraCerrarCliente(bitacora);

				Guia guiaCliente = saveGuiaCliente(ot);
				Bitacora bitacora2 = new Bitacora();
				bitacora2.setGuia(guiaCliente);
				bitacora2.setUbicacion(ot.getUbicacion());
				bitacora2.setOrdenTrabajo(ot);
				bitacoraDAO.updateAsignaBitacoraAGuia(bitacora2);
				ordenTrabajoDAO.updateCambioAutorizado(ot);
				Gestion gestion = new Gestion();
				gestion.setOt(ot);
				gestion.setUsuario(usuario);
				gestion.setFecha(utilDAO.getDate());
				gestion.setGestion("  cierra la orden de trabajo por cliente en la sucursal ");
				gestionesDAO.saveGestion(gestion);
				return "La orden de trabajo se cerró correctamente, debe imprimir la guía de despacho de entrega al cliente, en 'OTs pendientes por guía'";
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Guia saveGuiaCliente(OrdenTrabajo ot) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setOrdenTrabajo(ot);
			guia.setEstado(estadoDAO
					.getEstadoById(Constants.GUIA_ESTADO_POR_EMITIR));
			guia.setDestino(ubicacionDAO.get(Constants.UBICACION_ID_CLIENTE));
			guia.setOrigen(ot.getUbicacion());
			guia.setVigente(true);
			guia.setEntregaCliente(true);
			guia.setTipoGuia(Constants.GUIA_TIPO_UNITARIA);
			guiaDAO.save(guia);
			return guia;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateEnviarTareaEjecutiva(OrdenTrabajo ot, Usuario usuario)
			throws Exception {
		try {
			OrdenTrabajo otAux = ordenTrabajoDAO.getOTById(ot.getId());
			Date fechaActual = utilDAO.getDate();
			Gestion gestion = new Gestion();
			gestion.setOt(ot);
			gestion.setUsuario(usuario);
			gestion.setFecha(fechaActual);
			gestion.setGestion(" ingresa el rechazo de la entrega del producto al cliente debido a: "
					+ ot.getObservacionEntrega());
			Guia guia = new Guia();
			guia.setOrdenTrabajo(ot);
			gestionesDAO.saveGestion(gestion);

			updateOTTareaUrgente(otAux);

			Estado estado = new Estado();
			estado.setId(Constants.OT_ESTADO_RECHAZADA_POR_CLIENTE);
			ot.setEstado(estado);
			ordenTrabajoDAO.updateEstadoOT(ot);

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateEnviarTareaEjecutivaDesdeCD(Recepcion recepcion,
			Ubicacion ubicacion, Usuario usuario, List<Parte> partes,
			List<Accesorio> accesorios) throws Exception {
		try {
			Ubicacion ubicacionOT = ubicacionDAO.getUbicacionOT(recepcion
					.getGuia().getOrdenTrabajo().getId());
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(recepcion
					.getGuia().getOrdenTrabajo().getId());
			this.updateParteOTConAnteriorObervacion(partes, usuario, ubicacion);
			this.updateUbicacionAccesoriosRecibidos(ordenTrabajo, ubicacion,
					recepcion.getOrigen(), accesorios);

			updateOTTareaUrgente(ordenTrabajo);

			Guia guiaRecibida = recepcion.getGuia();
			if (guiaRecibida == null
					&& ubicacionOT.getTipo().equals(
							Constants.UBICACION_SERVICIO_TECNICO)) {
				guiaRecibida = new Guia();
				guiaRecibida.setOrigen(ubicacionOT);
				guiaRecibida.setDestino(ubicacion);
			}
			guiaRecibida
					.setEstado(estadoDAO
							.getEstadoById(Constants.GUIA_ESTADO_EMITIDA_RECIBIDA_NO_REEMISION));
			guiaRecibida.setVigente(true);
			guiaDAO.updateEstado(guiaRecibida);

			Gestion gestion = new Gestion();
			gestion.setOt(recepcion.getGuia().getOrdenTrabajo());
			gestion.setUsuario(usuario);
			gestion.setGestion("  rechaza la recepcion de la OT en "
					+ ubicacion.getId() + " " + ubicacion.getNombre());
			gestionesDAO.saveGestion(gestion);

			if (ubicacion.getTipo().equals(
					Constants.UBICACION_CENTRO_DISTRIBUCION)) {
				recepcion
						.setEstado(estadoDAO
								.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_CENTRO_DE_DISTRIBUCION));
			}
			if (ubicacion.getTipo().equals(Constants.UBICACION_SUCURSAL)) {
				recepcion
						.setEstado(estadoDAO
								.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_SUCURSAL));
			} else {
				recepcion
						.setEstado(estadoDAO
								.getEstadoById(Constants.OT_ESTADO_CON_RECEPCION_RECHAZADA_EN_BODEGA));
			}
			recepcion.setUsuario(usuario);
			recepcionDAO.saveRecepcionOT(recepcion);

			Bitacora bitacora = bitacoraDAO.getByIdGuia(guiaRecibida.getId());
			envioRecepcionService.recibirBitacora(recepcion.getOrigen(),
					ubicacion, bitacora, ordenTrabajo, recepcion);
			ordenTrabajoDAO.updateOTRecepcion(ordenTrabajo);

			Guia guiaNueva = new Guia();
			guiaNueva.setOrdenTrabajo(ordenTrabajo);
			guiaNueva.setOrigen(ubicacion);
			guiaNueva.setEntregaCliente(false);
			guiaNueva.setVigente(true);
			guiaNueva.setEstado(new Estado());
			guiaNueva.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guiaDAO.save(guiaNueva);

			Bitacora bitacoraNueva = new Bitacora();
			bitacoraNueva.setGuia(guiaNueva);
			bitacoraNueva.setOrdenTrabajo(ordenTrabajo);
			bitacoraNueva.setUbicacion(ubicacion);
			bitacoraDAO.updateAsignaBitacoraAGuia(bitacoraNueva);

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo updateOTTareaUrgente(OrdenTrabajo ot) throws Exception {
		try {
			ot = ordenTrabajoDAO.getOTById(ot.getId());
			ot.setTareaUrgente(true);
			ordenTrabajoDAO.updateOTTareaUrgente(ot);
			if (ot.getEjecutiva().getId() == null) {
				throw new SSTException(
						"La orden de trabajo no tiene una ejecutiva de marca asignada, contactese con la ejecutiva supervisora");
			}
			return ot;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void asignarDestinoAUbicacion(Ubicacion origen, Ubicacion destino)
			throws Exception {
		try {
			origen = ubicacionDAO.get(origen.getId());
			destino = ubicacionDAO.get(destino.getId());

			if (origen == null)
				throw new SSTException("La ubicacion de origen no existe");
			if (destino == null)
				throw new SSTException("La ubicacion de destino no existe");

			FilterDestino filter = new FilterDestino();
			filter.setIdDestino(destino.getId());
			filter.setIdOrigen(origen.getId());
			Destino destinoOrigen = destinoDAO.getByFilter(filter);
			if (destinoOrigen == null) {
				destinoOrigen = new Destino();
				destinoOrigen.setDestino(destino);
				destinoOrigen.setOrigen(origen);
				destinoOrigen.setVigente(true);
				destinoDAO.save(destinoOrigen);
			} else {
				if (!destinoOrigen.getVigente()) {
					destinoOrigen.setVigente(true);
					destinoDAO.updateVigencia(destinoOrigen);
				}
			}

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialCompleta(Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = new ReglaComercial();
			ReglaComercial reglaPorValor = null;
			ReglaComercial reglaPorFallaReiterada = null;
			ReglaComercial reglaPorFallaFabricacion = null;
			ReglaComercial reglaPorAutorizacionProveedor = null;
			ReglaComercial reglaPorCertificacionFalla = null;
			reglaPorValor = this.getReglaComercialVigentePorValor(producto, ubicacion);
			reglaPorFallaReiterada = this.getReglaComercialVigentePorFallaReiterada(producto, ubicacion);
			reglaPorAutorizacionProveedor = this.getReglaComercialVigentePorAutorizacionProveedor(producto, ubicacion);
			reglaPorCertificacionFalla = this.getReglaComercialVigentePorCertificacionFalla(producto, ubicacion);
			
			try {
				reglaPorFallaFabricacion = this.getReglaComercialVigentePoFallaFabricacion(producto, ubicacion);
			} catch (SSTException ignore) {
			}
			
			//TODO Analizando regla comercial (plarrain)
			/*System.out.println("-----> reglaPorValor: "+reglaPorValor);
			System.out.println("-----> reglaPorFallaReiterada: "+reglaPorFallaReiterada);
			System.out.println("-----> reglaPorAutorizacionProveedor: "+reglaPorAutorizacionProveedor);
			System.out.println("-----> reglaPorCertificacionFalla: "+reglaPorCertificacionFalla);
			System.out.println("-----> reglaPorFallaFabricacion: "+reglaPorFallaFabricacion);*/
			
			if (reglaPorValor != null) {
				reglaComercial.setCambioAutomatico(new ReglaCambioAutomatico());
				reglaComercial.getCambioAutomatico().setPrecioLimite(reglaPorValor.getCambioAutomatico().getPrecioLimite());
			}

			if (reglaPorFallaReiterada != null) {
				if (reglaComercial.getCambioAutomatico() == null)
					reglaComercial.setCambioAutomatico(new ReglaCambioAutomatico());
					reglaComercial.getCambioAutomatico().setDiasTope(reglaPorFallaReiterada.getCambioAutomatico().getDiasTope());
					reglaComercial.getCambioAutomatico().setNumeroFallas(reglaPorFallaReiterada.getCambioAutomatico().getNumeroFallas());
			}
			
			if (reglaPorFallaFabricacion != null) {
				reglaComercial.setFallaFabricacion(new ReglaFallaFabricacion());
				reglaComercial.getFallaFabricacion().setDiasTope(reglaPorFallaFabricacion.getFallaFabricacion().getDiasTope());
				reglaComercial.getFallaFabricacion().setPrecioLimite(reglaPorFallaFabricacion.getFallaFabricacion().getPrecioLimite());
			}
			
			if (reglaPorAutorizacionProveedor != null) {
				reglaComercial.setReglaCambioProoveedor(new ReglaCambioProoveedor());
				reglaComercial.getReglaCambioProoveedor().setAutorizadoProveedor(reglaPorAutorizacionProveedor.getReglaCambioProoveedor().getAutorizadoProveedor());
				reglaComercial.getReglaCambioProoveedor().setNotaProoveedor(reglaPorAutorizacionProveedor.getReglaCambioProoveedor().getNotaProoveedor());
			}
			
			if (reglaPorCertificacionFalla != null) {
				reglaComercial.setCertificacionFalla(new ReglaCertificacionFalla());
				reglaComercial.getCertificacionFalla().setInicio(reglaPorCertificacionFalla.getCertificacionFalla().getInicio());
				reglaComercial.getCertificacionFalla().setTermino(reglaPorCertificacionFalla.getCertificacionFalla().getTermino());
			}
			
			return reglaComercial;
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialVigentePorAutorizacionProveedor(
			Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			return this.getReglaComercialVigente(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO, producto, ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialVigentePorValor(Producto producto,
			Ubicacion ubicacion) throws Exception {
		try {
			return this.getReglaComercialVigente(Constants.CAMBIO_AUTOMATICO_VALOR, producto, ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialVigentePorFallaReiterada(
			Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			return this.getReglaComercialVigente(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA, producto,	ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialVigentePoFallaFabricacion(Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			Familia familia = null;
			FamiliaExcluida familiaExcluida = null;

			if (producto != null) {
				familia = familiaDAO.getByIdProducto(producto.getId());
			}

			if (familia != null) {
				familiaExcluida = familiaDAO.getFamiliaExcluidaByIdFamilia(familia.getId());
			}

			if (familiaExcluida != null) {
				return null;
			}
			
			return this.getReglaComercialVigente(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION, producto, ubicacion);
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialVigentePorCertificacionFalla(Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			Familia familia = null;
			FamiliaExcluida familiaExcluida = null;

			if (producto != null) {
				familia = familiaDAO.getByIdProducto(producto.getId());
			}

			if (familia != null) {
				familiaExcluida = familiaDAO.getFamiliaExcluidaByIdFamilia(familia.getId());
			}

			if (familiaExcluida != null) {
				return null;
			}
			
			return this.getReglaComercialVigente(Constants.CAMBIO_CERTIFICACION_FALLA, producto, ubicacion);
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigente(String tipoAutorizacion,
			Producto producto, Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = null;

			if (reglaComercial == null && ubicacion != null)
				reglaComercial = this.getReglaComercialVigentePorTienda(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null && ubicacion != null)
				reglaComercial = this.getReglaComercialVigentePorZona(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null && producto != null)
				reglaComercial = this.getReglaComercialVigentePorProducto(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null && producto != null)
				reglaComercial = this.getReglaComercialVigentePorFamilia(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null && producto != null)
				reglaComercial = this.getReglaComercialVigentePorLinea(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null && producto != null)
				reglaComercial = this.getReglaComercialVigenteCertificacionFalla(tipoAutorizacion, producto, ubicacion);
			if (reglaComercial == null)
				reglaComercial = this.getReglaComercialVigenteGeneral(tipoAutorizacion, producto, ubicacion);

			return reglaComercial;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigenteCertificacionFalla(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigenteGeneral(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigentePorTienda(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			filterRegla.setIdTienda(ubicacion.getId());
			return reglaComercialDAO.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigentePorZona(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			Zona zona = zonaDAO.getByIdUbicacion(ubicacion.getId());
			if (zona == null) {
				return null;
			}
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			filterRegla.setIdZona(zona.getId());
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigentePorProducto(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			filterRegla.setIdProducto(producto.getId().longValue());
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigentePorFamilia(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			Familia familia = familiaDAO.getByIdProducto(producto.getId());
			if (familia == null) {
				return null;
			}
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			filterRegla.setIdFamilia(familia.getId());
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	private ReglaComercial getReglaComercialVigentePorLinea(
			String tipoAutorizacion, Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			FilterRegla filterRegla = new FilterRegla();
			Familia familia = familiaDAO.getByIdProducto(producto.getId());
			if (familia == null) {
				return null;
			}
			Linea linea = lineaDAO.getByIdFamilia(familia.getId());
			if (linea == null) {
				return null;
			}
			filterRegla.setIdTipoAutorizacion(tipoAutorizacion);
			filterRegla.setIdLinea(linea.getCodigo());
			return reglaComercialDAO
					.getReglaComercialVigenteByFilter(filterRegla);
		} catch (Exception e) {
			throw e;
		}
	}

	public Usuario autorizacionTipo(FilterTipoCambio filter, String ip,
			Ubicacion ubicacion, Date hoy) throws Exception {
		try {
			filter.setIp(ip);
			filter.setUbicacion(ubicacion);
			filter.setFechaActual(hoy);
			//login productivo
			filter.setUsuario(loginService.login(filter.getUsuario(),filter.getIp()));
			//login Falso
			//System.out.println("Inicia Login Simulado");
			//filter.setUsuario(loginService.fullLogin(filter.getUsuario(),filter.getUbicacion(),filter.getIp()));
			List<Rol> roles = administracionService.listRolesByUsuario(filter
					.getUsuario());

			Boolean isJefeTienda = false;
			for (Rol rol : roles) {
				if (rol.getId().equals(
						(Constants.ROL_JEFE_DE_TIENDA).longValue())) {
					isJefeTienda = true;
				}
			}

			// TODO Se debe descomentar las siguientes lineas (plarrain)
			if(!isJefeTienda){
				throw new SSTException("El usuario no tiene permisos para autorizar el cambio"
			); }
			 

			filter.setDocumento(documentoDAO.getDocumentoByIdAndTipo(filter
					.getDocumento()));
			List<Producto> productos = productoDAO
					.listProductoByTipoDocumentoAndIdDocumento(filter
							.getDocumento());
			for (Producto prod : productos) {
				if (prod.getId().equals(filter.getProducto().getId())) {
					filter.setProducto(prod);
				}
			}

			filter.setReglaComercial(getReglaComercialCompleta(
					filter.getProducto(), filter.getUbicacion()));

			if (filter.getMotivo().equals(
					Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)) {
				if (validarFallaFabricacionJT(filter)) {
					return filter.getUsuario();
				}
			} else if (filter.getMotivo().equals(
					Constants.CAMBIO_AUTOMATICO_VALOR)) {
				if (validarCambioPorValorJT(filter)) {
					return filter.getUsuario();
				}
			} else {
				if (validarFallaReiteradaJT(filter)) {
					return filter.getUsuario();
				}
			}
			return filter.getUsuario();
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Boolean validarFallaFabricacionJT(FilterTipoCambio filter)
			throws Exception {
		try {
			if (filter.getReglaComercial().getFallaFabricacion() != null && filter.getReglaComercial().getFallaFabricacion().getPrecioLimite() != null) {
				Calendar fechaDiasFF = Calendar.getInstance();
				fechaDiasFF.setTime(filter.getDocumento().getFechaEmision());
				fechaDiasFF.add(Calendar.DAY_OF_YEAR, filter.getReglaComercial().getFallaFabricacion().getDiasTope());
				Calendar fechaActual = Calendar.getInstance();
				fechaActual.setTime(filter.getFechaActual());
				if (fechaDiasFF.before(fechaActual) || filter.getProducto().getPrecioVenta() > filter.getReglaComercial().getFallaFabricacion().getPrecioLimite()) {
					return true;
				} else {
					throw new SSTException(
							"No es posible realizar un cambio por Falla de Fabricacion");
				}
			} else {
				throw new SSTException(
						"El producto no tiene regla comercial para falla de fabricacion");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Boolean validarCambioPorValorJT(FilterTipoCambio filter)
			throws Exception {
		try {
			//System.out.println("-----> validarCambioPorValorJT: "+filter.getProducto().getPrecioVenta()+" > "+filter.getReglaComercial().getCambioAutomatico().getPrecioLimite());
			if (filter.getProducto().getPrecioVenta() < filter.getReglaComercial().getCambioAutomatico().getPrecioLimite()) {
				return true;
			} else {
				throw new SSTException(
						"El precio del producto es menor que el precio limite para realizar un cambio por valor");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorValor(FilterTipoCambio filter, Ubicacion ubicacion)
			throws Exception {
		try {
			return isCambioPorValor(filter.getProducto(), ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Boolean isCambioPorValor(Producto producto, Ubicacion ubicacion)
			throws Exception {
		try {
			ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
					ubicacion);

			if (reglaComercial == null || reglaComercial.getCambioAutomatico() == null)
				return false;

			return isCambioPorValor(producto, reglaComercial);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorValor(Producto producto,
			ReglaComercial reglaComercial) throws Exception {
		try {
			if (producto.getPrecioVenta() > reglaComercial
					.getCambioAutomatico().getPrecioLimite())
				throw new SSTException(
						"Precio del producto es mayor al precio limite de $"
								+ reglaComercial.getCambioAutomatico()
										.getPrecioLimite());
			return true;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorAutorizacionProveedor(FilterTipoCambio filter,
			Ubicacion ubicacion) throws Exception {
		try {
			return isCambioPorAutorizacionProveedor(filter.getProducto(),
					ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorAutorizacionProveedor(Producto producto,
			Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
					ubicacion);

			if (reglaComercial == null
					|| reglaComercial.getReglaCambioProoveedor() == null)
				return false;

			return isCambioPorAutorizacionProveedor(producto, reglaComercial);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	// TODO 24
	public Boolean isCambioPor24h(Producto producto, Ubicacion ubicacion)
			throws Exception {
		
		//TODO Se modifico comparacion de fechas (plarrain)
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 

		Date fechaCreacion = sdf.parse(dateParseString(producto.getFechaEntrega()), new ParsePosition(0));
		Date fechaActual = sdf.parse(dateParseString(new Date()), new ParsePosition(0));
		int dias=(int) ((fechaCreacion.getTime()-fechaActual.getTime())/86400000);
		//System.out.println("-----> fechaCreacion v/s fechaActual: "+fechaCreacion +" v/s "+ fechaActual);
		//System.out.println(fechaCreacion.compareTo(fechaActual));
		if (dias>=0){//fechaCreacion.before(fechaActual) ){
			//System.out.println("-----> Cambio Menor de 24 horas.");
			return true;
		}
		/*if (fechaCreacion.compareTo(fechaActual) > 0){//fechaCreacion.before(fechaActual) ){
			//System.out.println("-----> Cambio Menor de 24 horas.");
			return true;
		}*/
		return false;
		
		/*Date fechaCreacion = producto.getFechaEntrega();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaCreacion);
		int diaTope = cal.get(Calendar.DATE) + 2;
		Calendar fechaActual = Calendar.getInstance();
		int diaActual = fechaActual.get(Calendar.DATE);
		
		System.out.println("-----> diaActual < diaTope: "+diaActual +" < "+ diaTope);
		if (diaActual < diaTope) {
			System.out.println("-----> Cambio Menor de 24 horas.");
			return true;
		}
		return false;*/
	}
	
	public String dateParseString(Date fechaConvertir) {

		DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm"); //SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String convertido = fechaHora.format(fechaConvertir);
		//System.out.println("-----> La fecha convertida a String va: "+convertido);
		return convertido;
	}

	public Boolean isCambioPorAutorizacionProveedor(Producto producto,
			ReglaComercial reglaComercial) throws Exception {
		try {
			return reglaComercial.getReglaCambioProoveedor()
					.getAutorizadoProveedor();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Boolean validarFallaReiteradaJT(FilterTipoCambio filter)
			throws Exception {
		try {
			FilterOT filterOT = new FilterOT();
			filterOT.setNumeroSerie(filter.getNumeroSerie());
			filterOT.setContratoEmitido(true);
			List<OrdenTrabajo> oTs = ordenTrabajoDAO
					.listOTbyNumeroSerie(filterOT);

			// OrdenTrabajo ot = oTs.get(0);
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.setTime(filter.getFechaActual());

			Calendar fechaFallaReiterada = Calendar.getInstance();
			fechaFallaReiterada
					.setTime(filter.getDocumento().getFechaEmision());
			// fechaFallaReiterada.add(Calendar.DAY_OF_YEAR,filter.getReglaComercial().getCambioAutomatico().getDiasTope());

			if ((oTs.size() + 1) >= filter.getReglaComercial()
					.getCambioAutomatico().getNumeroFallas()) {
				if (fechaFallaReiterada.before(fechaActual)) {
					return true;
				} else {
					throw new SSTException(
							"La fecha es menor a la fecha limite");
				}
			} else {
				throw new SSTException("El numero de fallas es menor al limite");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Guia> listGuiaDocumentoProductoByDocumento(Documento documento)
			throws Exception {
		try {
			return guiaDAO.listGuiaDocumentoProductoByDocumento(documento);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean getCrearNotaCredito(Documento documento, Date hoy)
			throws Exception {
		try {
			documento = documentoDAO.getDocumentoByIdAndTipo(documento);

			if (documento == null) {
				throw new SSTException(
						"El documento asociado a esta Orden de trabajo no existe en la Base de Datos");
			}

			Calendar fechaActual = Calendar.getInstance();
			fechaActual.setTime(hoy);

			Calendar fechaLimite = Calendar.getInstance();
			fechaLimite.setTime(documento.getFechaEmision());

			fechaLimite.add(Calendar.DAY_OF_YEAR,
					notaCreidtoDAO.getDiasNotaCredito());

			if (fechaLimite.after(fechaActual)) {
				return true;
			} else {
				return false;
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Integer updateTicketCambio(OrdenTrabajo oT) throws Exception {
		try {
			return ordenTrabajoDAO.updateTicketCambio(oT);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean validarFallaReiterada(OrdenTrabajo oT, Ubicacion sucursal, Date hoy) throws Exception {
		try {
			if ((oT.getTipoCambio() != null && oT.getTipoCambio().getCodigo().equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) || (oT.getTipoCambioJT() != null && oT.getTipoCambioJT()	.getCodigo().equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA))) {
				return false;
			}
			oT.setProducto(productoDAO.getProductoById(oT.getProducto().getId()));

			ReglaComercial regla = getReglaComercialCompleta(oT.getProducto(), sucursal);

			if (regla == null || regla.getCambioAutomatico() == null || regla.getCambioAutomatico().getDiasTope() == null) {
				return false;
			}

			FilterOT filter = new FilterOT();
			filter.setNumeroSerie(oT.getNumeroSerie());
			filter.setOrderBy("d_fecha_cierre_cliente");
			filter.setIdProducto(oT.getProducto().getId().longValue());
			filter.setSortOrder("asc");
			filter.setIdDocumento(oT.getIdDocumento().longValue());
			filter.setCerradaCliente(true);
			filter.setImei(oT.getImei()); //TODO Esto estaba comentado, se descomenta para realizar pruebas. (plarrain)
			filter.setCalificaFR(true);

			List<OrdenTrabajo> oTs = ordenTrabajoDAO.listByFilter(filter);
			
			/*System.out.println("-----> oTs.isEmpty(): "+oTs.isEmpty());
			System.out.println("-----> oTs.size() + 1: "+oTs.size() + 1);
			System.out.println("-----> regla.getCambioAutomatico().getNumeroFallas(): "+regla.getCambioAutomatico().getNumeroFallas());*/
			
			if (oTs.isEmpty() || (oTs.size() + 1) < regla.getCambioAutomatico().getNumeroFallas()) {
				return false;
			} else {
				OrdenTrabajo ultimaOT = oTs.get(0);
				Calendar fechaLimite = Calendar.getInstance();
				if (ultimaOT.getFechaCierreCliente() == null) {
					return false;
				}
				fechaLimite.setTime(ultimaOT.getFechaCierreCliente());
				fechaLimite.add(Calendar.DAY_OF_YEAR, regla
						.getCambioAutomatico().getDiasTope());
				Calendar fechaActual = Calendar.getInstance();
				fechaActual.setTime(hoy);
				
				/*System.out.println("-----> fechaLimite: "+fechaLimite);
				System.out.println("-----> fechaActual: "+fechaActual);*/
				
				if (fechaLimite.after(fechaActual)) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorFallaFabricacion(Producto producto,
			Documento documento, Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
					ubicacion);

			if (reglaComercial.getCertificacionFalla() == null) {
				return false;
			}
			
			/*Date fechaActual = utilDAO.getDate();
			Calendar fechaLimite = Calendar.getInstance();
			fechaLimite.setTime(documento.getFechaEmision());
			fechaLimite.add(Calendar.DAY_OF_YEAR, reglaComercial
					.getFallaFabricacion().getDiasTope() + 1);

			if (fechaActual.before(fechaLimite.getTime())) {
				return true;
			} else {
				return false;
			}*/
			
			Date fechaActual = new Date();
			// SimpleDateFormat formateador = new
			// SimpleDateFormat("dd/MM/yyyy");
			// Date fechaEmision = formateador.parse(fecha);

			long MILISEGUNDOS_EN_UN_DIA = 1000 * 60 * 60 * 24;
			long dias = (fechaActual.getTime() - documento.getFechaEmision()
					.getTime()) / MILISEGUNDOS_EN_UN_DIA;
			
			/*System.out.println(fechaActual.getTime() + " - " + documento.getFechaEmision()
					.getTime() + "/" + MILISEGUNDOS_EN_UN_DIA);
			System.out.println("dias: " + dias);
			System.out.println("reglaComercial.getCertificacionFalla().getInicio(): " + reglaComercial.getCertificacionFalla().getInicio());
			System.out.println("reglaComercial.getCertificacionFalla().getTermino(): " + reglaComercial.getCertificacionFalla().getTermino());*/
			
			if (dias >= reglaComercial.getCertificacionFalla().getInicio()
					&& dias <= reglaComercial.getCertificacionFalla()
							.getTermino()) {
				return true;
			} else {
				return false;
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isCambioPorCertificacionFalla(Producto producto,
			Documento documento, Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
					ubicacion);
			if (reglaComercial.getCertificacionFalla() == null) {
				return false;
			}
			// Boolean valid = false;
			Date fechaActual = new Date();
			// SimpleDateFormat formateador = new
			// SimpleDateFormat("dd/MM/yyyy");
			// Date fechaEmision = formateador.parse(fecha);

			long MILISEGUNDOS_EN_UN_DIA = 1000 * 60 * 60 * 24;
			long dias = (fechaActual.getTime() - documento.getFechaEmision()
					.getTime()) / MILISEGUNDOS_EN_UN_DIA;
			if (dias >= reglaComercial.getCertificacionFalla().getInicio()
					&& dias <= reglaComercial.getCertificacionFalla()
							.getTermino()) {
				return true;
			} else {
				return false;
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	// public Boolean isCambioPorFallaReiterada(Producto producto, Documento
	// documento, Ubicacion ubicacion) throws Exception{
	// try {
	// ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
	// ubicacion);
	//
	// if (reglaComercial == null || (reglaComercial.getCambioAutomatico() ==
	// null || reglaComercial.getCambioAutomatico().getDiasTope() == null ||
	// reglaComercial.getCambioAutomatico().getNumeroFallas() == null ))
	// return false;
	//
	// Date fechaActual = utilDAO.getDate();
	// Calendar fechaLimite = Calendar.getInstance();
	// fechaLimite.setTime(documento.getFechaEmision());
	// fechaLimite.add(Calendar.DAY_OF_YEAR,
	// reglaComercial.getFallaFabricacion().getDiasTope());
	//
	// if(fechaActual.before(fechaLimite.getTime())) {
	// return true;
	// }
	// else{
	// return false;
	// }
	// } catch (SSTException e){
	// throw e;
	// } catch (Exception e) {
	// log.error(e,e);
	// throw e;
	// }
	// }

	public Boolean isCambioAutomaticoPorFallaFabricacion(Producto producto,
			Ubicacion ubicacion) throws Exception {
		try {
			ReglaComercial reglaComercial = getReglaComercialCompleta(producto,
					ubicacion);

			if (reglaComercial == null
					|| reglaComercial.getFallaFabricacion() == null)
				return false;

			if (isFamiliaExcluidaFallaFabricacionByProducto(producto)) {
				return false;
			} else {
				if (producto.getPrecioVenta() <= reglaComercial
						.getFallaFabricacion().getPrecioLimite()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean validarFallaFabricacion(OrdenTrabajo oT, Ubicacion sucursal,
			Date hoy) throws Exception {
		try {
			oT.setProducto(productoDAO
					.getProductoById(oT.getProducto().getId()));
			ReglaComercial regla = getReglaComercialCompleta(oT.getProducto(),
					sucursal);
			if (isFamiliaExcluidaFallaFabricacionByProducto(oT.getProducto()))
				throw new SSTException(
						"El producto no puede enviarse a fallas de fabricación porque la familia no califica como falla de fabricación");

			if (regla == null || regla.getFallaFabricacion() == null) {
				return false;
			}

			FilterTipoCambio filterTICA = new FilterTipoCambio();

			Documento documento = new Documento();
			documento.setId(oT.getIdDocumento());
			documento.setTipo(oT.getTipoDocumento());
			documento = documentoDAO.getDocumentoByIdAndTipo(documento);
			filterTICA.setDocumento(documento);
			filterTICA.setProducto(oT.getProducto());

			Calendar fechaLimite = Calendar.getInstance();
			fechaLimite.setTime(documento.getFechaEmision());
			fechaLimite.add(Calendar.DAY_OF_YEAR, regla.getFallaFabricacion()
					.getDiasTope());

			Calendar fechaActual = Calendar.getInstance();
			fechaActual.setTime(hoy);

			if (fechaLimite.after(fechaActual) || fechaLimite.getTime().equals(fechaActual.getTime())) {
				List<Producto> productos = productoDAO
						.listProductoByTipoDocumentoAndIdDocumento(documento);
				for (Producto prod : productos) {
					if (prod.getId().equals(oT.getProducto().getId())) {
						if (prod.getPrecioVenta() <= regla
								.getFallaFabricacion().getPrecioLimite()) {
							if (isFamiliaExcluidaFallaFabricacionByProducto(prod)) {
								throw new SSTException(
										"El producto no puede enviarse a fallas de fabricación porque la familia no califica como falla de fabricación");
							}
							return true;
						} else {
							throw new SSTException(
									"Precio del producto es mayor al precio limite de $"
											+ regla.getFallaFabricacion()
													.getPrecioLimite());
						}
					}
				}
			} else {
				throw new SSTException(
						"Está fuera del plazo de "
								+ regla.getFallaFabricacion().getDiasTope()
								+ " días para cambiar un producto por falla de fabricación");
			}
			return false;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isFamiliaExcluidaNumeroSerieByProducto(Producto producto)
			throws Exception {
		try {
			Familia familia = familiaDAO.getByIdProducto(producto.getId());
			if (familia == null)
				return false;
			return !(familiaDAO.getFamiliaExcluidaSerieByIdFamilia(familia
					.getId()) == null);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Boolean isFamiliaExcluidaFallaFabricacionByProducto(Producto producto)
			throws Exception {
		try {
			Familia familia = familiaDAO.getByIdProducto(producto.getId());
			if (familia == null)
				return false;
			return !(familiaDAO.getFamiliaExcluidaByIdFamilia(familia.getId()) == null);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public OrdenTrabajo cerrarOTYCrearOTFallaFabricacion(OrdenTrabajo oT,
			Ubicacion sucursal, Usuario usuario, Date hoy) throws Exception {
		try {
			OrdenTrabajo oTOriginal = oT;
			oT = ordenTrabajoDAO.getOTById(oT.getId());
			if (!this.validarFallaReiterada(oT, sucursal, hoy)) {
				throw new SSTException(
						"No se puede volver a crear una OT, ya que no califica para falla reiterada");
			}
			ordenTrabajoDAO.updateCerrarOT(oT);
			oT.setId(null);

			oT.setLogistico(getLogisticoById(usuario.getId()));
			oT = ordenTrabajoService
					.saveOTFallaReiterada(oT, usuario, sucursal);
			List<TipoFallas> fallasOTOriginal = tipoFallasDAO
					.ListTipoFallasOTByOT(oTOriginal);

			/* Guardar Fallas */
			List<Long> idTipoFallas = new ArrayList<Long>();
			for (TipoFallas tipoFallas : fallasOTOriginal) {
				idTipoFallas.add(tipoFallas.getId().longValue());
			}
			FilterOT filterOT = new FilterOT();
			filterOT.setDescripcionFalla(oT.getDescripcionFalla());
			filterOT.setIdOT(oT.getId());
			saveTipoFallas(oT.getId(), idTipoFallas, filterOT);

			/* copiar Accesorios */
			List<Accesorio> accesorios = accesorioDAO.listAccesoriosByOT(oT
					.getId());
			List<Accesorio> accesoriosOTOriginal = accesorioDAO
					.listAccesoriosByOT(oTOriginal.getId());

			for (int i = 0; i < accesorios.size(); i++) {
				accesorios.get(i).setUbicacion(
						accesoriosOTOriginal.get(i).getUbicacion());
				accesorios.get(i).setRequerido(true);
				accesorios.get(i).setRecibidoCCalidad(
						accesoriosOTOriginal.get(i).getRecibidoCCalidad());
				accesorios.get(i).setRecibidoCd(
						accesoriosOTOriginal.get(i).getRecibidoCd());
				accesorios.get(i).setRecibidoDestino(
						accesoriosOTOriginal.get(i).getRecibidoDestino());
				accesorios.get(i).setCodigoBarra(null);

				updateUbicacionAccesorio(accesorios.get(i));
			}

			/* copiar Partes */
			List<Parte> partes = parteDAO.listPartesOTByOT(oT.getId());
			List<Parte> partesOTOriginal = parteDAO.listPartesOTByOT(oTOriginal
					.getId());
			for (int i = 0; i < partes.size(); i++) {
				partes.get(i)
						.setcCalidad(partesOTOriginal.get(i).getcCalidad());
				partes.get(i).setEstado(partesOTOriginal.get(i).getEstado());
				partes.get(i).setObservaciones(
						partesOTOriginal.get(i).getObservaciones());
				partes.get(i).setId(partesOTOriginal.get(i).getId());
				partes.get(i).setIdOT(partesOTOriginal.get(i).getIdOT());
			}
			for (Parte parte : partes) {
				parteDAO.updateParteOT(parte);
			}

			oT.setCodigoBarra(null);
			/* copiar revision */
			updateOTRevision(oT);

			/* Cerrar OT */
			oTOriginal.getEstado().setId(
					Constants.OT_ESTADO_RECHAZADA_POR_CLIENTE);
			oTOriginal.setCerrada(true);
			oTOriginal.setCerradaCliente(true);
			oTOriginal
					.setMotivoCierre("Producto rechazado por cliente en la entrega");
			ordenTrabajoDAO.updateCerrarOT(oTOriginal);
			Bitacora bitacora = bitacoraDAO.getUltimaBitacoraAbierta(oTOriginal
					.getId());
			bitacora.setFechaSalida(utilDAO.getDate());
			bitacoraDAO.updateFechaSalida(bitacora);

			return oT;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listGuiasPendientesAgrupadasSucursal(Ubicacion ubicacion,
			GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			FilterGuiasPendientes filter = new FilterGuiasPendientes();
			filter.setIdUbicacion(ubicacion.getId());
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setIdOrigen(ubicacion.getId());
			List<GuiaPendienteAgrupada> guiasPendientesAgrupadas = guiaAgrupadaDAO
					.listGuiasAgrupadasPendientes(filter, gridControl);
			for (GuiaPendienteAgrupada gpa : guiasPendientesAgrupadas) {
				if (gpa.getTipo().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION)) {
					gpa.getTipo().setGlosa("Cambio por Falla de Fabricación");
				} else if (gpa.getTipo().getCodigo()
						.equals(Constants.CAMBIO_AUTOMATICO_FALLA_REITERADA)) {
					gpa.getTipo().setGlosa(
							"Cambio automático proveedor producto");
				}
			}
			listRange.setRows(guiasPendientesAgrupadas);
			listRange.setTotal(guiaAgrupadaDAO
					.getTotalGuiasAgrupadasPendientes(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void processGuiasPendientesAgrupadas(Ubicacion ubicacion,
			Usuario usuario) throws Exception {
		try {
			FilterGuiasPendientes filterGuiasPendientes = new FilterGuiasPendientes();
			filterGuiasPendientes.setIdUbicacion(ubicacion.getId());
			Integer bitacorasDesvinculadas = bitacoraDAO
					.updateBitacoraUnlinkGuia(filterGuiasPendientes);
			log.info("Bitacoras descinculadas : " + bitacorasDesvinculadas);

			desvincularGuiaFromAccesorio(filterGuiasPendientes);
			Integer guiasEliminadas = guiaAgrupadaDAO
					.deletePorEnviarByUbicacion(filterGuiasPendientes);
			log.info("Guias Eliminadas : " + guiasEliminadas);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION, true);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_FALLA_FABRICACION, false);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO, true);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO, false);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_CARTA, true);
			processGuiasPendientesAgrupadas(ubicacion, usuario,
					Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO, false);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private void desvincularGuiaFromAccesorio(
			FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		try {
			List<Guia> guiaAgrupadas = guiaAgrupadaDAO
					.listPorEnviarByUbicacion(filterGuiasPendientes);
			for (Guia guiaAgrupada : guiaAgrupadas) {
				if (guiaAgrupada != null) {
					FilterGuia filterGuia = new FilterGuia();
					filterGuia.setIdGuia(guiaAgrupada.getId());
					List<Accesorio> accesorios = accesorioDAO
							.listAccesoriosFromIdGuia(filterGuia);
					if (accesorios.size() > 0) {
						accesorioDAO
								.updateDesvincularGuiaFromAccesorio(guiaAgrupada);
					}
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private void processGuiasPendientesAgrupadas(Ubicacion ubicacion,
			Usuario usuario, String tipoCambio, Boolean procesadaOW)
			throws Exception {
		try {
			FilterGuiasPendientes filterGuiasPendientes = new FilterGuiasPendientes();
			filterGuiasPendientes.setIdUbicacion(ubicacion.getId());
			filterGuiasPendientes.setTipoCambio(tipoCambio);
			filterGuiasPendientes.setTipoCambioJT(tipoCambio);
			filterGuiasPendientes.setProcesadaOW(procesadaOW);
			List<OrdenTrabajo> ots = guiaAgrupadaDAO
					.listOtPendientesPorGuia(filterGuiasPendientes);
			Bitacora bitacora = new Bitacora();
			Integer productos = 0;
			Producto productoAnterior = new Producto();
			productoAnterior.setId(0);

			if (!ots.isEmpty()) {
				Guia guia = this
						.saveGuiaParaAgrupacion(
								ubicacion,
								usuario,
								ubicacionDAO
										.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
				for (OrdenTrabajo ordenTrabajo : ots) {
					if (!productoAnterior.getId().equals(
							ordenTrabajo.getProducto().getId())) {
						productos++;
						productoAnterior = ordenTrabajo.getProducto();
						if (productos.equals(11)) {
							productos = 0;
							guia = this
									.saveGuiaParaAgrupacion(
											ubicacion,
											usuario,
											ubicacionDAO
													.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF));
						}
					}
					bitacora.setGuia(guia);
					bitacora.setOrdenTrabajo(ordenTrabajo);
					bitacora.setUbicacion(ubicacion);
					bitacoraDAO.updateAsignaBitacoraAGuia(bitacora);

					asignarGuiaToAccesorios(guia,
							accesorioDAO.listAccesoriosByOT(ordenTrabajo
									.getId()));
				}
			}

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Guia saveGuiaParaAgrupacion(Ubicacion origen, Usuario usuario,
			Ubicacion destino) throws Exception {
		try {
			Guia guia = new Guia();
			guia.setEstado(new Estado());
			guia.getEstado().setId(Constants.GUIA_ESTADO_POR_EMITIR);
			guia.setOrigen(origen);
			guia.setDestino(destino);
			guia.setEntregaCliente(false);
			guia.setVigente(true);
			guia.setUsuario(usuario);
			guia.setTipoGuia(Constants.GUIA_TIPO_AGRUPADA);
			guiaDAO.save(guia);
			return guia;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/* Setter de los DAO */
	public void setDocumentoDAO(DocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) {
		this.bitacoraDAO = bitacoraDAO;
	}

	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO) {
		this.tipoFallasDAO = tipoFallasDAO;
	}

	public void setAccesorioDAO(AccesorioDAO accesorioDAO) {
		this.accesorioDAO = accesorioDAO;
	}

	public void setGestionesDAO(GestionesDAO gestionesDAO) {
		this.gestionesDAO = gestionesDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public void setEjecutivaDAO(EjecutivaDAO ejecutivaDAO) {
		this.ejecutivaDAO = ejecutivaDAO;
	}

	public void setAdministracionService(
			AdministracionService administracionService) {
		this.administracionService = administracionService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}

	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}

	public void setLogisticoDAO(LogisticoDAO logisticoDAO) {
		this.logisticoDAO = logisticoDAO;
	}

	public void setRecepcionDAO(RecepcionDAO recepcionDAO) {
		this.recepcionDAO = recepcionDAO;
	}

	public void setDestinoDAO(DestinoDAO destinoDAO) {
		this.destinoDAO = destinoDAO;
	}

	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}

	public void setFamiliaDAO(FamiliaDAO familiaDAO) {
		this.familiaDAO = familiaDAO;
	}

	public void setLineaDAO(LineaDAO lineaDAO) {
		this.lineaDAO = lineaDAO;
	}

	public void setReglaComercialDAO(ReglaComercialDAO reglaComercialDAO) {
		this.reglaComercialDAO = reglaComercialDAO;
	}

	public void setNotaCreditoDAO(NotaCreditoDAO notaCreditoDAO) {
		this.notaCreidtoDAO = notaCreditoDAO;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public void setCambioAutomaticoProveedorCartaDAO(
			CambioAutomaticoProveedorCartaDAO cambioAutomaticoProveedorCartaDAO) {
		this.cambioAutomaticoProveedorCartaDAO = cambioAutomaticoProveedorCartaDAO;
	}

	public void setGuiaAgrupadaDAO(GuiaAgrupadaDAO guiaAgrupadaDAO) {
		this.guiaAgrupadaDAO = guiaAgrupadaDAO;
	}

	public void setEjecutivaService(EjecutivaService ejecutivaService) {
		this.ejecutivaService = ejecutivaService;
	}

	public void setOrdenTrabajoService(OrdenTrabajoService ordenTrabajoService) {
		this.ordenTrabajoService = ordenTrabajoService;
	}

	public void setEnvioRecepcionService(
			EnvioRecepcionService envioRecepcionService) {
		this.envioRecepcionService = envioRecepcionService;
	}

	public void setOWService(OWService oWService) {
		this.oWService = oWService;
	}

	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO) {
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}

	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}
}
