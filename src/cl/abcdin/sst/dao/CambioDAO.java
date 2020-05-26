package cl.abcdin.sst.dao;

import java.util.Date;

import cl.abcdin.sst.model.Cambio;

public class CambioDAO extends BaseDAO{

	public Cambio getCambioByOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("cambio.getCambioByOT", idOT);
	}
	
	public Long existeNumeroCambio(Long numeroCambio) throws Exception {
		return getSqlSessionTemplate().selectOne("cambio.existeNumeroCambio", numeroCambio);
	}
	
	public Date getFechaAuorizacionByOT(Long idOT)throws Exception {
		return getSqlSessionTemplate().selectOne("cambio.getFechaAuorizacionByOT",idOT);
	}
	
	public Integer autorizarCambio(Cambio cambio) throws Exception {
		return getSqlSessionTemplate().update("cambio.autorizarCambio", cambio);
	}
}
