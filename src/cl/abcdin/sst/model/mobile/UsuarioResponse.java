package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class UsuarioResponse extends Response {
	private UsuarioMobile usuario;

	@XmlElement(name="usuario", nillable = true)
	public UsuarioMobile getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioMobile usuario) {
		this.usuario = usuario;
	}
	
}
