package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Comuna;

public class ComunaDAO extends BaseDAO{

	public List<Comuna> listComunas() throws Exception {
		return getSqlSessionTemplate().selectList("comuna.listComunas");
	}
	
	public List<Comuna> listComunasST() throws Exception {
		return getSqlSessionTemplate().selectList("comuna.listComunasST");
	}
	
	public List<Comuna> listComunasConSTByRegion(Long idRegion) throws Exception {
		return getSqlSessionTemplate().selectList("comuna.listComunasConSTByRegion", idRegion);
	}

	public List<Comuna> listComunasByRegion(Long idRegion) throws Exception {
		return getSqlSessionTemplate().selectList("comuna.listComunasByRegion", idRegion);
	}
	
	public List<Comuna> listComunasConSucursalesByRegion(Long idRegion) throws Exception {
		return getSqlSessionTemplate().selectList("comuna.listComunasConSucursalesByRegion", idRegion);
	}
	
	public List<Comuna> listComunasByIdProvincia(Long idProvincia) throws Exception {
	    return getSqlSessionTemplate().selectList("comuna.listComunasByIdProvincia",idProvincia);
	}
}
