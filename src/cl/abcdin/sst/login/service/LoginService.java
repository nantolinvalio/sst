package cl.abcdin.sst.login.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.PrtsDAO;
import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UsuarioDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.login.dao.LoginDAO;
import cl.abcdin.sst.login.model.Acceso;
import cl.abcdin.sst.model.Prts;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.utils.SSTTParametros;
import cl.abcdin.sst.utils.Utils;

public class LoginService implements ILoginService{
	
	private final String accesoUsuario = "U";
	private final String accesoGrupo = "G";
	private Boolean tieneAccesos = false;
	
	private String initialContextFactory;
	private String providerURLPrimary;
	private String providerURLSecundary;
	private String application;
	private LoginDAO loginDAO;
	private List<Acceso> accesos;
	private UbicacionDAO ubicacionDAO;
	private UsuarioDAO usuarioDAO;
	private PrtsDAO prtsDAO;
	
//	private UsuarioDAO usuarioDAO;
	
	private static final Log log = LogFactory.getLog(LoginService.class);
	
	public Usuario fullLogin(Usuario usuario, Ubicacion ubicacion, String ip) throws Exception {
		usuario = this.login(usuario, ip);
		usuario = usuarioDAO.get(usuario.getId());
		List<Ubicacion> ubicaciones = ubicacionDAO.listAllByIdUsuario(usuario);
		Boolean existUbicacion = false;

		for (Ubicacion ub : ubicaciones) {
			if (ub.getId().equals(ubicacion.getId())) {
				existUbicacion = true;
				usuario.setUbicacion(ubicacionDAO.get(ubicacion.getId()));
				break;
			}
		}

		if (!existUbicacion) {
			throw new SSTException("LA UBICACIÓN A LA QUE INTENTA INGRESAR NO ESTA AUTORIZADA");
		}
		return usuario;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Usuario login(Usuario user, String ip) throws Exception {
		Usuario usuario = new Usuario();
        String usuarioLDAP = user.getLogin().replace('.', ' ');
        String usuarioZIMBRA = user.getLogin().replace(' ', '.').toLowerCase();
        String xuid = "cn=" + usuarioLDAP.toLowerCase();
        String zuid = "uid=" + usuarioZIMBRA + ",ou=people,dc=tiendas,dc=abcdin,dc=cl";
        Boolean autenticado = false;
        Boolean autorizado = false;
        NamingEnumeration results = null;
        DirContext ctx = null;
        List<Prts> listaParametros = null;
        this.validateInfoUsuario(user);
        
        try {
            String SEARCHBASE = "";
            String FILTER = "(cn=" + usuarioLDAP + ")";

            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
            env.put(Context.PROVIDER_URL, providerURLPrimary);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, xuid);
            env.put(Context.SECURITY_CREDENTIALS, user.getPassword());
            ctx = new InitialDirContext(env);
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(SEARCHBASE, FILTER, constraints);
            while ((results != null) && results.hasMore()) {
            	SearchResult sr = (SearchResult) results.nextElement();
            	Attributes attrs = sr.getAttributes();
                if (attrs.get("rut") != null) {
                	usuario.setRut(attrs.get("rut").get().toString());
                	usuario.setId(Utils.getIdFromRun(attrs.get("rut").get().toString()));
                }
                if (attrs.get("cn") != null) {
                	usuario.setNombre(attrs.get("cn").get().toString());
                } 
                if (attrs.get("mail") != null) {
                	usuario.setEmail(attrs.get("mail").get().toString());
                }
            } 
            ctx.close();
            autenticado = true;
        } catch (CommunicationException e) { 
        	log.error(e);
        } catch (NamingException e) { 
        	//NO HACER NADA
        } catch (Exception e) { 
        	log.error("LDAP FAIL",e);
        } finally {
        	if (results != null) {
                try {
                   results.close();
                   results = null;
                } catch (Exception e) { }
             }
        }
        
        if (!autenticado) {
            try {
                String SEARCHBASE = "";
                String FILTER = "(&(uid=" + usuarioZIMBRA + "))";

                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
                env.put(Context.PROVIDER_URL, providerURLSecundary);
                env.put(Context.SECURITY_AUTHENTICATION, "simple");
                env.put(Context.SECURITY_PRINCIPAL, zuid);
                env.put(Context.SECURITY_CREDENTIALS, user.getPassword());
                ctx = new InitialDirContext(env);
                SearchControls constraints = new SearchControls();
                constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
                results = ctx.search(SEARCHBASE, FILTER, constraints);
                while ((results != null) && results.hasMore()) {
                    SearchResult sr = (SearchResult) results.nextElement();
                    Attributes attrs = sr.getAttributes();
                    if (attrs.get("rut") != null) {
                    	usuario.setRut(attrs.get("rut").get().toString());
                    	usuario.setId(Utils.getIdFromRun(attrs.get("rut").get().toString()));
                    } 
                    if (attrs.get("givenName") != null) {
                    	usuario.setNombre(attrs.get("rut").get().toString());
                    } 
                    if (attrs.get("zimbraMailDeliveryAddress") != null) {
                    	usuario.setEmail(attrs.get("zimbraMailDeliveryAddress").get().toString());
                    }
                } 
                ctx.close();
                autenticado = true;
            } catch (CommunicationException e) { 
            	log.error(e);
            } catch (NamingException e) { 
            	//NO HACER NADA
            } catch (Exception e) { 
            	log.error("LDAP FAIL",e);
            } finally {
            	if (results != null) {
                    try {
                       results.close();
                       results = null;
                    } catch (Exception e) { }
                 }
            }
        } 

        if (autenticado) {
            for (Acceso acceso : getAccesos()) {
            	if (!autorizado) {
            		if (acceso.getTipo().equals(accesoUsuario)) {
            			if (usuario.getEmail().equalsIgnoreCase(acceso.getBusqueda())) {
                            autorizado = true;
                        } else if (acceso.getBusqueda().equalsIgnoreCase("*")) {
                            autorizado = true;
                        }
    				} else if (acceso.getTipo().equals(accesoGrupo)) {
    					 try {
	                        String SEARCHBASE = "";
	                        String FILTER = "(cn=" + acceso.getBusqueda() + ")";
	                        String tcons = "";
	                        String tcons2 = "";

	                        Hashtable env = new Hashtable();
	                        env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
	                        env.put(Context.PROVIDER_URL, providerURLPrimary);
	                        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	                        env.put(Context.SECURITY_PRINCIPAL, xuid);
	                        env.put(Context.SECURITY_CREDENTIALS, user.getPassword());
	                        ctx = new InitialDirContext(env);
	                        SearchControls constraints = new SearchControls();
	                        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	                        results = ctx.search(SEARCHBASE, FILTER, constraints);
	                        while ((results != null) && results.hasMore()) {
	                            SearchResult sr = (SearchResult) results.nextElement();
	                            Attributes attrs = sr.getAttributes();
	                            for (Enumeration enum1 = attrs.getAll(); enum1.hasMoreElements();) {
	                                Attribute a = (Attribute) enum1.nextElement();
	                                tcons = a.getID();
	                                for (Enumeration enum2 = a.getAll(); enum2.hasMoreElements();) {
	                                    tcons2 = enum2.nextElement().toString();
	                                    if (tcons.equals("member")) {
	                                        String cdn = tcons2.substring(3, tcons2.length());
	                                        cdn = cdn.substring(0, cdn.indexOf(','));
	                                        if (cdn.toLowerCase().equals(usuario.getNombre().toLowerCase())) {
	                                            autorizado = true;
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                        ctx.close();
	                    } catch (NamingException e) { 
	                    	//NO HACER NADA
	                    } catch (Exception e) { 
	                    	log.error("LDAP FAIL",e);
	                    } finally {
	                    	if (results != null) {
	                            try {
	                               results.close();
	                               results = null;
	                            } catch (Exception e) { }
	                         }
	                    }

	                    if (!autorizado) {
	                        String usergrupo = acceso.getBusqueda().replace(' ', '.').toLowerCase();
	                        try {
	                            String SEARCHBASE = "";
	                            String FILTER = "(&(uid=" + usergrupo + "))";
	                            String tcons = "";
	                            String tcons2 = "";

	                            Hashtable env = new Hashtable();
	                            env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
	                            env.put(Context.PROVIDER_URL, providerURLSecundary);
	                            env.put(Context.SECURITY_AUTHENTICATION, "simple");
	                            env.put(Context.SECURITY_PRINCIPAL, zuid);
	                            env.put(Context.SECURITY_CREDENTIALS, user.getPassword());
	                            ctx = new InitialDirContext(env);
	                            SearchControls constraints = new SearchControls();
	                            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	                            results = ctx.search(SEARCHBASE, FILTER, constraints);
	                            while ((results != null) && results.hasMore()) {
	                                SearchResult sr = (SearchResult) results.nextElement();
	                                Attributes attrs = sr.getAttributes();
	                                for (Enumeration enum1 = attrs.getAll(); enum1.hasMoreElements();) {
	                                    Attribute a = (Attribute) enum1.nextElement();
	                                    tcons = a.getID();
	                                    for (Enumeration enum2 = a.getAll(); enum2.hasMoreElements();) {
	                                        tcons2 = enum2.nextElement().toString();
	                                        if (tcons.equals("zimbraMailForwardingAddress")) {
	                                            if (tcons2.toLowerCase().equalsIgnoreCase(usuario.getEmail())) {
	                                                autorizado = true;
	                                            }
	                                        }
	                                    }
	                                }
	                            }
	                            ctx.close();
	                        } catch (NamingException e) {
	                        	//NO HACER NADA
	                        } catch (Exception e) { 
	                        	log.error("LDAP FAIL",e);
	                        } finally {
	                        	if (results != null) {
	                                try {
	                                   results.close();
	                                   results = null;
	                                } catch (Exception e) { }
	                             }
	                        }
	                    }
    				}
            	}
			}
        } else {
        	throw new SSTException("Usuario no válido o Password Incorrecta");
        }

        log.info("autorizado " + autorizado);
        if (autorizado) {
        	log.info("Usuario autenticado y autorizado para ingresar");
			try {
				this.saveRegistro(usuario, ip);
				listaParametros = prtsDAO.getTotalPrts();
				SSTTParametros.init(listaParametros);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
            return usuario;
        } else {
        	log.info("Usuario sin acceso a la aplicació�n");
        	throw new SSTException("Usuario o Contraseña Invalido");
        }
        
	}
	
	private void validateInfoUsuario(Usuario usuario) throws Exception {
		if (usuario.getLogin() == null || usuario.getLogin().equals("")) {
			log.info("Empty user");
			throw new SSTException("Falta información: USUARIO");
		}
		if (usuario.getPassword() == null || usuario.getPassword().equals("")) {
			log.info("Empty password");
			throw new SSTException("Falta información: PASSWORD");		
		}
		log.info("User/Login => " + usuario.getLogin());
		log.info("Password length => " + usuario.getPassword().length());
	}
	
	private void saveRegistro(Usuario usuario, String ip) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("CODIGO", application);
		map.put("USUARIO", usuario.getNombre());
		map.put("IP", ip);
		loginDAO.saveRegistro(map);
	}
	

	private List<Acceso> getAccesos() throws Exception {
		if (accesos == null) {
			accesos = loginDAO.listAccesosByCodigo(application);
			for (Acceso acceso : accesos) {
				if (acceso.getTipo().equals(accesoUsuario) || acceso.getTipo().equals(accesoGrupo)) {
					tieneAccesos = true;
				}
			}
		}
		if (!tieneAccesos) {
			throw new Exception("No hay accesos para la aplicación " + application + " no existe");			
		}
		return accesos;
	}
	
	public void setInitialContextFactory(String initialContextFactory) {
		this.initialContextFactory = initialContextFactory;
	}

	public void setProviderURLPrimary(String providerURLPrimary) {
		this.providerURLPrimary = providerURLPrimary;
	}
	
	public void setProviderURLSecundary(String providerURLSecundary) {
		this.providerURLSecundary = providerURLSecundary;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public void setUbicacionDAO(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void setPrtsDAO(PrtsDAO prtsDAO) {
		this.prtsDAO = prtsDAO;
	}

}
