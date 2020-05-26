package cl.abcdin.sst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


public class SessionStorageService {
	
	public static WebContext getWebContext() {
		return WebContextFactory.get();
	}

	public static HttpServletRequest getRequest() {
		return SessionStorageService.getWebContext().getHttpServletRequest();
	}

	public static HttpSession getSession() {
		return SessionStorageService.getRequest().getSession();
	}

	public static HttpSession getSession(Boolean bool) {
		return SessionStorageService.getRequest().getSession(bool);
	}
	
	public static void setAttribute(String id, Object object) {
		SessionStorageService.getSession().setAttribute(id, object);
	}

	public static Object getAttribute(String id) {
		return SessionStorageService.getSession().getAttribute(id);
	}
	
	public static void removeObject(String id) {
		SessionStorageService.getSession().removeAttribute(id);
	}
	
	public static void invalidate() {
		SessionStorageService.getSession().invalidate();
	}
}
