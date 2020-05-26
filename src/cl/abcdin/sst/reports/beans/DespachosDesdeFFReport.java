package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class DespachosDesdeFFReport  extends CommandReportSST{

    private DespachoInternoDAO despachoInternoDAO;
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, DespachoInterno.class);
	}

	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("estado.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("estado.glosa");
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			if (columna.getPropiedad().equals("usuario.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("usuario.nombre");
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		Columna colFechaCreacion = new Columna();
		colFechaCreacion.setPropiedad("fechaCreacion");
		columnas.add(colFechaCreacion);
		Columna colIdDespacho = new Columna();
		colIdDespacho.setPropiedad("id");
		columnas.add(colIdDespacho);
		Columna colCantidadOTLeidas = new Columna();
		colCantidadOTLeidas.setPropiedad("cantidadOTLeidas");
		columnas.add(colCantidadOTLeidas);
		Columna colCantidadOT = new Columna();
		colCantidadOT.setPropiedad("cantidadOT");
		columnas.add(colCantidadOT);
		
		
		columnas.removeAll(removeMe);
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterOT filterot = JsonUtil.toReflectedTypes(FilterOT.class, filter);
		return despachoInternoDAO.listDespachosDesdeFFByFilter(filterot);
	}

	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO) {
		this.despachoInternoDAO = despachoInternoDAO;
	}
}
