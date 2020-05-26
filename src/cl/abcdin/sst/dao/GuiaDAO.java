package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.GridControl;

public class GuiaDAO extends BaseDAO {

	public List<Guia> listGuiasPendientesBySucursal(FilterGuiasPendientes filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiasPendientesBySucursal", filter, gridControl.getRowBounds());
	}
	
	public List<Guia> listGuiasPendientesBySucursal(FilterGuiasPendientes filter) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiasPendientesBySucursal", filter);
	}
	
	public Integer getTotalGuiasPendientesBySucursal(FilterGuiasPendientes filter) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getTotalGuiasPendientesBySucursal", filter);
	}
	
	public Guia get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.get", id);
	}
	
	public Guia getGuiaByGuia(Guia guia) throws Exception{
		return getSqlSessionTemplate().selectOne("guia.getGuiaByGuia",guia);
	}
	
	public List<Guia> getByNumero(Long numero) throws Exception {
		return getSqlSessionTemplate().selectList("guia.getByNumero", numero);
	}
	
	public Long getSiguienteNumeroGuia(FilterGuia filter) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getSiguienteNumeroGuia", filter);
	}
	
	public Integer update(Guia guia) throws Exception {
		return getSqlSessionTemplate().update("guia.update", guia);
	}
	
	public Guia getGuiaByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getGuiaByIdOT", idOT);
	}

	public Guia getUltimaGuiaByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getUltimaGuiaByOT", idOT);
	}

	public Guia getUltimaGuiaAccesoriosByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getUltimaGuiaAccesoriosByOT", idOT);
	}
	
	public Integer updateEstado(Guia guia) throws Exception {
		return getSqlSessionTemplate().update("guia.updateEstado", guia);
	}
	
	public Integer save(Guia guia) throws Exception {
		return getSqlSessionTemplate().update("guia.save", guia);
	}
	
	public Integer getExisteGuiaId(Long idGuia) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getExisteGuiaId", idGuia);
	}

	public Guia getGuiaRecepcion(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getGuiaRecepcion", guia);
	} 
	
	public Guia getGuiaAccesorioForIdOT(Guia guia)throws Exception{
		return getSqlSessionTemplate().selectOne("guia.getGuiaAccesorioForIdOT", guia);
	}

	public List<Guia> listGuiaRecepcion(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiaRecepcion", guia);
	}
	
	public List<Guia> listGuiaDocumentoProductoByDocumento(Documento documento) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiaDocumentoProductoByDocumento", documento);
	}
	
	public List<Guia> listGuiasBodegaByFilter(FilterGuia filter, GridControl gridControl)throws Exception{
		return getSqlSessionTemplate().selectList("guia.listGuiasBodegaByFilter",filter, gridControl.getRowBounds());
	}
	
	public List<Guia> listGuiasBodegaByFilter(FilterGuia filter)throws Exception{
		return getSqlSessionTemplate().selectList("guia.listGuiasBodegaByFilter",filter);
	}
	
	public Integer getTotalGuiasBodegaByFilter(FilterGuia filter) throws Exception{
		return getSqlSessionTemplate().selectOne("guia.getTotalGuiasBodegaByFilter",filter);
	}
	
	public Guia getGuiaVigenteByIdOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("guia.getGuiaVigenteByIdOT",idOT);
	}
	
	public Integer getGuiaPendiente(Long idOT)throws Exception{
		return getSqlSessionTemplate().selectOne("guia.getGuiaPendiente",idOT);
	}
	
	public Guia getGuiaSinEmitirByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getGuiaSinEmitirByIdOT",idOT);
	}
	
	public List<Guia> listGuiasByIdDespacho(Long id) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiasByIdDespacho",id);
	}
	
	public Integer getExisteNumero(Long numero) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getExisteNumero",numero);
	}
	
	public List<Guia> listGuiasToUbicacionDiezMilByidDespacho(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectList("guia.listGuiasToUbicacionDiezMilByidDespacho",idDespacho);
	}
	
	public Integer getGuiasToUbicacionDiezMilByidDespacho(Long idDespacho) throws Exception {
		return getSqlSessionTemplate().selectOne("guia.getGuiasToUbicacionDiezMilByidDespacho",idDespacho);
	}
}
