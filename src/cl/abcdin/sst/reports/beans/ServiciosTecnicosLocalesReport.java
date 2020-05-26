package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class ServiciosTecnicosLocalesReport extends CommandReportSST{

	private ServicioTecnicoDAO servicioTecnicoDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, ServicioTecnico.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterServicioTecnico filterServicioTecnico = JsonUtil.toReflectedTypes(FilterServicioTecnico.class, filter);
		Ubicacion ubicacion = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		filterServicioTecnico.setIdOrigen(ubicacion.getId());
		return servicioTecnicoDAO.listSTecnicoFromProductoByUbicacion(filterServicioTecnico);
	}

	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO) {
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}

}
