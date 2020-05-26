package cl.abcdin.sst.model;

import cl.abcdin.sst.utils.Constants;

public class Ubicacion {
	private Long id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String tipo;
	private String centroCosto;
	private Comuna comuna;
	private Region region;
	private Boolean vigente;
	private String rut;
	private String giro;
	private Long cantidadProductos;
	private Long cantidadDestinos;
	private String glosa;
	private String codigo; 
	private String codigoOW; 
	
	public Ubicacion(){}
	
	public Ubicacion(ServicioTecnico servicioTecnico) throws Exception {
		if (servicioTecnico != null) {
			this.nombre = servicioTecnico.getNombre();
			if (servicioTecnico.getId() != null)
				this.id = servicioTecnico.getId().longValue();
			this.direccion = servicioTecnico.getDireccion();
			this.telefono = servicioTecnico.getTelefono();
			this.tipo = Constants.UBICACION_SERVICIO_TECNICO;
			this.comuna = servicioTecnico.getComuna();
			this.region = servicioTecnico.getRegion();
			this.vigente = servicioTecnico.getVigente();
			this.rut = servicioTecnico.getRut();
			this.giro = servicioTecnico.getGiro();
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
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
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public Long getCantidadProductos() {
		return cantidadProductos;
	}
	public void setCantidadProductos(Long cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	public Long getCantidadDestinos() {
		return cantidadDestinos;
	}
	public void setCantidadDestinos(Long cantidadDestinos) {
		this.cantidadDestinos = cantidadDestinos;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoOW() {
		return codigoOW;
	}

	public void setCodigoOW(String codigoOW) {
		this.codigoOW = codigoOW;
	}
}
