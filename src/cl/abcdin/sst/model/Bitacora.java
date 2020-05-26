package cl.abcdin.sst.model;

import java.util.Date;
import java.util.List;

public class Bitacora {
	private Long id;
	private Guia guia;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Estado estado;
	private Ubicacion ubicacion;
	private Boolean vigente;
	private Integer duracion;
	private OrdenTrabajo ordenTrabajo;
	private Recepcion recepcion;
	private Long idOT;
	private List<BitacoraInterna> bitacorasInternas;
	private Integer numeroIT;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
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
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public List<BitacoraInterna> getBitacorasInternas() {
		return bitacorasInternas;
	}
	public void setBitacorasInternas(List<BitacoraInterna> bitacorasInternas) {
		this.bitacorasInternas = bitacorasInternas;
	}
	public Integer getNumeroIT() {
		return numeroIT;
	}
	public void setNumeroIT(Integer numeroIT) {
		this.numeroIT = numeroIT;
	}
	
}
