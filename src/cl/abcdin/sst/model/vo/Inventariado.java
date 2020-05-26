package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Inventario;
import cl.abcdin.sst.model.InventarioProducto;
import cl.abcdin.sst.model.InventarioUbicacion;

public class Inventariado extends Inventario{
	
	private Inventario inventario;
	private InventarioProducto inventarioProducto;
	private InventarioUbicacion inventarioubicacion;
	
	public Inventario getInventario() {
		return inventario;
	}
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	public InventarioProducto getInventarioProducto() {
		return inventarioProducto;
	}
	public void setInventarioProducto(InventarioProducto inventarioProducto) {
		this.inventarioProducto = inventarioProducto;
	}
	public InventarioUbicacion getInventarioubicacion() {
		return inventarioubicacion;
	}
	public void setInventarioubicacion(InventarioUbicacion inventarioubicacion) {
		this.inventarioubicacion = inventarioubicacion;
	}
	
	
}
