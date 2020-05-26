package cl.abcdin.sst.model.mobile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class Response {
	private Long codigo;
	private String glosa;
	
	public Response(){}
	
	public Response(Long codigo, String glosa){
		this.codigo = codigo;
		this.glosa = glosa;
	}
	@XmlElement(nillable = true)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	@XmlElement(nillable = true)
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	
}
