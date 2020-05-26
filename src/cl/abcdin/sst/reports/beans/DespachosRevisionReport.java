package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class DespachosRevisionReport extends CommandReportSST {
	DespachoInternoDAO despachoInternoDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, DespachoInterno.class);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
//		List<Columna> removeME = new ArrayList<Columna>();
		
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterDespachoInterno filterDespachoInterno = JsonUtil.toReflectedTypes(FilterDespachoInterno.class, filter);
		Ubicacion origen = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		filterDespachoInterno.setIdOrigen(origen.getId());
		return despachoInternoDAO.ListRevisionesByFilter(filterDespachoInterno);
	}
	
	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO){
		this.despachoInternoDAO = despachoInternoDAO;
	}
}
