package cl.abcdin.sst.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.model.Macro;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.vo.Modulo;

public class UtilDAO extends BaseDAO {

	public Date getDate() throws Exception {
		return getSqlSessionTemplate().selectOne("util.getDate");
	}
	
	public Date getDateTrunc() throws Exception {
		return getSqlSessionTemplate().selectOne("util.getDateTrunc");
	}
	
	public Modulo getModuloInicialByUsuario(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectOne("modulo.getModuloInicialByUsuario", usuario);
	}

	public List<Modulo> listModulosMenu(Usuario usuario) throws Exception {
		return getSqlSessionTemplate().selectList("modulo.listModulosMenu", usuario);
	}

	public Modulo getModuloByNombre(String modulo) throws Exception {
		return getSqlSessionTemplate().selectOne("modulo.getModuloByNombre", modulo);
	}
	
	public List<Macro> listMacros() throws Exception {
		return getSqlSessionTemplate().selectList("modulo.listMacros");
	}

	public List<Modulo> listModulosInRolByMacro(Long idRol, String macro) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idRol", idRol);
		param.put("macro", macro);
		return getSqlSessionTemplate().selectList("modulo.listModulosInRolByMacro", param);
	}

	public List<Modulo> listModulosNotInRolByMacro(Long idRol, String macro) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idRol", idRol);
		param.put("macro", macro);
		return getSqlSessionTemplate().selectList("modulo.listModulosNotInRolByMacro", param);
	}
	
	public Integer deleteModuloByRol(Long idRol) throws Exception {
		return getSqlSessionTemplate().delete("modulo.deleteModuloByRol",idRol);
	}
	
	public Integer saveRolModulo(Long idRol, Long idModulo) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("id", null);
		param.put("idRol", idRol);
		param.put("idModulo", idModulo);
		return getSqlSessionTemplate().insert("modulo.saveRolModulo", param);		
	}
	
	public Modulo getModuloByNombreUsuario(String modulo, Usuario usuario) throws Exception {
		HashMap<Object,String> param = new HashMap<Object, String>();
		param.put("modulo", modulo);
		param.put("rut", usuario.getRut());
		return getSqlSessionTemplate().selectOne("modulo.getModuloByNombreUsuario", param);
	}	
	
	public Modulo getModuloByNombreUsuarioPermisos(String modulo, Usuario usuario) throws Exception {
		HashMap<Object,String> param = new HashMap<Object, String>();
		param.put("modulo", modulo);
		param.put("rut", usuario.getRut());
		return getSqlSessionTemplate().selectOne("modulo.getModuloByNombreUsuarioPermisos", param);
	}	
	
	public List<Modulo> listSubModuloByFilter(Long idModulo, Usuario usuario) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idModulo", idModulo);
		param.put("rut", usuario.getRut());
		return getSqlSessionTemplate().selectList("modulo.listSubModuloByFilter", param);
	}

	
	public List<Modulo> listSubModulosInModuloByRol(Long idModulo, Long idRol) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idModulo", idModulo);
		param.put("idRol", idRol);
		return getSqlSessionTemplate().selectList("modulo.listSubModulosInModuloByRol", param);
	}
	
	public List<Modulo> listSubModulosNotInModuloByRol(Long idModulo, Long idRol) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idModulo", idModulo);
		param.put("idRol", idRol);
		return getSqlSessionTemplate().selectList("modulo.listSubModulosNotInModuloByRol", param);
	}

	public Modulo getModuloByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("modulo.getModuloByCodigo", codigo);
	}

	public Integer deleteSubModulosByRolAndModulo(Rol rol, Modulo modulo) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idRol", rol.getId());
		param.put("idModulo", modulo.getId());		
		return getSqlSessionTemplate().delete("modulo.deleteSubModulosByRolAndModulo",param);
	}

	public Integer saveRolModuloSubModulo(Rol rol, Modulo modulo, Modulo subModulo, Integer orden) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("id", null);
		param.put("idRol", rol.getId());
		param.put("idModulo", modulo.getId());
		param.put("idSubModulo", subModulo.getId());		
		param.put("orden", orden);		
		return getSqlSessionTemplate().delete("modulo.saveRolModuloSubModulo",param);
	}
	
	public Modulo getModuloById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("modulo.getModuloById", id);
	}
	
	public Long getNumeroTicketCambio() throws Exception {
		return getSqlSessionTemplate().selectOne("util.getNumeroTicketCambio");
	}
	
	public Integer getCantidadCodBarras(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("util.getCantidadCodBarras", filterOT);
	}

	public Long getSeqInterfazPMM() {
		return getSqlSessionTemplate().selectOne("util.getSeqInterfazPMM");
	}
	
	public Long getSeqRegInterfazPMM() {
		return getSqlSessionTemplate().selectOne("util.getSeqRegInterfazPMM");
	}
	
	public Long getSeqInterfazORR() {
		return getSqlSessionTemplate().selectOne("util.getSeqInterfazORR");
	}
	
	public Long getSeqDetInterfazORR() {
		return getSqlSessionTemplate().selectOne("util.getSeqDetInterfazORR");
	}
	
	public Long getSeqInterfazASN() {
		return getSqlSessionTemplate().selectOne("util.getSeqInterfazASN");
	}
	
	public Long getSeqRegInterfazASN() {
		return getSqlSessionTemplate().selectOne("util.getSeqRegInterfazASN");
	}
}
