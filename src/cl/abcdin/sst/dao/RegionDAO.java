package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Region;

public class RegionDAO extends BaseDAO{
	
	public List<Region> listRegiones() throws Exception {
		return getSqlSessionTemplate().selectList("region.listRegiones");
	}

}
