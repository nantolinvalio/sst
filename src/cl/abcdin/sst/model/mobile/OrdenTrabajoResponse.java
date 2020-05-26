package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class OrdenTrabajoResponse extends Response {
	private OrdenTrabajoMobile ordenTrabajo;

	@XmlElement(name="ordenTrabajo", nillable = true)
	public OrdenTrabajoMobile getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajoMobile ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

}
