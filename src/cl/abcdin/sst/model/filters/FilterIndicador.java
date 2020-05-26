package cl.abcdin.sst.model.filters;

import java.util.Date;

import cl.abcdin.sst.model.Indicador;


public class FilterIndicador extends FilterBase {
	private String rutEjecutiva;
	private Long idSemaforo;
	private Long idIndicador;
	private Long idProveedor;
	private String idMarca;
	private Date fecha;
	private Date fechaInicio;
	private Date fechaTermino;
	private Integer diasExigencia;
	private String fechaInicioString;
	private String fechaTerminoString;
	private String fechaString;
	private Indicador indicador;
	private Long idEjecutiva;
	private String tipoOT;
	private Long rut;
	private Long idFacturar;
	
	public Long getIdSemaforo() {
		return idSemaforo;
	}
	public void setIdSemaforo(Long idSemaforo) {
		this.idSemaforo = idSemaforo;
	}
	public Long getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}
	public String getRutEjecutiva() {
		return rutEjecutiva;
	}
	public void setRutEjecutiva(String rutEjecutiva) {
		this.rutEjecutiva = rutEjecutiva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Integer getDiasExigencia() {
		return diasExigencia;
	}
	public void setDiasExigencia(Integer diasExigencia) {
		this.diasExigencia = diasExigencia;
	}
	
	public String getFechaInicioString() {
		return fechaInicioString;
	}
	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}
	public String getFechaTerminoString() {
		return fechaTerminoString;
	}
	public void setFechaTerminoString(String fechaTerminoString) {
		this.fechaTerminoString = fechaTerminoString;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFechaString() {
		return fechaString;
	}
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	public Indicador getIndicador() {
		return indicador;
	}
	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}
	public Long getIdEjecutiva() {
		return idEjecutiva;
	}
	public void setIdEjecutiva(Long idEjecutiva) {
		this.idEjecutiva = idEjecutiva;
	}
	public String getTipoOT() {
		return tipoOT;
	}
	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}
	public Long getIdFacturar() {
		return idFacturar;
	}
	public void setIdFacturar(Long idFacturar) {
		this.idFacturar = idFacturar;
	}
	public Long getRut() {
		return rut;
	}
	public void setRut(Long rut) {
		this.rut = rut;
	}
}
