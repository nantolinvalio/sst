package cl.abcdin.sst.model;

import java.util.Date;

public class OrdenTrabajo{
	private Long id;
	private Cliente cliente;
	private Sucursal sucursal;
	private Date fechaCreacion;
	private TipoOT tipo;
	private Estado estado;
	private Estado ultimoEstado;
	private DespachoDetalle despachoDetalle;
	private String numeroSerie;
	private Long numeroCargo;
	private String numeroContrato;
	private Boolean contratoEmitido;
	private Empresa empresaFacturar;
	private ServicioTecnico servicioTecnico;
	private Date fechaCambio;//FECHA CAMBIO AUTORIZADO
	private Boolean cambioAutorizado;
	private Persona ejecutiva;
	private Producto producto;
	private String diagnostico;
	private Long diasServicioTecnico;
	private Long diasSucursalInicio;
	private Long diasSucursalTermino;
	private Long diasTransito;
	private Long semaforoSucursalInicio;
	private Long numeroEnviosServicioTecnico;
	private Long semaforoSucursalfin;
	private Long semaforoServicioTecnico;
	private Long guiaRecibeSucursal;
	private Date fechaGuiaRecibeSucursal;
	private Long guiaSucursalEnvia;
	private Date fechaGuiaSucursalEnvia;
	private Long guiaServicioTecnico;
	private Date fechaGuiaServicioTecnico;
	private Date fechaCierreCliente;
	private Date fechaGuiaDesdeSucursal;
	private Date fechaGuiaDesdeServicioTecnico;
	private Long codigoCorto;
	private Long iva;
	private Long costo;
	private Date fechaGuiaDesdeBodega;
	private Long numeroGuiaDesdeBodega;
	private Long guiaDesdeSucursal;
	private Long guiaDesdeServicioTecnico;
	private Date fechaGuiaABodega;
	private Long guiaABodega;
	private Date fechaGuiaASucursal;
	private Long guiaASucursal;
	private String accion;
	private String estadoGuia;
	private String familia;
	private String historial;
	private Boolean vigente;
	private Boolean cerradaCliente;
	private Boolean cerrada;
	private Boolean tareaUrgente;
	private Boolean tareaUrgenteFF;
	private TipoCambio tipoCambio;
	private String tipoDocumento;
	private Integer idDocumento;
	private Persona logistico;
	private String motivoCierre;
	private String motivoDesactivacion;
	private Date fechaVencimiento;
	private Date fechaEmision;
	private String contrato;
	private String numeroTelefono;
	private String descripcionFisica;
	private String descripcionFalla;
	private String descripcionEstado;
	private String tipoFalla;
	private PasosOT pasosOT;
	private Long idDestino;
	private Ubicacion destino;
	private Long idDestinoAntesFacturar;
	private Long numeroAtencion;
	private Long numeroCambio;
	private ServicioTecnico sTecnico;
	private String numeroCelular;
	private Boolean checkMarca; 
	private Boolean enviarRemate; 
	private Long numeroFolio;
	private Integer estadoBitacora;
	private BitacoraInterna bitacoraInterna;
	private String motivoCambio;
	private String tipoFacturar;
	private Ubicacion ubicacion;
	private String observacionEntrega;
	private Long notaCredito;
	private String observacion;
	private TipoCambio tipoCambioJT;
	private Long numeroDocumento;
	private String imei;
	private String numeroIncidenteMarca;
	private String codigoBarra;
	private Long ticketCambio;
	private ReglaComercial reglaComercial;
	private Usuario usuarioCambio;
	private Integer idRecepcionCamion;
	private Boolean calificaFR;
	private Cambio cambio;
	private Usuario usuarioCCalidad;
	private Boolean cCalidadAprueba;
	private String cCalidadObservacion;
	private Clasificacion clasificacion;
	private String nombreTecnico;
	private Date fechaRevision;
	private String observacionRevision;
	private Integer recuperacion;
	private Boolean excluyeCCalidad;
	private Integer numeroXN;
	private Boolean procesadoOW;
	private Usuario usuarioModificacionEstado;
	private Boolean modificado;
	private Long idEstadoCambio;
	private Integer numeroIT;
	private String banderaOrigenOT;
	private String conOsinProductoFisico;
	private Boolean nuevaOt;
	private String identificadorTipoXN;
	private String centroCostoXN;

	public String getIdentificadorTipoXN() {
		return identificadorTipoXN;
	}

	public void setIdentificadorTipoXN(String identificadorTipoXN) {
		this.identificadorTipoXN = identificadorTipoXN;
	}

	public String getConOsinProductoFisico() {
		return conOsinProductoFisico;
	}

