package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.GuiaAgrupadaDAO;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.reports.common.CommandReport;

public class GuiaAgrupadaGIMReport extends CommandReport{
	private GuiaDAO guiaDAO;
	private GuiaAgrupadaDAO guiaAgrupadaDAO;
	private UbicacionDAO ubicacionDAO;
	private ProveedorDAO proveedorDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		this.ignorePagination = Boolean.TRUE;
		Long idGuia = Long.parseLong(getData("idGuia", map));
		Guia guia = guiaDAO.get(idGuia);		
		guia.setOrigen(ubicacionDAO.get(guia.getOrigen().getId()));
		Ubicacion destino = new Ubicacion();
		destino = ubicacionDAO.get(guia.getDestino().getId());
		if (destino == null){
			destino = proveedorDAO.getProveedorById(guia.getDestino().getId());
		}
		guia.setDestino(destino);
		
		cl.abcdin.sst.model.vo.GuiaAgrupadaReport guiaReport = new cl.abcdin.sst.model.vo.GuiaAgrupadaReport();
        guiaReport.setGuia(guia);
        guiaReport.setProdcutosReport(guiaAgrupadaDAO.listDetalleGuiaAgrupadaByGuia(guia));
        
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
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}
}
