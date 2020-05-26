package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.GuiaRemateDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class GuiaRemateReport extends CommandReportSST{
	
	private GuiaRemateDAO guiaRemateDAO;

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, GuiaPendienteAgrupada.class);
	}

	
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("checkMarca")) {
				removeMe.add(columna);
			} else if (columna.getPropiedad().equals("imprimir")) {
				removeMe.add(columna);
			} else if (columna.getPropiedad().equals("destino.tipo")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("destino.tipo").toString() + " " + fields.get("destino.nombre").toString() + " " + fields.get("destino.direccion").toString();
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}
		Columna colDireccion = new Columna();
		colDireccion.setPropiedad("destino.direccion");
		Columna colNombre = new Columna();
		colNombre.setPropiedad("destino.nombre");
		
		columnas.removeAll(removeMe);
		columnas.add(colDireccion);
		columnas.add(colNombre);
		return parseAbstractColumns(columnas, clazz);
	}	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String gridControlString = getData("gridControl", map);
		GridControl gridControl = JsonUtil.toReflectedTypes(GridControl.class, gridControlString);
		return guiaRemateDAO.listAllGuiasRemate(gridControl);
	}

	public void setGuiaRemateDAO(GuiaRemateDAO guiaRemateDAO) {
		this.guiaRemateDAO = guiaRemateDAO;
	}

}
