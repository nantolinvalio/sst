package cl.abcdin.sst.model;

import java.util.Date;

public class Inventario extends TipoGenerico{
	private Ubicacion ubicacion;
	private Date fechaCreacion;
	private Date fechaCierre;
	private Usuario usuarioCreacion;
	private Usuario usuarioCierre;
	private Boolean vigente;
	private Boolean estado;
	private Integer productosPreparados;
	private Integer productosSobrantes;
	private Integer productosFaltantes;
	private Integer productosContados;
	private Integer sinDiferencia;
	private Double brecha; 
	
	public Integer getProductosPreparados() {
		return productosPreparados;
	}
	public void setProductosPreparados(Integer productosPreparados) {
		this.productosPreparados = productosPreparados;
	}
	public Integer getProductosSobrantes() {
		return productosSobrantes;
	}
	public void setProductosSobrantes(Integer productosSobrantes) {
		this.productosSobrantes = productosSobrantes;
	}
	public Integer getProductosFaltantes() {
		return productosFaltantes;
	}
	public void setProductosFaltantes(Integer productosFaltantes) {
		this.productosFaltantes = productosFaltantes;
	}
	public Integer getProductosContados() {
		return productosContados;
	}
	public void setProductosContados(Integer productosContados) {
		this.productosContados = productosContados;
	}
	public Integer getSinDiferencia() {
		return sinDiferencia;
	}
	public void setSinDiferencia(Integer sinDiferencia) {
		this.sinDiferencia = sinDiferencia;
	}
	public Double getBrecha() {
		return brecha;
	}
	public void setBrecha(Double brecha) {
		this.brecha = brecha;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Usuario getUsuarioCierre() {
		return usuarioCierre;
	}
	public void setUsuarioCierre(Usuario usuarioCierre) {
		this.usuarioCierre = usuarioCierre;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
