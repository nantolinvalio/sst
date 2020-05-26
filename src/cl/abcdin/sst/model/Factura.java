package cl.abcdin.sst.model;

import java.util.Date;

import cl.abcdin.sst.model.filters.FilterBase;

public class Factura extends FilterBase{
	private Long id;
	private Long numero;
	private Date fechaEmision;
	private Float iva; 
	private Long montoNeto; 
	private Integer rut;
	private String nombre; 
	private String direccion;
	private String giro;
	private String comuna;
	private Date fechaRegistro;
	private Long numeroSC;
	private Estado estado;
	private Date fechaSC;
	private Date fechaAceptacion;
	private Date fechaRechazada;
	private String tipoFactura;
	private Integer cantidadFacturas;
	private Integer cantidadFacturasRechazadas;
	private Integer cantidadOTs;
	private Long montoTotal;
	private String otsConcatenadas;
	private Boolean checkMarca; 
	private Long idFacturar; 
	private String rutFacturar;
	private String generada;
	private String emitir;
	private String emitida;
	private String aceptada;
	private Producto producto;
	private Marca marca;
	private Long valor;
	private Long cantidad;
	private Long subTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Float getIva() {
		return iva;
	}
	public void setIva(Float iva) {
		this.iva = iva;
	}
	public Long getMontoNeto() {
		return montoNeto;
	}
	public void setMontoNeto(Long montoNeto) {
		this.montoNeto = montoNeto;
	}
//	public String getRut() {
//		return rut;
//	}
//	public void setRut(String rut) {
//		this.rut = rut;
//	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Long getNumeroSC() {
		return numeroSC;
	}
	public void setNumeroSC(Long numeroSC) {
		this.numeroSC = numeroSC;
	}
	
	public void setNumeroSC(Integer numeroSC) {
		this.numeroSC = numeroSC.longValue();
	}
	
	public Date getFechaSC() {
		return fechaSC;
	}
	public void setFechaSC(Date fechaSC) {
		this.fechaSC = fechaSC;
	}
	public Date getFechaAceptacion() {
		return fechaAceptacion;
	}
	public void setFechaAceptacion(Date fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}
	public String getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	public Integer getCantidadFacturas() {
		return cantidadFacturas;
	}
	public void setCantidadFacturas(Integer cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
	}
	public Long getMontoTotal() {
		return montoTotal;
	}
	public void setTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Date getFechaRechazada() {
		return fechaRechazada;
	}
	public void setFechaRechazada(Date fechaRechazada) {
		this.fechaRechazada = fechaRechazada;
	}
	public String getOtsConcatenadas() {
		return otsConcatenadas;
	}
	public void setOtsConcatenadas(String otsConcatenadas) {
		this.otsConcatenadas = otsConcatenadas;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Integer getCantidadFacturasRechazadas() {
		return cantidadFacturasRechazadas;
	}
	public void setCantidadFacturasRechazadas(Integer cantidadFacturasRechazadas) {
		this.cantidadFacturasRechazadas = cantidadFacturasRechazadas;
	}
	public Integer getCantidadOTs() {
		return cantidadOTs;
	}
	public void setCantidadOTs(Integer cantidadOTs) {
		this.cantidadOTs = cantidadOTs;
	}
	public Integer getRut() {
		return rut;
	}
	public void setRut(Integer rut) {
		this.rut = rut;
	}
	public Boolean getCheckMarca() {
		return checkMarca;
	}
	public void setCheckMarca(Boolean checkMarca) {
		this.checkMarca = checkMarca;
	}
	public Long getIdFacturar() {
		return idFacturar;
	}
	public void setIdFacturar(Long idFacturar) {
		this.idFacturar = idFacturar;
	}
	public String getRutFacturar() {
		return rutFacturar;
	}
	public void setRutFacturar(String rutFacturar) {
		this.rutFacturar = rutFacturar;
	}
	public String getEmitir() {
		return emitir;
	}
	public void setEmitir(String emitir) {
		this.emitir = emitir;
	}
	public String getGenerada() {
		return generada;
	}
	public void setGenerada(String generada) {
		this.generada = generada;
	}
	public String getEmitida() {
		return emitida;
	}
	public void setEmitida(String emitida) {
		this.emitida = emitida;
	}
	public String getAceptada() {
		return aceptada;
	}
	public void setAceptada(String aceptada) {
		this.aceptada = aceptada;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public Long getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
}
