package cl.abcdin.sst.model.filters;

import java.util.Date;

public class FilterInventario extends FilterBase{
	private Integer idUbicacionInterna;
	private Date fechaCreacionDesde;
	private Date fechaCreacionHasta;
	private Date fechaCierreDesde;
	private Date fechaCierreHasta;
	private Boolean vigente;
	private Integer idInventario;
	private Integer idInventarioUbicacion;
	private Long idOT;

	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}
	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}
	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}
	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}
	public Date getFechaCierreDesde() {
		return fechaCierreDesde;
	}
	public void setFechaCierreDesde(Date fechaCierreDesde) {
		this.fechaCierreDesde = fechaCierreDesde;
	}
	public Date getFechaCierreHasta() {
		return fechaCierreHasta;
	}
	public void setFechaCierreHasta(Date fechaCierreHasta) {
		this.fechaCierreHasta = fechaCierreHasta;
	}
	public Integer getIdUbicacionInterna() {
		return idUbicacionInterna;
	}
	public void setIdUbicacionInterna(Integer idUbicacionInterna) {
		this.idUbicacionInterna = idUbicacionInterna;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Integer getIdInventario() {
		return idInventario;
	}
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}
	public Integer getIdInventarioUbicacion() {
		return idInventarioUbicacion;
	}
	public void setIdInventarioUbicacion(Integer idInventarioUbicacion) {
		this.idInventarioUbicacion = idInventarioUbicacion;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
}
