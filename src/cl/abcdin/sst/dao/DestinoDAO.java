package cl.abcdin.sst.dao;

import java.util.HashMap;

import cl.abcdin.sst.model.Destino;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.filters.FilterDestino;

public class DestinoDAO extends BaseDAO {

	public Destino getByFilter(FilterDestino filter) throws Exception {
		return getSqlSessionTemplate().selectOne("destino.getByFilter", filter);
	}
	
	public Integer updateVigencia(Destino destino) throws Exception {
		return getSqlSessionTemplate().update("destino.updateVigencia", destino);
	}
	
	public Integer save(Destino destino) throws Exception {
		return getSqlSessionTemplate().insert("destino.save", destino);
	}
	
	public Integer updateVigenciaUbicacion(Destino destino) throws Exception{
		return getSqlSessionTemplate().update("destino.updateVigencia", destino);
	}
	
	public Destino get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacion.get", id);
	}
	
	public Integer isDestinoDeUbicacion(Ubicacion origen, Ubicacion destino) throws Exception{
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idOrigen", origen.getId());
		param.put("idDestino", destino.getId());
		return getSqlSessionTemplate().selectOne("destino.isDestinoDeUbicacion", param);
	}
}
