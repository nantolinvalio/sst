package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.RecepcionCamion;
import cl.abcdin.sst.model.RecepcionCamionGuia;
import cl.abcdin.sst.model.RecepcionCamionOT;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.DetalleRecepcion;

public class RecepcionMasivaDAO extends BaseDAO{
	
	
	public List<cl.abcdin.sst.model.Recepcion> listRecepcionesMasivas()throws Exception{
		return getSqlSessionTemplate().selectList("recepcionMasiva.listRecepcionesMasivas");
	}
	
	public Integer saveRecepcionMasiva(cl.abcdin.sst.model.Recepcion recepcion)throws Exception{
		return getSqlSessionTemplate().insert("recepcionMasiva.saveRecepcionMasiva",recepcion);
	}
	
	public Guia getGuiaByFilterGuia(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getGuiaByFilterGuia",filterRecepcionProducto);
	}
	
	public Integer existeGuiaEnRecepcion(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.existeGuiaEnRecepcion",filterRecepcionProducto);
	}
	
	public Integer getTotalEstadoRecepcionCamion(FilterRecepcionProducto filterRecepcionProducto) throws Exception{
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getTotalEstadoRecepcionCamion",filterRecepcionProducto);
	}

	public Integer saveRecepcionesCamionGuias(RecepcionCamionGuia recepcionCamionGuia) throws Exception{
		return getSqlSessionTemplate().insert("recepcionMasiva.saveRecepcionesCamionGuias",recepcionCamionGuia);
	}
	
	public Integer updateCerrarRecepcionMasiva(RecepcionCamionGuia recepcionCamionGuia) throws Exception{
		return getSqlSessionTemplate().update("recepcionMasiva.updateCerrarRecepcionMasiva",recepcionCamionGuia);
	}
	
	public List<OrdenTrabajo> listOTbyIdGuia(Long idGuia)throws Exception {
		return getSqlSessionTemplate().selectList("recepcionMasiva.listOTbyIdGuia", idGuia);
	}
	
	public List<DetalleRecepcion> listDetalleGuiaRecepcion(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("recepcionMasiva.listDetalleGuiaRecepcion",filterRecepcionProducto, gridControl.getRowBounds());
	}
	
	public Integer getTotalDetalleGuiaRecepcion(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getTotalDetalleGuiaRecepcion",filterRecepcionProducto);
	}
	
	public List<DetalleRecepcion> listDetalleGuiaRecepcion(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectList("recepcionMasiva.listDetalleGuiaRecepcion",filterRecepcionProducto);
	}
	
	public Integer updateTerminoRecepcionMasiva(RecepcionCamion recepcionCamion)throws Exception {
		return getSqlSessionTemplate().update("recepcionMasiva.updateTerminoRecepcionMasiva",recepcionCamion);
	}
	
	public RecepcionCamion getRecepcionCamionById(Integer idRecepcion) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getRecepcionCamionById",idRecepcion);
	}
	
	public RecepcionCamion getRecepcionCamionAbiertaByTransportista(Long idTransportista) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getRecepcionCamionAbiertaByTransportista",idTransportista);
	}
	
	public RecepcionCamionGuia getRecepcionGuiaByFilter(FilterRecepcionProducto filterRecepcionProducto) {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getRecepcionGuiaByFilter",filterRecepcionProducto);
	}
	
	public RecepcionCamionGuia getUltimaRecepcionGuiaByFilter(FilterRecepcionProducto filterRecepcionProducto) {
		return getSqlSessionTemplate().selectOne("recepcionMasiva.getUltimaRecepcionGuiaByFilter",filterRecepcionProducto);
	}
	
	public Integer saveOTRecepcionMasiva(RecepcionCamionOT recepcionCamionOT) throws Exception {
		return getSqlSessionTemplate().insert("recepcionMasiva.saveOTRecepcionMasiva", recepcionCamionOT);
	}
	
	public Integer updateOTRecepcionMasiva(RecepcionCamionOT recepcionCamionOT) throws Exception {
		return getSqlSessionTemplate().insert("recepcionMasiva.updateOTRecepcionMasiva", recepcionCamionOT);
	}
}
