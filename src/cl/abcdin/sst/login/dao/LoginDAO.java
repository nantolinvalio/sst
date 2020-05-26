package cl.abcdin.sst.login.dao;

import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.dao.BaseDAO;
import cl.abcdin.sst.login.model.Acceso;
import cl.abcdin.sst.login.model.Aplicacion;

public class LoginDAO extends BaseDAO {

	public Aplicacion getAplicacionByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("login.getAplicacionByCodigo", codigo);
	}
	
	public List<Acceso> listAccesosByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectList("login.listAccesosByCodigo", codigo);
	}
	
	public Integer saveRegistro(HashMap<String, Object> map) throws Exception {
		return getSqlSessionTemplate().insert("login.saveRegistro",map);
	}
}
