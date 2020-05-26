package cl.abcdin.sst.model.filters;

import java.util.List;

public class FilterServicioTecnico extends FilterBase {
	private Long idOrigen;
	private Long idOT;
	private Integer idProducto;
	private String tipoOT;
	private Integer idServicioTecnico;
	private Integer idComuna;
	private Integer idRegion;
	private Integer idProvincia;
	private String rut;
	private String nombre;
	private String comuna;
	private String region;
	private Boolean soloMisDestinos;
	private List<Integer> idStecnicos;
    
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public Long getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getTipoOT() {
		return tipoOT;
	}
	public void setTipoOT(String tipoOT) {
		this.tipoOT = tipoOT;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public Integer getIdServicioTecnico() {
		return idServicioTecnico;
	}
	public void setIdServicioTecnico(Integer idServicioTecnico) {
		this.idServicioTecnico = idServicioTecnico;
	}
	public Integer getIdComuna() {
		return idComuna;
	}
	public void setIdComuna(Integer idComuna) {
		this.idComuna = idComuna;
	}
    public Integer getIdRegion() {
        return idRegion;
    }
    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
    public Integer getIdProvincia() {
        return idProvincia;
    }
    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }
	public Boolean getSoloMisDestinos() {
		return soloMisDestinos;
	}
	public void setSoloMisDestinos(Boolean soloMisDestinos) {
		this.soloMisDestinos = soloMisDestinos;
	}
	public List<Integer> getIdStecnicos() {
		return idStecnicos;
	}
	public void setIdStecnicos(List<Integer> idStecnicos) {
		this.idStecnicos = idStecnicos;
	}
	
	
}
