package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.UbicacionInternaDetalle;

public class UbicacionInternaDetalleDAO extends BaseDAO{
	public Integer save(UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception {
		return getSqlSessionTemplate().insert("ubicacionInternaDetalle.save",ubicacionInternaDetalle);
	}
	
	public Integer eliminarFromUbicacionInternaDetalle(UbicacionInternaDetalle ubicacionInternaDetalle) throws Exception{
		return getSqlSessionTemplate().delete("ubicacionInternaDetalle.eliminarFromUbicacionInternaDetalle",ubicacionInternaDetalle);
	}
	
	public Integer hasSucursalesByIdUbicacionInterna(Long idUbicacionInterna) throws Exception{
		return getSqlSessionTemplate().selectOne("ubicacionInternaDetalle.hasSucursalesByIdUbicacionInterna",idUbicacionInterna);
	}
}
