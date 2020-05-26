package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.IndicadorDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.service.BodegaService;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoIndicadorFFReport extends CommandReportSST{
	private BodegaService bodegaService;
	private IndicadorDAO indicadorDAO;
	

	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterIndicador filterIndica = JsonUtil.toReflectedTypes(FilterIndicador.class, filter);
		filterIndica.setIndicador(indicadorDAO.getIndicadorById(filterIndica.getIndicador().getId()));
		List<OrdenTrabajo> ordenesTrabajo = bodegaService.listOTIndicadorForParametro(filterIndica);
		return ordenesTrabajo;
	}

	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, OrdenTrabajo.class);
	}
	
	
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
						String semaforo = "";
						String aux = fields.get("semaforoServicioTecnico").toString();
						switch (Integer.parseInt(aux)){
						case 1:
							semaforo= "1- Verde";
							break;
						case 2:
							semaforo= "2- Verde Claro";
							break;	
						case 3:
							semaforo= "3- Amarillo";
							break;
						case 4:
							semaforo= "4- Rojo";
							break;
						default: 
							semaforo = "0";
							break;
					}
						return semaforo;
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
						String semaforo = "";
						String aux = fields.get("semaforoSucursalInicio").toString();
						switch (Integer.parseInt(aux)){
						case 1:
							semaforo= "1- Verde";
							break;
						case 2:
							semaforo= "2- Verde Claro";
							break;	
						case 3:
							semaforo= "3- Amarillo";
							break;
						case 4:
							semaforo= "4- Rojo";
							break;
						default: 
							semaforo = "0";
							break;
					}
						return semaforo;
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
						String semaforo = "";
						String aux = fields.get("semaforoSucursalfin").toString();
						switch (Integer.parseInt(aux)){
						case 1:
							semaforo= "1- Verde";
							break;
						case 2:
							semaforo= "2- Verde Claro";
							break;	
						case 3:
							semaforo= "3- Amarillo";
							break;
						case 4:
							semaforo= "4- Rojo";
							break;
						default: 
							semaforo = "0";
							break;
					}
						return semaforo;
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
		columnas.removeAll(removeMe);	
		return parseAbstractColumns(columnas, clazz);
	}
	
	public void setBodegaService(BodegaService bodegaService) {
		this.bodegaService = bodegaService;
	}
	public void setIndicadorDAO(IndicadorDAO indicadorDAO) {
		this.indicadorDAO = indicadorDAO;
	}
}
