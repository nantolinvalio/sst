package cl.abcdin.sst.utils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.login.service.LoginService;
import cl.abcdin.sst.model.Prts;


public final class SSTTParametros {
	/** LOG CONSTANT **/
	private static final Log log = LogFactory.getLog(LoginService.class);
	/**
	 * Instancia singleton
	 */
	private static SSTTParametros instancia = null;
	private List<Prts> listaParametros = null;

	/**
	 * 
	 * 
	 * <p>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
	 * </ul>
	 * <p>
	 * 
	 * @since 1.X
	 */
	private SSTTParametros(List<Prts> listaParametros) {
		this.listaParametros = listaParametros;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
	 * </ul>
	 * <p>
	 * 
	 * @return
	 * @since 1.X
	 */
	public static SSTTParametros getInstancia() {
		if (instancia == null) {
			throw new AssertionError(
					"Debes solicitar el inicio de la instancia primero");

		}
		return instancia;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
	 * </ul>
	 * <p>
	 * 
	 * @param url
	 * @return
	 * @since 1.X
	 */
	public static SSTTParametros init(List<Prts> listaParametros) {

		try {

			instancia = new SSTTParametros(listaParametros);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}

		return instancia;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
	 * </ul>
	 * <p>
	 * 
	 * @return
	 * @since 1.X
	 */
	public List<Prts> getListaParametros() {
		return listaParametros;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
	 * </ul>
	 * <p>
	 * Metodo que retorna el parametro ligado al codigo dado
	 * 
	 * @param codigo
	 * @return
	 * @since 1.X
	 */
	public Prts getParametroPorCodigo(String codigo) {
		if (codigo != null
//				&& !Constantes.VACIO.getValor().equalsIgnoreCase(codigo)
				&& this.listaParametros != null
				&& !this.listaParametros.isEmpty()) {

			for (Prts parametroActual : listaParametros) {
				if (parametroActual.getCodigo().equalsIgnoreCase(codigo)) {
					return parametroActual;
				}
			}
		}
		return null;

	}

}

