package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.PeticionDocumento;
import cl.abcdin.sst.model.filters.FilterOT;


public class PeticionDocumentoDAO extends BaseDAO{

	public Integer save(PeticionDocumento peticionDocumento) throws Exception {
		return getSqlSessionTemplate().insert("peticionDocumento.save", peticionDocumento);
	}
	
	public Integer update(PeticionDocumento peticionDocumento) throws Exception{
		return getSqlSessionTemplate().update("peticionDocumento.update", peticionDocumento);
	}
	
	public List<PeticionDocumento> listPeticionDocumentoByDespachoIterno(FilterOT filter) throws Exception{
		return getSqlSessionTemplate().selectList("peticionDocumento.listPeticionDocumentoByDespachoIterno", filter);
	}
	
	public Integer getPeticionDocumentoByDespachoIterno(FilterOT filter) throws Exception{
		return getSqlSessionTemplate().selectOne("peticionDocumento.getPeticionDocumentoByDespachoIterno", filter);
	}
	
}
