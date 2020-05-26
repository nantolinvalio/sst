package cl.abcdin.sst.model.filters;

import java.util.Date;

import cl.abcdin.sst.model.DiasTramos;

public class FilterFactura extends FilterBase{
	private Long idEjecutiva;
	private Long idProveedor;
	private Long idTransportista;
	private Long idFacturar;
	private Long idOT;
	private Long codigoCorto;
	private Long rut;
	private Long idIndicador;
	private String idFamilia;
	private String idMarca;
	private String tipoOT;
	private String tipoFacturado;
	private Date fecha;
	private Date fechaInicio;
	private Date fechaTermino;
	private DiasTramos diasTramos;
	private Integer idProducto;
	
	public Long getIdEjecutiva() {
		return idEjecutiva;
	}
	public void setIdEjecutiva(Long idEjecutiva) {
		this.idEjecutiva = idEjecutiva;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public Long getIdTransportista() {
		return idTransportista;
	}
	public void setIdTransportista(Long idTransportista) {
		this.idTransportista = idTransportista;
	}
	public Long getIdFacturar() {
		return idFacturar;
	}
	public void setIdFacturar(Long idFacturar) {
		this.idFacturar = idFacturar;
	}
	public String getTipoOT() {
		return tipoOT;
	}
	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}
	public String getTipoFacturado() {
		return tipoFacturado;
	}
	public void setTipoFacturado(String tipoFacturado) {
		this.tipoFacturado = tipoFacturado;
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
	public Long getRut() {
		return rut;
	}
	public void setRut(Long rut) {
		this.rut = rut;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
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
	public Long getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public DiasTramos getDiasTramos() {
		return diasTramos;
	}
	public void setDiasTramos(DiasTramos diasTramos) {
		this.diasTramos = diasTramos;
	}
	public Long getCodigoCorto() {
		return codigoCorto;
	}
	public void setCodigoCorto(Long codigoCorto) {
		this.codigoCorto = codigoCorto;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
}
