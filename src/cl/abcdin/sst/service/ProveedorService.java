
package cl.abcdin.sst.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.CambioAutomaticoProveedorCartaDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.CambioAutomaticoProveedorCarta;

public class ProveedorService {

	private CambioAutomaticoProveedorCartaDAO cambioAutomaticoProveedorCartaDAO;
	private static final Log log = LogFactory.getLog(ProveedorService.class);

	public CambioAutomaticoProveedorCarta saveCambioAutProveedorCarta(CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta) throws Exception{
		try {
			CambioAutomaticoProveedorCarta cambio = new CambioAutomaticoProveedorCarta();
			if (cambioAutomaticoProveedorCarta.getNumeroSerie() == null) {
				cambio = cambioAutomaticoProveedorCartaDAO.getVigenteByNumeroSerieAndProductoAndCertificado(cambioAutomaticoProveedorCarta);	
			} else {
				cambio = cambioAutomaticoProveedorCartaDAO.getVigenteByNumeroSerieAndProducto(cambioAutomaticoProveedorCarta);	
			}		
			
			if (cambio != null) {
				throw new SSTException("El certificado para este producto se encuentra en proceso");
			}
			
			//configura los valores por defecto
			cambioAutomaticoProveedorCarta.setVigente(true);
			cambioAutomaticoProveedorCarta.setEnProceso(false);
			cambioAutomaticoProveedorCarta.setEntregaCliente(false);
			
			cambioAutomaticoProveedorCartaDAO.saveCambioAutProveedorCarta(cambioAutomaticoProveedorCarta);
			
			return cambioAutomaticoProveedorCarta;
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
 			log.error(e, e);
			throw e;
		}
	}
	
	public CambioAutomaticoProveedorCarta getVigenteByNumeroSerieAndProductoAndCertificado(CambioAutomaticoProveedorCarta cambioAutomaticoProveedorCarta) throws Exception{
		try {
			return cambioAutomaticoProveedorCartaDAO.getVigenteByNumeroSerieAndProductoAndCertificado(cambioAutomaticoProveedorCarta);
		} catch (SSTException e) {
			throw e;
		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	public void setCambioAutomaticoProveedorCartaDAO(CambioAutomaticoProveedorCartaDAO cambioAutomaticoProveedorCartaDAO) {
		this.cambioAutomaticoProveedorCartaDAO = cambioAutomaticoProveedorCartaDAO;
	}
}
