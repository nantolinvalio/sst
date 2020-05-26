package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.PeticionDocumento;
import cl.abcdin.sst.model.PeticionDocumentoDetalle;


public class PeticionDocumentoDetalleDAO extends BaseDAO{

	public Integer save(PeticionDocumentoDetalle peticiondetalle) throws Exception {
		return getSqlSessionTemplate().insert("peticionDocumentoDetalle.save",peticiondetalle);
	}
	
	public List<PeticionDocumentoDetalle> getForFacturacion(PeticionDocumento peticionDocumento) throws Exception {
		return getSqlSessionTemplate().selectList("peticionDocumentoDetalle.getForFacturacion",peticionDocumento.getId());
	}
	
	public List<PeticionDocumentoDetalle> ListOtsForTO(Long idGuia) throws Exception {
		return getSqlSessionTemplate().selectList("peticionDocumentoDetalle.ListOtsForTO",idGuia);
	}
}

