package cl.abcdin.sst.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ParameterNameDiscoverer;

import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.mobile.Response;
import cl.abcdin.sst.model.mobile.UsuarioResponse;
import cl.abcdin.sst.service.MobileService;
import cl.abcdin.sst.service.SpringApplicationContext;
import cl.abcdin.sst.utils.Constants;

public class PDAServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(PDAServlet.class);
	private MobileService instance;
	private Method[] methods;
	
	private ParameterNameDiscoverer parameterNameDiscoverer;
	
	private static final long serialVersionUID = 1L;

	public PDAServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}

	private void action(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		log.debug("PDAServlet");
		OutputStream out = response.getOutputStream();
		try {
			configure();
			String action = request.getParameter("action");
			
			Object resp = evaluate(request, action);
			
			JAXBContext jc = JAXBContext.newInstance(resp.getClass());
			Marshaller jaxbMarshaller = jc.createMarshaller();
			
			log.debug("Lockup for parameters =>");
			
			jaxbMarshaller.marshal(resp, out);
			
		} catch (InvocationTargetException ex) {
			createErrorResponse((Exception) ex.getCause(),out);
		} catch (Exception ex) {
			createErrorResponse(ex,out);				
		} finally {
			out.close();
		}
	}
	
	private void createErrorResponse(Exception e, OutputStream out) throws ServletException{
		Response responce = new Response();
		responce.setCodigo(Constants.CODIGO_ERROR_MOBILE);
		try {
			if (e.getMessage() != null) {
				responce.setGlosa(e.getMessage());
			} else {
				responce.setGlosa(ExceptionUtils.getStackTrace(e));			
			}
			JAXBContext jc;
			jc = JAXBContext.newInstance(Response.class);
			Marshaller jaxbMarshaller = jc.createMarshaller();
			jaxbMarshaller.marshal(responce, out);
		} catch (JAXBException e1) {
			log.error(e, e);
			throw new ServletException(e);
		}
	}
	
	private Object execute(HttpServletRequest request, String action) throws Exception {
		Method method = getMethod(action);
		Object[] params = configureParameters(method, request);
		return method.invoke(getMobileService(), params);
	}
	
	private Boolean isValidLogin(HttpServletRequest request, String action) throws Exception {
		UsuarioResponse usuarioResponse = this.getUsuarioResponse(request);
		return usuarioResponse.getCodigo().equals(Constants.CODIGO_OK_MOBILE);
	}

	private Usuario getUsuario(HttpServletRequest request) throws Exception {
		UsuarioResponse resp = this.getUsuarioResponse(request);
		Usuario usuario = new Usuario();
		usuario.setId(resp.getUsuario().getId());
		usuario.setNombre(resp.getUsuario().getNombre());
		usuario.setRut(resp.getUsuario().getRun());
		return usuario;
	}
	
	private Ubicacion getUbicacion(HttpServletRequest request) throws Exception {
		Method method = getMethod("getUbicacionById");
		Object[] params = configureParameters(method, request);
		return (Ubicacion)method.invoke(getMobileService(), params);
	}
	
	private UsuarioResponse getUsuarioResponse(HttpServletRequest request) throws Exception {
		Method method = getMethod("login");
		Object[] params = configureParameters(method, request);
		return (UsuarioResponse)method.invoke(getMobileService(), params);
	}
	
	
	private Object evaluate(HttpServletRequest request, String action) throws Exception{
		if (action == null || action.equals("")) {
			return new Response(Constants.CODIGO_OK_MOBILE, null);
		} else if (action.equals("getUbicaciones")) {
			return execute(request, action);
		} else if (action.equals("logout")) {
			this.invalidateSession(request);
			return new Response(Constants.CODIGO_OK_MOBILE, null);
		} else if (action.equals("login")) {
			Object resp = execute(request, action);
			return resp;
		} else if (isValidLogin(request, action)) {
			return execute(request, action);
		}
		throw new Exception("Acceso Denegado");
	}
	
	private void invalidateSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	public Boolean isValidSession(HttpServletRequest request) throws Exception{
		if (request.getSession(false) == null)
			return false;
		if (request.getSession(false).getAttribute(Constants.SESSION_USER) == null)
			return false;
		if (request.getSession(false).getAttribute(Constants.SESSION_UBICACION) == null)
			return false;
		return true;
	}
	
	private Object[] configureParameters(Method method, HttpServletRequest request) throws Exception {
		Class<?>[] parameterTypes = method.getParameterTypes();
		String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);	
		Object[] objects = new Object[parameterNames.length];
		
		if (parameterNames.length == parameterTypes.length) {
			for (int i = 0; i < parameterNames.length; i++) {
				String parameterName =  parameterNames[i];
				Class<?> clazz = parameterTypes[i];
				Object value = request.getParameter(parameterNames[i]);
				log.debug("parameter " + parameterName + " is Type " + clazz.getName());
				
				if (value == null) {
					if (parameterName.equals("ip")) {
						value = request.getRemoteAddr();
					} else if (parameterName.equals("usuario")) {
						value = getUsuario(request);
					} else if (parameterName.equals("ubicacion")) {
						value = getUbicacion(request);
					} else {
						throw new Exception("Parameter not found => " + parameterName);						
					}
				}
				try {
					if (clazz == String.class) {
						objects[i] = value;
					} else if (clazz == Long.class) {
						objects[i] = Long.parseLong((String)value);						
					} else if (clazz == Integer.class) {
						objects[i] = Integer.parseInt((String)value);
					} else if (clazz == List.class) {
						if (value.equals("")) {
							objects[i] = new ArrayList<Object>();
						} else {
							String[] splts = value.toString().split(",");
							List<String> lst = new ArrayList<String>();
							for (String string : splts) {
								lst.add(string);
							}
							objects[i] = lst;
						}
					} else {
						objects[i] = clazz.cast(value);
					}
				} catch (Exception e) {
					throw new Exception("Trying convert parameter " + parameterName + " whit value \"" + value + "\" to type " + clazz.getCanonicalName() + " FAIL");
				}
			}
		} else {
			throw new Exception("the mismatch between parameters don't match => parameterNames: " + parameterNames.length + " parameterTypes: " + parameterTypes.length);
		}
		return objects;
	}
	
	private Method getMethod(String action) throws Exception {
		Method method = null;
		
		for (Method method_aux : methods) {
			if (method_aux.getName().equals(action)) {
				log.debug("Find Method => " + action );
				method = method_aux;
				break;
			}
		}
		
		if (method == null) {
			throw new Exception("Method not found => " + action);
		}
		return method;
	}
	
	private void configure() {
		if (instance == null) {
			getParameterNameDiscoverer();
			getMobileService();
			getMethods();
		}
	}
	
	private ParameterNameDiscoverer getParameterNameDiscoverer(){
		if (parameterNameDiscoverer == null)
			parameterNameDiscoverer = (ParameterNameDiscoverer)SpringApplicationContext.getBean("localVariableTableParameterNameDiscoverer");
		return parameterNameDiscoverer;	
	}
	
	private MobileService getMobileService() {
		if (instance == null)
			instance = (MobileService)SpringApplicationContext.getBean("mobileService");
		return instance;		
	}
	
	private Method[] getMethods() {
		if (methods == null) {
			methods = MobileService.class.getMethods();
		}
		return methods;		
	}
}
