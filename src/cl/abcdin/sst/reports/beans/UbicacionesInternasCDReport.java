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
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterUbicacionInterna;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.service.AdministracionService;
import cl.abcdin.sst.utils.Constants;

public class UbicacionesInternasCDReport extends CommandReportSST {
	
	private AdministracionService administracionService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, UbicacionInternaCD.class);
	}
	
	@SuppressWarnings({ "serial", "unchecked" })
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe= new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if(columna.getPropiedad().equals("imprimir")){
				removeMe.add(columna);
			}
			if (columna.getPropiedad().equals("sucursales")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						String sucursalSTR="";
						List<Sucursal> sucursales = (List<Sucursal>) fields.get("sucursales");
						
						Integer i=0;
						for(Sucursal sucursal:sucursales){
							if(i.equals(sucursales.size()-1)){
								sucursalSTR=sucursalSTR+sucursal.getGlosa().trim()+".";
							} else {
								sucursalSTR=sucursalSTR+sucursal.getGlosa().trim()+", ";
							}
						}
						return sucursalSTR;
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			
			if (columna.getPropiedad().equals("familias")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						String familiasSTR="";
						List<Familia> familias = (List<Familia>) fields.get("familias");
						
						Integer i=0;
						for(Familia familia:familias){
							if(i.equals(familias.size()-1)){
								familiasSTR=familiasSTR+familia.getNombre().trim()+".";
							} else {
								familiasSTR=familiasSTR+familia.getNombre().trim()+", ";
							}
						}
						return familiasSTR;
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			
			if (columna.getPropiedad().equals("lineas")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						String lineasSTR="";
						List<Linea> lineas = (List<Linea>) fields.get("lineas");
						
						Integer i=0;
						for(Linea linea:lineas){
							if(i.equals(lineas.size()-1)){
								lineasSTR=lineasSTR+linea.getGlosa().trim()+".";
							} else {
								lineasSTR=lineasSTR+linea.getGlosa().trim()+", ";
							}
						}
						return lineasSTR;
					}

					@Override
					public String getClassName() {
						return String.class.toString();
					}

				});
			}
			
			if (columna.getPropiedad().equals("vigente")) {
				columna.setCustomExpression(new CustomExpression() {
					@SuppressWarnings("rawtypes")
					@Override
					public Object evaluate(Map fields, Map variables, Map parameters) {
						return (Boolean) fields.get("vigente") ? "ACTIVA" : "INACTIVA";
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

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterUbicacionInterna filterUbicacionInterna = JsonUtil.toReflectedTypes(FilterUbicacionInterna.class, filter);
		return administracionService.listUbicacionInternaCDByFilter(filterUbicacionInterna);
	}

	public void setAdministracionService(AdministracionService administracionService){
		this.administracionService= administracionService;
	}

}
