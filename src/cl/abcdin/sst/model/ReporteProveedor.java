package cl.abcdin.sst.model;

import java.util.Date;

public class ReporteProveedor {

	private Proveedor proveedor;
	private Producto producto;
	private Marca marca;
	private Cliente cliente;
	private Date fechaVenta;
	private Ubicacion ubicacion;
	private Integer cantidadFallas;
	private String fallas;
	private Integer totalVenta;
	private Integer cantidadFF;
	private Integer cantidadST;
	private Integer cantidadOT;
	private Double factorFF;
	private Double factorST;
	private Double factorOT;
	private OrdenTrabajo ordenTrabajo;
	
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public String getFallas() {
		return fallas;
	}
	public void setFallas(String fallas) {
		this.fallas = fallas;
	}
	public Integer getTotalVenta() {
		return totalVenta;
	}
	public void setTotalVenta(Integer totalVenta) {
		this.totalVenta = totalVenta;
	}
	public Integer getCantidadFF() {
		return cantidadFF;
	}
	public void setCantidadFF(Integer cantidadFF) {
		this.cantidadFF = cantidadFF;
	}
	
//	public Integer getCantidadTotal() {
//		return cantidadTotal;
//	}
//	public void setCantidadTotal(Integer cantidadTotal) {
//		this.cantidadTotal = cantidadTotal;
//	}
	public Double getFactorFF() {
		return factorFF;
	}
	public void setFactorFF(Double factorFF) {
		this.factorFF = factorFF;
	}
	
	public Integer getCantidadFallas() {
		return cantidadFallas;
	}
	public void setCantidadFallas(Integer cantidadFallas) {
		this.cantidadFallas = cantidadFallas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Integer getCantidadST() {
		return cantidadST;
	}
	public void setCantidadST(Integer cantidadST) {
		this.cantidadST = cantidadST;
	}
	public Double getFactorST() {
		return factorST;
	}
	public void setFactorST(Double factorST) {
		this.factorST = factorST;
	}
	public Integer getCantidadOT() {
		return cantidadOT;
	}
	public void setCantidadOT(Integer cantidadOT) {
		this.cantidadOT = cantidadOT;
	}
	public Double getFactorOT() {
		return factorOT;
	}
	public void setFactorOT(Double factorOT) {
		this.factorOT = factorOT;
	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	
}
