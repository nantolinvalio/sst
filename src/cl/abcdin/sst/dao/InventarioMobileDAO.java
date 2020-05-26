package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterInventario;
import cl.abcdin.sst.model.mobile.InventarioMobile;

public class InventarioMobileDAO extends BaseDAO{
	public List<InventarioMobile> list() throws Exception {
		return getSqlSessionTemplate().selectList("inventarioMobile.list");
	}
	
	public Integer existOTOnIventario(FilterInventario fiInventario) throws Exception {
		return getSqlSessionTemplate().selectOne("inventarioMobile.existOTOnIventario",fiInventario);
	}
	
	public Integer updateInventariado(Long idOT, Long idInventarioUbicacion,Usuario usuario) throws Exception {
		HashMap<Object, Object> param = new HashMap<Object, Object>();
		param.put("idInventarioUbicacion",idInventarioUbicacion);
		param.put("idOT", idOT);
		param.put("usuario", usuario);
		return getSqlSessionTemplate().update("inventarioMobile.updateInventariado",param);
	}
	
	public Long getIdInventarioByIdInventarioUbicacion(Long idInventarioUbicacion) throws Exception{
		return getSqlSessionTemplate().selectOne("inventarioMobile.getIdInventarioByIdInventarioUbicacion",idInventarioUbicacion);
	}
}
