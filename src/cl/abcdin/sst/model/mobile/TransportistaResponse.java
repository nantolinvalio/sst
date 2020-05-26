package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class TransportistaResponse extends Response {
	
	private List<TransportistaMobile> transportista;
	
	@XmlElementWrapper(name = "transportistas")
    @XmlElement(name = "transportista", nillable = true)
	public List<TransportistaMobile> getTransportista() {
		return transportista;
	}

	public void setTransportista(List<TransportistaMobile> transportista) {
		this.transportista = transportista;
	}
}
