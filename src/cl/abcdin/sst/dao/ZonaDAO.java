package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.TiendaZona;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.Zona;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.FilterZona;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.TreeView;

public class ZonaDAO extends BaseDAO {

	public Zona get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.get", id);
	}
	
	public Zona getByIdUbicacion(Long idUbicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getByIdUbicacion", idUbicacion);
	}
	
	public Zona getByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getByCodigo", codigo);
	}
	
	public List<Zona> list(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("zona.list", gridControl, gridControl.getRowBounds());
	}
	
	public List<Zona> listAll(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("zona.list", gridControl);
	}
	
	public List<Zona> listAll() throws Exception {
		return getSqlSessionTemplate().selectList("zona.listAll");
	}
	
	public List<TreeView> listAllAsTreeView() throws Exception {
		return getSqlSessionTemplate().selectList("zona.listAllAsTreeView");
	}
	
	public Integer getTotal() throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getTotal");
	}

	public Integer update(Zona zona) throws Exception {
		return getSqlSessionTemplate().update("zona.update", zona);
	}
	
	public Integer save(Zona zona) throws Exception {
		return getSqlSessionTemplate().insert("zona.save", zona);
	}
	
	public List<Ubicacion> listSucursalesNotInZonaByZona(Long id) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesNotInZonaByZona", id);
	}
	
	public List<TiendaZona> listSucursalesByZona(Long id) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesByZona", id);
	}
	
	public List<TreeView> listSucursalesByZonaAsTreeView(Long id) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesByZonaAsTreeView", id);
	}
	
	public List<TreeView> listSucursalesByFilterAsTreeView(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesByFilterAsTreeView", filter);
	}
	
	public List<TreeView> listSucursalesHistoricasByFilterAsTreeView(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesHistoricasByFilterAsTreeView", filter);
	}
	
	public List<TreeView> listZonasAsTreeViewByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listZonasAsTreeViewByFilter", filter);
	}
	
	public List<TreeView> listZonasHistoricasAsTreeViewByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listZonasHistoricasAsTreeViewByFilter", filter);
	}
	
	public Integer getCountSucursalesByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getCountSucursalesByFilter", filter);
	}
	
	public Integer getCountSucursalesHistoricasByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getCountSucursalesHistoricasByFilter", filter);
	}
	
	public List<TiendaZona> listSucursalesWithoutZona() throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesWithoutZona");
	}
	
	public List<TreeView> listSucursalesWithoutZonaAsTreeView() throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesWithoutZonaAsTreeView");
	}
	
	public List<Ubicacion> listSucursalesByFilter(FilterZona filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesByFilter", filter, gridControl.getRowBounds());
	}
	
	public List<Ubicacion> listSucursalesByFilter(FilterZona filter) throws Exception {
		return getSqlSessionTemplate().selectList("zona.listSucursalesByFilter", filter);
	}
	
	public Integer getTotalSucursalesByFilter(FilterZona filter) throws Exception {
		return getSqlSessionTemplate().selectOne("zona.getTotalSucursalesByFilter", filter);
	}
	
	public void deleteSucursalZonaByZona(Zona zona) throws Exception {
		getSqlSessionTemplate().delete("zona.deleteSucursalZonaByZona", zona);
	}
	
	public void deleteSucursalZonaByUbicacion(Ubicacion ubicacion) throws Exception {
		getSqlSessionTemplate().delete("zona.deleteSucursalZonaByUbicacion", ubicacion);
	}
	
	public void saveSucursalZona(Ubicacion sucursal, Zona zona, Usuario usuario) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idSucursal", sucursal.getId());
		param.put("idZona", zona.getId());
		param.put("idUsuario", usuario.getId());
		getSqlSessionTemplate().insert("zona.saveSucursalZona", param);
	}
}
