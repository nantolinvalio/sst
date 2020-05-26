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
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.vo.GuiaPendiente;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class GuiaPendienteReport extends CommandReportSST{

	private GuiaDAO guiaDAO;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Collection getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, GuiaPendiente.class);
	}

	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	protected <T> Collection getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("imprimir")) {
				removeMe.add(columna);
			}
			if (columna.getPropiedad().equals("destino.id")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public String getClassName() {
						return String.class.getName();
					}
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						Ubicacion ubicacion = new Ubicacion();
						ubicacion.setId((Long) fields.get("destino.id"));
						ubicacion.setTipo((String) fields.get("destino.tipo"));
						ubicacion.setNombre((String) fields.get("destino.nombre"));
						ubicacion.setDireccion((String) fields.get("destino.direccion"));
						if (ubicacion.getTipo().equals("BR") || ubicacion.getTipo().equals("CD")) {
							return ubicacion.getId() + " " + ubicacion.getNombre();
						} else if (ubicacion.getTipo().equals("ST")){
							return ubicacion.getId() + " " + ubicacion.getNombre() + ", " + ubicacion.getDireccion();
						} else if (ubicacion.getTipo().equals("UA")){
							return ubicacion.getNombre();
						}
						return "";
					}
				});
			}
		}
		Columna colTipo = new Columna();
		colTipo.setPropiedad("destino.tipo");
		Columna colNombre = new Columna();
		colNombre.setPropiedad("destino.nombre");
		Columna colDireccion = new Columna();
		colDireccion.setPropiedad("destino.direccion");
		columnas.removeAll(removeMe);
		columnas.add(colTipo);
		columnas.add(colNombre);
		columnas.add(colDireccion);
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filterString = getData("filter", map);
		FilterGuiasPendientes filter = JsonUtil.toReflectedTypes(FilterGuiasPendientes.class, filterString);
		Ubicacion ubicacion = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		filter.setIdUbicacion(ubicacion.getId());
		return guiaDAO.listGuiasPendientesBySucursal(filter);
	}

	public void setGuiaDAO(GuiaDAO guiaDAO) {
		this.guiaDAO = guiaDAO;
	}

}
