package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class OrdenesAutorizadasSinGuiaReport extends CommandReportSST{

	private OrdenTrabajoDAO ordenTrabajoDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, OrdenTrabajo.class);
	}
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("checkMarca")) {
				removeMe.add(columna);
			} else if (columna.getPropiedad().equals("tipo.codigo")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("tipo.glosa").toString();
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("producto.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("producto.id").toString() + " " + fields.get("producto.descripcion").toString() + " " + fields.get("producto.marca.nombre").toString();
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("destino.glosa")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("destino.glosa").toString() + " " + fields.get("destino.nombre").toString() + ", " + fields.get("destino.direccion").toString();
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		Columna colTipo = new Columna();
		colTipo.setPropiedad("tipo.glosa");
		Columna colNombre = new Columna();
		colNombre.setPropiedad("producto.descripcion");
		Columna colMarca = new Columna();
		colMarca.setPropiedad("producto.marca.nombre");
		Columna colDestinoNombre = new Columna();
		colDestinoNombre.setPropiedad("destino.nombre");
		Columna colDestinoDireccion = new Columna();
		colDestinoDireccion.setPropiedad("destino.direccion");
		
		columnas.removeAll(removeMe);
		columnas.add(colTipo);
		columnas.add(colNombre);
		columnas.add(colMarca);
		columnas.add(colDestinoNombre);
		columnas.add(colDestinoDireccion);
		return parseAbstractColumns(columnas, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String gridControlString = getData("gridControl", map);
		GridControl gridControl = JsonUtil.toReflectedTypes(GridControl.class, gridControlString);
		return ordenTrabajoDAO.listAllOTRemate(gridControl);
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

}
