package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.mobile.DespachoMobile;
import cl.abcdin.sst.model.mobile.TrasladoMobile;

public class DespachoMobileDAO extends BaseDAO{
	public List<DespachoMobile> listDespachosFallasFabricacion()throws Exception {
		return getSqlSessionTemplate().selectList("despachoMobile.listDespachosFallasFabricacion");
	}
	
	public DespachoMobile getDespachosbyId(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoMobile.getDespachosbyId",idDespacho);
	}
	
	public Integer updateEstadoByDespachoMobile(DespachoMobile despachoMobile) throws Exception {
		return getSqlSessionTemplate().update("despachoMobile.updateEstadoByDespachoMobile",despachoMobile);
	}
	
	public List<TrasladoMobile> listTrasladosASalaProveedores() throws Exception {
		return getSqlSessionTemplate().selectList("despachoMobile.listTrasladosASalaProveedores");
	}
	
	public List<TrasladoMobile> listTrasladosAFallasFabricacion() throws Exception {
		return getSqlSessionTemplate().selectList("despachoMobile.listTrasladosAFallasFabricacion");
	}
}
