package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterUsuario;
import cl.abcdin.sst.model.filters.GridControl;

public class UsuarioDAO extends BaseDAO {

	public Usuario get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("usuario.get", id);
	}

	public List<Usuario> listEjecutivasMarca() throws Exception {
		return getSqlSessionTemplate().selectList("usuario.listEjecutivasMarca");
	}

	public List<Usuario> listUsuarios(FilterUsuario filterUsuario, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("usuario.listUsuarios",filterUsuario, gridControl.getRowBounds());
	}
	
	public List<Usuario> listAllUsuarios(FilterUsuario filterUsuario, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("usuario.listUsuarios",filterUsuario);
	}

	public Integer getTotalUsuarios(FilterUsuario filterUsuario) throws Exception {
		return getSqlSessionTemplate().selectOne("usuario.getTotalUsuarios", filterUsuario);
	}
	
	public Integer updateEstadoUsuario(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectOne("usuario.updateEstadoUsuario", usuario);
	}
	
	public Integer save(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectOne("usuario.save", usuario);
	}
	
	public Integer update(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectOne("usuario.update", usuario);
	}
	
}
