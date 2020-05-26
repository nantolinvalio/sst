package cl.abcdin.sst.model.filters;

public class FilterUbicacion extends FilterBase {
	private Long idComuna;
	private Long idRegion;
	private Long idUbicacion;
	private Long id;
	private String tipo;
	private String rut;
	private String nombre;
	private Boolean vigente;
	

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

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public Long getIdComuna() {
		return idComuna;
	}

	public void setIdComuna(Long idComuna) {
		this.idComuna = idComuna;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	
}
