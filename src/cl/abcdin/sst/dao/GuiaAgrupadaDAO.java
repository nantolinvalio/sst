package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.model.vo.ProductoReport;

public class GuiaAgrupadaDAO extends BaseDAO {
	
	public List<OrdenTrabajo> listOtPendientesPorGuia(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listOtPendientesPorGuia", filterGuiasPendientes);
	}
	
	public Integer deletePorEnviarByUbicacion(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().delete("guiaAgrupada.deletePorEnviarByUbicacion", filterGuiasPendientes);
	}
	
	public List<Guia> listPorEnviarByUbicacion(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.getPorEnviarByUbicacion", filterGuiasPendientes);
	}
	
	public List<GuiaPendienteAgrupada> listGuiasAgrupadasPendientes(FilterGuiasPendientes filterGuiasPendientes, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listGuiasAgrupadasPendientes", filterGuiasPendientes, gridControl.getRowBounds());
	}
	
	public List<GuiaPendienteAgrupada> listGuiasAgrupadasPendientesFF(FilterGuiasPendientes filterGuiasPendientes, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listGuiasAgrupadasPendientesFF", filterGuiasPendientes, gridControl.getRowBounds());
	}
	
	public List<GuiaPendienteAgrupada> listGuiasAgrupadasPendientesFF(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listGuiasAgrupadasPendientesFF", filterGuiasPendientes);
	}
	
	public List<GuiaPendienteAgrupada> listGuiasAgrupadasPendientes(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listGuiasAgrupadasPendientes", filterGuiasPendientes);
	}
	
	public Integer getTotalGuiasAgrupadasPendientes(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaAgrupada.getTotalGuiasAgrupadasPendientes", filterGuiasPendientes);
	}
	
	public Integer getTotalGuiasAgrupadasPendientesFF(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaAgrupada.getTotalGuiasAgrupadasPendientesFF", filterGuiasPendientes);
	}
	
	public Integer getTotalGuiasAgrupadasPendientesSinEmitir(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaAgrupada.getTotalGuiasAgrupadasPendientesSinEmitir",filterGuiasPendientes);
	}	
	
	public List<ProductoReport> listDetalleGuiaAgrupadaByGuia (Guia guia) throws Exception {
		return getSqlSessionTemplate().selectList("guiaAgrupada.listDetalleGuiaAgrupadaByGuia", guia);
	}
	
	public Guia getGuiaRecepcionAgrupada(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaAgrupada.getGuiaRecepcionAgrupada", guia);
	}

	public Guia getGuiaAgrupadabyGuia(Guia guia) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaAgrupada.getGuiaAgrupadabyGuia",guia);
	}
	
	public List<OrdenTrabajo> listOtPendientesPorGuiaATransportista(FilterGuiasPendientes filterGuiasPendientes) throws Exception{
		return getSqlSessionTemplate().selectList("guiaAgrupada.listOtPendientesPorGuiaATransportista",filterGuiasPendientes);
	}
}
