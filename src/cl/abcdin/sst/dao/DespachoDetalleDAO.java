package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.DespachoDetalle;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;

public class DespachoDetalleDAO extends BaseDAO{
	public Integer saveDespachoDetalle(DespachoDetalle despachoDetalle) throws Exception {
		return getSqlSessionTemplate().insert("despachoDetalle.saveDespachoDetalle",despachoDetalle);
	}
	
	public List<DespachoDetalle> listDespachoDetalleByIdDespacho(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectList("despachoDetalle.listDespachoDetalleByIdDespacho",idDespacho);
	}
	
	public Integer updateEstadoDespachoDetalleByIdAndOT(DespachoDetalle despachoDetalle) throws Exception {
		return getSqlSessionTemplate().update("despachoDetalle.updateEstadoDespachoDetalleByIdAndOT",despachoDetalle);
	}
	
	public DespachoDetalle getIndicadoresbyidDespacho(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoDetalle.getIndicadoresbyidDespacho",id);
	}
	
	public List<DespachoDetalle> listDespachoDetalleByIdDespachoInterno(FilterDespachoInterno filterDespacho) throws Exception {
		return getSqlSessionTemplate().selectList("despachoDetalle.listDespachoDetalleByIdDespachoInterno",filterDespacho);
	}
	
	public DespachoDetalle listDespachoDetalleByIdOT(FilterDespachoInterno filterDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("despachoDetalle.listDespachoDetalleByIdOT",filterDespacho);
	}
}
