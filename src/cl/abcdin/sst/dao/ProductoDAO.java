package cl.abcdin.sst.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.ProductoExcluido;
import cl.abcdin.sst.model.filters.FilterProcedimiento;
import cl.abcdin.sst.model.filters.FilterProducto;
import cl.abcdin.sst.model.filters.FilterRegla;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.TreeView;

public class ProductoDAO extends BaseDAO {

	public List<Producto> listByFilter (FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listByFilter", filter);
	}
	
	public List<Producto> listProductosByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosByFilter", filterProducto, gridControl.getRowBounds());
	}
	
	public Integer getTotalProductosByFilter(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalProductosByFilter", filterProducto);
	}
		
	public List<Producto> listAll () throws Exception {
		return getSqlSessionTemplate().selectList("producto.listAll");
	}
	
	public List<TreeView> listByFilterAsTreeView (FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listByFilterAsTreeView", filter);
	}
	
	public List<TreeView> listByFilterReglaAsTreeView (FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listByFilterReglaAsTreeView", filter);
	}
	
	public List<TreeView> listHistoricosByFilterReglaAsTreeView (FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listHistoricosByFilterReglaAsTreeView", filter);
	}
	
	public Integer getCountProductosFamiliaByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getCountProductosFamiliaByFilter",filter);
	}
	
	public Integer getCountProductosHistoricosFamiliaByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getCountProductosHistoricosFamiliaByFilter",filter);
	}
	
	public Integer getCountProductosLineaByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getCountProductosLineaByFilter",filter);
	}
	
	public Integer getCountProductosHistoricosLineaByFilter(FilterRegla filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getCountProductosHistoricosLineaByFilter",filter);
	}
  
	public Producto getProductoById(Integer idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getProductoById",idProducto);
	}
	
	public Producto getProductoById(Long idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getProductoById",idProducto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> listProductoByTipoDocumentoAndIdDocumento(Documento documento) throws Exception {
	    Map<String, Object> params = new HashMap<String, Object>(); 
	    ResultSet rs = null;
	    params.put("id", documento.getId());
	    params.put("tipo", documento.getTipo());
	    params.put("cursor", rs);
	    
	    getSqlSessionTemplate().selectOne("producto.listProductoByTipoDocumentoAndIdDocumento", params);
	    
	    return (ArrayList<Producto>)params.get("cursor");
	}
	
	public OrdenTrabajo getProductoOTById(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getProductoOTById", idOT);
	}
	
	public Integer getTotalEnDocumentoByFilter(FilterProducto filter) throws Exception {
	    Map<String, Object> params = new HashMap<String, Object>();
	    Integer total = 0;
	    params.put("idDocumento", filter.getIdDocumento());
	    params.put("idProducto", filter.getIdProducto());
	    params.put("tipoDocumento", filter.getTipoDocumento());
	    params.put("total", total);
	    
	    getSqlSessionTemplate().selectOne("producto.getTotalEnDocumentoByFilter", params);
	    
	    
	    return (Integer)params.get("total");
	    
//		return getSqlSessionTemplate().selectOne("producto.getTotalEnDocumentoByFilter",filter);
	}
	
	public Integer getTotalSumEnDocumentoByFilter(FilterProducto filter) throws Exception {
	    Map<String, Object> params = new HashMap<String, Object>();
	    Integer total = 0;
	    params.put("idDocumento", filter.getIdDocumento());
	    params.put("idProducto", filter.getIdProducto());
	    params.put("tipoDocumento", filter.getTipoDocumento());
	    params.put("total", total);
	    
	    getSqlSessionTemplate().selectOne("producto.getTotalSumEnDocumentoByFilter", params);
	    
	    
	    return (Integer)params.get("total");
	    
//		return getSqlSessionTemplate().selectOne("producto.getTotalEnDocumentoByFilter",filter);
	}
	
	public List<Producto> listProductosByDespacho(DespachoInterno despacho)throws Exception{
		return getSqlSessionTemplate().selectList("producto.listProductosByOt",despacho);
	}

	public List<Producto> listProductosByServicioTecnico(FilterProducto filter, GridControl gridControl)throws Exception{
		return getSqlSessionTemplate().selectList("producto.listProductosByServicioTecnico",filter, gridControl.getRowBounds()); //listSTecnicoByFilter
	}

	public List<CheckForFlexigrid> listAllProductosServicioTecnicoCheck(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listAllProductosServicioTecnicoCheck",filter);
	}

	public List<CheckForFlexigrid> listAllProductosByFilterCheck(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listAllProductosByFilterCheck", filter);
	}
	
	public Integer getTotalSTecnicoP(FilterProducto filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalSTecnicoP", filter);
	}
	
	public List<ProductoExcluido> listProductosParaExcluirCCByFilter(FilterProducto filterProducto, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosParaExcluirCCByFilter",filterProducto, gridControl.getRowBounds());
	}
	
	public List<ProductoExcluido> listProductosParaExcluirCCByFilter(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosParaExcluirCCByFilter",filterProducto);
	}
	
	public Integer getTotalProductosParaExcluirCCByFilter(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalProductosParaExcluirCCByFilter",filterProducto);
	}
	
	public Integer getExisteProcedimientobyFilter(FilterProcedimiento filter) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getExisteProcedimientobyFilter",filter);
	}
	
	public List<CheckForFlexigrid> listAllProductosCCCheck(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listAllProductosCCCheck",filterProducto);
	}
	public ProductoExcluido getProductoExcluidoCCByIdProducto(Integer idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getProductoExcluidoCCByIdProducto",idProducto);
	}
		
	public Integer saveProductoExcluidoCC(ProductoExcluido productoExcluido) throws Exception {
		return getSqlSessionTemplate().insert("producto.saveProductoExcluidoCC",productoExcluido);
	}
	
	public List<ProductoExcluido> listProductosExcluidosCCByFilter(FilterProducto filterProducto) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosExcluidosCCByFilter",filterProducto);
	}
	
	public Integer deleteProductoExcluidoCC(List<Integer> productosAgregar)throws Exception {
		return getSqlSessionTemplate().delete("producto.deleteProductoExcluidoCC",productosAgregar);
	}
	
	public Integer deleteProductoExcluidoCC() throws Exception{
		return getSqlSessionTemplate().delete("producto.deleteAllProductoExcluidoCC");
	}
	
	public List<Producto> listProductosByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public Integer getTotalProductosByIdUbicacionInterna(Integer idUbicacionInterna) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalProductosByIdUbicacionInterna",idUbicacionInterna);
	}
	
	public List<Producto> listProductosLessCodigoUbicacion(String codigo, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listProductosLessCodigoUbicacion",codigo,gridControl.getRowBounds());
	}
	
	public Integer getTotalProductoLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.getTotalProductoLessCodigoUbicacion",codigo);
	}
	
	public List<CheckForFlexigrid> listAllProductosLessCodigoUbicacion(String codigo) throws Exception {
		return getSqlSessionTemplate().selectList("producto.listAllProductosLessCodigoUbicacion",codigo);
	}
	
	public Integer isProductoExcuidoByIdProducto(Integer idProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("producto.isProductoExcuidoByIdProducto",idProducto);
	}
	
	public Integer isProductoInUbicacionInternaCD(FilterProducto filterProducto) throws Exception{
		return getSqlSessionTemplate().selectOne("producto.isProductoInUbicacionInternaCD",filterProducto);
	}
}
