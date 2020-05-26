package cl.abcdin.sst.model.filters;

import java.util.Date;

import cl.abcdin.sst.model.OrdenTrabajo;

public class FilterGuia extends FilterBase {
	private String tipoDocumento;
	private Long idDocumento;
	private Long idSucursal;
	private Long idUsuario;
	private Long estadoOT;
	private Long idSTecnico;
	private Date fechaRecepcionOtInicio;
	private Date fechaRecepcionOtFin;
	private Long idOT;
	private Boolean oTCerrada;
	private Boolean oTVigente;
	private Boolean guiaVigente;
	private Long idOrigen;
	private OrdenTrabajo ordenTrabajo;
	private Long numero;
	private Long idGuia;
	private Long idProducto;
	private Long idEtapa;
	private String idFamilia;
	private String idMarca;
	private String tipoOT;
	private String tipoGuia;
	private Boolean enviarSinGuia;
	private Boolean paraEnvioSinGuia; //para seleccionar las guias que pueden ser emitidas en menu "enviar sin guia"
	
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Long getEstadoOT() {
		return estadoOT;
	}
	public void setEstadoOT(Long estadoOT) {
		this.estadoOT = estadoOT;
	}
	public Long getIdSTecnico() {
		return idSTecnico;
	}
	public void setIdSTecnico(Long idSTecnico) {
		this.idSTecnico = idSTecnico;
	}
	public Date getFechaRecepcionOtInicio() {
		return fechaRecepcionOtInicio;
	}
	public void setFechaRecepcionOtInicio(Date fechaRecepcionOtInicio) {
		this.fechaRecepcionOtInicio = fechaRecepcionOtInicio;
	}
	public Date getFechaRecepcionOtFin() {
		return fechaRecepcionOtFin;
	}
	public void setFechaRecepcionOtFin(Date fechaRecepcionOtFin) {
		this.fechaRecepcionOtFin = fechaRecepcionOtFin;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Long getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Boolean getoTCerrada() {
		return oTCerrada;
	}
	public void setoTCerrada(Boolean oTCerrada) {
		this.oTCerrada = oTCerrada;
	}
	public Boolean getoTVigente() {
		return oTVigente;
	}
	public void setoTVigente(Boolean oTVigente) {
		this.oTVigente = oTVigente;
	}
	public Boolean getGuiaVigente() {
		return guiaVigente;
	}
	public void setGuiaVigente(Boolean guiaVigente) {
		this.guiaVigente = guiaVigente;
	}
	public Long getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}
	public String getTipoOT() {
		return tipoOT;
	}
	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTipoGuia() {
		return tipoGuia;
	}
	public void setTipoGuia(String tipoGuia) {
		this.tipoGuia = tipoGuia;
	}
	public Long getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Long idGuia) {
		this.idGuia = idGuia;
	}
	public Boolean getEnviarSinGuia() {
		return enviarSinGuia;
	}
	public void setEnviarSinGuia(Boolean enviarSinGuia) {
		this.enviarSinGuia = enviarSinGuia;
	}
	public Boolean getParaEnvioSinGuia() {
		return paraEnvioSinGuia;
	}
	public void setParaEnvioSinGuia(Boolean paraEnvioSinGuia) {
		this.paraEnvioSinGuia = paraEnvioSinGuia;
	}
	
}
