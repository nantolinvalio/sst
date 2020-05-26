package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.BitacoraInternaDAO;
import cl.abcdin.sst.dao.BitacoraInternaMobileDAO;
import cl.abcdin.sst.dao.DespachoDetalleMobileDAO;
import cl.abcdin.sst.dao.DespachoInternoCamionDAO;
import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.dao.DespachoMobileDAO;
import cl.abcdin.sst.dao.EnvioSucursalDAO;
import cl.abcdin.sst.dao.EnvioSucursalDetalleDAO;
import cl.abcdin.sst.dao.EnvioSucursalMobileDAO;
import cl.abcdin.sst.dao.FamiliaDAO;
import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.InventarioMobileDAO;
import cl.abcdin.sst.dao.InventarioProductoDAO;
import cl.abcdin.sst.dao.InventarioUbicacionDAO;
import cl.abcdin.sst.dao.LineaDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.OrdenTrabajoMobileDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.SelloDAO;
import cl.abcdin.sst.dao.TransportistaDAO;
import cl.abcdin.sst.dao.TransportistaMobileDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UbicacionInternaDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.login.service.ILoginService;
import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.DespachoInternoCamion;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.InventarioProducto;
import cl.abcdin.sst.model.InventarioUbicacion;
import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Sello;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterOTMobile;
import cl.abcdin.sst.model.mobile.BitacoraInternaMobile;
import cl.abcdin.sst.model.mobile.DespachoDetalleMobile;
import cl.abcdin.sst.model.mobile.DespachoMobile;
import cl.abcdin.sst.model.mobile.DespachoResponse;
import cl.abcdin.sst.model.mobile.InventarioResponse;
import cl.abcdin.sst.model.mobile.InventarioUbicacionResponse;
import cl.abcdin.sst.model.mobile.MoveCCResponse;
import cl.abcdin.sst.model.mobile.MoveResponse;
import cl.abcdin.sst.model.mobile.MoveSPResponse;
import cl.abcdin.sst.model.mobile.OrdenTrabajoMobile;
import cl.abcdin.sst.model.mobile.OrdenTrabajoResponse;
import cl.abcdin.sst.model.mobile.Response;
import cl.abcdin.sst.model.mobile.TransportistaResponse;
import cl.abcdin.sst.model.mobile.TrasladoMobile;
import cl.abcdin.sst.model.mobile.TrasladosResponse;
import cl.abcdin.sst.model.mobile.UbicacionMobile;
import cl.abcdin.sst.model.mobile.UbicacionesResponce;
import cl.abcdin.sst.model.mobile.UsuarioMobile;
import cl.abcdin.sst.model.mobile.UsuarioResponse;
import cl.abcdin.sst.utils.Constants;

public class MobileService {
	private static final Log log = LogFactory.getLog(MobileService.class);
	
	private OrdenTrabajoMobileDAO ordenTrabajoMobileDAO;
	private GestionesDAO gestionesDAO;
	private DespachoMobileDAO despachoMobileDAO;
	private DespachoDetalleMobileDAO despachoDetalleMobileDAO;
	private ILoginService loginService;
	private BodegaService bodegaService;
	private UbicacionDAO ubicacionDAO;
	private BitacoraInternaMobileDAO bitacoraInternaMobileDAO;
	private BitacoraDAO bitacoraDAO;
	private DespachoInternoDAO despachoInternoDAO;
	private TransportistaMobileDAO transportistaMobileDAO;
	private TransportistaDAO transportistaDAO;
	private GuiaDAO guiaDAO;
	private DespachoInternoCamionDAO despachoInternoCamionDAO;
	private SelloDAO selloDAO;
	private ProductoDAO productoDAO;
	private FamiliaDAO familiaDAO;
	private SucursalService sucursalService;
	private UbicacionInternaDAO ubicacionInternaDAO;
	private LineaDAO lineaDAO;
	private BitacoraInternaDAO bitacoraInternaDAO;
	private EnvioSucursalMobileDAO envioSucursalMobileDAO;
	private EnvioSucursalDetalleDAO envioSucursalDetalleDAO;
	private EnvioSucursalDAO envioSucursalDAO;
	private InventarioMobileDAO inventarioMobileDAO;
	private InventarioProductoDAO inventarioProductoDAO;
	private InventarioUbicacionDAO inventarioUbicacionDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;

