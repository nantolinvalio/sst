package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.Persona;
import cl.abcdin.sst.model.filters.FilterEjecutiva;

public class EjecutivaDAO extends BaseDAO {

	public Persona getEjecutivaByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ejecutiva.getEjecutivaByOT", idOT);
	}
	
	public Integer existeEjecutivaByFilter(FilterEjecutiva filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ejecutiva.existeEjecutivaByFilter",filter);
	}
	
	public Long getIdEjecutivaStaMarcasByFilter(FilterEjecutiva filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ejecutiva.getIdEjecutivaStaMarcasByFilter",filter);
	}
	
	public Persona getEjecutivaByFilter(FilterEjecutiva filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ejecutiva.getEjecutivaByFilter",filter);
	}
}
