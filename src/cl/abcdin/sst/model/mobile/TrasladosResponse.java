package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class TrasladosResponse extends Response{
	private List<TrasladoMobile> traslados;

	@XmlElementWrapper(name="traslados")
	@XmlElement(name="traslado", nillable = true)
	public List<TrasladoMobile> getTraslados() {
		return traslados;
	}

	public void setTraslados(List<TrasladoMobile> traslados) {
		this.traslados = traslados;
	}
}
