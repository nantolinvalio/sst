package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.json.JsonUtil;

import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.IndicadorDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.reports.common.CommandReportSST;
import cl.abcdin.sst.service.BodegaService;
import cl.abcdin.sst.utils.Constants;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoIndicadorFFGuiaReport extends CommandReportSST{
	private BodegaService bodegaService;
	private IndicadorDAO indicadorDAO;

	@Override
	protected Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filterColumn", map);
		FilterColumna filterColumna = JsonUtil.toReflectedTypes(FilterColumna.class, filter);
		filterColumna.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		return getAbstractColumns(filterColumna, Guia.class);
	}
	
	
	@SuppressWarnings("unchecked")
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = getColumnaDAO().listColumnasByFilter(filterColumna);
		List<Columna> removeMe = new ArrayList<Columna>();
		for (Columna columna : columnas) {
			if (columna.getPropiedad().equals("facturar")) {
				removeMe.add(columna);
			}	
		}

		columnas.removeAll(removeMe);	
		return parseAbstractColumns(columnas, clazz);
	}
	
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		String filter = getData("filter", map);
		FilterIndicador filterIndica = JsonUtil.toReflectedTypes(FilterIndicador.class, filter);
		filterIndica.setIndicador(indicadorDAO.getIndicadorById(filterIndica.getIndicador().getId()));
		List<cl.abcdin.sst.model.Guia> guias = bodegaService.listGuiaIndicadorForParametro(filterIndica);
		return guias;
	}
	public void setBodegaService(BodegaService bodegaService) {
		this.bodegaService = bodegaService;
	}
	public void setIndicadorDAO(IndicadorDAO indicadorDAO) {
		this.indicadorDAO = indicadorDAO;
	}
}
