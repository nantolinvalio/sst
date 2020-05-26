package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.filters.FilterGuiasPendientes;
import cl.abcdin.sst.model.filters.FilterOT;
import cl.abcdin.sst.model.filters.FilterRecepcionProducto;


public class BitacoraDAO extends BaseDAO {

	public List<Bitacora> listBitacorasByIdOT(Long idOt) throws Exception {
		return getSqlSessionTemplate().selectList("bitacora.listBitacorasByIdOT", idOt);
	}
	
	public List<Bitacora> listResumenBitacorasByIdOT(Long idOt) throws Exception {
		return getSqlSessionTemplate().selectList("bitacora.listResumenBitacorasByIdOT", idOt);
	}

	public Bitacora getByIdGuia(Long idGuia) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getByIdGuia", idGuia);
	}
	
	public Bitacora getBitacoraAccesorioByIdGuia(Long idGuia) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getBitacoraAccesorioByIdGuia", idGuia);
	}
	
	public Bitacora getByIdGuiaAgrupadaAndOT(FilterOT filterOT) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getByIdGuiaAgrupadaAndOT", filterOT);
	}
	
	public Integer deleteBitacoraMayoresByOT(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().delete("bitacora.deleteBitacoraMayoresByOT", bitacora);
	}
	
	public Integer deleteBitacoraAccesoriosMayoresByOT(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().delete("bitacora.deleteBitacoraAccesoriosMayoresByOT", bitacora);
	}
	
	public Integer updateFechaSalida (Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateFechaSalida", bitacora);	
	}
	
	public Integer updateFechaSalidaBitacoraAccesorio (Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateFechaSalidaBitacoraAccesorio", bitacora);	
	}
	
	public Integer save(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().insert("bitacora.save", bitacora);	
	}
	
	public Integer saveBitacoraAccesorios(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().insert("bitacora.saveBitacoraAccesorios", bitacora);	
	}
	
	public Integer updateAsignaBitacoraAGuia(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateAsignaBitacoraAGuia",bitacora);
	}
	
	public Integer updateAsignaBitacoraAGuiaAccesorio(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateAsignaBitacoraAGuiaAccesorio",bitacora);
	}

	public Integer saveBitacoraEnCrearOT(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().insert("bitacora.saveBitacoraEnCrearOT",bitacora);
	}

	public Integer deleteBitacoraByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().delete("bitacora.deleteBitacoraByOT",idOT);
	}
	
	public Integer deleteBitacoraByOTNoEstado(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().delete("bitacora.deleteBitacoraByOTNoEstado", bitacora);
	}
	
	public Bitacora getEstadoUltimaUbicacion(Recepcion recepcion) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getEstadoUltimaUbicacion", recepcion);
	}	
	
	public Integer updateBitacoraFechaEstado(Recepcion recepcion) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateBitacoraFechaEstado", recepcion);
	}		
	
	public Integer saveBitacoraEstadoSiguiente(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().insert("bitacora.saveBitacoraEstadoSiguiente", bitacora);
	}
	
	public Bitacora getUltimaBitacora(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getUltimaBitacora", idOT);
	}
	
	public Bitacora getUltimaBitacoraAccesorio(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getUltimaBitacoraAccesorio", idOT);
	}
	
	public Bitacora getUltimaBitacoraAbierta(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacora.getUltimaBitacoraAbierta", idOT);
	}
	
	public Integer saveBitacoraCerrarCliente(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().insert("bitacora.saveBitacoraCerrarCliente", bitacora);
	}
	
	public Integer updateBitacoraByOT(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateBitacoraByOT",bitacora);
	}
	
	public Integer updateBitacoraUnlinkGuia(FilterGuiasPendientes filterGuiasPendientes) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateBitacoraUnlinkGuia",filterGuiasPendientes);
	}
	
	public Bitacora getBitacoraByEnvioRecepcionMasiva(FilterRecepcionProducto filterRecepcionProducto) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getBitacoraByEnvioRecepcionMasiva",filterRecepcionProducto);
	}
	
	public Integer updateBitacoraEnvioRecepcionByBitacora(Bitacora bitacora) throws Exception {
		return getSqlSessionTemplate().update("bitacora.updateBitacoraEnvioRecepcionByBitacora",bitacora);
	}
	
	public Bitacora getBitacoraAndUbicacionByIdOT(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacora.getBitacoraAndUbicacionByIdOT",id);
	}
	public Integer updateEstadoBitacora(Bitacora bitacora) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacora.updateEstadoBitacora",bitacora);
	}
	
	public Integer updateEstadoUltimaBitacora(Bitacora bitacora) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacora.updateEstadoUltimaBitacora",bitacora);
	}
	public Bitacora getBitacoraUltimaUbicacionOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacora.getBitacoraUltimaUbicacionOT",idOT);
	}
	
	public Integer updateBitacoraAccesorio(Bitacora bitacora){
		return getSqlSessionTemplate().selectOne("bitacora.updateBitacoraAccesorio",bitacora);
	}
	public Long getUbicacionInicialOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("bitacora.getUbicacionInicialOT", idOT);
	}
}
