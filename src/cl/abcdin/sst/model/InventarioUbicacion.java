package cl.abcdin.sst.model;

import java.util.Date;

public class InventarioUbicacion extends TipoGenerico{
	private Inventario inventario;
	private UbicacionInterna ubicacionInterna;
	private Integer productosPreparados;
	private Integer productosSobrantes;
	private Integer productosFaltantes;
	private Integer productosContados;
	private Double brecha;
	private Date fechaInicio;
	private Date fechaTermino;
	private Integer sinDiferencia;
	private Boolean terminada;
	
	public Inventario getInventario() {
		return inventario;
	}
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	public UbicacionInterna getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(UbicacionInterna ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Integer getProductosContados() {
		return productosContados;
	}
	public void setProductosContados(Integer productosContados) {
		this.productosContados = productosContados;
	}
	public Boolean getTerminada() {
		return terminada;
	}
	public void setTerminada(Boolean terminada) {
		this.terminada = terminada;
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
}
