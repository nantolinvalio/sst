package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.vo.STecnicoEjecutiva;

public class ServicioTecnicoEjecutivaDAO extends BaseDAO{

	public Integer save(STecnicoEjecutiva stEjecutiva) throws Exception {
		return getSqlSessionTemplate().insert("servicioTecnicoEjecutiva.save",stEjecutiva);
	}
	
	public Integer update(STecnicoEjecutiva stEjecutiva) throws Exception {
		return getSqlSessionTemplate().update("servicioTecnicoEjecutiva.update",stEjecutiva);
	}
	
	public STecnicoEjecutiva get(STecnicoEjecutiva stEjecutiva) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnicoEjecutiva.get",stEjecutiva);
	}
	public Integer getExiste(STecnicoEjecutiva stEjecutiva) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnicoEjecutiva.getExiste",stEjecutiva);
	}
}
