package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.GridControl;

public class RolDAO extends BaseDAO {

	public List<Rol> listRolesByUsuario(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectList("rol.getRolesByUsuario",usuario);
	}

	public Integer updateEstado(Rol rol) throws Exception {
		return getSqlSessionTemplate().update("rol.updateEstado", rol);
	}

	public Integer updateNombre(Rol rol) throws Exception {
		return getSqlSessionTemplate().update("rol.updateNombre", rol);
	}
	
	public Integer save(Rol rol) throws Exception {
		return getSqlSessionTemplate().insert("rol.save", rol);
	}

	public List<Rol> listRoles(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("rol.listRoles", gridControl, gridControl.getRowBounds());
	}
	
	public Integer getTotalRoles() throws Exception {
		return getSqlSessionTemplate().selectOne("rol.getTotalRoles");
	}
	
	public List<Rol> listRolesActivos() throws Exception {
		return getSqlSessionTemplate().selectList("rol.listRolesActivos");
	}
	
	public Rol getRolByNombre(String nombre) throws Exception {
		return getSqlSessionTemplate().selectOne("rol.getRolByNombre", nombre);
	}
	
	public Rol getRolById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("rol.getRolById",id);
	}

	public Integer deleteRolesUsuarios(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().delete("rol.deleteRolesUsuarios", usuario);
	}
	
	public Integer saveRolUsuario(Rol rol, Usuario usuario) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("id", null);
		param.put("idRol", rol.getId());
		param.put("idUsuario", usuario.getId());
		return getSqlSessionTemplate().insert("rol.saveRolUsuario", param);
	}
}
