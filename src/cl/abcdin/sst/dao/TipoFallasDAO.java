package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;

import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.filters.FilterTipoFallas;
import cl.abcdin.sst.model.filters.GridControl;

public class TipoFallasDAO extends BaseDAO{

	public Integer deleteFallasAccesorios(TipoFallas tipoFallas) throws Exception {
		return getSqlSessionTemplate().delete("tipoFallas.deleteFallasAccesorios", tipoFallas);
	}
	public Integer deleteAccesoriosFallas(Accesorio accesorio) throws Exception {
		return getSqlSessionTemplate().delete("tipoFallas.deleteAccesoriosFallas", accesorio);
	}
	
	public Integer saveAccesoriosForTipoFallas(TipoFallas tipoFallas, Accesorio accesorio) throws Exception {
		HashMap<Object,Object> param = new HashMap<Object, Object>();
		param.put("idTipoFalla", tipoFallas.getId());
		param.put("idAccesorio", accesorio.getId());
		param.put("id", null);
		return getSqlSessionTemplate().insert("tipoFallas.saveAccesoriosForTipoFallas", param);
	}
	
	public List<TipoFallas> listTipoFallasByFilter(FilterTipoFallas filter) throws Exception {
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByFilter", filter);
	}
	
	public Integer listTotalFallasByOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("tipoFallas.listTotalFallasByOT",idOT);
	}

	public Integer deleteFallasByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().delete("tipoFallas.deleteFallasByOT", idOT);
	}
	
	public Integer save(TipoFallas tipoFallas) throws Exception {
		return getSqlSessionTemplate().insert("tipoFallas.save", tipoFallas);
	}

	public Integer update(TipoFallas tipoFallas) throws Exception {
		return getSqlSessionTemplate().update("tipoFallas.update", tipoFallas);
	}

	public TipoFallas get(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("tipoFallas.get", id);
	}
	
	public Integer listTotalFallasAccesorioByFilter(FilterTipoFallas filter) throws Exception {
		return getSqlSessionTemplate().selectOne("tipoFallas.listTotalFallasAccesorioByFilter",filter);
	}
	
	public List<TipoFallas> listAccesoriosByidTipoFalla(Long idTipoFalla) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listAccesoriosByidTipoFalla",idTipoFalla);
	}
	
	public Integer listTotalFallasOTByFilter(FilterTipoFallas filter) throws Exception {
		return getSqlSessionTemplate().selectOne("tipoFallas.listTotalFallasOTByFilter", filter);
	}
	
	public List<TipoFallas> listFallasOTById(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectList("tipoFallas.listFallasOTById",idOT);
	}
	
	public Integer saveTipoFallasOT(FilterTipoFallas filter) throws Exception{
		return getSqlSessionTemplate().insert("tipoFallas.saveTipoFallasOT", filter);
	}
	
	public Integer updateAccesoriosOTByFilter(FilterTipoFallas filter) throws Exception{
		return getSqlSessionTemplate().update("tipoFallas.updateAccesoriosOTByFilter", filter);
	}
	
	public List<TipoFallas> listTipoFallasByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByOT", idOT);
	}
	
	public List<TipoFallas> ListTipoFallasOTByOT(OrdenTrabajo oT) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.ListTipoFallasOTByOT",oT);
	}
	
	public List<TipoFallas> listTipoFallasByIdProducto(Integer idProducto) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByIdProducto",idProducto);
	}
	
	public List<TipoFallas> listTipoFallasByIdFamilia(String idFamilia) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByIdFamilia",idFamilia);
	}

	public List<TipoFallas> listTipoFallasByFilterFallas(FilterTipoFallas filter,GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByFilterFallas",filter,gridControl.getRowBounds());
	}

	public List<TipoFallas> listTipoFallasByFilterFallas(FilterTipoFallas filter) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasByFilterFallas",filter);
	}
	
	public List<TipoFallas> listTipoFallasNotExistsByFilterFallas(FilterTipoFallas filter) throws Exception{
		return getSqlSessionTemplate().selectList("tipoFallas.listTipoFallasNotExistsByFilterFallas",filter);
	}
	
	public Integer getTipoFallasByFilter(FilterTipoFallas filter) throws Exception{
		return getSqlSessionTemplate().selectOne("tipoFallas.getTipoFallasByFilter",filter);
	}
	
	public Integer getExisteTipoFalla(TipoFallas tipoFalla) throws Exception{
		return getSqlSessionTemplate().selectOne("tipoFallas.getExisteTipoFalla",tipoFalla);
	}
	
	public Integer saveTipoFallas(TipoFallas tipoFalla) throws Exception{
		return getSqlSessionTemplate().insert("tipoFallas.saveTipoFallas",tipoFalla);
	}
	
	public Integer updateTipoFallaByEstado(TipoFallas filter) throws Exception{
		return getSqlSessionTemplate().selectOne("tipoFallas.updateTipoFallaByEstado",filter);
	}
}
