package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.OrdenTrabajoNotaCredito;
import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class TicketCambioReport extends CommandReport{

	private UtilDAO utilDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO; 
	private ClienteDAO clienteDAO;
	private ProductoDAO productoDAO;
	
	
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		Long idOT = Long.parseLong(getData("idOT", map));
		OrdenTrabajo ot = ordenTrabajoDAO.getOTById(idOT);
		ot.setCliente(clienteDAO.getClienteByRutFromSST(ot.getCliente()));

		Documento documento = new Documento();
		documento.setId(ot.getIdDocumento());
		documento.setTipo(ot.getTipoDocumento());
		
		List<Producto> productos = productoDAO.listProductoByTipoDocumentoAndIdDocumento(documento);
		
		for(Producto prod: productos){
			if(prod.getId().equals(ot.getProducto().getId())){
				ot.setProducto(prod);
			}
		}
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(Constants.SESSION_USER);
		
		OrdenTrabajoNotaCredito ordenTrabajoNotaCredito = new OrdenTrabajoNotaCredito();
		ordenTrabajoNotaCredito.setOt(ot);
		ordenTrabajoNotaCredito.setUsuarioReport(usuario);
		ordenTrabajoNotaCredito.setFechaReport(utilDAO.getDate());
		
        Collection<OrdenTrabajoNotaCredito> lst = new ArrayList<OrdenTrabajoNotaCredito>();
        lst.add(ordenTrabajoNotaCredito);
		return lst;
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

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
}
