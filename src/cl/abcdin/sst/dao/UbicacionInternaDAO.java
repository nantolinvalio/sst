package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Inventario;
import cl.abcdin.sst.model.UbicacionInterna;
import cl.abcdin.sst.model.UbicacionInternaCD;
import cl.abcdin.sst.model.filters.FilterUbicacionInterna;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class UbicacionInternaDAO extends BaseDAO{
	
	public UbicacionInterna getUbicacionInternaByCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getUbicacionInternaByCodigo",codigo);
	}
	
	public List<UbicacionInterna> getUbicacionInternaByTipo(String tipo) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacionInterna.getUbicacionInternaByTipo",tipo);
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCDByFilter(FilterUbicacionInterna filterUbicacionInterna, GridControl gridControl) throws Exception{
	    return getSqlSessionTemplate().selectList("ubicacionInterna.listUbicacionInternaCDByFilter",filterUbicacionInterna,gridControl.getRowBounds());
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCDByFilter(FilterUbicacionInterna filterUbicacionInterna) throws Exception{
	    return getSqlSessionTemplate().selectList("ubicacionInterna.listUbicacionInternaCDByFilter",filterUbicacionInterna);
	}
	
	public Integer getTotalUbicacionInternaCD(FilterUbicacionInterna filterUbicacionInterna) throws Exception {
        return getSqlSessionTemplate().selectOne("ubicacionInterna.getTotalUbicacionInternaCD",filterUbicacionInterna);
    }
	
	public UbicacionInternaCD getUbicacionInternaCDByCodigo(String codigo)  throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getUbicacionInternaCDByCodigo",codigo);
	}
	
	public Integer save(UbicacionInterna ubicacionInterna) throws Exception{
		return getSqlSessionTemplate().insert("ubicacionInterna.save",ubicacionInterna);
	}
	
	public Integer updateModificaUbicacionInternaCD(UbicacionInternaCD ubicacionInternaCD) throws Exception{
		return getSqlSessionTemplate().update("ubicacionInterna.updateModificaUbicacionInternaCD",ubicacionInternaCD);
	}
	
	public List<UbicacionInternaCD> listUbicacionInternaCD() throws Exception {
		return getSqlSessionTemplate().selectList("ubicacionInterna.listUbicacionInternaCD");
	}
	
	public List<UbicacionInterna> listTodasUbicacionesInternasCD() throws Exception{
		return getSqlSessionTemplate().selectList("ubicacionInterna.listTodasUbicacionesInternasCD");
	}
	
	public List<UbicacionInternaCD> listUbicacionInternasCDByIdOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectList("ubicacionInterna.listUbicacionInternasCDByIdOT",idOT);
	}
	
	public List<UbicacionInternaCD> listUbicacionInternasCDOnlySucursalByIdOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectList("ubicacionInterna.listUbicacionInternasCDOnlySucursalByIdOT",idOT);
	}
	
	public List<UbicacionInterna> listToInventario(FilterUbicacionInterna filterUbicacionInterna,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ubicacionInterna.listToInventario", filterUbicacionInterna,gridControl.getRowBounds());
	}
	
	public Integer getTotalToInventario() throws Exception{
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getTotalToInventario");
	}
	
	public List<CheckForFlexigrid> listAllToInventario() throws Exception{
		return getSqlSessionTemplate().selectList("ubicacionInterna.listAllToInventario");
	}
	
	public Integer upateInventario(UbicacionInterna ubicacionInterna) throws Exception{
		return getSqlSessionTemplate().update("ubicacionInterna.upateInventario",ubicacionInterna);
	}
	
	public Integer updateInventarioByInventario(Inventario inventario) throws Exception{
		return getSqlSessionTemplate().update("ubicacionInterna.updateInventarioByInventario",inventario);
	}
	
//	public List<UbicacionInterna> listFromInventario(Integer idInventario, GridControl gridControl) throws Exception{
//		return getSqlSessionTemplate().selectList("ubicacionInterna.listFromInventario",idInventario, gridControl.getRowBounds());
//	}
	
//	public Integer getTotalFromInventario(Integer idInventario) throws Exception{
//		return getSqlSessionTemplate().selectOne("ubicacionInterna.getTotalFromInventario",idInventario);
//	}
	
	public UbicacionInterna getUbicacionInternaById(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getUbicacionInternaById",idUbicacionInterna);
	}
	
	public Integer updateInventarioById(UbicacionInterna ubicacionInterna) throws Exception {
		return getSqlSessionTemplate().update("ubicacionInterna.updateInventarioById",ubicacionInterna);
	}
	
	public UbicacionInterna getUbicacionInternaActualByIdOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getUbicacionInternaActualByIdOT",idOT);
	}
	
	public Integer getTotalOTOnUbicacionInterna(Long idUbicacionInterna) throws Exception{
		return getSqlSessionTemplate().selectOne("ubicacionInterna.getTotalOTOnUbicacionInterna",idUbicacionInterna);
	}
}
