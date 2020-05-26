package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.RutaServicioTecnico;
import cl.abcdin.sst.model.filters.FilterRutaServicioTecnico;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class RutaServicioTecnicoDAO extends BaseDAO{
	public List<RutaServicioTecnico> listRutasServicioTecnico(FilterRutaServicioTecnico filterRutaServicioTecnico) throws Exception {
        return getSqlSessionTemplate().selectList("rutaServicioTecnico.listRutasServicioTecnico",filterRutaServicioTecnico);
    }
	
    public List<RutaServicioTecnico> listRutasServicioTecnico(FilterRutaServicioTecnico filterRutaServicioTecnico, GridControl gridControl) throws Exception {
        return getSqlSessionTemplate().selectList("rutaServicioTecnico.listRutasServicioTecnico",filterRutaServicioTecnico, gridControl.getRowBounds());
    }
    
    public Integer getTotalRutasServicioTecnico(FilterRutaServicioTecnico filterRutaServicioTecnico) throws Exception {
        return getSqlSessionTemplate().selectOne("rutaServicioTecnico.getTotalRutasServicioTecnico");
    }
    
    public List<CheckForFlexigrid> listAllRutasServicioTecnicoCheck() throws Exception {
        return getSqlSessionTemplate().selectList("rutaServicioTecnico.listAllRutasServicioTecnicoCheck");
    }
    
    public Integer saveRutaServicioTecnico(RutaServicioTecnico rutaServicioTecnico)throws Exception {
        return getSqlSessionTemplate().insert("rutaServicioTecnico.saveRutaServicioTecnico",rutaServicioTecnico);
    }
    
    public RutaServicioTecnico getRutaServicioTecnicoByCodigo(String codigo) throws Exception{
        return getSqlSessionTemplate().selectOne("rutaServicioTecnico.getRutaServicioTecnicoByCodigo",codigo);
    }
    
    public Integer updateRutaServicioTecnico(RutaServicioTecnico rutaServicioTecnico) throws Exception{
        return getSqlSessionTemplate().update("rutaServicioTecnico.updateRutaServicioTecnico",rutaServicioTecnico);
    }
}
