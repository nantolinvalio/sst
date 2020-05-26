package cl.abcdin.sst.model.filters;

import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;

public class FilterDespachoInterno extends FilterBase{
	private Long idProveedor;
	private Date fechaCreacionDesde;
	private Date fechaCreacionHasta;
	private String idFamilia;
	private Long idEstado;
	private Long idDespacho;
	private Long idOT;
	private Long idOrigen;
	private List<OrdenTrabajo> idOts;
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
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
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
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
	public Long getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	public List<OrdenTrabajo> getIdOts() {
		return idOts;
	}
	public void setIdOts(List<OrdenTrabajo> idOts) {
		this.idOts = idOts;
	}
	
	
}
