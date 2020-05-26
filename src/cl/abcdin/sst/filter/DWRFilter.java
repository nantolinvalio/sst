package cl.abcdin.sst.filter;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

import cl.abcdin.sst.exceptions.AccessException;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.service.SessionStorageService;
import cl.abcdin.sst.utils.Constants;

public class DWRFilter implements AjaxFilter {
	private static final Log log = LogFactory.getLog(DWRFilter.class);
	
	public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
		log.debug(this.getClass().toString() + ".doFilter");
		if(method.getName().equals("login") || method.getName().equals("listUbicacionesByUsuario")) {
			Object respLogin = chain.doFilter(obj, method, params);
			if (respLogin == null) {
				throw new SSTException("Usuario o Contraseña Invalido");
			}
			return respLogin;
		} 		
		
		if(method.getName().equals("logout")) {
			this.invalidateSession();
			return true;
		} 		
		
		if(method.getName().equals("fullLogin")) {
			log.debug("Realizando Full Login");
			Object respLogin = chain.doFilter(obj, method, params);
			
			if (respLogin == null) {
				throw new SSTException("Usuario o Contraseña Invalido");
			}
			this.createSession(respLogin);
			return true;
		} 
		
		if(!isValidSession()) {
			log.debug("Usuario sin sesion DWRFilter");
			invalidateSession();
			throw new AccessException("Acceso Denegado");
		}
		return chain.doFilter(obj, method, params);
	}
	
	public Boolean isValidSession() throws Exception{
		if (SessionStorageService.getSession(false) == null)
			return false;
		if (SessionStorageService.getAttribute(Constants.SESSION_USER) == null)
			return false;
		if (SessionStorageService.getAttribute(Constants.SESSION_UBICACION) == null)
			return false;
		return true;
	}
	
	private void invalidateSession() throws Exception {
		try {
			SessionStorageService.invalidate();
		} catch(Exception ignore) {	}
	}
	
	private void createSession(Object obj) throws Exception{
		Usuario usuario = (Usuario)obj;
		invalidateSession();
		log.debug("Creando nueva sesion");
		SessionStorageService.setAttribute(Constants.SESSION_USER, usuario);
		SessionStorageService.setAttribute(Constants.SESSION_UBICACION, usuario.getUbicacion());
	}
	
	/*
	 * Solo mientras dure la migracion cuando el nuevo login entre al sistema eliminar
	 * */
}
