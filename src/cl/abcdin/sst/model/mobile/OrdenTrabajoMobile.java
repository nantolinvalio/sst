package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;

public class OrdenTrabajoMobile {
	private Long id;
	private String codigoBarras;
	private String producto;
	private String marca;
	private String familia;
	private String linea;
	private String clasificacion;
	private String ubicacionInterna;
	private String proveedor;
	private String sucursal; 
	private String ruta;
	private String etapa;
	private String fechaCreacion;
	private String numeroContrato;
	private String servicioTecnico;
	private Integer idEstadoDespachoDetalle;
	private String numeroSerie;
	private Boolean cCalidadAprueba;
	
	@XmlElement(nillable = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(nillable = true)
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	@XmlElement(nillable = true)
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	@XmlElement(nillable = true)
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@XmlElement(nillable = true)
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	
	@XmlElement(nillable = true)
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	@XmlElement(nillable = true)
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	@XmlElement(nillable = true)
	public String getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(String ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
	
	@XmlElement(nillable = true)
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	@XmlElement(nillable = true)
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	@XmlElement(nillable = true)
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	@XmlElement(nillable = true)
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	@XmlElement(nillable = true)
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@XmlElement(nillable = true)
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	
	@XmlElement(nillable = true)
	public String getServicioTecnico() {
		return servicioTecnico;
	}
	public void setServicioTecnico(String servicioTecnico) {
		this.servicioTecnico = servicioTecnico;
	}
	
	@XmlElement(nillable = true)
	public Integer getIdEstadoDespachoDetalle() {
		return idEstadoDespachoDetalle;
	}
	public void setIdEstadoDespachoDetalle(Integer idEstadoDespachoDetalle) {
		this.idEstadoDespachoDetalle = idEstadoDespachoDetalle;
	}
	
	@XmlElement(nillable = true)
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Boolean getcCalidadAprueba() {
		return cCalidadAprueba;
	}
	public void setcCalidadAprueba(Boolean cCalidadAprueba) {
		this.cCalidadAprueba = cCalidadAprueba;
	}
}
