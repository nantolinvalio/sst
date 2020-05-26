package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.GuiaReport;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;
import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.utils.Constants;

public class GuiaEnvioRetiroReport extends CommandReport {

	private GuiaDAO guiaDAO;
	private UbicacionDAO ubicacionDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private ProductoDAO productoDAO;
	private ParteDAO parteDAO;
	private UtilDAO utilDAO;
	private ClienteDAO clienteDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		Long idOT = Long.parseLong(getData("idOT", map));
		Long idGuia = Long.parseLong(getData("idGuia", map));
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(Constants.SESSION_USER);
		Guia guia = guiaDAO.get(idGuia);
		guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
		guia.setDestino(ubicacionDAO.get(guia.getDestino().getId()));
		
		OrdenTrabajoGeneral ot = ordenTrabajoDAO.getOTGeneralByOT(idOT);
		ot.setProductoReport(productoDAO.getProductoById(guia.getOrdenTrabajo().getProducto().getId()));
		
		
		
		if(guia.getTipoGuia() != null && (guia.getTipoGuia().equals(Constants.GUIA_TIPO_ENVIAR_A_CLIENTE) || guia.getTipoGuia().equals(Constants.GUIA_TIPO_RETIRO_EN_CLIENTE))){
			ot.setPartesListReport(null);
		}
		else{
			ot.setPartesListReport(parteDAO.listPartesOTByOT(idOT));
		}
        ot.setFecha(utilDAO.getDate());
        ot.setUsuarioReport(usuario);
		
        GuiaReport guiaReport = new GuiaReport();
        guiaReport.setGuia(guia);
        guiaReport.setOrdenTrabajoGeneral(ot);
        
        if (guia.getDestino().getId().equals(Constants.UBICACION_ID_CLIENTE)) {
        	Cliente cliente = clienteDAO.getClienteByOT(idOT);
        	guia.getDestino().setRut(cliente.getRut());
        	guia.getDestino().setNombre(cliente.getNombreCompleto());
        	guia.getDestino().setComuna(cliente.getComuna());
        	guia.getDestino().setDireccion(cliente.getCalle() + " " + cliente.getNumeroCasa());
		} else if (guia.getDestino().getTipo().equals(Constants.UBICACION_BODEGA_REGIONAL) || 
					guia.getDestino().getTipo().equals(Constants.UBICACION_CENTRO_DISTRIBUCION) || 
					guia.getDestino().getTipo().equals(Constants.UBICACION_SUCURSAL)) {
			guia.getDestino().setRut(Constants.RUT_ABCDIN);
		}
        
        List<GuiaReport> guias = new ArrayList<GuiaReport>();
        guias.add(guiaReport);
        
		
		return guias;
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
	

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

}
