package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class UbicacionMobile {
	private Long id;
	private String nombre;
	
	@XmlElement(nillable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(nillable = true)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
