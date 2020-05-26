package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.mobile.TransportistaMobile;

public class TransportistaMobileDAO extends BaseDAO{
	public List<TransportistaMobile> listTransportistas() throws Exception {
		return getSqlSessionTemplate().selectList("transportistaMobile.listTransportistas");
	}
}