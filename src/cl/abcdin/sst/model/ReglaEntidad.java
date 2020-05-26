package cl.abcdin.sst.model;

public class ReglaEntidad<T> {
	private Long id;
	private ReglaComercial reglaComercial;
	private String entidadRegla;
	private T idEntidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ReglaComercial getReglaComercial() {
		return reglaComercial;
	}
	public void setReglaComercial(ReglaComercial reglaComercial) {
		this.reglaComercial = reglaComercial;
	}
	public String getEntidadRegla() {
		return entidadRegla;
	}
	public void setEntidadRegla(String entidadRegla) {
		this.entidadRegla = entidadRegla;
	}
	public T getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(T idEntidad) {
		this.idEntidad = idEntidad;
	}
	
	
}
