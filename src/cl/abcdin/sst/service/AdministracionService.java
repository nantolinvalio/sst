package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.ComunaDAO;
import cl.abcdin.sst.dao.DestinoDAO;
import cl.abcdin.sst.dao.FamiliaDAO;
import cl.abcdin.sst.dao.LineaDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParametroDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProcedimientoDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.ProvinciaDAO;
import cl.abcdin.sst.dao.ReglaComercialDAO;
import cl.abcdin.sst.dao.RolDAO;
import cl.abcdin.sst.dao.RutaServicioTecnicoDAO;
import cl.abcdin.sst.dao.RutaServicioTecnicoDetalleDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.ServicioTecnicoEjecutivaDAO;
import cl.abcdin.sst.dao.SucursalDAO;
import cl.abcdin.sst.dao.TipoCambioDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.dao.UbicacionInternaDetalleDAO;
import cl.abcdin.sst.dao.UsuarioDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.dao.ZonaDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Destino;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.FamiliaExcluidaControlCalidad;
import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Procedimiento;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.ProductoExcluido;
import cl.abcdin.sst.model.Provincia;
import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.ReglaEntidad;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.RutaServicioTecnico;
import cl.abcdin.sst.model.RutaServicioTecnicoDetalle;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.TiendaZona;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.UbicacionInternaDetalle;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.Zona;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterDestino;
import cl.abcdin.sst.model.filters.FilterEjecutiva;
import cl.abcdin.sst.model.filters.FilterFamilia;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterParte;
import cl.abcdin.sst.model.filters.FilterProcedimiento;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.FilterReglaHistorica;
import cl.abcdin.sst.model.filters.FilterRutaServicioTecnico;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.FilterTipoFallas;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.FilterUbicacionInterna;
import cl.abcdin.sst.model.filters.FilterUsuario;
import cl.abcdin.sst.model.filters.FilterZona;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.CondicionRecepcion;
import cl.abcdin.sst.model.vo.FamiliaExcluida;
import cl.abcdin.sst.model.vo.Modulo;
import cl.abcdin.sst.model.vo.STecnicoEjecutiva;
import cl.abcdin.sst.model.vo.ServicioTecnicoProducto;
import cl.abcdin.sst.model.vo.TreeView;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.Utils;

public class AdministracionService {
	private static final Log log = LogFactory.getLog(AdministracionService.class);

	private RolDAO rolDAO;
	private ZonaDAO zonaDAO;
	private ProductoDAO productoDAO;
	private FamiliaDAO familiaDAO;
	private LineaDAO lineaDAO;
	private ReglaComercialDAO reglaComercialDAO;
	private ParametroDAO parametroDAO;
	private UbicacionDAO ubicacionDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private DestinoDAO destinoDAO;
	private TipoCambioDAO tipoCambioDAO;
	private UsuarioDAO usuarioDAO;
	private UtilDAO utilDAO;
	private RutaServicioTecnicoDAO rutaServicioTecnicoDAO;
    private ProvinciaDAO provinciaDAO;
    private RutaServicioTecnicoDetalleDAO rutaServicioTecnicoDetalleDAO;
	private TipoFallasDAO tipoFallasDAO;
	private AccesorioDAO accesorioDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private ParteDAO parteDAO;
	private ServicioTecnicoEjecutivaDAO servicioTecnicoEjecutivaDAO;
	private ProcedimientoDAO procedimientoDAO;
	private UbicacionInternaDAO ubicacionInternaDAO;
	private SucursalDAO sucursalDAO;
	private UbicacionInternaDetalleDAO ubicacionInternaDetalleDAO;
	private ComunaDAO comunaDAO;
	
