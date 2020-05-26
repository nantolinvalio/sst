package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class DespachoMobile {
	private Long id;
	private String nombre;
	private Integer estado;
	private Long idDestino;
	private String nombreUbicacion;
	
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
	@XmlElement(nillable = true)
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@XmlElement(nillable = true)
	public Long getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}
	@XmlElement(nillable = true)
	public String getNombreUbicacion() {
		return nombreUbicacion;
	}
	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}
}
