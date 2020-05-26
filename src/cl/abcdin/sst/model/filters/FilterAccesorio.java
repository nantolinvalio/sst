package cl.abcdin.sst.model.filters;

public class FilterAccesorio extends FilterBase {
	private Long idOT;
	private Integer idAccesorio;
	private Long idUbicacion;
	private Long idAccesorioOT;
	private String idFamilia;
	private Long idProducto;
	private Long idTipoFalla;
	private Boolean vigente;
	
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Integer getIdAccesorio() {
		return idAccesorio;
	}
	public void setIdAccesorio(Integer idAccesorio) {
		this.idAccesorio = idAccesorio;
	}
	public Long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public Long getIdAccesorioOT() {
		return idAccesorioOT;
	}
	public void setIdAccesorioOT(Long idAccesorioOT) {
		this.idAccesorioOT = idAccesorioOT;
	}
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
	public Long getIdTipoFalla() {
		return idTipoFalla;
	}
	public void setIdTipoFalla(Long idTipoFalla) {
		this.idTipoFalla = idTipoFalla;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
