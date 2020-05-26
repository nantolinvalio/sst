package cl.abcdin.sst.model.filters;

public class FilterUbicacionInterna extends FilterBase{
    private String nombre;
    private String idLinea;
    private String idFamilia;
    private Integer idSucursal;
    private String codigo;
    private Boolean vigente;
    private Long notId;
    private Long idProducto;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Integer getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public Long getNotId() {
		return notId;
	}
	public void setNotId(Long notId) {
		this.notId = notId;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
}
