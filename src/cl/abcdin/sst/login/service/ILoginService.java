package cl.abcdin.sst.login.service;

import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;

public interface ILoginService {
	public Usuario fullLogin(Usuario usuario, Ubicacion ubicacion, String ip) throws Exception;
	
	public Usuario login(Usuario user, String ip) throws Exception;
	
}
