package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Procedimiento;
import cl.abcdin.sst.model.filters.FilterProcedimiento;
import cl.abcdin.sst.model.filters.GridControl;

public class ProcedimientoDAO extends BaseDAO{
	
	public List<Procedimiento> listProcedimientosProductosByFilter(FilterProcedimiento filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("procedimiento.listProcedimientosProductosByFilter", filter, gridControl.getRowBounds());
	}
	
	public List<Procedimiento> listProcedimientosProductosByFilter(FilterProcedimiento filter) throws Exception {
		return getSqlSessionTemplate().selectList("procedimiento.listProcedimientosProductosByFilter", filter);
	}
	
	public Integer getTotalProcedimientosProductosByFilter(FilterProcedimiento filter) throws Exception {
		return getSqlSessionTemplate().selectOne("procedimiento.getTotalProcedimientosProductosByFilter", filter);
	}
	
	public Integer saveProcedimiento(Procedimiento procedimiento) throws Exception {
		return getSqlSessionTemplate().insert("procedimiento.saveProcedimientos",procedimiento);
	}
	
	public Integer updateEstadoProcedimiento(Procedimiento procedimiento) throws Exception{
		return getSqlSessionTemplate().update("procedimiento.updateEstadoProcedimiento", procedimiento);
	}
	
	public Integer updateProcedimientoProducto(Procedimiento procedimiento) throws Exception{
		return getSqlSessionTemplate().update("procedimiento.updateProcedimientoProducto", procedimiento);
	}
	
	public Procedimiento get(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("procedimiento.get", id);
	}
	
}
