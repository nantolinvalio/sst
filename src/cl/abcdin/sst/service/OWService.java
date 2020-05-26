package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.DocumentoDAO;
import cl.abcdin.sst.dao.FacturaGestionDAO;
import cl.abcdin.sst.dao.GuiaAgrupadaDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.OWDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.PeticionDocumentoDetalleDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.PeticionDocumento;
import cl.abcdin.sst.model.PeticionDocumentoDetalle;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.vo.OWCabecera;
import cl.abcdin.sst.model.vo.OWCall;
import cl.abcdin.sst.model.vo.OWDetalle;
import cl.abcdin.sst.model.vo.ProductoReport;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.OWConstants;

public class OWService {
	private static final Log log = LogFactory.getLog(OWService.class);

	private OWDAO oWDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private DocumentoDAO documentoDAO;
	private GuiaDAO guiaDAO;
	private GuiaAgrupadaDAO guiaAgrupadaDAO;
	private PeticionDocumentoDetalleDAO peticionDocumentoDetalleDAO;
	private UbicacionDAO ubicacionDAO;
	private FacturaGestionDAO facturaGestionDAO;

	public Boolean validaStock(Ubicacion origen, String ubicacion, Producto producto, Integer cantidad) throws Exception {
		String origenOW = StringUtils.leftPad(origen.getId().toString(),12,' ');
		String ubicacionOW = ubicacion;
		Integer productoOW = producto.getId();
		Integer cantidadOW = cantidad;
		return oWDAO.validaStock(origenOW, ubicacionOW, productoOW, cantidadOW);
	}
	
