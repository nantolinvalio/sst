package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.service.EjecutivaService;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class FacturacionDetalleReport extends CommandReportSST{
	
	private EjecutivaService ejecutivaService;
	
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, Factura.class);
	}

	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterIndicador filterFactura = JsonUtil.toReflectedTypes(FilterIndicador.class, filter);
		return ejecutivaService.listFacturasIndicadoresEjecutivaFacturacion(filterFactura);
	}


	public void setEjecutivaService(EjecutivaService ejecutivaService) {
		this.ejecutivaService = ejecutivaService;
	}
}
