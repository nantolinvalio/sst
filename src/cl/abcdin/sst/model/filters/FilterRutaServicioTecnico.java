package cl.abcdin.sst.model.filters;

import java.util.List;

public class FilterRutaServicioTecnico extends FilterBase{
    private String codigo;
    private String nombre;
    private Boolean vigente;
    private List<Integer> idStecnicos;
    private Integer idRuta;
    private List<Integer> eliminar;
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getVigente() {
        return vigente;
    }
    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }
    public List<Integer> getIdStecnicos() {
        return idStecnicos;
    }
    public void setIdStecnicos(List<Integer> idStecnicos) {
        this.idStecnicos = idStecnicos;
    }
    public Integer getIdRuta() {
        return idRuta;
    }
    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }
	public List<Integer> getEliminar() {
		return eliminar;
	}
	public void setEliminar(List<Integer> eliminar) {
		this.eliminar = eliminar;
	}
}
