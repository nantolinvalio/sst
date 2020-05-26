package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.ReglaEntidad;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.FilterReglaHistorica;
import cl.abcdin.sst.model.filters.GridControl;

public class ReglaComercialDAO extends BaseDAO {
	public Integer save(ReglaComercial reglaComercial) throws Exception {
		return getSqlSessionTemplate().insert("reglaComercial.save", reglaComercial);
	}
	
	public Integer saveHistorial(ReglaComercial reglaComercial) throws Exception {
		return getSqlSessionTemplate().insert("reglaComercial.saveHistorial", reglaComercial);
	}

	public Integer update(ReglaComercial reglaComercial) throws Exception {
		return getSqlSessionTemplate().update("reglaComercial.update", reglaComercial);
	}
	
	@SuppressWarnings("rawtypes")
	public Integer saveReglaEntidad(ReglaEntidad reglaEntidad) throws Exception {
		return getSqlSessionTemplate().insert("reglaComercial.saveReglaEntidad", reglaEntidad);
	}
	
	@SuppressWarnings("rawtypes")
	public Integer saveReglaEntidadHistorial(ReglaEntidad reglaEntidad) throws Exception {
		return getSqlSessionTemplate().insert("reglaComercial.saveReglaEntidadHistorial", reglaEntidad);
	}
	
	public Integer deleteAllReglaEntidadByRegla(ReglaComercial reglaComercial) throws Exception {
		return getSqlSessionTemplate().delete("reglaComercial.deleteAllReglaEntidadByRegla", reglaComercial);
	}
	
	public List<ReglaComercial> listByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("reglaComercial.listByFilter", filter);
	}
	
	public Integer getTotalByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercial.getTotalByFilter", filter);
	}
	
	public List<ReglaComercial> listByFilter(FilterRegla filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("reglaComercial.listByFilter", filter, gridControl.getRowBounds());
	}
	 
	public ReglaComercial getById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercial.get",id);
	}
	
	public List<Usuario> listUsuariosReglasComerciales() throws Exception {
		return getSqlSessionTemplate().selectList("reglaComercial.listUsuariosReglasComerciales");
	}
	
	public List<ReglaComercial> listHistoricoByFilter(FilterReglaHistorica filter) throws Exception {
		return getSqlSessionTemplate().selectList("reglaComercialHistorico.listHistoricoByFilter", filter);
	}
	
	public List<ReglaComercial> listHistoricoByFilter(FilterReglaHistorica filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("reglaComercialHistorico.listHistoricoByFilter", filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalHistoricoByFilter(FilterReglaHistorica filter) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercialHistorico.getTotalHistoricoByFilter", filter);
	}
	
	public ReglaComercial getReglaComercialHisoricaById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercialHistorico.getById", id);
	}
	
	public ReglaComercial getReglaComercialVigenteByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercial.getReglaComercialVigenteByFilter", filter);
	}
	
	public ReglaComercial getLastReglaComercialHistoricaByIdRegla(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("reglaComercialHistorico.getLastReglaComercialHistoricaByIdRegla", id);
	}
	
}
