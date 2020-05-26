package cl.abcdin.sst.model.filters;


public class FilterRecepcionProducto extends FilterBase{
	private Long idRecepcionCamionGuia;
	private Integer idEstadoBitacora;
	private String codigoBarra;
	private String numeroSerie;
	private Boolean guiaVigente;
	private Long idOT;
	private Integer idRecepcionCamion;
	private Long idGuia;
	private Long idDestino;
	private Integer idEstadoRecepcion;
	private Integer idEstadoGuia;
	
	public Integer getIdEstadoBitacora() {
		return idEstadoBitacora;
	}
	public void setIdEstadoBitacora(Integer idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Boolean getGuiaVigente() {
		return guiaVigente;
	}
	public void setGuiaVigente(Boolean guiaVigente) {
		this.guiaVigente = guiaVigente;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Long getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Long idGuia) {
		this.idGuia = idGuia;
	}
	public Integer getIdRecepcionCamion() {
		return idRecepcionCamion;
	}
	public void setIdRecepcionCamion(Integer idRecepcionCamion) {
		this.idRecepcionCamion = idRecepcionCamion;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public Long getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}
	public Integer getIdEstadoRecepcion() {
		return idEstadoRecepcion;
	}
	public void setIdEstadoRecepcion(Integer idEstadoRecepcion) {
		this.idEstadoRecepcion = idEstadoRecepcion;
	}
	public Long getIdRecepcionCamionGuia() {
		return idRecepcionCamionGuia;
	}
	public void setIdRecepcionCamionGuia(Long idRecepcionCamionGuia) {
		this.idRecepcionCamionGuia = idRecepcionCamionGuia;
	}
	public Integer getIdEstadoGuia() {
		return idEstadoGuia;
	}
	public void setIdEstadoGuia(Integer idEstadoGuia) {
		this.idEstadoGuia = idEstadoGuia;
	}
	
	
	
}
