package cl.abcdin.sst.model;

import java.util.Date;

public class InventarioProducto extends TipoGenerico{
	private InventarioUbicacion inventarioUbicacion;
	private OrdenTrabajo ordenTrabajo;
	private Boolean preparado;
	private Boolean inventariado;
	private Date fechaInventario;
	private Usuario usuarioInventario;
	private Ubicacion ubicacion;
	private UbicacionInterna ubicacionInterna;
	
	public InventarioUbicacion getInventarioUbicacion() {
		return inventarioUbicacion;
	}
	public void setInventarioUbicacion(InventarioUbicacion inventarioUbicacion) {
		this.inventarioUbicacion = inventarioUbicacion;
	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Boolean getPreparado() {
		return preparado;
	}
	public void setPreparado(Boolean preparado) {
		this.preparado = preparado;
	}
	public Boolean getInventariado() {
		return inventariado;
	}
	public void setInventariado(Boolean inventariado) {
		this.inventariado = inventariado;
	}
	public Date getFechaInventario() {
		return fechaInventario;
	}
	public void setFechaInventario(Date fechaInventario) {
		this.fechaInventario = fechaInventario;
	}
	public Usuario getUsuarioInventario() {
		return usuarioInventario;
	}
	public void setUsuarioInventario(Usuario usuarioInventario) {
		this.usuarioInventario = usuarioInventario;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public UbicacionInterna getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(UbicacionInterna ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
}
