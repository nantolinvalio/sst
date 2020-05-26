package cl.abcdin.sst.model.filters;

public class FilterTipoFallas extends FilterBase{
	private Long idProducto;
	private String idFamilia;
	private Integer idAccesorio;
	private Long idOT;
	private Long idTipoFalla;
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public Integer getIdAccesorio() {
		return idAccesorio;
	}
	public void setIdAccesorio(Integer idAccesorio) {
		this.idAccesorio = idAccesorio;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Long getIdTipoFalla() {
		return idTipoFalla;
	}
	public void setIdTipoFalla(Long idTipoFalla) {
		this.idTipoFalla = idTipoFalla;
	}
}
