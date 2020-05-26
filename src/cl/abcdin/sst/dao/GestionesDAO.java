package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Gestion;

public class GestionesDAO extends BaseDAO {

	public List<Gestion> listGestionesByIdOT(Long idOT)throws Exception{
		return getSqlSessionTemplate().selectList("gestiones.listGestionesByIdOT", idOT);
	}
	
	public Gestion get(Long id)throws Exception{
		return getSqlSessionTemplate().selectOne("gestiones.get", id);
	}
	
	public void saveGestion(Gestion gestion)throws Exception{
		getSqlSessionTemplate().selectList("gestiones.saveGestion", gestion);
	}
}
