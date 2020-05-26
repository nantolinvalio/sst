package cl.abcdin.sst.reports.beans;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.RecepcionDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.vo.OrdenTrabajoRecepcion;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class RecepcionFallaFabricacionReport extends CommandReportSST{

	private RecepcionDAO recepcionDAO;	

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, OrdenTrabajoRecepcion.class);
	}

	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("ordenTrabajo.producto.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("ordenTrabajo.producto.id").toString() + " " + fields.get("ordenTrabajo.producto.descripcion").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("ordenTrabajo.sucursal.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("ordenTrabajo.sucursal.glosa").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("recepcionOrdenTrabajo.guia.estado.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("recepcionOrdenTrabajo.guia.estado.glosa").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("ordenTrabajo.producto.familia.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("ordenTrabajo.producto.familia.nombre").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("ordenTrabajo.producto.familia.linea.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("ordenTrabajo.producto.familia.linea.glosa").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("ordenTrabajo.producto.marca.codigo")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("ordenTrabajo.producto.marca.nombre").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("recepcionCamion.transportista.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("recepcionCamion.transportista.nombreCompleto").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("recepcionOrdenTrabajo.estado.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("recepcionOrdenTrabajo.estado.glosa").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("recepcionOrdenTrabajo.usuario.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("recepcionOrdenTrabajo.usuario.nombreCompleto").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			} else if (columna.getPropiedad().equals("recepcionOrdenTrabajo.guia.estadoActual.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("recepcionOrdenTrabajo.guia.estadoActual.glosa").toString();
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		Columna colProductoDescripcion = new Columna();
		colProductoDescripcion.setPropiedad("ordenTrabajo.producto.descripcion");
		columnas.add(colProductoDescripcion);	
		Columna colSucursalNombre = new Columna();
		colSucursalNombre.setPropiedad("ordenTrabajo.sucursal.glosa");
		columnas.add(colSucursalNombre);	
		Columna colEstadoGuiaNombre = new Columna();
		colEstadoGuiaNombre.setPropiedad("recepcionOrdenTrabajo.guia.estado.glosa");
		columnas.add(colEstadoGuiaNombre);
		Columna colFamiliaNombre = new Columna();
		colFamiliaNombre.setPropiedad("ordenTrabajo.producto.familia.nombre");
		columnas.add(colFamiliaNombre);
		Columna colLineaNombre = new Columna();
		colLineaNombre.setPropiedad("ordenTrabajo.producto.familia.linea.glosa");
		columnas.add(colLineaNombre);
		Columna colMarcaNombre = new Columna();
		colMarcaNombre.setPropiedad("ordenTrabajo.producto.marca.nombre");
		columnas.add(colMarcaNombre);
		Columna colTransportistaNombre = new Columna();
		colTransportistaNombre.setPropiedad("recepcionCamion.transportista.nombreCompleto");
		columnas.add(colTransportistaNombre);
		Columna colEstadoRecepcionNombre = new Columna();
		colEstadoRecepcionNombre.setPropiedad("recepcionOrdenTrabajo.estado.glosa");
		columnas.add(colEstadoRecepcionNombre);
		Columna colUsuarioNombre = new Columna();
		colUsuarioNombre.setPropiedad("recepcionOrdenTrabajo.usuario.nombreCompleto");
		columnas.add(colUsuarioNombre);
		Columna colEstadoActualGuia = new Columna();
		colEstadoActualGuia.setPropiedad("recepcionOrdenTrabajo.guia.estadoActual.glosa");
		columnas.add(colEstadoActualGuia);
		
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterRecepcion filterRecepcion = JsonUtil.toReflectedTypes(FilterRecepcion.class, filter);
		return recepcionDAO.listOTByRecepcionesFilter(filterRecepcion);
	}

	public void setRecepcionDAO(RecepcionDAO recepcionDAO) {
		this.recepcionDAO = recepcionDAO;
	}

}
