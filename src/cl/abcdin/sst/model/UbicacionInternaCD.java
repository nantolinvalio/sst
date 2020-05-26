package cl.abcdin.sst.model;

import java.util.Date;
import java.util.List;

public class UbicacionInternaCD extends UbicacionInterna{
    private Usuario usuarioModificacion;
    private Date fechaModificacion;
    private List<Sucursal> sucursales;
    private List<Familia> familias;
    private List<Linea> lineas;
    
    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }
    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }
    public Date getFechaModificacion() {
        return fechaModificacion;
    }
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public List<Sucursal> getSucursales() {
        return sucursales;
    }
    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    public List<Familia> getFamilias() {
        return familias;
    }
    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }
    public List<Linea> getLineas() {
        return lineas;
    }
    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }
}