	public Boolean isProductoOnUbicacionInternaCD(Integer idProducto, String codigoUbicacion) throws Exception{
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigoUbicacion);
			FilterProducto filterProducto = new FilterProducto();
			if(ubicacionInterna!=null && ubicacionInterna.getId()!=null){
				filterProducto.setIdUbicacionInterna(ubicacionInterna.getId());
				filterProducto.setIdProducto(idProducto.longValue());
				if(productoDAO.isProductoInUbicacionInternaCD(filterProducto)>0){
					return true;
				} else {
					return false;
				}
			} else {
				throw new SSTException("La ubicación de código "+codigoUbicacion+" no existe");
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public UbicacionInternaCD getUbicacionInternaCDByCodigo(String codigo)  throws Exception {
		try {
			return ubicacionInternaDAO.getUbicacionInternaCDByCodigo(codigo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Accesorio getAccesorioById(Long id) throws Exception {
		try {
			return accesorioDAO.get(id);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void updateAccesoriosByEstado(Accesorio accesorio) throws Exception {
		try {
			accesorioDAO.updateAccesoriosByEstado(accesorio);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public void saveAccesorio(Accesorio accesorio) throws Exception {
		try {
			if (accesorio.getId() == null) {
				accesorioDAO.save(accesorio);
			} else {
				accesorioDAO.update(accesorio);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
		
	public void saveAccesoriosForTipoFalla(TipoFallas tipoFallas, List<Accesorio> accesorios) throws Exception {
		try {
			tipoFallasDAO.deleteFallasAccesorios(tipoFallas);
			for (Accesorio accesorio : accesorios) {
				tipoFallasDAO.saveAccesoriosForTipoFallas(tipoFallas, accesorio);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
		
	public void saveTipoFallaForAccesorios(Accesorio accesorio, List<TipoFallas> tipoFallas) throws Exception {
		try {
			tipoFallasDAO.deleteAccesoriosFallas(accesorio);
			for (TipoFallas tipoFalla : tipoFallas) {
				tipoFallasDAO.saveAccesoriosForTipoFallas(tipoFalla, accesorio);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Ubicacion saveUbicacion(Ubicacion ubicacion, Boolean nuevo) throws Exception {
		try {
			if (nuevo) {
				this.saveUbicacion(ubicacion);
			} else {
				ubicacionDAO.updateUbicacion(ubicacion);
			}
			return ubicacion;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listAccesoriosByFilterForAdministracion(FilterAccesorio filter, GridControl gridControl) throws Exception {
		try {
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(accesorioDAO.listAccesoriosByFilter(filter, gridControl));
			listRange.setTotal(accesorioDAO.getTotalAccesoriosByFilter(filter));
			return listRange;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Procedimiento getProcedimientoById(Long id) throws Exception {
		try {
			return procedimientoDAO.get(id);
		}  catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	public Procedimiento getProcedimientoByIdProducto(Integer idProducto) throws Exception {
		try {
			Producto producto = productoDAO.getProductoById(idProducto);
			
			if (producto == null) {
				throw new SSTException("El producto " + idProducto + "no existe en el sistema");
			}
			
			FilterProcedimiento filterProcedimiento = new FilterProcedimiento();
			filterProcedimiento.setProducto(producto.getId());
			filterProcedimiento.setVigente(true);
			
			List<Procedimiento> procedimientos = procedimientoDAO.listProcedimientosProductosByFilter(filterProcedimiento);
			
			if (procedimientos.size() == 0) {
				filterProcedimiento.setProducto(null);
				filterProcedimiento.setMarca(producto.getMarca().getCodigo());
				filterProcedimiento.setLinea(producto.getFamilia().getLinea().getCodigo());
				procedimientos = procedimientoDAO.listProcedimientosProductosByFilter(filterProcedimiento);
			}
			
			if (procedimientos.size() > 1) {
				throw new SSTException("Existe mas de un procedimiento vigente para el producto " + producto.getId() + " " + producto.getDescripcion());
			} else if (procedimientos.size() == 1) {
				return procedimientos.get(0);				
			} else {
				return null;
			}
			

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Usuario saveUsuario(Usuario usuario, Boolean nuevo) throws Exception {
		try {
			usuario.setId(Utils.getIdFromRun(usuario.getRut()));
			usuario.setCookie(Utils.getMD5(usuario.getRut()).toUpperCase());
			usuario.setVigente(true);
			Usuario user = usuarioDAO.get(usuario.getId());

			if (nuevo) {
				if (user != null) {
					throw new SSTException(
							"El usuario que esta intentando grabar ya existe en la base de datos: "
									+ user.getNombreCompleto());
				}
				usuarioDAO.save(usuario);
			} else {
				if (user == null) {
					throw new SSTException(
							"No hay usuarios registrados con el rut: "
									+ usuario.getRut());
				}
				usuarioDAO.update(usuario);
			}

			ubicacionDAO.deleteUbicacionesUsuarios(usuario);
			rolDAO.deleteRolesUsuarios(usuario);

			for (Ubicacion ubicacion : usuario.getUbicaciones()) {
				ubicacionDAO.saveUbicacionUsuario(ubicacion, usuario);
			}

			for (Rol rol : usuario.getRoles()) {
				rolDAO.saveRolUsuario(rol, usuario);
			}

			return usuario;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Rol updateRol(Rol rol, List<Modulo> modulos) throws Exception {
		try {
			rolDAO.updateNombre(rol);

			utilDAO.deleteModuloByRol(rol.getId());

			for (Modulo modulo : modulos) {
				utilDAO.saveRolModulo(rol.getId(), modulo.getId());
			}
			return rol;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Modulo saveConfiguracionPestana(Rol rol, Modulo modulo,List<Modulo> subModulos) throws Exception {
		try {
			Integer orden = 0;
			utilDAO.deleteSubModulosByRolAndModulo(rol, modulo);

			for (Modulo subModulo : subModulos) {
				utilDAO.saveRolModuloSubModulo(rol, modulo, subModulo, orden++);
			}
			return modulo;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Rol> listRolesByUsuario(Usuario usuario) throws Exception {
		try {
			if(usuario == null){
				throw new SSTException("El usuario no esta configurado en el sistema");
			} else {
				List<Rol> roles = rolDAO.listRolesByUsuario(usuario);
				if(roles.size() == 0){
					throw new SSTException("El usuario "+usuario.getNombre()+" no tiene roles configurados en el sistema");
				}
				return roles;
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listRoles(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();

			listRange.setRows(rolDAO.listRoles(gridControl));
			listRange.setTotal(rolDAO.getTotalRoles());
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateEstadoRol(Rol rol) throws Exception {
		try {
			rolDAO.updateEstado(rol);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Rol saveRol(Rol rol) throws Exception {
		try {
			Rol rolActual = rolDAO.getRolByNombre(rol.getNombre());
			if (rolActual != null) {
				throw new SSTException(
						"Ya existe en el sistema un perfil de usuario con este nombre");
			}
			rol.setVigente(true);
			rolDAO.save(rol);
			return rol;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Rol getRolById(Long id) throws Exception {
		try {
			return rolDAO.getRolById(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Rol> listRolesActivos() throws Exception {
		try {
			return rolDAO.listRolesActivos();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
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

	public Zona getZonaById(Long id) throws Exception {
		try {
			return zonaDAO.get(id);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listZonas(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(zonaDAO.list(gridControl));
			listRange.setTotal(zonaDAO.getTotal());
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Zona> listAllZonas() throws Exception {
		try {
			return zonaDAO.listAll();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TreeView> listZonasAsTreeView() throws Exception {
		try {
			return zonaDAO.listAllAsTreeView();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private List<TreeView> listZonasAsTreeViewByFilter(FilterRegla filter)
			throws Exception {
		List<TreeView> zonas = zonaDAO.listZonasAsTreeViewByFilter(filter);
		for (TreeView zona : zonas) {
			if (zona.getChecked() == null) {
				filter.setIdZona(Long.parseLong(zona.getId()));
				Integer cantTiendas = zonaDAO
						.getCountSucursalesByFilter(filter);
				if (cantTiendas > 0) {
					zona.setChildren(zonaDAO
							.listSucursalesByFilterAsTreeView(filter));
				}
			}
		}
		return zonas;
	}

	private List<TreeView> listZonasHistoricasAsTreeViewByFilter(
			FilterRegla filter) throws Exception {
		List<TreeView> zonas = zonaDAO
				.listZonasHistoricasAsTreeViewByFilter(filter);
		for (TreeView zona : zonas) {
			if (zona.getChecked() == null) {
				filter.setIdZona(Long.parseLong(zona.getId()));
				Integer cantTiendas = zonaDAO
						.getCountSucursalesHistoricasByFilter(filter);
				if (cantTiendas > 0) {
					zona.setChildren(zonaDAO
							.listSucursalesHistoricasByFilterAsTreeView(filter));
				}
			}
		}
		return zonas;
	}

	private List<TreeView> listLineasAsTreeViewByFilter(FilterRegla filter)
			throws Exception {
		List<TreeView> lineas = lineaDAO.listLineasAsTreeViewByFilter(filter);
		for (TreeView linea : lineas) {
			if (linea.getChecked() == null) {
				filter.setIdLinea(linea.getId());
				Integer cantFamilias = familiaDAO
						.getCountFamiliasByFilter(filter);
				Integer cantProductosByLinea = productoDAO
						.getCountProductosLineaByFilter(filter);
				if (cantFamilias > 0 || cantProductosByLinea > 0) {
					linea.setChildren(familiaDAO
							.listByFilterReglaAsTreeView(filter));
					for (TreeView familia : linea.getChildren()) {
						if (familia.getChecked() == null) {
							filter.setIdFamilia(familia.getId());
							Integer cantProductos = productoDAO
									.getCountProductosFamiliaByFilter(filter);
							if (cantProductos > 0) {
								familia.setChildren(productoDAO
										.listByFilterReglaAsTreeView(filter));
							}
						}
					}
				}
			}
		}
		return lineas;
	}

	private List<TreeView> listLineasHistoricasAsTreeViewByFilter(
			FilterRegla filter) throws Exception {
		List<TreeView> lineas = lineaDAO
				.listLineasHistoricasAsTreeViewByFilter(filter);
		for (TreeView linea : lineas) {
			if (linea.getChecked() == null) {
				filter.setIdLinea(linea.getId());
				Integer cantFamilias = familiaDAO
						.getCountFamiliasHistoricasByFilter(filter);
				Integer cantProductosByLinea = productoDAO
						.getCountProductosHistoricosLineaByFilter(filter);
				if (cantFamilias > 0 || cantProductosByLinea > 0) {
					linea.setChildren(familiaDAO
							.listHistoricasByFilterReglaAsTreeView(filter));
					for (TreeView familia : linea.getChildren()) {
						if (familia.getChecked() == null) {
							filter.setIdFamilia(familia.getId());
							Integer cantProductos = productoDAO
									.getCountProductosHistoricosFamiliaByFilter(filter);
							if (cantProductos > 0) {
								familia.setChildren(productoDAO
										.listHistoricosByFilterReglaAsTreeView(filter));
							}
						}
					}
				}
			}
		}
		return lineas;
	}

	public List<TreeView> listSucursalesByZonaAsTreeView(Long idZona)
			throws Exception {
		try {
			return zonaDAO.listSucursalesByZonaAsTreeView(idZona);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TreeView> listLineaAsTreeView() throws Exception {
		try {
			return lineaDAO.listAllAsTreeView();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Linea> listAllLineas() throws Exception {
		try {
			return lineaDAO.listAll();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Familia> listFamiliasByFilter(FilterProducto filter)
			throws Exception {
		try {
			return familiaDAO.listByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Producto> listProductosByFilter(FilterProducto filter)
			throws Exception {
		try {
			return productoDAO.listByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listProductosByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterProducto.setOrderBy(gridControl.getOrderBy());
			filterProducto.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(productoDAO.listProductosByFilter(filterProducto, gridControl));
			listRange.setTotal(productoDAO.getTotalProductosByFilter(filterProducto));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listProcedimientosProductosByFilter(FilterProcedimiento filter, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(procedimientoDAO.listProcedimientosProductosByFilter(filter, gridControl));
			listRange.setTotal(procedimientoDAO.getTotalProcedimientosProductosByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Integer updateEstadoProcedimiento(Procedimiento procedimiento, Usuario usuario)throws Exception {
		try {
			procedimiento.setUsuarioModificacion(usuario);
			return procedimientoDAO.updateEstadoProcedimiento(procedimiento);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Integer updateProcedimientoProducto(Procedimiento procedimiento)throws Exception {
		try {
			return procedimientoDAO.updateProcedimientoProducto(procedimiento);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
//   modelos
	public ListRange listProductosByServicioTecnico(FilterProducto filter, GridControl gridControl) throws Exception {
		try {
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(productoDAO.listProductosByServicioTecnico(filter, gridControl));
			listRange.setTotal(productoDAO.getTotalSTecnicoP(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllProductosServicioTecnicoCheck(FilterProducto filter) throws Exception {
		try {
			return  productoDAO.listAllProductosServicioTecnicoCheck(filter);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllProductosByFilterCheck(FilterProducto filterProducto) throws Exception {
		try {
			return productoDAO.listAllProductosByFilterCheck(filterProducto);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllDestinosUbicacionCheck(FilterUbicacion filterUbicacion) throws Exception {
		try {
			return ubicacionDAO.listAllDestinosUbicacionCheck(filterUbicacion);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/*
	 * public ListRange listProductosAsignar (FilterServicioTecnico
	 * filterServicioTecnico, GridControl gridControl) throws Exception{ try{
	 * filterServicioTecnico.setSortOrder(gridControl.getSortOrder());
	 * filterServicioTecnico.setOrderBy(gridControl.getOrderBy()); ListRange
	 * listRange = new ListRange();
	 * listRange.setRows(servicioTecnicoDAO.listServicioTecnico
	 * (filterServicioTecnico, gridControl));
	 * listRange.setTotal(servicioTecnicoDAO
	 * .getTotalServiciosTecnicos(filterServicioTecnico)); return listRange;
	 * }catch (Exception e){ log.error(e,e); throw e; } }
	 */

	/* nuevo list para productos fin */

	public List<Usuario> listUsuariosReglasComerciales() throws Exception {
		try {
			return reglaComercialDAO.listUsuariosReglasComerciales();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateEstadoUsuario(Usuario usuario) throws Exception {
		try {
			usuarioDAO.updateEstadoUsuario(usuario);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateVigenciaServTecnico(
			ServicioTecnicoProducto servicioTecnicoProducto) throws Exception {
		try {
			servicioTecnicoDAO.updateVigencia(servicioTecnicoProducto);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void getIdComunaByIdUbicacion(Long idc) throws Exception {
		try {
			ubicacionDAO.getIdComunaByIdUbicacion(idc);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	

	public void updateServicioTecByubicacion(ServicioTecnico servicioTecnico)
			throws Exception {
		try {
			servicioTecnicoDAO.updateVigenciaServTec(servicioTecnico);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void updateServicioTecnico(ServicioTecnico servicioTecnico)
			throws Exception {
		try {
			servicioTecnicoDAO.updateAllServicioTecnico(servicioTecnico);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TreeView> listFamiliaByFilterAsTreeView(FilterProducto filter)
			throws Exception {
		try {
			return familiaDAO.listByFilterAsTreeView(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TreeView> listProductoByFilterAsTreeView(FilterProducto filter)
			throws Exception {
		try {
			return productoDAO.listByFilterAsTreeView(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveZona(Zona zona, Usuario usuario) throws Exception {
		try {
			if (zona.getId() == null || zona.getId() == 0) {
				zona.setUsuarioCreacion(usuario);
				zonaDAO.save(zona);
			} else {
				zona.setUsuarioModificacion(usuario);
				zonaDAO.update(zona);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<Ubicacion> listSucursalesNotInZonaByCodigoZona(String codigo)
			throws Exception {
		try {
			Zona zona = zonaDAO.getByCodigo(codigo);
			if (zona == null)
				throw new SSTException("La zona no existe");
			return zonaDAO.listSucursalesNotInZonaByZona(zona.getId());
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
//	public List<Columna> listColumnasIndicadores(Integer id, Integer idRol)
//			throws Exception {
//		try {
//			FilterColumna columna = new FilterColumna();
//			columna.setIdIndicador(id);
//			columna.setIdRol(idRol);
//			return columnaDAO.listColumnasIndicadores(columna);
//		} catch (Exception e) {
//			log.error(e, e);
//			throw e;
//		}
//	}

	public ListRange listSucursalesZonaByFilter(FilterZona filter, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(zonaDAO.listSucursalesByFilter(filter, gridControl));
			listRange.setTotal(zonaDAO.getTotalSucursalesByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listOTDescripcionFallas(FilterOT filterOT, GridControl gridControl) throws Exception {
		try {
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			ListRange listRange = new ListRange();
			listRange.setRows(ordenTrabajoDAO.listOTDescripcionFallas(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTDescripcionFallas(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	

	public List<Ubicacion> listSucursalesZonaByFilter(FilterZona filter)
			throws Exception {
		try {
			return zonaDAO.listSucursalesByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TiendaZona> listSucursalesByCodigoZona(String codigo)
			throws Exception {
		try {
			Zona zona = zonaDAO.getByCodigo(codigo);
			if (zona == null)
				throw new SSTException("La zona no existe");
			return zonaDAO.listSucursalesByZona(zona.getId());
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveSucursalesZona(List<Ubicacion> sucursales, Zona zona,
			Usuario usuario) throws Exception {
		try {
			zona = zonaDAO.getByCodigo(zona.getCodigo());
			if (zona == null)
				throw new SSTException("La zona no existe");

			zonaDAO.deleteSucursalZonaByZona(zona);
			for (Ubicacion sucursal : sucursales) {
				zonaDAO.deleteSucursalZonaByUbicacion(sucursal);
				zonaDAO.saveSucursalZona(sucursal, zona, usuario);
			}

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private <T> void saveReglaEntidad(ReglaComercial reglaComercial, String entidad, T id) throws Exception {
		ReglaEntidad<T> reglaEntidad = new ReglaEntidad<T>();
		reglaEntidad.setReglaComercial(reglaComercial);
		reglaEntidad.setEntidadRegla(entidad);
		reglaEntidad.setIdEntidad(id);
		reglaComercialDAO.saveReglaEntidad(reglaEntidad);
		reglaComercialDAO.saveReglaEntidadHistorial(reglaEntidad);
	}

	private void saveReglasZonas(ReglaComercial reglaComercial, List<TreeView> zonas) throws Exception {
		for (TreeView zona : zonas) {
			if (zona.getChecked().equals("undetermined")
					&& zona.getChildren() != null
					&& zona.getChildren().size() > 0) {
				saveReglasTiendas(reglaComercial, zona.getChildren());
			} else if (Boolean.parseBoolean(zona.getChecked())) {
				this.saveReglaEntidad(reglaComercial,
						Constants.ENTIDAD_TIPO_ZONA, zona.getMetadata().getId());
			}
		}
	}

	private void saveReglasTiendas(ReglaComercial reglaComercial, List<TreeView> tiendas) throws Exception {
		for (TreeView tienda : tiendas) {
			if (Boolean.parseBoolean(tienda.getChecked())) {
				this.saveReglaEntidad(reglaComercial,
						Constants.ENTIDAD_TIPO_TIENDA, tienda.getMetadata()
								.getId());
			}
		}
	}

	private void saveReglasLineas(ReglaComercial reglaComercial, List<TreeView> lineas) throws Exception {
		for (TreeView linea : lineas) {
			if (linea.getChecked().equals("undetermined")
					&& linea.getChildren() != null
					&& linea.getChildren().size() > 0) {
				saveReglasFamilias(reglaComercial, linea.getChildren());
			} else if (Boolean.parseBoolean(linea.getChecked())) {
				this.saveReglaEntidad(reglaComercial,
						Constants.ENTIDAD_TIPO_LINEA, linea.getMetadata()
								.getId());
			}
		}
	}

	private void saveReglasFamilias(ReglaComercial reglaComercial, List<TreeView> familias) throws Exception {
		for (TreeView familia : familias) {
			if (familia.getChecked().equals("undetermined")
					&& familia.getChildren() != null
					&& familia.getChildren().size() > 0) {
				saveReglasProductos(reglaComercial, familia.getChildren());
			} else if (Boolean.parseBoolean(familia.getChecked())) {
				this.saveReglaEntidad(reglaComercial,
						Constants.ENTIDAD_TIPO_FAMILIA, familia.getMetadata()
								.getId());
			}
		}
	}

	private void saveReglasProductos(ReglaComercial reglaComercial, List<TreeView> productos) throws Exception {
		for (TreeView producto : productos) {
			if (Boolean.parseBoolean(producto.getChecked())) {
				this.saveReglaEntidad(reglaComercial,
						Constants.ENTIDAD_TIPO_PRODUCTO, producto.getMetadata()
								.getId());
			}
		}
	}

	private void validateReglaComercialNueva(ReglaComercial reglaComercial)
			throws Exception {
		if ((reglaComercial.getZonas() != null && reglaComercial.getZonas().size() > 0)
				|| (reglaComercial.getLineas() != null && reglaComercial.getLineas().size() > 0)) {
			validateReglaComercialEspecifica(reglaComercial);
		} else {
			validateReglaComercialGeneral(reglaComercial);
		}
	}

	private void validateReglaComercialGeneral(ReglaComercial reglaComercial) throws Exception {
		FilterRegla filter = new FilterRegla();
		filter.setFechaInicio(reglaComercial.getFechaInicio());
		filter.setFechaTermino(reglaComercial.getFechaTermino());
		filter.setIdRegla(reglaComercial.getId());
		ReglaComercial rc = reglaComercialDAO.getReglaComercialVigenteByFilter(filter);
		if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
			throw new SSTException(
					"Ya existe a lo menos una regla general vigente en este periodo. ID: "+ rc.getId() + ". Nombre: " + rc.getNombre());
		}
	}

	private void validateReglaComercialEspecifica(ReglaComercial reglaComercial)
			throws Exception {
		if (reglaComercial.getZonas() != null && reglaComercial.getZonas().size() > 0) {
			validateReglaComercialZona(reglaComercial, reglaComercial.getZonas());
		}
		if (reglaComercial.getLineas() != null
				&& reglaComercial.getLineas().size() > 0) {
			validateReglaComercialLineas(reglaComercial,
					reglaComercial.getLineas());
		}
	}

	private void validateReglaComercialLineas(ReglaComercial reglaComercial, List<TreeView> lineas) throws Exception {
		for (TreeView linea : lineas) {
			if (linea.getChecked().equals("undetermined")
					&& linea.getChildren() != null
					&& linea.getChildren().size() > 0) {
				validateReglaComercialFamilias(reglaComercial, linea.getChildren());
			} else if (Boolean.parseBoolean(linea.getChecked())) {
				FilterRegla filter = new FilterRegla();
				filter.setFechaInicio(reglaComercial.getFechaInicio());
				filter.setFechaTermino(reglaComercial.getFechaTermino());
				filter.setIdLinea(linea.getMetadata().getId());
				filter.setIdRegla(reglaComercial.getId());
				if (reglaComercial.getReglaCambioProoveedor()
						.getAutorizadoProveedor() == true)
					filter.setIdTipoAutorizacion(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO);
				ReglaComercial rc = reglaComercialDAO
						.getReglaComercialVigenteByFilter(filter);
				if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
					throw new SSTException(
							"Ya existe a lo menos una regla general vigente en este periodo. ID: "
									+ rc.getId() + ". Nombre: "
									+ rc.getNombre());
				}
			}
		}
	}

	private void validateReglaComercialFamilias(ReglaComercial reglaComercial, List<TreeView> familias) throws Exception {
		for (TreeView familia : familias) {
			if (familia.getChecked().equals("undetermined")
					&& familia.getChildren() != null
					&& familia.getChildren().size() > 0) {
				validateReglaComercialProductos(reglaComercial, familia.getChildren());
			} else if (Boolean.parseBoolean(familia.getChecked())) {
				FilterRegla filter = new FilterRegla();
				filter.setFechaInicio(reglaComercial.getFechaInicio());
				filter.setFechaTermino(reglaComercial.getFechaTermino());
				filter.setIdFamilia(familia.getMetadata().getId());
				filter.setIdRegla(reglaComercial.getId());
				if (reglaComercial.getReglaCambioProoveedor().getAutorizadoProveedor() == true)
					filter.setIdTipoAutorizacion(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO);
				ReglaComercial rc = reglaComercialDAO.getReglaComercialVigenteByFilter(filter);
				if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
					throw new SSTException(
							"Ya existe a lo menos una regla general vigente en este periodo. ID: "
									+ rc.getId() + ". Nombre: "
									+ rc.getNombre());
				}
			}
		}
	}

	private void validateReglaComercialProductos(ReglaComercial reglaComercial,
			List<TreeView> productos) throws Exception {
		for (TreeView producto : productos) {
			if (Boolean.parseBoolean(producto.getChecked())) {
				FilterRegla filter = new FilterRegla();
				filter.setFechaInicio(reglaComercial.getFechaInicio());
				filter.setFechaTermino(reglaComercial.getFechaTermino());
				filter.setIdProducto(Long.parseLong(producto.getMetadata()
						.getId()));
				filter.setIdRegla(reglaComercial.getId());
				if (reglaComercial.getReglaCambioProoveedor()
						.getAutorizadoProveedor() == true)
					filter.setIdTipoAutorizacion(Constants.CAMBIO_AUTOMATICO_PROVEEDOR_PRODUCTO);
				ReglaComercial rc = reglaComercialDAO
						.getReglaComercialVigenteByFilter(filter);
				if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
					throw new SSTException(
							"Ya existe a lo menos una regla general vigente en este periodo. ID: "
									+ rc.getId() + ". Nombre: "
									+ rc.getNombre());
				}
			}
		}
	}

	private void validateReglaComercialZona(ReglaComercial reglaComercial,
			List<TreeView> zonas) throws Exception {
		for (TreeView zona : zonas) {
			if (zona.getChecked().equals("undetermined")
					&& zona.getChildren() != null
					&& zona.getChildren().size() > 0) {
				validateReglaComercialTienda(reglaComercial, zona.getChildren());
			} else if (Boolean.parseBoolean(zona.getChecked())) {
				FilterRegla filter = new FilterRegla();
				filter.setFechaInicio(reglaComercial.getFechaInicio());
				filter.setFechaTermino(reglaComercial.getFechaTermino());
				filter.setIdZona(Long.parseLong(zona.getMetadata().getId()));
				filter.setIdRegla(reglaComercial.getId());
				ReglaComercial rc = reglaComercialDAO
						.getReglaComercialVigenteByFilter(filter);
				if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
					throw new SSTException(
							"Ya existe a lo menos una regla general vigente en este periodo. ID: "
									+ rc.getId() + ". Nombre: "
									+ rc.getNombre());
				}
			}
		}
	}

	private void validateReglaComercialTienda(ReglaComercial reglaComercial,
			List<TreeView> tiendas) throws Exception {
		for (TreeView tienda : tiendas) {
			if (Boolean.parseBoolean(tienda.getChecked())) {
				FilterRegla filter = new FilterRegla();
				filter.setFechaInicio(reglaComercial.getFechaInicio());
				filter.setFechaTermino(reglaComercial.getFechaTermino());
				filter.setIdTienda(Long.parseLong(tienda.getMetadata().getId()));
				filter.setIdRegla(reglaComercial.getId());
				ReglaComercial rc = reglaComercialDAO
						.getReglaComercialVigenteByFilter(filter);
				if (rc != null && !rc.getId().equals(reglaComercial.getId())) {
					throw new SSTException(
							"Ya existe a lo menos una regla general vigente en este periodo. ID: "
									+ rc.getId() + ". Nombre: "
									+ rc.getNombre());
				}
			}
		}
	}

	public Long saveReglaComercial(ReglaComercial reglaComercial,Usuario usuario) throws Exception {
		try {
			reglaComercial.setUsuarioCreacion(usuario);
			if (reglaComercial.getVigente()) {
				validateReglaComercialNueva(reglaComercial);
			}
			if (reglaComercial.getId() == null || reglaComercial.getId() == 0) {
				reglaComercialDAO.save(reglaComercial);
			} else {
				reglaComercialDAO.update(reglaComercial);
			}
			ReglaComercial reglaHistorica = reglaComercialDAO.getById(reglaComercial.getId());
			reglaHistorica.setUsuarioCreacion(usuario);
			reglaComercialDAO.saveHistorial(reglaHistorica);
			reglaComercialDAO.deleteAllReglaEntidadByRegla(reglaHistorica);

			if (reglaComercial.getZonas() != null && reglaComercial.getZonas().size() > 0) {
				saveReglasZonas(reglaHistorica, reglaComercial.getZonas());
			}
			if (reglaComercial.getLineas() != null && reglaComercial.getLineas().size() > 0) {
				saveReglasLineas(reglaHistorica, reglaComercial.getLineas());
			}
			return reglaComercial.getId();
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listReglasComercialesByFilter(FilterRegla filter,
			GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(reglaComercialDAO.listByFilter(filter,gridControl));
			listRange.setTotal(reglaComercialDAO.getTotalByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ReglaComercial getReglaComercialById(Long id) throws Exception {
		try {
			ReglaComercial reglaComercial = reglaComercialDAO.getById(id);
			FilterRegla filter = new FilterRegla();
			filter.setIdRegla(reglaComercial.getId());
			reglaComercial.setZonas(this.listZonasAsTreeViewByFilter(filter));
			reglaComercial.setLineas(this.listLineasAsTreeViewByFilter(filter));
			return reglaComercial;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ReglaComercial getReglaComercialHisoricaById(Long id)
			throws Exception {
		try {
			ReglaComercial reglaComercial = reglaComercialDAO.getReglaComercialHisoricaById(id);
			FilterRegla filter = new FilterRegla();
			filter.setIdReglaHistorica(reglaComercial.getIdHistorico());
			reglaComercial.setZonas(this.listZonasHistoricasAsTreeViewByFilter(filter));
			reglaComercial.setLineas(this.listLineasHistoricasAsTreeViewByFilter(filter));
			return reglaComercial;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listReglasComercialesHistoricasByFilter(
			FilterReglaHistorica filter, GridControl gridControl)
			throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(reglaComercialDAO.listHistoricoByFilter(filter,
					gridControl));
			listRange.setTotal(reglaComercialDAO
					.getTotalHistoricoByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<ReglaComercial> listReglasComercialesByFilter(FilterRegla filter)
			throws Exception {
		try {
			return reglaComercialDAO.listByFilter(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public CondicionRecepcion getCondicionRecepcion() throws Exception {
		try {
			return parametroDAO.getCondicionRecepcion();
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveCondicionRecepcion(String descripcion, Usuario usuario)
			throws Exception {
		try {
			CondicionRecepcion condicion = parametroDAO.getCondicionRecepcion();
			if (condicion != null) {
				condicion.setDescripcion(descripcion);
				condicion.setUsuarioCreacion(usuario);
				parametroDAO.updateCondicionRecepcion(condicion);
			} else {
				condicion = new CondicionRecepcion();
				condicion.setDescripcion(descripcion);
				condicion.setUsuarioCreacion(usuario);
				parametroDAO.insertCondicionRecepcion(condicion);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listFamiliasExcluidasByFilter(FilterProducto filter,
			GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(familiaDAO.listFamiliasExcluidasByFilter(filter,
					gridControl));
			listRange.setTotal(familiaDAO
					.getTotalFamiliasExcluidasByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveFamiliasExcluidas(List<FamiliaExcluida> seleccionadas,
			List<FamiliaExcluida> noSeleccionadas, Usuario usuario)
			throws Exception {
		try {
			for (FamiliaExcluida familiaExcluida : noSeleccionadas) {
				familiaDAO.deleteFamiliaExcluida(familiaExcluida);
			}
			for (FamiliaExcluida familiaExcluida : seleccionadas) {
				FamiliaExcluida familia = familiaDAO
						.getFamiliaExcluidaByIdFamilia(familiaExcluida.getId());
				if (familia == null) {
					familiaExcluida.setUsuarioExclusion(usuario);
					familiaDAO.saveFamiliaExcluida(familiaExcluida);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listFamiliasExcluidasSerieByFilter(FilterProducto filter,
			GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filter.setOrderBy(gridControl.getOrderBy());
			filter.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(familiaDAO.listFamiliasExcluidasSerieByFilter(
					filter, gridControl));
			listRange.setTotal(familiaDAO
					.getTotalFamiliasExcluidasByFilter(filter));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void saveFamiliasExcluidasSerie(List<FamiliaExcluida> seleccionadas,List<FamiliaExcluida> noSeleccionadas, Usuario usuario)throws Exception {
		try {
			for (FamiliaExcluida familiaExcluida : noSeleccionadas) {
				familiaDAO.deleteFamiliaExcluidaSerie(familiaExcluida);
			}
			for (FamiliaExcluida familiaExcluida : seleccionadas) {
				FamiliaExcluida familia = familiaDAO.getFamiliaExcluidaSerieByIdFamilia(familiaExcluida.getId());
				if (familia == null) {
					familiaExcluida.setUsuarioExclusion(usuario);
					familiaDAO.saveFamiliaExcluidaSerie(familiaExcluida);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void asignarProductosAServicioTecnico(ServicioTecnico servicioTecnico, List<CheckForFlexigrid> productos, Boolean isGM, Boolean isGP) throws Exception {
		try {
			for (CheckForFlexigrid productoCheck : productos) {
				Producto producto = new Producto();
				producto.setId(productoCheck.getId().intValue());
				
				if (isGM) {
					this.asignarProductoAServicioTecnico(producto, servicioTecnico, Constants.TIPO_OT_GARANTIA_MASTER);	
				} 
				
				if (isGP) {
					this.asignarProductoAServicioTecnico(producto, servicioTecnico, Constants.TIPO_OT_GARANTIA_PROVEEDOR);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}		
	}
	
	public void asignarServicioTecnicoAProductos(Producto producto, List<CheckForFlexigrid> serviciosTecnicos, Boolean isGM, Boolean isGP) throws Exception {
		try {
			for (CheckForFlexigrid servicioTecnicoCheck : serviciosTecnicos) {
				ServicioTecnico servicioTecnico = new ServicioTecnico();
				servicioTecnico.setId(servicioTecnicoCheck.getId().intValue());
				
				if (isGM) {
					this.asignarProductoAServicioTecnico(producto, servicioTecnico, Constants.TIPO_OT_GARANTIA_MASTER);	
				} 
				
				if (isGP) {
					this.asignarProductoAServicioTecnico(producto, servicioTecnico, Constants.TIPO_OT_GARANTIA_PROVEEDOR);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}		
	}
	
	public void asignarProductoAServicioTecnico(Producto producto,ServicioTecnico servicioTecnico, String tipoGarantia)throws Exception {
		try {
			FilterServicioTecnico filterServicioTecnico = new FilterServicioTecnico();
			filterServicioTecnico.setIdProducto(producto.getId());
			filterServicioTecnico.setIdServicioTecnico(servicioTecnico.getId());
			filterServicioTecnico.setTipoOT(tipoGarantia);

			ServicioTecnicoProducto servicioTecnicoProducto = servicioTecnicoDAO.getByFilter(filterServicioTecnico);

			if (servicioTecnicoProducto == null) {
				servicioTecnicoProducto = new ServicioTecnicoProducto();
				servicioTecnicoProducto.setProducto(producto);
				servicioTecnicoProducto.setServicioTecnico(servicioTecnico);
				servicioTecnicoProducto.setTipoGarantia(tipoGarantia);
				servicioTecnicoProducto.setVigente(true);
				servicioTecnicoDAO.saveServicioTecnicoProducto(servicioTecnicoProducto);
			} else if (!servicioTecnicoProducto.getVigente()) {
				servicioTecnicoProducto.setVigente(true);
				servicioTecnicoDAO.updateVigencia(servicioTecnicoProducto);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Parte getParteById(Integer id)throws Exception{
		try {
			return parteDAO.get(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void eliminarAsignacionServicioTecnico(List<CheckForFlexigrid> idServicioTecnicoProducto)throws Exception {
		try {
			for (CheckForFlexigrid check : idServicioTecnicoProducto) {
				ServicioTecnicoProducto servicioTecnicoProducto = new ServicioTecnicoProducto(); 
				servicioTecnicoProducto.setId(check.getId());
				servicioTecnicoProducto.setVigente(false);
				servicioTecnicoDAO.updateVigencia(servicioTecnicoProducto);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void eliminarAsignacionUbicacion(List<CheckForFlexigrid> idDestino)throws Exception {
		try {
			for (CheckForFlexigrid check : idDestino) {
				Destino destino = new Destino(); 
				destino.setId(check.getId());
				destino.setVigente(false);
				destinoDAO.updateVigenciaUbicacion(destino);
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public void asignarDestinoAUbicacion(Ubicacion ubicacion, List<CheckForFlexigrid> destinos) throws Exception {
		try {
			for (CheckForFlexigrid check : destinos){
				Ubicacion destino = new Ubicacion();
				ubicacion = ubicacionDAO.get(ubicacion.getId());
				destino = ubicacionDAO.get(check.getId());

				if (ubicacion == null)
					throw new SSTException("La ubicacion de origen no existe");
				if (destino == null)
					throw new SSTException("La ubicacion de destino no existe");

				FilterDestino filter = new FilterDestino();
				filter.setIdDestino(destino.getId());
				filter.setIdOrigen(ubicacion.getId());

				Destino destinoOrigen = destinoDAO.getByFilter(filter);

				if (destinoOrigen == null) {
					destinoOrigen = new Destino();
					destinoOrigen.setDestino(destino);
					destinoOrigen.setOrigen(ubicacion);
					destinoOrigen.setVigente(true);
					destinoDAO.save(destinoOrigen);
				} else {
					if (!destinoOrigen.getVigente()) {
						destinoOrigen.setVigente(true);
						destinoDAO.updateVigencia(destinoOrigen);
					}
				}

			} 
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void asignarProveedoresAUbicacion(Ubicacion ubicacion, List<CheckForFlexigrid> proveedores) throws Exception {
		try {
			for (CheckForFlexigrid check : proveedores){
				Ubicacion destino = new Ubicacion();
				ubicacion = ubicacionDAO.get(ubicacion.getId());
				destino = ubicacionDAO.getProveedorById(check.getId());

				if (ubicacion == null)
					throw new SSTException("La ubicacion de origen no existe");
				if (destino == null)
					throw new SSTException("La ubicacion de destino no existe");

				FilterDestino filter = new FilterDestino();
				filter.setIdDestino(destino.getId());
				filter.setIdOrigen(ubicacion.getId());

				Destino destinoOrigen = destinoDAO.getByFilter(filter);

				if (destinoOrigen == null) {
					destinoOrigen = new Destino();
					destinoOrigen.setDestino(destino);
					destinoOrigen.setOrigen(ubicacion);
					destinoOrigen.setVigente(true);
					destinoDAO.save(destinoOrigen);
				} else {
					if (!destinoOrigen.getVigente()) {
						destinoOrigen.setVigente(true);
						destinoDAO.updateVigencia(destinoOrigen);
					}
				}

			} 
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void asignarBodegasAUbicacion(Ubicacion ubicacion, List<Ubicacion> bodegas) throws Exception {
		try {
			
			List<Ubicacion> bodegasForDelete = ubicacionDAO.listBodegas();
			
			for (Ubicacion bodega : bodegasForDelete) {
				FilterDestino filterDestino = new FilterDestino();
				filterDestino.setIdDestino(bodega.getId());
				filterDestino.setIdOrigen(ubicacion.getId());
				Destino destino = destinoDAO.getByFilter(filterDestino);
				if(destino != null) {
					destino.setVigente(false);
					destinoDAO.updateVigenciaUbicacion(destino);
				}
			}
			
			
			for (Ubicacion bodega : bodegas){
				Ubicacion destino = ubicacionDAO.get(bodega.getId());
				ubicacion = ubicacionDAO.get(ubicacion.getId());

				if (ubicacion == null)
					throw new SSTException("La ubicacion de origen no existe");
				if (destino == null)
					throw new SSTException("La ubicacion de destino no existe");

				FilterDestino filter = new FilterDestino();
				filter.setIdDestino(destino.getId());
				filter.setIdOrigen(ubicacion.getId());

				Destino destinoOrigen = destinoDAO.getByFilter(filter);

				if (destinoOrigen == null) {
					destinoOrigen = new Destino();
					destinoOrigen.setDestino(destino);
					destinoOrigen.setOrigen(ubicacion);
					destinoOrigen.setVigente(true);
					destinoDAO.save(destinoOrigen);
				} else {
					if (!destinoOrigen.getVigente()) {
						destinoOrigen.setVigente(true);
						destinoDAO.updateVigencia(destinoOrigen);
					}
				}

			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public Ubicacion saveUbicacion(Ubicacion ubicacion) throws Exception {
		try {
			if (ubicacionDAO.getUbicacionByUbicacion(ubicacion) != null)
				throw new SSTException("Ya existe esta ubicacion en el sistema");

			if (ubicacion.getId() != null) {
				if (ubicacionDAO.get(ubicacion.getId()) != null)
					throw new SSTException("Ya existe este centro de costo en el sistema");
			}
			
			ubicacion.setVigente(true);
			if (ubicacion.getId() == null) {
				ubicacion.setGiro(ubicacion.getDireccion());
				ubicacionDAO.save(ubicacion);
			} else {
				ubicacion.setGiro(Constants.GIRO_GRANDES_TIENDAS);
				ubicacionDAO.saveWithId(ubicacion);
			}

			return ubicacion;

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public List<TipoCambio> listAllTipoCambio() throws Exception {
		try {
			return tipoCambioDAO.listAllTipoCambio();

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listUsuarios(FilterUsuario filterUsuario, GridControl gridControl) throws Exception {
		try {
			filterUsuario.setSortOrder(gridControl.getSortOrder());
			filterUsuario.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(usuarioDAO.listUsuarios(filterUsuario, gridControl));
			listRange.setTotal(usuarioDAO.getTotalUsuarios(filterUsuario));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}

	}

	public ListRange listServicioTecnico(FilterServicioTecnico filterServicioTecnico, GridControl gridControl) throws Exception {
		try {
			filterServicioTecnico.setSortOrder(gridControl.getSortOrder());
			filterServicioTecnico.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(servicioTecnicoDAO.listServicioTecnico(filterServicioTecnico, gridControl));
			listRange.setTotal(servicioTecnicoDAO.getTotalServiciosTecnicos(filterServicioTecnico));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public ListRange listServicioTecnicoProducto(FilterServicioTecnico filterServicioTecnico, GridControl gridControl) throws Exception {
		try {
			filterServicioTecnico.setSortOrder(gridControl.getSortOrder());
			filterServicioTecnico.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(servicioTecnicoDAO.listServicioTecnicoProducto(filterServicioTecnico, gridControl));
			listRange.setTotal(servicioTecnicoDAO.getTotalServicioTecnicoProducto(filterServicioTecnico));
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listProductosParaExcluirCCByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
        try {
            ListRange listRange = new ListRange();
            filterProducto.setSortOrder(gridControl.getSortOrder());
            filterProducto.setOrderBy(gridControl.getOrderBy());
            listRange.setRows(productoDAO.listProductosParaExcluirCCByFilter(filterProducto, gridControl));
            listRange.setTotal(productoDAO.getTotalProductosParaExcluirCCByFilter(filterProducto));
            return listRange;
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
    }
	
	public List<CheckForFlexigrid> listAllProductosCCCheck(FilterProducto filterProducto) throws Exception {
        try {
            return productoDAO.listAllProductosCCCheck(filterProducto);
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
    }
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosProductoCheck(FilterServicioTecnico filter) throws Exception {
		try{
			return servicioTecnicoDAO.listAllServiciosTecnicosProductoCheck(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listFamiliasParaExcluirCC(FilterFamilia filterFamilia, GridControl gridControl) throws Exception{
		try {
			filterFamilia.setSortOrder(gridControl.getSortOrder());
			filterFamilia.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(familiaDAO.listFamiliasParaExcluirCC(filterFamilia,gridControl));
			listRange.setTotal(familiaDAO.getTotalFamiliasParaExcluirCC());
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllFamiliaCheck(FilterFamilia filterFamilia) throws Exception {
		try {
			return familiaDAO.listAllFamiliaCheck();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateProductosExcluidosCC(List<ProductoExcluido> productosExcluidos, Usuario usuario) throws Exception {
		try {
			
			ProductoExcluido productoAux;
			List<Integer> productosEliminar=new ArrayList<Integer>();
			for(ProductoExcluido productoExcluido:productosExcluidos){
				productoAux=productoDAO.getProductoExcluidoCCByIdProducto(productoExcluido.getId());
				
				if(productoAux!=null){
					if(!productoExcluido.getExcluido()){
						productosEliminar.add(productoExcluido.getId());
					}
				} else if(productoExcluido.getExcluido()){
					this.agregarProductoExluidosCC(productoExcluido, usuario);
				}
			}
			
			if(!productosEliminar.isEmpty()){
				if(productosEliminar.size()>1000){
					Integer index = 0;
					List<Integer> prodAux = new ArrayList<Integer>();
					for(Integer idProducto:productosEliminar){
						if(!(index<1000)){
							productoDAO.deleteProductoExcluidoCC(prodAux);
							index=0;
							prodAux = new ArrayList<Integer>();
						}
						prodAux.add(idProducto);
						index++;
					}
					productoDAO.deleteProductoExcluidoCC(prodAux);
				} else {
					productoDAO.deleteProductoExcluidoCC(productosEliminar);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void agregarProductoExluidosCC(ProductoExcluido productoExcluido, Usuario usuario) throws Exception {
		try {
				productoExcluido.setUsuarioExclusion(usuario);
				productoDAO.saveProductoExcluidoCC(productoExcluido);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void updateFamiliasExcluidas(List<FamiliaExcluidaControlCalidad> familiasExcluidasControlCalidad,Usuario usuario)throws Exception {
		try {
			Familia familiaAux;
			List<String> familiasEliminar = new ArrayList<String>();
			for(FamiliaExcluidaControlCalidad familiaExcluidaControlCalidad:familiasExcluidasControlCalidad){
				familiaAux=familiaDAO.getFamiliaExcluidaCCByIdFamilia(familiaExcluidaControlCalidad.getFamilia().getId());
				
				if(familiaAux!=null && !familiaExcluidaControlCalidad.getExcluida()){
					familiasEliminar.add(familiaExcluidaControlCalidad.getFamilia().getId());
				} else if(familiaAux==null && familiaExcluidaControlCalidad.getExcluida()){
					this.agregarFamiliaExcluida(familiaExcluidaControlCalidad, usuario);
				}
			}
			if(!familiasEliminar.isEmpty()){
				familiaDAO.deleteFamiliaExcluidaCC(familiasEliminar);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private void agregarFamiliaExcluida(FamiliaExcluidaControlCalidad familiaExcluidaControlCalidad,Usuario usuario) throws Exception{
		try {
				familiaExcluidaControlCalidad.setUsuario(usuario);
				familiaDAO.saveFamiliaExcluidaCC(familiaExcluidaControlCalidad);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Boolean isDestinoDeUbicacion(Ubicacion origen, Ubicacion destino) throws Exception {
		try {
			return destinoDAO.isDestinoDeUbicacion(origen,destino) > 0;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listDestinosUbicacion(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception {
		try {
			filterUbicacion.setSortOrder(gridControl.getSortOrder());
			filterUbicacion.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(ubicacionDAO.listDestinosUbicacion(filterUbicacion, gridControl));
			listRange.setTotal(ubicacionDAO.getTotalDestinosUbicacion(filterUbicacion)); 
			return listRange;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public ListRange listRutasServicioTecnico(FilterRutaServicioTecnico filterRutaServicioTecnico, GridControl gridControl) throws Exception {
        try {
            filterRutaServicioTecnico.setOrderBy(gridControl.getOrderBy());
            filterRutaServicioTecnico.setSortOrder(gridControl.getSortOrder());
            ListRange listRange = new ListRange();
            listRange.setRows(rutaServicioTecnicoDAO.listRutasServicioTecnico(filterRutaServicioTecnico, gridControl));
            listRange.setTotal(rutaServicioTecnicoDAO.getTotalRutasServicioTecnico(filterRutaServicioTecnico));
            return listRange;
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
    }
	
	public List<CheckForFlexigrid> listAllRutasServicioTecnicoCheck() throws Exception {
        try {
            return rutaServicioTecnicoDAO.listAllRutasServicioTecnicoCheck();
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
    } 
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosCheck(FilterServicioTecnico filter) throws Exception {
		try{
			return servicioTecnicoDAO.listAllServiciosTecnicosCheck(filter);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	
	public ListRange listTipoFallasByFilterFallas(FilterTipoFallas filter,GridControl gridControl)throws Exception{
		try {
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(tipoFallasDAO.listTipoFallasByFilterFallas(filter,gridControl));
			listRange.setTotal(tipoFallasDAO.getTipoFallasByFilter(filter)); 
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<TipoFallas> listTipoFallasByFilterFallas(FilterTipoFallas filter) throws Exception{
		try {
			return tipoFallasDAO.listTipoFallasByFilterFallas(filter);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public List<TipoFallas> listTipoFallasNotExistsByFilterFallas(FilterTipoFallas filter) throws Exception{
		try {
			return tipoFallasDAO.listTipoFallasNotExistsByFilterFallas(filter);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Familia getFamiliaByFilter(FilterProducto filter)throws Exception{
		try {
			return familiaDAO.getFamiliaByFilter(filter);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void updateTipoFallaByEstado(TipoFallas tipoFalla) throws Exception {
		try {
			tipoFallasDAO.updateTipoFallaByEstado(tipoFalla);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void saveTipoFalla(TipoFallas tipoFalla) throws Exception {
		try {
			if (tipoFalla.getId() == null) {
				tipoFallasDAO.save(tipoFalla);
			} else {
				tipoFallasDAO.update(tipoFalla);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public TipoFallas getTipoFallas(Integer id) throws Exception {
		try {
			return tipoFallasDAO.get(id);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listPartesByFilterPartes(FilterParte filter, GridControl gridControl)throws Exception{
		try {
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(parteDAO.listPartesByFilterPartes(filter,gridControl));
			listRange.setTotal(parteDAO.getPartesByFilter(filter)); 
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void savePartes(Parte parte)throws Exception{
		try {
			if (parte.getId() == null) {
				parteDAO.save(parte);
			} else {
				parteDAO.update(parte);
			}
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public Procedimiento saveProcedimiento(Procedimiento procedimiento, Usuario usuario)throws Exception{
		try {
			if (procedimiento.getId() != null) {	
				procedimiento.setUsuarioModificacion(usuario);
				procedimientoDAO.updateProcedimientoProducto(procedimiento);
			} else {
				if (procedimiento.getProducto() != null) {
					FilterProcedimiento filterProcedimiento = new FilterProcedimiento();
					filterProcedimiento.setProducto(procedimiento.getProducto().getId());
					Integer total = procedimientoDAO.getTotalProcedimientosProductosByFilter(filterProcedimiento);
					if (total != 0) {
						throw new SSTException("El procedimiento ya esta registrado ");
					}
				} else {
					FilterProcedimiento filterProcedimiento = new FilterProcedimiento();
					filterProcedimiento.setMarca(procedimiento.getMarca().getCodigo());
					filterProcedimiento.setLinea(procedimiento.getLinea().getCodigo());
					Integer total = procedimientoDAO.getTotalProcedimientosProductosByFilter(filterProcedimiento);
					if (total != 0) {
						throw new SSTException("El procedimiento ya esta registrado ");
					}
				}
				procedimiento.setVigente(true);
				procedimiento.setUsuarioCreacion(usuario);
				procedimientoDAO.saveProcedimiento(procedimiento);
			}
			return procedimiento;
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public void updateParteByEstado(Parte parte)throws Exception{
		try {
				parteDAO.updateParteByEstado(parte);
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public void setRutaServicioTecnicoDAO(RutaServicioTecnicoDAO rutaServicioTecnicoDAO) {
		this.rutaServicioTecnicoDAO = rutaServicioTecnicoDAO;
	}
	
	public ListRange listServicioTecnicoForEjecutiva(FilterEjecutiva filter, GridControl gridControl)throws Exception{
		try {
			filter.setSortOrder(gridControl.getSortOrder());
			filter.setOrderBy(gridControl.getOrderBy());
			ListRange listRange = new ListRange();
			listRange.setRows(servicioTecnicoDAO.listServicioTecnicoForEjecutiva(filter,gridControl));
			listRange.setTotal(servicioTecnicoDAO.getServicioTecnicoForEjecutiva(filter)); 
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public void updateSTecnicoEjecutiva(STecnicoEjecutiva stEjecutiva) throws Exception{
		try {
			if(!stEjecutiva.getId().equals("")){
				servicioTecnicoEjecutivaDAO.update(stEjecutiva);
			} else {
				servicioTecnicoEjecutivaDAO.save(stEjecutiva);
			}
			
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public STecnicoEjecutiva convertStringToSTecnicoEjecutiva(String id) throws Exception{
		try {
			STecnicoEjecutiva stEjecutiva = new STecnicoEjecutiva();
			String element[] = id.split("_");
			Long idSTecnico =  Long.parseLong((element[0]));
			String codigo = element[1];
			
			stEjecutiva.setUbicacion(new Ubicacion());
			stEjecutiva.getUbicacion().setId(idSTecnico);
			stEjecutiva.setMarca(new Marca());
			stEjecutiva.getMarca().setCodigo(codigo);
			return stEjecutiva;
		} catch (Exception e) {
			log.error(e ,e);
			throw e;
		}
	}
	
	public STecnicoEjecutiva getExisteEjecutivaSTecnico(String id)throws Exception{
		try {
			STecnicoEjecutiva stEjecutiva = convertStringToSTecnicoEjecutiva(id);
			if(servicioTecnicoEjecutivaDAO.getExiste(stEjecutiva) > 0){
				stEjecutiva = servicioTecnicoEjecutivaDAO.get(stEjecutiva); 
			} 
			return stEjecutiva;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listUbicacionInternaCDByFilter(FilterUbicacionInterna filterUbicacionInterna, GridControl gridControl) throws Exception {
	    try {
            ListRange listRange = new ListRange();
            filterUbicacionInterna.setOrderBy(gridControl.getOrderBy());
            filterUbicacionInterna.setSortOrder(gridControl.getSortOrder());
            List<UbicacionInternaCD> ubicacionesInternasCD = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna, gridControl);
            for(UbicacionInternaCD ubicacionInternaCD : ubicacionesInternasCD){
                ubicacionInternaCD.setSucursales(sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInternaCD.getId()));
                ubicacionInternaCD.setFamilias(familiaDAO.listFamiliasByIdUbicacionInterna(ubicacionInternaCD.getId()));
                ubicacionInternaCD.setLineas(lineaDAO.listLineasByIdUbicacionInterna(ubicacionInternaCD.getId()));
            }
            listRange.setRows(ubicacionesInternasCD);
            listRange.setTotal(ubicacionInternaDAO.getTotalUbicacionInternaCD(filterUbicacionInterna));
            return listRange;
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCDByFilter(FilterUbicacionInterna filterUbicacionInterna) throws Exception {
	    try {
            List<UbicacionInternaCD> ubicacionesInternasCD = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
            for(UbicacionInternaCD ubicacionInternaCD : ubicacionesInternasCD){
                ubicacionInternaCD.setSucursales(sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInternaCD.getId()));
                ubicacionInternaCD.setFamilias(familiaDAO.listFamiliasByIdUbicacionInterna(ubicacionInternaCD.getId()));
                ubicacionInternaCD.setLineas(lineaDAO.listLineasByIdUbicacionInterna(ubicacionInternaCD.getId()));
            }
            return ubicacionesInternasCD;
        } catch (Exception e) {
            log.error(e,e);
            throw e;
        }
	}
	
	

	
	public ListRange listFamiliasByIdUbicacionInterna(String codigo) throws Exception {
		try {
			ListRange listRange = new ListRange();
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigo);
			
			listRange.setRows(familiaDAO.listFamiliasByIdUbicacionInterna(ubicacionInterna.getId()));
			listRange.setTotal(familiaDAO.getTotalFamiliasByIdUbicacionInterna(ubicacionInterna.getId()));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listSucursalesByIdUbicacionInterna(String codigo) throws Exception {
		try {
			ListRange listRange = new ListRange();
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigo);
			
			listRange.setRows(sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInterna.getId()));
			listRange.setTotal(sucursalDAO.getTotalSucursalesByIdUbicacionInterna(ubicacionInterna.getId()));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listProductosByIdUbicacionInterna(String codigo) throws Exception {
		try {
			ListRange listRange = new ListRange();
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigo);
			
			listRange.setRows(productoDAO.listProductosByIdUbicacionInterna(ubicacionInterna.getId()));
			listRange.setTotal(productoDAO.getTotalProductosByIdUbicacionInterna(ubicacionInterna.getId()));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listLineasByCodigoUbicacionInterna(String codigo) throws Exception{
		try {
			ListRange listRange = new ListRange();
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigo);
			
			listRange.setRows(lineaDAO.listLineasByIdUbicacionInterna(ubicacionInterna.getId()));
			listRange.setTotal(lineaDAO.getTotalLineasByIdUbicacionInterna(ubicacionInterna.getId()));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listLineasLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();

			listRange.setRows(lineaDAO.listLineasLessCodigoUbicacion(codigo, gridControl));
			listRange.setTotal(lineaDAO.getTotalLineasLessCodigoUbicacion(codigo));
			
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listProductosLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();

			listRange.setRows(productoDAO.listProductosLessCodigoUbicacion(codigo, gridControl));
			listRange.setTotal(productoDAO.getTotalProductoLessCodigoUbicacion(codigo));
			
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listSucursalLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();

			listRange.setRows(sucursalDAO.listSucursalLessCodigoUbicacion(codigo, gridControl));
			listRange.setTotal(sucursalDAO.getTotalSucursalLessCodigoUbicacion(codigo));
			
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllSucursalLessCodigoUbicacion(String codigo) throws Exception {
		try {
			return sucursalDAO.listAllSucursalLessCodigoUbicacion(codigo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public List<CheckForFlexigrid> listAllFamiliaLessCodigoUbicacion(String codigo) throws Exception {
		try {
			return familiaDAO.listAllFamiliaLessCodigoUbicacion(codigo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listFamiliaLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			
			listRange.setRows(familiaDAO.listFamiliaLessCodigoUbicacion(codigo, gridControl));
			listRange.setTotal(familiaDAO.getTotalFamiliaLessCodigoUbicacion(codigo));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllIdLineaLessCodigoUbicacion(String codigo) throws Exception {
		try {
			return lineaDAO.listAllIdLineaLessCodigoUbicacion(codigo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllLineasByCodigoUbicacionInterna(String codigo) throws Exception {
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(codigo);
			return lineaDAO.listAllLineasByIdUbicacionInterna(ubicacionInterna.getId());
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listAllProductosLessCodigoUbicacion(String codigo) throws Exception {
		try {
			return productoDAO.listAllProductosLessCodigoUbicacion(codigo);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Linea> listLineasByCodigo(List<String> codigos) throws Exception{
		try {
			return lineaDAO.listLineasByCodigo(codigos);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public Integer saveUbicacionInternaCD(UbicacionInterna ubicacionInterna, Usuario usuario) throws Exception {
		try {
			UbicacionInterna ubicacionInternaAux = getUbicacionInternaCDByCodigo(ubicacionInterna.getCodigo());
			
			if(ubicacionInternaAux!=null){
				throw new SSTException("Ya existe una ubicación con el código "+ubicacionInterna.getCodigo());
			}
			
			ubicacionInterna.setUsuario(usuario);
			ubicacionInterna.setUbicacionPadre(new Ubicacion());
			ubicacionInterna.getUbicacionPadre().setId(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA);
			return ubicacionInternaDAO.save(ubicacionInterna);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void ubicacionInternaCDHasSucursal(Long idUbicacionInterna) throws Exception{
		try {
			if(ubicacionInternaDetalleDAO.hasSucursalesByIdUbicacionInterna(idUbicacionInterna)<=0){
				throw new SSTException("Primero debe agregar una sucursal a la ubicación interna");
			}
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveLineasForUbicacionInterna(List<String> lineas, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		try {
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo()));
			
			if(ubicacionInternaDetalle.getUbicacionInterna()==null){
				throw new SSTException("No existe la ubicación interna");
			}
			this.ubicacionInternaCDHasSucursal(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
			List<Sucursal> sucursales= sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInternaDetalle.getUbicacionInterna().getId());
			for(Sucursal sucursal:sucursales){
				for(String idLinea:lineas){
					FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
					filterUbicacionInterna.setIdLinea(idLinea);
					filterUbicacionInterna.setIdSucursal(sucursal.getId());
					filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
					filterUbicacionInterna.setVigente(true);
					List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
					
					if(!ubicacionesSucursal.isEmpty()){
						Linea linea = lineaDAO.getById(idLinea);
						
						throw new SSTException("No se puede agregar la línea "+linea.getGlosa()+", ya existe una ubicación con la sucursal "+sucursal.getGlosa()+" que incluye a esta línea");
					}
				}
			}
			
			ubicacionInternaDetalle.setLinea(new Linea());
			for(String linea : lineas){
				ubicacionInternaDetalle.getLinea().setCodigo(linea);
				ubicacionInternaDetalleDAO.save(ubicacionInternaDetalle);
			}
		}catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveFamiliasForUbicacionInterna(List<String> familias, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		try {
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo()));
			if(ubicacionInternaDetalle.getUbicacionInterna()==null){
				throw new SSTException("No existe la ubicación interna");
			}
			this.ubicacionInternaCDHasSucursal(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
			List<Sucursal> sucursales= sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInternaDetalle.getUbicacionInterna().getId());
			for(Sucursal sucursal:sucursales){
				for(String idFamilia:familias){
					FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
					filterUbicacionInterna.setIdFamilia(idFamilia);
					filterUbicacionInterna.setIdSucursal(sucursal.getId());
					filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
					filterUbicacionInterna.setVigente(true);
					List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
					
					if(!ubicacionesSucursal.isEmpty()){
						Familia familia = familiaDAO.getById(idFamilia);
						throw new SSTException("No se puede agregar la familia "+familia.getNombre()+", ya existe una ubicación con la sucursal "+sucursal.getGlosa()+" que incluye a esta familia");
					}
				}
			}
			
			ubicacionInternaDetalle.setFamilia(new Familia());
			for(String familia : familias){
				ubicacionInternaDetalle.getFamilia().setCodigo(familia);
				ubicacionInternaDetalleDAO.save(ubicacionInternaDetalle);
			}
		} catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void sucursalesAreAvailable(List<String> familias, List<String> lineas, List<Integer> productos, List<Integer> sucursales, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		try {
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo()));
			if(ubicacionInternaDetalle.getUbicacionInterna()==null){
				throw new SSTException("No existe la ubicación interna");
			}
			for(Integer idSucursal:sucursales){
				for(String idFamilia:familias){
					FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
					filterUbicacionInterna.setIdFamilia(idFamilia);
					filterUbicacionInterna.setIdSucursal(idSucursal);
					filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
					filterUbicacionInterna.setVigente(true);
					List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
					
					
					if(!ubicacionesSucursal.isEmpty()){
						Familia familia = familiaDAO.getById(idFamilia);
						Sucursal sucursal = sucursalDAO.getSucursalById(idSucursal.longValue());
						throw new SSTException("No se puede agregar la sucursal "+sucursal.getGlosa()+", ya existe una ubicación con la familia "+familia.getNombre()+" que incluye a esta sucursal, o está intentado de volver a activar un ubicación que tiene conflictos con una ya activada");
					}
				}
			}
			
			for(Integer idSucursal:sucursales){
				for(String idLinea:lineas){
					FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
					filterUbicacionInterna.setIdLinea(idLinea);
					filterUbicacionInterna.setIdSucursal(idSucursal);
					filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
					filterUbicacionInterna.setVigente(true);
					List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
					
					if(!ubicacionesSucursal.isEmpty()){
						Linea linea = lineaDAO.getById(idLinea);
						Sucursal sucursal = sucursalDAO.getSucursalById(idSucursal.longValue());
						throw new SSTException("No se puede agregar la sucursal "+sucursal.getGlosa()+", ya existe una ubicación con la linea "+linea.getGlosa()+" que incluye a esta sucursal, o está intentado de volver a activar un ubicación que tiene conflictos con una ya activada");
					}
				}
			}
			
			for(Integer idSucursal:sucursales){
				for(Integer idProducto:productos){
					FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
					filterUbicacionInterna.setIdProducto(idProducto.longValue());
					filterUbicacionInterna.setIdSucursal(idSucursal);
					filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
					filterUbicacionInterna.setVigente(true);
					List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
					
					if(!ubicacionesSucursal.isEmpty()){
						Producto producto = productoDAO.getProductoById(filterUbicacionInterna.getIdProducto());
						Sucursal sucursal = sucursalDAO.getSucursalById(idSucursal.longValue());
						throw new SSTException("No se puede agregar el sucursal "+sucursal.getGlosa()+", ya existe una ubicación con el producto "+producto.getDescripcion()+" que incluye a esta sucursal, o está intentado de volver a activar un ubicación que tiene conflictos con una ya activada");
					}
				}
			}
			
		}catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveSucursalesForUbicacionInterna(List<Integer> sucursales, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		try {
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo()));
			ubicacionInternaDetalle.setSucursal(new Sucursal());
			for(Integer sucursal : sucursales){
				ubicacionInternaDetalle.getSucursal().setId(sucursal);
				ubicacionInternaDetalleDAO.save(ubicacionInternaDetalle);
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer saveProductoForUbicacionInterna(UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception{
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInterna);
			if(ubicacionInternaDetalle.getUbicacionInterna()==null){
				throw new SSTException("No existe la ubicación interna");
			}
			this.ubicacionInternaCDHasSucursal(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
			List<Sucursal> sucursales= sucursalDAO.listSucursalesByIdUbicacionInterna(ubicacionInternaDetalle.getUbicacionInterna().getId());
			for(Sucursal sucursal:sucursales){
				FilterUbicacionInterna filterUbicacionInterna = new FilterUbicacionInterna();
				filterUbicacionInterna.setIdProducto(ubicacionInternaDetalle.getProducto().getId().longValue());
				filterUbicacionInterna.setIdSucursal(sucursal.getId());
				filterUbicacionInterna.setNotId(ubicacionInternaDetalle.getUbicacionInterna().getId().longValue());
				filterUbicacionInterna.setVigente(true);
				List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternaCDByFilter(filterUbicacionInterna);
				if(!ubicacionesSucursal.isEmpty()){
					Producto producto = productoDAO.getProductoById(filterUbicacionInterna.getIdProducto());
					throw new SSTException("No se puede agregar el producto "+producto.getDescripcion()+", ya existe una ubicación con la sucursal "+sucursal.getGlosa()+" que incluye a este producto");
				}
			}
			return ubicacionInternaDetalleDAO.save(ubicacionInternaDetalle);
		} catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer updateModificaUbicacionInternaCD(UbicacionInternaCD ubicacionInternaCD,Usuario usuarioModificacion) throws Exception{
		try {
			
			UbicacionInterna ubiAux = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaCD.getCodigo());
			if(ubiAux==null){
				throw new SSTException("No existe la ubicación interna");
			}
			if(!ubiAux.getVigente() && ubicacionInternaCD.getVigente()){
				UbicacionInternaDetalle ubicacionInternaDetalle = new UbicacionInternaDetalle();
				ubicacionInternaDetalle.setUbicacionInterna(ubiAux);
				
				List<Familia> familias = familiaDAO.listFamiliasByIdUbicacionInterna(ubiAux.getId());
				List<String> idFamilias = new ArrayList<String>();
				for(Familia familia:familias){
					idFamilias.add(familia.getId());
				}
				
				List<Linea> lineas = lineaDAO.listLineasByIdUbicacionInterna(ubiAux.getId());
				List<String> idLineas = new ArrayList<String>();
				for(Linea linea:lineas){
					idLineas.add(linea.getId());
				}
				
				List<Producto> productos = productoDAO.listProductosByIdUbicacionInterna(ubiAux.getId());
				List<Integer> idProductos = new ArrayList<Integer>();
				for(Producto producto:productos){
					idProductos.add(producto.getId());
				}
				
				List<Sucursal> sucursales = sucursalDAO.listSucursalesByIdUbicacionInterna(ubiAux.getId());
				List<Integer> idSucursales = new ArrayList<Integer>();
				for(Sucursal sucursal:sucursales){
					idSucursales.add(sucursal.getId());
				}
				
				this.sucursalesAreAvailable(idFamilias, idLineas, idProductos, idSucursales, ubicacionInternaDetalle);
			}
			
			this.ubicacionInternaCDHasSucursal(ubiAux.getId().longValue());
			ubicacionInternaCD.setUsuarioModificacion(usuarioModificacion);
			return ubicacionInternaDAO.updateModificaUbicacionInternaCD(ubicacionInternaCD);
		} catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCD() throws Exception {
		try {
			return ubicacionInternaDAO.listUbicacionInternaCD();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public void eliminarProductosFromUbicacionInternaDetalle(List<Integer> productos, UbicacionInternaDetalle ubicacionInternaDetalle, Usuario usuario) throws Exception {
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInterna);
			UbicacionInternaCD ubicacionInternaCD = ubicacionInternaDAO.getUbicacionInternaCDByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaCD.setUsuarioModificacion(usuario);
			ubicacionInternaDAO.updateModificaUbicacionInternaCD(ubicacionInternaCD);
			ubicacionInternaDetalle.setProducto(new Producto());
			for(Integer id: productos){
				ubicacionInternaDetalle.getProducto().setId(id);
				ubicacionInternaDetalleDAO.eliminarFromUbicacionInternaDetalle(ubicacionInternaDetalle);
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void eliminarSucursalesFromUbicacionInternaDetalle(List<Integer> sucursales, UbicacionInternaDetalle ubicacionInternaDetalle, Usuario usuario) throws Exception {
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInterna);
			UbicacionInternaCD ubicacionInternaCD = ubicacionInternaDAO.getUbicacionInternaCDByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaCD.setUsuarioModificacion(usuario);
			ubicacionInternaDAO.updateModificaUbicacionInternaCD(ubicacionInternaCD);
			ubicacionInternaDetalle.setSucursal(new Sucursal());
			for(Integer id: sucursales){
				ubicacionInternaDetalle.getSucursal().setId(id);
				ubicacionInternaDetalleDAO.eliminarFromUbicacionInternaDetalle(ubicacionInternaDetalle);
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void eliminarFamiliasFromUbicacionInternaDetalle(List<String> familias, UbicacionInternaDetalle ubicacionInternaDetalle, Usuario usuario) throws Exception {
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInterna);
			UbicacionInternaCD ubicacionInternaCD = ubicacionInternaDAO.getUbicacionInternaCDByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaCD.setUsuarioModificacion(usuario);
			ubicacionInternaDAO.updateModificaUbicacionInternaCD(ubicacionInternaCD);
			ubicacionInternaDetalle.setFamilia(new Familia());
			for(String id: familias){
				ubicacionInternaDetalle.getFamilia().setId(id);
				ubicacionInternaDetalleDAO.eliminarFromUbicacionInternaDetalle(ubicacionInternaDetalle);
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void eliminarLineasFromUbicacionInternaDetalle(List<String> lineas, UbicacionInternaDetalle ubicacionInternaDetalle, Usuario usuario) throws Exception {
		try {
			UbicacionInterna ubicacionInterna = ubicacionInternaDAO.getUbicacionInternaByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaDetalle.setUbicacionInterna(ubicacionInterna);
			UbicacionInternaCD ubicacionInternaCD = ubicacionInternaDAO.getUbicacionInternaCDByCodigo(ubicacionInternaDetalle.getUbicacionInterna().getCodigo());
			ubicacionInternaCD.setUsuarioModificacion(usuario);
			ubicacionInternaDAO.updateModificaUbicacionInternaCD(ubicacionInternaCD);
			ubicacionInternaDetalle.setLinea(new Linea());
			for(String codigo: lineas){
				ubicacionInternaDetalle.getLinea().setCodigo(codigo);
				ubicacionInternaDetalleDAO.eliminarFromUbicacionInternaDetalle(ubicacionInternaDetalle);
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public void saveRutaServicioTecnicoDetalle(RutaServicioTecnicoDetalle rutaServicioTecnicoDetalle, List<Integer> sTecnicos, Usuario usuario) throws Exception {
		try {
			RutaServicioTecnico ruta = rutaServicioTecnicoDAO.getRutaServicioTecnicoByCodigo(rutaServicioTecnicoDetalle.getRutaServicioTecnico().getCodigo());
			List<Integer> sTexistentes = rutaServicioTecnicoDetalleDAO.listIdStecnicoByRuta(ruta.getId().longValue());
			List<Integer> agregar=new ArrayList<Integer>();
			List<Integer> eliminar=new ArrayList<Integer>();
			
			for(Integer existe:sTexistentes){
				if(!sTecnicos.contains(existe)){
					eliminar.add(existe);
				}
			}
			
			for(Integer nuevo:sTecnicos){
				if(rutaServicioTecnicoDetalleDAO.getCountSTecnicoOtrasRutas(ruta, nuevo)) {
					Ubicacion ubicacion = ubicacionDAO.get(nuevo.longValue());
					throw new SSTException("El servicio técnico " + ubicacion.getNombre() + " está asociado a otra ruta");
				}
				
				if(!sTexistentes.contains(nuevo)){
					agregar.add(nuevo);
				}
			}
			
			if(!eliminar.isEmpty()){
				FilterRutaServicioTecnico filterRutaServicioTecnico = new FilterRutaServicioTecnico();
				filterRutaServicioTecnico.setEliminar(eliminar);
				filterRutaServicioTecnico.setIdRuta(ruta.getId());
				rutaServicioTecnicoDetalleDAO.deleteSTFromRuta(filterRutaServicioTecnico);
			}
			
			if(!agregar.isEmpty()){
				rutaServicioTecnicoDetalle.setUsuarioCreacion(usuario);
				rutaServicioTecnicoDetalle.setServicioTecnico(new ServicioTecnico());
				
				rutaServicioTecnicoDetalle.setRutaServicioTecnico(ruta);
				for(Integer id: agregar){
					rutaServicioTecnicoDetalle.getServicioTecnico().setId(id);
					rutaServicioTecnicoDetalleDAO.saveRutaServicioTecnicoDetalle(rutaServicioTecnicoDetalle);
				}
			}
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public Integer saveRutaServicioTecnico(RutaServicioTecnico rutaServicioTecnico, Usuario usuario) throws Exception{
		try {
			rutaServicioTecnico.setUsuarioCreacion(usuario);
			RutaServicioTecnico ruta = rutaServicioTecnicoDAO.getRutaServicioTecnicoByCodigo(rutaServicioTecnico.getCodigo());
			if(ruta!=null){
				return rutaServicioTecnicoDAO.updateRutaServicioTecnico(rutaServicioTecnico);
			}
			return rutaServicioTecnicoDAO.saveRutaServicioTecnico(rutaServicioTecnico);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	    
	}
	
	public List<Provincia> listProvinciaByIdRegion(Integer idRegion) throws Exception {
		try {
			return provinciaDAO.listProvinciaByIdRegion(idRegion);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<Comuna> listComunasByIdProvincia(Long idProvincia) throws Exception {
		try {
			return comunaDAO.listComunasByIdProvincia(idProvincia);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<ServicioTecnico> listServiciosTecnicosForRutasSTByFilter(FilterServicioTecnico filterServicioTecnico) throws Exception {
		try {
			return servicioTecnicoDAO.listServiciosTecnicosForRutasSTByFilter(filterServicioTecnico);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<ServicioTecnico> listServiciosTecnicosFromRutasSTByRuta(RutaServicioTecnico rutaServicioTecnico) throws Exception {
		try {
			return servicioTecnicoDAO.listServiciosTecnicosFromRutasSTByRuta(rutaServicioTecnico);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<UbicacionInterna> listTodasUbicacionesInternasCD() throws Exception{
		try {
			return ubicacionInternaDAO.listTodasUbicacionesInternasCD();
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
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

	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}

	public void setDestinoDAO(DestinoDAO destinoDAO) {
		this.destinoDAO = destinoDAO;
	}

	public void setTipoCambioDAO(TipoCambioDAO tipoCambioDAO) {
		this.tipoCambioDAO = tipoCambioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
	
	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO) {
		this.tipoFallasDAO = tipoFallasDAO;
	}
	
	public void setAccesorioDAO(AccesorioDAO accesorioDAO) {
		this.accesorioDAO = accesorioDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setProcedimientoDAO(ProcedimientoDAO procedimientoDAO) {
		this.procedimientoDAO = procedimientoDAO;
	}

	
	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}

	public void setServicioTecnicoEjecutivaDAO(ServicioTecnicoEjecutivaDAO servicioTecnicoEjecutivaDAO) {
		this.servicioTecnicoEjecutivaDAO = servicioTecnicoEjecutivaDAO;
	}

	public void setProvinciaDAO(ProvinciaDAO provinciaDAO){
		this.provinciaDAO = provinciaDAO;
	}
	
	public void setRutaServicioTecnicoDetalleDAO(RutaServicioTecnicoDetalleDAO rutaServicioTecnicoDetalleDAO) {
		this.rutaServicioTecnicoDetalleDAO = rutaServicioTecnicoDetalleDAO;
	}
	
	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO){
	    this.ubicacionInternaDAO=ubicacionInternaDAO;
	}
	
	public void setSucursalDAO(SucursalDAO sucursalDAO){
	    this.sucursalDAO= sucursalDAO;
	}

	public void setUbicacionInternaDetalleDAO(UbicacionInternaDetalleDAO ubicacionInternaDetalleDAO) {
		this.ubicacionInternaDetalleDAO = ubicacionInternaDetalleDAO;
	}
	
	public void setComunaDAO(ComunaDAO comunaDAO) throws Exception {
		this.comunaDAO = comunaDAO;
	}

}
