package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class OTPendienteReport extends CommandReportSST {

	private OrdenTrabajoDAO ordenTrabajoDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, OrdenTrabajo.class);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("estado.id")) {
				removeMe.add(columna);
			}
		}
		columnas.removeAll(removeMe);
		return parseAbstractColumns(columnas, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filterString = getData("filter", map);
		FilterOT filter = JsonUtil.toReflectedTypes(FilterOT.class, filterString);
		Ubicacion ubicacion = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		filter.setSucursal(ubicacion.getId());
		return ordenTrabajoDAO.listOTPendientesEnSucursal(filter);
	}


	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

}
