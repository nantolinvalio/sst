package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.ReporteProveedor;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class ProveedorReportFallas  extends CommandReportSST{

     private ProveedorDAO proveedorDAO;	
     private TipoFallasDAO tipoFallasDAO;

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, ReporteProveedor.class);
	}

	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("producto.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("producto.id").toString() + " " + fields.get("producto.descripcion").toString() ;
					}

					@Override	
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			
		}
		Columna colProductoDescripcion = new Columna();
		colProductoDescripcion.setPropiedad("producto.descripcion");
		columnas.add(colProductoDescripcion);
		columnas.removeAll(removeMe);
		
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterOT filterot = JsonUtil.toReflectedTypes(FilterOT.class, filter);
		List<ReporteProveedor> reporteProveedors = proveedorDAO.listReporteFallaByFilter(filterot);
		
		for (ReporteProveedor obj : reporteProveedors) {
			List<TipoFallas> fallas = tipoFallasDAO.listTipoFallasByOT(obj.getOrdenTrabajo().getId());
			StringBuilder sb = new StringBuilder();
			for (TipoFallas falla : fallas) {
				sb.append(falla.getDescripcion() + ", ");					
			}
			(obj).setFallas(sb.toString());
		}

		return reporteProveedors;
	}

	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO) {
		this.tipoFallasDAO = tipoFallasDAO;
	}
}
