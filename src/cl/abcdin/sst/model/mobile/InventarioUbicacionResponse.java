package cl.abcdin.sst.model.mobile;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class InventarioUbicacionResponse extends Response{
	private OrdenTrabajoMobile ordenTrabajoMobile;
	
	@XmlElement(name="ordenTrabajo", nillable = true)
	public OrdenTrabajoMobile getOrdenTrabajoMobile() {
		return ordenTrabajoMobile;
	}
	public void setOrdenTrabajoMobile(OrdenTrabajoMobile ordenTrabajoMobile) {
		this.ordenTrabajoMobile = ordenTrabajoMobile;
	}
}
