package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class UbicacionDAO extends BaseDAO {
	
	public List<Ubicacion> listByIdUsuario(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listByIdUsuario", usuario);
	}	

	public List<Ubicacion> listDependenciasByFilter(FilterUbicacion filterUbicacion) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listDependenciasByFilter", filterUbicacion);
	}
	
	public List<Ubicacion> listByTipo(String tipo) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listByTipo", tipo);
	}
	
	public Ubicacion get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.get", id);
	}
	
	public Ubicacion getProveedorById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getProveedorById", id);
	}
	
	public Ubicacion getIdComunaByIdUbicacion(Long idc) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.getIdComunaByIdUbicacion", idc);
	}
	
	public List<ServicioTecnico> listDestinosUbicacion(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("destino.listDestinosUbicacion",filterUbicacion, gridControl.getRowBounds());
	}
	
	public Integer getTotalDestinosUbicacion(FilterUbicacion filterUbicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("destino.getTotalDestinosUbicacion", filterUbicacion);
	}
	
	public List<CheckForFlexigrid> listAllDestinosUbicacionCheck(FilterUbicacion filter) throws Exception {
		return getSqlSessionTemplate().selectList("destino.listAllDestinosUbicacionCheck", filter);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Ubicacion> listUbicacionesByIdAndTipo(Map tipoAndId) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listUbicacionesByIdAndTipo", tipoAndId);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Ubicacion> listSTBodegasProducto(Map origenProductoAndTipoOT) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listSTBodegasProducto",origenProductoAndTipoOT);
	}
	
	public Ubicacion getUbicacionByUbicacion(Ubicacion ubicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.getUbicacionByUbicacion", ubicacion);
	}

	public Integer save(Ubicacion ubicacion) throws Exception {
		return getSqlSessionTemplate().insert("ubicacion.save", ubicacion);
	}
	
	public Integer updateUbicacion(Ubicacion ubicacion) throws Exception{
		return getSqlSessionTemplate().update("ubicacion.updateUbicacion", ubicacion);
	}
	
	public Integer updateVigenciaUbicacion(Ubicacion ubicacion) throws Exception{
		return getSqlSessionTemplate().update("ubicacion.updateVigencia", ubicacion);
	}
	
	public Integer saveWithId(Ubicacion ubicacion) throws Exception {
		return getSqlSessionTemplate().insert("ubicacion.saveWithId", ubicacion);
	}
	
	public Ubicacion getUbicacionOT(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.getUbicacionOT", id);
	}	

	public Ubicacion getUbicacionOTAccesorios(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.getUbicacionOTAccesorios", id);
	}	
	
	public List<Ubicacion> listBodegas() throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listBodegas");
	}
	
	public List<Ubicacion> listBodegasNotMe(Ubicacion ubicacion) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listBodegasNotMe", ubicacion);
	}
	
	public Ubicacion getUltimaUbicacionInterna (Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("ubicacion.getUltimaUbicacionInterna", idOT);
	}
	
	public List<Ubicacion> listAllByIdUsuario(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listAllByIdUsuario", usuario);
	}
	
	public Integer deleteUbicacionesUsuarios(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().delete("ubicacion.deleteUbicacionesUsuarios", usuario);
	}
	
	public Integer saveUbicacionUsuario(Ubicacion ubicacion, Usuario usuario) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("id", null);
		param.put("idUbicacion", ubicacion.getId());
		param.put("idUsuario", usuario.getId());
		return getSqlSessionTemplate().insert("ubicacion.saveUbicacionUsuario", param);
	}
	
	public List<Ubicacion> listUbicacionByFilter(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listUbicacionByFilter", filterUbicacion, gridControl.getRowBounds());
	}
	
	public List<Ubicacion> listUbicacionByFilter(FilterUbicacion filterUbicacion) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listUbicacionByFilter", filterUbicacion);
	}
	
	public Integer getTotalUbicacionByFilter(FilterUbicacion filterUbicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.getTotalUbicacionByFilter", filterUbicacion);
	}
	
	public List<Ubicacion> listUbicacionByTipo(String tipo) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listUbicacionByTipo",tipo);
	}
	
	public List<Ubicacion> listDestinosForIdOrigen(Ubicacion ubicacion) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacion.listDestinosForIdOrigen", ubicacion);
	}
	
	public Ubicacion getDireccionTienda(Long idFacturar) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getDireccionTienda", idFacturar);
	}
}
