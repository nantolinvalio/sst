package cl.abcdin.sst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.DespachoInternoDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.ProveedorDAO;
import cl.abcdin.sst.dao.RecepcionDAO;
import cl.abcdin.sst.dao.ServicioTecnicoDAO;
import cl.abcdin.sst.dao.TipoFallasDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.model.ListRange;
import cl.abcdin.sst.model.ReporteProveedor;
import cl.abcdin.sst.model.ServicioTecnico;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.filters.FilterServicioTecnico;
import cl.abcdin.sst.model.filters.FilterUbicacion;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;

public class BuscadoresService  {
	
	private RecepcionDAO recepcionDAO;
	private OrdenTrabajoDAO ordenTrabajoDAO;
	private ServicioTecnicoDAO servicioTecnicoDAO;
	private DespachoInternoDAO despachoInternoDAO;
	private UbicacionDAO ubicacionDAO;
	private ProveedorDAO proveedorDAO;
	private TipoFallasDAO tipoFallasDAO;
	
	private static final Log log = LogFactory.getLog(BuscadoresService.class);
	
	public ListRange listOTByRecepcionesFilter(FilterRecepcion filterRecepcion, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterRecepcion.setOrderBy(gridControl.getOrderBy());
			filterRecepcion.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(recepcionDAO.listOTByRecepcionesFilter(filterRecepcion, gridControl));
			listRange.setTotal(recepcionDAO.getTotalOTByRecepcionesFilter(filterRecepcion));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOTRemate(GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			listRange.setRows(ordenTrabajoDAO.listOTRemate(gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOTRemate());
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public List<ServicioTecnico> ListServiciosTecnicosBuscadorOT(FilterServicioTecnico filterServicioTecnico) throws Exception {
		try {
			return servicioTecnicoDAO.ListServiciosTecnicosBuscadorOT(filterServicioTecnico);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOtTrasladoToFFByFilter(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOtTrasladoToFFByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getOtTrasladoToFFByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	
	public ListRange listDespachosByFilter(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(despachoInternoDAO.listDespachosToFallaFabricacionByFilter(filterOT, gridControl));
			listRange.setTotal(despachoInternoDAO.getDespachosToFallaFabricacionByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listDespachosDesdeFFByFilter(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(despachoInternoDAO.listDespachosDesdeFFByFilter(filterOT, gridControl));
			listRange.setTotal(despachoInternoDAO.getDespachosDesdeFFByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public ListRange listDespachosToFallaFabricacionByFilter(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(despachoInternoDAO.listDespachosToFallaFabricacionByFilter(filterOT, gridControl));
			listRange.setTotal(despachoInternoDAO.getDespachosToFallaFabricacionByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	
	public List<CheckForFlexigrid> listAllCheck(FilterOT filterOT)throws Exception{
		try {
			 List<CheckForFlexigrid> lst = new ArrayList<CheckForFlexigrid>();
			lst = ordenTrabajoDAO.listAllCheck(filterOT);
			return lst;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
//	public List<CheckForFlexigrid> listAllBitacorasCheck(FilterBitacoraInterna filterBitacoraInterna) throws Exception {
//		try {
//			List<CheckForFlexigrid> lst = new ArrayList<CheckForFlexigrid>();
//			lst = bitacoraInternaDAO.listAllBitacorasCheck(filterBitacoraInterna);
//			return lst;
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	public List<CheckForFlexigrid> listAllOtParaDespachoCheck(FilterOT filterOT) throws Exception {
		try {
			return ordenTrabajoDAO.listAllOtParaDespachoCheck(filterOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange ListRevisionesByFilter(FilterDespachoInterno filterDespachoInterno, Ubicacion ubicacion, GridControl gridControl) throws Exception {
		try {
			filterDespachoInterno.setIdOrigen(ubicacion.getId());
			ListRange listRange = new ListRange();
			if(gridControl.getOrderBy()==null || gridControl.getOrderBy().equals("")){
				gridControl.setOrderBy("di.d_fecha_creacion");
			}
			filterDespachoInterno.setOrderBy(gridControl.getOrderBy());
			filterDespachoInterno.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(despachoInternoDAO.ListRevisionesByFilter(filterDespachoInterno, gridControl));
			listRange.setTotal(despachoInternoDAO.getTotalRevisionesByFilter(filterDespachoInterno));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
//	public ListRange listBitacorasInternasByFilter(FilterBitacoraInterna filterBitacoraInterna, GridControl gridControl) throws Exception {
//		try {
//			ListRange listRange = new ListRange();
//			listRange.setRows(bitacoraInternaDAO.listBitacorasInternasByFilter(filterBitacoraInterna, gridControl));
//			listRange.setTotal(bitacoraInternaDAO.getTotalBitacorasInternasByFilter(filterBitacoraInterna));
//			return listRange;
//		} catch (Exception e) {
//			log.error(e,e);
//			throw e;
//		}
//	}
	
	public ListRange listOtParaDespachoByFilter(FilterOT filterOT, GridControl gridControl, Ubicacion ubicacion) throws Exception {
		try {
			filterOT.setIdUbicacion(ubicacion.getId());
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOtParaDespachoByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getTotalOtParaDespachoByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listOtInFFTrasladoByFilter(FilterOT filterOT,GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ordenTrabajoDAO.listOtInFFTrasladoByFilter(filterOT, gridControl));
			listRange.setTotal(ordenTrabajoDAO.getOtInFFTrasladoByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	public List<CheckForFlexigrid> listALLOtInFFTrasladoCheck(FilterOT filterOT) throws Exception {
		try {
			return ordenTrabajoDAO.listALLOtInFFTrasladoCheck(filterOT);
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listUbicacionByFilter(FilterUbicacion filterUbicacion, GridControl gridControl) throws Exception {
		try {
			ListRange listRange = new ListRange();
			filterUbicacion.setOrderBy(gridControl.getOrderBy());
			filterUbicacion.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(ubicacionDAO.listUbicacionByFilter(filterUbicacion, gridControl));
			listRange.setTotal(ubicacionDAO.getTotalUbicacionByFilter(filterUbicacion));
			return listRange;
		} catch (Exception e) {
			log.error(e,e);
			throw e;
		}
	}
	
	public ListRange listProveedorReportByfilter(FilterOT filterOT, GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(proveedorDAO.listProveedorReportByFilter(filterOT, gridControl));
			listRange.setTotal(proveedorDAO.getTotalProveedorReportByFilter(filterOT));
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}

	public ListRange listReporteFallaByBilter(FilterOT filterOT, GridControl gridControl)throws Exception{
		try {
			ListRange listRange = new ListRange();
			filterOT.setOrderBy(gridControl.getOrderBy());
			filterOT.setSortOrder(gridControl.getSortOrder());
			listRange.setRows(proveedorDAO.listReporteFallaByFilter(filterOT, gridControl));
			listRange.setTotal(proveedorDAO.getTotalReporteFallaByFilter(filterOT));
			for (Object obj : listRange.getRows()) {
				List<TipoFallas> fallas = tipoFallasDAO.listTipoFallasByOT(((ReporteProveedor)obj).getOrdenTrabajo().getId());
				StringBuilder sb = new StringBuilder();
				for (TipoFallas falla : fallas) {
					sb.append(falla.getDescripcion() + ", ");					
				}
				((ReporteProveedor)obj).setFallas(sb.toString());
			}
			return listRange;
		} catch (Exception e) {
			log.error(e , e);
			throw e;
		}
	}
	
	/*setters de los DAO*/
	public void setRecepcionDAO(RecepcionDAO recepcionDAO) {
		this.recepcionDAO = recepcionDAO;
	}

	public void setOrdenTrabajoDAO(OrdenTrabajoDAO ordenTrabajoDAO) {
		this.ordenTrabajoDAO = ordenTrabajoDAO;
	}
	
	public void setServicioTecnicoDAO(ServicioTecnicoDAO servicioTecnicoDAO){
		this.servicioTecnicoDAO = servicioTecnicoDAO;
	}
	
	public void setDespachoInternoDAO(DespachoInternoDAO despachoInternoDAO) {
		this.despachoInternoDAO = despachoInternoDAO;
	}
	
	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}
	
	public void setProveedorDAO(ProveedorDAO proveedorDAO){
		this.proveedorDAO = proveedorDAO;
	}
	
	public void setTipoFallasDAO(TipoFallasDAO tipoFallasDAO){
		this.tipoFallasDAO = tipoFallasDAO;
	}
}
