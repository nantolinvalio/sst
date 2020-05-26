package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Sello;

public class SelloDAO extends BaseDAO{

	public Integer save(Sello sello) throws Exception {
		return getSqlSessionTemplate().insert("sello.save",sello);
	}
	
	public List<Sello> getSellosByIdGuia(Long id) throws Exception {
		return getSqlSessionTemplate().selectList("sello.getSellosByIdGuia",id);
	}
}
