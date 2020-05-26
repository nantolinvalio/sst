package cl.abcdin.sst.dao;

import java.util.HashMap;

import cl.abcdin.sst.model.EnvioSucursalDetalle;

public class EnvioSucursalDetalleDAO extends BaseDAO{
	public Integer save(EnvioSucursalDetalle envioSucursalDetalle) throws Exception {
		return getSqlSessionTemplate().insert("envioSucursalDetalle.save",envioSucursalDetalle);
	}
	
	public Integer cerrarEnvioSucursalDetalleByIdEnvio(Integer idEnvio) throws Exception {
		return getSqlSessionTemplate().update("envioSucursalDetalle.cerrarEnvioSucursalDetalleByIdEnvio",idEnvio);
	}
	
	public Integer cerrarEnvioSucursalDetalleByIdEnvio(Long idEnvio) throws Exception {
		return getSqlSessionTemplate().update("envioSucursalDetalle.cerrarEnvioSucursalDetalleByIdEnvio",idEnvio);
	}
	
	public Integer isOTInEnvio(Long idEnvio, Long idOT) throws Exception {
		HashMap<Object, Object> param = new HashMap<Object, Object>();
		param.put("idEnvio", idEnvio);
		param.put("idOT", idOT);
		return getSqlSessionTemplate().selectOne("envioSucursalDetalle.isOTInEnvio",param);
	}
	
	public Integer getTotalOTOnEnvio(Long idEnvio) throws Exception {
		return getSqlSessionTemplate().selectOne("envioSucursalDetalle.getTotalOTOnEnvio",idEnvio);
	}
	
	public Integer getTotalOTLeidas(Long idEnvio) throws Exception{
		return getSqlSessionTemplate().selectOne("envioSucursalDetalle.getTotalOTLeidas",idEnvio);
	}
}