	private void ubicacionInternaOnInventario(Long idOT, Integer idUbicacionInternaDestino)throws Exception {
		try {
			BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getBitacoraInternaById(idOT);
			if(bitacoraInterna!=null){
				UbicacionInterna ubicacionInternaOrigen = ubicacionInternaDAO.getUbicacionInternaById(bitacoraInterna.getUbicacionInterna().getId());
				if(ubicacionInternaOrigen.getInventario()){
					throw new SSTException("la ubicación de origen está siendo inventariada, no se puede mover este producto");
				}
			}
			
			UbicacionInterna ubicacionInternaDestino = ubicacionInternaDAO.getUbicacionInternaById(idUbicacionInternaDestino);
			if(ubicacionInternaDestino.getInventario()){
				throw new SSTException("la ubicación de destino está siendo inventariada, no se puede mover este producto");
			}
		} catch (SSTException e){
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response autorizar(String user, String password, String action) throws SSTException {
		Response response = new Response();
		response.setCodigo(0L);
		response.setGlosa("Autorizar Dummy");
		
		return response;
	}
	
	public Response listTrasladosASalaProveedores() throws Exception {
		try {
			TrasladosResponse trasladosResponse = new TrasladosResponse();
			trasladosResponse.setCodigo(0L);
			trasladosResponse.setGlosa("");
			trasladosResponse.setTraslados(despachoMobileDAO.listTrasladosASalaProveedores());
			
			return trasladosResponse;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	//mover productos desde Falla de fabricacion hacia traslado
public Response moveDespachoFallasFabricacion(Long idDespacho, String codigoBarra,Usuario usuario)  throws Exception {
		try {
			MoveResponse moveResponse = new MoveResponse();
			FilterOTMobile filter = new FilterOTMobile();
			filter.setCodigoBarra(codigoBarra);
			OrdenTrabajoMobile oT = ordenTrabajoMobileDAO.getOTbyFilter(filter);
			if(oT ==  null){
				filter.setCodigoBarraAccesorio(filter.getCodigoBarra());
				filter.setCodigoBarra(null);
				oT = ordenTrabajoMobileDAO.getOTbyFilter(filter);
			}
			DespachoMobile despachoMobile = despachoMobileDAO.getDespachosbyId(idDespacho);
			filter.setIdDespachoInterno(idDespacho);
			
			if(oT==null){
				throw new SSTException("No existe la orden de trabajo");
			} else if(despachoMobile==null){
				throw new SSTException("No existe el despacho");
			}else {
				filter.setIdOT(oT.getId());
				if(ordenTrabajoMobileDAO.isOtOnDespachoByFilter(filter).equals(0)){
					Gestion gestion = new Gestion();
					gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ", la cual no pertenece al despacho " + idDespacho);
					gestion.setUsuario(usuario);
					gestion.setOt(new OrdenTrabajo());
					gestion.getOt().setId(oT.getId());
					gestionesDAO.saveGestion(gestion);
					throw new SSTException("El producto no corresponde a este despacho");
				}
			}
			DespachoDetalleMobile despachoDetalleMobile = new DespachoDetalleMobile();
			despachoDetalleMobile.setOrdenTrabajo(oT.getId());
			despachoDetalleMobile.setDespachoInterno(idDespacho);
			despachoDetalleMobile.setEstado(Constants.PRODUCTO_ESCANEADO);
			despachoDetalleMobile.setUsuario(usuario.getId());
			
			despachoDetalleMobileDAO.updateEstado(despachoDetalleMobile);
			moveResponse = despachoDetalleMobileDAO.getOTRecibidasYTotal(idDespacho);
			moveResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveResponse.setOrdenTrabajo(oT);
			
			Gestion gestion = new Gestion();
			gestion.setGestion("escanea OT número "+oT.getId()+" para el traslado hacia "+ despachoMobile.getNombreUbicacion());
			gestion.setUsuario(usuario);
			gestion.setOt(new OrdenTrabajo());
			gestion.getOt().setId(oT.getId());
			gestionesDAO.saveGestion(gestion);
			return moveResponse;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}
	
	public Response listTransportistas()  throws Exception {
		try {
			TransportistaResponse transportistaResponse = new TransportistaResponse();
			
			transportistaResponse.setTransportista(transportistaMobileDAO.listTransportistas());
			transportistaResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			
			return transportistaResponse;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response saveTrasportista(Long idDespacho, Long id, String patente, List<String> sellos,Ubicacion ubicacion,Usuario usuario) throws Exception {
		try {
			Response response = new Response();
			if (patente != null && patente.length() > 8) {
				throw new SSTException("Patente " + patente + " demasiado extensa. Maximo 8 caracteres");
			}
			if(ordenTrabajoMobileDAO.getOtForSaveTransportista(idDespacho) > 0){
				List<Sello>	sellosAux = convertSellos(sellos);
				DespachoInterno despachoInterno = despachoInternoDAO.getDespachoById(idDespacho);
				DespachoInternoCamion despachoInternoCamion = despachoInternoCamionDAO.getDespachoInternoCamionUltimo(despachoInterno);
				List<Guia> guias= bodegaService.processGuiasGimMobile(despachoInterno, usuario, ubicacion,despachoInternoCamion);
				DespachoInternoCamion despachoInternoCamionAux = despachoInternoCamionDAO.getDespachoInternoCamionUltimo(despachoInterno);
				for(Sello sello : sellosAux){
					sello.setDespachoInternoCamion(despachoInternoCamionAux);
					selloDAO.save(sello);
				}
				
				for(Guia guia : guias){
					guia.setTransportista(transportistaDAO.getTransportistaById(id));
					guia.getTransportista().setPatente(patente);
					if (guiaDAO.update(guia).equals(0)){
						throw new SSTException("Error al actualizar el registro");
					}
				}
				response.setCodigo(0L);
			} else {
				throw new SSTException("No existen ot's escaneadas para transportar");
			}
			return response;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	private List<Sello> convertSellos(List<String> sellos)throws Exception{
		try {
			List<Sello> sellosAux = new ArrayList<Sello>();
			for(String sello : sellos){
				sellosAux.add(stringToSello(sello));
			}
			
			return sellosAux;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Sello stringToSello(String sello)throws Exception{
		try {
			Sello selloAux = new Sello();
			selloAux.setNumero(sello);
			return selloAux;
		}  catch (Exception e) {
			throw new SSTException("Ingrese solo números");
		}
	}
	
	public Response listTrasladosDesdeSalaProveedores() throws SSTException {
		TrasladosResponse trasladosResponse = new  TrasladosResponse();
		trasladosResponse.setCodigo(0L);
		trasladosResponse.setGlosa("");
		trasladosResponse.setTraslados(new ArrayList<TrasladoMobile>());
		for (int i = 0; i < 10; i++) {
			TrasladoMobile trasladoMobile = new TrasladoMobile();
			trasladoMobile.setId((long) i);
			trasladoMobile.setNombre("Traslado Dummy " + i);
			trasladosResponse.getTraslados().add(trasladoMobile);
		}
		
		return trasladosResponse;
	}
	
	public Response moveDesdeSalaProveedores(String codigoBarra, Usuario usuario) throws Exception {
		try {
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			Gestion gestion = new Gestion();
			if(ordenTrabajoMobile==null){
				throw new SSTException("No existe la orden de trabajo");
			} else{
				gestion.setUsuario(usuario);
				BitacoraInternaMobile bitacoraInternaMobile = bitacoraInternaMobileDAO.getBitacoraInternaMobileByIdOT(ordenTrabajoMobile.getId());
				gestion.setOt(new OrdenTrabajo());
				gestion.getOt().setId(ordenTrabajoMobile.getId());
				if(!bitacoraInternaMobile.getUbicacionInterna().equals(Constants.BODEGA_FALLA_FABRICACION)){
					gestion.setGestion("Intentó mover a sensibles el producto con código "+codigoBarra+",el cuál no está en la ubicación Bodega Falla Fabricación");
					gestionesDAO.saveGestion(gestion);
					throw new SSTException("el producto debe estar en Bodega Falla Fabricación para poder ser trasladado a Sensibles");
				}
			}
			MoveSPResponse moveSPResponse = new MoveSPResponse();
			moveSPResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveSPResponse.setGlosa("");
			moveSPResponse.setOrdenTrabajo(ordenTrabajoMobile);
			
			this.cerrarYCrearBitacoraInterna(ordenTrabajoMobile, Constants.BITACORA_INTERNA_OT_EN_UBICACION_INTERNA_SENSIBLES, null);
			
			gestion.setGestion("traslada producto con código de barra "+ codigoBarra + "a ubicación interna Sensibles");
			gestionesDAO.saveGestion(gestion);

			return moveSPResponse;

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

//	Listar despachos 10015 a 10012
	public Response listTrasladosAFallasFabricacion() throws Exception {
		try {
			TrasladosResponse trasladosResponse = new TrasladosResponse();
			trasladosResponse.setCodigo(0L);
			trasladosResponse.setGlosa("");
			trasladosResponse.setTraslados(despachoMobileDAO.listTrasladosAFallasFabricacion());
			
			return trasladosResponse;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
		
	}

	// traslado desde 10015 a 10012
	public Response moveHaciaFallasFabricacion(Long idTraslado, String codigoBarra, Usuario usuario) throws Exception {
		try {
			FilterOTMobile filter = new FilterOTMobile();
			filter.setCodigoBarra(codigoBarra);
			OrdenTrabajoMobile oT = ordenTrabajoMobileDAO.getOTbyFilter(filter);
			if(oT ==  null){
				filter.setCodigoBarraAccesorio(filter.getCodigoBarra());
				filter.setCodigoBarra(null);
				oT = ordenTrabajoMobileDAO.getOTbyFilter(filter);
			}
			DespachoMobile despachoMobile = despachoMobileDAO.getDespachosbyId(idTraslado);
			filter.setIdDespachoInterno(idTraslado);
			if(oT==null){
				throw new SSTException("No existe la orden de trabajo");
			} else if(despachoMobile==null){
				throw new SSTException("No existe el despacho");
			}else {
				filter.setIdOT(oT.getId());
				if(ordenTrabajoMobileDAO.isOtOnDespachoByFilter(filter).equals(0)){
					Gestion gestion = new Gestion();
					gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ", la cual no pertenece al despacho " + idTraslado);
					gestion.setUsuario(usuario);
					gestion.setOt(new OrdenTrabajo());
					gestion.getOt().setId(oT.getId());
					gestionesDAO.saveGestion(gestion);
					throw new SSTException("El producto no corresponde a este despacho");
				}
			}
			
			MoveResponse moveResponse = new MoveResponse(); 
			
			DespachoDetalleMobile despachoDetalleMobile = new DespachoDetalleMobile();
			despachoDetalleMobile.setOrdenTrabajo(oT.getId());
			despachoDetalleMobile.setDespachoInterno(idTraslado);
			despachoDetalleMobile.setEstado(Constants.PRODUCTO_ESCANEADO);
			despachoDetalleMobile.setUsuario(usuario.getId());

			despachoDetalleMobileDAO.updateEstado(despachoDetalleMobile);
			moveResponse = despachoDetalleMobileDAO.getOTRecibidasYTotal(idTraslado);
			moveResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveResponse.setOrdenTrabajo(oT);

			Gestion gestion = new Gestion();
			gestion.setGestion("escanea OT número "+oT.getId()+" para el traslado: " + oT.getProveedor()+" - "+oT.getFamilia()+",desde CD hacia Falla Fabricación");
			gestion.setUsuario(usuario);
			gestion.setOt(new OrdenTrabajo());
			gestion.getOt().setId(oT.getId());
			gestionesDAO.saveGestion(gestion);

			return moveResponse;
		} catch (SSTException e) {
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	} 

	private Boolean apruebaControlCalidad(Long idOT) throws Exception{
		try {
			FilterOT filter = new FilterOT();
			filter.setIdOT(idOT);
			OrdenTrabajo oT = sucursalService.getOTByFilter(filter);
			
			if(oT.getcCalidadAprueba()!=null && oT.getcCalidadAprueba()){
				return true;
			} else if(oT.getExcluyeCCalidad()){
				return true;
			} else {
				return this.isExcluidoControlCalidadByIdOT(oT.getId());
			}
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response getOrdenTrabajoMoveUbicacionInterna(String codigoBarra, Usuario usuario) throws Exception {
		try {
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			if(ordenTrabajoMobile==null || ordenTrabajoMobile.getId()==null){
				throw new SSTException("La orde de trabajo no existe");
			}
			UbicacionInternaCD ubicacionInternaCD = this.getUbicacionInternaByIdOT(ordenTrabajoMobile.getId());
			if(ubicacionInternaCD==null){
				throw new SSTException("La orden de trabajo no tiene una ubicación asociada, o tiene más de una");
			}
			ordenTrabajoMobile.setUbicacionInterna(ubicacionInternaCD.getCodigo()+" - "+ubicacionInternaCD.getNombre());
			
			BitacoraInternaMobile bitacoraInternaMobile = bitacoraInternaMobileDAO.getBitacoraInternaMobileByIdOT(ordenTrabajoMobile.getId());
			Gestion gestion = new Gestion();
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(ordenTrabajoMobile.getId());
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			if(bitacoraInternaMobile == null || !bitacoraInternaMobile.getUbicacionInterna().equals(Constants.BODEGA_SERVICIO_TECNICO)){
				gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra en Bodega Servicio Tecnico");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra en Bodega Servicio Tecnico");
			}
			
			OrdenTrabajoResponse ordenTrabajoResponse = new OrdenTrabajoResponse();
			if(this.apruebaControlCalidad(ordenTrabajoMobile.getId())){
				ordenTrabajoResponse.setOrdenTrabajo(ordenTrabajoMobile);
				ordenTrabajoResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			} else {
				gestion.setGestion("la orden de trabajo con código " + codigoBarra + " aún no ha sido revisada en control de calidad");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("la orden de trabajo con código " + codigoBarra + " aún no ha sido revisada en control de calidad");
			}
			return ordenTrabajoResponse;
		} catch(SSTException e){
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Response moveUbicacionInterna(String codigoBarra, Usuario usuario) throws Exception {
		try {
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			if(ordenTrabajoMobile==null || ordenTrabajoMobile.getId()==null){
				throw new SSTException("La orde de trabajo no existe");
			}
			
			UbicacionInternaCD ubicacionInternaCD = this.getUbicacionInternaByIdOT(ordenTrabajoMobile.getId());
			if(ubicacionInternaCD==null){
				throw new SSTException("La orden de trabajo no tiene una ubicación asociada, o tiene más de una");
			}
			
			this.ubicacionInternaOnInventario(ordenTrabajoMobile.getId(),ubicacionInternaCD.getId());
			
			BitacoraInternaMobile bitacoraInternaMobile = bitacoraInternaMobileDAO.getBitacoraInternaMobileByIdOT(ordenTrabajoMobile.getId());
			if(bitacoraInternaMobile==null){
				OrdenTrabajo estadoOT = ordenTrabajoDAO.getEstadoOT(ordenTrabajoMobile.getId());
				throw new SSTException("no se puede almacenar la OT, por que se encuentra en estado "+estadoOT.getEstado().getDescripcion());
			}
			Gestion gestion = new Gestion();
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(ordenTrabajoMobile.getId());
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			if(!bitacoraInternaMobile.getUbicacionInterna().equals(Constants.BODEGA_SERVICIO_TECNICO)){
				gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra en Bodega Servicio Tecnico");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra en Bodega Servicio Tecnico");
			}
			
			MoveResponse moveResponse = new MoveResponse();
			moveResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveResponse.setGlosa("");
			moveResponse.setOrdenTrabajo(ordenTrabajoMobile);
			
			this.cerrarYCrearBitacoraInterna(ordenTrabajoMobile, Constants.BITACORA_INTERNA_OT_ALMACENADA, null);
			ordenTrabajo.setModificado(false);
			ordenTrabajoDAO.updateModificado(ordenTrabajo);
			
			gestion.setGestion("traslada producto con código de barra "+ codigoBarra + "a ubicación interna Sensibles");
			gestionesDAO.saveGestion(gestion);

			return moveResponse;
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private List<UbicacionInternaCD> listUbicacionesByIdProducto(List<UbicacionInternaCD> ubicacionesInternasCD,Integer idProducto) throws Exception{
		try {
			List<UbicacionInternaCD> ubicacionesProducto = new ArrayList<UbicacionInternaCD>();
			for(UbicacionInternaCD ubicacionInternaCD: ubicacionesInternasCD){
				List<Producto> productos = productoDAO.listProductosByIdUbicacionInterna(ubicacionInternaCD.getId());
				for(Producto producto:productos){
					if(producto.getId().equals(idProducto)){
						ubicacionesProducto.add(ubicacionInternaCD);
					}
				}
			}
			return ubicacionesProducto;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private List<UbicacionInternaCD> listUbicacionesByIdFamilia(List<UbicacionInternaCD> ubicacionesInternasCD,String idFamilia) throws Exception{
		try {
			List<UbicacionInternaCD> ubicacionesFamilia = new ArrayList<UbicacionInternaCD>();
			for(UbicacionInternaCD ubicacionInternaCD:ubicacionesInternasCD){
				List<Familia> familias = familiaDAO.listFamiliasByIdUbicacionInterna(ubicacionInternaCD.getId());
				for(Familia familia:familias){
					if(familia.getId().equals(idFamilia)){
						ubicacionesFamilia.add(ubicacionInternaCD);
					}
				}
			}
			return ubicacionesFamilia;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	private List<UbicacionInternaCD> listUbicacionesByIdLinea(List<UbicacionInternaCD> ubicacionesInternasCD, String idLinea) throws Exception{
		try {
			List<UbicacionInternaCD> ubicacionesLinea = new ArrayList<UbicacionInternaCD>();
			for(UbicacionInternaCD ubicacionInternaCD:ubicacionesInternasCD){
				List<Linea> lineas = lineaDAO.listLineasByIdUbicacionInterna(ubicacionInternaCD.getId());
				for(Linea linea:lineas){
					if(linea.getId().equals(idLinea)){
						ubicacionesLinea.add(ubicacionInternaCD);
					}
				}
			}
			return ubicacionesLinea;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	public UbicacionInternaCD getUbicacionInternaByIdOT(Long idOT) throws Exception {
		try {
			FilterOT filter = new FilterOT();
			filter.setIdOT(idOT);
			OrdenTrabajo ordenTrabajo = sucursalService.getOTByFilter(filter);
			List<UbicacionInternaCD> ubicacionesInternasCD = ubicacionInternaDAO.listUbicacionInternasCDByIdOT(idOT);
			if(ubicacionesInternasCD.isEmpty()){
				throw new SSTException("no existe ubicaciones internas para esta OT");
			} else if(ubicacionesInternasCD.size()==1){
				return ubicacionesInternasCD.get(0);
			} else {
				List<UbicacionInternaCD> ubicacionesProducto = this.listUbicacionesByIdProducto(ubicacionesInternasCD, ordenTrabajo.getProducto().getId());
				if(ubicacionesProducto.size()==1){
					return ubicacionesProducto.get(0);
				} 
				
				List<UbicacionInternaCD> ubicacionesFamilia = this.listUbicacionesByIdFamilia(ubicacionesInternasCD, ordenTrabajo.getProducto().getFamilia().getId());
				if(ubicacionesFamilia.size()==1){
					return ubicacionesFamilia.get(0);
				}
				
				List<UbicacionInternaCD> ubicacionesLinea = this.listUbicacionesByIdLinea(ubicacionesInternasCD, ordenTrabajo.getProducto().getFamilia().getLinea().getCodigo());
				if(ubicacionesLinea.size()==1){
					return ubicacionesLinea.get(0);
				}
				
				List<UbicacionInternaCD> ubicacionesSucursal = ubicacionInternaDAO.listUbicacionInternasCDOnlySucursalByIdOT(idOT);
				if(ubicacionesSucursal.size()==1){
					return ubicacionesSucursal.get(0);
				} else {
					throw new SSTException("Existen más de una ubicación para este producto");
				}				
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	
	public Response listDespachosATiendas() throws Exception {
		try {
			DespachoResponse despachoResponse = new DespachoResponse();
			despachoResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			despachoResponse.setDespachos(envioSucursalMobileDAO.listDespachos());
			return despachoResponse;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response moveDespachoTienda(Long idDespacho, String codigoBarra,Usuario usuario) throws Exception{
		try {
			MoveResponse moveResponse = new MoveResponse();
			
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			
			if(ordenTrabajoMobile==null){
				throw new SSTException("No existe la orden de trabajo");
			}
			
			BitacoraInterna bitacoraInterna = bitacoraInternaDAO.getBitacoraInternaById(ordenTrabajoMobile.getId());
			Gestion gestion = new Gestion();
			OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
			ordenTrabajo.setId(ordenTrabajoMobile.getId());
			gestion.setOt(ordenTrabajo);
			gestion.setUsuario(usuario);
			if(bitacoraInterna==null || !bitacoraInterna.getEstado().getId().equals(Constants.BITACORA_INTERNA_OT_ALMACENADA)){
				gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra almacenada");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no se encuentra almacenada");
			}
			
			if(envioSucursalDetalleDAO.isOTInEnvio(idDespacho, ordenTrabajo.getId()).equals(0)){
				gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no pertenece a este despacho");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual no pertenece a este despacho");
			}
			
			
			Integer idUsuarioCarga = envioSucursalMobileDAO.getIdUsuarioCargaByIdDespacho(idDespacho);
			if(idUsuarioCarga!=null){
				if(idUsuarioCarga.equals(usuario.getId())){
					gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual está en un depacho que otro usuario ya está revisando");
					gestionesDAO.saveGestion(gestion);
					throw new SSTException("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ",la cual está en un depacho que otro usuario ya está revisando");
				}
			} else {
				envioSucursalMobileDAO.updateUsuarioCarga(idDespacho, usuario.getId());
			}
			
			this.cerrarYCrearBitacoraInterna(ordenTrabajoMobile, Constants.BITACORA_INTERNA_OT_EN_MESON_ELABORACION, null);
			ordenTrabajo.setModificado(false);
			ordenTrabajoDAO.updateModificado(ordenTrabajo);
			
			gestion.setGestion("orden de trabajo traladada a meson elaboración " + codigoBarra);
			gestionesDAO.saveGestion(gestion);
			moveResponse.setTotalOT(envioSucursalDetalleDAO.getTotalOTOnEnvio(idDespacho).longValue());
			moveResponse.setTotalOTLeidas(envioSucursalDetalleDAO.getTotalOTLeidas(idDespacho).longValue());
			moveResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveResponse.setOrdenTrabajo(ordenTrabajoMobile);
			
			return moveResponse;
		} catch(SSTException e){
			throw e;
		}catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Response terminarDespachoTienda(Long idDespacho, Usuario usuario) throws Exception {
		try {
			List<OrdenTrabajoMobile> oTEscaneadas = ordenTrabajoMobileDAO.listOTOnRDEMEByIdEnvioSucursal(idDespacho);
			if(envioSucursalDetalleDAO.getTotalOTOnEnvio(idDespacho).equals(oTEscaneadas.size())){
				envioSucursalDetalleDAO.cerrarEnvioSucursalDetalleByIdEnvio(idDespacho);
				envioSucursalMobileDAO.cerrarEnvioSucursalByEnvioSucursa(idDespacho,usuario);
				Response response = new Response();
				response.setCodigo(Constants.CODIGO_OK_MOBILE);
				return response;
			} else {
				throw new SSTException("Falta productos por ecanear");
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Response moveHaciaServicioTecnico(String codigoBarra, Usuario usuario) throws Exception {
		try {
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			
			if(ordenTrabajoMobile==null){
				throw new SSTException("No existe la orden de trabajo");
			}
			OrdenTrabajoResponse ordenTrabajoResponse = new OrdenTrabajoResponse();
			ordenTrabajoResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			ordenTrabajoResponse.setGlosa("");
			ordenTrabajoResponse.setOrdenTrabajo(ordenTrabajoMobile);
			
			
			Gestion gestion = new Gestion();
			gestion.setUsuario(usuario);
			gestion.setOt(new OrdenTrabajo());
			gestion.getOt().setId(ordenTrabajoMobile.getId());
			gestion.setGestion("traslada producto con código de barra "+ codigoBarra + "a ubicación interna Sensibles");
			gestionesDAO.saveGestion(gestion);

			return ordenTrabajoResponse;

		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	private Boolean isExcluidoControlCalidadByIdOT(Long idOT) throws Exception {
		try {
			OrdenTrabajo oT = ordenTrabajoDAO.getOTById(idOT);
			if (productoDAO.isProductoExcuidoByIdProducto(oT.getProducto().getId()) > 0) {
				return true;
			} else if (familiaDAO.isFamiliaExcluidaCCByIdProducto(oT.getProducto().getId()) > 0) {
				return true;
			} else if(oT.getExcluyeCCalidad()){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Response moveControlCalidad(String codigoBarra,Usuario usuario) throws Exception {
		try {
			MoveCCResponse moveCCResponse = new MoveCCResponse();
			OrdenTrabajoMobile ordenTrabajoMobile= ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			
			if(ordenTrabajoMobile==null){
				throw new SSTException("No existe la orden de trabajo");
			}
			this.ubicacionInternaOnInventario(ordenTrabajoMobile.getId(),Constants.BODEGA_CONTROL_CALIDAD);
			
			OrdenTrabajo oT = productoDAO.getProductoOTById(ordenTrabajoMobile.getId());
			oT.setId(ordenTrabajoMobile.getId());
			Gestion gestion = new Gestion();
			gestion.setUsuario(usuario);
			gestion.setOt(oT);
			if(this.isExcluidoControlCalidadByIdOT(oT.getId())){
				gestion.setGestion("Intentó mover a control de calidad el producto con código "+codigoBarra+", el cual está excluído de control de calidad");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("este producto no requiere control de calidad");
			}
			
			BitacoraInternaMobile bitacoraInterna = bitacoraInternaMobileDAO.getBitacoraInternaMobileByIdOT(ordenTrabajoMobile.getId());
			if(bitacoraInterna==null || !bitacoraInterna.getUbicacionInterna().equals(Constants.BODEGA_SERVICIO_TECNICO)){
				gestion.setGestion("Intentó mover a control de calidad el producto con código "+codigoBarra+", el cual no se encuentra en la bodega servicio técnico(RDEST)");
				gestionesDAO.saveGestion(gestion);
				throw new SSTException("el producto debe estar en la bodega servicio técnico para poder ser trasladado a control de calidad");
			}
			moveCCResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			moveCCResponse.setOrdenTrabajo(ordenTrabajoMobile);
			
			this.cerrarYCrearBitacoraInterna(ordenTrabajoMobile, Constants.BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD, null);
			oT.setModificado(false);
			ordenTrabajoDAO.updateModificado(oT);
			
			gestion.setGestion("Traslada para revisión en control de calidad");
			gestionesDAO.saveGestion(gestion);
			
			
			return moveCCResponse;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Response getOTByCodigo(String codigoBarra) {
		OrdenTrabajoResponse ordenTrabajoResponce =  new OrdenTrabajoResponse();
		try {
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			if (ordenTrabajoMobile == null) {
				ordenTrabajoResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
				ordenTrabajoResponce.setGlosa("NO EXISTE ORDEN DE TRABAJO CON ESE CODIGO EN EL SISTEMA");
			} else {
				ordenTrabajoResponce.setCodigo(Constants.CODIGO_OK_MOBILE);
				ordenTrabajoResponce.setOrdenTrabajo(ordenTrabajoMobile);
			}
		} catch (Exception e) {
			log.error(e,e);
			ordenTrabajoResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
			ordenTrabajoResponce.setGlosa(e.getMessage());
		}
		return ordenTrabajoResponce;
	}

	// public Response listDespachosFallasFabricacion() {
	// DespachoResponse despachoResponce = new DespachoResponse();
	// try {
	// List<DespachoMobile> despachos =
	// despachoMobileDAO.listDespachosFallasFabricacion();
	// if(despachos.isEmpty()){
	// despachoResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
	// despachoResponce.setGlosa("NO EXISTEN VISITAS DE PROVEEDORES");
	// } else {
	// despachoResponce.setCodigo(Constants.CODIGO_OK_MOBILE);
	// despachoResponce.setDespachos(despachos);
	// }
	// } catch (Exception e) {
	// log.error(e,e);
	// despachoResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
	// despachoResponce.setGlosa(e.getMessage());
	// }
	// return despachoResponce;
	// }

	//listar los despachos desde Fallas de fabricacion hacia clasificacion de OT
	public Response listDespachosFallasFabricacion() throws Exception{
		try {
			DespachoResponse despachoResponse = new DespachoResponse();
			despachoResponse.setDespachos(despachoMobileDAO.listDespachosFallasFabricacion());
			despachoResponse.setCodigo(Constants.CODIGO_OK_MOBILE);
			return despachoResponse;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}

	public Response moveSalaProveedores(Long idDespacho, String codigoBarra, Usuario usuario) throws Exception{
		MoveSPResponse responseMoveSP = new MoveSPResponse();
		try {
			FilterOTMobile filter = new FilterOTMobile();
			filter.setCodigoBarra(codigoBarra);
			OrdenTrabajoMobile oT = ordenTrabajoMobileDAO.getOTByCodigoHaciaSP(filter);
			if(oT == null){
				filter.setCodigoBarraAccesorio(codigoBarra);
				filter.setCodigoBarra(null);
				oT = ordenTrabajoMobileDAO.getOTByCodigoHaciaSP(filter);
			}
			DespachoMobile despachoMobile = despachoMobileDAO.getDespachosbyId(idDespacho);
			filter.setIdDespachoInterno(idDespacho);
			
			if(oT==null){
				throw new SSTException("No existe la orden de trabajo");
			} else if(despachoMobile==null){
				throw new SSTException("No existe el despacho");
			}else {
				filter.setIdOT(oT.getId());
				if(ordenTrabajoMobileDAO.isOtOnDespachoByFilter(filter).equals(0)){
					Gestion gestion = new Gestion();
					gestion.setGestion("Se escaneo erroneamente la orden de trabajo con código " + codigoBarra + ", la cual no pertenece al despacho " + idDespacho);
					gestion.setUsuario(usuario);
					gestion.setOt(new OrdenTrabajo());
					gestion.getOt().setId(oT.getId());
					gestionesDAO.saveGestion(gestion);
					throw new SSTException("El producto no corresponde a este despacho");
				} else if(oT.getIdEstadoDespachoDetalle().equals(Constants.PRODUCTO_ESCANEADO)){
					Gestion gestion = new Gestion();
					gestion.setGestion("se vuelve a escanear OT  "+oT.getId());
					gestion.setUsuario(usuario);
					gestion.setOt(new OrdenTrabajo());
					gestion.getOt().setId(oT.getId());
					gestionesDAO.saveGestion(gestion);
					
					responseMoveSP = despachoDetalleMobileDAO.getOTRecibidasYTotalInSP(idDespacho);
					responseMoveSP.setOrdenTrabajo(oT);
					responseMoveSP.setCodigo(Constants.CODIGO_OK_MOBILE);
					return responseMoveSP; 
				}
			}
			
			DespachoDetalleMobile despachoDetalleMobile = new DespachoDetalleMobile();
			despachoDetalleMobile.setOrdenTrabajo(oT.getId());
			despachoDetalleMobile.setDespachoInterno(idDespacho);
			despachoDetalleMobile.setEstado(Constants.PRODUCTO_ESCANEADO);
			despachoDetalleMobile.setUsuario(usuario.getId());
			
			
			despachoDetalleMobileDAO.updateEstado(despachoDetalleMobile);
			responseMoveSP = despachoDetalleMobileDAO.getOTRecibidasYTotalInSP(idDespacho);
			responseMoveSP.setOrdenTrabajo(oT);
			
			this.cerrarYCrearBitacoraInterna(oT, Constants.BITACORA_INTERNA_ESTADO_OT_TRASLADO_A_SALA_PROVEEDORES, idDespacho);
			
			Gestion gestion = new Gestion();
			gestion.setGestion("escanea OT número "+oT.getId()+" para el traslado: " + oT.getProveedor()+" - "+oT.getFamilia());
			gestion.setUsuario(usuario);
			gestion.setOt(new OrdenTrabajo());
			gestion.getOt().setId(oT.getId());
			gestionesDAO.saveGestion(gestion);
			
			this.terminarEscaneoToSPOnFallaFabricacion(despachoMobileDAO.getDespachosbyId(idDespacho));
			responseMoveSP.setCodigo(Constants.CODIGO_OK_MOBILE);
			return responseMoveSP; 
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		} 
	}

	private BitacoraInternaMobile cerrarYCrearBitacoraInterna(OrdenTrabajoMobile oT, Integer idEstadoNuevaBitacora,Long idDespacho) throws Exception {
		try {
			bitacoraInternaMobileDAO.cerrarBitacoraInternaByIdOT(oT.getId());
			BitacoraInternaMobile bitacoraInternaMobileNueva = new BitacoraInternaMobile();
			bitacoraInternaMobileNueva.setEstado(idEstadoNuevaBitacora);
			Bitacora bitacora = bitacoraDAO.getBitacoraUltimaUbicacionOT(oT.getId());
			bitacoraInternaMobileNueva.setBitacora(bitacora.getId());
			if (bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_ESTADO_OT_TRASLADO_A_SALA_PROVEEDORES)) {
				bitacoraInternaMobileNueva.setUbicacionInterna(Constants.UBICACION_ID_TRASLADO);
			} else if (bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_ESTADO_OT_EN_UBICACION_INTERNA_SALA_PROVEEDORES)) {
				bitacoraInternaMobileNueva.setUbicacionInterna(Constants.SALA_PROVEEDORES);
			} else if (bitacoraInternaMobileNueva.getEstado().equals(
					Constants.BITACORA_INTERNA_OT_REVISADA_SALA_PROVEEDORES)) {bitacoraInternaMobileNueva.setUbicacionInterna(Constants.SALA_PROVEEDORES);
					bitacoraInternaMobileNueva.setClasificacion(oT.getClasificacion());
			} else if (bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_OT_EN_UBICACION_INTERNA_SENSIBLES)) {
				bitacoraInternaMobileNueva.setUbicacionInterna(Constants.BODEGA_SENSIBLES);
			} else if(bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_OT_EN_CONTROL_CALIDAD)){
				bitacoraInternaMobileNueva.setUbicacionInterna(Constants.BODEGA_CONTROL_CALIDAD);
			} else if(bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_OT_ALMACENADA)){
				UbicacionInternaCD ubicacionInternaCD = this.getUbicacionInternaByIdOT(oT.getId());
				bitacoraInternaMobileNueva.setUbicacionInterna(ubicacionInternaCD.getId());
			} else if(bitacoraInternaMobileNueva.getEstado().equals(Constants.BITACORA_INTERNA_OT_EN_MESON_ELABORACION)){
				bitacoraInternaMobileNueva.setUbicacionInterna(Constants.BODEGA_MESON_ELABORACION);
			}
			if (idDespacho != null) {
				bitacoraInternaMobileNueva.setDespachoInterno(idDespacho);
			}
			bitacoraInternaMobileNueva.setOrdenTrabajo(oT.getId());

			bitacoraInternaMobileDAO.saveBitacoraInternaMobile(bitacoraInternaMobileNueva);

			return bitacoraInternaMobileNueva;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Ubicacion getUbicacionById(Long idUbicacion) throws Exception {
		try {
			return ubicacionDAO.get(idUbicacion);
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	public Response getUbicaciones(String user, String password, String ip) {
		UbicacionesResponce ubicacionesResponce = new UbicacionesResponce();
		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(user);
			usuario.setPassword(password);
			
			usuario = loginService.login(usuario, ip);
			
			if (usuario == null) {
				throw new SSTException("USUARIO O CONTRASEÑA INVALIDO");
			} else {
				List<Ubicacion> ubicaciones = ubicacionDAO.listAllByIdUsuario(usuario);
				List<UbicacionMobile> ubicacionesMobiles = new ArrayList<UbicacionMobile>();
				
				for (Ubicacion ubicacion : ubicaciones) {
					if (ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA) || ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF)) {
						UbicacionMobile ubicacionMobile = new UbicacionMobile();
						ubicacionMobile.setId(ubicacion.getId());
						ubicacionMobile.setNombre(ubicacion.getNombre());
						ubicacionesMobiles.add(ubicacionMobile);
					}
				}
				if (ubicacionesMobiles.size() == 0) {
					throw new SSTException("NO EXISTEN UBICACIONES CONFIGURADAS PARA EL USUARIO");
				}
				ubicacionesResponce.setUbicaciones(ubicacionesMobiles);
				ubicacionesResponce.setCodigo(Constants.CODIGO_OK_MOBILE);
			}
		} catch (SSTException e) {
			ubicacionesResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
			ubicacionesResponce.setGlosa(e.getMessage());
		} catch (Exception e) {
			log.error(e,e);
			ubicacionesResponce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
			ubicacionesResponce.setGlosa(e.getMessage());
		}
		return ubicacionesResponce;
	}

	public UsuarioResponse login(String user, String password, Long idUbicacion, String ip) throws Exception{
		UsuarioResponse usuarioResponce = new UsuarioResponse();
		try {
			Usuario usuario = new Usuario();
			UsuarioMobile usuarioMobile = new UsuarioMobile();
			usuario.setLogin(user);
			usuario.setPassword(password);

			usuario = loginService.login(usuario, ip);

			if (usuario == null) {
				throw new SSTException("USUARIO O CONTRASEÑA INVALIDO");
			} else {
				List<Ubicacion> ubicaciones = ubicacionDAO.listAllByIdUsuario(usuario);
				Boolean existUbicacion = false;

				for (Ubicacion ubicacion : ubicaciones) {
					if (ubicacion.getId().equals(idUbicacion) && (ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA) || ubicacion.getId().equals(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_FF))) {
						existUbicacion = true;
						break;
					}
				}

				if (!existUbicacion) {
					throw new SSTException("LA UBICACIÓN A LA QUE INTENTA INGRESAR NO ESTA AUTORIZADA");
				}

				usuarioMobile.setId(usuario.getId());
				usuarioMobile.setRun(usuario.getRut());
				usuarioMobile.setNombre(usuario.getNombre());

				usuarioResponce.setCodigo(Constants.CODIGO_OK_MOBILE);

				usuarioResponce.setUsuario(usuarioMobile);
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
		return usuarioResponce;
	}

	private Boolean terminarEscaneoToSPOnFallaFabricacion(DespachoMobile despachoMobile) throws Exception {
		try {
			List<DespachoDetalleMobile> despachosDetalle = despachoDetalleMobileDAO.listDespachoDetalleMobileByIdDespacho(despachoMobile.getId());
			for(DespachoDetalleMobile despachoDetalle:despachosDetalle){
				if(despachoDetalle.getEstado().equals(Constants.ESCANER_EN_PROCESO))
					return false;
			}
			despachoMobile.setEstado(Constants.ESTADO_DESPACHO_SP_EN_TRASLADO);
			despachoMobileDAO.updateEstadoByDespachoMobile(despachoMobile);
			return true;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response listInventarios() throws Exception{
		try {
			InventarioResponse response = new InventarioResponse();
			response.setInventarios(inventarioMobileDAO.list());
			response.setCodigo(Constants.CODIGO_OK_MOBILE);
			return response;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Response inventariar(String codigoBarra,Long id,Usuario usuario) throws Exception{
		try {
			InventarioUbicacionResponse response = new InventarioUbicacionResponse();
			
			OrdenTrabajoMobile ordenTrabajoMobile = ordenTrabajoMobileDAO.getOTByCodigo(codigoBarra);
			if(ordenTrabajoMobile==null || ordenTrabajoMobile.getId()==null){
				throw new SSTException("La orden de trabajo no existe");
			}
			
			Gestion gestion = new Gestion();
			gestion.setUsuario(usuario);
			gestion.setOt(new OrdenTrabajo());
			gestion.getOt().setId(ordenTrabajoMobile.getId());
			
			InventarioUbicacion inventarioUbicacion = inventarioUbicacionDAO.getInventarioUbicacionById(id);
			FilterInventario filterInventario = new FilterInventario();
			filterInventario.setIdOT(ordenTrabajoMobile.getId());
			filterInventario.setIdInventarioUbicacion(inventarioUbicacion.getId());
			
			if(inventarioMobileDAO.existOTOnIventario(filterInventario).equals(0)){
				InventarioProducto inventarioProducto = new InventarioProducto();
				inventarioProducto.setInventariado(true);
				inventarioProducto.setPreparado(false);
				inventarioProducto.setOrdenTrabajo(new OrdenTrabajo());
				inventarioProducto.getOrdenTrabajo().setId(ordenTrabajoMobile.getId());
				inventarioProducto.setInventarioUbicacion(inventarioUbicacion);
				inventarioProductoDAO.save(inventarioProducto);
			} 
			
			inventarioMobileDAO.updateInventariado(ordenTrabajoMobile.getId(), id,usuario);
			gestion.setGestion("Escanea en inventario la OT con codigo "+codigoBarra);
			gestionesDAO.saveGestion(gestion);
			
			response.setOrdenTrabajoMobile(ordenTrabajoMobile);
			response.setCodigo(Constants.CODIGO_OK_MOBILE);
			
			return response;
		
		} catch(SSTException e){
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	

	public void setGestionesDAO(GestionesDAO gestionesDAO) {
		this.gestionesDAO = gestionesDAO;
	}

	public void setOrdenTrabajoMobileDAO(OrdenTrabajoMobileDAO ordenTrabajoMobileDAO) {
		this.ordenTrabajoMobileDAO = ordenTrabajoMobileDAO;
	}

	public void setDespachoMobileDAO(DespachoMobileDAO despachoMobileDAO) {
		this.despachoMobileDAO = despachoMobileDAO;
	}

	public void setDespachoDetalleMobileDAO(DespachoDetalleMobileDAO despachoDetalleMobileDAO) {
		this.despachoDetalleMobileDAO = despachoDetalleMobileDAO;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setBitacoraInternaMobileDAO(BitacoraInternaMobileDAO bitacoraInternaMobileDAO) {
		this.bitacoraInternaMobileDAO = bitacoraInternaMobileDAO;
	}
	
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) throws Exception{
		this.bitacoraDAO = bitacoraDAO;
	}
	
	public void setTransportistaMobileDAO(TransportistaMobileDAO transportistaMobileDAO) throws Exception {
		this.transportistaMobileDAO = transportistaMobileDAO;
	}
	
	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO) throws Exception {
		this.despachoInternoDAO = despachoInternoDAO;
	}
	
	public void setBodegaService(BodegaService bodegaService) throws Exception {
		this.bodegaService = bodegaService;
	}
	
	public void setTransportistaDAO(TransportistaDAO transportistaDAO) throws Exception {
		this.transportistaDAO = transportistaDAO;
	}
	
	
	public void setGuiaDAO(GuiaDAO guiaDAO) throws Exception {
		this.guiaDAO = guiaDAO;
	}
	
	public void setDespachoInternoCamionDAO(DespachoInternoCamionDAO despachoInternoCamionDAO) throws Exception {
		this.despachoInternoCamionDAO = despachoInternoCamionDAO;
	}
	
	public void setSelloDAO(SelloDAO selloDAO) throws Exception {
		this.selloDAO = selloDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public void setFamiliaDAO(FamiliaDAO familiaDAO) {
		this.familiaDAO = familiaDAO;
	}

	public void setSucursalService(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	public void setUbicacionInternaDAO(UbicacionInternaDAO ubicacionInternaDAO) {
		this.ubicacionInternaDAO = ubicacionInternaDAO;
	}

	public void setLineaDAO(LineaDAO lineaDAO) {
		this.lineaDAO = lineaDAO;
	}

	public void setBitacoraInternaDAO(BitacoraInternaDAO bitacoraInternaDAO) {
		this.bitacoraInternaDAO = bitacoraInternaDAO;
	}

	public void setEnvioSucursalMobileDAO(EnvioSucursalMobileDAO envioSucursalMobileDAO) {
		this.envioSucursalMobileDAO = envioSucursalMobileDAO;
	}

	public void setEnvioSucursalDetalleDAO(EnvioSucursalDetalleDAO envioSucursalDetalleDAO) {
		this.envioSucursalDetalleDAO = envioSucursalDetalleDAO;
	}

	public EnvioSucursalDAO getEnvioSucursalDAO() {
		return envioSucursalDAO;
	}

	public void setEnvioSucursalDAO(EnvioSucursalDAO envioSucursalDAO) {
		this.envioSucursalDAO = envioSucursalDAO;
	}

	public void setInventarioMobileDAO(InventarioMobileDAO inventarioMobileDAO) {
		this.inventarioMobileDAO = inventarioMobileDAO;
	}
	
	public void setInventarioProductoDAO(InventarioProductoDAO inventarioProductoDAO){
		this.inventarioProductoDAO=inventarioProductoDAO;
	}

	public void setInventarioUbicacionDAO(InventarioUbicacionDAO inventarioUbicacionDAO) {
		this.inventarioUbicacionDAO = inventarioUbicacionDAO;
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO){
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}
}
