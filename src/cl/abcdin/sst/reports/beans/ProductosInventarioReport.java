package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.InventarioProductoDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.InventarioProducto;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class ProductosInventarioReport  extends CommandReportSST{
	private InventarioProductoDAO inventarioProductoDAO;
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, InventarioProducto.class);
	}

	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("inventariado")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return (Boolean) fields.get("inventariado")? "Inventariado": "Sin inventariar";
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			if (columna.getPropiedad().equals("preparado")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return (Boolean) fields.get("preparado")? "Preparado":"Sin preparar";
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
//		Columna colFechaCreacion = new Columna();
//		colFechaCreacion.setPropiedad("fechaCreacion");
//		columnas.add(colFechaCreacion);
//		Columna colIdDespacho = new Columna();
//		colIdDespacho.setPropiedad("id");
//		columnas.add(colIdDespacho);
//		Columna colCantidadOTLeidas = new Columna();
//		colCantidadOTLeidas.setPropiedad("cantidadOTLeidas");
//		columnas.add(colCantidadOTLeidas);
//		Columna colCantidadOT = new Columna();
//		colCantidadOT.setPropiedad("cantidadOT");
//		columnas.add(colCantidadOT);
		
		
		columnas.removeAll(removeMe);
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterInventario filterInventario = JsonUtil.toReflectedTypes(FilterInventario.class, filter);
		return inventarioProductoDAO.listInventarioProductoByFilter(filterInventario);
	}

	public void setInventarioProductoDAO(InventarioProductoDAO inventarioProductoDAO){
		this.inventarioProductoDAO=inventarioProductoDAO;
	}
}
