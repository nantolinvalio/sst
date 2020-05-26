package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Documento;

public class ClienteDAO extends BaseDAO{
	
	public Cliente getClienteByOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("cliente.getClienteByOT", idOT);
	}
	
	public Cliente getClienteByFilter(Documento documento) throws Exception {
		return getSqlSessionTemplate().selectOne("cliente.getClienteByFilter", documento);
	}
	
	public Cliente getClienteByRut(Cliente cliente) throws Exception{
		return getSqlSessionTemplate().selectOne("cliente.getClienteByRut", cliente);
	}

	public Cliente getClienteByRutFromSST(Cliente cliente) throws Exception{
		return getSqlSessionTemplate().selectOne("cliente.getClienteByRutFromSST",cliente);
	}
	
	public Integer saveCliente(Cliente cliente) throws Exception{
		return getSqlSessionTemplate().insert("cliente.saveCliente",cliente);
	}
	
	public Integer updateCliente(Cliente cliente) throws Exception{
		return getSqlSessionTemplate().insert("cliente.updateCliente",cliente);
	}
}
