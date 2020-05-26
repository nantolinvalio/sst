package cl.abcdin.sst.model;

import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.vo.TreeView;

public class ReglaComercial {
	private Long id;
	private Long idHistorico;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaTermino;
	private Boolean vigente;
	private ReglaCambioAutomatico cambioAutomatico;
	private ReglaFallaFabricacion fallaFabricacion;
	private ReglaCambioProoveedor reglaCambioProoveedor;
	private ReglaCertificacionFalla certificacionFalla;
	private Usuario usuarioCreacion;
	private List<TreeView> zonas;
	private List<TreeView> lineas;
	private Date fechaCreacion;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public ReglaCambioAutomatico getCambioAutomatico() {
		return cambioAutomatico;
	}
	public void setCambioAutomatico(ReglaCambioAutomatico cambioAutomatico) {
		this.cambioAutomatico = cambioAutomatico;
	}
	public ReglaFallaFabricacion getFallaFabricacion() {
		return fallaFabricacion;
	}
	public void setFallaFabricacion(ReglaFallaFabricacion fallaFabricacion) {
		this.fallaFabricacion = fallaFabricacion;
	}
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public List<TreeView> getZonas() {
		return zonas;
	}
	public void setZonas(List<TreeView> zonas) {
		this.zonas = zonas;
	}
	public List<TreeView> getLineas() {
		return lineas;
	}
	public void setLineas(List<TreeView> lineas) {
		this.lineas = lineas;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getIdHistorico() {
		return idHistorico;
	}
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}
	public ReglaCambioProoveedor getReglaCambioProoveedor() {
		return reglaCambioProoveedor;
	}
	public void setReglaCambioProoveedor(ReglaCambioProoveedor reglaCambioProoveedor) {
		this.reglaCambioProoveedor = reglaCambioProoveedor;
	}
	public ReglaCertificacionFalla getCertificacionFalla() {
		return certificacionFalla;
	}
	public void setCertificacionFalla(ReglaCertificacionFalla certificacionFalla) {
		this.certificacionFalla = certificacionFalla;
	}
	
}
