package cl.abcdin.sst.model;


public class ServicioTecnico extends TipoGenerico {
	
	private String direccion;
	private String rut;
	private String telefono;
	private Comuna comuna;
	private Region region;
	private OrdenTrabajo ot;// tiene producto y producto tiene marca
	private Guia guia;
	private Cliente cliente;
	private String giro;
	private Boolean vigente;
	private Integer cantidadProductos;
	private String tipo;
	private String tipoGlosa;
	private String nombre;
	
	public ServicioTecnico() {}
	
	public ServicioTecnico(Ubicacion ubicacion) throws Exception {
		this.nombre = ubicacion.getNombre();
		this.setId(ubicacion.getId().intValue());
		this.direccion = ubicacion.getDireccion();
		this.telefono = ubicacion.getTelefono();
		this.comuna = ubicacion.getComuna();
		this.region = ubicacion.getRegion();
		this.vigente = ubicacion.getVigente();
		this.rut = ubicacion.getRut();
		this.giro = ubicacion.getGiro();
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public OrdenTrabajo getOt() {
		return ot;
	}
	public void setOt(OrdenTrabajo ot) {
		this.ot = ot;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Integer getCantidadProductos() {
		return cantidadProductos;
	}
	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoGlosa() {
		return tipoGlosa;
	}
	public void setTipoGlosa(String tipoGlosa) {
		this.tipoGlosa = tipoGlosa;
	}

	
}
