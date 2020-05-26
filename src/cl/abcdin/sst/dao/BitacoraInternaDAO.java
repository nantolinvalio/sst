package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Bitacora;
import cl.abcdin.sst.model.BitacoraInterna;

public class BitacoraInternaDAO extends BaseDAO{
	public void saveBitacoraInterna(BitacoraInterna bitacoraInterna) throws Exception {
		getSqlSessionTemplate().insert("bitacoraInterna.saveBitacoraInterna",bitacoraInterna);
	}
	
	public List<BitacoraInterna> listResumenBitacorasInternasByBitacora(Bitacora bitacora) throws Exception{
		return getSqlSessionTemplate().selectList("bitacoraInterna.listResumenBitacorasInternasByBitacora", bitacora);
	}
	
	public Integer cerrarBitacoraInternaByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().update("bitacoraInterna.cerrarBitacoraInternaByIdOT",idOT);
	}
	
	public Integer abrirBitacoraInternaByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().update("bitacoraInterna.abrirBitacoraInternaByIdOT",idOT);
	}
	
	public BitacoraInterna getBitacoraInternaById(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacoraInterna.getBitacoraInternaById",idOT);
	}
	
	public BitacoraInterna getUltimaBitacoraInternaByIdOT(Long idOT) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacoraInterna.getUltimaBitacoraInternaByIdOT",idOT);
	}
	
	public Integer updateNumeroItByIdBitacoraInterna(BitacoraInterna bitacoraInterna) throws Exception{
		return getSqlSessionTemplate().update("bitacoraInterna.updateNumeroItByIdBitacoraInterna",bitacoraInterna);
	}
	
	public BitacoraInterna getBitacoraInternaByBitacora(Long id) throws Exception{
		return getSqlSessionTemplate().selectOne("bitacoraInterna.getBitacoraInternaByBitacora",id);
	}
}
