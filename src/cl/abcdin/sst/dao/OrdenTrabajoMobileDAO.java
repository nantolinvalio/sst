package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.filters.FilterOTMobile;
import cl.abcdin.sst.model.mobile.OrdenTrabajoMobile;

public class OrdenTrabajoMobileDAO extends BaseDAO {
	
	public OrdenTrabajoMobile getOTByCodigo (String codigoBarras) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoMobile.getOTByCodigo",codigoBarras);
	}
	
	public OrdenTrabajoMobile getOTbyFilter(FilterOTMobile filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoMobile.getOTbyFilter",filter);
	}
	
	public Integer isOtOnDespachoByFilter(FilterOTMobile filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoMobile.isOtOnDespachoByFilter",filter);
	}
	
	public List<OrdenTrabajoMobile> listOtByDespachoMobile(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoMobile.listOtByDespachoMobile",idDespacho);
	}
	
	public Integer getOtForSaveTransportista(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoMobile.getOtForSaveTransportista",idDespacho);
	}
	
	public List<OrdenTrabajoMobile> listOTOnRDEMEByIdEnvioSucursal(Long idEnvio) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoMobile.listOTOnRDEMEByIdEnvioSucursal",idEnvio);
	}
	
	public OrdenTrabajoMobile getOTByCodigoHaciaSP (FilterOTMobile filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoMobile.getOTByCodigoHaciaSP",filter);
	}
}
