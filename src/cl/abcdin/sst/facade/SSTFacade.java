package cl.abcdin.sst.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.login.service.ILoginService;
import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.Cambio;
import cl.abcdin.sst.model.CambioAutomaticoProveedorCarta;
import cl.abcdin.sst.model.Clasificacion;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.DespachoDetalle;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.FamiliaExcluidaControlCalidad;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.Inventario;
import cl.abcdin.sst.model.InventarioUbicacion;
import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.Logistico;
import cl.abcdin.sst.model.Macro;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.PasosOT;
import cl.abcdin.sst.model.PeticionDocumento;
import cl.abcdin.sst.model.Procedimiento;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.ProductoExcluido;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.Provincia;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.RecepcionCamion;
import cl.abcdin.sst.model.Region;
import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.Resultado;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.RutaServicioTecnico;
import cl.abcdin.sst.model.RutaServicioTecnicoDetalle;
import cl.abcdin.sst.model.Seccion;
import cl.abcdin.sst.model.SeccionColumna;
import cl.abcdin.sst.model.Sello;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.TiendaZona;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.UbicacionInternaDetalle;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.Zona;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.model.filters.FilterDespachoInternoDetalle;
import cl.abcdin.sst.model.filters.FilterEjecutiva;
import cl.abcdin.sst.model.filters.FilterEnvioSucursal;
import cl.abcdin.sst.model.filters.FilterFactura;
import cl.abcdin.sst.model.filters.FilterFamilia;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.FilterHisotrial;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterParte;
import cl.abcdin.sst.model.filters.FilterProcedimiento;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterProveedor;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.FilterReglaHistorica;
import cl.abcdin.sst.model.filters.FilterRutaServicioTecnico;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.FilterTipoCambio;
import cl.abcdin.sst.model.filters.FilterTipoFallas;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.FilterUbicacionInterna;
import cl.abcdin.sst.model.filters.FilterUsuario;
import cl.abcdin.sst.model.filters.FilterZona;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.CondicionRecepcion;
import cl.abcdin.sst.model.vo.CrearOTGP;
import cl.abcdin.sst.model.vo.FamiliaExcluida;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.model.vo.Modulo;
import cl.abcdin.sst.model.vo.STecnicoEjecutiva;
import cl.abcdin.sst.model.vo.ServicioTecnicoProducto;
import cl.abcdin.sst.model.vo.TreeView;
import cl.abcdin.sst.service.AdministracionService;
import cl.abcdin.sst.service.BodegaService;
import cl.abcdin.sst.service.BuscadoresService;
import cl.abcdin.sst.service.EjecutivaService;
import cl.abcdin.sst.service.EnvioRecepcionService;
import cl.abcdin.sst.service.MovimientosInternosService;
import cl.abcdin.sst.service.OrdenTrabajoService;
import cl.abcdin.sst.service.SessionStorageService;
import cl.abcdin.sst.service.SucursalService;
import cl.abcdin.sst.service.UtilService;
import cl.abcdin.sst.utils.Constants;

public class SSTFacade {
	
	private static SucursalService sucursalService;
	private static AdministracionService administracionService;
	private static ILoginService loginService;
	private static UtilService utilService;
	private static EjecutivaService ejecutivaService;
	private static BodegaService bodegaService;
	private static EnvioRecepcionService envioRecepcionService;
	private static BuscadoresService buscadoresService;
	private static OrdenTrabajoService ordenTrabajoService;
	private static MovimientosInternosService movimientosInternosService;
	
	/*AdministracionService*/
	public Boolean isProductoOnUbicacionInternaCD(Integer idProducto, String codigoUbicacion) throws Exception{
		return administracionService.isProductoOnUbicacionInternaCD(idProducto, codigoUbicacion);
	}
	
