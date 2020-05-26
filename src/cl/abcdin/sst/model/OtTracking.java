package cl.abcdin.sst.model;

import java.util.Date;

public class OtTracking {

	private Long sid;
	private Long sidOt;
	private Long sidOtInterfaz;
	private Date fechaTracking;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getSidOt() {
		return sidOt;
	}

	public void setSidOt(Long sidOt) {
		this.sidOt = sidOt;
	}

	public Long getSidOtInterfaz() {
		return sidOtInterfaz;
	}

	public void setSidOtInterfaz(Long sidOtInterfaz) {
		this.sidOtInterfaz = sidOtInterfaz;
	}

	public Date getFechaTracking() {
		return fechaTracking;
	}

	public void setFechaTracking(Date fechaTracking) {
		this.fechaTracking = fechaTracking;
	}

}
