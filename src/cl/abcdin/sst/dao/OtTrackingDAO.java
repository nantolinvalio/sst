package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.OtTracking;

public class OtTrackingDAO extends BaseDAO {

	public Integer save(OtTracking otTracking) throws Exception {
		return getSqlSessionTemplate().insert("otTracking.save", otTracking);
	}

}
