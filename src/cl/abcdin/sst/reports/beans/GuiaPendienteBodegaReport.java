package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import cl.abcdin.sst.dao.GuiaDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class GuiaPendienteBodegaReport extends CommandReportSST{

	private GuiaDAO guiaDAO;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Collection getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, Guia.class);
	}

	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	protected <T> Collection getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("imprimir") || columna.getPropiedad().equals("seleccionado") || columna.getPropiedad().equals("imprimir_rapido")) {
				removeMe.add(columna);
			}
			if (columna.getPropiedad().equals("origen") || columna.getPropiedad().equals("origen.nombre")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public String getClassName() {
						return String.class.getName();
					}
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						Ubicacion ubicacion = new Ubicacion();
						ubicacion.setId((Long) fields.get("origen.id"));
						ubicacion.setTipo((String) fields.get("origen.glosa"));
						ubicacion.setNombre((String) fields.get("origen.nombre"));
						ubicacion.setDireccion((String) fields.get("origen.direccion"));
						if (ubicacion.getTipo().equals("ST")) {
							return ubicacion.getTipo() + " " + ubicacion.getNombre() + ", " + ubicacion.getDireccion();
						} else {
							return ubicacion.getTipo() + " " + ubicacion.getId() + " " + ubicacion.getNombre() + ", " + ubicacion.getDireccion();
						}
					}
				});
			}
		}
		Columna colTipo = new Columna();
		colTipo.setPropiedad("origen.glosa");
		Columna colNombre = new Columna();
		colNombre.setPropiedad("origen.nombre");
		Columna colDireccion = new Columna();
		colDireccion.setPropiedad("origen.direccion");
		Columna colOrigenId = new Columna();
		colOrigenId.setPropiedad("origen.id");
		columnas.removeAll(removeMe);
		columnas.add(colTipo);
		columnas.add(colNombre);
		columnas.add(colDireccion);
		columnas.add(colOrigenId);
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filterString = getData("filter", map);
		FilterGuia filter = JsonUtil.toReflectedTypes(FilterGuia.class, filterString);
		Ubicacion ubicacion = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		filter.setIdOrigen(ubicacion.getId());
		return guiaDAO.listGuiasBodegaByFilter(filter);
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

}
