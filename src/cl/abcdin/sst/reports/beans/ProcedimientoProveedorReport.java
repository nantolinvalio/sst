package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.ProcedimientoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Procedimiento;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterProcedimiento;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class ProcedimientoProveedorReport extends CommandReportSST{
	private static final Log log = LogFactory.getLog(FamiliaExcluidaCCReport.class);
	
	private ProcedimientoDAO procedimientoDAO;

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map,HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		log.debug(filter);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		log.debug(filterColumna.getClass().toString());
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, Procedimiento.class);
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
		    if(columna.getPropiedad().equals("bloquear")){
		        removeMe.add(columna);
		    }
			if (columna.getPropiedad().equals("vigente")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return (Boolean) fields.get("vigente") ? "ACTIVO" : "BLOQUEADO";
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		columnas.removeAll(removeMe);
		return parseAbstractColumns(columnas, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		
		FilterProcedimiento filterProcedimiento = JsonUtil.toReflectedTypes(FilterProcedimiento.class, filter);
		
		log.debug(filterProcedimiento.getClass().toString());
		return procedimientoDAO.listProcedimientosProductosByFilter(filterProcedimiento);
	}


	public void setProcedimientoDAO(ProcedimientoDAO procedimientoDAO) {
		this.procedimientoDAO = procedimientoDAO;
	}

}
