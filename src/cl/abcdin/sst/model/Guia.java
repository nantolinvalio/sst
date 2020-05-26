package cl.abcdin.sst.model;

import java.util.Date;

public class Guia {
	private Long id;
	private Long numero;
	private Date fechaEmision;
	private Date fechaEntrega;
	private Date fechaRegistro;
	private Estado estado;
	private Estado estadoActual;
	private Transportista transportista;
	private Usuario usuario;
	private Recepcion recepcion;
	private Ubicacion destino;
	private Ubicacion origen;
	private Boolean vigente;
	private OrdenTrabajo ordenTrabajo;
	private String observacion;
	private Boolean entregaCliente;
	private Date fechaUltimoEstado;
	private String tipoGuia;
	private DespachoInternoCamion despachoInternoCamion;// para  guias de despachos internos
	private Integer numeroTSTO;
	private Integer numeroTO;
	private Boolean procesadoOW;


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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recepcion getRecepcion() {
		return recepcion;
	}

	public void setRecepcion(Recepcion recepcion) {
		this.recepcion = recepcion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getEntregaCliente() {
		return entregaCliente;
	}

	public void setEntregaCliente(Boolean entregaCliente) {
		this.entregaCliente = entregaCliente;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	
	public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}

	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	public String getTipoGuia() {
		return tipoGuia;
	}

	public void setTipoGuia(String tipoGuia) {
		this.tipoGuia = tipoGuia;
	}

	public DespachoInternoCamion getDespachoInternoCamion() {
		return despachoInternoCamion;
	}

	public void setDespachoInternoCamion(DespachoInternoCamion despachoInternoCamion) {
		this.despachoInternoCamion = despachoInternoCamion;
	}

	public Integer getNumeroTSTO() {
		return numeroTSTO;
	}

	public void setNumeroTSTO(Integer numeroTSTO) {
		this.numeroTSTO = numeroTSTO;
	}

	public Boolean getProcesadoOW() {
		return procesadoOW;
	}

	public void setProcesadoOW(Boolean procesadoOW) {
		this.procesadoOW = procesadoOW;
	}

	public Integer getNumeroTO() {
		return numeroTO;
	}

	public void setNumeroTO(Integer numeroTO) {
		this.numeroTO = numeroTO;
	}
	
}	
