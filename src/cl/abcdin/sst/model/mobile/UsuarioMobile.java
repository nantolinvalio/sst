package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class UsuarioMobile {
	private Long id;
	private String run; 
	private String nombre;

	@XmlElement(nillable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(nillable = true)
	public String getRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	@XmlElement(nillable = true)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
