package cl.abcdin.sst.model;

import java.util.Date;

public class RecepcionCamionGuia {
	private Long id;
	private RecepcionCamion recepcionCamion;
	private Estado estadoGuia;
	private Date fechaInicio;
	private Date fechaTermino;
	private Guia guia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RecepcionCamion getRecepcionCamion() {
		return recepcionCamion;
	}
	public void setRecepcionCamion(RecepcionCamion recepcionCamion) {
		this.recepcionCamion = recepcionCamion;
	}
	public Estado getEstadoGuia() {
		return estadoGuia;
	}
	public void setEstadoGuia(Estado estadoGuia) {
		this.estadoGuia = estadoGuia;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
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
	
	
}
