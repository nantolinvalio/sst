package cl.abcdin.sst.model.vo;

import java.util.Date;

import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Usuario;

public class OrdenTrabajoNotaCredito {
	private OrdenTrabajo ot;
	private Usuario usuarioReport;
	private Date fechaReport;
	
	public OrdenTrabajo getOt() {
		return ot;
	}

	public void setOt(OrdenTrabajo ot) {
		this.ot = ot;
	}

	public Date getFechaReport() {
		return fechaReport;
	}

	public void setFechaReport(Date fechaReport) {
		this.fechaReport = fechaReport;
	}

	public Usuario getUsuarioReport() {
		return usuarioReport;
	}

	public void setUsuarioReport(Usuario usuarioReport) {
		this.usuarioReport = usuarioReport;
	}
	
}
