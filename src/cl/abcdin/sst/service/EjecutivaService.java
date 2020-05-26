package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.CambioDAO;
import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.DiasTramosDAO;
import cl.abcdin.sst.dao.EstadoDAO;
import cl.abcdin.sst.dao.FacturaGestionDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.IndicadorDAO;
import cl.abcdin.sst.dao.IndicadorFacturaDAO;
import cl.abcdin.sst.dao.IndicadorGestionDAO;
import cl.abcdin.sst.dao.IndicadorMarcaDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParametroDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.RecepcionDAO;
import cl.abcdin.sst.dao.SemaforoDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.TransportistaDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UsuarioDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Cambio;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.DiasTramos;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Empresa;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.PasosOT;
import cl.abcdin.sst.model.Persona;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.Prts;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.Resultado;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterFactura;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterProveedor;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.filters.SemaforoFilter;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.FacturaOT;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.SSTTParametros;

public class EjecutivaService {
	private static final Log log = LogFactory.getLog(EjecutivaService.class);
	
	private SemaforoDAO semaforoDAO;
	private UtilDAO utilDAO;
	private IndicadorMarcaDAO indicadorMarcaDAO;
	private IndicadorGestionDAO indicadorGestionDAO;
	private IndicadorFacturaDAO indicadorFacturaDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private ParametroDAO parametroDAO;
	private DiasTramosDAO diasTramosDAO;
	private IndicadorDAO indicadorDAO;
	private FacturaGestionDAO facturaGestionDAO;
	private BitacoraDAO bitacoraDAO;
	private EstadoDAO estadoDAO;
	private GestionesDAO gestionesDAO;
	private AccesorioDAO accesorioDAO;
	private ParteDAO parteDAO;
	private ClienteDAO clienteDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private ProveedorDAO proveedorDAO;
	private TransportistaDAO transportistaDAO;
	private CambioDAO cambioDAO;
	private RecepcionDAO recepcionDAO;
	private UsuarioDAO usuarioDAO;
	private GuiaDAO guiaDAO;
	private SucursalService sucursalService;
	private UbicacionDAO ubicacionDAO;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private OWService oWService;
	private InterfazService interfazService;
	//private IndicadorColaDAO indicadorColaDAO;
	
