package cl.abcdin.sst.model;

public class TipoFallas extends TipoGenerico{
	private Producto producto;
	private String descripcion;
	private Boolean vigente;
	private String tipo;
	private Accesorio accesorio;
	private Long idFallaAccesorio;
	private Long idTipoFallaOT;
	private Integer idOT;
	private Familia familia;
	private Integer cantidad;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Accesorio getAccesorio() {
		return accesorio;
	}
	public void setAccesorio(Accesorio accesorio) {
		this.accesorio = accesorio;
	}
	public Long getIdFallaAccesorio() {
		return idFallaAccesorio;
	}
	public void setIdFallaAccesorio(Long idFallaAccesorio) {
		this.idFallaAccesorio = idFallaAccesorio;
	}
	public Long getIdTipoFallaOT() {
		return idTipoFallaOT;
	}
	public void setIdTipoFallaOT(Long idTipoFallaOT) {
		this.idTipoFallaOT = idTipoFallaOT;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	public Integer getIdOT() {
		return idOT;
	}
	public void setIdOT(Integer idOT) {
		this.idOT = idOT;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
