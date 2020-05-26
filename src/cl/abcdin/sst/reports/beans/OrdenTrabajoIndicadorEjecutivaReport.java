package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.service.EjecutivaService;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.Utils;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoIndicadorEjecutivaReport extends CommandReportSST {
	private EjecutivaService ejecutivaService;
	
	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("checkMarca")) {
				removeMe.add(columna);
			}	
			if (columna.getPropiedad().equals("semaforoServicioTecnico")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {		
						return Utils.getIndicadorColor(fields.get("semaforoServicioTecnico").toString());
					}
					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			if (columna.getPropiedad().equals("semaforoSucursalInicio")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {	
						return Utils.getIndicadorColor(fields.get("semaforoSucursalInicio").toString());
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			if (columna.getPropiedad().equals("semaforoSucursalfin")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {	
						return Utils.getIndicadorColor(fields.get("semaforoSucursalInicio").toString());					
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			if (columna.getPropiedad().equals("sucursal.glosa")) {
				columna.setCustomExpression(new CustomExpression() {
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return fields.get("sucursal.id").toString() + " " + fields.get("sucursal.glosa").toString();
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
		}

		Columna colSucursalNombre = new Columna();
		colSucursalNombre.setPropiedad("sucursal.id");
		columnas.add(colSucursalNombre);
		columnas.removeAll(removeMe);	
		return parseAbstractColumns(columnas, clazz);
	}
	
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		if(filterColumna.getIdRol().equals(Constants.ROL_EJECUTIVA_SUPERVISORA))
			filterColumna.setIdRol(Constants.ROL_EJECUTIVA_MARCA);
		return getAbstractColumns(filterColumna, OrdenTrabajo.class);
	}

	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterIndicador filterIndicador = JsonUtil.toReflectedTypes(FilterIndicador.class, filter);
		String filterColumnaString = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filterColumnaString);
		List<OrdenTrabajo> ordenTrabajos = null;
		if (filterColumna.getIdRol().equals(Constants.ROL_EJECUTIVA_MARCA)) {
			ordenTrabajos = ejecutivaService.listOTIndicadoresEjecutivaMarca(filterIndicador);			
		} else if (filterColumna.getIdRol().equals(Constants.ROL_EJECUTIVA_GESTION)) {
			ordenTrabajos = ejecutivaService.listOTIndicadoresEjecutivaGestion(filterIndicador);
		} else if (filterColumna.getIdRol().equals(Constants.ROL_EJECUTIVA_SUPERVISORA)) {
			ordenTrabajos = ejecutivaService.listOTIndicadoresNivelServicio(filterIndicador);
		}
		return ordenTrabajos;
	}

	public EjecutivaService getEjecutivaService() {
		return ejecutivaService;
	}

	public void setEjecutivaService(EjecutivaService ejecutivaService) {
		this.ejecutivaService = ejecutivaService;
	}

}
