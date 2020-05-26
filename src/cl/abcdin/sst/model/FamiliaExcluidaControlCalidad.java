package cl.abcdin.sst.model;

import java.util.Date;


public class FamiliaExcluidaControlCalidad {
	private Long id;
	private Familia familia;
	private Usuario usuario;
	private Date fechaCreacion;
	private Boolean excluida;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getExcluida() {
		return excluida;
	}
	public void setExcluida(Boolean excluida) {
		this.excluida = excluida;
	}
}
