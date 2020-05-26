package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.filters.FilterParte;
import cl.abcdin.sst.model.filters.GridControl;

public class ParteDAO extends BaseDAO{
	
	public List<Parte> listPartesOTByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectList("parte.listPartesOTByOT", idOT);
	}
	
	public Parte get(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("parte.get", id);
	}

	public List<Parte> listPartesByFilter(FilterParte filter) throws Exception {
		return getSqlSessionTemplate().selectList("parte.listPartesByFilter", filter);		
	}
	
	public Integer saveParteOT(Parte parte) throws Exception {
		return getSqlSessionTemplate().insert("parte.saveParteOT",parte);
	}
	
	public Parte getParteOTbyId(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("parte.getParteOTbyId", id);
	}
	
	public Parte getParteOTbyId(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("parte.getParteOTbyId", id);
	}

	public Integer updateParteOT(Parte parte) throws Exception {
		return getSqlSessionTemplate().update("parte.updateParteOT",parte);
	}
	
	public List<Parte> listPartesByFilterPartes(FilterParte filter,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("parte.listPartesByFilterPartes",filter, gridControl.getRowBounds());
	}
	
	public Integer getPartesByFilter(FilterParte filter) throws Exception {
		return getSqlSessionTemplate().selectOne("parte.getPartesByFilter",filter);
	}
	
	public Integer save(Parte parte) throws Exception {
		return getSqlSessionTemplate().insert("parte.save",parte);
	}
	
	public Integer update(Parte parte) throws Exception {
		return getSqlSessionTemplate().update("parte.update",parte);
	}
	
	public Integer updateParteByEstado(Parte parte) throws Exception {
		return getSqlSessionTemplate().update("parte.updateParteByEstado",parte);
	}
	
}
