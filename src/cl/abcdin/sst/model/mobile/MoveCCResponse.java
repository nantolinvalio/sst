package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class MoveCCResponse extends Response{
	private OrdenTrabajoMobile ordenTrabajo;
	
	
	public OrdenTrabajoMobile getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajoMobile ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
}
