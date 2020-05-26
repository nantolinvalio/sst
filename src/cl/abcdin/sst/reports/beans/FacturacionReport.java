package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.FacturaGestionDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class FacturacionReport extends CommandReportSST{
	
	
	private FacturaGestionDAO facturaGestionDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, Factura.class);
	}

	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		//String filter = getData("filter", map);
		//FilterFactura filterFactura = JsonUtil.toReflectedTypes(FilterFactura.class, filter);
		return facturaGestionDAO.listFactura();
	}

	@SuppressWarnings({ "unchecked" })
	protected <T> Collection getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("checkMarca")) {
				removeMe.add(columna);
			}			
		}
		columnas.removeAll(removeMe);	
		return parseAbstractColumns(columnas, clazz);
	}
	
	public void setFacturaGestionDAO(FacturaGestionDAO facturaGestionDAO) {
		this.facturaGestionDAO = facturaGestionDAO;
	}
}
