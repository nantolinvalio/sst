package cl.abcdin.sst.model.filters;

public class FilterParte extends FilterBase{
	private String idFamilia;
	private Long idProducto;
	private Boolean vigente;
	
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

}
