package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.ReporteProveedor;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterProveedor;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class ProveedorDAO extends BaseDAO{

//	lista proveedores para las ubicaciones
	public List<Proveedor> listProveedoresByFilter(FilterProveedor filterProveedor, GridControl gridControl) throws Exception {
		return  getSqlSessionTemplate().selectList("proveedor.listProveedores", filterProveedor, gridControl.getRowBounds());
	}
	
	public List<Proveedor> listProveedores() throws Exception {
		return  getSqlSessionTemplate().selectList("proveedor.listProveedores");
	}
	
	public Proveedor getProveedorByOt(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getProveedorByOt", idOT);
	}
	
	public Ubicacion getProveedorById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getProveedorById", id);
	}
	
	public Ubicacion getProveedoresById(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getProveedoresById", filterUbicacion);
	}
	
	public List<CheckForFlexigrid> listAllProveedoresCheck(FilterProveedor filterProveedor) throws Exception {
		return getSqlSessionTemplate().selectList("proveedor.listAllProveedoresCheck",filterProveedor);
	}
	
	public Integer getTotalProveedoresByFilter(FilterProveedor filterUbicacion) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getTotalProveedoresByFilter", filterUbicacion);
	}
	
	public List<ReporteProveedor> listProveedorReportByFilter(FilterOT filter,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("proveedor.listProveedorReportByFilter", filter,gridControl.getRowBounds());
	}
	
	public List<ReporteProveedor> listProveedorReportByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("proveedor.listProveedorReportByFilter", filter);
	}
	
	public Integer getTotalProveedorReportByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getTotalProveedorReportByFilter", filter);
	}
	
	public List<ReporteProveedor> listReporteFallaByFilter(FilterOT filter,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("proveedor.listReporteFallaByFilter", filter,gridControl.getRowBounds());
	}
	
	public List<ReporteProveedor> listReporteFallaByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("proveedor.listReporteFallaByFilter", filter);
	}
	
	public Integer getTotalReporteFallaByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("proveedor.getTotalReporteFallaByFilter", filter);
	}
}
