package cl.abcdin.sst.model;

import java.util.Date;

public class TiendaZona {
	private Ubicacion tienda;
	private Zona zona;
	private Usuario usuario;
	private Date fechaCreacion;
	
	public Ubicacion getTienda() {
		return tienda;
	}
	public void setTienda(Ubicacion tienda) {
		this.tienda = tienda;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
