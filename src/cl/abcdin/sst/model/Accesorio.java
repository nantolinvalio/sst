package cl.abcdin.sst.model;

public class Accesorio extends TipoGenerico{
	private String descripcion;
	private Ubicacion ubicacion;
	private Ubicacion destinoCambio;
	private Boolean recibidoCd;
	private Boolean requerido;
	private Boolean recibidoCCalidad;
	private Boolean recibidoDestino;
	private Boolean vigente;
	private String tipo;
	private Producto producto;
	private Long idOT;
	private String codigoBarra;
	private Boolean recibido;
	private Ubicacion ultimaUbicacion;
	private Guia guia;
	private Integer numeroFallas;
	private Familia familia;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Boolean getRecibidoCd() {
		return recibidoCd;
	}
	public void setRecibidoCd(Boolean recibidoCd) {
		this.recibidoCd = recibidoCd;
	}
	public Boolean getRequerido() {
		return requerido;
	}
	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}
	public Boolean getRecibidoCCalidad() {
		return recibidoCCalidad;
	}
	public void setRecibidoCCalidad(Boolean recibidoCCalidad) {
		this.recibidoCCalidad = recibidoCCalidad;
	}
	public Boolean getRecibidoDestino() {
		return recibidoDestino;
	}
	public void setRecibidoDestino(Boolean recibidoDestino) {
		this.recibidoDestino = recibidoDestino;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Boolean getRecibido() {
		return recibido;
	}
	public void setRecibido(Boolean recibido) {
		this.recibido = recibido;
	}
	public Ubicacion getDestinoCambio() {
		return destinoCambio;
	}
	public void setDestinoCambio(Ubicacion destinoCambio) {
		this.destinoCambio = destinoCambio;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public Ubicacion getUltimaUbicacion() {
		return ultimaUbicacion;
	}
	public void setUltimaUbicacion(Ubicacion ultimaUbicacion) {
		this.ultimaUbicacion = ultimaUbicacion;
	}
	
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Integer getNumeroFallas() {
		return numeroFallas;
	}
	public void setNumeroFallas(Integer numeroFallas) {
		this.numeroFallas = numeroFallas;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	
}
