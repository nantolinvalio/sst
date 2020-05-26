package cl.abcdin.sst.model.vo;

import java.util.Date;

import cl.abcdin.sst.model.Procedimiento;

public class CrearOTGP {
	private Boolean consultarHistorial;
	private Boolean consultarST;
	private Boolean consultarGM;
	private Boolean consultarGP;
	private Boolean crearOT;
	private Procedimiento procedimiento;
	private Boolean bigTicket;
	private Date fechaVencimiento;
	
	public Boolean getConsultarHistorial() {
		return consultarHistorial;
	}
	public void setConsultarHistorial(Boolean consultarHistorial) {
		this.consultarHistorial = consultarHistorial;
	}
	public Boolean getConsultarST() {
		return consultarST;
	}
	public void setConsultarST(Boolean consultarST) {
		this.consultarST = consultarST;
	}
	public Boolean getConsultarGM() {
		return consultarGM;
	}
	public void setConsultarGM(Boolean consultarGM) {
		this.consultarGM = consultarGM;
	}
	public Boolean getConsultarGP() {
		return consultarGP;
	}
	public void setConsultarGP(Boolean consultarGP) {
		this.consultarGP = consultarGP;
	}
	public Boolean getCrearOT() {
		return crearOT;
	}
	public void setCrearOT(Boolean crearOT) {
		this.crearOT = crearOT;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Boolean getBigTicket() {
		return bigTicket;
	}
	public void setBigTicket(Boolean bigTicket) {
		this.bigTicket = bigTicket;
	}
	public Procedimiento getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	
	
}
