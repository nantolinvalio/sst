package cl.abcdin.sst.dao;


public class NotaCreditoDAO extends BaseDAO{
	
	public Integer getDiasNotaCredito()throws Exception{
		return getSqlSessionTemplate().selectOne("notaCredito.getDiasNotaCredito");
	}
}
