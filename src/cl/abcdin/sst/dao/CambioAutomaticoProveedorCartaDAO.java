package cl.abcdin.sst.dao;

import cl.abcdin.sst.model.CambioAutomaticoProveedorCarta;
import cl.abcdin.sst.model.Producto;

public class CambioAutomaticoProveedorCartaDAO extends BaseDAO{
	
	public Integer saveCambioAutProveedorCarta(CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta) throws Exception{
		return getSqlSessionTemplate().insert("cambioAutomaticoProveedorCarta.saveCambioAutProveedorCarta", cambioAutomaticoProveedorCarta);
	}
	//getCambioAutProveedorCartaByOT
	public CambioAutomaticoProveedorCarta getVigenteByNumeroSerieAndProducto(CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta) throws Exception {
		return getSqlSessionTemplate().selectOne("cambioAutomaticoProveedorCarta.getVigenteByNumeroSerieAndProducto", cambioAutomaticoProveedorCarta);
	}
	
	public CambioAutomaticoProveedorCarta getVigenteByNumeroSerieAndProductoAndCertificado(CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta) throws Exception {
		return getSqlSessionTemplate().selectOne("cambioAutomaticoProveedorCarta.getVigenteByNumeroSerieAndProductoAndCertificado", cambioAutomaticoProveedorCarta);
	}
	
	public CambioAutomaticoProveedorCarta getNumeroCertificadoByIdProducto(Producto producto) throws Exception {
		return getSqlSessionTemplate().selectOne("cambioAutomaticoProveedorCarta.getNumeroCertificadoByIdProducto", producto);
	}
	
}
