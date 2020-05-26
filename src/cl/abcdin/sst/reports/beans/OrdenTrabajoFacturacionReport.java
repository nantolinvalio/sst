package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.FacturaGestionDAO;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterFactura;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoFacturacionReport extends CommandReportSST{
	private FacturaGestionDAO facturaGestionDAO;
	
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterFactura filterFactura = JsonUtil.toReflectedTypes(FilterFactura.class, filter);
		List<OrdenTrabajo> ordenesTrabajo =  facturaGestionDAO.listOtsFactura(filterFactura);
		return ordenesTrabajo;
	}

	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, OrdenTrabajo.class);
	}

	public void setFacturaGestionDAO(FacturaGestionDAO facturaGestionDAO) {
		this.facturaGestionDAO = facturaGestionDAO;
	}
}
