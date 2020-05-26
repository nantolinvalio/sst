package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class InventarioResponse extends Response{
	private List<InventarioMobile> inventarios;
	
	@XmlElementWrapper(name = "inventarios")
    @XmlElement(name = "inventario", nillable = true)
	public List<InventarioMobile> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<InventarioMobile> inventarios) {
		this.inventarios = inventarios;
	}
}
