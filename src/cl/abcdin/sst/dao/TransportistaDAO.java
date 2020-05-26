package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Transportista;

public class TransportistaDAO extends BaseDAO{

	public List<Transportista> listTransportista() throws Exception {
		return getSqlSessionTemplate().selectList("transportista.listTransportista");
	}

	public Transportista getTransportistaByUltimaRecepcionOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("transportista.getTransportistaByUltimaRecepcionOT", idOT);
	}
	
	public Transportista getTransportistaById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("transportista.getTransportistaById",id);
	}
}
