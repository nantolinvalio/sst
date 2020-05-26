package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Producto;

public class ProductoReport {
	private Producto producto;
	private Integer cantidadAccesorios;
	private Integer cantidadOT;
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidadAccesorios() {
		return cantidadAccesorios;
	}
	public void setCantidadAccesorios(Integer cantidadAccesorios) {
		this.cantidadAccesorios = cantidadAccesorios;
	}
	public Integer getCantidadOT() {
		return cantidadOT;
	}
	public void setCantidadOT(Integer cantidadOT) {
		this.cantidadOT = cantidadOT;
	}
}
