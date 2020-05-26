package cl.abcdin.sst.model.vo;

import java.util.Date;

import cl.abcdin.sst.model.Transportista;
import cl.abcdin.sst.model.Usuario;

public class GuiaPendienteAgrupada extends GuiaPendiente {
	private Integer cantidadProductos;
	private Integer cantidadOT;
	private Date fechaEmision;
	private Transportista transportista;
	private Usuario usuario;
	
	public Integer getCantidadProductos() {
		return cantidadProductos;
	}
	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	public Integer getCantidadOT() {
		return cantidadOT;
	}
	public void setCantidadOT(Integer cantidadOT) {
		this.cantidadOT = cantidadOT;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Transportista getTransportista() {
		return transportista;
	}
	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
