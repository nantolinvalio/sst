package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.mobile.BitacoraInternaMobile;

public class BitacoraInternaMobileDAO extends BaseDAO{
	public Integer saveBitacoraInternaMobile(BitacoraInternaMobile bitacoraInternaMobile) throws Exception {
		return getSqlSessionTemplate().insert("bitacoraInternaMobile.saveBitacoraInternaMobile",bitacoraInternaMobile);
	}
	
	public Integer cerrarBitacoraInternaByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().update("bitacoraInternaMobile.cerrarBitacoraInternaByIdOT",idOT);
	}
	
	public BitacoraInternaMobile getBitacoraInternaMobileByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacoraInternaMobile.getBitacoraInternaMobileByIdOT",idOT);
	}
}
