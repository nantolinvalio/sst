package cl.abcdin.sst.model.filters;

import cl.abcdin.sst.model.Usuario;

public class FilterColumna {
	private Usuario usuario;
	private Integer idSeccion;
	private String tipo;
	private Integer idRol;
	private Integer idIndicador;
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}
	public Integer getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
