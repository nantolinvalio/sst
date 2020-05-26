package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.OrdenTrabajoRecepcion;

public class RecepcionDAO extends BaseDAO{
	public List<Recepcion> listRecepcionesByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectList("recepcion.listRecepcionesByOT",idOT);
	}
	
	public Integer getExisteRecepcionOT(Recepcion recepcion) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcion.getExisteRecepcion",recepcion);
	}
	
	public Integer saveRecepcionOT(Recepcion recepcion) throws Exception {
		return getSqlSessionTemplate().insert("recepcion.saveRecepcionOT",recepcion);
	}	
	
	public List<OrdenTrabajoRecepcion> listOTByRecepcionesFilter(FilterRecepcion filterRecepcion, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoRecepcion.listOTByRecepcionesFilter",filterRecepcion, gridControl.getRowBounds());
	}	
	
	public List<OrdenTrabajoRecepcion> listOTByRecepcionesFilter(FilterRecepcion filterRecepcion) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoRecepcion.listOTByRecepcionesFilter",filterRecepcion);
	}	
	
	public Integer getTotalOTByRecepcionesFilter(FilterRecepcion filterRecepcion) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoRecepcion.getTotalOTByRecepcionesFilter", filterRecepcion);		
	}
	
	public Recepcion getRecepcionByGuia(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectOne("recepcion.getRecepcionByGuia", guia);		
	}
	
	
}
