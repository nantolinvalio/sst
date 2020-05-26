package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.DespachoInternoCamion;

public class DespachoInternoCamionDAO extends BaseDAO{

	public Integer save(DespachoInternoCamion despachoInternoCamion)throws Exception{
		return getSqlSessionTemplate().insert("despachoInternoCamion.save", despachoInternoCamion);
	}
	
	public DespachoInternoCamion get(DespachoInterno despachoInterno)throws Exception{
		return getSqlSessionTemplate().selectOne("despachoInternoCamion.get", despachoInterno);
	}
	
	public DespachoInternoCamion getDespachoInternoCamionUltimo(DespachoInterno despachoInterno)throws Exception{
		return getSqlSessionTemplate().selectOne("despachoInternoCamion.getDespachoInternoCamionUltimo", despachoInterno);
	}
}
