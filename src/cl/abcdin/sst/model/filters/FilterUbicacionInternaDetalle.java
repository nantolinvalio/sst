package cl.abcdin.sst.model.filters;

public class FilterUbicacionInternaDetalle extends FilterUbicacionInterna{
	private Integer idUbicacionInterna;
	private Long idOT;
	
	public Integer getIdUbicacionInterna() {
		return idUbicacionInterna;
	}
	public void setIdUbicacionInterna(Integer idUbicacionInterna) {
		this.idUbicacionInterna = idUbicacionInterna;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
}
