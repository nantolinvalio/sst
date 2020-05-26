package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class DespachoResponse extends Response{
	private List<DespachoMobile> despachos;

	@XmlElementWrapper(name = "despachos")
    @XmlElement(name = "despacho", nillable = true)
	public List<DespachoMobile> getDespachos() {
		return despachos;
	}

	public void setDespachos(List<DespachoMobile> despachos) {
		this.despachos = despachos;
	}
}
