package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.ClienteDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ProductoDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.InformeCambioProducto;
import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.utils.Constants;

public class InformeCambioProductoReport extends CommandReport{

	private UtilDAO utilDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO; 
	private ClienteDAO clienteDAO;
	private ProductoDAO productoDAO;
	private TipoFallasDAO tipoFallasDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		Long idOT = Long.parseLong(getData("idOT", map));
		InformeCambioProducto informeCambioProducto = new InformeCambioProducto();
		informeCambioProducto.setOrdenTrabajo(ordenTrabajoDAO.getOTById(idOT));
		informeCambioProducto.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		informeCambioProducto.setFecha(utilDAO.getDate());
		informeCambioProducto.setTipoFallas(tipoFallasDAO.listTipoFallasByOT(idOT));
		informeCambioProducto.getOrdenTrabajo().setCliente(clienteDAO.getClienteByOT(idOT));
		informeCambioProducto.getOrdenTrabajo().getNumeroIncidenteMarca();
		
		Documento documento = new Documento();
		documento.setId(informeCambioProducto.getOrdenTrabajo().getIdDocumento());
		documento.setTipo(informeCambioProducto.getOrdenTrabajo().getTipoDocumento());
		
		List<Producto> productos = productoDAO.listProductoByTipoDocumentoAndIdDocumento(documento);
		
		for(Producto prod: productos){
			if(prod.getId().equals(informeCambioProducto.getOrdenTrabajo().getProducto().getId())){
				informeCambioProducto.getOrdenTrabajo().setProducto(prod);
			}
		}
		
        Collection<InformeCambioProducto> informeCambioProductos = new ArrayList<InformeCambioProducto>();
        informeCambioProductos.add(informeCambioProducto);
		return informeCambioProductos;
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

	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO) {
		this.tipoFallasDAO = tipoFallasDAO;
	}

}
