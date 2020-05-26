package cl.abcdin.sst.model;

public class GuiaRemateDetalle {
	private Long id;
	private Guia guia;
	private OrdenTrabajo ordenTrabajo;
	
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
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	
	
}
