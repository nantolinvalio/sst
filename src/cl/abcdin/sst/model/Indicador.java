package cl.abcdin.sst.model;

public class Indicador {
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private String tipo;
	private Double valor;
	private Integer cantidad;
	private Long rangoMinimo;
	private Long rangoMaximo;
	private String grupo;
	private String subGrupo;
	private Double tiempoInicio;
	private Double tiempoTermino;
	
	public Indicador(){}
	
	public Indicador(Integer id, Integer cantidad){
		this.id = id;
		this.cantidad = cantidad;
	}
	
	public Indicador(Integer id, Double valor){
		this.id = id;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public Long getRangoMaximo() {
		return rangoMaximo;
	}

	public void setRangoMaximo(Long rangoMaximo) {
		this.rangoMaximo = rangoMaximo;
	}

	public Long getRangoMinimo() {
		return rangoMinimo;
	}

	public void setRangoMinimo(Long rangoMinimo) {
		this.rangoMinimo = rangoMinimo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(String subGrupo) {
		this.subGrupo = subGrupo;
	}

	public Double getTiempoTermino() {
		return tiempoTermino;
	}

	public void setTiempoTermino(Double tiempoTermino) {
		this.tiempoTermino = tiempoTermino;
	}

	public Double getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(Double tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
}
