package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Prts;


public class PrtsDAO extends BaseDAO {

	public List<Prts> getTotalPrts() throws Exception {
		return getSqlSessionTemplate().selectList("prts.getTotalPrts");
	}
}
