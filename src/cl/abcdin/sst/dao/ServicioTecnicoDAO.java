package cl.abcdin.sst.dao;

import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.RutaServicioTecnico;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.filters.FilterEjecutiva;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.STecnicoEjecutiva;
import cl.abcdin.sst.model.vo.ServicioTecnicoProducto;

public class ServicioTecnicoDAO extends BaseDAO {

	public ServicioTecnico getServicioTecnicoByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getServicioTecnicoByOT", idOT);
	}

	public List<ServicioTecnico> listServicioTecnico() throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnico");
	}

	public List<ServicioTecnico> listServicioTecnico(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnico", filterServicioTecnico);
	}
	
	public List<ServicioTecnico> listServicioTecnico(FilterServicioTecnico filterServicioTecnico, GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnico",filterServicioTecnico, gridControl.getRowBounds());
	}
	
	public Integer getTotalSTecnicoByProducto(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getTotalSTecnicoByProducto", filterServicioTecnico);
	}
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosCheck(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listAllServiciosTecnicosCheck", filter);
	}
	
	public Integer getTotalServiciosTecnicos(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getTotalServiciosTecnicos", filterServicioTecnico);
	}
	
	public Integer getTotalProductos(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalProductos", filterProducto);
	}

	public List<ServicioTecnico> listServicioTecnicoGM() throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnicoGM");
	}

	public Integer asignarSTecnicoOT(ServicioTecnico servicioTecnico) throws Exception {
		return getSqlSessionTemplate().update("servicioTecnico.asignarSTecnicoOT", servicioTecnico);
	}

	@SuppressWarnings("rawtypes")
	public List<ServicioTecnico> listServicioTecnicoByOrigenProductoGarantia(Map origenProductoAndTipoOT) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnicoByOrigenProductoGarantia",origenProductoAndTipoOT);
	}

	public List<ServicioTecnico> listSTecnicoByFilter(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listSTecnicoByFilter", filter);
	}

	public List<ServicioTecnico> listBodegasByFilter(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listSBodegasByFilter", filter);
	}

	public ServicioTecnicoProducto getByFilter(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getByFilter",filterServicioTecnico);
	}

	public Integer saveServicioTecnicoProducto(ServicioTecnicoProducto servicioTecnicoProducto) throws Exception {
		return getSqlSessionTemplate().insert("servicioTecnico.saveServicioTecnicoProducto",servicioTecnicoProducto);
	}

	public Integer updateVigencia(ServicioTecnicoProducto servicioTecnicoProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.updateVigencia", servicioTecnicoProducto);
	}
/***/
	public List<ServicioTecnico> listServicioTecnicoProducto(FilterServicioTecnico filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnicoProducto", filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalServicioTecnicoProducto(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getTotalServicioTecnicoProducto", filter);
	}
	
	public List<CheckForFlexigrid> listAllServiciosTecnicosProductoCheck(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listAllServiciosTecnicosProductoCheck", filter);
	}
/***/	
	public Integer updateVigenciaServTec(ServicioTecnico servicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.updateVigenciaServTec", servicioTecnico);
	}
	
	public Integer updateAllServicioTecnico(ServicioTecnico servicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.updateAllServicioTecnico", servicioTecnico);
	}

	public List<ServicioTecnico> listSTecnicoFromProductoByUbicacion(FilterServicioTecnico filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listSTecnicoFromProductoByUbicacion", filter, gridControl.getRowBounds());
	}

	public List<ServicioTecnico> listSTecnicoFromProductoByUbicacion(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listSTecnicoFromProductoByUbicacion", filter);
	}

	public Integer getTotalSTecnicoFromProductoByUbicacion(FilterServicioTecnico filter) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getTotalSTecnicoFromProductoByUbicacion",filter);
	}

	public ServicioTecnico getSTecnicoById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getSTecnicoById", id);
	}

	public Integer updateSTecnicoOT(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("servicioTecnico.updateSTecnicoOT", ot);
	}

	public Integer updateVigenteEnSTecnicoProducto(ServicioTecnicoProducto servicioTecnicoProducto) throws Exception {
		return getSqlSessionTemplate().update("servicioTecnico.updateVigenteEnSTecnicoProducto", servicioTecnicoProducto);
	}

	public List<ServicioTecnico> ListServiciosTecnicosBuscadorOT(FilterServicioTecnico filterServicioTecnico) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.ListServiciosTecnicosBuscadorOT",filterServicioTecnico);
	}
	
	public List<STecnicoEjecutiva> listServicioTecnicoForEjecutiva(FilterEjecutiva filterEjecutiva,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnicoForEjecutiva",filterEjecutiva,gridControl.getRowBounds());
	}
	
	public List<STecnicoEjecutiva> listServicioTecnicoForEjecutiva(FilterEjecutiva filterEjecutiva) throws Exception {
		return getSqlSessionTemplate().selectList("servicioTecnico.listServicioTecnicoForEjecutiva",filterEjecutiva);
	}
	
	public Integer getServicioTecnicoForEjecutiva(FilterEjecutiva filterEjecutiva) throws Exception {
		return getSqlSessionTemplate().selectOne("servicioTecnico.getServicioTecnicoForEjecutiva",filterEjecutiva);
	}
	
	public List<ServicioTecnico> listServiciosTecnicosForRutasSTByFilter(FilterServicioTecnico filterServicioTecnico) throws Exception{
	    return getSqlSessionTemplate().selectList("servicioTecnico.listServiciosTecnicosForRutasSTByFilter",filterServicioTecnico);
	}
	
	public List<ServicioTecnico> listServiciosTecnicosFromRutasSTByRuta(RutaServicioTecnico rutaServicioTecnico) throws Exception {
	    return getSqlSessionTemplate().selectList("servicioTecnico.listServiciosTecnicosFromRutasSTByRuta",rutaServicioTecnico);
	}
}
