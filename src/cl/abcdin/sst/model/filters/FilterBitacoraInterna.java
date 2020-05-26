package cl.abcdin.sst.model.filters;

import java.util.Date;

public class FilterBitacoraInterna extends FilterBase{
	private Long idProveedor;
	private String idFamilia;
	private Date fechaCreacionCorte;
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public Date getFechaCreacionCorte() {
		return fechaCreacionCorte;
	}
	public void setFechaCreacionCorte(Date fechaCreacionCorte) {
		this.fechaCreacionCorte = fechaCreacionCorte;
	}
}
