package cl.abcdin.sst.model.filters;

public class FilterGuiasPendientes extends FilterBase{
	private Long idUbicacion;
	private String tipoOT;
	private String tipoCambio;
	private String tipoCambioJT;
	private Integer idEstadoOT;
	private Integer idEstadoBitacora;
	private Long idOrigen;
	private Integer idEstadoGuia;
	private Boolean procesadaOW;

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Integer getIdEstadoOT() {
		return idEstadoOT;
	}

	public void setIdEstadoOT(Integer idEstadoOT) {
		this.idEstadoOT = idEstadoOT;
	}

	public Integer getIdEstadoBitacora() {
		return idEstadoBitacora;
	}

	public void setIdEstadoBitacora(Integer idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}

	public String getTipoCambioJT() {
		return tipoCambioJT;
	}

	public void setTipoCambioJT(String tipoCambioJT) {
		this.tipoCambioJT = tipoCambioJT;
	}

	public Long getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getTipoOT() {
		return tipoOT;
	}

	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}

	public Integer getIdEstadoGuia() {
		return idEstadoGuia;
	}

	public void setIdEstadoGuia(Integer idEstadoGuia) {
		this.idEstadoGuia = idEstadoGuia;
	}

	public Boolean getProcesadaOW() {
		return procesadaOW;
	}

	public void setProcesadaOW(Boolean procesadaOW) {
		this.procesadaOW = procesadaOW;
	}
	
}
