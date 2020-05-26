package cl.abcdin.sst.model;

import java.util.Date;

public class BitacoraInterna {
	private Long id;
	private Bitacora bitacora;
	private Estado estado;
	private Date fechaInicio;
	private Date fechaTermino;
	private UbicacionInterna ubicacionInterna;
	private DespachoInterno despachoInterno;
	private Recepcion recepcion;
	private OrdenTrabajo ordenTrabajo;
	private Clasificacion clasificacion;
	private Boolean vigente;
	private Integer numeroIT;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Bitacora getBitacora() {
		return bitacora;
	}
	public void setBitacora(Bitacora bitacora) {
		this.bitacora = bitacora;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public UbicacionInterna getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(UbicacionInterna ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
	public DespachoInterno getDespachoInterno() {
		return despachoInterno;
	}
	public void setDespachoInterno(DespachoInterno despachoInterno) {
		this.despachoInterno = despachoInterno;
	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Recepcion getRecepcion() {
		return recepcion;
	}
	public void setRecepcion(Recepcion recepcion) {
		this.recepcion = recepcion;
	}
	public Clasificacion getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Integer getNumeroIT() {
		return numeroIT;
	}
	public void setNumeroIT(Integer numeroIT) {
		this.numeroIT = numeroIT;
	}
	
	
}
