package cl.abcdin.sst.model.vo;

public class OWCall {
	private Long idEntrada;
	private Integer estado;
	private String salida;
	private Integer idSalida;
	private String usuario;
	private String mcu;
	
	public Long getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}
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
	public Integer getIdSalida() {
		return idSalida;
	}
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getMcu() {
		return mcu;
	}
	public void setMcu(String mcu) {
		this.mcu = mcu;
	}
	
	
	
}
