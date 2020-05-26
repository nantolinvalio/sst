package cl.abcdin.sst.model;

public class Estado extends TipoGenerico{
	private String grupo;
	private Boolean vigente;
	private Estado estadoSiguiente;
	private String descripcion;
	private String icono;
	private String nombre;
	

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public Estado getEstadoSiguiente() {
		return estadoSiguiente;
	}

	public void setEstadoSiguiente(Estado estadoSiguiente) {
		this.estadoSiguiente = estadoSiguiente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
