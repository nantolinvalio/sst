package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Estado;

public class EstadoDAO extends BaseDAO{

	public List<Estado> listEstadosOT() throws Exception {
		return getSqlSessionTemplate().selectList("estado.listEstadosByGrupo", "OT");
	}
	
	public Estado getNextEstadoByIdEstado(Integer idEstado) throws Exception {
		return getSqlSessionTemplate().selectOne("estado.getNextEstadoByIdEstado", idEstado);
	}
	
	public Estado getEstadoById(Integer idEstado) throws Exception {
		return getSqlSessionTemplate().selectOne("estado.getEstadoById", idEstado);
	}
	
	public Estado getEstadoById(Long idEstado) throws Exception {
		return getSqlSessionTemplate().selectOne("estado.getEstadoById", idEstado);
	}
	
	public List<Estado> listEstadoOTFallaFabricacion() throws Exception {
		return getSqlSessionTemplate().selectList("estado.listEstadoOTFallaFabricacion");
	}
	
	public List<Estado> listEstadoRecepcionGuia() throws Exception {
		return getSqlSessionTemplate().selectList("estado.listEstadoRecepcionGuia");
	}
	
	public List<Estado> listEstadoDespacho() throws Exception {
		return getSqlSessionTemplate().selectList("estado.listEstadoDespacho");
	}
}
