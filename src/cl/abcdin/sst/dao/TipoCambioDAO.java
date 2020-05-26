package cl.abcdin.sst.dao;
import java.util.List;

import cl.abcdin.sst.model.TipoCambio;

public class TipoCambioDAO extends BaseDAO{
	public List<TipoCambio> listAllTipoCambio() throws Exception {
		return getSqlSessionTemplate().selectList("tipoCambio.listAllTipoCambio");
	}
	
	public TipoCambio getTipoCambioByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("tipoCambio.getTipoCambioByCodigo",codigo);
	}
}
