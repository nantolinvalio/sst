package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.filters.FilterIndicador;

public class IndicadorGestionDAO extends BaseDAO{
	
	public Double getTotalOTEnSucursalInicio(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectOne("indicadorGestion.getTotalOTEnSucursalInicio", filterIndicador);
	}
	
	public Double getTotalOTEnSucursalTermino(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectOne("indicadorGestion.getTotalOTEnSucursalTermino", filterIndicador);
	}
	
	public Double getTiempoPromedioSucursalInicio(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectOne("indicadorGestion.getTiempoPromedioSucursalInicio", filterIndicador);
	}
	
	public Double getTiempoPromedioSucursalTermino(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectOne("indicadorGestion.getTiempoPromedioSucursalTermino", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTEnSucursalInicio(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectList("indicadorGestion.listOTEnSucursalInicio", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTEnSucursalTermino(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectList("indicadorGestion.listOTEnSucursalTermino", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTTiempoPromedioSucursalInicio(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectList("indicadorGestion.listOTTiempoPromedioSucursalInicio", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTTiempoPromedioSucursalTermino(FilterIndicador filterIndicador) throws Exception {
		return	getSqlSessionTemplate().selectList("indicadorGestion.listOTTiempoPromedioSucursalTermino", filterIndicador);
	}
	
}

	