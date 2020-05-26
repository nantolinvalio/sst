package cl.abcdin.sst.model;

import java.util.Date;

public class Producto extends TipoGenerico{
	
	private String descripcion;
	private Marca marca;
	private Linea idLinea;
	private Long cantidad;
	private Long precioVenta;
	private Date fechaEntrega;
	private Familia familia;
	private Integer duracionGarantia;
	private Boolean bigTicket;
	private Long idFactura;
	private Boolean enGarantiaProveedor;
	private Boolean cambioAutorizadoProveedor;
	private Boolean cambioPorFallaFabricacion;
	private Boolean productoParaEvaluacion;
	private Boolean cambioPorValor;
	private Boolean cambioCertificacionFalla;
	private String motivoGarantiaProveedor;
	private String motivoCambioAutorizadoProveedor;
	private String motivoCambioPorFallaFabricacion;
	private Boolean cambioMeson;
	private String motivoProductoParaEvaluacion;
	private String motivoCambioPorValor;
	private Proveedor proveedor;
	private Integer cantidadServiciosTecnicos;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long usuarioCreacion;
    private Long usuarioModificacion;
    private String vigente;
    private Long idp;
    private String motivoCambioMenor24h;
    private boolean cambioPor24h;
    
    
    
    
	
    
    
    


	public boolean isCambioPor24h() {
		return cambioPor24h;
	}

	public void setCambioPor24h(boolean cambioPor24h) {
		this.cambioPor24h = cambioPor24h;
	}

	public String getMotivoCambioMenor24h() {
		return motivoCambioMenor24h;
	}

	public void setMotivoCambioMenor24h(String motivoCambioMenor24h) {
		this.motivoCambioMenor24h = motivoCambioMenor24h;
	}

	public Long getIdp() {
		return idp;
	}

	public void setIdp(Long idp) {
		this.idp = idp;
	}

	public Linea getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(Linea idLinea) {
		this.idLinea = idLinea;
	}

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	public Long getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Long usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Long getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Long usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
    
	public String getMotivoGarantiaProveedor() {
		return motivoGarantiaProveedor;
	}
	public void setMotivoGarantiaProveedor(String motivoGarantiaProveedor) {
		this.motivoGarantiaProveedor = motivoGarantiaProveedor;
	}
	public String getMotivoCambioAutorizadoProveedor() {
		return motivoCambioAutorizadoProveedor;
	}
	public void setMotivoCambioAutorizadoProveedor(
			String motivoCambioAutorizadoProveedor) {
		this.motivoCambioAutorizadoProveedor = motivoCambioAutorizadoProveedor;
	}
	public String getMotivoCambioPorFallaFabricacion() {
		return motivoCambioPorFallaFabricacion;
	}
	public void setMotivoCambioPorFallaFabricacion(
			String motivoCambioPorFallaFabricacion) {
		this.motivoCambioPorFallaFabricacion = motivoCambioPorFallaFabricacion;
	}
	public String getMotivoCambioPorValor() {
		return motivoCambioPorValor;
	}
	public void setMotivoCambioPorValor(String motivoCambioPorValor) {
		this.motivoCambioPorValor = motivoCambioPorValor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Long getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Long precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	public Integer getDuracionGarantia() {
		return duracionGarantia;
	}
	public void setDuracionGarantia(Integer duracionGarantia) {
		this.duracionGarantia = duracionGarantia;
	}
	public Boolean getBigTicket() {
		return bigTicket;
	}
	public void setBigTicket(Boolean bigTicket) {
		this.bigTicket = bigTicket;
	}
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public Boolean getCambioPorValor() {
		return cambioPorValor;
	}
	public void setCambioPorValor(Boolean cambioPorValor) {
		this.cambioPorValor = cambioPorValor;
	}
	public Boolean getCambioPorFallaFabricacion() {
		return cambioPorFallaFabricacion;
	}
	public void setCambioPorFallaFabricacion(Boolean cambioPorFallaFabricacion) {
		this.cambioPorFallaFabricacion = cambioPorFallaFabricacion;
	}
	public Boolean getCambioAutorizadoProveedor() {
		return cambioAutorizadoProveedor;
	}
	public void setCambioAutorizadoProveedor(Boolean cambioAutorizadoProveedor) {
		this.cambioAutorizadoProveedor = cambioAutorizadoProveedor;
	}
	public Boolean getEnGarantiaProveedor() {
		return enGarantiaProveedor;
	}
	public void setEnGarantiaProveedor(Boolean enGarantiaProveedor) {
		this.enGarantiaProveedor = enGarantiaProveedor;
	}
	public Boolean getProductoParaEvaluacion() {
		return productoParaEvaluacion;
	}
	public void setProductoParaEvaluacion(Boolean productoParaEvaluacion) {
		this.productoParaEvaluacion = productoParaEvaluacion;
	}
	public String getMotivoProductoParaEvaluacion() {
		return motivoProductoParaEvaluacion;
	}
	public void setMotivoProductoParaEvaluacion(
			String motivoProductoParaEvaluacion) {
		this.motivoProductoParaEvaluacion = motivoProductoParaEvaluacion;
	}
	public Integer getCantidadServiciosTecnicos() {
		return cantidadServiciosTecnicos;
	}
	public void setCantidadServiciosTecnicos(Integer cantidadServiciosTecnicos) {
		this.cantidadServiciosTecnicos = cantidadServiciosTecnicos;
	}

	public Boolean getCambioCertificacionFalla() {
		return cambioCertificacionFalla;
	}

	public void setCambioCertificacionFalla(Boolean cambioCertificacionFalla) {
		this.cambioCertificacionFalla = cambioCertificacionFalla;
	}
	public Boolean getCambioMeson() {
		return cambioMeson;
	}

	public void setCambioMeson(Boolean cambioMeson) {
		this.cambioMeson = cambioMeson;
	}
	
}
