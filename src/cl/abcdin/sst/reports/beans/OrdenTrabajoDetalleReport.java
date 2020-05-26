package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.AccesorioDAO;
import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ParteDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;
import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.Utils;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoDetalleReport extends CommandReport{

	private UtilDAO utilDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO; 
	private ClienteDAO clienteDAO;
	private AccesorioDAO accesorioDAO;
	private ParteDAO parteDAO;
	private ProveedorDAO proveedorDAO;
	private ProductoDAO productoDAO;
	private TipoFallasDAO tipoFallasDAO;
	
	
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		Long idOT = Long.parseLong(getData("idOT", map));
		OrdenTrabajoGeneral ot = ordenTrabajoDAO.getOTGeneralByOT(idOT);
		Usuario usuario = (Usuario)request.getSession().getAttribute(Constants.SESSION_USER);
		
		ot.setProductoReport(productoDAO.getProductoById(ot.getIdProducto()));
		ot.setClienteReport(clienteDAO.getClienteByOT(idOT));
		Proveedor proveedorReport = proveedorDAO.getProveedorByOt(idOT);
		if (proveedorReport != null) {
			proveedorReport.setRut(Utils.getRunFromId(proveedorReport.getId()));			
		}
		ot.setProveedorReport(proveedorReport);
		
		String numeroAtencion = ot.getNumeroAtencion() != null && ot.getNumeroAtencion() > 0 ? Long.toString(ot.getNumeroAtencion()) : null;
        ot.setAccesoriosListReport(accesorioDAO.listAccesoriosByOT(idOT));
        ot.setPartesListReport(parteDAO.listPartesOTByOT(idOT));
        ot.setTipoFallasListReport(tipoFallasDAO.listTipoFallasByOT(idOT));	
        ot.setNumeroAtencionReport(numeroAtencion);
        ot.setFecha(utilDAO.getDate());
        ot.setUsuarioReport(usuario);
        
        Collection<OrdenTrabajoGeneral> otgList = new ArrayList<OrdenTrabajoGeneral>();
        otgList.add(ot);
		return otgList;
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void setAccesorioDAO(AccesorioDAO accesorioDAO) {
		this.accesorioDAO = accesorioDAO;
	}

	public void setParteDAO(ParteDAO parteDAO) {
		this.parteDAO = parteDAO;
	}
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO) {
		this.tipoFallasDAO = tipoFallasDAO;
	}
}
