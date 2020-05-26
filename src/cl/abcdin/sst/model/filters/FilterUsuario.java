package cl.abcdin.sst.model.filters;

public class FilterUsuario extends FilterBase {
	private Long id;
	private String nombre;
	private String run;
	private Long idUbicacion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	public Long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
}
