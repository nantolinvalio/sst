package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Indicador;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Resultado;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.filters.FilterIndicador;
import cl.abcdin.sst.model.filters.GridControl;
import cl.abcdin.sst.model.filters.SemaforoFilter;

public class SemaforoDAO extends BaseDAO {


	public Resultado calcularSemaforo(Usuario usuario) throws Exception{
		Resultado resultado = new Resultado();
		resultado.setIdUsuario(usuario.getId());
		getSqlSessionTemplate().selectOne("indicador.calcularSemaforo", resultado);
		return resultado;		
	}	

	public Resultado actualizarVista(String vista) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setParametro1(vista);
		getSqlSessionTemplate().selectOne("indicador.actualizarVista", resultado);
		return resultado;		
	}
	
	public Resultado calcularIndicadores(Usuario usuario) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setIdUsuario(usuario.getId());
		getSqlSessionTemplate().selectOne("indicador.calcularIndicadores", resultado);
		return resultado;		
	}
	
	public Resultado procesoBodega(Usuario usuario) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setIdUsuario(usuario.getId());
		getSqlSessionTemplate().selectOne("indicador.procesoBodega", resultado);
		return resultado;		
	}
	
	public Resultado procesoProveedores(Usuario usuario) throws Exception {
		Resultado resultado = new Resultado();
		resultado.setIdUsuario(usuario.getId());
		getSqlSessionTemplate().selectOne("indicador.procesoProveedores", resultado);
		return resultado;		
	}
	
	public Double getNivelServicioOTNOPorCD(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicador.getNivelServicioOTNOPorCD",filterIndicador);
	}
	
	public Double getNivelServicioOTPorCD(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectOne("indicador.getNivelServicioOTPorCD",filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTNivelServicioOTPorCD(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicador.listOTNivelServicioOTPorCD",filterIndicador);
	}
	
	public List<OrdenTrabajo> listOTNivelServicioOTNOPorCD(FilterIndicador filterIndicador) throws Exception {
		return getSqlSessionTemplate().selectList("indicador.listOTNivelServicioOTNOPorCD",filterIndicador);
	}

	public List<OrdenTrabajo> listSemaforo(SemaforoFilter semaforoFilter){
		return getSqlSessionTemplate().selectList("semaforo.listSemaforo", semaforoFilter);
	}
	
	public Integer updateSemaforoPrioridadSucIni(OrdenTrabajo orden) throws Exception {
		return getSqlSessionTemplate().update("semaforo.updateSemaforoPrioridadSucIni", orden);
	}
	
	public Integer updateSemaforoPrioridadSucFin(OrdenTrabajo orden) throws Exception {
		return getSqlSessionTemplate().update("semaforo.updateSemaforoPrioridadSucFin", orden);
	}
	
	public Integer updateSemaforoPrioridadST(OrdenTrabajo orden) throws Exception {
		return getSqlSessionTemplate().update("semaforo.updateSemaforoPrioridadST", orden);
	}

	public Long getOtNotDespachadasFromSucursal(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtNotDespachadasFromSucursal",indicador);
	}
	
	public Long getOtNotRecibidasInBodega(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtNotRecibidasInBodega",indicador);
	}
	
	public Long getGuiasEmitidasToTransportistas(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getGuiasEmitidasToTransportistas",indicador);
	}
	
	public Long getOtPendientesWithRecepcionFaltante(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtPendientesWithRecepcionFaltante",indicador);
	}
	
	public Long getOtPendientesWithRecepcionIncompleta(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtPendientesWithRecepcionIncompleta",indicador);
	}
	
	public Long getOtRecibidasFFAndNotRevisadasInSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtRecibidasFFAndNotRevisadasInSP",indicador);
	}
	
	public Long getOtNotRecibidasInSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtNotRecibidasInSP",indicador);
	}
	
	public Long getOtWithClasificasionCPAndNotDesapachaFromSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtWithClasificasionCPAndNotDesapachaFromSP",indicador);
	}
	
	public Long getOtWithClasificasionRPAndNotDesapachaFromSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtWithClasificasionRPAndNotDesapachaFromSP",indicador);
	}
	
	public Long getOtWithClasificasionDAAndNotDesapachaFromSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtWithClasificasionDAAndNotDesapachaFromSP",indicador);
	}
	
	public Long getOtWithClasificasionAVAndNotDesapachaFromSP(Indicador indicador) throws Exception {
		return getSqlSessionTemplate().selectOne("semaforo.getOtWithClasificasionAVAndNotDesapachaFromSP",indicador);
	}
	
	public List<OrdenTrabajo> listOtOtNotDespachadasFromSucursal(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtOtNotDespachadasFromSucursal",filter, gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtOtNotDespachadasFromSucursal(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtOtNotDespachadasFromSucursal",filter);
	}

	public List<OrdenTrabajo> listOtNotRecibidasInBodega(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtNotRecibidasInBodega",filter, gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtNotRecibidasInBodega(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtNotRecibidasInBodega",filter);
	}
	
	public List<Guia> listGuiasEmitidasToTransportistas(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listGuiasEmitidasToTransportistas",filter, gridControl.getRowBounds());
	}
	
	public List<Guia> listGuiasEmitidasToTransportistas(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listGuiasEmitidasToTransportistas",filter);
	}
	
	public List<OrdenTrabajo> listOtPendientesWithRecepcionFaltante(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtPendientesWithRecepcionFaltante",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> listOtPendientesWithRecepcionFaltante(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtPendientesWithRecepcionFaltante",filter);
	}
	
	public List<OrdenTrabajo> listOtPendientesWithRecepcionIncompleta(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtPendientesWithRecepcionIncompleta",filter, gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtPendientesWithRecepcionIncompleta(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtPendientesWithRecepcionIncompleta",filter);
	}
	
	public List<OrdenTrabajo> ListOtRecibidasFFAndNotRevisadasInSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.ListOtRecibidasFFAndNotRevisadasInSP",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> ListOtRecibidasFFAndNotRevisadasInSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.ListOtRecibidasFFAndNotRevisadasInSP",filter);
	}
	
	
	public List<OrdenTrabajo> listOtNotRecibidasInSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtNotRecibidasInSP",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> listOtNotRecibidasInSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtNotRecibidasInSP",filter);
	}
	
	public List<OrdenTrabajo> listOtWithClasificasionCPAndNotDesapachaFromSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionCPAndNotDesapachaFromSP",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> listOtWithClasificasionCPAndNotDesapachaFromSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionCPAndNotDesapachaFromSP",filter);
	}
	
	public List<OrdenTrabajo> listOtWithClasificasionRPAndNotDesapachaFromSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionRPAndNotDesapachaFromSP",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> listOtWithClasificasionRPAndNotDesapachaFromSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionRPAndNotDesapachaFromSP",filter);
	}
	
	public List<OrdenTrabajo> listOtWithClasificasionDAAndNotDesapachaFromSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionDAAndNotDesapachaFromSP",filter, gridControl.getRowBounds());
	}
	public List<OrdenTrabajo> listOtWithClasificasionDAAndNotDesapachaFromSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionDAAndNotDesapachaFromSP",filter);
	}
	
	public List<OrdenTrabajo> listOtWithClasificasionAVAndNotDesapachaFromSP(FilterIndicador filter, GridControl gridControl) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionAVAndNotDesapachaFromSP",filter, gridControl.getRowBounds());
	}
	
	public List<OrdenTrabajo> listOtWithClasificasionAVAndNotDesapachaFromSP(FilterIndicador filter) throws Exception {
		return getSqlSessionTemplate().selectList("semaforo.listOtWithClasificasionAVAndNotDesapachaFromSP",filter);
	}
//	public Long getOtEnvioToServicioTecnicoSinRecepcion(Indicador indicador) throws Exception {
//		return getSqlSessionTemplate().selectOne("semaforo.getOtEnvioToServicioTecnicoSinRecepcion",indicador);
//	}
}
