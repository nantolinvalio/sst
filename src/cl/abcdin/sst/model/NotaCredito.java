package cl.abcdin.sst.model;

public class NotaCredito extends TipoGenerico{
	private Integer diasLimite;
	private Boolean vigente;
	private String descripcion;
	
	public Integer getDiasLimite() {
		return diasLimite;
	}

	public void setDiasLimite(Integer diasLimite) {
		this.diasLimite = diasLimite;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
