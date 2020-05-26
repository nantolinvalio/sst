package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.ServicioTecnico;

public class ServicioTecnicoProducto {
	private ServicioTecnico servicioTecnico;
	private Producto producto;
	private Long id;
	private String tipoGarantia;
	private Boolean vigente;
	
	public ServicioTecnico getServicioTecnico() {
		return servicioTecnico;
	}
	public void setServicioTecnico(ServicioTecnico servicioTecnico) {
		this.servicioTecnico = servicioTecnico;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoGarantia() {
		return tipoGarantia;
	}
	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	
}