	public void sucursalesAreAvailable(List<String> familias, List<String> lineas, List<Integer> productos, List<Integer> sucursales, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception{
		administracionService.sucursalesAreAvailable(familias, lineas, productos, sucursales, ubicacionInternaDetalle);
	}
	
	public List<UbicacionInterna> listTodasUbicacionesInternasCD() throws Exception {
		return administracionService.listTodasUbicacionesInternasCD();
	}
	
	public List<ServicioTecnico> listServiciosTecnicosFromRutasSTByRuta(RutaServicioTecnico rutaServicioTecnico) throws Exception {
		return administracionService.listServiciosTecnicosFromRutasSTByRuta(rutaServicioTecnico);
	}
	
	public List<Comuna> listComunasByIdProvincia(Long idProvincia) throws Exception {
		return administracionService.listComunasByIdProvincia(idProvincia);
	}
	
	public Integer saveProductoForUbicacionInterna(UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		return administracionService.saveProductoForUbicacionInterna(ubicacionInternaDetalle);
	}
	
	public ListRange listLineasByCodigoUbicacionInterna(String codigo) throws Exception{
		return administracionService.listLineasByCodigoUbicacionInterna(codigo);
	}
	
	public List<ServicioTecnico> listServiciosTecnicosForRutasSTByFilter(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return administracionService.listServiciosTecnicosForRutasSTByFilter(filterServicioTecnico);
	}
	
	public ListRange listFamiliasByIdUbicacionInterna(String codigo) throws Exception {
		return administracionService.listFamiliasByIdUbicacionInterna(codigo);
	}
	
	public ListRange listFamiliaLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception{
		return administracionService.listFamiliaLessCodigoUbicacion(codigo, gridControl);
	}
	
	public ListRange listSucursalLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception{
		return administracionService.listSucursalLessCodigoUbicacion(codigo, gridControl);
	}
	
	public ListRange listProductosLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception{
		return administracionService.listProductosLessCodigoUbicacion(codigo, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllSucursalLessCodigoUbicacion(String codigo) throws Exception {
		return administracionService.listAllSucursalLessCodigoUbicacion(codigo);
	}
	
	public List<CheckForFlexigrid> listAllFamiliaLessCodigoUbicacion(String codigo) throws Exception {
		return administracionService.listAllFamiliaLessCodigoUbicacion(codigo);
	}
	
	public List<CheckForFlexigrid> listAllProductosLessCodigoUbicacion(String codigo) throws Exception {
		return administracionService.listAllProductosLessCodigoUbicacion(codigo);
	}
	 
	public ListRange listSucursalesByIdUbicacionInterna(String codigo) throws Exception{
		return administracionService.listSucursalesByIdUbicacionInterna(codigo);
	}
	
	public ListRange listProductosByIdUbicacionInterna(String codigo) throws Exception {
		return administracionService.listProductosByIdUbicacionInterna(codigo);
	}
	
	public void eliminarProductosFromUbicacionInternaDetalle(List<Integer> productos, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.eliminarProductosFromUbicacionInternaDetalle(productos, ubicacionInternaDetalle, getUsuarioSession());
	}
	
	public void eliminarSucursalesFromUbicacionInternaDetalle(List<Integer> sucursales, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception{
		administracionService.eliminarSucursalesFromUbicacionInternaDetalle(sucursales, ubicacionInternaDetalle, getUsuarioSession());
	}
	
	public void eliminarFamiliasFromUbicacionInternaDetalle(List<String> familias, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.eliminarFamiliasFromUbicacionInternaDetalle(familias, ubicacionInternaDetalle, getUsuarioSession());
	}
	
	public void eliminarLineasFromUbicacionInternaDetalle(List<String> lineas, UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.eliminarLineasFromUbicacionInternaDetalle(lineas, ubicacionInternaDetalle, getUsuarioSession());
	}
	
	public void saveLineasForUbicacionInterna(List<String> lineas,UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.saveLineasForUbicacionInterna(lineas, ubicacionInternaDetalle);
	}
	
	public void saveFamiliasForUbicacionInterna(List<String> familias,UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.saveFamiliasForUbicacionInterna(familias, ubicacionInternaDetalle);
	}
	
	public void saveSucursalesForUbicacionInterna(List<Integer> sucursales,UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		administracionService.saveSucursalesForUbicacionInterna(sucursales, ubicacionInternaDetalle);
	}
	
	public UbicacionInternaCD getUbicacionInternaCDByCodigo(String codigo) throws Exception {
		return administracionService.getUbicacionInternaCDByCodigo(codigo);
	}
	
	public List<CheckForFlexigrid> listAllLineasByCodigoUbicacionInterna(String codigo) throws Exception {
		return administracionService.listAllLineasByCodigoUbicacionInterna(codigo);
	}
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosCheck(FilterServicioTecnico filter) throws Exception {
		return administracionService.listAllServiciosTecnicosCheck(filter);
	}
	
	public Integer updateModificaUbicacionInternaCD(UbicacionInternaCD ubicacionInternaCD) throws Exception{
		return administracionService.updateModificaUbicacionInternaCD(ubicacionInternaCD,getUsuarioSession());
	}
	
	public Accesorio getAccesorioById(Long id) throws Exception {
		return administracionService.getAccesorioById(id);
	}
	
	public void updateAccesoriosByEstado(Accesorio accesorio) throws Exception {
		administracionService.updateAccesoriosByEstado(accesorio);
	}

	public void saveAccesorio(Accesorio accesorio) throws Exception {
		administracionService.saveAccesorio(accesorio);
	}
 
	public ListRange listAccesoriosByFilterForAdministracion(FilterAccesorio filter, GridControl gridControl) throws Exception {
		return administracionService.listAccesoriosByFilterForAdministracion(filter, gridControl);
	}
	
	public Usuario saveUsuario(Usuario usuario, Boolean nuevo) throws Exception {
		return administracionService.saveUsuario(usuario, nuevo);
	}
	 
	public Ubicacion saveUbicacion(Ubicacion ubicacion, Boolean nuevo) throws Exception {
		return administracionService.saveUbicacion(ubicacion, nuevo);
	}
	
	public List<Rol> listMyRoles() throws Exception {
		return administracionService.listRolesByUsuario(getUsuarioSession());
	}

	public ListRange listRoles(GridControl gridControl) throws Exception {
		return administracionService.listRoles(gridControl);
	}

	public void updateEstadoRol(Rol rol) throws Exception {
		administracionService.updateEstadoRol(rol);
	}
	
	public void updateServicioTecByubicacion(ServicioTecnico servicioTecnico) throws Exception{
		administracionService.updateServicioTecByubicacion(servicioTecnico);
	}
	
	public void updateServicioTecnico(ServicioTecnico servicioTecnico) throws Exception{
		administracionService.updateServicioTecnico(servicioTecnico);
	}
	
	public void updateVigenciaServTecnico(ServicioTecnicoProducto servicioTecnicoProducto) throws Exception{
		administracionService.updateVigenciaServTecnico(servicioTecnicoProducto);
	}
	
	public void getIdComunaByIdUbicacion(Long idc) throws Exception{
		administracionService.getIdComunaByIdUbicacion(idc);
	}
	
	public Rol updateRol(Rol rol, List<Modulo> modulos) throws Exception {
		return administracionService.updateRol(rol, modulos);
	}
	
	public Modulo saveConfiguracionPestana(Rol rol, Modulo modulo, List<Modulo> subModulos) throws Exception {
		return administracionService.saveConfiguracionPestana(rol, modulo, subModulos);
	}
	
	public Rol saveRol(Rol rol) throws Exception {
		return administracionService.saveRol(rol);
	}
	
	public Rol getRolById(Long id) throws Exception {
		return administracionService.getRolById(id);
	}
	
	public List<Rol> listRolesActivos() throws Exception {
		return administracionService.listRolesActivos();
	}
	
	public List<Rol> listRolesByUsuario(Usuario usuario) throws Exception {
		return administracionService.listRolesByUsuario(usuario);
	}
	
	public Zona getZonaById (Long id) throws Exception {
		return administracionService.getZonaById(id);
	}

	public ListRange listZonas(GridControl gridControl) throws Exception {
		return administracionService.listZonas(gridControl);
	}

	public List<Zona> listAllZonas() throws Exception {
		return administracionService.listAllZonas();
	}
	
	public List<TreeView> listZonasAsTreeView() throws Exception {
		return administracionService.listZonasAsTreeView();
	}
	
	public List<TreeView> listSucursalesByZonaAsTreeView(Long idZona) throws Exception {
		return administracionService.listSucursalesByZonaAsTreeView(idZona);
	}
	
	public List<TreeView> listLineaAsTreeView() throws Exception {
		return administracionService.listLineaAsTreeView();
	}

	public List<Linea> listAllLineas() throws Exception {
		return administracionService.listAllLineas();
	}
	
	public ListRange listLineasLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		return administracionService.listLineasLessCodigoUbicacion(codigo, gridControl);
	}
	
	public List<TreeView> listFamiliaByFilterAsTreeView(FilterProducto filter) throws Exception {
		return administracionService.listFamiliaByFilterAsTreeView(filter);
	}
	
	public ListRange listProductosByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
		return administracionService.listProductosByFilter(filterProducto, gridControl);
	}
	
	public ListRange listProcedimientosProductosByFilter(FilterProcedimiento filter, GridControl gridControl) throws Exception {
		return administracionService.listProcedimientosProductosByFilter(filter, gridControl);
	}
	
	public List<TreeView> listProductoByFilterAsTreeView(FilterProducto filter) throws Exception {
		return administracionService.listProductoByFilterAsTreeView(filter);
	}
	
	public void saveZona (Zona zona) throws Exception {
		administracionService.saveZona(zona, getUsuarioSession());
	}
	
	public ListRange listProductosParaExcluirCCByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
		return administracionService.listProductosParaExcluirCCByFilter(filterProducto, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllProductosCCCheck(FilterProducto filterProducto) throws Exception {
		return administracionService.listAllProductosCCCheck(filterProducto);
	}
	
	public ListRange listRutasServicioTecnico(FilterRutaServicioTecnico filterRutaServicioTecnico, GridControl gridControl) throws Exception {
	    return administracionService.listRutasServicioTecnico(filterRutaServicioTecnico, gridControl);
	}
//	
	public List<CheckForFlexigrid> listAllRutasServicioTecnicoCheck() throws Exception {
	    return administracionService.listAllRutasServicioTecnicoCheck();
	}
	
	public List<Ubicacion> listSucursalesNotInZonaByCodigoZona(String codigo) throws Exception {
		return administracionService.listSucursalesNotInZonaByCodigoZona(codigo);
	}
	
	public List<Columna> listColumnasIndicador(FilterColumna filter) throws Exception {
		return utilService.listColumnasIndicador(filter);
	}
	
	public List<Columna> listColumnasNotInIndicador(FilterColumna filter) throws Exception {
		return utilService.listColumnasNotInIndicador(filter);
	}
		
	public List<Columna> getidColumnasById (Integer id) throws Exception {
		return utilService.getidColumnasById(id);
	}
	
	public List<TiendaZona> listSucursalesByCodigoZona(String codigo) throws Exception {
		return administracionService.listSucursalesByCodigoZona(codigo);
	}
	
	public void saveSucursalesZona(List<Ubicacion> sucursales, Zona zona) throws Exception {
		administracionService.saveSucursalesZona(sucursales, zona, getUsuarioSession());
	}
	
	public ListRange listSucursalesZonaByFilter(FilterZona filter, GridControl gridControl) throws Exception {
		return administracionService.listSucursalesZonaByFilter(filter, gridControl);
	}
	
	public ListRange listOTDescripcionFallas (FilterOT filterOT, GridControl gridControl) throws Exception {
		return administracionService.listOTDescripcionFallas(filterOT, gridControl);
	}
	
	public Long saveReglaComercial(ReglaComercial reglaComercial) throws Exception {
		return administracionService.saveReglaComercial(reglaComercial, getUsuarioSession());
	}
	
	public ListRange listReglasComercialesByFilter(FilterRegla filter, GridControl gridControl) throws Exception {
		return administracionService.listReglasComercialesByFilter(filter, gridControl);
	}
	
	public List<ReglaComercial> listReglasComercialByFilter(FilterRegla filter) throws Exception {
		return administracionService.listReglasComercialesByFilter(filter);
	}
	
	public ReglaComercial getReglaComercialById(Long id) throws Exception {
		return administracionService.getReglaComercialById(id);
	}
	
	public ReglaComercial getReglaComercialHisoricaById(Long id) throws Exception {
		return administracionService.getReglaComercialHisoricaById(id);
	}
	
	public List<Producto> listProductosByFilter(FilterProducto filter) throws Exception {
		return administracionService.listProductosByFilter(filter);
	}
//    modelo
	public ListRange listProductosByServicioTecnico(FilterProducto filter, GridControl gridControl) throws Exception {
		return administracionService.listProductosByServicioTecnico(filter, gridControl);
	}
//	  modelo
	public List<CheckForFlexigrid> listAllProductosServicioTecnicoCheck(FilterProducto filter) throws Exception {
		return administracionService.listAllProductosServicioTecnicoCheck(filter);
	}
	
	public List<CheckForFlexigrid> listAllProductosByFilterCheck(FilterProducto filterProducto) throws Exception {
		return administracionService.listAllProductosByFilterCheck(filterProducto);
	}
	
	public List<CheckForFlexigrid> listAllDestinosUbicacionCheck(FilterUbicacion filterUbicacion) throws Exception {
		return administracionService.listAllDestinosUbicacionCheck(filterUbicacion);
	}
	
	public ListRange listTipoFallasByFilterFallas(FilterTipoFallas filter,GridControl gridControl) throws Exception {
		return administracionService.listTipoFallasByFilterFallas(filter,gridControl);
	}
	
	public List<TipoFallas> listTipoFallasByFilterFallas(FilterTipoFallas filter) throws Exception{
		return administracionService.listTipoFallasByFilterFallas(filter);
	}

	public List<TipoFallas> listTipoFallasNotExistsByFilterFallas(FilterTipoFallas filter) throws Exception{
		return administracionService.listTipoFallasNotExistsByFilterFallas(filter);
	}
	
	public List<Usuario> listUsuariosReglasComerciales() throws Exception {
		return administracionService.listUsuariosReglasComerciales();
	}
	
	public ListRange listReglasComercialesHistoricasByFilter(FilterReglaHistorica filter, GridControl gridControl) throws Exception {
		return administracionService.listReglasComercialesHistoricasByFilter(filter, gridControl);
	}
	
	public void saveCondicionRecepcion(String descripcion) throws Exception {
		administracionService.saveCondicionRecepcion(descripcion, getUsuarioSession());
	}
	
	public CondicionRecepcion getCondicionRecepcion() throws Exception {
		return administracionService.getCondicionRecepcion();
	}
	
	public ListRange listFamiliasExcluidasByFilter(FilterProducto filter, GridControl gridControl) throws Exception {
		return administracionService.listFamiliasExcluidasByFilter(filter, gridControl);
	}
	
	public void saveFamiliasExcluidas(List<FamiliaExcluida> seleccionadas, List<FamiliaExcluida> noSeleccionadas) throws Exception{
		administracionService.saveFamiliasExcluidas(seleccionadas, noSeleccionadas, getUsuarioSession());
	} 
	
	public ListRange listFamiliasExcluidasSerieByFilter(FilterProducto filter, GridControl gridControl) throws Exception {
		return administracionService.listFamiliasExcluidasSerieByFilter(filter, gridControl);
	}
	
	public void saveFamiliasExcluidasSerie(List<FamiliaExcluida> seleccionadas, List<FamiliaExcluida> noSeleccionadas) throws Exception{
		administracionService.saveFamiliasExcluidasSerie(seleccionadas, noSeleccionadas, getUsuarioSession());
	}	

	public List<TipoCambio> listAllTipoCambio()throws Exception{
		return administracionService.listAllTipoCambio();
	}
	
	public ListRange listUsuarios(FilterUsuario filterUsuario, GridControl gridControl) throws Exception {
		return administracionService.listUsuarios(filterUsuario, gridControl);
	}
	
	public ListRange listServicioTecnico(FilterServicioTecnico filterServicioTecnico, GridControl gridControl)throws Exception {
		return administracionService.listServicioTecnico(filterServicioTecnico, gridControl );
	}
	
	public ListRange listServicioTecnicoProducto(FilterServicioTecnico filterServicioTecnico, GridControl gridControl)throws Exception {
		return administracionService.listServicioTecnicoProducto(filterServicioTecnico, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosProductoCheck(FilterServicioTecnico filterServicioTecnico)throws Exception {
		return administracionService.listAllServiciosTecnicosProductoCheck(filterServicioTecnico);
	}
	
	public ListRange listDestinosUbicacion(FilterUbicacion filterUbicacion, GridControl gridControl)throws Exception {
		return administracionService.listDestinosUbicacion(filterUbicacion, gridControl );
	}
	
	public void updateEstadoUsuario(Usuario usuario) throws Exception {
		administracionService.updateEstadoUsuario(usuario);
	}
	
	public ListRange listFamiliasParaExcluirCC(FilterFamilia filterFamilia, GridControl gridControl) throws Exception {
		return administracionService.listFamiliasParaExcluirCC(filterFamilia, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllFamiliaCheck(FilterFamilia filterFamilia) throws Exception{
		return administracionService.listAllFamiliaCheck(filterFamilia);
	}
	
	public void updateFamiliasExcluidas(List<FamiliaExcluidaControlCalidad> familiaExcluidaControlCalidad) throws Exception {
		administracionService.updateFamiliasExcluidas(familiaExcluidaControlCalidad, getUsuarioSession());
	} 
	
	public void updateTipoFallaByEstado(TipoFallas tipoFalla) throws Exception {
        administracionService.updateTipoFallaByEstado(tipoFalla);
    }
    
    public void saveTipoFalla(TipoFallas tipoFalla) throws Exception {
        administracionService.saveTipoFalla(tipoFalla);
    }
    
    public void saveTipoFallaForAccesorios(Accesorio accesorio, List<TipoFallas> tipoFallas) throws Exception {
        administracionService.saveTipoFallaForAccesorios(accesorio, tipoFallas);
    }
    
    public TipoFallas getTipoFallas(Integer id) throws Exception {
        return administracionService.getTipoFallas(id);
    }
    
    public void updateParteByEstado(Parte parte) throws Exception {
        administracionService.updateParteByEstado(parte);
   }
    
    public void updateProductosExcluidosCC(List<ProductoExcluido> productosExcluidos) throws Exception {
    	administracionService.updateProductosExcluidosCC(productosExcluidos, getUsuarioSession());
    }
	
	
	public Parte getParteById(Integer id) throws Exception {
		return administracionService.getParteById(id);
	}
	
	public ListRange listServicioTecnicoForEjecutiva(FilterEjecutiva filter,GridControl gridControl) throws Exception {
		return administracionService.listServicioTecnicoForEjecutiva(filter,gridControl);
	}
	
	public void updateSTecnicoEjecutiva(STecnicoEjecutiva stEjecutiva)throws Exception{
		administracionService.updateSTecnicoEjecutiva(stEjecutiva);
	}
	
	public STecnicoEjecutiva getExisteEjecutivaSTecnico(String id)throws Exception{
		return administracionService.getExisteEjecutivaSTecnico(id);
	}
	
	public ListRange listUbicacionInternaCDByFilter(FilterUbicacionInterna filterUbicacionInterna, GridControl gridControl) throws Exception {
	    return administracionService.listUbicacionInternaCDByFilter(filterUbicacionInterna, gridControl);
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCD() throws Exception {
		return administracionService.listUbicacionInternaCD();
	}
	
	public List<CheckForFlexigrid> listAllIdLineaLessCodigoUbicacion(String codigo) throws Exception {
		return administracionService.listAllIdLineaLessCodigoUbicacion(codigo);
	}
	
	public List<Linea> listLineasByCodigo(List<String> codigos) throws Exception{
		return administracionService.listLineasByCodigo(codigos);
	}
	
	public Integer saveUbicacionInternaCD(UbicacionInterna ubicacionInterna) throws Exception{
		return administracionService.saveUbicacionInternaCD(ubicacionInterna, getUsuarioSession());
	}
	
	/*EjecutivaService*/
	public Transportista getTransportistaByUltimaRecepcionOT(Long idOT)throws Exception{
		return ejecutivaService.getTransportistaByUltimaRecepcionOT(idOT);
	}
	
	public void updateIndicadores (List<Indicador> indicadores) throws Exception {
		ejecutivaService.updateIndicadores(indicadores);
	}
	
	public void updateParametros (List <Parametro> parametros) throws Exception {
		ejecutivaService.updateParametros(parametros);
	}
	
	public OrdenTrabajo getOTById(Long idOT) throws Exception {
		return ejecutivaService.getOTById(idOT);
	}
	
	public Resultado calcularSemaforo() throws Exception {
		return ejecutivaService.calcularSemaforo(getUsuarioSession());
	}
	
	public List<Indicador> listIndicadoresNivelServicio(FilterIndicador filterIndicador) throws Exception {
		return ejecutivaService.listIndicadoresNivelServicio(filterIndicador);
	}
	
	public Resultado actualizarVista(String vista) throws Exception {
		return ejecutivaService.actualizarVista(vista);
	}

	public Resultado calcularIndicadores() throws Exception {
		return ejecutivaService.calcularIndicadores(getUsuarioSession());
	}

	public Resultado procesoBodega() throws Exception {
		return ejecutivaService.procesoBodega(getUsuarioSession());
	}

	public Resultado procesoProveedores() throws Exception {
		return ejecutivaService.procesoProveedores(getUsuarioSession());
	}
	
	public List<Indicador> listIndicadoresEjecutivaGestion(FilterIndicador filterIndicador) throws Exception {		
		return ejecutivaService.listIndicadoresEjecutivaGestion(filterIndicador);
	}
	
	public List<Indicador> listIndicadoresEjecutivaFacturacion(FilterIndicador filter) throws Exception {
		return ejecutivaService.listIndicadoresEjecutivaFacturacion(filter);
	}
	
	public List<Indicador> listIndicadoresEjecutivaMarca(FilterIndicador filter) throws Exception {		
		return ejecutivaService.listIndicadoresEjecutivaMarca(filter);
	}
	
	public ListRange listOTRecepcionBodegaByFilter(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception{
		return ejecutivaService.listOTRecepcionBodegaByFilter(filterRecepcionProducto, gridControl);
	}
	
	public ListRange listOTCambioAuomaticoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception{
		return ejecutivaService.listOTCambioAuomaticoByFilter(filterOT, gridControl);
	}
	
	public Indicador getIndicadorById (Integer id) throws Exception {
		return ejecutivaService.getIndicadorById(id);
	}
	
	public Procedimiento getProcedimientoById (Long id) throws Exception{
		return administracionService.getProcedimientoById(id);
	}
	
	public Procedimiento getProcedimientoByIdProducto (Integer idProducto) throws Exception{
		return administracionService.getProcedimientoByIdProducto(idProducto);
	}
	
	public ListRange listFacturas(GridControl gridControl) throws Exception {
		return ejecutivaService.listFactura(gridControl);
	}
	
	public List<Transportista> listTransportista() throws Exception {
		return ejecutivaService.listTransportista();
	}
	
	public List<Marca> listMarca() throws Exception {
		return ejecutivaService.listMarca();
	}
	
	public List<Proveedor> listProveedor() throws Exception {
		return ejecutivaService.listProveedor();
	}
	
	public List<Familia> listFamilia() throws Exception {
		return ejecutivaService.listFamilia();
	}
	
	public List<Familia> listFamiliasByFilter(FilterProducto filter) throws Exception {
		return administracionService.listFamiliasByFilter(filter);
	}
	
	public Integer saveRutaServicioTecnico(RutaServicioTecnico rutaServicioTecnico) throws Exception{
	    return administracionService.saveRutaServicioTecnico(rutaServicioTecnico, getUsuarioSession());
	}
	
	public List<Provincia> listProvinciaByIdRegion(Integer idRegion) throws Exception {
	    return administracionService.listProvinciaByIdRegion(idRegion);
	}
	
	public void saveRutaServicioTecnicoDetalle(RutaServicioTecnicoDetalle rutaServicioTecnicoDetalle, List<Integer> sTecnicos) throws Exception {
	    administracionService.saveRutaServicioTecnicoDetalle(rutaServicioTecnicoDetalle, sTecnicos, getUsuarioSession());
	}
	
	public List<Bitacora> listAllBitacorasByIdOT (Long idOt) throws Exception {
		return ejecutivaService.listAllBitacorasByIdOT(idOt);
	}

	public List<Bitacora> listResumenBitacorasByIdOT (Long idOt) throws Exception {
		return ejecutivaService.listResumenBitacorasByIdOT(idOt);
	}
	
	public void AceptarFactura(List<Factura> listafacturas) throws Exception {
		ejecutivaService.AceptarRechazarFactura(Constants.FACTURA_ACEPTADA, listafacturas, getUsuarioSession(),getUbicacionSession());	
	}
	
	public void RechazarFactura(List<Factura> listafacturas) throws Exception {
		ejecutivaService.AceptarRechazarFactura(Constants.FACTURA_RECHAZADA, listafacturas, getUsuarioSession(),getUbicacionSession());	
	}
	
	public void deshacerFactura(List<Factura> listafacturas)throws Exception {
		ejecutivaService.deshacerFactura(listafacturas, getUsuarioSession());	
	}
	
	public void asignarFactura(List<Factura> listafacturas, Long numeroFactura, Date fechaEmision)throws Exception {
		ejecutivaService.asignarFactura(listafacturas, numeroFactura, fechaEmision, getUsuarioSession());	
	}
	
	public void procesarOW(List<Factura> listafacturas) throws Exception {
		ejecutivaService.procesarOW(Constants.FACTURA_PROCESO_OW, listafacturas, getUsuarioSession(),getUbicacionSession());	
	}
	
	public List<Gestion> listGestionesByIdOT(Long idOT)throws Exception {
		return ejecutivaService.listGestionesByIdOT(idOT);
	}	
	
	public ListRange listResumenFacturasByOT(FilterFactura filter, GridControl gridControl) throws Exception {		
		return ejecutivaService.listResumenFacturasByOT(gridControl, filter);
	}
	
	public Cliente getClienteByOT(Long idOT) throws Exception {
		return ejecutivaService.getClienteByOT(idOT);
	}
	
	public void updateClienteByOT( Long idOT, Cliente cliente) throws Exception {
		ejecutivaService.updateClienteByOT(idOT,getUsuarioSession(), cliente);
	}
	
	public Integer updateSTecnicoOT(OrdenTrabajo ordenTrabajo)  throws Exception {
		return ejecutivaService.updateSTecnicoOT(ordenTrabajo, getUsuarioSession());
	}
	
	public Integer updateEstadoOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return ejecutivaService.updateEstadoOT(ordenTrabajo);
	}
	
	public void saveGestion(Long idOT, String descripcion) throws Exception {
		ejecutivaService.saveGestion(idOT,getUsuarioSession(),descripcion);
	}
	
	public void saveGestionWithFecha(Long idOT, String descripcion, Date fecha) throws Exception {
		ejecutivaService.saveGestion(idOT, getUsuarioSession(), descripcion, fecha);
	}
	
	public void asignarServicioTecnico(ServicioTecnico servicioTecnico) throws Exception {
		ordenTrabajoService.asignarServicioTecnico(servicioTecnico, getUsuarioSession());
	}
	
	public OrdenTrabajo getEstadoOT(Long idOT) throws Exception {
		return ejecutivaService.getEstadoOT(idOT);
	}
	
	public ServicioTecnico getServicioTecnicoByOT(Long idOT) throws Exception {
		return ejecutivaService.getServicioTecnicoByOT(idOT);
	}
	
	public ServicioTecnico getSTecnicoById(Long id) throws Exception {
		return ejecutivaService.getSTecnicoById(id);
	}

	public List<ServicioTecnico> listServicioTecnicoByComuna(FilterServicioTecnico filter) throws Exception {
		return ejecutivaService.listServicioTecnicoByComuna(filter);
	}
	
//	public List<ServicioTecnico> listServicioTecnico() throws Exception {
//		return ejecutivaService.listServicioTecnico();
//	}
	
	public List<ServicioTecnico> listServicioTecnicoGM() throws Exception {
		return ejecutivaService.listServicioTecnicoGM();
	}
	
	public ListRange listOtsFactura(FilterFactura filter, GridControl gridControl) throws Exception {		
		return ejecutivaService.listOtsFactura(filter, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllOtsFacturaCheck(FilterFactura filter) throws Exception {	
		return ejecutivaService.listAllOtsFacturaCheck(filter);
	}
	
	public ListRange listFacturasIndicadoresEjecutivaFacturacion(FilterIndicador filter, GridControl gridControl) throws Exception {		
		return ejecutivaService.listFacturasIndicadoresEjecutivaFacturacion(filter, gridControl);
	}
	
	public Cambio getCambioByOT(Long idOT)  throws Exception {
		return ejecutivaService.getCambioByOT(idOT);
	} 
	
	public void updateCerrarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		ejecutivaService.updateCerrarOT(ordenTrabajo, getUsuarioSession());
	}
	//este va hacia proveedorDAO
//	public ListRange listProveedores(FilterProveedor filterProveedor, GridControl gridControl) throws Exception {
//		return ejecutivaService.listProveedores(filterProveedor, gridControl);
//	}
	
	public List<Proveedor> listProveedores() throws Exception {
		return ejecutivaService.listProveedores();
	}

	public List<Recepcion> listRecepcionesByOT(Long idOT) throws Exception {
		return ejecutivaService.listRecepcionesByOT(idOT);
	}
	
	public void autorizarCambioBodega(Cambio cambio) throws Exception {
		ejecutivaService.autorizarCambioBodega(cambio, getUsuarioSession());
	}
	
	public void autorizarCambioProveedor(Cambio cambio) throws Exception {
		ejecutivaService.autorizarCambioProveedor(cambio, getUsuarioSession());
	}
	
	public void autorizarCambioTransporte(Cambio cambio) throws Exception {
		ejecutivaService.autorizarCambioTransporte(cambio, getUsuarioSession());
	}
	
	public void updateDesactivarOT(OrdenTrabajo ordenTrabajo) throws Exception{
		ejecutivaService.updateDesactivarOT(ordenTrabajo, getUsuarioSession());
	}
	
	public PasosOT getPasosOT(Long idOT) throws Exception {
		return ejecutivaService.getPasosOT(idOT);
	}
	
	public OrdenTrabajo getAntesFacturarByOT(Long idOT) throws Exception {
		return ejecutivaService.getAntesFacturarByOT(idOT);
	}
	
	public Integer updateAntesEnviarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return ejecutivaService.updateAntesEnviarOT(ordenTrabajo);
	}
	
	public void antesEnviarOT(OrdenTrabajo ordenTrabajo,List<Accesorio> accesorios) throws Exception {
		ejecutivaService.antesEnviarOT(ordenTrabajo,accesorios,getUsuarioSession());
	}
	
	public List<Accesorio> listAccesoriosByOT(Long idOT) throws Exception {
		return ejecutivaService.listAccesoriosByOT(idOT);
	}
	
	public List<Parte> listPartesOTByOT(Long idOT) throws Exception {
		return ejecutivaService.listPartesOTByOT(idOT);
	}
	
	public Integer existeOTAntesEnviar(Long idOT) throws Exception {
		return ejecutivaService.existeOTAntesEnviar(idOT);
	}
	
	public void saveCambioPrioridadSemaforo(List<OrdenTrabajo> listaOts, String cambioPrioridad) throws Exception {
		ejecutivaService.saveCambioPrioridadSemaforo(listaOts, cambioPrioridad, getUsuarioSession());
	}
		
	
	public Usuario moverOrden(List<OrdenTrabajo> ordenes) throws Exception{
		return ejecutivaService.moverOrden(ordenes, getUsuarioSession());
	}
	
	public Usuario moverOrdenSupervisora(List<OrdenTrabajo> ordenes, Long idEjecutiva) throws Exception{
		return ejecutivaService.moverOrden(ordenes, getUsuarioById(idEjecutiva));
	}
	
	public void GenerarFactura(List<OrdenTrabajo> listaOTs) throws Exception {
		ejecutivaService.GenerarFactura(listaOTs, getUsuarioSession());
	}
	
	public Cliente getClienteByFilter(Documento documento) throws Exception{
		return ejecutivaService.getClienteByFilter(documento);
	}
	
	public Cliente getClienteByRut(Cliente cliente)throws Exception {
		return ejecutivaService.getClienteByRut(cliente);
	}
	
	public OrdenTrabajo getOtByIdOrNumeroAtencion(FilterOT filter)throws Exception{
		return ejecutivaService.getOtByIdOrNumeroAtencion(filter);
	}
	
	public void autorizarCambioGarantiaMaster(OrdenTrabajo ordenTrabajo)throws Exception{
		 ejecutivaService.autorizarCambioGarantiaMaster(ordenTrabajo, getUbicacionSession(),getUsuarioSession());
	}
	
	public Factura getFacturaById(Integer id)throws Exception{
		 return ejecutivaService.getFacturaById(id);
	}
	
	public Factura getPasosFacturaById(Integer id)throws Exception{
		 return ejecutivaService.getPasosFacturaById(id);
	}
	
	public ListRange listarDetalleFactura(FilterFactura filterFactura) throws Exception {
		return ejecutivaService.listarDetalleFactura(filterFactura);
	}
	/*UtiliService*/
	
	public Modulo getModuloById(Long id) throws Exception {
		return utilService.getModuloById(id);
	}
	
	public List<Parametro> listUbicacionesShowByTipo() throws Exception {
		return utilService.listUbicacionesShowByTipo();
	}
	
	public List<Modulo> listModulosInRolByMacro(Long idRol, String macro) throws Exception {
		return utilService.listModulosInRolByMacro(idRol, macro);
	}
	
	public List<Modulo> listModulosNotInRolByMacro(Long idRol, String macro) throws Exception {
		return utilService.listModulosNotInRolByMacro(idRol, macro);
	}	
	
	public void logClientException(Map<?, ?> e, String message) throws Exception {
		utilService.logClientException(e, message);
	}
	
	public List<Comuna> listComunasConSucursalesByRegion(Long idRegion) throws Exception {
		return utilService.listComunasConSucursalesByRegion(idRegion);
	}
	
	public List<Comuna> listComunasConSTByRegion(Long idRegion) throws Exception {
		return utilService.listComunasConSTByRegion(idRegion);
	}
	
	public List<Parametro> listParametrosByTipo(String tipo) throws Exception{
		return utilService.listParametrosByTipo(tipo);
	}
	
	public Date getDate() throws Exception {
		return utilService.getDate();
	}

	public Date getDateTrunc() throws Exception {
		return utilService.getDateTrunc();
	}	

	public List<Ubicacion> listUbicacionesByIdUsuario() throws Exception {
		return utilService.listUbicacionesByIdUsuario(getUsuarioSession());
	}
	
	public List<Ubicacion> listUbicacionesByUsuario(Usuario usuario) throws Exception {
		return utilService.listUbicacionesByIdUsuario(usuario);
	}
	
	public List<Ubicacion> listDependenciasByFilter(FilterUbicacion filterUbicacion) throws Exception {
		return utilService.listDependenciasByFilter(filterUbicacion);
	}
	
	public List<Ubicacion> listAllByIdUsuario(Usuario usuario) throws Exception {
		return utilService.listAllByIdUsuario(usuario);
	}

	public List<Ubicacion> listSucursales() throws Exception {
		return utilService.listSucursales();
	}

	public List<Ubicacion> listBodegasNotMe() throws Exception {
		return utilService.listBodegasNotMe(getUbicacionSession());
	}
	
	public List<Ubicacion> listBodegas() throws Exception {
		return utilService.listBodegas();
	}
	
	public List<Ubicacion> listUbicacionesByIdAndTipo(String tipo, Long idRegion) throws Exception {
		return utilService.listUbicacionesByIdAndTipo(tipo, idRegion);
	}

	public List<Estado> listEstadosOT() throws Exception {
		return utilService.listEstadosOT();
	}
	
	public List<Estado> listEstadoOTFallaFabricacion() throws Exception {
		return utilService.listEstadoOTFallaFabricacion();
	}
	
	public List<Estado> listEstadoRecepcionGuia() throws Exception {
		return utilService.listEstadoRecepcionGuia();
	}
	
	public List<Parametro> listEstadosAutorizacionCambio() throws Exception{
		return utilService.listEstadosAutorizacionCambio();
	}
	
	public Boolean getCodigoBarraObligatorio() throws Exception {
		return utilService.getCodigoBarraObligatorio();
	}

	public List<Usuario> listEjecutivasMarca() throws Exception {
		return utilService.listEjecutivasMarca();
	}

	public List<Parametro> listTiposUbicacion() throws Exception {
		return utilService.listTiposUbicacion();
	}
	
	public List<Parametro> listTiposDocumento() throws Exception {
		return utilService.listTiposDocumento();
	}

	public List<Parametro> listTiposCambio() throws Exception {
		return utilService.listTiposCambio();
	}

	public List<Parametro> listTiposSemaforo() throws Exception {
		return utilService.listTiposSemaforo();
	}
	
	public List<Parametro> listTiposCambioAutomatico() throws Exception {
		return utilService.listTiposCambioAutomatico();
	}

	public List<Parametro> listTiposOT() throws Exception {
		return utilService.listTiposOT();
	}

	public List<Parametro> listTiposOTOT() throws Exception {
		return utilService.listTiposOTOT();
	}

	public List<Columna> listColumnasByFilter(FilterColumna filter) throws Exception {
		return utilService.listColumnasByFilter(filter, getUsuarioSession());
	}
	
	public List<Columna> listColumnasIndicadorByFilter(FilterColumna filter) throws Exception {
		return utilService.listColumnasIndicadorByFilter(filter);
	}
	
	public void saveSeccionColumnaIndicador(Indicador indicador, Rol rol, Seccion seccion, List<Columna> idcolumna ) throws Exception {
		utilService.saveSeccionColumnaIndicador(indicador, rol, seccion, idcolumna);
	}
	
	public void saveSeccionColumna(SeccionColumna seccionColumna, Usuario usuario) throws Exception {
		utilService.saveSeccionColumna(seccionColumna, getUsuarioSession());
}
	
	public void updateOrdenColumnasSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		utilService.updateOrdenColumnasSeccionColumna(seccionColumna, getUsuarioSession());
	}
	
	public void deleteSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		utilService.deleteSeccionColumna(seccionColumna, getUsuarioSession());
	}	
	
	public Modulo getModuloInicialByUsuario() throws Exception {		
		return utilService.getModuloInicialByUsuario(getUsuarioSession());
	}

	public List<Modulo> listModulosMenu() throws Exception {		
		return utilService.listModulosMenu(getUsuarioSession());
	}
	
	public Modulo getModuloByNombre(String modulo) throws Exception {		
		return utilService.getModuloByNombre(modulo);
	}	
	
	public List<Modulo> listSubModuloByFilter(String nombreModulo) throws Exception {
		return utilService.listSubModuloByFilter(nombreModulo, getUsuarioSession());
	}
	
	public List<Modulo> listSubModulosInModuloByRol(Long idModulo, Long idRol) throws Exception {
		return utilService.listSubModulosInModuloByRol(idModulo,idRol);
	}
	
	public List<Modulo> listSubModulosNotInModuloByRol(Long idModulo, Long idRol) throws Exception {
		return utilService.listSubModulosNotInModuloByRol(idModulo,idRol);
	}
	
	public Modulo getModuloByNombreUsuario(String modulo) throws Exception {		
		return utilService.getModuloByNombreUsuario(modulo, getUsuarioSession());
	}
	
	public List<Macro> listMacros() throws Exception {
		return utilService.listMacros();
	}	

	public Usuario getUsuarioById(Long id) throws Exception {
		return utilService.getUsuarioById(id);
	}
	
	public List<Region> listRegiones() throws Exception{
		return utilService.listRegiones();
	}
	
	public List<Comuna> listComunas() throws Exception {
		return utilService.listComunas();
	}
	
	public List<Comuna> listComunasByRegion(Long idRegion) throws Exception {
		return utilService.listComunasByRegion(idRegion);
	}
	
	public List<Comuna> listComunasST() throws Exception {
		return utilService.listComunasST();
	}
	
	public Modulo getModuloByCodigo(String codigo) throws Exception {
		return utilService.getModuloByCodigo(codigo);
	}
	
	public Ubicacion getUbicacionById(Long id) throws Exception{
		return utilService.getUbicacionById(id);
	}
	
	public Ubicacion getCentroDistribucion() throws Exception {
		return utilService.getCentroDistribucion();
	}
	
	public Ubicacion getCentroDistribucionFF() throws Exception {
		return utilService.getCentroDistribucionFF();
	}
	
	public Ubicacion getUbicacionTransportista() throws Exception {
		return utilService.getUbicacionTransportista();
	}
	
	public List<Logistico> listLogisticosRecepcionesCamion() throws Exception{
		return utilService.listLogisticosRecepcionesCamion();
	}
	
	public List<Estado> listEstadoDespacho()throws Exception{
		return utilService.listEstadoDespacho();
	}
	
	public List<Parametro> listProveedorRemateLiquidacion()throws Exception{
		return utilService.listProveedorRemateLiquidacion();
	}
	
	public List<UbicacionInterna> getUbicacionInternaByTipo(String tipo)throws Exception{
		return utilService.getUbicacionInternaByTipo(tipo);
	}
	
	public List<Ubicacion> listUbicacionByTipo(String tipo)throws Exception{
		return utilService.listUbicacionByTipo(tipo);
	}
	
	public List<Ubicacion> listDestinosForIdOrigen()throws Exception{
		return utilService.listDestinosForIdOrigen(getUbicacionSession());
	}
	
	public Boolean isDestinoDeUbicacion(Ubicacion origen, Ubicacion destino) throws Exception{
		return administracionService.isDestinoDeUbicacion(origen, destino);
	}
	
	public Ubicacion getProveedorById(Long id)throws Exception{
		return utilService.getProveedorById(id);
	}
	
	public ListRange listProveedoresByFilter(FilterProveedor filterProveedor, GridControl gridControl)throws Exception{
		return utilService.listProveedoresByFilter(filterProveedor, gridControl);
	}
	
	
	public List<CheckForFlexigrid> listAllProveedoresCheck(FilterProveedor filterProveedor) throws Exception {
		return ejecutivaService.listAllProveedoresCheck(filterProveedor);
	}
	
	/*LoginService*/
	
	public Usuario getUsuarioSession() throws Exception {
		return (Usuario)SessionStorageService.getAttribute(Constants.SESSION_USER);
	}

	public Usuario login(Usuario usuario) throws Exception {
		return loginService.login(usuario, SessionStorageService.getRequest().getRemoteAddr());
	}
	
	public void logout() throws Exception {
	}
	
	public Usuario fullLogin(Usuario usuario) throws Exception {
		return loginService.fullLogin(usuario, usuario.getUbicacion(), SessionStorageService.getRequest().getRemoteAddr());
	}
	
	public Ubicacion getUbicacionSession() throws Exception {
		return (Ubicacion)SessionStorageService.getAttribute(Constants.SESSION_UBICACION);
	}
	
	/*SucursalService*/
	
	public OrdenTrabajo updateOTTareaUrgente(OrdenTrabajo ot)throws Exception{
		return sucursalService.updateOTTareaUrgente(ot);
	}
	public void processGuiasPendientesAgrupadas() throws Exception {
		sucursalService.processGuiasPendientesAgrupadas(getUbicacionSession(), getUsuarioSession());
	}
	
	
	public OrdenTrabajo cerrarOTYCrearOTFallaFabricacion(OrdenTrabajo oT) throws Exception{
		return sucursalService.cerrarOTYCrearOTFallaFabricacion(oT, getUbicacionSession(), getUsuarioSession(), getDate());
	}
	
	public Boolean validarFallaFabricacion(OrdenTrabajo oT)throws Exception{
		return sucursalService.validarFallaFabricacion(oT, getUbicacionSession(), getDateTrunc());
	}
	
	public Boolean validarFallaReiterada(OrdenTrabajo oT) throws Exception{
		return sucursalService.validarFallaReiterada(oT, getUbicacionSession(), getDate());
	}
	
	public Boolean isFamiliaExcluidaNumeroSerieByProducto(Producto producto) throws Exception {
		return sucursalService.isFamiliaExcluidaNumeroSerieByProducto(producto);
	}
	
	public Boolean isFamiliaExcluidaFallaFabricacionByProducto(Producto producto) throws Exception {
		return sucursalService.isFamiliaExcluidaFallaFabricacionByProducto(producto);
	}
	
	public List<Sucursal> listSucursalesByComuna(Long idComuna) throws Exception {
		return sucursalService.listSucursalesByComuna(idComuna);
	}
	
	public Integer updateTicketCambio(OrdenTrabajo oT) throws Exception {
		return sucursalService.updateTicketCambio(oT);
	}
	
	public OrdenTrabajo updateOTFallaReiterada(OrdenTrabajo oT) throws Exception {
		return sucursalService.updateOTFallaReiterada(oT);
	}
	
	public OrdenTrabajo updateOTFRNotaCredito(OrdenTrabajo oT) throws Exception {
		return sucursalService.updateOTFRNotaCredito(oT);
	}
	
	public OrdenTrabajo updateOTCATicketCambio(OrdenTrabajo oT) throws Exception {
		return sucursalService.updateOTCATicketCambio(oT,getUbicacionSession());
	}
	
	public List<Guia> listGuiaDocumentoProductoByDocumento(Documento documento) throws Exception {
		return sucursalService.listGuiaDocumentoProductoByDocumento(documento);
	}
	
	public ListRange listSTecnicoFromProductoByUbicacion(FilterServicioTecnico filterServicioTecnico, GridControl gridControl) throws Exception {
		return sucursalService.listSTecnicoFromProductoByUbicacion(filterServicioTecnico, gridControl, getUbicacionSession());
	}
	
	public ListRange listGuiasPendientesSucursal(FilterGuiasPendientes filterGuiasPendientes, GridControl gridControl) throws Exception {
		return sucursalService.listGuiasPendientesSucursal(filterGuiasPendientes, getUbicacionSession(), gridControl);
	}
	
	
	public ListRange listGuiasPendientesAgrupadasSucursal(GridControl gridControl) throws Exception {
		return sucursalService.listGuiasPendientesAgrupadasSucursal(getUbicacionSession(), gridControl);	
	}
	
	public ListRange listGuiasPendientesAgrupadasFF(GridControl gridControl) throws Exception {
		return envioRecepcionService.listGuiasPendientesAgrupadasFF(getUbicacionSession(), gridControl);
	}
	
	public Documento getDocumentoByIdAndTipo(Documento documento)  throws Exception {
		return sucursalService.getDocumentoByIdAndTipo(documento);
	}
	
	public Documento getDocumentoCompletoByIdAndTipo(Documento documento)  throws Exception {
		return sucursalService.getDocumentoCompletoByIdAndTipo(documento,getUbicacionSession());
	}
	
	public List<Producto> listProductoByTipoDocumentoAndIdDocumento(Documento documento) throws Exception {
		return sucursalService.listProductoByTipoDocumentoAndIdDocumento(documento);
	}
	
	public Producto getProductoById(Integer id) throws Exception {
		return sucursalService.getProductoById(id);
	}
	
	public void asignarProductoAServicioTecnico(ServicioTecnico servicioTecnico, Producto producto, String tipoGarantia) throws Exception {
		administracionService.asignarProductoAServicioTecnico(producto, servicioTecnico, tipoGarantia);
	}
	
	public void asignarProductosAServicioTecnico(ServicioTecnico servicioTecnico, List<CheckForFlexigrid> productos, Boolean isGM, Boolean isGP) throws Exception {
		administracionService.asignarProductosAServicioTecnico(servicioTecnico, productos, isGM, isGP);
	}
	
	public void asignarServicioTecnicoAProductos(Producto producto, List<CheckForFlexigrid> serviciosTecnicos, Boolean isGM, Boolean isGP) throws Exception {
		administracionService.asignarServicioTecnicoAProductos(producto, serviciosTecnicos, isGM, isGP);
	}
	
	public void asignarDestinoAUbicacion(Ubicacion ubicacion, List<CheckForFlexigrid> destinos ) throws Exception {
		administracionService.asignarDestinoAUbicacion(ubicacion, destinos);
	}
	
	public void asignarBodegasAUbicacion (Ubicacion ubicacion, List<Ubicacion> bodegas  ) throws Exception {
		administracionService.asignarBodegasAUbicacion(ubicacion, bodegas);
	}
	
	public void asignarProveedoresAUbicacion (Ubicacion ubicacion, List<CheckForFlexigrid> proveedor  ) throws Exception {
		administracionService.asignarProveedoresAUbicacion(ubicacion, proveedor);
	}
	
	public void eliminarAsignacionServicioTecnico(List<CheckForFlexigrid> idServicioTecnicoProducto) throws Exception{
		administracionService.eliminarAsignacionServicioTecnico(idServicioTecnicoProducto);
	}
	
	public void eliminarAsignacionUbicacion(List<CheckForFlexigrid> idDestino) throws Exception{
		administracionService.eliminarAsignacionUbicacion(idDestino);
	}
	
	public Familia getFamiliaByFilter(FilterProducto filter) throws Exception{
		return administracionService.getFamiliaByFilter(filter);
	}
	
	public ListRange listPartesByFilterPartes(FilterParte filter,GridControl gridControl) throws Exception {
		return administracionService.listPartesByFilterPartes(filter, gridControl);
	}
	
	public void savePartes(Parte parte) throws Exception {
		 administracionService.savePartes(parte);
	}
	
	public Procedimiento saveProcedimiento(Procedimiento procedimiento) throws Exception{
		return administracionService.saveProcedimiento(procedimiento, getUsuarioSession());
	}
	
	
	public CrearOTGP getCrearOTGP(FilterServicioTecnico filterST, FilterHisotrial filterH,Documento documento)throws Exception{
		return sucursalService.getCrearOTGP(filterST, filterH, documento, getUbicacionSession(), getDate());
	}
	
	public Guia getGuiaById(Long id) throws Exception {
		return sucursalService.getGuiaById(id, getUbicacionSession(), getUsuarioSession());
	}

	public List<ServicioTecnico> listSTecnicoYBodegasByFilter(FilterServicioTecnico filter) throws Exception {
		return sucursalService.listSTecnicoYBodegasByFilter(filter, getUbicacionSession());
	}

	public List<ServicioTecnico> listSTecnicoByFilter(FilterServicioTecnico filter) throws Exception {
		return sucursalService.listSTecnicoByFilter(filter, getUbicacionSession());
	}
		
	public List<OrdenTrabajo> listHistorialOT(FilterHisotrial filter) throws Exception {
		return sucursalService.listHistorialOT(filter);
	}
	
	public List<TipoFallas> listTipoFallasByFilter(FilterTipoFallas filter) throws Exception {
		return sucursalService.listTipoFallasByFilter(filter);
	}
	
	public List<TipoFallas> listTipoFallasByIdProducto(Integer idProducto) throws Exception{
		return sucursalService.listTipoFallasByIdProducto(idProducto);
	}
	
	public List<List<TipoFallas>> listTipoFallasCrearOT(OrdenTrabajo oT) throws Exception {
		return sucursalService.listTipoFallasCrearOT(oT);
	}
	
	public Bitacora getBitacoraByIdGuia(Long idGuia) throws Exception {
		return sucursalService.getBitacoraByIdGuia(idGuia);
	}

	public Boolean saveTipoFallas(Long idOT, List<Long> idTipoFallas,FilterOT filterOT) throws Exception{
		return sucursalService.saveTipoFallas(idOT,idTipoFallas,filterOT);
	}
	
	public Integer listTotalFallasOTByFilter(FilterTipoFallas filter) throws Exception{
		return sucursalService.listTotalFallasOTByFilter(filter);
	}
	
	public Integer updateDesactivarOTByFilter(FilterOT filter) throws Exception {
		return sucursalService.updateDesactivarOTByFilter(filter);
	}
	
	public List<TipoFallas> listTipoFallasByOT(Long idOT)throws Exception {
		return sucursalService.listTipoFallasByOT(idOT);
	}

	public Guia emitirGuia(Long idOT, Guia guia, ServicioTecnico servicioTecnico) throws Exception {
		return sucursalService.emitirGuia(idOT, guia, servicioTecnico, getUsuarioSession(), getUbicacionSession());
	}
	
	public Guia emitirGuiaAccesorio(Long idOT, Guia guia) throws Exception {
		return sucursalService.emitirGuiaAccesorio(idOT, guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public Boolean validaStockGuiaUnitaria(Long idGuia, Long idOT) throws Exception {
		return sucursalService.validaStockGuiaUnitaria(idGuia, idOT);
	}
	
	public Boolean validaStockGuiaAgrupada(Long idGuia) throws Exception {
		return sucursalService.validaStockGuiaAgrupada(idGuia);
	}
	
	public Guia emitirGuiaAgrupada(Guia guia) throws Exception {
		return sucursalService.emitirGuiaAgrupada(guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public Guia reEmitirGuia(Long idOT, Guia guia, ServicioTecnico servicioTecnico) throws Exception {
		return sucursalService.reEmitirGuia(idOT, guia, servicioTecnico, getUsuarioSession(), getUbicacionSession());
	}
	
	public Guia confirmarEmisionGuia(Long idOT, Guia guia) throws Exception {
		return envioRecepcionService.confirmarEmisionGuia(idOT, guia, getUsuarioSession());
	}
	
	public Guia confirmarEmisionGuiaAgrupada(Guia guia) throws Exception {
		return envioRecepcionService.confirmarEmisionGuiaAgrupada(guia, getUsuarioSession(),getUbicacionSession());
	}
	
	public Guia reEmitirGuiaAccesorio(Long idOT, Guia guia) throws Exception {
		return sucursalService.reEmitirGuiaAccesorio(idOT, guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public Guia reEmitirGuiaAgrupada(Guia guia) throws Exception {
		return sucursalService.reEmitirGuiaAgrupada(guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public Guia reEmitirGuiaAgrupadaFF(Guia guia) throws Exception {
		return envioRecepcionService.reEmitirGuiaAgrupadaFF(guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public Integer listTotalOTbyidOT(Long idOT) throws Exception {
		return sucursalService.listTotalOTbyidOT(idOT);
	}
	
	public Integer updateUbicacionAccesorio(Accesorio accesorio) throws Exception {
		return sucursalService.updateUbicacionAccesorio(accesorio);
	}
	
	public Integer updateUbicacionByEstado(Ubicacion ubicacion) throws Exception {
		return sucursalService.updateUbicacionByEstado(ubicacion);
	}
	
	public Integer updateEstadoProcedimiento(Procedimiento procedimiento) throws Exception {
		return administracionService.updateEstadoProcedimiento(procedimiento, getUsuarioSession());
	}
	
	public Integer updateProcedimientoProducto(Procedimiento procedimiento) throws Exception {
		return administracionService.updateProcedimientoProducto(procedimiento);
	}
	
	public ServicioTecnico getOTContratoByGuia(FilterOT filter) throws Exception {
		return sucursalService.getOTContratoByGuia(filter, getUbicacionSession());
	}
	
	public OrdenTrabajo getProductoOTById(Long idOT)throws Exception {
		return sucursalService.getProductoOTById(idOT);
	}
	
	public Cliente saveCliente(Cliente cliente) throws Exception {
		return sucursalService.saveCliente(cliente);
	}
	
	public OrdenTrabajo terminarOrdenTrabajo(Cliente cliente, OrdenTrabajo oT, Ubicacion origen) throws Exception {
		return sucursalService.terminarOrdenTrabajo(cliente,oT,getUbicacionSession());
	}
	
	public List<Parte> listPartesByFilter(FilterParte filter) throws Exception {
		return sucursalService.listPartesByFilter(filter);
	}
	
	public ListRange listOTEnTransito(FilterOT filter, GridControl gridControl) throws Exception {
		return sucursalService.listOTEnTransito(filter, gridControl, getUbicacionSession());
	}
	
	public ListRange listOTPendientesAccesorios(FilterOT filter, GridControl gridControl) throws Exception {
		return sucursalService.listOTPendientesAccesorios(filter, gridControl, getUbicacionSession());
	}
	
	public void recibirOTSucursal(OrdenTrabajo ot) throws Exception {
		sucursalService.recibirOTSucursal(ot, getUbicacionSession());
	}
	
	
	public OrdenTrabajo saveOTGP(OrdenTrabajo oT) throws Exception{
		return sucursalService.saveOTGP(oT,getUsuarioSession(),getUbicacionSession());
	}
	
	public OrdenTrabajo saveOTParaEvaluacion(OrdenTrabajo oT) throws Exception {
		return sucursalService.saveOTParaEvaluacion(oT, getUsuarioSession(), getUbicacionSession());
	}
	
	public OrdenTrabajo saveOTGM(OrdenTrabajo oT) throws Exception{
		return sucursalService.saveOTGM(oT, getUsuarioSession(), getUbicacionSession());
	}
	
	public OrdenTrabajo saveOTGMFromCD(OrdenTrabajo oT, Ubicacion ubicacion) throws Exception{
		return sucursalService.saveOTGMFromCD(oT, ubicacion ,getDate(),getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTGMFromCDForCambio(OrdenTrabajo oT, Ubicacion ubicacion, String origenGlosa) throws Exception{
		return sucursalService.saveOTGMFromCDForCambio(oT, ubicacion ,getDate(), origenGlosa,getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTJT(OrdenTrabajo oT, String tipoDocumento) throws Exception{
		return sucursalService.saveOTJT(oT, tipoDocumento, getUbicacionSession(),getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTGenerico(OrdenTrabajo oT, String tipoDocumento) throws Exception{
		return sucursalService.saveOTGenerico(oT, tipoDocumento, getUbicacionSession(),getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTCambioPorValor(OrdenTrabajo oT, String tipoDocumento) throws Exception {
		return sucursalService.saveOTCambioPorValor(oT, tipoDocumento, getUbicacionSession(),getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTCambioPorProveedorCertificado(OrdenTrabajo oT, CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta, String productoFisico)throws Exception {
		return sucursalService.saveOTCambioPorProveedorCertificado(oT, cambioAutomaticoProveedorCarta, productoFisico, getUbicacionSession(), getUsuarioSession());
	}
	
	public OrdenTrabajo saveOTCambioPorProveedor(OrdenTrabajo oT) throws Exception {
		return sucursalService.saveOTCambioPorProveedor(oT, getUbicacionSession(), getUsuarioSession());
	}
	
	public CambioAutomaticoProveedorCarta getNumeroCertificadoByIdProducto(Producto producto) throws Exception{
		return sucursalService.getNumeroCertificadoByIdProducto(producto);
	}
	
	public OrdenTrabajo saveOTFallaFabricacion(OrdenTrabajo oT, Ubicacion sucursal, Usuario usuario)throws Exception {
		return sucursalService.saveOTFallaFabricacion(oT, getUbicacionSession(), getUsuarioSession());
	}
	
	public void saveSTecnicoOT(OrdenTrabajo ot) throws Exception{
		sucursalService.saveSTecnicoOT(ot, getUsuarioSession());  
	}
	
	public Guia getGuiaByIdOT(Long idOT) throws Exception {
		return sucursalService.getGuiaByIdOT(idOT);
	}

	public List<Accesorio> listAccesoriosByFilter(FilterAccesorio filter) throws Exception{
		return sucursalService.listAccesoriosByFilter(filter);
	}
	
	public List<Accesorio> listAccesoriosNotExistsByFilter(FilterAccesorio filter) throws Exception{
		return sucursalService.listAccesoriosNotExistsByFilter(filter);
	}
	
	public void saveAccesoriosForTipoFalla(TipoFallas tipoFallas, List<Accesorio> accesorios) throws Exception {
		administracionService.saveAccesoriosForTipoFalla(tipoFallas, accesorios);
	}
	

	public boolean saveParteOT(List<Parte> partes)throws Exception {
		return sucursalService.saveParteOT(partes, getUsuarioSession(),getUbicacionSession());
	}
	
	public Integer updateOTRevision(OrdenTrabajo oT)throws Exception {
		return sucursalService.updateOTRevision(oT);
	}
	
	public Integer updateOTBTNOrigen(OrdenTrabajo oT)throws Exception {
		return sucursalService.updateOTBTNOrigen(oT);
	}
	
	public OrdenTrabajo getOTRecibeSucursal(FilterOT filter) throws Exception{
		return envioRecepcionService.getOTRecibeSucursal(filter, getUbicacionSession());
	}
	
	public Ubicacion getUbicacionOT(Long idOT) throws Exception{
		return sucursalService.getUbicacionOT(idOT);
	}
	
	public Ubicacion getUbicacionOTAccesorio(Long idOT) throws Exception{
		return sucursalService.getUbicacionOTAccesorio(idOT);
	}
	
	public Guia getGuiaRecepcion(Long idOT, String guiaAccesorio) throws Exception{
		return sucursalService.getGuiaRecepcion(idOT, guiaAccesorio, getUbicacionSession());
	}
	
	public Guia getGuiaAccesorioForIdOT(Long idOT) throws Exception{
		return sucursalService.getGuiaAccesorioForIdOT(idOT);
	}

	public List<Guia> listGuiaRecepcion(Long idOT) throws Exception{
		return sucursalService.listGuiaRecepcion(idOT, getUbicacionSession());
	}
	
	public Integer getCantidadOtsEjecutiva(Long idOT) throws Exception{
		return sucursalService.getCantidadOtsEjecutiva(getUsuarioSession(), idOT);
	}
	
	public Integer getCantidadOTByFilter(FilterOT filter) throws Exception{
		return sucursalService.getCantidadOTByFilter(filter);
	}
	
	public OrdenTrabajo getOTByFilterForEntregaCliente(FilterOT filter) throws Exception {
		return sucursalService.getOTByFilterForEntregaCliente(filter);
	}
	
	public OrdenTrabajo getOTByFilter(FilterOT filter) throws Exception {
		return sucursalService.getOTByFilter(filter);
	}
	
	public List<Accesorio> listAccesoriosForGuiaTipo(OrdenTrabajo ordenTrabajo,Guia guia,Boolean cliente) throws Exception{
		return sucursalService.listAccesoriosForGuiaTipo(ordenTrabajo,guia,cliente,getUbicacionSession());
	}
	
	public List<Accesorio> listAllAccesoriosFromTipoGuia(Guia guia) throws Exception{
		return sucursalService.listAllAccesoriosFromTipoGuia(guia);
	}
	
	public List<Indicador> listIndicadorSucursal() throws Exception {
		return sucursalService.listIndicadorSucursal(getUbicacionSession());
	}	
	
	public Bitacora getUltimaBitacora(Long idOT) throws Exception {
		return sucursalService.getUltimaBitacora(idOT);
	}
	
	public ListRange listOTIndicadorSucursal(FilterIndicador filter, GridControl gridControl) throws Exception {
		return sucursalService.listOTIndicadorSucursal(filter, gridControl, getUbicacionSession());
	}
	
	public List<Integer> validaCodigosDeBarraAccesorios(List<Accesorio> accesorios) throws Exception {
		return sucursalService.validaCodigosDeBarraAccesorios(accesorios);
	}
	
	public Boolean validaCodigosDeBarraOT(OrdenTrabajo ot) throws Exception {
		return sucursalService.validaCodigosDeBarraOT(ot);
	}
	
	public String updateClienteAceptaProducto(List<Accesorio> accesorios, OrdenTrabajo ot) throws Exception {
		return sucursalService.updateClienteAceptaProducto(accesorios, ot, getUsuarioSession(), getUbicacionSession());
	}
	
	public void updateEnviarTareaEjecutiva(OrdenTrabajo ot) throws Exception {
		sucursalService.updateEnviarTareaEjecutiva(ot, getUsuarioSession());
	}
	public void updateEnviarTareaEjecutivaDesdeCD(Recepcion recepcion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		sucursalService.updateEnviarTareaEjecutivaDesdeCD(recepcion,getUbicacionSession(), getUsuarioSession(), partes, accesorios);
	}
	
	public ReglaComercial getReglaComercialCompleta(Producto producto) throws Exception {
		return sucursalService.getReglaComercialCompleta(producto, getUbicacionSession());
	}
	
	public ReglaComercial getReglaComercialVigentePorValor(Producto producto) throws Exception {
		return sucursalService.getReglaComercialVigentePorValor(producto, getUbicacionSession());
	}
	
	public ReglaComercial getReglaComercialVigentePorFallaReiterada(Producto producto) throws Exception {
		return sucursalService.getReglaComercialVigentePorFallaReiterada(producto, getUbicacionSession());
	}
	
	public ReglaComercial getReglaComercialVigentePoFallaFabricacion(Producto producto) throws Exception {
		return sucursalService.getReglaComercialVigentePoFallaFabricacion(producto, getUbicacionSession());
	}
	
	public Usuario autorizacionTipo(FilterTipoCambio filter)throws Exception{
		return sucursalService.autorizacionTipo(filter, SessionStorageService.getRequest().getRemoteAddr(), getUbicacionSession(), getDate());
	}
	
	public Boolean isCambioPorValor(FilterTipoCambio filter, Date hoy) throws Exception {
		return sucursalService.isCambioPorValor(filter, getUbicacionSession());
	}
	
	public OrdenTrabajo updateNotaCredito(OrdenTrabajo oT) throws Exception{
		return sucursalService.updateNotaCredito(oT);
	}
	
	
	
	public Boolean getCrearNotaCredito(Documento documento)throws Exception{
		return sucursalService.getCrearNotaCredito(documento,getDate());
	}
	
	
	/*BodegaService*/
	public ListRange listOTListasParaDespachoSucursal(FilterOT filterOT, GridControl gridControl) throws Exception{
		return bodegaService.listOTListasParaDespachoSucursal(filterOT, gridControl);
	}
	
	public Inventario getEstadisticasInventarioByIdInventario(Integer idInventario) throws Exception{
		return bodegaService.getEstadisticasInventarioByIdInventario(idInventario);
	}
	
	public ListRange listUbicacionesInternasByFilter(FilterInventario filterInventario,GridControl gridControl) throws Exception{
		return bodegaService.listUbicacionesInternasByFilter(filterInventario, gridControl);
	}
	
	public void terminarInventarioUbicacion(Integer idInventarioUbicacion) throws Exception{
		bodegaService.terminarInventarioUbicacion(idInventarioUbicacion);
	}
	
	public Integer contarInventarioUbicacionOpenByIdInventario(Integer idInventario) throws Exception{
		return bodegaService.contarInventarioUbicacionOpenByIdInventario(idInventario);
	}
	
	public InventarioUbicacion getEstadisitcasByIdInventarioUbicacion(Long id) throws Exception {
		return bodegaService.getEstadisitcasByIdInventarioUbicacion(id);
	}
	
	public ListRange listInventarioProductoByFilter(FilterInventario filterInventario, GridControl gridControl) throws Exception{
		return bodegaService.listInventarioProductoByFilter(filterInventario, gridControl);
	}
	
	public void abrirCerrarEnvioSucursal(Integer idSucursal, Boolean crear) throws Exception {
		bodegaService.abrirCerrarEnvioSucursal(idSucursal, crear, getUsuarioSession());
	}
	
	public ListRange listEnviosToSucursal(FilterEnvioSucursal filterEnvioSucursal, GridControl gridControl) throws Exception{
		return bodegaService.listEnviosToSucursal(filterEnvioSucursal, gridControl);
	}
	
	public OrdenTrabajo getEstadosOT(Long idOT) throws Exception {
		return bodegaService.getEstadosOT(idOT);
	}
	
	public ListRange listOTToCambioEstadoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception{
		return bodegaService.listOTToCambioEstadoByFilter(filterOT, gridControl);
	}
	
	public void saveInventario(List<UbicacionInterna> ubicaciones) throws Exception{
		bodegaService.saveInventario(ubicaciones, getUsuarioSession(),getUbicacionSession());
	}
	
	public List<CheckForFlexigrid> ListAllCheckOTToCambioEstadoByFilter(FilterOT filterOT) throws Exception {
		return bodegaService.ListAllCheckOTToCambioEstadoByFilter(filterOT);
	}
	
//	public ListRange listUbicacionesInternasFromInventario(Integer idInventario, GridControl gridControl) throws Exception{
//		return bodegaService.listUbicacionesInternasFromInventario(idInventario,gridControl);
//	}
	
	public ListRange listInventarioUbicacionesByIdInventario(Integer idInventario, GridControl gridControl) throws Exception {
		return bodegaService.listInventarioUbicacionesByIdInventario(idInventario, gridControl);
	}
	
	public void abrirCerrarInventario(Inventario inventario) throws Exception{
		bodegaService.abrirCerrarInventario(inventario,getUsuarioSession());
	}
	
	public ListRange listUbicacionesInternasToInventario(GridControl gridControl) throws Exception {
		return bodegaService.listUbicacionesInternasToInventario(gridControl);
	}
	
	public List<CheckForFlexigrid> listAllUbicacionesInternasToInventario() throws Exception {
		return bodegaService.listAllUbicacionesInternasToInventario();
	}
	
	public ListRange listInventariosByFilter(FilterInventario filterInventario, GridControl gridControl) throws Exception {
		return bodegaService.listInventariosByFilter(filterInventario, gridControl);
	}
	
	public ListRange selectInventarioResumen(Integer idInventarioUbicacion, GridControl gridControl) throws Exception {
		return bodegaService.selectInventarioResumen(idInventarioUbicacion, gridControl);
	}
	
	public Integer getTotalInventarioResumen(Integer idInventarioUbicacion) throws Exception {
		return bodegaService.getTotalInventarioResumen(idInventarioUbicacion);
	}
	
	public ListRange listGuiasBodegaByFilter(FilterGuia filter, GridControl gridControl)throws Exception{
		return bodegaService.listGuiasBodegaByFilter(filter, gridControl, getUbicacionSession());
	}
	
	public void generarTrasladoFF(List<OrdenTrabajo> ots)throws Exception{
		bodegaService.generarTrasladoFF(ots, getUbicacionSession(),getUsuarioSession());
	}
	
	public DespachoInterno generarTrasladoDesdeFF(List<OrdenTrabajo> ots, Ubicacion destino,String tipoUbicacion)throws Exception{
		return bodegaService.generarTrasladoDesdeFF(ots, destino,tipoUbicacion ,getUbicacionSession(),getUsuarioSession());
	}
	
	public DespachoInterno getDespachoById(Long idDespacho)throws Exception{
		return bodegaService.getDespachoById(idDespacho);
	}
	
	public ListRange listOtByDespacho(DespachoInterno despacho,GridControl gridControl)throws Exception{
		return bodegaService.listOtByDespacho(despacho,gridControl);
	}
	
	public List<OrdenTrabajo> listOtEscaneadasByDespacho(DespachoInterno despacho)throws Exception{
		return bodegaService.listOtEscaneadasByDespacho(despacho);
	}
	
	public ListRange listOtByDespachoToSP(DespachoInterno despacho,GridControl gridControl)throws Exception{
		return bodegaService.listOtByDespachoToSP(despacho,gridControl);
	}
	
	public List<Guia> processGuiasGim(DespachoInterno despachoInterno)throws Exception {
		return bodegaService.processGuiasGim(despachoInterno,getUsuarioSession(),getUbicacionSession());
	}
//	public ListRange listGuiasBodegaForEnvioSinGuiaByFilter(FilterGuia filter, GridControl gridControl) throws Exception {
//		return bodegaService.listGuiasBodegaForEnvioSinGuiaByFilter(filter, gridControl, getUbicacionSession());
//	}
	
	public OrdenTrabajo getOTRevisaBodegaFF(FilterOT filter) throws Exception{
		return bodegaService.getOTRevisaBodegaFF(filter, getUbicacionSession());
	}
	
	public OrdenTrabajo getOTRevisaSalaProveedores(FilterOT filter) throws Exception {
		return bodegaService.getOTRevisaSalaProveedores(filter);
	}
	
	public ServicioTecnico saveServicioTecnico(Ubicacion ubicacion) throws Exception {
		return bodegaService.saveServicioTecnico(ubicacion);
	}

	public ListRange listOTByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return bodegaService.listOTByFilter(filterOT, listMyRoles(), gridControl);
	}
	
	public void modificarEstadoOT(Long idOT,Integer idEstado) throws Exception{
		bodegaService.modificarEstadoOT(idOT, idEstado, getUsuarioSession());
	}
	
	public ListRange listGuiasRemate(GridControl gridControl) throws Exception {
		return bodegaService.listGuiasRemate(gridControl);
	}
	
	public void eliminarGuiaRemate(List<GuiaPendienteAgrupada> guiaPendienteAgrupadas) throws Exception {
		bodegaService.eliminarGuiaRemate(guiaPendienteAgrupadas, getUsuarioSession(), getUbicacionSession());
	}
	
	public void marcarRecibidaGuiaRemate(List<GuiaPendienteAgrupada> guiaPendienteAgrupadas) throws Exception {
		bodegaService.marcarRecibidaGuiaRemate(guiaPendienteAgrupadas, getUsuarioSession(), getUbicacionSession());
	}
	
	public List<CheckForFlexigrid> listOtByDespachoCheck(DespachoInterno despacho) throws Exception {
		return bodegaService.listOtByDespachoCheck(despacho);
	}
	
	public void desvincularOTDespacho(DespachoInterno despacho,List<OrdenTrabajo> ots) throws Exception {
		 bodegaService.desvincularOTDespacho(despacho,ots,getUbicacionSession());
	}
	
	public void desvincularOTDespachoExterno(DespachoInterno despacho,List<OrdenTrabajo> ots) throws Exception {
		 bodegaService.desvincularOTDespachoExterno(despacho,ots);
	}
	
	public void updateEstadoFromDespachoToNextEstado(DespachoInterno despachoInterno) throws Exception {
		 bodegaService.updateEstadoFromDespachoToNextEstado(despachoInterno);
	}
	
	public List<Guia> listGuiasByIdDespacho(Long id) throws Exception{
		return   bodegaService.listGuiasByIdDespacho(id);
	}
	
	public List<Guia> listGuiasToUbicacionDiezMilByidDespacho(Long id) throws Exception{
		return   bodegaService.listGuiasToUbicacionDiezMilByidDespacho(id);
	}
	
	public void reEmitirGuiaDespachoAgrupada(DespachoInterno despachoInterno,Guia guia) throws Exception{
		bodegaService.reEmitirGuiaDespachoAgrupada(despachoInterno, guia, getUsuarioSession(),getUbicacionSession());
	}
	
	public void reEmitirGuiaDespachoAgrupadaHaciaDestino(DespachoInterno despachoInterno,Guia guia) throws Exception{
		bodegaService.reEmitirGuiaDespachoAgrupadaHaciaDestino(despachoInterno, guia, getUsuarioSession(),getUbicacionSession());
	}
	
	public void emitirGuiaDespachoAgrupada(DespachoInterno despachoInterno,Guia guia) throws Exception{
		bodegaService.emitirGuiaDespachoAgrupada(despachoInterno, guia, getUsuarioSession(),getUbicacionSession());
	}
	
	
	
	public void revisarOnSalaProveedores(OrdenTrabajo oT) throws Exception {
		bodegaService.revisarOnSalaProveedores(oT,getUbicacionSession(),false);
	}
	
	public void revisarMasivoOnSalaProveedores(List<OrdenTrabajo> oTs) throws Exception {
		bodegaService.revisarMasivoOnSalaProveedores(oTs,getUbicacionSession());
	}
	
	public List<Indicador> getIndicadoresEjecutivaFF(List<Indicador> indicadores) throws Exception {
		return bodegaService.getIndicadoresEjecutivaFF(indicadores);
	}
	
	public ListRange listOTIndicadorEjecutivaFF(FilterIndicador filter, GridControl gridControl) throws Exception {
		return bodegaService.listOTIndicadorEjecutivaFF(filter, gridControl);
	}
	
	public ListRange listGuiaIndicadorEjecutivaFF(FilterIndicador filter, GridControl gridControl) throws Exception {
		return bodegaService.listGuiaIndicadorEjecutivaFF(filter, gridControl);
	}
	
	public List<Sello> getSellosByIdGuia(Long id) throws Exception {
		return bodegaService.getSellosByIdGuia(id);
	}
	
	public ListRange listOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT,GridControl gridControl) throws Exception {
		return bodegaService.listOtByIdGuiaAndClasificacionAvOrDa(filterOT, gridControl);
	}
	
	public ListRange listOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT,GridControl gridControl) throws Exception {
		return bodegaService.listOtByIdGuiaAndClasificacionCpOrRp(filterOT, gridControl);
	}
		
	public ListRange listOtByIdGuia(FilterOT filterOT, GridControl gridControl) throws Exception {
		return bodegaService.listOtByIdGuia(filterOT, gridControl);
	}
	
	public void generarSC(List<OrdenTrabajo> ots,DespachoInterno despacho,Guia guia) throws Exception {
		 bodegaService.generarSC(ots,despacho,guia,getUsuarioSession(),getUbicacionSession());
	}
	
	
	public void generarDM(List<OrdenTrabajo> ots,DespachoInterno despacho,Integer numeroGuia,String origen) throws Exception {
		 bodegaService.generarDM(ots,despacho,numeroGuia,origen,getUsuarioSession(),getUbicacionSession());
	}
	
	public void cerrarOTByUsuario(List<OrdenTrabajo> ots) throws Exception {
		 bodegaService.cerrarOTByUsuario(ots,getUsuarioSession());
	}
	
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT)throws Exception{
		return bodegaService.listAllOtByIdGuiaAndClasificacionAvOrDa(filterOT);
	}
	
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT)throws Exception{
		return bodegaService.listAllOtByIdGuiaAndClasificacionCpOrRp(filterOT);
	}
	
	public void saveBitacoraHaciaDestino(List<OrdenTrabajo> ots,Guia guia,Clasificacion clasificacion)throws Exception{
		 bodegaService.saveBitacoraHaciaDestino(ots, guia,clasificacion,getUbicacionSession());
	}
	
	public Integer getExisteNumero(Long numero)throws Exception{
		return bodegaService.getExisteNumero(numero);
	}
	
	public Guia saveGuiaHaciaBodegaDiezMil(List<OrdenTrabajo> ots,DespachoInterno despacho) throws Exception{
		return bodegaService.saveGuiaHaciaBodegaDiezMil(ots,despacho,getUbicacionSession(),getUsuarioSession());
	}
	
	public DespachoDetalle getIndicadoresbyidDespacho(DespachoInterno despacho)throws Exception{
		return bodegaService.getIndicadoresbyidDespacho(despacho);
	} 
	
	public List<OrdenTrabajo> listOtByDespachoAndEstado(FilterDespachoInternoDetalle filter)throws Exception{
		return bodegaService.listOtByDespachoAndEstado(filter);
	} 
	
	public List<PeticionDocumento> listPeticionDocumentoByDespachoIterno(FilterOT filter)throws Exception{
		return bodegaService.listPeticionDocumentoByDespachoIterno(filter);
	} 
	
	public Guia confirmarGuiaDespachoAgrupada(Guia guia)throws Exception{
		return bodegaService.confirmarGuiaDespachoAgrupada(guia,getUsuarioSession(),getUbicacionSession());
	}
	
	public Boolean verificarEstadoParaEliminarOtsFromDespacho(List<OrdenTrabajo> ots,DespachoInterno despacho)throws Exception{
		return bodegaService.verificarEstadoParaEliminarOtsFromDespacho(ots,despacho);
	}
	
	public List<OrdenTrabajo> verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(List<OrdenTrabajo> ots)throws Exception{
		return bodegaService.verificarEstadoParaEliminarOtsFromDespachoHaciaDestinos(ots);
	}
	
	public void updateRecuperacionForOT(List<OrdenTrabajo> ots)throws Exception{
		bodegaService.updateRecuperacionForOT(ots);
	}
	/*BuscadoresService*/
	public ListRange listUbicacionByFilter(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception {
		return buscadoresService.listUbicacionByFilter(filterUbicacion, gridControl);
	}
	
	public ListRange listOTByRecepcionesFilter(FilterRecepcion filterRecepcion, GridControl gridControl) throws Exception {
		return buscadoresService.listOTByRecepcionesFilter(filterRecepcion, gridControl);
	}
	
	public ListRange listOTRemate(GridControl gridControl) throws Exception {
		return buscadoresService.listOTRemate(gridControl);
	}
	
	public List<ServicioTecnico> ListServiciosTecnicosBuscadorOT(FilterServicioTecnico filterServicioTecnico)throws Exception {
		return buscadoresService.ListServiciosTecnicosBuscadorOT(filterServicioTecnico);
	}
	
	public ListRange listOtTrasladoToFFByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return buscadoresService.listOtTrasladoToFFByFilter(filterOT, gridControl);
	}
	
	public ListRange listOtInFFTrasladoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return buscadoresService.listOtInFFTrasladoByFilter(filterOT, gridControl);
	}
	
	public ListRange listDespachosToFallaFabricacionByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return buscadoresService.listDespachosToFallaFabricacionByFilter(filterOT, gridControl);
	}
	
	public ListRange listDespachosDesdeFFByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return buscadoresService.listDespachosDesdeFFByFilter(filterOT, gridControl);
	}
	
	public List<CheckForFlexigrid> listAllCheck(FilterOT filterOT) throws Exception {
		return buscadoresService.listAllCheck(filterOT);
	}
	
	public List<CheckForFlexigrid> listALLOtInFFTrasladoCheck(FilterOT filterOT) throws Exception {
		return buscadoresService.listALLOtInFFTrasladoCheck(filterOT);
	}
//	public List<CheckForFlexigrid> listAllBitacorasCheck(FilterBitacoraInterna filterBitacoraInterna) throws Exception {
//		return buscadoresService.listAllBitacorasCheck(filterBitacoraInterna);
//	}
//	
//	public ListRange listBitacorasInternasByFilter(FilterBitacoraInterna filterBitacoraInterna, GridControl gridControl) throws Exception {
//		return buscadoresService.listBitacorasInternasByFilter(filterBitacoraInterna, gridControl);
//	}
	
	public List<CheckForFlexigrid> listAllOtParaDespachoCheck(FilterOT filterOT) throws Exception {
		return buscadoresService.listAllOtParaDespachoCheck(filterOT);
	}
	
	public ListRange listOtParaDespachoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception{
		return buscadoresService.listOtParaDespachoByFilter(filterOT, gridControl, getUbicacionSession());
	}
	
	public ListRange ListRevisionesByFilter(FilterDespachoInterno filterDespachoInterno,GridControl gridControl) throws Exception {
		return buscadoresService.ListRevisionesByFilter(filterDespachoInterno,getUbicacionSession(), gridControl);
	}
	
	public ListRange listProveedorReportByfilter(FilterOT filterOT,GridControl gridControl) throws Exception {
		return buscadoresService.listProveedorReportByfilter(filterOT, gridControl);
	}
	
	public ListRange listReporteFallaByBilter(FilterOT filterOT,GridControl gridControl) throws Exception {
		return buscadoresService.listReporteFallaByBilter(filterOT, gridControl);
	}
	
	/*EnvioRecepcionService*/
	public GuiaPendienteAgrupada emitirGuiaRemate(GuiaPendienteAgrupada guia) throws Exception {
		return envioRecepcionService.emitirGuiaRemate(guia, getUsuarioSession(), getUbicacionSession());
	}
	
	public List<Accesorio> listAccesoriosOTbyFilter(FilterAccesorio filterAccesorio) throws Exception {
		return envioRecepcionService.listAccesoriosOTbyFilter(filterAccesorio, getUbicacionSession());
	}
	
	public GuiaPendienteAgrupada getGuiaRemateById(Long id) throws Exception{
		return envioRecepcionService.getGuiaRemateById(id,getUbicacionSession(),getUsuarioSession());
	}
	
	public void saveGuiaRemate(List<OrdenTrabajo> ordenTrabajos) throws Exception {
		envioRecepcionService.saveGuiaRemate(ordenTrabajos, getUsuarioSession(), getUbicacionSession());
	}
	
	public Integer getTotalGuiasPendientesBySucursalSinEmitir(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return envioRecepcionService.getTotalGuiasPendientesBySucursalSinEmitir(filterGuiasPendientes, getUbicacionSession());
	}
	
	public Integer getTotalGuiasAgrupadasPendientesSinEmitir(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return envioRecepcionService.getTotalGuiasAgrupadasPendientesSinEmitir(getUbicacionSession(), filterGuiasPendientes);
	}
	
	public Guia getGuiaRecepcionAgrupada(Long idOT) throws Exception{
		return envioRecepcionService.getGuiaRecepcionAgrupada(idOT, getUbicacionSession());
	}
	
	public Recepcion saveRecepcionMasiva(RecepcionCamion recepcionCamion) throws Exception {
		return envioRecepcionService.saveRecepcionMasiva(recepcionCamion, getUsuarioSession());
	}
	
	public Recepcion getRecepcionCamionAbiertaByTransportista(Long idTransportista) throws Exception {
		return envioRecepcionService.getRecepcionCamionAbiertaByTransportista(idTransportista);
	}
	
	public void recibirOT(FilterOT filterOT, Integer idRecepcionCamion) throws Exception {
		envioRecepcionService.recibirOT(filterOT,idRecepcionCamion,getUbicacionSession());
	}
	
	public ListRange listDetalleGuiaRecepcion(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception {
		return envioRecepcionService.listDetalleGuiaRecepcion(filterRecepcionProducto, gridControl);
	}
	
	public void saveRecepcionOTGuiaAgrupadas(Recepcion recepcion,String accesoriosRecibidos, Boolean aceptarRecepcion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		envioRecepcionService.saveRecepcionOTGuiaAgrupadas(recepcion,accesoriosRecibidos, aceptarRecepcion, getUsuarioSession(), getUbicacionSession(), partes, accesorios);
	}
	
	public void saveRecepcionOTRechazoGuiaAgrupadas(Recepcion recepcion,String accesoriosRecibidos, Boolean aceptarRecepcion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		envioRecepcionService.saveRecepcionOTRechazoGuiaAgrupadas(recepcion, getUbicacionSession(), getUsuarioSession(), partes, accesorios);
	}
	
	public Boolean terminarRecepcionMasiva(FilterRecepcionProducto filterRecepcionProducto)throws Exception{
		return envioRecepcionService.terminarRecepcionMasiva(filterRecepcionProducto, getUbicacionSession(), getUsuarioSession());
	}
	
	public void saveRecepcionOT(Recepcion recepcion,Boolean aceptarRecepcion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		envioRecepcionService.saveRecepcionOT(recepcion, aceptarRecepcion, getUsuarioSession(), getUbicacionSession(), partes, accesorios);
	}
	
	public void saveRecepcionGuiaAccesorios(Recepcion recepcion,Boolean aceptarRecepcion, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		envioRecepcionService.saveRecepcionGuiaAccesorios(recepcion, aceptarRecepcion, getUsuarioSession(), getUbicacionSession(), partes, accesorios);
	}
	
	public void saveRecepcionOTRechazo(Recepcion recepcion,Boolean tareaEjecutiva, List<Parte> partes, List<Accesorio> accesorios) throws Exception {
		envioRecepcionService.saveRecepcionOTRechazo(recepcion, tareaEjecutiva, getUbicacionSession(), getUsuarioSession(), partes, accesorios);
	}
	
	
	public OrdenTrabajo getOTRecibeBodega(FilterOT filter) throws Exception{
		return envioRecepcionService.getOTRecibeBodega(filter, getUbicacionSession());
	}
	
	public List<Recepcion> listRecepcionesMasivas() throws Exception{
		return envioRecepcionService.listRecepcionesMasivas();
	} 
	
	public Guia getGuiaVigenteByIdOT(Long idOT) throws Exception {
		return envioRecepcionService.getGuiaVigenteByIdOT(idOT);
	}
	
	public Recepcion getRecepcionByGuia(Guia guia)throws Exception{
		return envioRecepcionService.getRecepcionByGuia(guia);
	}
	
	public void emitirEnvioSinGuia(List<OrdenTrabajo> oTs, Ubicacion destino,Date fechaEmision) throws Exception{
		envioRecepcionService.emitirEnvioSinGuia(oTs, destino, getUsuarioSession(), getUbicacionSession(),fechaEmision);
	}
	
	public List<OrdenTrabajo> listOTByGuiaRemate(Long idGuiaRemate) throws Exception {
		return envioRecepcionService.listOTByGuiaRemate(idGuiaRemate);
	}
	
	/*OrdenTrabajoService*/
	public OrdenTrabajo getOTCreaEnvioRecepcion(FilterOT filterOT) throws Exception {
		return ordenTrabajoService.getOTCreaEnvioRecepcion(filterOT);
	}
	
	
	public OrdenTrabajo getOTControlCalidadByFilter(FilterOT filterOT) throws Exception {
		return ordenTrabajoService.getOTControlCalidadByFilter(filterOT);
	}
	
	public void updateExcluirCCalidad(OrdenTrabajo ordenTrabajo) throws Exception{
		ordenTrabajoService.updateExcluirCCalidad(ordenTrabajo);
	}
	
	public OrdenTrabajo updateOTControlCalidadByFilter(OrdenTrabajo ordenTrabajo) throws Exception {
		return ordenTrabajoService.updateOTControlCalidadByOT(ordenTrabajo, getUsuarioSession(), getUbicacionSession());
	}
	
	public void saveOtTerminarGarantiaMaster(Long idOT,List<Accesorio> accesorios,Ubicacion ubicacion) throws Exception {
		 ordenTrabajoService.saveOtTerminarGarantiaMaster(idOT, accesorios,ubicacion);
	}
	
	/*MovimientosInternosServiceEmilio*/
	
	public BitacoraInterna saveBitacoraInterna(OrdenTrabajo ordenTrabajo, Clasificacion clasificacion) throws Exception {
		return movimientosInternosService.saveBitacoraInterna(ordenTrabajo, clasificacion);
	}
	
	public DespachoInterno saveDespachoInternoASalaProveedores(DespachoInterno despachoInterno, List<CheckForFlexigrid> checksOTs) throws Exception {
		return movimientosInternosService.saveDespachoInternoASalaProveedores(despachoInterno, checksOTs, getUsuarioSession());
	}
	
	public ListRange listDespachosInternosOnTraladoToSP(GridControl gridControl) throws Exception {
		return movimientosInternosService.listDespachosInternosOnTraladoToSP(gridControl);
	}
	
	public ListRange listOTRecepcionByDespachoInterno(DespachoInterno despacho, GridControl gridControl) throws Exception {
		return movimientosInternosService.listOTRecepcionByDespachoInterno(despacho, gridControl);
	}
	
	public void marcarOTRecibidaINDespachoInternoByFilter(FilterOT filterOT) throws Exception {
		movimientosInternosService.marcarOTRecibidaINDespachoInternoByFilter(filterOT);
	}
	
	public DespachoInterno getCantidadesDespachoByDespachoInterno(DespachoInterno despachoInterno) throws Exception {
		return movimientosInternosService.getCantidadesDespachoByDespachoInterno(despachoInterno);
	}
	
	public void terminarRecepconDespachoInternoOnSP(DespachoInterno despachoInterno) throws Exception {
		movimientosInternosService.terminarRecepconDespachoInternoOnSP(despachoInterno);
	}
	
//	public void terminarRecepcionDespachoInternoOnSPIncompleta(DespachoInterno despachoInterno) throws Exception {
//		movimientosInternosServiceEmilio.terminarRecepcionDespachoInternoOnSPIncompleta(despachoInterno);
//	}
	/*Setters de los Services*/
	
	public void setLoginService(ILoginService loginService) {
		SSTFacade.loginService = loginService;
	}
	
	public void setSucursalService(SucursalService sucursalService) {
		SSTFacade.sucursalService = sucursalService;
	}
	
	public void setUtilService(UtilService utilService) {
		SSTFacade.utilService = utilService;
	}

	public static void setAdministracionService(AdministracionService administracionService) {
		SSTFacade.administracionService = administracionService;
	}

	public static void setEjecutivaService(EjecutivaService ejecutivaService) {
		SSTFacade.ejecutivaService = ejecutivaService;
	}
	
	public static void setBodegaService(BodegaService bodegaService){
		SSTFacade.bodegaService = bodegaService;
	}

	public static void setEnvioRecepcionService(EnvioRecepcionService envioRecepcionService) {
		SSTFacade.envioRecepcionService = envioRecepcionService;
	}
	
	public static void setBuscadoresService(BuscadoresService buscadoresService) {
		SSTFacade.buscadoresService = buscadoresService;
	}
	
	public static void setOrdenTrabajoService(OrdenTrabajoService ordenTrabajoService) {
		SSTFacade.ordenTrabajoService = ordenTrabajoService;
	}

	public static void setMovimientosInternosService(MovimientosInternosService movimientosInternosService) {
		SSTFacade.movimientosInternosService = movimientosInternosService;
	}
	
	public OrdenTrabajo saveOTCambioPorVentaMenor24Hr(OrdenTrabajo oT) throws Exception {
		return sucursalService.saveOTCambioPorVentaMenor24Hr(oT, getUbicacionSession(), getUsuarioSession());
	}
}