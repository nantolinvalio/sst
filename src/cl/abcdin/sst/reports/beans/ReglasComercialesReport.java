package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.ReglaComercialDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class ReglasComercialesReport extends CommandReportSST {
	
	private ReglaComercialDAO reglaComercialDAO; 
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, ReglaComercial.class);
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("vigente")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return (Boolean) fields.get("vigente") ? "ACTIVA" : "INACTIVA";
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		return parseAbstractColumns(columnas, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterRegla filterRegla = JsonUtil.toReflectedTypes(FilterRegla.class, filter);
		return reglaComercialDAO.listByFilter(filterRegla);
	}

	public void setReglaComercialDAO(ReglaComercialDAO reglaComercialDAO) {
		this.reglaComercialDAO = reglaComercialDAO;
	}

}
