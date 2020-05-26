package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.DiasTramos;
import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.PeticionDocumentoDetalle;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.filters.FilterFactura;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.FacturaOT;

public class FacturaGestionDAO extends BaseDAO {

	public List<Factura> listFactura(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listFactura", gridControl, gridControl.getRowBounds() );
	}

	public List<Long> listIdOTbyIdFactura(Long idFactura) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listIdOTbyIdFactura", idFactura);
	}
	
	public List<Factura> listFactura() throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listFactura");
	}

	public Integer listTotalFactura() throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.listTotalFactura");
	}	
	
	public List<Transportista> listTransportista() throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listTransportista");
	}
	
	public List<Marca> listMarca() throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listMarca");
	}
	
	public List<Proveedor> listProveedor() throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listProveedor");
	}
	
	public List<Familia> listFamilia() throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listFamilia");
	}
	
	public List<Long> getIdOTByIdFactura(Long idFactura) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.getIdOTByIdFactura", idFactura);
	}
	
	public Integer getIdEstadoByIdFactura(Long idFactura) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getIdEstadoByIdFactura", idFactura);
	}
	
	public Integer updateEstadoFactura(Factura factura) throws Exception {
		return getSqlSessionTemplate().update("facturaGestion.updateEstadoFactura", factura);
	}
	
	public Integer updateEstadoFacturaAceptada(Factura factura) throws Exception {
		return getSqlSessionTemplate().update("facturaGestion.updateEstadoFacturaAceptada", factura);
	}
	
	public Integer updateOtTareaUrgente(OrdenTrabajo orden) throws Exception {
		return getSqlSessionTemplate().update("facturaGestion.updateOtTareaUrgente", orden);
	}
	
	public List<Factura> listResumenFacturasByOT(FilterFactura filter) throws Exception {
		Long sumaRut = (long) 0;
		if (filter.getIdFacturar() != null){
			sumaRut += filter.getIdFacturar();
		}
		if (filter.getIdProveedor() != null){
			sumaRut += filter.getIdProveedor();
		}
		if (filter.getIdTransportista() != null){
			sumaRut += filter.getIdTransportista();
		}
		filter.setRut(sumaRut);
		return getSqlSessionTemplate().selectList("facturaGestion.listResumenFacturasByOT", filter);
	}
	
	public List<Factura> listResumenFacturasByOT(GridControl gridControl, FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listResumenFacturasByOT", filter, gridControl.getRowBounds() );
	}
	
	public Integer listTotalResumenFacturas(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.listTotalResumenFacturas", filter);
	}
	
	public List<OrdenTrabajo> listOtsFactura(FilterFactura filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listOtsFactura", filter, gridControl.getRowBounds() );
	}
	
	public List<OrdenTrabajo> listOtsFactura(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listOtsFactura", filter);
	}
	
	public List<CheckForFlexigrid> listAllOtsFacturaCheck(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listAllOtsFacturaCheck", filter);
	}

	public Integer listTotalOtsFactura(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.listTotalOtsFactura", filter);
	}	
	
	public List<Factura> listFacturasByRutFacturado(GridControl gridControl, FilterFactura filter) throws Exception {
		DiasTramos diasTramos = getSqlSessionTemplate().selectOne("diasTramos.getDiasTramos");
		filter.setDiasTramos(diasTramos);
		return getSqlSessionTemplate().selectList("facturaGestion.listFacturasByRutFacturado", filter, gridControl.getRowBounds() );
	}
	
	public List<Factura> listFacturasByRutFacturado(FilterFactura filter) throws Exception {
		DiasTramos diasTramos = getSqlSessionTemplate().selectOne("diasTramos.getDiasTramos");
		filter.setDiasTramos(diasTramos);
		return getSqlSessionTemplate().selectList("facturaGestion.listFacturasByRutFacturado", filter);
	}

	public Integer listTotalFacturasByRutFacturado(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.listTotalFacturasByRutFacturado", filter);
	}
	
	public List<Producto> listProductosByListOT(List<OrdenTrabajo> ots) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listProductosByListOT", ots);
	}
	
	public Integer getExisteOTFactura(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getExisteOTFactura", idOT);
	}
	
	public Integer getExisteOTFacturaDestino(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getExisteOTFacturaDestino", idOT);
	}
	
	public Integer getVerificaOTsFactura(List<OrdenTrabajo> idOTs) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getVerificaOTsFactura", idOTs);
	}
		
	public Factura getOTFactura(List<OrdenTrabajo> idOTs) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getOTFactura", idOTs);
	}
	
	public Long getCalculoNetoIvaByListOT(List<OrdenTrabajo> idOTs) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getCalculoNetoIvaByListOT", idOTs);
	}
	
	public Integer insertFactura(Factura factura) throws Exception {
		return getSqlSessionTemplate().insert("facturaGestion.insertFactura", factura);
	}
	
	public Integer insertRelacionFactura(FacturaOT facturaOT) throws Exception {
		return getSqlSessionTemplate().insert("facturaGestion.insertRelacionFactura", facturaOT);
	}
	
	public Integer insertDetallesFactura(Producto producto) throws Exception {
		return getSqlSessionTemplate().insert("facturaGestion.insertDetallesFactura", producto);
	}
	
	public List<OrdenTrabajo> listOtByFactura(Factura factura) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listOtByFactura", factura.getId());
	}
	
	public List<PeticionDocumentoDetalle> getProductoForFacturacion(Factura factura) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.getProductoForFacturacion", factura.getId());
	}
	
	public Factura getFacturaById(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getFacturaById", id);
	}
	
	public Factura getPasosFacturaById(Integer id) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getPasosFacturaById", id);
	}
	
	public List<Factura> listarDetalleFactura(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectList("facturaGestion.listarDetalleFactura", filter.getIdFacturar());
	}
	
	public Integer getTotalDetalleFactura(FilterFactura filter) throws Exception {
		return getSqlSessionTemplate().selectOne("facturaGestion.getTotalDetalleFactura", filter.getIdFacturar());
	}
}
