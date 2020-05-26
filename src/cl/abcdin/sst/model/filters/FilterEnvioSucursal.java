package cl.abcdin.sst.model.filters;

import java.util.Date;

public class FilterEnvioSucursal extends FilterBase{
	private Date fechaInicio;

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
}
