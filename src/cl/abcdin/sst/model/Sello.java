package cl.abcdin.sst.model;

public class Sello {
	
	private Long id;
	private String numero;
	private DespachoInternoCamion despachoInternoCamion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public DespachoInternoCamion getDespachoInternoCamion() {
		return despachoInternoCamion;
	}
	public void setDespachoInternoCamion(DespachoInternoCamion despachoInternoCamion) {
		this.despachoInternoCamion = despachoInternoCamion;
	}
}
