package cl.abcdin.sst.model.filters;

import java.util.Date;

public class FilterReglaHistorica extends FilterBase{
	private Long idRegla;
	private Long idZona;
	private Long idTienda;
	private String idTipoAutorizacion;
	private Long idUsuario;
	private Boolean idEstado;
	private Boolean vigente;
	private String idLinea;
	private String idFamilia;
	private Long idProducto;
	private Date fechaInicioDesde;
	private Date fechaTerminoDesde;
	private Date fechaCreacionDesde;
	private Date fechaInicioHasta;
	private Date fechaTerminoHasta;
	private Date fechaCreacionHasta;
	
	public Long getIdRegla() {
		return idRegla;
	}
	public void setIdRegla(Long idRegla) {
		this.idRegla = idRegla;
	}
	public Long getIdZona() {
		return idZona;
	}
	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}
	public Long getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public String getIdTipoAutorizacion() {
		return idTipoAutorizacion;
	}
	public void setIdTipoAutorizacion(String idTipoAutorizacion) {
		this.idTipoAutorizacion = idTipoAutorizacion;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Boolean getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Boolean idEstado) {
		this.idEstado = idEstado;
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
	public Date getFechaInicioDesde() {
		return fechaInicioDesde;
	}
	public void setFechaInicioDesde(Date fechaInicioDesde) {
		this.fechaInicioDesde = fechaInicioDesde;
	}
	public Date getFechaTerminoDesde() {
		return fechaTerminoDesde;
	}
	public void setFechaTerminoDesde(Date fechaTerminoDesde) {
		this.fechaTerminoDesde = fechaTerminoDesde;
	}
	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}
	public Date getFechaInicioHasta() {
		return fechaInicioHasta;
	}
	public void setFechaInicioHasta(Date fechaInicioHasta) {
		this.fechaInicioHasta = fechaInicioHasta;
	}
	public Date getFechaTerminoHasta() {
		return fechaTerminoHasta;
	}
	public void setFechaTerminoHasta(Date fechaTerminoHasta) {
		this.fechaTerminoHasta = fechaTerminoHasta;
	}
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
