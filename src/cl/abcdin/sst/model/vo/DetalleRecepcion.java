package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Guia;

public class DetalleRecepcion {
	private Guia guia;
	private Integer cantidadTotalOT;
	private Integer cantidadOTRecibidas;
	private Integer cantidadOTRevisadas;
	private Estado estado;
	
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Integer getCantidadTotalOT() {
		return cantidadTotalOT;
	}
	public void setCantidadTotalOT(Integer cantidadTotalOT) {
		this.cantidadTotalOT = cantidadTotalOT;
	}
	public Integer getCantidadOTRecibidas() {
		return cantidadOTRecibidas;
	}
	public void setCantidadOTRecibidas(Integer cantidadOTRecibidas) {
		this.cantidadOTRecibidas = cantidadOTRecibidas;
	}
	public Integer getCantidadOTRevisadas() {
		return cantidadOTRevisadas;
	}
	public void setCantidadOTRevisadas(Integer cantidadOTRevisadas) {
		this.cantidadOTRevisadas = cantidadOTRevisadas;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
