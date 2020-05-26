package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.FamiliaExcluidaControlCalidad;
import cl.abcdin.sst.model.filters.FilterFamilia;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.FamiliaExcluida;
import cl.abcdin.sst.model.vo.TreeView;

public class FamiliaDAO extends BaseDAO{

	public List<Familia> listByFilter(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listByFilter", filter);
	}
	
	public List<TreeView> listByFilterAsTreeView(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listByFilterAsTreeView", filter);
	}
	
	public List<TreeView> listByFilterReglaAsTreeView(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listByFilterReglaAsTreeView", filter);
	}
	
	public List<TreeView> listHistoricasByFilterReglaAsTreeView(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listHistoricasByFilterReglaAsTreeView", filter);
	}
	
	public Familia getByIdProducto(Integer idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getByIdProducto", idProducto);
	}
	
	public Integer getCountFamiliasByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getCountFamiliasByFilter",filter);
	}
	
	public Integer getCountFamiliasHistoricasByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getCountFamiliasHistoricasByFilter",filter);
	}
	
	public List<TreeView> listFamiliasExcluidasByFilter(FilterProducto filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listFamiliasExcluidasByFilter", filter, gridControl.getRowBounds());
	}
	
	public List<TreeView> listFamiliasExcluidasByFilter(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listFamiliasExcluidasByFilter", filter);
	}
	
	public Integer getTotalFamiliasExcluidasByFilter(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getTotalFamiliasExcluidasByFilter",filter);
	}

	public void deleteFamiliaExcluida(FamiliaExcluida familia) throws Exception {
		getSqlSessionTemplate().delete("familia.deleteFamiliaExcluida", familia);
	}
	
	public FamiliaExcluida getFamiliaExcluidaByIdFamilia(String idFamilia) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getFamiliaExcluidaByIdFamilia", idFamilia);
	}
	
	public void saveFamiliaExcluida(FamiliaExcluida familia) throws Exception {
		getSqlSessionTemplate().insert("familia.saveFamiliaExcluida", familia);
	}
		
	public List<TreeView> listFamiliasExcluidasSerieByFilter(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listFamiliasExcluidasSerieByFilter", filter);
	}
	
	public List<TreeView> listFamiliasExcluidasSerieByFilter(FilterProducto filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listFamiliasExcluidasSerieByFilter", filter, gridControl.getRowBounds());
	}
	
	public void deleteFamiliaExcluidaSerie(FamiliaExcluida familia) throws Exception {
		getSqlSessionTemplate().delete("familia.deleteFamiliaExcluidaSerie", familia);
	}
	
	public FamiliaExcluida getFamiliaExcluidaSerieByIdFamilia(String idFamilia) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getFamiliaExcluidaSerieByIdFamilia", idFamilia);
	}

	public void saveFamiliaExcluidaSerie(FamiliaExcluida familia) throws Exception {
		getSqlSessionTemplate().insert("familia.saveFamiliaExcluidaSerie", familia);
	}
	
	public List<Familia> listFamiliasParaExcluirCC(FilterFamilia filterFamilia, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("familia.listFamiliasParaExcluirCC",filterFamilia, gridControl.getRowBounds());
	}
	public List<Familia> listFamiliasParaExcluirCC(FilterFamilia filterFamilia) throws Exception {
        return getSqlSessionTemplate().selectList("familia.listFamiliasParaExcluirCC",filterFamilia);
    }
	
	
	public Integer getTotalFamiliasParaExcluirCC()throws Exception{
		return getSqlSessionTemplate().selectOne("familia.getTotalFamiliasParaExcluirCC");
	}
	
	public List<CheckForFlexigrid> listAllFamiliaCheck() throws Exception {
		return getSqlSessionTemplate().selectList("familia.listAllFamiliaCheck");
	}
	
	public Familia getFamiliaExcluidaCCByIdFamilia(String idFamilia) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getFamiliaExcluidaCCByIdFamilia",idFamilia);
	}
	
	public Integer saveFamiliaExcluidaCC(FamiliaExcluidaControlCalidad familiaExcluidaControlCalidad) throws Exception{
		return getSqlSessionTemplate().insert("familia.saveFamiliaExcluidaCC",familiaExcluidaControlCalidad);
	}
	
	public Integer deleteFamiliaExcluidaCC(List<String> idFamilia) throws Exception {
		return getSqlSessionTemplate().delete("familia.deleteFamiliaExcluidaCC",idFamilia);
	}
	
	public Integer deleteFamiliaExcluidaCC() throws Exception {
		return getSqlSessionTemplate().delete("familia.deleteAllFamiliaExcluidaCC");
	}
	
	public Familia getFamiliaByFilter(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getFamiliaByFilter",filter);
	}
	
	public List<Familia> listFamiliasByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
	    return getSqlSessionTemplate().selectList("familia.listFamiliasByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public Integer getTotalFamiliasByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getTotalFamiliasByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public List<Familia> listFamiliaLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("familia.listFamiliaLessCodigoUbicacion",codigo,gridControl.getRowBounds());
	}
	
	public Integer getTotalFamiliaLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.getTotalFamiliaLessCodigoUbicacion",codigo);
	}
	
	public List<CheckForFlexigrid> listAllFamiliaLessCodigoUbicacion(String codigo) throws Exception{
		return getSqlSessionTemplate().selectList("familia.listAllFamiliaLessCodigoUbicacion",codigo);
	}
	
	public Integer isFamiliaExcluidaCCByIdProducto(Integer idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("familia.isFamiliaExcluidaCCByIdProducto",idProducto);
	}
	
	public Familia getById(String idFamilia)throws Exception{
		return getSqlSessionTemplate().selectOne("familia.getById",idFamilia);
	}
}
