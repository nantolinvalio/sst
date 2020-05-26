package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class SucursalResponse extends Response{
	private List<SucursalMobile> Despachos;

	@XmlElement(name="despachos",nillable=true)
	public List<SucursalMobile> getDespachos() {
		return Despachos;
	}

	public void setDespachos(List<SucursalMobile> despachos) {
		Despachos = despachos;
	}
}
