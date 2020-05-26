package cl.abcdin.sst.model;

public class PeticionDocumentoDetalle {

	private Long id;
	private PeticionDocumento peticionDocumento;
	private OrdenTrabajo ordenTrabajo;
	private Long cantidad;
//	private Long idProducto;
//	private Integer porcentaje;
//	
//	public Integer getPorcentaje() {
//		return porcentaje;
//	}
//	public void setPorcentaje(Integer porcentaje) {
//		this.porcentaje = porcentaje;
//	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PeticionDocumento getPeticionDocumento() {
		return peticionDocumento;
	}
	public void setPeticionDocumento(PeticionDocumento peticionDocumento) {
		this.peticionDocumento = peticionDocumento;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
}
