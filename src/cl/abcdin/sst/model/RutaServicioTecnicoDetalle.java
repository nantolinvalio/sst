package cl.abcdin.sst.model;

import java.util.Date;

public class RutaServicioTecnicoDetalle extends TipoGenerico{
    private RutaServicioTecnico rutaServicioTecnico;
    private ServicioTecnico servicioTecnico;
    private Usuario usuarioCreacion;
    private Date fechaCreacion;
    
    public RutaServicioTecnico getRutaServicioTecnico() {
        return rutaServicioTecnico;
    }
    public void setRutaServicioTecnico(RutaServicioTecnico rutaServicioTecnico) {
        this.rutaServicioTecnico = rutaServicioTecnico;
    }
    public ServicioTecnico getServicioTecnico() {
        return servicioTecnico;
    }
    public void setServicioTecnico(ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
    }
    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }
    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
