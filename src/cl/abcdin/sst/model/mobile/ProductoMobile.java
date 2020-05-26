package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class ProductoMobile {
	private Long codigoCorto;
	private String descripcion;
	private String marca;
	@XmlElement(nillable=true)
	public Long getCodigoCorto() {
		return codigoCorto;
	}
	public void setCodigoCorto(Long codigoCorto) {
		this.codigoCorto = codigoCorto;
	}
	@XmlElement(nillable=true)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@XmlElement(nillable=true)
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
}