	public void setConOsinProductoFisico(String conOsinProductoFisico) {
		this.conOsinProductoFisico = conOsinProductoFisico;
	}

	public String getBanderaOrigenOT() {
		return banderaOrigenOT;
	}

	public void setBanderaOrigenOT(String banderaOrigenOT) {
		this.banderaOrigenOT = banderaOrigenOT;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public Boolean getCerradaCliente() {
		return cerradaCliente;
	}

	public void setCerradaCliente(Boolean cerradaCliente) {
		this.cerradaCliente = cerradaCliente;
	}

	public Boolean getCerrada() {
		return cerrada;
	}

	public void setCerrada(Boolean cerrada) {
		this.cerrada = cerrada;
	}

	public Boolean getTareaUrgente() {
		return tareaUrgente;
	}

	public void setTareaUrgente(Boolean tareaUrgente) {
		this.tareaUrgente = tareaUrgente;
	}

	public void setGuiaServicioTecnico(Long guiaServicioTecnico) {
		this.guiaServicioTecnico = guiaServicioTecnico;
	}

	public Persona getEjecutiva() {
		return ejecutiva;
	}

	public void setEjecutiva(Persona ejecutiva) {
		this.ejecutiva = ejecutiva;
	}

	public Date getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public ServicioTecnico getServicioTecnico() {
		return servicioTecnico;
	}

	public void setServicioTecnico(ServicioTecnico servicioTecnico) {
		this.servicioTecnico = servicioTecnico;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public TipoOT getTipo() {
		return tipo;
	}

	public void setTipo(TipoOT tipo) {
		this.tipo = tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Long getNumeroCargo() {
		return numeroCargo;
	}

	public void setNumeroCargo(Long numeroCargo) {
		this.numeroCargo = numeroCargo;
	}

	public Empresa getEmpresaFacturar() {
		return empresaFacturar;
	}

	public void setEmpresaFacturar(Empresa empresaFacturar) {
		this.empresaFacturar = empresaFacturar;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getDiasServicioTecnico() {
		return diasServicioTecnico;
	}

	public void setDiasServicioTecnico(Long diasServicioTecnico) {
		this.diasServicioTecnico = diasServicioTecnico;
	}

	public Long getDiasSucursalInicio() {
		return diasSucursalInicio;
	}

	public void setDiasSucursalInicio(Long diasSucursalInicio) {
		this.diasSucursalInicio = diasSucursalInicio;
	}

	public Long getDiasSucursalTermino() {
		return diasSucursalTermino;
	}

	public void setDiasSucursalTermino(Long diasSucursalTermino) {
		this.diasSucursalTermino = diasSucursalTermino;
	}

	public Long getSemaforoSucursalInicio() {
		return semaforoSucursalInicio;
	}

	public void setSemaforoSucursalInicio(Long semaforoSucursalInicio) {
		this.semaforoSucursalInicio = semaforoSucursalInicio;
	}

	public Long getNumeroEnviosServicioTecnico() {
		return numeroEnviosServicioTecnico;
	}

	public void setNumeroEnviosServicioTecnico(
			Long numeroEnviosServicioTecnico) {
		this.numeroEnviosServicioTecnico = numeroEnviosServicioTecnico;
	}

	public Long getSemaforoServicioTecnico() {
		return semaforoServicioTecnico;
	}

	public void setSemaforoServicioTecnico(Long semaforoServicioTecnico) {
		this.semaforoServicioTecnico = semaforoServicioTecnico;
	}

	public Long getSemaforoSucursalfin() {
		return semaforoSucursalfin;
	}

	public void setSemaforoSucursalfin(Long semaforoSucursalfin) {
		this.semaforoSucursalfin = semaforoSucursalfin;
	}

	public Long getGuiaRecibeSucursal() {
		return guiaRecibeSucursal;
	}

	public void setGuiaRecibeSucursal(Long guiaRecibeSucursal) {
		this.guiaRecibeSucursal = guiaRecibeSucursal;
	}

	public Long getGuiaSucursalEnvia() {
		return guiaSucursalEnvia;
	}

	public void setGuiaSucursalEnvia(Long guiaSucursalEnvia) {
		this.guiaSucursalEnvia = guiaSucursalEnvia;
	}

	public Date getFechaGuiaSucursalEnvia() {
		return fechaGuiaSucursalEnvia;
	}

	public void setFechaGuiaSucursalEnvia(Date fechaGuiaSucursalEnvia) {
		this.fechaGuiaSucursalEnvia = fechaGuiaSucursalEnvia;
	}

	public Date getFechaGuiaRecibeSucursal() {
		return fechaGuiaRecibeSucursal;
	}

	public void setFechaGuiaRecibeSucursal(Date fechaGuiaRecibeSucursal) {
		this.fechaGuiaRecibeSucursal = fechaGuiaRecibeSucursal;
	}

	public Long getGuiaServicioTecnico() {
		return guiaServicioTecnico;
	}

	public Date getFechaGuiaServicioTecnico() {
		return fechaGuiaServicioTecnico;
	}

	public void setFechaGuiaServicioTecnico(Date fechaGuiaServicioTecnico) {
		this.fechaGuiaServicioTecnico = fechaGuiaServicioTecnico;
	}

	public Date getFechaCierreCliente() {
		return fechaCierreCliente;
	}

	public void setFechaCierreCliente(Date fechaCierreCliente) {
		this.fechaCierreCliente = fechaCierreCliente;
	}

	public Date getFechaGuiaDesdeSucursal() {
		return fechaGuiaDesdeSucursal;
	}

	public void setFechaGuiaDesdeSucursal(Date fechaGuiaDesdeSucursal) {
		this.fechaGuiaDesdeSucursal = fechaGuiaDesdeSucursal;
	}

	public Date getFechaGuiaDesdeServicioTecnico() {
		return fechaGuiaDesdeServicioTecnico;
	}

	public void setFechaGuiaDesdeServicioTecnico(
			Date fechaGuiaDesdeServicioTecnico) {
		this.fechaGuiaDesdeServicioTecnico = fechaGuiaDesdeServicioTecnico;
	}

	public Long getCodigoCorto() {
		return codigoCorto;
	}

	public void setCodigoCorto(Long codigoCorto) {
		this.codigoCorto = codigoCorto;
	}

	public Long getIva() {
		return iva;
	}

	public void setIva(Long iva) {
		this.iva = iva;
	}

	public Long getCosto() {
		return costo;
	}

	public void setCosto(Long costo) {
		this.costo = costo;
	}

	public Date getFechaGuiaDesdeBodega() {
		return fechaGuiaDesdeBodega;
	}

	public void setFechaGuiaDesdeBodega(Date fechaGuiaDesdeBodega) {
		this.fechaGuiaDesdeBodega = fechaGuiaDesdeBodega;
	}

	public Long getNumeroGuiaDesdeBodega() {
		return numeroGuiaDesdeBodega;
	}

	public void setNumeroGuiaDesdeBodega(Long numeroGuiaDesdeBodega) {
		this.numeroGuiaDesdeBodega = numeroGuiaDesdeBodega;
	}

	public Long getGuiaDesdeSucursal() {
		return guiaDesdeSucursal;
	}

	public void setGuiaDesdeSucursal(Long guiaDesdeSucursal) {
		this.guiaDesdeSucursal = guiaDesdeSucursal;
	}

	public Long getGuiaDesdeServicioTecnico() {
		return guiaDesdeServicioTecnico;
	}

	public void setGuiaDesdeServicioTecnico(Long guiaDesdeServicioTecnico) {
		this.guiaDesdeServicioTecnico = guiaDesdeServicioTecnico;
	}

	public Date getFechaGuiaABodega() {
		return fechaGuiaABodega;
	}

	public void setFechaGuiaABodega(Date fechaGuiaABodega) {
		this.fechaGuiaABodega = fechaGuiaABodega;
	}

	public Long getGuiaABodega() {
		return guiaABodega;
	}

	public void setGuiaABodega(Long guiaABodega) {
		this.guiaABodega = guiaABodega;
	}

	public Date getFechaGuiaASucursal() {
		return fechaGuiaASucursal;
	}

	public void setFechaGuiaASucursal(Date fechaGuiaASucursal) {
		this.fechaGuiaASucursal = fechaGuiaASucursal;
	}

	public Long getGuiaASucursal() {
		return guiaASucursal;
	}

	public void setGuiaASucursal(Long guiaASucursal) {
		this.guiaASucursal = guiaASucursal;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEstadoGuia() {
		return estadoGuia;
	}

	public void setEstadoGuia(String estadoGuia) {
		this.estadoGuia = estadoGuia;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	public Boolean getCambioAutorizado() {
		return cambioAutorizado;
	}

	public void setCambioAutorizado(Boolean cambioAutorizado) {
		this.cambioAutorizado = cambioAutorizado;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Persona getLogistico() {
		return logistico;
	}

	public void setLogistico(Persona logistico) {
		this.logistico = logistico;
	}

	public String getMotivoCierre() {
		return motivoCierre;
	}

	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDescripcionFisica() {
		return descripcionFisica;
	}

	public void setDescripcionFisica(String descripcionFisica) {
		this.descripcionFisica = descripcionFisica;
	}

	public String getDescripcionFalla() {
		return descripcionFalla;
	}

	public void setDescripcionFalla(String descripcionFalla) {
		this.descripcionFalla = descripcionFalla;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getTipoFalla() {
		return tipoFalla;
	}

	public void setTipoFalla(String tipoFalla) {
		this.tipoFalla = tipoFalla;
	}

	public Boolean getContratoEmitido() {
		return contratoEmitido;
	}

	public void setContratoEmitido(Boolean contratoEmitido) {
		this.contratoEmitido = contratoEmitido;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getMotivoDesactivacion() {
		return motivoDesactivacion;
	}

	public void setMotivoDesactivacion(String motivoDesactivacion) {
		this.motivoDesactivacion = motivoDesactivacion;
	}

	public PasosOT getPasosOT() {
		return pasosOT;
	}

	public void setPasosOT(PasosOT pasosOT) {
		this.pasosOT = pasosOT;
	}

	public Long getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

	public Long getNumeroAtencion() {
		return numeroAtencion;
	}

	public void setNumeroAtencion(Long numeroAtencion) {
		this.numeroAtencion = numeroAtencion;
	}

	public Long getNumeroCambio() {
		return numeroCambio;
	}

	public void setNumeroCambio(Long numeroCambio) {
		this.numeroCambio = numeroCambio;
	}

	public ServicioTecnico getsTecnico() {
		return sTecnico;
	}

	public void setsTecnico(ServicioTecnico sTecnico) {
		this.sTecnico = sTecnico;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public Boolean getCheckMarca() {
		return checkMarca;
	}

	public void setCheckMarca(Boolean checkMarca) {
		this.checkMarca = checkMarca;
	}

	public Long getNumeroFolio() {
		return numeroFolio;
	}

	public void setNumeroFolio(Long numeroFolio) {
		this.numeroFolio = numeroFolio;
	}

	public Integer getEstadoBitacora() {
		return estadoBitacora;
	}

	public void setEstadoBitacora(Integer estadoBitacora) {
		this.estadoBitacora = estadoBitacora;
	}

	public String getMotivoCambio() {
		return motivoCambio;
	}

	public void setMotivoCambio(String motivoCambio) {
		this.motivoCambio = motivoCambio;
	}

	public String getTipoFacturar() {
		return tipoFacturar;
	}

	public void setTipoFacturar(String tipoFacturar) {
		this.tipoFacturar = tipoFacturar;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Long getDiasTransito() {
		return diasTransito;
	}

	public void setDiasTransito(Long diasTransito) {
		this.diasTransito = diasTransito;
	}

	public String getObservacionEntrega() {
		return observacionEntrega;
	}

	public void setObservacionEntrega(String observacionEntrega) {
		this.observacionEntrega = observacionEntrega;
	}

	public Long getNotaCredito() {
		return notaCredito;
	}

	public void setNotaCredito(Long notaCredito) {
		this.notaCredito = notaCredito;
	}

	public TipoCambio getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(TipoCambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public TipoCambio getTipoCambioJT() {
		return tipoCambioJT;
	}

	public void setTipoCambioJT(TipoCambio tipoCambioJT) {
		this.tipoCambioJT = tipoCambioJT;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

//	public Long getIdZona() {
//		return idZona;
//	}
//
//	public void setIdZona(Long idZona) {
//		this.idZona = idZona;
//	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getNumeroIncidenteMarca() {
		return numeroIncidenteMarca;
	}

	public void setNumeroIncidenteMarca(String numeroIncidenteMarca) {
		this.numeroIncidenteMarca = numeroIncidenteMarca;
	}

	public Long getTicketCambio() {
		return ticketCambio;
	}

	public void setTicketCambio(Long ticketCambio) {
		this.ticketCambio = ticketCambio;
	}

	public ReglaComercial getReglaComercial() {
		return reglaComercial;
	}

	public void setReglaComercial(ReglaComercial reglaComercial) {
		this.reglaComercial = reglaComercial;
	}

	public Boolean getEnviarRemate() {
		return enviarRemate;
	}

	public void setEnviarRemate(Boolean enviarRemate) {
		this.enviarRemate = enviarRemate;
	}

	public Usuario getUsuarioCambio() {
		return usuarioCambio;
	}

	public void setUsuarioCambio(Usuario usuarioCambio) {
		this.usuarioCambio = usuarioCambio;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Integer getIdRecepcionCamion() {
		return idRecepcionCamion;
	}

	public void setIdRecepcionCamion(Integer idRecepcionCamion) {
		this.idRecepcionCamion = idRecepcionCamion;
	}

	public Boolean getTareaUrgenteFF() {
		return tareaUrgenteFF;
	}

	public void setTareaUrgenteFF(Boolean tareaUrgenteFF) {
		this.tareaUrgenteFF = tareaUrgenteFF;
	}

	public Boolean getCalificaFR() {
		return calificaFR;
	}

	public void setCalificaFR(Boolean calificaFR) {
		this.calificaFR = calificaFR;
	}

	public Cambio getCambio() {
		return cambio;
	}

	public void setCambio(Cambio cambio) {
		this.cambio = cambio;
	}

	public Long getIdDestinoAntesFacturar() {
		return idDestinoAntesFacturar;
	}

	public void setIdDestinoAntesFacturar(Long idDestinoAntesFacturar) {
		this.idDestinoAntesFacturar = idDestinoAntesFacturar;
	}

	public Boolean getcCalidadAprueba() {
		return cCalidadAprueba;
	}

	public void setcCalidadAprueba(Boolean cCalidadAprueba) {
		this.cCalidadAprueba = cCalidadAprueba;
	}

	public Usuario getUsuarioCCalidad() {
		return usuarioCCalidad;
	}

	public void setUsuarioCCalidad(Usuario usuarioCCalidad) {
		this.usuarioCCalidad = usuarioCCalidad;
	}

	public String getcCalidadObservacion() {
		return cCalidadObservacion;
	}

	public void setcCalidadObservacion(String cCalidadObservacion) {
		this.cCalidadObservacion = cCalidadObservacion;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}


	public DespachoDetalle getDespachoDetalle() {
		return despachoDetalle;
	}

	public void setDespachoDetalle(DespachoDetalle despachoDetalle) {
		this.despachoDetalle = despachoDetalle;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public BitacoraInterna getBitacoraInterna() {
		return bitacoraInterna;
	}

	public void setBitacoraInterna(BitacoraInterna bitacoraInterna) {
		this.bitacoraInterna = bitacoraInterna;
	}

	public String getNombreTecnico() {
		return nombreTecnico;
	}

	public void setNombreTecnico(String nombreTecnico) {
		this.nombreTecnico = nombreTecnico;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getObservacionRevision() {
		return observacionRevision;
	}

	public void setObservacionRevision(String observacionRevision) {
		this.observacionRevision = observacionRevision;
	}

	public Integer getRecuperacion() {
		return recuperacion;
	}

	public void setRecuperacion(Integer recuperacion) {
		this.recuperacion = recuperacion;
	}

	public Boolean getExcluyeCCalidad() {
		return excluyeCCalidad;
	}

	public void setExcluyeCCalidad(Boolean excluyeCCalidad) {
		this.excluyeCCalidad = excluyeCCalidad;
	}

	public Boolean getProcesadoOW() {
		return procesadoOW;
	}

	public void setProcesadoOW(Boolean procesadoOW) {
		this.procesadoOW = procesadoOW;
	}

	public Integer getNumeroXN() {
		return numeroXN;
	}

	public void setNumeroXN(Integer numeroXN) {
		this.numeroXN = numeroXN;
	}

	public Estado getUltimoEstado() {
		return ultimoEstado;
	}

	public void setUltimoEstado(Estado ultimoEstado) {
		this.ultimoEstado = ultimoEstado;
	}

	public Usuario getUsuarioModificacionEstado() {
		return usuarioModificacionEstado;
	}

	public void setUsuarioModificacionEstado(Usuario usuarioModificacionEstado) {
		this.usuarioModificacionEstado = usuarioModificacionEstado;
	}

	public Long getIdEstadoCambio() {
		return idEstadoCambio;
	}

	public void setIdEstadoCambio(Long idEstadoCambio) {
		this.idEstadoCambio = idEstadoCambio;
	}

	public Boolean getModificado() {
		return modificado;
	}

	public void setModificado(Boolean modificado) {
		this.modificado = modificado;
	}

	public Integer getNumeroIT() {
		return numeroIT;
	}

	public void setNumeroIT(Integer numeroIT) {
		this.numeroIT = numeroIT;
	}

	public Boolean getNuevaOt() {
		return nuevaOt;
	}

	public void setNuevaOt(Boolean nuevaOt) {
		this.nuevaOt = nuevaOt;
	}

	public String getCentroCostoXN() {
		return centroCostoXN;
	}

	public void setCentroCostoXN(String centroCostoXN) {
		this.centroCostoXN = centroCostoXN;
	}
	
	
	
	
}
