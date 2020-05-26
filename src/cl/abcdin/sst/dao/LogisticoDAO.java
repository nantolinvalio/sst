package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Logistico;

public class LogisticoDAO extends BaseDAO{
	
	public Logistico getLogisticoById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("logistico.getLogisticoById",id);
	}
	
	public List<Logistico> listLogisticosRecepcionesCamion() throws Exception {
		return getSqlSessionTemplate().selectList("logistico.listLogisticosRecepcionesCamion");
	}
}
