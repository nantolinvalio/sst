package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Recepcion;
import cl.abcdin.sst.model.RecepcionCamion;
import cl.abcdin.sst.model.filters.FilterBase;

public class OrdenTrabajoRecepcion extends FilterBase{
	
	private OrdenTrabajo ordenTrabajo;
	private Recepcion recepcionOrdenTrabajo;
	private RecepcionCamion recepcionCamion;
	
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Recepcion getRecepcionOrdenTrabajo() {
		return recepcionOrdenTrabajo;
	}
	public void setRecepcionOrdenTrabajo(Recepcion recepcionOrdenTrabajo) {
		this.recepcionOrdenTrabajo = recepcionOrdenTrabajo;
	}
	public RecepcionCamion getRecepcionCamion() {
		return recepcionCamion;
	}
	public void setRecepcionCamion(RecepcionCamion recepcionCamion) {
		this.recepcionCamion = recepcionCamion;
	}
	
}
