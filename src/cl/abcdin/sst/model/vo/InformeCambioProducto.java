package cl.abcdin.sst.model.vo;

import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.Usuario;

public class InformeCambioProducto {
	private OrdenTrabajo ordenTrabajo;
	private List<TipoFallas> tipoFallas;
	private Date fecha;
	private Usuario usuario;
	
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public List<TipoFallas> getTipoFallas() {
		return tipoFallas;
	}
	public void setTipoFallas(List<TipoFallas> tipoFallas) {
		this.tipoFallas = tipoFallas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
