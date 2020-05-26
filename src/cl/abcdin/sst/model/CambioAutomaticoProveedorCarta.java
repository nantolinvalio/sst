package cl.abcdin.sst.model;

import java.util.Date;

public class CambioAutomaticoProveedorCarta {
	private Long id;
	private String numeroCertificado;
	private String numeroSerie;
	private Producto producto;
	private Boolean vigente;
	private Date fechaCreacion;
	private Usuario usuario;
	private Boolean entregaCliente;
	private Boolean enProceso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroCertificado() {
		return numeroCertificado;
	}
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getEntregaCliente() {
		return entregaCliente;
	}
	public void setEntregaCliente(Boolean entregaCliente) {
		this.entregaCliente = entregaCliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Boolean getEnProceso() {
		return enProceso;
	}
	public void setEnProceso(Boolean enProceso) {
		this.enProceso = enProceso;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	
	
	
}
