package cl.abcdin.sst.model.vo;

public class Modulo {
	private Long id;//	m.id_modulo,
	private String codigo;//    m.s_codigo,
	private String nombre;//    m.s_nombre,
	private String macro;//    m.s_macro,
	private String tipo;//    m.s_tipo,
	private String etiqueta;//    m.s_etiqueta,
	private Long orden;//    m.i_orden,
	private String icono;//    m.s_icono,
	private Modulo padre;//    m.i_modulo_padre,
	private Boolean migrado;//    m.s_migrado
	private String descripcion;
	private Integer grupo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getMacro() {
		return macro;
	}
	public void setMacro(String macro) {
		this.macro = macro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public Modulo getPadre() {
		return padre;
	}
	public void setPadre(Modulo padre) {
		this.padre = padre;
	}
	public Boolean getMigrado() {
		return migrado;
	}
	public void setMigrado(Boolean migrado) {
		this.migrado = migrado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
	
}
