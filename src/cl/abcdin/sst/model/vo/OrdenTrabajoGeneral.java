package cl.abcdin.sst.model.vo;

import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Persona;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.Sucursal;
import cl.abcdin.sst.model.TipoCambio;
import cl.abcdin.sst.model.TipoFallas;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.Usuario;

public class OrdenTrabajoGeneral {
	
	private Long id;
	private TipoOT tipo;
	private Date fechaCreacion;
	private Date fechaVencimiento;
	private String tipoDocumento;
	private Long idDocumento;
	private Date fechaEmision;
	private Usuario logistico;
	private Usuario ejecutiva;
	private Sucursal sucursal;
	private Long numeroAtencion;
	private Long numeroCambio;
	private Integer idProducto;
	private String descripcionFalla;
	private String numeroSerie;
	private String descripcionFisica;
	private String numeroTelefono;
	private String descripcionEstado;
	private Long idServicioTecnico;
	private Long idDestino;
	private Persona cliente;//-->nombre e id
	private String observacion;
	private Cliente clienteReport;
	private List<Accesorio> accesoriosListReport;
	private List<Parte> partesListReport;
	private List<TipoFallas> tipoFallasListReport;
	private String numeroAtencionReport;
	private Producto productoReport;
	private Date fecha;
	private Usuario usuarioReport;
	private Proveedor proveedorReport;
	private TipoCambio tipoCambio;
	private String numeroIncidenteMarca;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoOT getTipo() {
		return tipo;
	}
	public void setTipo(TipoOT tipo) {
		this.tipo = tipo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Usuario getLogistico() {
		return logistico;
	}
	public void setLogistico(Usuario logistico) {
		this.logistico = logistico;
	}
	public Usuario getEjecutiva() {
		return ejecutiva;
	}
	public void setEjecutiva(Usuario ejecutiva) {
		this.ejecutiva = ejecutiva;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getDescripcionFalla() {
		return descripcionFalla;
	}
	public void setDescripcionFalla(String descripcionFalla) {
		this.descripcionFalla = descripcionFalla;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getDescripcionFisica() {
		return descripcionFisica;
	}
	public void setDescripcionFisica(String descripcionFisica) {
		this.descripcionFisica = descripcionFisica;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	public Long getIdServicioTecnico() {
		return idServicioTecnico;
	}
	public void setIdServicioTecnico(Long idServicioTecnico) {
		this.idServicioTecnico = idServicioTecnico;
	}
	public Long getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}
	public Persona getCliente() {
		return cliente;
	}
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Cliente getClienteReport() {
		return clienteReport;
	}
	public void setClienteReport(Cliente clienteReport) {
		this.clienteReport = clienteReport;
	}
	public List<Accesorio> getAccesoriosListReport() {
		return accesoriosListReport;
	}
	public void setAccesoriosListReport(List<Accesorio> accesoriosListReport) {
		this.accesoriosListReport = accesoriosListReport;
	}
	public List<Parte> getPartesListReport() {
		return partesListReport;
	}
	public void setPartesListReport(List<Parte> partesListReport) {
		this.partesListReport = partesListReport;
	}
	public List<TipoFallas> getTipoFallasListReport() {
		return tipoFallasListReport;
	}
	public void setTipoFallasListReport(List<TipoFallas> tipoFallasListReport) {
		this.tipoFallasListReport = tipoFallasListReport;
	}
	public String getNumeroAtencionReport() {
		return numeroAtencionReport;
	}
	public void setNumeroAtencionReport(String numeroAtencionReport) {
		this.numeroAtencionReport = numeroAtencionReport;
	}
	public Producto getProductoReport() {
		return productoReport;
	}
	public void setProductoReport(Producto productoReport) {
		this.productoReport = productoReport;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuarioReport() {
		return usuarioReport;
	}
	public void setUsuarioReport(Usuario usuarioReport) {
		this.usuarioReport = usuarioReport;
	}
	public Proveedor getProveedorReport() {
		return proveedorReport;
	}
	public void setProveedorReport(Proveedor proveedorReport) {
		this.proveedorReport = proveedorReport;
	}
	public TipoCambio getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(TipoCambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getNumeroIncidenteMarca() {
		return numeroIncidenteMarca;
	}
	public void setNumeroIncidenteMarca(String numeroIncidenteMarca) {
		this.numeroIncidenteMarca = numeroIncidenteMarca;
	}
}