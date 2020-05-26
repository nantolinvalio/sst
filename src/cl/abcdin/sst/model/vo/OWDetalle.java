package cl.abcdin.sst.model.vo;

public class OWDetalle {
	private Long id;
	private Integer producto;
	private Integer cantidad;
	private Integer porcentaje;
	private String destinoCC;
	private String ubicacionTO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad.intValue();
	}
	public Integer getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getDestinoCC() {
		return destinoCC;
	}
	public void setDestinoCC(String destinoCC) {
		this.destinoCC = destinoCC;
	}
	public String getUbicacionTO() {
		return ubicacionTO;
	}
	public void setUbicacionTO(String ubicacionTO) {
		this.ubicacionTO = ubicacionTO;
	}	
}
