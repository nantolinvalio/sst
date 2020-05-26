package cl.abcdin.sst.model;

import java.util.Date;

public class UbicacionInterna extends TipoGenerico{

	private TipoUbicacionInterna tipo;
	private String nombre;
	private Usuario usuario;
	private Date fechaInicio;
	private String descripcion;
	private Boolean inventario;
	private Boolean vigente;
	private Ubicacion ubicacionPadre;
	private Integer totalProductos; 
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getInventario() {
		return inventario;
	}
	public void setInventario(Boolean inventario) {
		this.inventario = inventario;
	}
	public TipoUbicacionInterna getTipo() {
		return tipo;
	}
	public void setTipo(TipoUbicacionInterna tipo) {
		this.tipo = tipo;
	}
    public Boolean getVigente() {
        return vigente;
    }
    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }
	public Ubicacion getUbicacionPadre() {
		return ubicacionPadre;
	}
	public void setUbicacionPadre(Ubicacion ubicacionPadre) {
		this.ubicacionPadre = ubicacionPadre;
	}
	public Integer getTotalProductos() {
		return totalProductos;
	}
	public void setTotalProductos(Integer totalProductos) {
		this.totalProductos = totalProductos;
	}
}
