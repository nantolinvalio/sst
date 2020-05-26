package cl.abcdin.sst.model;

import java.util.Date;


public class DespachoInterno  {
	private Long id;
	private Estado estado;
	private Date fechaCreacion;
	private Date fechaCierre;
	private Usuario usuarioCreacion;
	private Usuario usuarioCierre;
	private Integer cantidadOTLeidas;
	private Integer cantidadOT;
	private Integer cantidadOTRecibidas;
	private Ubicacion origen;
	private Ubicacion Destino;
	private UbicacionInterna origenInterno;
	private UbicacionInterna destinoInterno;
	private Familia familia;
	private Proveedor proveedor;
	private Long porcentajeRecuperacion;
	private String tipo;
	private String tipoUbicacion;
	
	
	public Ubicacion getDestino() {
		return Destino;
	}
	public void setDestino(Ubicacion destino) {
		Destino = destino;
	}
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Long getPorcentajeRecuperacion() {
		return porcentajeRecuperacion;
	}
	public void setPorcentajeRecuperacion(Long porcentajeRecuperacion) {
		this.porcentajeRecuperacion = porcentajeRecuperacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getCantidadOTLeidas() {
		return cantidadOTLeidas;
	}
	public void setCantidadOTLeidas(Integer cantidadOTLeidas) {
		this.cantidadOTLeidas = cantidadOTLeidas;
	}
	public Integer getCantidadOT() {
		return cantidadOT;
	}
	public void setCantidadOT(Integer cantidadOT) {
		this.cantidadOT = cantidadOT;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Ubicacion getOrigen() {
		return origen;
	}
	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}
	public UbicacionInterna getOrigenInterno() {
		return origenInterno;
	}
	public void setOrigenInterno(UbicacionInterna origenInterno) {
		this.origenInterno = origenInterno;
	}
	public UbicacionInterna getDestinoInterno() {
		return destinoInterno;
	}
	public void setDestinoInterno(UbicacionInterna destinoInterno) {
		this.destinoInterno = destinoInterno;
	}
	public Integer getCantidadOTRecibidas() {
		return cantidadOTRecibidas;
	}
	public void setCantidadOTRecibidas(Integer cantidadOTRecibidas) {
		this.cantidadOTRecibidas = cantidadOTRecibidas;
	}
	public String getTipoUbicacion() {
		return tipoUbicacion;
	}
	public void setTipoUbicacion(String tipoUbicacion) {
		this.tipoUbicacion = tipoUbicacion;
	}
}
