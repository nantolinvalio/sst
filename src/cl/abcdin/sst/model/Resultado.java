package cl.abcdin.sst.model;

public class Resultado {
	private Integer estado;
	private String parametro1;
	private Boolean conErrores;
	private String salida;
	private Long idUsuario;
	
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public Boolean getConErrores() {
		return conErrores;
	}
	public void setConErrores(Boolean conErrores) {
		this.conErrores = conErrores;
	}
	public String getParametro1() {
		return parametro1;
	}
	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
