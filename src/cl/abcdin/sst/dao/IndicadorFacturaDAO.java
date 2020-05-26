package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.GridControl;

public class IndicadorFacturaDAO extends BaseDAO {
	
	public Double getTotalFacturasListasParaEmitir(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorFactura.getTotalFacturasListasParaEmitir", filterIndicador);
	}
	
	public Double getTotalFacturasEmitidas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorFactura.getTotalFacturasEmitidas", filterIndicador);
	}
	
	public Double getTotalFacturasAceptadasMarcadas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorFactura.getTotalFacturasAceptadasMarcadas", filterIndicador);
	}
	
	public Double getTotalFacturasRechazadasMarcadas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicadorFactura.getTotalFacturasRechazadasMarcadas", filterIndicador);
	}
	
	public List<Factura> listFacturasListasParaEmitir(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasListasParaEmitir", filterIndicador);
	}
	
	public List<Factura> listFacturasEmitidas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasEmitidas", filterIndicador);
	}
	
	public List<Factura> listFacturasAceptadasMarcadas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasAceptadasMarcadas", filterIndicador);
	}
	
	public List<Factura> listFacturasRechazadasMarcadas(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasRechazadasMarcadas", filterIndicador);
	}
	
	public List<Factura> listFacturasListasParaEmitir(FilterIndicador filterIndicador, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasListasParaEmitir", filterIndicador, gridControl.getRowBounds());
	}
	
	public List<Factura> listFacturasEmitidas(FilterIndicador filterIndicador, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasEmitidas", filterIndicador, gridControl.getRowBounds());
	}
	
	public List<Factura> listFacturasAceptadasMarcadas(FilterIndicador filterIndicador, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasAceptadasMarcadas", filterIndicador, gridControl.getRowBounds());
	}
	
	public List<Factura> listFacturasRechazadasMarcadas(FilterIndicador filterIndicador, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("indicadorFactura.listFacturasRechazadasMarcadas", filterIndicador, gridControl.getRowBounds());
	}
	
	public List<Factura> listIndicadorFacturaByRut(FilterIndicador filter,GridControl gridControl) throws Exception{
		return getSqlSessionTemplate().selectList("indicadorFactura.listIndicadorFacturaByRut", filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalIndicadorFacturaByRut(FilterIndicador filter) throws Exception{
		return getSqlSessionTemplate().selectOne("indicadorFactura.getTotalIndicadorFacturaByRut",filter);
	}
}
