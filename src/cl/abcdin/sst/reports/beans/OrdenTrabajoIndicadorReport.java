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
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class OrdenTrabajoIndicadorReport extends CommandReportSST{
	private OrdenTrabajoDAO ordenTrabajoDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterIndicador filterIndicador = JsonUtil.toReflectedTypes(FilterIndicador.class, filter);
		Ubicacion sucursal = (Ubicacion)request.getSession().getAttribute(Constants.SESSION_UBICACION);
		List<OrdenTrabajo> ordenesTrabajo = new ArrayList<OrdenTrabajo>();
		
		FilterOT filterOT = new FilterOT();
		filterOT.setOrderBy(filterIndicador.getOrderBy());
		filterOT.setSortOrder(filterIndicador.getSortOrder());
		filterOT.setSucursal(sucursal.getId());
		
		if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_ABIERTAS_SUCURSAL.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTAbiertasSucursal(filterOT);
		} else if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_PENDIENTES_ACCESORIOS.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTPendienteAccesorios(filterOT);
		} else if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_PENDIENTES_GUIAS.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTPendienteGuia(filterOT);
		} else if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_PENDIENTES_ENTREGA_CLIENTE.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTPendienteEntregaCliente(filterOT);
		} else if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_STL_SIN_CONTRATO.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTEnviadasSTLSinContrato(filterOT);
		} else if (filterIndicador.getIdIndicador().equals(Constants.INDICADOR_OT_AUTORIZACION_CAMBIO.longValue())) {
			ordenesTrabajo = ordenTrabajoDAO.listOTAutorizadaCambio(filterOT);
		} else {
			throw new SSTException("Indicador inexistente para las sucursales");
		} 
		return ordenesTrabajo;
	}

	@SuppressWarnings("rawtypes")
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
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("semaforoServicioTecnico")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
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
					@SuppressWarnings("rawtypes")
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
					@SuppressWarnings("rawtypes")
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
		}
		return parseAbstractColumns(columnas, clazz);
	}
	
	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}

}
