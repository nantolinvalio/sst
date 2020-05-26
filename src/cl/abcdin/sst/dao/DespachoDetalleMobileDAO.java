package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.mobile.DespachoDetalleMobile;
import cl.abcdin.sst.model.mobile.MoveResponse;
import cl.abcdin.sst.model.mobile.MoveSPResponse;

public class DespachoDetalleMobileDAO extends BaseDAO {
	public Integer updateEstado(DespachoDetalleMobile despachoDetalleMobile) throws Exception {
		return getSqlSessionTemplate().update("despachoDetalleMobile.updateEstado",despachoDetalleMobile);
	}
	
	public MoveSPResponse getOTRecibidasYTotalInSP(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoDetalleMobile.getOTRecibidasYTotalInSP",idDespacho);
	}
	
	public MoveResponse getOTRecibidasYTotal(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoDetalleMobile.getOTRecibidasYTotal",idDespacho);
	}
	
	public DespachoDetalleMobile getDespachoDetalleMobileByDespachoDetalleMobile(DespachoDetalleMobile despachoDetalleMobile) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoDetalleMobile.getDespachoDetalleMobileByDespachoDetalleMobile",despachoDetalleMobile);
	}
	
	public List<DespachoDetalleMobile> listDespachoDetalleMobileByIdDespacho(Long idDespachoDetalle) throws Exception {
		return getSqlSessionTemplate().selectList("despachoDetalleMobile.listDespachoDetalleMobileByIdDespacho",idDespachoDetalle);
	}
}
