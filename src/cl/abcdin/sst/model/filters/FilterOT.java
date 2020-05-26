package cl.abcdin.sst.model.filters;

import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Rol;
import cl.abcdin.sst.model.Usuario;

public class FilterOT extends FilterBase{
	private Long idEjecutiva;
	private Long estadoOT;
	private Date fechaInicio;
	private Date fechaTermino;
	private String numeroContrato;
	private Long idOT;
	private String ot;
	private String rutCliente; //idCliente
	private Long semaforo;
	private Long sucursal;
	private String tipoCambio;
	private String tipoOT;
	private String tipoUbicacion;
	private Long idUbicacion;
	private String tipoDocumento;
	private Long idDocumento;
	private Long idRol;
	private String idLinea;
	private String idFamilia;
	private String idMarca;
	private Long idProducto;
	private List<Rol> roles;
	private String descripcionFalla;
	private String motivoDesactivacion;
	private Long numeroFolio;
	private Long idDestino;
	private Long idOrigen;
	private Long idSTecnico;
	private Date fechaVencimiento;
	private Date fechaEmision;
	private Integer idEstadoBitacora;
	private Integer numeroAtencion;
	private Integer numeroCambio;
	private Boolean cerradaCliente;
	private Boolean cerrada;
	private Boolean vigente;
	private String nombreCliente;
	private Long idGuia;
	private Long idGuiaAgrupada;
	private Long numeroGuia;
	private Long numeroGuiaAgrupada;
	private String numeroSerie;
	private Date fechaCreacion;
	private Date fechaCreacionFin;
	private Date fechaAutorizacionOtInicio;
	private Date fechaAutorizacionOtFin;
	private Date fechaRecepcionOtInicio;
	private Date fechaRecepcionOtFin;
	private Long numeroDocumento;
	private Long idZona;
	private Long idSucursal;
	private String idEstado;
	private Boolean contratoEmitido;
	private Long codigoCorto;
	private Long idEtapa;
	private String codigoBarra;
	private String codigoBarraAccesorio;
	private Long numeroGuiaRetiro;
	private Long numeroGuiaDespachoST;
	private Long numeroGuiaDespacho;
	private Integer idAccesorio;
	private String imei;
	private Boolean calificaFR;
	private Boolean productoRemate;
	private List<Long> ots;
	private List<OrdenTrabajo> ordenTrabajos;
	private Boolean ccalidadAprueba;
	private Integer ccalidad;
	private String tipoUbicacionActual; 
	private String ubicacionInterna;
	private Long idProveedor;
	private Date fechaCreacionCorte;
	private Long guiaABodega;
	public String clasificacion;
	private Long idIndicador;
	private Boolean tareaUrgente;
	private Boolean cambioAutorizado;
	private Integer idUbicacionInterna;
	private Boolean almacenada;
	private Date fechaCambioEstadoInicio;
	private Date fechaCambioEstadoFin;
	private Long idDespacho;
	private Long destinoFinal;
	
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Long getGuiaABodega() {
		return guiaABodega;
	}
	public void setGuiaABodega(Long guiaABodega) {
		this.guiaABodega = guiaABodega;
	}
	public Long getNumeroGuiaDespacho() {
		return numeroGuiaDespacho;
	}
	public void setNumeroGuiaDespacho(Long numeroGuiaDespacho) {
		this.numeroGuiaDespacho = numeroGuiaDespacho;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	private Usuario usuario;
	
	public Long getEstadoOT() {
		return estadoOT;
	}
	public void setEstadoOT(Long estadoOT) {
		this.estadoOT = estadoOT;
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
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	public Long getSemaforo() {
		return semaforo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaCreacionFin() {
		return fechaCreacionFin;
	}
	public void setFechaCreacionFin(Date fechaCreacionFin) {
		this.fechaCreacionFin = fechaCreacionFin;
	}
	public String getRutCliente() {
		return rutCliente;
	}
	public void setSemaforo(Long semaforo) {
		this.semaforo = semaforo;
	}
	public Long getSucursal() {
		return sucursal;
	}
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getTipoOT() {
		return tipoOT;
	}
	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}
	public String getTipoUbicacion() {
		return tipoUbicacion;
	}
	public void setTipoUbicacion(String tipoUbicacion) {
		this.tipoUbicacion = tipoUbicacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getIdEjecutiva() {
		return idEjecutiva;
	}
	public void setIdEjecutiva(Long idEjecutiva) {
		this.idEjecutiva = idEjecutiva;
	}
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public String getDescripcionFalla() {
		return descripcionFalla;
	}
	public void setDescripcionFalla(String descripcionFalla) {
		this.descripcionFalla = descripcionFalla;
	}
	public String getMotivoDesactivacion() {
		return motivoDesactivacion;
	}
	public void setMotivoDesactivacion(String motivoDesactivacion) {
		this.motivoDesactivacion = motivoDesactivacion;
	}
	public Long getNumeroFolio() {
		return numeroFolio;
	}
	public void setNumeroFolio(Long numeroFolio) {
		this.numeroFolio = numeroFolio;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Long getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
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
	public Integer getIdEstadoBitacora() {
		return idEstadoBitacora;
	}
	public void setIdEstadoBitacora(Integer idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}
	public Long getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public Integer getNumeroAtencion() {
		return numeroAtencion;
	}
	public void setNumeroAtencion(Integer numeroAtencion) {
		this.numeroAtencion = numeroAtencion;
	}
	public Integer getNumeroCambio() {
		return numeroCambio;
	}
	public void setNumeroCambio(Integer numeroCambio) {
		this.numeroCambio = numeroCambio;
	}
	public Long getIdSTecnico() {
		return idSTecnico;
	}
	public void setIdSTecnico(Long idSTecnico) {
		this.idSTecnico = idSTecnico;
	}
	public Boolean getCerradaCliente() {
		return cerradaCliente;
	}
	public void setCerradaCliente(Boolean cerradaCliente) {
		this.cerradaCliente = cerradaCliente;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Long getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Long idGuia) {
		this.idGuia = idGuia;
	}
	public Long getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Long getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(Long numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(String idLinea) {
		this.idLinea = idLinea;
	}
	
	public Date getFechaRecepcionOtInicio() {
		return fechaRecepcionOtInicio;
	}
	public void setFechaRecepcionOtInicio(Date fechaRecepcionOtInicio) {
		this.fechaRecepcionOtInicio = fechaRecepcionOtInicio;
	}
	public Date getFechaAutorizacionOtInicio() {
		return fechaAutorizacionOtInicio;
	}
	public void setFechaAutorizacionOtInicio(Date fechaAutorizacionOtInicio) {
		this.fechaAutorizacionOtInicio = fechaAutorizacionOtInicio;
	}
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Long getIdZona() {
		return idZona;
	}
	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Date getFechaAutorizacionOtFin() {
		return fechaAutorizacionOtFin;
	}
	public void setFechaAutorizacionOtFin(Date fechaAutorizacionOtFin) {
		this.fechaAutorizacionOtFin = fechaAutorizacionOtFin;
	}
	public Date getFechaRecepcionOtFin() {
		return fechaRecepcionOtFin;
	}
	public void setFechaRecepcionOtFin(Date fechaRecepcionOtFin) {
		this.fechaRecepcionOtFin = fechaRecepcionOtFin;
	}
	
	public Boolean getContratoEmitido() {
		return contratoEmitido;
	}
	public void setContratoEmitido(Boolean contratoEmitido) {
		this.contratoEmitido = contratoEmitido;
	}
	
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public Long getIdGuiaAgrupada() {
		return idGuiaAgrupada;
	}
	public void setIdGuiaAgrupada(Long idGuiaAgrupada) {
		this.idGuiaAgrupada = idGuiaAgrupada;
	}
	public Long getCodigoCorto() {
		return codigoCorto;
	}
	public void setCodigoCorto(Long codigoCorto) {
		this.codigoCorto = codigoCorto;
	}
	public Long getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Long idEtapa) {
		this.idEtapa = idEtapa;
	}
	
	public Long getNumeroGuiaDespachoST() {
		return numeroGuiaDespachoST;
	}
	public void setNumeroGuiaDespachoST(Long numeroGuiaDespachoST) {
		this.numeroGuiaDespachoST = numeroGuiaDespachoST;
	}
	public Long getNumeroGuiaRetiro() {
		return numeroGuiaRetiro;
	}
	public void setNumeroGuiaRetiro(Long numeroGuiaRetiro) {
		this.numeroGuiaRetiro = numeroGuiaRetiro;
	}
	public Long getNumeroGuiaAgrupada() {
		return numeroGuiaAgrupada;
	}
	public void setNumeroGuiaAgrupada(Long numeroGuiaAgrupada) {
		this.numeroGuiaAgrupada = numeroGuiaAgrupada;
	}
	public Integer getIdAccesorio() {
		return idAccesorio;
	}
	public void setIdAccesorio(Integer idAccesorio) {
		this.idAccesorio = idAccesorio;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getCodigoBarraAccesorio() {
		return codigoBarraAccesorio;
	}
	public void setCodigoBarraAccesorio(String codigoBarraAccesorio) {
		this.codigoBarraAccesorio = codigoBarraAccesorio;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Boolean getCalificaFR() {
		return calificaFR;
	}
	public void setCalificaFR(Boolean calificaFR) {
		this.calificaFR = calificaFR;
	}
	public Boolean getProductoRemate() {
		return productoRemate;
	}
	public void setProductoRemate(Boolean productoRemate) {
		this.productoRemate = productoRemate;
	}
	public List<Long> getOts() {
		return ots;
	}
	public void setOts(List<Long> ots) {
		this.ots = ots;
	}
	public List<OrdenTrabajo> getOrdenTrabajos() {
		return ordenTrabajos;
	}
	public void setOrdenTrabajos(List<OrdenTrabajo> ordenTrabajos) {
		this.ordenTrabajos = ordenTrabajos;
	}
	public String getTipoUbicacionActual() {
		return tipoUbicacionActual;
	}
	public void setTipoUbicacionActual(String tipoUbicacionActual) {
		this.tipoUbicacionActual = tipoUbicacionActual;
	}
	public Boolean getCcalidadAprueba() {
		return ccalidadAprueba;
	}
	public void setCcalidadAprueba(Boolean ccalidadAprueba) {
		this.ccalidadAprueba = ccalidadAprueba;
	}
	public String getUbicacionInterna() {
		return ubicacionInterna;
	}
	public void setUbicacionInterna(String ubicacionInterna) {
		this.ubicacionInterna = ubicacionInterna;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public Date getFechaCreacionCorte() {
		return fechaCreacionCorte;
	}
	public void setFechaCreacionCorte(Date fechaCreacionCorte) {
		this.fechaCreacionCorte = fechaCreacionCorte;
	}
	public Long getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}
	public Boolean getTareaUrgente() {
		return tareaUrgente;
	}
	public void setTareaUrgente(Boolean tareaUrgente) {
		this.tareaUrgente = tareaUrgente;
	}
	public Boolean getCambioAutorizado() {
		return cambioAutorizado;
	}
	public void setCambioAutorizado(Boolean cambioAutorizado) {
		this.cambioAutorizado = cambioAutorizado;
	}
	public Boolean getCerrada() {
		return cerrada;
	}
	public void setCerrada(Boolean cerrada) {
		this.cerrada = cerrada;
	}
	public Integer getCcalidad() {
		return ccalidad;
	}
	public void setCcalidad(Integer ccalidad) {
		this.ccalidad = ccalidad;
	}
	public Integer getIdUbicacionInterna() {
		return idUbicacionInterna;
	}
	public void setIdUbicacionInterna(Integer idUbicacionInterna) {
		this.idUbicacionInterna = idUbicacionInterna;
	}
	public Boolean getAlmacenada() {
		return almacenada;
	}
	public void setAlmacenada(Boolean almacenada) {
		this.almacenada = almacenada;
	}
	public Date getFechaCambioEstadoInicio() {
		return fechaCambioEstadoInicio;
	}
	public void setFechaCambioEstadoInicio(Date fechaCambioEstadoInicio) {
		this.fechaCambioEstadoInicio = fechaCambioEstadoInicio;
	}
	public Date getFechaCambioEstadoFin() {
		return fechaCambioEstadoFin;
	}
	public void setFechaCambioEstadoFin(Date fechaCambioEstadoFin) {
		this.fechaCambioEstadoFin = fechaCambioEstadoFin;
	}
	public Long getIdDespacho() {
		return idDespacho;
	}
	public void setIdDespacho(Long idDespacho) {
		this.idDespacho = idDespacho;
	}
	public Long getDestinoFinal() {
		return destinoFinal;
	}
	public void setDestinoFinal(Long destinoFinal) {
		this.destinoFinal = destinoFinal;
	}
}
