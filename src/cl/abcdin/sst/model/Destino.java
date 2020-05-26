package cl.abcdin.sst.model;

public class Destino {
	private Long id;
	private Ubicacion origen;
	private Ubicacion destino;
	private Boolean vigente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ubicacion getOrigen() {
		return origen;
	}
	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}
	public Ubicacion getDestino() {
		return destino;
	}
	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	
}
