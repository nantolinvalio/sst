package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.mobile.DespachoMobile;

public class EnvioSucursalMobileDAO extends BaseDAO{
	public List<DespachoMobile> listDespachos() throws Exception {
		return getSqlSessionTemplate().selectList("envioSucursalMobile.listDespachos");
	}
	
	public Integer getIdUsuarioCargaByIdDespacho(Long idEnvio) throws Exception{
		return getSqlSessionTemplate().selectOne("envioSucursalMobile.getIdUsuarioCargaByIdDespacho",idEnvio);
	}
	
	public Integer updateUsuarioCarga (Long IdEnvio, Long idUsuarioCarga) throws Exception {
		HashMap<Object, Object> param = new HashMap<Object, Object>();
		param.put("idEnvio", IdEnvio);
		param.put("idUsuarioCarga", idUsuarioCarga);
		return getSqlSessionTemplate().update("envioSucursalMobile.updateUsuarioCarga",param);
	}
	
	public Integer cerrarEnvioSucursalByEnvioSucursa(Long idEnvio, Usuario usuario) throws Exception {
		HashMap<Object, Object> param = new HashMap<Object, Object>();
		param.put("id", idEnvio);
		param.put("usuarioTermino", usuario);
		return getSqlSessionTemplate().update("envioSucursal.cerrarEnvioSucursalByEnvioSucursal",param);
	}
}
