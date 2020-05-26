package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class MoveDespachoSucursalResponse extends Response{
	private OrdenTrabajoMobile ordenTrabajo;
	private Long totalOTLeidas ;
	private Long totalOT;
	
	@XmlElement(nillable = true)
	public OrdenTrabajoMobile getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajoMobile ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

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

}
