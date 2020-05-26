package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;


public class SucursalMobile {
	private Long id;
	private String nombre;
	private String usuario;
	private String glosa;
	private Boolean abiertoDespacho;
	
	@XmlElement(nillable=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(nillable=true)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement(nillable=true)
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@XmlElement(nillable=true)
	public Boolean getAbiertoDespacho() {
		return abiertoDespacho;
	}
	public void setAbiertoDespacho(Boolean abiertoDespacho) {
		this.abiertoDespacho = abiertoDespacho;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
}
