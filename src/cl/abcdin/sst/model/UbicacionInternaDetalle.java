package cl.abcdin.sst.model;

public class UbicacionInternaDetalle extends TipoGenerico{
	private UbicacionInterna ubicacionInterna;
	private Producto producto;
	private Familia familia;
	private Linea linea;
	private Sucursal sucursal;
	
	public UbicacionInterna getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(UbicacionInterna ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
