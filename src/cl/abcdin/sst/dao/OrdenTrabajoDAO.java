package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.DespachoInterno;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.PasosOT;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.RecepcionCamionOT;
import cl.abcdin.sst.model.Resultado;
import cl.abcdin.sst.model.filters.FilterDespachoInterno;
import cl.abcdin.sst.model.filters.FilterDespachoInternoDetalle;
import cl.abcdin.sst.model.filters.FilterHisotrial;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterRecepcion;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.vo.CheckForFlexigrid;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;

public class OrdenTrabajoDAO extends BaseDAO {

	public List<OrdenTrabajo> listByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajo.listByFilter", filterOT, gridControl.getRowBounds());
	}

	public List<OrdenTrabajo> listByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajo.listByFilter", filterOT);
	}
	
	public List<OrdenTrabajo> listOTDescripcionFallas(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajo.listOTDescripcionFallas", filterOT, gridControl.getRowBounds());
	}
	
	public Integer getTotalOTDescripcionFallas(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getTotalOTDescripcionFallas", filterOT);
	}

	public OrdenTrabajo getOTById(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOTById", idOT);
	}
	
	public OrdenTrabajo getClasificacionOTById(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getClasificacionOTById", idOT);
	}
	
	public Integer getTotalByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getTotalByFilter", filterOT);
	}
	
	public OrdenTrabajo getEstadoOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getEstadoOT", idOT);
	}
	
	//Falla reiterada
	public String getEsFallaReiterada(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getEsFallaReiterada", idOT);
	}
	
	public Integer updateEstadoOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateEstadoOT", ordenTrabajo);
	}
	
	public Integer updateSTecnicoOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateSTecnicoOT", ordenTrabajo);
	}
	
	public Integer updateCerrarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateCerrarOT", ordenTrabajo);
	}
	
	public Integer updateDesactivarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateDesactivarOT", ordenTrabajo);
	}
	
	public PasosOT getPasosOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getPasosOT", idOT);
	}
	
	public OrdenTrabajo getAntesFacturarByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getAntesFacturarByOT", idOT);
	}
	
	public Integer updateAntesEnviarOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateAntesEnviarOT", ordenTrabajo);
	}
	
	public Integer updateNumeroCargo(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateNumeroCargo", ordenTrabajo);
	}
	
	public Integer existeOTAntesEnviar(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.existeOTAntesEnviar",idOT);
	}

	public Resultado asignarOTEjecutiva(Long idOT, Long idUsuario, Long idEjecutiva){
		Resultado resultado = new Resultado();
		return resultado;
	}
	//--report orden trabajo--//
	public OrdenTrabajoGeneral getOTGeneralByOT(Long idOT){
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getOTGeneralByOT", idOT);		
	}
	
	//End--report orden trabajo--//
	
	public List<OrdenTrabajo> listHistorialOT(FilterHisotrial filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajo.listHistorialOT", filter);
	}
	
	public Integer listTotalOTbyidOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.listTotalOTbyidOT", idOT);
	}
	
	public Integer updateDescripcionFallaByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateDescripcionFallaByFilter", filter);
	}
	
	public Integer updateDesactivarOTByFilter(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateDesactivarOTByFilter", filter);
	}
	
	public Integer updateOrdenTrabajoMoveToHoja(OrdenTrabajo orden) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOrdenTrabajoMoveToHoja", orden);
	}
	
	public Integer updateUbicacionSTecnico(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateUbicacionSTecnico", ot);
	}
	
	public Integer updateEjecutiva(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateEjecutiva", ot);
	}
	
	public Integer updateUbicacionSucursal(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateUbicacionSucursal", ot);
	}
	
	public Integer updateOTbyOT(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTbyOT", oT);
	}
	
	public Integer exsiteAntesCrear(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.exsiteAntesCrear",filter);
	}
	
	public Integer existeNumeroAtencion(Long numeroAtencion)throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.existeNumeroAtencion",numeroAtencion);
	}
	
	public Integer existeNumeroCambio(Long numeroCambio) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.existeNumeroCambio",numeroCambio);
	}
	
	public  Integer saveOT(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().insert("ordenTrabajo.saveOT",oT);
	}
	
	public Integer updateDestinoOT(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateDestinoOT",oT);
	}
	
	public Integer updateOTGMYNumeroCambio(Long idOT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTGMYNumeroCambio",idOT);
	}
	
	public Integer updateIdSTecnico(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.updateIdSTecnico",oT);
	}
	
	public Integer updateIdSucursal(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.updateIdSucursal",oT);
	}
	
	public Integer updateOTCambio(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.updateOTCambio",oT);
	}
	
	public Integer existeBitacora(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.existeBitacora",id);
	}

	public List<OrdenTrabajo> listOTEnTransito(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTEnTransito",filter);
	}
	
	public List<OrdenTrabajo> listOTEnTransito(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTEnTransito",filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalOTEnTransito(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTEnTransito",filter);
	}
	
	public List<OrdenTrabajo> listOTPendientesEnSucursal(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendientesEnSucursal",filter);
	}
	
	public List<OrdenTrabajo> listOTPendientesEnSucursal(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendientesEnSucursal",filter, gridControl.getRowBounds());
	}
	
	public Integer getTotalOTPendientesEnSucursal(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTPendientesEnSucursal",filter);
	}
	
	public Integer getTotalOTAbiertasSucursal(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTAbiertasSucursal",filter);
	}
	
	public Integer getTotalOTPendienteAccesorios(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTPendienteAccesorios",filter);
	}
	
	public Integer getTotalOTPendienteGuia(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTPendienteGuia",filter);
	}
	
	public Integer getTotalOTPendienteEntregaCliente(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTPendienteEntregaCliente",filter);
	}
	
	public Integer getTotalOTEnviadasSTLSinContrato(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTEnviadasSTLSinContrato",filter);
	}
	
	public Integer getTotalOTAutorizadaCambio(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTAutorizadaCambio",filter);
	}
	
	public List<OrdenTrabajo> listOTAbiertasSucursal(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTAbiertasSucursal",filter);
	}
	
	public List<OrdenTrabajo> listOTPendienteAccesorios(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteAccesorios",filter);
	}
	
	public List<OrdenTrabajo> listOTPendienteGuia(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteGuia",filter);
	}
	
	public List<OrdenTrabajo> listOTPendienteEntregaCliente(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteEntregaCliente",filter);
	}
	
	public List<OrdenTrabajo> listOTEnviadasSTLSinContrato(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTEnviadasSTLSinContrato",filter);
	}
	
	public List<OrdenTrabajo> listOTAutorizadaCambio(FilterOT filter) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTAutorizadaCambio",filter);
	}

	public List<OrdenTrabajo> listOTAbiertasSucursal(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTAbiertasSucursal",filter,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOTPendienteAccesorios(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteAccesorios",filter,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOTPendienteGuia(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteGuia",filter,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOTPendienteEntregaCliente(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTPendienteEntregaCliente",filter,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOTEnviadasSTLSinContrato(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTEnviadasSTLSinContrato",filter,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOTAutorizadaCambio(FilterOT filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTAutorizadaCambio",filter,gridControl.getRowBounds());
	}

	public Integer updateOTRevision(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTRevision",oT);
	}
	
	public Integer updateOTBTNOrigen(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTBTNOrigen",oT);
	}
	
	public Integer updateTipoXN(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateTipoXN",oT);
	}
	
	public Integer updateCCXN(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateCCXN",oT);
	}

	public Integer updateOTEnvioRetiro(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTEnvioRetiro",oT);
	}
	
	public String getContratoOT(Recepcion recepcion) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.getContratoOT", recepcion);		
	}
	
	public Integer updateOTRecepcion(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTRecepcion", ot);		
	}
	
	public Integer updateOTTareaUrgente(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTTareaUrgente", ot);		
	}
	
	public Integer updateOTTareaUrgenteFlagDown(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTTareaUrgenteFlagDown", ot);		
	}
	
	public Integer updateOTTareaUrgenteFF(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateOTTareaUrgenteFF", ot);
	}
	
	public Integer updateCambioAutorizado(OrdenTrabajo ot) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateCambioAutorizado", ot);		
	}	

	public List<OrdenTrabajo> listOTCambioAuomaticoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTCambioAuomaticoByFilter", filterOT, gridControl.getRowBounds());		
	}

	public List<OrdenTrabajo> listOTCambioAuomaticoByFilter(FilterOT filterOt) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTCambioAuomaticoByFilter", filterOt);		
	}
	
	public Integer getTotalOTCambioAuomaticoByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTCambioAuomaticoByFilter", filterOT);		
	}
	
	public List<OrdenTrabajo> listOTbyNumeroSerie(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTbyNumeroSerie", filterOT);
	}
	
	public Integer updateIdLogistico(OrdenTrabajo oT)throws Exception{
		return getSqlSessionTemplate().update("ordenTrabajo.updateIdLogistico",oT);
	}
	
	public Integer updateTicketCambio(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateTicketCambio",oT);
	}
	
	public Integer updateNotaCredito(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateNotaCredito",oT);
	}
	
	public Integer updateEnviarRemateByIdOT(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateEnviarRemateByIdOT",oT);
	}
	
	public Integer updateCambioGMaster(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateCambioGMaster",oT);
	}
	
	public List<RecepcionCamionOT> listOTGuiaAgrupadaByIdGuia(Long idGuia) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTGuiaAgrupadaByIdGuia",idGuia);
	}
	
	public List<RecepcionCamionOT> listOTGuiaAgrupadaByIdRecepcionGuia(Long idRecepcionGuia) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTGuiaAgrupadaByIdRecepcionGuia",idRecepcionGuia);
	}
	
	public OrdenTrabajo getOtByIdOrNumeroAtencion(FilterOT filter){
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtByIdOrNumeroAtencion",filter);
	}
	
	public Integer getTotalNumeroCambio(Long numeroCambio){
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalNumeroCambio",numeroCambio);
	}
	
	public List<OrdenTrabajo> listOTbyFilterRecepcion(FilterRecepcion filterRecepcion) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTbyFilterRecepcion",filterRecepcion);
	}
	
	public void updateRollbackEstadoGuia(OrdenTrabajo ordenTrabajo) throws Exception {
		getSqlSessionTemplate().update("ordenTrabajo.updateRollbackEstadoGuia",ordenTrabajo);
	}
	
	public Integer updateClienteByOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateClienteByOT",ordenTrabajo);
	}
	
	public RecepcionCamionOT getOTGuiaAgrupadaByRecepcionCamionOT(RecepcionCamionOT recepcionCamionOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOTGuiaAgrupadaByRecepcionCamionOT",recepcionCamionOT);
	}
	
	public List<OrdenTrabajo> listOTRecepcionBodegaByFilter(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTRecepcionBodegaByFilter",filterRecepcionProducto);
	}
	
	public List<OrdenTrabajo> listOTRecepcionBodegaByFilter(FilterRecepcionProducto filterRecepcionProducto, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTRecepcionBodegaByFilter",filterRecepcionProducto, gridControl.getRowBounds());
	}
	
	public Integer getTotalOTRecepcionBodegaByFilter(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTRecepcionBodegaByFilter",filterRecepcionProducto);
	}
	
	public OrdenTrabajo updateOTControlCalidadByOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajo.updateOTControlCalidadByOT",ordenTrabajo);
	}
	
	public List<OrdenTrabajo> listAllOTRemate(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTRemate",gridControl);
	}
	
	public List<OrdenTrabajo> listOTRemate(GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTRemate",gridControl, gridControl.getRowBounds());
	}
	
	public Integer getTotalOTRemate() throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTRemate");
	}
	
	public List<OrdenTrabajo> listOtTrasladoToFFByFilter(FilterOT filterOT, GridControl gridControl)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtTrasladoToFFByFilter",filterOT,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtTrasladoToFFByFilter(FilterOT filterOT)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtTrasladoToFFByFilter",filterOT);
	}
	
	public Integer getOtTrasladoToFFByFilter(FilterOT filterOT)throws Exception{
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtTrasladoToFFByFilter",filterOT);
	}
	
	public List<OrdenTrabajo> listOTByGuiaRemate(Long idGuiaRemate) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTByGuiaRemate",idGuiaRemate);
	}
	
	public List<CheckForFlexigrid> listAllCheck(FilterOT filterOT) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listAllCheck", filterOT);
	}
	
	public OrdenTrabajo getOTbyCodigoAccesorio(String codigoBarra) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOTbyCodigoAccesorio",codigoBarra);
	}
	
	public List<OrdenTrabajo> listOtByDespacho(FilterDespachoInterno filterDespachoInterno,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespacho",filterDespachoInterno,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtEscaneadasByDespacho(DespachoInterno despacho) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtEscaneadasByDespacho",despacho);
	}
	
	public List<OrdenTrabajo> listOtByDespachoToSP(FilterDespachoInterno filterDespachoInterno,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespachoToSP",filterDespachoInterno,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtByDespachoToSP(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespachoToSP",filterDespachoInterno);
	}
	
	
	public Integer getTotalOtByDespachoToSP(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOtByDespachoToSP",filterDespachoInterno);
	}

	public List<OrdenTrabajo> listOtByDespacho(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespacho",filterDespachoInterno);
	}
	
	public Integer getOtByDespacho(FilterDespachoInterno filterDespachoInterno) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtByDespacho",filterDespachoInterno);
	}
	
	public List<CheckForFlexigrid> listOtByDespachoCheck(DespachoInterno despachoInterno) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespachoCheck", despachoInterno);
	}
	
	public List<OrdenTrabajo> listOtParaDespachoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtParaDespachoByFilter",filterOT, gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtParaDespachoByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtParaDespachoByFilter",filterOT);
	}
	
	public Integer getTotalOtParaDespachoByFilter(FilterOT filterOT)throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOtParaDespachoByFilter",filterOT);
	}
	
	public List<CheckForFlexigrid> listAllOtParaDespachoCheck(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listAllOtParaDespachoCheck",filterOT);
	}
	
	public List<OrdenTrabajo> listOTRecepcionByDespachoInterno(DespachoInterno despacho, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTRecepcionByDespachoInterno",despacho,gridControl.getRowBounds());
	}
	
	public Integer getTotaltOTRecepcionByDespachoInterno(DespachoInterno despacho) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTRecepcionByDespachoInterno",despacho);
	}
	
	public OrdenTrabajo getOTRecebirDespachoSPByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOTRecebirDespachoSPByFilter",filterOT);
	}
	
	public Integer updateRevisionSP(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateRevisionSP",oT);
	}
	
	public Integer updateObservacionOT(OrdenTrabajo oT) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateObservacionOT",oT);
	}
	
	public List<OrdenTrabajo> listOtInFFTrasladoByFilter(FilterOT filterOT, GridControl gridControl)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtInFFTrasladoByFilter",filterOT,gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtInFFTrasladoByFilter(FilterOT filterOT)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtInFFTrasladoByFilter",filterOT);
	}
	
	public Integer getOtInFFTrasladoByFilter(FilterOT filterOT)throws Exception{
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtInFFTrasladoByFilter",filterOT);
	}
	public List<CheckForFlexigrid> listALLOtInFFTrasladoCheck(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listALLOtInFFTrasladoCheck",filterOT);
	}
	public List<OrdenTrabajo> listOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByIdGuiaAndClasificacionAvOrDa",filterOT, gridControl.getRowBounds());
	}
	public Integer getOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtByIdGuiaAndClasificacionAvOrDa",filterOT);
	}
	
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionAvOrDa(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listAllOtByIdGuiaAndClasificacionAvOrDa",filterOT);
	}
	
	public List<OrdenTrabajo> listOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT,GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByIdGuiaAndClasificacionCpOrRp",filterOT,gridControl.getRowBounds());
	}
	public Integer getOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtByIdGuiaAndClasificacionCpOrRp",filterOT);
	}
	
	public List<OrdenTrabajo> listOtByIdGuia(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByIdGuia",filterOT, gridControl.getRowBounds());
	}
	public Integer getOtByIdGuia(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getOtByIdGuia",filterOT);
	}
	
	public List<CheckForFlexigrid> listAllOtByIdGuiaAndClasificacionCpOrRp(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listAllOtByIdGuiaAndClasificacionCpOrRp",filterOT);
	}
	
	public List<OrdenTrabajo> listOTByIdGuiaAgrupada(Long idGuia) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTByIdGuiaAgrupada",idGuia);
	}
	
	public Integer updateExcluirCCalidad(OrdenTrabajo ordenTrabajo) throws Exception{
		return getSqlSessionTemplate().update("ordenTrabajo.updateExcluirCCalidad",ordenTrabajo);
	}
	
	public List<OrdenTrabajo> listOTByUbicacionInterna(Integer idUbicacionInterna) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTByUbicacionInterna",idUbicacionInterna);
	}
	
	public List<OrdenTrabajo> listOTToEnvioSucursal(Integer idSucursal) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTToEnvioSucursal",idSucursal);
	}
	
	public List<OrdenTrabajo> listOTToCambioEstadoByFilter(FilterOT filterOT, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTToCambioEstadoByFilter",filterOT,gridControl.getRowBounds());
	}
	
	public Integer getTotalOTToCambioEstadoByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTToCambioEstadoByFilter",filterOT);
	}
	
	public List<CheckForFlexigrid> ListAllCheckOTToCambioEstadoByFilter(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.ListAllCheckOTToCambioEstadoByFilter",filterOT);
	}
	
	public OrdenTrabajo getEstadosOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getEstadosOT",idOT);
	}
	
	public Integer modificarEstadoOT(OrdenTrabajo ordenTrabajo) throws Exception{
		return getSqlSessionTemplate().update("ordenTrabajo.modificarEstadoOT",ordenTrabajo);
	}
	
	public List<OrdenTrabajo> listOtByDespachoAndEstado(FilterDespachoInternoDetalle filterdespacho) throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOtByDespachoAndEstado",filterdespacho);
	}
	
	public List<OrdenTrabajo> listOTListasParaDespachoSucursal(FilterOT filterOT)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTListasParaDespachoSucursal",filterOT);
	}
	
	public List<OrdenTrabajo> listOTListasParaDespachoSucursal(FilterOT filterOT,GridControl gridControl)throws Exception{
		return getSqlSessionTemplate().selectList("ordenTrabajoSelect.listOTListasParaDespachoSucursal",filterOT,gridControl.getRowBounds());
	}
	
	public Integer getTotalOTListasParaDespachoSucursal() throws Exception {
		return getSqlSessionTemplate().selectOne("ordenTrabajoSelect.getTotalOTListasParaDespachoSucursal");
	}
	
	public Integer updateModificado(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateModificado",ordenTrabajo);
	}
	
	public Integer updateRecuperacionForOT(OrdenTrabajo ordenTrabajo) throws Exception {
		return getSqlSessionTemplate().update("ordenTrabajo.updateRecuperacionForOT",ordenTrabajo);
	}
}
