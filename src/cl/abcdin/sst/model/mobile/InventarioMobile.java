package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class InventarioMobile {
//	private Long idInventario;
//	private Long idInventarioUbicacion;
	private Long id;//Corresponde al id de la tabla sstt_inventario_ubicaciones
	private String nombre;
	
//	@XmlElement(nillable=true)
//	public Long getIdInventario() {
//		return idInventario;
//	}
//	public void setIdInventario(Long idInventario) {
//		this.idInventario = idInventario;
//	}
//	@XmlElement(nillable=true)
//	public Long getIdInventarioUbicacion() {
//		return idInventarioUbicacion;
//	}
//	public void setIdInventarioUbicacion(Long idInventarioUbicacion) {
//		this.idInventarioUbicacion = idInventarioUbicacion;
//	}
	@XmlElement(nillable=true)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement(nillable=true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
