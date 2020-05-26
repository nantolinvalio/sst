package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.SeccionColumna;
import cl.abcdin.sst.model.filters.FilterColumna;

public class ColumnaDAO extends BaseDAO{
	
	public List<Columna> listColumnasIndicador(FilterColumna filter) throws Exception {
		return getSqlSessionTemplate().selectList("columna.listColumnasIndicador", filter);
	}
	
	public List<Columna> listColumnasNotInIndicador(FilterColumna filter) throws Exception {
		return getSqlSessionTemplate().selectList("columna.listColumnasNotInIndicador", filter);
	}
	
	public List<Columna> getidColumnasById(FilterColumna columna) throws Exception {
		return getSqlSessionTemplate().selectList("columna.getidColumnasById", columna);
	}
	
	public List<Columna> listColumnasByFilter(FilterColumna filter) throws Exception {
		return getSqlSessionTemplate().selectList("columna.listColumnasByFilter", filter);
	}

	public List<Columna> listColumnasDefaultByFilter(FilterColumna filter) throws Exception {
		return getSqlSessionTemplate().selectList("columna.listColumnasDefaultByFilter", filter);
	}

	public Integer getCantidadColumnasByFilter(FilterColumna filter) throws Exception {
		return getSqlSessionTemplate().selectOne("columna.getCantidadColumnasByFilter", filter);
	}

	public Integer saveSeccionColumnaIndicador(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().insert("seccionColumna.saveSeccionColumnaIndicador", seccionColumna);
	}
	
	public Integer deleteSeccionColumnaByIndicadorRol(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().insert("seccionColumna.deleteSeccionColumnaByIndicadorRol", seccionColumna);
	}
	
	public Integer saveSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().insert("seccionColumna.saveSeccionColumna", seccionColumna);
	}  
	
	public Integer deleteSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().delete("seccionColumna.deleteSeccionColumna", seccionColumna);
	}
	
	public Integer deleteColumnasIndicadores(FilterColumna columna) throws Exception {
		return getSqlSessionTemplate().delete("columna.deleteColumnasIndicadores", columna);
	}
	
	public Integer saveColumnasIndicadores(FilterColumna columna) throws Exception {
		return getSqlSessionTemplate().insert("columna.saveColumnasIndicadores", columna);
	}
	
	public Integer updateOrdenSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().update("seccionColumna.updateOrdenSeccionColumna", seccionColumna);
	}
	
	public Integer desplazaDerechaOrdenSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().update("seccionColumna.desplazaDerechaOrdenSeccionColumna", seccionColumna);
	}
	
	public Integer desplazaIzquierdaOrdenSeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().update("seccionColumna.desplazaIzquierdaOrdenSeccionColumna", seccionColumna);
	}
	
	public SeccionColumna getSeccionColumnaBySeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().selectOne("seccionColumna.getSeccionColumnaBySeccionColumna", seccionColumna);
	}
	
	public List<SeccionColumna> listSeccionColumnaBySeccionColumna(SeccionColumna seccionColumna) throws Exception {
		return getSqlSessionTemplate().selectList("seccionColumna.listSeccionColumnaBySeccionColumna", seccionColumna);
	}
}
