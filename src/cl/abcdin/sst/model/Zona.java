package cl.abcdin.sst.model;

import java.util.Date;
import java.util.List;

public class Zona {
	private Long id;
	private String codigo;
	private String nombre;
	private Boolean vigente;
	private Date fechaCreacion;
	private Usuario usuarioCreacion;
	private Date fechaModificacion;
	private Usuario usuarioModificacion;
	private List<TiendaZona> tiendas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public List<TiendaZona> getTiendas() {
		return tiendas;
	}
	public void setTiendas(List<TiendaZona> tiendas) {
		this.tiendas = tiendas;
	}
	
}
