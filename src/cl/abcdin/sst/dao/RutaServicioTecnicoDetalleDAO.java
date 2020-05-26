package cl.abcdin.sst.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.RutaServicioTecnico;
import cl.abcdin.sst.model.RutaServicioTecnicoDetalle;
import cl.abcdin.sst.model.filters.FilterRutaServicioTecnico;

public class RutaServicioTecnicoDetalleDAO extends BaseDAO{
    
    public Integer saveRutaServicioTecnicoDetalle(RutaServicioTecnicoDetalle rutaServicioTecnicoDetalle) throws Exception {
        return getSqlSessionTemplate().insert("rutaServicioTecnicoDetalle.saveRutaServicioTecnicoDetalle",rutaServicioTecnicoDetalle);
    }
    
    public Boolean getCountSTecnicoOtrasRutas(RutaServicioTecnico rutaServicioTecnico, Integer stecnico) throws Exception {
    	Map<String, Object> params = new HashMap<String, Object>(); 
	    params.put("id_ruta",rutaServicioTecnico.getId());
	    params.put("id_stecnico", stecnico);

	    Integer cant = getSqlSessionTemplate().selectOne("rutaServicioTecnicoDetalle.getCountSTecnicoOtrasRutas", params);
	    
	    return cant != 0;
    }
    
    public Integer deleteAllByidRuta(Integer idRuta) throws Exception{
        return getSqlSessionTemplate().delete("rutaServicioTecnicoDetalle.deleteAllByidRuta",idRuta);
    }
    
    public Integer deleteByFilter(FilterRutaServicioTecnico filter) throws Exception {
        return getSqlSessionTemplate().delete("rutaServicioTecnicoDetalle.deleteByFilter",filter);
    }
    
    public List<Integer> listIdStecnicoByRuta(Long idRuta) throws Exception {
    	return getSqlSessionTemplate().selectList("rutaServicioTecnicoDetalle.listIdStecnicoByRuta", idRuta);
    }
    
    public Integer deleteSTFromRuta(FilterRutaServicioTecnico filterRutaServicioTecnico) throws Exception{
    	return getSqlSessionTemplate().delete("rutaServicioTecnicoDetalle.deleteSTFromRuta",filterRutaServicioTecnico);
    }
}
