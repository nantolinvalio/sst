package cl.abcdin.sst.model.filters;

import java.sql.Date;

public class FilterProducto extends FilterBase{
	private String idLinea;
	private String idFamilia;
	private String tipoDocumento;
	private Long idDocumento;
	private String familia;
    private Long idSTecnico;
    private Long id;
    private String marca;
    private Boolean conServicioTecnico;
    private Long idProveedor;
    private Long idProducto;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer idUbicacionInterna;
    
    
    
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Long getIdSTecnico() {
		return idSTecnico;
	}

	public void setIdSTecnico(Long idSTecnico) {
		this.idSTecnico = idSTecnico;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
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

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(String idLinea) {
		this.idLinea = idLinea;
	}

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public Boolean getConServicioTecnico() {
		return conServicioTecnico;
	}

	public void setConServicioTecnico(Boolean conServicioTecnico) {
		this.conServicioTecnico = conServicioTecnico;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdUbicacionInterna() {
		return idUbicacionInterna;
	}

	public void setIdUbicacionInterna(Integer idUbicacionInterna) {
		this.idUbicacionInterna = idUbicacionInterna;
	}
}
