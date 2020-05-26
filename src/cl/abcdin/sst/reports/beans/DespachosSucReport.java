package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.EnvioSucursalDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.EnvioSucursal;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterEnvioSucursal;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.utils.Constants;

public class DespachosSucReport  extends CommandReportSST{

    private EnvioSucursalDAO envioSucursalDAO;
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, EnvioSucursal.class);
	}

	@SuppressWarnings("unchecked")
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("abrirCerrar")) {
				removeMe.add(columna);
			}
		}
		
		Columna colSucursalGlosa = new Columna();
		colSucursalGlosa.setPropiedad("sucursal.glosa");
		Columna colSucursalDireccion = new Columna();
		colSucursalDireccion.setPropiedad("sucursal.direccion");
		Columna colFechaInicioColumna = new Columna();
		colFechaInicioColumna.setPropiedad("fechaInicio");
		Columna colFechaTermino = new Columna();
		colFechaTermino.setPropiedad("fechaTermino");
		Columna colTotalLeidas = new Columna();
		colTotalLeidas.setPropiedad("totalLeidas");
		
		columnas.removeAll(removeMe);
		return parseAbstractColumns(columnas, clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterEnvioSucursal filterEnvioSucursal = JsonUtil.toReflectedTypes(FilterEnvioSucursal.class, filter);
		return envioSucursalDAO.listEnviosToSucursal(filterEnvioSucursal);
	}
	
	public void setEnvioSucursalDAO(EnvioSucursalDAO envioSucursalDAO) {
		this.envioSucursalDAO = envioSucursalDAO;
	}
}
