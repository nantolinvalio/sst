package cl.abcdin.sst.model;

import java.util.List;

public class Linea {
	private String id;
	private String codigo;
	private String nombre;
	private String glosa;
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

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	private List<Familia> familias;

	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
