package cl.abcdin.sst.model.mobile;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class BitacoraInternaMobile {
	private Long id;
	private Long bitacora;
	private Integer estado;
	private Date fechaInicio;
	private Date fechaTermino;
	private Long despachoInterno;
	private Long recepcion;
	private Long ordenTrabajo;
	private String clasificacion;
	private Integer ubicacionInterna;
	
	@XmlElement(nillable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(nillable = true)
	public Long getBitacora() {
		return bitacora;
	}
	public void setBitacora(Long bitacora) {
		this.bitacora = bitacora;
	}
	@XmlElement(nillable = true)
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	@XmlElement(nillable = true)
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	@XmlElement(nillable = true)
	public Long getDespachoInterno() {
		return despachoInterno;
	}
	public void setDespachoInterno(Long despachoInterno) {
		this.despachoInterno = despachoInterno;
	}
	@XmlElement(nillable = true)
	public Long getRecepcion() {
		return recepcion;
	}
	public void setRecepcion(Long recepcion) {
		this.recepcion = recepcion;
	}
	@XmlElement(nillable = true)
	public Long getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(Long ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	@XmlElement(nillable = true)
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	@XmlElement(nillable = true)
	public Integer getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(Integer ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
	@XmlElement(nillable = true)
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
}
