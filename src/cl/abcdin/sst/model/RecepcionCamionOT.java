package cl.abcdin.sst.model;

import java.util.Date;

public class RecepcionCamionOT {
	private Integer id;
	private OrdenTrabajo ordenTrabajo;
	private RecepcionCamionGuia recepcionCamionGuia;
	private Date fechaRecepcion;
	
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public RecepcionCamionGuia getRecepcionCamionGuia() {
		return recepcionCamionGuia;
	}
	public void setRecepcionCamionGuia(RecepcionCamionGuia recepcionCamionGuia) {
		this.recepcionCamionGuia = recepcionCamionGuia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	
}
