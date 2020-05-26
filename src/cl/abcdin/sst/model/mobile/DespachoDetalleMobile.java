package cl.abcdin.sst.model.mobile;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class DespachoDetalleMobile {
	private Long id;
	private Long ordenTrabajo;
	private Long despachoInterno;
	private Integer estado;
	private Date fechaEstado;
	private Long usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(nillable = true)
	public Long getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(Long ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	@XmlElement(nillable = true)
	public Long getDespachoInterno() {
		return despachoInterno;
	}
	public void setDespachoInterno(Long despachoInterno) {
		this.despachoInterno = despachoInterno;
	}
	@XmlElement(nillable = true)
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@XmlElement(nillable = true)
	public Date getFechaEstado() {
		return fechaEstado;
	}
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	@XmlElement(nillable = true)
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
}
