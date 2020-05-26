package cl.abcdin.sst.model;

public class PeticionDocumento {

	private Long id;
	private Integer numero;
	private String tipo;
	private Long facturar;
	private Boolean vigente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getFacturar() {
		return facturar;
	}
	public void setFacturar(Long facturar) {
		this.facturar = facturar;
	}
	
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
