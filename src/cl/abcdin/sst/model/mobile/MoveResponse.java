package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class MoveResponse extends Response{
	private Long totalOTLeidas;
	private Long totalOT;
	private OrdenTrabajoMobile ordenTrabajo;
	
	@XmlElement(nillable = true)
	public Long getTotalOTLeidas() {
		return totalOTLeidas;
	}
	public void setTotalOTLeidas(Long totalOTLeidas) {
		this.totalOTLeidas = totalOTLeidas;
	}
	@XmlElement(nillable = true)
	public Long getTotalOT() {
		return totalOT;
	}
	public void setTotalOT(Long totalOT) {
		this.totalOT = totalOT;
	}
	@XmlElement(nillable = true)
	public OrdenTrabajoMobile getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajoMobile ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
}
