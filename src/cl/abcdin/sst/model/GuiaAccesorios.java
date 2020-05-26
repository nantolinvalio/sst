package cl.abcdin.sst.model;

public class GuiaAccesorios {
	private Long id;
	private Accesorio accesorio;
	private Guia guia;
	
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Accesorio getAccesorio() {
		return accesorio;
	}
	public void setAccesorio(Accesorio accesorio) {
		this.accesorio = accesorio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
