package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.GuiaRemateDetalle;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.GuiaPendienteAgrupada;
import cl.abcdin.sst.model.vo.ProductoReport;

public class GuiaRemateDAO extends BaseDAO {
	
	public Integer save(Guia guia) throws Exception {
		return getSqlSessionTemplate().update("guiaRemate.save", guia);
	}
	
	public Integer saveDetalle(GuiaRemateDetalle guiaRemateDetalle) throws Exception {
		return getSqlSessionTemplate().update("guiaRemate.saveDetalle", guiaRemateDetalle);
	}

	public List<GuiaPendienteAgrupada> listGuiasRemate(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("guiaRemate.listGuiasRemate", gridControl, gridControl.getRowBounds());
	}

	public List<GuiaPendienteAgrupada> listAllGuiasRemate(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("guiaRemate.listGuiasRemate", gridControl);
	}

	public Integer getTotalGuiasRemate() throws Exception {
		return getSqlSessionTemplate().selectOne("guiaRemate.getTotalGuiasRemate");
	}
	
	public GuiaPendienteAgrupada getGuiaRemateById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaRemate.getGuiaRemateById", id);
	}
	
	public Integer deleteDetalleGuiaRemate(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaRemate.deleteDetalleGuiaRemate", id);
	}
	
	public Integer deleteGuiaRemate(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("guiaRemate.deleteGuiaRemate", id);
	}

	public Integer updateEstado(GuiaPendienteAgrupada agrupada) throws Exception {
		return getSqlSessionTemplate().update("guiaRemate.updateEstado", agrupada);
	}
	
	public Integer cerrarOTFromGuiaRemate(GuiaPendienteAgrupada agrupada) throws Exception {
		return getSqlSessionTemplate().update("guiaRemate.cerrarOTFromGuiaRemate", agrupada);
	}
	
	public List<GuiaPendienteAgrupada> getGuiaRemateByNumero(Long numero) throws Exception {
		return getSqlSessionTemplate().selectList("guiaRemate.getGuiaRemateByNumero", numero);
	}
	
	public Integer emitirGuiaRemate(GuiaPendienteAgrupada guia) throws Exception {
		return getSqlSessionTemplate().update("guiaRemate.emitirGuiaRemate", guia);
	}
	
	public List<ProductoReport> listDetalleGuiaRemateByIdGuia(Long idGuia) throws Exception {
		return getSqlSessionTemplate().selectList("guiaRemate.listDetalleGuiaRemateByIdGuia",idGuia);
	}
}
