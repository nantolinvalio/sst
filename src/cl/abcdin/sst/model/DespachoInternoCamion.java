package cl.abcdin.sst.model;

public class DespachoInternoCamion {

	private Long id;
	private DespachoInterno despachoInterno;
	
	public DespachoInterno getDespachoInterno() {
		return despachoInterno;
	}
	public void setDespachoInterno(DespachoInterno despachoInterno) {
		this.despachoInterno = despachoInterno;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
