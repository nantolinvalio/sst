package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.InventarioProducto;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.filters.GridControl;

public class InventarioProductoDAO extends BaseDAO{
	public Integer save(InventarioProducto inventarioProducto) throws Exception {
		return getSqlSessionTemplate().insert("inventarioProducto.save",inventarioProducto);
	}
	
	public List<InventarioProducto> listInventarioProductoByFilter(FilterInventario filterInventario, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("inventarioProducto.listInventarioProductoByFilter",filterInventario,gridControl.getRowBounds());
	}
	
	public List<InventarioProducto> listInventarioProductoByFilter(FilterInventario filterInventario) throws Exception{
		return getSqlSessionTemplate().selectList("inventarioProducto.listInventarioProductoByFilter",filterInventario);
	}
	
	public Integer getTotalInventarioProductoByFilter(FilterInventario filterInventario) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioProducto.getTotalInventarioProductoByFilter",filterInventario);
	}
}