	public List<Indicador> listIndicadoresEjecutivaGestion(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			List<Indicador> indicadores = new ArrayList<Indicador>();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			
			filter.setIdSemaforo(0L);
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_INICIO,
					indicadorGestionDAO.getTotalOTEnSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_TERMINO,
					indicadorGestionDAO.getTotalOTEnSucursalTermino(filter)));
			
			filter.setIdSemaforo(1L);
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_INICIO_VERDE_CLARO,
					indicadorGestionDAO.getTotalOTEnSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_TERMINO_VERDE_CLARO,
					indicadorGestionDAO.getTotalOTEnSucursalTermino(filter)));
			
			filter.setIdSemaforo(2L);
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_INICIO_VERDE_OSCURO,
					indicadorGestionDAO.getTotalOTEnSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_TERMINO_VERDE_OSCURO,
					indicadorGestionDAO.getTotalOTEnSucursalTermino(filter)));
			
			filter.setIdSemaforo(3L);
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_INICIO_AMARILLO,
					indicadorGestionDAO.getTotalOTEnSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_TERMINO_AMARILLO,
					indicadorGestionDAO.getTotalOTEnSucursalTermino(filter)));
			
			filter.setIdSemaforo(4L);
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_INICIO_ROJO,
					indicadorGestionDAO.getTotalOTEnSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.OT_SUCURSAL_TERMINO_ROJO,
					indicadorGestionDAO.getTotalOTEnSucursalTermino(filter)));
			
			filter.setFechaInicio(primerTramo);
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_SUCURSAL_INICIO_7,
					indicadorGestionDAO.getTiempoPromedioSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_SUCURSAL_TERMINO_7,
					indicadorGestionDAO.getTiempoPromedioSucursalTermino(filter)));
			
			filter.setFechaInicio(segundoTramo);
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_SUCURSAL_INICIO_30,
					indicadorGestionDAO.getTiempoPromedioSucursalInicio(filter)));
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_SUCURSAL_TERMINO_30,
					indicadorGestionDAO.getTiempoPromedioSucursalTermino(filter)));
			
			return indicadores;			
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<OrdenTrabajo> listOTIndicadoresEjecutivaGestion(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			
			filter.setIdSemaforo(0L);
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_INICIO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_TERMINO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalTermino(filter);
			}
			
			filter.setIdSemaforo(1L);
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_INICIO_VERDE_CLARO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_TERMINO_VERDE_CLARO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalTermino(filter);
			}
			
			filter.setIdSemaforo(2L);
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_INICIO_VERDE_OSCURO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_TERMINO_VERDE_OSCURO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalTermino(filter);
			}
			
			filter.setIdSemaforo(3L);
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_INICIO_AMARILLO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_TERMINO_AMARILLO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalTermino(filter);
			}
			
			filter.setIdSemaforo(4L);
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_INICIO_ROJO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SUCURSAL_TERMINO_ROJO.longValue())) {
				return indicadorGestionDAO.listOTEnSucursalTermino(filter);
			}
			
			filter.setFechaInicio(primerTramo);
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_SUCURSAL_INICIO_7.longValue())) {
				return indicadorGestionDAO.listOTTiempoPromedioSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_SUCURSAL_TERMINO_7.longValue())) {
				return indicadorGestionDAO.listOTTiempoPromedioSucursalTermino(filter);
			}
			
			filter.setFechaInicio(segundoTramo);
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_SUCURSAL_INICIO_30.longValue())) {
				return indicadorGestionDAO.listOTTiempoPromedioSucursalInicio(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_SUCURSAL_TERMINO_30.longValue())) {
				return indicadorGestionDAO.listOTTiempoPromedioSucursalTermino(filter);
			}
			
			return null;			
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public Resultado calcularSemaforo(Usuario usuario) throws Exception {
		try {
			return semaforoDAO.calcularSemaforo(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Resultado actualizarVista(String vista) throws Exception {
		try {
			return semaforoDAO.actualizarVista(vista);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Resultado calcularIndicadores(Usuario usuario) throws Exception {
		try {
			return semaforoDAO.calcularIndicadores(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Resultado procesoBodega(Usuario usuario) throws Exception {
		try {
			return semaforoDAO.procesoBodega(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Resultado procesoProveedores(Usuario usuario) throws Exception {
		try {
			return semaforoDAO.procesoProveedores(usuario);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Indicador> listIndicadoresNivelServicio(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			List<Indicador> indicadores = new ArrayList<Indicador>();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			filter.setFechaInicio(primerTramo);
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR,
					semaforoDAO.getNivelServicioOTPorCD(filter)));
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR,
					semaforoDAO.getNivelServicioOTNOPorCD(filter)));
			
			

			filter.setFechaInicio(segundoTramo);
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_CD_30,
					semaforoDAO.getNivelServicioOTPorCD(filter)));
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_NO_CD_30,
					semaforoDAO.getNivelServicioOTNOPorCD(filter)));
			
			return indicadores;			
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	

	public List<OrdenTrabajo> listOTIndicadoresNivelServicio(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			filter.setFechaInicio(primerTramo);
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR.longValue())) {
				return semaforoDAO.listOTNivelServicioOTPorCD(filter);
			}
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR.longValue())) {
				return semaforoDAO.listOTNivelServicioOTNOPorCD(filter);
			}
			
			filter.setFechaInicio(segundoTramo);
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_CD_30.longValue())) {
				return semaforoDAO.listOTNivelServicioOTPorCD(filter);
			}
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_NO_CD_30.longValue())) {
				return semaforoDAO.listOTNivelServicioOTNOPorCD(filter);
			}
			return null;			
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<OrdenTrabajo> listOTIndicadoresEjecutivaMarca(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			if (filter.getIdIndicador().equals(Constants.OT_ASIGNADAS_EJECUTIVA_MARCA_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listOTAsignadaEjecutivaMarcaIndicador(filter);
			}
			filter.setIdSemaforo(0L);
			if (filter.getIdIndicador().equals(Constants.OT_SERVICIO_TECNICO_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listOTServicioTecnicoIndicador(filter);
			}
			filter.setIdSemaforo(1L);
			if (filter.getIdIndicador().equals(Constants.OT_SERVICIO_TECNICO_VERDE_CLARO.longValue())) {
				return indicadorMarcaDAO.listOTServicioTecnicoIndicador(filter);
			}
			filter.setIdSemaforo(2L);
			if (filter.getIdIndicador().equals(Constants.OT_SERVICIO_TECNICO_VERDE_OSCURO.longValue())) {
				return indicadorMarcaDAO.listOTServicioTecnicoIndicador(filter);
			}
			filter.setIdSemaforo(3L);
			if (filter.getIdIndicador().equals(Constants.OT_SERVICIO_TECNICO_AMARILLO.longValue())) {
				return indicadorMarcaDAO.listOTServicioTecnicoIndicador(filter);
			}
			filter.setIdSemaforo(4L);
			if (filter.getIdIndicador().equals(Constants.OT_SERVICIO_TECNICO_ROJO.longValue())) {
				return indicadorMarcaDAO.listOTServicioTecnicoIndicador(filter);
			}
			filter.setFechaInicio(primerTramo);
			if (filter.getIdIndicador().equals(Constants.OT_ASIGNADAS_SOLUCIONADAS_CD_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listOTAsignadasSolucionadasCDIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SOLUCIONADAS_CLIENTE_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listOTSolucionadaClienteIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_STL_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioSTLIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_OT_SOLUCIONADAS_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioOTSolucionadaIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_PROMEDIO_ENTREGA_CLIENTE_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioEntregaClienteIndicador(filter);
			}
			filter.setDiasExigencia(diasTramos.getPrimerTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTPorCDIndicador(filter);
			}
			filter.setDiasExigencia(diasTramos.getSegundoTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTNOPorCDIndicador(filter);
			}
			filter.setDiasExigencia(diasTramos.getTercerTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_ACIDO_BY_CD_INDICADOR.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTAcidoIndicador(filter);
			}
			filter.setFechaInicio(segundoTramo);
			if (filter.getIdIndicador().equals(Constants.OT_SOLUCIONADAS_CD_30.longValue())) {
				return indicadorMarcaDAO.listOTAsignadasSolucionadasCDIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.OT_SOLUCIONADAS_CLIENTE_30.longValue())) {
				return indicadorMarcaDAO.listOTSolucionadaClienteIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_SERVICIO_TECNICO_LOCAL_30.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioSTLIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_SOLUCIONADA_30.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioOTSolucionadaIndicador(filter);
			}
			if (filter.getIdIndicador().equals(Constants.TIEMPO_CERRADA_CLIENTE_30.longValue())) {
				return indicadorMarcaDAO.listTiempoPromedioEntregaClienteIndicador(filter);
			}
			filter.setDiasExigencia(diasTramos.getPrimerTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_CD_30.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTPorCDIndicador(filter);
			}		
			
			filter.setDiasExigencia(diasTramos.getSegundoTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_NO_CD_30.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTNOPorCDIndicador(filter);
			}		
			filter.setDiasExigencia(diasTramos.getTercerTramoExigencia());
			if (filter.getIdIndicador().equals(Constants.NIVEL_SERVICIO_ACIDO_30.longValue())) {
				return indicadorMarcaDAO.listNivelServicioOTAcidoIndicador(filter);
			}		
			return null;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Indicador> listIndicadoresEjecutivaMarca(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			List<Indicador> indicadores = new ArrayList<Indicador>();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			indicadores.add(new Indicador(
				Constants.OT_ASIGNADAS_EJECUTIVA_MARCA_INDICADOR,
				indicadorMarcaDAO.getTotalOTAsignadaEjecutivaMarcaIndicador(filter)));
			
			filter.setIdSemaforo(0L);
			indicadores.add(new Indicador(
				Constants.OT_SERVICIO_TECNICO_INDICADOR,
				indicadorMarcaDAO.getTotalOTServicioTecnicoIndicador(filter)));
			
			filter.setIdSemaforo(1L);
			indicadores.add(new Indicador(
					Constants.OT_SERVICIO_TECNICO_VERDE_CLARO,
					indicadorMarcaDAO.getTotalOTServicioTecnicoIndicador(filter)));
			
			filter.setIdSemaforo(2L);
			indicadores.add(new Indicador(
					Constants.OT_SERVICIO_TECNICO_VERDE_OSCURO,
					indicadorMarcaDAO.getTotalOTServicioTecnicoIndicador(filter)));
			
			filter.setIdSemaforo(3L);
			indicadores.add(new Indicador(
					Constants.OT_SERVICIO_TECNICO_AMARILLO,
					indicadorMarcaDAO.getTotalOTServicioTecnicoIndicador(filter)));
			
			filter.setIdSemaforo(4L);
			indicadores.add(new Indicador(
					Constants.OT_SERVICIO_TECNICO_ROJO,
					indicadorMarcaDAO.getTotalOTServicioTecnicoIndicador(filter)));
			
			filter.setFechaInicio(primerTramo);
			indicadores.add(new Indicador(
					Constants.OT_ASIGNADAS_SOLUCIONADAS_CD_INDICADOR,
					indicadorMarcaDAO.getTotalOTAsignadasSolucionadasCDIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.OT_SOLUCIONADAS_CLIENTE_INDICADOR,
					indicadorMarcaDAO.getTotalOTSolucionadaClienteIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_STL_INDICADOR,
					indicadorMarcaDAO.getTiempoPromedioSTLIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_OT_SOLUCIONADAS_INDICADOR,
					indicadorMarcaDAO.getTiempoPromedioOTSolucionadaIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_PROMEDIO_ENTREGA_CLIENTE_INDICADOR,
					indicadorMarcaDAO.getTiempoPromedioEntregaClienteIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getPrimerTramoExigencia());
			indicadores.add(new Indicador(	
					Constants.NIVEL_SERVICIO_PASADOS_OTBYCD_INDICADOR,
					indicadorMarcaDAO.getNivelServicioOTPorCDIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getSegundoTramoExigencia());
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_NO_PASADOS_OT_BY_CD_INDICADOR,
					indicadorMarcaDAO.getNivelServicioOTNOPorCDIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getTercerTramoExigencia());
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_ACIDO_BY_CD_INDICADOR,
					indicadorMarcaDAO.getNivelServicioOTAcidoIndicador(filter)));
			
			filter.setFechaInicio(segundoTramo);
			indicadores.add(new Indicador(
					Constants.OT_SOLUCIONADAS_CD_30,
					indicadorMarcaDAO.getTotalOTAsignadasSolucionadasCDIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.OT_SOLUCIONADAS_CLIENTE_30,
					indicadorMarcaDAO.getTotalOTSolucionadaClienteIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_SERVICIO_TECNICO_LOCAL_30,
					indicadorMarcaDAO.getTiempoPromedioSTLIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_SOLUCIONADA_30,
					indicadorMarcaDAO.getTiempoPromedioOTSolucionadaIndicador(filter)));
			
			indicadores.add(new Indicador(
					Constants.TIEMPO_CERRADA_CLIENTE_30,
					indicadorMarcaDAO.getTiempoPromedioEntregaClienteIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getPrimerTramoExigencia());
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_CD_30,
					indicadorMarcaDAO.getNivelServicioOTPorCDIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getSegundoTramoExigencia());
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_NO_CD_30,
					indicadorMarcaDAO.getNivelServicioOTNOPorCDIndicador(filter)));
			
			filter.setDiasExigencia(diasTramos.getTercerTramoExigencia());
			indicadores.add(new Indicador(
					Constants.NIVEL_SERVICIO_ACIDO_30,
					indicadorMarcaDAO.getNivelServicioOTAcidoIndicador(filter)));
			
			return indicadores;			
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}	
	
	public List<Indicador> listIndicadoresEjecutivaFacturacion(FilterIndicador filter) throws Exception {
		try {
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			List<Indicador> indicadores = new ArrayList<Indicador>();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}
			
			filter.setFechaInicio(primerTramo);
			indicadores.add(new Indicador(	
					Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_7_DIAS,
					indicadorFacturaDAO.getTotalFacturasListasParaEmitir(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_EMITIDAS_ULTIMOS_7_DIAS,
					indicadorFacturaDAO.getTotalFacturasEmitidas(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_7_DIAS,
					indicadorFacturaDAO.getTotalFacturasAceptadasMarcadas(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_7_DIAS,
					indicadorFacturaDAO.getTotalFacturasRechazadasMarcadas(filter)));
			
			filter.setFechaInicio(segundoTramo);
			indicadores.add(new Indicador(	
					Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_30_DIAS,
					indicadorFacturaDAO.getTotalFacturasListasParaEmitir(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_EMITIDAS_ULTIMOS_30_DIAS,
					indicadorFacturaDAO.getTotalFacturasEmitidas(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_30_DIAS,
					indicadorFacturaDAO.getTotalFacturasAceptadasMarcadas(filter)));
			indicadores.add(new Indicador(	
					Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_30_DIAS,
					indicadorFacturaDAO.getTotalFacturasRechazadasMarcadas(filter)));
			
			return indicadores;			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Indicador getIndicadorById (Integer id) throws Exception {
		try {
			return indicadorDAO.getIndicadorById(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOTRecepcionBodegaByFilter(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterRecepcionProducto.setOrderBy(gridControl.getOrderBy());
			filterRecepcionProducto.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOTRecepcionBodegaByFilter(filterRecepcionProducto, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTRecepcionBodegaByFilter(filterRecepcionProducto));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOTCambioAuomaticoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOTCambioAuomaticoByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTCambioAuomaticoByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public OrdenTrabajo getOTById(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.getOTById(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public List<Gestion> listGestionesByIdOT(Long idOT) throws Exception {
		try {
			return gestionesDAO.listGestionesByIdOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Accesorio> listAccesoriosByOT(Long idOT) throws Exception {
		try {
			return accesorioDAO.listAccesoriosByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Parte> listPartesOTByOT(Long idOT) throws Exception {
		try {
			return parteDAO.listPartesOTByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateClienteByOT(Long idOT,Usuario usuario, Cliente cliente) throws Exception {
		try {
			cliente = sucursalService.saveCliente(cliente);
			OrdenTrabajo oT = ordenTrabajoDAO.getOTById(idOT);
			
			if(oT.getCliente()==null || oT.getCliente().getId()==null){
				oT.setCliente(cliente);
				ordenTrabajoDAO.updateOTbyOT(oT);
			}
			saveGestion(idOT,usuario, " actualiza informacion de contacto del cliente");
		} catch (Exception e) {       
			log.error(e,e); 
			throw e;
		}
	}
	
	
	public Integer updateSTecnicoOT(OrdenTrabajo ordenTrabajo, Usuario usuario) throws Exception {
		try {
			if(ordenTrabajoDAO.updateSTecnicoOT(ordenTrabajo)==1){
				saveGestion(ordenTrabajo.getId(),usuario, "  ingresa el contrato y/o diagnostico del servicio técnico");
				return 1;
			}
			return null;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveGestion(Long idOT, Usuario usuario, String descripcion) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(idOT);
			Gestion gestion = new Gestion();
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			gestion.setGestion(descripcion);
			gestionesDAO.saveGestion(gestion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveGestion(Long idOT, Usuario usuario, String descripcion, Date fecha) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(idOT);
			Gestion gestion = new Gestion();
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			gestion.setGestion(descripcion);
			gestion.setFecha(fecha);
			gestionesDAO.saveGestion(gestion);
			ordenTrabajoDAO.updateOTTareaUrgenteFlagDown(gestion.getOt());
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateIndicadores (List<Indicador> indicadores) throws Exception {
		try{
			for(Indicador rIndicador: indicadores){
				Indicador comprueba;
      			comprueba = indicadorDAO.getIndicadorById(rIndicador.getId());
      			if (comprueba.getRangoMaximo() != rIndicador.getRangoMaximo() || comprueba.getRangoMinimo() != rIndicador.getRangoMinimo()){
      			indicadorDAO.updateIndicador(rIndicador);	
      			}
			}
		}catch (Exception e){
		}
	}
	
	public void updateParametros (List<Parametro> parametros) throws Exception {
		try{
			for (Parametro parametro: parametros){
				parametroDAO.updateParametros(parametro);
			}
		}catch (Exception e){
		}
	}
		
	public Integer asignarSTecnicoOT(ServicioTecnico servicioTecnico) throws Exception {
		try {
			return servicioTecnicoDAO.asignarSTecnicoOT(servicioTecnico);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer updateEstadoOT(OrdenTrabajo ordenTrabajo) throws Exception {
		try {
			return ordenTrabajoDAO.updateEstadoOT(ordenTrabajo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public OrdenTrabajo getEstadoOT(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.getEstadoOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public ServicioTecnico getServicioTecnicoByOT(Long idOT) throws Exception {
		try {
			  ServicioTecnico servicio = servicioTecnicoDAO.getServicioTecnicoByOT(idOT);
			  if (servicio == null)
				  return null;
			  servicio.setGuia(guiaDAO.getGuiaByIdOT(idOT));
			  return servicio;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ServicioTecnico getSTecnicoById(Long id)throws Exception{
		try{
			ServicioTecnico servicio = servicioTecnicoDAO.getSTecnicoById(id);
			if(servicio == null){
				return null;
			}else{
			return servicio;
			}
		   }catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	

	public List<ServicioTecnico> listServicioTecnicoByComuna(FilterServicioTecnico filter) throws Exception {
		try {
			return servicioTecnicoDAO.listServicioTecnico(filter);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<ServicioTecnico> listServicioTecnico() throws Exception {
		try {
			return servicioTecnicoDAO.listServicioTecnico();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<ServicioTecnico> listServicioTecnicoGM() throws Exception {
		try {
			return servicioTecnicoDAO.listServicioTecnicoGM();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
//	public ListRange listProveedores(FilterProveedor filterProveedor, GridControl gridControl) throws Exception {
//		try {
//			filterProveedor.setSortOrder(gridControl.getSortOrder());
//			filterProveedor.setOrderBy(gridControl.getOrderBy());
//		    ListRange listRange = new ListRange();
//		    listRange.setRows(proveedorDAO.listProveedores(filterProveedor, gridControl));
//		    listRange.setTotal(proveedorDAO.getTotalProveedor(filterProveedor));
//			return listRange;
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	public List<Proveedor> listProveedores() throws Exception {
		try {
			return proveedorDAO.listProveedores();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllProveedoresCheck(FilterProveedor filterProveedor) throws Exception {
		try {
			return proveedorDAO.listAllProveedoresCheck(filterProveedor);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void autorizarCambioBodega(Cambio cambio, Usuario usuario) throws Exception {
		try {
			if(cambio.getNumeroCambio() != 0){
				if(cambioDAO.existeNumeroCambio(cambio.getNumeroCambio())>0){
					throw new SSTException("El numero de autorizacion de cambio ya ha sido utilizado en una orden de trabajo, autorice el cambio con otro numero");
				}
			}
			cambioDAO.autorizarCambio(cambio);
			this.saveGestion(cambio.getOt().getId(), usuario, " autoriza el cambio del producto al cliente");
			
			cambio.getOt().setTipoFacturar(cambio.getTipoFacturar());
			this.iniciarBitacoraCambio(cambio.getOt());
			interfazService.createInterfazAsn(cambio);
			interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_ASN);
			OrdenTrabajo oT = cambio.getOt();
			if("S".equals(cambio.getOt().getConOsinProductoFisico())){
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_BODEGA_SIN);	
			}else{
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_BODEGA_CON);	
			}
			ordenTrabajoDAO.updateTipoXN(oT);
			oT.setBanderaOrigenOT(Constants.CASO_USO_3);
			ordenTrabajoDAO.updateOTBTNOrigen(oT);
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void autorizarCambioProveedor(Cambio cambio, Usuario usuario) throws Exception {
		try {
			if(cambio.getNumeroCambio() != 0){
				if(cambioDAO.existeNumeroCambio(cambio.getNumeroCambio())>0){
					throw new SSTException("El numero de autorizacion de cambio ya ha sido utilizado en una orden de trabajo, autorice el cambio con otro numero");
				}
			}
			cambioDAO.autorizarCambio(cambio);
			this.saveGestion(cambio.getOt().getId(), usuario, " autoriza el cambio del producto al cliente");
			
			cambio.getOt().setTipoFacturar(cambio.getTipoFacturar());
			this.iniciarBitacoraCambio(cambio.getOt());
			
			//System.out.println("-----> getConOsinProductoFisico(): "+cambio.getOt().getConOsinProductoFisico());
			
			if("S".equals(cambio.getOt().getConOsinProductoFisico())){
				OrdenTrabajo oT = cambio.getOt();
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_PROVEEDOR_SIN);
				ordenTrabajoDAO.updateTipoXN(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_4);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
				
				this.logicaComunTransporteProveedor(cambio, usuario, Constants.SUCURSAL_FRANCHISE_PROVEEDOR);
				
			}else{
				interfazService.createInterfazAsn(cambio);
				interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_ASN);
				
				OrdenTrabajo oT = cambio.getOt();
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_PROVEEDOR_CON);
				ordenTrabajoDAO.updateTipoXN(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_3);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
				
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void autorizarCambioTransporte(Cambio cambio, Usuario usuario) throws Exception {
		try {
			
			//System.out.println("-----> getConOsinProductoFisico(): "+cambio.getOt().getConOsinProductoFisico());
			if(cambio.getNumeroCambio() != 0){
				if(cambioDAO.existeNumeroCambio(cambio.getNumeroCambio())>0){
					throw new SSTException("El numero de autorizacion de cambio ya ha sido utilizado en una orden de trabajo, autorice el cambio con otro numero");
				}
			}
			cambioDAO.autorizarCambio(cambio);
			this.saveGestion(cambio.getOt().getId(), usuario, " autoriza el cambio del producto al cliente");
			
			cambio.getOt().setTipoFacturar(cambio.getTipoFacturar());
			this.iniciarBitacoraCambio(cambio.getOt());
			
			if("S".equals(cambio.getOt().getConOsinProductoFisico())){
				OrdenTrabajo oT = cambio.getOt();
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_TRANSPORTE_SIN);
				ordenTrabajoDAO.updateTipoXN(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_4);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
				
				this.logicaComunTransporteProveedor(cambio, usuario, Constants.SUCURSAL_FRANCHISE_TRANSPORTE);
				
			}else{
				interfazService.createInterfazAsn(cambio);
				interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_ASN);
				
				OrdenTrabajo oT = cambio.getOt();
				oT.setIdentificadorTipoXN(Constants.TIPO_XN_TRANSPORTE_CON);
				ordenTrabajoDAO.updateTipoXN(oT);
				oT.setBanderaOrigenOT(Constants.CASO_USO_4);
				ordenTrabajoDAO.updateOTBTNOrigen(oT);
			}
						
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void logicaComunTransporteProveedor(Cambio cambio, Usuario usuario, String sucFranchise) throws Exception {
		try {
			//1� Implementacion del aumento del NO DISPONIBLE en sucursal LOGISTICA INVERSA en PMM.
			Prts logisticaInversa = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.LOGISTICA_INVERSA);
			Ubicacion ubi = new Ubicacion();
			ubi.setId(Long.valueOf(logisticaInversa.getValor()));
			cambio.setUbicacion(ubi);
			cambio.getOt().setBanderaOrigenOT(Constants.CASO_USO_GENERICO_1);
			interfazService.createInterfazPMM(cambio.getUbicacion(), cambio.getOt(), Constants.DC_QUANTITY_POSITIVO);
			interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_PMM);
			
			//2� Disminucion del No disponible en PMM
			ubi = new Ubicacion();
			//ubi.setId(Long.valueOf(sucursalFranchise.getValor()));
			ubi.setId(Long.valueOf(logisticaInversa.getValor()));
			cambio.setUbicacion(ubi);
			cambio.getOt().setBanderaOrigenOT(Constants.CASO_USO_4);
			interfazService.createInterfazPMM(cambio.getUbicacion(), cambio.getOt(), Constants.DC_QUANTITY_NEGATIVO);
			interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_PMM);
			
			//3� Implementacion del aumento del NO DISPONIBLE en sucursal FRANCHISEDE en PMM.
			Prts sucursalFranchise = SSTTParametros.getInstancia().getParametroPorCodigo(sucFranchise);
			ubi = new Ubicacion();
			ubi.setId(Long.valueOf(sucursalFranchise.getValor()));
			cambio.setUbicacion(ubi);
			cambio.getOt().setBanderaOrigenOT(Constants.CASO_USO_4);
			interfazService.createInterfazPMM(cambio.getUbicacion(), cambio.getOt(), Constants.DC_QUANTITY_POSITIVO);
			interfazService.saveOtTracking(cambio.getOt(), Constants.ID_OT_INTERFAZ_PMM);
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveCambioPrioridadSemaforo(List<OrdenTrabajo> listaOts, String cambioPrioridad, Usuario usuario) throws Exception{
		try {
			Integer operador = cambioPrioridad.equals("subir") ? 1 : -1;
			List<OrdenTrabajo> listaOrdenesSemaforo = new ArrayList<OrdenTrabajo>();
			
			SemaforoFilter semaforoFilter = new SemaforoFilter();
			semaforoFilter.setOrdenTrabajos(listaOts);
			semaforoFilter.setTipoSemaforo("suc_inicio");
			listaOrdenesSemaforo = semaforoDAO.listSemaforo(semaforoFilter);//va por los semaforos sucursal inicio
			semaforoFilter.setTipoSemaforo("suc_fin");
			listaOrdenesSemaforo.addAll(semaforoDAO.listSemaforo(semaforoFilter));//va por los semaforos sucursal fin
			semaforoFilter.setTipoSemaforo("s_tecnico");
			listaOrdenesSemaforo.addAll(semaforoDAO.listSemaforo(semaforoFilter));//va por los semaforos servicio tecnico
			
			Gestion gestion = new Gestion();
			gestion.setUsuario(usuario);
			for (OrdenTrabajo ordenTrabajo : listaOrdenesSemaforo) {
				gestion.setOt(ordenTrabajo);
				if(ordenTrabajo.getSemaforoSucursalInicio() != null && ((cambioPrioridad.equals("subir") && ordenTrabajo.getSemaforoSucursalInicio() < 4) || (cambioPrioridad.equals("bajar")  && ordenTrabajo.getSemaforoSucursalInicio() > 1))){
					ordenTrabajo.setSemaforoSucursalInicio(ordenTrabajo.getSemaforoSucursalInicio() + operador);
					semaforoDAO.updateSemaforoPrioridadSucIni(ordenTrabajo);
					gestion.setGestion("modifico el color del semaforo de sucursal inicio de la orden de trabajo");
					gestionesDAO.saveGestion(gestion);
				}
				if(ordenTrabajo.getSemaforoSucursalfin() != null && ((cambioPrioridad.equals("subir") && ordenTrabajo.getSemaforoSucursalfin() < 4) || (cambioPrioridad.equals("bajar")  && ordenTrabajo.getSemaforoSucursalfin() > 1))){
					ordenTrabajo.setSemaforoSucursalfin(ordenTrabajo.getSemaforoSucursalfin() + operador);
					semaforoDAO.updateSemaforoPrioridadSucFin(ordenTrabajo);
					gestion.setGestion("modifico el color del semaforo de sucursal termino de la orden de trabajo");
					gestionesDAO.saveGestion(gestion);
				}
				if(ordenTrabajo.getSemaforoServicioTecnico() != null && ((cambioPrioridad.equals("subir") && ordenTrabajo.getSemaforoServicioTecnico() < 4) || (cambioPrioridad.equals("bajar")  && ordenTrabajo.getSemaforoServicioTecnico() > 1))){
					ordenTrabajo.setSemaforoServicioTecnico(ordenTrabajo.getSemaforoServicioTecnico() + operador);
					semaforoDAO.updateSemaforoPrioridadST(ordenTrabajo);
					gestion.setGestion("modifico el color del semaforo de servicio técnico de la orden de trabajo");
					gestionesDAO.saveGestion(gestion);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	//Begin------Gestion Facturas------//
	//listEjecutivasMarca
	
	public List<Familia> listFamilia()throws Exception{
		try {
		return facturaGestionDAO.listFamilia();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	public List<Proveedor> listProveedor()throws Exception{
		try {
			return facturaGestionDAO.listProveedor();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	public List<Marca> listMarca()throws Exception{
		try {
			return facturaGestionDAO.listMarca();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	public List<Transportista> listTransportista()throws Exception{
		try {			
			return transportistaDAO.listTransportista();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	public Transportista getTransportistaByUltimaRecepcionOT(Long idOT)throws Exception{
		try {			
			return transportistaDAO.getTransportistaByUltimaRecepcionOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}		
	}
	
	public ListRange listFactura(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			Factura factura = new Factura();
			factura.setOrderBy(gridControl.getOrderBy());
			factura.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(facturaGestionDAO.listFactura(gridControl));
			listRange.setTotal(facturaGestionDAO.listTotalFactura());
			for (Object obj : listRange.getRows()) {
				List<Long> ots = facturaGestionDAO.listIdOTbyIdFactura(((Factura)obj).getId());
				StringBuilder sb = new StringBuilder();
				
				for (Long ot : ots) {
					sb.append(ot.toString() + ",");					
				}
				((Factura)obj).setOtsConcatenadas(sb.toString());
			}
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void AceptarRechazarFactura(Integer estadoId, List<Factura> facturas, Usuario usuario,Ubicacion ubicacion) throws Exception{
		try{
			for (Factura factura : facturas) {				
				if (factura.getEstado().getId().equals(Constants.FACTURA_ACEPTADA) || factura.getEstado().getId().equals(Constants.FACTURA_RECHAZADA)){
					throw new SSTException("No se puede marcar una factura que ya esta marcada como aceptada o rechazada");
				}else if(factura.getEstado().equals(Constants.FACTURA_DESHECHA)){
					throw new SSTException("No se puede marcar una factura que ha sido borrada");
				}else if(factura.getEstado().getId().equals(Constants.FACTURA_LISTA_PARA_EMISION_CON_NUMERO_SC_ASIGNADO)){
					throw new SSTException("No se puede marcar una factura que esta lista para emitir");				
				}else if(factura.getEstado().getId().equals(Constants.FACTURA_GENERADA_ESPERA_EMISION)){				
					throw new SSTException("Las facturas todadavia no han sido procesadas por OW");
				}else if(factura.getEstado().getId().equals(Constants.FACTURA_ASIGNADA)){
					factura.setFechaAceptacion( utilDAO.getDate());					
					factura.setEstado(new Estado());
					factura.getEstado().setId(estadoId);
					facturaGestionDAO.updateEstadoFacturaAceptada(factura);
					
					List<Long> ots = facturaGestionDAO.getIdOTByIdFactura(factura.getId());
					for (Long idOT : ots) {
						OrdenTrabajo ot = ordenTrabajoDAO.getOTById(idOT);
						
						Gestion gestion = new Gestion();
						gestion.setFecha( utilDAO.getDate());
						if(estadoId.equals(Constants.FACTURA_ACEPTADA)){
							gestion.setGestion(usuario.getNombreCompleto() + " marco aceptada la factura de la orden de trabajo");
						}else if (estadoId.equals(Constants.FACTURA_RECHAZADA)){
							gestion.setGestion(usuario.getNombreCompleto() + " marco rechazada la factura de la orden de trabajo");
						}
						gestion.setOt(ot);
						gestion.setUsuario(usuario);
						gestionesDAO.saveGestion(gestion);
						
						if (ot.getEmpresaFacturar().getNombre().equals(Constants.FACTURAR_TIPO_PROVEEDOR) || ot.getEmpresaFacturar().getNombre().equals(Constants.FACTURAR_TIPO_TRANSPORTISTA)) {
							ot.setMotivoCierre("Por factura aceptada");
							gestion.setGestion(" cierra la orden de trabajo");	
							gestionesDAO.saveGestion(gestion);
							facturaGestionDAO.updateOtTareaUrgente(ot);
						}
					}
				}
			}
		}catch (SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e, e);
			throw e;			
		}
	}
	
	public void procesarOW(Integer estadoId, List<Factura> facturas, Usuario usuario,Ubicacion ubicacion) throws Exception{
		try{
			for (Factura factura : facturas) {		
				if(factura.getEstado().getId().equals(Constants.FACTURA_GENERADA_ESPERA_EMISION)){
					factura.setNumeroSC(oWService.moveSCDesdePostVenta(factura, Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA,Constants.UBICACION_FF_OW, factura.getIdFacturar(), ubicacion));
					factura.setFechaSC(utilDAO.getDate());
					factura.getEstado().setId(Constants.FACTURA_LISTA_PARA_EMISION_CON_NUMERO_SC_ASIGNADO);
					facturaGestionDAO.updateEstadoFactura(factura);
					List<Long> ots = facturaGestionDAO.getIdOTByIdFactura(factura.getId());
					for(Long ot : ots){
						OrdenTrabajo otAux = ordenTrabajoDAO.getOTById(ot);
						otAux.setCerrada(true);
						facturaGestionDAO.updateOtTareaUrgente(otAux);
					}
				}else{
					throw new SSTException("No se puede procesar la factura ya que su estado es : " +factura.getEstado().getGlosa());
				}
			}
		}catch (SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e, e);
			throw e;			
		}
	}
	
	public void deshacerFactura(List<Factura> facturas, Usuario usuario) throws Exception{
		try{
			for (Factura factura: facturas) {					
				
				List<Long> ots = facturaGestionDAO.getIdOTByIdFactura(factura.getId());
				if(factura.getEstado().getId().equals(Constants.FACTURA_GENERADA_ESPERA_EMISION) || factura.getEstado().getId().equals(Constants.FACTURA_RECHAZADA)){
					factura.getEstado().setId(Constants.FACTURA_DESHECHA);
					facturaGestionDAO.updateEstadoFacturaAceptada(factura);
					for (Long idOT : ots) {
						OrdenTrabajo ot = new OrdenTrabajo(); 
						ot.setId(idOT);
						
						Gestion gestion = new Gestion();
						gestion.setFecha(utilDAO.getDate());
						gestion.setUsuario(usuario);
						gestion.setOt(ot);
						gestion.setGestion(" desactivo la factura a la que estaba asignada la orden de trabajo");
						gestionesDAO.saveGestion(gestion);
					}
				}
				else{
					throw new SSTException("Solo se pueden deshacer facturas que esten generadas o rechazadas");	
				}	
			}
		}catch (SSTException e) {
			throw e;
		}catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public void asignarFactura(List<Factura> facturas, Long numeroFactura, Date fechaEmision, Usuario usuario) throws Exception{
		try{
			for (Factura factura: facturas) {
				List<Long> ots = facturaGestionDAO.getIdOTByIdFactura(factura.getId());
				factura.getEstado().setId(Constants.FACTURA_ASIGNADA);
				factura.setFechaEmision(fechaEmision);
				factura.setNumero(numeroFactura);
				if(facturaGestionDAO.updateEstadoFacturaAceptada(factura) == 0){
					new SSTException("Error al asignar el numero y fecha de emision a la factura");
				} else{
					for (Long idOT : ots) {
						OrdenTrabajo ot = new OrdenTrabajo(); 
						ot.setId(idOT);
						
						Gestion gestion = new Gestion();
						gestion.setFecha(utilDAO.getDate());
						gestion.setUsuario(usuario);
						gestion.setOt(ot);
						gestion.setGestion(" emitio la factura de la orden de trabajo con el numero " + numeroFactura.toString());
						gestionesDAO.saveGestion(gestion);
						}
				}	
			}
		}catch (SSTException e) {
			throw e;
		}catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listResumenFacturasByOT(GridControl gridControl, FilterFactura filter) throws Exception {
		try{
			Long sumaRut = (long) 0;
			if (filter.getIdFacturar() != null){
				sumaRut += filter.getIdFacturar();
			}
			if (filter.getIdProveedor() != null){
				sumaRut += filter.getIdProveedor();
			}
			if (filter.getIdTransportista() != null){
				sumaRut += filter.getIdTransportista();
			}
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setRut(sumaRut);
			ListRange listRange = new ListRange();
			Factura factura = new Factura();
			factura.setOrderBy(gridControl.getOrderBy());
			factura.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(facturaGestionDAO.listResumenFacturasByOT(gridControl, filter));
			listRange.setTotal(facturaGestionDAO.listTotalResumenFacturas(filter));
			return listRange;			
		}catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOtsFactura(FilterFactura filter, GridControl gridControl) throws Exception {
		try{
			ListRange listRange = new ListRange();			
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(facturaGestionDAO.listOtsFactura(filter, gridControl));
			listRange.setTotal(facturaGestionDAO.listTotalOtsFactura(filter));
			return listRange;			
		}catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllOtsFacturaCheck(FilterFactura filter) throws Exception {
		try{
			return facturaGestionDAO.listAllOtsFacturaCheck(filter);
		}catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Factura> listFacturasIndicadoresEjecutivaFacturacion(FilterIndicador filter) throws Exception {
		try{
			
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}

			filter.setFechaInicio(primerTramo);
			if (filter.getIdIndicador().equals(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_7_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasListasParaEmitir(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_EMITIDAS_ULTIMOS_7_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasEmitidas(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_7_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasAceptadasMarcadas(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_7_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasRechazadasMarcadas(filter);
			}

			filter.setFechaInicio(segundoTramo);
			if (filter.getIdIndicador().equals(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_30_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasListasParaEmitir(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_EMITIDAS_ULTIMOS_30_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasEmitidas(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_30_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasAceptadasMarcadas(filter);
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_30_DIAS.longValue())) {
				return indicadorFacturaDAO.listFacturasRechazadasMarcadas(filter);
			}
			return null;			
		} catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listFacturasIndicadoresEjecutivaFacturacion(FilterIndicador filter, GridControl gridControl) throws Exception {
		try{
			ListRange listRange = new ListRange();	
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			
			DiasTramos diasTramos = diasTramosDAO.getDiasTramos();
			Calendar calendar = Calendar.getInstance();
			Date primerTramo = null;
			Date segundoTramo = null;
			
			if (filter.getFechaTermino() == null) {
				filter.setFechaTermino(utilDAO.getDateTrunc());
			}
			
			if (filter.getFechaInicio() == null) {
				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getPrimerTramo());
				primerTramo = calendar.getTime();

				calendar.setTime(utilDAO.getDateTrunc());
				calendar.add(Calendar.DATE, -diasTramos.getSegundoTramo());
				segundoTramo = calendar.getTime();
			} else {
				primerTramo = filter.getFechaInicio();
				segundoTramo = filter.getFechaInicio();
			}

			filter.setFechaInicio(primerTramo);
			if (filter.getIdIndicador().equals(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_7_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasListasParaEmitir(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasListasParaEmitir(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_EMITIDAS_ULTIMOS_7_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasEmitidas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasEmitidas(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_7_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasAceptadasMarcadas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasAceptadasMarcadas(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_7_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasRechazadasMarcadas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasRechazadasMarcadas(filter).intValue());
			}

			filter.setFechaInicio(segundoTramo);
			if (filter.getIdIndicador().equals(Constants.FACTURAS_LISTAS_PARA_EMITIR_ULTIMOS_30_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasListasParaEmitir(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasListasParaEmitir(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_EMITIDAS_ULTIMOS_30_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasEmitidas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasEmitidas(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_ACEPTADAS_MARCADAS_ULTIMOS_30_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasAceptadasMarcadas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasAceptadasMarcadas(filter).intValue());
			}
			if (filter.getIdIndicador().equals(Constants.FACTURAS_RECHAZADAS_MARCADAS_ULTIMOS_30_DIAS.longValue())) {
				listRange.setRows(indicadorFacturaDAO.listFacturasRechazadasMarcadas(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalFacturasRechazadasMarcadas(filter).intValue());
			}
			//Indicador sin nombre
			if(filter.getIdIndicador().equals(Constants.RESUMEN_FACTURAS.longValue())){
				listRange.setRows(indicadorFacturaDAO.listIndicadorFacturaByRut(filter, gridControl));
				listRange.setTotal(indicadorFacturaDAO.getTotalIndicadorFacturaByRut(filter));
			}
			return listRange;			
		} catch (Exception e){
			log.error(e,e);
			throw e;
		}
	}
	
	public void GenerarFactura(List<OrdenTrabajo> OTs, Usuario usuario) throws Exception {
		try{
			String rutFacturar = null;
			for (OrdenTrabajo ordenTrabajo : OTs) {
				ordenTrabajo = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
				if (rutFacturar == null) {
					rutFacturar = ordenTrabajo.getEmpresaFacturar().getRut();
				}else if (!rutFacturar.equals(ordenTrabajo.getEmpresaFacturar().getRut())) {
					throw new SSTException("Las ordenes de trabajo tienen mas de un destino a quien facturar");
				}
				if (facturaGestionDAO.getExisteOTFactura(ordenTrabajo.getId()) > 0){
					throw new SSTException("La orden de trabajo " + ordenTrabajo.getId().toString() + " ya esta asignada a una factura");
				}
				if (facturaGestionDAO.getExisteOTFacturaDestino(ordenTrabajo.getId()) == 0){
					throw new SSTException("La orden de trabajo " + ordenTrabajo.getId().toString() + "  no tiene asignado a quien se le factura");
				}
			}

			Factura factura = new Factura();
			factura = facturaGestionDAO.getOTFactura(OTs);
			factura = getDestinoFacturar(factura);
			
			Long neto = facturaGestionDAO.getCalculoNetoIvaByListOT(OTs);
			factura.setMontoNeto(neto);
			facturaGestionDAO.insertFactura(factura);
			factura.setId(factura.getId()+1);
			for (OrdenTrabajo ordenTrabajo : OTs) {
				Gestion gestion = new Gestion();
				gestion.setFecha( utilDAO.getDate());
				gestion.setUsuario(usuario);			
				gestion.setOt(ordenTrabajo);
				gestion.setGestion(" genero una factura con la orden de trabajo");
				gestionesDAO.saveGestion(gestion);
				
				FacturaOT facturaOT = new FacturaOT();
				facturaOT.setFactura(factura);
				facturaOT.setOt(ordenTrabajo);
				facturaGestionDAO.insertRelacionFactura(facturaOT);					
			}
				
			List<Producto> productos = facturaGestionDAO.listProductosByListOT(OTs);
			for (Producto producto : productos) {
				producto.setIdFactura(factura.getId());
				facturaGestionDAO.insertDetallesFactura(producto);
			}
		} catch (SSTException e) {
			throw e;
		}catch (Exception e){  
			log.error(e,e); 
			throw e;
		}
	}
	
	private Factura getDestinoFacturar (Factura factura)throws Exception{
		try {
			if(factura.getTipoFactura().equals(Constants.FACTURAR_TIPO_TRANSPORTISTA)){
				Transportista transportista = transportistaDAO.getTransportistaById(factura.getIdFacturar());
				if(transportista == null){
					throw new SSTException("El destino a facturar es el transportista : "+factura.getIdFacturar() +", y no ha sido ingresado al sistema");
				}else{
					factura.setNombre(transportista.getNombreCompleto());
					factura.setDireccion(transportista.getDireccion());
					factura.setComuna(transportista.getComuna().getGlosa());
				}
			} else if(factura.getTipoFactura().equals(Constants.FACTURAR_TIPO_PROVEEDOR)){
				Ubicacion proveedor = proveedorDAO.getProveedorById(factura.getIdFacturar());
				if(proveedor == null){
					throw new SSTException("El destino a facturar es el proveedor : "+factura.getIdFacturar()+", y no ha sido ingresado al sistema");
				}else{
					factura.setNombre(proveedor.getNombre());
					factura.setDireccion(proveedor.getDireccion());
					factura.setComuna(proveedor.getComuna() != null ?proveedor.getComuna().getGlosa():"");
				}
			} else {
				Ubicacion tienda = ubicacionDAO.getDireccionTienda(factura.getIdFacturar());
				if(tienda == null){
					throw new SSTException("El destino a facturar es la tienda : "+factura.getIdFacturar()+" ,y no ha sido ingresado al sistema");
				}else{
					factura.setNombre(tienda.getNombre());
					factura.setDireccion(tienda.getDireccion());
					factura.setComuna(tienda.getComuna().getGlosa());
					factura.setGiro(tienda.getGiro());
				}
			}
			return factura;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	//End------Gestion Facturas------//
	public List<Bitacora> listResumenBitacorasByIdOT (Long idOt) throws Exception {
		try{
			List<Bitacora> bitacoras = bitacoraDAO.listResumenBitacorasByIdOT(idOt);

			if (bitacoras.size() == 0) {
				return bitacoras;
			}
			for (Bitacora bitacora : bitacoras) {
				bitacora.setBitacorasInternas(this.listResumenBitacoraInternaByBitacora(bitacora));
			}
			Bitacora bitacora = bitacoras.get(bitacoras.size() - 1);
			while (bitacora.getEstado().getEstadoSiguiente() != null && bitacora.getEstado().getEstadoSiguiente().getId() != 0) {
				Bitacora bitacoraSiguiente = new Bitacora();
				Estado estadoSiguiente = estadoDAO.getNextEstadoByIdEstado(bitacora.getEstado().getId());
				bitacoraSiguiente.setEstado(estadoSiguiente);
				bitacoras.add(bitacoraSiguiente);
				bitacora = bitacoraSiguiente;
			}
			
			return bitacoras;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}		
	}
	
	public List<BitacoraInterna> listResumenBitacoraInternaByBitacora(Bitacora bitacora) throws Exception {
		try {
			List<BitacoraInterna> bitacorasInternas = bitacoraInternaDAO.listResumenBitacorasInternasByBitacora(bitacora);

			for (BitacoraInterna bitacoraInterna : bitacorasInternas) {
				bitacoraInterna.setEstado(estadoDAO.getEstadoById(bitacoraInterna.getEstado().getId()));
			}
			
			if (bitacorasInternas.size() == 0) {
				return bitacorasInternas;
			}
			
			BitacoraInterna bitacoraInterna = bitacorasInternas.get(bitacorasInternas.size() - 1);
			while (bitacoraInterna.getEstado().getEstadoSiguiente() != null && bitacoraInterna.getEstado().getEstadoSiguiente().getId() != 0) {
				BitacoraInterna bitacoraInternaSiguiente = new BitacoraInterna();
				Estado estadoSiguiente = estadoDAO.getNextEstadoByIdEstado(bitacoraInterna.getEstado().getId());
				bitacoraInternaSiguiente.setEstado(estadoSiguiente);
				bitacorasInternas.add(bitacoraInternaSiguiente);
				bitacoraInterna = bitacoraInternaSiguiente;
			}
			
			return bitacorasInternas;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<Bitacora> listAllBitacorasByIdOT (Long idOt) throws Exception {
		try{
			return bitacoraDAO.listBitacorasByIdOT(idOt);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}		
	}
	
	public Cliente getClienteByOT(Long idOT) throws Exception {
		try {
			return clienteDAO.getClienteByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public Cambio getCambioByOT(Long idOT) throws Exception {
		try {
			return cambioDAO.getCambioByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Recepcion> listRecepcionesByOT(Long idOT) throws Exception {
		try {
			return recepcionDAO.listRecepcionesByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateCerrarOT(OrdenTrabajo ordenTrabajo, Usuario usuario) throws Exception {
		try {
			ordenTrabajoDAO.updateCerrarOT(ordenTrabajo);
			this.saveGestion(ordenTrabajo.getId(), usuario, " cierra la orden de trabajo");
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateDesactivarOT(OrdenTrabajo ordenTrabajo, Usuario usuario) throws Exception {
		try {
			this.saveGestion(ordenTrabajo.getId(), usuario, " desactiva la Orden de Trabajo");
			ordenTrabajoDAO.updateDesactivarOT(ordenTrabajo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public PasosOT getPasosOT(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.getPasosOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public OrdenTrabajo getAntesFacturarByOT(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.getAntesFacturarByOT(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public Integer updateAntesEnviarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		try {
			return ordenTrabajoDAO.updateAntesEnviarOT(ordenTrabajo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
			
		}
	}
	
	public void antesEnviarOT(OrdenTrabajo ordenTrabajo, List<Accesorio> accesorios,Usuario usuario) throws Exception {
		try {
			ordenTrabajoDAO.updateAntesEnviarOT(ordenTrabajo);
			Gestion gestion = new Gestion();
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			gestion.setGestion("actualizo información de cargo, destino y accesorios. antes de facturar la orden de trabajo");
			gestionesDAO.saveGestion(gestion); 
				
			if(ordenTrabajoDAO.existeOTAntesEnviar(ordenTrabajo.getId()) == 1){
				ordenTrabajo.setMotivoCierre("Cierre automático por enviar a remate, por asumir perdida");
				ordenTrabajoDAO.updateCerrarOT(ordenTrabajo);
				gestion.setGestion("cierra la orden de trabajo");
				gestionesDAO.saveGestion(gestion); 
			}
			Boolean enviarRemate = true;
			for(Accesorio accesorio : accesorios) {
				accesorio.setRecibidoDestino(false);
				updateAccesorioAntesEnviarOT(accesorio);
				accesorioDAO.updateEstadoAccesorioById(accesorio);
				enviarRemate = accesorio.getRecibidoCd().equals(false) ? false : enviarRemate ;
			}
			
			Cambio cambio = cambioDAO.getCambioByOT(ordenTrabajo.getId());
			if(enviarRemate && (cambio.getOt().getEmpresaFacturar().getId().equals(Constants.UBICACION_NUEVA_DE_LYON_072_PISO_6) || cambio.getOt().getEmpresaFacturar().getId().equals(Constants.UBICACION_DIN_GRANDES_TIENDAS) )){
				ordenTrabajo.setEnviarRemate(true);
				ordenTrabajoDAO.updateEnviarRemateByIdOT(ordenTrabajo);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void autorizarCambioGarantiaMaster(OrdenTrabajo ordenTrabajo, Ubicacion ubicacion, Usuario usuario) throws Exception{
		try {
			if(ordenTrabajoDAO.getTotalNumeroCambio(ordenTrabajo.getNumeroCambio()) > 0){
				throw new SSTException("El número de autorización de cambio ya ha sido utilizado en una orden de trabajo, autorice el cambio con otro número de cambio");
			}
			
			OrdenTrabajo ot = ordenTrabajoDAO.getOTById(ordenTrabajo.getId());
			ot.setNumeroCambio(ordenTrabajo.getNumeroCambio());
			ot.setTipoFacturar(Constants.FACTURAR_TIPO_EMPRESA);
			TipoCambio tipoCambio = new TipoCambio();
			tipoCambio.setCodigo(Constants.CAMBIO_EJECUTIVA_MARCA);
			ot.setTipoCambio(tipoCambio);
			Empresa empresaFacturar = new Empresa();
			empresaFacturar.setId(Constants.UBICACION_NUEVA_DE_LYON_072_PISO_6);
			ot.setEmpresaFacturar(empresaFacturar);
			ot.setMotivoCambio("Cambio autorizado por ejecutivo de garantía master"); 
			if(ot.getFechaCambio() == null){
				ot.setFechaCambio(utilDAO.getDate());
			}
			ot.setCambioAutorizado(true);
			ot.setTareaUrgente(false);
			ordenTrabajoDAO.updateCambioGMaster(ot);
			
			Gestion gestion = new Gestion();
			gestion.setOt(ot);
			gestion.setUsuario(usuario); 
			gestion.setGestion("Autoriza el cambio del producto al cliente");
			gestionesDAO.saveGestion(gestion);
			this.iniciarBitacoraCambio(ot);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void iniciarBitacoraCambio(OrdenTrabajo ot) throws Exception{
		try {
			
			Bitacora bitacora = bitacoraDAO.getBitacoraUltimaUbicacionOT(ot.getId());
			Ubicacion ubicacion = ubicacionDAO.get(bitacora.getUbicacion().getId());
			bitacora.setUbicacion(ubicacion);
			
			if(ot.getTipoFacturar().equals(Constants.FACTURAR_TIPO_TIENDA) || ot.getTipoFacturar().equals(Constants.FACTURAR_TIPO_EMPRESA) ){
				if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_SUCURSAL)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CAMION_A_CD_PARA_REMATE));
				} else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_BODEGA_REGIONAL_LUEGO_A_CAMION_A_CD));
				} else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)){
					Guia guia = guiaDAO.get(bitacora.getGuia().getId());
					guia.setVigente(false);
					guiaDAO.updateEstado(guia);
					
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRO_DE_DISTRIBUCION_LUEGO_SE_ENVIA_A_REMATE));
				} else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)){
					Ubicacion ultimaUbicacionInterna = ubicacionDAO.getUltimaUbicacionInterna(ot.getId());
					if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_SUCURSAL)){
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SUCURSAL));
					} else if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_BR));
					} else if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)){
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_CD));
					}  
				} else if(bitacora.getUbicacion() == null && bitacora.getUbicacion().getId().equals(Constants.UBICACION_ID_CLIENTE)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CLIENTE_LUEGO_ENTREGA_PRODUCTO_A_SC));
				} 
			} else if(ot.getTipoFacturar().equals(Constants.FACTURAR_TIPO_PROVEEDOR)){
				if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_SUCURSAL)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SUCURSAL_LUEGO_A_CAMION_A_CD_PARA_PROVEEDOR));
				} else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_BODEGA_REGIONALLUEGO_A_CAMION_A_CD_PARA_PROVEEDOR));
				} else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CENTRO_DE_DISTRIBUCION_LUEGO_SE_ENVIA_A_PROVEEDOR));
				}else if(bitacora.getUbicacion().getTipo().equals(Constants.UBICACION_SERVICIO_TECNICO)){
					Ubicacion ultimaUbicacionInterna = ubicacionDAO.getUltimaUbicacionInterna(ot.getId());
					if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_SUCURSAL)){
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_SUCURSAL));
					} else if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL)){
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_BR));
					} else if(ultimaUbicacionInterna.getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION)){
						//DEJAR GUIA NO VIGENTE
						bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_SERVICIO_TECNICO_LUEGO_A_CAMION_A_CD));
					}  
				} else if(bitacora.getUbicacion().getId().equals(Constants.UBICACION_ID_CLIENTE)){
					bitacora.setEstado(estadoDAO.getEstadoById(Constants.BITACORA_EN_CLIENTE_LUEGO_ENTREGA_PRODUCTO_A_SC));
				}
			}
			bitacoraDAO.updateEstadoBitacora(bitacora);
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public void updateAccesorioAntesEnviarOT(Accesorio accesorio) throws Exception{
		try{ 
			if (accesorio.getRecibidoDestino() != null && accesorio.getRecibidoDestino()){
				accesorio.setUbicacion(accesorio.getUbicacion());
			}  
			if (accesorio.getRecibidoCd() != null && accesorio.getRecibidoCd()){
				accesorio.setUbicacion(ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA));
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer existeOTAntesEnviar(Long idOT) throws Exception {
		try {
			return ordenTrabajoDAO.existeOTAntesEnviar(idOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
    public Usuario moverOrden(List<OrdenTrabajo> listOrdenes, Usuario usuario) throws Exception {
		try {
			Usuario user = new Usuario();
			Persona ejecutiva = new Persona();
			user = usuarioDAO.get(usuario.getId()); 
			for (OrdenTrabajo ordenTrabajo : listOrdenes) {
				ejecutiva.setId(usuario.getId());
				ordenTrabajo.setEjecutiva(ejecutiva);
				ordenTrabajoDAO.updateOrdenTrabajoMoveToHoja(ordenTrabajo); 
				Gestion gestion = new Gestion();
				gestion.setUsuario(usuario);
				gestion.setOt(ordenTrabajo);
				if(ordenTrabajo.getEjecutiva().getId() != null && (ordenTrabajo.getEjecutiva().getId() == usuario.getId())){
					gestion.setGestion("movio la orden de trabajo a su hoja de trabajo");
				} else {
					gestion.setGestion("asigno la orden de trabajo a " + user.getNombreCompleto());
				}		
				gestionesDAO.saveGestion(gestion);
			}
			return user;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
    
    public Cliente getClienteByFilter(Documento documento) throws Exception {
    	try {
			return clienteDAO.getClienteByFilter(documento);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
    }
	
    public Cliente getClienteByRut(Cliente cliente) throws Exception {
    	try {
			return clienteDAO.getClienteByRut(cliente);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
    }
    
    public OrdenTrabajo getOtByIdOrNumeroAtencion(FilterOT filter) throws Exception{
		try {
			return ordenTrabajoDAO.getOtByIdOrNumeroAtencion(filter);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
    
    public Factura getFacturaById(Integer id) throws Exception{
		try {
			return facturaGestionDAO.getFacturaById(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
    
    public Factura getPasosFacturaById(Integer id) throws Exception{
		try {
			return facturaGestionDAO.getPasosFacturaById(id);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
    
    public ListRange listarDetalleFactura(FilterFactura filterFactura) throws Exception{
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(facturaGestionDAO.listarDetalleFactura(filterFactura));
			listRange.setTotal(facturaGestionDAO.getTotalDetalleFactura(filterFactura));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
    
	public void setIndicadorGestionDAO(IndicadorGestionDAO indicadorGestionDAO) {
		this.indicadorGestionDAO = indicadorGestionDAO;
	} 
	
	public void setSemaforoDAO(SemaforoDAO semaforoDAO) {
		this.semaforoDAO = semaforoDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
	
	public void setIndicadorMarcaDAO(IndicadorMarcaDAO indicadorMarcaDAO) {
		this.indicadorMarcaDAO = indicadorMarcaDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

//	public void setDiasTramosDAO(DiasTramosDAO diasTramosDAO) {
//		this.diasTramosDAO = diasTramosDAO;
//	}

	public void setIndicadorDAO(IndicadorDAO indicadorDAO) {
		this.indicadorDAO = indicadorDAO;
	}

	public void setIndicadorFacturaDAO(IndicadorFacturaDAO indicadorFacturaDAO) {
		this.indicadorFacturaDAO = indicadorFacturaDAO;
	}

	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) {
		this.bitacoraDAO = bitacoraDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}
	
	public void setGestionesDAO(GestionesDAO gestionesDAO)
	{
		this.gestionesDAO = gestionesDAO;
	}
	
	public void setFacturaGestionDAO(FacturaGestionDAO facturaGestionDAO) {
		this.facturaGestionDAO = facturaGestionDAO;
	}
	
	public void setAccesorioDAO(AccesorioDAO accesorioDAO) {
		this.accesorioDAO = accesorioDAO;
	}
	
	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}
	
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO){
		this.proveedorDAO = proveedorDAO;
	}
	
	public void setParametroDAO(ParametroDAO parametroDAO){
		this.parametroDAO = parametroDAO;
	}
	
	
	public void setTransportistaDAO(TransportistaDAO transportistaDAO){
		this.transportistaDAO = transportistaDAO;
	}
	
	public void setCambioDAO(CambioDAO cambioDAO){
		this.cambioDAO = cambioDAO;
	}
	
	public void setRecepcionDAO(RecepcionDAO recepcionDAO){
		this.recepcionDAO = recepcionDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO) {
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}

	public void setDiasTramosDAO(DiasTramosDAO diasTramosDAO) {
		this.diasTramosDAO = diasTramosDAO;
	}
	
	public void setOWService(OWService oWService) {
		this.oWService = oWService;
	}

	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}
}
