package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Linea;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.TreeView;

public class LineaDAO extends BaseDAO{
	
	public List<Linea> listAll() throws Exception {
		return getSqlSessionTemplate().selectList("linea.listAll");
	}
	
	public List<Linea> listAll(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("linea.listAll",gridControl.getRowBounds());
	}
	
	public List<TreeView> listAllAsTreeView() throws Exception {
		return getSqlSessionTemplate().selectList("linea.listAllAsTreeView");
	}
	
	public Integer getTotalAll() throws Exception {
		return getSqlSessionTemplate().selectOne("linea.getTotalAll");
	}
	
	public List<TreeView> listLineasHistoricasAsTreeViewByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("linea.listLineasHistoricasAsTreeViewByFilter", filter);
	}
	
	public List<TreeView> listLineasAsTreeViewByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("linea.listLineasAsTreeViewByFilter", filter);
	}
	
	public Linea getByIdFamilia(String idFamilia) throws Exception {
		return getSqlSessionTemplate().selectOne("linea.getByIdFamilia", idFamilia);
	}
	
	public List<Linea> listLineasByIdUbicacionInterna(Integer idUbicacionInterna)throws Exception{
	    return getSqlSessionTemplate().selectList("linea.listLineasByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public List<Linea> listLineasByIdUbicacionInterna(Integer idUbicacionInterna, GridControl gridControl)throws Exception{
	    return getSqlSessionTemplate().selectList("linea.listLineasByIdUbicacionInterna",idUbicacionInterna, gridControl.getRowBounds());
	}
	
	public Integer getTotalLineasByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectOne("linea.getTotalLineasByIdUbicacionInterna",idUbicacionInterna);
	}

	public List<Linea> listLineasLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("linea.listLineasLessCodigoUbicacion",codigo,gridControl.getRowBounds());
	}
	
	public Integer getTotalLineasLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("linea.getTotalLineasLessCodigoUbicacion",codigo);
	}
	
	public List<CheckForFlexigrid> listAllIdLineaLessCodigoUbicacion(String codigo) throws Exception{
		return getSqlSessionTemplate().selectList("linea.listAllIdLineaLessCodigoUbicacion",codigo);
	}
	
	public List<CheckForFlexigrid> listAllIdLinea() throws Exception{
		return getSqlSessionTemplate().selectList("linea.listAllIdLinea");
	}
	
	public List<Linea> listLineasByCodigo(List<String> codigos) throws Exception {
		return getSqlSessionTemplate().selectList("linea.listLineasByCodigo",codigos);
	}
	
	public List<CheckForFlexigrid> listAllLineasByIdUbicacionInterna(Integer idUbicacion) throws Exception{
		return getSqlSessionTemplate().selectList("linea.listAllLineasByIdUbicacionInterna",idUbicacion);
	}
	
	public Linea getById(String idLinea) throws Exception{
		return getSqlSessionTemplate().selectOne("linea.getById",idLinea);
	}
}
