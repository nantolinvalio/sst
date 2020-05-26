package cl.abcdin.sst.reports.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cl.abcdin.sst.dao.ParametroDAO;
import cl.abcdin.sst.model.vo.CondicionRecepcion;
import cl.abcdin.sst.reports.common.CommandReport;

@SuppressWarnings("rawtypes")
public class CondicionRecepcionReport extends CommandReport{

	private ParametroDAO parametroDAO; 
	
	@Override
	protected Collection getData(Map map, HttpServletRequest request) throws Exception {
		List<CondicionRecepcion> condiciones = new ArrayList<CondicionRecepcion>();
		condiciones.add(parametroDAO.getCondicionRecepcion());
		return condiciones;
	}

	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}
}
