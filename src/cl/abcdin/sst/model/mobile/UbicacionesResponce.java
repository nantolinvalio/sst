package cl.abcdin.sst.model.mobile;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Response")
public class UbicacionesResponce extends Response {
	private List<UbicacionMobile> ubicaciones;

	@XmlElementWrapper(name="ubicaciones")
	@XmlElement(name="ubicacion", nillable = true)
	public List<UbicacionMobile> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<UbicacionMobile> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

}
