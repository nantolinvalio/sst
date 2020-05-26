package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.DiasTramos;

public class DiasTramosDAO extends BaseDAO{
	
	public DiasTramos getDiasTramos() throws Exception {
		return getSqlSessionTemplate().selectOne("diasTramos.getDiasTramos");
	}
}
