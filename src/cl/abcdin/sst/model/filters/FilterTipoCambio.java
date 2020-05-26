package cl.abcdin.sst.model.filters;

import java.util.Date;

import cl.abcdin.sst.model.Documento;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.ReglaComercial;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;

public class FilterTipoCambio extends FilterBase{
	private Usuario usuario;
	private String ip;
	private Producto producto;
	private String motivo;
	private Ubicacion ubicacion;
	private Date fechaActual;
	private Documento documento;
	private String numeroSerie;
	private ReglaComercial reglaComercial;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public ReglaComercial getReglaComercial() {
		return reglaComercial;
	}
	public void setReglaComercial(ReglaComercial reglaComercial) {
		this.reglaComercial = reglaComercial;
	}
}
