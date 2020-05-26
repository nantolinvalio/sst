package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Inventario;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.GridControl;

public class InventarioDAO extends BaseDAO{
	public List<Inventario> list(FilterInventario filterInventario, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("inventario.list",filterInventario,gridControl.getRowBounds());
	}
	
	public List<Inventario> list(FilterInventario filterInventario) throws Exception {
		return getSqlSessionTemplate().selectList("inventario.list",filterInventario);
	}
	
	public Integer getTotalInventarios(FilterInventario filterInventario) throws Exception {
		return getSqlSessionTemplate().selectOne("inventario.getTotalInventarios",filterInventario);
	}
	
	public Integer save(Inventario inventario) throws Exception{
		return getSqlSessionTemplate().insert("inventario.save",inventario);
	}
	
	public Integer abrirCerrarInventario(Inventario inventario) throws Exception{
		return getSqlSessionTemplate().update("inventario.abrirCerrarInventario",inventario);
	}
	
	public Inventario getById(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("inventario.getById",id);
	}
	
	public List<Ubicacion> selectInventarioResumen(Integer idInventarioUbicacion, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("inventario.selectInventarioResumen",idInventarioUbicacion, gridControl.getRowBounds());
	}
	
	public Integer getTotalInventarioResumen(Integer idInventarioUbicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("inventario.getInventarioResumen", idInventarioUbicacion);
	}
	
	public Integer updateEstadisticas(Inventario inventario){
		return getSqlSessionTemplate().update("inventario.updateEstadisticas",inventario);
	}
	
	public Inventario getEstadisticasInventarioByIdInventario(Integer idInventario) throws Exception {
		return getSqlSessionTemplate().selectOne("inventario.getEstadisticasInventarioByIdInventario",idInventario);
	}
}
