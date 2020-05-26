package cl.abcdin.sst.model;

import java.util.Date;

public class EnvioSucursal extends TipoGenerico{
	private Sucursal sucursal;
	private Date fechaInicio;
	private Usuario usuarioInicio;
	private Date fechaTermino;
	private Usuario usuarioTermino;
	private Usuario usuarioCarga;
//	private Integer cantitdadOT;
	private Integer totalLeidas;
	private Integer totalOT;
	private Integer almacenadas;
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Usuario getUsuarioInicio() {
		return usuarioInicio;
	}
	public void setUsuarioInicio(Usuario usuarioInicio) {
		this.usuarioInicio = usuarioInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Usuario getUsuarioTermino() {
		return usuarioTermino;
	}
	public void setUsuarioTermino(Usuario usuarioTermino) {
		this.usuarioTermino = usuarioTermino;
	}
//	public Integer getCantitdadOT() {
//		return cantitdadOT;
//	}
//	public void setCantitdadOT(Integer cantitdadOT) {
//		this.cantitdadOT = cantitdadOT;
//	}
	public Integer getTotalLeidas() {
		return totalLeidas;
	}
	public void setTotalLeidas(Integer totalLeidas) {
		this.totalLeidas = totalLeidas;
	}
	public Integer getTotalOT() {
		return totalOT;
	}
	public void setTotalOT(Integer totalOT) {
		this.totalOT = totalOT;
	}
	public Usuario getUsuarioCarga() {
		return usuarioCarga;
	}
	public void setUsuarioCarga(Usuario usuarioCarga) {
		this.usuarioCarga = usuarioCarga;
	}
	public Integer getAlmacenadas() {
		return almacenadas;
	}
	public void setAlmacenadas(Integer almacenadas) {
		this.almacenadas = almacenadas;
	}
}
