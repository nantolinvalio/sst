package cl.abcdin.sst.model;

public class Parametro extends TipoGenerico {
	private String nombre;
	private String grupo;
	private String codigo;
	
	private Long integer1;
	private Long integer2;
	private Long integer3;
	private Long integer4;
	
	public Long getInteger1() {
		return integer1;
	}
	public void setInteger1(Long integer1) {
		this.integer1 = integer1;
	}
	public Long getInteger2() {
		return integer2;
	}
	public void setInteger2(Long integer2) {
		this.integer2 = integer2;
	}
	public Long getInteger3() {
		return integer3;
	}
	public void setInteger3(Long integer3) {
		this.integer3 = integer3;
	}
	public Long getInteger4() {
		return integer4;
	}
	public void setInteger4(Long integer4) {
		this.integer4 = integer4;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
