package cl.abcdin.sst.model.filters;

import java.util.Date;


public class FilterRegla extends FilterBase{
	private Long idRegla;
	private Long idReglaHistorica;
	private Long idZona;
	private Long idTienda;
	private String idLinea;
	private String idFamilia;
	private Long idProducto;
	private Date fechaInicio;
	private Date fechaTermino;
	private String idTipoAutorizacion;
	private Boolean vigente;

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

	public Long getIdReglaHistorica() {
		return idReglaHistorica;
	}

	public void setIdReglaHistorica(Long idReglaHistorica) {
		this.idReglaHistorica = idReglaHistorica;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdTipoAutorizacion() {
		return idTipoAutorizacion;
	}

	public void setIdTipoAutorizacion(String idTipoAutorizacion) {
		this.idTipoAutorizacion = idTipoAutorizacion;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
