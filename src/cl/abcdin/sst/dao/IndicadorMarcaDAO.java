package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.filters.FilterIndicador;


public class IndicadorMarcaDAO extends BaseDAO{
	
	public Double getTotalOTAsignadaEjecutivaMarcaIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTotalOTAsignadaEjecutivaMarcaIndicador", filterIndicador);
	}
	
	public Double getTotalOTServicioTecnicoIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTotalOTServicioTecnicoIndicador", filterIndicador);
	}
	
	public Double getTotalOTAsignadasSolucionadasCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTotalOTAsignadasSolucionadasCDIndicador", filterIndicador);
	}
	
	public Double getTotalOTSolucionadaClienteIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTotalOTSolucionadaClienteIndicador", filterIndicador);
	}
	
	public Double getNivelServicioOTPorCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getNivelServicioOTPorCDIndicador", filterIndicador);
	}
	
	public Double getNivelServicioOTNOPorCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getNivelServicioOTNOPorCDIndicador", filterIndicador);
	}
	
	public Double getNivelServicioOTAcidoIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getNivelServicioOTAcidoIndicador", filterIndicador);
	}
	
	public Double getTiempoPromedioSTLIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTiempoPromedioSTLIndicador", filterIndicador);
	}
	
	public Double getTiempoPromedioOTSolucionadaIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTiempoPromedioOTSolucionadaIndicador", filterIndicador);
	}
	
	public Double getTiempoPromedioEntregaClienteIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorMarca.getTiempoPromedioEntregaClienteIndicador", filterIndicador);
	}
	
	
	public List<OrdenTrabajo> listTiempoPromedioEntregaClienteIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listTiempoPromedioEntregaClienteIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listTiempoPromedioOTSolucionadaIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listTiempoPromedioOTSolucionadaIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listTiempoPromedioSTLIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listTiempoPromedioSTLIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listNivelServicioOTAcidoIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listNivelServicioOTAcidoIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listNivelServicioOTNOPorCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listNivelServicioOTNOPorCDIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTServicioTecnicoIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listOTServicioTecnicoIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listNivelServicioOTPorCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listNivelServicioOTPorCDIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTAsignadaEjecutivaMarcaIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listOTAsignadaEjecutivaMarcaIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTAsignadasSolucionadasCDIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listOTAsignadasSolucionadasCDIndicador", filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTSolucionadaClienteIndicador(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorMarca.listOTSolucionadaClienteIndicador", filterIndicador);
	}
	
}
