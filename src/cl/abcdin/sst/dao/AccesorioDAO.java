package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.filters.FilterAccesorio;
import cl.abcdin.sst.model.filters.FilterGuia;
import cl.abcdin.sst.model.filters.GridControl;

public class AccesorioDAO extends BaseDAO{

	public List<Accesorio> listAccesoriosByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosByOT", idOT);
	}
	
	public Accesorio get(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("accesorio.get", id);
	}
	
	public Integer updateAccesoriosByEstado(Accesorio accesorio) throws Exception{
		return getSqlSessionTemplate().update("accesorio.updateAccesoriosByEstado",accesorio);
	}
	
	public Integer update(Accesorio accesorio) throws Exception{
		return getSqlSessionTemplate().update("accesorio.update",accesorio);
	}
	
	public Integer save(Accesorio accesorio) throws Exception{
		return getSqlSessionTemplate().update("accesorio.save",accesorio);
	}

	public Integer updateAccesoriosOTRequridoByFilter(FilterAccesorio filter) throws Exception {
		return getSqlSessionTemplate().update("accesorio.updateAccesoriosOTRequridoByFilter",filter);
	}
	
	public List<Accesorio> listAccesoriosByFilter(FilterAccesorio filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosByFilter",filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalAccesoriosByFilter(FilterAccesorio filter) throws Exception {
		return getSqlSessionTemplate().selectOne("accesorio.getTotalAccesoriosByFilter",filter);
	}
	
	public Integer updateUbicacionAccesorio(Accesorio accesorio) throws Exception {
		return getSqlSessionTemplate().update("accesorio.updateUbicacionAccesorio", accesorio);
	}
	
	public Integer updateUbicacionListAccesorio(Accesorio accesorio) throws Exception {
		return getSqlSessionTemplate().update("accesorio.updateUbicacionListAccesorio", accesorio);
	}
	
	public Integer updateEstadoAccesorioById(Accesorio accesorio) throws Exception {
		return getSqlSessionTemplate().update("accesorio.updateEstadoAccesorioById", accesorio);
	}

	public List<Accesorio> listAccesoriosByFilter(FilterAccesorio filter) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosByFilter", filter);
	}

	public List<Accesorio> listAccesoriosNotExistsByFilter(FilterAccesorio filter) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosNotExistsByFilter", filter);
	}
	
	public List<Accesorio> listAccesoriosFromIdGuia(FilterGuia filterguia) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosFromIdGuia", filterguia);
	}
	
	public List<Accesorio> listAllAccesoriosFromTipoGuia(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAllAccesoriosFromTipoGuia", guia);
	}
	
	public Integer saveAcceoriosOT(Accesorio accesorio) throws Exception {
		return getSqlSessionTemplate().insert("accesorio.saveAcceoriosOT",accesorio);
	}

	public Integer existeAccesorioOTById(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("accesorio.existeAccesorioOTById",id);
	}
	
	public Accesorio getAccesorioForIdAndIdOT(Accesorio accesorio) throws Exception{
		return getSqlSessionTemplate().selectOne("accesorio.getAccesorioForIdAndIdOT",accesorio);
	}
	
	public Integer updateIdGuiaFromAccesorio(Accesorio accesorio)throws Exception{
		return getSqlSessionTemplate().update("accesorio.updateIdGuiaFromAccesorio",accesorio);
	}
	
	public Integer updateDesvincularGuiaFromAccesorio(Guia guia)throws Exception{
		return getSqlSessionTemplate().selectOne("accesorio.updateDesvincularGuiaFromAccesorio",guia);
	}
	
	public Long getUbicacion(Accesorio accesorio) throws Exception{
		return getSqlSessionTemplate().selectOne("accesorio.getUbicacion",accesorio);
	}
	
	public List<Accesorio> ListAccesoriosFromGuiaAnterior(Long idOT)throws Exception{
		return getSqlSessionTemplate().selectList("accesorio.ListAccesoriosFromGuiaAnterior",idOT);
	}
	
	public List<Accesorio> ListAccesoriosFromClienteByIdOT(Long idOT)throws Exception{
		return getSqlSessionTemplate().selectList("accesorio.ListAccesoriosFromClienteByIdOT",idOT);
	}
	
	public List<Accesorio> listAccesoriosOTbyFilter(FilterAccesorio filterAccesorio) throws Exception {
		return getSqlSessionTemplate().selectList("accesorio.listAccesoriosOTbyFilter",filterAccesorio);
	}
}
