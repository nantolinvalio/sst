package cl.abcdin.sst.model.vo;

import org.apache.commons.lang.StringUtils;


public class OWCabecera {
	private Long id;
	private String origenCC;
	private String destinoCC;
	private String origenUB;
	private String destinoUB;
	private Integer numeroDocumento;
	private String tipoDocumento;
	private String observacion;
	private String usuario;
	private String tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrigenCC() {
		return origenCC;
	}
	public void setOrigenCC(String origenCC) {
		this.origenCC = StringUtils.leftPad(origenCC,12,' ');
	}
	public void setOrigenCC(Integer origenCC) {
		this.origenCC = StringUtils.leftPad(origenCC.toString(),12,' ');
	}
	public void setOrigenCC(Long origenCC) {
		this.origenCC = StringUtils.leftPad(origenCC.toString(),12,' ');
	}
	
	public String getOrigenUB() {
		return origenUB;
	}
	public void setOrigenUB(String origenUB) {
		this.origenUB = origenUB;
	}
	public String getDestinoUB() {
		return destinoUB;
	}
	public void setDestinoUB(String destinoUB) {
		this.destinoUB = destinoUB;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento.intValue();
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDestinoCC() {
		return destinoCC;
	}
	public void setDestinoCC(String destinoCC) {
		this.destinoCC = destinoCC;
	}
	
	public void setDestinoCC(Long destinoCC) {
		this.destinoCC = destinoCC.toString();
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