	public Integer createXN(OrdenTrabajo ordenTrabajo,Ubicacion ubicacion) throws Exception {
		try {
			Integer resultado;

			Documento documento = new Documento();
			documento.setId(ordenTrabajo.getIdDocumento());
			documento.setTipo(ordenTrabajo.getTipoDocumento());
			documento = documentoDAO.getDocumentoByIdAndTipo(documento);
			
			OWCabecera cabecera = new OWCabecera();
			cabecera.setNumeroDocumento(documento.getId());
			cabecera.setOrigenCC(ubicacion.getId());
			cabecera.setOrigenUB(OWConstants.UBICACION_FDP);
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setDestinoCC(documento.getIdCliente());
			cabecera.setTipoDocumento(documento.getTipoOW());
			cabecera.setTipo(OWConstants.TRASACCION_OW_XN);
			
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			OWDetalle detalle = new OWDetalle();
			detalle.setId(cabecera.getId());
			detalle.setCantidad(-1);
			detalle.setProducto(ordenTrabajo.getProducto().getId());
			
			resultado = oWDAO.saveDetalle(detalle);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar el detalle en OW");
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
	
			
			if(ordenTrabajo.getBanderaOrigenOT().equals(Constants.CASO_USO_3) || 
				ordenTrabajo.getBanderaOrigenOT().equals(Constants.CASO_USO_4) ||
				ordenTrabajo.getBanderaOrigenOT().equals(Constants.CASO_USO_10)){
				
				if(ordenTrabajo.getIdentificadorTipoXN().equals(Constants.TIPO_XN_BODEGA_CON) || 
						ordenTrabajo.getIdentificadorTipoXN().equals(Constants.TIPO_XN_PROVEEDOR_CON) || 
						ordenTrabajo.getIdentificadorTipoXN().equals(Constants.TIPO_XN_TRANSPORTE_CON)){
					owCall.setMcu(Constants.ID_SUCURSAL_10490);
					ordenTrabajo.setCentroCostoXN(Constants.ID_SUCURSAL_10490.trim());
					ordenTrabajoDAO.updateCCXN(ordenTrabajo);
					oWDAO.createXNCC(owCall);
				}else if(ordenTrabajo.getIdentificadorTipoXN().equals(Constants.TIPO_XN_PROVEEDOR_SIN)){
					owCall.setMcu(Constants.ID_SUCURSAL_10488);
					ordenTrabajo.setCentroCostoXN(Constants.ID_SUCURSAL_10488.trim());
					ordenTrabajoDAO.updateCCXN(ordenTrabajo);
					oWDAO.createXNCC(owCall);
				}else if(ordenTrabajo.getIdentificadorTipoXN().equals(Constants.TIPO_XN_TRANSPORTE_SIN)){
					owCall.setMcu(Constants.ID_SUCURSAL_10487);
					ordenTrabajo.setCentroCostoXN(Constants.ID_SUCURSAL_10487.trim());
					ordenTrabajoDAO.updateCCXN(ordenTrabajo);
					oWDAO.createXNCC(owCall);
				}else{
					oWDAO.createXN(owCall);
				}
			}else{
				oWDAO.createXN(owCall);
			}

			if (owCall.getEstado() != 0) {
				throw new SSTException("No se ha podido grabar la XN: " +  owCall.getSalida());
			}
			
			return owCall.getIdSalida();
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer createXN(Long idOT,Ubicacion ubicacion) throws Exception {
		try {
			OrdenTrabajo ordenTrabajo = ordenTrabajoDAO.getOTById(idOT);
			return this.createXN(ordenTrabajo,ubicacion);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer createTSTO(Long idGuia, String ubicacionOrigen, String ubicacionDestino) throws Exception {
		try {
			Ubicacion destinoCC;
			Guia guia = guiaDAO.get(idGuia);
			List<ProductoReport> productoReports = new ArrayList<ProductoReport>();
			destinoCC = ubicacionDAO.get(guia.getDestino().getId());
			if(ubicacionOrigen == null){
				ubicacionOrigen = OWConstants.UBICACION_FDP;
			}
			productoReports = guiaAgrupadaDAO.listDetalleGuiaAgrupadaByGuia(guia);
			
			if(productoReports.size() == 0){
				ProductoReport nuevoProductoReport = new ProductoReport();
				nuevoProductoReport.setCantidadOT(1);
				nuevoProductoReport.setProducto(guia.getOrdenTrabajo().getProducto());
				productoReports.add(nuevoProductoReport);
			}
			OWCabecera cabecera = new OWCabecera();
			cabecera.setNumeroDocumento(guia.getNumero().intValue());
			cabecera.setOrigenCC(guia.getOrigen().getId());
			cabecera.setOrigenUB(ubicacionOrigen);
			cabecera.setDestinoUB(ubicacionDestino);
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setTipo(OWConstants.TRASACCION_OW_TSTO);
			
			if(destinoCC.getTipo().equals(Constants.TIPO_UBICACION_CASA_REMATE) ||destinoCC.getTipo().equals(Constants.TIPO_UBICACION_LIQUIDADORA)){
				destinoCC = ubicacionDAO.get(Constants.UBICACION_CENTRO_DISTRIBUCION_LA_VARA_10013);
				cabecera.setDestinoCC(destinoCC.getId());
			} else {
				cabecera.setDestinoCC(guia.getDestino().getId());
			}  

			Integer resultado;
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			for (ProductoReport productoReport : productoReports) {
				OWDetalle detalle = new OWDetalle();
				detalle.setId(cabecera.getId());
				detalle.setCantidad(productoReport.getCantidadOT());
				detalle.setProducto(productoReport.getProducto().getId());
				
				
				resultado = oWDAO.saveDetalle(detalle);
				if (resultado == 0) {
					throw new SSTException("No se ha podido grabar el detalle en OW");
				}
			}
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			
			oWDAO.createTSTO(owCall);
			
			if (owCall.getEstado() != 0) {
				throw new SSTException("No se ha podido grabar la TS/TO en OW: " +  owCall.getSalida());
			}
			
			return owCall.getIdSalida();
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	

	public Integer createTSTO(Long idGuia) throws Exception {
		return createTSTO(idGuia,OWConstants.UBICACION_FDP,OWConstants.UBICACION_FDP);
	}

	public Integer receiveTO(Long idGuia) throws Exception {
		try {
			Guia guia = guiaDAO.get(idGuia);
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(guia.getNumeroTSTO().longValue());
			owCall.setUsuario(OWConstants.USER_OW);
			
			oWDAO.receiveTO(owCall);
			if (owCall.getEstado() != 0) {
				throw new SSTException(owCall.getSalida());
			} 
			return owCall.getIdSalida();
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer receiveTOForUbicacion(Long idGuia) throws Exception {
		try {
			Integer resultado;
			Guia guia = guiaDAO.get(idGuia);
			
			OWCabecera cabecera = new OWCabecera();
			cabecera.setOrigenCC(guia.getOrigen().getId());
			cabecera.setOrigenUB(OWConstants.UBICACION_FDP);
			cabecera.setDestinoCC(guia.getDestino().getId());
			cabecera.setObservacion("Recepcion de TO ");
			cabecera.setNumeroDocumento(guia.getNumeroTSTO());
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setTipo(OWConstants.TRASACCION_OW_TO);
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			List<PeticionDocumentoDetalle> peticionDocumentoDetalles = peticionDocumentoDetalleDAO.ListOtsForTO(guia.getId());
			for(PeticionDocumentoDetalle detalles : peticionDocumentoDetalles){
				OWDetalle detalle = new OWDetalle();
				detalle.setId(cabecera.getId());
				detalle.setCantidad(detalles.getCantidad());
				detalle.setProducto(detalles.getOrdenTrabajo().getProducto().getId());
				detalle.setUbicacionTO(convertUbicaciontoOW(detalles.getOrdenTrabajo().getClasificacion().getCodigo()));
				
				resultado = oWDAO.saveDetalle(detalle);
				if (resultado == 0) {
					throw new SSTException("No se ha podido grabar el detalle en OW");
				}
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			owCall.setUsuario(OWConstants.USER_OW);
			
			oWDAO.receiveMultiTO(owCall);
			if (owCall.getEstado() != 0) {
				throw new SSTException(owCall.getSalida());
			} 
			return owCall.getIdSalida();
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer moveIT(OrdenTrabajo ot, Long idOrigenCC,String origen, String destino) throws Exception {
		try {
			Integer resultado;
			
			OWCabecera cabecera = new OWCabecera();
			cabecera.setOrigenCC(idOrigenCC);
			cabecera.setOrigenUB(origen);
			cabecera.setDestinoUB(destino);
			cabecera.setObservacion("movimiento IT");
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setTipo(OWConstants.TRASACCION_OW_IT);
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			
			OrdenTrabajo ordenTrabajo =ordenTrabajoDAO.getOTById(ot.getId());
			OWDetalle detalle = new OWDetalle();
			detalle.setId(cabecera.getId());
			detalle.setCantidad(1);
			detalle.setProducto(ordenTrabajo.getProducto().getId());
			
			resultado = oWDAO.saveDetalle(detalle);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar el detalle en OW");
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			
			oWDAO.moveIT(owCall);
			if (owCall.getEstado() != 0) {
				throw new SSTException(owCall.getSalida());
			} 
			return owCall.getIdSalida();
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer moveITAgrupada(OrdenTrabajo ot, Long idOrigenCC,String origen, String destino) throws Exception {
		try {
			Integer resultado;
			
			OWCabecera cabecera = new OWCabecera();
			cabecera.setOrigenCC(idOrigenCC);
			cabecera.setOrigenUB(origen);
			cabecera.setDestinoUB(destino);
			cabecera.setObservacion("movimiento IT");
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setTipo(OWConstants.TRASACCION_OW_IT);
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			
//			OrdenTrabajo ordenTrabajo =ordenTrabajoDAO.getOTById(ot.getId());
			OWDetalle detalle = new OWDetalle();
			detalle.setId(cabecera.getId());
			detalle.setCantidad(ot.getProducto().getCantidad());
			detalle.setProducto(ot.getProducto().getId());
			
			resultado = oWDAO.saveDetalle(detalle);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar el detalle en OW");
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			
			oWDAO.moveIT(owCall);
			if (owCall.getEstado() != 0) {
				throw new SSTException(owCall.getSalida());
			} 
			return owCall.getIdSalida();
			
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer moveSC(PeticionDocumento peticionDocumento,Long origenCC,String origenUbicacion,Long destinoCC,Long numeroGuia,Ubicacion ubicacion) throws Exception {
		try {
			Integer resultado;
			OWCabecera cabecera = new OWCabecera();
			cabecera.setDestinoCC(destinoCC);
			cabecera.setOrigenCC(origenCC);
			cabecera.setOrigenUB(origenUbicacion);
			cabecera.setObservacion("número de guía : "+numeroGuia.toString());
			cabecera.setTipo(OWConstants.TRASACCION_OW_SC);
			
			
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			List<PeticionDocumentoDetalle> detalles = peticionDocumentoDetalleDAO.getForFacturacion(peticionDocumento);
			for(PeticionDocumentoDetalle peticiondetalle : detalles){
				OWDetalle detalle = new OWDetalle();
				detalle.setId(cabecera.getId());
				detalle.setProducto(peticiondetalle.getOrdenTrabajo().getProducto().getId());
				detalle.setPorcentaje(peticiondetalle.getOrdenTrabajo().getRecuperacion());
				detalle.setCantidad(-peticiondetalle.getCantidad());
				detalle.setDestinoCC(this.convertFacturarOW(origenCC.toString()));
				resultado = oWDAO.saveDetalle(detalle);
				if (resultado == 0) {
					throw new SSTException("No se ha podido grabar el detalle en OW");
				}
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			oWDAO.moveSC(owCall);
			
			if (owCall.getEstado() != 0) {
				throw new SSTException("Comuniquese con el administrador ya que se ha producido el siguiente problema en OW "+owCall.getSalida());
			} else {
				return owCall.getIdSalida();
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer moveSCDesdePostVenta(Factura factura,Long origenCC,String origenUbicacion,Long destinoCC,Ubicacion ubicacion) throws Exception {
		try {
			Integer resultado;
			OWCabecera cabecera = new OWCabecera();
			cabecera.setDestinoCC(destinoCC);
			cabecera.setOrigenCC(origenCC);
			cabecera.setOrigenUB(origenUbicacion);
//			cabecera.setObservacion("número de guía : prueba");
			cabecera.setTipo(OWConstants.TRASACCION_OW_SC);
			
			
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			List<PeticionDocumentoDetalle> detalles= facturaGestionDAO.getProductoForFacturacion(factura);
			for(PeticionDocumentoDetalle peticiondetalle : detalles){
				OWDetalle detalle = new OWDetalle();
				detalle.setId(cabecera.getId());
				detalle.setProducto(peticiondetalle.getOrdenTrabajo().getProducto().getId());
				detalle.setPorcentaje(peticiondetalle.getOrdenTrabajo().getRecuperacion());
				detalle.setCantidad(-peticiondetalle.getCantidad());
				detalle.setDestinoCC(this.convertFacturarOW(origenCC.toString()));
				resultado = oWDAO.saveDetalle(detalle);
				if (resultado == 0) {
					throw new SSTException("No se ha podido grabar el detalle en OW");
				}
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			oWDAO.moveSC(owCall);
			
			if (owCall.getEstado() != 0) {
				throw new SSTException("Comuniquese con el administrador ya que se ha producido el siguiente problema en OW "+owCall.getSalida());
			} else {
				return owCall.getIdSalida();
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public Integer moveDM(PeticionDocumento peticionDocumento,Integer numeroGuia,Ubicacion origenCC,String origenUbicacion, Ubicacion destinoCC) throws Exception {
		try {
			Integer resultado;
			
			OWCabecera cabecera = new OWCabecera();
			cabecera.setOrigenCC(origenCC.getId());
			cabecera.setOrigenUB(origenUbicacion);
			cabecera.setObservacion("número de guía : "+numeroGuia.toString());
			cabecera.setUsuario(OWConstants.USER_OW);
			cabecera.setTipo(OWConstants.TRASACCION_OW_DM);
			
			resultado = oWDAO.saveCabecera(cabecera);
			if (resultado == 0) {
				throw new SSTException("No se ha podido grabar la cabecera en OW");
			}
			
			List<PeticionDocumentoDetalle> detalles = peticionDocumentoDetalleDAO.getForFacturacion(peticionDocumento);
			for(PeticionDocumentoDetalle peticiondetalle : detalles){
				OWDetalle detalle = new OWDetalle();
				detalle.setId(cabecera.getId());
				detalle.setCantidad(-peticiondetalle.getCantidad());
				detalle.setProducto(peticiondetalle.getOrdenTrabajo().getProducto().getId());
				resultado = oWDAO.saveDetalle(detalle);
				if (resultado == 0) {
					throw new SSTException("No se ha podido grabar el detalle en OW");
				}
			}
			
			OWCall owCall = new OWCall();
			owCall.setIdEntrada(cabecera.getId());
			oWDAO.moveDM(owCall);
			
			if (owCall.getEstado() != 0) {
				throw new SSTException("Comuniquese con el administrador ya que se ha producido el siguiente problema en OW "+owCall.getSalida());
			} else {
				return owCall.getIdSalida();
			}
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public String convertUbicaciontoOW(String ubicacion){
			String ubicacionOW="";
			if(ubicacion.equals("DA")){
				ubicacionOW = Constants.UBICACION_DA_OW;
			} else if(ubicacion.equals("AV")){
				ubicacionOW = Constants.UBICACION_AV_OW;
			} else if(ubicacion.equals("CP")){
				ubicacionOW = Constants.UBICACION_CP_OW;
			} else if(ubicacion.equals("RP")){
				ubicacionOW = Constants.UBICACION_RP_OW;
			} else if(ubicacion.equals("TR")){
				ubicacionOW = Constants.UBICACION_RTR_OW;
			} else if(ubicacion.equals("RDEFP")){
				ubicacionOW = Constants.UBICACION_FF_OW;
			}
			return ubicacionOW;
	}
	
	private String convertFacturarOW(String ubicacion){
		String ubicacionConvertida = "";
		ubicacionConvertida = StringUtils.leftPad(ubicacion,Constants.LARGO_FACTURAR_OW,Constants.LEFT_PAD_FACTURAR_OW);
		return ubicacionConvertida;
	}
	

	public void setoWDAO(OWDAO oWDAO) {
		this.oWDAO = oWDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public DocumentoDAO getDocumentoDAO() {
		return documentoDAO;
	}

	public void setDocumentoDAO(DocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}

	public void setGuiaAgrupadaDAO(GuiaAgrupadaDAO guiaAgrupadaDAO) {
		this.guiaAgrupadaDAO = guiaAgrupadaDAO;
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

	public void setPeticionDocumentoDetalleDAO(PeticionDocumentoDetalleDAO peticionDocumentoDetalleDAO) {
		this.peticionDocumentoDetalleDAO = peticionDocumentoDetalleDAO;
	}
	
	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}
	
	public void setFacturaGestionDAO(FacturaGestionDAO facturaGestionDAO) {
		this.facturaGestionDAO = facturaGestionDAO;
	}
}
