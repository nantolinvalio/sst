package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.Map;

import cl.abcdin.sst.model.vo.OWCabecera;
import cl.abcdin.sst.model.vo.OWCall;
import cl.abcdin.sst.model.vo.OWDetalle;

public class OWDAO extends BaseDAO {
	

	public Boolean validaStock(String origen, String ubicacion, Integer producto, Integer cantidad) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(); 
	    params.put("centro_costo",origen);
	    params.put("ubicacion", ubicacion);
	    params.put("producto", producto);
	    params.put("cantidad", cantidad);
	    params.put("existe", null);
		
	    getSqlSessionTemplate().selectOne("oW.validaStock", params);
	    return (Boolean)params.get("existe");
		
	}
	
	public OWCall createXN(OWCall owCall) throws Exception {
		getSqlSessionTemplate().selectOne("oW.createXN", owCall);
		return owCall;
	}
	
	//CC 05/19 Nuevos procesos de creacion de XN
	
	public OWCall createXNCC(OWCall owCall) throws Exception {
		getSqlSessionTemplate().selectOne("oW.createXN_CC", owCall);
		return owCall;
	}

	public OWCall createTSTO(OWCall owCall) throws Exception {
		getSqlSessionTemplate().selectOne("oW.createTSTO", owCall);
		return owCall;
	}

	public OWCall receiveTO(OWCall owCall) throws Exception {
		getSqlSessionTemplate().selectOne("oW.receiveTO", owCall);
		return owCall;
	}
	
	public OWCall receiveMultiTO(OWCall owCall) throws Exception {
		getSqlSessionTemplate().selectOne("oW.receiveMultiTO", owCall);
		return owCall;
	}
	
	public OWCall moveIT(OWCall owCall) throws Exception {
		return getSqlSessionTemplate().selectOne("oW.moveIT", owCall);
	}
	
	public OWCall moveSC(OWCall owCall) throws Exception {
		return getSqlSessionTemplate().selectOne("oW.moveSC", owCall);
	}
	
	public OWCall moveDM(OWCall owCall) throws Exception {
		return getSqlSessionTemplate().selectOne("oW.moveDM", owCall);
	}
	
	public Integer saveCabecera (OWCabecera owCabecera) throws Exception {
		return getSqlSessionTemplate().insert("oW.saveCabecera", owCabecera);
	}
	
	public Integer saveDetalle (OWDetalle owDetalle) throws Exception {
		return getSqlSessionTemplate().insert("oW.saveDetalle", owDetalle);
	}
}
