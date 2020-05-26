package cl.abcdin.sst.model;

import java.util.Date;

public class DespachoDetalle {

	private Long id;
	private OrdenTrabajo ordenTrabajo;
	private Estado estado;
	private Date fechaEstado;
	private DespachoInterno despachoInterno;
	private Usuario usuario;
	private Long factura;
	private Long notaCredito;
	private Long bodega;
	private Long dañado;
	private Long aptoVenta;
	private Long cerradas;
	private Long vigentes;

	public Long getFactura() {
		return factura;
	}

	public void setFactura(Long factura) {
		this.factura = factura;
	}

	public void setFactura(Integer factura) {
		this.factura = factura.longValue();
	}

	public Long getNotaCredito() {
		return notaCredito;
	}

	public void setNotaCredito(Long notaCredito) {
		this.notaCredito = notaCredito;
	}

	public void setNotaCredito(Integer notaCredito) {
		this.notaCredito = notaCredito.longValue();
	}

	public Long getBodega() {
		return bodega;
	}

	public void setBodega(Long bodega) {
		this.bodega = bodega;
	}

	public void setBodega(Integer bodega) {
		this.bodega = bodega.longValue();
	}

	public Long getCerradas() {
		return cerradas;
	}

	public void setCerradas(Long cerradas) {
		this.cerradas = cerradas;
	}

	public Long getVigentes() {
		return vigentes;
	}

	public void setVigentes(Long vigentes) {
		this.vigentes = vigentes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public DespachoInterno getDespachoInterno() {
		return despachoInterno;
	}

	public void setDespachoInterno(DespachoInterno despachoInterno) {
		this.despachoInterno = despachoInterno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Long getDañado() {
		return dañado;
	}

}
