package cl.abcdin.sst.model;

import java.util.Date;

public class Cambio {
	
	private OrdenTrabajo ot;
	private Ubicacion ubicacion;
	private Transportista transportista;
	private Proveedor proveedor;
	private Comuna comuna;
	private String motivoCambio;
	private Long numeroCambio;
	private Date fechaCambioAutorizado;
	private String tipoFacturar;
	
	public OrdenTrabajo getOt() {
		return ot;
	}
	public void setOt(OrdenTrabajo ot) {
		this.ot = ot;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Transportista getTransportista() {
		return transportista;
	}
	public void setTranportista(Transportista tranportista) {
		this.transportista = tranportista;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	public String getMotivoCambio() {
		return motivoCambio;
	}
	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}
	public Long getNumeroCambio() {
		return numeroCambio;
	}
	public void setNumeroCambio(Long numeroCambio) {
		this.numeroCambio = numeroCambio;
	}
	public Date getFechaCambioAutorizado() {
		return fechaCambioAutorizado;
	}
	public void setFechaCambioAutorizado(Date fechaCambioAutorizado) {
		this.fechaCambioAutorizado = fechaCambioAutorizado;
	}
	public String getTipoFacturar() {
		return tipoFacturar;
	}
	public void setTipoFacturar(String tipoFacturar) {
		this.tipoFacturar = tipoFacturar;
	}
	
}
