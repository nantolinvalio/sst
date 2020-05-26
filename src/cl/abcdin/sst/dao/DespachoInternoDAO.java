package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.GridControl;

public class DespachoInternoDAO extends BaseDAO{
	
	public List<DespachoInterno> ListRevisionesByFilter(FilterDespachoInterno filterDespachoInterno, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("despachoInterno.ListRevisionesByFilter",filterDespachoInterno, gridControl.getRowBounds());
	}
	
	public List<DespachoInterno> ListRevisionesByFilter(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectList("despachoInterno.ListRevisionesByFilter",filterDespachoInterno);
	}
	
	public Integer getTotalRevisionesByFilter(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoInterno.getTotalRevisionesByFilter",filterDespachoInterno);
	}
	
	public Integer save(DespachoInterno despacho) throws Exception{
		return getSqlSessionTemplate().insert("despachoInterno.save", despacho);
	}
	
	public DespachoInterno getDespachoById(Long idDespacho) throws Exception{
		return getSqlSessionTemplate().selectOne("despachoInterno.getDespachoById", idDespacho);
	}
	
	
	public List<DespachoInterno> listDespachosToFallaFabricacionByFilter(FilterOT filterOT,GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosToFallaFabricacionByFilter", filterOT,gridControl.getRowBounds());
	}
	
	public List<DespachoInterno> listDespachosToFallaFabricacionByFilter(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosToFallaFabricacionByFilter", filterOT);
	}
	
	public List<DespachoInterno> listDespachosByFilter(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosByFilter", filterOT);
	}
	
	public Integer getDespachosToFallaFabricacionByFilter(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectOne("despachoInterno.getDespachosToFallaFabricacionByFilter", filterOT);
	}

	
	public Integer updateEstadoFromDespachoToNextEstado(DespachoInterno despachoInterno) throws Exception{
		return getSqlSessionTemplate().update("despachoInterno.updateEstadoFromDespachoToNextEstado", despachoInterno);
	}
	
	public Integer updateEstadoByDespachoInterno(DespachoInterno despachoInterno) throws Exception {
		return getSqlSessionTemplate().update("despachoInterno.updateEstadoByDespachoInterno",despachoInterno);
	}
	
	public List<DespachoInterno> listDespachosInternosOnTraladoToSP(GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosInternosOnTraladoToSP",gridControl);
	}
	
	public List<DespachoInterno> listDespachosInternosOnTraladoToSP() throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosInternosOnTraladoToSP");
	}
	
	public Integer getTotalDespachosInternosOnTraladoToSP() throws Exception {
		return getSqlSessionTemplate().selectOne("despachoInterno.getTotalDespachosInternosOnTraladoToSP");
	}
	
	public DespachoInterno getCantidadesDespachoByDespachoInterno(DespachoInterno despachoInterno)throws Exception {
		return getSqlSessionTemplate().selectOne("despachoInterno.getCantidadesDespachoByDespachoInterno",despachoInterno);
	}
	
	public DespachoInterno getDespachoByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoInterno.getDespachoByIdOT",idOT);
	} 
	
	public List<DespachoInterno> listDespachosDesdeFFByFilter(FilterOT filterOT,GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosDesdeFFByFilter", filterOT,gridControl.getRowBounds());
	}
	
	public List<DespachoInterno> listDespachosDesdeFFByFilter(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectList("despachoInterno.listDespachosDesdeFFByFilter", filterOT);
	}
	
	public Integer getDespachosDesdeFFByFilter(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectOne("despachoInterno.getDespachosDesdeFFByFilter", filterOT);
	}
}
