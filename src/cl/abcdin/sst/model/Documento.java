package cl.abcdin.sst.model;

import java.util.Date;
import java.util.List;

public class Documento extends TipoGenerico{
	private String tipo;
	private Date fechaEmision;
	private Sucursal sucursal;
	private Long idCliente;
	private String telefono;
	private String tipoOW;
	private List<Producto> productos;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public String getTipoOW() {
		return tipoOW;
	}
	public void setTipoOW(String tipoOW) {
		this.tipoOW = tipoOW;
	}
	
	
}
