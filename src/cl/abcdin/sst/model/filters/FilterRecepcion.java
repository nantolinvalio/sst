package cl.abcdin.sst.model.filters;

import java.util.Date;

import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Usuario;

public class FilterRecepcion  extends FilterBase{
	private String responsable;
	private Date FechaTermino;
	private Long transportista;
	private Integer idRecepcion;
	private Estado estado;
	private Usuario usuarioRecepcion;
	private Integer numeroGuia;
	private Estado estadoRecepcionGuia;
	private Estado estadoActualGuia;
	private Estado estadoRecepcionOT;
	private Long idZona;
	private Long idSucursal;
	private Long idOT;
	private String idLinea;
	private String idFamilia;
	private Long idProducto;
	private String idMarca;
	private String numeroSerie;
	private String codigoBarras;
	private Date fechaRecepcionInicio;
	private Date fechaCreacion;
	private Date fechaRecepcionFin;
	private Date fechaCreacionFin;
	private Estado estadoOT;
	private Long idGuia;
	
	public Long getTransportista() {
		return transportista;
	}
	public void setTransportista(Long transportista) {
		this.transportista = transportista;
	}
	public Integer getIdRecepcion() {
		return idRecepcion;
	}
	public void setIdRecepcion(Integer idRecepcion) {
		this.idRecepcion = idRecepcion;
	}
	public Usuario getUsuarioRecepcion() {
		return usuarioRecepcion;
	}
	public void setUsuarioRecepcion(Usuario usuarioRecepcion) {
		this.usuarioRecepcion = usuarioRecepcion;
	}
	public Integer getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(Integer numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public Estado getEstadoRecepcionGuia() {
		return estadoRecepcionGuia;
	}
	public void setEstadoRecepcionGuia(Estado estadoRecepcionGuia) {
		this.estadoRecepcionGuia = estadoRecepcionGuia;
	}
	public Estado getEstadoActualGuia() {
		return estadoActualGuia;
	}
	public void setEstadoActualGuia(Estado estadoActualGuia) {
		this.estadoActualGuia = estadoActualGuia;
	}
	public Estado getEstadoRecepcionOT() {
		return estadoRecepcionOT;
	}
	public void setEstadoRecepcionOT(Estado estadoRecepcionOT) {
		this.estadoRecepcionOT = estadoRecepcionOT;
	}
	public Long getIdZona() {
		return idZona;
	}
	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public String getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(String idLinea) {
		this.idLinea = idLinea;
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
	public String getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Date getFechaRecepcionInicio() {
		return fechaRecepcionInicio;
	}
	public void setFechaRecepcionInicio(Date fechaRecepcionInicio) {
		this.fechaRecepcionInicio = fechaRecepcionInicio;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaRecepcionFin() {
		return fechaRecepcionFin;
	}
	public void setFechaRecepcionFin(Date fechaRecepcionFin) {
		this.fechaRecepcionFin = fechaRecepcionFin;
	}
	public Date getFechaCreacionFin() {
		return fechaCreacionFin;
	}
	public void setFechaCreacionFin(Date fechaCreacionFin) {
		this.fechaCreacionFin = fechaCreacionFin;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public Date getFechaTermino() {
		return FechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		FechaTermino = fechaTermino;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Estado getEstadoOT() {
		return estadoOT;
	}
	public void setEstadoOT(Estado estadoOT) {
		this.estadoOT = estadoOT;
	}
	public Long getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Long idGuia) {
		this.idGuia = idGuia;
	}
	
	
}
