package cl.abcdin.sst.model;

import java.io.Serializable;

public class Prts implements Serializable{
	private static final long serialVersionUID = -6667251242030807779L;
	
	private Long sid;
	private String codigo;
	private String valor;
	private String descripcion;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
