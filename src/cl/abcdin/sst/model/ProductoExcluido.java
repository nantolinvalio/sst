package cl.abcdin.sst.model;

import java.util.Date;

public class ProductoExcluido extends Producto{
	private Usuario usuarioExclusion;
	private Date fechaCreacion;
	private Long idProductoExcluido;
	private Boolean excluido;
	
	public Usuario getUsuarioExclusion() {
		return usuarioExclusion;
	}
	public void setUsuarioExclusion(Usuario usuarioExclusion) {
		this.usuarioExclusion = usuarioExclusion;
	}
	
	public Long getIdProductoExcluido() {
		return idProductoExcluido;
	}
	public void setIdProductoExcluido(Long idProductoExcluido) {
		this.idProductoExcluido = idProductoExcluido;
	}
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
	public Boolean getExcluido() {
		return excluido;
	}
	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
}
