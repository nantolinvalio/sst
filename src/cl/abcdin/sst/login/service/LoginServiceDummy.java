package cl.abcdin.sst.login.service;

import java.util.List;

import cl.abcdin.sst.dao.UbicacionDAO;
import cl.abcdin.sst.dao.UsuarioDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.login.dao.LoginDAO;
import cl.abcdin.sst.model.Prts;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.utils.SSTTParametros;
import cl.abcdin.sst.utils.Utils;
import cl.abcdin.sst.dao.PrtsDAO;

public class LoginServiceDummy implements ILoginService{
	
	private UbicacionDAO ubicacionDAO;
	private UsuarioDAO usuarioDAO;
	private PrtsDAO prtsDAO;
	
	public Usuario fullLogin(Usuario usuario, Ubicacion ubicacion, String ip) throws Exception {
		System.out.println("FULLLOGIN FALSO");
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
		
		List<Prts> listaParametros = null;
		listaParametros = prtsDAO.getTotalPrts();
		SSTTParametros.init(listaParametros);
		
		if (!existUbicacion) {
			throw new SSTException("LA UBICACIÓN A LA QUE INTENTA INGRESAR NO ESTA AUTORIZADA");
		}
		return usuario;
	}
	
	public Usuario login(Usuario user, String ip) throws Exception {
		System.out.println("login falso");
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.get(Long.parseLong(user.getLogin()));
		return usuario;
	}
	
	public void setInitialContextFactory(String initialContextFactory) {
	}

	public void setProviderURLPrimary(String providerURLPrimary) {
	}
	
	public void setProviderURLSecundary(String providerURLSecundary) {
	}

	public void setLoginDAO(LoginDAO loginDAO) {
	}

	public void setApplication(String application) {
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