package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class SucursalDAO extends BaseDAO{
	
	public Sucursal getSucursalById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("sucursal.getSucursalById",id);
	}
	
	public List<Sucursal> listSucursalesByComuna(Long idComuna) throws Exception {
		return getSqlSessionTemplate().selectList("sucursal.listSucursalesByComuna",idComuna);
	}
	
	public List<Sucursal> listSucursalesByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
	    return getSqlSessionTemplate().selectList("sucursal.listSucursalesByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public Integer getTotalSucursalesByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectOne("sucursal.getTotalSucursalesByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public List<CheckForFlexigrid> listAllSucursalLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectList("sucursal.listAllSucursalLessCodigoUbicacion",codigo);
	}
	
	public Integer getTotalSucursalLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("sucursal.getTotalSucursalLessCodigoUbicacion",codigo);
	}
	
	public List<Sucursal> listSucursalLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("sucursal.listSucursalLessCodigoUbicacion",codigo, gridControl.getRowBounds());
	}
}
