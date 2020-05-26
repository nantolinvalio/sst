package cl.abcdin.sst.model;

public class SeccionColumna {
	private Long id;
	private Seccion seccion;
	private Columna columna;
	private Usuario usuario;
	private Rol rol;
	private Indicador indicador;
	
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	public Columna getColumna() {
		return columna;
	}
	public void setColumna(Columna columna) {
		this.columna = columna;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Indicador getIndicador() {
		return indicador;
	}
	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	
}
