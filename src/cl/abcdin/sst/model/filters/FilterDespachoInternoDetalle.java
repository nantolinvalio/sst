package cl.abcdin.sst.model.filters;

public class FilterDespachoInternoDetalle extends FilterBase{

	private Long idDespacho;
	private Long idOT;
	private Long idEstado;
	
	public Long getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(Long idDespacho) {
		this.idDespacho = idDespacho;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
}
