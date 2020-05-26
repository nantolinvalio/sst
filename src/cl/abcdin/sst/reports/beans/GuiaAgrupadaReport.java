package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.GuiaAgrupadaDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.GuiaRemateDAO;
import cl.abcdin.sst.dao.TransportistaDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.model.vo.ProductoReport;
import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.utils.Constants;

public class GuiaAgrupadaReport extends CommandReport{
	private GuiaDAO guiaDAO;
	private UbicacionDAO ubicacionDAO;
	private GuiaAgrupadaDAO guiaAgrupadaDAO;
	private TransportistaDAO transportistaDAO;
	private GuiaRemateDAO guiaRemateDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		Long idGuia = Long.parseLong(getData("idGuia", map));
		
		Guia guia = guiaDAO.get(idGuia);
		
		List<ProductoReport> productosRemate = null;
		if(guia==null && guiaRemateDAO.getGuiaRemateById(idGuia)!=null){
			GuiaPendienteAgrupada guiaAux = guiaRemateDAO.getGuiaRemateById(idGuia);
			guia = new Guia();
			guia.setOrigen(guiaAux.getOrigen());
			guia.setDestino(guiaAux.getDestino());
			guia.setTransportista(guiaAux.getTransportista());
			guia.setFechaEmision(guiaAux.getFechaEmision());
			productosRemate = guiaRemateDAO.listDetalleGuiaRemateByIdGuia(idGuia);
		}
		
		guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
		guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
		
		if(guia.getDestino().getId().equals(Constants.UBICACION_ID_TRANSPORTE)){
			guia.setDestino(new Ubicacion());
			Transportista transportista =  transportistaDAO.getTransportistaById(guia.getTransportista().getId());
			
			guia.getDestino().setNombre(transportista.getNombreCompleto());
			guia.getDestino().setDireccion(transportista.getDireccion());
			guia.getDestino().setRut(transportista.getRut());
			guia.getDestino().setComuna(transportista.getComuna());
			guia.getDestino().setGiro("Transportes");
		} else if (guia.getDestino().getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL) || 
				guia.getDestino().getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) ||
				guia.getDestino().getTipo().equals(Constants.UBICACION_SUCURSAL)){
			guia.getDestino().setRut(Constants.RUT_ABCDIN);
		}
		
		cl.abcdin.sst.model.vo.GuiaAgrupadaReport guiaReport = new cl.abcdin.sst.model.vo.GuiaAgrupadaReport();
        guiaReport.setGuia(guia);
        guiaReport.setProdcutosReport(productosRemate!=null? productosRemate : guiaAgrupadaDAO.listDetalleGuiaAgrupadaByGuia(guia));
        
        List<cl.abcdin.sst.model.vo.GuiaAgrupadaReport> guias = new ArrayList<cl.abcdin.sst.model.vo.GuiaAgrupadaReport>();
        guias.add(guiaReport);
		
		return guias;
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}
	
	public void setGuiaAgrupadaDAO(GuiaAgrupadaDAO guiaAgrupadaDAO) {
		this.guiaAgrupadaDAO = guiaAgrupadaDAO;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setTransportistaDAO(TransportistaDAO transportistaDAO) {
		this.transportistaDAO = transportistaDAO;
	}
	
	public void setGuiaRemateDAO(GuiaRemateDAO guiaRemateDAO) {
		this.guiaRemateDAO = guiaRemateDAO;
	}
}
