package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Indicador;

public class IndicadorDAO extends BaseDAO {

	public Indicador getIndicadorById(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("indicador.getIndicadorById", id);
	}

	public Indicador getIndicadorById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("indicador.getIndicadorById", id);
	}
	
	public int saveIndicador(Indicador indicador)throws Exception {
		return getSqlSessionTemplate().insert("indicador.saveIndicador", indicador);
	}
	
	public int updateIndicador(Indicador indicador)throws Exception {
		return getSqlSessionTemplate().update("indicador.updateIndicador", indicador);
	}
	
	public List<Indicador> getIndicadorByIdParametro(Integer idParametro) throws Exception {
		return getSqlSessionTemplate().selectList("indicador.getIndicadorByIdParametro",idParametro);
	}
}
