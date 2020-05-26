package cl.abcdin.sst.model;

import java.util.Date;

public class RutaServicioTecnico extends TipoGenerico{
	private Usuario usuarioCreacion;
	private Date fechaCreacion;
	private Boolean vigente;
	private Date fechaModificacion;
	private Usuario usuarioModificacion;
	private String nombre;
	
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
}
