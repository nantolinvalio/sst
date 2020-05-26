package cl.abcdin.sst.model;

import ar.com.fdvs.dj.domain.CustomExpression;

public class Columna {
	private Integer id;
	private String nombre;
	private String descripcion;
	private String alinear;
	private Integer ancho;
	private Integer orden;
	private String propiedad;
	private String columna;
	private String formato;
	private CustomExpression customExpression;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAlinear() {
		return alinear;
	}
	public void setAlinear(String alinear) {
		this.alinear = alinear;
	}
	public Integer getAncho() {
		return ancho;
	}
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}
	public String getColumna() {
		return columna;
	}
	public void setColumna(String columna) {
		this.columna = columna;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public CustomExpression getCustomExpression() {
		return customExpression;
	}
	public void setCustomExpression(CustomExpression customExpression) {
		this.customExpression = customExpression;
	}
	
	
}
