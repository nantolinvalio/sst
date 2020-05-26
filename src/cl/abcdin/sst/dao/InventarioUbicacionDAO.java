package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.InventarioUbicacion;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.GridControl;

public class InventarioUbicacionDAO extends BaseDAO{
	public Integer save(InventarioUbicacion inventarioUbicacion) throws Exception{
		return getSqlSessionTemplate().insert("inventarioUbicacion.save",inventarioUbicacion);
	}
	
	public Integer haveRedundantUbicacionesByIdInventario(Integer idInventario) throws Exception {
		return getSqlSessionTemplate().insert("inventarioUbicacion.haveRedundantUbicacionesByIdInventario",idInventario);
	}
	
	public List<InventarioUbicacion> listEstadisticas(Integer idInventario) throws Exception{
		return getSqlSessionTemplate().selectList("inventarioUbicacion.listEstadisticas",idInventario);
	}
	
	public Integer cerrarInventarioUbicacion(InventarioUbicacion inventarioUbicacion) throws Exception {
		return getSqlSessionTemplate().update("inventarioUbicacion.cerrarInventarioUbicacion",inventarioUbicacion);
	}
	
	public InventarioUbicacion getInventarioUbicacionById(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.getInventarioUbicacionById",id);
	}
	
	public InventarioUbicacion getEstadisitcasByIdInventarioUbicacion(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.getEstadisitcasByIdInventarioUbicacion",id);
	}
	
	public List<InventarioUbicacion> listInventarioUbicacionesByFilterInventario(FilterInventario filterInventario, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("inventarioUbicacion.listInventarioUbicacionesByFilterInventario",filterInventario, gridControl.getRowBounds());
	}
	
	public Integer getTotalInventarioUbicacionesByFilterInventario(FilterInventario filterInventario) throws Exception {
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.getTotalInventarioUbicacionesByFilterInventario",filterInventario);
	}
	
	public InventarioUbicacion getEstadisticasByIdInventarioUbicacion(Integer idInventarioUbicacion) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.getEstadisticasByIdInventarioUbicacion",idInventarioUbicacion);
	}
	
	public Integer contarInventarioUbicacionOpenByIdInventario(Integer idInventario) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.contarInventarioUbicacionOpenByIdInventario",idInventario);
	}
	
	public List<InventarioUbicacion> listUbicacionesInternasByFilter(FilterInventario filterInventario, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("inventarioUbicacion.listUbicacionesInternasByFilter",filterInventario,gridControl.getRowBounds());
	}
	
	public List<InventarioUbicacion> listUbicacionesInternasByFilter(FilterInventario filterInventario) throws Exception{
		return getSqlSessionTemplate().selectList("inventarioUbicacion.listUbicacionesInternasByFilter",filterInventario);
	}
	
	public Integer getTotalUbicacionesInternasByFilter(FilterInventario filterInventario) throws Exception {
		return getSqlSessionTemplate().selectOne("inventarioUbicacion.getTotalUbicacionesInternasByFilter",filterInventario);
	} 
}
